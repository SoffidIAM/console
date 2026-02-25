//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity AccountMetadataEntity
 */
public abstract class AccountMetadataEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.AccountMetadataEntityDao
{
	com.soffid.iam.base.model.AccountAttributeEntityDao accountAttributeEntityDao;

	/**
	 * Sets reference to <code>accountAttributeEntityDao</code>.
	 */
	public void setAccountAttributeEntityDao (com.soffid.iam.base.model.AccountAttributeEntityDao accountAttributeEntityDao) {
		this.accountAttributeEntityDao = accountAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>accountAttributeEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountAttributeEntityDao getAccountAttributeEntityDao () {
		return accountAttributeEntityDao;
	}

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

	com.soffid.iam.iga.model.SystemEntityDao systemEntityDao;

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
	 * Operation findByName
	 * @param system
	 * @param name
	 * @return
	**/
	public com.soffid.iam.base.model.AccountMetadataEntity findByName(
	    java.lang.String system, 
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, system, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.AccountMetadataEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.base.model.AccountMetadataEntity where system.name = :system and name = :name\nand system.tenant.id=:tenantId",
			criteria, system, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.AccountMetadataEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.AccountMetadataEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.AccountMetadataEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.AccountMetadataEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findBySystem
	 * @param systemName
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity> findBySystem(
	    java.lang.String systemName)
	
	{
		return findBySystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, systemName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity> findBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	
	{
		return findBySystem("from com.soffid.iam.base.model.AccountMetadataEntity where system.name = :systemName\nand system.tenant.id=:tenantId order by order",
			criteria, systemName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity> findBySystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("systemName", systemName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.AccountMetadataEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.DataType} object 
	 */
	public void toDataType(com.soffid.iam.base.model.AccountMetadataEntity source, com.soffid.iam.base.api.DataType target) {
		// Attributes for DataType
		target.setName(source.getName());
		target.setOrder(source.getOrder());
		target.setId(source.getId());
		// Missing attribute scope on entity
		// Missing attribute objectType on entity
		// Incompatible types source.dataObjectType and target.dataObjectType
		target.setType(source.getType());
		target.setSize(source.getSize());
		target.setRequired(java.lang.Boolean.TRUE.equals(source.getRequired()));
		// Missing attribute readOnly on entity
		// Missing attribute multiLine on entity
		target.setMultiValued(java.lang.Boolean.TRUE.equals(source.getMultiValued()));
		// Missing attribute searchCriteria on entity
		target.setMultiValuedRows(source.getMultiValuedRows());
		target.setLabel(source.getLabel());
		// Missing attribute hint on entity
		// Missing attribute nlsLabel on entity
		// Missing attribute nlsLabels on entity
		// Missing attribute description on entity
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
		// Missing attribute validator on entity
		// Missing attribute enumeration on entity
		// Missing attribute builtin on entity
		// Missing attribute builtinHandler on entity
		// Missing attribute letterCase on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.DataType} object 
	 */
	public com.soffid.iam.base.api.DataType toDataType(com.soffid.iam.base.model.AccountMetadataEntity entity) {
		final com.soffid.iam.base.api.DataType target = new com.soffid.iam.base.api.DataType();
		this.toDataType(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.DataType} list 
	 */
	public java.util.List<com.soffid.iam.base.api.DataType> toDataTypeList (java.util.Collection<com.soffid.iam.base.model.AccountMetadataEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.DataType> list =
				new java.util.LinkedList<com.soffid.iam.base.api.DataType>();
			for (final com.soffid.iam.base.model.AccountMetadataEntity instance: instances)
			{
				list.add( toDataType(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.DataType} object 
	 */
	public void dataTypeToEntity (com.soffid.iam.base.api.DataType source, com.soffid.iam.base.model.AccountMetadataEntity target, boolean copyIfNull) {
		// Attributes for AccountMetadataEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		// Missing attribute system on entity
		if (copyIfNull || source.getOrder() != null)
		{
			target.setOrder(source.getOrder());
		}
		// Missing attribute data on entity
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		if (copyIfNull || source.getSize() != null)
		{
			target.setSize(source.getSize());
		}
		target.setRequired(new java.lang.Boolean(source.isRequired()));
		target.setMultiValued(new java.lang.Boolean(source.isMultiValued()));
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
		if (copyIfNull || source.getFilterExpression() != null)
		{
			target.setFilterExpression(source.getFilterExpression());
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
		// Missing attribute translations on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.DataType} object 
	 */
	public com.soffid.iam.base.model.AccountMetadataEntity dataTypeToEntity (com.soffid.iam.base.api.DataType instance) {
		com.soffid.iam.base.model.AccountMetadataEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newAccountMetadataEntity();
		dataTypeToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.DataType} list 
	 */
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity>  dataTypeToEntityList (java.util.Collection<com.soffid.iam.base.api.DataType> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.AccountMetadataEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.AccountMetadataEntity>();
		for (com.soffid.iam.base.api.DataType instance: instances)
		{
			list.add (dataTypeToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.AccountMetadataEntity} .
	 */
	public com.soffid.iam.base.model.AccountMetadataEntity newAccountMetadataEntity()
	{
		return new com.soffid.iam.base.model.AccountMetadataEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.AccountMetadataEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.AccountMetadataEntity result = (com.soffid.iam.base.model.AccountMetadataEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.AccountMetadataEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.base.model.AccountMetadataEntity where system.tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.base.model.AccountMetadataEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.AccountMetadataEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.AccountMetadataEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountMetadataEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.AccountMetadataEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.AccountMetadataEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountMetadataEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.AccountMetadataEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountMetadataEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.AccountMetadataEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.AccountMetadataEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountMetadataEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AccountMetadataEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.AccountMetadataEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.AccountMetadataEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountMetadataEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AccountMetadataEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.AccountMetadataEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountMetadataEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AccountMetadataEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AccountMetadataEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.AccountMetadataEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.AccountMetadataEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.AccountMetadataEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
