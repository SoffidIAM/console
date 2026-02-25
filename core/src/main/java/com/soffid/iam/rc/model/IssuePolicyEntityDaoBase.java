//
// (c) 2014 Soffid
//
//
package com.soffid.iam.rc.model;
/**
 * DAO Base for Entity IssuePolicyEntity
 */
public abstract class IssuePolicyEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.rc.model.IssuePolicyEntityDao
{
	com.soffid.iam.rc.model.IssuePolicyActionEntityDao issuePolicyActionEntityDao;

	/**
	 * Sets reference to <code>issuePolicyActionEntityDao</code>.
	 */
	public void setIssuePolicyActionEntityDao (com.soffid.iam.rc.model.IssuePolicyActionEntityDao issuePolicyActionEntityDao) {
		this.issuePolicyActionEntityDao = issuePolicyActionEntityDao;
	}

	/**
	 * Gets reference to <code>issuePolicyActionEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssuePolicyActionEntityDao getIssuePolicyActionEntityDao () {
		return issuePolicyActionEntityDao;
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
	 * Operation findByType
	 * @param type
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.rc.model.IssuePolicyEntity> findByType(
	    java.lang.String type)
	
	{
		return findByType((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, type);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssuePolicyEntity> findByType(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type)
	
	{
		return findByType("from com.soffid.iam.rc.model.IssuePolicyEntity where tenant.id=:tenantId and type=:type",
			criteria, type);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssuePolicyEntity> findByType(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("type", type, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.rc.model.IssuePolicyEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.IssuePolicy} object 
	 */
	public void toIssuePolicy(com.soffid.iam.rc.model.IssuePolicyEntity source, com.soffid.iam.rc.api.IssuePolicy target) {
		// Attributes for IssuePolicy
		target.setId(source.getId());
		target.setType(source.getType());
		target.setDescription(source.getDescription());
		target.setActor(source.getActor());
		target.setStatus(source.getStatus());
		// Incompatible types source.actions and target.actions
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssuePolicy} object 
	 */
	public com.soffid.iam.rc.api.IssuePolicy toIssuePolicy(com.soffid.iam.rc.model.IssuePolicyEntity entity) {
		final com.soffid.iam.rc.api.IssuePolicy target = new com.soffid.iam.rc.api.IssuePolicy();
		this.toIssuePolicy(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssuePolicy} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.IssuePolicy> toIssuePolicyList (java.util.Collection<com.soffid.iam.rc.model.IssuePolicyEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.rc.api.IssuePolicy> list =
				new java.util.LinkedList<com.soffid.iam.rc.api.IssuePolicy>();
			for (final com.soffid.iam.rc.model.IssuePolicyEntity instance: instances)
			{
				list.add( toIssuePolicy(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.IssuePolicy} object 
	 */
	public void issuePolicyToEntity (com.soffid.iam.rc.api.IssuePolicy source, com.soffid.iam.rc.model.IssuePolicyEntity target, boolean copyIfNull) {
		// Attributes for IssuePolicyEntity
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		if (copyIfNull || source.getStatus() != null)
		{
			target.setStatus(source.getStatus());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getActor() != null)
		{
			target.setActor(source.getActor());
		}
		// Missing attribute tenant on entity
		if (copyIfNull || source.getActions() != null)
		{
			// Incompatible types source.actions and target.actions
		}
		if (copyIfNull || source.getCreatedOn() != null)
		{
			target.setCreatedOn(source.getCreatedOn());
		}
		if (copyIfNull || source.getCreatedBy() != null)
		{
			target.setCreatedBy(source.getCreatedBy());
		}
		if (copyIfNull || source.getUpdatedOn() != null)
		{
			target.setUpdatedOn(source.getUpdatedOn());
		}
		if (copyIfNull || source.getUpdatedBy() != null)
		{
			target.setUpdatedBy(source.getUpdatedBy());
		}
		if (copyIfNull || source.getDeletedOn() != null)
		{
			target.setDeletedOn(source.getDeletedOn());
		}
		if (copyIfNull || source.getDeletedBy() != null)
		{
			target.setDeletedBy(source.getDeletedBy());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssuePolicy} object 
	 */
	public com.soffid.iam.rc.model.IssuePolicyEntity issuePolicyToEntity (com.soffid.iam.rc.api.IssuePolicy instance) {
		com.soffid.iam.rc.model.IssuePolicyEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newIssuePolicyEntity();
		issuePolicyToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssuePolicy} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyEntity>  issuePolicyToEntityList (java.util.Collection<com.soffid.iam.rc.api.IssuePolicy> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.rc.model.IssuePolicyEntity> list =
			new java.util.LinkedList<com.soffid.iam.rc.model.IssuePolicyEntity>();
		for (com.soffid.iam.rc.api.IssuePolicy instance: instances)
		{
			list.add (issuePolicyToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.IssuePolicyEntity} .
	 */
	public com.soffid.iam.rc.model.IssuePolicyEntity newIssuePolicyEntity()
	{
		return new com.soffid.iam.rc.model.IssuePolicyEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.IssuePolicyEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.rc.model.IssuePolicyEntity result = (com.soffid.iam.rc.model.IssuePolicyEntity) this.getHibernateTemplate().get(com.soffid.iam.rc.model.IssuePolicyEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.rc.model.IssuePolicyEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.rc.model.IssuePolicyEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.IssuePolicyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.IssuePolicyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssuePolicyEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.IssuePolicyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.IssuePolicyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssuePolicyEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.IssuePolicyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssuePolicyEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.IssuePolicyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.IssuePolicyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssuePolicyEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssuePolicyEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.IssuePolicyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.IssuePolicyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssuePolicyEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssuePolicyEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.IssuePolicyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssuePolicyEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssuePolicyEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"IssuePolicyEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.rc.model.IssuePolicyEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.rc.model.IssuePolicyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.rc.model.IssuePolicyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
