//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity ReconcileRoleEntity
 */
public abstract class ReconcileRoleEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.ReconcileRoleEntityDao
{
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
	 * Operation findByProcessId
	 * @param processId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity> findByProcessId(
	    java.lang.Long processId)
	
	{
		return findByProcessId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, processId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity> findByProcessId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long processId)
	
	{
		return findByProcessId("from com.soffid.iam.iga.model.ReconcileRoleEntity where tenant.id=:tenantId and processId=:processId",
			criteria, processId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity> findByProcessId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long processId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("processId", processId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ReconcileRole} object 
	 */
	public void toReconcileRole(com.soffid.iam.iga.model.ReconcileRoleEntity source, com.soffid.iam.iga.api.ReconcileRole target) {
		// Attributes for ReconcileRole
		target.setId(source.getId());
		target.setRoleName(source.getRoleName());
		target.setDescription(source.getDescription());
		target.setProcessId(source.getProcessId());
		target.setProposedAction(source.getProposedAction());
		target.setDispatcher(source.getDispatcher());
		target.setAppName(source.getAppName());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileRole} object 
	 */
	public com.soffid.iam.iga.api.ReconcileRole toReconcileRole(com.soffid.iam.iga.model.ReconcileRoleEntity entity) {
		final com.soffid.iam.iga.api.ReconcileRole target = new com.soffid.iam.iga.api.ReconcileRole();
		this.toReconcileRole(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileRole} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ReconcileRole> toReconcileRoleList (java.util.Collection<com.soffid.iam.iga.model.ReconcileRoleEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.ReconcileRole> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.ReconcileRole>();
			for (final com.soffid.iam.iga.model.ReconcileRoleEntity instance: instances)
			{
				list.add( toReconcileRole(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ReconcileRole} object 
	 */
	public void reconcileRoleToEntity (com.soffid.iam.iga.api.ReconcileRole source, com.soffid.iam.iga.model.ReconcileRoleEntity target, boolean copyIfNull) {
		// Attributes for ReconcileRoleEntity
		if (copyIfNull || source.getRoleName() != null)
		{
			target.setRoleName(source.getRoleName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getProcessId() != null)
		{
			target.setProcessId(source.getProcessId());
		}
		if (copyIfNull || source.getProposedAction() != null)
		{
			target.setProposedAction(source.getProposedAction());
		}
		if (copyIfNull || source.getDispatcher() != null)
		{
			target.setDispatcher(source.getDispatcher());
		}
		if (copyIfNull || source.getAppName() != null)
		{
			target.setAppName(source.getAppName());
		}
		// Missing attribute tenant on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileRole} object 
	 */
	public com.soffid.iam.iga.model.ReconcileRoleEntity reconcileRoleToEntity (com.soffid.iam.iga.api.ReconcileRole instance) {
		com.soffid.iam.iga.model.ReconcileRoleEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newReconcileRoleEntity();
		reconcileRoleToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileRole} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity>  reconcileRoleToEntityList (java.util.Collection<com.soffid.iam.iga.api.ReconcileRole> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.ReconcileRoleEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.ReconcileRoleEntity>();
		for (com.soffid.iam.iga.api.ReconcileRole instance: instances)
		{
			list.add (reconcileRoleToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} .
	 */
	public com.soffid.iam.iga.model.ReconcileRoleEntity newReconcileRoleEntity()
	{
		return new com.soffid.iam.iga.model.ReconcileRoleEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ReconcileRoleEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.ReconcileRoleEntity result = (com.soffid.iam.iga.model.ReconcileRoleEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.ReconcileRoleEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.ReconcileRoleEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ReconcileRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileRoleEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ReconcileRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileRoleEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ReconcileRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileRoleEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileRoleEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileRoleEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileRoleEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileRoleEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileRoleEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileRoleEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ReconcileRoleEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.ReconcileRoleEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
