//
// (c) 2014 Soffid
//
//
package com.soffid.iam.sync.model;
/**
 * DAO Base for Entity ScheduledTaskHandlerEntity
 */
public abstract class ScheduledTaskHandlerEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.sync.model.ScheduledTaskHandlerEntityDao
{
	com.soffid.iam.sync.model.ScheduledTaskEntityDao scheduledTaskEntityDao;

	/**
	 * Sets reference to <code>scheduledTaskEntityDao</code>.
	 */
	public void setScheduledTaskEntityDao (com.soffid.iam.sync.model.ScheduledTaskEntityDao scheduledTaskEntityDao) {
		this.scheduledTaskEntityDao = scheduledTaskEntityDao;
	}

	/**
	 * Gets reference to <code>scheduledTaskEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntityDao getScheduledTaskEntityDao () {
		return scheduledTaskEntityDao;
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
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.sync.model.ScheduledTaskHandlerEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.sync.model.ScheduledTaskHandlerEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.sync.model.ScheduledTaskHandlerEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.sync.model.ScheduledTaskHandlerEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.sync.api.ScheduledTaskHandler} object 
	 */
	public void toScheduledTaskHandler(com.soffid.iam.sync.model.ScheduledTaskHandlerEntity source, com.soffid.iam.sync.api.ScheduledTaskHandler target) {
		// Attributes for ScheduledTaskHandler
		target.setId(source.getId());
		target.setName(source.getName());
		target.setClassName(source.getClassName());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ScheduledTaskHandler} object 
	 */
	public com.soffid.iam.sync.api.ScheduledTaskHandler toScheduledTaskHandler(com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity) {
		final com.soffid.iam.sync.api.ScheduledTaskHandler target = new com.soffid.iam.sync.api.ScheduledTaskHandler();
		this.toScheduledTaskHandler(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ScheduledTaskHandler} list 
	 */
	public java.util.List<com.soffid.iam.sync.api.ScheduledTaskHandler> toScheduledTaskHandlerList (java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.sync.api.ScheduledTaskHandler> list =
				new java.util.LinkedList<com.soffid.iam.sync.api.ScheduledTaskHandler>();
			for (final com.soffid.iam.sync.model.ScheduledTaskHandlerEntity instance: instances)
			{
				list.add( toScheduledTaskHandler(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.sync.api.ScheduledTaskHandler} object 
	 */
	public void scheduledTaskHandlerToEntity (com.soffid.iam.sync.api.ScheduledTaskHandler source, com.soffid.iam.sync.model.ScheduledTaskHandlerEntity target, boolean copyIfNull) {
		// Attributes for ScheduledTaskHandlerEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getClassName() != null)
		{
			target.setClassName(source.getClassName());
		}
		// Missing attribute tenant on entity
		// Missing attribute tasks on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ScheduledTaskHandler} object 
	 */
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntity scheduledTaskHandlerToEntity (com.soffid.iam.sync.api.ScheduledTaskHandler instance) {
		com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newScheduledTaskHandlerEntity();
		scheduledTaskHandlerToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ScheduledTaskHandler} list 
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity>  scheduledTaskHandlerToEntityList (java.util.Collection<com.soffid.iam.sync.api.ScheduledTaskHandler> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> list =
			new java.util.LinkedList<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity>();
		for (com.soffid.iam.sync.api.ScheduledTaskHandler instance: instances)
		{
			list.add (scheduledTaskHandlerToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} .
	 */
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntity newScheduledTaskHandlerEntity()
	{
		return new com.soffid.iam.sync.model.ScheduledTaskHandlerEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.sync.model.ScheduledTaskHandlerEntity result = (com.soffid.iam.sync.model.ScheduledTaskHandlerEntity) this.getHibernateTemplate().get(com.soffid.iam.sync.model.ScheduledTaskHandlerEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.sync.model.ScheduledTaskHandlerEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskHandlerEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskHandlerEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskHandlerEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskHandlerEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskHandlerEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskHandlerEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ScheduledTaskHandlerEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
