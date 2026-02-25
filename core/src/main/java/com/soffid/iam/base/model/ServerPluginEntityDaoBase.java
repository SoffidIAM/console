//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity ServerPluginEntity
 */
public abstract class ServerPluginEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.ServerPluginEntityDao
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

	com.soffid.iam.base.model.TenantEntityDao tenantEntityDao;

	/**
	 * Sets reference to <code>tenantEntityDao</code>.
	 */
	public void setTenantEntityDao (com.soffid.iam.base.model.TenantEntityDao tenantEntityDao) {
		this.tenantEntityDao = tenantEntityDao;
	}

	/**
	 * Gets reference to <code>tenantEntityDao</code>.
	 */
	public com.soffid.iam.base.model.TenantEntityDao getTenantEntityDao () {
		return tenantEntityDao;
	}


	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.base.model.ServerPluginEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.ServerPluginEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.base.model.ServerPluginEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.ServerPluginEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.ServerPluginEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.ServerPluginEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.ServerPluginEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAll
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> findAll(
)
	
	{
		return findAll((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> findAll(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findAll("select serverPluginEntity from com.soffid.iam.base.model.ServerPluginEntity as serverPluginEntity",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> findAll(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
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
			return (java.util.List<com.soffid.iam.base.model.ServerPluginEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAllBasicData
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> findAllBasicData(
)
	
	{
		return findAllBasicData((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> findAllBasicData(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findAllBasicData("select id, version, name, enabled from com.soffid.iam.base.model.ServerPluginEntity as serverPluginEntity",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> findAllBasicData(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
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
			return (java.util.List<com.soffid.iam.base.model.ServerPluginEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAgentsBasicDataByServerPluginID
	 * @param id
	 * @return
	**/
	public java.util.List<java.lang.Object[]> findAgentsBasicDataByServerPluginID(
	    java.lang.Long id)
	
	{
		return findAgentsBasicDataByServerPluginID((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, id);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<java.lang.Object[]> findAgentsBasicDataByServerPluginID(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		return findAgentsBasicDataByServerPluginID("select agent.id ,agent.description, agent.className, agent.enableAccessControl , agent.authoritativeSource,\nagent.enableAttributeMapping, agent.enableObjectTriggers, p.tenant.name \nfrom com.soffid.iam.base.model.AgentDescriptorEntity agent \nleft join agent.plugin as p \nwhere p.id=:id",
			criteria, id);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<java.lang.Object[]> findAgentsBasicDataByServerPluginID(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("id", id, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<java.lang.Object[]>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.ServerPlugin} object 
	 */
	public void toServerPlugin(com.soffid.iam.base.model.ServerPluginEntity source, com.soffid.iam.base.api.ServerPlugin target) {
		// Attributes for ServerPlugin
		target.setId(source.getId());
		target.setVersion(source.getVersion());
		target.setName(source.getName());
		target.setEnabled(source.isEnabled());
		target.setAuthor(source.getAuthor());
		target.setDeployed(source.getDeployed());
		// Incompatible types source.modules and target.modules
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.ServerPlugin} object 
	 */
	public com.soffid.iam.base.api.ServerPlugin toServerPlugin(com.soffid.iam.base.model.ServerPluginEntity entity) {
		final com.soffid.iam.base.api.ServerPlugin target = new com.soffid.iam.base.api.ServerPlugin();
		this.toServerPlugin(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.ServerPlugin} list 
	 */
	public java.util.List<com.soffid.iam.base.api.ServerPlugin> toServerPluginList (java.util.Collection<com.soffid.iam.base.model.ServerPluginEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.ServerPlugin> list =
				new java.util.LinkedList<com.soffid.iam.base.api.ServerPlugin>();
			for (final com.soffid.iam.base.model.ServerPluginEntity instance: instances)
			{
				list.add( toServerPlugin(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.ServerPlugin} object 
	 */
	public void serverPluginToEntity (com.soffid.iam.base.api.ServerPlugin source, com.soffid.iam.base.model.ServerPluginEntity target, boolean copyIfNull) {
		// Attributes for ServerPluginEntity
		if (copyIfNull || source.getVersion() != null)
		{
			target.setVersion(source.getVersion());
		}
		// Missing attribute content on entity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getAuthor() != null)
		{
			target.setAuthor(source.getAuthor());
		}
		if (copyIfNull || source.getDeployed() != null)
		{
			target.setDeployed(source.getDeployed());
		}
		target.setEnabled(source.isEnabled());
		// Missing attribute tenant on entity
		// Missing attribute agents on entity
		if (copyIfNull || source.getModules() != null)
		{
			// Incompatible types source.modules and target.modules
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.ServerPlugin} object 
	 */
	public com.soffid.iam.base.model.ServerPluginEntity serverPluginToEntity (com.soffid.iam.base.api.ServerPlugin instance) {
		com.soffid.iam.base.model.ServerPluginEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newServerPluginEntity();
		serverPluginToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.ServerPlugin} list 
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity>  serverPluginToEntityList (java.util.Collection<com.soffid.iam.base.api.ServerPlugin> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.ServerPluginEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.ServerPluginEntity>();
		for (com.soffid.iam.base.api.ServerPlugin instance: instances)
		{
			list.add (serverPluginToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.ServerPluginEntity} .
	 */
	public com.soffid.iam.base.model.ServerPluginEntity newServerPluginEntity()
	{
		return new com.soffid.iam.base.model.ServerPluginEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.ServerPluginEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.ServerPluginEntity result = (com.soffid.iam.base.model.ServerPluginEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.ServerPluginEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.base.model.ServerPluginEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.base.model.ServerPluginEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.ServerPluginEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.ServerPluginEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerPluginEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.ServerPluginEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.ServerPluginEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerPluginEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.ServerPluginEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServerPluginEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.ServerPluginEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.ServerPluginEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerPluginEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.ServerPluginEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.ServerPluginEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.ServerPluginEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerPluginEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.ServerPluginEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.ServerPluginEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServerPluginEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.ServerPluginEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ServerPluginEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.ServerPluginEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.ServerPluginEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.ServerPluginEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
