//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity SystemGroupEntity
 */
public abstract class SystemGroupEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.SystemGroupEntityDao
{
	com.soffid.iam.iga.model.GroupEntityDao groupEntityDao;

	/**
	 * Sets reference to <code>groupEntityDao</code>.
	 */
	public void setGroupEntityDao (com.soffid.iam.iga.model.GroupEntityDao groupEntityDao) {
		this.groupEntityDao = groupEntityDao;
	}

	/**
	 * Gets reference to <code>groupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupEntityDao getGroupEntityDao () {
		return groupEntityDao;
	}

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


	/**
	 * Operation findBySystem
	 * @param systemName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.SystemGroupEntity> findBySystem(
	    java.lang.String systemName)
	
	{
		return findBySystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, systemName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemGroupEntity> findBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	
	{
		return findBySystem("select gd from com.soffid.iam.iga.model.SystemGroupEntity gd where gd.system.name=:systemName and gd.system.tenant.id = :tenantId",
			criteria, systemName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemGroupEntity> findBySystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("systemName", systemName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.SystemGroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.SystemGroup} object 
	 */
	public void toSystemGroup(com.soffid.iam.iga.model.SystemGroupEntity source, com.soffid.iam.iga.api.SystemGroup target) {
		// Attributes for SystemGroup
		target.setId(source.getId());
		// Missing attribute systemCode on entity
		// Missing attribute groupCode on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.SystemGroup} object 
	 */
	public com.soffid.iam.iga.api.SystemGroup toSystemGroup(com.soffid.iam.iga.model.SystemGroupEntity entity) {
		final com.soffid.iam.iga.api.SystemGroup target = new com.soffid.iam.iga.api.SystemGroup();
		this.toSystemGroup(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.SystemGroup} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.SystemGroup> toSystemGroupList (java.util.Collection<com.soffid.iam.iga.model.SystemGroupEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.SystemGroup> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.SystemGroup>();
			for (final com.soffid.iam.iga.model.SystemGroupEntity instance: instances)
			{
				list.add( toSystemGroup(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.SystemGroup} object 
	 */
	public void systemGroupToEntity (com.soffid.iam.iga.api.SystemGroup source, com.soffid.iam.iga.model.SystemGroupEntity target, boolean copyIfNull) {
		// Attributes for SystemGroupEntity
		// Missing attribute group on entity
		// Missing attribute system on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.SystemGroup} object 
	 */
	public com.soffid.iam.iga.model.SystemGroupEntity systemGroupToEntity (com.soffid.iam.iga.api.SystemGroup instance) {
		com.soffid.iam.iga.model.SystemGroupEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newSystemGroupEntity();
		systemGroupToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.SystemGroup} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemGroupEntity>  systemGroupToEntityList (java.util.Collection<com.soffid.iam.iga.api.SystemGroup> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.SystemGroupEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.SystemGroupEntity>();
		for (com.soffid.iam.iga.api.SystemGroup instance: instances)
		{
			list.add (systemGroupToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.SystemGroupEntity} .
	 */
	public com.soffid.iam.iga.model.SystemGroupEntity newSystemGroupEntity()
	{
		return new com.soffid.iam.iga.model.SystemGroupEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.SystemGroupEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.SystemGroupEntity result = (com.soffid.iam.iga.model.SystemGroupEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.SystemGroupEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemGroupEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.SystemGroupEntity> result = (java.util.List<com.soffid.iam.iga.model.SystemGroupEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.SystemGroupEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.SystemGroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.SystemGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SystemGroupEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.SystemGroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.SystemGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SystemGroupEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.SystemGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SystemGroupEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.SystemGroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.SystemGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SystemGroupEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.SystemGroupEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.SystemGroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.SystemGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SystemGroupEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.SystemGroupEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.SystemGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SystemGroupEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.SystemGroupEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"SystemGroupEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.SystemGroupEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemGroupEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.SystemGroupEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.SystemGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemGroupEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.SystemGroupEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
