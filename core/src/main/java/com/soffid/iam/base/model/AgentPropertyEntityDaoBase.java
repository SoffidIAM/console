//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity AgentPropertyEntity
 */
public abstract class AgentPropertyEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.AgentPropertyEntityDao
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


	/**
	 *  Copy data to {@link com.soffid.iam.base.api.AgentProperty} object 
	 */
	public void toAgentProperty(com.soffid.iam.base.model.AgentPropertyEntity source, com.soffid.iam.base.api.AgentProperty target) {
		// Attributes for AgentProperty
		target.setTemplate(source.getTemplate());
		target.setType(source.getType());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.AgentProperty} object 
	 */
	public com.soffid.iam.base.api.AgentProperty toAgentProperty(com.soffid.iam.base.model.AgentPropertyEntity entity) {
		final com.soffid.iam.base.api.AgentProperty target = new com.soffid.iam.base.api.AgentProperty();
		this.toAgentProperty(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.AgentProperty} list 
	 */
	public java.util.List<com.soffid.iam.base.api.AgentProperty> toAgentPropertyList (java.util.Collection<com.soffid.iam.base.model.AgentPropertyEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.AgentProperty> list =
				new java.util.LinkedList<com.soffid.iam.base.api.AgentProperty>();
			for (final com.soffid.iam.base.model.AgentPropertyEntity instance: instances)
			{
				list.add( toAgentProperty(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.AgentProperty} object 
	 */
	public void agentPropertyToEntity (com.soffid.iam.base.api.AgentProperty source, com.soffid.iam.base.model.AgentPropertyEntity target, boolean copyIfNull) {
		// Attributes for AgentPropertyEntity
		if (copyIfNull || source.getTemplate() != null)
		{
			target.setTemplate(source.getTemplate());
		}
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		// Missing attribute agent on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.AgentProperty} list 
	 */
	public java.util.List<com.soffid.iam.base.model.AgentPropertyEntity>  agentPropertyToEntityList (java.util.Collection<com.soffid.iam.base.api.AgentProperty> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.AgentPropertyEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.AgentPropertyEntity>();
		for (com.soffid.iam.base.api.AgentProperty instance: instances)
		{
			list.add (agentPropertyToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.AgentPropertyEntity} .
	 */
	public com.soffid.iam.base.model.AgentPropertyEntity newAgentPropertyEntity()
	{
		return new com.soffid.iam.base.model.AgentPropertyEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.AgentPropertyEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.AgentPropertyEntity result = (com.soffid.iam.base.model.AgentPropertyEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.AgentPropertyEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.AgentPropertyEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.AgentPropertyEntity> result = (java.util.List<com.soffid.iam.base.model.AgentPropertyEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.AgentPropertyEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.AgentPropertyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.AgentPropertyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AgentPropertyEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.AgentPropertyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.AgentPropertyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AgentPropertyEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.AgentPropertyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AgentPropertyEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.AgentPropertyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.AgentPropertyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AgentPropertyEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AgentPropertyEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.AgentPropertyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.AgentPropertyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AgentPropertyEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AgentPropertyEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.AgentPropertyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AgentPropertyEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AgentPropertyEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AgentPropertyEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.AgentPropertyEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.AgentPropertyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.AgentPropertyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.AgentPropertyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.AgentPropertyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.AgentPropertyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
