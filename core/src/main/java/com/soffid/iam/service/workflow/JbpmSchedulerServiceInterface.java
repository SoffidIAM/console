package com.soffid.iam.service.workflow;

public interface JbpmSchedulerServiceInterface {

	public void start();
	public void stop() throws InterruptedException;
	void setSchedulerThreads(int schedulerThreads);
	int getSchedulerThreads();
	void setMaxScheduledInterval(int maxScheduledInterval);
	int getMaxScheduledInterval();
	void setScheduledInterval(int scheduledInterval);
	int getScheduledInterval();
	boolean isStarted();
	void start(boolean delayed);
	
}
