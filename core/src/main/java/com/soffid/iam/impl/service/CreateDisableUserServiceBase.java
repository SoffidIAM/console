//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.CreateDisableUserService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.CreateDisableUserService
 */
public abstract class CreateDisableUserServiceBase
	implements com.soffid.iam.impl.service.CreateDisableUserService
 {
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

	private com.soffid.iam.iga.model.MailDomainEntityDao mailDomainEntityDao;

	/**
	 * Sets reference to <code>mailDomainEntityDao</code>.
	 */
	public void setMailDomainEntityDao (com.soffid.iam.iga.model.MailDomainEntityDao mailDomainEntityDao) {
		this.mailDomainEntityDao = mailDomainEntityDao;
	}

	/**
	 * Gets reference to <code>mailDomainEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailDomainEntityDao getMailDomainEntityDao () {
		return mailDomainEntityDao;
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
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#com.soffid.iam.base.api.User disableUser(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User disableUser(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.impl.service.CreateDisableUserService.disableUser(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleDisableUser(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.disableUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.disableUser", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleDisableUser(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#com.soffid.iam.base.api.User findUserByShortName(java.lang.String nomCurt)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User findUserByShortName(
		final java.lang.String nomCurt)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomCurt == null || nomCurt.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.impl.service.CreateDisableUserService.findUserByShortName(java.lang.String nomCurt) - nomCurt cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserByShortName(nomCurt)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.findUserByShortName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.findUserByShortName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleFindUserByShortName(java.lang.String nomCurt) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#com.soffid.iam.base.api.User setServersToUser(java.lang.String codiUsuari, java.lang.String servidorPerfilId, java.lang.String servidorCorreuId, java.lang.String servidorHomeId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.User setServersToUser(
		final java.lang.String codiUsuari, 
		final java.lang.String servidorPerfilId, 
		final java.lang.String servidorCorreuId, 
		final java.lang.String servidorHomeId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.impl.service.CreateDisableUserService.setServersToUser(java.lang.String codiUsuari, java.lang.String servidorPerfilId, java.lang.String servidorCorreuId, java.lang.String servidorHomeId) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSetServersToUser(codiUsuari, servidorPerfilId, servidorCorreuId, servidorHomeId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.User) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.setServersToUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.setServersToUser", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.User handleSetServersToUser(java.lang.String codiUsuari, java.lang.String servidorPerfilId, java.lang.String servidorCorreuId, java.lang.String servidorHomeId) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#com.soffid.iam.iga.api.Group getSuperGroup(java.lang.String codiSubGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Group getSuperGroup(
		final java.lang.String codiSubGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiSubGrup == null || codiSubGrup.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.impl.service.CreateDisableUserService.getSuperGroup(java.lang.String codiSubGrup) - codiSubGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetSuperGroup(codiSubGrup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Group) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.getSuperGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.getSuperGroup", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Group handleGetSuperGroup(java.lang.String codiSubGrup) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#com.soffid.iam.iga.api.Role getAdministratorRoleByGroup(java.lang.String codiGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Role getAdministratorRoleByGroup(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Role com.soffid.iam.impl.service.CreateDisableUserService.getAdministratorRoleByGroup(java.lang.String codiGrup) - codiGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAdministratorRoleByGroup(codiGrup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Role) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.getAdministratorRoleByGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.getAdministratorRoleByGroup", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Role handleGetAdministratorRoleByGroup(java.lang.String codiGrup) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#java.lang.Boolean existShortName(java.lang.String nomCurt)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.Boolean existShortName(
		final java.lang.String nomCurt)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomCurt == null || nomCurt.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.Boolean com.soffid.iam.impl.service.CreateDisableUserService.existShortName(java.lang.String nomCurt) - nomCurt cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleExistShortName(nomCurt)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Boolean) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.existShortName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.existShortName", (Throwable) __r[1]);
	}

	protected abstract java.lang.Boolean handleExistShortName(java.lang.String nomCurt) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#java.lang.String setInitialPasswordToUser(java.lang.String codiUsuari, java.lang.String codiDominiContrasenyes)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String setInitialPasswordToUser(
		final java.lang.String codiUsuari, 
		final java.lang.String codiDominiContrasenyes)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.impl.service.CreateDisableUserService.setInitialPasswordToUser(java.lang.String codiUsuari, java.lang.String codiDominiContrasenyes) - codiUsuari cannot be null");
		}
		if (codiDominiContrasenyes == null || codiDominiContrasenyes.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.impl.service.CreateDisableUserService.setInitialPasswordToUser(java.lang.String codiUsuari, java.lang.String codiDominiContrasenyes) - codiDominiContrasenyes cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSetInitialPasswordToUser(codiUsuari, codiDominiContrasenyes)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.setInitialPasswordToUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.setInitialPasswordToUser", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleSetInitialPasswordToUser(java.lang.String codiUsuari, java.lang.String codiDominiContrasenyes) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsByUserCode(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsByUserCode(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.impl.service.CreateDisableUserService.findGroupsByUserCode(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupsByUserCode(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.findGroupsByUserCode", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.findGroupsByUserCode", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleFindGroupsByUserCode(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#java.util.Collection<com.soffid.iam.iga.api.Group> getManagedGroups()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> getManagedGroups()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetManagedGroups()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.getManagedGroups", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.getManagedGroups", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleGetManagedGroups() throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#java.util.Collection<com.soffid.iam.iga.api.Group> getManagedGroups(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> getManagedGroups(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.impl.service.CreateDisableUserService.getManagedGroups(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetManagedGroups(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.getManagedGroups", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.getManagedGroups", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleGetManagedGroups(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#java.util.Collection<java.lang.String> getContractTypesUserCreate()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.String> getContractTypesUserCreate()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetContractTypesUserCreate()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.getContractTypesUserCreate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.getContractTypesUserCreate", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.String> handleGetContractTypesUserCreate() throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#java.util.Collection<com.soffid.iam.iga.api.Group> getOUDependent(java.lang.String codiUnitatOrganitzativa)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> getOUDependent(
		final java.lang.String codiUnitatOrganitzativa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUnitatOrganitzativa == null || codiUnitatOrganitzativa.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.impl.service.CreateDisableUserService.getOUDependent(java.lang.String codiUnitatOrganitzativa) - codiUnitatOrganitzativa cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetOUDependent(codiUnitatOrganitzativa)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.getOUDependent", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.getOUDependent", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleGetOUDependent(java.lang.String codiUnitatOrganitzativa) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CreateDisableUserService#	 * @see com.soffid.iam.impl.service.CreateDisableUserService#java.util.Collection<com.soffid.iam.base.api.User> getUsersByNIF(java.lang.String nif)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.User> getUsersByNIF(
		final java.lang.String nif)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nif == null || nif.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.User> com.soffid.iam.impl.service.CreateDisableUserService.getUsersByNIF(java.lang.String nif) - nif cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUsersByNIF(nif)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.User>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CreateDisableUserService.class).
			warn ("Error on CreateDisableUserService.getUsersByNIF", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CreateDisableUserService.getUsersByNIF", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.User> handleGetUsersByNIF(java.lang.String nif) throws Exception;

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
