//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.base.service.UserService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.base.service.UserService
 */
public abstract class UserServiceBase
	implements com.soffid.iam.base.service.UserService
 {
	private com.soffid.iam.am.model.AccessLogEntityDao accessLogEntityDao;

	/**
	 * Sets reference to <code>accessLogEntityDao</code>.
	 */
	public void setAccessLogEntityDao (com.soffid.iam.am.model.AccessLogEntityDao accessLogEntityDao) {
		this.accessLogEntityDao = accessLogEntityDao;
	}

	/**
	 * Gets reference to <code>accessLogEntityDao</code>.
	 */
	public com.soffid.iam.am.model.AccessLogEntityDao getAccessLogEntityDao () {
		return accessLogEntityDao;
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

	private com.soffid.iam.am.model.AccountPasswordEntityDao accountPasswordEntityDao;

	/**
	 * Sets reference to <code>accountPasswordEntityDao</code>.
	 */
	public void setAccountPasswordEntityDao (com.soffid.iam.am.model.AccountPasswordEntityDao accountPasswordEntityDao) {
		this.accountPasswordEntityDao = accountPasswordEntityDao;
	}

	/**
	 * Gets reference to <code>accountPasswordEntityDao</code>.
	 */
	public com.soffid.iam.am.model.AccountPasswordEntityDao getAccountPasswordEntityDao () {
		return accountPasswordEntityDao;
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

	private com.soffid.iam.rc.model.AuditEntityDao auditEntityDao;

	/**
	 * Sets reference to <code>auditEntityDao</code>.
	 */
	public void setAuditEntityDao (com.soffid.iam.rc.model.AuditEntityDao auditEntityDao) {
		this.auditEntityDao = auditEntityDao;
	}

	/**
	 * Gets reference to <code>auditEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.AuditEntityDao getAuditEntityDao () {
		return auditEntityDao;
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

	private com.soffid.iam.am.model.HostEntityDao hostEntityDao;

	/**
	 * Sets reference to <code>hostEntityDao</code>.
	 */
	public void setHostEntityDao (com.soffid.iam.am.model.HostEntityDao hostEntityDao) {
		this.hostEntityDao = hostEntityDao;
	}

	/**
	 * Gets reference to <code>hostEntityDao</code>.
	 */
	public com.soffid.iam.am.model.HostEntityDao getHostEntityDao () {
		return hostEntityDao;
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

	private com.soffid.iam.rc.model.IssueUserEntityDao issueUserEntityDao;

	/**
	 * Sets reference to <code>issueUserEntityDao</code>.
	 */
	public void setIssueUserEntityDao (com.soffid.iam.rc.model.IssueUserEntityDao issueUserEntityDao) {
		this.issueUserEntityDao = issueUserEntityDao;
	}

	/**
	 * Gets reference to <code>issueUserEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueUserEntityDao getIssueUserEntityDao () {
		return issueUserEntityDao;
	}

	private com.soffid.iam.iga.service.MailListsService mailListsService;

	/**
	 * Sets reference to <code>mailListsService</code>.
	 */
	public void setMailListsService (com.soffid.iam.iga.service.MailListsService mailListsService) {
		this.mailListsService = mailListsService;
	}

	/**
	 * Gets reference to <code>mailListsService</code>.
	 */
	public com.soffid.iam.iga.service.MailListsService getMailListsService () {
		return mailListsService;
	}

	private com.soffid.iam.iga.service.MailService mailService;

	/**
	 * Sets reference to <code>mailService</code>.
	 */
	public void setMailService (com.soffid.iam.iga.service.MailService mailService) {
		this.mailService = mailService;
	}

	/**
	 * Gets reference to <code>mailService</code>.
	 */
	public com.soffid.iam.iga.service.MailService getMailService () {
		return mailService;
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

	private com.soffid.iam.am.model.PasswordDomainEntityDao passwordDomainEntityDao;

	/**
	 * Sets reference to <code>passwordDomainEntityDao</code>.
	 */
	public void setPasswordDomainEntityDao (com.soffid.iam.am.model.PasswordDomainEntityDao passwordDomainEntityDao) {
		this.passwordDomainEntityDao = passwordDomainEntityDao;
	}

	/**
	 * Gets reference to <code>passwordDomainEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PasswordDomainEntityDao getPasswordDomainEntityDao () {
		return passwordDomainEntityDao;
	}

	private com.soffid.iam.am.model.PasswordEntityDao passwordEntityDao;

	/**
	 * Sets reference to <code>passwordEntityDao</code>.
	 */
	public void setPasswordEntityDao (com.soffid.iam.am.model.PasswordEntityDao passwordEntityDao) {
		this.passwordEntityDao = passwordEntityDao;
	}

	/**
	 * Gets reference to <code>passwordEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PasswordEntityDao getPasswordEntityDao () {
		return passwordEntityDao;
	}

	private com.soffid.iam.iga.model.PrinterEntityDao printerEntityDao;

	/**
	 * Sets reference to <code>printerEntityDao</code>.
	 */
	public void setPrinterEntityDao (com.soffid.iam.iga.model.PrinterEntityDao printerEntityDao) {
		this.printerEntityDao = printerEntityDao;
	}

	/**
	 * Gets reference to <code>printerEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.PrinterEntityDao getPrinterEntityDao () {
		return printerEntityDao;
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

	private com.soffid.iam.am.model.SecretEntityDao secretEntityDao;

	/**
	 * Sets reference to <code>secretEntityDao</code>.
	 */
	public void setSecretEntityDao (com.soffid.iam.am.model.SecretEntityDao secretEntityDao) {
		this.secretEntityDao = secretEntityDao;
	}

	/**
	 * Gets reference to <code>secretEntityDao</code>.
	 */
	public com.soffid.iam.am.model.SecretEntityDao getSecretEntityDao () {
		return secretEntityDao;
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

	private com.soffid.iam.am.model.SessionEntityDao sessionEntityDao;

	/**
	 * Sets reference to <code>sessionEntityDao</code>.
	 */
	public void setSessionEntityDao (com.soffid.iam.am.model.SessionEntityDao sessionEntityDao) {
		this.sessionEntityDao = sessionEntityDao;
	}

	/**
	 * Gets reference to <code>sessionEntityDao</code>.
	 */
	public com.soffid.iam.am.model.SessionEntityDao getSessionEntityDao () {
		return sessionEntityDao;
	}

	private com.soffid.iam.am.service.SignalService signalService;

	/**
	 * Sets reference to <code>signalService</code>.
	 */
	public void setSignalService (com.soffid.iam.am.service.SignalService signalService) {
		this.signalService = signalService;
	}

	/**
	 * Gets reference to <code>signalService</code>.
	 */
	public com.soffid.iam.am.service.SignalService getSignalService () {
		return signalService;
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

	private com.soffid.iam.base.model.UserDataEntityDao userDataEntityDao;

	/**
	 * Sets reference to <code>userDataEntityDao</code>.
	 */
	public void setUserDataEntityDao (com.soffid.iam.base.model.UserDataEntityDao userDataEntityDao) {
		this.userDataEntityDao = userDataEntityDao;
	}

	/**
	 * Gets reference to <code>userDataEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserDataEntityDao getUserDataEntityDao () {
		return userDataEntityDao;
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

	private com.soffid.iam.iga.model.UserGroupEntityDao userGroupEntityDao;

	/**
	 * Sets reference to <code>userGroupEntityDao</code>.
	 */
	public void setUserGroupEntityDao (com.soffid.iam.iga.model.UserGroupEntityDao userGroupEntityDao) {
		this.userGroupEntityDao = userGroupEntityDao;
	}

	/**
	 * Gets reference to <code>userGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserGroupEntityDao getUserGroupEntityDao () {
		return userGroupEntityDao;
	}

	private com.soffid.iam.base.model.UserPreferenceEntityDao userPreferenceEntityDao;

	/**
	 * Sets reference to <code>userPreferenceEntityDao</code>.
	 */
	public void setUserPreferenceEntityDao (com.soffid.iam.base.model.UserPreferenceEntityDao userPreferenceEntityDao) {
		this.userPreferenceEntityDao = userPreferenceEntityDao;
	}

	/**
	 * Gets reference to <code>userPreferenceEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserPreferenceEntityDao getUserPreferenceEntityDao () {
		return userPreferenceEntityDao;
	}

	private com.soffid.iam.iga.model.UserPrinterEntityDao userPrinterEntityDao;

	/**
	 * Sets reference to <code>userPrinterEntityDao</code>.
	 */
	public void setUserPrinterEntityDao (com.soffid.iam.iga.model.UserPrinterEntityDao userPrinterEntityDao) {
		this.userPrinterEntityDao = userPrinterEntityDao;
	}

	/**
	 * Gets reference to <code>userPrinterEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserPrinterEntityDao getUserPrinterEntityDao () {
		return userPrinterEntityDao;
	}

	private com.soffid.iam.iga.model.UserProcessEntityDao userProcessEntityDao;

	/**
	 * Sets reference to <code>userProcessEntityDao</code>.
	 */
	public void setUserProcessEntityDao (com.soffid.iam.iga.model.UserProcessEntityDao userProcessEntityDao) {
		this.userProcessEntityDao = userProcessEntityDao;
	}

	/**
	 * Gets reference to <code>userProcessEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserProcessEntityDao getUserProcessEntityDao () {
		return userProcessEntityDao;
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

	private com.soffid.iam.am.model.VaultFolderAccessEntityDao vaultFolderAccessEntityDao;

	/**
	 * Sets reference to <code>vaultFolderAccessEntityDao</code>.
	 */
	public void setVaultFolderAccessEntityDao (com.soffid.iam.am.model.VaultFolderAccessEntityDao vaultFolderAccessEntityDao) {
		this.vaultFolderAccessEntityDao = vaultFolderAccessEntityDao;
	}

	/**
	 * Gets reference to <code>vaultFolderAccessEntityDao</code>.
	 */
	public com.soffid.iam.am.model.VaultFolderAccessEntityDao getVaultFolderAccessEntityDao () {
		return vaultFolderAccessEntityDao;
	}

	private com.soffid.iam.am.model.VaultFolderEntityDao vaultFolderEntityDao;

	/**
	 * Sets reference to <code>vaultFolderEntityDao</code>.
	 */
	public void setVaultFolderEntityDao (com.soffid.iam.am.model.VaultFolderEntityDao vaultFolderEntityDao) {
		this.vaultFolderEntityDao = vaultFolderEntityDao;
	}

	/**
	 * Gets reference to <code>vaultFolderEntityDao</code>.
	 */
	public com.soffid.iam.am.model.VaultFolderEntityDao getVaultFolderEntityDao () {
		return vaultFolderEntityDao;
	}


	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#byte[] getESSORules(java.lang.String user)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public byte[] getESSORules(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("byte[] com.soffid.iam.base.service.UserService.getESSORules(java.lang.String user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetESSORules(user)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (byte[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getESSORules", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getESSORules", (Throwable) __r[1]);
	}

	protected abstract byte[] handleGetESSORules(java.lang.String user) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.am.api.ExtranetCard createExtranetCard(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.ExtranetCard createExtranetCard(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.ExtranetCard com.soffid.iam.base.service.UserService.createExtranetCard(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateExtranetCard(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.ExtranetCard) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.createExtranetCard", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.createExtranetCard", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.ExtranetCard handleCreateExtranetCard(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.am.api.ExtranetCard findExtranetCardByUserNameAndCardName(java.lang.String userName, java.lang.String codiTargeta)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.ExtranetCard findExtranetCardByUserNameAndCardName(
		final java.lang.String userName, 
		final java.lang.String codiTargeta)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.ExtranetCard com.soffid.iam.base.service.UserService.findExtranetCardByUserNameAndCardName(java.lang.String userName, java.lang.String codiTargeta) - userName cannot be null");
		}
		if (codiTargeta == null || codiTargeta.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.ExtranetCard com.soffid.iam.base.service.UserService.findExtranetCardByUserNameAndCardName(java.lang.String userName, java.lang.String codiTargeta) - codiTargeta cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindExtranetCardByUserNameAndCardName(userName, codiTargeta)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.ExtranetCard) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findExtranetCardByUserNameAndCardName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findExtranetCardByUserNameAndCardName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.ExtranetCard handleFindExtranetCardByUserNameAndCardName(java.lang.String userName, java.lang.String codiTargeta) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.am.api.ExtranetCard update(com.soffid.iam.am.api.ExtranetCard targetaExtranet)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.ExtranetCard update(
		final com.soffid.iam.am.api.ExtranetCard targetaExtranet)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (targetaExtranet == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.ExtranetCard com.soffid.iam.base.service.UserService.update(com.soffid.iam.am.api.ExtranetCard targetaExtranet) - targetaExtranet cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(targetaExtranet)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.ExtranetCard) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.ExtranetCard handleUpdate(com.soffid.iam.am.api.ExtranetCard targetaExtranet) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.am.api.Host findMailServerByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Host findMailServerByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.base.service.UserService.findMailServerByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMailServerByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Host) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findMailServerByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findMailServerByUserName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Host handleFindMailServerByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.am.api.Host findHomeServerByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Host findHomeServerByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.base.service.UserService.findHomeServerByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindHomeServerByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Host) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findHomeServerByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findHomeServerByUserName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Host handleFindHomeServerByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.am.api.Host findProfileServerByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Host findProfileServerByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Host com.soffid.iam.base.service.UserService.findProfileServerByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindProfileServerByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Host) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findProfileServerByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findProfileServerByUserName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Host handleFindProfileServerByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.AsyncProcessTracker disableUsers(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AsyncProcessTracker disableUsers(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (rules == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.base.service.UserService.disableUsers(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules) - rules cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDisableUsers(scimQuery, rules)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.AsyncProcessTracker) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.disableUsers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.disableUsers", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AsyncProcessTracker handleDisableUsers(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.AsyncProcessTracker disableUsersPreview(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, java.util.List<java.lang.Object[]> actions)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AsyncProcessTracker disableUsersPreview(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, 
		final java.util.List<java.lang.Object[]> actions)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (rules == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.base.service.UserService.disableUsersPreview(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, java.util.List<java.lang.Object[]> actions) - rules cannot be empty");
		}
		if (actions == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.base.service.UserService.disableUsersPreview(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, java.util.List<java.lang.Object[]> actions) - actions cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDisableUsersPreview(scimQuery, rules, actions)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.AsyncProcessTracker) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.disableUsersPreview", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.disableUsersPreview", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AsyncProcessTracker handleDisableUsersPreview(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, java.util.List<java.lang.Object[]> actions) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User disableUser(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User disableUser(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.disableUser(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDisableUser(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.disableUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.disableUser", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleDisableUser(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User create(com.soffid.iam.base.api.User usuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User create(
		final com.soffid.iam.base.api.User usuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuari == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.create(com.soffid.iam.base.api.User usuari) - usuari cannot be null");
		}
		if (usuari.getUserName() == null || usuari.getUserName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.create(com.soffid.iam.base.api.User usuari) - usuari.userName cannot be null");
		}
		if (usuari.getFirstName() == null || usuari.getFirstName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.create(com.soffid.iam.base.api.User usuari) - usuari.firstName cannot be null");
		}
		if (usuari.getLastName() == null || usuari.getLastName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.create(com.soffid.iam.base.api.User usuari) - usuari.lastName cannot be null");
		}
		if (usuari.getUserType() == null || usuari.getUserType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.create(com.soffid.iam.base.api.User usuari) - usuari.userType cannot be null");
		}
		if (usuari.getPrimaryGroup() == null || usuari.getPrimaryGroup().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.create(com.soffid.iam.base.api.User usuari) - usuari.primaryGroup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(usuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleCreate(com.soffid.iam.base.api.User usuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User findUserByDataTypeNameAndDataTypeValue(java.lang.String codiTipusDada, java.lang.String valorTipusDada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User findUserByDataTypeNameAndDataTypeValue(
		final java.lang.String codiTipusDada, 
		final java.lang.String valorTipusDada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiTipusDada == null || codiTipusDada.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.findUserByDataTypeNameAndDataTypeValue(java.lang.String codiTipusDada, java.lang.String valorTipusDada) - codiTipusDada cannot be null");
		}
		if (valorTipusDada == null || valorTipusDada.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.findUserByDataTypeNameAndDataTypeValue(java.lang.String codiTipusDada, java.lang.String valorTipusDada) - valorTipusDada cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserByDataTypeNameAndDataTypeValue(codiTipusDada, valorTipusDada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findUserByDataTypeNameAndDataTypeValue", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findUserByDataTypeNameAndDataTypeValue", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleFindUserByDataTypeNameAndDataTypeValue(java.lang.String codiTipusDada, java.lang.String valorTipusDada) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User findUserByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public com.soffid.iam.base.api.User findUserByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.findUserByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findUserByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findUserByUserName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleFindUserByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User findUserByUserId(java.lang.Long id)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User findUserByUserId(
		final java.lang.Long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (id == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.findUserByUserId(java.lang.Long id) - id cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserByUserId(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findUserByUserId", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findUserByUserId", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleFindUserByUserId(java.lang.Long id) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User findUserByUserNif(java.lang.String nif)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User findUserByUserNif(
		final java.lang.String nif)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nif == null || nif.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.findUserByUserNif(java.lang.String nif) - nif cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserByUserNif(nif)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findUserByUserNif", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findUserByUserNif", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleFindUserByUserNif(java.lang.String nif) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User getCurrentUser()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User getCurrentUser()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetCurrentUser()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getCurrentUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getCurrentUser", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleGetCurrentUser() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User getUserInfo(java.lang.String user)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackForClassName={"java.lang.Exception"}, noRollbackForClassName={"UnknownUserException"})
	public com.soffid.iam.base.api.User getUserInfo(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.getUserInfo(java.lang.String user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserInfo(user)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.UnknownUserException) 
			throw (com.soffid.iam.exception.UnknownUserException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getUserInfo", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getUserInfo", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleGetUserInfo(java.lang.String user) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User setServersToUser(java.lang.String userName, java.lang.String nomServidorPerfil, java.lang.String nomServidorCorreu, java.lang.String nomServidorHome)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User setServersToUser(
		final java.lang.String userName, 
		final java.lang.String nomServidorPerfil, 
		final java.lang.String nomServidorCorreu, 
		final java.lang.String nomServidorHome)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.setServersToUser(java.lang.String userName, java.lang.String nomServidorPerfil, java.lang.String nomServidorCorreu, java.lang.String nomServidorHome) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSetServersToUser(userName, nomServidorPerfil, nomServidorCorreu, nomServidorHome)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.setServersToUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.setServersToUser", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleSetServersToUser(java.lang.String userName, java.lang.String nomServidorPerfil, java.lang.String nomServidorCorreu, java.lang.String nomServidorHome) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.User update(com.soffid.iam.base.api.User usuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User update(
		final com.soffid.iam.base.api.User usuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuari == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.update(com.soffid.iam.base.api.User usuari) - usuari cannot be null");
		}
		if (usuari.getUserName() == null || usuari.getUserName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.update(com.soffid.iam.base.api.User usuari) - usuari.userName cannot be null");
		}
		if (usuari.getFirstName() == null || usuari.getFirstName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.update(com.soffid.iam.base.api.User usuari) - usuari.firstName cannot be null");
		}
		if (usuari.getLastName() == null || usuari.getLastName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.update(com.soffid.iam.base.api.User usuari) - usuari.lastName cannot be null");
		}
		if (usuari.getUserType() == null || usuari.getUserType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.update(com.soffid.iam.base.api.User usuari) - usuari.userType cannot be null");
		}
		if (usuari.getPrimaryGroup() == null || usuari.getPrimaryGroup().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.UserService.update(com.soffid.iam.base.api.User usuari) - usuari.primaryGroup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(usuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleUpdate(com.soffid.iam.base.api.User usuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.base.api.UserData findDataByUserAndCode(java.lang.String userName, java.lang.String codiTipusDada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.UserData findDataByUserAndCode(
		final java.lang.String userName, 
		final java.lang.String codiTipusDada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.base.service.UserService.findDataByUserAndCode(java.lang.String userName, java.lang.String codiTipusDada) - userName cannot be null");
		}
		if (codiTipusDada == null || codiTipusDada.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.base.service.UserService.findDataByUserAndCode(java.lang.String userName, java.lang.String codiTipusDada) - codiTipusDada cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDataByUserAndCode(userName, codiTipusDada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.UserData) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findDataByUserAndCode", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findDataByUserAndCode", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.UserData handleFindDataByUserAndCode(java.lang.String userName, java.lang.String codiTipusDada) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.iga.api.BpmUserProcess create(com.soffid.iam.iga.api.BpmUserProcess userProcess)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.BpmUserProcess create(
		final com.soffid.iam.iga.api.BpmUserProcess userProcess)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userProcess == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.BpmUserProcess com.soffid.iam.base.service.UserService.create(com.soffid.iam.iga.api.BpmUserProcess userProcess) - userProcess cannot be null");
		}
		if (userProcess.getProcessId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.BpmUserProcess com.soffid.iam.base.service.UserService.create(com.soffid.iam.iga.api.BpmUserProcess userProcess) - userProcess.processId cannot be null");
		}
		if (userProcess.getFinished() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.BpmUserProcess com.soffid.iam.base.service.UserService.create(com.soffid.iam.iga.api.BpmUserProcess userProcess) - userProcess.finished cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(userProcess)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.BpmUserProcess) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.BpmUserProcess handleCreate(com.soffid.iam.iga.api.BpmUserProcess userProcess) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.iam.iga.api.BpmUserProcess update(com.soffid.iam.iga.api.BpmUserProcess usuariWFProces)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.BpmUserProcess update(
		final com.soffid.iam.iga.api.BpmUserProcess usuariWFProces)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuariWFProces == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.BpmUserProcess com.soffid.iam.base.service.UserService.update(com.soffid.iam.iga.api.BpmUserProcess usuariWFProces) - usuariWFProces cannot be null");
		}
		if (usuariWFProces.getProcessId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.BpmUserProcess com.soffid.iam.base.service.UserService.update(com.soffid.iam.iga.api.BpmUserProcess usuariWFProces) - usuariWFProces.processId cannot be null");
		}
		if (usuariWFProces.getFinished() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.BpmUserProcess com.soffid.iam.base.service.UserService.update(com.soffid.iam.iga.api.BpmUserProcess usuariWFProces) - usuariWFProces.finished cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(usuariWFProces)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.BpmUserProcess) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.BpmUserProcess handleUpdate(com.soffid.iam.iga.api.BpmUserProcess usuariWFProces) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.User> findUsers(com.soffid.zkdb.api.Query q)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.User> findUsers(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.User> com.soffid.iam.base.service.UserService.findUsers(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUsers(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.User>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findUsers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findUsers", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.User> handleFindUsers(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#int isUpdatePendingExtended(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public int isUpdatePendingExtended(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("int com.soffid.iam.base.service.UserService.isUpdatePendingExtended(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsUpdatePendingExtended(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Integer) __r[0]).intValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.isUpdatePendingExtended", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.isUpdatePendingExtended", (Throwable) __r[1]);
	}

	protected abstract int handleIsUpdatePendingExtended(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.lang.String setTemporaryPassword(java.lang.String userName, java.lang.String passwordDomain)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String setTemporaryPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.UserService.setTemporaryPassword(java.lang.String userName, java.lang.String passwordDomain) - userName cannot be null");
		}
		if (passwordDomain == null || passwordDomain.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.UserService.setTemporaryPassword(java.lang.String userName, java.lang.String passwordDomain) - passwordDomain cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSetTemporaryPassword(userName, passwordDomain)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.setTemporaryPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.setTemporaryPassword", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleSetTemporaryPassword(java.lang.String userName, java.lang.String passwordDomain) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.lang.String createNewUserProcess(java.lang.String processName, java.lang.String userName, boolean canviaAProces)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String createNewUserProcess(
		final java.lang.String processName, 
		final java.lang.String userName, 
		final boolean canviaAProces)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (processName == null || processName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.UserService.createNewUserProcess(java.lang.String processName, java.lang.String userName, boolean canviaAProces) - processName cannot be null");
		}
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.UserService.createNewUserProcess(java.lang.String processName, java.lang.String userName, boolean canviaAProces) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateNewUserProcess(processName, userName, canviaAProces)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.createNewUserProcess", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.createNewUserProcess", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleCreateNewUserProcess(java.lang.String processName, java.lang.String userName, boolean canviaAProces) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.lang.String generateRandomPassword()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String generateRandomPassword()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateRandomPassword()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.generateRandomPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.generateRandomPassword", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGenerateRandomPassword() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.lang.String getFollowingName()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String getFollowingName()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetFollowingName()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getFollowingName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getFollowingName", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetFollowingName() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.lang.String setPassword(java.lang.String userName, java.lang.String passwordDomain)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String setPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.UserService.setPassword(java.lang.String userName, java.lang.String passwordDomain) - userName cannot be null");
		}
		if (passwordDomain == null || passwordDomain.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.UserService.setPassword(java.lang.String userName, java.lang.String passwordDomain) - passwordDomain cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSetPassword(userName, passwordDomain)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.setPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.setPassword", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleSetPassword(java.lang.String userName, java.lang.String passwordDomain) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.lang.String[] getTasks(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getTasks(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.base.service.UserService.getTasks(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetTasks(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getTasks", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleGetTasks(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.lang.String[] refreshChanges(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] refreshChanges(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.base.service.UserService.refreshChanges(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleRefreshChanges(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.refreshChanges", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.refreshChanges", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleRefreshChanges(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.base.api.UserData> findUserDataByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.UserData> findUserDataByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.UserData> com.soffid.iam.base.service.UserService.findUserDataByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserDataByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.UserData>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findUserDataByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findUserDataByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.UserData> handleFindUserDataByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.Printer> findPrintersByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Printer> findPrintersByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Printer> com.soffid.iam.base.service.UserService.findPrintersByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPrintersByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Printer>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findPrintersByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findPrintersByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Printer> handleFindPrintersByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.Role> findUserRolesHierachyByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> findUserRolesHierachyByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.base.service.UserService.findUserRolesHierachyByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserRolesHierachyByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findUserRolesHierachyByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findUserRolesHierachyByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleFindUserRolesHierachyByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.Role> findUserRolesHierachyByUserName(java.lang.String userName, java.lang.Boolean incloureRolsUsuariDirectes)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> findUserRolesHierachyByUserName(
		final java.lang.String userName, 
		final java.lang.Boolean incloureRolsUsuariDirectes)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.base.service.UserService.findUserRolesHierachyByUserName(java.lang.String userName, java.lang.Boolean incloureRolsUsuariDirectes) - userName cannot be null");
		}
		if (incloureRolsUsuariDirectes == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.base.service.UserService.findUserRolesHierachyByUserName(java.lang.String userName, java.lang.Boolean incloureRolsUsuariDirectes) - incloureRolsUsuariDirectes cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserRolesHierachyByUserName(userName, incloureRolsUsuariDirectes)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findUserRolesHierachyByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findUserRolesHierachyByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleFindUserRolesHierachyByUserName(java.lang.String userName, java.lang.Boolean incloureRolsUsuariDirectes) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.bpm.api.ProcessInstance> findBpmUserProcessInstanceByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public java.util.Collection<com.soffid.iam.bpm.api.ProcessInstance> findBpmUserProcessInstanceByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.bpm.api.ProcessInstance> com.soffid.iam.base.service.UserService.findBpmUserProcessInstanceByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindBpmUserProcessInstanceByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.bpm.api.ProcessInstance>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findBpmUserProcessInstanceByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findBpmUserProcessInstanceByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.bpm.api.ProcessInstance> handleFindBpmUserProcessInstanceByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> findBpmUserProcessByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> findBpmUserProcessByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> com.soffid.iam.base.service.UserService.findBpmUserProcessByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindBpmUserProcessByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findBpmUserProcessByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findBpmUserProcessByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> handleFindBpmUserProcessByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> findBpmUserProcessByProcessId(java.lang.Long idProces)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> findBpmUserProcessByProcessId(
		final java.lang.Long idProces)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (idProces == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> com.soffid.iam.base.service.UserService.findBpmUserProcessByProcessId(java.lang.Long idProces) - idProces cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindBpmUserProcessByProcessId(idProces)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findBpmUserProcessByProcessId", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findBpmUserProcessByProcessId", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> handleFindBpmUserProcessByProcessId(java.lang.Long idProces) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> findBpmUserProcessByUserNif(java.lang.String nifUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> findBpmUserProcessByUserNif(
		final java.lang.String nifUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nifUsuari == null || nifUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> com.soffid.iam.base.service.UserService.findBpmUserProcessByUserNif(java.lang.String nifUsuari) - nifUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindBpmUserProcessByUserNif(nifUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findBpmUserProcessByUserNif", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findBpmUserProcessByUserNif", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> handleFindBpmUserProcessByUserNif(java.lang.String nifUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.am.api.Session> findSessionByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.Session> findSessionByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.Session> com.soffid.iam.base.service.UserService.findSessionByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSessionByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.Session>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findSessionByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findSessionByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.Session> handleFindSessionByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.am.api.ExtranetCard> findExtranetCardsByUserName(java.lang.String userName, java.lang.String activa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.ExtranetCard> findExtranetCardsByUserName(
		final java.lang.String userName, 
		final java.lang.String activa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.ExtranetCard> com.soffid.iam.base.service.UserService.findExtranetCardsByUserName(java.lang.String userName, java.lang.String activa) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindExtranetCardsByUserName(userName, activa)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.ExtranetCard>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findExtranetCardsByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findExtranetCardsByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.ExtranetCard> handleFindExtranetCardsByUserName(java.lang.String userName, java.lang.String activa) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<java.lang.String> findUserNames()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		readOnly=true)
	public java.util.Collection<java.lang.String> findUserNames()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserNames()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findUserNames", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findUserNames", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.String> handleFindUserNames() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.PrinterUser> findUserPrintersByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.PrinterUser> findUserPrintersByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.PrinterUser> com.soffid.iam.base.service.UserService.findUserPrintersByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserPrintersByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.PrinterUser>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findUserPrintersByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findUserPrintersByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.PrinterUser> handleFindUserPrintersByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> findNetworksACByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> findNetworksACByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> com.soffid.iam.base.service.UserService.findNetworksACByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNetworksACByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findNetworksACByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findNetworksACByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> handleFindNetworksACByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getActiveTasks(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getActiveTasks(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> com.soffid.iam.base.service.UserService.getActiveTasks(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetActiveTasks(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getActiveTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getActiveTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> handleGetActiveTasks(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.InformationSystem> getApplicationsByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.InformationSystem> getApplicationsByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.InformationSystem> com.soffid.iam.base.service.UserService.getApplicationsByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetApplicationsByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.InformationSystem>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getApplicationsByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getApplicationsByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.InformationSystem> handleGetApplicationsByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.InformationSystem> getBpmEnabledApplicationsByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.InformationSystem> getBpmEnabledApplicationsByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.InformationSystem> com.soffid.iam.base.service.UserService.getBpmEnabledApplicationsByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetBpmEnabledApplicationsByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.InformationSystem>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getBpmEnabledApplicationsByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getBpmEnabledApplicationsByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.InformationSystem> handleGetBpmEnabledApplicationsByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.am.api.PasswordStatus> getPasswordsUserType(java.util.Date dataInici, java.util.Date dataFi, java.lang.String tipusUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.PasswordStatus> getPasswordsUserType(
		final java.util.Date dataInici, 
		final java.util.Date dataFi, 
		final java.lang.String tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dataInici == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.PasswordStatus> com.soffid.iam.base.service.UserService.getPasswordsUserType(java.util.Date dataInici, java.util.Date dataFi, java.lang.String tipusUsuari) - dataInici cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPasswordsUserType(dataInici, dataFi, tipusUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.PasswordStatus>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getPasswordsUserType", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getPasswordsUserType", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.PasswordStatus> handleGetPasswordsUserType(java.util.Date dataInici, java.util.Date dataFi, java.lang.String tipusUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.Role> getApplicationRolesByuserNameAndApplicationName(java.lang.String userName, java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> getApplicationRolesByuserNameAndApplicationName(
		final java.lang.String userName, 
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.base.service.UserService.getApplicationRolesByuserNameAndApplicationName(java.lang.String userName, java.lang.String codiAplicacio) - userName cannot be null");
		}
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.base.service.UserService.getApplicationRolesByuserNameAndApplicationName(java.lang.String userName, java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetApplicationRolesByuserNameAndApplicationName(userName, codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getApplicationRolesByuserNameAndApplicationName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getApplicationRolesByuserNameAndApplicationName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleGetApplicationRolesByuserNameAndApplicationName(java.lang.String userName, java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.base.api.DataType> getDataType()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.DataType> getDataType()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDataType()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.DataType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getDataType", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getDataType", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.DataType> handleGetDataType() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getUserExplicitRoles(long userId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackForClassName={"java.lang.Exception"}, noRollbackForClassName={"UnknownUserException"})
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getUserExplicitRoles(
		final long userId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserExplicitRoles(userId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleGrant>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.UnknownUserException) 
			throw (com.soffid.iam.exception.UnknownUserException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getUserExplicitRoles", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getUserExplicitRoles", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleGrant> handleGetUserExplicitRoles(long userId) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroups(long userId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroups(
		final long userId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserGroups(userId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getUserGroups", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getUserGroups", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleGetUserGroups(long userId) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroupsHierarchy(long userId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackForClassName={"java.lang.Exception"}, noRollbackForClassName={"UnknownUserException"})
	public java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroupsHierarchy(
		final long userId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserGroupsHierarchy(userId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.UnknownUserException) 
			throw (com.soffid.iam.exception.UnknownUserException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getUserGroupsHierarchy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getUserGroupsHierarchy", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleGetUserGroupsHierarchy(long userId) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroupsHierarchy(long userId, java.lang.String holderGroup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackForClassName={"java.lang.Exception"}, noRollbackForClassName={"UnknownUserException"})
	public java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroupsHierarchy(
		final long userId, 
		final java.lang.String holderGroup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException
	{
		if (holderGroup == null || holderGroup.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.base.service.UserService.getUserGroupsHierarchy(long userId, java.lang.String holderGroup) - holderGroup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserGroupsHierarchy(userId, holderGroup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.UnknownUserException) 
			throw (com.soffid.iam.exception.UnknownUserException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getUserGroupsHierarchy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getUserGroupsHierarchy", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleGetUserGroupsHierarchy(long userId, java.lang.String holderGroup) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getUserRoles(long userId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackForClassName={"java.lang.Exception"}, noRollbackForClassName={"UnknownUserException"})
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getUserRoles(
		final long userId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserRoles(userId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleGrant>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.UnknownUserException) 
			throw (com.soffid.iam.exception.UnknownUserException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getUserRoles", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getUserRoles", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleGrant> handleGetUserRoles(long userId) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Collection<com.soffid.iam.iga.api.BpmProcess> getBpmUserProcessList()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class}, readOnly=true)
	public java.util.Collection<com.soffid.iam.iga.api.BpmProcess> getBpmUserProcessList()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetBpmUserProcessList()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.BpmProcess>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.getBpmUserProcessList", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.getBpmUserProcessList", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.BpmProcess> handleGetBpmUserProcessList() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.List<com.soffid.iam.am.api.PasswordDomainStatus> findPasswordDomainStatus(java.lang.String user)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.am.api.PasswordDomainStatus> findPasswordDomainStatus(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.am.api.PasswordDomainStatus> com.soffid.iam.base.service.UserService.findPasswordDomainStatus(java.lang.String user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPasswordDomainStatus(user)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.am.api.PasswordDomainStatus>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findPasswordDomainStatus", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findPasswordDomainStatus", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.am.api.PasswordDomainStatus> handleFindPasswordDomainStatus(java.lang.String user) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#java.util.Map<java.lang.String,java.lang.Object> findUserAttributes(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Map<java.lang.String,java.lang.Object> findUserAttributes(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserAttributes(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Map<java.lang.String,java.lang.Object>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.findUserAttributes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.findUserAttributes", (Throwable) __r[1]);
	}

	protected abstract java.util.Map<java.lang.String,java.lang.Object> handleFindUserAttributes(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#void delete(com.soffid.iam.base.api.User user)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.base.api.User user)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.delete(com.soffid.iam.base.api.User user) - user cannot be null");
		}
		if (user.getUserName() == null || user.getUserName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.delete(com.soffid.iam.base.api.User user) - user.userName cannot be null");
		}
		if (user.getFirstName() == null || user.getFirstName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.delete(com.soffid.iam.base.api.User user) - user.firstName cannot be null");
		}
		if (user.getLastName() == null || user.getLastName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.delete(com.soffid.iam.base.api.User user) - user.lastName cannot be null");
		}
		if (user.getUserType() == null || user.getUserType().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.delete(com.soffid.iam.base.api.User user) - user.userType cannot be null");
		}
		if (user.getPrimaryGroup() == null || user.getPrimaryGroup().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.delete(com.soffid.iam.base.api.User user) - user.primaryGroup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(user);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.base.api.User user) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#void delete(com.soffid.iam.iga.api.BpmUserProcess usuariWFProces)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.BpmUserProcess usuariWFProces)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuariWFProces == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.delete(com.soffid.iam.iga.api.BpmUserProcess usuariWFProces) - usuariWFProces cannot be null");
		}
		if (usuariWFProces.getProcessId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.delete(com.soffid.iam.iga.api.BpmUserProcess usuariWFProces) - usuariWFProces.processId cannot be null");
		}
		if (usuariWFProces.getFinished() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.delete(com.soffid.iam.iga.api.BpmUserProcess usuariWFProces) - usuariWFProces.finished cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(usuariWFProces);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.BpmUserProcess usuariWFProces) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#void merge(java.lang.Long srcId, java.lang.Long targetId, java.lang.Long eventId)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void merge(
		final java.lang.Long srcId, 
		final java.lang.Long targetId, 
		final java.lang.Long eventId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (srcId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.merge(java.lang.Long srcId, java.lang.Long targetId, java.lang.Long eventId) - srcId cannot be null");
		}
		if (targetId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.merge(java.lang.Long srcId, java.lang.Long targetId, java.lang.Long eventId) - targetId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleMerge(srcId, targetId, eventId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.merge", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.merge", (Throwable) __r[1]);
	}

	protected abstract void handleMerge(java.lang.Long srcId, java.lang.Long targetId, java.lang.Long eventId) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#void sendPassword(java.lang.String userName, java.lang.String passwordDomain)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void sendPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.sendPassword(java.lang.String userName, java.lang.String passwordDomain) - userName cannot be null");
		}
		if (passwordDomain == null || passwordDomain.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.sendPassword(java.lang.String userName, java.lang.String passwordDomain) - passwordDomain cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendPassword(userName, passwordDomain);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.sendPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.sendPassword", (Throwable) __r[1]);
	}

	protected abstract void handleSendPassword(java.lang.String userName, java.lang.String passwordDomain) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#void setPassword(java.lang.String userName, java.lang.String passwordDomain, com.soffid.iam.am.api.Password newPassword)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void setPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain, 
		final com.soffid.iam.am.api.Password newPassword)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.setPassword(java.lang.String userName, java.lang.String passwordDomain, com.soffid.iam.am.api.Password newPassword) - userName cannot be null");
		}
		if (passwordDomain == null || passwordDomain.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.setPassword(java.lang.String userName, java.lang.String passwordDomain, com.soffid.iam.am.api.Password newPassword) - passwordDomain cannot be null");
		}
		if (newPassword == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.setPassword(java.lang.String userName, java.lang.String passwordDomain, com.soffid.iam.am.api.Password newPassword) - newPassword cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSetPassword(userName, passwordDomain, newPassword);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.BadPasswordException) 
			throw (com.soffid.iam.exception.BadPasswordException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.setPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.setPassword", (Throwable) __r[1]);
	}

	protected abstract void handleSetPassword(java.lang.String userName, java.lang.String passwordDomain, com.soffid.iam.am.api.Password newPassword) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#void setTemporaryPassword(java.lang.String userName, java.lang.String passwordDomain, com.soffid.iam.am.api.Password newPassword)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void setTemporaryPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain, 
		final com.soffid.iam.am.api.Password newPassword)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.setTemporaryPassword(java.lang.String userName, java.lang.String passwordDomain, com.soffid.iam.am.api.Password newPassword) - userName cannot be null");
		}
		if (passwordDomain == null || passwordDomain.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.setTemporaryPassword(java.lang.String userName, java.lang.String passwordDomain, com.soffid.iam.am.api.Password newPassword) - passwordDomain cannot be null");
		}
		if (newPassword == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.setTemporaryPassword(java.lang.String userName, java.lang.String passwordDomain, com.soffid.iam.am.api.Password newPassword) - newPassword cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSetTemporaryPassword(userName, passwordDomain, newPassword);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.BadPasswordException) 
			throw (com.soffid.iam.exception.BadPasswordException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.setTemporaryPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.setTemporaryPassword", (Throwable) __r[1]);
	}

	protected abstract void handleSetTemporaryPassword(java.lang.String userName, java.lang.String passwordDomain, com.soffid.iam.am.api.Password newPassword) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#void unlockPasswordDomain(java.lang.String user, java.lang.String passwordDomain)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void unlockPasswordDomain(
		final java.lang.String user, 
		final java.lang.String passwordDomain)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.unlockPasswordDomain(java.lang.String user, java.lang.String passwordDomain) - user cannot be null");
		}
		if (passwordDomain == null || passwordDomain.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.unlockPasswordDomain(java.lang.String user, java.lang.String passwordDomain) - passwordDomain cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUnlockPasswordDomain(user, passwordDomain);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.unlockPasswordDomain", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.unlockPasswordDomain", (Throwable) __r[1]);
	}

	protected abstract void handleUnlockPasswordDomain(java.lang.String user, java.lang.String passwordDomain) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.UserService#	 * @see com.soffid.iam.base.service.UserService#void updateUserAttributes(java.lang.String userName, java.util.Map<java.lang.String,java.lang.Object> attributes)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void updateUserAttributes(
		final java.lang.String userName, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.updateUserAttributes(java.lang.String userName, java.util.Map<java.lang.String,java.lang.Object> attributes) - userName cannot be null");
		}
		if (attributes == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.UserService.updateUserAttributes(java.lang.String userName, java.util.Map<java.lang.String,java.lang.Object> attributes) - attributes cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateUserAttributes(userName, attributes);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.UserService.class).
			warn ("Error on UserService.updateUserAttributes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserService.updateUserAttributes", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateUserAttributes(java.lang.String userName, java.util.Map<java.lang.String,java.lang.Object> attributes) throws Exception;

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
