//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.rc.service.SoDRuleService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.rc.service.SoDRuleService
 */
public abstract class SoDRuleServiceBase
	implements com.soffid.iam.rc.service.SoDRuleService
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

	private com.soffid.iam.rc.model.SoDRoleEntityDao soDRoleEntityDao;

	/**
	 * Sets reference to <code>soDRoleEntityDao</code>.
	 */
	public void setSoDRoleEntityDao (com.soffid.iam.rc.model.SoDRoleEntityDao soDRoleEntityDao) {
		this.soDRoleEntityDao = soDRoleEntityDao;
	}

	/**
	 * Gets reference to <code>soDRoleEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.SoDRoleEntityDao getSoDRoleEntityDao () {
		return soDRoleEntityDao;
	}

	private com.soffid.iam.rc.model.SoDRuleEntityDao soDRuleEntityDao;

	/**
	 * Sets reference to <code>soDRuleEntityDao</code>.
	 */
	public void setSoDRuleEntityDao (com.soffid.iam.rc.model.SoDRuleEntityDao soDRuleEntityDao) {
		this.soDRuleEntityDao = soDRuleEntityDao;
	}

	/**
	 * Gets reference to <code>soDRuleEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.SoDRuleEntityDao getSoDRuleEntityDao () {
		return soDRuleEntityDao;
	}

	private com.soffid.iam.rc.model.SoDRuleMatrixEntityDao soDRuleMatrixEntityDao;

	/**
	 * Sets reference to <code>soDRuleMatrixEntityDao</code>.
	 */
	public void setSoDRuleMatrixEntityDao (com.soffid.iam.rc.model.SoDRuleMatrixEntityDao soDRuleMatrixEntityDao) {
		this.soDRuleMatrixEntityDao = soDRuleMatrixEntityDao;
	}

	/**
	 * Gets reference to <code>soDRuleMatrixEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.SoDRuleMatrixEntityDao getSoDRuleMatrixEntityDao () {
		return soDRuleMatrixEntityDao;
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
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#boolean isGreater(com.soffid.iam.rc.api.SoDRisk first, com.soffid.iam.rc.api.SoDRisk second)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isGreater(
		final com.soffid.iam.rc.api.SoDRisk first, 
		final com.soffid.iam.rc.api.SoDRisk second)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (first == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.rc.service.SoDRuleService.isGreater(com.soffid.iam.rc.api.SoDRisk first, com.soffid.iam.rc.api.SoDRisk second) - first cannot be null");
		}
		if (second == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.rc.service.SoDRuleService.isGreater(com.soffid.iam.rc.api.SoDRisk first, com.soffid.iam.rc.api.SoDRisk second) - second cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsGreater(first, second)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.isGreater", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.isGreater", (Throwable) __r[1]);
	}

	protected abstract boolean handleIsGreater(com.soffid.iam.rc.api.SoDRisk first, com.soffid.iam.rc.api.SoDRisk second) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRisk qualifyUser(java.util.Collection<com.soffid.iam.iga.api.RoleAccount> ra)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.SoDRisk qualifyUser(
		final java.util.Collection<com.soffid.iam.iga.api.RoleAccount> ra)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (ra == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRisk com.soffid.iam.rc.service.SoDRuleService.qualifyUser(java.util.Collection<com.soffid.iam.iga.api.RoleAccount> ra) - ra cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleQualifyUser(ra)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.SoDRisk) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.qualifyUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.qualifyUser", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.SoDRisk handleQualifyUser(java.util.Collection<com.soffid.iam.iga.api.RoleAccount> ra) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRole create(com.soffid.iam.rc.api.SoDRole role)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.SoDRole create(
		final com.soffid.iam.rc.api.SoDRole role)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (role == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRole com.soffid.iam.rc.service.SoDRuleService.create(com.soffid.iam.rc.api.SoDRole role) - role cannot be null");
		}
		if (role.getRole() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRole com.soffid.iam.rc.service.SoDRuleService.create(com.soffid.iam.rc.api.SoDRole role) - role.role cannot be null");
		}
		if (role.getRuleId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRole com.soffid.iam.rc.service.SoDRuleService.create(com.soffid.iam.rc.api.SoDRole role) - role.ruleId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(role)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.SoDRole) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.SoDRole handleCreate(com.soffid.iam.rc.api.SoDRole role) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRule create(com.soffid.iam.rc.api.SoDRule rule)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.SoDRule create(
		final com.soffid.iam.rc.api.SoDRule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRule com.soffid.iam.rc.service.SoDRuleService.create(com.soffid.iam.rc.api.SoDRule rule) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRule com.soffid.iam.rc.service.SoDRuleService.create(com.soffid.iam.rc.api.SoDRule rule) - rule.name cannot be null");
		}
		if (rule.getRisk() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRule com.soffid.iam.rc.service.SoDRuleService.create(com.soffid.iam.rc.api.SoDRule rule) - rule.risk cannot be null");
		}
		if (rule.getApplication() == null || rule.getApplication().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRule com.soffid.iam.rc.service.SoDRuleService.create(com.soffid.iam.rc.api.SoDRule rule) - rule.application cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(rule)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.SoDRule) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.SoDRule handleCreate(com.soffid.iam.rc.api.SoDRule rule) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRule getRuleById(java.lang.Long ruleId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.SoDRule getRuleById(
		final java.lang.Long ruleId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (ruleId == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRule com.soffid.iam.rc.service.SoDRuleService.getRuleById(java.lang.Long ruleId) - ruleId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetRuleById(ruleId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.SoDRule) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.getRuleById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.getRuleById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.SoDRule handleGetRuleById(java.lang.Long ruleId) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRule isAllowed(com.soffid.iam.iga.api.RoleAccount ra)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.SoDRule isAllowed(
		final com.soffid.iam.iga.api.RoleAccount ra)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (ra == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRule com.soffid.iam.rc.service.SoDRuleService.isAllowed(com.soffid.iam.iga.api.RoleAccount ra) - ra cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleIsAllowed(ra)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.SoDRule) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.isAllowed", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.isAllowed", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.SoDRule handleIsAllowed(com.soffid.iam.iga.api.RoleAccount ra) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRule update(com.soffid.iam.rc.api.SoDRule rule)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.SoDRule update(
		final com.soffid.iam.rc.api.SoDRule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRule com.soffid.iam.rc.service.SoDRuleService.update(com.soffid.iam.rc.api.SoDRule rule) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRule com.soffid.iam.rc.service.SoDRuleService.update(com.soffid.iam.rc.api.SoDRule rule) - rule.name cannot be null");
		}
		if (rule.getRisk() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRule com.soffid.iam.rc.service.SoDRuleService.update(com.soffid.iam.rc.api.SoDRule rule) - rule.risk cannot be null");
		}
		if (rule.getApplication() == null || rule.getApplication().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRule com.soffid.iam.rc.service.SoDRuleService.update(com.soffid.iam.rc.api.SoDRule rule) - rule.application cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(rule)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.SoDRule) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.SoDRule handleUpdate(com.soffid.iam.rc.api.SoDRule rule) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRuleMatrix create(com.soffid.iam.rc.api.SoDRuleMatrix role)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.SoDRuleMatrix create(
		final com.soffid.iam.rc.api.SoDRuleMatrix role)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (role == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRuleMatrix com.soffid.iam.rc.service.SoDRuleService.create(com.soffid.iam.rc.api.SoDRuleMatrix role) - role cannot be null");
		}
		if (role.getRuleId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRuleMatrix com.soffid.iam.rc.service.SoDRuleService.create(com.soffid.iam.rc.api.SoDRuleMatrix role) - role.ruleId cannot be null");
		}
		if (role.getRisk() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRuleMatrix com.soffid.iam.rc.service.SoDRuleService.create(com.soffid.iam.rc.api.SoDRuleMatrix role) - role.risk cannot be null");
		}
		if (role.getRow() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRuleMatrix com.soffid.iam.rc.service.SoDRuleService.create(com.soffid.iam.rc.api.SoDRuleMatrix role) - role.row cannot be null");
		}
		if (role.getColumn() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRuleMatrix com.soffid.iam.rc.service.SoDRuleService.create(com.soffid.iam.rc.api.SoDRuleMatrix role) - role.column cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(role)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.SoDRuleMatrix) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.SoDRuleMatrix handleCreate(com.soffid.iam.rc.api.SoDRuleMatrix role) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.iam.rc.api.SoDRuleMatrix update(com.soffid.iam.rc.api.SoDRuleMatrix role)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.SoDRuleMatrix update(
		final com.soffid.iam.rc.api.SoDRuleMatrix role)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (role == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRuleMatrix com.soffid.iam.rc.service.SoDRuleService.update(com.soffid.iam.rc.api.SoDRuleMatrix role) - role cannot be null");
		}
		if (role.getRuleId() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRuleMatrix com.soffid.iam.rc.service.SoDRuleService.update(com.soffid.iam.rc.api.SoDRuleMatrix role) - role.ruleId cannot be null");
		}
		if (role.getRisk() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRuleMatrix com.soffid.iam.rc.service.SoDRuleService.update(com.soffid.iam.rc.api.SoDRuleMatrix role) - role.risk cannot be null");
		}
		if (role.getRow() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRuleMatrix com.soffid.iam.rc.service.SoDRuleService.update(com.soffid.iam.rc.api.SoDRuleMatrix role) - role.row cannot be null");
		}
		if (role.getColumn() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.SoDRuleMatrix com.soffid.iam.rc.service.SoDRuleService.update(com.soffid.iam.rc.api.SoDRuleMatrix role) - role.column cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(role)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.SoDRuleMatrix) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.SoDRuleMatrix handleUpdate(com.soffid.iam.rc.api.SoDRuleMatrix role) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.SoDRule> findSodRules(com.soffid.zkdb.api.Query query)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.SoDRule> findSodRules(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSodRules(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.SoDRule>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.findSodRules", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.findSodRules", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.SoDRule> handleFindSodRules(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#java.lang.String generateChangesReport(com.soffid.iam.rc.api.SoDRule rule, java.util.Collection<com.soffid.iam.rc.api.SoDRole> grants, java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> matrix)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String generateChangesReport(
		final com.soffid.iam.rc.api.SoDRule rule, 
		final java.util.Collection<com.soffid.iam.rc.api.SoDRole> grants, 
		final java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> matrix)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.rc.service.SoDRuleService.generateChangesReport(com.soffid.iam.rc.api.SoDRule rule, java.util.Collection<com.soffid.iam.rc.api.SoDRole> grants, java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> matrix) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.rc.service.SoDRuleService.generateChangesReport(com.soffid.iam.rc.api.SoDRule rule, java.util.Collection<com.soffid.iam.rc.api.SoDRole> grants, java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> matrix) - rule.name cannot be null");
		}
		if (rule.getRisk() == null ) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.rc.service.SoDRuleService.generateChangesReport(com.soffid.iam.rc.api.SoDRule rule, java.util.Collection<com.soffid.iam.rc.api.SoDRole> grants, java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> matrix) - rule.risk cannot be null");
		}
		if (rule.getApplication() == null || rule.getApplication().trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.rc.service.SoDRuleService.generateChangesReport(com.soffid.iam.rc.api.SoDRule rule, java.util.Collection<com.soffid.iam.rc.api.SoDRole> grants, java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> matrix) - rule.application cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateChangesReport(rule, grants, matrix)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.generateChangesReport", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.generateChangesReport", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGenerateChangesReport(com.soffid.iam.rc.api.SoDRule rule, java.util.Collection<com.soffid.iam.rc.api.SoDRole> grants, java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> matrix) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#java.util.Collection<com.soffid.iam.rc.api.SoDRule> findAffectingRulesByRolAccount(com.soffid.iam.iga.api.RoleAccount ra)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.rc.api.SoDRule> findAffectingRulesByRolAccount(
		final com.soffid.iam.iga.api.RoleAccount ra)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (ra == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.rc.api.SoDRule> com.soffid.iam.rc.service.SoDRuleService.findAffectingRulesByRolAccount(com.soffid.iam.iga.api.RoleAccount ra) - ra cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAffectingRulesByRolAccount(ra)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.rc.api.SoDRule>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.findAffectingRulesByRolAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.findAffectingRulesByRolAccount", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.rc.api.SoDRule> handleFindAffectingRulesByRolAccount(com.soffid.iam.iga.api.RoleAccount ra) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> findMatrixByRule(java.lang.Long ruleId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> findMatrixByRule(
		final java.lang.Long ruleId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (ruleId == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> com.soffid.iam.rc.service.SoDRuleService.findMatrixByRule(java.lang.Long ruleId) - ruleId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMatrixByRule(ruleId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.findMatrixByRule", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.findMatrixByRule", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> handleFindMatrixByRule(java.lang.Long ruleId) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#java.util.Collection<com.soffid.iam.rc.api.SoDRole> findRolesByRule(java.lang.Long ruleId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.rc.api.SoDRole> findRolesByRule(
		final java.lang.Long ruleId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (ruleId == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.rc.api.SoDRole> com.soffid.iam.rc.service.SoDRuleService.findRolesByRule(java.lang.Long ruleId) - ruleId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRolesByRule(ruleId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.rc.api.SoDRole>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.findRolesByRule", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.findRolesByRule", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.rc.api.SoDRole> handleFindRolesByRule(java.lang.Long ruleId) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#java.util.Collection<com.soffid.iam.rc.api.SoDRule> findRuleByApplication(java.lang.Long applicationId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.rc.api.SoDRule> findRuleByApplication(
		final java.lang.Long applicationId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (applicationId == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.rc.api.SoDRule> com.soffid.iam.rc.service.SoDRuleService.findRuleByApplication(java.lang.Long applicationId) - applicationId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRuleByApplication(applicationId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.rc.api.SoDRule>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.findRuleByApplication", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.findRuleByApplication", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.rc.api.SoDRule> handleFindRuleByApplication(java.lang.Long applicationId) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#java.util.List<com.soffid.iam.iga.api.RoleAccount> findViolotions(java.lang.String applicationName, com.soffid.iam.rc.api.SoDRisk riskLevel)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.iga.api.RoleAccount> findViolotions(
		final java.lang.String applicationName, 
		final com.soffid.iam.rc.api.SoDRisk riskLevel)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (riskLevel == null) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.rc.service.SoDRuleService.findViolotions(java.lang.String applicationName, com.soffid.iam.rc.api.SoDRisk riskLevel) - riskLevel cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindViolotions(applicationName, riskLevel)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.findViolotions", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.findViolotions", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.iga.api.RoleAccount> handleFindViolotions(java.lang.String applicationName, com.soffid.iam.rc.api.SoDRisk riskLevel) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#void internalRemovingRole(java.lang.Long roleId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void internalRemovingRole(
		final java.lang.Long roleId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (roleId == null) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.internalRemovingRole(java.lang.Long roleId) - roleId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleInternalRemovingRole(roleId);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.internalRemovingRole", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.internalRemovingRole", (Throwable) __r[1]);
	}

	protected abstract void handleInternalRemovingRole(java.lang.Long roleId) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#void qualifyRolAccountList(java.util.List<com.soffid.iam.iga.api.RoleAccount> ra)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void qualifyRolAccountList(
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> ra)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (ra == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.qualifyRolAccountList(java.util.List<com.soffid.iam.iga.api.RoleAccount> ra) - ra cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleQualifyRolAccountList(ra);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.qualifyRolAccountList", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.qualifyRolAccountList", (Throwable) __r[1]);
	}

	protected abstract void handleQualifyRolAccountList(java.util.List<com.soffid.iam.iga.api.RoleAccount> ra) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#void remove(com.soffid.iam.rc.api.SoDRole role)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void remove(
		final com.soffid.iam.rc.api.SoDRole role)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (role == null) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.remove(com.soffid.iam.rc.api.SoDRole role) - role cannot be null");
		}
		if (role.getRole() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.remove(com.soffid.iam.rc.api.SoDRole role) - role.role cannot be null");
		}
		if (role.getRuleId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.remove(com.soffid.iam.rc.api.SoDRole role) - role.ruleId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemove(role);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.remove", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.remove", (Throwable) __r[1]);
	}

	protected abstract void handleRemove(com.soffid.iam.rc.api.SoDRole role) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#void remove(com.soffid.iam.rc.api.SoDRule rule)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void remove(
		final com.soffid.iam.rc.api.SoDRule rule)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (rule == null) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.remove(com.soffid.iam.rc.api.SoDRule rule) - rule cannot be null");
		}
		if (rule.getName() == null || rule.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.remove(com.soffid.iam.rc.api.SoDRule rule) - rule.name cannot be null");
		}
		if (rule.getRisk() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.remove(com.soffid.iam.rc.api.SoDRule rule) - rule.risk cannot be null");
		}
		if (rule.getApplication() == null || rule.getApplication().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.remove(com.soffid.iam.rc.api.SoDRule rule) - rule.application cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemove(rule);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.remove", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.remove", (Throwable) __r[1]);
	}

	protected abstract void handleRemove(com.soffid.iam.rc.api.SoDRule rule) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.SoDRuleService#	 * @see com.soffid.iam.rc.service.SoDRuleService#void remove(com.soffid.iam.rc.api.SoDRuleMatrix role)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void remove(
		final com.soffid.iam.rc.api.SoDRuleMatrix role)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (role == null) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.remove(com.soffid.iam.rc.api.SoDRuleMatrix role) - role cannot be null");
		}
		if (role.getRuleId() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.remove(com.soffid.iam.rc.api.SoDRuleMatrix role) - role.ruleId cannot be null");
		}
		if (role.getRisk() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.remove(com.soffid.iam.rc.api.SoDRuleMatrix role) - role.risk cannot be null");
		}
		if (role.getRow() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.remove(com.soffid.iam.rc.api.SoDRuleMatrix role) - role.row cannot be null");
		}
		if (role.getColumn() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.service.SoDRuleService.remove(com.soffid.iam.rc.api.SoDRuleMatrix role) - role.column cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemove(role);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.SoDRuleService.class).
			warn ("Error on SoDRuleService.remove", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoDRuleService.remove", (Throwable) __r[1]);
	}

	protected abstract void handleRemove(com.soffid.iam.rc.api.SoDRuleMatrix role) throws Exception;

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
