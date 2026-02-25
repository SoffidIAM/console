//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.CustomObjectService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.CustomObjectService
 */
public abstract class CustomObjectServiceBase
	implements com.soffid.iam.iga.service.CustomObjectService
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

	private com.soffid.iam.iga.model.CustomObjectAttributeEntityDao customObjectAttributeEntityDao;

	/**
	 * Sets reference to <code>customObjectAttributeEntityDao</code>.
	 */
	public void setCustomObjectAttributeEntityDao (com.soffid.iam.iga.model.CustomObjectAttributeEntityDao customObjectAttributeEntityDao) {
		this.customObjectAttributeEntityDao = customObjectAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>customObjectAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.CustomObjectAttributeEntityDao getCustomObjectAttributeEntityDao () {
		return customObjectAttributeEntityDao;
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


	/**
	 * @see com.soffid.iam.iga.service.CustomObjectService#	 * @see com.soffid.iam.iga.service.CustomObjectService#com.soffid.iam.iga.api.CustomObject createCustomObject(com.soffid.iam.iga.api.CustomObject obj)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.CustomObject createCustomObject(
		final com.soffid.iam.iga.api.CustomObject obj)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (obj == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObject com.soffid.iam.iga.service.CustomObjectService.createCustomObject(com.soffid.iam.iga.api.CustomObject obj) - obj cannot be null");
		}
		if (obj.getName() == null || obj.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObject com.soffid.iam.iga.service.CustomObjectService.createCustomObject(com.soffid.iam.iga.api.CustomObject obj) - obj.name cannot be null");
		}
		if (obj.getDescription() == null || obj.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObject com.soffid.iam.iga.service.CustomObjectService.createCustomObject(com.soffid.iam.iga.api.CustomObject obj) - obj.description cannot be null");
		}
		if (obj.getType() == null || obj.getType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObject com.soffid.iam.iga.service.CustomObjectService.createCustomObject(com.soffid.iam.iga.api.CustomObject obj) - obj.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreateCustomObject(obj)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.CustomObject) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.CustomObjectService.class).
			warn ("Error on CustomObjectService.createCustomObject", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CustomObjectService.createCustomObject", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.CustomObject handleCreateCustomObject(com.soffid.iam.iga.api.CustomObject obj) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.CustomObjectService#	 * @see com.soffid.iam.iga.service.CustomObjectService#com.soffid.iam.iga.api.CustomObject findCustomObjectByTypeAndName(java.lang.String objectType, java.lang.String name)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.CustomObject findCustomObjectByTypeAndName(
		final java.lang.String objectType, 
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (objectType == null || objectType.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObject com.soffid.iam.iga.service.CustomObjectService.findCustomObjectByTypeAndName(java.lang.String objectType, java.lang.String name) - objectType cannot be null");
		}
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObject com.soffid.iam.iga.service.CustomObjectService.findCustomObjectByTypeAndName(java.lang.String objectType, java.lang.String name) - name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindCustomObjectByTypeAndName(objectType, name)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.CustomObject) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.CustomObjectService.class).
			warn ("Error on CustomObjectService.findCustomObjectByTypeAndName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CustomObjectService.findCustomObjectByTypeAndName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.CustomObject handleFindCustomObjectByTypeAndName(java.lang.String objectType, java.lang.String name) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.CustomObjectService#	 * @see com.soffid.iam.iga.service.CustomObjectService#com.soffid.iam.iga.api.CustomObject load(java.lang.Long id)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.CustomObject load(
		final java.lang.Long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (id == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObject com.soffid.iam.iga.service.CustomObjectService.load(java.lang.Long id) - id cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleLoad(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.CustomObject) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.CustomObjectService.class).
			warn ("Error on CustomObjectService.load", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CustomObjectService.load", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.CustomObject handleLoad(java.lang.Long id) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.CustomObjectService#	 * @see com.soffid.iam.iga.service.CustomObjectService#com.soffid.iam.iga.api.CustomObject updateCustomObject(com.soffid.iam.iga.api.CustomObject obj)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.CustomObject updateCustomObject(
		final com.soffid.iam.iga.api.CustomObject obj)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (obj == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObject com.soffid.iam.iga.service.CustomObjectService.updateCustomObject(com.soffid.iam.iga.api.CustomObject obj) - obj cannot be null");
		}
		if (obj.getName() == null || obj.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObject com.soffid.iam.iga.service.CustomObjectService.updateCustomObject(com.soffid.iam.iga.api.CustomObject obj) - obj.name cannot be null");
		}
		if (obj.getDescription() == null || obj.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObject com.soffid.iam.iga.service.CustomObjectService.updateCustomObject(com.soffid.iam.iga.api.CustomObject obj) - obj.description cannot be null");
		}
		if (obj.getType() == null || obj.getType().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.CustomObject com.soffid.iam.iga.service.CustomObjectService.updateCustomObject(com.soffid.iam.iga.api.CustomObject obj) - obj.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdateCustomObject(obj)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.CustomObject) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.CustomObjectService.class).
			warn ("Error on CustomObjectService.updateCustomObject", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CustomObjectService.updateCustomObject", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.CustomObject handleUpdateCustomObject(com.soffid.iam.iga.api.CustomObject obj) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.CustomObjectService#	 * @see com.soffid.iam.iga.service.CustomObjectService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObject> findCustomObjects(java.lang.String objectType, com.soffid.zkdb.api.Query query)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObject> findCustomObjects(
		final java.lang.String objectType, 
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (objectType == null || objectType.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObject> com.soffid.iam.iga.service.CustomObjectService.findCustomObjects(java.lang.String objectType, com.soffid.zkdb.api.Query query) - objectType cannot be null");
		}
		if (query == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObject> com.soffid.iam.iga.service.CustomObjectService.findCustomObjects(java.lang.String objectType, com.soffid.zkdb.api.Query query) - query cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindCustomObjects(objectType, query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObject>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.CustomObjectService.class).
			warn ("Error on CustomObjectService.findCustomObjects", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CustomObjectService.findCustomObjects", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObject> handleFindCustomObjects(java.lang.String objectType, com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.CustomObjectService#	 * @see com.soffid.iam.iga.service.CustomObjectService#java.util.Collection<java.lang.String> findCustomObjectNames(java.lang.String objectType)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		readOnly=true)
	public java.util.Collection<java.lang.String> findCustomObjectNames(
		final java.lang.String objectType)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (objectType == null || objectType.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<java.lang.String> com.soffid.iam.iga.service.CustomObjectService.findCustomObjectNames(java.lang.String objectType) - objectType cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindCustomObjectNames(objectType)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<java.lang.String>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.CustomObjectService.class).
			warn ("Error on CustomObjectService.findCustomObjectNames", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CustomObjectService.findCustomObjectNames", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<java.lang.String> handleFindCustomObjectNames(java.lang.String objectType) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.CustomObjectService#	 * @see com.soffid.iam.iga.service.CustomObjectService#void deleteCustomObject(com.soffid.iam.iga.api.CustomObject obj)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void deleteCustomObject(
		final com.soffid.iam.iga.api.CustomObject obj)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (obj == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.CustomObjectService.deleteCustomObject(com.soffid.iam.iga.api.CustomObject obj) - obj cannot be null");
		}
		if (obj.getName() == null || obj.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.CustomObjectService.deleteCustomObject(com.soffid.iam.iga.api.CustomObject obj) - obj.name cannot be null");
		}
		if (obj.getDescription() == null || obj.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.CustomObjectService.deleteCustomObject(com.soffid.iam.iga.api.CustomObject obj) - obj.description cannot be null");
		}
		if (obj.getType() == null || obj.getType().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.CustomObjectService.deleteCustomObject(com.soffid.iam.iga.api.CustomObject obj) - obj.type cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteCustomObject(obj);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.CustomObjectService.class).
			warn ("Error on CustomObjectService.deleteCustomObject", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CustomObjectService.deleteCustomObject", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteCustomObject(com.soffid.iam.iga.api.CustomObject obj) throws Exception;

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
