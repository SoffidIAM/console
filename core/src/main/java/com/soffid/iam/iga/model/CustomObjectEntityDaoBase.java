//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity CustomObjectEntity
 */
public abstract class CustomObjectEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.CustomObjectEntityDao
{
	com.soffid.iam.iga.model.CustomObjectAttributeEntityDao customObjectAttributeEntityDao;

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


	/**
	 * Operation findByTypeAndName
	 * @param objectType
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.CustomObjectEntity findByTypeAndName(
	    java.lang.String objectType, 
	    java.lang.String name)
	
	{
		return findByTypeAndName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, objectType, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity findByTypeAndName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String objectType, java.lang.String name)
	
	{
		return findByTypeAndName("select o from com.soffid.iam.iga.model.CustomObjectEntity as o where o.type.name = :objectType and o.name = :name and o.type.tenant.id=:tenantId and(o.deleted is false or o.deleted is null)",
			criteria, objectType, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity findByTypeAndName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String objectType, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("objectType", objectType, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.CustomObjectEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.CustomObjectEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.CustomObjectEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByTypeAndNameDeleted
	 * @param objectType
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.CustomObjectEntity findByTypeAndNameDeleted(
	    java.lang.String objectType, 
	    java.lang.String name)
	
	{
		return findByTypeAndNameDeleted((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, objectType, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity findByTypeAndNameDeleted(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String objectType, java.lang.String name)
	
	{
		return findByTypeAndNameDeleted("select o from com.soffid.iam.iga.model.CustomObjectEntity as o where o.type.name = :objectType and o.name = :name and o.type.tenant.id=:tenantId",
			criteria, objectType, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity findByTypeAndNameDeleted(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String objectType, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("objectType", objectType, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.CustomObjectEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.CustomObjectEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.CustomObjectEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findCustomObjectNames
	 * @param type
	 * @return
	**/
	public java.util.List<java.lang.String> findCustomObjectNames(
	    java.lang.String type)
	
	{
		return findCustomObjectNames((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, type);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<java.lang.String> findCustomObjectNames(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type)
	
	{
		return findCustomObjectNames("select o.name from com.soffid.iam.iga.model.CustomObjectEntity as o where o.type.name=:type and o.type.tenant.id=:tenantId and (o.deleted is false or o.deleted is null)",
			criteria, type);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<java.lang.String> findCustomObjectNames(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("type", type, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<java.lang.String>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public void toCustomObject(com.soffid.iam.iga.model.CustomObjectEntity source, com.soffid.iam.iga.api.CustomObject target) {
		// Attributes for CustomObject
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		// Incompatible types source.type and target.type
		// Incompatible types source.attributes and target.attributes
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
		target.setDeleted(source.getDeleted());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public com.soffid.iam.iga.api.CustomObject toCustomObject(com.soffid.iam.iga.model.CustomObjectEntity entity) {
		final com.soffid.iam.iga.api.CustomObject target = new com.soffid.iam.iga.api.CustomObject();
		this.toCustomObject(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.CustomObject} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.CustomObject> toCustomObjectList (java.util.Collection<com.soffid.iam.iga.model.CustomObjectEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.CustomObject> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.CustomObject>();
			for (final com.soffid.iam.iga.model.CustomObjectEntity instance: instances)
			{
				list.add( toCustomObject(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public void customObjectToEntity (com.soffid.iam.iga.api.CustomObject source, com.soffid.iam.iga.model.CustomObjectEntity target, boolean copyIfNull) {
		// Attributes for CustomObjectEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getType() != null)
		{
			// Incompatible types source.type and target.type
		}
		if (copyIfNull || source.getAttributes() != null)
		{
			// Incompatible types source.attributes and target.attributes
		}
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
		if (copyIfNull || source.getDeleted() != null)
		{
			target.setDeleted(source.getDeleted());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity customObjectToEntity (com.soffid.iam.iga.api.CustomObject instance) {
		com.soffid.iam.iga.model.CustomObjectEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newCustomObjectEntity();
		customObjectToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.CustomObject} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectEntity>  customObjectToEntityList (java.util.Collection<com.soffid.iam.iga.api.CustomObject> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.CustomObjectEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.CustomObjectEntity>();
		for (com.soffid.iam.iga.api.CustomObject instance: instances)
		{
			list.add (customObjectToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.CustomObjectEntity} .
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity newCustomObjectEntity()
	{
		return new com.soffid.iam.iga.model.CustomObjectEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.CustomObjectEntity result = (com.soffid.iam.iga.model.CustomObjectEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.CustomObjectEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.CustomObjectEntity> result = (java.util.List<com.soffid.iam.iga.model.CustomObjectEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.CustomObjectEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.CustomObjectEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.CustomObjectEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectEntityDao.create - 'entity' can not be null");
		}

		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.CustomObjectEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.CustomObjectEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.CustomObjectEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.CustomObjectEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.CustomObjectEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.CustomObjectEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.CustomObjectEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.CustomObjectEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"CustomObjectEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.CustomObjectEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.CustomObjectEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.CustomObjectEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
