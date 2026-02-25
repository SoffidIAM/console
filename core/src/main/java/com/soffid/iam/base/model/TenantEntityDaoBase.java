//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity TenantEntity
 */
public abstract class TenantEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.TenantEntityDao
{
	com.soffid.iam.base.model.TenantDisabledPermissionEntityDao tenantDisabledPermissionEntityDao;

	/**
	 * Sets reference to <code>tenantDisabledPermissionEntityDao</code>.
	 */
	public void setTenantDisabledPermissionEntityDao (com.soffid.iam.base.model.TenantDisabledPermissionEntityDao tenantDisabledPermissionEntityDao) {
		this.tenantDisabledPermissionEntityDao = tenantDisabledPermissionEntityDao;
	}

	/**
	 * Gets reference to <code>tenantDisabledPermissionEntityDao</code>.
	 */
	public com.soffid.iam.base.model.TenantDisabledPermissionEntityDao getTenantDisabledPermissionEntityDao () {
		return tenantDisabledPermissionEntityDao;
	}

	com.soffid.iam.base.model.TenantServerEntityDao tenantServerEntityDao;

	/**
	 * Sets reference to <code>tenantServerEntityDao</code>.
	 */
	public void setTenantServerEntityDao (com.soffid.iam.base.model.TenantServerEntityDao tenantServerEntityDao) {
		this.tenantServerEntityDao = tenantServerEntityDao;
	}

	/**
	 * Gets reference to <code>tenantServerEntityDao</code>.
	 */
	public com.soffid.iam.base.model.TenantServerEntityDao getTenantServerEntityDao () {
		return tenantServerEntityDao;
	}


	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.base.model.TenantEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.TenantEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.base.model.TenantEntity where name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.TenantEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.TenantEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.TenantEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.TenantEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByServer
	 * @param server
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.TenantEntity> findByServer(
	    java.lang.String server)
	
	{
		return findByServer((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.TenantEntity> findByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		return findByServer("select t from com.soffid.iam.base.model.TenantEntity as t join t.servers as s where s.tenantServer.name=:server",
			criteria, server);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.TenantEntity> findByServer(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.TenantEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.Tenant} object 
	 */
	public void toTenant(com.soffid.iam.base.model.TenantEntity source, com.soffid.iam.base.api.Tenant target) {
		// Attributes for Tenant
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setEnabled(source.isEnabled());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Tenant} object 
	 */
	public com.soffid.iam.base.api.Tenant toTenant(com.soffid.iam.base.model.TenantEntity entity) {
		final com.soffid.iam.base.api.Tenant target = new com.soffid.iam.base.api.Tenant();
		this.toTenant(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Tenant} list 
	 */
	public java.util.List<com.soffid.iam.base.api.Tenant> toTenantList (java.util.Collection<com.soffid.iam.base.model.TenantEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.Tenant> list =
				new java.util.LinkedList<com.soffid.iam.base.api.Tenant>();
			for (final com.soffid.iam.base.model.TenantEntity instance: instances)
			{
				list.add( toTenant(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.Tenant} object 
	 */
	public void tenantToEntity (com.soffid.iam.base.api.Tenant source, com.soffid.iam.base.model.TenantEntity target, boolean copyIfNull) {
		// Attributes for TenantEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		target.setEnabled(source.isEnabled());
		// Missing attribute disabledPermissions on entity
		// Missing attribute servers on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Tenant} object 
	 */
	public com.soffid.iam.base.model.TenantEntity tenantToEntity (com.soffid.iam.base.api.Tenant instance) {
		com.soffid.iam.base.model.TenantEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newTenantEntity();
		tenantToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Tenant} list 
	 */
	public java.util.List<com.soffid.iam.base.model.TenantEntity>  tenantToEntityList (java.util.Collection<com.soffid.iam.base.api.Tenant> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.TenantEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.TenantEntity>();
		for (com.soffid.iam.base.api.Tenant instance: instances)
		{
			list.add (tenantToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.TenantEntity} .
	 */
	public com.soffid.iam.base.model.TenantEntity newTenantEntity()
	{
		return new com.soffid.iam.base.model.TenantEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.TenantEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.TenantEntity result = (com.soffid.iam.base.model.TenantEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.TenantEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.TenantEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.TenantEntity> result = (java.util.List<com.soffid.iam.base.model.TenantEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.TenantEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.TenantEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.TenantEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TenantEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.TenantEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.TenantEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TenantEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.TenantEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TenantEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.TenantEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.TenantEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TenantEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.TenantEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.TenantEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.TenantEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TenantEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.TenantEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.TenantEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TenantEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.TenantEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"TenantEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.TenantEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.TenantEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.TenantEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.TenantEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.TenantEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
