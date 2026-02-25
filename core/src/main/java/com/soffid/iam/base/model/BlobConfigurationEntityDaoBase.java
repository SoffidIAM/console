//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity BlobConfigurationEntity
 */
public abstract class BlobConfigurationEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.BlobConfigurationEntityDao
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
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.base.model.BlobConfigurationEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			return (java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByNameAndTenant
	 * @param name
	 * @param tenantId
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByNameAndTenant(
	    java.lang.String name, 
	    java.lang.Long tenantId)
	
	{
		return findByNameAndTenant((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, tenantId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByNameAndTenant(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.Long tenantId)
	
	{
		return findByNameAndTenant("select b from com.soffid.iam.base.model.BlobConfigurationEntity as b where b.name=:name and b.tenant.id=:tenantId",
			criteria, name, tenantId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByNameAndTenant(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.Long tenantId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", tenantId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByNameAndTenantName
	 * @param name
	 * @param tenantName
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByNameAndTenantName(
	    java.lang.String name, 
	    java.lang.String tenantName)
	
	{
		return findByNameAndTenantName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, tenantName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByNameAndTenantName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String tenantName)
	
	{
		return findByNameAndTenantName("select b from com.soffid.iam.base.model.BlobConfigurationEntity as b where b.name=:name and b.tenant.name=:tenantName",
			criteria, name, tenantName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByNameAndTenantName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String tenantName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantName", tenantName, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.BlobConfigurationEntity} .
	 */
	public com.soffid.iam.base.model.BlobConfigurationEntity newBlobConfigurationEntity()
	{
		return new com.soffid.iam.base.model.BlobConfigurationEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.BlobConfigurationEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.BlobConfigurationEntity result = (com.soffid.iam.base.model.BlobConfigurationEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.BlobConfigurationEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.base.model.BlobConfigurationEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.BlobConfigurationEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.BlobConfigurationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"BlobConfigurationEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.BlobConfigurationEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.BlobConfigurationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"BlobConfigurationEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.BlobConfigurationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"BlobConfigurationEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.BlobConfigurationEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.BlobConfigurationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"BlobConfigurationEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.BlobConfigurationEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.BlobConfigurationEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.BlobConfigurationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"BlobConfigurationEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.BlobConfigurationEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.BlobConfigurationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"BlobConfigurationEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.BlobConfigurationEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"BlobConfigurationEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.BlobConfigurationEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
