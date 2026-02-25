//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.GroupService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.GroupService
 */
public abstract class GroupServiceBase
	implements com.soffid.iam.iga.service.GroupService
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

	private com.soffid.iam.service.impl.AttributeValidationService attributeValidationService;

	/**
	 * Sets reference to <code>attributeValidationService</code>.
	 */
	public void setAttributeValidationService (com.soffid.iam.service.impl.AttributeValidationService attributeValidationService) {
		this.attributeValidationService = attributeValidationService;
	}

	/**
	 * Gets reference to <code>attributeValidationService</code>.
	 */
	public com.soffid.iam.service.impl.AttributeValidationService getAttributeValidationService () {
		return attributeValidationService;
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

	private com.soffid.iam.base.model.ConfigEntityDao configEntityDao;

	/**
	 * Sets reference to <code>configEntityDao</code>.
	 */
	public void setConfigEntityDao (com.soffid.iam.base.model.ConfigEntityDao configEntityDao) {
		this.configEntityDao = configEntityDao;
	}

	/**
	 * Gets reference to <code>configEntityDao</code>.
	 */
	public com.soffid.iam.base.model.ConfigEntityDao getConfigEntityDao () {
		return configEntityDao;
	}

	private com.soffid.iam.iga.model.GroupAttributeEntityDao groupAttributeEntityDao;

	/**
	 * Sets reference to <code>groupAttributeEntityDao</code>.
	 */
	public void setGroupAttributeEntityDao (com.soffid.iam.iga.model.GroupAttributeEntityDao groupAttributeEntityDao) {
		this.groupAttributeEntityDao = groupAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>groupAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupAttributeEntityDao getGroupAttributeEntityDao () {
		return groupAttributeEntityDao;
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

	private com.soffid.iam.am.model.HostEntityDao hostEntityDao;

	/**
	 * Sets reference to <code>hostEntityDao</code>.
	 */
	public void setHostEntityDao (com.soffid.iam.am.model.HostEntityDao hostEntityDao) {
		this.hostEntityDao = hostEntityDao;
	}

	/**
	 * Gets reference to <code>hostEntityDao</code>.
	 */
	public com.soffid.iam.am.model.HostEntityDao getHostEntityDao () {
		return hostEntityDao;
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

	private com.soffid.iam.iga.model.RoleGroupEntityDao roleGroupEntityDao;

	/**
	 * Sets reference to <code>roleGroupEntityDao</code>.
	 */
	public void setRoleGroupEntityDao (com.soffid.iam.iga.model.RoleGroupEntityDao roleGroupEntityDao) {
		this.roleGroupEntityDao = roleGroupEntityDao;
	}

	/**
	 * Gets reference to <code>roleGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleGroupEntityDao getRoleGroupEntityDao () {
		return roleGroupEntityDao;
	}

	private com.soffid.iam.impl.service.RuleEvaluatorService ruleEvaluatorService;

	/**
	 * Sets reference to <code>ruleEvaluatorService</code>.
	 */
	public void setRuleEvaluatorService (com.soffid.iam.impl.service.RuleEvaluatorService ruleEvaluatorService) {
		this.ruleEvaluatorService = ruleEvaluatorService;
	}

	/**
	 * Gets reference to <code>ruleEvaluatorService</code>.
	 */
	public com.soffid.iam.impl.service.RuleEvaluatorService getRuleEvaluatorService () {
		return ruleEvaluatorService;
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

	private com.soffid.iam.iga.model.UserGroupAttributeEntityDao userGroupAttributeEntityDao;

	/**
	 * Sets reference to <code>userGroupAttributeEntityDao</code>.
	 */
	public void setUserGroupAttributeEntityDao (com.soffid.iam.iga.model.UserGroupAttributeEntityDao userGroupAttributeEntityDao) {
		this.userGroupAttributeEntityDao = userGroupAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>userGroupAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserGroupAttributeEntityDao getUserGroupAttributeEntityDao () {
		return userGroupAttributeEntityDao;
	}

	private com.soffid.iam.iga.model.UserGroupEntityDao userGroupEntityDao;

	/**
	 * Sets reference to <code>userGroupEntityDao</code>.
	 */
	public void setUserGroupEntityDao (com.soffid.iam.iga.model.UserGroupEntityDao userGroupEntityDao) {
		this.userGroupEntityDao = userGroupEntityDao;
	}

	/**
	 * Gets reference to <code>userGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserGroupEntityDao getUserGroupEntityDao () {
		return userGroupEntityDao;
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
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.UserGroup> findGroupMembers(java.lang.String codiGrup)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.UserGroup> findGroupMembers(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.UserGroup> com.soffid.iam.iga.service.GroupService.findGroupMembers(java.lang.String codiGrup) - codiGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupMembers(codiGrup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.UserGroup>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findGroupMembers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findGroupMembers", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.UserGroup> handleFindGroupMembers(java.lang.String codiGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.am.api.Host getOfficeServer(com.soffid.iam.iga.api.Group grup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Host getOfficeServer(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetOfficeServer(grup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Host) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.getOfficeServer", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.getOfficeServer", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Host handleGetOfficeServer(com.soffid.iam.iga.api.Group grup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group create(com.soffid.iam.iga.api.Group grup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Group create(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grup == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.create(com.soffid.iam.iga.api.Group grup) - grup cannot be null");
		}
		if (grup.getName() == null || grup.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.create(com.soffid.iam.iga.api.Group grup) - grup.name cannot be null");
		}
		if (grup.getDescription() == null || grup.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.create(com.soffid.iam.iga.api.Group grup) - grup.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(grup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Group) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Group handleCreate(com.soffid.iam.iga.api.Group grup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group createHistoric(com.soffid.iam.iga.api.Group grup)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Group createHistoric(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grup == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.createHistoric(com.soffid.iam.iga.api.Group grup) - grup cannot be null");
		}
		if (grup.getName() == null || grup.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.createHistoric(com.soffid.iam.iga.api.Group grup) - grup.name cannot be null");
		}
		if (grup.getDescription() == null || grup.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.createHistoric(com.soffid.iam.iga.api.Group grup) - grup.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateHistoric(grup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Group) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.createHistoric", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.createHistoric", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Group handleCreateHistoric(com.soffid.iam.iga.api.Group grup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group findGroupByGroupNameAndDate(java.lang.String codi, java.util.Date date)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Group findGroupByGroupNameAndDate(
		final java.lang.String codi, 
		final java.util.Date date)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.findGroupByGroupNameAndDate(java.lang.String codi, java.util.Date date) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupByGroupNameAndDate(codi, date)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Group) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findGroupByGroupNameAndDate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findGroupByGroupNameAndDate", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Group handleFindGroupByGroupNameAndDate(java.lang.String codi, java.util.Date date) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group findGroupByGroupName(java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Group findGroupByGroupName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.findGroupByGroupName(java.lang.String codi) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupByGroupName(codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Group) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findGroupByGroupName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findGroupByGroupName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Group handleFindGroupByGroupName(java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group findGroupById(java.lang.Long grupId)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Group findGroupById(
		final java.lang.Long grupId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grupId == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.findGroupById(java.lang.Long grupId) - grupId cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupById(grupId)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Group) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findGroupById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findGroupById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Group handleFindGroupById(java.lang.Long grupId) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group findPrimaryGroupByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Group findPrimaryGroupByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.findPrimaryGroupByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPrimaryGroupByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Group) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findPrimaryGroupByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findPrimaryGroupByUserName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Group handleFindPrimaryGroupByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group getSuperGroup(java.lang.String codiGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Group getSuperGroup(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.getSuperGroup(java.lang.String codiGrup) - codiGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetSuperGroup(codiGrup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Group) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.getSuperGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.getSuperGroup", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Group handleGetSuperGroup(java.lang.String codiGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.Group update(com.soffid.iam.iga.api.Group grup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Group update(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grup == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.update(com.soffid.iam.iga.api.Group grup) - grup cannot be null");
		}
		if (grup.getName() == null || grup.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.update(com.soffid.iam.iga.api.Group grup) - grup.name cannot be null");
		}
		if (grup.getDescription() == null || grup.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Group com.soffid.iam.iga.service.GroupService.update(com.soffid.iam.iga.api.Group grup) - grup.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(grup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Group) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Group handleUpdate(com.soffid.iam.iga.api.Group grup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.UserGroup create(com.soffid.iam.iga.api.UserGroup usuariGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.UserGroup create(
		final com.soffid.iam.iga.api.UserGroup usuariGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuariGrup == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserGroup com.soffid.iam.iga.service.GroupService.create(com.soffid.iam.iga.api.UserGroup usuariGrup) - usuariGrup cannot be null");
		}
		if (usuariGrup.getUser() == null || usuariGrup.getUser().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserGroup com.soffid.iam.iga.service.GroupService.create(com.soffid.iam.iga.api.UserGroup usuariGrup) - usuariGrup.user cannot be null");
		}
		if (usuariGrup.getGroup() == null || usuariGrup.getGroup().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserGroup com.soffid.iam.iga.service.GroupService.create(com.soffid.iam.iga.api.UserGroup usuariGrup) - usuariGrup.group cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(usuariGrup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.UserGroup) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.UserGroup handleCreate(com.soffid.iam.iga.api.UserGroup usuariGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.UserGroup findUserGroupByUserNameAndGroupName(java.lang.String codiUsuari, java.lang.String codiGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.UserGroup findUserGroupByUserNameAndGroupName(
		final java.lang.String codiUsuari, 
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserGroup com.soffid.iam.iga.service.GroupService.findUserGroupByUserNameAndGroupName(java.lang.String codiUsuari, java.lang.String codiGrup) - codiUsuari cannot be null");
		}
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserGroup com.soffid.iam.iga.service.GroupService.findUserGroupByUserNameAndGroupName(java.lang.String codiUsuari, java.lang.String codiGrup) - codiGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserGroupByUserNameAndGroupName(codiUsuari, codiGrup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.UserGroup) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findUserGroupByUserNameAndGroupName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findUserGroupByUserNameAndGroupName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.UserGroup handleFindUserGroupByUserNameAndGroupName(java.lang.String codiUsuari, java.lang.String codiGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.iam.iga.api.UserGroup update(com.soffid.iam.iga.api.UserGroup usuariGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.UserGroup update(
		final com.soffid.iam.iga.api.UserGroup usuariGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuariGrup == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserGroup com.soffid.iam.iga.service.GroupService.update(com.soffid.iam.iga.api.UserGroup usuariGrup) - usuariGrup cannot be null");
		}
		if (usuariGrup.getUser() == null || usuariGrup.getUser().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserGroup com.soffid.iam.iga.service.GroupService.update(com.soffid.iam.iga.api.UserGroup usuariGrup) - usuariGrup.user cannot be null");
		}
		if (usuariGrup.getGroup() == null || usuariGrup.getGroup().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserGroup com.soffid.iam.iga.service.GroupService.update(com.soffid.iam.iga.api.UserGroup usuariGrup) - usuariGrup.group cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(usuariGrup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.UserGroup) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.UserGroup handleUpdate(com.soffid.iam.iga.api.UserGroup usuariGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findGroupUserByJsonQuery(java.lang.String query, java.lang.Integer startIndex, java.lang.Integer count)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findGroupUserByJsonQuery(
		final java.lang.String query, 
		final java.lang.Integer startIndex, 
		final java.lang.Integer count)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupUserByJsonQuery(query, startIndex, count)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findGroupUserByJsonQuery", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findGroupUserByJsonQuery", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> handleFindGroupUserByJsonQuery(java.lang.String query, java.lang.Integer startIndex, java.lang.Integer count) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Group> findGroups(com.soffid.zkdb.api.Query q)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Group> findGroups(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.GroupService.findGroups(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroups(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findGroups", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findGroups", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Group> handleFindGroups(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findUserGroup(com.soffid.zkdb.api.Query query)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findUserGroup(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserGroup(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findUserGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findUserGroup", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> handleFindUserGroup(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findUsersBelongtoGroupByGroupName(java.lang.String codiGrup, java.util.Date date, java.lang.Integer start, java.lang.Integer pageSize)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> findUsersBelongtoGroupByGroupName(
		final java.lang.String codiGrup, 
		final java.util.Date date, 
		final java.lang.Integer start, 
		final java.lang.Integer pageSize)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUsersBelongtoGroupByGroupName(codiGrup, date, start, pageSize)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findUsersBelongtoGroupByGroupName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findUsersBelongtoGroupByGroupName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserGroup> handleFindUsersBelongtoGroupByGroupName(java.lang.String codiGrup, java.util.Date date, java.lang.Integer start, java.lang.Integer pageSize) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<java.lang.String> findGroupNames()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		readOnly=true)
	public java.util.Collection<java.lang.String> findGroupNames()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupNames()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findGroupNames", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findGroupNames", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.String> handleFindGroupNames() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.GroupService.findGroupsByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupsByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findGroupsByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findGroupsByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleFindGroupsByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsByGroupsType(java.lang.String tipus)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsByGroupsType(
		final java.lang.String tipus)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipus == null || tipus.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.GroupService.findGroupsByGroupsType(java.lang.String tipus) - tipus cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupsByGroupsType(tipus)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findGroupsByGroupsType", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findGroupsByGroupsType", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleFindGroupsByGroupsType(java.lang.String tipus) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsFromRolesByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsFromRolesByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.GroupService.findGroupsFromRolesByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupsFromRolesByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findGroupsFromRolesByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findGroupsFromRolesByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleFindGroupsFromRolesByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsFromUsersByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> findGroupsFromUsersByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.GroupService.findGroupsFromUsersByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupsFromUsersByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findGroupsFromUsersByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findGroupsFromUsersByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleFindGroupsFromUsersByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUsersRolesWithGroupByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUsersRolesWithGroupByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.GroupService.findUsersRolesWithGroupByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUsersRolesWithGroupByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findUsersRolesWithGroupByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findUsersRolesWithGroupByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindUsersRolesWithGroupByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUsersRolesDomainTypeAndUserGroups(java.lang.String codiGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUsersRolesDomainTypeAndUserGroups(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.RoleAccount> com.soffid.iam.iga.service.GroupService.findUsersRolesDomainTypeAndUserGroups(java.lang.String codiGrup) - codiGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUsersRolesDomainTypeAndUserGroups(codiGrup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.RoleAccount>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findUsersRolesDomainTypeAndUserGroups", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findUsersRolesDomainTypeAndUserGroups", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.RoleAccount> handleFindUsersRolesDomainTypeAndUserGroups(java.lang.String codiGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Group> findSubgroupsByGroupName(java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> findSubgroupsByGroupName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.GroupService.findSubgroupsByGroupName(java.lang.String codi) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSubgroupsByGroupName(codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findSubgroupsByGroupName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findSubgroupsByGroupName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleFindSubgroupsByGroupName(java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Group> findSubgroupsByGroupNameAndDate(java.lang.String codi, java.util.Date date)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> findSubgroupsByGroupNameAndDate(
		final java.lang.String codi, 
		final java.util.Date date)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.GroupService.findSubgroupsByGroupNameAndDate(java.lang.String codi, java.util.Date date) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSubgroupsByGroupNameAndDate(codi, date)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findSubgroupsByGroupNameAndDate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findSubgroupsByGroupNameAndDate", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleFindSubgroupsByGroupNameAndDate(java.lang.String codi, java.util.Date date) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUserGroupHistoryByUserName(java.lang.String userName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUserGroupHistoryByUserName(
		final java.lang.String userName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (userName == null || userName.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.UserGroup> com.soffid.iam.iga.service.GroupService.findUserGroupHistoryByUserName(java.lang.String userName) - userName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserGroupHistoryByUserName(userName)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.UserGroup>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findUserGroupHistoryByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findUserGroupHistoryByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.UserGroup> handleFindUserGroupHistoryByUserName(java.lang.String userName) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUsersGroupByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUsersGroupByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.UserGroup> com.soffid.iam.iga.service.GroupService.findUsersGroupByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUsersGroupByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.UserGroup>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.findUsersGroupByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.findUsersGroupByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.UserGroup> handleFindUsersGroupByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Group> getGroups()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> getGroups()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetGroups()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.getGroups", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.getGroups", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleGetGroups() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Group> getParentList(java.lang.String codiGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> getParentList(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.GroupService.getParentList(java.lang.String codiGrup) - codiGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetParentList(codiGrup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.getParentList", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.getParentList", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleGetParentList(java.lang.String codiGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.Role> getRolesFromGroup(com.soffid.iam.iga.api.Group grup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Role> getRolesFromGroup(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grup == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.iga.service.GroupService.getRolesFromGroup(com.soffid.iam.iga.api.Group grup) - grup cannot be null");
		}
		if (grup.getName() == null || grup.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.iga.service.GroupService.getRolesFromGroup(com.soffid.iam.iga.api.Group grup) - grup.name cannot be null");
		}
		if (grup.getDescription() == null || grup.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Role> com.soffid.iam.iga.service.GroupService.getRolesFromGroup(com.soffid.iam.iga.api.Group grup) - grup.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetRolesFromGroup(grup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Role>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.getRolesFromGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.getRolesFromGroup", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Role> handleGetRolesFromGroup(com.soffid.iam.iga.api.Group grup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.GroupRoles> getRolesFromGroup(java.lang.String codiGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.GroupRoles> getRolesFromGroup(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.GroupRoles> com.soffid.iam.iga.service.GroupService.getRolesFromGroup(java.lang.String codiGrup) - codiGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetRolesFromGroup(codiGrup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.GroupRoles>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.getRolesFromGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.getRolesFromGroup", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.GroupRoles> handleGetRolesFromGroup(java.lang.String codiGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#java.util.Collection<com.soffid.iam.iga.api.GroupRoles> getRolesFromGroupAndParentGroup(com.soffid.iam.iga.api.Group grup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.GroupRoles> getRolesFromGroupAndParentGroup(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grup == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.GroupRoles> com.soffid.iam.iga.service.GroupService.getRolesFromGroupAndParentGroup(com.soffid.iam.iga.api.Group grup) - grup cannot be null");
		}
		if (grup.getName() == null || grup.getName().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.GroupRoles> com.soffid.iam.iga.service.GroupService.getRolesFromGroupAndParentGroup(com.soffid.iam.iga.api.Group grup) - grup.name cannot be null");
		}
		if (grup.getDescription() == null || grup.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.GroupRoles> com.soffid.iam.iga.service.GroupService.getRolesFromGroupAndParentGroup(com.soffid.iam.iga.api.Group grup) - grup.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetRolesFromGroupAndParentGroup(grup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.GroupRoles>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.getRolesFromGroupAndParentGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.getRolesFromGroupAndParentGroup", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.GroupRoles> handleGetRolesFromGroupAndParentGroup(com.soffid.iam.iga.api.Group grup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#void addGroupToUser(java.lang.String codiUsuari, java.lang.String codiGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void addGroupToUser(
		final java.lang.String codiUsuari, 
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.addGroupToUser(java.lang.String codiUsuari, java.lang.String codiGrup) - codiUsuari cannot be null");
		}
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.addGroupToUser(java.lang.String codiUsuari, java.lang.String codiGrup) - codiGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleAddGroupToUser(codiUsuari, codiGrup);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.addGroupToUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.addGroupToUser", (Throwable) __r[1]);
	}

	protected abstract void handleAddGroupToUser(java.lang.String codiUsuari, java.lang.String codiGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#void delete(com.soffid.iam.iga.api.Group grup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grup == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.delete(com.soffid.iam.iga.api.Group grup) - grup cannot be null");
		}
		if (grup.getName() == null || grup.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.delete(com.soffid.iam.iga.api.Group grup) - grup.name cannot be null");
		}
		if (grup.getDescription() == null || grup.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.delete(com.soffid.iam.iga.api.Group grup) - grup.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(grup);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.Group grup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#void delete(com.soffid.iam.iga.api.UserGroup usuariGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.UserGroup usuariGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuariGrup == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.delete(com.soffid.iam.iga.api.UserGroup usuariGrup) - usuariGrup cannot be null");
		}
		if (usuariGrup.getUser() == null || usuariGrup.getUser().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.delete(com.soffid.iam.iga.api.UserGroup usuariGrup) - usuariGrup.user cannot be null");
		}
		if (usuariGrup.getGroup() == null || usuariGrup.getGroup().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.delete(com.soffid.iam.iga.api.UserGroup usuariGrup) - usuariGrup.group cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(usuariGrup);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.UserGroup usuariGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#void propagateRolsChangesToDispatcher(java.lang.String codiGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void propagateRolsChangesToDispatcher(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.propagateRolsChangesToDispatcher(java.lang.String codiGrup) - codiGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handlePropagateRolsChangesToDispatcher(codiGrup);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.propagateRolsChangesToDispatcher", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.propagateRolsChangesToDispatcher", (Throwable) __r[1]);
	}

	protected abstract void handlePropagateRolsChangesToDispatcher(java.lang.String codiGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#void removeGroupFormUser(java.lang.String codiUsuari, java.lang.String codiGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void removeGroupFormUser(
		final java.lang.String codiUsuari, 
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.removeGroupFormUser(java.lang.String codiUsuari, java.lang.String codiGrup) - codiUsuari cannot be null");
		}
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.removeGroupFormUser(java.lang.String codiUsuari, java.lang.String codiGrup) - codiGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRemoveGroupFormUser(codiUsuari, codiGrup);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.removeGroupFormUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.removeGroupFormUser", (Throwable) __r[1]);
	}

	protected abstract void handleRemoveGroupFormUser(java.lang.String codiUsuari, java.lang.String codiGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#void setSuperGroup(java.lang.String codiSubGrup, java.lang.String codiSuperGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void setSuperGroup(
		final java.lang.String codiSubGrup, 
		final java.lang.String codiSuperGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiSubGrup == null || codiSubGrup.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.setSuperGroup(java.lang.String codiSubGrup, java.lang.String codiSuperGrup) - codiSubGrup cannot be null");
		}
		if (codiSuperGrup == null || codiSuperGrup.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.setSuperGroup(java.lang.String codiSubGrup, java.lang.String codiSuperGrup) - codiSuperGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSetSuperGroup(codiSubGrup, codiSuperGrup);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.setSuperGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.setSuperGroup", (Throwable) __r[1]);
	}

	protected abstract void handleSetSuperGroup(java.lang.String codiSubGrup, java.lang.String codiSuperGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.GroupService#	 * @see com.soffid.iam.iga.service.GroupService#void synchronize(java.lang.String groupName)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void synchronize(
		final java.lang.String groupName)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (groupName == null || groupName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.GroupService.synchronize(java.lang.String groupName) - groupName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSynchronize(groupName);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.GroupService.class).
			warn ("Error on GroupService.synchronize", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GroupService.synchronize", (Throwable) __r[1]);
	}

	protected abstract void handleSynchronize(java.lang.String groupName) throws Exception;

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
