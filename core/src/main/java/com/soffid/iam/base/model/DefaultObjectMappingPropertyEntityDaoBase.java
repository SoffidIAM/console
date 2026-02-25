//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity DefaultObjectMappingPropertyEntity
 */
public abstract class DefaultObjectMappingPropertyEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.DefaultObjectMappingPropertyEntityDao
{
	com.soffid.iam.base.model.DefaultObjectMappingEntityDao defaultObjectMappingEntityDao;

	/**
	 * Sets reference to <code>defaultObjectMappingEntityDao</code>.
	 */
	public void setDefaultObjectMappingEntityDao (com.soffid.iam.base.model.DefaultObjectMappingEntityDao defaultObjectMappingEntityDao) {
		this.defaultObjectMappingEntityDao = defaultObjectMappingEntityDao;
	}

	/**
	 * Gets reference to <code>defaultObjectMappingEntityDao</code>.
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingEntityDao getDefaultObjectMappingEntityDao () {
		return defaultObjectMappingEntityDao;
	}


	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} .
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity newDefaultObjectMappingPropertyEntity()
	{
		return new com.soffid.iam.base.model.DefaultObjectMappingPropertyEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity result = (com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.DefaultObjectMappingPropertyEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> result = (java.util.List<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingPropertyEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingPropertyEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingPropertyEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingPropertyEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingPropertyEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingPropertyEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingPropertyEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
