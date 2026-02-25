//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity NetworkAuthorizationEntity
 */
public abstract class NetworkAuthorizationEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.NetworkAuthorizationEntityDao
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

	com.soffid.iam.am.model.NetworkEntityDao networkEntityDao;

	/**
	 * Sets reference to <code>networkEntityDao</code>.
	 */
	public void setNetworkEntityDao (com.soffid.iam.am.model.NetworkEntityDao networkEntityDao) {
		this.networkEntityDao = networkEntityDao;
	}

	/**
	 * Gets reference to <code>networkEntityDao</code>.
	 */
	public com.soffid.iam.am.model.NetworkEntityDao getNetworkEntityDao () {
		return networkEntityDao;
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
	 * Operation findByNetworkAndIdentity
	 * @param networkName
	 * @param identity
	 * @return
	**/
	public com.soffid.iam.am.model.NetworkAuthorizationEntity findByNetworkAndIdentity(
	    java.lang.String networkName, 
	    java.lang.String identity)
	
	{
		return findByNetworkAndIdentity((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, networkName, identity);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntity findByNetworkAndIdentity(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String networkName, java.lang.String identity)
	
	{
		return findByNetworkAndIdentity("select xarxaAC \nfrom com.soffid.iam.am.model.NetworkAuthorizationEntity xarxaAC \nleft join xarxaAC.user user \nleft join xarxaAC.role rol \nleft join xarxaAC.group grup \nwhere \n(\n  (grup is not null and grup.name = :identity) or \n  (user is not null and user.userName = :identity) or \n  (rol is not null and rol.name = :identity) \n) \nand xarxaAC.network.name = :networkName and xarxaAC.network.tenant.id = :tenantId ",
			criteria, networkName, identity);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntity findByNetworkAndIdentity(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String networkName, java.lang.String identity)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("networkName", networkName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("identity", identity, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.NetworkAuthorizationEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.NetworkAuthorizationEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.NetworkAuthorizationEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByGroupName
	 * @param groupName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByGroupName(
	    java.lang.String groupName)
	
	{
		return findByGroupName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, groupName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByGroupName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	
	{
		return findByGroupName("select xarxaAC\nfrom com.soffid.iam.am.model.NetworkAuthorizationEntity xarxaAC\nwhere xarxaAC.group.name = :groupName and xarxaAC.group.tenant.id = :tenantId ",
			criteria, groupName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByGroupName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("groupName", groupName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByUserName(
	    java.lang.String userName)
	
	{
		return findByUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findByUserName("select xarxaAC\nfrom com.soffid.iam.am.model.NetworkAuthorizationEntity xarxaAC\nwhere xarxaAC.user.userName = :userName and xarxaAC.user.tenant.id = :tenantId ",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByRoleName
	 * @param roleName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByRoleName(
	    java.lang.String roleName)
	
	{
		return findByRoleName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, roleName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByRoleName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName)
	
	{
		return findByRoleName("select xarxaAC\nfrom com.soffid.iam.am.model.NetworkAuthorizationEntity xarxaAC\nwhere xarxaAC.role.name = :roleName and xarxaAC.role.system.tenant.id = :tenantId ",
			criteria, roleName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByRoleName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("roleName", roleName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByRole
	 * @param roleName
	 * @param informationSystem
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByRole(
	    java.lang.String roleName, 
	    java.lang.String informationSystem, 
	    java.lang.String system)
	
	{
		return findByRole((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, roleName, informationSystem, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByRole(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String informationSystem, java.lang.String system)
	
	{
		return findByRole("select xarxaAC\nfrom com.soffid.iam.am.model.NetworkAuthorizationEntity xarxaAC\nleft join xarxaAC.role as elrol\nleft join elrol.informationSystem as aplica\nleft join elrol.system as agent\nwhere elrol.name = :roleName \nand aplica.name = :informationSystem\nand agent.name = :system and agent.tenant.id = :tenantId \norder by xarxaAC.network.name",
			criteria, roleName, informationSystem, system);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByRole(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String informationSystem, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("roleName", roleName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("informationSystem", informationSystem, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByNetwork
	 * @param network
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByNetwork(
	    com.soffid.iam.am.model.NetworkEntity network)
	
	{
		return findByNetwork((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, network);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByNetwork(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.am.model.NetworkEntity network)
	
	{
		return findByNetwork("from com.soffid.iam.am.model.NetworkAuthorizationEntity where network=:network",
			criteria, network);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByNetwork(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.am.model.NetworkEntity network)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("network", network);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.NetworkAuthorization} object 
	 */
	public void toNetworkAuthorization(com.soffid.iam.am.model.NetworkAuthorizationEntity source, com.soffid.iam.am.api.NetworkAuthorization target) {
		// Attributes for NetworkAuthorization
		// Missing attribute identity on entity
		target.setLevel(source.getLevel());
		// Missing attribute mask on entity
		// Missing attribute networkCode on entity
		target.setId(source.getId());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.NetworkAuthorization} object 
	 */
	public com.soffid.iam.am.api.NetworkAuthorization toNetworkAuthorization(com.soffid.iam.am.model.NetworkAuthorizationEntity entity) {
		final com.soffid.iam.am.api.NetworkAuthorization target = new com.soffid.iam.am.api.NetworkAuthorization();
		this.toNetworkAuthorization(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.NetworkAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.api.NetworkAuthorization> toNetworkAuthorizationList (java.util.Collection<com.soffid.iam.am.model.NetworkAuthorizationEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.NetworkAuthorization> list =
				new java.util.LinkedList<com.soffid.iam.am.api.NetworkAuthorization>();
			for (final com.soffid.iam.am.model.NetworkAuthorizationEntity instance: instances)
			{
				list.add( toNetworkAuthorization(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.NetworkAuthorization} object 
	 */
	public void networkAuthorizationToEntity (com.soffid.iam.am.api.NetworkAuthorization source, com.soffid.iam.am.model.NetworkAuthorizationEntity target, boolean copyIfNull) {
		// Attributes for NetworkAuthorizationEntity
		if (copyIfNull || source.getLevel() != null)
		{
			target.setLevel(source.getLevel());
		}
		// Missing attribute hostsName on entity
		// Missing attribute network on entity
		// Missing attribute role on entity
		// Missing attribute group on entity
		// Missing attribute user on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.NetworkAuthorization} object 
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntity networkAuthorizationToEntity (com.soffid.iam.am.api.NetworkAuthorization instance) {
		com.soffid.iam.am.model.NetworkAuthorizationEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newNetworkAuthorizationEntity();
		networkAuthorizationToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.NetworkAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity>  networkAuthorizationToEntityList (java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.NetworkAuthorizationEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.NetworkAuthorizationEntity>();
		for (com.soffid.iam.am.api.NetworkAuthorization instance: instances)
		{
			list.add (networkAuthorizationToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} .
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntity newNetworkAuthorizationEntity()
	{
		return new com.soffid.iam.am.model.NetworkAuthorizationEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.NetworkAuthorizationEntity result = (com.soffid.iam.am.model.NetworkAuthorizationEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.NetworkAuthorizationEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> loadAll() {
		java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> result = (java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.am.model.NetworkAuthorizationEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.NetworkAuthorizationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NetworkAuthorizationEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.NetworkAuthorizationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NetworkAuthorizationEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.NetworkAuthorizationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NetworkAuthorizationEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.NetworkAuthorizationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NetworkAuthorizationEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.NetworkAuthorizationEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.NetworkAuthorizationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NetworkAuthorizationEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.NetworkAuthorizationEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.NetworkAuthorizationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NetworkAuthorizationEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.NetworkAuthorizationEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"NetworkAuthorizationEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.NetworkAuthorizationEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
