//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity RoleDependencyEntity
 */
public abstract class RoleDependencyEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.RoleDependencyEntityDao
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
	 * Operation findByContainer
	 * @param containerRole
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findByContainer(
	    com.soffid.iam.iga.model.RoleEntity containerRole)
	
	{
		return findByContainer((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, containerRole);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findByContainer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.RoleEntity containerRole)
	
	{
		return findByContainer("select rolAssociacioRol\nfrom com.soffid.iam.iga.model.RoleDependencyEntity rolAssociacioRol\nwhere \nrolAssociacioRol.container = :containerRole\n",
			criteria, containerRole);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findByContainer(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.RoleEntity containerRole)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("containerRole", containerRole);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findContainerByRoleNameAndApplicationCodeAndDBCode
	 * @param roleName
	 * @param informationSystem
	 * @param systemName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findContainerByRoleNameAndApplicationCodeAndDBCode(
	    java.lang.String roleName, 
	    java.lang.String informationSystem, 
	    java.lang.String systemName)
	
	{
		return findContainerByRoleNameAndApplicationCodeAndDBCode((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, roleName, informationSystem, systemName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findContainerByRoleNameAndApplicationCodeAndDBCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String informationSystem, java.lang.String systemName)
	
	{
		return findContainerByRoleNameAndApplicationCodeAndDBCode("select rolAssociacioRol.container\nfrom com.soffid.iam.iga.model.RoleDependencyEntity rolAssociacioRol\nwhere \n rolAssociacioRol.contained.name = :roleName and\n rolAssociacioRol.contained.informationSystem.name = :informationSystem and\n rolAssociacioRol.contained.system.name = :systemName and rolAssociacioRol.contained.system.tenant.id = :tenantId\n",
			criteria, roleName, informationSystem, systemName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findContainerByRoleNameAndApplicationCodeAndDBCode(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String informationSystem, java.lang.String systemName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("roleName", roleName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("informationSystem", informationSystem, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("systemName", systemName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findRolesAssociationRole
	 * @param containedRole
	 * @param containerRole
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findRolesAssociationRole(
	    com.soffid.iam.iga.model.RoleEntity containedRole, 
	    com.soffid.iam.iga.model.RoleEntity containerRole)
	
	{
		return findRolesAssociationRole((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, containedRole, containerRole);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findRolesAssociationRole(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.RoleEntity containedRole, com.soffid.iam.iga.model.RoleEntity containerRole)
	
	{
		return findRolesAssociationRole("select rolAssociacioRol\nfrom com.soffid.iam.iga.model.RoleDependencyEntity rolAssociacioRol\nwhere \n   rolAssociacioRol.contained = :containedRole and    rolAssociacioRol.container = :containerRole\n",
			criteria, containedRole, containerRole);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findRolesAssociationRole(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.RoleEntity containedRole, com.soffid.iam.iga.model.RoleEntity containerRole)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("containedRole", containedRole);
			queryObject.setParameter("containerRole", containerRole);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findRolesAssociationContainerRole
	 * @param containedRole
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findRolesAssociationContainerRole(
	    com.soffid.iam.iga.model.RoleEntity containedRole)
	
	{
		return findRolesAssociationContainerRole((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, containedRole);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findRolesAssociationContainerRole(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.RoleEntity containedRole)
	
	{
		return findRolesAssociationContainerRole("select rolAssociacioRol\nfrom com.soffid.iam.iga.model.RoleDependencyEntity rolAssociacioRol\nwhere rolAssociacioRol.contained = :containedRole\n",
			criteria, containedRole);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findRolesAssociationContainerRole(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.RoleEntity containedRole)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("containedRole", containedRole);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.iga.model.RoleDependencyEntity#	 * @see com.soffid.iam.iga.model.RoleDependencyEntity#void assignDomainValue(com.soffid.iam.iga.model.RoleDependencyEntity entity, com.soffid.iam.iga.api.RoleGrant valueObject, com.soffid.iam.iga.model.RoleEntity grantedRole, com.soffid.iam.iga.model.RoleEntity granteeRole)
	 */
	public void assignDomainValue(
		com.soffid.iam.iga.model.RoleDependencyEntity entity, 
		com.soffid.iam.iga.api.RoleGrant valueObject, 
		com.soffid.iam.iga.model.RoleEntity grantedRole, 
		com.soffid.iam.iga.model.RoleEntity granteeRole)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (entity == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleDependencyEntity.assignDomainValue(com.soffid.iam.iga.model.RoleDependencyEntity entity, com.soffid.iam.iga.api.RoleGrant valueObject, com.soffid.iam.iga.model.RoleEntity grantedRole, com.soffid.iam.iga.model.RoleEntity granteeRole) - entity cannot be null");
		}
		try
		{
			handleAssignDomainValue(entity, valueObject, grantedRole, granteeRole);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.model.RoleDependencyEntity.class).
				warn ("Error on RoleDependencyEntity.assignDomainValue", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on RoleDependencyEntity.assignDomainValue: "+th.toString(), th);
		}
	}

	protected abstract void handleAssignDomainValue(com.soffid.iam.iga.model.RoleDependencyEntity entity, com.soffid.iam.iga.api.RoleGrant valueObject, com.soffid.iam.iga.model.RoleEntity grantedRole, com.soffid.iam.iga.model.RoleEntity granteeRole) throws Exception;

	/**
	 * @see com.soffid.iam.iga.model.RoleDependencyEntity#	 * @see com.soffid.iam.iga.model.RoleDependencyEntity#void assignGranteeDomainValue(com.soffid.iam.iga.model.RoleDependencyEntity entity, com.soffid.iam.iga.api.RoleGrant valueObject, com.soffid.iam.iga.model.RoleEntity grantedRole, com.soffid.iam.iga.model.RoleEntity granteeRole)
	 */
	public void assignGranteeDomainValue(
		com.soffid.iam.iga.model.RoleDependencyEntity entity, 
		com.soffid.iam.iga.api.RoleGrant valueObject, 
		com.soffid.iam.iga.model.RoleEntity grantedRole, 
		com.soffid.iam.iga.model.RoleEntity granteeRole)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (entity == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleDependencyEntity.assignGranteeDomainValue(com.soffid.iam.iga.model.RoleDependencyEntity entity, com.soffid.iam.iga.api.RoleGrant valueObject, com.soffid.iam.iga.model.RoleEntity grantedRole, com.soffid.iam.iga.model.RoleEntity granteeRole) - entity cannot be null");
		}
		try
		{
			handleAssignGranteeDomainValue(entity, valueObject, grantedRole, granteeRole);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.model.RoleDependencyEntity.class).
				warn ("Error on RoleDependencyEntity.assignGranteeDomainValue", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on RoleDependencyEntity.assignGranteeDomainValue: "+th.toString(), th);
		}
	}

	protected abstract void handleAssignGranteeDomainValue(com.soffid.iam.iga.model.RoleDependencyEntity entity, com.soffid.iam.iga.api.RoleGrant valueObject, com.soffid.iam.iga.model.RoleEntity grantedRole, com.soffid.iam.iga.model.RoleEntity granteeRole) throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public void toRoleGrant(com.soffid.iam.iga.model.RoleDependencyEntity source, com.soffid.iam.iga.api.RoleGrant target) {
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
		target.setStatus(source.getStatus());
		target.setMandatory(source.getMandatory());
		// Missing attribute attributes on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public com.soffid.iam.iga.api.RoleGrant toRoleGrant(com.soffid.iam.iga.model.RoleDependencyEntity entity) {
		final com.soffid.iam.iga.api.RoleGrant target = new com.soffid.iam.iga.api.RoleGrant();
		this.toRoleGrant(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleGrant} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.RoleGrant> toRoleGrantList (java.util.Collection<com.soffid.iam.iga.model.RoleDependencyEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.RoleGrant> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.RoleGrant>();
			for (final com.soffid.iam.iga.model.RoleDependencyEntity instance: instances)
			{
				list.add( toRoleGrant(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public void roleGrantToEntity (com.soffid.iam.iga.api.RoleGrant source, com.soffid.iam.iga.model.RoleDependencyEntity target, boolean copyIfNull) {
		// Attributes for RoleDependencyEntity
		// Missing attribute contained on entity
		// Missing attribute container on entity
		// Missing attribute granteeApplicationDomain on entity
		// Missing attribute granteeGroupDomain on entity
		// Missing attribute granteeDomainValue on entity
		// Missing attribute domainApplication on entity
		// Missing attribute domainGroup on entity
		// Missing attribute domainApplicationValue on entity
		if (copyIfNull || source.getStatus() != null)
		{
			target.setStatus(source.getStatus());
		}
		if (copyIfNull || source.getMandatory() != null)
		{
			target.setMandatory(source.getMandatory());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public com.soffid.iam.iga.model.RoleDependencyEntity roleGrantToEntity (com.soffid.iam.iga.api.RoleGrant instance) {
		com.soffid.iam.iga.model.RoleDependencyEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newRoleDependencyEntity();
		roleGrantToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleGrant} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity>  roleGrantToEntityList (java.util.Collection<com.soffid.iam.iga.api.RoleGrant> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.RoleDependencyEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.RoleDependencyEntity>();
		for (com.soffid.iam.iga.api.RoleGrant instance: instances)
		{
			list.add (roleGrantToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RoleDependencyEntity} .
	 */
	public com.soffid.iam.iga.model.RoleDependencyEntity newRoleDependencyEntity()
	{
		return new com.soffid.iam.iga.model.RoleDependencyEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RoleDependencyEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.RoleDependencyEntity result = (com.soffid.iam.iga.model.RoleDependencyEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.RoleDependencyEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> result = (java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.RoleDependencyEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RoleDependencyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RoleDependencyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleDependencyEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RoleDependencyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RoleDependencyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleDependencyEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RoleDependencyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleDependencyEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RoleDependencyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RoleDependencyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleDependencyEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleDependencyEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RoleDependencyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RoleDependencyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleDependencyEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleDependencyEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RoleDependencyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleDependencyEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleDependencyEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"RoleDependencyEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.RoleDependencyEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
