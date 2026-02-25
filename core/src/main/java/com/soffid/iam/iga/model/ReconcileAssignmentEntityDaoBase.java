//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity ReconcileAssignmentEntity
 */
public abstract class ReconcileAssignmentEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.ReconcileAssignmentEntityDao
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
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity> findByProcessId(
	    java.lang.Long processId)
	
	{
		return findByProcessId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, processId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity> findByProcessId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long processId)
	
	{
		return findByProcessId("from com.soffid.iam.iga.model.ReconcileAssignmentEntity where tenant.id=:tenantId and processId=:processId",
			criteria, processId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity> findByProcessId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long processId)
	
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
			return (java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ReconcileAssignment} object 
	 */
	public void toReconcileAssignment(com.soffid.iam.iga.model.ReconcileAssignmentEntity source, com.soffid.iam.iga.api.ReconcileAssignment target) {
		// Attributes for ReconcileAssignment
		target.setId(source.getId());
		target.setAssignmentName(source.getAssignmentName());
		target.setProcessId(source.getProcessId());
		target.setAccountName(source.getAccountName());
		target.setRoleName(source.getRoleName());
		target.setProposedAction(source.getProposedAction());
		target.setDispatcher(source.getDispatcher());
		target.setDomainValue(source.getDomainValue());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileAssignment} object 
	 */
	public com.soffid.iam.iga.api.ReconcileAssignment toReconcileAssignment(com.soffid.iam.iga.model.ReconcileAssignmentEntity entity) {
		final com.soffid.iam.iga.api.ReconcileAssignment target = new com.soffid.iam.iga.api.ReconcileAssignment();
		this.toReconcileAssignment(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileAssignment} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ReconcileAssignment> toReconcileAssignmentList (java.util.Collection<com.soffid.iam.iga.model.ReconcileAssignmentEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.ReconcileAssignment> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.ReconcileAssignment>();
			for (final com.soffid.iam.iga.model.ReconcileAssignmentEntity instance: instances)
			{
				list.add( toReconcileAssignment(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ReconcileAssignment} object 
	 */
	public void reconcileAssignmentToEntity (com.soffid.iam.iga.api.ReconcileAssignment source, com.soffid.iam.iga.model.ReconcileAssignmentEntity target, boolean copyIfNull) {
		// Attributes for ReconcileAssignmentEntity
		if (copyIfNull || source.getAssignmentName() != null)
		{
			target.setAssignmentName(source.getAssignmentName());
		}
		if (copyIfNull || source.getProcessId() != null)
		{
			target.setProcessId(source.getProcessId());
		}
		if (copyIfNull || source.getAccountName() != null)
		{
			target.setAccountName(source.getAccountName());
		}
		if (copyIfNull || source.getRoleName() != null)
		{
			target.setRoleName(source.getRoleName());
		}
		if (copyIfNull || source.getProposedAction() != null)
		{
			target.setProposedAction(source.getProposedAction());
		}
		if (copyIfNull || source.getDispatcher() != null)
		{
			target.setDispatcher(source.getDispatcher());
		}
		if (copyIfNull || source.getDomainValue() != null)
		{
			target.setDomainValue(source.getDomainValue());
		}
		// Missing attribute tenant on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileAssignment} object 
	 */
	public com.soffid.iam.iga.model.ReconcileAssignmentEntity reconcileAssignmentToEntity (com.soffid.iam.iga.api.ReconcileAssignment instance) {
		com.soffid.iam.iga.model.ReconcileAssignmentEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newReconcileAssignmentEntity();
		reconcileAssignmentToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileAssignment} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity>  reconcileAssignmentToEntityList (java.util.Collection<com.soffid.iam.iga.api.ReconcileAssignment> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.ReconcileAssignmentEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.ReconcileAssignmentEntity>();
		for (com.soffid.iam.iga.api.ReconcileAssignment instance: instances)
		{
			list.add (reconcileAssignmentToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} .
	 */
	public com.soffid.iam.iga.model.ReconcileAssignmentEntity newReconcileAssignmentEntity()
	{
		return new com.soffid.iam.iga.model.ReconcileAssignmentEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ReconcileAssignmentEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.ReconcileAssignmentEntity result = (com.soffid.iam.iga.model.ReconcileAssignmentEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.ReconcileAssignmentEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.ReconcileAssignmentEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ReconcileAssignmentEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAssignmentEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ReconcileAssignmentEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAssignmentEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ReconcileAssignmentEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAssignmentEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAssignmentEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAssignmentEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileAssignmentEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAssignmentEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAssignmentEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileAssignmentEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAssignmentEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAssignmentEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileAssignmentEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ReconcileAssignmentEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.ReconcileAssignmentEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
