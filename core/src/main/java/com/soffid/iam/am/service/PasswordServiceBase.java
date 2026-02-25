//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.am.service.PasswordService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.am.service.PasswordService
 */
public abstract class PasswordServiceBase
	implements com.soffid.iam.am.service.PasswordService
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


	/**
	 * @see com.soffid.iam.am.service.PasswordService#	 * @see com.soffid.iam.am.service.PasswordService#boolean checkPassword(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean checkPassword(
		final java.lang.String account, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.am.api.Password password, 
		final boolean checkTrusted, 
		final boolean checkExpired)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null || account.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.PasswordService.checkPassword(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired) - account cannot be null");
		}
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.PasswordService.checkPassword(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired) - dispatcher cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.PasswordService.checkPassword(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckPassword(account, dispatcher, password, checkTrusted, checkExpired)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.PasswordService.class).
			warn ("Error on PasswordService.checkPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PasswordService.checkPassword", (Throwable) __r[1]);
	}

	protected abstract boolean handleCheckPassword(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password password, boolean checkTrusted, boolean checkExpired) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.PasswordService#	 * @see com.soffid.iam.am.service.PasswordService#boolean checkExpiredPassword(java.lang.String accoount, java.lang.String dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean checkExpiredPassword(
		final java.lang.String accoount, 
		final java.lang.String dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (accoount == null || accoount.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.PasswordService.checkExpiredPassword(java.lang.String accoount, java.lang.String dispatcher) - accoount cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckExpiredPassword(accoount, dispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.PasswordService.class).
			warn ("Error on PasswordService.checkExpiredPassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PasswordService.checkExpiredPassword", (Throwable) __r[1]);
	}

	protected abstract boolean handleCheckExpiredPassword(java.lang.String accoount, java.lang.String dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.PasswordService#	 * @see com.soffid.iam.am.service.PasswordService#boolean checkPin(java.lang.String user, java.lang.String pin)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean checkPin(
		final java.lang.String user, 
		final java.lang.String pin)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.PasswordService.checkPin(java.lang.String user, java.lang.String pin) - user cannot be null");
		}
		if (pin == null || pin.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.am.service.PasswordService.checkPin(java.lang.String user, java.lang.String pin) - pin cannot be null");
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.PasswordService.class).
			warn ("Error on PasswordService.checkPin", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PasswordService.checkPin", (Throwable) __r[1]);
	}

	protected abstract boolean handleCheckPin(java.lang.String user, java.lang.String pin) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.PasswordService#	 * @see com.soffid.iam.am.service.PasswordService#com.soffid.iam.am.api.PolicyCheckResult checkPolicy(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password password)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final java.lang.String account, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.am.api.Password password)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null || account.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.am.service.PasswordService.checkPolicy(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password password) - account cannot be null");
		}
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.am.service.PasswordService.checkPolicy(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password password) - dispatcher cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PolicyCheckResult com.soffid.iam.am.service.PasswordService.checkPolicy(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password password) - password cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCheckPolicy(account, dispatcher, password)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PolicyCheckResult) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.PasswordService.class).
			warn ("Error on PasswordService.checkPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PasswordService.checkPolicy", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PolicyCheckResult handleCheckPolicy(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password password) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.PasswordService#	 * @see com.soffid.iam.am.service.PasswordService#java.lang.String getDefaultDispatcher()
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
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.PasswordService.class).
			warn ("Error on PasswordService.getDefaultDispatcher", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PasswordService.getDefaultDispatcher", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetDefaultDispatcher() throws Exception;

	/**
	 * @see com.soffid.iam.am.service.PasswordService#	 * @see com.soffid.iam.am.service.PasswordService#java.lang.String getPolicyDescription(java.lang.String account, java.lang.String dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String getPolicyDescription(
		final java.lang.String account, 
		final java.lang.String dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null || account.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.am.service.PasswordService.getPolicyDescription(java.lang.String account, java.lang.String dispatcher) - account cannot be null");
		}
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.am.service.PasswordService.getPolicyDescription(java.lang.String account, java.lang.String dispatcher) - dispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPolicyDescription(account, dispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.PasswordService.class).
			warn ("Error on PasswordService.getPolicyDescription", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PasswordService.getPolicyDescription", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetPolicyDescription(java.lang.String account, java.lang.String dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.PasswordService#	 * @see com.soffid.iam.am.service.PasswordService#java.util.Calendar getPasswordExpiredDate(java.lang.String account, java.lang.String dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Calendar getPasswordExpiredDate(
		final java.lang.String account, 
		final java.lang.String dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null || account.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Calendar com.soffid.iam.am.service.PasswordService.getPasswordExpiredDate(java.lang.String account, java.lang.String dispatcher) - account cannot be null");
		}
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Calendar com.soffid.iam.am.service.PasswordService.getPasswordExpiredDate(java.lang.String account, java.lang.String dispatcher) - dispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPasswordExpiredDate(account, dispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Calendar) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.PasswordService.class).
			warn ("Error on PasswordService.getPasswordExpiredDate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PasswordService.getPasswordExpiredDate", (Throwable) __r[1]);
	}

	protected abstract java.util.Calendar handleGetPasswordExpiredDate(java.lang.String account, java.lang.String dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.PasswordService#	 * @see com.soffid.iam.am.service.PasswordService#void changePassword(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password oldPassword, com.soffid.iam.am.api.Password newPassword)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void changePassword(
		final java.lang.String account, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.am.api.Password oldPassword, 
		final com.soffid.iam.am.api.Password newPassword)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException, com.soffid.iam.exception.InvalidPasswordException
	{
		if (account == null || account.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.PasswordService.changePassword(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password oldPassword, com.soffid.iam.am.api.Password newPassword) - account cannot be null");
		}
		if (dispatcher == null || dispatcher.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.PasswordService.changePassword(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password oldPassword, com.soffid.iam.am.api.Password newPassword) - dispatcher cannot be null");
		}
		if (oldPassword == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.PasswordService.changePassword(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password oldPassword, com.soffid.iam.am.api.Password newPassword) - oldPassword cannot be null");
		}
		if (newPassword == null) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.PasswordService.changePassword(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password oldPassword, com.soffid.iam.am.api.Password newPassword) - newPassword cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleChangePassword(account, dispatcher, oldPassword, newPassword);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.BadPasswordException) 
			throw (com.soffid.iam.exception.BadPasswordException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InvalidPasswordException) 
			throw (com.soffid.iam.exception.InvalidPasswordException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.PasswordService.class).
			warn ("Error on PasswordService.changePassword", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PasswordService.changePassword", (Throwable) __r[1]);
	}

	protected abstract void handleChangePassword(java.lang.String account, java.lang.String dispatcher, com.soffid.iam.am.api.Password oldPassword, com.soffid.iam.am.api.Password newPassword) throws Exception;

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
