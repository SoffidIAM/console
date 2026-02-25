//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.ACLService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.ACLService
 */
public abstract class ACLServiceBase
	implements com.soffid.iam.impl.service.ACLService
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
	 * @see com.soffid.iam.impl.service.ACLService#	 * @see com.soffid.iam.impl.service.ACLService#boolean isAccountIncluded(long userId, com.soffid.iam.iga.api.AccessControlList acl)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isAccountIncluded(
		final long userId, 
		final com.soffid.iam.iga.api.AccessControlList acl)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (acl == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.ACLService.isAccountIncluded(long userId, com.soffid.iam.iga.api.AccessControlList acl) - acl cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsAccountIncluded(userId, acl)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ACLService.class).
			warn ("Error on ACLService.isAccountIncluded", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ACLService.isAccountIncluded", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsAccountIncluded(long userId, com.soffid.iam.iga.api.AccessControlList acl) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.ACLService#	 * @see com.soffid.iam.impl.service.ACLService#boolean isCurrentUserIncluded(com.soffid.iam.iga.api.AccessControlList acl)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isCurrentUserIncluded(
		final com.soffid.iam.iga.api.AccessControlList acl)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (acl == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.ACLService.isCurrentUserIncluded(com.soffid.iam.iga.api.AccessControlList acl) - acl cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsCurrentUserIncluded(acl)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ACLService.class).
			warn ("Error on ACLService.isCurrentUserIncluded", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ACLService.isCurrentUserIncluded", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsCurrentUserIncluded(com.soffid.iam.iga.api.AccessControlList acl) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.ACLService#	 * @see com.soffid.iam.impl.service.ACLService#boolean isUserIncluded(long userId, com.soffid.iam.iga.api.AccessControlList acl)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isUserIncluded(
		final long userId, 
		final com.soffid.iam.iga.api.AccessControlList acl)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (acl == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.ACLService.isUserIncluded(long userId, com.soffid.iam.iga.api.AccessControlList acl) - acl cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsUserIncluded(userId, acl)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ACLService.class).
			warn ("Error on ACLService.isUserIncluded", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ACLService.isUserIncluded", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsUserIncluded(long userId, com.soffid.iam.iga.api.AccessControlList acl) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.ACLService#	 * @see com.soffid.iam.impl.service.ACLService#com.soffid.iam.iga.api.AccessControlList expandACL(com.soffid.iam.iga.api.AccessControlList acl)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.AccessControlList expandACL(
		final com.soffid.iam.iga.api.AccessControlList acl)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (acl == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.AccessControlList com.soffid.iam.impl.service.ACLService.expandACL(com.soffid.iam.iga.api.AccessControlList acl) - acl cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleExpandACL(acl)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.AccessControlList) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ACLService.class).
			warn ("Error on ACLService.expandACL", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ACLService.expandACL", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.AccessControlList handleExpandACL(com.soffid.iam.iga.api.AccessControlList acl) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.ACLService#	 * @see com.soffid.iam.impl.service.ACLService#com.soffid.iam.iga.api.AccessControlList expandUser(long userId)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.AccessControlList expandUser(
		final long userId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleExpandUser(userId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.AccessControlList) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ACLService.class).
			warn ("Error on ACLService.expandUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ACLService.expandUser", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.AccessControlList handleExpandUser(long userId) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.ACLService#	 * @see com.soffid.iam.impl.service.ACLService#java.util.Collection<java.lang.String> expandACLAccounts(com.soffid.iam.iga.api.AccessControlList acl)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.String> expandACLAccounts(
		final com.soffid.iam.iga.api.AccessControlList acl)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (acl == null) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.impl.service.ACLService.expandACLAccounts(com.soffid.iam.iga.api.AccessControlList acl) - acl cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleExpandACLAccounts(acl)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ACLService.class).
			warn ("Error on ACLService.expandACLAccounts", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ACLService.expandACLAccounts", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.String> handleExpandACLAccounts(com.soffid.iam.iga.api.AccessControlList acl) throws Exception;

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
