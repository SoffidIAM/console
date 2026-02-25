//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity EntryPointEntity
 */
public abstract class EntryPointEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.EntryPointEntityDao
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

	com.soffid.iam.am.model.EntryPointAccountEntityDao entryPointAccountEntityDao;

	/**
	 * Sets reference to <code>entryPointAccountEntityDao</code>.
	 */
	public void setEntryPointAccountEntityDao (com.soffid.iam.am.model.EntryPointAccountEntityDao entryPointAccountEntityDao) {
		this.entryPointAccountEntityDao = entryPointAccountEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointAccountEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointAccountEntityDao getEntryPointAccountEntityDao () {
		return entryPointAccountEntityDao;
	}

	com.soffid.iam.am.model.EntryPointExecutableEntityDao entryPointExecutableEntityDao;

	/**
	 * Sets reference to <code>entryPointExecutableEntityDao</code>.
	 */
	public void setEntryPointExecutableEntityDao (com.soffid.iam.am.model.EntryPointExecutableEntityDao entryPointExecutableEntityDao) {
		this.entryPointExecutableEntityDao = entryPointExecutableEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointExecutableEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointExecutableEntityDao getEntryPointExecutableEntityDao () {
		return entryPointExecutableEntityDao;
	}

	com.soffid.iam.am.model.EntryPointGroupEntityDao entryPointGroupEntityDao;

	/**
	 * Sets reference to <code>entryPointGroupEntityDao</code>.
	 */
	public void setEntryPointGroupEntityDao (com.soffid.iam.am.model.EntryPointGroupEntityDao entryPointGroupEntityDao) {
		this.entryPointGroupEntityDao = entryPointGroupEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointGroupEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointGroupEntityDao getEntryPointGroupEntityDao () {
		return entryPointGroupEntityDao;
	}

	com.soffid.iam.am.model.EntryPointIconEntityDao entryPointIconEntityDao;

	/**
	 * Sets reference to <code>entryPointIconEntityDao</code>.
	 */
	public void setEntryPointIconEntityDao (com.soffid.iam.am.model.EntryPointIconEntityDao entryPointIconEntityDao) {
		this.entryPointIconEntityDao = entryPointIconEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointIconEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointIconEntityDao getEntryPointIconEntityDao () {
		return entryPointIconEntityDao;
	}

	com.soffid.iam.am.model.EntryPointRoleEntityDao entryPointRoleEntityDao;

	/**
	 * Sets reference to <code>entryPointRoleEntityDao</code>.
	 */
	public void setEntryPointRoleEntityDao (com.soffid.iam.am.model.EntryPointRoleEntityDao entryPointRoleEntityDao) {
		this.entryPointRoleEntityDao = entryPointRoleEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointRoleEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointRoleEntityDao getEntryPointRoleEntityDao () {
		return entryPointRoleEntityDao;
	}

	com.soffid.iam.am.model.EntryPointTreeEntityDao entryPointTreeEntityDao;

	/**
	 * Sets reference to <code>entryPointTreeEntityDao</code>.
	 */
	public void setEntryPointTreeEntityDao (com.soffid.iam.am.model.EntryPointTreeEntityDao entryPointTreeEntityDao) {
		this.entryPointTreeEntityDao = entryPointTreeEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointTreeEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointTreeEntityDao getEntryPointTreeEntityDao () {
		return entryPointTreeEntityDao;
	}

	com.soffid.iam.am.model.EntryPointUserEntityDao entryPointUserEntityDao;

	/**
	 * Sets reference to <code>entryPointUserEntityDao</code>.
	 */
	public void setEntryPointUserEntityDao (com.soffid.iam.am.model.EntryPointUserEntityDao entryPointUserEntityDao) {
		this.entryPointUserEntityDao = entryPointUserEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointUserEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointUserEntityDao getEntryPointUserEntityDao () {
		return entryPointUserEntityDao;
	}

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

	com.soffid.iam.am.model.HostEntryPointEntityDao hostEntryPointEntityDao;

	/**
	 * Sets reference to <code>hostEntryPointEntityDao</code>.
	 */
	public void setHostEntryPointEntityDao (com.soffid.iam.am.model.HostEntryPointEntityDao hostEntryPointEntityDao) {
		this.hostEntryPointEntityDao = hostEntryPointEntityDao;
	}

	/**
	 * Gets reference to <code>hostEntryPointEntityDao</code>.
	 */
	public com.soffid.iam.am.model.HostEntryPointEntityDao getHostEntryPointEntityDao () {
		return hostEntryPointEntityDao;
	}


	/**
	 * Operation findByCriteria
	 * @param name
	 * @param code
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity> findByCriteria(
	    java.lang.String name, 
	    java.lang.String code)
	
	{
		return findByCriteria((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, code);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity> findByCriteria(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String code)
	
	{
		return findByCriteria("select pue from com.soffid.iam.am.model.EntryPointEntity as pue where (:name is null or name like :name) and (:code is null or (pue.code is not null and pue.code like :code)) and pue.tenant.id = :tenantId order by pue.code, pue.name",
			criteria, name, code);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity> findByCriteria(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String code)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("code", code, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.EntryPointEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessTree} object 
	 */
	public void toAccessTree(com.soffid.iam.am.model.EntryPointEntity source, com.soffid.iam.am.api.AccessTree target) {
		// Attributes for AccessTree
		target.setId(source.getId());
		target.setCode(source.getCode());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		// Incompatible types source.visible and target.visible
		// Incompatible types source.menu and target.menu
		// Missing attribute columnsNumber on entity
		// Incompatible types source.publicAccess and target.publicAccess
		target.setMenuType(source.getMenuType());
		// Missing attribute authorizations on entity
		// Missing attribute executions on entity
		// Missing attribute icon1Image on entity
		// Missing attribute icon2Image on entity
		// Incompatible types source.informationSystem and target.informationSystem
		// Missing attribute parentId on entity
		// Missing attribute order on entity
		// Missing attribute icon1Id on entity
		// Missing attribute icon2Id on entity
		// Missing attribute xmlAccessTree on entity
		// Incompatible types source.system and target.system
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTree} object 
	 */
	public com.soffid.iam.am.api.AccessTree toAccessTree(com.soffid.iam.am.model.EntryPointEntity entity) {
		final com.soffid.iam.am.api.AccessTree target = new com.soffid.iam.am.api.AccessTree();
		this.toAccessTree(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTree} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessTree> toAccessTreeList (java.util.Collection<com.soffid.iam.am.model.EntryPointEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.AccessTree> list =
				new java.util.LinkedList<com.soffid.iam.am.api.AccessTree>();
			for (final com.soffid.iam.am.model.EntryPointEntity instance: instances)
			{
				list.add( toAccessTree(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessTree} object 
	 */
	public void accessTreeToEntity (com.soffid.iam.am.api.AccessTree source, com.soffid.iam.am.model.EntryPointEntity target, boolean copyIfNull) {
		// Attributes for EntryPointEntity
		if (copyIfNull || source.getCode() != null)
		{
			target.setCode(source.getCode());
		}
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		// Incompatible types source.visible and target.visible
		// Incompatible types source.menu and target.menu
		// Missing attribute numberOfColumns on entity
		// Incompatible types source.publicAccess and target.publicAccess
		if (copyIfNull || source.getMenuType() != null)
		{
			target.setMenuType(source.getMenuType());
		}
		if (copyIfNull || source.getSystem() != null)
		{
			// Incompatible types source.system and target.system
		}
		// Missing attribute authorizedRoles on entity
		// Missing attribute authorizedUsers on entity
		// Missing attribute authorizedGroups on entity
		// Missing attribute executionMethod on entity
		// Missing attribute authorizedAccounts on entity
		// Missing attribute icon1 on entity
		// Missing attribute icon2 on entity
		if (copyIfNull || source.getInformationSystem() != null)
		{
			// Incompatible types source.informationSystem and target.informationSystem
		}
		// Missing attribute parentEntryPointTree on entity
		// Missing attribute childrenEntryPointTree on entity
		// Missing attribute xmlEntryPoint on entity
		// Missing attribute tenant on entity
		// Missing attribute hosts on entity
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
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTree} object 
	 */
	public com.soffid.iam.am.model.EntryPointEntity accessTreeToEntity (com.soffid.iam.am.api.AccessTree instance) {
		com.soffid.iam.am.model.EntryPointEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newEntryPointEntity();
		accessTreeToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTree} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity>  accessTreeToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessTree> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.EntryPointEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.EntryPointEntity>();
		for (com.soffid.iam.am.api.AccessTree instance: instances)
		{
			list.add (accessTreeToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointEntity newEntryPointEntity()
	{
		return new com.soffid.iam.am.model.EntryPointEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.EntryPointEntity result = (com.soffid.iam.am.model.EntryPointEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.EntryPointEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.EntryPointEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.EntryPointEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"EntryPointEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.EntryPointEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.EntryPointEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.EntryPointEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
