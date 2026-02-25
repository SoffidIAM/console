//
// (c) 2014 Soffid
//
//
package com.soffid.iam.sync.model;
/**
 * DAO Base for Entity ServerRegistrationTokenEntity
 */
public abstract class ServerRegistrationTokenEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.sync.model.ServerRegistrationTokenEntityDao
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
	 * Operation findByToken
	 * @param token
	 * @return
	**/
	public com.soffid.iam.sync.model.ServerRegistrationTokenEntity findByToken(
	    java.lang.String token)
	
	{
		return findByToken((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, token);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.sync.model.ServerRegistrationTokenEntity findByToken(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String token)
	
	{
		return findByToken("from com.soffid.iam.sync.model.ServerRegistrationTokenEntity where tenant.id=:tenantId and token=:token",
			criteria, token);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.sync.model.ServerRegistrationTokenEntity findByToken(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String token)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("token", token, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.sync.model.ServerRegistrationTokenEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.sync.model.ServerRegistrationTokenEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.sync.model.ServerRegistrationTokenEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.sync.model.ServerRegistrationTokenEntity#	 * @see com.soffid.iam.sync.model.ServerRegistrationTokenEntity#void removeExpiredTokens()
	 */
	public void removeExpiredTokens()
		throws com.soffid.iam.exception.InternalErrorException
	{
		try
		{
			handleRemoveExpiredTokens();
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.model.ServerRegistrationTokenEntity.class).
				warn ("Error on ServerRegistrationTokenEntity.removeExpiredTokens", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on ServerRegistrationTokenEntity.removeExpiredTokens: "+th.toString(), th);
		}
	}

	protected abstract void handleRemoveExpiredTokens() throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.sync.api.ServerRegistrationToken} object 
	 */
	public void toServerRegistrationToken(com.soffid.iam.sync.model.ServerRegistrationTokenEntity source, com.soffid.iam.sync.api.ServerRegistrationToken target) {
		// Attributes for ServerRegistrationToken
		target.setStep(source.getStep());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ServerRegistrationToken} object 
	 */
	public com.soffid.iam.sync.api.ServerRegistrationToken toServerRegistrationToken(com.soffid.iam.sync.model.ServerRegistrationTokenEntity entity) {
		final com.soffid.iam.sync.api.ServerRegistrationToken target = new com.soffid.iam.sync.api.ServerRegistrationToken();
		this.toServerRegistrationToken(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ServerRegistrationToken} list 
	 */
	public java.util.List<com.soffid.iam.sync.api.ServerRegistrationToken> toServerRegistrationTokenList (java.util.Collection<com.soffid.iam.sync.model.ServerRegistrationTokenEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.sync.api.ServerRegistrationToken> list =
				new java.util.LinkedList<com.soffid.iam.sync.api.ServerRegistrationToken>();
			for (final com.soffid.iam.sync.model.ServerRegistrationTokenEntity instance: instances)
			{
				list.add( toServerRegistrationToken(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.sync.api.ServerRegistrationToken} object 
	 */
	public void serverRegistrationTokenToEntity (com.soffid.iam.sync.api.ServerRegistrationToken source, com.soffid.iam.sync.model.ServerRegistrationTokenEntity target, boolean copyIfNull) {
		// Attributes for ServerRegistrationTokenEntity
		target.setStep(source.getStep());
		// Missing attribute token on entity
		// Missing attribute expiration on entity
		// Missing attribute tenant on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ServerRegistrationToken} list 
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerRegistrationTokenEntity>  serverRegistrationTokenToEntityList (java.util.Collection<com.soffid.iam.sync.api.ServerRegistrationToken> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.sync.model.ServerRegistrationTokenEntity> list =
			new java.util.LinkedList<com.soffid.iam.sync.model.ServerRegistrationTokenEntity>();
		for (com.soffid.iam.sync.api.ServerRegistrationToken instance: instances)
		{
			list.add (serverRegistrationTokenToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} .
	 */
	public com.soffid.iam.sync.model.ServerRegistrationTokenEntity newServerRegistrationTokenEntity()
	{
		return new com.soffid.iam.sync.model.ServerRegistrationTokenEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ServerRegistrationTokenEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.sync.model.ServerRegistrationTokenEntity result = (com.soffid.iam.sync.model.ServerRegistrationTokenEntity) this.getHibernateTemplate().get(com.soffid.iam.sync.model.ServerRegistrationTokenEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerRegistrationTokenEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.sync.model.ServerRegistrationTokenEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.sync.model.ServerRegistrationTokenEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ServerRegistrationTokenEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerRegistrationTokenEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ServerRegistrationTokenEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerRegistrationTokenEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ServerRegistrationTokenEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerRegistrationTokenEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ServerRegistrationTokenEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerRegistrationTokenEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ServerRegistrationTokenEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ServerRegistrationTokenEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerRegistrationTokenEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ServerRegistrationTokenEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ServerRegistrationTokenEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerRegistrationTokenEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ServerRegistrationTokenEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ServerRegistrationTokenEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.sync.model.ServerRegistrationTokenEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerRegistrationTokenEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.sync.model.ServerRegistrationTokenEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerRegistrationTokenEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.sync.model.ServerRegistrationTokenEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
