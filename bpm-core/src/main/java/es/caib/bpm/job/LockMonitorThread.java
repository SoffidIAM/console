package es.caib.bpm.job;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.JobSession;
import org.jbpm.job.Job;
import org.jbpm.persistence.JbpmPersistenceException;
import org.jbpm.svc.Services;

import es.caib.bpm.servei.BPMJobExecutor;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.SeyconServiceLocator;

public class LockMonitorThread extends NotLoggedThread {

	int lockMonitorInterval;
	int maxLockTime;
	int lockBufferTime;

	boolean isActive = true;

	public LockMonitorThread(int lockMonitorInterval, int maxLockTime,
			int lockBufferTime) {
		this.lockMonitorInterval = lockMonitorInterval;
		this.maxLockTime = maxLockTime;
		this.lockBufferTime = lockBufferTime;
	}

	public void run() {
		try {
			while (isActive) {
				try {
					unlockOverdueJobs();
					if ((isActive) && (lockMonitorInterval > 0)) {
						sleep(lockMonitorInterval);
					}
				} catch (InterruptedException e) {
					log.info("lock monitor thread '" + getName()
							+ "' got interrupted");
				} catch (Exception e) {
					log.error("exception in lock monitor thread. waiting "
							+ lockMonitorInterval + " milliseconds", e);
					try {
						sleep(lockMonitorInterval);
					} catch (InterruptedException e2) {
						log.debug("delay after exception got interrupted", e2);
					}
				}
			}
		} catch (Exception e) {
			log.error("exception in lock monitor thread", e);
		} finally {
			log.info(getName() + " leaves cyberspace");
		}
	}

	protected void unlockOverdueJobs() throws NamingException, CreateException, InternalErrorException {
		BPMJobExecutor executor = SeyconServiceLocator.instance().getBPMJobExecutor();
		Date threshold = new Date(System.currentTimeMillis() - maxLockTime
				- lockBufferTime);
		executor.unlockOverdueJobs(threshold);

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

	private static Logger log = Logger.getLogger(LockMonitorThread.class);
}
