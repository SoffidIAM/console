//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity GroupTypeEntity
 */
public abstract class GroupTypeEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.GroupTypeEntityDao
{
	com.soffid.iam.iga.model.GroupEntityDao groupEntityDao;

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

	com.soffid.iam.iga.model.GroupTypeEntityDao groupTypeEntityDao;

	/**
	 * Sets reference to <code>groupTypeEntityDao</code>.
	 */
	public void setGroupTypeEntityDao (com.soffid.iam.iga.model.GroupTypeEntityDao groupTypeEntityDao) {
		this.groupTypeEntityDao = groupTypeEntityDao;
	}

	/**
	 * Gets reference to <code>groupTypeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupTypeEntityDao getGroupTypeEntityDao () {
		return groupTypeEntityDao;
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


	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.GroupTypeEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.GroupTypeEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.iga.model.GroupTypeEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.GroupTypeEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.iga.model.GroupTypeEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.GroupTypeEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.GroupTypeEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByFilter
	 * @param name
	 * @param description
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity> findByFilter(
	    java.lang.String name, 
	    java.lang.String description)
	
	{
		return findByFilter((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, description);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity> findByFilter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String description)
	
	{
		return findByFilter("select uo from com.soffid.iam.iga.model.GroupTypeEntity uo where (:name is null or (:name is not null and uo.name like :name)) and  (:description is null or (:description is not null and uo.description like :description)) and uo.tenant.id = :tenantId",
			criteria, name, description);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity> findByFilter(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String description)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("description", description, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.GroupTypeEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.GroupType} object 
	 */
	public void toGroupType(com.soffid.iam.iga.model.GroupTypeEntity source, com.soffid.iam.iga.api.GroupType target) {
		// Attributes for GroupType
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setId(source.getId());
		target.setRoleHolder(source.isRoleHolder());
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.GroupType} object 
	 */
	public com.soffid.iam.iga.api.GroupType toGroupType(com.soffid.iam.iga.model.GroupTypeEntity entity) {
		final com.soffid.iam.iga.api.GroupType target = new com.soffid.iam.iga.api.GroupType();
		this.toGroupType(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.GroupType} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.GroupType> toGroupTypeList (java.util.Collection<com.soffid.iam.iga.model.GroupTypeEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.GroupType> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.GroupType>();
			for (final com.soffid.iam.iga.model.GroupTypeEntity instance: instances)
			{
				list.add( toGroupType(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.GroupType} object 
	 */
	public void groupTypeToEntity (com.soffid.iam.iga.api.GroupType source, com.soffid.iam.iga.model.GroupTypeEntity target, boolean copyIfNull) {
		// Attributes for GroupTypeEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		target.setRoleHolder(source.isRoleHolder());
		// Missing attribute tenant on entity
		// Missing attribute groupEntities on entity
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
	 *  Transforms from {@link com.soffid.iam.iga.api.GroupType} object 
	 */
	public com.soffid.iam.iga.model.GroupTypeEntity groupTypeToEntity (com.soffid.iam.iga.api.GroupType instance) {
		com.soffid.iam.iga.model.GroupTypeEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newGroupTypeEntity();
		groupTypeToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.GroupType} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity>  groupTypeToEntityList (java.util.Collection<com.soffid.iam.iga.api.GroupType> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.GroupTypeEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.GroupTypeEntity>();
		for (com.soffid.iam.iga.api.GroupType instance: instances)
		{
			list.add (groupTypeToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.GroupTypeEntity} .
	 */
	public com.soffid.iam.iga.model.GroupTypeEntity newGroupTypeEntity()
	{
		return new com.soffid.iam.iga.model.GroupTypeEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.GroupTypeEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.GroupTypeEntity result = (com.soffid.iam.iga.model.GroupTypeEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.GroupTypeEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.GroupTypeEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.GroupTypeEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.GroupTypeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.GroupTypeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"GroupTypeEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.iga.model.GroupTypeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.GroupTypeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"GroupTypeEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.GroupTypeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"GroupTypeEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.GroupTypeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.GroupTypeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"GroupTypeEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.GroupTypeEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.GroupTypeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.GroupTypeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"GroupTypeEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.GroupTypeEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.GroupTypeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"GroupTypeEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.GroupTypeEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"GroupTypeEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.GroupTypeEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.GroupTypeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.GroupTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupTypeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.GroupTypeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
