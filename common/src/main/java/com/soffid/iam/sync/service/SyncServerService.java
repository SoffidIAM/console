//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service SyncServerService
 */
public interface SyncServerService {
	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.SyncServerService";

	/**
	 * Operation findLogs

	 * @param server 
	 * @param config 
	 * @param firstRow 
	 * @param numRows 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.LogFileEntry> findLogs(
		final java.lang.String server, 
		final com.soffid.iam.sync.api.LogConfiguration config, 
		final long firstRow, 
		final long numRows)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getServerLogs

	 * @param server 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.LogConfiguration> getServerLogs(
		final java.lang.String server)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAgentTasks

	 * @param url 
	 * @param agentName 
	 * @param taskId 
	 * @return 
	 */
	com.soffid.iam.sync.api.SyncAgentTaskLog getAgentTasks(
		final java.lang.String url, 
		final java.lang.String agentName, 
		final java.lang.Long taskId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSyncServerInfo

	 * @param url 
	 * @return 
	 */
	com.soffid.iam.sync.api.SyncServerInfo getSyncServerInfo(
		final java.lang.String url)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSeyconServerLog

	 * @param urlServer 
	 * @return 
	 */
	java.io.InputStream getSeyconServerLog(
		final java.lang.String urlServer)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getServerService
	 * Gets a remote interface for any server, using the console specific authorization key

	 * @param servicePath 
	 * @return 
	 */
	java.lang.Object getServerService(
		final java.lang.String servicePath)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation tailServerLog

	 * @param urlServer 
	 * @return 
	 */
	java.lang.String[] tailServerLog(
		final java.lang.String urlServer)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUnscheduledTasks

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.SyncserverTask> findUnscheduledTasks()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAgentTasks

	 * @param url 
	 * @param agentCodi 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getAgentTasks(
		final java.lang.String url, 
		final java.lang.String agentCodi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPendingTasksInfo

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getPendingTasksInfo()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getServerAgentStatus

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getServerAgentStatus()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getServerAgentStatus

	 * @param url 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getServerAgentStatus(
		final java.lang.String url)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getServerTasks

	 * @param url 
	 * @return 
	 */
	java.util.Collection<java.lang.Object> getServerTasks(
		final java.lang.String url)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSyncServersStatus

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getSyncServersStatus()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSyncServerInstances

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.Server> getSyncServerInstances()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSyncServers

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.Server> getSyncServers()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPendingTasksStats
	 * Returns a map with a list of pairs date - long for each agent. The key is the agent name. An special agent named 'Unscheduled' holds the not scheduled tasks

	 * @return 
	 */
	java.util.Map<java.lang.String,java.util.Vector<java.lang.Object[]>> getPendingTasksStats()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getStats

	 * @param server 
	 * @param metric 
	 * @param seconds 
	 * @param step 
	 * @return 
	 */
	java.util.Map<java.lang.String,int[]> getStats(
		final java.lang.String server, 
		final java.lang.String metric, 
		final int seconds, 
		final int step)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation boostTask

	 * @param taskId 
	 */
	void boostTask(
		final long taskId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation cancelTask

	 * @param taskId 
	 */
	void cancelTask(
		final long taskId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation cancelUnscheduledTasks

	 */
	void cancelUnscheduledTasks()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation releaseAllTasks

	 */
	void releaseAllTasks()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation releaseTask

	 * @param taskId 
	 */
	void releaseTask(
		final long taskId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation resetSyncServer

	 * @param url 
	 * @param server 
	 */
	void resetSyncServer(
		final java.lang.String url, 
		final java.lang.String server)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateDispatcherConfiguration
	 * Calls synchronization servers to notify dispatcher configuration changes

	 */
	void updateDispatcherConfiguration()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updatePendingTasks

	 */
	void updatePendingTasks()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation configureLog

	 * @param server 
	 * @param config 
	 */
	void configureLog(
		final java.lang.String server, 
		final com.soffid.iam.sync.api.LogConfiguration config)
			throws com.soffid.iam.exception.InternalErrorException;

}
