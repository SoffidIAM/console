//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.DomainService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.DomainService
 */
public abstract class DomainServiceBase
	implements com.soffid.iam.iga.service.DomainService
 {
	private com.soffid.iam.iga.model.ApplicationDomainEntityDao applicationDomainEntityDao;

	/**
	 * Sets reference to <code>applicationDomainEntityDao</code>.
	 */
	public void setApplicationDomainEntityDao (com.soffid.iam.iga.model.ApplicationDomainEntityDao applicationDomainEntityDao) {
		this.applicationDomainEntityDao = applicationDomainEntityDao;
	}

	/**
	 * Gets reference to <code>applicationDomainEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntityDao getApplicationDomainEntityDao () {
		return applicationDomainEntityDao;
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

	private com.soffid.iam.iga.model.DomainValueEntityDao domainValueEntityDao;

	/**
	 * Sets reference to <code>domainValueEntityDao</code>.
	 */
	public void setDomainValueEntityDao (com.soffid.iam.iga.model.DomainValueEntityDao domainValueEntityDao) {
		this.domainValueEntityDao = domainValueEntityDao;
	}

	/**
	 * Gets reference to <code>domainValueEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.DomainValueEntityDao getDomainValueEntityDao () {
		return domainValueEntityDao;
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
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#com.soffid.iam.iga.api.Domain create(com.soffid.iam.iga.api.Domain domini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Domain create(
		final com.soffid.iam.iga.api.Domain domini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (domini == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Domain com.soffid.iam.iga.service.DomainService.create(com.soffid.iam.iga.api.Domain domini) - domini cannot be null");
		}
		if (domini.getName() == null || domini.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Domain com.soffid.iam.iga.service.DomainService.create(com.soffid.iam.iga.api.Domain domini) - domini.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(domini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Domain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Domain handleCreate(com.soffid.iam.iga.api.Domain domini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#com.soffid.iam.iga.api.Domain findDomainByApplicationAndName(java.lang.String codiAplicacio, java.lang.String name)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Domain findDomainByApplicationAndName(
		final java.lang.String codiAplicacio, 
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Domain com.soffid.iam.iga.service.DomainService.findDomainByApplicationAndName(java.lang.String codiAplicacio, java.lang.String name) - codiAplicacio cannot be null");
		}
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Domain com.soffid.iam.iga.service.DomainService.findDomainByApplicationAndName(java.lang.String codiAplicacio, java.lang.String name) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDomainByApplicationAndName(codiAplicacio, name)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Domain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.findDomainByApplicationAndName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.findDomainByApplicationAndName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Domain handleFindDomainByApplicationAndName(java.lang.String codiAplicacio, java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#com.soffid.iam.iga.api.Domain findApplicationDomainByDomianNameAndApplicationName(java.lang.String nomDomini, java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Domain findApplicationDomainByDomianNameAndApplicationName(
		final java.lang.String nomDomini, 
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomDomini == null || nomDomini.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Domain com.soffid.iam.iga.service.DomainService.findApplicationDomainByDomianNameAndApplicationName(java.lang.String nomDomini, java.lang.String codiAplicacio) - nomDomini cannot be null");
		}
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Domain com.soffid.iam.iga.service.DomainService.findApplicationDomainByDomianNameAndApplicationName(java.lang.String nomDomini, java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationDomainByDomianNameAndApplicationName(nomDomini, codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Domain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.findApplicationDomainByDomianNameAndApplicationName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.findApplicationDomainByDomianNameAndApplicationName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Domain handleFindApplicationDomainByDomianNameAndApplicationName(java.lang.String nomDomini, java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#com.soffid.iam.iga.api.Domain findGroupsDomain()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Domain findGroupsDomain()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupsDomain()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Domain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.findGroupsDomain", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.findGroupsDomain", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Domain handleFindGroupsDomain() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#com.soffid.iam.iga.api.Domain findUserDomainGroup()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Domain findUserDomainGroup()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserDomainGroup()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Domain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.findUserDomainGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.findUserDomainGroup", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Domain handleFindUserDomainGroup() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#com.soffid.iam.iga.api.Domain update(com.soffid.iam.iga.api.Domain domini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Domain update(
		final com.soffid.iam.iga.api.Domain domini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (domini == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Domain com.soffid.iam.iga.service.DomainService.update(com.soffid.iam.iga.api.Domain domini) - domini cannot be null");
		}
		if (domini.getName() == null || domini.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Domain com.soffid.iam.iga.service.DomainService.update(com.soffid.iam.iga.api.Domain domini) - domini.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(domini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Domain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Domain handleUpdate(com.soffid.iam.iga.api.Domain domini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#com.soffid.iam.iga.api.DomainValue create(com.soffid.iam.iga.api.DomainValue valorDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.DomainValue create(
		final com.soffid.iam.iga.api.DomainValue valorDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (valorDomini == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.DomainValue com.soffid.iam.iga.service.DomainService.create(com.soffid.iam.iga.api.DomainValue valorDomini) - valorDomini cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(valorDomini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.DomainValue) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.DomainValue handleCreate(com.soffid.iam.iga.api.DomainValue valorDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#com.soffid.iam.iga.api.DomainValue findApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue(java.lang.String nomDomini, java.lang.String codiAplicacio, java.lang.String valor)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.DomainValue findApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue(
		final java.lang.String nomDomini, 
		final java.lang.String codiAplicacio, 
		final java.lang.String valor)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomDomini == null || nomDomini.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.DomainValue com.soffid.iam.iga.service.DomainService.findApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue(java.lang.String nomDomini, java.lang.String codiAplicacio, java.lang.String valor) - nomDomini cannot be null");
		}
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.DomainValue com.soffid.iam.iga.service.DomainService.findApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue(java.lang.String nomDomini, java.lang.String codiAplicacio, java.lang.String valor) - codiAplicacio cannot be null");
		}
		if (valor == null || valor.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.DomainValue com.soffid.iam.iga.service.DomainService.findApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue(java.lang.String nomDomini, java.lang.String codiAplicacio, java.lang.String valor) - valor cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue(nomDomini, codiAplicacio, valor)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.DomainValue) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.findApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.findApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.DomainValue handleFindApplicationDomainValueByDomainNameAndDomainApplicationNameAndValue(java.lang.String nomDomini, java.lang.String codiAplicacio, java.lang.String valor) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#com.soffid.iam.iga.api.DomainValue update(com.soffid.iam.iga.api.DomainValue valorDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.DomainValue update(
		final com.soffid.iam.iga.api.DomainValue valorDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (valorDomini == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.DomainValue com.soffid.iam.iga.service.DomainService.update(com.soffid.iam.iga.api.DomainValue valorDomini) - valorDomini cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(valorDomini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.DomainValue) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.DomainValue handleUpdate(com.soffid.iam.iga.api.DomainValue valorDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValues(com.soffid.zkdb.api.Query q)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValues(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> com.soffid.iam.iga.service.DomainService.findDomainValues(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDomainValues(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.findDomainValues", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.findDomainValues", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> handleFindDomainValues(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#java.util.Collection<com.soffid.iam.iga.api.Domain> findApplicationDomainsByApplicationName(java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Domain> findApplicationDomainsByApplicationName(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Domain> com.soffid.iam.iga.service.DomainService.findApplicationDomainsByApplicationName(java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindApplicationDomainsByApplicationName(codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Domain>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.findApplicationDomainsByApplicationName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.findApplicationDomainsByApplicationName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Domain> handleFindApplicationDomainsByApplicationName(java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#java.util.Collection<com.soffid.iam.iga.api.Domain> findDomainsByApplicationName(java.lang.String codiAplicacio)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Domain> findDomainsByApplicationName(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiAplicacio == null || codiAplicacio.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Domain> com.soffid.iam.iga.service.DomainService.findDomainsByApplicationName(java.lang.String codiAplicacio) - codiAplicacio cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDomainsByApplicationName(codiAplicacio)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Domain>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.findDomainsByApplicationName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.findDomainsByApplicationName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Domain> handleFindDomainsByApplicationName(java.lang.String codiAplicacio) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#java.util.List<com.soffid.iam.iga.api.DomainValue> findDomainValuesByDomain(com.soffid.iam.iga.api.Domain domini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.iga.api.DomainValue> findDomainValuesByDomain(
		final com.soffid.iam.iga.api.Domain domini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (domini == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.iga.api.DomainValue> com.soffid.iam.iga.service.DomainService.findDomainValuesByDomain(com.soffid.iam.iga.api.Domain domini) - domini cannot be null");
		}
		if (domini.getName() == null || domini.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.iga.api.DomainValue> com.soffid.iam.iga.service.DomainService.findDomainValuesByDomain(com.soffid.iam.iga.api.Domain domini) - domini.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDomainValuesByDomain(domini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.iga.api.DomainValue>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.findDomainValuesByDomain", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.findDomainValuesByDomain", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.iga.api.DomainValue> handleFindDomainValuesByDomain(com.soffid.iam.iga.api.Domain domini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#void delete(com.soffid.iam.iga.api.Domain domini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.Domain domini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (domini == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DomainService.delete(com.soffid.iam.iga.api.Domain domini) - domini cannot be null");
		}
		if (domini.getName() == null || domini.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DomainService.delete(com.soffid.iam.iga.api.Domain domini) - domini.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(domini);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.Domain domini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.DomainService#	 * @see com.soffid.iam.iga.service.DomainService#void delete(com.soffid.iam.iga.api.DomainValue valorDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.DomainValue valorDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (valorDomini == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.DomainService.delete(com.soffid.iam.iga.api.DomainValue valorDomini) - valorDomini cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(valorDomini);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.DomainService.class).
			warn ("Error on DomainService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DomainService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.DomainValue valorDomini) throws Exception;

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
