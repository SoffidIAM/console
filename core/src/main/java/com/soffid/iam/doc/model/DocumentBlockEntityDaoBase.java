//
// (c) 2014 Soffid
//
//
package com.soffid.iam.doc.model;
/**
 * DAO Base for Entity DocumentBlockEntity
 */
public abstract class DocumentBlockEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.doc.model.DocumentBlockEntityDao
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
	 * Operation findByPath
	 * @param path
	 * @return
	**/
	public java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity> findByPath(
	    java.lang.String path)
	
	{
		return findByPath((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, path);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity> findByPath(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String path)
	
	{
		return findByPath("select db from com.soffid.iam.doc.model.DocumentBlockEntity as db where db.path=:path order by sequenceNumber",
			criteria, path);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity> findByPath(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String path)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("path", path, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.doc.model.DocumentBlockEntity} .
	 */
	public com.soffid.iam.doc.model.DocumentBlockEntity newDocumentBlockEntity()
	{
		return new com.soffid.iam.doc.model.DocumentBlockEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 */
	public com.soffid.iam.doc.model.DocumentBlockEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.doc.model.DocumentBlockEntity result = (com.soffid.iam.doc.model.DocumentBlockEntity) this.getHibernateTemplate().get(com.soffid.iam.doc.model.DocumentBlockEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.doc.model.DocumentBlockEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.doc.model.DocumentBlockEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.doc.model.DocumentBlockEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DocumentBlockEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.doc.model.DocumentBlockEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.doc.model.DocumentBlockEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DocumentBlockEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.doc.model.DocumentBlockEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DocumentBlockEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.doc.model.DocumentBlockEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.doc.model.DocumentBlockEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DocumentBlockEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.doc.model.DocumentBlockEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.doc.model.DocumentBlockEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.doc.model.DocumentBlockEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DocumentBlockEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.doc.model.DocumentBlockEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.doc.model.DocumentBlockEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DocumentBlockEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.doc.model.DocumentBlockEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"DocumentBlockEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.doc.model.DocumentBlockEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.doc.model.DocumentBlockEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.doc.model.DocumentBlockEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
