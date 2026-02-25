//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.base.service.AuthorizationService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.base.service.AuthorizationService
 */
public abstract class AuthorizationServiceBase
	implements com.soffid.iam.base.service.AuthorizationService
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

	private com.soffid.iam.am.service.PasswordService passwordService;

	/**
	 * Sets reference to <code>passwordService</code>.
	 */
	public void setPasswordService (com.soffid.iam.am.service.PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	/**
	 * Gets reference to <code>passwordService</code>.
	 */
	public com.soffid.iam.am.service.PasswordService getPasswordService () {
		return passwordService;
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
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#boolean hasPermission(java.lang.String action, java.lang.Object object)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean hasPermission(
		final java.lang.String action, 
		final java.lang.Object object)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (action == null || action.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.base.service.AuthorizationService.hasPermission(java.lang.String action, java.lang.Object object) - action cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleHasPermission(action, object)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.hasPermission", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.hasPermission", (Throwable) __r[1]);
	}

	protected abstract boolean handleHasPermission(java.lang.String action, java.lang.Object object) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#com.soffid.iam.base.api.AuthorizationRole create(com.soffid.iam.base.api.AuthorizationRole autoritzacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AuthorizationRole create(
		final com.soffid.iam.base.api.AuthorizationRole autoritzacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (autoritzacio == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AuthorizationRole com.soffid.iam.base.service.AuthorizationService.create(com.soffid.iam.base.api.AuthorizationRole autoritzacio) - autoritzacio cannot be null");
		}
		if (autoritzacio.getAuthorization() == null || autoritzacio.getAuthorization().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AuthorizationRole com.soffid.iam.base.service.AuthorizationService.create(com.soffid.iam.base.api.AuthorizationRole autoritzacio) - autoritzacio.authorization cannot be null");
		}
		if (autoritzacio.getRole() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AuthorizationRole com.soffid.iam.base.service.AuthorizationService.create(com.soffid.iam.base.api.AuthorizationRole autoritzacio) - autoritzacio.role cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(autoritzacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.AuthorizationRole) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AuthorizationRole handleCreate(com.soffid.iam.base.api.AuthorizationRole autoritzacio) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#com.soffid.iam.common.security.SoffidPrincipal getCurrentPrincipal()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.common.security.SoffidPrincipal getCurrentPrincipal()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetCurrentPrincipal()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.common.security.SoffidPrincipal) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getCurrentPrincipal", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getCurrentPrincipal", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.common.security.SoffidPrincipal handleGetCurrentPrincipal() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.lang.String[] getUserAuthorizationString(java.lang.String codiAutoritzacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getUserAuthorizationString(
		final java.lang.String codiAutoritzacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAutoritzacio == null || codiAutoritzacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.base.service.AuthorizationService.getUserAuthorizationString(java.lang.String codiAutoritzacio) - codiAutoritzacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserAuthorizationString(codiAutoritzacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getUserAuthorizationString", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getUserAuthorizationString", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleGetUserAuthorizationString(java.lang.String codiAutoritzacio) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.lang.String[] getUserAuthorizationString(java.lang.String athorizationName, java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getUserAuthorizationString(
		final java.lang.String athorizationName, 
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (athorizationName == null || athorizationName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.base.service.AuthorizationService.getUserAuthorizationString(java.lang.String athorizationName, java.lang.String userName) - athorizationName cannot be null");
		}
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.base.service.AuthorizationService.getUserAuthorizationString(java.lang.String athorizationName, java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserAuthorizationString(athorizationName, userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getUserAuthorizationString", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getUserAuthorizationString", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleGetUserAuthorizationString(java.lang.String athorizationName, java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.lang.String[] getUserAuthorizationsString()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getUserAuthorizationsString()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserAuthorizationsString()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getUserAuthorizationsString", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getUserAuthorizationsString", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleGetUserAuthorizationsString() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.lang.String[] getUserAuthorizationsString(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getUserAuthorizationsString(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.base.service.AuthorizationService.getUserAuthorizationsString(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserAuthorizationsString(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getUserAuthorizationsString", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getUserAuthorizationsString", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleGetUserAuthorizationsString(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.lang.String[] getUserAuthorizationsString(java.lang.String userName, java.util.Map<java.lang.String,java.lang.String> loginProperties)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getUserAuthorizationsString(
		final java.lang.String userName, 
		final java.util.Map<java.lang.String,java.lang.String> loginProperties)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.base.service.AuthorizationService.getUserAuthorizationsString(java.lang.String userName, java.util.Map<java.lang.String,java.lang.String> loginProperties) - userName cannot be null");
		}
		if (loginProperties == null) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.base.service.AuthorizationService.getUserAuthorizationsString(java.lang.String userName, java.util.Map<java.lang.String,java.lang.String> loginProperties) - loginProperties cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserAuthorizationsString(userName, loginProperties)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getUserAuthorizationsString", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getUserAuthorizationsString", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleGetUserAuthorizationsString(java.lang.String userName, java.util.Map<java.lang.String,java.lang.String> loginProperties) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.lang.String[] getUserGroupAuthorizationString(java.lang.String userName, java.lang.String holderGroup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String[] getUserGroupAuthorizationString(
		final java.lang.String userName, 
		final java.lang.String holderGroup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.base.service.AuthorizationService.getUserGroupAuthorizationString(java.lang.String userName, java.lang.String holderGroup) - userName cannot be null");
		}
		if (holderGroup == null || holderGroup.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String[] com.soffid.iam.base.service.AuthorizationService.getUserGroupAuthorizationString(java.lang.String userName, java.lang.String holderGroup) - holderGroup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserGroupAuthorizationString(userName, holderGroup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getUserGroupAuthorizationString", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getUserGroupAuthorizationString", (Throwable) __r[1]);
	}

	protected abstract java.lang.String[] handleGetUserGroupAuthorizationString(java.lang.String userName, java.lang.String holderGroup) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection findAuthorizations(java.lang.String ambit, java.lang.String descripcio, java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection findAuthorizations(
		final java.lang.String ambit, 
		final java.lang.String descripcio, 
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAuthorizations(ambit, descripcio, codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.findAuthorizations", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.findAuthorizations", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection handleFindAuthorizations(java.lang.String ambit, java.lang.String descripcio, java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getDescriptionUserAuthorizations()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getDescriptionUserAuthorizations()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDescriptionUserAuthorizations()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AuthorizationRole>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getDescriptionUserAuthorizations", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getDescriptionUserAuthorizations", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> handleGetDescriptionUserAuthorizations() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getDescriptionUserAuthorizations(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getDescriptionUserAuthorizations(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> com.soffid.iam.base.service.AuthorizationService.getDescriptionUserAuthorizations(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDescriptionUserAuthorizations(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AuthorizationRole>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getDescriptionUserAuthorizations", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getDescriptionUserAuthorizations", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> handleGetDescriptionUserAuthorizations(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection<java.lang.Object> getAuthorizationInfo(java.lang.String autoritzacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.Object> getAuthorizationInfo(
		final java.lang.String autoritzacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (autoritzacio == null || autoritzacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.Object> com.soffid.iam.base.service.AuthorizationService.getAuthorizationInfo(java.lang.String autoritzacio) - autoritzacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAuthorizationInfo(autoritzacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.Object>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getAuthorizationInfo", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getAuthorizationInfo", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.Object> handleGetAuthorizationInfo(java.lang.String autoritzacio) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getAuthorizationRoles(java.lang.String authorization)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getAuthorizationRoles(
		final java.lang.String authorization)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (authorization == null || authorization.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> com.soffid.iam.base.service.AuthorizationService.getAuthorizationRoles(java.lang.String authorization) - authorization cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAuthorizationRoles(authorization)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AuthorizationRole>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getAuthorizationRoles", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getAuthorizationRoles", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> handleGetAuthorizationRoles(java.lang.String authorization) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorization(java.lang.String codiAutoritzacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorization(
		final java.lang.String codiAutoritzacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAutoritzacio == null || codiAutoritzacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> com.soffid.iam.base.service.AuthorizationService.getUserAuthorization(java.lang.String codiAutoritzacio) - codiAutoritzacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserAuthorization(codiAutoritzacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AuthorizationRole>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getUserAuthorization", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getUserAuthorization", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> handleGetUserAuthorization(java.lang.String codiAutoritzacio) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorization(java.lang.String codiAutoritzacio, java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorization(
		final java.lang.String codiAutoritzacio, 
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAutoritzacio == null || codiAutoritzacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> com.soffid.iam.base.service.AuthorizationService.getUserAuthorization(java.lang.String codiAutoritzacio, java.lang.String codiUsuari) - codiAutoritzacio cannot be null");
		}
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> com.soffid.iam.base.service.AuthorizationService.getUserAuthorization(java.lang.String codiAutoritzacio, java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserAuthorization(codiAutoritzacio, codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AuthorizationRole>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getUserAuthorization", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getUserAuthorization", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> handleGetUserAuthorization(java.lang.String codiAutoritzacio, java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorizations()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorizations()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserAuthorizations()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.AuthorizationRole>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getUserAuthorizations", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getUserAuthorizations", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> handleGetUserAuthorizations() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.Collection getUserAuthorizations(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection getUserAuthorizations(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection com.soffid.iam.base.service.AuthorizationService.getUserAuthorizations(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserAuthorizations(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getUserAuthorizations", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getUserAuthorizations", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection handleGetUserAuthorizations(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#java.util.List getScopeList()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List getScopeList()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetScopeList()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.getScopeList", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.getScopeList", (Throwable) __r[1]);
	}

	protected abstract java.util.List handleGetScopeList() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AuthorizationService#	 * @see com.soffid.iam.base.service.AuthorizationService#void delete(com.soffid.iam.base.api.AuthorizationRole authorization)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.base.api.AuthorizationRole authorization)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (authorization == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AuthorizationService.delete(com.soffid.iam.base.api.AuthorizationRole authorization) - authorization cannot be null");
		}
		if (authorization.getAuthorization() == null || authorization.getAuthorization().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AuthorizationService.delete(com.soffid.iam.base.api.AuthorizationRole authorization) - authorization.authorization cannot be null");
		}
		if (authorization.getRole() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AuthorizationService.delete(com.soffid.iam.base.api.AuthorizationRole authorization) - authorization.role cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(authorization);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AuthorizationService.class).
			warn ("Error on AuthorizationService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.base.api.AuthorizationRole authorization) throws Exception;

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
