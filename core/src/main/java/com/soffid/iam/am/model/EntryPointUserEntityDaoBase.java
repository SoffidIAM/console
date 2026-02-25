//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity EntryPointUserEntity
 */
public abstract class EntryPointUserEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.EntryPointUserEntityDao
{
	com.soffid.iam.rc.model.AuditEntityDao auditEntityDao;

	/**
	 * Sets reference to <code>auditEntityDao</code>.
	 */
	public void setAuditEntityDao (com.soffid.iam.rc.model.AuditEntityDao auditEntityDao) {
		this.auditEntityDao = auditEntityDao;
	}

	/**
	 * Gets reference to <code>auditEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.AuditEntityDao getAuditEntityDao () {
		return auditEntityDao;
	}

	com.soffid.iam.am.model.EntryPointEntityDao entryPointEntityDao;

	/**
	 * Sets reference to <code>entryPointEntityDao</code>.
	 */
	public void setEntryPointEntityDao (com.soffid.iam.am.model.EntryPointEntityDao entryPointEntityDao) {
		this.entryPointEntityDao = entryPointEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointEntityDao getEntryPointEntityDao () {
		return entryPointEntityDao;
	}

	com.soffid.iam.base.model.UserEntityDao userEntityDao;

	/**
	 * Sets reference to <code>userEntityDao</code>.
	 */
	public void setUserEntityDao (com.soffid.iam.base.model.UserEntityDao userEntityDao) {
		this.userEntityDao = userEntityDao;
	}

	/**
	 * Gets reference to <code>userEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserEntityDao getUserEntityDao () {
		return userEntityDao;
	}


	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public void toAccessTreeAuthorization(com.soffid.iam.am.model.EntryPointUserEntity source, com.soffid.iam.am.api.AccessTreeAuthorization target) {
		// Attributes for AccessTreeAuthorization
		target.setId(source.getId());
		// Missing attribute authorizationLevelDescription on entity
		// Missing attribute accessTreeId on entity
		// Missing attribute authorizationEntityType on entity
		// Missing attribute authorizationEntityId on entity
		// Missing attribute authorizedEntityDescription on entity
		// Missing attribute authorizedEntityCode on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public com.soffid.iam.am.api.AccessTreeAuthorization toAccessTreeAuthorization(com.soffid.iam.am.model.EntryPointUserEntity entity) {
		final com.soffid.iam.am.api.AccessTreeAuthorization target = new com.soffid.iam.am.api.AccessTreeAuthorization();
		this.toAccessTreeAuthorization(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessTreeAuthorization> toAccessTreeAuthorizationList (java.util.Collection<com.soffid.iam.am.model.EntryPointUserEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.AccessTreeAuthorization> list =
				new java.util.LinkedList<com.soffid.iam.am.api.AccessTreeAuthorization>();
			for (final com.soffid.iam.am.model.EntryPointUserEntity instance: instances)
			{
				list.add( toAccessTreeAuthorization(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public void accessTreeAuthorizationToEntity (com.soffid.iam.am.api.AccessTreeAuthorization source, com.soffid.iam.am.model.EntryPointUserEntity target, boolean copyIfNull) {
		// Attributes for EntryPointUserEntity
		// Missing attribute authorizationLevel on entity
		// Missing attribute entryPoint on entity
		// Missing attribute user on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public com.soffid.iam.am.model.EntryPointUserEntity accessTreeAuthorizationToEntity (com.soffid.iam.am.api.AccessTreeAuthorization instance) {
		com.soffid.iam.am.model.EntryPointUserEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newEntryPointUserEntity();
		accessTreeAuthorizationToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointUserEntity>  accessTreeAuthorizationToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.EntryPointUserEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.EntryPointUserEntity>();
		for (com.soffid.iam.am.api.AccessTreeAuthorization instance: instances)
		{
			list.add (accessTreeAuthorizationToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointUserEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointUserEntity newEntryPointUserEntity()
	{
		return new com.soffid.iam.am.model.EntryPointUserEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointUserEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.EntryPointUserEntity result = (com.soffid.iam.am.model.EntryPointUserEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.EntryPointUserEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointUserEntity> loadAll() {
		java.util.List<com.soffid.iam.am.model.EntryPointUserEntity> result = (java.util.List<com.soffid.iam.am.model.EntryPointUserEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.am.model.EntryPointUserEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointUserEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointUserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointUserEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointUserEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointUserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointUserEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointUserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointUserEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointUserEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointUserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointUserEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointUserEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointUserEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointUserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointUserEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointUserEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointUserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointUserEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointUserEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"EntryPointUserEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.EntryPointUserEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointUserEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.EntryPointUserEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointUserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointUserEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.EntryPointUserEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
