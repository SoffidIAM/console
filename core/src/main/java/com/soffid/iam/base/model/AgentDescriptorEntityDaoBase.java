//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity AgentDescriptorEntity
 */
public abstract class AgentDescriptorEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.AgentDescriptorEntityDao
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

	com.soffid.iam.base.model.ServerPluginModuleEntityDao serverPluginModuleEntityDao;

	/**
	 * Sets reference to <code>serverPluginModuleEntityDao</code>.
	 */
	public void setServerPluginModuleEntityDao (com.soffid.iam.base.model.ServerPluginModuleEntityDao serverPluginModuleEntityDao) {
		this.serverPluginModuleEntityDao = serverPluginModuleEntityDao;
	}

	/**
	 * Gets reference to <code>serverPluginModuleEntityDao</code>.
	 */
	public com.soffid.iam.base.model.ServerPluginModuleEntityDao getServerPluginModuleEntityDao () {
		return serverPluginModuleEntityDao;
	}

	com.soffid.iam.base.model.AgentPropertyEntityDao agentPropertyEntityDao;

	/**
	 * Sets reference to <code>agentPropertyEntityDao</code>.
	 */
	public void setAgentPropertyEntityDao (com.soffid.iam.base.model.AgentPropertyEntityDao agentPropertyEntityDao) {
		this.agentPropertyEntityDao = agentPropertyEntityDao;
	}

	/**
	 * Gets reference to <code>agentPropertyEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AgentPropertyEntityDao getAgentPropertyEntityDao () {
		return agentPropertyEntityDao;
	}


	/**
	 * Operation findByClass
	 * @param tenant
	 * @param className
	 * @return
	**/
	public com.soffid.iam.base.model.AgentDescriptorEntity findByClass(
	    java.lang.String tenant, 
	    java.lang.String className)
	
	{
		return findByClass((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, tenant, className);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntity findByClass(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String tenant, java.lang.String className)
	
	{
		return findByClass("select agentDescriptorEntity\nfrom com.soffid.iam.base.model.AgentDescriptorEntity as agentDescriptorEntity\njoin agentDescriptorEntity.plugin as plugin join plugin.tenant as tenant where agentDescriptorEntity.className = :className  and  tenant.name = :tenant order by agentDescriptorEntity.description",
			criteria, tenant, className);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntity findByClass(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String tenant, java.lang.String className)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("tenant", tenant, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("className", className, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.AgentDescriptorEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.AgentDescriptorEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.AgentDescriptorEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAllOnlyBasicData
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> findAllOnlyBasicData(
)
	
	{
		return findAllOnlyBasicData((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> findAllOnlyBasicData(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findAllOnlyBasicData("select agentDescriptorEntity\nfrom com.soffid.iam.base.model.AgentDescriptorEntity as agentDescriptorEntity\norder by agentDescriptorEntity.description",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> findAllOnlyBasicData(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByDescription
	 * @param description
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> findByDescription(
	    java.lang.String description)
	
	{
		return findByDescription((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, description);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> findByDescription(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String description)
	
	{
		return findByDescription("from com.soffid.iam.base.model.AgentDescriptorEntity where description=:description",
			criteria, description);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> findByDescription(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String description)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("description", description, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.AgentDescriptor} object 
	 */
	public void toAgentDescriptor(com.soffid.iam.base.model.AgentDescriptorEntity source, com.soffid.iam.base.api.AgentDescriptor target) {
		// Attributes for AgentDescriptor
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		target.setClassName(source.getClassName());
		target.setUserInterface(source.getUserInterface());
		target.setEnableAccessControl(source.isEnableAccessControl());
		target.setAuthoritativeSource(source.isAuthoritativeSource());
		target.setEnableAttributeMapping(source.isEnableAttributeMapping());
		target.setEnableObjectTriggers(java.lang.Boolean.TRUE.equals(source.getEnableObjectTriggers()));
		target.setService(java.lang.Boolean.TRUE.equals(source.getService()));
		// Incompatible types source.properties and target.properties
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.AgentDescriptor} object 
	 */
	public com.soffid.iam.base.api.AgentDescriptor toAgentDescriptor(com.soffid.iam.base.model.AgentDescriptorEntity entity) {
		final com.soffid.iam.base.api.AgentDescriptor target = new com.soffid.iam.base.api.AgentDescriptor();
		this.toAgentDescriptor(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.AgentDescriptor} list 
	 */
	public java.util.List<com.soffid.iam.base.api.AgentDescriptor> toAgentDescriptorList (java.util.Collection<com.soffid.iam.base.model.AgentDescriptorEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.AgentDescriptor> list =
				new java.util.LinkedList<com.soffid.iam.base.api.AgentDescriptor>();
			for (final com.soffid.iam.base.model.AgentDescriptorEntity instance: instances)
			{
				list.add( toAgentDescriptor(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.AgentDescriptor} object 
	 */
	public void agentDescriptorToEntity (com.soffid.iam.base.api.AgentDescriptor source, com.soffid.iam.base.model.AgentDescriptorEntity target, boolean copyIfNull) {
		// Attributes for AgentDescriptorEntity
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getClassName() != null)
		{
			target.setClassName(source.getClassName());
		}
		if (copyIfNull || source.getUserInterface() != null)
		{
			target.setUserInterface(source.getUserInterface());
		}
		// Missing attribute plugin on entity
		target.setEnableAccessControl(source.isEnableAccessControl());
		// Missing attribute module on entity
		target.setAuthoritativeSource(source.isAuthoritativeSource());
		target.setEnableAttributeMapping(source.isEnableAttributeMapping());
		target.setEnableObjectTriggers(new java.lang.Boolean(source.isEnableObjectTriggers()));
		target.setService(new java.lang.Boolean(source.isService()));
		// Missing attribute defaultObjectMappings on entity
		if (copyIfNull || source.getProperties() != null)
		{
			// Incompatible types source.properties and target.properties
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.AgentDescriptor} object 
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntity agentDescriptorToEntity (com.soffid.iam.base.api.AgentDescriptor instance) {
		com.soffid.iam.base.model.AgentDescriptorEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newAgentDescriptorEntity();
		agentDescriptorToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.AgentDescriptor} list 
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity>  agentDescriptorToEntityList (java.util.Collection<com.soffid.iam.base.api.AgentDescriptor> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.AgentDescriptorEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.AgentDescriptorEntity>();
		for (com.soffid.iam.base.api.AgentDescriptor instance: instances)
		{
			list.add (agentDescriptorToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.AgentDescriptorEntity} .
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntity newAgentDescriptorEntity()
	{
		return new com.soffid.iam.base.model.AgentDescriptorEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.AgentDescriptorEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.AgentDescriptorEntity result = (com.soffid.iam.base.model.AgentDescriptorEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.AgentDescriptorEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> result = (java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.AgentDescriptorEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.AgentDescriptorEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.AgentDescriptorEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AgentDescriptorEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.AgentDescriptorEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.AgentDescriptorEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AgentDescriptorEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.AgentDescriptorEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AgentDescriptorEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.AgentDescriptorEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.AgentDescriptorEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AgentDescriptorEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AgentDescriptorEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.AgentDescriptorEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.AgentDescriptorEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AgentDescriptorEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AgentDescriptorEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.AgentDescriptorEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AgentDescriptorEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AgentDescriptorEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AgentDescriptorEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.AgentDescriptorEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.AgentDescriptorEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.AgentDescriptorEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
