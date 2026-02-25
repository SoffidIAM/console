//
// (c) 2014 Soffid
//
//
package com.soffid.iam.sync.model;
/**
 * DAO Base for Entity ServerCertificateEntity
 */
public abstract class ServerCertificateEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.sync.model.ServerCertificateEntityDao
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


	/**
	 * Operation findByServer
	 * @param serverId
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.sync.model.ServerCertificateEntity> findByServer(
	    long serverId)
	
	{
		return findByServer((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, serverId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerCertificateEntity> findByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long serverId)
	
	{
		return findByServer("select cert\nfrom com.soffid.iam.sync.model.ServerCertificateEntity as cert\nwhere cert.server.id=:serverId ",
			criteria, serverId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerCertificateEntity> findByServer(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long serverId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("serverId", serverId, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.sync.model.ServerCertificateEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ServerCertificateEntity} .
	 */
	public com.soffid.iam.sync.model.ServerCertificateEntity newServerCertificateEntity()
	{
		return new com.soffid.iam.sync.model.ServerCertificateEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ServerCertificateEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.sync.model.ServerCertificateEntity result = (com.soffid.iam.sync.model.ServerCertificateEntity) this.getHibernateTemplate().get(com.soffid.iam.sync.model.ServerCertificateEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerCertificateEntity> loadAll() {
		java.util.List<com.soffid.iam.sync.model.ServerCertificateEntity> result = (java.util.List<com.soffid.iam.sync.model.ServerCertificateEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.sync.model.ServerCertificateEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ServerCertificateEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ServerCertificateEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerCertificateEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ServerCertificateEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ServerCertificateEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerCertificateEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ServerCertificateEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerCertificateEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ServerCertificateEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ServerCertificateEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerCertificateEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ServerCertificateEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ServerCertificateEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ServerCertificateEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerCertificateEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ServerCertificateEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ServerCertificateEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerCertificateEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ServerCertificateEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ServerCertificateEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.sync.model.ServerCertificateEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerCertificateEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.sync.model.ServerCertificateEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerCertificateEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.sync.model.ServerCertificateEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
