//
// (c) 2014 Soffid
//
//
package com.soffid.iam.rc.model;
/**
 * DAO Base for Entity SoDRuleEntity
 */
public abstract class SoDRuleEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.rc.model.SoDRuleEntityDao
{
	com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao;

	/**
	 * Sets reference to <code>informationSystemEntityDao</code>.
	 */
	public void setInformationSystemEntityDao (com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao) {
		this.informationSystemEntityDao = informationSystemEntityDao;
	}

	/**
	 * Gets reference to <code>informationSystemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.InformationSystemEntityDao getInformationSystemEntityDao () {
		return informationSystemEntityDao;
	}

	com.soffid.iam.rc.model.SoDRoleEntityDao soDRoleEntityDao;

	/**
	 * Sets reference to <code>soDRoleEntityDao</code>.
	 */
	public void setSoDRoleEntityDao (com.soffid.iam.rc.model.SoDRoleEntityDao soDRoleEntityDao) {
		this.soDRoleEntityDao = soDRoleEntityDao;
	}

	/**
	 * Gets reference to <code>soDRoleEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.SoDRoleEntityDao getSoDRoleEntityDao () {
		return soDRoleEntityDao;
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
	 *  Copy data to {@link com.soffid.iam.rc.api.SoDRule} object 
	 */
	public void toSoDRule(com.soffid.iam.rc.model.SoDRuleEntity source, com.soffid.iam.rc.api.SoDRule target) {
		// Attributes for SoDRule
		target.setId(source.getId());
		target.setName(source.getName());
		target.setNumber(source.getNumber());
		target.setRisk(source.getRisk());
		target.setType(source.getType());
		// Incompatible types source.application and target.application
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.SoDRule} object 
	 */
	public com.soffid.iam.rc.api.SoDRule toSoDRule(com.soffid.iam.rc.model.SoDRuleEntity entity) {
		final com.soffid.iam.rc.api.SoDRule target = new com.soffid.iam.rc.api.SoDRule();
		this.toSoDRule(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.SoDRule} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.SoDRule> toSoDRuleList (java.util.Collection<com.soffid.iam.rc.model.SoDRuleEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.rc.api.SoDRule> list =
				new java.util.LinkedList<com.soffid.iam.rc.api.SoDRule>();
			for (final com.soffid.iam.rc.model.SoDRuleEntity instance: instances)
			{
				list.add( toSoDRule(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.SoDRule} object 
	 */
	public void soDRuleToEntity (com.soffid.iam.rc.api.SoDRule source, com.soffid.iam.rc.model.SoDRuleEntity target, boolean copyIfNull) {
		// Attributes for SoDRuleEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getRisk() != null)
		{
			target.setRisk(source.getRisk());
		}
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		if (copyIfNull || source.getNumber() != null)
		{
			target.setNumber(source.getNumber());
		}
		if (copyIfNull || source.getApplication() != null)
		{
			// Incompatible types source.application and target.application
		}
		// Missing attribute tenant on entity
		// Missing attribute roles on entity
		// Missing attribute matrixCells on entity
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
	 *  Transforms from {@link com.soffid.iam.rc.api.SoDRule} object 
	 */
	public com.soffid.iam.rc.model.SoDRuleEntity soDRuleToEntity (com.soffid.iam.rc.api.SoDRule instance) {
		com.soffid.iam.rc.model.SoDRuleEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newSoDRuleEntity();
		soDRuleToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.SoDRule} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleEntity>  soDRuleToEntityList (java.util.Collection<com.soffid.iam.rc.api.SoDRule> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.rc.model.SoDRuleEntity> list =
			new java.util.LinkedList<com.soffid.iam.rc.model.SoDRuleEntity>();
		for (com.soffid.iam.rc.api.SoDRule instance: instances)
		{
			list.add (soDRuleToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.SoDRuleEntity} .
	 */
	public com.soffid.iam.rc.model.SoDRuleEntity newSoDRuleEntity()
	{
		return new com.soffid.iam.rc.model.SoDRuleEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.SoDRuleEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.rc.model.SoDRuleEntity result = (com.soffid.iam.rc.model.SoDRuleEntity) this.getHibernateTemplate().get(com.soffid.iam.rc.model.SoDRuleEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.rc.model.SoDRuleEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.rc.model.SoDRuleEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.SoDRuleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.SoDRuleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SoDRuleEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.rc.model.SoDRuleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.SoDRuleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SoDRuleEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.SoDRuleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SoDRuleEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.SoDRuleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRuleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SoDRuleEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.SoDRuleEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.SoDRuleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRuleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SoDRuleEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.SoDRuleEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRuleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SoDRuleEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.SoDRuleEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"SoDRuleEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.rc.model.SoDRuleEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.rc.model.SoDRuleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.rc.model.SoDRuleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
