//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity SamlRequestEntity
 */
public abstract class SamlRequestEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.SamlRequestEntityDao
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
	 * Operation findByExternalId
	 * @param externalId
	 * @return
	**/
	public com.soffid.iam.am.model.SamlRequestEntity findByExternalId(
	    java.lang.String externalId)
	
	{
		return findByExternalId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, externalId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.SamlRequestEntity findByExternalId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId)
	
	{
		return findByExternalId("from com.soffid.iam.am.model.SamlRequestEntity where tenant.id=:tenantId and externalId=:externalId",
			criteria, externalId);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.SamlRequestEntity findByExternalId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("externalId", externalId, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.SamlRequestEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.SamlRequestEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.SamlRequestEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findExpired
	 * @param d
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.am.model.SamlRequestEntity> findExpired(
	    java.util.Date d)
	
	{
		return findExpired((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, d);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.am.model.SamlRequestEntity> findExpired(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date d)
	
	{
		return findExpired("select a from com.soffid.iam.am.model.SamlRequestEntity as a where a.date < :d",
			criteria, d);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.am.model.SamlRequestEntity> findExpired(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date d)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("d", d, org.hibernate.Hibernate.TIMESTAMP);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.am.model.SamlRequestEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.am.model.SamlRequestEntity#	 * @see com.soffid.iam.am.model.SamlRequestEntity#void deleteExpired()
	 */
	public void deleteExpired()
		throws com.soffid.iam.exception.InternalErrorException
	{
		try
		{
			handleDeleteExpired();
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.model.SamlRequestEntity.class).
				warn ("Error on SamlRequestEntity.deleteExpired", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on SamlRequestEntity.deleteExpired: "+th.toString(), th);
		}
	}

	protected abstract void handleDeleteExpired() throws Exception;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.SamlRequestEntity} .
	 */
	public com.soffid.iam.am.model.SamlRequestEntity newSamlRequestEntity()
	{
		return new com.soffid.iam.am.model.SamlRequestEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.SamlRequestEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.SamlRequestEntity result = (com.soffid.iam.am.model.SamlRequestEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.SamlRequestEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.SamlRequestEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.SamlRequestEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.SamlRequestEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.SamlRequestEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.SamlRequestEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SamlRequestEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.SamlRequestEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.SamlRequestEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SamlRequestEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.SamlRequestEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SamlRequestEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.SamlRequestEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.SamlRequestEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SamlRequestEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.SamlRequestEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.SamlRequestEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.SamlRequestEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SamlRequestEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.SamlRequestEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.SamlRequestEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SamlRequestEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.SamlRequestEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"SamlRequestEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.SamlRequestEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.SamlRequestEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.SamlRequestEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.SamlRequestEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.SamlRequestEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.SamlRequestEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
