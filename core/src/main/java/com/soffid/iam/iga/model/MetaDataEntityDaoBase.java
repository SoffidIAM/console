//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity MetaDataEntity
 */
public abstract class MetaDataEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.MetaDataEntityDao
{
	com.soffid.iam.rc.model.AuditEntityDao auditEntityDao;

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

	com.soffid.iam.iga.model.CustomObjectTypeEntityDao customObjectTypeEntityDao;

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

	com.soffid.iam.base.model.TenantEntityDao tenantEntityDao;

	/**
	 * Sets reference to <code>tenantEntityDao</code>.
	 */
	public void setTenantEntityDao (com.soffid.iam.base.model.TenantEntityDao tenantEntityDao) {
		this.tenantEntityDao = tenantEntityDao;
	}

	/**
	 * Gets reference to <code>tenantEntityDao</code>.
	 */
	public com.soffid.iam.base.model.TenantEntityDao getTenantEntityDao () {
		return tenantEntityDao;
	}

	com.soffid.iam.base.model.UserDataEntityDao userDataEntityDao;

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

	com.soffid.iam.iga.model.TranslatedLabelEntityDao translatedLabelEntityDao;

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
	 * Operation findDataTypeByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.MetaDataEntity findDataTypeByName(
	    java.lang.String name)
	
	{
		return findDataTypeByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.MetaDataEntity findDataTypeByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findDataTypeByName("from com.soffid.iam.iga.model.MetaDataEntity where name = :name and tenant.id = :tenantId and scope='user'",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.MetaDataEntity findDataTypeByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.MetaDataEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.MetaDataEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.MetaDataEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByObjectTypeAndName
	 * @param type
	 * @param codi
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findByObjectTypeAndName(
	    java.lang.String type, 
	    java.lang.String codi)
	
	{
		return findByObjectTypeAndName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, type, codi);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findByObjectTypeAndName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type, java.lang.String codi)
	
	{
		return findByObjectTypeAndName("from com.soffid.iam.iga.model.MetaDataEntity tipusDada where (:codi is null or tipusDada.name like :codi) and   (tipusDada.objectType.name = :type or :type is null ) and tipusDada.tenant.id = :tenantId",
			criteria, type, codi);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findByObjectTypeAndName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type, java.lang.String codi)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("type", type, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("codi", codi, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.MetaDataEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByScope
	 * @param scope
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findByScope(
	    com.soffid.iam.base.api.MetadataScope scope)
	
	{
		return findByScope((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, scope);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findByScope(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.api.MetadataScope scope)
	
	{
		return findByScope("from com.soffid.iam.iga.model.MetaDataEntity where tenant.id=:tenantId and scope=:scope",
			criteria, scope);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findByScope(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.api.MetadataScope scope)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("scope", scope);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.MetaDataEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findDataTypesByName
	 * @param name
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findDataTypesByName(
	    java.lang.String name)
	
	{
		return findDataTypesByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findDataTypesByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findDataTypesByName("from com.soffid.iam.iga.model.MetaDataEntity tipusDada where (:name is null or tipusDada.name like :name) and tipusDada.tenant.id = :tenantId",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findDataTypesByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.MetaDataEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findDataTypesByScopeAndName
	 * @param scope
	 * @param codi
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findDataTypesByScopeAndName(
	    com.soffid.iam.base.api.MetadataScope scope, 
	    java.lang.String codi)
	
	{
		return findDataTypesByScopeAndName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, scope, codi);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findDataTypesByScopeAndName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.api.MetadataScope scope, java.lang.String codi)
	
	{
		return findDataTypesByScopeAndName("from com.soffid.iam.iga.model.MetaDataEntity tipusDada where (:codi is null or tipusDada.name like :codi) and   (tipusDada.scope = :scope or :scope is null ) and tipusDada.tenant.id = :tenantId",
			criteria, scope, codi);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findDataTypesByScopeAndName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.api.MetadataScope scope, java.lang.String codi)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("scope", scope);
			queryObject.setParameter("codi", codi, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.MetaDataEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.iga.model.MetaDataEntity#	 * @see com.soffid.iam.iga.model.MetaDataEntity#void renameAttributeValues(com.soffid.iam.base.api.TypeEnumeration type, java.lang.String oldValue, java.lang.String newValue)
	 */
	public void renameAttributeValues(
		com.soffid.iam.base.api.TypeEnumeration type, 
		java.lang.String oldValue, 
		java.lang.String newValue)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (type == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.MetaDataEntity.renameAttributeValues(com.soffid.iam.base.api.TypeEnumeration type, java.lang.String oldValue, java.lang.String newValue) - type cannot be null");
		}
		if (oldValue == null || oldValue.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.MetaDataEntity.renameAttributeValues(com.soffid.iam.base.api.TypeEnumeration type, java.lang.String oldValue, java.lang.String newValue) - oldValue cannot be null");
		}
		if (newValue == null || newValue.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.MetaDataEntity.renameAttributeValues(com.soffid.iam.base.api.TypeEnumeration type, java.lang.String oldValue, java.lang.String newValue) - newValue cannot be null");
		}
		try
		{
			handleRenameAttributeValues(type, oldValue, newValue);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.model.MetaDataEntity.class).
				warn ("Error on MetaDataEntity.renameAttributeValues", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on MetaDataEntity.renameAttributeValues: "+th.toString(), th);
		}
	}

	protected abstract void handleRenameAttributeValues(com.soffid.iam.base.api.TypeEnumeration type, java.lang.String oldValue, java.lang.String newValue) throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.DataType} object 
	 */
	public void toDataType(com.soffid.iam.iga.model.MetaDataEntity source, com.soffid.iam.base.api.DataType target) {
		// Attributes for DataType
		target.setName(source.getName());
		target.setOrder(source.getOrder());
		target.setId(source.getId());
		target.setScope(source.getScope());
		// Incompatible types source.objectType and target.objectType
		// Incompatible types source.dataObjectType and target.dataObjectType
		target.setType(source.getType());
		target.setSize(source.getSize());
		target.setRequired(java.lang.Boolean.TRUE.equals(source.getRequired()));
		target.setReadOnly(java.lang.Boolean.TRUE.equals(source.getReadOnly()));
		target.setMultiLine(java.lang.Boolean.TRUE.equals(source.getMultiLine()));
		target.setMultiValued(java.lang.Boolean.TRUE.equals(source.getMultiValued()));
		target.setSearchCriteria(source.getSearchCriteria());
		target.setMultiValuedRows(source.getMultiValuedRows());
		target.setLabel(source.getLabel());
		target.setHint(source.getHint());
		target.setNlsLabel(source.getNlsLabel());
		// Missing attribute nlsLabels on entity
		target.setDescription(source.getDescription());
		// Incompatible types source.values and target.values
		target.setAdminVisibility(source.getAdminVisibility());
		target.setOperatorVisibility(source.getOperatorVisibility());
		target.setUserVisibility(source.getUserVisibility());
		// Missing attribute systemName on entity
		target.setUnique(source.getUnique());
		target.setVisibilityExpression(source.getVisibilityExpression());
		target.setValidationExpression(source.getValidationExpression());
		target.setFilterExpression(source.getFilterExpression());
		target.setOnLoadTrigger(source.getOnLoadTrigger());
		target.setOnChangeTrigger(source.getOnChangeTrigger());
		target.setOnFocusTrigger(source.getOnFocusTrigger());
		target.setValidator(source.getValidator());
		target.setEnumeration(source.getEnumeration());
		target.setBuiltin(source.getBuiltin());
		target.setBuiltinHandler(source.getBuiltinHandler());
		target.setLetterCase(source.getLetterCase());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.DataType} object 
	 */
	public com.soffid.iam.base.api.DataType toDataType(com.soffid.iam.iga.model.MetaDataEntity entity) {
		final com.soffid.iam.base.api.DataType target = new com.soffid.iam.base.api.DataType();
		this.toDataType(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.DataType} list 
	 */
	public java.util.List<com.soffid.iam.base.api.DataType> toDataTypeList (java.util.Collection<com.soffid.iam.iga.model.MetaDataEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.DataType> list =
				new java.util.LinkedList<com.soffid.iam.base.api.DataType>();
			for (final com.soffid.iam.iga.model.MetaDataEntity instance: instances)
			{
				list.add( toDataType(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.DataType} object 
	 */
	public void dataTypeToEntity (com.soffid.iam.base.api.DataType source, com.soffid.iam.iga.model.MetaDataEntity target, boolean copyIfNull) {
		// Attributes for MetaDataEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getOrder() != null)
		{
			target.setOrder(source.getOrder());
		}
		// Missing attribute data on entity
		if (copyIfNull || source.getScope() != null)
		{
			target.setScope(source.getScope());
		}
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		if (copyIfNull || source.getSize() != null)
		{
			target.setSize(source.getSize());
		}
		target.setRequired(new java.lang.Boolean(source.isRequired()));
		target.setReadOnly(new java.lang.Boolean(source.isReadOnly()));
		target.setMultiValued(new java.lang.Boolean(source.isMultiValued()));
		target.setMultiLine(new java.lang.Boolean(source.isMultiLine()));
		if (copyIfNull || source.getSearchCriteria() != null)
		{
			target.setSearchCriteria(source.getSearchCriteria());
		}
		if (copyIfNull || source.getMultiValuedRows() != null)
		{
			target.setMultiValuedRows(source.getMultiValuedRows());
		}
		if (copyIfNull || source.getValues() != null)
		{
			// Incompatible types source.values and target.values
		}
		if (copyIfNull || source.getLabel() != null)
		{
			target.setLabel(source.getLabel());
		}
		if (copyIfNull || source.getHint() != null)
		{
			target.setHint(source.getHint());
		}
		if (copyIfNull || source.getNlsLabel() != null)
		{
			target.setNlsLabel(source.getNlsLabel());
		}
		if (copyIfNull || source.getBuiltinHandler() != null)
		{
			target.setBuiltinHandler(source.getBuiltinHandler());
		}
		if (copyIfNull || source.getAdminVisibility() != null)
		{
			target.setAdminVisibility(source.getAdminVisibility());
		}
		if (copyIfNull || source.getOperatorVisibility() != null)
		{
			target.setOperatorVisibility(source.getOperatorVisibility());
		}
		if (copyIfNull || source.getUserVisibility() != null)
		{
			target.setUserVisibility(source.getUserVisibility());
		}
		if (copyIfNull || source.getUnique() != null)
		{
			target.setUnique(source.getUnique());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getObjectType() != null)
		{
			// Incompatible types source.objectType and target.objectType
		}
		if (copyIfNull || source.getDataObjectType() != null)
		{
			// Incompatible types source.dataObjectType and target.dataObjectType
		}
		if (copyIfNull || source.getVisibilityExpression() != null)
		{
			target.setVisibilityExpression(source.getVisibilityExpression());
		}
		if (copyIfNull || source.getValidationExpression() != null)
		{
			target.setValidationExpression(source.getValidationExpression());
		}
		if (copyIfNull || source.getOnLoadTrigger() != null)
		{
			target.setOnLoadTrigger(source.getOnLoadTrigger());
		}
		if (copyIfNull || source.getOnChangeTrigger() != null)
		{
			target.setOnChangeTrigger(source.getOnChangeTrigger());
		}
		if (copyIfNull || source.getOnFocusTrigger() != null)
		{
			target.setOnFocusTrigger(source.getOnFocusTrigger());
		}
		if (copyIfNull || source.getValidator() != null)
		{
			target.setValidator(source.getValidator());
		}
		if (copyIfNull || source.getEnumeration() != null)
		{
			target.setEnumeration(source.getEnumeration());
		}
		// Missing attribute tenant on entity
		if (copyIfNull || source.getFilterExpression() != null)
		{
			target.setFilterExpression(source.getFilterExpression());
		}
		if (copyIfNull || source.getBuiltin() != null)
		{
			target.setBuiltin(source.getBuiltin());
		}
		if (copyIfNull || source.getLetterCase() != null)
		{
			target.setLetterCase(source.getLetterCase());
		}
		// Missing attribute translations on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.DataType} object 
	 */
	public com.soffid.iam.iga.model.MetaDataEntity dataTypeToEntity (com.soffid.iam.base.api.DataType instance) {
		com.soffid.iam.iga.model.MetaDataEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newMetaDataEntity();
		dataTypeToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.DataType} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity>  dataTypeToEntityList (java.util.Collection<com.soffid.iam.base.api.DataType> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.MetaDataEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.MetaDataEntity>();
		for (com.soffid.iam.base.api.DataType instance: instances)
		{
			list.add (dataTypeToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.MetaDataEntity} .
	 */
	public com.soffid.iam.iga.model.MetaDataEntity newMetaDataEntity()
	{
		return new com.soffid.iam.iga.model.MetaDataEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.MetaDataEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.MetaDataEntity result = (com.soffid.iam.iga.model.MetaDataEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.MetaDataEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.MetaDataEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.MetaDataEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.MetaDataEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.MetaDataEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MetaDataEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.MetaDataEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.MetaDataEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MetaDataEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.MetaDataEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MetaDataEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.MetaDataEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.MetaDataEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MetaDataEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MetaDataEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.MetaDataEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.MetaDataEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MetaDataEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MetaDataEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.MetaDataEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MetaDataEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MetaDataEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"MetaDataEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.MetaDataEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.MetaDataEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.MetaDataEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
