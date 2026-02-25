//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity LuceneIndexPartEntity
 */
public abstract class LuceneIndexPartEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.LuceneIndexPartEntityDao
{
	com.soffid.iam.base.model.LuceneIndexEntityDao luceneIndexEntityDao;

	/**
	 * Sets reference to <code>luceneIndexEntityDao</code>.
	 */
	public void setLuceneIndexEntityDao (com.soffid.iam.base.model.LuceneIndexEntityDao luceneIndexEntityDao) {
		this.luceneIndexEntityDao = luceneIndexEntityDao;
	}

	/**
	 * Gets reference to <code>luceneIndexEntityDao</code>.
	 */
	public com.soffid.iam.base.model.LuceneIndexEntityDao getLuceneIndexEntityDao () {
		return luceneIndexEntityDao;
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
	 * Operation findByIndex
	 * @param index
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.LuceneIndexPartEntity> findByIndex(
	    java.lang.Long index)
	
	{
		return findByIndex((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, index);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.LuceneIndexPartEntity> findByIndex(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long index)
	
	{
		return findByIndex("select p from com.soffid.iam.base.model.LuceneIndexPartEntity as p where p.index.id = :index order by name, order",
			criteria, index);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.LuceneIndexPartEntity> findByIndex(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long index)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("index", index, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.base.model.LuceneIndexPartEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} .
	 */
	public com.soffid.iam.base.model.LuceneIndexPartEntity newLuceneIndexPartEntity()
	{
		return new com.soffid.iam.base.model.LuceneIndexPartEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.LuceneIndexPartEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.LuceneIndexPartEntity result = (com.soffid.iam.base.model.LuceneIndexPartEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.LuceneIndexPartEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.LuceneIndexPartEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.base.model.LuceneIndexPartEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.base.model.LuceneIndexPartEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.LuceneIndexPartEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"LuceneIndexPartEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.LuceneIndexPartEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"LuceneIndexPartEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.LuceneIndexPartEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"LuceneIndexPartEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.LuceneIndexPartEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"LuceneIndexPartEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.LuceneIndexPartEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.LuceneIndexPartEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"LuceneIndexPartEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.LuceneIndexPartEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.LuceneIndexPartEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"LuceneIndexPartEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.LuceneIndexPartEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"LuceneIndexPartEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.LuceneIndexPartEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.LuceneIndexPartEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.LuceneIndexPartEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.LuceneIndexPartEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.LuceneIndexPartEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.LuceneIndexPartEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
