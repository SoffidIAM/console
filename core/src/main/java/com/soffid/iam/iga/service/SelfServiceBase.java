//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.SelfService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.SelfService
 */
public abstract class SelfServiceBase
	implements com.soffid.iam.iga.service.SelfService
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
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#boolean setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force)
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
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.SelfService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.SelfService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.SelfService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.SelfService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.SelfService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.SelfService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - account.passwordPolicy cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.SelfService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - password cannot be null");
		}
		if (untilDate == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.iga.service.SelfService.setHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) - untilDate cannot be null");
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.setHPAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.setHPAccountPassword", (Throwable) __r[1]);
	}

	protected abstract boolean handleSetHPAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password, java.util.Date untilDate, boolean force) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.am.api.AccessTree findRoot()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.AccessTree findRoot()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoot()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.AccessTree) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.findRoot", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.findRoot", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.AccessTree handleFindRoot() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.am.api.Password generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account)
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
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.generateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.generateAccountTemporaryPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.generateAccountTemporaryPassword", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleGenerateAccountTemporaryPassword(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.am.api.Password queryAccountPassword(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password queryAccountPassword(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountPassword(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountPassword(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountPassword(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountPassword(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountPassword(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountPassword(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.queryAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.queryAccountPassword", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleQueryAccountPassword(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.am.api.Password queryAccountPasswordBypassPolicy(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password queryAccountPasswordBypassPolicy(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountPasswordBypassPolicy(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountPasswordBypassPolicy(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountPasswordBypassPolicy(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountPasswordBypassPolicy(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountPasswordBypassPolicy(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountPasswordBypassPolicy(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleQueryAccountPasswordBypassPolicy(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.queryAccountPasswordBypassPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.queryAccountPasswordBypassPolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleQueryAccountPasswordBypassPolicy(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.am.api.Password queryAccountSshKey(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password queryAccountSshKey(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountSshKey(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountSshKey(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountSshKey(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountSshKey(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountSshKey(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountSshKey(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.queryAccountSshKey", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.queryAccountSshKey", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleQueryAccountSshKey(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.am.api.Password queryAccountSshKeyBypassPolicy(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password queryAccountSshKeyBypassPolicy(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountSshKeyBypassPolicy(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountSshKeyBypassPolicy(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountSshKeyBypassPolicy(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountSshKeyBypassPolicy(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountSshKeyBypassPolicy(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.iga.service.SelfService.queryAccountSshKeyBypassPolicy(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleQueryAccountSshKeyBypassPolicy(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.queryAccountSshKeyBypassPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.queryAccountSshKeyBypassPolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleQueryAccountSshKeyBypassPolicy(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.am.api.PasswordStatus passwordsStatus(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordStatus passwordsStatus(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordStatus com.soffid.iam.iga.service.SelfService.passwordsStatus(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordStatus com.soffid.iam.iga.service.SelfService.passwordsStatus(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordStatus com.soffid.iam.iga.service.SelfService.passwordsStatus(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordStatus com.soffid.iam.iga.service.SelfService.passwordsStatus(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordStatus com.soffid.iam.iga.service.SelfService.passwordsStatus(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordStatus com.soffid.iam.iga.service.SelfService.passwordsStatus(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handlePasswordsStatus(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordStatus) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.passwordsStatus", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.passwordsStatus", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordStatus handlePasswordsStatus(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.base.api.Account getAccountById(long id)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account getAccountById(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAccountById(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.getAccountById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.getAccountById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleGetAccountById(long id) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.base.api.Account updateSharedAccount(com.soffid.iam.base.api.Account account)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.Account updateSharedAccount(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.iga.service.SelfService.updateSharedAccount(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.iga.service.SelfService.updateSharedAccount(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.iga.service.SelfService.updateSharedAccount(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.iga.service.SelfService.updateSharedAccount(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.iga.service.SelfService.updateSharedAccount(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.Account com.soffid.iam.iga.service.SelfService.updateSharedAccount(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateSharedAccount(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.Account) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.updateSharedAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.updateSharedAccount", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.Account handleUpdateSharedAccount(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.base.api.DataType getDataTypeDescription(java.lang.String systemName, java.lang.String attName)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.DataType getDataTypeDescription(
		final java.lang.String systemName, 
		final java.lang.String attName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (attName == null || attName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.DataType com.soffid.iam.iga.service.SelfService.getDataTypeDescription(java.lang.String systemName, java.lang.String attName) - attName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDataTypeDescription(systemName, attName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.DataType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.getDataTypeDescription", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.getDataTypeDescription", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.DataType handleGetDataTypeDescription(java.lang.String systemName, java.lang.String attName) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.base.api.User getCurrentUser()
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.getCurrentUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.getCurrentUser", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleGetCurrentUser() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.base.api.UserData createSharedAccountData(com.soffid.iam.base.api.UserData data)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.UserData createSharedAccountData(
		final com.soffid.iam.base.api.UserData data)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (data == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.iga.service.SelfService.createSharedAccountData(com.soffid.iam.base.api.UserData data) - data cannot be null");
		}
		if (data.getAttribute() == null || data.getAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.iga.service.SelfService.createSharedAccountData(com.soffid.iam.base.api.UserData data) - data.attribute cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateSharedAccountData(data)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.UserData) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.createSharedAccountData", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.createSharedAccountData", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.UserData handleCreateSharedAccountData(com.soffid.iam.base.api.UserData data) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.base.api.UserData updateSharedAccountData(com.soffid.iam.base.api.UserData data)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.UserData updateSharedAccountData(
		final com.soffid.iam.base.api.UserData data)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (data == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.iga.service.SelfService.updateSharedAccountData(com.soffid.iam.base.api.UserData data) - data cannot be null");
		}
		if (data.getAttribute() == null || data.getAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.iga.service.SelfService.updateSharedAccountData(com.soffid.iam.base.api.UserData data) - data.attribute cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateSharedAccountData(data)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.UserData) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.updateSharedAccountData", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.updateSharedAccountData", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.UserData handleUpdateSharedAccountData(com.soffid.iam.base.api.UserData data) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.base.api.UserData updateUserAttribute(com.soffid.iam.base.api.UserData attribute)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.UserData updateUserAttribute(
		final com.soffid.iam.base.api.UserData attribute)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (attribute == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.iga.service.SelfService.updateUserAttribute(com.soffid.iam.base.api.UserData attribute) - attribute cannot be null");
		}
		if (attribute.getAttribute() == null || attribute.getAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.iga.service.SelfService.updateUserAttribute(com.soffid.iam.base.api.UserData attribute) - attribute.attribute cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateUserAttribute(attribute)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.UserData) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.updateUserAttribute", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.updateUserAttribute", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.UserData handleUpdateUserAttribute(com.soffid.iam.base.api.UserData attribute) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#com.soffid.iam.iga.api.System getDispatcherInformation(java.lang.String dispatcherCode)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.System getDispatcherInformation(
		final java.lang.String dispatcherCode)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dispatcherCode == null || dispatcherCode.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.System com.soffid.iam.iga.service.SelfService.getDispatcherInformation(java.lang.String dispatcherCode) - dispatcherCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDispatcherInformation(dispatcherCode)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.System) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.getDispatcherInformation", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.getDispatcherInformation", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.System handleGetDispatcherInformation(java.lang.String dispatcherCode) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#java.lang.String getClientHost()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String getClientHost()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetClientHost()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.getClientHost", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.getClientHost", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetClientHost() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#java.lang.String queryOtherAffectedAccounts(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String queryOtherAffectedAccounts(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.SelfService.queryOtherAffectedAccounts(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.SelfService.queryOtherAffectedAccounts(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.SelfService.queryOtherAffectedAccounts(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.SelfService.queryOtherAffectedAccounts(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.SelfService.queryOtherAffectedAccounts(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.iga.service.SelfService.queryOtherAffectedAccounts(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleQueryOtherAffectedAccounts(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.queryOtherAffectedAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.queryOtherAffectedAccounts", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleQueryOtherAffectedAccounts(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#java.util.Collection<com.soffid.iam.am.api.AccessTree> findChildren(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.AccessTree> findChildren(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (puntEntrada == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTree> com.soffid.iam.iga.service.SelfService.findChildren(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada cannot be null");
		}
		if (puntEntrada.getName() == null || puntEntrada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTree> com.soffid.iam.iga.service.SelfService.findChildren(com.soffid.iam.am.api.AccessTree puntEntrada) - puntEntrada.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindChildren(puntEntrada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.AccessTree>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.findChildren", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.findChildren", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.AccessTree> handleFindChildren(com.soffid.iam.am.api.AccessTree puntEntrada) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#java.util.Collection<com.soffid.iam.am.api.AccessTree> findEntryPoints(java.lang.String name)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.AccessTree> findEntryPoints(
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.AccessTree> com.soffid.iam.iga.service.SelfService.findEntryPoints(java.lang.String name) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindEntryPoints(name)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.AccessTree>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.findEntryPoints", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.findEntryPoints", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.AccessTree> handleFindEntryPoints(java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccounts()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccounts()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleAccounts()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.findRoleAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.findRoleAccounts", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindRoleAccounts() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUserGroupsByUserName()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUserGroupsByUserName()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserGroupsByUserName()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.UserGroup>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.findUserGroupsByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.findUserGroupsByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.UserGroup> handleFindUserGroupsByUserName() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#java.util.Collection<com.soffid.iam.base.api.Account> getUserAccounts()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.Account> getUserAccounts()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserAccounts()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.Account>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.getUserAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.getUserAccounts", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.Account> handleGetUserAccounts() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#java.util.Collection<com.soffid.iam.base.api.UserData> getUserAttributes()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.UserData> getUserAttributes()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserAttributes()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.UserData>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.getUserAttributes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.getUserAttributes", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.UserData> handleGetUserAttributes() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#java.util.List<com.soffid.iam.base.api.UserData> getAccountAttributes(com.soffid.iam.base.api.Account acc)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.UserData> getAccountAttributes(
		final com.soffid.iam.base.api.Account acc)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (acc == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserData> com.soffid.iam.iga.service.SelfService.getAccountAttributes(com.soffid.iam.base.api.Account acc) - acc cannot be null");
		}
		if (acc.getSystem() == null || acc.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserData> com.soffid.iam.iga.service.SelfService.getAccountAttributes(com.soffid.iam.base.api.Account acc) - acc.system cannot be null");
		}
		if (acc.getName() == null || acc.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserData> com.soffid.iam.iga.service.SelfService.getAccountAttributes(com.soffid.iam.base.api.Account acc) - acc.name cannot be null");
		}
		if (acc.getKey() == null || acc.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserData> com.soffid.iam.iga.service.SelfService.getAccountAttributes(com.soffid.iam.base.api.Account acc) - acc.key cannot be null");
		}
		if (acc.getType() == null ) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserData> com.soffid.iam.iga.service.SelfService.getAccountAttributes(com.soffid.iam.base.api.Account acc) - acc.type cannot be null");
		}
		if (acc.getPasswordPolicy() == null || acc.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.UserData> com.soffid.iam.iga.service.SelfService.getAccountAttributes(com.soffid.iam.base.api.Account acc) - acc.passwordPolicy cannot be null");
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.getAccountAttributes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.getAccountAttributes", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.UserData> handleGetAccountAttributes(com.soffid.iam.base.api.Account acc) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#java.util.List<com.soffid.iam.base.api.Account> getSharedAccounts(java.lang.String filter)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.Account> getSharedAccounts(
		final java.lang.String filter)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetSharedAccounts(filter)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.Account>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.getSharedAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.getSharedAccounts", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.Account> handleGetSharedAccounts(java.lang.String filter) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#void checkCanSetAccountPassword(com.soffid.iam.base.api.Account account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void checkCanSetAccountPassword(
		final com.soffid.iam.base.api.Account account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.checkCanSetAccountPassword(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.checkCanSetAccountPassword(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.checkCanSetAccountPassword(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.checkCanSetAccountPassword(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.checkCanSetAccountPassword(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.checkCanSetAccountPassword(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCheckCanSetAccountPassword(account);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.checkCanSetAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.checkCanSetAccountPassword", (Throwable) __r[1]);
	}

	protected abstract void handleCheckCanSetAccountPassword(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#void checkinHPAccount(com.soffid.iam.base.api.Account account)
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
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.checkinHPAccount(com.soffid.iam.base.api.Account account) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.checkinHPAccount(com.soffid.iam.base.api.Account account) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.checkinHPAccount(com.soffid.iam.base.api.Account account) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.checkinHPAccount(com.soffid.iam.base.api.Account account) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.checkinHPAccount(com.soffid.iam.base.api.Account account) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.checkinHPAccount(com.soffid.iam.base.api.Account account) - account.passwordPolicy cannot be null");
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.checkinHPAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.checkinHPAccount", (Throwable) __r[1]);
	}

	protected abstract void handleCheckinHPAccount(com.soffid.iam.base.api.Account account) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#void setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void setAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.passwordPolicy cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - password cannot be null");
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
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.setAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.setAccountPassword", (Throwable) __r[1]);
	}

	protected abstract void handleSetAccountPassword(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.SelfService#	 * @see com.soffid.iam.iga.service.SelfService#void setAccountSshKey(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void setAccountSshKey(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountSshKey(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account cannot be null");
		}
		if (account.getSystem() == null || account.getSystem().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountSshKey(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.system cannot be null");
		}
		if (account.getName() == null || account.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountSshKey(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.name cannot be null");
		}
		if (account.getKey() == null || account.getKey().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountSshKey(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.key cannot be null");
		}
		if (account.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountSshKey(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.type cannot be null");
		}
		if (account.getPasswordPolicy() == null || account.getPasswordPolicy().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountSshKey(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - account.passwordPolicy cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.SelfService.setAccountSshKey(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSetAccountSshKey(account, password);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.SelfService.class).
			warn ("Error on SelfService.setAccountSshKey", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SelfService.setAccountSshKey", (Throwable) __r[1]);
	}

	protected abstract void handleSetAccountSshKey(com.soffid.iam.base.api.Account account, com.soffid.iam.am.api.Password password) throws Exception;

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
