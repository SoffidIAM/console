//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity TenantDisabledPermissionEntity
 */
public abstract class TenantDisabledPermissionEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.TenantDisabledPermissionEntityDao
{
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
	 * Creates an instance of {@link com.soffid.iam.base.model.TenantDisabledPermissionEntity} .
	 */
	public com.soffid.iam.base.model.TenantDisabledPermissionEntity newTenantDisabledPermissionEntity()
	{
		return new com.soffid.iam.base.model.TenantDisabledPermissionEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.TenantDisabledPermissionEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.TenantDisabledPermissionEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.TenantDisabledPermissionEntity result = (com.soffid.iam.base.model.TenantDisabledPermissionEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.TenantDisabledPermissionEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.TenantDisabledPermissionEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.TenantDisabledPermissionEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.TenantDisabledPermissionEntity> result = (java.util.List<com.soffid.iam.base.model.TenantDisabledPermissionEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.TenantDisabledPermissionEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.TenantDisabledPermissionEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.TenantDisabledPermissionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TenantDisabledPermissionEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.TenantDisabledPermissionEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.TenantDisabledPermissionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TenantDisabledPermissionEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.TenantDisabledPermissionEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.TenantDisabledPermissionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TenantDisabledPermissionEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.TenantDisabledPermissionEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.TenantDisabledPermissionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TenantDisabledPermissionEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.TenantDisabledPermissionEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.TenantDisabledPermissionEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.TenantDisabledPermissionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TenantDisabledPermissionEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.TenantDisabledPermissionEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.TenantDisabledPermissionEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.TenantDisabledPermissionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TenantDisabledPermissionEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.TenantDisabledPermissionEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.TenantDisabledPermissionEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"TenantDisabledPermissionEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.TenantDisabledPermissionEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.TenantDisabledPermissionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.TenantDisabledPermissionEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.TenantDisabledPermissionEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.TenantDisabledPermissionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.TenantDisabledPermissionEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.TenantDisabledPermissionEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
