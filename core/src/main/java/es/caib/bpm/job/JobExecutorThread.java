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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.jboss.security.SecurityAssociation;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.JobSession;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.job.Job;
import org.jbpm.svc.Services;

import es.caib.bpm.servei.BpmJobExecutor;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.SeyconServiceLocator;

public class JobExecutorThread extends NotLoggedThread {
	private static Log log = LogFactory.getLog(JobExecutorThread.class); 
	BpmJobExecutor executor = null;
	
	BpmJobExecutor getExecutor () throws CreateException, NamingException {
		if (executor == null ) {
			executor = SeyconServiceLocator.instance().getBpmJobExecutor();
			
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
						log.info (String.format(Messages.getString("JobExecutorThread.ExecutingJob"), l.longValue()));  //$NON-NLS-1$
						try {
							executor.executeJob(l.longValue());
						} catch (Exception e) {
							executor.anotateFailure(l.longValue(), e);
						}
					}

				} else { // no jobs acquired
					log.info(Messages.getString("JobExecutorThread.NoJobToExecute")); //$NON-NLS-1$
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
				if (isActive)
					log.info(String.format(Messages.getString("JobExecutorThread.JobExecutorThreadInterrupted"), //$NON-NLS-1$
						(isActive ? "active" : "inactive"), getName())); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (Exception e) {
				if (isActive)
				{
					log.error(String.format(Messages.getString("JobExecutorThread.ExceptionInJobExecutor"), //$NON-NLS-1$
						currentIdleInterval), e); 
    				try {
    					synchronized (jobExecutor) {
    						jobExecutor.wait(currentIdleInterval);
    					}
    				} catch (InterruptedException e2) {
    					if (isActive)
    						log.debug(Messages.getString("JobExecutorThread.DelayAfterException"), e2); //$NON-NLS-1$
    				}
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
		log.info(String.format(Messages.getString("JobExecutorThread.Leaves"), getName()));  //$NON-NLS-1$
	}

	protected void executeJob(Long id) {
		try {
			log.debug(String.format(Messages.getString("JobExecutorThread.Executing"), id));  //$NON-NLS-1$
			getExecutor().executeJob(id.longValue());
		} catch (Exception e) {
			log.info(String.format(Messages.getString("JobExecutorThread.ExecutingJobException"), id), e);  //$NON-NLS-1$
			try {
				executor.anotateFailure(id.longValue(), e);
			} catch (InternalErrorException e1) {
				log.warn (Messages.getString("JobExecutorThread.AnotatingFailureError"), e1); //$NON-NLS-1$
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