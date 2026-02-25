//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.base.service.AdditionalDataService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.base.service.AdditionalDataService
 */
public abstract class AdditionalDataServiceBase
	implements com.soffid.iam.base.service.AdditionalDataService
 {
	private com.soffid.iam.base.model.AccountMetadataEntityDao accountMetadataEntityDao;

	/**
	 * Sets reference to <code>accountMetadataEntityDao</code>.
	 */
	public void setAccountMetadataEntityDao (com.soffid.iam.base.model.AccountMetadataEntityDao accountMetadataEntityDao) {
		this.accountMetadataEntityDao = accountMetadataEntityDao;
	}

	/**
	 * Gets reference to <code>accountMetadataEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountMetadataEntityDao getAccountMetadataEntityDao () {
		return accountMetadataEntityDao;
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

	private com.soffid.iam.impl.service.CrudRegistryService crudRegistryService;

	/**
	 * Sets reference to <code>crudRegistryService</code>.
	 */
	public void setCrudRegistryService (com.soffid.iam.impl.service.CrudRegistryService crudRegistryService) {
		this.crudRegistryService = crudRegistryService;
	}

	/**
	 * Gets reference to <code>crudRegistryService</code>.
	 */
	public com.soffid.iam.impl.service.CrudRegistryService getCrudRegistryService () {
		return crudRegistryService;
	}

	private com.soffid.iam.iga.model.CustomObjectEntityDao customObjectEntityDao;

	/**
	 * Sets reference to <code>customObjectEntityDao</code>.
	 */
	public void setCustomObjectEntityDao (com.soffid.iam.iga.model.CustomObjectEntityDao customObjectEntityDao) {
		this.customObjectEntityDao = customObjectEntityDao;
	}

	/**
	 * Gets reference to <code>customObjectEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.CustomObjectEntityDao getCustomObjectEntityDao () {
		return customObjectEntityDao;
	}

	private com.soffid.iam.iga.model.CustomObjectRoleEntityDao customObjectRoleEntityDao;

	/**
	 * Sets reference to <code>customObjectRoleEntityDao</code>.
	 */
	public void setCustomObjectRoleEntityDao (com.soffid.iam.iga.model.CustomObjectRoleEntityDao customObjectRoleEntityDao) {
		this.customObjectRoleEntityDao = customObjectRoleEntityDao;
	}

	/**
	 * Gets reference to <code>customObjectRoleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.CustomObjectRoleEntityDao getCustomObjectRoleEntityDao () {
		return customObjectRoleEntityDao;
	}

	private com.soffid.iam.iga.model.CustomObjectTypeEntityDao customObjectTypeEntityDao;

	/**
	 * Sets reference to <code>customObjectTypeEntityDao</code>.
	 */
	public void setCustomObjectTypeEntityDao (com.soffid.iam.iga.model.CustomObjectTypeEntityDao customObjectTypeEntityDao) {
		this.customObjectTypeEntityDao = customObjectTypeEntityDao;
	}

	/**
	 * Gets reference to <code>customObjectTypeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntityDao getCustomObjectTypeEntityDao () {
		return customObjectTypeEntityDao;
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

	private com.soffid.iam.base.service.GenAIProviderService genAIProviderService;

	/**
	 * Sets reference to <code>genAIProviderService</code>.
	 */
	public void setGenAIProviderService (com.soffid.iam.base.service.GenAIProviderService genAIProviderService) {
		this.genAIProviderService = genAIProviderService;
	}

	/**
	 * Gets reference to <code>genAIProviderService</code>.
	 */
	public com.soffid.iam.base.service.GenAIProviderService getGenAIProviderService () {
		return genAIProviderService;
	}

	private com.soffid.iam.impl.service.LuceneIndexService luceneIndexService;

	/**
	 * Sets reference to <code>luceneIndexService</code>.
	 */
	public void setLuceneIndexService (com.soffid.iam.impl.service.LuceneIndexService luceneIndexService) {
		this.luceneIndexService = luceneIndexService;
	}

	/**
	 * Gets reference to <code>luceneIndexService</code>.
	 */
	public com.soffid.iam.impl.service.LuceneIndexService getLuceneIndexService () {
		return luceneIndexService;
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

	private com.soffid.iam.iga.model.TranslatedLabelEntityDao translatedLabelEntityDao;

	/**
	 * Sets reference to <code>translatedLabelEntityDao</code>.
	 */
	public void setTranslatedLabelEntityDao (com.soffid.iam.iga.model.TranslatedLabelEntityDao translatedLabelEntityDao) {
		this.translatedLabelEntityDao = translatedLabelEntityDao;
	}

	/**
	 * Gets reference to <code>translatedLabelEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.TranslatedLabelEntityDao getTranslatedLabelEntityDao () {
		return translatedLabelEntityDao;
	}

	private com.soffid.iam.base.model.UserDataEntityDao userDataEntityDao;

	/**
	 * Sets reference to <code>userDataEntityDao</code>.
	 */
	public void setUserDataEntityDao (com.soffid.iam.base.model.UserDataEntityDao userDataEntityDao) {
		this.userDataEntityDao = userDataEntityDao;
	}

	/**
	 * Gets reference to <code>userDataEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserDataEntityDao getUserDataEntityDao () {
		return userDataEntityDao;
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.AccountAccessLevelEnum getAccessLevel(com.soffid.iam.iga.api.CustomObjectType type)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.AccountAccessLevelEnum getAccessLevel(
		final com.soffid.iam.iga.api.CustomObjectType type)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (type == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AccountAccessLevelEnum com.soffid.iam.base.service.AdditionalDataService.getAccessLevel(com.soffid.iam.iga.api.CustomObjectType type) - type cannot be null");
		}
		if (type.getName() == null || type.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AccountAccessLevelEnum com.soffid.iam.base.service.AdditionalDataService.getAccessLevel(com.soffid.iam.iga.api.CustomObjectType type) - type.name cannot be null");
		}
		if (type.getDescription() == null || type.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AccountAccessLevelEnum com.soffid.iam.base.service.AdditionalDataService.getAccessLevel(com.soffid.iam.iga.api.CustomObjectType type) - type.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAccessLevel(type)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.AccountAccessLevelEnum) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.getAccessLevel", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.getAccessLevel", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.AccountAccessLevelEnum handleGetAccessLevel(com.soffid.iam.iga.api.CustomObjectType type) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.DataType create(com.soffid.iam.base.api.DataType tipusDada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.DataType create(
		final com.soffid.iam.base.api.DataType tipusDada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipusDada == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.DataType com.soffid.iam.base.service.AdditionalDataService.create(com.soffid.iam.base.api.DataType tipusDada) - tipusDada cannot be null");
		}
		if (tipusDada.getName() == null || tipusDada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.DataType com.soffid.iam.base.service.AdditionalDataService.create(com.soffid.iam.base.api.DataType tipusDada) - tipusDada.name cannot be null");
		}
		if (tipusDada.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.DataType com.soffid.iam.base.service.AdditionalDataService.create(com.soffid.iam.base.api.DataType tipusDada) - tipusDada.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(tipusDada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.DataType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.DataType handleCreate(com.soffid.iam.base.api.DataType tipusDada) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.DataType findSystemDataType(java.lang.String system, java.lang.String name)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.DataType findSystemDataType(
		final java.lang.String system, 
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (system == null || system.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.DataType com.soffid.iam.base.service.AdditionalDataService.findSystemDataType(java.lang.String system, java.lang.String name) - system cannot be null");
		}
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.DataType com.soffid.iam.base.service.AdditionalDataService.findSystemDataType(java.lang.String system, java.lang.String name) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSystemDataType(system, name)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.DataType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findSystemDataType", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findSystemDataType", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.DataType handleFindSystemDataType(java.lang.String system, java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.DataType findDataTypeByName(java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.DataType findDataTypeByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.DataType com.soffid.iam.base.service.AdditionalDataService.findDataTypeByName(java.lang.String codi) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDataTypeByName(codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.DataType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findDataTypeByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findDataTypeByName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.DataType handleFindDataTypeByName(java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.DataType update(com.soffid.iam.base.api.DataType tipusDada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.DataType update(
		final com.soffid.iam.base.api.DataType tipusDada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipusDada == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.DataType com.soffid.iam.base.service.AdditionalDataService.update(com.soffid.iam.base.api.DataType tipusDada) - tipusDada cannot be null");
		}
		if (tipusDada.getName() == null || tipusDada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.DataType com.soffid.iam.base.service.AdditionalDataService.update(com.soffid.iam.base.api.DataType tipusDada) - tipusDada.name cannot be null");
		}
		if (tipusDada.getType() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.DataType com.soffid.iam.base.service.AdditionalDataService.update(com.soffid.iam.base.api.DataType tipusDada) - tipusDada.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(tipusDada)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.DataType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.DataType handleUpdate(com.soffid.iam.base.api.DataType tipusDada) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.UserData create(com.soffid.iam.base.api.UserData dadaUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.UserData create(
		final com.soffid.iam.base.api.UserData dadaUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dadaUsuari == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.base.service.AdditionalDataService.create(com.soffid.iam.base.api.UserData dadaUsuari) - dadaUsuari cannot be null");
		}
		if (dadaUsuari.getAttribute() == null || dadaUsuari.getAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.base.service.AdditionalDataService.create(com.soffid.iam.base.api.UserData dadaUsuari) - dadaUsuari.attribute cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(dadaUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.UserData) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.UserData handleCreate(com.soffid.iam.base.api.UserData dadaUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.UserData update(com.soffid.iam.base.api.UserData dadaUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.base.api.UserData update(
		final com.soffid.iam.base.api.UserData dadaUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dadaUsuari == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.base.service.AdditionalDataService.update(com.soffid.iam.base.api.UserData dadaUsuari) - dadaUsuari cannot be null");
		}
		if (dadaUsuari.getAttribute() == null || dadaUsuari.getAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.UserData com.soffid.iam.base.service.AdditionalDataService.update(com.soffid.iam.base.api.UserData dadaUsuari) - dadaUsuari.attribute cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(dadaUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.base.api.UserData) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.base.api.UserData handleUpdate(com.soffid.iam.base.api.UserData dadaUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.iga.api.CustomObjectType createCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.CustomObjectType createCustomObjectType(
		final com.soffid.iam.iga.api.CustomObjectType obj)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (obj == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObjectType com.soffid.iam.base.service.AdditionalDataService.createCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj) - obj cannot be null");
		}
		if (obj.getName() == null || obj.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObjectType com.soffid.iam.base.service.AdditionalDataService.createCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj) - obj.name cannot be null");
		}
		if (obj.getDescription() == null || obj.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObjectType com.soffid.iam.base.service.AdditionalDataService.createCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj) - obj.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateCustomObjectType(obj)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.CustomObjectType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.createCustomObjectType", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.createCustomObjectType", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.CustomObjectType handleCreateCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.iga.api.CustomObjectType findCustomObjectTypeByName(java.lang.String name)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.CustomObjectType findCustomObjectTypeByName(
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObjectType com.soffid.iam.base.service.AdditionalDataService.findCustomObjectTypeByName(java.lang.String name) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindCustomObjectTypeByName(name)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.CustomObjectType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findCustomObjectTypeByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findCustomObjectTypeByName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.CustomObjectType handleFindCustomObjectTypeByName(java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.iga.api.CustomObjectType updateCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.CustomObjectType updateCustomObjectType(
		final com.soffid.iam.iga.api.CustomObjectType obj)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (obj == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObjectType com.soffid.iam.base.service.AdditionalDataService.updateCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj) - obj cannot be null");
		}
		if (obj.getName() == null || obj.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObjectType com.soffid.iam.base.service.AdditionalDataService.updateCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj) - obj.name cannot be null");
		}
		if (obj.getDescription() == null || obj.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObjectType com.soffid.iam.base.service.AdditionalDataService.updateCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj) - obj.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateCustomObjectType(obj)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.CustomObjectType) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.updateCustomObjectType", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.updateCustomObjectType", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.CustomObjectType handleUpdateCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.iga.api.ExtensibleObjectRegister findExtensibleObjectRegister(java.lang.String name)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ExtensibleObjectRegister findExtensibleObjectRegister(
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ExtensibleObjectRegister com.soffid.iam.base.service.AdditionalDataService.findExtensibleObjectRegister(java.lang.String name) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindExtensibleObjectRegister(name)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ExtensibleObjectRegister) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findExtensibleObjectRegister", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findExtensibleObjectRegister", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ExtensibleObjectRegister handleFindExtensibleObjectRegister(java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObjectType> findCustomObjectType(com.soffid.zkdb.api.Query query)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObjectType> findCustomObjectType(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindCustomObjectType(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObjectType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findCustomObjectType", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findCustomObjectType", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObjectType> handleFindCustomObjectType(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypes(com.soffid.iam.base.api.MetadataScope scope)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypes(
		final com.soffid.iam.base.api.MetadataScope scope)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (scope == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.DataType> com.soffid.iam.base.service.AdditionalDataService.findDataTypes(com.soffid.iam.base.api.MetadataScope scope) - scope cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDataTypes(scope)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.DataType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findDataTypes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findDataTypes", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.DataType> handleFindDataTypes(com.soffid.iam.base.api.MetadataScope scope) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypes2(com.soffid.iam.base.api.MetadataScope scope)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypes2(
		final com.soffid.iam.base.api.MetadataScope scope)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (scope == null) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.DataType> com.soffid.iam.base.service.AdditionalDataService.findDataTypes2(com.soffid.iam.base.api.MetadataScope scope) - scope cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDataTypes2(scope)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.DataType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findDataTypes2", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findDataTypes2", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.DataType> handleFindDataTypes2(com.soffid.iam.base.api.MetadataScope scope) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByObjectTypeAndName(java.lang.String objectType, java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByObjectTypeAndName(
		final java.lang.String objectType, 
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (objectType == null || objectType.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.DataType> com.soffid.iam.base.service.AdditionalDataService.findDataTypesByObjectTypeAndName(java.lang.String objectType, java.lang.String codi) - objectType cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDataTypesByObjectTypeAndName(objectType, codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.DataType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findDataTypesByObjectTypeAndName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findDataTypesByObjectTypeAndName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.DataType> handleFindDataTypesByObjectTypeAndName(java.lang.String objectType, java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByObjectTypeAndName2(java.lang.String objectType, java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByObjectTypeAndName2(
		final java.lang.String objectType, 
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (objectType == null || objectType.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.DataType> com.soffid.iam.base.service.AdditionalDataService.findDataTypesByObjectTypeAndName2(java.lang.String objectType, java.lang.String codi) - objectType cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDataTypesByObjectTypeAndName2(objectType, codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.DataType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findDataTypesByObjectTypeAndName2", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findDataTypesByObjectTypeAndName2", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.DataType> handleFindDataTypesByObjectTypeAndName2(java.lang.String objectType, java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByName(java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.DataType> com.soffid.iam.base.service.AdditionalDataService.findDataTypesByName(java.lang.String codi) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDataTypesByName(codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.DataType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findDataTypesByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findDataTypesByName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.DataType> handleFindDataTypesByName(java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByScopeAndName(com.soffid.iam.base.api.MetadataScope scope, java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByScopeAndName(
		final com.soffid.iam.base.api.MetadataScope scope, 
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindDataTypesByScopeAndName(scope, codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.DataType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findDataTypesByScopeAndName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findDataTypesByScopeAndName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.DataType> handleFindDataTypesByScopeAndName(com.soffid.iam.base.api.MetadataScope scope, java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> getDataTypes()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.DataType> getDataTypes()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDataTypes()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.DataType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.getDataTypes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.getDataTypes", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.DataType> handleGetDataTypes() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.List<com.soffid.iam.iga.api.ExtensibleObjectRegister> findExtensibleObjectRegisters()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.iga.api.ExtensibleObjectRegister> findExtensibleObjectRegisters()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindExtensibleObjectRegisters()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.iga.api.ExtensibleObjectRegister>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findExtensibleObjectRegisters", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findExtensibleObjectRegisters", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.iga.api.ExtensibleObjectRegister> handleFindExtensibleObjectRegisters() throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.List<com.soffid.iam.base.api.DataType> findSystemDataTypes(java.lang.String system)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.DataType> findSystemDataTypes(
		final java.lang.String system)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (system == null || system.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.DataType> com.soffid.iam.base.service.AdditionalDataService.findSystemDataTypes(java.lang.String system) - system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSystemDataTypes(system)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.DataType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findSystemDataTypes", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findSystemDataTypes", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.DataType> handleFindSystemDataTypes(java.lang.String system) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.List<com.soffid.iam.base.api.DataType> findSystemDataTypes2(java.lang.String system)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.base.api.DataType> findSystemDataTypes2(
		final java.lang.String system)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (system == null || system.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.base.api.DataType> com.soffid.iam.base.service.AdditionalDataService.findSystemDataTypes2(java.lang.String system) - system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindSystemDataTypes2(system)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<com.soffid.iam.base.api.DataType>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.findSystemDataTypes2", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.findSystemDataTypes2", (Throwable) __r[1]);
	}

	protected abstract java.util.List<com.soffid.iam.base.api.DataType> handleFindSystemDataTypes2(java.lang.String system) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.List<java.lang.String> getGenAIMetadata(java.lang.String engine)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<java.lang.String> getGenAIMetadata(
		final java.lang.String engine)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (engine == null || engine.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<java.lang.String> com.soffid.iam.base.service.AdditionalDataService.getGenAIMetadata(java.lang.String engine) - engine cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetGenAIMetadata(engine)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.getGenAIMetadata", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.getGenAIMetadata", (Throwable) __r[1]);
	}

	protected abstract java.util.List<java.lang.String> handleGetGenAIMetadata(java.lang.String engine) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#void delete(com.soffid.iam.base.api.DataType tipusDada)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.base.api.DataType tipusDada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tipusDada == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.delete(com.soffid.iam.base.api.DataType tipusDada) - tipusDada cannot be null");
		}
		if (tipusDada.getName() == null || tipusDada.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.delete(com.soffid.iam.base.api.DataType tipusDada) - tipusDada.name cannot be null");
		}
		if (tipusDada.getType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.delete(com.soffid.iam.base.api.DataType tipusDada) - tipusDada.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(tipusDada);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.base.api.DataType tipusDada) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#void delete(com.soffid.iam.base.api.UserData dadaUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.base.api.UserData dadaUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dadaUsuari == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.delete(com.soffid.iam.base.api.UserData dadaUsuari) - dadaUsuari cannot be null");
		}
		if (dadaUsuari.getAttribute() == null || dadaUsuari.getAttribute().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.delete(com.soffid.iam.base.api.UserData dadaUsuari) - dadaUsuari.attribute cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(dadaUsuari);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.base.api.UserData dadaUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#void deleteCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void deleteCustomObjectType(
		final com.soffid.iam.iga.api.CustomObjectType obj)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (obj == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.deleteCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj) - obj cannot be null");
		}
		if (obj.getName() == null || obj.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.deleteCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj) - obj.name cannot be null");
		}
		if (obj.getDescription() == null || obj.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.deleteCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj) - obj.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteCustomObjectType(obj);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.deleteCustomObjectType", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.deleteCustomObjectType", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#void registerExtensibleObject(com.soffid.iam.iga.api.ExtensibleObjectRegister register)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerExtensibleObject(
		final com.soffid.iam.iga.api.ExtensibleObjectRegister register)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (register == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.registerExtensibleObject(com.soffid.iam.iga.api.ExtensibleObjectRegister register) - register cannot be null");
		}
		if (register.getName() == null || register.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.registerExtensibleObject(com.soffid.iam.iga.api.ExtensibleObjectRegister register) - register.name cannot be null");
		}
		if (register.getDescsription() == null || register.getDescsription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.registerExtensibleObject(com.soffid.iam.iga.api.ExtensibleObjectRegister register) - register.descsription cannot be null");
		}
		if (register.getClassName() == null || register.getClassName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.registerExtensibleObject(com.soffid.iam.iga.api.ExtensibleObjectRegister register) - register.className cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterExtensibleObject(register);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.registerExtensibleObject", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.registerExtensibleObject", (Throwable) __r[1]);
	}

	protected abstract void handleRegisterExtensibleObject(com.soffid.iam.iga.api.ExtensibleObjectRegister register) throws Exception;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#	 * @see com.soffid.iam.base.service.AdditionalDataService#void registerStandardObject(java.lang.String resourceName, com.soffid.iam.base.api.MetadataScope scope, boolean reset)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerStandardObject(
		final java.lang.String resourceName, 
		final com.soffid.iam.base.api.MetadataScope scope, 
		final boolean reset)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (resourceName == null || resourceName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.base.service.AdditionalDataService.registerStandardObject(java.lang.String resourceName, com.soffid.iam.base.api.MetadataScope scope, boolean reset) - resourceName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterStandardObject(resourceName, scope, reset);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.service.AdditionalDataService.class).
			warn ("Error on AdditionalDataService.registerStandardObject", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AdditionalDataService.registerStandardObject", (Throwable) __r[1]);
	}

	protected abstract void handleRegisterStandardObject(java.lang.String resourceName, com.soffid.iam.base.api.MetadataScope scope, boolean reset) throws Exception;

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
