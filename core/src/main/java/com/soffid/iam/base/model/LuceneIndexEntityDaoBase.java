//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity LuceneIndexEntity
 */
public abstract class LuceneIndexEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.LuceneIndexEntityDao
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

	com.soffid.iam.base.model.LuceneIndexPartEntityDao luceneIndexPartEntityDao;

	/**
	 * Sets reference to <code>luceneIndexPartEntityDao</code>.
	 */
	public void setLuceneIndexPartEntityDao (com.soffid.iam.base.model.LuceneIndexPartEntityDao luceneIndexPartEntityDao) {
		this.luceneIndexPartEntityDao = luceneIndexPartEntityDao;
	}

	/**
	 * Gets reference to <code>luceneIndexPartEntityDao</code>.
	 */
	public com.soffid.iam.base.model.LuceneIndexPartEntityDao getLuceneIndexPartEntityDao () {
		return luceneIndexPartEntityDao;
	}


	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.base.model.LuceneIndexEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.LuceneIndexEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.base.model.LuceneIndexEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.LuceneIndexEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.base.model.LuceneIndexEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.LuceneIndexEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.LuceneIndexEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.base.model.LuceneIndexEntity#	 * @see com.soffid.iam.base.model.LuceneIndexEntity#void lock(com.soffid.iam.base.model.LuceneIndexEntity entity)
	 */
	public void lock(
		com.soffid.iam.base.model.LuceneIndexEntity entity)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (entity == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.LuceneIndexEntity.lock(com.soffid.iam.base.model.LuceneIndexEntity entity) - entity cannot be null");
		}
		try
		{
			handleLock(entity);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.LuceneIndexEntity.class).
				warn ("Error on LuceneIndexEntity.lock", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on LuceneIndexEntity.lock: "+th.toString(), th);
		}
	}

	protected abstract void handleLock(com.soffid.iam.base.model.LuceneIndexEntity entity) throws Exception;

	/**
	 * @see com.soffid.iam.base.model.LuceneIndexEntity#	 * @see com.soffid.iam.base.model.LuceneIndexEntity#void refresh(com.soffid.iam.base.model.LuceneIndexEntity entity)
	 */
	public void refresh(
		com.soffid.iam.base.model.LuceneIndexEntity entity)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (entity == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.LuceneIndexEntity.refresh(com.soffid.iam.base.model.LuceneIndexEntity entity) - entity cannot be null");
		}
		try
		{
			handleRefresh(entity);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.LuceneIndexEntity.class).
				warn ("Error on LuceneIndexEntity.refresh", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on LuceneIndexEntity.refresh: "+th.toString(), th);
		}
	}

	protected abstract void handleRefresh(com.soffid.iam.base.model.LuceneIndexEntity entity) throws Exception;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.LuceneIndexEntity} .
	 */
	public com.soffid.iam.base.model.LuceneIndexEntity newLuceneIndexEntity()
	{
		return new com.soffid.iam.base.model.LuceneIndexEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.LuceneIndexEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.LuceneIndexEntity result = (com.soffid.iam.base.model.LuceneIndexEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.LuceneIndexEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.LuceneIndexEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.base.model.LuceneIndexEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.base.model.LuceneIndexEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.LuceneIndexEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.LuceneIndexEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"LuceneIndexEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.LuceneIndexEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.LuceneIndexEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"LuceneIndexEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.LuceneIndexEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"LuceneIndexEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.LuceneIndexEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.LuceneIndexEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"LuceneIndexEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.LuceneIndexEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.LuceneIndexEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.LuceneIndexEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"LuceneIndexEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.LuceneIndexEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.LuceneIndexEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"LuceneIndexEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.LuceneIndexEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"LuceneIndexEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.LuceneIndexEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.LuceneIndexEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.LuceneIndexEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.LuceneIndexEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.LuceneIndexEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.LuceneIndexEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
