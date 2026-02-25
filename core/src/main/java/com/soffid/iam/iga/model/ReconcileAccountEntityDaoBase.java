//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity ReconcileAccountEntity
 */
public abstract class ReconcileAccountEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.ReconcileAccountEntityDao
{
	com.soffid.iam.base.model.AccountEntityDao accountEntityDao;

	/**
	 * Sets reference to <code>accountEntityDao</code>.
	 */
	public void setAccountEntityDao (com.soffid.iam.base.model.AccountEntityDao accountEntityDao) {
		this.accountEntityDao = accountEntityDao;
	}

	/**
	 * Gets reference to <code>accountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountEntityDao getAccountEntityDao () {
		return accountEntityDao;
	}

	com.soffid.iam.iga.model.ReconcileAccountAttributesEntityDao reconcileAccountAttributesEntityDao;

	/**
	 * Sets reference to <code>reconcileAccountAttributesEntityDao</code>.
	 */
	public void setReconcileAccountAttributesEntityDao (com.soffid.iam.iga.model.ReconcileAccountAttributesEntityDao reconcileAccountAttributesEntityDao) {
		this.reconcileAccountAttributesEntityDao = reconcileAccountAttributesEntityDao;
	}

	/**
	 * Gets reference to <code>reconcileAccountAttributesEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ReconcileAccountAttributesEntityDao getReconcileAccountAttributesEntityDao () {
		return reconcileAccountAttributesEntityDao;
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
	 * Operation findByProcessId
	 * @param processId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity> findByProcessId(
	    java.lang.Long processId)
	
	{
		return findByProcessId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, processId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity> findByProcessId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long processId)
	
	{
		return findByProcessId("from com.soffid.iam.iga.model.ReconcileAccountEntity where tenant.id=:tenantId and processId=:processId",
			criteria, processId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity> findByProcessId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long processId)
	
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
			return (java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ReconcileAccount} object 
	 */
	public void toReconcileAccount(com.soffid.iam.iga.model.ReconcileAccountEntity source, com.soffid.iam.iga.api.ReconcileAccount target) {
		// Attributes for ReconcileAccount
		target.setId(source.getId());
		target.setAccountName(source.getAccountName());
		target.setDescription(source.getDescription());
		target.setProcessId(source.getProcessId());
		target.setProposedAction(source.getProposedAction());
		target.setDispatcher(source.getDispatcher());
		target.setPrimaryGroup(source.getPrimaryGroup());
		target.setUserCode(source.getUserCode());
		target.setUserType(source.getUserType());
		target.setUserFullName(source.getUserFullName());
		target.setAccountType(source.getAccountType());
		target.setActive(source.isActive());
		target.setNewAccount(source.getNewAccount());
		target.setDeletedAccount(source.getDeletedAccount());
		// Incompatible types source.attributes and target.attributes
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileAccount} object 
	 */
	public com.soffid.iam.iga.api.ReconcileAccount toReconcileAccount(com.soffid.iam.iga.model.ReconcileAccountEntity entity) {
		final com.soffid.iam.iga.api.ReconcileAccount target = new com.soffid.iam.iga.api.ReconcileAccount();
		this.toReconcileAccount(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileAccount} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ReconcileAccount> toReconcileAccountList (java.util.Collection<com.soffid.iam.iga.model.ReconcileAccountEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.ReconcileAccount> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.ReconcileAccount>();
			for (final com.soffid.iam.iga.model.ReconcileAccountEntity instance: instances)
			{
				list.add( toReconcileAccount(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ReconcileAccount} object 
	 */
	public void reconcileAccountToEntity (com.soffid.iam.iga.api.ReconcileAccount source, com.soffid.iam.iga.model.ReconcileAccountEntity target, boolean copyIfNull) {
		// Attributes for ReconcileAccountEntity
		if (copyIfNull || source.getAccountName() != null)
		{
			target.setAccountName(source.getAccountName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getProcessId() != null)
		{
			target.setProcessId(source.getProcessId());
		}
		if (copyIfNull || source.getNewAccount() != null)
		{
			target.setNewAccount(source.getNewAccount());
		}
		if (copyIfNull || source.getDeletedAccount() != null)
		{
			target.setDeletedAccount(source.getDeletedAccount());
		}
		if (copyIfNull || source.getProposedAction() != null)
		{
			target.setProposedAction(source.getProposedAction());
		}
		if (copyIfNull || source.getDispatcher() != null)
		{
			target.setDispatcher(source.getDispatcher());
		}
		if (copyIfNull || source.getUserCode() != null)
		{
			target.setUserCode(source.getUserCode());
		}
		if (copyIfNull || source.getPrimaryGroup() != null)
		{
			target.setPrimaryGroup(source.getPrimaryGroup());
		}
		if (copyIfNull || source.getUserType() != null)
		{
			target.setUserType(source.getUserType());
		}
		if (copyIfNull || source.getUserFullName() != null)
		{
			target.setUserFullName(source.getUserFullName());
		}
		if (copyIfNull || source.getAccountType() != null)
		{
			target.setAccountType(source.getAccountType());
		}
		target.setActive(source.isActive());
		// Missing attribute tenant on entity
		if (copyIfNull || source.getAttributes() != null)
		{
			// Incompatible types source.attributes and target.attributes
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileAccount} object 
	 */
	public com.soffid.iam.iga.model.ReconcileAccountEntity reconcileAccountToEntity (com.soffid.iam.iga.api.ReconcileAccount instance) {
		com.soffid.iam.iga.model.ReconcileAccountEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newReconcileAccountEntity();
		reconcileAccountToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileAccount} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity>  reconcileAccountToEntityList (java.util.Collection<com.soffid.iam.iga.api.ReconcileAccount> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.ReconcileAccountEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.ReconcileAccountEntity>();
		for (com.soffid.iam.iga.api.ReconcileAccount instance: instances)
		{
			list.add (reconcileAccountToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} .
	 */
	public com.soffid.iam.iga.model.ReconcileAccountEntity newReconcileAccountEntity()
	{
		return new com.soffid.iam.iga.model.ReconcileAccountEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ReconcileAccountEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.ReconcileAccountEntity result = (com.soffid.iam.iga.model.ReconcileAccountEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.ReconcileAccountEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.ReconcileAccountEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ReconcileAccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAccountEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ReconcileAccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAccountEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ReconcileAccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAccountEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAccountEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileAccountEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAccountEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileAccountEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileAccountEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileAccountEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ReconcileAccountEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.ReconcileAccountEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
