package com.soffid.iam.bpm.job;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class JobExecutor {
	private static final long serialVersionUID = 1L;

	protected String name;
	protected int nbrOfThreads;
	protected int idleInterval;
	protected int maxIdleInterval;
	protected int historyMaxSize;

	protected int maxLockTime;
	protected int lockMonitorInterval;
	protected int lockBufferTime;

	protected Map threads = new HashMap();
	protected LockMonitorThread lockMonitorThread;
	protected Map monitoredJobIds = Collections.synchronizedMap(new HashMap());

	protected boolean isStarted = false;

	protected static String hostName;

	public synchronized void start() {
		if (!isStarted) {
			log.debug(String.format(Messages.getString("JobExecutor.StartingThreadGroup"), name)); //$NON-NLS-1$
			for (int i = 0; i < nbrOfThreads; i++) {
				startThread();
			}
			lockMonitorThread = new LockMonitorThread(
					lockMonitorInterval, maxLockTime, lockBufferTime);
			isStarted = true;
		} else {
			log.debug(String.format(Messages.getString("JobExecutor.IgnoringStartingThreadGroup"), name));  //$NON-NLS-1$
		}
	}

	/**
	 * signals to all threads in this job executor to stop. It may be that
	 * threads are in the middle of something and they will finish that firts.
	 * Use {@link #stopAndJoin()} in case you want a method that blocks until
	 * all the threads are actually finished.
	 * 
	 * @return a list of all the stopped threads. In case no threads were
	 *         stopped an empty list will be returned.
	 */
	public synchronized List stop() {
		List stoppedThreads = new ArrayList(threads.size());
		if (isStarted) {
			log.debug(String.format(Messages.getString("JobExecutor.StoppingThreadGroup"), name)); //$NON-NLS-1$
			for (int i = 0; i < nbrOfThreads; i++) {
				stoppedThreads.add(stopThread());
			}
			lockMonitorThread.deactivate();
			isStarted = false;
		} else {
			log.debug(String.format(Messages.getString("JobExecutor.IgnoringStopThreadGroup"), name));   //$NON-NLS-1$
		}
		return stoppedThreads;
	}

	public void stopAndJoin() throws InterruptedException {
		Iterator iter = stop().iterator();
		while (iter.hasNext()) {
			Thread thread = (Thread) iter.next();
			thread.join();
		}
		if (lockMonitorThread != null)
			lockMonitorThread.join();
	}

	protected synchronized void startThread() {
		String threadName = getNextThreadName();
		Thread thread = createThread(threadName);
		threads.put(threadName, thread);
		log.debug(String.format(Messages.getString("JobExecutor.StartingNewExecutorThread"), threadName));   //$NON-NLS-1$
		thread.start();
	}

	protected Thread createThread(String threadName) {//Thread.currentThread()org.jboss.security.SecurityAssociation.getSubject()
		return new JobExecutorThread(threadName,
				this, 
				idleInterval, maxIdleInterval, maxLockTime, historyMaxSize);
	}

	protected String getNextThreadName() {
		return getThreadName(threads.size() + 1);
	}

	protected String getLastThreadName() {
		return getThreadName(threads.size());
	}

	private String getThreadName(int index) {
		return name + ":" + getHostName() + ":" + index; //$NON-NLS-1$ //$NON-NLS-2$
	}

	private static String getHostName() {
		if (hostName == null) {
			try {
				hostName = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				hostName = "127.0.0.1"; //$NON-NLS-1$
			}
		}
		return hostName;
	}

	protected synchronized Thread stopThread() {
		String threadName = getLastThreadName();
		JobExecutorThread thread = (JobExecutorThread) threads
				.remove(threadName);
		log.debug(String.format(Messages.getString("JobExecutor.RemovingExecutorThread"), threadName)); //$NON-NLS-1$
		thread.deactivate();
		return thread;
	}

	public void setMonitoredJobIds(Map monitoredJobIds) {
		this.monitoredJobIds = monitoredJobIds;
	}

	public Set getMonitoredJobIds() {
		return new HashSet(monitoredJobIds.values());
	}

	public void addMonitoredJobId(String threadName, long jobId) {
		monitoredJobIds.put(threadName, new Long(jobId));
	}

	public void removeMonitoredJobId(String threadName) {
		monitoredJobIds.remove(threadName);
	}

	public void setHistoryMaxSize(int historyMaxSize) {
		this.historyMaxSize = historyMaxSize;
	}

	public int getHistoryMaxSize() {
		return historyMaxSize;
	}

	public void setIdleInterval(int idleInterval) {
		this.idleInterval = idleInterval;
	}

	public int getIdleInterval() {
		return idleInterval;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setMaxIdleInterval(int maxIdleInterval) {
		this.maxIdleInterval = maxIdleInterval;
	}

	public int getMaxIdleInterval() {
		return maxIdleInterval;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSize(int nbrOfThreads) {
		this.nbrOfThreads = nbrOfThreads;
	}

	public int getSize() {
		return nbrOfThreads;
	}

	public void setThreads(Map threads) {
		this.threads = threads;
	}

	public Map getThreads() {
		return threads;
	}

	public void setMaxLockTime(int maxLockTime) {
		this.maxLockTime = maxLockTime;
	}

	public int getMaxLockTime() {
		return maxLockTime;
	}

	public void setLockBufferTime(int lockBufferTime) {
		this.lockBufferTime = lockBufferTime;
	}

	public int getLockBufferTime() {
		return lockBufferTime;
	}

	public void setLockMonitorInterval(int lockMonitorInterval) {
		this.lockMonitorInterval = lockMonitorInterval;
	}

	public int getLockMonitorInterval() {
		return lockMonitorInterval;
	}

	public void setNbrOfThreads(int nbrOfThreads) {
		this.nbrOfThreads = nbrOfThreads;
	}

	public int getNbrOfThreads() {
		return nbrOfThreads;
	}

	private static Log log = LogFactory.getLog(JobExecutor.class);
}
