package com.soffid.iam.bpm.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.JbpmException;
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
					if (job.isExclusive()) {
						log.debug(String.format(Messages.getString("BpmJobExecutorImpl.ExclusiveJobFounded"), job));  //$NON-NLS-1$
						ProcessInstance processInstance = job
								.getProcessInstance();
						log.debug(String.format(Messages.getString("BpmJobExecutorImpl.FindingExclusiveJobs"), processInstance)); //$NON-NLS-1$
						jobsToLock = jobSession.findExclusiveJobs(lockOwner,
								processInstance);
						log.debug(String.format(Messages.getString("BpmJobExecutorImpl.TryObtainExclusiveLocks"), jobsToLock, //$NON-NLS-1$
								processInstance)); 
					} else {
						log.debug(String.format(Messages.getString("BpmJobExecutorImpl.TryObtainLock"), job));  //$NON-NLS-1$
						jobsToLock = Collections.singletonList(job);
					}

					Date lockTime = new Date();
					for (Iterator iter = jobsToLock.iterator(); iter.hasNext();) {
						job = (Job) iter.next();
						job.setLockOwner(lockOwner);
						job.setLockTime(lockTime);
						jbpmContext.getSession().update(job);
						jobs.add(new Long(job.getId()));
					}

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
				boolean success = job.execute(jbpmContext);
				if (success)
					jobSession.deleteJob(job);
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
		
		try {
			i.flush(ctx.getSession(), last, newExecution);
			last = newExecution;
		} catch (Exception e) {
			log.warn(Messages.getString("BpmJobExecutorImpl.IndexingError"), e); //$NON-NLS-1$
		} finally {
			ctx.close();
		}
	}

}
