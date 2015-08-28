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
import org.jbpm.JbpmContext;
import org.jbpm.JbpmException;
import org.jbpm.db.JobSession;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.job.Job;

import com.soffid.iam.bpm.config.Configuration;
import com.soffid.iam.bpm.index.Indexer;

import es.caib.bpm.servei.BpmJobExecutorBase;

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
			JbpmContext jbpmContext = Configuration.getConfig()
					.createJbpmContext();
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

	@Override
	protected void handleExecuteJob(long id) throws Exception {
		JbpmContext jbpmContext = Configuration.getConfig().createJbpmContext();
		try {
			JobSession jobSession = jbpmContext.getJobSession();
			Job job = jobSession.loadJob(id);
			boolean success = job.execute(jbpmContext);
			if (success)
				jobSession.deleteJob(job);
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
		JbpmContext jbpmContext = Configuration.getConfig().createJbpmContext();
		try {
			JobSession jobSession = jbpmContext.getJobSession();
			Job job = jobSession.loadJob(id);
			log.debug(String.format(Messages.getString("BpmJobExecutorImpl.ExceptionWhileExecuting"), job), e);  //$NON-NLS-1$
			StringWriter memoryWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(memoryWriter));
			job.setException(memoryWriter.toString());
			job.setRetries(job.getRetries() - 1);
			Date d = new Date (System.currentTimeMillis()+10 * 60 * 1000); // Esperar deu minuts
			job.setDueDate(d);
			job.setLockOwner(null);
			jbpmContext.getSession().update(job);
		} finally {
			jbpmContext.close();
		}
	}

	@Override
	protected Date handleGetNextDueDate(String lockOwner) throws Exception {
		JbpmContext jbpmContext = Configuration.getConfig().createJbpmContext();
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
		JbpmContext jbpmContext = Configuration.getConfig().createJbpmContext();
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

	@Override
	protected void handleIndexPendingProcesses() throws Exception {
		Indexer i = Indexer.getIndexer ();
		JbpmContext ctx = Configuration.getConfig().createJbpmContext();
		try {
			i.flush(ctx.getSession());
		} catch (Exception e) {
			log.warn(Messages.getString("BpmJobExecutorImpl.IndexingError"), e); //$NON-NLS-1$
		} finally {
			ctx.close();
		}
	}
}
