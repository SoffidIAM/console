//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.EntitlementDelegationService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.EntitlementDelegationService
 */
public abstract class EntitlementDelegationServiceBase
	implements com.soffid.iam.iga.service.EntitlementDelegationService
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
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#com.soffid.iam.iga.api.RoleAccount acceptDelegation(com.soffid.iam.iga.api.RoleAccount ra)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.RoleAccount acceptDelegation(
		final com.soffid.iam.iga.api.RoleAccount ra)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (ra == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleAccount com.soffid.iam.iga.service.EntitlementDelegationService.acceptDelegation(com.soffid.iam.iga.api.RoleAccount ra) - ra cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleAcceptDelegation(ra)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.RoleAccount) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.EntitlementDelegationService.class).
			warn ("Error on EntitlementDelegationService.acceptDelegation", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntitlementDelegationService.acceptDelegation", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.RoleAccount handleAcceptDelegation(com.soffid.iam.iga.api.RoleAccount ra) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#com.soffid.iam.iga.api.RoleAccount cancelDelegation(com.soffid.iam.iga.api.RoleAccount rolAccount)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.RoleAccount cancelDelegation(
		final com.soffid.iam.iga.api.RoleAccount rolAccount)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (rolAccount == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleAccount com.soffid.iam.iga.service.EntitlementDelegationService.cancelDelegation(com.soffid.iam.iga.api.RoleAccount rolAccount) - rolAccount cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCancelDelegation(rolAccount)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.RoleAccount) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.EntitlementDelegationService.class).
			warn ("Error on EntitlementDelegationService.cancelDelegation", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntitlementDelegationService.cancelDelegation", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.RoleAccount handleCancelDelegation(com.soffid.iam.iga.api.RoleAccount rolAccount) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#com.soffid.iam.iga.api.RoleAccount delegate(com.soffid.iam.iga.api.RoleAccount rolAccount, java.lang.String user, java.lang.String account, java.util.Date since, java.util.Date until)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.RoleAccount delegate(
		final com.soffid.iam.iga.api.RoleAccount rolAccount, 
		final java.lang.String user, 
		final java.lang.String account, 
		final java.util.Date since, 
		final java.util.Date until)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (rolAccount == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleAccount com.soffid.iam.iga.service.EntitlementDelegationService.delegate(com.soffid.iam.iga.api.RoleAccount rolAccount, java.lang.String user, java.lang.String account, java.util.Date since, java.util.Date until) - rolAccount cannot be null");
		}
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleAccount com.soffid.iam.iga.service.EntitlementDelegationService.delegate(com.soffid.iam.iga.api.RoleAccount rolAccount, java.lang.String user, java.lang.String account, java.util.Date since, java.util.Date until) - user cannot be null");
		}
		if (since == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.RoleAccount com.soffid.iam.iga.service.EntitlementDelegationService.delegate(com.soffid.iam.iga.api.RoleAccount rolAccount, java.lang.String user, java.lang.String account, java.util.Date since, java.util.Date until) - since cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDelegate(rolAccount, user, account, since, until)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.RoleAccount) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.EntitlementDelegationService.class).
			warn ("Error on EntitlementDelegationService.delegate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntitlementDelegationService.delegate", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.RoleAccount handleDelegate(com.soffid.iam.iga.api.RoleAccount rolAccount, java.lang.String user, java.lang.String account, java.util.Date since, java.util.Date until) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#java.util.List<java.lang.String> findAccountsToDelegate(com.soffid.iam.iga.api.RoleAccount rolAccount, java.lang.String user)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<java.lang.String> findAccountsToDelegate(
		final com.soffid.iam.iga.api.RoleAccount rolAccount, 
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (rolAccount == null) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.iga.service.EntitlementDelegationService.findAccountsToDelegate(com.soffid.iam.iga.api.RoleAccount rolAccount, java.lang.String user) - rolAccount cannot be null");
		}
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.iga.service.EntitlementDelegationService.findAccountsToDelegate(com.soffid.iam.iga.api.RoleAccount rolAccount, java.lang.String user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAccountsToDelegate(rolAccount, user)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.EntitlementDelegationService.class).
			warn ("Error on EntitlementDelegationService.findAccountsToDelegate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntitlementDelegationService.findAccountsToDelegate", (Throwable) __r[1]);
	}

	protected abstract java.util.List<java.lang.String> handleFindAccountsToDelegate(com.soffid.iam.iga.api.RoleAccount rolAccount, java.lang.String user) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#java.util.List<com.soffid.iam.iga.api.RoleAccount> findActiveDelegations()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.iga.api.RoleAccount> findActiveDelegations()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindActiveDelegations()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.EntitlementDelegationService.class).
			warn ("Error on EntitlementDelegationService.findActiveDelegations", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntitlementDelegationService.findActiveDelegations", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.iga.api.RoleAccount> handleFindActiveDelegations() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#java.util.List<com.soffid.iam.iga.api.RoleAccount> findDelegationsToAccept()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.iga.api.RoleAccount> findDelegationsToAccept()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDelegationsToAccept()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.EntitlementDelegationService.class).
			warn ("Error on EntitlementDelegationService.findDelegationsToAccept", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntitlementDelegationService.findDelegationsToAccept", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.iga.api.RoleAccount> handleFindDelegationsToAccept() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#	 * @see com.soffid.iam.iga.service.EntitlementDelegationService#void revertExpiredDelegations()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void revertExpiredDelegations()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRevertExpiredDelegations();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.EntitlementDelegationService.class).
			warn ("Error on EntitlementDelegationService.revertExpiredDelegations", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on EntitlementDelegationService.revertExpiredDelegations", (Throwable) __r[1]);
	}

	protected abstract void handleRevertExpiredDelegations() throws Exception;

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
