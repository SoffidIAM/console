//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.AuthorizationInformationService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.AuthorizationInformationService
 */
public abstract class AuthorizationInformationServiceBase
	implements com.soffid.iam.impl.service.AuthorizationInformationService
 {
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
	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#boolean interventionNeeded(java.lang.String codiAplicacio, java.lang.String[] codisRols)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean interventionNeeded(
		final java.lang.String codiAplicacio, 
		final java.lang.String[] codisRols)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleInterventionNeeded(codiAplicacio, codisRols)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AuthorizationInformationService.class).
			warn ("Error on AuthorizationInformationService.interventionNeeded", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationInformationService.interventionNeeded", (Throwable) __r[1]);
	}

	protected abstract boolean handleInterventionNeeded(java.lang.String codiAplicacio, java.lang.String[] codisRols) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationCode(java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationCode(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.InformationSystem com.soffid.iam.impl.service.AuthorizationInformationService.findApplicationByApplicationCode(java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationByApplicationCode(codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.InformationSystem) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AuthorizationInformationService.class).
			warn ("Error on AuthorizationInformationService.findApplicationByApplicationCode", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationInformationService.findApplicationByApplicationCode", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.InformationSystem handleFindApplicationByApplicationCode(java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#com.soffid.iam.iga.api.Role getSystemsRoles(java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Role getSystemsRoles(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.impl.service.AuthorizationInformationService.getSystemsRoles(java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetSystemsRoles(codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Role) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AuthorizationInformationService.class).
			warn ("Error on AuthorizationInformationService.getSystemsRoles", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationInformationService.getSystemsRoles", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Role handleGetSystemsRoles(java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#java.lang.Boolean isApplicationManager(java.lang.String codiUsuari, java.lang.String codiApliacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean isApplicationManager(
		final java.lang.String codiUsuari, 
		final java.lang.String codiApliacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Boolean com.soffid.iam.impl.service.AuthorizationInformationService.isApplicationManager(java.lang.String codiUsuari, java.lang.String codiApliacio) - codiUsuari cannot be null");
		}
		if (codiApliacio == null || codiApliacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Boolean com.soffid.iam.impl.service.AuthorizationInformationService.isApplicationManager(java.lang.String codiUsuari, java.lang.String codiApliacio) - codiApliacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsApplicationManager(codiUsuari, codiApliacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Boolean) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AuthorizationInformationService.class).
			warn ("Error on AuthorizationInformationService.isApplicationManager", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationInformationService.isApplicationManager", (Throwable) __r[1]);
	}

	protected abstract java.lang.Boolean handleIsApplicationManager(java.lang.String codiUsuari, java.lang.String codiApliacio) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#java.util.Collection<com.soffid.iam.base.api.User> findApplicationManagersByApplicationCode(java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.User> findApplicationManagersByApplicationCode(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.User> com.soffid.iam.impl.service.AuthorizationInformationService.findApplicationManagersByApplicationCode(java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationManagersByApplicationCode(codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.User>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AuthorizationInformationService.class).
			warn ("Error on AuthorizationInformationService.findApplicationManagersByApplicationCode", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationInformationService.findApplicationManagersByApplicationCode", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.User> handleFindApplicationManagersByApplicationCode(java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#java.util.Collection<com.soffid.iam.iga.api.InformationSystem> findManagedApplicationsByUserCode(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.InformationSystem> findManagedApplicationsByUserCode(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.InformationSystem> com.soffid.iam.impl.service.AuthorizationInformationService.findManagedApplicationsByUserCode(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindManagedApplicationsByUserCode(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.InformationSystem>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AuthorizationInformationService.class).
			warn ("Error on AuthorizationInformationService.findManagedApplicationsByUserCode", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationInformationService.findManagedApplicationsByUserCode", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.InformationSystem> handleFindManagedApplicationsByUserCode(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationCode(java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationCode(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.impl.service.AuthorizationInformationService.findRolesByApplicationCode(java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRolesByApplicationCode(codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AuthorizationInformationService.class).
			warn ("Error on AuthorizationInformationService.findRolesByApplicationCode", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationInformationService.findRolesByApplicationCode", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleFindRolesByApplicationCode(java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationCodeUnrestricted(java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationCodeUnrestricted(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.impl.service.AuthorizationInformationService.findRolesByApplicationCodeUnrestricted(java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRolesByApplicationCodeUnrestricted(codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AuthorizationInformationService.class).
			warn ("Error on AuthorizationInformationService.findRolesByApplicationCodeUnrestricted", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationInformationService.findRolesByApplicationCodeUnrestricted", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleFindRolesByApplicationCodeUnrestricted(java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByUserCode(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByUserCode(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.impl.service.AuthorizationInformationService.findRolesByUserCode(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRolesByUserCode(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AuthorizationInformationService.class).
			warn ("Error on AuthorizationInformationService.findRolesByUserCode", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationInformationService.findRolesByUserCode", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleFindRolesByUserCode(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#java.util.Collection<com.soffid.iam.iga.api.InformationSystem> getApplicationsByUserCode(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.InformationSystem> getApplicationsByUserCode(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.InformationSystem> com.soffid.iam.impl.service.AuthorizationInformationService.getApplicationsByUserCode(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetApplicationsByUserCode(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.InformationSystem>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AuthorizationInformationService.class).
			warn ("Error on AuthorizationInformationService.getApplicationsByUserCode", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationInformationService.getApplicationsByUserCode", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.InformationSystem> handleGetApplicationsByUserCode(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#java.util.Collection<com.soffid.iam.iga.api.Role> getApplicationRolesByUserCodeAndApplicationCode(java.lang.String codiUsuari, java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> getApplicationRolesByUserCodeAndApplicationCode(
		final java.lang.String codiUsuari, 
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.impl.service.AuthorizationInformationService.getApplicationRolesByUserCodeAndApplicationCode(java.lang.String codiUsuari, java.lang.String codiAplicacio) - codiUsuari cannot be null");
		}
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.impl.service.AuthorizationInformationService.getApplicationRolesByUserCodeAndApplicationCode(java.lang.String codiUsuari, java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetApplicationRolesByUserCodeAndApplicationCode(codiUsuari, codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AuthorizationInformationService.class).
			warn ("Error on AuthorizationInformationService.getApplicationRolesByUserCodeAndApplicationCode", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationInformationService.getApplicationRolesByUserCodeAndApplicationCode", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleGetApplicationRolesByUserCodeAndApplicationCode(java.lang.String codiUsuari, java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#	 * @see com.soffid.iam.impl.service.AuthorizationInformationService#java.util.Collection<com.soffid.iam.iga.api.Role> getRolesByUserCode(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> getRolesByUserCode(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.impl.service.AuthorizationInformationService.getRolesByUserCode(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetRolesByUserCode(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AuthorizationInformationService.class).
			warn ("Error on AuthorizationInformationService.getRolesByUserCode", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuthorizationInformationService.getRolesByUserCode", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleGetRolesByUserCode(java.lang.String codiUsuari) throws Exception;

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
