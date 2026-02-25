//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service.ejb;
/**
 * EJB ScheduledTaskService
 */
public interface ScheduledTaskService

 {

	boolean isStopping(
		final com.soffid.iam.sync.api.ScheduledTask task)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.ScheduledTask create(
		final com.soffid.iam.sync.api.ScheduledTask task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.ScheduledTask findById(
		final java.lang.Long id)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.ScheduledTask load(
		final java.lang.Long taskId)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.ScheduledTask update(
		final com.soffid.iam.sync.api.ScheduledTask task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.sync.api.ScheduledTask> listEnabledTasks()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.sync.api.ScheduledTask> listServerTasks(
		final java.lang.String server)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.sync.api.ScheduledTask> listTasks()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void remove(
		final com.soffid.iam.sync.api.ScheduledTask task)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void startNow(
		final com.soffid.iam.sync.api.ScheduledTask task)
	throws com.soffid.iam.exception.InternalErrorException;

	void stop(
		final com.soffid.iam.sync.api.ScheduledTask task)
	throws com.soffid.iam.exception.InternalErrorException;

}
