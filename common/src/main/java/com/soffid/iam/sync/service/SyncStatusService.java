//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service SyncStatusService
 */
public interface SyncStatusService {
	public final static String REMOTE_PATH = "SEU/SyncStatusService";

	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.SyncStatusService";

	/**
	 * Operation getTasksGrid

	 * @return 
	 */
	com.soffid.iam.base.api.TasksGrid getTasksGrid()
			throws com.soffid.iam.exception.InternalErrorException;

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
	 * Operation getTasks

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.SyncAgentTaskLog> getTasks(
		final com.soffid.zkdb.api.Query q)
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
	 * Operation findActiveDirectoryDomains

	 * @return 
	 */
	java.util.Map<java.lang.String,java.lang.String> findActiveDirectoryDomains()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getMazingerRules

	 * @param user 
	 * @return 
	 */
	byte[] getMazingerRules(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountPassword

	 * @param user 
	 * @param accountId 
	 * @return 
	 */
	com.soffid.iam.am.api.Password getAccountPassword(
		final java.lang.String user, 
		final java.lang.Long accountId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountPassword
	 * Method user for SSO. Provides the password if the user has the right access level on the account

	 * @param user 
	 * @param accountId 
	 * @param level 
	 * @return 
	 */
	com.soffid.iam.am.api.Password getAccountPassword(
		final java.lang.String user, 
		final java.lang.Long accountId, 
		final com.soffid.iam.base.api.AccountAccessLevelEnum level)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountSshKey

	 * @param user 
	 * @param accountId 
	 * @return 
	 */
	com.soffid.iam.am.api.Password getAccountSshKey(
		final java.lang.String user, 
		final java.lang.Long accountId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountSshKey
	 * Method user for SSO. Provides the password if the user has the right access level on the account

	 * @param user 
	 * @param accountId 
	 * @param level 
	 * @return 
	 */
	com.soffid.iam.am.api.Password getAccountSshKey(
		final java.lang.String user, 
		final java.lang.Long accountId, 
		final com.soffid.iam.base.api.AccountAccessLevelEnum level)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkPasswordSynchronizationStatus

	 * @param accountName 
	 * @param serverName 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordValidation checkPasswordSynchronizationStatus(
		final java.lang.String accountName, 
		final java.lang.String serverName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation testPropagateObject

	 * @param dispatcher 
	 * @param type 
	 * @param object1 
	 * @param object2 
	 * @return 
	 */
	com.soffid.iam.sync.api.DebugTaskResults testPropagateObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getNativeObject

	 * @param dispatcher 
	 * @param type 
	 * @param object1 
	 * @param object2 
	 * @return 
	 */
	com.soffid.iam.sync.api.GetObjectResults getNativeObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSoffidObject

	 * @param dispatcher 
	 * @param type 
	 * @param object1 
	 * @param object2 
	 * @return 
	 */
	com.soffid.iam.sync.api.GetObjectResults getSoffidObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reconcile

	 * @param dispatcher 
	 * @param accountName 
	 * @return 
	 */
	com.soffid.iam.sync.api.GetObjectResults reconcile(
		final java.lang.String dispatcher, 
		final java.lang.String accountName)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSyncServerInfo

	 * @param tenant 
	 * @return 
	 */
	com.soffid.iam.sync.api.SyncServerInfo getSyncServerInfo(
		final java.lang.String tenant)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSyncServerInfo

	 * @param server 
	 * @param tenant 
	 * @return 
	 */
	com.soffid.iam.sync.api.SyncServerInfo getSyncServerInfo(
		final java.lang.String server, 
		final java.lang.String tenant)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSyncServerStatus

	 * @param tenant 
	 * @return 
	 */
	com.soffid.iam.sync.api.SyncServerInfo getSyncServerStatus(
		final java.lang.String tenant)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSyncServerStatus

	 * @param server 
	 * @param tenant 
	 * @return 
	 */
	com.soffid.iam.sync.api.SyncServerInfo getSyncServerStatus(
		final java.lang.String server, 
		final java.lang.String tenant)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDBConnectionStatus

	 * @return 
	 */
	java.lang.String getDBConnectionStatus()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation resetAllServer

	 * @return 
	 */
	java.lang.String resetAllServer()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation resetServerAgents

	 * @param server 
	 * @return 
	 */
	java.lang.String resetServerAgents(
		final java.lang.String server)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation tailServerLog

	 * @param urlServer 
	 * @return 
	 */
	java.lang.String[] tailServerLog(
		final java.lang.String urlServer)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getServerAgentHostsURL

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getServerAgentHostsURL()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.FileNotFoundException, java.io.IOException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSyncAgentsInfo

	 * @param tenant 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getSyncAgentsInfo(
		final java.lang.String tenant)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation invoke
	 * Invokes a custom method

	 * @param dispatcher 
	 * @param verb 
	 * @param object 
	 * @param attributes 
	 * @return 
	 */
	java.util.Collection<java.util.Map<java.lang.String,java.lang.Object>> invoke(
		final java.lang.String dispatcher, 
		final java.lang.String verb, 
		final java.lang.String object, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation assignTemporaryPermissions

	 * @param host 
	 * @param accountName 
	 * @param accountSystem 
	 * @param permissions 
	 * @return 
	 */
	java.util.List<java.lang.String> assignTemporaryPermissions(
		final java.lang.String host, 
		final java.lang.String accountName, 
		final java.lang.String accountSystem, 
		final java.util.List<java.lang.String> permissions)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation testObjectMapping

	 * @param sentences 
	 * @param dispatcher 
	 * @param type 
	 * @param object1 
	 * @param object2 
	 * @return 
	 */
	java.util.Map<java.lang.String,java.lang.Object> testObjectMapping(
		final java.util.Map<java.lang.String,java.lang.String> sentences, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

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
	 * Operation checkConnectivity

	 * @param dispatcher 
	 */
	void checkConnectivity(
		final java.lang.String dispatcher)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reconfigureDispatchers

	 */
	void reconfigureDispatchers()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.io.IOException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeTemporaryPermissions

	 * @param host 
	 * @param accountName 
	 * @param accountSystem 
	 * @param permissions 
	 */
	void removeTemporaryPermissions(
		final java.lang.String host, 
		final java.lang.String accountName, 
		final java.lang.String accountSystem, 
		final java.util.List<java.lang.String> permissions)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation resendAccountPassword

	 * @param accountId 
	 */
	void resendAccountPassword(
		final java.lang.Long accountId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation resendUserPassword

	 * @param user 
	 * @param passwordDomain 
	 */
	void resendUserPassword(
		final java.lang.String user, 
		final java.lang.String passwordDomain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setAccountPassword

	 * @param accountName 
	 * @param serverName 
	 * @param password 
	 * @param mustChange 
	 */
	void setAccountPassword(
		final java.lang.String accountName, 
		final java.lang.String serverName, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setAccountSshPrivateKey

	 * @param accountName 
	 * @param serverName 
	 * @param privateKey 
	 */
	void setAccountSshPrivateKey(
		final java.lang.String accountName, 
		final java.lang.String serverName, 
		final com.soffid.iam.am.api.Password privateKey)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation startScheduledTask

	 * @param t 
	 */
	void startScheduledTask(
		final com.soffid.iam.sync.api.ScheduledTask t)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation stopScheduledTask

	 * @param t 
	 */
	void stopScheduledTask(
		final com.soffid.iam.sync.api.ScheduledTask t)
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
