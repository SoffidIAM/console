//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service TaskQueue
 * Cola de tareas pendientes de ejecución
 */
public interface TaskQueue {
	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.TaskQueue";

	/**
	 * Operation getTasksGrid

	 * @return 
	 */
	com.soffid.iam.base.api.TasksGrid getTasksGrid()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getTasks

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.SyncAgentTaskLog> getTasks(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isBestServer

	 * @return 
	 */
	boolean isBestServer()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation addTask

	 * @param newTask 
	 * @return 
	 */
	com.soffid.iam.sync.engine.TaskHandler addTask(
		final com.soffid.iam.sync.model.TaskEntity newTask)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findTaskHandlerById

	 * @param taskId 
	 * @return 
	 */
	com.soffid.iam.sync.engine.TaskHandler findTaskHandlerById(
		final long taskId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getNextPendingTask

	 * @param taskDispatcher 
	 * @param previousTask 
	 * @return 
	 */
	com.soffid.iam.sync.engine.TaskHandler getNextPendingTask(
		final com.soffid.iam.sync.engine.DispatcherHandler taskDispatcher, 
		final com.soffid.iam.sync.engine.TaskHandler previousTask)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPendingTask

	 * @param taskDispatcher 
	 * @return 
	 */
	com.soffid.iam.sync.engine.TaskHandler getPendingTask(
		final com.soffid.iam.sync.engine.DispatcherHandler taskDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation peekTaskToPersist

	 * @return 
	 */
	com.soffid.iam.sync.engine.TaskHandler peekTaskToPersist()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation countErrorTasks
	 * Number of errored tasks

	 * @param taskDispatcher 
	 * @return 
	 */
	int countErrorTasks(
		final com.soffid.iam.sync.engine.DispatcherHandler taskDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation countTasks

	 * @return 
	 */
	int countTasks()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation countTasks

	 * @param taskDispatcher 
	 * @return 
	 */
	int countTasks(
		final com.soffid.iam.sync.engine.DispatcherHandler taskDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation debugTask

	 * @param task 
	 * @return 
	 */
	java.util.Map<java.lang.String,com.soffid.iam.sync.api.DebugTaskResults> debugTask(
		final com.soffid.iam.sync.engine.TaskHandler task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation processOBTask

	 * @param task 
	 * @return 
	 */
	java.util.Map<java.lang.String,java.lang.Exception> processOBTask(
		final com.soffid.iam.sync.engine.TaskHandler task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation addTask

	 * @param newTask 
	 */
	void addTask(
		final com.soffid.iam.sync.engine.TaskHandler newTask)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation cancelTask

	 * @param taskId 
	 * @param hash 
	 */
	void cancelTask(
		final long taskId, 
		final java.lang.String hash)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation expireTasks

	 */
	void expireTasks()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation notifyTaskStatus

	 * @param task 
	 * @param taskDispatcher 
	 * @param bOK 
	 * @param sReason 
	 * @param t 
	 */
	void notifyTaskStatus(
		final com.soffid.iam.sync.engine.TaskHandler task, 
		final com.soffid.iam.sync.engine.DispatcherHandler taskDispatcher, 
		final boolean bOK, 
		final java.lang.String sReason, 
		final java.lang.Throwable t)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation notifyTaskStatusNewTransaction

	 * @param task 
	 * @param taskDispatcher 
	 * @param bOK 
	 * @param sReason 
	 * @param t 
	 */
	void notifyTaskStatusNewTransaction(
		final com.soffid.iam.sync.engine.TaskHandler task, 
		final com.soffid.iam.sync.engine.DispatcherHandler taskDispatcher, 
		final boolean bOK, 
		final java.lang.String sReason, 
		final java.lang.Throwable t)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation persistTask

	 * @param newTask 
	 */
	void persistTask(
		final com.soffid.iam.sync.engine.TaskHandler newTask)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation pushTaskToPersist

	 * @param newTask 
	 */
	void pushTaskToPersist(
		final com.soffid.iam.sync.engine.TaskHandler newTask)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerServerInstance
	 * Registers a kubernetes sync server instance

	 * @param name 
	 * @param url 
	 */
	void registerServerInstance(
		final java.lang.String name, 
		final java.lang.String url)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeTask

	 * @param task 
	 */
	void removeTask(
		final com.soffid.iam.sync.model.TaskEntity task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateServerInstanceTasks

	 */
	void updateServerInstanceTasks()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateTask

	 * @param task 
	 */
	void updateTask(
		final com.soffid.iam.sync.model.TaskEntity task)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation cleanupCompletedTasks

	 * @param dispatcher 
	 */
	void cleanupCompletedTasks(
		final com.soffid.iam.sync.engine.DispatcherHandler dispatcher)
			throws com.soffid.iam.exception.InternalErrorException;

}
