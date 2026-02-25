//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity RoleGroupEntity
 */
public abstract class RoleGroupEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.RoleGroupEntityDao
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

	com.soffid.iam.iga.model.DomainValueEntityDao domainValueEntityDao;

	/**
	 * Sets reference to <code>domainValueEntityDao</code>.
	 */
	public void setDomainValueEntityDao (com.soffid.iam.iga.model.DomainValueEntityDao domainValueEntityDao) {
		this.domainValueEntityDao = domainValueEntityDao;
	}

	/**
	 * Gets reference to <code>domainValueEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.DomainValueEntityDao getDomainValueEntityDao () {
		return domainValueEntityDao;
	}

	com.soffid.iam.iga.model.GroupEntityDao groupEntityDao;

	/**
	 * Sets reference to <code>groupEntityDao</code>.
	 */
	public void setGroupEntityDao (com.soffid.iam.iga.model.GroupEntityDao groupEntityDao) {
		this.groupEntityDao = groupEntityDao;
	}

	/**
	 * Gets reference to <code>groupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupEntityDao getGroupEntityDao () {
		return groupEntityDao;
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

	com.soffid.iam.sync.model.TaskEntityDao taskEntityDao;

	/**
	 * Sets reference to <code>taskEntityDao</code>.
	 */
	public void setTaskEntityDao (com.soffid.iam.sync.model.TaskEntityDao taskEntityDao) {
		this.taskEntityDao = taskEntityDao;
	}

	/**
	 * Gets reference to <code>taskEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.TaskEntityDao getTaskEntityDao () {
		return taskEntityDao;
	}


	/**
	 * Operation findOwnerGroupsByRole
	 * @param rolOtorgat
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> findOwnerGroupsByRole(
	    com.soffid.iam.iga.model.RoleEntity rolOtorgat)
	
	{
		return findOwnerGroupsByRole((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, rolOtorgat);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> findOwnerGroupsByRole(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.RoleEntity rolOtorgat)
	
	{
		return findOwnerGroupsByRole("select rolsgrup from com.soffid.iam.iga.model.RoleGroupEntity rolsgrup where rolsgrup.grantedRole = :rolOtorgat",
			criteria, rolOtorgat);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> findOwnerGroupsByRole(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.RoleEntity rolOtorgat)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("rolOtorgat", rolOtorgat);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleGroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAssignedRolesByGroup
	 * @param grup
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> findAssignedRolesByGroup(
	    com.soffid.iam.iga.model.GroupEntity grup)
	
	{
		return findAssignedRolesByGroup((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, grup);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> findAssignedRolesByGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.GroupEntity grup)
	
	{
		return findAssignedRolesByGroup("select rolsgrup from com.soffid.iam.iga.model.RoleGroupEntity rolsgrup where rolsgrup.group = :grup",
			criteria, grup);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> findAssignedRolesByGroup(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.GroupEntity grup)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("grup", grup);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleGroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.GroupRoles} object 
	 */
	public void toGroupRoles(com.soffid.iam.iga.model.RoleGroupEntity source, com.soffid.iam.iga.api.GroupRoles target) {
		// Attributes for GroupRoles
		target.setId(source.getId());
		// Missing attribute roleName on entity
		// Missing attribute roleDescription on entity
		// Missing attribute roleDatabases on entity
		// Missing attribute applicationCode on entity
		// Missing attribute groupCode on entity
		// Missing attribute groupDescription on entity
		// Missing attribute domainValue on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.GroupRoles} object 
	 */
	public com.soffid.iam.iga.api.GroupRoles toGroupRoles(com.soffid.iam.iga.model.RoleGroupEntity entity) {
		final com.soffid.iam.iga.api.GroupRoles target = new com.soffid.iam.iga.api.GroupRoles();
		this.toGroupRoles(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.GroupRoles} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.GroupRoles> toGroupRolesList (java.util.Collection<com.soffid.iam.iga.model.RoleGroupEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.GroupRoles> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.GroupRoles>();
			for (final com.soffid.iam.iga.model.RoleGroupEntity instance: instances)
			{
				list.add( toGroupRoles(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.GroupRoles} object 
	 */
	public void groupRolesToEntity (com.soffid.iam.iga.api.GroupRoles source, com.soffid.iam.iga.model.RoleGroupEntity target, boolean copyIfNull) {
		// Attributes for RoleGroupEntity
		// Missing attribute group on entity
		// Missing attribute grantedRole on entity
		// Missing attribute grantedApplicationDomain on entity
		// Missing attribute grantedGroupDomain on entity
		// Missing attribute grantedDomainValue on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.GroupRoles} object 
	 */
	public com.soffid.iam.iga.model.RoleGroupEntity groupRolesToEntity (com.soffid.iam.iga.api.GroupRoles instance) {
		com.soffid.iam.iga.model.RoleGroupEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newRoleGroupEntity();
		groupRolesToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.GroupRoles} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity>  groupRolesToEntityList (java.util.Collection<com.soffid.iam.iga.api.GroupRoles> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.RoleGroupEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.RoleGroupEntity>();
		for (com.soffid.iam.iga.api.GroupRoles instance: instances)
		{
			list.add (groupRolesToEntity(instance));
		}
		return list;
	}

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public void toRoleGrant(com.soffid.iam.iga.model.RoleGroupEntity source, com.soffid.iam.iga.api.RoleGrant target) {
		// Attributes for RoleGrant
		target.setId(source.getId());
		// Missing attribute roleId on entity
		// Missing attribute roleName on entity
		// Missing attribute roleDescription on entity
		// Missing attribute system on entity
		// Missing attribute informationSystem on entity
		// Missing attribute hasDomain on entity
		// Missing attribute domainValue on entity
		// Missing attribute domainDescription on entity
		// Missing attribute ownerAccountName on entity
		// Missing attribute ownerInformationSystem on entity
		// Missing attribute ownerSystem on entity
		// Missing attribute ownerGroup on entity
		// Missing attribute ownerRole on entity
		// Missing attribute ownerRolDomainValue on entity
		// Missing attribute ownerRoleName on entity
		// Missing attribute ownerRoleDescription on entity
		// Missing attribute user on entity
		// Missing attribute startDate on entity
		// Missing attribute endDate on entity
		// Missing attribute enabled on entity
		// Missing attribute holderGroup on entity
		// Missing attribute status on entity
		// Missing attribute mandatory on entity
		// Missing attribute attributes on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public com.soffid.iam.iga.api.RoleGrant toRoleGrant(com.soffid.iam.iga.model.RoleGroupEntity entity) {
		final com.soffid.iam.iga.api.RoleGrant target = new com.soffid.iam.iga.api.RoleGrant();
		this.toRoleGrant(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleGrant} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.RoleGrant> toRoleGrantList (java.util.Collection<com.soffid.iam.iga.model.RoleGroupEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.RoleGrant> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.RoleGrant>();
			for (final com.soffid.iam.iga.model.RoleGroupEntity instance: instances)
			{
				list.add( toRoleGrant(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public void roleGrantToEntity (com.soffid.iam.iga.api.RoleGrant source, com.soffid.iam.iga.model.RoleGroupEntity target, boolean copyIfNull) {
		// Attributes for RoleGroupEntity
		// Missing attribute group on entity
		// Missing attribute grantedRole on entity
		// Missing attribute grantedApplicationDomain on entity
		// Missing attribute grantedGroupDomain on entity
		// Missing attribute grantedDomainValue on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public com.soffid.iam.iga.model.RoleGroupEntity roleGrantToEntity (com.soffid.iam.iga.api.RoleGrant instance) {
		com.soffid.iam.iga.model.RoleGroupEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newRoleGroupEntity();
		roleGrantToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleGrant} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity>  roleGrantToEntityList (java.util.Collection<com.soffid.iam.iga.api.RoleGrant> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.RoleGroupEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.RoleGroupEntity>();
		for (com.soffid.iam.iga.api.RoleGrant instance: instances)
		{
			list.add (roleGrantToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RoleGroupEntity} .
	 */
	public com.soffid.iam.iga.model.RoleGroupEntity newRoleGroupEntity()
	{
		return new com.soffid.iam.iga.model.RoleGroupEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RoleGroupEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.RoleGroupEntity result = (com.soffid.iam.iga.model.RoleGroupEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.RoleGroupEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> result = (java.util.List<com.soffid.iam.iga.model.RoleGroupEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.RoleGroupEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RoleGroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RoleGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleGroupEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RoleGroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RoleGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleGroupEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RoleGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleGroupEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RoleGroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RoleGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleGroupEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleGroupEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RoleGroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RoleGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleGroupEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleGroupEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RoleGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleGroupEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleGroupEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"RoleGroupEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.RoleGroupEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.RoleGroupEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.RoleGroupEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
