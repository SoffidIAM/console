//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.ApplicationService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.ApplicationService
 */
public abstract class ApplicationServiceBase
	implements com.soffid.iam.iga.service.ApplicationService
 {
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

	private com.soffid.iam.service.impl.AttributeValidationService attributeValidationService;

	/**
	 * Sets reference to <code>attributeValidationService</code>.
	 */
	public void setAttributeValidationService (com.soffid.iam.service.impl.AttributeValidationService attributeValidationService) {
		this.attributeValidationService = attributeValidationService;
	}

	/**
	 * Gets reference to <code>attributeValidationService</code>.
	 */
	public com.soffid.iam.service.impl.AttributeValidationService getAttributeValidationService () {
		return attributeValidationService;
	}

	private com.soffid.iam.base.model.AuthorizationEntityDao authorizationEntityDao;

	/**
	 * Sets reference to <code>authorizationEntityDao</code>.
	 */
	public void setAuthorizationEntityDao (com.soffid.iam.base.model.AuthorizationEntityDao authorizationEntityDao) {
		this.authorizationEntityDao = authorizationEntityDao;
	}

	/**
	 * Gets reference to <code>authorizationEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AuthorizationEntityDao getAuthorizationEntityDao () {
		return authorizationEntityDao;
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

	private com.soffid.iam.iga.model.DomainValueEntityDao domainValueEntityDao;

	/**
	 * Sets reference to <code>domainValueEntityDao</code>.
	 */
	public void setDomainValueEntityDao (com.soffid.iam.iga.model.DomainValueEntityDao domainValueEntityDao) {
		this.domainValueEntityDao = domainValueEntityDao;
	}

	/**
	 * Gets reference to <code>domainValueEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.DomainValueEntityDao getDomainValueEntityDao () {
		return domainValueEntityDao;
	}

	private com.soffid.iam.iga.service.EntitlementDelegationService entitlementDelegationService;

	/**
	 * Sets reference to <code>entitlementDelegationService</code>.
	 */
	public void setEntitlementDelegationService (com.soffid.iam.iga.service.EntitlementDelegationService entitlementDelegationService) {
		this.entitlementDelegationService = entitlementDelegationService;
	}

	/**
	 * Gets reference to <code>entitlementDelegationService</code>.
	 */
	public com.soffid.iam.iga.service.EntitlementDelegationService getEntitlementDelegationService () {
		return entitlementDelegationService;
	}

	private com.soffid.iam.am.model.EntryPointRoleEntityDao entryPointRoleEntityDao;

	/**
	 * Sets reference to <code>entryPointRoleEntityDao</code>.
	 */
	public void setEntryPointRoleEntityDao (com.soffid.iam.am.model.EntryPointRoleEntityDao entryPointRoleEntityDao) {
		this.entryPointRoleEntityDao = entryPointRoleEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointRoleEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointRoleEntityDao getEntryPointRoleEntityDao () {
		return entryPointRoleEntityDao;
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

	private com.soffid.iam.iga.model.InformationSystemAttributeEntityDao informationSystemAttributeEntityDao;

	/**
	 * Sets reference to <code>informationSystemAttributeEntityDao</code>.
	 */
	public void setInformationSystemAttributeEntityDao (com.soffid.iam.iga.model.InformationSystemAttributeEntityDao informationSystemAttributeEntityDao) {
		this.informationSystemAttributeEntityDao = informationSystemAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>informationSystemAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.InformationSystemAttributeEntityDao getInformationSystemAttributeEntityDao () {
		return informationSystemAttributeEntityDao;
	}

	private com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao;

	/**
	 * Sets reference to <code>informationSystemEntityDao</code>.
	 */
	public void setInformationSystemEntityDao (com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao) {
		this.informationSystemEntityDao = informationSystemEntityDao;
	}

	/**
	 * Gets reference to <code>informationSystemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.InformationSystemEntityDao getInformationSystemEntityDao () {
		return informationSystemEntityDao;
	}

	private com.soffid.iam.rc.model.IssueEntityDao issueEntityDao;

	/**
	 * Sets reference to <code>issueEntityDao</code>.
	 */
	public void setIssueEntityDao (com.soffid.iam.rc.model.IssueEntityDao issueEntityDao) {
		this.issueEntityDao = issueEntityDao;
	}

	/**
	 * Gets reference to <code>issueEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueEntityDao getIssueEntityDao () {
		return issueEntityDao;
	}

	private com.soffid.iam.rc.service.IssueService issueService;

	/**
	 * Sets reference to <code>issueService</code>.
	 */
	public void setIssueService (com.soffid.iam.rc.service.IssueService issueService) {
		this.issueService = issueService;
	}

	/**
	 * Gets reference to <code>issueService</code>.
	 */
	public com.soffid.iam.rc.service.IssueService getIssueService () {
		return issueService;
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

	private com.soffid.iam.am.model.NetworkAuthorizationEntityDao networkAuthorizationEntityDao;

	/**
	 * Sets reference to <code>networkAuthorizationEntityDao</code>.
	 */
	public void setNetworkAuthorizationEntityDao (com.soffid.iam.am.model.NetworkAuthorizationEntityDao networkAuthorizationEntityDao) {
		this.networkAuthorizationEntityDao = networkAuthorizationEntityDao;
	}

	/**
	 * Gets reference to <code>networkAuthorizationEntityDao</code>.
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntityDao getNetworkAuthorizationEntityDao () {
		return networkAuthorizationEntityDao;
	}

	private com.soffid.iam.iga.model.NoticeEntityDao noticeEntityDao;

	/**
	 * Sets reference to <code>noticeEntityDao</code>.
	 */
	public void setNoticeEntityDao (com.soffid.iam.iga.model.NoticeEntityDao noticeEntityDao) {
		this.noticeEntityDao = noticeEntityDao;
	}

	/**
	 * Gets reference to <code>noticeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.NoticeEntityDao getNoticeEntityDao () {
		return noticeEntityDao;
	}

	private com.soffid.iam.iga.model.RoleAccountAttributeEntityDao roleAccountAttributeEntityDao;

	/**
	 * Sets reference to <code>roleAccountAttributeEntityDao</code>.
	 */
	public void setRoleAccountAttributeEntityDao (com.soffid.iam.iga.model.RoleAccountAttributeEntityDao roleAccountAttributeEntityDao) {
		this.roleAccountAttributeEntityDao = roleAccountAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>roleAccountAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleAccountAttributeEntityDao getRoleAccountAttributeEntityDao () {
		return roleAccountAttributeEntityDao;
	}

	private com.soffid.iam.iga.model.RoleAccountEntityDao roleAccountEntityDao;

	/**
	 * Sets reference to <code>roleAccountEntityDao</code>.
	 */
	public void setRoleAccountEntityDao (com.soffid.iam.iga.model.RoleAccountEntityDao roleAccountEntityDao) {
		this.roleAccountEntityDao = roleAccountEntityDao;
	}

	/**
	 * Gets reference to <code>roleAccountEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleAccountEntityDao getRoleAccountEntityDao () {
		return roleAccountEntityDao;
	}

	private com.soffid.iam.iga.model.RoleAttributeEntityDao roleAttributeEntityDao;

	/**
	 * Sets reference to <code>roleAttributeEntityDao</code>.
	 */
	public void setRoleAttributeEntityDao (com.soffid.iam.iga.model.RoleAttributeEntityDao roleAttributeEntityDao) {
		this.roleAttributeEntityDao = roleAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>roleAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleAttributeEntityDao getRoleAttributeEntityDao () {
		return roleAttributeEntityDao;
	}

	private com.soffid.iam.iga.model.RoleDependencyEntityDao roleDependencyEntityDao;

	/**
	 * Sets reference to <code>roleDependencyEntityDao</code>.
	 */
	public void setRoleDependencyEntityDao (com.soffid.iam.iga.model.RoleDependencyEntityDao roleDependencyEntityDao) {
		this.roleDependencyEntityDao = roleDependencyEntityDao;
	}

	/**
	 * Gets reference to <code>roleDependencyEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleDependencyEntityDao getRoleDependencyEntityDao () {
		return roleDependencyEntityDao;
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

	private com.soffid.iam.iga.model.RoleGroupEntityDao roleGroupEntityDao;

	/**
	 * Sets reference to <code>roleGroupEntityDao</code>.
	 */
	public void setRoleGroupEntityDao (com.soffid.iam.iga.model.RoleGroupEntityDao roleGroupEntityDao) {
		this.roleGroupEntityDao = roleGroupEntityDao;
	}

	/**
	 * Gets reference to <code>roleGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleGroupEntityDao getRoleGroupEntityDao () {
		return roleGroupEntityDao;
	}

	private com.soffid.iam.impl.service.RuleEvaluatorService ruleEvaluatorService;

	/**
	 * Sets reference to <code>ruleEvaluatorService</code>.
	 */
	public void setRuleEvaluatorService (com.soffid.iam.impl.service.RuleEvaluatorService ruleEvaluatorService) {
		this.ruleEvaluatorService = ruleEvaluatorService;
	}

	/**
	 * Gets reference to <code>ruleEvaluatorService</code>.
	 */
	public com.soffid.iam.impl.service.RuleEvaluatorService getRuleEvaluatorService () {
		return ruleEvaluatorService;
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

	private com.soffid.iam.base.model.UserAccountEntityDao userAccountEntityDao;

	/**
	 * Sets reference to <code>userAccountEntityDao</code>.
	 */
	public void setUserAccountEntityDao (com.soffid.iam.base.model.UserAccountEntityDao userAccountEntityDao) {
		this.userAccountEntityDao = userAccountEntityDao;
	}

	/**
	 * Gets reference to <code>userAccountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserAccountEntityDao getUserAccountEntityDao () {
		return userAccountEntityDao;
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


	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValueByText(com.soffid.iam.iga.api.Domain domain, java.lang.String text)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValueByText(
		final com.soffid.iam.iga.api.Domain domain, 
		final java.lang.String text)
		throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception
	{
		if (domain == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> com.soffid.iam.iga.service.ApplicationService.findDomainValueByText(com.soffid.iam.iga.api.Domain domain, java.lang.String text) - domain cannot be null");
		}
		if (domain.getName() == null || domain.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> com.soffid.iam.iga.service.ApplicationService.findDomainValueByText(com.soffid.iam.iga.api.Domain domain, java.lang.String text) - domain.name cannot be null");
		}
		if (text == null || text.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> com.soffid.iam.iga.service.ApplicationService.findDomainValueByText(com.soffid.iam.iga.api.Domain domain, java.lang.String text) - text cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDomainValueByText(domain, text)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue>) __r[0];
		if (__r[1] instanceof java.lang.Exception) 
			throw (java.lang.Exception) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findDomainValueByText", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findDomainValueByText", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> handleFindDomainValueByText(com.soffid.iam.iga.api.Domain domain, java.lang.String text) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValueByTextAsync(com.soffid.iam.iga.api.Domain domain, java.lang.String text)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValueByTextAsync(
		final com.soffid.iam.iga.api.Domain domain, 
		final java.lang.String text)
		throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception
	{
		if (domain == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> com.soffid.iam.iga.service.ApplicationService.findDomainValueByTextAsync(com.soffid.iam.iga.api.Domain domain, java.lang.String text) - domain cannot be null");
		}
		if (domain.getName() == null || domain.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> com.soffid.iam.iga.service.ApplicationService.findDomainValueByTextAsync(com.soffid.iam.iga.api.Domain domain, java.lang.String text) - domain.name cannot be null");
		}
		if (text == null || text.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> com.soffid.iam.iga.service.ApplicationService.findDomainValueByTextAsync(com.soffid.iam.iga.api.Domain domain, java.lang.String text) - text cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDomainValueByTextAsync(domain, text)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue>) __r[0];
		if (__r[1] instanceof java.lang.Exception) 
			throw (java.lang.Exception) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findDomainValueByTextAsync", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findDomainValueByTextAsync", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> handleFindDomainValueByTextAsync(com.soffid.iam.iga.api.Domain domain, java.lang.String text) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<java.lang.String> findRoleNames(java.lang.String systemName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		readOnly=true)
	public java.util.Collection<java.lang.String> findRoleNames(
		final java.lang.String systemName)
		throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception
	{
		if (systemName == null || systemName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.iga.service.ApplicationService.findRoleNames(java.lang.String systemName) - systemName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleNames(systemName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.String>) __r[0];
		if (__r[1] instanceof java.lang.Exception) 
			throw (java.lang.Exception) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleNames", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleNames", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.String> handleFindRoleNames(java.lang.String systemName) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.base.api.AsyncProcessTracker removeRedundantRoles(java.lang.String query)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AsyncProcessTracker removeRedundantRoles(
		final java.lang.String query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleRemoveRedundantRoles(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.AsyncProcessTracker) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.removeRedundantRoles", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.removeRedundantRoles", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AsyncProcessTracker handleRemoveRedundantRoles(java.lang.String query) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.InformationSystem create(com.soffid.iam.iga.api.InformationSystem aplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.InformationSystem create(
		final com.soffid.iam.iga.api.InformationSystem aplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (aplicacio == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.InformationSystem com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.InformationSystem aplicacio) - aplicacio cannot be null");
		}
		if (aplicacio.getRelativeName() == null || aplicacio.getRelativeName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.InformationSystem com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.InformationSystem aplicacio) - aplicacio.relativeName cannot be null");
		}
		if (aplicacio.getName() == null || aplicacio.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.InformationSystem com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.InformationSystem aplicacio) - aplicacio.name cannot be null");
		}
		if (aplicacio.getDescription() == null || aplicacio.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.InformationSystem com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.InformationSystem aplicacio) - aplicacio.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(aplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.InformationSystem) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.InformationSystem handleCreate(com.soffid.iam.iga.api.InformationSystem aplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationNameUnrestricted(java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationNameUnrestricted(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.InformationSystem com.soffid.iam.iga.service.ApplicationService.findApplicationByApplicationNameUnrestricted(java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationByApplicationNameUnrestricted(codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.InformationSystem) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findApplicationByApplicationNameUnrestricted", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findApplicationByApplicationNameUnrestricted", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.InformationSystem handleFindApplicationByApplicationNameUnrestricted(java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationName(java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationName(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.InformationSystem com.soffid.iam.iga.service.ApplicationService.findApplicationByApplicationName(java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationByApplicationName(codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.InformationSystem) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findApplicationByApplicationName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findApplicationByApplicationName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.InformationSystem handleFindApplicationByApplicationName(java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.InformationSystem findApplicationById(java.lang.Long id)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.InformationSystem findApplicationById(
		final java.lang.Long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (id == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.InformationSystem com.soffid.iam.iga.service.ApplicationService.findApplicationById(java.lang.Long id) - id cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationById(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.InformationSystem) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findApplicationById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findApplicationById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.InformationSystem handleFindApplicationById(java.lang.Long id) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role approveRoleDefinition(com.soffid.iam.iga.api.Role rol)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Role approveRoleDefinition(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rol == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.approveRoleDefinition(com.soffid.iam.iga.api.Role rol) - rol cannot be null");
		}
		if (rol.getName() == null || rol.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.approveRoleDefinition(com.soffid.iam.iga.api.Role rol) - rol.name cannot be null");
		}
		if (rol.getKey() == null || rol.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.approveRoleDefinition(com.soffid.iam.iga.api.Role rol) - rol.key cannot be null");
		}
		if (rol.getDescription() == null || rol.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.approveRoleDefinition(com.soffid.iam.iga.api.Role rol) - rol.description cannot be null");
		}
		if (rol.getSystem() == null || rol.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.approveRoleDefinition(com.soffid.iam.iga.api.Role rol) - rol.system cannot be null");
		}
		if (rol.getInformationSystemName() == null || rol.getInformationSystemName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.approveRoleDefinition(com.soffid.iam.iga.api.Role rol) - rol.informationSystemName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleApproveRoleDefinition(rol)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Role) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.approveRoleDefinition", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.approveRoleDefinition", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Role handleApproveRoleDefinition(com.soffid.iam.iga.api.Role rol) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role create(com.soffid.iam.iga.api.Role rol)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Role create(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rol == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.Role rol) - rol cannot be null");
		}
		if (rol.getName() == null || rol.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.Role rol) - rol.name cannot be null");
		}
		if (rol.getKey() == null || rol.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.Role rol) - rol.key cannot be null");
		}
		if (rol.getDescription() == null || rol.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.Role rol) - rol.description cannot be null");
		}
		if (rol.getSystem() == null || rol.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.Role rol) - rol.system cannot be null");
		}
		if (rol.getInformationSystemName() == null || rol.getInformationSystemName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.Role rol) - rol.informationSystemName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(rol)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Role) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Role handleCreate(com.soffid.iam.iga.api.Role rol) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role create2(com.soffid.iam.iga.api.Role rol)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Role create2(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rol == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.create2(com.soffid.iam.iga.api.Role rol) - rol cannot be null");
		}
		if (rol.getName() == null || rol.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.create2(com.soffid.iam.iga.api.Role rol) - rol.name cannot be null");
		}
		if (rol.getKey() == null || rol.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.create2(com.soffid.iam.iga.api.Role rol) - rol.key cannot be null");
		}
		if (rol.getDescription() == null || rol.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.create2(com.soffid.iam.iga.api.Role rol) - rol.description cannot be null");
		}
		if (rol.getSystem() == null || rol.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.create2(com.soffid.iam.iga.api.Role rol) - rol.system cannot be null");
		}
		if (rol.getInformationSystemName() == null || rol.getInformationSystemName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.create2(com.soffid.iam.iga.api.Role rol) - rol.informationSystemName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate2(rol)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Role) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.create2", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.create2", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Role handleCreate2(com.soffid.iam.iga.api.Role rol) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role denyRoleDefinition(com.soffid.iam.iga.api.Role rol)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Role denyRoleDefinition(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rol == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.denyRoleDefinition(com.soffid.iam.iga.api.Role rol) - rol cannot be null");
		}
		if (rol.getName() == null || rol.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.denyRoleDefinition(com.soffid.iam.iga.api.Role rol) - rol.name cannot be null");
		}
		if (rol.getKey() == null || rol.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.denyRoleDefinition(com.soffid.iam.iga.api.Role rol) - rol.key cannot be null");
		}
		if (rol.getDescription() == null || rol.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.denyRoleDefinition(com.soffid.iam.iga.api.Role rol) - rol.description cannot be null");
		}
		if (rol.getSystem() == null || rol.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.denyRoleDefinition(com.soffid.iam.iga.api.Role rol) - rol.system cannot be null");
		}
		if (rol.getInformationSystemName() == null || rol.getInformationSystemName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.denyRoleDefinition(com.soffid.iam.iga.api.Role rol) - rol.informationSystemName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDenyRoleDefinition(rol)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Role) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.denyRoleDefinition", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.denyRoleDefinition", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Role handleDenyRoleDefinition(com.soffid.iam.iga.api.Role rol) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role findRoleById(java.lang.Long rolId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Role findRoleById(
		final java.lang.Long rolId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rolId == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.findRoleById(java.lang.Long rolId) - rolId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleById(rolId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Role) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Role handleFindRoleById(java.lang.Long rolId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role findRoleByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRol, java.lang.String codiAplicacio, java.lang.String codiDispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Role findRoleByRoleNameAndApplicationNameAndDispatcherName(
		final java.lang.String nomRol, 
		final java.lang.String codiAplicacio, 
		final java.lang.String codiDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomRol == null || nomRol.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.findRoleByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRol, java.lang.String codiAplicacio, java.lang.String codiDispatcher) - nomRol cannot be null");
		}
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.findRoleByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRol, java.lang.String codiAplicacio, java.lang.String codiDispatcher) - codiAplicacio cannot be null");
		}
		if (codiDispatcher == null || codiDispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.findRoleByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRol, java.lang.String codiAplicacio, java.lang.String codiDispatcher) - codiDispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleByRoleNameAndApplicationNameAndDispatcherName(nomRol, codiAplicacio, codiDispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Role) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleByRoleNameAndApplicationNameAndDispatcherName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleByRoleNameAndApplicationNameAndDispatcherName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Role handleFindRoleByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRol, java.lang.String codiAplicacio, java.lang.String codiDispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role findRoleByNameAndSystem(java.lang.String name, java.lang.String system)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Role findRoleByNameAndSystem(
		final java.lang.String name, 
		final java.lang.String system)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.findRoleByNameAndSystem(java.lang.String name, java.lang.String system) - name cannot be null");
		}
		if (system == null || system.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.findRoleByNameAndSystem(java.lang.String name, java.lang.String system) - system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleByNameAndSystem(name, system)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Role) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleByNameAndSystem", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleByNameAndSystem", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Role handleFindRoleByNameAndSystem(java.lang.String name, java.lang.String system) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role findRoleByShortName(java.lang.String name)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Role findRoleByShortName(
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.findRoleByShortName(java.lang.String name) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleByShortName(name)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Role) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleByShortName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleByShortName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Role handleFindRoleByShortName(java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role update(com.soffid.iam.iga.api.Role rol)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Role update(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rol == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.Role rol) - rol cannot be null");
		}
		if (rol.getName() == null || rol.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.Role rol) - rol.name cannot be null");
		}
		if (rol.getKey() == null || rol.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.Role rol) - rol.key cannot be null");
		}
		if (rol.getDescription() == null || rol.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.Role rol) - rol.description cannot be null");
		}
		if (rol.getSystem() == null || rol.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.Role rol) - rol.system cannot be null");
		}
		if (rol.getInformationSystemName() == null || rol.getInformationSystemName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.Role rol) - rol.informationSystemName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(rol)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Role) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Role handleUpdate(com.soffid.iam.iga.api.Role rol) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role update2(com.soffid.iam.iga.api.Role rol)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Role update2(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rol == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.update2(com.soffid.iam.iga.api.Role rol) - rol cannot be null");
		}
		if (rol.getName() == null || rol.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.update2(com.soffid.iam.iga.api.Role rol) - rol.name cannot be null");
		}
		if (rol.getKey() == null || rol.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.update2(com.soffid.iam.iga.api.Role rol) - rol.key cannot be null");
		}
		if (rol.getDescription() == null || rol.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.update2(com.soffid.iam.iga.api.Role rol) - rol.description cannot be null");
		}
		if (rol.getSystem() == null || rol.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.update2(com.soffid.iam.iga.api.Role rol) - rol.system cannot be null");
		}
		if (rol.getInformationSystemName() == null || rol.getInformationSystemName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.iga.service.ApplicationService.update2(com.soffid.iam.iga.api.Role rol) - rol.informationSystemName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate2(rol)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Role) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.update2", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.update2", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Role handleUpdate2(com.soffid.iam.iga.api.Role rol) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.RoleAccount create(com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.RoleAccount create(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rolsUsuaris == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleAccount com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) - rolsUsuaris cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(rolsUsuaris)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.RoleAccount) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.RoleAccount handleCreate(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.RoleAccount enableOrDisableOnDates(com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.RoleAccount enableOrDisableOnDates(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rolsUsuaris == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleAccount com.soffid.iam.iga.service.ApplicationService.enableOrDisableOnDates(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) - rolsUsuaris cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleEnableOrDisableOnDates(rolsUsuaris)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.RoleAccount) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.enableOrDisableOnDates", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.enableOrDisableOnDates", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.RoleAccount handleEnableOrDisableOnDates(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.RoleAccount findRoleAccountById(long id)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.RoleAccount findRoleAccountById(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleAccountById(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.RoleAccount) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleAccountById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleAccountById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.RoleAccount handleFindRoleAccountById(long id) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.RoleAccount update(com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.RoleAccount update(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rolsUsuaris == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleAccount com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) - rolsUsuaris cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(rolsUsuaris)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.RoleAccount) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.RoleAccount handleUpdate(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.RoleAccount updateAttributes(com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.RoleAccount updateAttributes(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rolsUsuaris == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleAccount com.soffid.iam.iga.service.ApplicationService.updateAttributes(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) - rolsUsuaris cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateAttributes(rolsUsuaris)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.RoleAccount) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.updateAttributes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.updateAttributes", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.RoleAccount handleUpdateAttributes(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.RoleGrant create(com.soffid.iam.iga.api.RoleGrant grant)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.RoleGrant create(
		final com.soffid.iam.iga.api.RoleGrant grant)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grant == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleGrant com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.RoleGrant grant) - grant cannot be null");
		}
		if (grant.getRoleId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleGrant com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.RoleGrant grant) - grant.roleId cannot be null");
		}
		if (grant.getRoleName() == null || grant.getRoleName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleGrant com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.RoleGrant grant) - grant.roleName cannot be null");
		}
		if (grant.getSystem() == null || grant.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleGrant com.soffid.iam.iga.service.ApplicationService.create(com.soffid.iam.iga.api.RoleGrant grant) - grant.system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(grant)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.RoleGrant) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.RoleGrant handleCreate(com.soffid.iam.iga.api.RoleGrant grant) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.RoleGrant update(com.soffid.iam.iga.api.RoleGrant grant)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.RoleGrant update(
		final com.soffid.iam.iga.api.RoleGrant grant)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grant == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleGrant com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.RoleGrant grant) - grant cannot be null");
		}
		if (grant.getRoleId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleGrant com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.RoleGrant grant) - grant.roleId cannot be null");
		}
		if (grant.getRoleName() == null || grant.getRoleName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleGrant com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.RoleGrant grant) - grant.roleName cannot be null");
		}
		if (grant.getSystem() == null || grant.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleGrant com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.RoleGrant grant) - grant.system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(grant)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.RoleGrant) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.RoleGrant handleUpdate(com.soffid.iam.iga.api.RoleGrant grant) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.InformationSystem> findApplications(com.soffid.zkdb.api.Query query)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.InformationSystem> findApplications(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplications(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.InformationSystem>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findApplications", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findApplications", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.InformationSystem> handleFindApplications(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValues(com.soffid.zkdb.api.Query query)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValues(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDomainValues(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findDomainValues", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findDomainValues", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> handleFindDomainValues(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> findRedundantRoles(java.lang.String query)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> findRedundantRoles(
		final java.lang.String query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRedundantRoles(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRedundantRoles", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRedundantRoles", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> handleFindRedundantRoles(java.lang.String query) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> findRoleAccounts(com.soffid.zkdb.api.Query query)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> findRoleAccounts(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (query == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findRoleAccounts(com.soffid.zkdb.api.Query query) - query cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleAccounts(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleAccounts", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> handleFindRoleAccounts(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Role> findRoles(com.soffid.zkdb.api.Query query)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Role> findRoles(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoles(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoles", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoles", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Role> handleFindRoles(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.lang.String generateChangesReport(com.soffid.iam.iga.api.Role rol)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rol == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.ApplicationService.generateChangesReport(com.soffid.iam.iga.api.Role rol) - rol cannot be null");
		}
		if (rol.getName() == null || rol.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.ApplicationService.generateChangesReport(com.soffid.iam.iga.api.Role rol) - rol.name cannot be null");
		}
		if (rol.getKey() == null || rol.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.ApplicationService.generateChangesReport(com.soffid.iam.iga.api.Role rol) - rol.key cannot be null");
		}
		if (rol.getDescription() == null || rol.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.ApplicationService.generateChangesReport(com.soffid.iam.iga.api.Role rol) - rol.description cannot be null");
		}
		if (rol.getSystem() == null || rol.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.ApplicationService.generateChangesReport(com.soffid.iam.iga.api.Role rol) - rol.system cannot be null");
		}
		if (rol.getInformationSystemName() == null || rol.getInformationSystemName().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.ApplicationService.generateChangesReport(com.soffid.iam.iga.api.Role rol) - rol.informationSystemName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateChangesReport(rol)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.generateChangesReport", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.generateChangesReport", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGenerateChangesReport(com.soffid.iam.iga.api.Role rol) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.lang.String generateChangesReport(com.soffid.iam.iga.api.Role rol, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.Role rol, 
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, 
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rol == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.ApplicationService.generateChangesReport(com.soffid.iam.iga.api.Role rol, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove) - rol cannot be null");
		}
		if (rol.getName() == null || rol.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.ApplicationService.generateChangesReport(com.soffid.iam.iga.api.Role rol, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove) - rol.name cannot be null");
		}
		if (rol.getKey() == null || rol.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.ApplicationService.generateChangesReport(com.soffid.iam.iga.api.Role rol, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove) - rol.key cannot be null");
		}
		if (rol.getDescription() == null || rol.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.ApplicationService.generateChangesReport(com.soffid.iam.iga.api.Role rol, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove) - rol.description cannot be null");
		}
		if (rol.getSystem() == null || rol.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.ApplicationService.generateChangesReport(com.soffid.iam.iga.api.Role rol, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove) - rol.system cannot be null");
		}
		if (rol.getInformationSystemName() == null || rol.getInformationSystemName().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.ApplicationService.generateChangesReport(com.soffid.iam.iga.api.Role rol, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove) - rol.informationSystemName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateChangesReport(rol, grantsToAdd, grantsToRemove)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.generateChangesReport", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.generateChangesReport", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGenerateChangesReport(com.soffid.iam.iga.api.Role rol, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomRole == null || nomRole.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> com.soffid.iam.iga.service.ApplicationService.findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - nomRole cannot be null");
		}
		if (codiAplicacioRol == null || codiAplicacioRol.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> com.soffid.iam.iga.service.ApplicationService.findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - codiAplicacioRol cannot be null");
		}
		if (codiDispatcher == null || codiDispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> com.soffid.iam.iga.service.ApplicationService.findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - codiDispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(nomRole, codiAplicacioRol, codiDispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> handleFindNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.InformationSystem> findApplicationChildren(java.lang.String applicationName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.InformationSystem> findApplicationChildren(
		final java.lang.String applicationName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (applicationName == null || applicationName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.InformationSystem> com.soffid.iam.iga.service.ApplicationService.findApplicationChildren(java.lang.String applicationName) - applicationName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationChildren(applicationName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.InformationSystem>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findApplicationChildren", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findApplicationChildren", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.InformationSystem> handleFindApplicationChildren(java.lang.String applicationName) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findApplicationManagementRoles()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> findApplicationManagementRoles()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationManagementRoles()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findApplicationManagementRoles", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findApplicationManagementRoles", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleFindApplicationManagementRoles() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findApplicationManagers(java.lang.String informationSystem, java.lang.String roleName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findApplicationManagers(
		final java.lang.String informationSystem, 
		final java.lang.String roleName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (informationSystem == null || informationSystem.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findApplicationManagers(java.lang.String informationSystem, java.lang.String roleName) - informationSystem cannot be null");
		}
		if (roleName == null || roleName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findApplicationManagers(java.lang.String informationSystem, java.lang.String roleName) - roleName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationManagers(informationSystem, roleName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findApplicationManagers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findApplicationManagers", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindApplicationManagers(java.lang.String informationSystem, java.lang.String roleName) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomRole == null || nomRole.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> com.soffid.iam.iga.service.ApplicationService.findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - nomRole cannot be null");
		}
		if (codiAplicacioRol == null || codiAplicacioRol.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> com.soffid.iam.iga.service.ApplicationService.findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - codiAplicacioRol cannot be null");
		}
		if (codiDispatcher == null || codiDispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> com.soffid.iam.iga.service.ApplicationService.findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - codiDispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(nomRole, codiAplicacioRol, codiDispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AuthorizationRole>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> handleFindRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByAccount(long accountId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByAccount(
		final long accountId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindEffectiveRoleGrantByAccount(accountId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleGrant>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findEffectiveRoleGrantByAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findEffectiveRoleGrantByAccount", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleGrant> handleFindEffectiveRoleGrantByAccount(long accountId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByUser(long userId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByUser(
		final long userId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindEffectiveRoleGrantByUser(userId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleGrant>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findEffectiveRoleGrantByUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findEffectiveRoleGrantByUser", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleGrant> handleFindEffectiveRoleGrantByUser(long userId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByUserAndHolderGroup(long userId, long groupId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByUserAndHolderGroup(
		final long userId, 
		final long groupId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindEffectiveRoleGrantByUserAndHolderGroup(userId, groupId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleGrant>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findEffectiveRoleGrantByUserAndHolderGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findEffectiveRoleGrantByUserAndHolderGroup", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleGrant> handleFindEffectiveRoleGrantByUserAndHolderGroup(long userId, long groupId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantsByRoleId(java.lang.Long rolId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantsByRoleId(
		final java.lang.Long rolId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rolId == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleGrant> com.soffid.iam.iga.service.ApplicationService.findEffectiveRoleGrantsByRoleId(java.lang.Long rolId) - rolId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindEffectiveRoleGrantsByRoleId(rolId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleGrant>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findEffectiveRoleGrantsByRoleId", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findEffectiveRoleGrantsByRoleId", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleGrant> handleFindEffectiveRoleGrantsByRoleId(java.lang.Long rolId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findEffectiveUserRolesByInformationSystem(java.lang.String informationSystem)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findEffectiveUserRolesByInformationSystem(
		final java.lang.String informationSystem)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (informationSystem == null || informationSystem.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findEffectiveUserRolesByInformationSystem(java.lang.String informationSystem) - informationSystem cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindEffectiveUserRolesByInformationSystem(informationSystem)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findEffectiveUserRolesByInformationSystem", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findEffectiveUserRolesByInformationSystem", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindEffectiveUserRolesByInformationSystem(java.lang.String informationSystem) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findGroupManagementRoles()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> findGroupManagementRoles()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupManagementRoles()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findGroupManagementRoles", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findGroupManagementRoles", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleFindGroupManagementRoles() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findGroupManagers(java.lang.String group, java.lang.String roleName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findGroupManagers(
		final java.lang.String group, 
		final java.lang.String roleName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (group == null || group.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findGroupManagers(java.lang.String group, java.lang.String roleName) - group cannot be null");
		}
		if (roleName == null || roleName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findGroupManagers(java.lang.String group, java.lang.String roleName) - roleName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupManagers(group, roleName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findGroupManagers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findGroupManagers", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindGroupManagers(java.lang.String group, java.lang.String roleName) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Group> findRoleHoldersGroupsByRole(com.soffid.iam.iga.api.Role rol)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> findRoleHoldersGroupsByRole(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rol == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.ApplicationService.findRoleHoldersGroupsByRole(com.soffid.iam.iga.api.Role rol) - rol cannot be null");
		}
		if (rol.getName() == null || rol.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.ApplicationService.findRoleHoldersGroupsByRole(com.soffid.iam.iga.api.Role rol) - rol.name cannot be null");
		}
		if (rol.getKey() == null || rol.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.ApplicationService.findRoleHoldersGroupsByRole(com.soffid.iam.iga.api.Role rol) - rol.key cannot be null");
		}
		if (rol.getDescription() == null || rol.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.ApplicationService.findRoleHoldersGroupsByRole(com.soffid.iam.iga.api.Role rol) - rol.description cannot be null");
		}
		if (rol.getSystem() == null || rol.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.ApplicationService.findRoleHoldersGroupsByRole(com.soffid.iam.iga.api.Role rol) - rol.system cannot be null");
		}
		if (rol.getInformationSystemName() == null || rol.getInformationSystemName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.ApplicationService.findRoleHoldersGroupsByRole(com.soffid.iam.iga.api.Role rol) - rol.informationSystemName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleHoldersGroupsByRole(rol)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleHoldersGroupsByRole", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleHoldersGroupsByRole", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleFindRoleHoldersGroupsByRole(com.soffid.iam.iga.api.Role rol) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomRole == null || nomRole.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> com.soffid.iam.iga.service.ApplicationService.findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - nomRole cannot be null");
		}
		if (codiAplicacioRol == null || codiAplicacioRol.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> com.soffid.iam.iga.service.ApplicationService.findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - codiAplicacioRol cannot be null");
		}
		if (codiDispatcher == null || codiDispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> com.soffid.iam.iga.service.ApplicationService.findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - codiDispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(nomRole, codiAplicacioRol, codiDispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> handleFindApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccountByAccount(long accountId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccountByAccount(
		final long accountId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleAccountByAccount(accountId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleAccountByAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleAccountByAccount", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindRoleAccountByAccount(long accountId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantByAccount(java.lang.Long accountId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantByAccount(
		final java.lang.Long accountId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (accountId == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleGrant> com.soffid.iam.iga.service.ApplicationService.findRoleGrantByAccount(java.lang.Long accountId) - accountId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleGrantByAccount(accountId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleGrant>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleGrantByAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleGrantByAccount", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleGrant> handleFindRoleGrantByAccount(java.lang.Long accountId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantByRole(java.lang.Long rolId, java.lang.Long numRegistres)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantByRole(
		final java.lang.Long rolId, 
		final java.lang.Long numRegistres)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rolId == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleGrant> com.soffid.iam.iga.service.ApplicationService.findRoleGrantByRole(java.lang.Long rolId, java.lang.Long numRegistres) - rolId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleGrantByRole(rolId, numRegistres)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleGrant>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleGrantByRole", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleGrantByRole", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleGrant> handleFindRoleGrantByRole(java.lang.Long rolId, java.lang.Long numRegistres) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccountByAccountNoRule(long accountId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccountByAccountNoRule(
		final long accountId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleAccountByAccountNoRule(accountId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleAccountByAccountNoRule", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleAccountByAccountNoRule", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindRoleAccountByAccountNoRule(long accountId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> findRoleGrantHierarchyByAccount(long accountId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> findRoleGrantHierarchyByAccount(
		final long accountId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleGrantHierarchyByAccount(accountId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleGrantHierarchyByAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleGrantHierarchyByAccount", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> handleFindRoleGrantHierarchyByAccount(long accountId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> findRoleGrantHierarchyByUser(long userId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> findRoleGrantHierarchyByUser(
		final long userId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleGrantHierarchyByUser(userId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleGrantHierarchyByUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleGrantHierarchyByUser", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> handleFindRoleGrantHierarchyByUser(long userId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantsByGroup(com.soffid.iam.iga.api.Group grup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantsByGroup(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grup == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleGrant> com.soffid.iam.iga.service.ApplicationService.findRoleGrantsByGroup(com.soffid.iam.iga.api.Group grup) - grup cannot be null");
		}
		if (grup.getName() == null || grup.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleGrant> com.soffid.iam.iga.service.ApplicationService.findRoleGrantsByGroup(com.soffid.iam.iga.api.Group grup) - grup.name cannot be null");
		}
		if (grup.getDescription() == null || grup.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleGrant> com.soffid.iam.iga.service.ApplicationService.findRoleGrantsByGroup(com.soffid.iam.iga.api.Group grup) - grup.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleGrantsByGroup(grup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleGrant>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRoleGrantsByGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRoleGrantsByGroup", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleGrant> handleFindRoleGrantsByGroup(com.soffid.iam.iga.api.Group grup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationName(java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationName(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.iga.service.ApplicationService.findRolesByApplicationName(java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRolesByApplicationName(codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRolesByApplicationName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRolesByApplicationName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleFindRolesByApplicationName(java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationNameUnrestricted(java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationNameUnrestricted(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.iga.service.ApplicationService.findRolesByApplicationNameUnrestricted(java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRolesByApplicationNameUnrestricted(codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRolesByApplicationNameUnrestricted", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRolesByApplicationNameUnrestricted", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleFindRolesByApplicationNameUnrestricted(java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.iga.service.ApplicationService.findRolesByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRolesByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRolesByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRolesByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleFindRolesByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByDomainNameAndApplicationName(java.lang.String nomDomini, java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByDomainNameAndApplicationName(
		final java.lang.String nomDomini, 
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomDomini == null || nomDomini.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.iga.service.ApplicationService.findRolesByDomainNameAndApplicationName(java.lang.String nomDomini, java.lang.String codiAplicacio) - nomDomini cannot be null");
		}
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.iga.service.ApplicationService.findRolesByDomainNameAndApplicationName(java.lang.String nomDomini, java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRolesByDomainNameAndApplicationName(nomDomini, codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findRolesByDomainNameAndApplicationName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findRolesByDomainNameAndApplicationName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleFindRolesByDomainNameAndApplicationName(java.lang.String nomDomini, java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findGrantedRolesToGroupByGroup(com.soffid.iam.iga.api.Group grup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> findGrantedRolesToGroupByGroup(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grup == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.iga.service.ApplicationService.findGrantedRolesToGroupByGroup(com.soffid.iam.iga.api.Group grup) - grup cannot be null");
		}
		if (grup.getName() == null || grup.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.iga.service.ApplicationService.findGrantedRolesToGroupByGroup(com.soffid.iam.iga.api.Group grup) - grup.name cannot be null");
		}
		if (grup.getDescription() == null || grup.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.iga.service.ApplicationService.findGrantedRolesToGroupByGroup(com.soffid.iam.iga.api.Group grup) - grup.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGrantedRolesToGroupByGroup(grup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findGrantedRolesToGroupByGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findGrantedRolesToGroupByGroup", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleFindGrantedRolesToGroupByGroup(com.soffid.iam.iga.api.Group grup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomRole == null || nomRole.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - nomRole cannot be null");
		}
		if (codiAplicacioRol == null || codiAplicacioRol.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - codiAplicacioRol cannot be null");
		}
		if (codiDispatcher == null || codiDispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - codiDispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(nomRole, codiAplicacioRol, codiDispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findUserRolesByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserRolesByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findUserRolesByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findUserRolesByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindUserRolesByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesHistoryByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesHistoryByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findUserRolesHistoryByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserRolesHistoryByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findUserRolesHistoryByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findUserRolesHistoryByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindUserRolesHistoryByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserNameNoSoD(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserNameNoSoD(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findUserRolesByUserNameNoSoD(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserRolesByUserNameNoSoD(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findUserRolesByUserNameNoSoD", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findUserRolesByUserNameNoSoD", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindUserRolesByUserNameNoSoD(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByInformationSystem(java.lang.String informationSystem)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByInformationSystem(
		final java.lang.String informationSystem)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (informationSystem == null || informationSystem.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findUserRolesByInformationSystem(java.lang.String informationSystem) - informationSystem cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserRolesByInformationSystem(informationSystem)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findUserRolesByInformationSystem", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findUserRolesByInformationSystem", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindUserRolesByInformationSystem(java.lang.String informationSystem) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserNameNoRules(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserNameNoRules(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.ApplicationService.findUserRolesByUserNameNoRules(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserRolesByUserNameNoRules(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findUserRolesByUserNameNoRules", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findUserRolesByUserNameNoRules", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindUserRolesByUserNameNoRules(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.base.api.User> findUsersByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.User> findUsersByRoleNameAndRoleApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomRole == null || nomRole.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.User> com.soffid.iam.iga.service.ApplicationService.findUsersByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - nomRole cannot be null");
		}
		if (codiAplicacioRol == null || codiAplicacioRol.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.User> com.soffid.iam.iga.service.ApplicationService.findUsersByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - codiAplicacioRol cannot be null");
		}
		if (codiDispatcher == null || codiDispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.User> com.soffid.iam.iga.service.ApplicationService.findUsersByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) - codiDispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUsersByRoleNameAndRoleApplicationNameAndDispatcherName(nomRole, codiAplicacioRol, codiDispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.User>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.findUsersByRoleNameAndRoleApplicationNameAndDispatcherName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.findUsersByRoleNameAndRoleApplicationNameAndDispatcherName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.User> handleFindUsersByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<java.lang.Object> getPendingAlerts(java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.Object> getPendingAlerts(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPendingAlerts(codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.Object>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.getPendingAlerts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.getPendingAlerts", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.Object> handleGetPendingAlerts(java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> getRoles()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> getRoles()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetRoles()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.getRoles", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.getRoles", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleGetRoles() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void approveDelete(com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void approveDelete(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rolsUsuaris == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.approveDelete(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) - rolsUsuaris cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleApproveDelete(rolsUsuaris);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.approveDelete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.approveDelete", (Throwable) __r[1]);
	}

	protected abstract void handleApproveDelete(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void delete(com.soffid.iam.iga.api.InformationSystem aplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.InformationSystem aplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (aplicacio == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.InformationSystem aplicacio) - aplicacio cannot be null");
		}
		if (aplicacio.getRelativeName() == null || aplicacio.getRelativeName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.InformationSystem aplicacio) - aplicacio.relativeName cannot be null");
		}
		if (aplicacio.getName() == null || aplicacio.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.InformationSystem aplicacio) - aplicacio.name cannot be null");
		}
		if (aplicacio.getDescription() == null || aplicacio.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.InformationSystem aplicacio) - aplicacio.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(aplicacio);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.InformationSystem aplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void delete(com.soffid.iam.iga.api.Role rol)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rol == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.Role rol) - rol cannot be null");
		}
		if (rol.getName() == null || rol.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.Role rol) - rol.name cannot be null");
		}
		if (rol.getKey() == null || rol.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.Role rol) - rol.key cannot be null");
		}
		if (rol.getDescription() == null || rol.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.Role rol) - rol.description cannot be null");
		}
		if (rol.getSystem() == null || rol.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.Role rol) - rol.system cannot be null");
		}
		if (rol.getInformationSystemName() == null || rol.getInformationSystemName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.Role rol) - rol.informationSystemName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(rol);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.Role rol) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void delete(com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rolsUsuaris == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) - rolsUsuaris cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(rolsUsuaris);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void delete(com.soffid.iam.iga.api.RoleGrant grant)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.RoleGrant grant)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grant == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.RoleGrant grant) - grant cannot be null");
		}
		if (grant.getRoleId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.RoleGrant grant) - grant.roleId cannot be null");
		}
		if (grant.getRoleName() == null || grant.getRoleName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.RoleGrant grant) - grant.roleName cannot be null");
		}
		if (grant.getSystem() == null || grant.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.delete(com.soffid.iam.iga.api.RoleGrant grant) - grant.system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(grant);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.RoleGrant grant) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void deleteByRuleEvaluation(com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void deleteByRuleEvaluation(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rolsUsuaris == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.deleteByRuleEvaluation(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) - rolsUsuaris cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteByRuleEvaluation(rolsUsuaris);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.deleteByRuleEvaluation", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.deleteByRuleEvaluation", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteByRuleEvaluation(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void removeSentAlerts(java.lang.String codiAplicacio, java.util.Date dataDelete)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void removeSentAlerts(
		final java.lang.String codiAplicacio, 
		final java.util.Date dataDelete)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dataDelete == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.removeSentAlerts(java.lang.String codiAplicacio, java.util.Date dataDelete) - dataDelete cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemoveSentAlerts(codiAplicacio, dataDelete);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.removeSentAlerts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.removeSentAlerts", (Throwable) __r[1]);
	}

	protected abstract void handleRemoveSentAlerts(java.lang.String codiAplicacio, java.util.Date dataDelete) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void denyApproval(com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void denyApproval(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rolsUsuaris == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.denyApproval(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) - rolsUsuaris cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDenyApproval(rolsUsuaris);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.denyApproval", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.denyApproval", (Throwable) __r[1]);
	}

	protected abstract void handleDenyApproval(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void denyDelete(com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void denyDelete(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rolsUsuaris == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.denyDelete(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) - rolsUsuaris cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDenyDelete(rolsUsuaris);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.denyDelete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.denyDelete", (Throwable) __r[1]);
	}

	protected abstract void handleDenyDelete(com.soffid.iam.iga.api.RoleAccount rolsUsuaris) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void enableOrDisableAllOnDates()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void enableOrDisableAllOnDates()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleEnableOrDisableAllOnDates();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.enableOrDisableAllOnDates", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.enableOrDisableAllOnDates", (Throwable) __r[1]);
	}

	protected abstract void handleEnableOrDisableAllOnDates() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void revokeRolesHoldedOnGroup(long userId, long groupId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void revokeRolesHoldedOnGroup(
		final long userId, 
		final long groupId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRevokeRolesHoldedOnGroup(userId, groupId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.revokeRolesHoldedOnGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.revokeRolesHoldedOnGroup", (Throwable) __r[1]);
	}

	protected abstract void handleRevokeRolesHoldedOnGroup(long userId, long groupId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void synchronizeRole(com.soffid.iam.iga.api.Role rol)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class})
	public void synchronizeRole(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rol == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.synchronizeRole(com.soffid.iam.iga.api.Role rol) - rol cannot be null");
		}
		if (rol.getName() == null || rol.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.synchronizeRole(com.soffid.iam.iga.api.Role rol) - rol.name cannot be null");
		}
		if (rol.getKey() == null || rol.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.synchronizeRole(com.soffid.iam.iga.api.Role rol) - rol.key cannot be null");
		}
		if (rol.getDescription() == null || rol.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.synchronizeRole(com.soffid.iam.iga.api.Role rol) - rol.description cannot be null");
		}
		if (rol.getSystem() == null || rol.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.synchronizeRole(com.soffid.iam.iga.api.Role rol) - rol.system cannot be null");
		}
		if (rol.getInformationSystemName() == null || rol.getInformationSystemName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.synchronizeRole(com.soffid.iam.iga.api.Role rol) - rol.informationSystemName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSynchronizeRole(rol);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.synchronizeRole", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.synchronizeRole", (Throwable) __r[1]);
	}

	protected abstract void handleSynchronizeRole(com.soffid.iam.iga.api.Role rol) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#	 * @see com.soffid.iam.iga.service.ApplicationService#void update(com.soffid.iam.iga.api.InformationSystem aplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void update(
		final com.soffid.iam.iga.api.InformationSystem aplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (aplicacio == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.InformationSystem aplicacio) - aplicacio cannot be null");
		}
		if (aplicacio.getRelativeName() == null || aplicacio.getRelativeName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.InformationSystem aplicacio) - aplicacio.relativeName cannot be null");
		}
		if (aplicacio.getName() == null || aplicacio.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.InformationSystem aplicacio) - aplicacio.name cannot be null");
		}
		if (aplicacio.getDescription() == null || aplicacio.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.ApplicationService.update(com.soffid.iam.iga.api.InformationSystem aplicacio) - aplicacio.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdate(aplicacio);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.ApplicationService.class).
			warn ("Error on ApplicationService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationService.update", (Throwable) __r[1]);
	}

	protected abstract void handleUpdate(com.soffid.iam.iga.api.InformationSystem aplicacio) throws Exception;

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
