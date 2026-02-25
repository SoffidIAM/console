//
// (c) 2014 Soffid
//
//
package com.soffid.iam.rc.model;
/**
 * DAO Base for Entity SoDRoleEntity
 */
public abstract class SoDRoleEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.rc.model.SoDRoleEntityDao
{
	com.soffid.iam.iga.model.RoleEntityDao roleEntityDao;

	/**
	 * Sets reference to <code>roleEntityDao</code>.
	 */
	public void setRoleEntityDao (com.soffid.iam.iga.model.RoleEntityDao roleEntityDao) {
		this.roleEntityDao = roleEntityDao;
	}

	/**
	 * Gets reference to <code>roleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleEntityDao getRoleEntityDao () {
		return roleEntityDao;
	}

	com.soffid.iam.rc.model.SoDRuleEntityDao soDRuleEntityDao;

	/**
	 * Sets reference to <code>soDRuleEntityDao</code>.
	 */
	public void setSoDRuleEntityDao (com.soffid.iam.rc.model.SoDRuleEntityDao soDRuleEntityDao) {
		this.soDRuleEntityDao = soDRuleEntityDao;
	}

	/**
	 * Gets reference to <code>soDRuleEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.SoDRuleEntityDao getSoDRuleEntityDao () {
		return soDRuleEntityDao;
	}

	com.soffid.iam.rc.model.SoDRuleMatrixEntityDao soDRuleMatrixEntityDao;

	/**
	 * Sets reference to <code>soDRuleMatrixEntityDao</code>.
	 */
	public void setSoDRuleMatrixEntityDao (com.soffid.iam.rc.model.SoDRuleMatrixEntityDao soDRuleMatrixEntityDao) {
		this.soDRuleMatrixEntityDao = soDRuleMatrixEntityDao;
	}

	/**
	 * Gets reference to <code>soDRuleMatrixEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.SoDRuleMatrixEntityDao getSoDRuleMatrixEntityDao () {
		return soDRuleMatrixEntityDao;
	}


	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.SoDRole} object 
	 */
	public void toSoDRole(com.soffid.iam.rc.model.SoDRoleEntity source, com.soffid.iam.rc.api.SoDRole target) {
		// Attributes for SoDRole
		target.setId(source.getId());
		// Incompatible types source.role and target.role
		// Missing attribute ruleId on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.SoDRole} object 
	 */
	public com.soffid.iam.rc.api.SoDRole toSoDRole(com.soffid.iam.rc.model.SoDRoleEntity entity) {
		final com.soffid.iam.rc.api.SoDRole target = new com.soffid.iam.rc.api.SoDRole();
		this.toSoDRole(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.SoDRole} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.SoDRole> toSoDRoleList (java.util.Collection<com.soffid.iam.rc.model.SoDRoleEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.rc.api.SoDRole> list =
				new java.util.LinkedList<com.soffid.iam.rc.api.SoDRole>();
			for (final com.soffid.iam.rc.model.SoDRoleEntity instance: instances)
			{
				list.add( toSoDRole(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.SoDRole} object 
	 */
	public void soDRoleToEntity (com.soffid.iam.rc.api.SoDRole source, com.soffid.iam.rc.model.SoDRoleEntity target, boolean copyIfNull) {
		// Attributes for SoDRoleEntity
		if (copyIfNull || source.getRole() != null)
		{
			// Incompatible types source.role and target.role
		}
		// Missing attribute rule on entity
		// Missing attribute columns on entity
		// Missing attribute rows on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.SoDRole} object 
	 */
	public com.soffid.iam.rc.model.SoDRoleEntity soDRoleToEntity (com.soffid.iam.rc.api.SoDRole instance) {
		com.soffid.iam.rc.model.SoDRoleEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newSoDRoleEntity();
		soDRoleToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.SoDRole} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRoleEntity>  soDRoleToEntityList (java.util.Collection<com.soffid.iam.rc.api.SoDRole> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.rc.model.SoDRoleEntity> list =
			new java.util.LinkedList<com.soffid.iam.rc.model.SoDRoleEntity>();
		for (com.soffid.iam.rc.api.SoDRole instance: instances)
		{
			list.add (soDRoleToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.SoDRoleEntity} .
	 */
	public com.soffid.iam.rc.model.SoDRoleEntity newSoDRoleEntity()
	{
		return new com.soffid.iam.rc.model.SoDRoleEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.SoDRoleEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.rc.model.SoDRoleEntity result = (com.soffid.iam.rc.model.SoDRoleEntity) this.getHibernateTemplate().get(com.soffid.iam.rc.model.SoDRoleEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRoleEntity> loadAll() {
		java.util.List<com.soffid.iam.rc.model.SoDRoleEntity> result = (java.util.List<com.soffid.iam.rc.model.SoDRoleEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.rc.model.SoDRoleEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.SoDRoleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.SoDRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SoDRoleEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.SoDRoleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.SoDRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SoDRoleEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.SoDRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SoDRoleEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.SoDRoleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SoDRoleEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.SoDRoleEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.SoDRoleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SoDRoleEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.SoDRoleEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SoDRoleEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.SoDRoleEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"SoDRoleEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.rc.model.SoDRoleEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRoleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.rc.model.SoDRoleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.SoDRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRoleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.rc.model.SoDRoleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
