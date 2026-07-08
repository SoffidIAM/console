//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.DispatcherService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.DispatcherService
 */
public abstract class DispatcherServiceBase
	implements com.soffid.iam.iga.service.DispatcherService
 {
	private com.soffid.iam.iga.model.AccessControlEntityDao accessControlEntityDao;

	/**
	 * Sets reference to <code>accessControlEntityDao</code>.
	 */
	public void setAccessControlEntityDao (com.soffid.iam.iga.model.AccessControlEntityDao accessControlEntityDao) {
		this.accessControlEntityDao = accessControlEntityDao;
	}

	/**
	 * Gets reference to <code>accessControlEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.AccessControlEntityDao getAccessControlEntityDao () {
		return accessControlEntityDao;
	}

	private com.soffid.iam.base.model.AccountAttributeEntityDao accountAttributeEntityDao;

	/**
	 * Sets reference to <code>accountAttributeEntityDao</code>.
	 */
	public void setAccountAttributeEntityDao (com.soffid.iam.base.model.AccountAttributeEntityDao accountAttributeEntityDao) {
		this.accountAttributeEntityDao = accountAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>accountAttributeEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountAttributeEntityDao getAccountAttributeEntityDao () {
		return accountAttributeEntityDao;
	}

	private com.soffid.iam.base.model.AccountEntityDao accountEntityDao;

	/**
	 * Sets reference to <code>accountEntityDao</code>.
	 */
	public void setAccountEntityDao (com.soffid.iam.base.model.AccountEntityDao accountEntityDao) {
		this.accountEntityDao = accountEntityDao;
	}

	/**
	 * Gets reference to <code>accountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountEntityDao getAccountEntityDao () {
		return accountEntityDao;
	}

	private com.soffid.iam.base.model.AccountMetadataEntityDao accountMetadataEntityDao;

	/**
	 * Sets reference to <code>accountMetadataEntityDao</code>.
	 */
	public void setAccountMetadataEntityDao (com.soffid.iam.base.model.AccountMetadataEntityDao accountMetadataEntityDao) {
		this.accountMetadataEntityDao = accountMetadataEntityDao;
	}

	/**
	 * Gets reference to <code>accountMetadataEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountMetadataEntityDao getAccountMetadataEntityDao () {
		return accountMetadataEntityDao;
	}

	private com.soffid.iam.base.service.AccountService accountService;

	/**
	 * Sets reference to <code>accountService</code>.
	 */
	public void setAccountService (com.soffid.iam.base.service.AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Gets reference to <code>accountService</code>.
	 */
	public com.soffid.iam.base.service.AccountService getAccountService () {
		return accountService;
	}

	private com.soffid.iam.base.model.AgentDescriptorEntityDao agentDescriptorEntityDao;

	/**
	 * Sets reference to <code>agentDescriptorEntityDao</code>.
	 */
	public void setAgentDescriptorEntityDao (com.soffid.iam.base.model.AgentDescriptorEntityDao agentDescriptorEntityDao) {
		this.agentDescriptorEntityDao = agentDescriptorEntityDao;
	}

	/**
	 * Gets reference to <code>agentDescriptorEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntityDao getAgentDescriptorEntityDao () {
		return agentDescriptorEntityDao;
	}

	private com.soffid.iam.base.model.AgentPropertyEntityDao agentPropertyEntityDao;

	/**
	 * Sets reference to <code>agentPropertyEntityDao</code>.
	 */
	public void setAgentPropertyEntityDao (com.soffid.iam.base.model.AgentPropertyEntityDao agentPropertyEntityDao) {
		this.agentPropertyEntityDao = agentPropertyEntityDao;
	}

	/**
	 * Gets reference to <code>agentPropertyEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AgentPropertyEntityDao getAgentPropertyEntityDao () {
		return agentPropertyEntityDao;
	}

	private com.soffid.iam.iga.service.ApplicationService applicationService;

	/**
	 * Sets reference to <code>applicationService</code>.
	 */
	public void setApplicationService (com.soffid.iam.iga.service.ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	/**
	 * Gets reference to <code>applicationService</code>.
	 */
	public com.soffid.iam.iga.service.ApplicationService getApplicationService () {
		return applicationService;
	}

	private com.soffid.iam.impl.service.AsyncRunnerService asyncRunnerService;

	/**
	 * Sets reference to <code>asyncRunnerService</code>.
	 */
	public void setAsyncRunnerService (com.soffid.iam.impl.service.AsyncRunnerService asyncRunnerService) {
		this.asyncRunnerService = asyncRunnerService;
	}

	/**
	 * Gets reference to <code>asyncRunnerService</code>.
	 */
	public com.soffid.iam.impl.service.AsyncRunnerService getAsyncRunnerService () {
		return asyncRunnerService;
	}

	private com.soffid.iam.iga.model.AttributeMappingEntityDao attributeMappingEntityDao;

	/**
	 * Sets reference to <code>attributeMappingEntityDao</code>.
	 */
	public void setAttributeMappingEntityDao (com.soffid.iam.iga.model.AttributeMappingEntityDao attributeMappingEntityDao) {
		this.attributeMappingEntityDao = attributeMappingEntityDao;
	}

	/**
	 * Gets reference to <code>attributeMappingEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.AttributeMappingEntityDao getAttributeMappingEntityDao () {
		return attributeMappingEntityDao;
	}

	private com.soffid.iam.rc.service.AuditService auditService;

	/**
	 * Sets reference to <code>auditService</code>.
	 */
	public void setAuditService (com.soffid.iam.rc.service.AuditService auditService) {
		this.auditService = auditService;
	}

	/**
	 * Gets reference to <code>auditService</code>.
	 */
	public com.soffid.iam.rc.service.AuditService getAuditService () {
		return auditService;
	}

	private com.soffid.iam.base.service.AuthorizationService authorizationService;

	/**
	 * Sets reference to <code>authorizationService</code>.
	 */
	public void setAuthorizationService (com.soffid.iam.base.service.AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	/**
	 * Gets reference to <code>authorizationService</code>.
	 */
	public com.soffid.iam.base.service.AuthorizationService getAuthorizationService () {
		return authorizationService;
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

	private com.soffid.iam.iga.model.GroupEntityDao groupEntityDao;

	/**
	 * Sets reference to <code>groupEntityDao</code>.
	 */
	public void setGroupEntityDao (com.soffid.iam.iga.model.GroupEntityDao groupEntityDao) {
		this.groupEntityDao = groupEntityDao;
	}

	/**
	 * Gets reference to <code>groupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupEntityDao getGroupEntityDao () {
		return groupEntityDao;
	}

	private com.soffid.iam.pam.model.HostSystemEntityDao hostSystemEntityDao;

	/**
	 * Sets reference to <code>hostSystemEntityDao</code>.
	 */
	public void setHostSystemEntityDao (com.soffid.iam.pam.model.HostSystemEntityDao hostSystemEntityDao) {
		this.hostSystemEntityDao = hostSystemEntityDao;
	}

	/**
	 * Gets reference to <code>hostSystemEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.HostSystemEntityDao getHostSystemEntityDao () {
		return hostSystemEntityDao;
	}

	private com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao;

	/**
	 * Sets reference to <code>metaDataEntityDao</code>.
	 */
	public void setMetaDataEntityDao (com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao) {
		this.metaDataEntityDao = metaDataEntityDao;
	}

	/**
	 * Gets reference to <code>metaDataEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MetaDataEntityDao getMetaDataEntityDao () {
		return metaDataEntityDao;
	}

	private com.soffid.iam.iga.model.ObjectMappingEntityDao objectMappingEntityDao;

	/**
	 * Sets reference to <code>objectMappingEntityDao</code>.
	 */
	public void setObjectMappingEntityDao (com.soffid.iam.iga.model.ObjectMappingEntityDao objectMappingEntityDao) {
		this.objectMappingEntityDao = objectMappingEntityDao;
	}

	/**
	 * Gets reference to <code>objectMappingEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ObjectMappingEntityDao getObjectMappingEntityDao () {
		return objectMappingEntityDao;
	}

	private com.soffid.iam.iga.model.ObjectMappingPropertyEntityDao objectMappingPropertyEntityDao;

	/**
	 * Sets reference to <code>objectMappingPropertyEntityDao</code>.
	 */
	public void setObjectMappingPropertyEntityDao (com.soffid.iam.iga.model.ObjectMappingPropertyEntityDao objectMappingPropertyEntityDao) {
		this.objectMappingPropertyEntityDao = objectMappingPropertyEntityDao;
	}

	/**
	 * Gets reference to <code>objectMappingPropertyEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ObjectMappingPropertyEntityDao getObjectMappingPropertyEntityDao () {
		return objectMappingPropertyEntityDao;
	}

	private com.soffid.iam.iga.model.ObjectMappingTriggerEntityDao objectMappingTriggerEntityDao;

	/**
	 * Sets reference to <code>objectMappingTriggerEntityDao</code>.
	 */
	public void setObjectMappingTriggerEntityDao (com.soffid.iam.iga.model.ObjectMappingTriggerEntityDao objectMappingTriggerEntityDao) {
		this.objectMappingTriggerEntityDao = objectMappingTriggerEntityDao;
	}

	/**
	 * Gets reference to <code>objectMappingTriggerEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ObjectMappingTriggerEntityDao getObjectMappingTriggerEntityDao () {
		return objectMappingTriggerEntityDao;
	}

	private com.soffid.iam.iga.model.ReconcileTriggerEntityDao reconcileTriggerEntityDao;

	/**
	 * Sets reference to <code>reconcileTriggerEntityDao</code>.
	 */
	public void setReconcileTriggerEntityDao (com.soffid.iam.iga.model.ReconcileTriggerEntityDao reconcileTriggerEntityDao) {
		this.reconcileTriggerEntityDao = reconcileTriggerEntityDao;
	}

	/**
	 * Gets reference to <code>reconcileTriggerEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ReconcileTriggerEntityDao getReconcileTriggerEntityDao () {
		return reconcileTriggerEntityDao;
	}

	private com.soffid.iam.iga.model.RoleEntityDao roleEntityDao;

	/**
	 * Sets reference to <code>roleEntityDao</code>.
	 */
	public void setRoleEntityDao (com.soffid.iam.iga.model.RoleEntityDao roleEntityDao) {
		this.roleEntityDao = roleEntityDao;
	}

	/**
	 * Gets reference to <code>roleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleEntityDao getRoleEntityDao () {
		return roleEntityDao;
	}

	private com.soffid.iam.sync.service.ScheduledTaskService scheduledTaskService;

	/**
	 * Sets reference to <code>scheduledTaskService</code>.
	 */
	public void setScheduledTaskService (com.soffid.iam.sync.service.ScheduledTaskService scheduledTaskService) {
		this.scheduledTaskService = scheduledTaskService;
	}

	/**
	 * Gets reference to <code>scheduledTaskService</code>.
	 */
	public com.soffid.iam.sync.service.ScheduledTaskService getScheduledTaskService () {
		return scheduledTaskService;
	}

	private com.soffid.iam.sync.model.ServerCertificateEntityDao serverCertificateEntityDao;

	/**
	 * Sets reference to <code>serverCertificateEntityDao</code>.
	 */
	public void setServerCertificateEntityDao (com.soffid.iam.sync.model.ServerCertificateEntityDao serverCertificateEntityDao) {
		this.serverCertificateEntityDao = serverCertificateEntityDao;
	}

	/**
	 * Gets reference to <code>serverCertificateEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ServerCertificateEntityDao getServerCertificateEntityDao () {
		return serverCertificateEntityDao;
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

	private com.soffid.iam.sync.model.ServerRegistrationTokenEntityDao serverRegistrationTokenEntityDao;

	/**
	 * Sets reference to <code>serverRegistrationTokenEntityDao</code>.
	 */
	public void setServerRegistrationTokenEntityDao (com.soffid.iam.sync.model.ServerRegistrationTokenEntityDao serverRegistrationTokenEntityDao) {
		this.serverRegistrationTokenEntityDao = serverRegistrationTokenEntityDao;
	}

	/**
	 * Gets reference to <code>serverRegistrationTokenEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ServerRegistrationTokenEntityDao getServerRegistrationTokenEntityDao () {
		return serverRegistrationTokenEntityDao;
	}

	private com.soffid.iam.sync.service.SyncServerService syncServerService;

	/**
	 * Sets reference to <code>syncServerService</code>.
	 */
	public void setSyncServerService (com.soffid.iam.sync.service.SyncServerService syncServerService) {
		this.syncServerService = syncServerService;
	}

	/**
	 * Gets reference to <code>syncServerService</code>.
	 */
	public com.soffid.iam.sync.service.SyncServerService getSyncServerService () {
		return syncServerService;
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

	private com.soffid.iam.iga.model.SystemGroupEntityDao systemGroupEntityDao;

	/**
	 * Sets reference to <code>systemGroupEntityDao</code>.
	 */
	public void setSystemGroupEntityDao (com.soffid.iam.iga.model.SystemGroupEntityDao systemGroupEntityDao) {
		this.systemGroupEntityDao = systemGroupEntityDao;
	}

	/**
	 * Gets reference to <code>systemGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.SystemGroupEntityDao getSystemGroupEntityDao () {
		return systemGroupEntityDao;
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

	private com.soffid.iam.iga.model.UserDomainEntityDao userDomainEntityDao;

	/**
	 * Sets reference to <code>userDomainEntityDao</code>.
	 */
	public void setUserDomainEntityDao (com.soffid.iam.iga.model.UserDomainEntityDao userDomainEntityDao) {
		this.userDomainEntityDao = userDomainEntityDao;
	}

	/**
	 * Gets reference to <code>userDomainEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserDomainEntityDao getUserDomainEntityDao () {
		return userDomainEntityDao;
	}

	private com.soffid.iam.base.model.UserEntityDao userEntityDao;

	/**
	 * Sets reference to <code>userEntityDao</code>.
	 */
	public void setUserEntityDao (com.soffid.iam.base.model.UserEntityDao userEntityDao) {
		this.userEntityDao = userEntityDao;
	}

	/**
	 * Gets reference to <code>userEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserEntityDao getUserEntityDao () {
		return userEntityDao;
	}

	private com.soffid.iam.base.service.UserService userService;

	/**
	 * Sets reference to <code>userService</code>.
	 */
	public void setUserService (com.soffid.iam.base.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Gets reference to <code>userService</code>.
	 */
	public com.soffid.iam.base.service.UserService getUserService () {
		return userService;
	}

	private com.soffid.iam.base.model.UserTypeEntityDao userTypeEntityDao;

	/**
	 * Sets reference to <code>userTypeEntityDao</code>.
	 */
	public void setUserTypeEntityDao (com.soffid.iam.base.model.UserTypeEntityDao userTypeEntityDao) {
		this.userTypeEntityDao = userTypeEntityDao;
	}

	/**
	 * Gets reference to <code>userTypeEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserTypeEntityDao getUserTypeEntityDao () {
		return userTypeEntityDao;
	}

	private com.soffid.iam.iga.model.UserTypeSystemEntityDao userTypeSystemEntityDao;

	/**
	 * Sets reference to <code>userTypeSystemEntityDao</code>.
	 */
	public void setUserTypeSystemEntityDao (com.soffid.iam.iga.model.UserTypeSystemEntityDao userTypeSystemEntityDao) {
		this.userTypeSystemEntityDao = userTypeSystemEntityDao;
	}

	/**
	 * Gets reference to <code>userTypeSystemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserTypeSystemEntityDao getUserTypeSystemEntityDao () {
		return userTypeSystemEntityDao;
	}


	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.lang.String createRemoteServer(java.lang.String name, java.lang.String tenant)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String createRemoteServer(
		final java.lang.String name, 
		final java.lang.String tenant)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.DispatcherService.createRemoteServer(java.lang.String name, java.lang.String tenant) - name cannot be null");
		}
		if (tenant == null || tenant.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.DispatcherService.createRemoteServer(java.lang.String name, java.lang.String tenant) - tenant cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateRemoteServer(name, tenant)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.createRemoteServer", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.createRemoteServer", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleCreateRemoteServer(java.lang.String name, java.lang.String tenant) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Map<java.lang.String,java.lang.String> findActiveDirectoryDomains()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Map<java.lang.String,java.lang.String> findActiveDirectoryDomains()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindActiveDirectoryDomains()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Map<java.lang.String,java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findActiveDirectoryDomains", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findActiveDirectoryDomains", (Throwable) __r[1]);
	}

	protected abstract java.util.Map<java.lang.String,java.lang.String> handleFindActiveDirectoryDomains() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.lang.String startVirtualSourceTransaction(boolean readonly, java.lang.String server)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String startVirtualSourceTransaction(
		final boolean readonly, 
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleStartVirtualSourceTransaction(readonly, server)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.startVirtualSourceTransaction", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.startVirtualSourceTransaction", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleStartVirtualSourceTransaction(boolean readonly, java.lang.String server) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void finishVirtualSourceTransaction(java.lang.String transactionId)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void finishVirtualSourceTransaction(
		final java.lang.String transactionId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (transactionId == null || transactionId.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.finishVirtualSourceTransaction(java.lang.String transactionId) - transactionId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleFinishVirtualSourceTransaction(transactionId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.finishVirtualSourceTransaction", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.finishVirtualSourceTransaction", (Throwable) __r[1]);
	}

	protected abstract void handleFinishVirtualSourceTransaction(java.lang.String transactionId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#boolean isGroupAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String group)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean isGroupAllowed(
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String group)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.DispatcherService.isGroupAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String group) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.DispatcherService.isGroupAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String group) - dispatcher.name cannot be null");
		}
		if (group == null || group.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.DispatcherService.isGroupAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String group) - group cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsGroupAllowed(dispatcher, group)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.isGroupAllowed", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.isGroupAllowed", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsGroupAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String group) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#boolean isRegistrationTokenAlive(java.lang.String token)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isRegistrationTokenAlive(
		final java.lang.String token)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (token == null || token.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.DispatcherService.isRegistrationTokenAlive(java.lang.String token) - token cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsRegistrationTokenAlive(token)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.isRegistrationTokenAlive", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.isRegistrationTokenAlive", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsRegistrationTokenAlive(java.lang.String token) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#boolean isUserAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String user)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean isUserAllowed(
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.DispatcherService.isUserAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String user) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.DispatcherService.isUserAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String user) - dispatcher.name cannot be null");
		}
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.DispatcherService.isUserAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsUserAllowed(dispatcher, user)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.isUserAllowed", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.isUserAllowed", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsUserAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String user) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#boolean isUserAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String user, java.util.Collection<com.soffid.iam.iga.api.RoleGrant> permissions)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean isUserAllowed(
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String user, 
		final java.util.Collection<com.soffid.iam.iga.api.RoleGrant> permissions)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.DispatcherService.isUserAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String user, java.util.Collection<com.soffid.iam.iga.api.RoleGrant> permissions) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.DispatcherService.isUserAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String user, java.util.Collection<com.soffid.iam.iga.api.RoleGrant> permissions) - dispatcher.name cannot be null");
		}
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.DispatcherService.isUserAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String user, java.util.Collection<com.soffid.iam.iga.api.RoleGrant> permissions) - user cannot be null");
		}
		if (permissions == null ) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.DispatcherService.isUserAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String user, java.util.Collection<com.soffid.iam.iga.api.RoleGrant> permissions) - permissions cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsUserAllowed(dispatcher, user, permissions)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.isUserAllowed", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.isUserAllowed", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsUserAllowed(com.soffid.iam.iga.api.System dispatcher, java.lang.String user, java.util.Collection<com.soffid.iam.iga.api.RoleGrant> permissions) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.base.api.AsyncProcessTracker applyConfigurationAsync(com.soffid.iam.iga.api.System dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AsyncProcessTracker applyConfigurationAsync(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.iga.service.DispatcherService.applyConfigurationAsync(com.soffid.iam.iga.api.System dispatcher) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.iga.service.DispatcherService.applyConfigurationAsync(com.soffid.iam.iga.api.System dispatcher) - dispatcher.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleApplyConfigurationAsync(dispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.AsyncProcessTracker) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.applyConfigurationAsync", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.applyConfigurationAsync", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AsyncProcessTracker handleApplyConfigurationAsync(com.soffid.iam.iga.api.System dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(
		final com.soffid.iam.base.api.AsyncProcessTracker process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (process == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.iga.service.DispatcherService.queryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process) - process cannot be null");
		}
		if (process.getId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.iga.service.DispatcherService.queryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process) - process.id cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleQueryProcessStatus(process)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.AsyncProcessTracker) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.queryProcessStatus", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.queryProcessStatus", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AsyncProcessTracker handleQueryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.AccessControl create(com.soffid.iam.iga.api.AccessControl controlAcces)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.AccessControl create(
		final com.soffid.iam.iga.api.AccessControl controlAcces)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (controlAcces == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AccessControl com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.AccessControl controlAcces) - controlAcces cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(controlAcces)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.AccessControl) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.AccessControl handleCreate(com.soffid.iam.iga.api.AccessControl controlAcces) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.AccessControl update(com.soffid.iam.iga.api.AccessControl controlAcces)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.AccessControl update(
		final com.soffid.iam.iga.api.AccessControl controlAcces)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (controlAcces == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AccessControl com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.AccessControl controlAcces) - controlAcces cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(controlAcces)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.AccessControl) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.AccessControl handleUpdate(com.soffid.iam.iga.api.AccessControl controlAcces) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.AttributeMapping create(com.soffid.iam.iga.api.AttributeMapping mapping)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.AttributeMapping create(
		final com.soffid.iam.iga.api.AttributeMapping mapping)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (mapping == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeMapping com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.AttributeMapping mapping) - mapping cannot be null");
		}
		if (mapping.getSoffidAttribute() == null || mapping.getSoffidAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeMapping com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.AttributeMapping mapping) - mapping.soffidAttribute cannot be null");
		}
		if (mapping.getSystemAttribute() == null || mapping.getSystemAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeMapping com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.AttributeMapping mapping) - mapping.systemAttribute cannot be null");
		}
		if (mapping.getDirection() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeMapping com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.AttributeMapping mapping) - mapping.direction cannot be null");
		}
		if (mapping.getObjectId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeMapping com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.AttributeMapping mapping) - mapping.objectId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(mapping)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.AttributeMapping) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.AttributeMapping handleCreate(com.soffid.iam.iga.api.AttributeMapping mapping) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.AttributeMapping update(com.soffid.iam.iga.api.AttributeMapping mapping)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.AttributeMapping update(
		final com.soffid.iam.iga.api.AttributeMapping mapping)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (mapping == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeMapping com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.AttributeMapping mapping) - mapping cannot be null");
		}
		if (mapping.getSoffidAttribute() == null || mapping.getSoffidAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeMapping com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.AttributeMapping mapping) - mapping.soffidAttribute cannot be null");
		}
		if (mapping.getSystemAttribute() == null || mapping.getSystemAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeMapping com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.AttributeMapping mapping) - mapping.systemAttribute cannot be null");
		}
		if (mapping.getDirection() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeMapping com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.AttributeMapping mapping) - mapping.direction cannot be null");
		}
		if (mapping.getObjectId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AttributeMapping com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.AttributeMapping mapping) - mapping.objectId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(mapping)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.AttributeMapping) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.AttributeMapping handleUpdate(com.soffid.iam.iga.api.AttributeMapping mapping) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ObjectMapping create(com.soffid.iam.iga.api.ObjectMapping om)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ObjectMapping create(
		final com.soffid.iam.iga.api.ObjectMapping om)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (om == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMapping com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ObjectMapping om) - om cannot be null");
		}
		if (om.getSystemObject() == null || om.getSystemObject().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMapping com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ObjectMapping om) - om.systemObject cannot be null");
		}
		if (om.getSoffidObject() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMapping com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ObjectMapping om) - om.soffidObject cannot be null");
		}
		if (om.getDispatcherId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMapping com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ObjectMapping om) - om.dispatcherId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(om)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ObjectMapping) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ObjectMapping handleCreate(com.soffid.iam.iga.api.ObjectMapping om) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ObjectMapping update(com.soffid.iam.iga.api.ObjectMapping om)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ObjectMapping update(
		final com.soffid.iam.iga.api.ObjectMapping om)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (om == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMapping com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ObjectMapping om) - om cannot be null");
		}
		if (om.getSystemObject() == null || om.getSystemObject().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMapping com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ObjectMapping om) - om.systemObject cannot be null");
		}
		if (om.getSoffidObject() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMapping com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ObjectMapping om) - om.soffidObject cannot be null");
		}
		if (om.getDispatcherId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMapping com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ObjectMapping om) - om.dispatcherId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(om)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ObjectMapping) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ObjectMapping handleUpdate(com.soffid.iam.iga.api.ObjectMapping om) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ObjectMappingProperty create(com.soffid.iam.iga.api.ObjectMappingProperty omp)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ObjectMappingProperty create(
		final com.soffid.iam.iga.api.ObjectMappingProperty omp)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (omp == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMappingProperty com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ObjectMappingProperty omp) - omp cannot be null");
		}
		if (omp.getProperty() == null || omp.getProperty().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMappingProperty com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ObjectMappingProperty omp) - omp.property cannot be null");
		}
		if (omp.getObjectId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMappingProperty com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ObjectMappingProperty omp) - omp.objectId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(omp)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ObjectMappingProperty) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ObjectMappingProperty handleCreate(com.soffid.iam.iga.api.ObjectMappingProperty omp) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ObjectMappingProperty update(com.soffid.iam.iga.api.ObjectMappingProperty om)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ObjectMappingProperty update(
		final com.soffid.iam.iga.api.ObjectMappingProperty om)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (om == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMappingProperty com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ObjectMappingProperty om) - om cannot be null");
		}
		if (om.getProperty() == null || om.getProperty().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMappingProperty com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ObjectMappingProperty om) - om.property cannot be null");
		}
		if (om.getObjectId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMappingProperty com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ObjectMappingProperty om) - om.objectId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(om)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ObjectMappingProperty) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ObjectMappingProperty handleUpdate(com.soffid.iam.iga.api.ObjectMappingProperty om) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ObjectMappingTrigger create(com.soffid.iam.iga.api.ObjectMappingTrigger trigger)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ObjectMappingTrigger create(
		final com.soffid.iam.iga.api.ObjectMappingTrigger trigger)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (trigger == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMappingTrigger com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ObjectMappingTrigger trigger) - trigger cannot be null");
		}
		if (trigger.getTrigger() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMappingTrigger com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ObjectMappingTrigger trigger) - trigger.trigger cannot be null");
		}
		if (trigger.getObjectId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMappingTrigger com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ObjectMappingTrigger trigger) - trigger.objectId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(trigger)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ObjectMappingTrigger) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ObjectMappingTrigger handleCreate(com.soffid.iam.iga.api.ObjectMappingTrigger trigger) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ObjectMappingTrigger update(com.soffid.iam.iga.api.ObjectMappingTrigger trigger)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ObjectMappingTrigger update(
		final com.soffid.iam.iga.api.ObjectMappingTrigger trigger)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (trigger == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMappingTrigger com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ObjectMappingTrigger trigger) - trigger cannot be null");
		}
		if (trigger.getTrigger() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMappingTrigger com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ObjectMappingTrigger trigger) - trigger.trigger cannot be null");
		}
		if (trigger.getObjectId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ObjectMappingTrigger com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ObjectMappingTrigger trigger) - trigger.objectId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(trigger)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ObjectMappingTrigger) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ObjectMappingTrigger handleUpdate(com.soffid.iam.iga.api.ObjectMappingTrigger trigger) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ReconcileTrigger create(com.soffid.iam.iga.api.ReconcileTrigger rp)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ReconcileTrigger create(
		final com.soffid.iam.iga.api.ReconcileTrigger rp)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rp == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileTrigger com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ReconcileTrigger rp) - rp cannot be null");
		}
		if (rp.getTrigger() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileTrigger com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ReconcileTrigger rp) - rp.trigger cannot be null");
		}
		if (rp.getScript() == null || rp.getScript().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileTrigger com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ReconcileTrigger rp) - rp.script cannot be null");
		}
		if (rp.getSystem() == null || rp.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileTrigger com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.ReconcileTrigger rp) - rp.system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(rp)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ReconcileTrigger) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ReconcileTrigger handleCreate(com.soffid.iam.iga.api.ReconcileTrigger rp) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ReconcileTrigger update(com.soffid.iam.iga.api.ReconcileTrigger rp)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ReconcileTrigger update(
		final com.soffid.iam.iga.api.ReconcileTrigger rp)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rp == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileTrigger com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ReconcileTrigger rp) - rp cannot be null");
		}
		if (rp.getTrigger() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileTrigger com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ReconcileTrigger rp) - rp.trigger cannot be null");
		}
		if (rp.getScript() == null || rp.getScript().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileTrigger com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ReconcileTrigger rp) - rp.script cannot be null");
		}
		if (rp.getSystem() == null || rp.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ReconcileTrigger com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.ReconcileTrigger rp) - rp.system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(rp)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ReconcileTrigger) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ReconcileTrigger handleUpdate(com.soffid.iam.iga.api.ReconcileTrigger rp) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.System create(com.soffid.iam.iga.api.System dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.System create(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.System com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.System dispatcher) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.System com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.System dispatcher) - dispatcher.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(dispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.System) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.System handleCreate(com.soffid.iam.iga.api.System dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.System findDispatcherByName(java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.System findDispatcherByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.System com.soffid.iam.iga.service.DispatcherService.findDispatcherByName(java.lang.String codi) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDispatcherByName(codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.System) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findDispatcherByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findDispatcherByName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.System handleFindDispatcherByName(java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.System findSoffidDispatcher()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.System findSoffidDispatcher()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSoffidDispatcher()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.System) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findSoffidDispatcher", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findSoffidDispatcher", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.System handleFindSoffidDispatcher() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.System update(com.soffid.iam.iga.api.System dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.System update(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.System com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.System dispatcher) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.System com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.System dispatcher) - dispatcher.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(dispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.System) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.System handleUpdate(com.soffid.iam.iga.api.System dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.SystemGroup create(com.soffid.iam.iga.api.SystemGroup grupDispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.SystemGroup create(
		final com.soffid.iam.iga.api.SystemGroup grupDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grupDispatcher == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.SystemGroup com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.SystemGroup grupDispatcher) - grupDispatcher cannot be null");
		}
		if (grupDispatcher.getSystemCode() == null || grupDispatcher.getSystemCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.SystemGroup com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.SystemGroup grupDispatcher) - grupDispatcher.systemCode cannot be null");
		}
		if (grupDispatcher.getGroupCode() == null || grupDispatcher.getGroupCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.SystemGroup com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.SystemGroup grupDispatcher) - grupDispatcher.groupCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(grupDispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.SystemGroup) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.SystemGroup handleCreate(com.soffid.iam.iga.api.SystemGroup grupDispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.SystemGroup update(com.soffid.iam.iga.api.SystemGroup grupDispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.SystemGroup update(
		final com.soffid.iam.iga.api.SystemGroup grupDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grupDispatcher == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.SystemGroup com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.SystemGroup grupDispatcher) - grupDispatcher cannot be null");
		}
		if (grupDispatcher.getSystemCode() == null || grupDispatcher.getSystemCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.SystemGroup com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.SystemGroup grupDispatcher) - grupDispatcher.systemCode cannot be null");
		}
		if (grupDispatcher.getGroupCode() == null || grupDispatcher.getGroupCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.SystemGroup com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.SystemGroup grupDispatcher) - grupDispatcher.groupCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(grupDispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.SystemGroup) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.SystemGroup handleUpdate(com.soffid.iam.iga.api.SystemGroup grupDispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.UserTypeDispatcher create(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.UserTypeDispatcher create(
		final com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipusUsuari == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserTypeDispatcher com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari) - tipusUsuari cannot be null");
		}
		if (tipusUsuari.getType() == null || tipusUsuari.getType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserTypeDispatcher com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari) - tipusUsuari.type cannot be null");
		}
		if (tipusUsuari.getDispatcherCode() == null || tipusUsuari.getDispatcherCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserTypeDispatcher com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari) - tipusUsuari.dispatcherCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(tipusUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.UserTypeDispatcher) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.UserTypeDispatcher handleCreate(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.UserTypeDispatcher update(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.UserTypeDispatcher update(
		final com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipusUsuari == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserTypeDispatcher com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari) - tipusUsuari cannot be null");
		}
		if (tipusUsuari.getType() == null || tipusUsuari.getType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserTypeDispatcher com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari) - tipusUsuari.type cannot be null");
		}
		if (tipusUsuari.getDispatcherCode() == null || tipusUsuari.getDispatcherCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserTypeDispatcher com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari) - tipusUsuari.dispatcherCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(tipusUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.UserTypeDispatcher) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.UserTypeDispatcher handleUpdate(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.DebugTaskResults testPropagateObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.DebugTaskResults testPropagateObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.DebugTaskResults com.soffid.iam.iga.service.DispatcherService.testPropagateObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - dispatcher cannot be null");
		}
		if (type == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.DebugTaskResults com.soffid.iam.iga.service.DispatcherService.testPropagateObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - type cannot be null");
		}
		if (object1 == null || object1.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.DebugTaskResults com.soffid.iam.iga.service.DispatcherService.testPropagateObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - object1 cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleTestPropagateObject(dispatcher, type, object1, object2)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.DebugTaskResults) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.testPropagateObject", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.testPropagateObject", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.DebugTaskResults handleTestPropagateObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.GetObjectResults getNativeObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.GetObjectResults getNativeObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.GetObjectResults com.soffid.iam.iga.service.DispatcherService.getNativeObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - dispatcher cannot be null");
		}
		if (type == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.GetObjectResults com.soffid.iam.iga.service.DispatcherService.getNativeObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - type cannot be null");
		}
		if (object1 == null || object1.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.GetObjectResults com.soffid.iam.iga.service.DispatcherService.getNativeObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - object1 cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetNativeObject(dispatcher, type, object1, object2)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.GetObjectResults) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.getNativeObject", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.getNativeObject", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.GetObjectResults handleGetNativeObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.GetObjectResults getSoffidObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.GetObjectResults getSoffidObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.GetObjectResults com.soffid.iam.iga.service.DispatcherService.getSoffidObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - dispatcher cannot be null");
		}
		if (type == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.GetObjectResults com.soffid.iam.iga.service.DispatcherService.getSoffidObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - type cannot be null");
		}
		if (object1 == null || object1.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.GetObjectResults com.soffid.iam.iga.service.DispatcherService.getSoffidObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - object1 cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetSoffidObject(dispatcher, type, object1, object2)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.GetObjectResults) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.getSoffidObject", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.getSoffidObject", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.GetObjectResults handleGetSoffidObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.GetObjectResults reconcile(java.lang.String dispatcher, java.lang.String accountName)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.GetObjectResults reconcile(
		final java.lang.String dispatcher, 
		final java.lang.String accountName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.GetObjectResults com.soffid.iam.iga.service.DispatcherService.reconcile(java.lang.String dispatcher, java.lang.String accountName) - dispatcher cannot be null");
		}
		if (accountName == null || accountName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.GetObjectResults com.soffid.iam.iga.service.DispatcherService.reconcile(java.lang.String dispatcher, java.lang.String accountName) - accountName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleReconcile(dispatcher, accountName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.GetObjectResults) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.reconcile", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.reconcile", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.GetObjectResults handleReconcile(java.lang.String dispatcher, java.lang.String accountName) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.Server create(com.soffid.iam.sync.api.Server server)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.Server create(
		final com.soffid.iam.sync.api.Server server)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (server == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.Server com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.sync.api.Server server) - server cannot be null");
		}
		if (server.getName() == null || server.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.Server com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.sync.api.Server server) - server.name cannot be null");
		}
		if (server.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.Server com.soffid.iam.iga.service.DispatcherService.create(com.soffid.iam.sync.api.Server server) - server.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(server)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.Server) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.Server handleCreate(com.soffid.iam.sync.api.Server server) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.Server update(com.soffid.iam.sync.api.Server server)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.Server update(
		final com.soffid.iam.sync.api.Server server)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (server == null) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.Server com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.sync.api.Server server) - server cannot be null");
		}
		if (server.getName() == null || server.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.Server com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.sync.api.Server server) - server.name cannot be null");
		}
		if (server.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.Server com.soffid.iam.iga.service.DispatcherService.update(com.soffid.iam.sync.api.Server server) - server.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(server)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.Server) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.Server handleUpdate(com.soffid.iam.sync.api.Server server) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.ServerRegistrationToken consumeRegistrationToken(java.lang.String token)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.sync.api.ServerRegistrationToken consumeRegistrationToken(
		final java.lang.String token)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (token == null || token.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.sync.api.ServerRegistrationToken com.soffid.iam.iga.service.DispatcherService.consumeRegistrationToken(java.lang.String token) - token cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleConsumeRegistrationToken(token)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.sync.api.ServerRegistrationToken) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.consumeRegistrationToken", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.consumeRegistrationToken", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.sync.api.ServerRegistrationToken handleConsumeRegistrationToken(java.lang.String token) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.System> findSystems(com.soffid.zkdb.api.Query q)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.System> findSystems(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.System> com.soffid.iam.iga.service.DispatcherService.findSystems(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSystems(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.System>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findSystems", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findSystems", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.System> handleFindSystems(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.lang.Long invokeAsync(java.lang.String dispatcher, java.lang.String verb, java.lang.String object, java.util.Map<java.lang.String,java.lang.Object> attributes)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Long invokeAsync(
		final java.lang.String dispatcher, 
		final java.lang.String verb, 
		final java.lang.String object, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Long com.soffid.iam.iga.service.DispatcherService.invokeAsync(java.lang.String dispatcher, java.lang.String verb, java.lang.String object, java.util.Map<java.lang.String,java.lang.Object> attributes) - dispatcher cannot be null");
		}
		if (verb == null || verb.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Long com.soffid.iam.iga.service.DispatcherService.invokeAsync(java.lang.String dispatcher, java.lang.String verb, java.lang.String object, java.util.Map<java.lang.String,java.lang.Object> attributes) - verb cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleInvokeAsync(dispatcher, verb, object, attributes)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Long) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.invokeAsync", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.invokeAsync", (Throwable) __r[1]);
	}

	protected abstract java.lang.Long handleInvokeAsync(java.lang.String dispatcher, java.lang.String verb, java.lang.String object, java.util.Map<java.lang.String,java.lang.Object> attributes) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.lang.String generateChangesReport(com.soffid.iam.iga.api.System dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.DispatcherService.generateChangesReport(com.soffid.iam.iga.api.System dispatcher) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.DispatcherService.generateChangesReport(com.soffid.iam.iga.api.System dispatcher) - dispatcher.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateChangesReport(dispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.generateChangesReport", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.generateChangesReport", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGenerateChangesReport(com.soffid.iam.iga.api.System dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.lang.String preRegisterServer(com.soffid.iam.sync.api.ServerRegistrationToken register)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String preRegisterServer(
		final com.soffid.iam.sync.api.ServerRegistrationToken register)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (register == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.DispatcherService.preRegisterServer(com.soffid.iam.sync.api.ServerRegistrationToken register) - register cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handlePreRegisterServer(register)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.preRegisterServer", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.preRegisterServer", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handlePreRegisterServer(com.soffid.iam.sync.api.ServerRegistrationToken register) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.lang.String[] getServerTenants(com.soffid.iam.sync.api.Server server)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class}, readOnly=true)
	public java.lang.String[] getServerTenants(
		final com.soffid.iam.sync.api.Server server)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (server == null) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.iga.service.DispatcherService.getServerTenants(com.soffid.iam.sync.api.Server server) - server cannot be null");
		}
		if (server.getName() == null || server.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.iga.service.DispatcherService.getServerTenants(com.soffid.iam.sync.api.Server server) - server.name cannot be null");
		}
		if (server.getType() == null ) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.iga.service.DispatcherService.getServerTenants(com.soffid.iam.sync.api.Server server) - server.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetServerTenants(server)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.getServerTenants", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.getServerTenants", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleGetServerTenants(com.soffid.iam.sync.api.Server server) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.System> findAllActiveDispatchers()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.System> findAllActiveDispatchers()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllActiveDispatchers()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.System>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findAllActiveDispatchers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findAllActiveDispatchers", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.System> handleFindAllActiveDispatchers() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.sync.api.Server> findAllServers()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.api.Server> findAllServers()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllServers()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.sync.api.Server>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findAllServers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findAllServers", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.sync.api.Server> handleFindAllServers() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.AttributeMapping> findAttributeMappingsByObject(java.lang.Long objectId)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.AttributeMapping> findAttributeMappingsByObject(
		final java.lang.Long objectId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (objectId == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.AttributeMapping> com.soffid.iam.iga.service.DispatcherService.findAttributeMappingsByObject(java.lang.Long objectId) - objectId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAttributeMappingsByObject(objectId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.AttributeMapping>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findAttributeMappingsByObject", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findAttributeMappingsByObject", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.AttributeMapping> handleFindAttributeMappingsByObject(java.lang.Long objectId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.AccessControl> findAccessControlByDispatcherName(java.lang.String codiAgent)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.AccessControl> findAccessControlByDispatcherName(
		final java.lang.String codiAgent)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAgent == null || codiAgent.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.AccessControl> com.soffid.iam.iga.service.DispatcherService.findAccessControlByDispatcherName(java.lang.String codiAgent) - codiAgent cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccessControlByDispatcherName(codiAgent)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.AccessControl>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findAccessControlByDispatcherName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findAccessControlByDispatcherName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.AccessControl> handleFindAccessControlByDispatcherName(java.lang.String codiAgent) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.ObjectMappingProperty> findObjectMappingPropertiesByObject(java.lang.Long objectId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.ObjectMappingProperty> findObjectMappingPropertiesByObject(
		final java.lang.Long objectId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (objectId == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.ObjectMappingProperty> com.soffid.iam.iga.service.DispatcherService.findObjectMappingPropertiesByObject(java.lang.Long objectId) - objectId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindObjectMappingPropertiesByObject(objectId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.ObjectMappingProperty>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findObjectMappingPropertiesByObject", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findObjectMappingPropertiesByObject", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.ObjectMappingProperty> handleFindObjectMappingPropertiesByObject(java.lang.Long objectId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.ObjectMappingTrigger> findObjectMappingTriggersByObject(java.lang.Long objectId)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.ObjectMappingTrigger> findObjectMappingTriggersByObject(
		final java.lang.Long objectId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (objectId == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.ObjectMappingTrigger> com.soffid.iam.iga.service.DispatcherService.findObjectMappingTriggersByObject(java.lang.Long objectId) - objectId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindObjectMappingTriggersByObject(objectId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.ObjectMappingTrigger>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findObjectMappingTriggersByObject", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findObjectMappingTriggersByObject", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.ObjectMappingTrigger> handleFindObjectMappingTriggersByObject(java.lang.Long objectId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.ObjectMapping> findObjectMappingsByDispatcher(java.lang.Long dispatcherId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.ObjectMapping> findObjectMappingsByDispatcher(
		final java.lang.Long dispatcherId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcherId == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.ObjectMapping> com.soffid.iam.iga.service.DispatcherService.findObjectMappingsByDispatcher(java.lang.Long dispatcherId) - dispatcherId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindObjectMappingsByDispatcher(dispatcherId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.ObjectMapping>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findObjectMappingsByDispatcher", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findObjectMappingsByDispatcher", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.ObjectMapping> handleFindObjectMappingsByDispatcher(java.lang.Long dispatcherId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.ReconcileTrigger> findReconcileTriggersByDispatcher(java.lang.Long dispatcherId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.ReconcileTrigger> findReconcileTriggersByDispatcher(
		final java.lang.Long dispatcherId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcherId == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.ReconcileTrigger> com.soffid.iam.iga.service.DispatcherService.findReconcileTriggersByDispatcher(java.lang.Long dispatcherId) - dispatcherId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindReconcileTriggersByDispatcher(dispatcherId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.ReconcileTrigger>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findReconcileTriggersByDispatcher", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findReconcileTriggersByDispatcher", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.ReconcileTrigger> handleFindReconcileTriggersByDispatcher(java.lang.Long dispatcherId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.sync.api.ScheduledTask> findScheduledTasksByDispatcher(com.soffid.iam.iga.api.System dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.api.ScheduledTask> findScheduledTasksByDispatcher(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.sync.api.ScheduledTask> com.soffid.iam.iga.service.DispatcherService.findScheduledTasksByDispatcher(com.soffid.iam.iga.api.System dispatcher) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.sync.api.ScheduledTask> com.soffid.iam.iga.service.DispatcherService.findScheduledTasksByDispatcher(com.soffid.iam.iga.api.System dispatcher) - dispatcher.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindScheduledTasksByDispatcher(dispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.sync.api.ScheduledTask>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findScheduledTasksByDispatcher", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findScheduledTasksByDispatcher", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.sync.api.ScheduledTask> handleFindScheduledTasksByDispatcher(com.soffid.iam.iga.api.System dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.sync.api.Server> findTenantServers()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.api.Server> findTenantServers()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindTenantServers()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.sync.api.Server>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findTenantServers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findTenantServers", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.sync.api.Server> handleFindTenantServers() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.AccessControl> getAccessControl(com.soffid.iam.iga.api.System agent)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.AccessControl> getAccessControl(
		final com.soffid.iam.iga.api.System agent)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (agent == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.AccessControl> com.soffid.iam.iga.service.DispatcherService.getAccessControl(com.soffid.iam.iga.api.System agent) - agent cannot be null");
		}
		if (agent.getName() == null || agent.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.AccessControl> com.soffid.iam.iga.service.DispatcherService.getAccessControl(com.soffid.iam.iga.api.System agent) - agent.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAccessControl(agent)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.AccessControl>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.getAccessControl", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.getAccessControl", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.AccessControl> handleGetAccessControl(com.soffid.iam.iga.api.System agent) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.SystemGroup> getDispatcherGroups(com.soffid.iam.iga.api.System agent)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.SystemGroup> getDispatcherGroups(
		final com.soffid.iam.iga.api.System agent)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (agent == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.SystemGroup> com.soffid.iam.iga.service.DispatcherService.getDispatcherGroups(com.soffid.iam.iga.api.System agent) - agent cannot be null");
		}
		if (agent.getName() == null || agent.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.SystemGroup> com.soffid.iam.iga.service.DispatcherService.getDispatcherGroups(com.soffid.iam.iga.api.System agent) - agent.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDispatcherGroups(agent)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.SystemGroup>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.getDispatcherGroups", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.getDispatcherGroups", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.SystemGroup> handleGetDispatcherGroups(com.soffid.iam.iga.api.System agent) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.UserTypeDispatcher> getDispatcherUserTypes(com.soffid.iam.iga.api.System agent)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.UserTypeDispatcher> getDispatcherUserTypes(
		final com.soffid.iam.iga.api.System agent)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (agent == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.UserTypeDispatcher> com.soffid.iam.iga.service.DispatcherService.getDispatcherUserTypes(com.soffid.iam.iga.api.System agent) - agent cannot be null");
		}
		if (agent.getName() == null || agent.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.UserTypeDispatcher> com.soffid.iam.iga.service.DispatcherService.getDispatcherUserTypes(com.soffid.iam.iga.api.System agent) - agent.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDispatcherUserTypes(agent)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.UserTypeDispatcher>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.getDispatcherUserTypes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.getDispatcherUserTypes", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.UserTypeDispatcher> handleGetDispatcherUserTypes(com.soffid.iam.iga.api.System agent) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<java.util.Map<java.lang.String,java.lang.Object>> invoke(java.lang.String dispatcher, java.lang.String verb, java.lang.String object, java.util.Map<java.lang.String,java.lang.Object> attributes)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.util.Map<java.lang.String,java.lang.Object>> invoke(
		final java.lang.String dispatcher, 
		final java.lang.String verb, 
		final java.lang.String object, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.util.Map<java.lang.String,java.lang.Object>> com.soffid.iam.iga.service.DispatcherService.invoke(java.lang.String dispatcher, java.lang.String verb, java.lang.String object, java.util.Map<java.lang.String,java.lang.Object> attributes) - dispatcher cannot be null");
		}
		if (verb == null || verb.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.util.Map<java.lang.String,java.lang.Object>> com.soffid.iam.iga.service.DispatcherService.invoke(java.lang.String dispatcher, java.lang.String verb, java.lang.String object, java.util.Map<java.lang.String,java.lang.Object> attributes) - verb cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleInvoke(dispatcher, verb, object, attributes)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.util.Map<java.lang.String,java.lang.Object>>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.invoke", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.invoke", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.util.Map<java.lang.String,java.lang.Object>> handleInvoke(java.lang.String dispatcher, java.lang.String verb, java.lang.String object, java.util.Map<java.lang.String,java.lang.Object> attributes) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.List<java.lang.String> assignTemporaryPermissions(java.lang.String host, java.lang.String accountName, java.lang.String accountSystem, java.util.List<java.lang.String> permissions)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<java.lang.String> assignTemporaryPermissions(
		final java.lang.String host, 
		final java.lang.String accountName, 
		final java.lang.String accountSystem, 
		final java.util.List<java.lang.String> permissions)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (accountName == null || accountName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.iga.service.DispatcherService.assignTemporaryPermissions(java.lang.String host, java.lang.String accountName, java.lang.String accountSystem, java.util.List<java.lang.String> permissions) - accountName cannot be null");
		}
		if (accountSystem == null || accountSystem.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.iga.service.DispatcherService.assignTemporaryPermissions(java.lang.String host, java.lang.String accountName, java.lang.String accountSystem, java.util.List<java.lang.String> permissions) - accountSystem cannot be null");
		}
		if (permissions == null ) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.iga.service.DispatcherService.assignTemporaryPermissions(java.lang.String host, java.lang.String accountName, java.lang.String accountSystem, java.util.List<java.lang.String> permissions) - permissions cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleAssignTemporaryPermissions(host, accountName, accountSystem, permissions)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.assignTemporaryPermissions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.assignTemporaryPermissions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<java.lang.String> handleAssignTemporaryPermissions(java.lang.String host, java.lang.String accountName, java.lang.String accountSystem, java.util.List<java.lang.String> permissions) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.List<java.security.cert.X509Certificate> findValidCertificates()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<java.security.cert.X509Certificate> findValidCertificates()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindValidCertificates()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<java.security.cert.X509Certificate>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.findValidCertificates", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.findValidCertificates", (Throwable) __r[1]);
	}

	protected abstract java.util.List<java.security.cert.X509Certificate> handleFindValidCertificates() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Map<java.lang.String,java.lang.Object> testObjectMapping(java.util.Map<java.lang.String,java.lang.String> sentences, java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Map<java.lang.String,java.lang.Object> testObjectMapping(
		final java.util.Map<java.lang.String,java.lang.String> sentences, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (sentences == null) {
			throw new IllegalArgumentException("java.util.Map<java.lang.String,java.lang.Object> com.soffid.iam.iga.service.DispatcherService.testObjectMapping(java.util.Map<java.lang.String,java.lang.String> sentences, java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - sentences cannot be null");
		}
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Map<java.lang.String,java.lang.Object> com.soffid.iam.iga.service.DispatcherService.testObjectMapping(java.util.Map<java.lang.String,java.lang.String> sentences, java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - dispatcher cannot be null");
		}
		if (type == null) {
			throw new IllegalArgumentException("java.util.Map<java.lang.String,java.lang.Object> com.soffid.iam.iga.service.DispatcherService.testObjectMapping(java.util.Map<java.lang.String,java.lang.String> sentences, java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - type cannot be null");
		}
		if (object1 == null || object1.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Map<java.lang.String,java.lang.Object> com.soffid.iam.iga.service.DispatcherService.testObjectMapping(java.util.Map<java.lang.String,java.lang.String> sentences, java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) - object1 cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleTestObjectMapping(sentences, dispatcher, type, object1, object2)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Map<java.lang.String,java.lang.Object>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.testObjectMapping", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.testObjectMapping", (Throwable) __r[1]);
	}

	protected abstract java.util.Map<java.lang.String,java.lang.Object> handleTestObjectMapping(java.util.Map<java.lang.String,java.lang.String> sentences, java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void addCertificate(com.soffid.iam.sync.api.Server server, java.security.cert.X509Certificate cert)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void addCertificate(
		final com.soffid.iam.sync.api.Server server, 
		final java.security.cert.X509Certificate cert)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (server == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.addCertificate(com.soffid.iam.sync.api.Server server, java.security.cert.X509Certificate cert) - server cannot be null");
		}
		if (server.getName() == null || server.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.addCertificate(com.soffid.iam.sync.api.Server server, java.security.cert.X509Certificate cert) - server.name cannot be null");
		}
		if (server.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.addCertificate(com.soffid.iam.sync.api.Server server, java.security.cert.X509Certificate cert) - server.type cannot be null");
		}
		if (cert == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.addCertificate(com.soffid.iam.sync.api.Server server, java.security.cert.X509Certificate cert) - cert cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleAddCertificate(server, cert);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.addCertificate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.addCertificate", (Throwable) __r[1]);
	}

	protected abstract void handleAddCertificate(com.soffid.iam.sync.api.Server server, java.security.cert.X509Certificate cert) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void applyConfiguration(com.soffid.iam.iga.api.System dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void applyConfiguration(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.applyConfiguration(com.soffid.iam.iga.api.System dispatcher) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.applyConfiguration(com.soffid.iam.iga.api.System dispatcher) - dispatcher.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleApplyConfiguration(dispatcher);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.applyConfiguration", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.applyConfiguration", (Throwable) __r[1]);
	}

	protected abstract void handleApplyConfiguration(com.soffid.iam.iga.api.System dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void checkConnectivity(java.lang.String dispatcher)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void checkConnectivity(
		final java.lang.String dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.checkConnectivity(java.lang.String dispatcher) - dispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCheckConnectivity(dispatcher);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.checkConnectivity", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.checkConnectivity", (Throwable) __r[1]);
	}

	protected abstract void handleCheckConnectivity(java.lang.String dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.AccessControl controlAcces)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.AccessControl controlAcces)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(controlAcces);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.AccessControl controlAcces) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.AttributeMapping mapping)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.AttributeMapping mapping)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(mapping);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.AttributeMapping mapping) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.ObjectMapping om)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.ObjectMapping om)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(om);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.ObjectMapping om) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.ObjectMappingProperty omp)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.ObjectMappingProperty omp)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(omp);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.ObjectMappingProperty omp) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.ObjectMappingTrigger tirger)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.ObjectMappingTrigger tirger)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(tirger);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.ObjectMappingTrigger tirger) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.ReconcileTrigger rp)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.ReconcileTrigger rp)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(rp);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.ReconcileTrigger rp) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.System dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(dispatcher);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.System dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.SystemGroup grupDispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.SystemGroup grupDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(grupDispatcher);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.SystemGroup grupDispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(tipusUsuari);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.sync.api.Server server)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.sync.api.Server server)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(server);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.sync.api.Server server) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void propagateGroupsForSystem(java.lang.String codiAgent, com.soffid.iam.sync.api.ScheduledTask task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void propagateGroupsForSystem(
		final java.lang.String codiAgent, 
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAgent == null || codiAgent.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.propagateGroupsForSystem(java.lang.String codiAgent, com.soffid.iam.sync.api.ScheduledTask task) - codiAgent cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handlePropagateGroupsForSystem(codiAgent, task);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.propagateGroupsForSystem", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.propagateGroupsForSystem", (Throwable) __r[1]);
	}

	protected abstract void handlePropagateGroupsForSystem(java.lang.String codiAgent, com.soffid.iam.sync.api.ScheduledTask task) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void propagateRolesForSystem(java.lang.String codiAgent, com.soffid.iam.sync.api.ScheduledTask task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void propagateRolesForSystem(
		final java.lang.String codiAgent, 
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAgent == null || codiAgent.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.propagateRolesForSystem(java.lang.String codiAgent, com.soffid.iam.sync.api.ScheduledTask task) - codiAgent cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handlePropagateRolesForSystem(codiAgent, task);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.propagateRolesForSystem", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.propagateRolesForSystem", (Throwable) __r[1]);
	}

	protected abstract void handlePropagateRolesForSystem(java.lang.String codiAgent, com.soffid.iam.sync.api.ScheduledTask task) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void propagateUsersForSystem(java.lang.String codiAgent, com.soffid.iam.sync.api.ScheduledTask task)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void propagateUsersForSystem(
		final java.lang.String codiAgent, 
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAgent == null || codiAgent.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.propagateUsersForSystem(java.lang.String codiAgent, com.soffid.iam.sync.api.ScheduledTask task) - codiAgent cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handlePropagateUsersForSystem(codiAgent, task);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.propagateUsersForSystem", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.propagateUsersForSystem", (Throwable) __r[1]);
	}

	protected abstract void handlePropagateUsersForSystem(java.lang.String codiAgent, com.soffid.iam.sync.api.ScheduledTask task) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void removeTemporaryPermissions(java.lang.String host, java.lang.String accountName, java.lang.String accountSystem, java.util.List<java.lang.String> permissions)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void removeTemporaryPermissions(
		final java.lang.String host, 
		final java.lang.String accountName, 
		final java.lang.String accountSystem, 
		final java.util.List<java.lang.String> permissions)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (host == null || host.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.removeTemporaryPermissions(java.lang.String host, java.lang.String accountName, java.lang.String accountSystem, java.util.List<java.lang.String> permissions) - host cannot be null");
		}
		if (accountName == null || accountName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.removeTemporaryPermissions(java.lang.String host, java.lang.String accountName, java.lang.String accountSystem, java.util.List<java.lang.String> permissions) - accountName cannot be null");
		}
		if (accountSystem == null || accountSystem.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.removeTemporaryPermissions(java.lang.String host, java.lang.String accountName, java.lang.String accountSystem, java.util.List<java.lang.String> permissions) - accountSystem cannot be null");
		}
		if (permissions == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.removeTemporaryPermissions(java.lang.String host, java.lang.String accountName, java.lang.String accountSystem, java.util.List<java.lang.String> permissions) - permissions cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemoveTemporaryPermissions(host, accountName, accountSystem, permissions);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.removeTemporaryPermissions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.removeTemporaryPermissions", (Throwable) __r[1]);
	}

	protected abstract void handleRemoveTemporaryPermissions(java.lang.String host, java.lang.String accountName, java.lang.String accountSystem, java.util.List<java.lang.String> permissions) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void renameAccounts(com.soffid.iam.iga.api.System dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void renameAccounts(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.renameAccounts(com.soffid.iam.iga.api.System dispatcher) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.renameAccounts(com.soffid.iam.iga.api.System dispatcher) - dispatcher.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRenameAccounts(dispatcher);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.renameAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.renameAccounts", (Throwable) __r[1]);
	}

	protected abstract void handleRenameAccounts(com.soffid.iam.iga.api.System dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#	 * @see com.soffid.iam.iga.service.DispatcherService#void setDefaultMappingsByDispatcher(java.lang.Long dispatcherId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void setDefaultMappingsByDispatcher(
		final java.lang.Long dispatcherId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcherId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DispatcherService.setDefaultMappingsByDispatcher(java.lang.Long dispatcherId) - dispatcherId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSetDefaultMappingsByDispatcher(dispatcherId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DispatcherService.class).
			warn ("Error on DispatcherService.setDefaultMappingsByDispatcher", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DispatcherService.setDefaultMappingsByDispatcher", (Throwable) __r[1]);
	}

	protected abstract void handleSetDefaultMappingsByDispatcher(java.lang.Long dispatcherId) throws Exception;

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
