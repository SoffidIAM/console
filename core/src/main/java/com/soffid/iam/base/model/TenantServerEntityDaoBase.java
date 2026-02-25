//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity TenantServerEntity
 */
public abstract class TenantServerEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.TenantServerEntityDao
{
	com.soffid.iam.sync.model.ServerEntityDao serverEntityDao;

	/**
	 * Sets reference to <code>serverEntityDao</code>.
	 */
	public void setServerEntityDao (com.soffid.iam.sync.model.ServerEntityDao serverEntityDao) {
		this.serverEntityDao = serverEntityDao;
	}

	/**
	 * Gets reference to <code>serverEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ServerEntityDao getServerEntityDao () {
		return serverEntityDao;
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
	 * Creates an instance of {@link com.soffid.iam.base.model.TenantServerEntity} .
	 */
	public com.soffid.iam.base.model.TenantServerEntity newTenantServerEntity()
	{
		return new com.soffid.iam.base.model.TenantServerEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.TenantServerEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.TenantServerEntity result = (com.soffid.iam.base.model.TenantServerEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.TenantServerEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.TenantServerEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.TenantServerEntity> result = (java.util.List<com.soffid.iam.base.model.TenantServerEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.TenantServerEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.TenantServerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.TenantServerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TenantServerEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.TenantServerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.TenantServerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TenantServerEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.TenantServerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TenantServerEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.TenantServerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.TenantServerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TenantServerEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.TenantServerEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.TenantServerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.TenantServerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TenantServerEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.TenantServerEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.TenantServerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TenantServerEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.TenantServerEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"TenantServerEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.TenantServerEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.TenantServerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.TenantServerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.TenantServerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.TenantServerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
