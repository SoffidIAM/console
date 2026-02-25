//
// (c) 2014 Soffid
//
//
package com.soffid.iam.sync.model;
/**
 * DAO Base for Entity ServerInstanceEntity
 */
public abstract class ServerInstanceEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.sync.model.ServerInstanceEntityDao
{
	com.soffid.iam.sync.model.ScheduledTaskEntityDao scheduledTaskEntityDao;

	/**
	 * Sets reference to <code>scheduledTaskEntityDao</code>.
	 */
	public void setScheduledTaskEntityDao (com.soffid.iam.sync.model.ScheduledTaskEntityDao scheduledTaskEntityDao) {
		this.scheduledTaskEntityDao = scheduledTaskEntityDao;
	}

	/**
	 * Gets reference to <code>scheduledTaskEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntityDao getScheduledTaskEntityDao () {
		return scheduledTaskEntityDao;
	}

	com.soffid.iam.am.model.SecretEntityDao secretEntityDao;

	/**
	 * Sets reference to <code>secretEntityDao</code>.
	 */
	public void setSecretEntityDao (com.soffid.iam.am.model.SecretEntityDao secretEntityDao) {
		this.secretEntityDao = secretEntityDao;
	}

	/**
	 * Gets reference to <code>secretEntityDao</code>.
	 */
	public com.soffid.iam.am.model.SecretEntityDao getSecretEntityDao () {
		return secretEntityDao;
	}

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


	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.sync.model.ServerInstanceEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.sync.model.ServerInstanceEntity where name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.sync.model.ServerInstanceEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.sync.model.ServerInstanceEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.sync.model.ServerInstanceEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByServerNameAndInstanceName
	 * @param serverName
	 * @param instanceName
	 * @return
	**/
	public com.soffid.iam.sync.model.ServerInstanceEntity findByServerNameAndInstanceName(
	    java.lang.String serverName, 
	    java.lang.String instanceName)
	
	{
		return findByServerNameAndInstanceName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, serverName, instanceName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity findByServerNameAndInstanceName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serverName, java.lang.String instanceName)
	
	{
		return findByServerNameAndInstanceName("select si from com.soffid.iam.sync.model.ServerInstanceEntityImpl as si where si.name=:instanceName and si.server.name=:serverName",
			criteria, serverName, instanceName);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity findByServerNameAndInstanceName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serverName, java.lang.String instanceName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("serverName", serverName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("instanceName", instanceName, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.sync.model.ServerInstanceEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.sync.model.ServerInstanceEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.sync.model.ServerInstanceEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUrl
	 * @param url
	 * @return
	**/
	public com.soffid.iam.sync.model.ServerInstanceEntity findByUrl(
	    java.lang.String url)
	
	{
		return findByUrl((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, url);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity findByUrl(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String url)
	
	{
		return findByUrl("from com.soffid.iam.sync.model.ServerInstanceEntity where url=:url",
			criteria, url);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity findByUrl(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String url)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("url", url, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.sync.model.ServerInstanceEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.sync.model.ServerInstanceEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.sync.model.ServerInstanceEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByServerName
	 * @param serverName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.sync.model.ServerInstanceEntity> findByServerName(
	    java.lang.String serverName)
	
	{
		return findByServerName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, serverName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerInstanceEntity> findByServerName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serverName)
	
	{
		return findByServerName("select si from com.soffid.iam.sync.model.ServerInstanceEntityImpl as si where si.server.name=:serverName",
			criteria, serverName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerInstanceEntity> findByServerName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serverName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("serverName", serverName, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.sync.model.ServerInstanceEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findBestServerInstances
	 * @param serverName
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> findBestServerInstances(
	    java.lang.String serverName)
	
	{
		return findBestServerInstances((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, serverName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> findBestServerInstances(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serverName)
	
	{
		return findBestServerInstances("select si from com.soffid.iam.sync.model.ServerInstanceEntityImpl as si where si.server.name=:serverName order by tasks, id desc",
			criteria, serverName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> findBestServerInstances(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serverName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("serverName", serverName, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findExpired
	 * @param lastSeen
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> findExpired(
	    java.util.Date lastSeen)
	
	{
		return findExpired((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, lastSeen);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> findExpired(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date lastSeen)
	
	{
		return findExpired("select si from com.soffid.iam.sync.model.ServerInstanceEntityImpl as si where si.lastSeen < :lastSeen",
			criteria, lastSeen);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> findExpired(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date lastSeen)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("lastSeen", lastSeen, org.hibernate.Hibernate.TIMESTAMP);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ServerInstanceEntity} .
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity newServerInstanceEntity()
	{
		return new com.soffid.iam.sync.model.ServerInstanceEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.sync.model.ServerInstanceEntity result = (com.soffid.iam.sync.model.ServerInstanceEntity) this.getHibernateTemplate().get(com.soffid.iam.sync.model.ServerInstanceEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> loadAll() {
		java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> result = (java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.sync.model.ServerInstanceEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ServerInstanceEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ServerInstanceEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerInstanceEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ServerInstanceEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ServerInstanceEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerInstanceEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ServerInstanceEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerInstanceEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ServerInstanceEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ServerInstanceEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerInstanceEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ServerInstanceEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ServerInstanceEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ServerInstanceEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerInstanceEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ServerInstanceEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ServerInstanceEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerInstanceEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ServerInstanceEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ServerInstanceEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.sync.model.ServerInstanceEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
