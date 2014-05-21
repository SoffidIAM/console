package es.caib.bpm.servei;

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

import es.caib.bpm.config.Configuration;
import es.caib.bpm.index.Indexer;

public class BPMJobExecutorImpl extends BPMJobExecutorBase {
	static Integer lock = new Integer(10001);
	private Log log;

	public BPMJobExecutorImpl () {
		log = LogFactory.getLog(getClass());
	}
	@Override
	protected List handleGetJobs(String lockOwner) throws Exception {
		Vector jobs = new Vector();
		Collection acquiredJobs;
		synchronized (lock) {
			log.debug("acquiring jobs for execution...");
			List jobsToLock = Collections.EMPTY_LIST;
			JbpmContext jbpmContext = Configuration.getConfig()
					.createJbpmContext();
			try {
				JobSession jobSession = jbpmContext.getJobSession();
				log.debug("querying for acquirable job...");
				Job job = jobSession.getFirstAcquirableJob(lockOwner);
				if (job != null) {
					if (job.isExclusive()) {
						log.debug("found exclusive " + job);
						ProcessInstance processInstance = job
								.getProcessInstance();
						log.debug("finding other exclusive jobs for "
								+ processInstance);
						jobsToLock = jobSession.findExclusiveJobs(lockOwner,
								processInstance);
						log.debug("trying to obtain exclusive locks on "
								+ jobsToLock + " for " + processInstance);
					} else {
						log.debug("trying to obtain lock on " + job);
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
					log.debug("no acquirable jobs in job table");
				}
			} finally {
				jbpmContext.close();
			}
		}
		return jobs;
	}

	@Override
	protected Exception handleExecuteJob(long id) throws Exception {
		JbpmContext jbpmContext = Configuration.getConfig().createJbpmContext();
		try {
			JobSession jobSession = jbpmContext.getJobSession();
			Job job = jobSession.loadJob(id);
			boolean success = job.execute(jbpmContext);
			if (success)
				jobSession.deleteJob(job);
			return null;
		} catch (Exception e) {
			jbpmContext.setRollbackOnly();
			return e;
		} finally {
			jbpmContext.close();
		}
	}

	@Override
	protected void handleAnotateFailure(long id, Exception e) throws Exception {
		JbpmContext jbpmContext = Configuration.getConfig().createJbpmContext();
		try {
			JobSession jobSession = jbpmContext.getJobSession();
			Job job = jobSession.loadJob(id);
			log.debug("exception while executing " + job, e);
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
				log.info("unlocking " + job + " owned by thread "
						+ job.getLockOwner());
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
			log.warn("Error indexing", e);
		} finally {
			ctx.close();
		}
	}

}
