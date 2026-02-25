//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity EntryPointRoleEntity
 */
public abstract class EntryPointRoleEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.EntryPointRoleEntityDao
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


	/**
	 * Operation findByRoleId
	 * @param idRol
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> findByRoleId(
	    java.lang.Long idRol)
	
	{
		return findByRoleId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, idRol);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> findByRoleId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long idRol)
	
	{
		return findByRoleId("select autor from com.soffid.iam.am.model.EntryPointRoleEntity autor where autor.role.id=:idRol",
			criteria, idRol);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> findByRoleId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long idRol)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("idRol", idRol, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public void toAccessTreeAuthorization(com.soffid.iam.am.model.EntryPointRoleEntity source, com.soffid.iam.am.api.AccessTreeAuthorization target) {
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
	public com.soffid.iam.am.api.AccessTreeAuthorization toAccessTreeAuthorization(com.soffid.iam.am.model.EntryPointRoleEntity entity) {
		final com.soffid.iam.am.api.AccessTreeAuthorization target = new com.soffid.iam.am.api.AccessTreeAuthorization();
		this.toAccessTreeAuthorization(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessTreeAuthorization> toAccessTreeAuthorizationList (java.util.Collection<com.soffid.iam.am.model.EntryPointRoleEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.AccessTreeAuthorization> list =
				new java.util.LinkedList<com.soffid.iam.am.api.AccessTreeAuthorization>();
			for (final com.soffid.iam.am.model.EntryPointRoleEntity instance: instances)
			{
				list.add( toAccessTreeAuthorization(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public void accessTreeAuthorizationToEntity (com.soffid.iam.am.api.AccessTreeAuthorization source, com.soffid.iam.am.model.EntryPointRoleEntity target, boolean copyIfNull) {
		// Attributes for EntryPointRoleEntity
		// Missing attribute entryPoint on entity
		// Missing attribute authorizationLevel on entity
		// Missing attribute role on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeAuthorization} object 
	 */
	public com.soffid.iam.am.model.EntryPointRoleEntity accessTreeAuthorizationToEntity (com.soffid.iam.am.api.AccessTreeAuthorization instance) {
		com.soffid.iam.am.model.EntryPointRoleEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newEntryPointRoleEntity();
		accessTreeAuthorizationToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity>  accessTreeAuthorizationToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.EntryPointRoleEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.EntryPointRoleEntity>();
		for (com.soffid.iam.am.api.AccessTreeAuthorization instance: instances)
		{
			list.add (accessTreeAuthorizationToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointRoleEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointRoleEntity newEntryPointRoleEntity()
	{
		return new com.soffid.iam.am.model.EntryPointRoleEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointRoleEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.EntryPointRoleEntity result = (com.soffid.iam.am.model.EntryPointRoleEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.EntryPointRoleEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> loadAll() {
		java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> result = (java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.am.model.EntryPointRoleEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointRoleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointRoleEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointRoleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointRoleEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointRoleEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointRoleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointRoleEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointRoleEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointRoleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointRoleEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointRoleEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointRoleEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointRoleEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"EntryPointRoleEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.EntryPointRoleEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.EntryPointRoleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
