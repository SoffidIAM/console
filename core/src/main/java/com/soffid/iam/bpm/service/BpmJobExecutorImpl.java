package com.soffid.iam.bpm.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.jbpm.JbpmContext;
import org.jbpm.db.JobSession;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.job.Job;

import com.soffid.iam.bpm.config.Configuration;
import com.soffid.iam.bpm.index.Indexer;
import com.soffid.iam.bpm.model.TenantModule;

import es.caib.seycon.ng.exception.SoffidStackTrace;
import es.caib.seycon.ng.utils.Security;

public class BpmJobExecutorImpl extends BpmJobExecutorBase {
	static Integer lock = new Integer(10001);
	private Log log;

	public BpmJobExecutorImpl () {
		log = LogFactory.getLog(getClass());
	}
	@Override
	protected List handleGetJobs(String lockOwner) throws Exception {
		Vector jobs = new Vector();
		Collection acquiredJobs;
		synchronized (lock) {
			log.debug(Messages.getString("BpmJobExecutorImpl.AcquiringJobs")); //$NON-NLS-1$
			List jobsToLock = Collections.EMPTY_LIST;
			JbpmContext jbpmContext = getContext();
			try {
				JobSession jobSession = jbpmContext.getJobSession();
				log.debug(Messages.getString("BpmJobExecutorImpl.QueryingAcquirableJob")); //$NON-NLS-1$
				Job job = jobSession.getFirstAcquirableJob(lockOwner);
				if (job != null) {
					Date lockTime = new Date();
					job.setLockOwner(lockOwner);
					job.setLockTime(lockTime);
					jbpmContext.getSession().update(job);
					jobs.add(new Long(job.getId()));
				} else {
					log.debug(Messages.getString("BpmJobExecutorImpl.NoAcquirableJobs")); //$NON-NLS-1$
				}
			} finally {
				jbpmContext.close();
			}
		}
		return jobs;
	}
	private JbpmContext getContext() {
		return Configuration.getConfig().createJbpmContext();
	}

	@Override
	protected void handleExecuteJob(long id) throws Exception {
		JbpmContext jbpmContext = getContext();
		try {
			JobSession jobSession = jbpmContext.getJobSession();
			Job job = jobSession.loadJob(id);
			ProcessInstance pi = job.getProcessInstance();
			TenantModule tm = (TenantModule) pi.getInstance(TenantModule.class);
			String tenant = tm != null? Security.getTenantName(tm.getTenantId()): Security.getMasterTenantName();
			Security.nestedLogin(tenant, "Process "+pi.getId(), Security.ALL_PERMISSIONS);
			try
			{
				jbpmContext.getSession().lock(job, LockMode.UPGRADE);
				boolean success = job.execute(jbpmContext);
				if (success)
					jobSession.deleteJob(job);
				else
				{
					job.setLockOwner(null);
					job.setLockTime(null);
					jobSession.saveJob(job);
				}
			} finally {
				Security.nestedLogoff();
			}
		} finally {
			try {
				jbpmContext.close();
			} catch (Exception e) {
				log.warn(String.format(Messages.getString("BpmJobExecutorImpl.ErrorClosingSession"), e.toString())); //$NON-NLS-1$
			}
		}
	}

	@Override
	protected void handleAnotateFailure(long id, Exception e) throws Exception {
		JbpmContext jbpmContext = getContext();
		try {
			JobSession jobSession = jbpmContext.getJobSession();
			Job job = jobSession.loadJob(id);
			log.debug(String.format(Messages.getString("BpmJobExecutorImpl.ExceptionWhileExecuting"), job), e);  //$NON-NLS-1$
			StringWriter memoryWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(memoryWriter));
			job.setException(memoryWriter.toString());
			if ( ! "true".equals(System.getProperty("soffid.bpm.error.retry"))) //$NON-NLS-1$ //$NON-NLS-2$
					job.setRetries(job.getRetries() - 1);
			Date d = new Date (System.currentTimeMillis()+10 * 60 * 1000); // Esperar deu minuts
			job.setDueDate(d);
			job.setLockOwner(null);
			job.setLockTime(null);
			jbpmContext.getSession().update(job);

			String mailNotification = System.getProperty("soffid.bpm.error.notify"); //$NON-NLS-1$
			if (mailNotification != null)
			{
				StringBuffer body = new StringBuffer();
				com.soffid.iam.bpm.api.ProcessInstance pi = getBpmEngine().getProcess(job.getId());
				body.append(String.format(Messages.getString("BpmJobExecutorImpl.3"), //$NON-NLS-1$
						job.getId(),
						job.getId(),
						pi.getDescription()))
					.append("\n"); //$NON-NLS-1$
				if ( job.getRetries() >= 0)
				{
					body.append(String.format(Messages.getString("BpmJobExecutorImpl.5"), job.getDueDate())) //$NON-NLS-1$
						.append("\n"); //$NON-NLS-1$
				}
				body.append(Messages.getString("BpmJobExecutorImpl.7")) //$NON-NLS-1$
					.append("\n"); //$NON-NLS-1$
				body.append( SoffidStackTrace.getStackTrace(e) );
				getMailService().sendHtmlMailToActors(mailNotification.split("\\s*,\\s*"), //$NON-NLS-1$
						String.format(Messages.getString("BpmJobExecutorImpl.10"), job.getProcessInstance().getId()), //$NON-NLS-1$
						body.toString());
			}
		} finally {
			jbpmContext.close();
		}
	}

	@Override
	protected Date handleGetNextDueDate(String lockOwner) throws Exception {
		JbpmContext jbpmContext = getContext();
		try {
			Date nextDueDate = null;
			JobSession jobSession = jbpmContext.getJobSession();
			Job job = jobSession.getFirstDueJob(lockOwner, null);
			if (job != null) {
				nextDueDate = job.getDueDate();
			}
			return nextDueDate;
		} finally {
			jbpmContext.close();
		}
	}

	@Override
	protected void handleUnlockOverdueJobs(Date threshold) throws Exception {
		JbpmContext jbpmContext = getContext();
		try {
			JobSession jobSession = jbpmContext.getJobSession();

			List jobsWithOverdueLockTime = jobSession
					.findJobsWithOverdueLockTime(threshold);
			Iterator iter = jobsWithOverdueLockTime.iterator();
			while (iter.hasNext()) {
				Job job = (Job) iter.next();
				// unlock
				log.info(String.format(Messages.getString("BpmJobExecutorImpl.UnlockinByThread"), job, job.getLockOwner())); //$NON-NLS-1$
				job.setLockOwner(null);
				job.setLockTime(null);
				jobSession.saveJob(job);
			}
		} finally {
			jbpmContext.close();
		}
	}

	long last = System.currentTimeMillis();

	@Override
	protected void handleIndexPendingProcesses() throws Exception {
		long newExecution = System.currentTimeMillis();
		Indexer i = Indexer.getIndexer ();
		JbpmContext ctx = getContext();
		Long nextProcess = null;
		do {
			ctx = getContext();
			try {
				nextProcess = i.flush(ctx.getSession(), last, newExecution, nextProcess, 100);
				if (nextProcess == null)
					last = newExecution;
			} catch (Exception e) {
				log.warn(Messages.getString("BpmJobExecutorImpl.IndexingError"), e); //$NON-NLS-1$
			} finally {
				ctx.close();
			}
		} while (nextProcess != null);
	}
	@Override
	protected boolean handleLockJob(long id, String lockOwner) throws Exception {
		Vector jobs = new Vector();
		Collection acquiredJobs;
		synchronized (lock) {
			JbpmContext jbpmContext = getContext();
			try {
				JobSession jobSession = jbpmContext.getJobSession();
				Job job = jobSession.getJob(id);
				if (job != null) {
					jbpmContext.getSession().lock(job, LockMode.FORCE);
					if (lockOwner.equals(job.getLockOwner())) {
						job.setLockTime(new Date());
						jbpmContext.getSession().update(job);
						return true;
					}
				} else {
					log.debug(Messages.getString("BpmJobExecutorImpl.NoAcquirableJobs")); //$NON-NLS-1$
				}
			} finally {
				jbpmContext.close();
			}
		}
		return false;
	}

}
