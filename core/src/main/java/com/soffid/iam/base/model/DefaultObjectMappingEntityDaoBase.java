//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity DefaultObjectMappingEntity
 */
public abstract class DefaultObjectMappingEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.DefaultObjectMappingEntityDao
{
	com.soffid.iam.base.model.AgentDescriptorEntityDao agentDescriptorEntityDao;

	/**
	 * Sets reference to <code>agentDescriptorEntityDao</code>.
	 */
	public void setAgentDescriptorEntityDao (com.soffid.iam.base.model.AgentDescriptorEntityDao agentDescriptorEntityDao) {
		this.agentDescriptorEntityDao = agentDescriptorEntityDao;
	}

	/**
	 * Gets reference to <code>agentDescriptorEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntityDao getAgentDescriptorEntityDao () {
		return agentDescriptorEntityDao;
	}

	com.soffid.iam.base.model.DefaultAttributeMappingEntityDao defaultAttributeMappingEntityDao;

	/**
	 * Sets reference to <code>defaultAttributeMappingEntityDao</code>.
	 */
	public void setDefaultAttributeMappingEntityDao (com.soffid.iam.base.model.DefaultAttributeMappingEntityDao defaultAttributeMappingEntityDao) {
		this.defaultAttributeMappingEntityDao = defaultAttributeMappingEntityDao;
	}

	/**
	 * Gets reference to <code>defaultAttributeMappingEntityDao</code>.
	 */
	public com.soffid.iam.base.model.DefaultAttributeMappingEntityDao getDefaultAttributeMappingEntityDao () {
		return defaultAttributeMappingEntityDao;
	}

	com.soffid.iam.base.model.DefaultObjectMappingPropertyEntityDao defaultObjectMappingPropertyEntityDao;

	/**
	 * Sets reference to <code>defaultObjectMappingPropertyEntityDao</code>.
	 */
	public void setDefaultObjectMappingPropertyEntityDao (com.soffid.iam.base.model.DefaultObjectMappingPropertyEntityDao defaultObjectMappingPropertyEntityDao) {
		this.defaultObjectMappingPropertyEntityDao = defaultObjectMappingPropertyEntityDao;
	}

	/**
	 * Gets reference to <code>defaultObjectMappingPropertyEntityDao</code>.
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingPropertyEntityDao getDefaultObjectMappingPropertyEntityDao () {
		return defaultObjectMappingPropertyEntityDao;
	}


	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} .
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingEntity newDefaultObjectMappingEntity()
	{
		return new com.soffid.iam.base.model.DefaultObjectMappingEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.DefaultObjectMappingEntity result = (com.soffid.iam.base.model.DefaultObjectMappingEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.DefaultObjectMappingEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultObjectMappingEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.DefaultObjectMappingEntity> result = (java.util.List<com.soffid.iam.base.model.DefaultObjectMappingEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.DefaultObjectMappingEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.DefaultObjectMappingEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.DefaultObjectMappingEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.DefaultObjectMappingEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.DefaultObjectMappingEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.DefaultObjectMappingEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.DefaultObjectMappingEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.DefaultObjectMappingEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.DefaultObjectMappingEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.DefaultObjectMappingEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"DefaultObjectMappingEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.DefaultObjectMappingEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultObjectMappingEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.DefaultObjectMappingEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultObjectMappingEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.DefaultObjectMappingEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
