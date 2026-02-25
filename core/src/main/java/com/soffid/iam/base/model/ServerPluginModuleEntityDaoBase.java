//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity ServerPluginModuleEntity
 */
public abstract class ServerPluginModuleEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.ServerPluginModuleEntityDao
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

	com.soffid.iam.base.model.ServerPluginEntityDao serverPluginEntityDao;

	/**
	 * Sets reference to <code>serverPluginEntityDao</code>.
	 */
	public void setServerPluginEntityDao (com.soffid.iam.base.model.ServerPluginEntityDao serverPluginEntityDao) {
		this.serverPluginEntityDao = serverPluginEntityDao;
	}

	/**
	 * Gets reference to <code>serverPluginEntityDao</code>.
	 */
	public com.soffid.iam.base.model.ServerPluginEntityDao getServerPluginEntityDao () {
		return serverPluginEntityDao;
	}


	/**
	 * Operation findByType
	 * @param type
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity> findByType(
	    com.soffid.iam.base.api.ServerPluginModuleType type)
	
	{
		return findByType((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, type);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity> findByType(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.api.ServerPluginModuleType type)
	
	{
		return findByType("from com.soffid.iam.base.model.ServerPluginModuleEntity where type=:type",
			criteria, type);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity> findByType(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.api.ServerPluginModuleType type)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("type", type);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.ServerPluginModule} object 
	 */
	public void toServerPluginModule(com.soffid.iam.base.model.ServerPluginModuleEntity source, com.soffid.iam.base.api.ServerPluginModule target) {
		// Attributes for ServerPluginModule
		target.setName(source.getName());
		target.setType(source.getType());
		target.setInitClass(source.getInitClass());
		// Incompatible types source.agents and target.agents
		target.setResourceName(source.getResourceName());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.ServerPluginModule} object 
	 */
	public com.soffid.iam.base.api.ServerPluginModule toServerPluginModule(com.soffid.iam.base.model.ServerPluginModuleEntity entity) {
		final com.soffid.iam.base.api.ServerPluginModule target = new com.soffid.iam.base.api.ServerPluginModule();
		this.toServerPluginModule(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.ServerPluginModule} list 
	 */
	public java.util.List<com.soffid.iam.base.api.ServerPluginModule> toServerPluginModuleList (java.util.Collection<com.soffid.iam.base.model.ServerPluginModuleEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.ServerPluginModule> list =
				new java.util.LinkedList<com.soffid.iam.base.api.ServerPluginModule>();
			for (final com.soffid.iam.base.model.ServerPluginModuleEntity instance: instances)
			{
				list.add( toServerPluginModule(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.ServerPluginModule} object 
	 */
	public void serverPluginModuleToEntity (com.soffid.iam.base.api.ServerPluginModule source, com.soffid.iam.base.model.ServerPluginModuleEntity target, boolean copyIfNull) {
		// Attributes for ServerPluginModuleEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		// Missing attribute contents on entity
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		// Missing attribute plugin on entity
		if (copyIfNull || source.getAgents() != null)
		{
			// Incompatible types source.agents and target.agents
		}
		if (copyIfNull || source.getInitClass() != null)
		{
			target.setInitClass(source.getInitClass());
		}
		if (copyIfNull || source.getResourceName() != null)
		{
			target.setResourceName(source.getResourceName());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.ServerPluginModule} list 
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity>  serverPluginModuleToEntityList (java.util.Collection<com.soffid.iam.base.api.ServerPluginModule> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.ServerPluginModuleEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.ServerPluginModuleEntity>();
		for (com.soffid.iam.base.api.ServerPluginModule instance: instances)
		{
			list.add (serverPluginModuleToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} .
	 */
	public com.soffid.iam.base.model.ServerPluginModuleEntity newServerPluginModuleEntity()
	{
		return new com.soffid.iam.base.model.ServerPluginModuleEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.ServerPluginModuleEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.ServerPluginModuleEntity result = (com.soffid.iam.base.model.ServerPluginModuleEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.ServerPluginModuleEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity> result = (java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.ServerPluginModuleEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.ServerPluginModuleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerPluginModuleEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.ServerPluginModuleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerPluginModuleEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.ServerPluginModuleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerPluginModuleEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.ServerPluginModuleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerPluginModuleEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.ServerPluginModuleEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.ServerPluginModuleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerPluginModuleEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.ServerPluginModuleEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.ServerPluginModuleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerPluginModuleEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.ServerPluginModuleEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ServerPluginModuleEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.ServerPluginModuleEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
