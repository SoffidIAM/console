package com.soffid.iam.service.workflow;

public interface JbpmSchedulerServiceInterface {

	public void start();
	public void stop() throws InterruptedException;
	
}
