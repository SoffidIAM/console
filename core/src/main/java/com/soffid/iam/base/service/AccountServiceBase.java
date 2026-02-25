//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.base.service.AccountService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.base.service.AccountService
 */
public abstract class AccountServiceBase
	implements com.soffid.iam.base.service.AccountService
 {
	private com.soffid.iam.pam.model.AccountAccessEntityDao accountAccessEntityDao;

	/**
	 * Sets reference to <code>accountAccessEntityDao</code>.
	 */
	public void setAccountAccessEntityDao (com.soffid.iam.pam.model.AccountAccessEntityDao accountAccessEntityDao) {
		this.accountAccessEntityDao = accountAccessEntityDao;
	}

	/**
	 * Gets reference to <code>accountAccessEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.AccountAccessEntityDao getAccountAccessEntityDao () {
		return accountAccessEntityDao;
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

	private com.soffid.iam.iga.model.AccountSnapshotEntityDao accountSnapshotEntityDao;

	/**
	 * Sets reference to <code>accountSnapshotEntityDao</code>.
	 */
	public void setAccountSnapshotEntityDao (com.soffid.iam.iga.model.AccountSnapshotEntityDao accountSnapshotEntityDao) {
		this.accountSnapshotEntityDao = accountSnapshotEntityDao;
	}

	/**
	 * Gets reference to <code>accountSnapshotEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.AccountSnapshotEntityDao getAccountSnapshotEntityDao () {
		return accountSnapshotEntityDao;
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

	private com.soffid.iam.am.model.EntryPointEntityDao entryPointEntityDao;

	/**
	 * Sets reference to <code>entryPointEntityDao</code>.
	 */
	public void setEntryPointEntityDao (com.soffid.iam.am.model.EntryPointEntityDao entryPointEntityDao) {
		this.entryPointEntityDao = entryPointEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointEntityDao getEntryPointEntityDao () {
		return entryPointEntityDao;
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

	private com.soffid.iam.pam.model.HostServiceEntityDao hostServiceEntityDao;

	/**
	 * Sets reference to <code>hostServiceEntityDao</code>.
	 */
	public void setHostServiceEntityDao (com.soffid.iam.pam.model.HostServiceEntityDao hostServiceEntityDao) {
		this.hostServiceEntityDao = hostServiceEntityDao;
	}

	/**
	 * Gets reference to <code>hostServiceEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.HostServiceEntityDao getHostServiceEntityDao () {
		return hostServiceEntityDao;
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

	private com.soffid.iam.pam.model.JumpServerGroupEntityDao jumpServerGroupEntityDao;

	/**
	 * Sets reference to <code>jumpServerGroupEntityDao</code>.
	 */
	public void setJumpServerGroupEntityDao (com.soffid.iam.pam.model.JumpServerGroupEntityDao jumpServerGroupEntityDao) {
		this.jumpServerGroupEntityDao = jumpServerGroupEntityDao;
	}

	/**
	 * Gets reference to <code>jumpServerGroupEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntityDao getJumpServerGroupEntityDao () {
		return jumpServerGroupEntityDao;
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

	private com.soffid.iam.pam.service.PamSecurityHandlerService pamSecurityHandlerService;

	/**
	 * Sets reference to <code>pamSecurityHandlerService</code>.
	 */
	public void setPamSecurityHandlerService (com.soffid.iam.pam.service.PamSecurityHandlerService pamSecurityHandlerService) {
		this.pamSecurityHandlerService = pamSecurityHandlerService;
	}

	/**
	 * Gets reference to <code>pamSecurityHandlerService</code>.
	 */
	public com.soffid.iam.pam.service.PamSecurityHandlerService getPamSecurityHandlerService () {
		return pamSecurityHandlerService;
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

	private com.soffid.iam.am.service.VaultService vaultService;

	/**
	 * Sets reference to <code>vaultService</code>.
	 */
	public void setVaultService (com.soffid.iam.am.service.VaultService vaultService) {
		this.vaultService = vaultService;
	}

	/**
	 * Gets reference to <code>vaultService</code>.
	 */
	public com.soffid.iam.am.service.VaultService getVaultService () {
		return vaultService;
	}


	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#boolean hasAccountSshKey(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean hasAccountSshKey(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.hasAccountSshKey(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.hasAccountSshKey(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.hasAccountSshKey(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.hasAccountSshKey(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.hasAccountSshKey(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.hasAccountSshKey(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleHasAccountSshKey(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.hasAccountSshKey", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.hasAccountSshKey", (Throwable) __r[1]);
	}

	protected abstract boolean handleHasAccountSshKey(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#boolean isAccountPasswordAvailable(long accountId)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isAccountPasswordAvailable(
		final long accountId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsAccountPasswordAvailable(accountId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.isAccountPasswordAvailable", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.isAccountPasswordAvailable", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsAccountPasswordAvailable(long accountId) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#boolean isUpdatePending(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean isUpdatePending(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.isUpdatePending(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.isUpdatePending(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.isUpdatePending(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.isUpdatePending(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.isUpdatePending(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.isUpdatePending(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsUpdatePending(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.isUpdatePending", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.isUpdatePending", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsUpdatePending(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#boolean needsAccount(java.lang.String userName, java.lang.String dispatcherName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class})
	public boolean needsAccount(
		final java.lang.String userName, 
		final java.lang.String dispatcherName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.needsAccount(java.lang.String userName, java.lang.String dispatcherName) - userName cannot be null");
		}
		if (dispatcherName == null || dispatcherName.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.needsAccount(java.lang.String userName, java.lang.String dispatcherName) - dispatcherName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleNeedsAccount(userName, dispatcherName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.needsAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.needsAccount", (Throwable) __r[1]);
	}

	protected abstract boolean handleNeedsAccount(java.lang.String userName, java.lang.String dispatcherName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#boolean setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean setHPAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password, 
		final java.util.Date untilDate, 
		final boolean force)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - account.passwordPolicy cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - password cannot be null");
		}
		if (untilDate == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AccountService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - untilDate cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSetHPAccountPassword(account, password, untilDate, force)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.setHPAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.setHPAccountPassword", (Throwable) __r[1]);
	}

	protected abstract boolean handleSetHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.Password generateAccountPassword(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password generateAccountPassword(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.generateAccountPassword(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.generateAccountPassword(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.generateAccountPassword(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.generateAccountPassword(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.generateAccountPassword(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.generateAccountPassword(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateAccountPassword(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.generateAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.generateAccountPassword", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleGenerateAccountPassword(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.Password generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password generateAccountTemporaryPassword(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateAccountTemporaryPassword(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.generateAccountTemporaryPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.generateAccountTemporaryPassword", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleGenerateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.Password queryAccountPassword(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password queryAccountPassword(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountPassword(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountPassword(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountPassword(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountPassword(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountPassword(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountPassword(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleQueryAccountPassword(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.queryAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.queryAccountPassword", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleQueryAccountPassword(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.Password queryAccountPasswordBypassPolicy(long accountId, com.soffid.iam.base.api.AccountAccessLevelEnum level)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password queryAccountPasswordBypassPolicy(
		final long accountId, 
		final com.soffid.iam.base.api.AccountAccessLevelEnum level)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (level == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountPasswordBypassPolicy(long accountId, com.soffid.iam.base.api.AccountAccessLevelEnum level) - level cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleQueryAccountPasswordBypassPolicy(accountId, level)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.queryAccountPasswordBypassPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.queryAccountPasswordBypassPolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleQueryAccountPasswordBypassPolicy(long accountId, com.soffid.iam.base.api.AccountAccessLevelEnum level) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.Password queryAccountSshKey(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password queryAccountSshKey(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountSshKey(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountSshKey(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountSshKey(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountSshKey(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountSshKey(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountSshKey(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleQueryAccountSshKey(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.queryAccountSshKey", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.queryAccountSshKey", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleQueryAccountSshKey(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.Password queryAccountSshKeyBypassPolicy(long accountId, com.soffid.iam.base.api.AccountAccessLevelEnum level)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password queryAccountSshKeyBypassPolicy(
		final long accountId, 
		final com.soffid.iam.base.api.AccountAccessLevelEnum level)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (level == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.queryAccountSshKeyBypassPolicy(long accountId, com.soffid.iam.base.api.AccountAccessLevelEnum level) - level cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleQueryAccountSshKeyBypassPolicy(accountId, level)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.queryAccountSshKeyBypassPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.queryAccountSshKeyBypassPolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleQueryAccountSshKeyBypassPolicy(long accountId, com.soffid.iam.base.api.AccountAccessLevelEnum level) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.Password setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, boolean temporary, boolean online)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password setAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean temporary, 
		final boolean online)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, boolean temporary, boolean online) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, boolean temporary, boolean online) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, boolean temporary, boolean online) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, boolean temporary, boolean online) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, boolean temporary, boolean online) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, boolean temporary, boolean online) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSetAccountPassword(account, password, temporary, online)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.BadPasswordException) 
			throw (com.soffid.iam.exception.BadPasswordException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.setAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.setAccountPassword", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleSetAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, boolean temporary, boolean online) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.am.api.PasswordValidation checkPasswordSynchronizationStatus(com.soffid.iam.base.api.Account account)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordValidation checkPasswordSynchronizationStatus(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordValidation com.soffid.iam.base.service.AccountService.checkPasswordSynchronizationStatus(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordValidation com.soffid.iam.base.service.AccountService.checkPasswordSynchronizationStatus(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordValidation com.soffid.iam.base.service.AccountService.checkPasswordSynchronizationStatus(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordValidation com.soffid.iam.base.service.AccountService.checkPasswordSynchronizationStatus(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordValidation com.soffid.iam.base.service.AccountService.checkPasswordSynchronizationStatus(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordValidation com.soffid.iam.base.service.AccountService.checkPasswordSynchronizationStatus(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckPasswordSynchronizationStatus(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordValidation) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.checkPasswordSynchronizationStatus", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.checkPasswordSynchronizationStatus", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordValidation handleCheckPasswordSynchronizationStatus(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account createAccount(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account createAccount(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateAccount(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.AccountAlreadyExistsException) 
			throw (com.soffid.iam.exception.AccountAlreadyExistsException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.createAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.createAccount", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleCreateAccount(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account createAccount2(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account createAccount2(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.createAccount2(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.createAccount2(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.createAccount2(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.createAccount2(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.createAccount2(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.createAccount2(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateAccount2(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.AccountAlreadyExistsException) 
			throw (com.soffid.iam.exception.AccountAlreadyExistsException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.createAccount2", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.createAccount2", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleCreateAccount2(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account findAccount(java.lang.String accountAndDispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account findAccount(
		final java.lang.String accountAndDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (accountAndDispatcher == null || accountAndDispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.findAccount(java.lang.String accountAndDispatcher) - accountAndDispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccount(accountAndDispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.findAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.findAccount", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleFindAccount(java.lang.String accountAndDispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account findAccount(java.lang.String accountName, java.lang.String dispatcherName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account findAccount(
		final java.lang.String accountName, 
		final java.lang.String dispatcherName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (accountName == null || accountName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.findAccount(java.lang.String accountName, java.lang.String dispatcherName) - accountName cannot be null");
		}
		if (dispatcherName == null || dispatcherName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.findAccount(java.lang.String accountName, java.lang.String dispatcherName) - dispatcherName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccount(accountName, dispatcherName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.findAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.findAccount", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleFindAccount(java.lang.String accountName, java.lang.String dispatcherName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account findAccountByExternalId(java.lang.String externalId, java.lang.String system)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account findAccountByExternalId(
		final java.lang.String externalId, 
		final java.lang.String system)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (externalId == null || externalId.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.findAccountByExternalId(java.lang.String externalId, java.lang.String system) - externalId cannot be null");
		}
		if (system == null || system.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.findAccountByExternalId(java.lang.String externalId, java.lang.String system) - system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccountByExternalId(externalId, system)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.findAccountByExternalId", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.findAccountByExternalId", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleFindAccountByExternalId(java.lang.String externalId, java.lang.String system) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account findAccountById(long id)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account findAccountById(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccountById(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.findAccountById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.findAccountById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleFindAccountById(long id) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account generateAccountSshPrivateKey(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account generateAccountSshPrivateKey(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.generateAccountSshPrivateKey(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.generateAccountSshPrivateKey(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.generateAccountSshPrivateKey(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.generateAccountSshPrivateKey(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.generateAccountSshPrivateKey(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.generateAccountSshPrivateKey(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateAccountSshPrivateKey(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.generateAccountSshPrivateKey", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.generateAccountSshPrivateKey", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleGenerateAccountSshPrivateKey(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account load(java.lang.Long identifier)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account load(
		final java.lang.Long identifier)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (identifier == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.load(java.lang.Long identifier) - identifier cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleLoad(identifier)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.load", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.load", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleLoad(java.lang.Long identifier) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account removeAccountSnapshot(com.soffid.iam.base.api.Account account)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account removeAccountSnapshot(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.removeAccountSnapshot(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.removeAccountSnapshot(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.removeAccountSnapshot(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.removeAccountSnapshot(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.removeAccountSnapshot(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.removeAccountSnapshot(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleRemoveAccountSnapshot(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.removeAccountSnapshot", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.removeAccountSnapshot", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleRemoveAccountSnapshot(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account setAccountSshPrivateKey(com.soffid.iam.base.api.Account account, java.lang.String privateKey)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account setAccountSshPrivateKey(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String privateKey)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.setAccountSshPrivateKey(com.soffid.iam.base.api.Account account, java.lang.String privateKey) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.setAccountSshPrivateKey(com.soffid.iam.base.api.Account account, java.lang.String privateKey) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.setAccountSshPrivateKey(com.soffid.iam.base.api.Account account, java.lang.String privateKey) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.setAccountSshPrivateKey(com.soffid.iam.base.api.Account account, java.lang.String privateKey) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.setAccountSshPrivateKey(com.soffid.iam.base.api.Account account, java.lang.String privateKey) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.setAccountSshPrivateKey(com.soffid.iam.base.api.Account account, java.lang.String privateKey) - account.passwordPolicy cannot be null");
		}
		if (privateKey == null || privateKey.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.setAccountSshPrivateKey(com.soffid.iam.base.api.Account account, java.lang.String privateKey) - privateKey cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSetAccountSshPrivateKey(account, privateKey)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.setAccountSshPrivateKey", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.setAccountSshPrivateKey", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleSetAccountSshPrivateKey(com.soffid.iam.base.api.Account account, java.lang.String privateKey) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account updateAccount(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account updateAccount(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.updateAccount(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.updateAccount(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.updateAccount(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.updateAccount(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.updateAccount(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.updateAccount(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateAccount(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.AccountAlreadyExistsException) 
			throw (com.soffid.iam.exception.AccountAlreadyExistsException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.updateAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.updateAccount", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleUpdateAccount(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.Account updateAccount2(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account updateAccount2(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.updateAccount2(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.updateAccount2(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.updateAccount2(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.updateAccount2(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.updateAccount2(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.base.service.AccountService.updateAccount2(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateAccount2(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.AccountAlreadyExistsException) 
			throw (com.soffid.iam.exception.AccountAlreadyExistsException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.updateAccount2", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.updateAccount2", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleUpdateAccount2(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.AsyncProcessTracker disableAccounts(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AsyncProcessTracker disableAccounts(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (rules == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.base.service.AccountService.disableAccounts(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules) - rules cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDisableAccounts(scimQuery, rules)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.AsyncProcessTracker) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.disableAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.disableAccounts", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AsyncProcessTracker handleDisableAccounts(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.AsyncProcessTracker disableAccountsPreview(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, java.util.List<java.lang.Object[]> actions)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AsyncProcessTracker disableAccountsPreview(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, 
		final java.util.List<java.lang.Object[]> actions)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (rules == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.base.service.AccountService.disableAccountsPreview(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, java.util.List<java.lang.Object[]> actions) - rules cannot be empty");
		}
		if (actions == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AsyncProcessTracker com.soffid.iam.base.service.AccountService.disableAccountsPreview(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, java.util.List<java.lang.Object[]> actions) - actions cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDisableAccountsPreview(scimQuery, rules, actions)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.AsyncProcessTracker) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.disableAccountsPreview", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.disableAccountsPreview", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AsyncProcessTracker handleDisableAccountsPreview(java.lang.String scimQuery, java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, java.util.List<java.lang.Object[]> actions) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.User getHPAccountOwner(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User getHPAccountOwner(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.AccountService.getHPAccountOwner(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.AccountService.getHPAccountOwner(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.AccountService.getHPAccountOwner(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.AccountService.getHPAccountOwner(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.AccountService.getHPAccountOwner(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.service.AccountService.getHPAccountOwner(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetHPAccountOwner(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.getHPAccountOwner", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.getHPAccountOwner", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleGetHPAccountOwner(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.UserAccount createAccount(com.soffid.iam.base.api.User usuari, com.soffid.iam.iga.api.System dispatcher, java.lang.String name)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.UserAccount createAccount(
		final com.soffid.iam.base.api.User usuari, 
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.NeedsAccountNameException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		if (usuari == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserAccount com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.User usuari, com.soffid.iam.iga.api.System dispatcher, java.lang.String name) - usuari cannot be null");
		}
		if (usuari.getUserName() == null || usuari.getUserName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserAccount com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.User usuari, com.soffid.iam.iga.api.System dispatcher, java.lang.String name) - usuari.userName cannot be null");
		}
		if (usuari.getFirstName() == null || usuari.getFirstName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserAccount com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.User usuari, com.soffid.iam.iga.api.System dispatcher, java.lang.String name) - usuari.firstName cannot be null");
		}
		if (usuari.getLastName() == null || usuari.getLastName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserAccount com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.User usuari, com.soffid.iam.iga.api.System dispatcher, java.lang.String name) - usuari.lastName cannot be null");
		}
		if (usuari.getUserType() == null || usuari.getUserType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserAccount com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.User usuari, com.soffid.iam.iga.api.System dispatcher, java.lang.String name) - usuari.userType cannot be null");
		}
		if (usuari.getPrimaryGroup() == null || usuari.getPrimaryGroup().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserAccount com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.User usuari, com.soffid.iam.iga.api.System dispatcher, java.lang.String name) - usuari.primaryGroup cannot be null");
		}
		if (dispatcher == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserAccount com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.User usuari, com.soffid.iam.iga.api.System dispatcher, java.lang.String name) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserAccount com.soffid.iam.base.service.AccountService.createAccount(com.soffid.iam.base.api.User usuari, com.soffid.iam.iga.api.System dispatcher, java.lang.String name) - dispatcher.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateAccount(usuari, dispatcher, name)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.UserAccount) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.NeedsAccountNameException) 
			throw (com.soffid.iam.exception.NeedsAccountNameException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.AccountAlreadyExistsException) 
			throw (com.soffid.iam.exception.AccountAlreadyExistsException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.createAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.createAccount", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.UserAccount handleCreateAccount(com.soffid.iam.base.api.User usuari, com.soffid.iam.iga.api.System dispatcher, java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.UserData createAccountAttribute(com.soffid.iam.base.api.UserData attribute)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.UserData createAccountAttribute(
		final com.soffid.iam.base.api.UserData attribute)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (attribute == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.base.service.AccountService.createAccountAttribute(com.soffid.iam.base.api.UserData attribute) - attribute cannot be null");
		}
		if (attribute.getAttribute() == null || attribute.getAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.base.service.AccountService.createAccountAttribute(com.soffid.iam.base.api.UserData attribute) - attribute.attribute cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateAccountAttribute(attribute)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.UserData) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.createAccountAttribute", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.createAccountAttribute", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.UserData handleCreateAccountAttribute(com.soffid.iam.base.api.UserData attribute) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.iam.base.api.UserData updateAccountAttribute(com.soffid.iam.base.api.UserData attribute)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.UserData updateAccountAttribute(
		final com.soffid.iam.base.api.UserData attribute)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (attribute == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.base.service.AccountService.updateAccountAttribute(com.soffid.iam.base.api.UserData attribute) - attribute cannot be null");
		}
		if (attribute.getAttribute() == null || attribute.getAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.base.service.AccountService.updateAccountAttribute(com.soffid.iam.base.api.UserData attribute) - attribute.attribute cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateAccountAttribute(attribute)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.UserData) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.updateAccountAttribute", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.updateAccountAttribute", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.UserData handleUpdateAccountAttribute(com.soffid.iam.base.api.UserData attribute) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Account> findAccounts(com.soffid.zkdb.api.Query query)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Account> findAccounts(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (query == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.findAccounts(com.soffid.zkdb.api.Query query) - query cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccounts(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Account>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.findAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.findAccounts", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Account> handleFindAccounts(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#int isUpdatePendingExtended(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public int isUpdatePendingExtended(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("int com.soffid.iam.base.service.AccountService.isUpdatePendingExtended(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("int com.soffid.iam.base.service.AccountService.isUpdatePendingExtended(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("int com.soffid.iam.base.service.AccountService.isUpdatePendingExtended(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("int com.soffid.iam.base.service.AccountService.isUpdatePendingExtended(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("int com.soffid.iam.base.service.AccountService.isUpdatePendingExtended(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("int com.soffid.iam.base.service.AccountService.isUpdatePendingExtended(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsUpdatePendingExtended(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Integer) __r[0]).intValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.isUpdatePendingExtended", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.isUpdatePendingExtended", (Throwable) __r[1]);
	}

	protected abstract int handleIsUpdatePendingExtended(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.lang.String guessAccountName(java.lang.String userName, java.lang.String dispatcherName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class})
	public java.lang.String guessAccountName(
		final java.lang.String userName, 
		final java.lang.String dispatcherName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.AccountService.guessAccountName(java.lang.String userName, java.lang.String dispatcherName) - userName cannot be null");
		}
		if (dispatcherName == null || dispatcherName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.AccountService.guessAccountName(java.lang.String userName, java.lang.String dispatcherName) - dispatcherName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGuessAccountName(userName, dispatcherName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.guessAccountName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.guessAccountName", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGuessAccountName(java.lang.String userName, java.lang.String dispatcherName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.lang.String guessAccountNameForDomain(java.lang.String userName, java.lang.String domainName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class})
	public java.lang.String guessAccountNameForDomain(
		final java.lang.String userName, 
		final java.lang.String domainName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.AccountService.guessAccountNameForDomain(java.lang.String userName, java.lang.String domainName) - userName cannot be null");
		}
		if (domainName == null || domainName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.AccountService.guessAccountNameForDomain(java.lang.String userName, java.lang.String domainName) - domainName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGuessAccountNameForDomain(userName, domainName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.guessAccountNameForDomain", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.guessAccountNameForDomain", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGuessAccountNameForDomain(java.lang.String userName, java.lang.String domainName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.lang.String predictAccountName(java.lang.Long userId, java.lang.String dispatcher, java.lang.Long domainId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public java.lang.String predictAccountName(
		final java.lang.Long userId, 
		final java.lang.String dispatcher, 
		final java.lang.Long domainId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.NeedsAccountNameException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		if (userId == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.AccountService.predictAccountName(java.lang.Long userId, java.lang.String dispatcher, java.lang.Long domainId) - userId cannot be null");
		}
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.AccountService.predictAccountName(java.lang.Long userId, java.lang.String dispatcher, java.lang.Long domainId) - dispatcher cannot be null");
		}
		if (domainId == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.service.AccountService.predictAccountName(java.lang.Long userId, java.lang.String dispatcher, java.lang.Long domainId) - domainId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handlePredictAccountName(userId, dispatcher, domainId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.NeedsAccountNameException) 
			throw (com.soffid.iam.exception.NeedsAccountNameException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.AccountAlreadyExistsException) 
			throw (com.soffid.iam.exception.AccountAlreadyExistsException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.predictAccountName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.predictAccountName", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handlePredictAccountName(java.lang.Long userId, java.lang.String dispatcher, java.lang.Long domainId) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<java.lang.String> findAccountNames(java.lang.String system)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		readOnly=true)
	public java.util.Collection<java.lang.String> findAccountNames(
		final java.lang.String system)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (system == null || system.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.findAccountNames(java.lang.String system) - system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccountNames(system)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.findAccountNames", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.findAccountNames", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.String> handleFindAccountNames(java.lang.String system) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<com.soffid.iam.pam.api.HostService> findAccountServices(com.soffid.iam.base.api.Account account)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.pam.api.HostService> findAccountServices(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.pam.api.HostService> com.soffid.iam.base.service.AccountService.findAccountServices(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.pam.api.HostService> com.soffid.iam.base.service.AccountService.findAccountServices(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.pam.api.HostService> com.soffid.iam.base.service.AccountService.findAccountServices(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.pam.api.HostService> com.soffid.iam.base.service.AccountService.findAccountServices(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.pam.api.HostService> com.soffid.iam.base.service.AccountService.findAccountServices(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.pam.api.HostService> com.soffid.iam.base.service.AccountService.findAccountServices(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccountServices(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.pam.api.HostService>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.findAccountServices", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.findAccountServices", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.pam.api.HostService> handleFindAccountServices(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<java.lang.String> getAccountUsers(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.String> getAccountUsers(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAccountUsers(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.getAccountUsers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.getAccountUsers", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.String> handleGetAccountUsers(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<java.lang.String> getAccountUsers(com.soffid.iam.base.api.Account account, com.soffid.iam.base.api.AccountAccessLevelEnum level)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.String> getAccountUsers(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.base.api.AccountAccessLevelEnum level)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account, com.soffid.iam.base.api.AccountAccessLevelEnum level) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account, com.soffid.iam.base.api.AccountAccessLevelEnum level) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account, com.soffid.iam.base.api.AccountAccessLevelEnum level) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account, com.soffid.iam.base.api.AccountAccessLevelEnum level) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account, com.soffid.iam.base.api.AccountAccessLevelEnum level) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account, com.soffid.iam.base.api.AccountAccessLevelEnum level) - account.passwordPolicy cannot be null");
		}
		if (level == null) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.base.service.AccountService.getAccountUsers(com.soffid.iam.base.api.Account account, com.soffid.iam.base.api.AccountAccessLevelEnum level) - level cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAccountUsers(account, level)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.getAccountUsers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.getAccountUsers", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.String> handleGetAccountUsers(com.soffid.iam.base.api.Account account, com.soffid.iam.base.api.AccountAccessLevelEnum level) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getActiveTasks(com.soffid.iam.base.api.Account account)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getActiveTasks(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> com.soffid.iam.base.service.AccountService.getActiveTasks(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> com.soffid.iam.base.service.AccountService.getActiveTasks(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> com.soffid.iam.base.service.AccountService.getActiveTasks(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> com.soffid.iam.base.service.AccountService.getActiveTasks(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> com.soffid.iam.base.service.AccountService.getActiveTasks(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> com.soffid.iam.base.service.AccountService.getActiveTasks(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetActiveTasks(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.getActiveTasks", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.getActiveTasks", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> handleGetActiveTasks(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<com.soffid.iam.base.api.UserAccount> getUserAccounts(com.soffid.iam.base.api.User usuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.UserAccount> getUserAccounts(
		final com.soffid.iam.base.api.User usuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuari == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.getUserAccounts(com.soffid.iam.base.api.User usuari) - usuari cannot be null");
		}
		if (usuari.getUserName() == null || usuari.getUserName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.getUserAccounts(com.soffid.iam.base.api.User usuari) - usuari.userName cannot be null");
		}
		if (usuari.getFirstName() == null || usuari.getFirstName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.getUserAccounts(com.soffid.iam.base.api.User usuari) - usuari.firstName cannot be null");
		}
		if (usuari.getLastName() == null || usuari.getLastName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.getUserAccounts(com.soffid.iam.base.api.User usuari) - usuari.lastName cannot be null");
		}
		if (usuari.getUserType() == null || usuari.getUserType().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.getUserAccounts(com.soffid.iam.base.api.User usuari) - usuari.userType cannot be null");
		}
		if (usuari.getPrimaryGroup() == null || usuari.getPrimaryGroup().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.getUserAccounts(com.soffid.iam.base.api.User usuari) - usuari.primaryGroup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserAccounts(usuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.UserAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.getUserAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.getUserAccounts", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.UserAccount> handleGetUserAccounts(com.soffid.iam.base.api.User usuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<java.lang.Long> getUserGrantedAccountIds(com.soffid.iam.base.api.User usuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.Long> getUserGrantedAccountIds(
		final com.soffid.iam.base.api.User usuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuari == null) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.Long> com.soffid.iam.base.service.AccountService.getUserGrantedAccountIds(com.soffid.iam.base.api.User usuari) - usuari cannot be null");
		}
		if (usuari.getUserName() == null || usuari.getUserName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.Long> com.soffid.iam.base.service.AccountService.getUserGrantedAccountIds(com.soffid.iam.base.api.User usuari) - usuari.userName cannot be null");
		}
		if (usuari.getFirstName() == null || usuari.getFirstName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.Long> com.soffid.iam.base.service.AccountService.getUserGrantedAccountIds(com.soffid.iam.base.api.User usuari) - usuari.firstName cannot be null");
		}
		if (usuari.getLastName() == null || usuari.getLastName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.Long> com.soffid.iam.base.service.AccountService.getUserGrantedAccountIds(com.soffid.iam.base.api.User usuari) - usuari.lastName cannot be null");
		}
		if (usuari.getUserType() == null || usuari.getUserType().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.Long> com.soffid.iam.base.service.AccountService.getUserGrantedAccountIds(com.soffid.iam.base.api.User usuari) - usuari.userType cannot be null");
		}
		if (usuari.getPrimaryGroup() == null || usuari.getPrimaryGroup().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.Long> com.soffid.iam.base.service.AccountService.getUserGrantedAccountIds(com.soffid.iam.base.api.User usuari) - usuari.primaryGroup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserGrantedAccountIds(usuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.Long>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.getUserGrantedAccountIds", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.getUserGrantedAccountIds", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.Long> handleGetUserGrantedAccountIds(com.soffid.iam.base.api.User usuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<com.soffid.iam.base.api.Account> getUserGrantedAccounts(com.soffid.iam.base.api.User usuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.Account> getUserGrantedAccounts(
		final com.soffid.iam.base.api.User usuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuari == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari) - usuari cannot be null");
		}
		if (usuari.getUserName() == null || usuari.getUserName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari) - usuari.userName cannot be null");
		}
		if (usuari.getFirstName() == null || usuari.getFirstName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari) - usuari.firstName cannot be null");
		}
		if (usuari.getLastName() == null || usuari.getLastName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari) - usuari.lastName cannot be null");
		}
		if (usuari.getUserType() == null || usuari.getUserType().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari) - usuari.userType cannot be null");
		}
		if (usuari.getPrimaryGroup() == null || usuari.getPrimaryGroup().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari) - usuari.primaryGroup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserGrantedAccounts(usuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.Account>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.getUserGrantedAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.getUserGrantedAccounts", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.Account> handleGetUserGrantedAccounts(com.soffid.iam.base.api.User usuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.Collection<com.soffid.iam.base.api.Account> getUserGrantedAccounts(com.soffid.iam.base.api.User usuari, com.soffid.iam.base.api.AccountAccessLevelEnum level)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.Account> getUserGrantedAccounts(
		final com.soffid.iam.base.api.User usuari, 
		final com.soffid.iam.base.api.AccountAccessLevelEnum level)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuari == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari, com.soffid.iam.base.api.AccountAccessLevelEnum level) - usuari cannot be null");
		}
		if (usuari.getUserName() == null || usuari.getUserName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari, com.soffid.iam.base.api.AccountAccessLevelEnum level) - usuari.userName cannot be null");
		}
		if (usuari.getFirstName() == null || usuari.getFirstName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari, com.soffid.iam.base.api.AccountAccessLevelEnum level) - usuari.firstName cannot be null");
		}
		if (usuari.getLastName() == null || usuari.getLastName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari, com.soffid.iam.base.api.AccountAccessLevelEnum level) - usuari.lastName cannot be null");
		}
		if (usuari.getUserType() == null || usuari.getUserType().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari, com.soffid.iam.base.api.AccountAccessLevelEnum level) - usuari.userType cannot be null");
		}
		if (usuari.getPrimaryGroup() == null || usuari.getPrimaryGroup().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari, com.soffid.iam.base.api.AccountAccessLevelEnum level) - usuari.primaryGroup cannot be null");
		}
		if (level == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.getUserGrantedAccounts(com.soffid.iam.base.api.User usuari, com.soffid.iam.base.api.AccountAccessLevelEnum level) - level cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserGrantedAccounts(usuari, level)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.Account>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.getUserGrantedAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.getUserGrantedAccounts", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.Account> handleGetUserGrantedAccounts(com.soffid.iam.base.api.User usuari, com.soffid.iam.base.api.AccountAccessLevelEnum level) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.Account> findAccountsNearToExpire(java.util.Date currentDate, java.util.Date limitDate, java.util.Collection<com.soffid.iam.base.api.AccountType> accTypes, java.util.Collection<com.soffid.iam.iga.api.UserType> userTypes)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.Account> findAccountsNearToExpire(
		final java.util.Date currentDate, 
		final java.util.Date limitDate, 
		final java.util.Collection<com.soffid.iam.base.api.AccountType> accTypes, 
		final java.util.Collection<com.soffid.iam.iga.api.UserType> userTypes)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (currentDate == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.findAccountsNearToExpire(java.util.Date currentDate, java.util.Date limitDate, java.util.Collection<com.soffid.iam.base.api.AccountType> accTypes, java.util.Collection<com.soffid.iam.iga.api.UserType> userTypes) - currentDate cannot be null");
		}
		if (limitDate == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.findAccountsNearToExpire(java.util.Date currentDate, java.util.Date limitDate, java.util.Collection<com.soffid.iam.base.api.AccountType> accTypes, java.util.Collection<com.soffid.iam.iga.api.UserType> userTypes) - limitDate cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccountsNearToExpire(currentDate, limitDate, accTypes, userTypes)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.Account>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.findAccountsNearToExpire", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.findAccountsNearToExpire", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.Account> handleFindAccountsNearToExpire(java.util.Date currentDate, java.util.Date limitDate, java.util.Collection<com.soffid.iam.base.api.AccountType> accTypes, java.util.Collection<com.soffid.iam.iga.api.UserType> userTypes) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.Account> findSharedAccountsByUser(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.Account> findSharedAccountsByUser(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.findSharedAccountsByUser(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSharedAccountsByUser(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.Account>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.findSharedAccountsByUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.findSharedAccountsByUser", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.Account> handleFindSharedAccountsByUser(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.iga.api.AccountHistory> findSharedAccountsHistoryByUser(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.iga.api.AccountHistory> findSharedAccountsHistoryByUser(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.iga.api.AccountHistory> com.soffid.iam.base.service.AccountService.findSharedAccountsHistoryByUser(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSharedAccountsHistoryByUser(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.iga.api.AccountHistory>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.findSharedAccountsHistoryByUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.findSharedAccountsHistoryByUser", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.iga.api.AccountHistory> handleFindSharedAccountsHistoryByUser(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.UserAccount> findUsersAccounts(java.lang.String userName, java.lang.String dispatcherName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.UserAccount> findUsersAccounts(
		final java.lang.String userName, 
		final java.lang.String dispatcherName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.findUsersAccounts(java.lang.String userName, java.lang.String dispatcherName) - userName cannot be null");
		}
		if (dispatcherName == null || dispatcherName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.findUsersAccounts(java.lang.String userName, java.lang.String dispatcherName) - dispatcherName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUsersAccounts(userName, dispatcherName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.UserAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.findUsersAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.findUsersAccounts", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.UserAccount> handleFindUsersAccounts(java.lang.String userName, java.lang.String dispatcherName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.UserAccount> findUserAccountsByDomain(java.lang.String user, java.lang.String passwordDomain)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.UserAccount> findUserAccountsByDomain(
		final java.lang.String user, 
		final java.lang.String passwordDomain)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.findUserAccountsByDomain(java.lang.String user, java.lang.String passwordDomain) - user cannot be null");
		}
		if (passwordDomain == null || passwordDomain.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.findUserAccountsByDomain(java.lang.String user, java.lang.String passwordDomain) - passwordDomain cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserAccountsByDomain(user, passwordDomain)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.UserAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.findUserAccountsByDomain", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.findUserAccountsByDomain", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.UserAccount> handleFindUserAccountsByDomain(java.lang.String user, java.lang.String passwordDomain) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.UserData> getAccountAttributes(com.soffid.iam.base.api.Account acc)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.UserData> getAccountAttributes(
		final com.soffid.iam.base.api.Account acc)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (acc == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserData> com.soffid.iam.base.service.AccountService.getAccountAttributes(com.soffid.iam.base.api.Account acc) - acc cannot be null");
		}
		if (acc.getSystem() == null || acc.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserData> com.soffid.iam.base.service.AccountService.getAccountAttributes(com.soffid.iam.base.api.Account acc) - acc.system cannot be null");
		}
		if (acc.getName() == null || acc.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserData> com.soffid.iam.base.service.AccountService.getAccountAttributes(com.soffid.iam.base.api.Account acc) - acc.name cannot be null");
		}
		if (acc.getKey() == null || acc.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserData> com.soffid.iam.base.service.AccountService.getAccountAttributes(com.soffid.iam.base.api.Account acc) - acc.key cannot be null");
		}
		if (acc.getType() == null ) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserData> com.soffid.iam.base.service.AccountService.getAccountAttributes(com.soffid.iam.base.api.Account acc) - acc.type cannot be null");
		}
		if (acc.getPasswordPolicy() == null || acc.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserData> com.soffid.iam.base.service.AccountService.getAccountAttributes(com.soffid.iam.base.api.Account acc) - acc.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAccountAttributes(acc)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.UserData>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.getAccountAttributes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.getAccountAttributes", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.UserData> handleGetAccountAttributes(com.soffid.iam.base.api.Account acc) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.UserAccountHistory> listAccountGrants(com.soffid.iam.base.api.Account acc)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.UserAccountHistory> listAccountGrants(
		final com.soffid.iam.base.api.Account acc)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (acc == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccountHistory> com.soffid.iam.base.service.AccountService.listAccountGrants(com.soffid.iam.base.api.Account acc) - acc cannot be null");
		}
		if (acc.getSystem() == null || acc.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccountHistory> com.soffid.iam.base.service.AccountService.listAccountGrants(com.soffid.iam.base.api.Account acc) - acc.system cannot be null");
		}
		if (acc.getName() == null || acc.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccountHistory> com.soffid.iam.base.service.AccountService.listAccountGrants(com.soffid.iam.base.api.Account acc) - acc.name cannot be null");
		}
		if (acc.getKey() == null || acc.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccountHistory> com.soffid.iam.base.service.AccountService.listAccountGrants(com.soffid.iam.base.api.Account acc) - acc.key cannot be null");
		}
		if (acc.getType() == null ) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccountHistory> com.soffid.iam.base.service.AccountService.listAccountGrants(com.soffid.iam.base.api.Account acc) - acc.type cannot be null");
		}
		if (acc.getPasswordPolicy() == null || acc.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccountHistory> com.soffid.iam.base.service.AccountService.listAccountGrants(com.soffid.iam.base.api.Account acc) - acc.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleListAccountGrants(acc)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.UserAccountHistory>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.listAccountGrants", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.listAccountGrants", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.UserAccountHistory> handleListAccountGrants(com.soffid.iam.base.api.Account acc) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.Account> listNonUserAccounts(com.soffid.iam.iga.api.System dispatcher, java.lang.String nom)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.Account> listNonUserAccounts(
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String nom)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcher == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.listNonUserAccounts(com.soffid.iam.iga.api.System dispatcher, java.lang.String nom) - dispatcher cannot be null");
		}
		if (dispatcher.getName() == null || dispatcher.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.Account> com.soffid.iam.base.service.AccountService.listNonUserAccounts(com.soffid.iam.iga.api.System dispatcher, java.lang.String nom) - dispatcher.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleListNonUserAccounts(dispatcher, nom)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.Account>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.listNonUserAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.listNonUserAccounts", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.Account> handleListNonUserAccounts(com.soffid.iam.iga.api.System dispatcher, java.lang.String nom) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#java.util.List<com.soffid.iam.base.api.UserAccount> listUserAccounts(com.soffid.iam.base.api.User usuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.UserAccount> listUserAccounts(
		final com.soffid.iam.base.api.User usuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuari == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.listUserAccounts(com.soffid.iam.base.api.User usuari) - usuari cannot be null");
		}
		if (usuari.getUserName() == null || usuari.getUserName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.listUserAccounts(com.soffid.iam.base.api.User usuari) - usuari.userName cannot be null");
		}
		if (usuari.getFirstName() == null || usuari.getFirstName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.listUserAccounts(com.soffid.iam.base.api.User usuari) - usuari.firstName cannot be null");
		}
		if (usuari.getLastName() == null || usuari.getLastName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.listUserAccounts(com.soffid.iam.base.api.User usuari) - usuari.lastName cannot be null");
		}
		if (usuari.getUserType() == null || usuari.getUserType().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.listUserAccounts(com.soffid.iam.base.api.User usuari) - usuari.userType cannot be null");
		}
		if (usuari.getPrimaryGroup() == null || usuari.getPrimaryGroup().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserAccount> com.soffid.iam.base.service.AccountService.listUserAccounts(com.soffid.iam.base.api.User usuari) - usuari.primaryGroup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleListUserAccounts(usuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.UserAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.listUserAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.listUserAccounts", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.UserAccount> handleListUserAccounts(com.soffid.iam.base.api.User usuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void checkinHPAccount(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void checkinHPAccount(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.checkinHPAccount(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.checkinHPAccount(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.checkinHPAccount(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.checkinHPAccount(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.checkinHPAccount(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.checkinHPAccount(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCheckinHPAccount(account);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.checkinHPAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.checkinHPAccount", (Throwable) __r[1]);
	}

	protected abstract void handleCheckinHPAccount(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void checkinHPAccounts()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void checkinHPAccounts()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCheckinHPAccounts();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.checkinHPAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.checkinHPAccounts", (Throwable) __r[1]);
	}

	protected abstract void handleCheckinHPAccounts() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void generateUserAccounts(java.lang.String user)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void generateUserAccounts(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.generateUserAccounts(java.lang.String user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleGenerateUserAccounts(user);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.generateUserAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.generateUserAccounts", (Throwable) __r[1]);
	}

	protected abstract void handleGenerateUserAccounts(java.lang.String user) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void removeAccount(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void removeAccount(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccount(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccount(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccount(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccount(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccount(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccount(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemoveAccount(account);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.removeAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.removeAccount", (Throwable) __r[1]);
	}

	protected abstract void handleRemoveAccount(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void removeAccount(com.soffid.iam.base.api.UserAccount account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void removeAccount(
		final com.soffid.iam.base.api.UserAccount account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccount(com.soffid.iam.base.api.UserAccount account) - account cannot be null");
		}
		if (account.getUser() == null || account.getUser().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccount(com.soffid.iam.base.api.UserAccount account) - account.user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemoveAccount(account);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.removeAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.removeAccount", (Throwable) __r[1]);
	}

	protected abstract void handleRemoveAccount(com.soffid.iam.base.api.UserAccount account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void removeAccountAttribute(com.soffid.iam.base.api.UserData attribute)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void removeAccountAttribute(
		final com.soffid.iam.base.api.UserData attribute)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (attribute == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccountAttribute(com.soffid.iam.base.api.UserData attribute) - attribute cannot be null");
		}
		if (attribute.getAttribute() == null || attribute.getAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccountAttribute(com.soffid.iam.base.api.UserData attribute) - attribute.attribute cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemoveAccountAttribute(attribute);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.removeAccountAttribute", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.removeAccountAttribute", (Throwable) __r[1]);
	}

	protected abstract void handleRemoveAccountAttribute(com.soffid.iam.base.api.UserData attribute) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void removeAccountGrant(com.soffid.iam.base.api.UserAccountHistory acc)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void removeAccountGrant(
		final com.soffid.iam.base.api.UserAccountHistory acc)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (acc == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccountGrant(com.soffid.iam.base.api.UserAccountHistory acc) - acc cannot be null");
		}
		if (acc.getId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccountGrant(com.soffid.iam.base.api.UserAccountHistory acc) - acc.id cannot be null");
		}
		if (acc.getUser() == null || acc.getUser().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccountGrant(com.soffid.iam.base.api.UserAccountHistory acc) - acc.user cannot be null");
		}
		if (acc.getAccount() == null || acc.getAccount().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccountGrant(com.soffid.iam.base.api.UserAccountHistory acc) - acc.account cannot be null");
		}
		if (acc.getSystem() == null || acc.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.removeAccountGrant(com.soffid.iam.base.api.UserAccountHistory acc) - acc.system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemoveAccountGrant(acc);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.removeAccountGrant", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.removeAccountGrant", (Throwable) __r[1]);
	}

	protected abstract void handleRemoveAccountGrant(com.soffid.iam.base.api.UserAccountHistory acc) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void renameAccount(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void renameAccount(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.renameAccount(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.renameAccount(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.renameAccount(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.renameAccount(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.renameAccount(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.renameAccount(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRenameAccount(account);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.AccountAlreadyExistsException) 
			throw (com.soffid.iam.exception.AccountAlreadyExistsException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.renameAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.renameAccount", (Throwable) __r[1]);
	}

	protected abstract void handleRenameAccount(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void sendAccountPassword(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void sendAccountPassword(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.sendAccountPassword(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.sendAccountPassword(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.sendAccountPassword(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.sendAccountPassword(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.sendAccountPassword(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.sendAccountPassword(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSendAccountPassword(account);
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.sendAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.sendAccountPassword", (Throwable) __r[1]);
	}

	protected abstract void handleSendAccountPassword(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void setAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.passwordPolicy cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSetAccountPassword(account, password);
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.setAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.setAccountPassword", (Throwable) __r[1]);
	}

	protected abstract void handleSetAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void setAccountTemporaryPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void setAccountTemporaryPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountTemporaryPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountTemporaryPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountTemporaryPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountTemporaryPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountTemporaryPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountTemporaryPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.passwordPolicy cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.setAccountTemporaryPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSetAccountTemporaryPassword(account, password);
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.setAccountTemporaryPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.setAccountTemporaryPassword", (Throwable) __r[1]);
	}

	protected abstract void handleSetAccountTemporaryPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void synchronizeAccount(java.lang.String accountName, java.lang.String system)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void synchronizeAccount(
		final java.lang.String accountName, 
		final java.lang.String system)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (accountName == null || accountName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.synchronizeAccount(java.lang.String accountName, java.lang.String system) - accountName cannot be null");
		}
		if (system == null || system.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.synchronizeAccount(java.lang.String accountName, java.lang.String system) - system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSynchronizeAccount(accountName, system);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.synchronizeAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.synchronizeAccount", (Throwable) __r[1]);
	}

	protected abstract void handleSynchronizeAccount(java.lang.String accountName, java.lang.String system) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void updateAccountLastUpdate(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void updateAccountLastUpdate(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountLastUpdate(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountLastUpdate(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountLastUpdate(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountLastUpdate(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountLastUpdate(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountLastUpdate(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateAccountLastUpdate(account);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.updateAccountLastUpdate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.updateAccountLastUpdate", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateAccountLastUpdate(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void updateAccountPasswordDate(com.soffid.iam.base.api.Account account, java.lang.Long passwordTerm)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void updateAccountPasswordDate(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.Long passwordTerm)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountPasswordDate(com.soffid.iam.base.api.Account account, java.lang.Long passwordTerm) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountPasswordDate(com.soffid.iam.base.api.Account account, java.lang.Long passwordTerm) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountPasswordDate(com.soffid.iam.base.api.Account account, java.lang.Long passwordTerm) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountPasswordDate(com.soffid.iam.base.api.Account account, java.lang.Long passwordTerm) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountPasswordDate(com.soffid.iam.base.api.Account account, java.lang.Long passwordTerm) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountPasswordDate(com.soffid.iam.base.api.Account account, java.lang.Long passwordTerm) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateAccountPasswordDate(account, passwordTerm);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.updateAccountPasswordDate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.updateAccountPasswordDate", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateAccountPasswordDate(com.soffid.iam.base.api.Account account, java.lang.Long passwordTerm) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void updateAccountPasswordDate2(com.soffid.iam.base.api.Account account, java.util.Date expirationDate)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void updateAccountPasswordDate2(
		final com.soffid.iam.base.api.Account account, 
		final java.util.Date expirationDate)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountPasswordDate2(com.soffid.iam.base.api.Account account, java.util.Date expirationDate) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountPasswordDate2(com.soffid.iam.base.api.Account account, java.util.Date expirationDate) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountPasswordDate2(com.soffid.iam.base.api.Account account, java.util.Date expirationDate) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountPasswordDate2(com.soffid.iam.base.api.Account account, java.util.Date expirationDate) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountPasswordDate2(com.soffid.iam.base.api.Account account, java.util.Date expirationDate) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.updateAccountPasswordDate2(com.soffid.iam.base.api.Account account, java.util.Date expirationDate) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUpdateAccountPasswordDate2(account, expirationDate);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.updateAccountPasswordDate2", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.updateAccountPasswordDate2", (Throwable) __r[1]);
	}

	protected abstract void handleUpdateAccountPasswordDate2(com.soffid.iam.base.api.Account account, java.util.Date expirationDate) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void grantAcccountToUser(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String user, 
		final java.lang.Long processId, 
		final java.util.Date until)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until) - account.passwordPolicy cannot be null");
		}
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until) - user cannot be null");
		}
		if (processId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until) - processId cannot be null");
		}
		if (until == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until) - until cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleGrantAcccountToUser(account, user, processId, until);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.grantAcccountToUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.grantAcccountToUser", (Throwable) __r[1]);
	}

	protected abstract void handleGrantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until, java.lang.String entryPoint)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void grantAcccountToUser(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String user, 
		final java.lang.Long processId, 
		final java.util.Date until, 
		final java.lang.String entryPoint)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until, java.lang.String entryPoint) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until, java.lang.String entryPoint) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until, java.lang.String entryPoint) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until, java.lang.String entryPoint) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until, java.lang.String entryPoint) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until, java.lang.String entryPoint) - account.passwordPolicy cannot be null");
		}
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until, java.lang.String entryPoint) - user cannot be null");
		}
		if (processId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until, java.lang.String entryPoint) - processId cannot be null");
		}
		if (until == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.grantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until, java.lang.String entryPoint) - until cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleGrantAcccountToUser(account, user, processId, until, entryPoint);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.grantAcccountToUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.grantAcccountToUser", (Throwable) __r[1]);
	}

	protected abstract void handleGrantAcccountToUser(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.util.Date until, java.lang.String entryPoint) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerAccountReservationProcess(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String user, 
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId) - account.passwordPolicy cannot be null");
		}
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId) - user cannot be null");
		}
		if (processId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId) - processId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterAccountReservationProcess(account, user, processId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.registerAccountReservationProcess", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.registerAccountReservationProcess", (Throwable) __r[1]);
	}

	protected abstract void handleRegisterAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AccountService#	 * @see com.soffid.iam.base.service.AccountService#void registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.lang.String entryPoint)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerAccountReservationProcess(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String user, 
		final java.lang.Long processId, 
		final java.lang.String entryPoint)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.lang.String entryPoint) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.lang.String entryPoint) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.lang.String entryPoint) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.lang.String entryPoint) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.lang.String entryPoint) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.lang.String entryPoint) - account.passwordPolicy cannot be null");
		}
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.lang.String entryPoint) - user cannot be null");
		}
		if (processId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AccountService.registerAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.lang.String entryPoint) - processId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterAccountReservationProcess(account, user, processId, entryPoint);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AccountService.class).
			warn ("Error on AccountService.registerAccountReservationProcess", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountService.registerAccountReservationProcess", (Throwable) __r[1]);
	}

	protected abstract void handleRegisterAccountReservationProcess(com.soffid.iam.base.api.Account account, java.lang.String user, java.lang.Long processId, java.lang.String entryPoint) throws Exception;

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
