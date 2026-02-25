//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity AuthoritativeChangeEntity
 * Contains pending authoritative changes
 */
public abstract class AuthoritativeChangeEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.AuthoritativeChangeEntityDao
{
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
	 * Creates an instance of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} .
	 */
	public com.soffid.iam.iga.model.AuthoritativeChangeEntity newAuthoritativeChangeEntity()
	{
		return new com.soffid.iam.iga.model.AuthoritativeChangeEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.AuthoritativeChangeEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.AuthoritativeChangeEntity result = (com.soffid.iam.iga.model.AuthoritativeChangeEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.AuthoritativeChangeEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.AuthoritativeChangeEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.AuthoritativeChangeEntity> result = (java.util.List<com.soffid.iam.iga.model.AuthoritativeChangeEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.AuthoritativeChangeEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.AuthoritativeChangeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AuthoritativeChangeEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.AuthoritativeChangeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AuthoritativeChangeEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.AuthoritativeChangeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AuthoritativeChangeEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.AuthoritativeChangeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AuthoritativeChangeEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AuthoritativeChangeEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.AuthoritativeChangeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AuthoritativeChangeEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AuthoritativeChangeEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.AuthoritativeChangeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AuthoritativeChangeEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AuthoritativeChangeEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AuthoritativeChangeEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.AuthoritativeChangeEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.AuthoritativeChangeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.AuthoritativeChangeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.AuthoritativeChangeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.AuthoritativeChangeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.AuthoritativeChangeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
