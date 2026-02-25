//
// (c) 2014 Soffid
//
//
package com.soffid.iam.sync.model;
/**
 * DAO Base for Entity ServerEntity
 */
public abstract class ServerEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.sync.model.ServerEntityDao
{
	com.soffid.iam.am.model.NetworkEntityDao networkEntityDao;

	/**
	 * Sets reference to <code>networkEntityDao</code>.
	 */
	public void setNetworkEntityDao (com.soffid.iam.am.model.NetworkEntityDao networkEntityDao) {
		this.networkEntityDao = networkEntityDao;
	}

	/**
	 * Gets reference to <code>networkEntityDao</code>.
	 */
	public com.soffid.iam.am.model.NetworkEntityDao getNetworkEntityDao () {
		return networkEntityDao;
	}

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

	com.soffid.iam.sync.model.ServerCertificateEntityDao serverCertificateEntityDao;

	/**
	 * Sets reference to <code>serverCertificateEntityDao</code>.
	 */
	public void setServerCertificateEntityDao (com.soffid.iam.sync.model.ServerCertificateEntityDao serverCertificateEntityDao) {
		this.serverCertificateEntityDao = serverCertificateEntityDao;
	}

	/**
	 * Gets reference to <code>serverCertificateEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ServerCertificateEntityDao getServerCertificateEntityDao () {
		return serverCertificateEntityDao;
	}

	com.soffid.iam.base.model.TenantServerEntityDao tenantServerEntityDao;

	/**
	 * Sets reference to <code>tenantServerEntityDao</code>.
	 */
	public void setTenantServerEntityDao (com.soffid.iam.base.model.TenantServerEntityDao tenantServerEntityDao) {
		this.tenantServerEntityDao = tenantServerEntityDao;
	}

	/**
	 * Gets reference to <code>tenantServerEntityDao</code>.
	 */
	public com.soffid.iam.base.model.TenantServerEntityDao getTenantServerEntityDao () {
		return tenantServerEntityDao;
	}

	com.soffid.iam.sync.model.ServerInstanceEntityDao serverInstanceEntityDao;

	/**
	 * Sets reference to <code>serverInstanceEntityDao</code>.
	 */
	public void setServerInstanceEntityDao (com.soffid.iam.sync.model.ServerInstanceEntityDao serverInstanceEntityDao) {
		this.serverInstanceEntityDao = serverInstanceEntityDao;
	}

	/**
	 * Gets reference to <code>serverInstanceEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntityDao getServerInstanceEntityDao () {
		return serverInstanceEntityDao;
	}


	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.sync.model.ServerEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.sync.model.ServerEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.sync.model.ServerEntity where name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.sync.model.ServerEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.sync.model.ServerEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.sync.model.ServerEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.sync.model.ServerEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findRemoteByUrl
	 * @param url
	 * @return
	**/
	public com.soffid.iam.sync.model.ServerEntity findRemoteByUrl(
	    java.lang.String url)
	
	{
		return findRemoteByUrl((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, url);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.sync.model.ServerEntity findRemoteByUrl(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String url)
	
	{
		return findRemoteByUrl("select server from com.soffid.iam.sync.model.ServerEntity as server where server.url=:url and server.type='remote'",
			criteria, url);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.sync.model.ServerEntity findRemoteByUrl(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String url)
	
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
			com.soffid.iam.sync.model.ServerEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.sync.model.ServerEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.sync.model.ServerEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation countServersByName
	 * @param name
	 * @return
	**/
	public java.lang.Long countServersByName(
	    java.lang.String name)
	
	{
		return countServersByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long countServersByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return countServersByName("select count(*) from com.soffid.iam.sync.model.ServerEntity as server where server.name like :name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long countServersByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			java.lang.Long result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.Long' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.Long) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByTenant
	 * @param name
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.sync.model.ServerEntity> findByTenant(
	    java.lang.String name)
	
	{
		return findByTenant((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerEntity> findByTenant(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByTenant("select server from com.soffid.iam.sync.model.ServerEntity as server join server.tenants as t where t.serverTenant.name = :name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerEntity> findByTenant(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.sync.model.ServerEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findGatewayByTenant
	 * @param name
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.sync.model.ServerEntity> findGatewayByTenant(
	    java.lang.String name)
	
	{
		return findGatewayByTenant((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerEntity> findGatewayByTenant(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findGatewayByTenant("select server from com.soffid.iam.sync.model.ServerEntity as server join server.tenants as t where t.serverTenant.name = :name and server.type='gateway'",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerEntity> findGatewayByTenant(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.sync.model.ServerEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.sync.api.Server} object 
	 */
	public void toServer(com.soffid.iam.sync.model.ServerEntity source, com.soffid.iam.sync.api.Server target) {
		// Attributes for Server
		target.setId(source.getId());
		target.setName(source.getName());
		target.setPk(source.getPk());
		target.setAuth(source.getAuth());
		// Missing attribute publicKey on entity
		target.setUseMasterDatabase(source.getUseMasterDatabase());
		// Missing attribute backupDatabase on entity
		target.setType(source.getType());
		target.setUrl(source.getUrl());
		// Missing attribute publicUrl on entity
		target.setJavaOptions(source.getJavaOptions());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.Server} object 
	 */
	public com.soffid.iam.sync.api.Server toServer(com.soffid.iam.sync.model.ServerEntity entity) {
		final com.soffid.iam.sync.api.Server target = new com.soffid.iam.sync.api.Server();
		this.toServer(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.Server} list 
	 */
	public java.util.List<com.soffid.iam.sync.api.Server> toServerList (java.util.Collection<com.soffid.iam.sync.model.ServerEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.sync.api.Server> list =
				new java.util.LinkedList<com.soffid.iam.sync.api.Server>();
			for (final com.soffid.iam.sync.model.ServerEntity instance: instances)
			{
				list.add( toServer(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.sync.api.Server} object 
	 */
	public void serverToEntity (com.soffid.iam.sync.api.Server source, com.soffid.iam.sync.model.ServerEntity target, boolean copyIfNull) {
		// Attributes for ServerEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getPk() != null)
		{
			target.setPk(source.getPk());
		}
		if (copyIfNull || source.getAuth() != null)
		{
			target.setAuth(source.getAuth());
		}
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		if (copyIfNull || source.getUseMasterDatabase() != null)
		{
			target.setUseMasterDatabase(source.getUseMasterDatabase());
		}
		if (copyIfNull || source.getUrl() != null)
		{
			target.setUrl(source.getUrl());
		}
		if (copyIfNull || source.getJavaOptions() != null)
		{
			target.setJavaOptions(source.getJavaOptions());
		}
		// Missing attribute secrets on entity
		// Missing attribute scheduledTasks on entity
		// Missing attribute networks on entity
		// Missing attribute tenants on entity
		// Missing attribute certificates on entity
		// Missing attribute instances on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.Server} object 
	 */
	public com.soffid.iam.sync.model.ServerEntity serverToEntity (com.soffid.iam.sync.api.Server instance) {
		com.soffid.iam.sync.model.ServerEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newServerEntity();
		serverToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.Server} list 
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerEntity>  serverToEntityList (java.util.Collection<com.soffid.iam.sync.api.Server> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.sync.model.ServerEntity> list =
			new java.util.LinkedList<com.soffid.iam.sync.model.ServerEntity>();
		for (com.soffid.iam.sync.api.Server instance: instances)
		{
			list.add (serverToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ServerEntity} .
	 */
	public com.soffid.iam.sync.model.ServerEntity newServerEntity()
	{
		return new com.soffid.iam.sync.model.ServerEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ServerEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.sync.model.ServerEntity result = (com.soffid.iam.sync.model.ServerEntity) this.getHibernateTemplate().get(com.soffid.iam.sync.model.ServerEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerEntity> loadAll() {
		java.util.List<com.soffid.iam.sync.model.ServerEntity> result = (java.util.List<com.soffid.iam.sync.model.ServerEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.sync.model.ServerEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ServerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ServerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ServerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ServerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ServerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ServerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ServerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ServerEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ServerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ServerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ServerEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ServerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ServerEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ServerEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.sync.model.ServerEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.sync.model.ServerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.sync.model.ServerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
