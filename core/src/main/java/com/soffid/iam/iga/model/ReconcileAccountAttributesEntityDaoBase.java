//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity ReconcileAccountAttributesEntity
 */
public abstract class ReconcileAccountAttributesEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.ReconcileAccountAttributesEntityDao
{
	com.soffid.iam.iga.model.ReconcileAccountEntityDao reconcileAccountEntityDao;

	/**
	 * Sets reference to <code>reconcileAccountEntityDao</code>.
	 */
	public void setReconcileAccountEntityDao (com.soffid.iam.iga.model.ReconcileAccountEntityDao reconcileAccountEntityDao) {
		this.reconcileAccountEntityDao = reconcileAccountEntityDao;
	}

	/**
	 * Gets reference to <code>reconcileAccountEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ReconcileAccountEntityDao getReconcileAccountEntityDao () {
		return reconcileAccountEntityDao;
	}


	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} .
	 */
	public com.soffid.iam.iga.model.ReconcileAccountAttributesEntity newReconcileAccountAttributesEntity()
	{
		return new com.soffid.iam.iga.model.ReconcileAccountAttributesEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ReconcileAccountAttributesEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.ReconcileAccountAttributesEntity result = (com.soffid.iam.iga.model.ReconcileAccountAttributesEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.ReconcileAccountAttributesEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> result = (java.util.List<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.ReconcileAccountAttributesEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ReconcileAccountAttributesEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAccountAttributesEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ReconcileAccountAttributesEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAccountAttributesEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ReconcileAccountAttributesEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAccountAttributesEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAccountAttributesEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileAccountAttributesEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAccountAttributesEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileAccountAttributesEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAccountAttributesEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileAccountAttributesEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ReconcileAccountAttributesEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.ReconcileAccountAttributesEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
