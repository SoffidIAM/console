//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.ApplicationBootService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.ApplicationBootService
 */
public abstract class ApplicationBootServiceBase
	implements com.soffid.iam.impl.service.ApplicationBootService
 {
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

	private com.soffid.iam.base.service.AdditionalDataService additionalDataService;

	/**
	 * Sets reference to <code>additionalDataService</code>.
	 */
	public void setAdditionalDataService (com.soffid.iam.base.service.AdditionalDataService additionalDataService) {
		this.additionalDataService = additionalDataService;
	}

	/**
	 * Gets reference to <code>additionalDataService</code>.
	 */
	public com.soffid.iam.base.service.AdditionalDataService getAdditionalDataService () {
		return additionalDataService;
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

	private com.soffid.iam.bpm.service.BpmConfigService bpmConfigService;

	/**
	 * Sets reference to <code>bpmConfigService</code>.
	 */
	public void setBpmConfigService (com.soffid.iam.bpm.service.BpmConfigService bpmConfigService) {
		this.bpmConfigService = bpmConfigService;
	}

	/**
	 * Gets reference to <code>bpmConfigService</code>.
	 */
	public com.soffid.iam.bpm.service.BpmConfigService getBpmConfigService () {
		return bpmConfigService;
	}

	private com.soffid.iam.bpm.service.BpmEngine bpmEngine;

	/**
	 * Sets reference to <code>bpmEngine</code>.
	 */
	public void setBpmEngine (com.soffid.iam.bpm.service.BpmEngine bpmEngine) {
		this.bpmEngine = bpmEngine;
	}

	/**
	 * Gets reference to <code>bpmEngine</code>.
	 */
	public com.soffid.iam.bpm.service.BpmEngine getBpmEngine () {
		return bpmEngine;
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

	private com.soffid.iam.impl.service.CrudRegistryService crudRegistryService;

	/**
	 * Sets reference to <code>crudRegistryService</code>.
	 */
	public void setCrudRegistryService (com.soffid.iam.impl.service.CrudRegistryService crudRegistryService) {
		this.crudRegistryService = crudRegistryService;
	}

	/**
	 * Gets reference to <code>crudRegistryService</code>.
	 */
	public com.soffid.iam.impl.service.CrudRegistryService getCrudRegistryService () {
		return crudRegistryService;
	}

	private com.soffid.iam.iga.model.CustomObjectTypeEntityDao customObjectTypeEntityDao;

	/**
	 * Sets reference to <code>customObjectTypeEntityDao</code>.
	 */
	public void setCustomObjectTypeEntityDao (com.soffid.iam.iga.model.CustomObjectTypeEntityDao customObjectTypeEntityDao) {
		this.customObjectTypeEntityDao = customObjectTypeEntityDao;
	}

	/**
	 * Gets reference to <code>customObjectTypeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntityDao getCustomObjectTypeEntityDao () {
		return customObjectTypeEntityDao;
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

	private com.soffid.iam.am.service.EntryPointService entryPointService;

	/**
	 * Sets reference to <code>entryPointService</code>.
	 */
	public void setEntryPointService (com.soffid.iam.am.service.EntryPointService entryPointService) {
		this.entryPointService = entryPointService;
	}

	/**
	 * Gets reference to <code>entryPointService</code>.
	 */
	public com.soffid.iam.am.service.EntryPointService getEntryPointService () {
		return entryPointService;
	}

	private com.soffid.iam.iga.service.GroupService groupService;

	/**
	 * Sets reference to <code>groupService</code>.
	 */
	public void setGroupService (com.soffid.iam.iga.service.GroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * Gets reference to <code>groupService</code>.
	 */
	public com.soffid.iam.iga.service.GroupService getGroupService () {
		return groupService;
	}

	private com.soffid.iam.impl.service.InternalPasswordService internalPasswordService;

	/**
	 * Sets reference to <code>internalPasswordService</code>.
	 */
	public void setInternalPasswordService (com.soffid.iam.impl.service.InternalPasswordService internalPasswordService) {
		this.internalPasswordService = internalPasswordService;
	}

	/**
	 * Gets reference to <code>internalPasswordService</code>.
	 */
	public com.soffid.iam.impl.service.InternalPasswordService getInternalPasswordService () {
		return internalPasswordService;
	}

	private com.soffid.iam.rc.service.IssuePolicyService issuePolicyService;

	/**
	 * Sets reference to <code>issuePolicyService</code>.
	 */
	public void setIssuePolicyService (com.soffid.iam.rc.service.IssuePolicyService issuePolicyService) {
		this.issuePolicyService = issuePolicyService;
	}

	/**
	 * Gets reference to <code>issuePolicyService</code>.
	 */
	public com.soffid.iam.rc.service.IssuePolicyService getIssuePolicyService () {
		return issuePolicyService;
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

	private com.soffid.iam.am.service.NetworkService networkService;

	/**
	 * Sets reference to <code>networkService</code>.
	 */
	public void setNetworkService (com.soffid.iam.am.service.NetworkService networkService) {
		this.networkService = networkService;
	}

	/**
	 * Gets reference to <code>networkService</code>.
	 */
	public com.soffid.iam.am.service.NetworkService getNetworkService () {
		return networkService;
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

	private com.soffid.iam.base.service.ServerPluginService serverPluginService;

	/**
	 * Sets reference to <code>serverPluginService</code>.
	 */
	public void setServerPluginService (com.soffid.iam.base.service.ServerPluginService serverPluginService) {
		this.serverPluginService = serverPluginService;
	}

	/**
	 * Gets reference to <code>serverPluginService</code>.
	 */
	public com.soffid.iam.base.service.ServerPluginService getServerPluginService () {
		return serverPluginService;
	}

	private com.soffid.iam.rc.service.SoDRuleService soDRuleService;

	/**
	 * Sets reference to <code>soDRuleService</code>.
	 */
	public void setSoDRuleService (com.soffid.iam.rc.service.SoDRuleService soDRuleService) {
		this.soDRuleService = soDRuleService;
	}

	/**
	 * Gets reference to <code>soDRuleService</code>.
	 */
	public com.soffid.iam.rc.service.SoDRuleService getSoDRuleService () {
		return soDRuleService;
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

	private com.soffid.iam.base.service.TenantService tenantService;

	/**
	 * Sets reference to <code>tenantService</code>.
	 */
	public void setTenantService (com.soffid.iam.base.service.TenantService tenantService) {
		this.tenantService = tenantService;
	}

	/**
	 * Gets reference to <code>tenantService</code>.
	 */
	public com.soffid.iam.base.service.TenantService getTenantService () {
		return tenantService;
	}

	private com.soffid.iam.iga.service.UserDomainService userDomainService;

	/**
	 * Sets reference to <code>userDomainService</code>.
	 */
	public void setUserDomainService (com.soffid.iam.iga.service.UserDomainService userDomainService) {
		this.userDomainService = userDomainService;
	}

	/**
	 * Gets reference to <code>userDomainService</code>.
	 */
	public com.soffid.iam.iga.service.UserDomainService getUserDomainService () {
		return userDomainService;
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


	/**
	 * @see com.soffid.iam.impl.service.ApplicationBootService#	 * @see com.soffid.iam.impl.service.ApplicationBootService#com.soffid.iam.impl.api.UpgradeStatus doUpgrade()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.impl.api.UpgradeStatus doUpgrade()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDoUpgrade()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.impl.api.UpgradeStatus) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ApplicationBootService.class).
			warn ("Error on ApplicationBootService.doUpgrade", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationBootService.doUpgrade", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.impl.api.UpgradeStatus handleDoUpgrade() throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.ApplicationBootService#	 * @see com.soffid.iam.impl.service.ApplicationBootService#com.soffid.iam.impl.api.UpgradeStatus getUpgradeStatus()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.impl.api.UpgradeStatus getUpgradeStatus()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUpgradeStatus()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.impl.api.UpgradeStatus) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ApplicationBootService.class).
			warn ("Error on ApplicationBootService.getUpgradeStatus", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationBootService.getUpgradeStatus", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.impl.api.UpgradeStatus handleGetUpgradeStatus() throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.ApplicationBootService#	 * @see com.soffid.iam.impl.service.ApplicationBootService#void consoleBoot()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackForClassName={"java.lang.Exception"})
	public void consoleBoot()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleConsoleBoot();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ApplicationBootService.class).
			warn ("Error on ApplicationBootService.consoleBoot", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationBootService.consoleBoot", (Throwable) __r[1]);
	}

	protected abstract void handleConsoleBoot() throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.ApplicationBootService#	 * @see com.soffid.iam.impl.service.ApplicationBootService#void doSyncUpgrade()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void doSyncUpgrade()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDoSyncUpgrade();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ApplicationBootService.class).
			warn ("Error on ApplicationBootService.doSyncUpgrade", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationBootService.doSyncUpgrade", (Throwable) __r[1]);
	}

	protected abstract void handleDoSyncUpgrade() throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.ApplicationBootService#	 * @see com.soffid.iam.impl.service.ApplicationBootService#void syncServerBoot()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackForClassName={"java.lang.Exception"})
	public void syncServerBoot()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSyncServerBoot();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ApplicationBootService.class).
			warn ("Error on ApplicationBootService.syncServerBoot", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationBootService.syncServerBoot", (Throwable) __r[1]);
	}

	protected abstract void handleSyncServerBoot() throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.ApplicationBootService#	 * @see com.soffid.iam.impl.service.ApplicationBootService#void tenantBoot(com.soffid.iam.base.api.Tenant tenant)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackForClassName={"java.lang.Exception"})
	public void tenantBoot(
		final com.soffid.iam.base.api.Tenant tenant)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tenant == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.ApplicationBootService.tenantBoot(com.soffid.iam.base.api.Tenant tenant) - tenant cannot be null");
		}
		if (tenant.getName() == null || tenant.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.ApplicationBootService.tenantBoot(com.soffid.iam.base.api.Tenant tenant) - tenant.name cannot be null");
		}
		if (tenant.getDescription() == null || tenant.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.ApplicationBootService.tenantBoot(com.soffid.iam.base.api.Tenant tenant) - tenant.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleTenantBoot(tenant);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ApplicationBootService.class).
			warn ("Error on ApplicationBootService.tenantBoot", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationBootService.tenantBoot", (Throwable) __r[1]);
	}

	protected abstract void handleTenantBoot(com.soffid.iam.base.api.Tenant tenant) throws Exception;

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
