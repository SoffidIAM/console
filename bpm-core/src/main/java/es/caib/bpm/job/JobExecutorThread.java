package es.caib.bpm.job;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.AccessControlContext;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.jboss.security.SecurityAssociation;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.JobSession;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.job.Job;
import org.jbpm.svc.Services;

import es.caib.bpm.servei.BPMJobExecutor;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.SeyconServiceLocator;

public class JobExecutorThread extends NotLoggedThread {
	private static Logger log = Logger.getLogger(JobExecutorThread.class); 
	BPMJobExecutor executor = null;
	
	BPMJobExecutor getExecutor () throws CreateException, NamingException {
		if (executor == null ) {
			executor = SeyconServiceLocator.instance().getBPMJobExecutor();
			
		}
		return executor;
	}
	
	
	public JobExecutorThread(String name, JobExecutor jobExecutor,
			int idleInterval,
			int maxIdleInterval, long maxLockTime, int maxHistory) 
	{
		super(name);
		this.jobExecutor = jobExecutor;
		this.idleInterval = idleInterval;
		this.maxIdleInterval = maxIdleInterval;
		this.maxLockTime = maxLockTime;

	}


	final JobExecutor jobExecutor;
	final int idleInterval;
	final int maxIdleInterval;
	final long maxLockTime;

	int currentIdleInterval;
	volatile boolean isActive = true;

	public void run() {
		currentIdleInterval = idleInterval;
		while (isActive) {
			try {//org.jboss.security.SecurityAssociation.getSubject()
				List acquiredJobs = getExecutor().getJobs(getName());
				
				if (!acquiredJobs.isEmpty()) {
					Iterator iter = acquiredJobs.iterator();
					while (iter.hasNext() && isActive) {
						Long l = (Long) iter.next();
						log.info ("Executing job "+l.longValue());
						Exception e = executor.executeJob(l.longValue());
						if (e != null)
							executor.anotateFailure(l.longValue(), e);
					}

				} else { // no jobs acquired
					log.info("No job to execute");
					if (isActive) {
						long waitPeriod = getWaitPeriod();
						if (waitPeriod > 0) {
							synchronized (jobExecutor) {
								jobExecutor.wait(waitPeriod);
							}
						}
					}
				}

				// no exception so resetting the currentIdleInterval
				currentIdleInterval = idleInterval;

			} catch (InterruptedException e) {
				log.info((isActive ? "active" : "inactive")
						+ " job executor thread '" + getName()
						+ "' got interrupted");
			} catch (Exception e) {
				log.error("exception in job executor thread. waiting "
						+ currentIdleInterval + " milliseconds", e);
				try {
					synchronized (jobExecutor) {
						jobExecutor.wait(currentIdleInterval);
					}
				} catch (InterruptedException e2) {
					log.debug("delay after exception got interrupted", e2);
				}
				// after an exception, the current idle interval is doubled to
				// prevent
				// continuous exception generation when e.g. the db is
				// unreachable
				currentIdleInterval <<= 1;
				if (currentIdleInterval > maxIdleInterval
						|| currentIdleInterval < 0) {
					currentIdleInterval = maxIdleInterval;
				}
			}
		}
		log.info(getName() + " leaves cyberspace");
	}

	protected void executeJob(Long id) {
		try {
			log.debug("executing " + id);
			getExecutor().executeJob(id.longValue());
		} catch (Exception e) {
			log.info("exception while executing " + id, e);
			try {
				executor.anotateFailure(id.longValue(), e);
			} catch (InternalErrorException e1) {
				log.warn ("Error anotating failure", e1);
			}
		}
	}

	private static boolean isPersistenceException(Throwable throwable) {
		do {
			if (throwable instanceof HibernateException)
				return true;
			throwable = throwable.getCause();
		} while (throwable != null);
		return false;
	}

	protected Date getNextDueDate() throws CreateException, NamingException, InternalErrorException {
		return getExecutor().getNextDueDate(getName());
	}

	protected long getWaitPeriod() throws CreateException, NamingException, InternalErrorException {
		long interval = currentIdleInterval;
		Date nextDueDate = getNextDueDate();
		if (nextDueDate != null) {
			long currentTime = System.currentTimeMillis();
			long nextDueTime = nextDueDate.getTime();
			if (nextDueTime < currentTime + currentIdleInterval) {
				interval = nextDueTime - currentTime;
			}
		}
		if (interval < 0) {
			interval = 0;
		}
		return interval;
	}

	/**
	 * Indicates that this thread should stop running. Execution will cease
	 * shortly afterwards.
	 */
	public void deactivate() {
		if (isActive) {
			isActive = false;
			interrupt();
		}
	}


}
