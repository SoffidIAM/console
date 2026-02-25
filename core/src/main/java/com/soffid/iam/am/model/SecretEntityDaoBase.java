//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity SecretEntity
 */
public abstract class SecretEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.SecretEntityDao
{
	com.soffid.iam.sync.model.ServerEntityDao serverEntityDao;

	/**
	 * Sets reference to <code>serverEntityDao</code>.
	 */
	public void setServerEntityDao (com.soffid.iam.sync.model.ServerEntityDao serverEntityDao) {
		this.serverEntityDao = serverEntityDao;
	}

	/**
	 * Gets reference to <code>serverEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ServerEntityDao getServerEntityDao () {
		return serverEntityDao;
	}

	com.soffid.iam.base.model.UserEntityDao userEntityDao;

	/**
	 * Sets reference to <code>userEntityDao</code>.
	 */
	public void setUserEntityDao (com.soffid.iam.base.model.UserEntityDao userEntityDao) {
		this.userEntityDao = userEntityDao;
	}

	/**
	 * Gets reference to <code>userEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserEntityDao getUserEntityDao () {
		return userEntityDao;
	}


	/**
	 * Operation findByUserAndServer
	 * @param userId
	 * @param serverId
	 * @return
	**/
	public com.soffid.iam.am.model.SecretEntity findByUserAndServer(
	    long userId, 
	    long serverId)
	
	{
		return findByUserAndServer((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userId, serverId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.SecretEntity findByUserAndServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long userId, long serverId)
	
	{
		return findByUserAndServer("select secret\nfrom com.soffid.iam.am.model.SecretEntity as secret\nwhere secret.server.id=:serverId and   secret.user.id=:userId",
			criteria, userId, serverId);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.SecretEntity findByUserAndServer(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long userId, long serverId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userId", userId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("serverId", serverId, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.SecretEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.SecretEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.SecretEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByServer
	 * @param server
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.SecretEntity> findByServer(
	    com.soffid.iam.sync.model.ServerEntity server)
	
	{
		return findByServer((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.SecretEntity> findByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.sync.model.ServerEntity server)
	
	{
		return findByServer("from com.soffid.iam.am.model.SecretEntity where server=:server",
			criteria, server);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.SecretEntity> findByServer(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.sync.model.ServerEntity server)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.SecretEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.SecretEntity} .
	 */
	public com.soffid.iam.am.model.SecretEntity newSecretEntity()
	{
		return new com.soffid.iam.am.model.SecretEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.SecretEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.SecretEntity result = (com.soffid.iam.am.model.SecretEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.SecretEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.SecretEntity> loadAll() {
		java.util.List<com.soffid.iam.am.model.SecretEntity> result = (java.util.List<com.soffid.iam.am.model.SecretEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.am.model.SecretEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.SecretEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.SecretEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SecretEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.SecretEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.SecretEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SecretEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.SecretEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SecretEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.SecretEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.SecretEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SecretEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.SecretEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.SecretEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.SecretEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SecretEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.SecretEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.SecretEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SecretEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.SecretEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"SecretEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.SecretEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.SecretEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.SecretEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.SecretEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.SecretEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
