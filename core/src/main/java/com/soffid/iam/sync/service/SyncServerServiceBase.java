//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.sync.service.SyncServerService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.sync.service.SyncServerService
 */
public abstract class SyncServerServiceBase
	implements com.soffid.iam.sync.service.SyncServerService
 {
	private com.soffid.iam.base.model.ConfigEntityDao configEntityDao;

	/**
	 * Sets reference to <code>configEntityDao</code>.
	 */
	public void setConfigEntityDao (com.soffid.iam.base.model.ConfigEntityDao configEntityDao) {
		this.configEntityDao = configEntityDao;
	}

	/**
	 * Gets reference to <code>configEntityDao</code>.
	 */
	public com.soffid.iam.base.model.ConfigEntityDao getConfigEntityDao () {
		return configEntityDao;
	}

	private com.soffid.iam.base.service.ConfigurationService configurationService;

	/**
	 * Sets reference to <code>configurationService</code>.
	 */
	public void setConfigurationService (com.soffid.iam.base.service.ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	/**
	 * Gets reference to <code>configurationService</code>.
	 */
	public com.soffid.iam.base.service.ConfigurationService getConfigurationService () {
		return configurationService;
	}

	private com.soffid.iam.iga.service.DispatcherService dispatcherService;

	/**
	 * Sets reference to <code>dispatcherService</code>.
	 */
	public void setDispatcherService (com.soffid.iam.iga.service.DispatcherService dispatcherService) {
		this.dispatcherService = dispatcherService;
	}

	/**
	 * Gets reference to <code>dispatcherService</code>.
	 */
	public com.soffid.iam.iga.service.DispatcherService getDispatcherService () {
		return dispatcherService;
	}

	private com.soffid.iam.sync.model.ServerEntityDao serverEntityDao;

	/**
	 * Sets reference to <code>serverEntityDao</code>.
	 */
	public void setServerEntityDao (com.soffid.iam.sync.model.ServerEntityDao serverEntityDao) {
		this.serverEntityDao = serverEntityDao;
	}

	/**
	 * Gets reference to <code>serverEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ServerEntityDao getServerEntityDao () {
		return serverEntityDao;
	}

	private com.soffid.iam.sync.model.ServerInstanceEntityDao serverInstanceEntityDao;

	/**
	 * Sets reference to <code>serverInstanceEntityDao</code>.
	 */
	public void setServerInstanceEntityDao (com.soffid.iam.sync.model.ServerInstanceEntityDao serverInstanceEntityDao) {
		this.serverInstanceEntityDao = serverInstanceEntityDao;
	}

	/**
	 * Gets reference to <code>serverInstanceEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntityDao getServerInstanceEntityDao () {
		return serverInstanceEntityDao;
	}

	private com.soffid.iam.base.model.StatsEntityDao statsEntityDao;

	/**
	 * Sets reference to <code>statsEntityDao</code>.
	 */
	public void setStatsEntityDao (com.soffid.iam.base.model.StatsEntityDao statsEntityDao) {
		this.statsEntityDao = statsEntityDao;
	}

	/**
	 * Gets reference to <code>statsEntityDao</code>.
	 */
	public com.soffid.iam.base.model.StatsEntityDao getStatsEntityDao () {
		return statsEntityDao;
	}

	private com.soffid.iam.base.service.StatsService statsService;

	/**
	 * Sets reference to <code>statsService</code>.
	 */
	public void setStatsService (com.soffid.iam.base.service.StatsService statsService) {
		this.statsService = statsService;
	}

	/**
	 * Gets reference to <code>statsService</code>.
	 */
	public com.soffid.iam.base.service.StatsService getStatsService () {
		return statsService;
	}

	private com.soffid.iam.iga.model.SystemEntityDao systemEntityDao;

	/**
	 * Sets reference to <code>systemEntityDao</code>.
	 */
	public void setSystemEntityDao (com.soffid.iam.iga.model.SystemEntityDao systemEntityDao) {
		this.systemEntityDao = systemEntityDao;
	}

	/**
	 * Gets reference to <code>systemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.SystemEntityDao getSystemEntityDao () {
		return systemEntityDao;
	}

	private com.soffid.iam.sync.model.TaskEntityDao taskEntityDao;

	/**
	 * Sets reference to <code>taskEntityDao</code>.
	 */
	public void setTaskEntityDao (com.soffid.iam.sync.model.TaskEntityDao taskEntityDao) {
		this.taskEntityDao = taskEntityDao;
	}

	/**
	 * Gets reference to <code>taskEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.TaskEntityDao getTaskEntityDao () {
		return taskEntityDao;
	}

	private com.soffid.iam.sync.model.TaskLogEntityDao taskLogEntityDao;

	/**
	 * Sets reference to <code>taskLogEntityDao</code>.
	 */
	public void setTaskLogEntityDao (com.soffid.iam.sync.model.TaskLogEntityDao taskLogEntityDao) {
		this.taskLogEntityDao = taskLogEntityDao;
	}

	/**
	 * Gets reference to <code>taskLogEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.TaskLogEntityDao getTaskLogEntityDao () {
		return taskLogEntityDao;
	}

	private com.soffid.iam.base.model.TenantEntityDao tenantEntityDao;

	/**
	 * Sets reference to <code>tenantEntityDao</code>.
	 */
	public void setTenantEntityDao (com.soffid.iam.base.model.TenantEntityDao tenantEntityDao) {
		this.tenantEntityDao = tenantEntityDao;
	}

	/**
	 * Gets reference to <code>tenantEntityDao</code>.
	 */
	public com.soffid.iam.base.model.TenantEntityDao getTenantEntityDao () {
		return tenantEntityDao;
	}


	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.LogFileEntry> findLogs(java.lang.String server, com.soffid.iam.sync.api.LogConfiguration config, long firstRow, long numRows)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.LogFileEntry> findLogs(
		final java.lang.String server, 
		final com.soffid.iam.sync.api.LogConfiguration config, 
		final long firstRow, 
		final long numRows)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (server == null || server.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.LogFileEntry> com.soffid.iam.sync.service.SyncServerService.findLogs(java.lang.String server, com.soffid.iam.sync.api.LogConfiguration config, long firstRow, long numRows) - server cannot be null");
		}
		if (config == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.LogFileEntry> com.soffid.iam.sync.service.SyncServerService.findLogs(java.lang.String server, com.soffid.iam.sync.api.LogConfiguration config, long firstRow, long numRows) - config cannot be null");
		}
		if (config.getName() == null || config.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.LogFileEntry> com.soffid.iam.sync.service.SyncServerService.findLogs(java.lang.String server, com.soffid.iam.sync.api.LogConfiguration config, long firstRow, long numRows) - config.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindLogs(server, config, firstRow, numRows)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.LogFileEntry>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.findLogs", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.findLogs", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.LogFileEntry> handleFindLogs(java.lang.String server, com.soffid.iam.sync.api.LogConfiguration config, long firstRow, long numRows) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.LogConfiguration> getServerLogs(java.lang.String server)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.api.LogConfiguration> getServerLogs(
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (server == null || server.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.sync.api.LogConfiguration> com.soffid.iam.sync.service.SyncServerService.getServerLogs(java.lang.String server) - server cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetServerLogs(server)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.sync.api.LogConfiguration>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getServerLogs", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getServerLogs", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.sync.api.LogConfiguration> handleGetServerLogs(java.lang.String server) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#com.soffid.iam.sync.api.SyncAgentTaskLog getAgentTasks(java.lang.String url, java.lang.String agentName, java.lang.Long taskId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.SyncAgentTaskLog getAgentTasks(
		final java.lang.String url, 
		final java.lang.String agentName, 
		final java.lang.Long taskId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (url == null || url.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.SyncAgentTaskLog com.soffid.iam.sync.service.SyncServerService.getAgentTasks(java.lang.String url, java.lang.String agentName, java.lang.Long taskId) - url cannot be null");
		}
		if (agentName == null || agentName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.SyncAgentTaskLog com.soffid.iam.sync.service.SyncServerService.getAgentTasks(java.lang.String url, java.lang.String agentName, java.lang.Long taskId) - agentName cannot be null");
		}
		if (taskId == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.SyncAgentTaskLog com.soffid.iam.sync.service.SyncServerService.getAgentTasks(java.lang.String url, java.lang.String agentName, java.lang.Long taskId) - taskId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAgentTasks(url, agentName, taskId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.SyncAgentTaskLog) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getAgentTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getAgentTasks", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.SyncAgentTaskLog handleGetAgentTasks(java.lang.String url, java.lang.String agentName, java.lang.Long taskId) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#com.soffid.iam.sync.api.SyncServerInfo getSyncServerInfo(java.lang.String url)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.SyncServerInfo getSyncServerInfo(
		final java.lang.String url)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (url == null || url.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.SyncServerInfo com.soffid.iam.sync.service.SyncServerService.getSyncServerInfo(java.lang.String url) - url cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetSyncServerInfo(url)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.SyncServerInfo) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getSyncServerInfo", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getSyncServerInfo", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.SyncServerInfo handleGetSyncServerInfo(java.lang.String url) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.io.InputStream getSeyconServerLog(java.lang.String urlServer)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.io.InputStream getSeyconServerLog(
		final java.lang.String urlServer)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (urlServer == null || urlServer.trim().length() == 0) {
			throw new IllegalArgumentException("java.io.InputStream com.soffid.iam.sync.service.SyncServerService.getSeyconServerLog(java.lang.String urlServer) - urlServer cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetSeyconServerLog(urlServer)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.io.InputStream) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getSeyconServerLog", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getSeyconServerLog", (Throwable) __r[1]);
	}

	protected abstract java.io.InputStream handleGetSeyconServerLog(java.lang.String urlServer) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.lang.Object getServerService(java.lang.String servicePath)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Object getServerService(
		final java.lang.String servicePath)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (servicePath == null || servicePath.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Object com.soffid.iam.sync.service.SyncServerService.getServerService(java.lang.String servicePath) - servicePath cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetServerService(servicePath)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Object) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getServerService", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getServerService", (Throwable) __r[1]);
	}

	protected abstract java.lang.Object handleGetServerService(java.lang.String servicePath) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.lang.String[] tailServerLog(java.lang.String urlServer)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] tailServerLog(
		final java.lang.String urlServer)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (urlServer == null || urlServer.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.sync.service.SyncServerService.tailServerLog(java.lang.String urlServer) - urlServer cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleTailServerLog(urlServer)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.tailServerLog", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.tailServerLog", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleTailServerLog(java.lang.String urlServer) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.SyncserverTask> findUnscheduledTasks()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.api.SyncserverTask> findUnscheduledTasks()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUnscheduledTasks()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.sync.api.SyncserverTask>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.findUnscheduledTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.findUnscheduledTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.sync.api.SyncserverTask> handleFindUnscheduledTasks() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getAgentTasks(java.lang.String url, java.lang.String agentCodi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getAgentTasks(
		final java.lang.String url, 
		final java.lang.String agentCodi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (agentCodi == null || agentCodi.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> com.soffid.iam.sync.service.SyncServerService.getAgentTasks(java.lang.String url, java.lang.String agentCodi) - agentCodi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAgentTasks(url, agentCodi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getAgentTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getAgentTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> handleGetAgentTasks(java.lang.String url, java.lang.String agentCodi) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getPendingTasksInfo()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getPendingTasksInfo()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPendingTasksInfo()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getPendingTasksInfo", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getPendingTasksInfo", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> handleGetPendingTasksInfo() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getServerAgentStatus()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getServerAgentStatus()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetServerAgentStatus()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getServerAgentStatus", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getServerAgentStatus", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> handleGetServerAgentStatus() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getServerAgentStatus(java.lang.String url)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getServerAgentStatus(
		final java.lang.String url)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetServerAgentStatus(url)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getServerAgentStatus", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getServerAgentStatus", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> handleGetServerAgentStatus(java.lang.String url) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<java.lang.Object> getServerTasks(java.lang.String url)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.Object> getServerTasks(
		final java.lang.String url)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (url == null || url.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.Object> com.soffid.iam.sync.service.SyncServerService.getServerTasks(java.lang.String url) - url cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetServerTasks(url)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.Object>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getServerTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getServerTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.Object> handleGetServerTasks(java.lang.String url) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getSyncServersStatus()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getSyncServersStatus()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetSyncServersStatus()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getSyncServersStatus", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getSyncServersStatus", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> handleGetSyncServersStatus() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.Server> getSyncServerInstances()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.api.Server> getSyncServerInstances()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetSyncServerInstances()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.sync.api.Server>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getSyncServerInstances", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getSyncServerInstances", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.sync.api.Server> handleGetSyncServerInstances() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.Server> getSyncServers()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.api.Server> getSyncServers()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetSyncServers()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.sync.api.Server>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getSyncServers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getSyncServers", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.sync.api.Server> handleGetSyncServers() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Map<java.lang.String,java.util.Vector<java.lang.Object[]>> getPendingTasksStats()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Map<java.lang.String,java.util.Vector<java.lang.Object[]>> getPendingTasksStats()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPendingTasksStats()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Map<java.lang.String,java.util.Vector<java.lang.Object[]>>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getPendingTasksStats", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getPendingTasksStats", (Throwable) __r[1]);
	}

	protected abstract java.util.Map<java.lang.String,java.util.Vector<java.lang.Object[]>> handleGetPendingTasksStats() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Map<java.lang.String,int[]> getStats(java.lang.String server, java.lang.String metric, int seconds, int step)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Map<java.lang.String,int[]> getStats(
		final java.lang.String server, 
		final java.lang.String metric, 
		final int seconds, 
		final int step)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (server == null || server.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Map<java.lang.String,int[]> com.soffid.iam.sync.service.SyncServerService.getStats(java.lang.String server, java.lang.String metric, int seconds, int step) - server cannot be null");
		}
		if (metric == null || metric.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Map<java.lang.String,int[]> com.soffid.iam.sync.service.SyncServerService.getStats(java.lang.String server, java.lang.String metric, int seconds, int step) - metric cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetStats(server, metric, seconds, step)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Map<java.lang.String,int[]>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.getStats", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.getStats", (Throwable) __r[1]);
	}

	protected abstract java.util.Map<java.lang.String,int[]> handleGetStats(java.lang.String server, java.lang.String metric, int seconds, int step) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#void boostTask(long taskId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void boostTask(
		final long taskId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleBoostTask(taskId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.boostTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.boostTask", (Throwable) __r[1]);
	}

	protected abstract void handleBoostTask(long taskId) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#void cancelTask(long taskId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void cancelTask(
		final long taskId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCancelTask(taskId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.cancelTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.cancelTask", (Throwable) __r[1]);
	}

	protected abstract void handleCancelTask(long taskId) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#void cancelUnscheduledTasks()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void cancelUnscheduledTasks()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCancelUnscheduledTasks();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.cancelUnscheduledTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.cancelUnscheduledTasks", (Throwable) __r[1]);
	}

	protected abstract void handleCancelUnscheduledTasks() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#void releaseAllTasks()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void releaseAllTasks()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleReleaseAllTasks();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.releaseAllTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.releaseAllTasks", (Throwable) __r[1]);
	}

	protected abstract void handleReleaseAllTasks() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#void releaseTask(long taskId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void releaseTask(
		final long taskId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleReleaseTask(taskId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.releaseTask", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.releaseTask", (Throwable) __r[1]);
	}

	protected abstract void handleReleaseTask(long taskId) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#void resetSyncServer(java.lang.String url, java.lang.String server)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void resetSyncServer(
		final java.lang.String url, 
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (url == null || url.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.SyncServerService.resetSyncServer(java.lang.String url, java.lang.String server) - url cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleResetSyncServer(url, server);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.resetSyncServer", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.resetSyncServer", (Throwable) __r[1]);
	}

	protected abstract void handleResetSyncServer(java.lang.String url, java.lang.String server) throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#void updateDispatcherConfiguration()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateDispatcherConfiguration()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateDispatcherConfiguration();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.updateDispatcherConfiguration", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.updateDispatcherConfiguration", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateDispatcherConfiguration() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#void updatePendingTasks()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updatePendingTasks()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdatePendingTasks();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.updatePendingTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.updatePendingTasks", (Throwable) __r[1]);
	}

	protected abstract void handleUpdatePendingTasks() throws Exception;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#	 * @see com.soffid.iam.sync.service.SyncServerService#void configureLog(java.lang.String server, com.soffid.iam.sync.api.LogConfiguration config)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void configureLog(
		final java.lang.String server, 
		final com.soffid.iam.sync.api.LogConfiguration config)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (server == null || server.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.SyncServerService.configureLog(java.lang.String server, com.soffid.iam.sync.api.LogConfiguration config) - server cannot be null");
		}
		if (config == null) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.SyncServerService.configureLog(java.lang.String server, com.soffid.iam.sync.api.LogConfiguration config) - config cannot be null");
		}
		if (config.getName() == null || config.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.service.SyncServerService.configureLog(java.lang.String server, com.soffid.iam.sync.api.LogConfiguration config) - config.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleConfigureLog(server, config);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.service.SyncServerService.class).
			warn ("Error on SyncServerService.configureLog", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SyncServerService.configureLog", (Throwable) __r[1]);
	}

	protected abstract void handleConfigureLog(java.lang.String server, com.soffid.iam.sync.api.LogConfiguration config) throws Exception;

	/**
	 * Gets the current <code>principal</code> if one has been set,
	 * otherwise returns <code>null</code>.
	 *
	 * @return the current principal
	 */
	protected java.security.Principal getPrincipal()
	{
		return com.soffid.iam.PrincipalStore.get();
	}

}
