//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity JumpServerGroupEntity
 */
public abstract class JumpServerGroupEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.JumpServerGroupEntityDao
{
	com.soffid.iam.pam.model.JumpServerEntityDao jumpServerEntityDao;

	/**
	 * Sets reference to <code>jumpServerEntityDao</code>.
	 */
	public void setJumpServerEntityDao (com.soffid.iam.pam.model.JumpServerEntityDao jumpServerEntityDao) {
		this.jumpServerEntityDao = jumpServerEntityDao;
	}

	/**
	 * Gets reference to <code>jumpServerEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.JumpServerEntityDao getJumpServerEntityDao () {
		return jumpServerEntityDao;
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
	public com.soffid.iam.pam.model.JumpServerGroupEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.pam.model.JumpServerGroupEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.pam.model.JumpServerGroupEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.pam.model.JumpServerGroupEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.pam.model.JumpServerGroupEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.JumpServerGroup} object 
	 */
	public void toJumpServerGroup(com.soffid.iam.pam.model.JumpServerGroupEntity source, com.soffid.iam.pam.api.JumpServerGroup target) {
		// Attributes for JumpServerGroup
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setStoreUrl(source.getStoreUrl());
		target.setStoreUserName(source.getStoreUserName());
		target.setPassword(source.getPassword());
		// Incompatible types source.jumpServers and target.jumpServers
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.JumpServerGroup} object 
	 */
	public com.soffid.iam.pam.api.JumpServerGroup toJumpServerGroup(com.soffid.iam.pam.model.JumpServerGroupEntity entity) {
		final com.soffid.iam.pam.api.JumpServerGroup target = new com.soffid.iam.pam.api.JumpServerGroup();
		this.toJumpServerGroup(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.JumpServerGroup} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.JumpServerGroup> toJumpServerGroupList (java.util.Collection<com.soffid.iam.pam.model.JumpServerGroupEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.pam.api.JumpServerGroup> list =
				new java.util.LinkedList<com.soffid.iam.pam.api.JumpServerGroup>();
			for (final com.soffid.iam.pam.model.JumpServerGroupEntity instance: instances)
			{
				list.add( toJumpServerGroup(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.JumpServerGroup} object 
	 */
	public void jumpServerGroupToEntity (com.soffid.iam.pam.api.JumpServerGroup source, com.soffid.iam.pam.model.JumpServerGroupEntity target, boolean copyIfNull) {
		// Attributes for JumpServerGroupEntity
		// Missing attribute tenant on entity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getStoreUrl() != null)
		{
			target.setStoreUrl(source.getStoreUrl());
		}
		if (copyIfNull || source.getStoreUserName() != null)
		{
			target.setStoreUserName(source.getStoreUserName());
		}
		if (copyIfNull || source.getPassword() != null)
		{
			target.setPassword(source.getPassword());
		}
		if (copyIfNull || source.getJumpServers() != null)
		{
			// Incompatible types source.jumpServers and target.jumpServers
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.JumpServerGroup} object 
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntity jumpServerGroupToEntity (com.soffid.iam.pam.api.JumpServerGroup instance) {
		com.soffid.iam.pam.model.JumpServerGroupEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newJumpServerGroupEntity();
		jumpServerGroupToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.JumpServerGroup} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerGroupEntity>  jumpServerGroupToEntityList (java.util.Collection<com.soffid.iam.pam.api.JumpServerGroup> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.pam.model.JumpServerGroupEntity> list =
			new java.util.LinkedList<com.soffid.iam.pam.model.JumpServerGroupEntity>();
		for (com.soffid.iam.pam.api.JumpServerGroup instance: instances)
		{
			list.add (jumpServerGroupToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} .
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntity newJumpServerGroupEntity()
	{
		return new com.soffid.iam.pam.model.JumpServerGroupEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.JumpServerGroupEntity result = (com.soffid.iam.pam.model.JumpServerGroupEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.JumpServerGroupEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerGroupEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.pam.model.JumpServerGroupEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.pam.model.JumpServerGroupEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.JumpServerGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"JumpServerGroupEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.JumpServerGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"JumpServerGroupEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.JumpServerGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"JumpServerGroupEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.JumpServerGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"JumpServerGroupEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.JumpServerGroupEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.JumpServerGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"JumpServerGroupEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.JumpServerGroupEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.JumpServerGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"JumpServerGroupEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.JumpServerGroupEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"JumpServerGroupEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.JumpServerGroupEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerGroupEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.JumpServerGroupEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.JumpServerGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerGroupEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.JumpServerGroupEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
