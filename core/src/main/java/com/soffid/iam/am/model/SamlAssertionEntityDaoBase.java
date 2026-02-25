//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity SamlAssertionEntity
 */
public abstract class SamlAssertionEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.SamlAssertionEntityDao
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
	public com.soffid.iam.am.model.SamlAssertionEntity findByExternalId(
	    java.lang.String externalId)
	
	{
		return findByExternalId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, externalId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.SamlAssertionEntity findByExternalId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId)
	
	{
		return findByExternalId("from com.soffid.iam.am.model.SamlAssertionEntity where tenant.id=:tenantId and externalId=:externalId",
			criteria, externalId);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.SamlAssertionEntity findByExternalId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId)
	
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
			com.soffid.iam.am.model.SamlAssertionEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.SamlAssertionEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.SamlAssertionEntity) results.iterator().next();
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
	public java.util.Collection<com.soffid.iam.am.model.SamlAssertionEntity> findExpired(
	    java.util.Date d)
	
	{
		return findExpired((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, d);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.am.model.SamlAssertionEntity> findExpired(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date d)
	
	{
		return findExpired("select a from com.soffid.iam.am.model.SamlAssertionEntity as a where a.date < :d",
			criteria, d);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.am.model.SamlAssertionEntity> findExpired(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date d)
	
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
			return (java.util.Collection<com.soffid.iam.am.model.SamlAssertionEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.am.model.SamlAssertionEntity#	 * @see com.soffid.iam.am.model.SamlAssertionEntity#void deleteExpired()
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
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.model.SamlAssertionEntity.class).
				warn ("Error on SamlAssertionEntity.deleteExpired", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on SamlAssertionEntity.deleteExpired: "+th.toString(), th);
		}
	}

	protected abstract void handleDeleteExpired() throws Exception;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.SamlAssertionEntity} .
	 */
	public com.soffid.iam.am.model.SamlAssertionEntity newSamlAssertionEntity()
	{
		return new com.soffid.iam.am.model.SamlAssertionEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.SamlAssertionEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.SamlAssertionEntity result = (com.soffid.iam.am.model.SamlAssertionEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.SamlAssertionEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.SamlAssertionEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.SamlAssertionEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.SamlAssertionEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.SamlAssertionEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.SamlAssertionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SamlAssertionEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.SamlAssertionEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.SamlAssertionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SamlAssertionEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.SamlAssertionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SamlAssertionEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.SamlAssertionEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.SamlAssertionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SamlAssertionEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.SamlAssertionEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.SamlAssertionEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.SamlAssertionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SamlAssertionEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.SamlAssertionEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.SamlAssertionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SamlAssertionEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.SamlAssertionEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"SamlAssertionEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.SamlAssertionEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.SamlAssertionEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.SamlAssertionEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.SamlAssertionEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.SamlAssertionEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
