//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity AccountSnapshotEntity
 */
public abstract class AccountSnapshotEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.AccountSnapshotEntityDao
{
	com.soffid.iam.base.model.AccountEntityDao accountEntityDao;

	/**
	 * Sets reference to <code>accountEntityDao</code>.
	 */
	public void setAccountEntityDao (com.soffid.iam.base.model.AccountEntityDao accountEntityDao) {
		this.accountEntityDao = accountEntityDao;
	}

	/**
	 * Gets reference to <code>accountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountEntityDao getAccountEntityDao () {
		return accountEntityDao;
	}


	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} .
	 */
	public com.soffid.iam.iga.model.AccountSnapshotEntity newAccountSnapshotEntity()
	{
		return new com.soffid.iam.iga.model.AccountSnapshotEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.AccountSnapshotEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.AccountSnapshotEntity result = (com.soffid.iam.iga.model.AccountSnapshotEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.AccountSnapshotEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.AccountSnapshotEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.AccountSnapshotEntity> result = (java.util.List<com.soffid.iam.iga.model.AccountSnapshotEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.AccountSnapshotEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.AccountSnapshotEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountSnapshotEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.AccountSnapshotEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountSnapshotEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.AccountSnapshotEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountSnapshotEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.AccountSnapshotEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountSnapshotEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AccountSnapshotEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.AccountSnapshotEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountSnapshotEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AccountSnapshotEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.AccountSnapshotEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountSnapshotEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AccountSnapshotEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AccountSnapshotEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.AccountSnapshotEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.AccountSnapshotEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.AccountSnapshotEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.AccountSnapshotEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.AccountSnapshotEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
