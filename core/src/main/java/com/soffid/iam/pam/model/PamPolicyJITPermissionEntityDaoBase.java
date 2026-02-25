//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity PamPolicyJITPermissionEntity
 */
public abstract class PamPolicyJITPermissionEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.PamPolicyJITPermissionEntityDao
{
	com.soffid.iam.pam.model.PamPolicyEntityDao pamPolicyEntityDao;

	/**
	 * Sets reference to <code>pamPolicyEntityDao</code>.
	 */
	public void setPamPolicyEntityDao (com.soffid.iam.pam.model.PamPolicyEntityDao pamPolicyEntityDao) {
		this.pamPolicyEntityDao = pamPolicyEntityDao;
	}

	/**
	 * Gets reference to <code>pamPolicyEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.PamPolicyEntityDao getPamPolicyEntityDao () {
		return pamPolicyEntityDao;
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
	 * Creates an instance of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} .
	 */
	public com.soffid.iam.pam.model.PamPolicyJITPermissionEntity newPamPolicyJITPermissionEntity()
	{
		return new com.soffid.iam.pam.model.PamPolicyJITPermissionEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.PamPolicyJITPermissionEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.PamPolicyJITPermissionEntity result = (com.soffid.iam.pam.model.PamPolicyJITPermissionEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.PamPolicyJITPermissionEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.pam.model.PamPolicyJITPermissionEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.PamPolicyJITPermissionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PamPolicyJITPermissionEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.PamPolicyJITPermissionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PamPolicyJITPermissionEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.PamPolicyJITPermissionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PamPolicyJITPermissionEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PamPolicyJITPermissionEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.PamPolicyJITPermissionEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PamPolicyJITPermissionEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.PamPolicyJITPermissionEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PamPolicyJITPermissionEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.PamPolicyJITPermissionEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"PamPolicyJITPermissionEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.PamPolicyJITPermissionEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamPolicyJITPermissionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
