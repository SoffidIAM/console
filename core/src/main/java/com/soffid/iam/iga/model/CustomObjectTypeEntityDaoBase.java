//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity CustomObjectTypeEntity
 */
public abstract class CustomObjectTypeEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.CustomObjectTypeEntityDao
{
	com.soffid.iam.base.model.AccountMetadataEntityDao accountMetadataEntityDao;

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

	com.soffid.iam.iga.model.CustomObjectEntityDao customObjectEntityDao;

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

	com.soffid.iam.iga.model.CustomObjectRoleEntityDao customObjectRoleEntityDao;

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

	com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao;

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

	com.soffid.iam.iga.model.ObjectMappingEntityDao objectMappingEntityDao;

	/**
	 * Sets reference to <code>objectMappingEntityDao</code>.
	 */
	public void setObjectMappingEntityDao (com.soffid.iam.iga.model.ObjectMappingEntityDao objectMappingEntityDao) {
		this.objectMappingEntityDao = objectMappingEntityDao;
	}

	/**
	 * Gets reference to <code>objectMappingEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ObjectMappingEntityDao getObjectMappingEntityDao () {
		return objectMappingEntityDao;
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
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.CustomObjectTypeEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.iga.model.CustomObjectTypeEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.iga.model.CustomObjectTypeEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.CustomObjectTypeEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.CustomObjectTypeEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.CustomObjectType} object 
	 */
	public void toCustomObjectType(com.soffid.iam.iga.model.CustomObjectTypeEntity source, com.soffid.iam.iga.api.CustomObjectType target) {
		// Attributes for CustomObjectType
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		// Missing attribute nlsDescription on entity
		target.setScope(source.getScope());
		target.setBuiltin(source.isBuiltin());
		target.setTextIndex(source.isTextIndex());
		target.setExtensibleObjectClass(source.getExtensibleObjectClass());
		target.setPublicAccess(source.getPublicAccess());
		// Missing attribute managerRoles on entity
		// Missing attribute userRoles on entity
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.CustomObjectType} object 
	 */
	public com.soffid.iam.iga.api.CustomObjectType toCustomObjectType(com.soffid.iam.iga.model.CustomObjectTypeEntity entity) {
		final com.soffid.iam.iga.api.CustomObjectType target = new com.soffid.iam.iga.api.CustomObjectType();
		this.toCustomObjectType(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.CustomObjectType} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.CustomObjectType> toCustomObjectTypeList (java.util.Collection<com.soffid.iam.iga.model.CustomObjectTypeEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.CustomObjectType> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.CustomObjectType>();
			for (final com.soffid.iam.iga.model.CustomObjectTypeEntity instance: instances)
			{
				list.add( toCustomObjectType(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.CustomObjectType} object 
	 */
	public void customObjectTypeToEntity (com.soffid.iam.iga.api.CustomObjectType source, com.soffid.iam.iga.model.CustomObjectTypeEntity target, boolean copyIfNull) {
		// Attributes for CustomObjectTypeEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getScope() != null)
		{
			target.setScope(source.getScope());
		}
		target.setBuiltin(source.isBuiltin());
		target.setTextIndex(source.isTextIndex());
		if (copyIfNull || source.getExtensibleObjectClass() != null)
		{
			target.setExtensibleObjectClass(source.getExtensibleObjectClass());
		}
		// Missing attribute tenant on entity
		if (copyIfNull || source.getPublicAccess() != null)
		{
			target.setPublicAccess(source.getPublicAccess());
		}
		// Missing attribute refererncedBy on entity
		// Missing attribute objects on entity
		// Missing attribute accessRoles on entity
		// Missing attribute attributes on entity
		// Missing attribute referencedBy on entity
		// Missing attribute mappings on entity
		// Missing attribute translations on entity
		if (copyIfNull || source.getCreatedOn() != null)
		{
			target.setCreatedOn(source.getCreatedOn());
		}
		if (copyIfNull || source.getCreatedBy() != null)
		{
			target.setCreatedBy(source.getCreatedBy());
		}
		if (copyIfNull || source.getUpdatedOn() != null)
		{
			target.setUpdatedOn(source.getUpdatedOn());
		}
		if (copyIfNull || source.getUpdatedBy() != null)
		{
			target.setUpdatedBy(source.getUpdatedBy());
		}
		if (copyIfNull || source.getDeletedOn() != null)
		{
			target.setDeletedOn(source.getDeletedOn());
		}
		if (copyIfNull || source.getDeletedBy() != null)
		{
			target.setDeletedBy(source.getDeletedBy());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.CustomObjectType} object 
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity customObjectTypeToEntity (com.soffid.iam.iga.api.CustomObjectType instance) {
		com.soffid.iam.iga.model.CustomObjectTypeEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newCustomObjectTypeEntity();
		customObjectTypeToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.CustomObjectType} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectTypeEntity>  customObjectTypeToEntityList (java.util.Collection<com.soffid.iam.iga.api.CustomObjectType> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.CustomObjectTypeEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.CustomObjectTypeEntity>();
		for (com.soffid.iam.iga.api.CustomObjectType instance: instances)
		{
			list.add (customObjectTypeToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} .
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity newCustomObjectTypeEntity()
	{
		return new com.soffid.iam.iga.model.CustomObjectTypeEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.CustomObjectTypeEntity result = (com.soffid.iam.iga.model.CustomObjectTypeEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.CustomObjectTypeEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectTypeEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.CustomObjectTypeEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.CustomObjectTypeEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.CustomObjectTypeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectTypeEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.CustomObjectTypeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectTypeEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.CustomObjectTypeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectTypeEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectTypeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectTypeEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.CustomObjectTypeEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectTypeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectTypeEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.CustomObjectTypeEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectTypeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectTypeEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.CustomObjectTypeEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"CustomObjectTypeEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.CustomObjectTypeEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectTypeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.CustomObjectTypeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectTypeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.CustomObjectTypeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
