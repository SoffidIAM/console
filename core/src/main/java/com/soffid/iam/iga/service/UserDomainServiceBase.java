//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.UserDomainService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.UserDomainService
 */
public abstract class UserDomainServiceBase
	implements com.soffid.iam.iga.service.UserDomainService
 {
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

	private com.soffid.iam.am.model.ForbiddenWordEntityDao forbiddenWordEntityDao;

	/**
	 * Sets reference to <code>forbiddenWordEntityDao</code>.
	 */
	public void setForbiddenWordEntityDao (com.soffid.iam.am.model.ForbiddenWordEntityDao forbiddenWordEntityDao) {
		this.forbiddenWordEntityDao = forbiddenWordEntityDao;
	}

	/**
	 * Gets reference to <code>forbiddenWordEntityDao</code>.
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntityDao getForbiddenWordEntityDao () {
		return forbiddenWordEntityDao;
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

	private com.soffid.iam.am.model.PolicyForbiddenWordEntityDao policyForbiddenWordEntityDao;

	/**
	 * Sets reference to <code>policyForbiddenWordEntityDao</code>.
	 */
	public void setPolicyForbiddenWordEntityDao (com.soffid.iam.am.model.PolicyForbiddenWordEntityDao policyForbiddenWordEntityDao) {
		this.policyForbiddenWordEntityDao = policyForbiddenWordEntityDao;
	}

	/**
	 * Gets reference to <code>policyForbiddenWordEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PolicyForbiddenWordEntityDao getPolicyForbiddenWordEntityDao () {
		return policyForbiddenWordEntityDao;
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
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.ForbiddenWord create(com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.ForbiddenWord create(
		final com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (paraulaProhibida == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.ForbiddenWord com.soffid.iam.iga.service.UserDomainService.create(com.soffid.iam.am.api.ForbiddenWord paraulaProhibida) - paraulaProhibida cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(paraulaProhibida)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.ForbiddenWord) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.ForbiddenWord handleCreate(com.soffid.iam.am.api.ForbiddenWord paraulaProhibida) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.ForbiddenWord update(com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.ForbiddenWord update(
		final com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (paraulaProhibida == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.ForbiddenWord com.soffid.iam.iga.service.UserDomainService.update(com.soffid.iam.am.api.ForbiddenWord paraulaProhibida) - paraulaProhibida cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(paraulaProhibida)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.ForbiddenWord) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.ForbiddenWord handleUpdate(com.soffid.iam.am.api.ForbiddenWord paraulaProhibida) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordDomain create(com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordDomain create(
		final com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dominiContrasenya == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordDomain com.soffid.iam.iga.service.UserDomainService.create(com.soffid.iam.am.api.PasswordDomain dominiContrasenya) - dominiContrasenya cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(dominiContrasenya)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordDomain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordDomain handleCreate(com.soffid.iam.am.api.PasswordDomain dominiContrasenya) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordDomain findPasswordDomainByName(java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordDomain findPasswordDomainByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordDomain com.soffid.iam.iga.service.UserDomainService.findPasswordDomainByName(java.lang.String codi) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPasswordDomainByName(codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordDomain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.findPasswordDomainByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.findPasswordDomainByName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordDomain handleFindPasswordDomainByName(java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordDomain update(com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordDomain update(
		final com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dominiContrasenya == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordDomain com.soffid.iam.iga.service.UserDomainService.update(com.soffid.iam.am.api.PasswordDomain dominiContrasenya) - dominiContrasenya cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(dominiContrasenya)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordDomain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordDomain handleUpdate(com.soffid.iam.am.api.PasswordDomain dominiContrasenya) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordPolicy create(com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordPolicy create(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (politicaContrasenyaDomini == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordPolicy com.soffid.iam.iga.service.UserDomainService.create(com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini) - politicaContrasenyaDomini cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(politicaContrasenyaDomini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordPolicy) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordPolicy handleCreate(com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordPolicy findPolicyByTypeAndPasswordDomain(java.lang.String tipus, java.lang.String domini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordPolicy findPolicyByTypeAndPasswordDomain(
		final java.lang.String tipus, 
		final java.lang.String domini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipus == null || tipus.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordPolicy com.soffid.iam.iga.service.UserDomainService.findPolicyByTypeAndPasswordDomain(java.lang.String tipus, java.lang.String domini) - tipus cannot be null");
		}
		if (domini == null || domini.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordPolicy com.soffid.iam.iga.service.UserDomainService.findPolicyByTypeAndPasswordDomain(java.lang.String tipus, java.lang.String domini) - domini cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPolicyByTypeAndPasswordDomain(tipus, domini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordPolicy) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.findPolicyByTypeAndPasswordDomain", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.findPolicyByTypeAndPasswordDomain", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordPolicy handleFindPolicyByTypeAndPasswordDomain(java.lang.String tipus, java.lang.String domini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordPolicy update(com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordPolicy update(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (politicaContrasenyaDomini == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordPolicy com.soffid.iam.iga.service.UserDomainService.update(com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini) - politicaContrasenyaDomini cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(politicaContrasenyaDomini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordPolicy) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordPolicy handleUpdate(com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordPolicyForbbidenWord create(com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordPolicyForbbidenWord create(
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (paraulaProhibidaContrasenya == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordPolicyForbbidenWord com.soffid.iam.iga.service.UserDomainService.create(com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya) - paraulaProhibidaContrasenya cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(paraulaProhibidaContrasenya)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordPolicyForbbidenWord) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordPolicyForbbidenWord handleCreate(com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordPolicyForbbidenWord update(com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.PasswordPolicyForbbidenWord update(
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (paraulaProhibidaContrasenya == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.PasswordPolicyForbbidenWord com.soffid.iam.iga.service.UserDomainService.update(com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya) - paraulaProhibidaContrasenya cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(paraulaProhibidaContrasenya)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.PasswordPolicyForbbidenWord) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.PasswordPolicyForbbidenWord handleUpdate(com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.iga.api.UserDomain create(com.soffid.iam.iga.api.UserDomain dominiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.UserDomain create(
		final com.soffid.iam.iga.api.UserDomain dominiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dominiUsuari == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserDomain com.soffid.iam.iga.service.UserDomainService.create(com.soffid.iam.iga.api.UserDomain dominiUsuari) - dominiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(dominiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.UserDomain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.UserDomain handleCreate(com.soffid.iam.iga.api.UserDomain dominiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.iga.api.UserDomain findUserDomainByName(java.lang.String codiDominiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.UserDomain findUserDomainByName(
		final java.lang.String codiDominiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiDominiUsuari == null || codiDominiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserDomain com.soffid.iam.iga.service.UserDomainService.findUserDomainByName(java.lang.String codiDominiUsuari) - codiDominiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserDomainByName(codiDominiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.UserDomain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.findUserDomainByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.findUserDomainByName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.UserDomain handleFindUserDomainByName(java.lang.String codiDominiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.iga.api.UserDomain update(com.soffid.iam.iga.api.UserDomain dominiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.UserDomain update(
		final com.soffid.iam.iga.api.UserDomain dominiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dominiUsuari == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserDomain com.soffid.iam.iga.service.UserDomainService.update(com.soffid.iam.iga.api.UserDomain dominiUsuari) - dominiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(dominiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.UserDomain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.UserDomain handleUpdate(com.soffid.iam.iga.api.UserDomain dominiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.iga.api.UserType create(com.soffid.iam.iga.api.UserType tipusUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.UserType create(
		final com.soffid.iam.iga.api.UserType tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipusUsuari == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserType com.soffid.iam.iga.service.UserDomainService.create(com.soffid.iam.iga.api.UserType tipusUsuari) - tipusUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(tipusUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.UserType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.UserType handleCreate(com.soffid.iam.iga.api.UserType tipusUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.iga.api.UserType update(com.soffid.iam.iga.api.UserType tipusUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.UserType update(
		final com.soffid.iam.iga.api.UserType tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipusUsuari == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserType com.soffid.iam.iga.service.UserDomainService.update(com.soffid.iam.iga.api.UserType tipusUsuari) - tipusUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(tipusUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.UserType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.UserType handleUpdate(com.soffid.iam.iga.api.UserType tipusUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserType> findUserTypes(com.soffid.zkdb.api.Query q)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserType> findUserTypes(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserType> com.soffid.iam.iga.service.UserDomainService.findUserTypes(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserTypes(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.findUserTypes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.findUserTypes", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserType> handleFindUserTypes(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<com.soffid.iam.am.api.PasswordDomain> findAllPasswordDomain()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.PasswordDomain> findAllPasswordDomain()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllPasswordDomain()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.PasswordDomain>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.findAllPasswordDomain", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.findAllPasswordDomain", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.PasswordDomain> handleFindAllPasswordDomain() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<com.soffid.iam.iga.api.UserDomain> findAllUserDomain()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.UserDomain> findAllUserDomain()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllUserDomain()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.UserDomain>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.findAllUserDomain", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.findAllUserDomain", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.UserDomain> handleFindAllUserDomain() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<com.soffid.iam.am.api.ForbiddenWord> findAllForbiddenWords()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.ForbiddenWord> findAllForbiddenWords()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllForbiddenWords()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.ForbiddenWord>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.findAllForbiddenWords", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.findAllForbiddenWords", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.ForbiddenWord> handleFindAllForbiddenWords() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<com.soffid.iam.am.api.PasswordPolicy> findAllPasswordPolicyDomain(java.lang.String codiDominiContrasenya)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.PasswordPolicy> findAllPasswordPolicyDomain(
		final java.lang.String codiDominiContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiDominiContrasenya == null || codiDominiContrasenya.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.PasswordPolicy> com.soffid.iam.iga.service.UserDomainService.findAllPasswordPolicyDomain(java.lang.String codiDominiContrasenya) - codiDominiContrasenya cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllPasswordPolicyDomain(codiDominiContrasenya)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.PasswordPolicy>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.findAllPasswordPolicyDomain", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.findAllPasswordPolicyDomain", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.PasswordPolicy> handleFindAllPasswordPolicyDomain(java.lang.String codiDominiContrasenya) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<com.soffid.iam.iga.api.UserType> findAllUserType()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.UserType> findAllUserType()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAllUserType()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.UserType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.findAllUserType", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.findAllUserType", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.UserType> handleFindAllUserType() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<java.lang.String> findNameGenerators()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.lang.String> findNameGenerators()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindNameGenerators()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.findNameGenerators", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.findNameGenerators", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.String> handleFindNameGenerators() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> findForbiddenWordsPasswordPolicy(com.soffid.iam.am.api.PasswordPolicy politicaContrasenya)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> findForbiddenWordsPasswordPolicy(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (politicaContrasenya == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> com.soffid.iam.iga.service.UserDomainService.findForbiddenWordsPasswordPolicy(com.soffid.iam.am.api.PasswordPolicy politicaContrasenya) - politicaContrasenya cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindForbiddenWordsPasswordPolicy(politicaContrasenya)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.am.api.PasswordPolicyForbbidenWord>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.findForbiddenWordsPasswordPolicy", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.findForbiddenWordsPasswordPolicy", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> handleFindForbiddenWordsPasswordPolicy(com.soffid.iam.am.api.PasswordPolicy politicaContrasenya) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#void delete(com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (paraulaProhibida == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.UserDomainService.delete(com.soffid.iam.am.api.ForbiddenWord paraulaProhibida) - paraulaProhibida cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(paraulaProhibida);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.am.api.ForbiddenWord paraulaProhibida) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#void delete(com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dominiContrasenya == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.UserDomainService.delete(com.soffid.iam.am.api.PasswordDomain dominiContrasenya) - dominiContrasenya cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(dominiContrasenya);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.am.api.PasswordDomain dominiContrasenya) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#void delete(com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (politicaContrasenyaDomini == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.UserDomainService.delete(com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini) - politicaContrasenyaDomini cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(politicaContrasenyaDomini);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#void delete(com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (paraulaProhibidaContrasenya == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.UserDomainService.delete(com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya) - paraulaProhibidaContrasenya cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(paraulaProhibidaContrasenya);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#void delete(com.soffid.iam.iga.api.UserDomain dominiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.UserDomain dominiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dominiUsuari == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.UserDomainService.delete(com.soffid.iam.iga.api.UserDomain dominiUsuari) - dominiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(dominiUsuari);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.UserDomain dominiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#	 * @see com.soffid.iam.iga.service.UserDomainService#void delete(com.soffid.iam.iga.api.UserType tipusUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.UserType tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipusUsuari == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.UserDomainService.delete(com.soffid.iam.iga.api.UserType tipusUsuari) - tipusUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(tipusUsuari);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.UserDomainService.class).
			warn ("Error on UserDomainService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on UserDomainService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.UserType tipusUsuari) throws Exception;

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
