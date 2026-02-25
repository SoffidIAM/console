//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity HostSystemEntity
 */
public abstract class HostSystemEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.HostSystemEntityDao
{
	com.soffid.iam.am.model.HostEntityDao hostEntityDao;

	/**
	 * Sets reference to <code>hostEntityDao</code>.
	 */
	public void setHostEntityDao (com.soffid.iam.am.model.HostEntityDao hostEntityDao) {
		this.hostEntityDao = hostEntityDao;
	}

	/**
	 * Gets reference to <code>hostEntityDao</code>.
	 */
	public com.soffid.iam.am.model.HostEntityDao getHostEntityDao () {
		return hostEntityDao;
	}

	com.soffid.iam.iga.model.SystemEntityDao systemEntityDao;

	/**
	 * Sets reference to <code>systemEntityDao</code>.
	 */
	public void setSystemEntityDao (com.soffid.iam.iga.model.SystemEntityDao systemEntityDao) {
		this.systemEntityDao = systemEntityDao;
	}

	/**
	 * Gets reference to <code>systemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.SystemEntityDao getSystemEntityDao () {
		return systemEntityDao;
	}


	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.HostSystemEntity} .
	 */
	public com.soffid.iam.pam.model.HostSystemEntity newHostSystemEntity()
	{
		return new com.soffid.iam.pam.model.HostSystemEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.HostSystemEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.HostSystemEntity result = (com.soffid.iam.pam.model.HostSystemEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.HostSystemEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.HostSystemEntity> loadAll() {
		java.util.List<com.soffid.iam.pam.model.HostSystemEntity> result = (java.util.List<com.soffid.iam.pam.model.HostSystemEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.pam.model.HostSystemEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.HostSystemEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.HostSystemEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostSystemEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.HostSystemEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.HostSystemEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostSystemEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.HostSystemEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostSystemEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.HostSystemEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.HostSystemEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostSystemEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.HostSystemEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.HostSystemEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.HostSystemEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostSystemEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.HostSystemEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.HostSystemEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostSystemEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.HostSystemEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"HostSystemEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.HostSystemEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.HostSystemEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.HostSystemEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.HostSystemEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.HostSystemEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
