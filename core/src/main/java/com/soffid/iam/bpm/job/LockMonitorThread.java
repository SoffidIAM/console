package com.soffid.iam.bpm.job;

import java.util.Date;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.bpm.servei.BpmJobExecutor;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.exception.InternalErrorException;

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
					log.info(String.format(Messages.getString("LockMonitorThread.LockMonitorThreadInterrupted"), getName()));  //$NON-NLS-1$
				} catch (Exception e) {
					log.error(String.format(Messages.getString("LockMonitorThread.LockMonitorThreadWaitingException"),  //$NON-NLS-1$
							lockMonitorInterval), e);
					try {
						sleep(lockMonitorInterval);
					} catch (InterruptedException e2) {
						log.debug(Messages.getString("LockMonitorThread.DelayAfterException"), e2); //$NON-NLS-1$
					}
				}
			}
		} catch (Exception e) {
			log.error(Messages.getString("LockMonitorThread.LockMonitorThreadException"), e); //$NON-NLS-1$
		} finally {
			log.info(String.format(Messages.getString("LockMonitorThread.LeavesCyberspace"), getName()));  //$NON-NLS-1$
		}
	}

	protected void unlockOverdueJobs() throws NamingException, CreateException, InternalErrorException {
		BpmJobExecutor executor = ServiceLocator.instance().getBpmJobExecutor();
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

	private static Log log = LogFactory.getLog(LockMonitorThread.class);
}
