//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service.ejb;
/**
 * @see <code>com.soffid.iam.sync.service.SyncServerService</code>,
 * @see <code>com.soffid.iam.sync.service.SyncServerService</code>,
 */
@jakarta.ejb.Stateless(name="SyncServerService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.sync.service.SyncServerService")
@jakarta.ejb.Local(com.soffid.iam.sync.service.ejb.SyncServerService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class SyncServerServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.sync.service.ejb.SyncServerService
{
	private com.soffid.iam.sync.service.SyncServerService syncServerService;

	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.LogFileEntry> findLogs(java.lang.String server, com.soffid.iam.sync.api.LogConfiguration config, long firstRow, long numRows)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.sync.api.LogFileEntry> findLogs(
		final java.lang.String server, 
		final com.soffid.iam.sync.api.LogConfiguration config, 
		final long firstRow, 
		final long numRows)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.findLogs. Required roles: [monitor:server:list]");
		try
		{
			return this.syncServerService.findLogs(server, config, firstRow, numRows); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.LogConfiguration> getServerLogs(java.lang.String server)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.sync.api.LogConfiguration> getServerLogs(
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.getServerLogs. Required roles: [monitor:server:list]");
		try
		{
			return this.syncServerService.getServerLogs(server); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#com.soffid.iam.sync.api.SyncAgentTaskLog getAgentTasks(java.lang.String url, java.lang.String agentName, java.lang.Long taskId)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.SyncAgentTaskLog getAgentTasks(
		final java.lang.String url, 
		final java.lang.String agentName, 
		final java.lang.Long taskId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:agent:list"))
			throw new SecurityException("Unable to execute SyncServerService.getAgentTasks. Required roles: [monitor:agent:list]");
		try
		{
			return this.syncServerService.getAgentTasks(url, agentName, taskId); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#com.soffid.iam.sync.api.SyncServerInfo getSyncServerInfo(java.lang.String url)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.SyncServerInfo getSyncServerInfo(
		final java.lang.String url)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.getSyncServerInfo. Required roles: [monitor:server:list]");
		try
		{
			return this.syncServerService.getSyncServerInfo(url); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.io.InputStream getSeyconServerLog(java.lang.String urlServer)
	 */
	@jakarta.annotation.security.PermitAll
	public java.io.InputStream getSeyconServerLog(
		final java.lang.String urlServer)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("base:log:query"))
			throw new SecurityException("Unable to execute SyncServerService.getSeyconServerLog. Required roles: [base:log:query]");
		try
		{
			return this.syncServerService.getSeyconServerLog(urlServer); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.lang.String[] tailServerLog(java.lang.String urlServer)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String[] tailServerLog(
		final java.lang.String urlServer)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("base:log:query"))
			throw new SecurityException("Unable to execute SyncServerService.tailServerLog. Required roles: [base:log:query]");
		try
		{
			return this.syncServerService.tailServerLog(urlServer); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.SyncserverTask> findUnscheduledTasks()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.sync.api.SyncserverTask> findUnscheduledTasks()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.findUnscheduledTasks. Required roles: [monitor:server:list]");
		try
		{
			return this.syncServerService.findUnscheduledTasks(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getAgentTasks(java.lang.String url, java.lang.String agentCodi)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getAgentTasks(
		final java.lang.String url, 
		final java.lang.String agentCodi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:agent:list"))
			throw new SecurityException("Unable to execute SyncServerService.getAgentTasks. Required roles: [monitor:agent:list]");
		try
		{
			return this.syncServerService.getAgentTasks(url, agentCodi); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getPendingTasksInfo()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getPendingTasksInfo()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.getPendingTasksInfo. Required roles: [monitor:server:list]");
		try
		{
			return this.syncServerService.getPendingTasksInfo(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getServerAgentStatus()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getServerAgentStatus()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:agent:list"))
			throw new SecurityException("Unable to execute SyncServerService.getServerAgentStatus. Required roles: [monitor:agent:list]");
		try
		{
			return this.syncServerService.getServerAgentStatus(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getServerAgentStatus(java.lang.String url)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.AgentStatusInfo> getServerAgentStatus(
		final java.lang.String url)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:agent:list"))
			throw new SecurityException("Unable to execute SyncServerService.getServerAgentStatus. Required roles: [monitor:agent:list]");
		try
		{
			return this.syncServerService.getServerAgentStatus(url); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<java.lang.Object> getServerTasks(java.lang.String url)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<java.lang.Object> getServerTasks(
		final java.lang.String url)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.getServerTasks. Required roles: [monitor:server:list]");
		try
		{
			return this.syncServerService.getServerTasks(url); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getSyncServersStatus()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.sync.api.SyncServerInfo> getSyncServersStatus()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.getSyncServersStatus. Required roles: [monitor:server:list]");
		try
		{
			return this.syncServerService.getSyncServersStatus(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.Server> getSyncServerInstances()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.sync.api.Server> getSyncServerInstances()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.getSyncServerInstances. Required roles: [monitor:server:list]");
		try
		{
			return this.syncServerService.getSyncServerInstances(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Collection<com.soffid.iam.sync.api.Server> getSyncServers()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.sync.api.Server> getSyncServers()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.getSyncServers. Required roles: [monitor:server:list]");
		try
		{
			return this.syncServerService.getSyncServers(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Map<java.lang.String,java.util.Vector<java.lang.Object[]>> getPendingTasksStats()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Map<java.lang.String,java.util.Vector<java.lang.Object[]>> getPendingTasksStats()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:agent:list"))
			throw new SecurityException("Unable to execute SyncServerService.getPendingTasksStats. Required roles: [monitor:agent:list]");
		try
		{
			return this.syncServerService.getPendingTasksStats(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#java.util.Map<java.lang.String,int[]> getStats(java.lang.String server, java.lang.String metric, int seconds, int step)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Map<java.lang.String,int[]> getStats(
		final java.lang.String server, 
		final java.lang.String metric, 
		final int seconds, 
		final int step)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.getStats. Required roles: [monitor:server:list]");
		try
		{
			return this.syncServerService.getStats(server, metric, seconds, step); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#void boostTask(long taskId)
	 */
	@jakarta.annotation.security.PermitAll
	public void boostTask(
		final long taskId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.boostTask. Required roles: [monitor:server:list]");
		try
		{
			this.syncServerService.boostTask(taskId); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#void cancelTask(long taskId)
	 */
	@jakarta.annotation.security.PermitAll
	public void cancelTask(
		final long taskId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.cancelTask. Required roles: [monitor:server:list]");
		try
		{
			this.syncServerService.cancelTask(taskId); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#void cancelUnscheduledTasks()
	 */
	@jakarta.annotation.security.PermitAll
	public void cancelUnscheduledTasks()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.cancelUnscheduledTasks. Required roles: [monitor:server:list]");
		try
		{
			this.syncServerService.cancelUnscheduledTasks(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#void releaseAllTasks()
	 */
	@jakarta.annotation.security.PermitAll
	public void releaseAllTasks()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.releaseAllTasks. Required roles: [monitor:server:list]");
		try
		{
			this.syncServerService.releaseAllTasks(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#void releaseTask(long taskId)
	 */
	@jakarta.annotation.security.PermitAll
	public void releaseTask(
		final long taskId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.releaseTask. Required roles: [monitor:server:list]");
		try
		{
			this.syncServerService.releaseTask(taskId); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#void resetSyncServer(java.lang.String url, java.lang.String server)
	 */
	@jakarta.annotation.security.PermitAll
	public void resetSyncServer(
		final java.lang.String url, 
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.resetSyncServer. Required roles: [monitor:server:list]");
		try
		{
			this.syncServerService.resetSyncServer(url, server); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.sync.service.SyncServerService#void configureLog(java.lang.String server, com.soffid.iam.sync.api.LogConfiguration config)
	 */
	@jakarta.annotation.security.PermitAll
	public void configureLog(
		final java.lang.String server, 
		final com.soffid.iam.sync.api.LogConfiguration config)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("monitor:server:list"))
			throw new SecurityException("Unable to execute SyncServerService.configureLog. Required roles: [monitor:server:list]");
		try
		{
			this.syncServerService.configureLog(server, config); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * Initizlizes been
	 *
	 * @see org.springframework.ejb.support.AbstractStatelessSessionBean#onEjbCreate()
	 */

	@Override

	@jakarta.annotation.PostConstruct
	public void createBean() 
	{
		super.createBean();
	}


	protected void onEjbCreate()
	{

		this.syncServerService = (com.soffid.iam.sync.service.SyncServerService)
		getBeanFactory().getBean("com.soffid.iam.sync.service.SyncServerService");
	}

	
	/**
	 * Override default BeanFactoryLocator implementation to
	 * provide singleton loading of the application context Bean factory.
	 *
	 * @see jakarta.ejb.SessionBean#setSessionContext(jakarta.ejb.SessionContext)
	 */
	public void setSessionContext(jakarta.ejb.SessionContext sessionContext)
	{
		super.setSessionContext(sessionContext);
		super.setBeanFactoryLocator(
		org.springframework.context.access.ContextSingletonBeanFactoryLocator.getInstance("beanRefFactory.xml"));
		super.setBeanFactoryLocatorKey("beanRefFactory");
	}

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog (getClass());

	/**
	 * Finds the root cause of the parent exception
	 * by traveling up the exception tree.
	 */	private static Throwable getRootCause(Throwable throwable)
	{
		if (throwable != null)
		{
			// Reflectively get any exception causes.
			try
			{
				Throwable targetException = null;
				// java.lang.reflect.InvocationTargetException
				String exceptionProperty = "targetException";
				if (org.apache.commons.beanutils.PropertyUtils.isReadable(throwable, exceptionProperty))
				{
					targetException = (Throwable)org.apache.commons.beanutils.PropertyUtils.getProperty(throwable, exceptionProperty);
				}
				else
				{
					exceptionProperty = "causedByException";
					//jakarta.ejb.EJBException
					if (org.apache.commons.beanutils.PropertyUtils.isReadable(throwable, exceptionProperty))
					{
						targetException = (Throwable)org.apache.commons.beanutils.PropertyUtils.getProperty(throwable, exceptionProperty);
					}
				}
				if (targetException != null)
				{
					throwable = targetException;
				}
			}
			catch (Exception exception)
			{
				// just print the exception and continue
				exception.printStackTrace();
			}
			if (throwable.getCause() != null)
			{
				throwable = throwable.getCause();
				throwable = getRootCause(throwable);
			}
		}
		return throwable;
	}
}
