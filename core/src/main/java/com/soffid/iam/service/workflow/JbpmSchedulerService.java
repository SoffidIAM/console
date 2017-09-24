package com.soffid.iam.service.workflow;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.PreRemove;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.bpm.index.IndexerThread;
import com.soffid.iam.bpm.job.JobExecutor;
import com.soffid.iam.deployer.DeployerService;

@Singleton(name = "JbpmSchedulerService")
@Startup
@javax.ejb.TransactionManagement(value = javax.ejb.TransactionManagementType.CONTAINER)
@javax.ejb.TransactionAttribute(value = javax.ejb.TransactionAttributeType.SUPPORTS)
public class JbpmSchedulerService {
	Log log = LogFactory.getLog(JbpmSchedulerService.class);

	@Resource
	private SessionContext context;

	JobExecutor scheduler = null;

	int scheduledInterval = 30000;
	int maxScheduledInterval = 30000;
	int schedulerThreads = 1;

	private IndexerThread indexerThread;

	@PostConstruct
	public void init() throws Exception {
		try {
			log.info("Starting");

			scheduler = new JobExecutor();
			scheduler.setName("BPM-Scheduler");
			scheduler.setIdleInterval(scheduledInterval);
			scheduler.setMaxIdleInterval(maxScheduledInterval);
			scheduler.setLockMonitorInterval(scheduledInterval * 10);
			scheduler.setMaxLockTime(60 * 60 * 1000); // Una Hora
			scheduler.setIdleInterval(scheduledInterval * 2);
			scheduler.setNbrOfThreads(schedulerThreads);

			indexerThread = new IndexerThread();
			indexerThread.setDelay(scheduledInterval);

			new DelayedThread().start();
			log.info("Started");
		} catch (Throwable e) {
			log.warn("Cannot start JBPM async thread", e);
		}
	}

	@PreDestroy
	public void stopService() throws Exception {
		if (indexerThread != null)
			indexerThread.setFinish(true);
		if (scheduler != null) {
			scheduler.stopAndJoin();
			scheduler = null;
		}
		if (indexerThread != null) {
			indexerThread.join();
			indexerThread = null;
		}
	}

	/**
	 * @return the scheduledInterval
	 */
	public int getScheduledInterval() {
		return scheduledInterval;
	}

	/**
	 * @param scheduledInterval
	 *            the scheduledInterval to set
	 */
	public void setScheduledInterval(int scheduledInterval) {
		this.scheduledInterval = scheduledInterval;
	}

	/**
	 * @return the maxScheduledInterval
	 */
	public int getMaxScheduledInterval() {
		return maxScheduledInterval;
	}

	/**
	 * @param maxScheduledInterval
	 *            the maxScheduledInterval to set
	 */
	public void setMaxScheduledInterval(int maxScheduledInterval) {
		this.maxScheduledInterval = maxScheduledInterval;
	}

	/**
	 * @return the schedulerThreads
	 */
	public int getSchedulerThreads() {
		return schedulerThreads;
	}

	/**
	 * @param schedulerThreads
	 *            the schedulerThreads to set
	 */
	public void setSchedulerThreads(int schedulerThreads) {
		this.schedulerThreads = schedulerThreads;
	}

	class DelayedThread extends Thread {
		public void run() {
			try {
				Thread.sleep(60000); // Un minuto de espera
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			scheduler.start();
			indexerThread.start();
		}
	}
}
