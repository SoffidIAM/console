//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.InternalPasswordService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.InternalPasswordService
 */
public abstract class InternalPasswordServiceBase
	implements com.soffid.iam.impl.service.InternalPasswordService
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

	private com.soffid.iam.sync.service.ConsoleLogonService consoleLogonService;

	/**
	 * Sets reference to <code>consoleLogonService</code>.
	 */
	public void setConsoleLogonService (com.soffid.iam.sync.service.ConsoleLogonService consoleLogonService) {
		this.consoleLogonService = consoleLogonService;
	}

	/**
	 * Gets reference to <code>consoleLogonService</code>.
	 */
	public com.soffid.iam.sync.service.ConsoleLogonService getConsoleLogonService () {
		return consoleLogonService;
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

	private com.soffid.iam.am.model.PasswordPolicyEntityDao passwordPolicyEntityDao;

	/**
	 * Sets reference to <code>passwordPolicyEntityDao</code>.
	 */
	public void setPasswordPolicyEntityDao (com.soffid.iam.am.model.PasswordPolicyEntityDao passwordPolicyEntityDao) {
		this.passwordPolicyEntityDao = passwordPolicyEntityDao;
	}

	/**
	 * Gets reference to <code>passwordPolicyEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntityDao getPasswordPolicyEntityDao () {
		return passwordPolicyEntityDao;
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

	private com.soffid.iam.sync.service.TaskGenerator taskGenerator;

	/**
	 * Sets reference to <code>taskGenerator</code>.
	 */
	public void setTaskGenerator (com.soffid.iam.sync.service.TaskGenerator taskGenerator) {
		this.taskGenerator = taskGenerator;
	}

	/**
	 * Gets reference to <code>taskGenerator</code>.
	 */
	public com.soffid.iam.sync.service.TaskGenerator getTaskGenerator () {
		return taskGenerator;
	}

	private com.soffid.iam.sync.service.TaskQueue taskQueue;

	/**
	 * Sets reference to <code>taskQueue</code>.
	 */
	public void setTaskQueue (com.soffid.iam.sync.service.TaskQueue taskQueue) {
		this.taskQueue = taskQueue;
	}

	/**
	 * Gets reference to <code>taskQueue</code>.
	 */
	public com.soffid.iam.sync.service.TaskQueue getTaskQueue () {
		return taskQueue;
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


	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#boolean checkPin(com.soffid.iam.base.model.UserEntity user, java.lang.String pin)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean checkPin(
		final com.soffid.iam.base.model.UserEntity user, 
		final java.lang.String pin)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.checkPin(com.soffid.iam.base.model.UserEntity user, java.lang.String pin) - user cannot be null");
		}
		if (pin == null || pin.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.checkPin(com.soffid.iam.base.model.UserEntity user, java.lang.String pin) - pin cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckPin(user, pin)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.checkPin", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.checkPin", (Throwable) __r[1]);
	}

	protected abstract boolean handleCheckPin(com.soffid.iam.base.model.UserEntity user, java.lang.String pin) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#boolean existsAccountPassword(com.soffid.iam.base.model.AccountEntity account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean existsAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.existsAccountPassword(com.soffid.iam.base.model.AccountEntity account) - account cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleExistsAccountPassword(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.existsAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.existsAccountPassword", (Throwable) __r[1]);
	}

	protected abstract boolean handleExistsAccountPassword(com.soffid.iam.base.model.AccountEntity account) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#boolean existsPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean existsPassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.existsPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain) - user cannot be null");
		}
		if (passwordDomain == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.existsPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain) - passwordDomain cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleExistsPassword(user, passwordDomain)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.existsPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.existsPassword", (Throwable) __r[1]);
	}

	protected abstract boolean handleExistsPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#boolean isAccountPasswordExpired(com.soffid.iam.base.model.AccountEntity account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean isAccountPasswordExpired(
		final com.soffid.iam.base.model.AccountEntity account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.isAccountPasswordExpired(com.soffid.iam.base.model.AccountEntity account) - account cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsAccountPasswordExpired(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.isAccountPasswordExpired", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.isAccountPasswordExpired", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsAccountPasswordExpired(com.soffid.iam.base.model.AccountEntity account) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#boolean isLastPasswordIdForUser(long id)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isLastPasswordIdForUser(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsLastPasswordIdForUser(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.isLastPasswordIdForUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.isLastPasswordIdForUser", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsLastPasswordIdForUser(long id) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#boolean isOldAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean isOldAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.isOldAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password) - account cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.isOldAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsOldAccountPassword(account, password)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.isOldAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.isOldAccountPassword", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsOldAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#boolean isOldPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean isOldPassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.isOldPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password) - user cannot be null");
		}
		if (passwordDomain == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.isOldPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password) - passwordDomain cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.isOldPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsOldPassword(user, passwordDomain, password)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.isOldPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.isOldPassword", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsOldPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#boolean isPasswordExpired(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean isPasswordExpired(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.isPasswordExpired(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain) - user cannot be null");
		}
		if (passwordDomain == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.isPasswordExpired(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain) - passwordDomain cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsPasswordExpired(user, passwordDomain)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.isPasswordExpired", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.isPasswordExpired", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsPasswordExpired(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#boolean updateExpiredPasswords(com.soffid.iam.base.model.UserEntity usuari, boolean externalAuth)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean updateExpiredPasswords(
		final com.soffid.iam.base.model.UserEntity usuari, 
		final boolean externalAuth)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuari == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.InternalPasswordService.updateExpiredPasswords(com.soffid.iam.base.model.UserEntity usuari, boolean externalAuth) - usuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateExpiredPasswords(usuari, externalAuth)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.updateExpiredPasswords", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.updateExpiredPasswords", (Throwable) __r[1]);
	}

	protected abstract boolean handleUpdateExpiredPasswords(com.soffid.iam.base.model.UserEntity usuari, boolean externalAuth) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.Password generateFakeAccountPassword(com.soffid.iam.base.model.AccountEntity account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password generateFakeAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateFakeAccountPassword(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.generateFakeAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.generateFakeAccountPassword", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleGenerateFakeAccountPassword(com.soffid.iam.base.model.AccountEntity account) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.Password generateFakePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passDomain)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password generateFakePassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passDomain)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (passDomain == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.impl.service.InternalPasswordService.generateFakePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passDomain) - passDomain cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateFakePassword(user, passDomain)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.generateFakePassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.generateFakePassword", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleGenerateFakePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passDomain) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.Password generateNewAccountPassword(com.soffid.iam.base.model.AccountEntity account, boolean mustBeChanged)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password generateNewAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final boolean mustBeChanged)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.impl.service.InternalPasswordService.generateNewAccountPassword(com.soffid.iam.base.model.AccountEntity account, boolean mustBeChanged) - account cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateNewAccountPassword(account, mustBeChanged)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.generateNewAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.generateNewAccountPassword", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleGenerateNewAccountPassword(com.soffid.iam.base.model.AccountEntity account, boolean mustBeChanged) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.Password generateNewPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passDomain, boolean mustBeChanged)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Password generateNewPassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passDomain, 
		final boolean mustBeChanged)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.impl.service.InternalPasswordService.generateNewPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passDomain, boolean mustBeChanged) - user cannot be null");
		}
		if (passDomain == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Password com.soffid.iam.impl.service.InternalPasswordService.generateNewPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passDomain, boolean mustBeChanged) - passDomain cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateNewPassword(user, passDomain, mustBeChanged)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Password) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.generateNewPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.generateNewPassword", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Password handleGenerateNewPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passDomain, boolean mustBeChanged) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.PasswordStatus getAccountPasswordsStatus(com.soffid.iam.base.model.AccountEntity account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordStatus getAccountPasswordsStatus(
		final com.soffid.iam.base.model.AccountEntity account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordStatus com.soffid.iam.impl.service.InternalPasswordService.getAccountPasswordsStatus(com.soffid.iam.base.model.AccountEntity account) - account cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAccountPasswordsStatus(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordStatus) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.getAccountPasswordsStatus", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.getAccountPasswordsStatus", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordStatus handleGetAccountPasswordsStatus(com.soffid.iam.base.model.AccountEntity account) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.PasswordStatus getAccountPasswordsStatusById(long account)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordStatus getAccountPasswordsStatusById(
		final long account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAccountPasswordsStatusById(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordStatus) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.getAccountPasswordsStatusById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.getAccountPasswordsStatusById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordStatus handleGetAccountPasswordsStatusById(long account) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.PasswordStatus getPasswordsStatus(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity domini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordStatus getPasswordsStatus(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity domini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordStatus com.soffid.iam.impl.service.InternalPasswordService.getPasswordsStatus(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity domini) - user cannot be null");
		}
		if (domini == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordStatus com.soffid.iam.impl.service.InternalPasswordService.getPasswordsStatus(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity domini) - domini cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPasswordsStatus(user, domini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordStatus) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.getPasswordsStatus", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.getPasswordsStatus", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordStatus handleGetPasswordsStatus(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity domini) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.PasswordStatus getPasswordsStatusById(long user, long domini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordStatus getPasswordsStatusById(
		final long user, 
		final long domini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPasswordsStatusById(user, domini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordStatus) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.getPasswordsStatusById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.getPasswordsStatusById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordStatus handleGetPasswordsStatusById(long user, long domini) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.PasswordValidation checkAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordValidation checkAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean checkTrusted, 
		final boolean checkExpired)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordValidation com.soffid.iam.impl.service.InternalPasswordService.checkAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired) - account cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordValidation com.soffid.iam.impl.service.InternalPasswordService.checkAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckAccountPassword(account, password, checkTrusted, checkExpired)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordValidation) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.checkAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.checkAccountPassword", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordValidation handleCheckAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.PasswordValidation checkPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordValidation checkPassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password, 
		final boolean checkTrusted, 
		final boolean checkExpired)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordValidation com.soffid.iam.impl.service.InternalPasswordService.checkPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired) - user cannot be null");
		}
		if (passwordDomain == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordValidation com.soffid.iam.impl.service.InternalPasswordService.checkPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired) - passwordDomain cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordValidation com.soffid.iam.impl.service.InternalPasswordService.checkPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckPassword(user, passwordDomain, password, checkTrusted, checkExpired)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordValidation) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.checkPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.checkPassword", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordValidation handleCheckPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.PolicyCheckResult checkAccountPolicy(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PolicyCheckResult checkAccountPolicy(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkAccountPolicy(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password) - account cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkAccountPolicy(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckAccountPolicy(account, password)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PolicyCheckResult) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.checkAccountPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.checkAccountPolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PolicyCheckResult handleCheckAccountPolicy(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.PolicyCheckResult checkPolicy(com.soffid.iam.am.model.PasswordPolicyEntity policy, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final com.soffid.iam.am.model.PasswordPolicyEntity policy, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (policy == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.am.model.PasswordPolicyEntity policy, com.soffid.iam.am.api.Password password) - policy cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.am.model.PasswordPolicyEntity policy, com.soffid.iam.am.api.Password password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckPolicy(policy, password)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PolicyCheckResult) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.checkPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.checkPolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PolicyCheckResult handleCheckPolicy(com.soffid.iam.am.model.PasswordPolicyEntity policy, com.soffid.iam.am.api.Password password) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.PolicyCheckResult checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password) - user cannot be null");
		}
		if (passwordDomain == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password) - passwordDomain cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckPolicy(user, passwordDomain, password)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PolicyCheckResult) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.checkPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.checkPolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PolicyCheckResult handleCheckPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.PolicyCheckResult checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean ignoreMinimumPeriod)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password, 
		final boolean ignoreMinimumPeriod)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean ignoreMinimumPeriod) - user cannot be null");
		}
		if (passwordDomain == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean ignoreMinimumPeriod) - passwordDomain cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean ignoreMinimumPeriod) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckPolicy(user, passwordDomain, password, ignoreMinimumPeriod)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PolicyCheckResult) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.checkPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.checkPolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PolicyCheckResult handleCheckPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean ignoreMinimumPeriod) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.PolicyCheckResult checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordPolicyEntity politica, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordPolicyEntity politica, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordPolicyEntity politica, com.soffid.iam.am.api.Password password) - user cannot be null");
		}
		if (politica == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordPolicyEntity politica, com.soffid.iam.am.api.Password password) - politica cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordPolicyEntity politica, com.soffid.iam.am.api.Password password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckPolicy(user, politica, password)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PolicyCheckResult) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.checkPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.checkPolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PolicyCheckResult handleCheckPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordPolicyEntity politica, com.soffid.iam.am.api.Password password) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#com.soffid.iam.am.api.PolicyCheckResult checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordPolicyEntity politica, com.soffid.iam.am.api.Password password, boolean ignoreMinimumPeriod)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordPolicyEntity politica, 
		final com.soffid.iam.am.api.Password password, 
		final boolean ignoreMinimumPeriod)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordPolicyEntity politica, com.soffid.iam.am.api.Password password, boolean ignoreMinimumPeriod) - user cannot be null");
		}
		if (politica == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordPolicyEntity politica, com.soffid.iam.am.api.Password password, boolean ignoreMinimumPeriod) - politica cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.impl.service.InternalPasswordService.checkPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordPolicyEntity politica, com.soffid.iam.am.api.Password password, boolean ignoreMinimumPeriod) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckPolicy(user, politica, password, ignoreMinimumPeriod)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PolicyCheckResult) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.checkPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.checkPolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PolicyCheckResult handleCheckPolicy(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordPolicyEntity politica, com.soffid.iam.am.api.Password password, boolean ignoreMinimumPeriod) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#java.lang.Long getLastPasswordIdForUser(java.lang.String user)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.Long getLastPasswordIdForUser(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Long com.soffid.iam.impl.service.InternalPasswordService.getLastPasswordIdForUser(java.lang.String user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetLastPasswordIdForUser(user)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Long) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.getLastPasswordIdForUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.getLastPasswordIdForUser", (Throwable) __r[1]);
	}

	protected abstract java.lang.Long handleGetLastPasswordIdForUser(java.lang.String user) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#java.lang.String getDefaultDispatcher()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String getDefaultDispatcher()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDefaultDispatcher()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.getDefaultDispatcher", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.getDefaultDispatcher", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetDefaultDispatcher() throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#java.lang.String getPolicyDescription(com.soffid.iam.am.model.PasswordPolicyEntity politica)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String getPolicyDescription(
		final com.soffid.iam.am.model.PasswordPolicyEntity politica)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (politica == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.impl.service.InternalPasswordService.getPolicyDescription(com.soffid.iam.am.model.PasswordPolicyEntity politica) - politica cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPolicyDescription(politica)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.getPolicyDescription", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.getPolicyDescription", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetPolicyDescription(com.soffid.iam.am.model.PasswordPolicyEntity politica) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#java.util.Calendar getPasswordExpiredDate(long user, long passwordDomain)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Calendar getPasswordExpiredDate(
		final long user, 
		final long passwordDomain)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPasswordExpiredDate(user, passwordDomain)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Calendar) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.getPasswordExpiredDate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.getPasswordExpiredDate", (Throwable) __r[1]);
	}

	protected abstract java.util.Calendar handleGetPasswordExpiredDate(long user, long passwordDomain) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#java.util.Collection<com.soffid.iam.am.model.PasswordDomainEntity> enumExpiredPasswords(com.soffid.iam.base.model.UserEntity usuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.model.PasswordDomainEntity> enumExpiredPasswords(
		final com.soffid.iam.base.model.UserEntity usuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuari == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.model.PasswordDomainEntity> com.soffid.iam.impl.service.InternalPasswordService.enumExpiredPasswords(com.soffid.iam.base.model.UserEntity usuari) - usuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleEnumExpiredPasswords(usuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.model.PasswordDomainEntity>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.enumExpiredPasswords", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.enumExpiredPasswords", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.model.PasswordDomainEntity> handleEnumExpiredPasswords(com.soffid.iam.base.model.UserEntity usuari) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#java.util.Collection<com.soffid.iam.am.api.PasswordStatus> getExpiredPasswords(java.util.Date desde, java.util.Date finsa, com.soffid.iam.base.model.UserTypeEntity tipusUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.PasswordStatus> getExpiredPasswords(
		final java.util.Date desde, 
		final java.util.Date finsa, 
		final com.soffid.iam.base.model.UserTypeEntity tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (desde == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.PasswordStatus> com.soffid.iam.impl.service.InternalPasswordService.getExpiredPasswords(java.util.Date desde, java.util.Date finsa, com.soffid.iam.base.model.UserTypeEntity tipusUsuari) - desde cannot be null");
		}
		if (finsa == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.PasswordStatus> com.soffid.iam.impl.service.InternalPasswordService.getExpiredPasswords(java.util.Date desde, java.util.Date finsa, com.soffid.iam.base.model.UserTypeEntity tipusUsuari) - finsa cannot be null");
		}
		if (tipusUsuari == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.PasswordStatus> com.soffid.iam.impl.service.InternalPasswordService.getExpiredPasswords(java.util.Date desde, java.util.Date finsa, com.soffid.iam.base.model.UserTypeEntity tipusUsuari) - tipusUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetExpiredPasswords(desde, finsa, tipusUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.PasswordStatus>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.getExpiredPasswords", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.getExpiredPasswords", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.PasswordStatus> handleGetExpiredPasswords(java.util.Date desde, java.util.Date finsa, com.soffid.iam.base.model.UserTypeEntity tipusUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void confirmAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void confirmAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.confirmAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password) - account cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.confirmAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleConfirmAccountPassword(account, password);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.confirmAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.confirmAccountPassword", (Throwable) __r[1]);
	}

	protected abstract void handleConfirmAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void confirmPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void confirmPassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.confirmPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password) - user cannot be null");
		}
		if (passwordDomain == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.confirmPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password) - passwordDomain cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.confirmPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleConfirmPassword(user, passwordDomain, password);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.confirmPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.confirmPassword", (Throwable) __r[1]);
	}

	protected abstract void handleConfirmPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void disableExpiredPassword()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void disableExpiredPassword()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDisableExpiredPassword();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.disableExpiredPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.disableExpiredPassword", (Throwable) __r[1]);
	}

	protected abstract void handleDisableExpiredPassword() throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void disableUntrustedPasswords()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void disableUntrustedPasswords()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDisableUntrustedPasswords();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.disableUntrustedPasswords", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.disableUntrustedPasswords", (Throwable) __r[1]);
	}

	protected abstract void handleDisableUntrustedPasswords() throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void storeAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void storeAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange, 
		final java.util.Date expirationDate)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate) - account cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStoreAccountPassword(account, password, mustChange, expirationDate);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.storeAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.storeAccountPassword", (Throwable) __r[1]);
	}

	protected abstract void handleStoreAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void storeAccountPassword(java.lang.String account, java.lang.String dispatcher, java.lang.String password, boolean mustChange, java.util.Date expirationDate)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void storeAccountPassword(
		final java.lang.String account, 
		final java.lang.String dispatcher, 
		final java.lang.String password, 
		final boolean mustChange, 
		final java.util.Date expirationDate)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null || account.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAccountPassword(java.lang.String account, java.lang.String dispatcher, java.lang.String password, boolean mustChange, java.util.Date expirationDate) - account cannot be null");
		}
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAccountPassword(java.lang.String account, java.lang.String dispatcher, java.lang.String password, boolean mustChange, java.util.Date expirationDate) - dispatcher cannot be null");
		}
		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAccountPassword(java.lang.String account, java.lang.String dispatcher, java.lang.String password, boolean mustChange, java.util.Date expirationDate) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStoreAccountPassword(account, dispatcher, password, mustChange, expirationDate);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.storeAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.storeAccountPassword", (Throwable) __r[1]);
	}

	protected abstract void handleStoreAccountPassword(java.lang.String account, java.lang.String dispatcher, java.lang.String password, boolean mustChange, java.util.Date expirationDate) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void storeAndForwardAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void storeAndForwardAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange, 
		final java.util.Date expirationDate)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAndForwardAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate) - account cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAndForwardAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStoreAndForwardAccountPassword(account, password, mustChange, expirationDate);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.storeAndForwardAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.storeAndForwardAccountPassword", (Throwable) __r[1]);
	}

	protected abstract void handleStoreAndForwardAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void storeAndForwardAccountPasswordById(long account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void storeAndForwardAccountPasswordById(
		final long account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange, 
		final java.util.Date expirationDate)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAndForwardAccountPasswordById(long account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStoreAndForwardAccountPasswordById(account, password, mustChange, expirationDate);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.storeAndForwardAccountPasswordById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.storeAndForwardAccountPasswordById", (Throwable) __r[1]);
	}

	protected abstract void handleStoreAndForwardAccountPasswordById(long account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void storeAndForwardPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void storeAndForwardPassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAndForwardPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) - user cannot be null");
		}
		if (passwordDomain == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAndForwardPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) - passwordDomain cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAndForwardPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStoreAndForwardPassword(user, passwordDomain, password, mustChange);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.storeAndForwardPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.storeAndForwardPassword", (Throwable) __r[1]);
	}

	protected abstract void handleStoreAndForwardPassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void storeAndForwardPasswordById(long user, long passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void storeAndForwardPasswordById(
		final long user, 
		final long passwordDomain, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAndForwardPasswordById(long user, long passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStoreAndForwardPasswordById(user, passwordDomain, password, mustChange);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.storeAndForwardPasswordById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.storeAndForwardPasswordById", (Throwable) __r[1]);
	}

	protected abstract void handleStoreAndForwardPasswordById(long user, long passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void storeAndSynchronizeAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void storeAndSynchronizeAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange, 
		final java.util.Date expirationDate)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAndSynchronizeAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate) - account cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAndSynchronizeAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStoreAndSynchronizeAccountPassword(account, password, mustChange, expirationDate);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.storeAndSynchronizeAccountPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.storeAndSynchronizeAccountPassword", (Throwable) __r[1]);
	}

	protected abstract void handleStoreAndSynchronizeAccountPassword(com.soffid.iam.base.model.AccountEntity account, com.soffid.iam.am.api.Password password, boolean mustChange, java.util.Date expirationDate) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void storeAndSynchronizePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void storeAndSynchronizePassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAndSynchronizePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) - user cannot be null");
		}
		if (passwordDomain == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAndSynchronizePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) - passwordDomain cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storeAndSynchronizePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStoreAndSynchronizePassword(user, passwordDomain, password, mustChange);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.storeAndSynchronizePassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.storeAndSynchronizePassword", (Throwable) __r[1]);
	}

	protected abstract void handleStoreAndSynchronizePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void storePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void storePassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) - user cannot be null");
		}
		if (passwordDomain == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) - passwordDomain cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStorePassword(user, passwordDomain, password, mustChange);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.storePassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.storePassword", (Throwable) __r[1]);
	}

	protected abstract void handleStorePassword(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, com.soffid.iam.am.api.Password password, boolean mustChange) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.InternalPasswordService#	 * @see com.soffid.iam.impl.service.InternalPasswordService#void storePassword(java.lang.String user, java.lang.String passwordDomain, java.lang.String password, boolean mustChange)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void storePassword(
		final java.lang.String user, 
		final java.lang.String passwordDomain, 
		final java.lang.String password, 
		final boolean mustChange)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storePassword(java.lang.String user, java.lang.String passwordDomain, java.lang.String password, boolean mustChange) - user cannot be null");
		}
		if (passwordDomain == null || passwordDomain.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storePassword(java.lang.String user, java.lang.String passwordDomain, java.lang.String password, boolean mustChange) - passwordDomain cannot be null");
		}
		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.InternalPasswordService.storePassword(java.lang.String user, java.lang.String passwordDomain, java.lang.String password, boolean mustChange) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleStorePassword(user, passwordDomain, password, mustChange);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.InternalPasswordService.class).
			warn ("Error on InternalPasswordService.storePassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on InternalPasswordService.storePassword", (Throwable) __r[1]);
	}

	protected abstract void handleStorePassword(java.lang.String user, java.lang.String passwordDomain, java.lang.String password, boolean mustChange) throws Exception;

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
