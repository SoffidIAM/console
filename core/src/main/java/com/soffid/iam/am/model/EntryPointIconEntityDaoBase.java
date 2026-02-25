//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity EntryPointIconEntity
 */
public abstract class EntryPointIconEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.EntryPointIconEntityDao
{

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointIconEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointIconEntity newEntryPointIconEntity()
	{
		return new com.soffid.iam.am.model.EntryPointIconEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointIconEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointIconEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.EntryPointIconEntity result = (com.soffid.iam.am.model.EntryPointIconEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.EntryPointIconEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointIconEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointIconEntity> loadAll() {
		java.util.List<com.soffid.iam.am.model.EntryPointIconEntity> result = (java.util.List<com.soffid.iam.am.model.EntryPointIconEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.am.model.EntryPointIconEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointIconEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointIconEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointIconEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointIconEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointIconEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointIconEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointIconEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointIconEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointIconEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointIconEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointIconEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointIconEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointIconEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointIconEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointIconEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointIconEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointIconEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointIconEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointIconEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointIconEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointIconEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointIconEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"EntryPointIconEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.EntryPointIconEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointIconEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointIconEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.EntryPointIconEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointIconEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointIconEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.EntryPointIconEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
