//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity AccessControlEntity
 * Access control rules for Oracle agent
 */
public abstract class AccessControlEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.AccessControlEntityDao
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

	com.soffid.iam.am.model.HostEntityDao hostEntityDao;

	/**
	 * Sets reference to <code>hostEntityDao</code>.
	 */
	public void setHostEntityDao (com.soffid.iam.am.model.HostEntityDao hostEntityDao) {
		this.hostEntityDao = hostEntityDao;
	}

	/**
	 * Gets reference to <code>hostEntityDao</code>.
	 */
	public com.soffid.iam.am.model.HostEntityDao getHostEntityDao () {
		return hostEntityDao;
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
	 * Operation findByAgentCode
	 * @param systemName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.AccessControlEntity> findByAgentCode(
	    java.lang.String systemName)
	
	{
		return findByAgentCode((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, systemName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AccessControlEntity> findByAgentCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	
	{
		return findByAgentCode("select cac from com.soffid.iam.iga.model.AccessControlEntity cac where cac.agent.name=:systemName and cac.agent.tenant.id = :tenantId",
			criteria, systemName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AccessControlEntity> findByAgentCode(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("systemName", systemName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.AccessControlEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.AccessControl} object 
	 */
	public void toAccessControl(com.soffid.iam.iga.model.AccessControlEntity source, com.soffid.iam.iga.api.AccessControl target) {
		// Attributes for AccessControl
		target.setId(source.getId());
		// Missing attribute agentId on entity
		// Missing attribute agentName on entity
		// Missing attribute roleDescription on entity
		// Missing attribute roleId on entity
		// Missing attribute hostName on entity
		// Missing attribute hostId on entity
		target.setProgram(source.getProgram());
		target.setGenericUser(source.getGenericUser());
		target.setGenericHost(source.getGenericHost());
		// Missing attribute remoteIp on entity
		target.setComments(source.getComments());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.AccessControl} object 
	 */
	public com.soffid.iam.iga.api.AccessControl toAccessControl(com.soffid.iam.iga.model.AccessControlEntity entity) {
		final com.soffid.iam.iga.api.AccessControl target = new com.soffid.iam.iga.api.AccessControl();
		this.toAccessControl(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.AccessControl} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.AccessControl> toAccessControlList (java.util.Collection<com.soffid.iam.iga.model.AccessControlEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.AccessControl> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.AccessControl>();
			for (final com.soffid.iam.iga.model.AccessControlEntity instance: instances)
			{
				list.add( toAccessControl(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.AccessControl} object 
	 */
	public void accessControlToEntity (com.soffid.iam.iga.api.AccessControl source, com.soffid.iam.iga.model.AccessControlEntity target, boolean copyIfNull) {
		// Attributes for AccessControlEntity
		if (copyIfNull || source.getGenericUser() != null)
		{
			target.setGenericUser(source.getGenericUser());
		}
		if (copyIfNull || source.getGenericHost() != null)
		{
			target.setGenericHost(source.getGenericHost());
		}
		if (copyIfNull || source.getProgram() != null)
		{
			target.setProgram(source.getProgram());
		}
		// Missing attribute role on entity
		// Missing attribute agent on entity
		// Missing attribute propagatedIPs on entity
		if (copyIfNull || source.getComments() != null)
		{
			target.setComments(source.getComments());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.AccessControl} object 
	 */
	public com.soffid.iam.iga.model.AccessControlEntity accessControlToEntity (com.soffid.iam.iga.api.AccessControl instance) {
		com.soffid.iam.iga.model.AccessControlEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newAccessControlEntity();
		accessControlToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.AccessControl} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.AccessControlEntity>  accessControlToEntityList (java.util.Collection<com.soffid.iam.iga.api.AccessControl> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.AccessControlEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.AccessControlEntity>();
		for (com.soffid.iam.iga.api.AccessControl instance: instances)
		{
			list.add (accessControlToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.AccessControlEntity} .
	 */
	public com.soffid.iam.iga.model.AccessControlEntity newAccessControlEntity()
	{
		return new com.soffid.iam.iga.model.AccessControlEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.AccessControlEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.AccessControlEntity result = (com.soffid.iam.iga.model.AccessControlEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.AccessControlEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.AccessControlEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.AccessControlEntity> result = (java.util.List<com.soffid.iam.iga.model.AccessControlEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.AccessControlEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.AccessControlEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.AccessControlEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccessControlEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.AccessControlEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.AccessControlEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccessControlEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.AccessControlEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccessControlEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.AccessControlEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.AccessControlEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccessControlEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AccessControlEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.AccessControlEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.AccessControlEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccessControlEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AccessControlEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.AccessControlEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccessControlEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AccessControlEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AccessControlEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.AccessControlEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.AccessControlEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.AccessControlEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.AccessControlEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.AccessControlEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.AccessControlEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
