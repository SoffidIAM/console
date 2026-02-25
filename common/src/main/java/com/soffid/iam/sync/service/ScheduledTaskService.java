//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service ScheduledTaskService
 */
public interface ScheduledTaskService {
	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.ScheduledTaskService";

	/**
	 * Operation isStopping

	 * @param task 
	 * @return 
	 */
	boolean isStopping(
		final com.soffid.iam.sync.api.ScheduledTask task)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param task 
	 * @return 
	 */
	com.soffid.iam.sync.api.ScheduledTask create(
		final com.soffid.iam.sync.api.ScheduledTask task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findById
	 * Finds a scheduled task by handler and params

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.sync.api.ScheduledTask findById(
		final java.lang.Long id)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findScheduledTaskByHandlerAndParams
	 * Finds a scheduled task by handler and params

	 * @param handler 
	 * @param params 
	 * @return 
	 */
	com.soffid.iam.sync.api.ScheduledTask findScheduledTaskByHandlerAndParams(
		final java.lang.String handler, 
		final java.lang.String params)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation load

	 * @param taskId 
	 * @return 
	 */
	com.soffid.iam.sync.api.ScheduledTask load(
		final java.lang.Long taskId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param task 
	 * @return 
	 */
	com.soffid.iam.sync.api.ScheduledTask update(
		final com.soffid.iam.sync.api.ScheduledTask task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param handler 
	 * @return 
	 */
	com.soffid.iam.sync.api.ScheduledTaskHandler create(
		final com.soffid.iam.sync.api.ScheduledTaskHandler handler)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param handler 
	 * @return 
	 */
	com.soffid.iam.sync.api.ScheduledTaskHandler update(
		final com.soffid.iam.sync.api.ScheduledTaskHandler handler)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation listEnabledTasks

	 * @return 
	 */
	java.util.List<com.soffid.iam.sync.api.ScheduledTask> listEnabledTasks()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation listHandlers

	 * @return 
	 */
	java.util.List<com.soffid.iam.sync.api.ScheduledTaskHandler> listHandlers()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation listServerTasks

	 * @param server 
	 * @return 
	 */
	java.util.List<com.soffid.iam.sync.api.ScheduledTask> listServerTasks(
		final java.lang.String server)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation listTasks

	 * @return 
	 */
	java.util.List<com.soffid.iam.sync.api.ScheduledTask> listTasks()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerEndTask

	 * @param task 
	 */
	void registerEndTask(
		final com.soffid.iam.sync.api.ScheduledTask task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerStartTask

	 * @param task 
	 */
	void registerStartTask(
		final com.soffid.iam.sync.api.ScheduledTask task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation remove

	 * @param task 
	 */
	void remove(
		final com.soffid.iam.sync.api.ScheduledTask task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation remove

	 * @param handler 
	 */
	void remove(
		final com.soffid.iam.sync.api.ScheduledTaskHandler handler)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation startNow

	 * @param task 
	 */
	void startNow(
		final com.soffid.iam.sync.api.ScheduledTask task)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation stop

	 * @param task 
	 */
	void stop(
		final com.soffid.iam.sync.api.ScheduledTask task)
			throws com.soffid.iam.exception.InternalErrorException;

}
