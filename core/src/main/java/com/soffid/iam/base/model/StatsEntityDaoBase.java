//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity StatsEntity
 */
public abstract class StatsEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.StatsEntityDao
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
	 * @param since
	 * @param until
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.StatsEntity> findByName(
	    java.lang.String name, 
	    java.lang.String since, 
	    java.lang.String until)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, since, until);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.StatsEntity> findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String since, java.lang.String until)
	
	{
		return findByName("select x from com.soffid.iam.base.model.StatsEntity as x where x.name=:name and x.date between :since and :until and tenant.id=:tenantId order by x.date, x.serie",
			criteria, name, since, until);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.StatsEntity> findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String since, java.lang.String until)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("since", since, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("until", until, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.base.model.StatsEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.base.model.StatsEntity#	 * @see com.soffid.iam.base.model.StatsEntity#void purge(int days)
	 */
	public void purge(
		int days)
		throws com.soffid.iam.exception.InternalErrorException
	{
		try
		{
			handlePurge(days);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.StatsEntity.class).
				warn ("Error on StatsEntity.purge", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on StatsEntity.purge: "+th.toString(), th);
		}
	}

	protected abstract void handlePurge(int days) throws Exception;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.StatsEntity} .
	 */
	public com.soffid.iam.base.model.StatsEntity newStatsEntity()
	{
		return new com.soffid.iam.base.model.StatsEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.StatsEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.StatsEntity result = (com.soffid.iam.base.model.StatsEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.StatsEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.StatsEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.base.model.StatsEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.base.model.StatsEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.StatsEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.StatsEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"StatsEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.StatsEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.StatsEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"StatsEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.StatsEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"StatsEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.StatsEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.StatsEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"StatsEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.StatsEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.StatsEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.StatsEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"StatsEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.StatsEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.StatsEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"StatsEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.StatsEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"StatsEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.StatsEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.StatsEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.StatsEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.StatsEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.StatsEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
