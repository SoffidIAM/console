//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service.ejb;
/**
 * EJB SyncServerService
 */
public interface SyncServerService

 {

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.LogFileEntry> findLogs(
		final java.lang.String server, 
		final com.soffid.iam.sync.api.LogConfiguration config, 
		final long firstRow, 
		final long numRows)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.sync.api.LogConfiguration> getServerLogs(
		final java.lang.String server)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.SyncAgentTaskLog getAgentTasks(
		final java.lang.String url, 
		final java.lang.String agentName, 
		final java.lang.Long taskId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.sync.api.SyncServerInfo getSyncServerInfo(
		final java.lang.String url)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.io.InputStream getSeyconServerLog(
		final java.lang.String urlServer)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String[] tailServerLog(
		final java.lang.String urlServer)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.sync.api.SyncserverTask> findUnscheduledTasks()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getAgentTasks(
		final java.lang.String url, 
		final java.lang.String agentCodi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getPendingTasksInfo()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getServerAgentStatus()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getServerAgentStatus(
		final java.lang.String url)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<java.lang.Object> getServerTasks(
		final java.lang.String url)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getSyncServersStatus()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.sync.api.Server> getSyncServerInstances()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.sync.api.Server> getSyncServers()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Map<java.lang.String,java.util.Vector<java.lang.Object[]>> getPendingTasksStats()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Map<java.lang.String,int[]> getStats(
		final java.lang.String server, 
		final java.lang.String metric, 
		final int seconds, 
		final int step)
	throws com.soffid.iam.exception.InternalErrorException;

	void boostTask(
		final long taskId)
	throws com.soffid.iam.exception.InternalErrorException;

	void cancelTask(
		final long taskId)
	throws com.soffid.iam.exception.InternalErrorException;

	void cancelUnscheduledTasks()
	throws com.soffid.iam.exception.InternalErrorException;

	void releaseAllTasks()
	throws com.soffid.iam.exception.InternalErrorException;

	void releaseTask(
		final long taskId)
	throws com.soffid.iam.exception.InternalErrorException;

	void resetSyncServer(
		final java.lang.String url, 
		final java.lang.String server)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void configureLog(
		final java.lang.String server, 
		final com.soffid.iam.sync.api.LogConfiguration config)
	throws com.soffid.iam.exception.InternalErrorException;

}
