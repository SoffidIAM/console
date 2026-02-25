//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity NetworkEntity
 */
public abstract class NetworkEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.NetworkEntityDao
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

	com.soffid.iam.base.model.ConfigEntityDao configEntityDao;

	/**
	 * Sets reference to <code>configEntityDao</code>.
	 */
	public void setConfigEntityDao (com.soffid.iam.base.model.ConfigEntityDao configEntityDao) {
		this.configEntityDao = configEntityDao;
	}

	/**
	 * Gets reference to <code>configEntityDao</code>.
	 */
	public com.soffid.iam.base.model.ConfigEntityDao getConfigEntityDao () {
		return configEntityDao;
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

	com.soffid.iam.am.model.NetworkAuthorizationEntityDao networkAuthorizationEntityDao;

	/**
	 * Sets reference to <code>networkAuthorizationEntityDao</code>.
	 */
	public void setNetworkAuthorizationEntityDao (com.soffid.iam.am.model.NetworkAuthorizationEntityDao networkAuthorizationEntityDao) {
		this.networkAuthorizationEntityDao = networkAuthorizationEntityDao;
	}

	/**
	 * Gets reference to <code>networkAuthorizationEntityDao</code>.
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntityDao getNetworkAuthorizationEntityDao () {
		return networkAuthorizationEntityDao;
	}

	com.soffid.iam.sync.model.ServerEntityDao serverEntityDao;

	/**
	 * Sets reference to <code>serverEntityDao</code>.
	 */
	public void setServerEntityDao (com.soffid.iam.sync.model.ServerEntityDao serverEntityDao) {
		this.serverEntityDao = serverEntityDao;
	}

	/**
	 * Gets reference to <code>serverEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ServerEntityDao getServerEntityDao () {
		return serverEntityDao;
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

	com.soffid.iam.pam.model.NetworkDiscoverRangeEntityDao networkDiscoverRangeEntityDao;

	/**
	 * Sets reference to <code>networkDiscoverRangeEntityDao</code>.
	 */
	public void setNetworkDiscoverRangeEntityDao (com.soffid.iam.pam.model.NetworkDiscoverRangeEntityDao networkDiscoverRangeEntityDao) {
		this.networkDiscoverRangeEntityDao = networkDiscoverRangeEntityDao;
	}

	/**
	 * Gets reference to <code>networkDiscoverRangeEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.NetworkDiscoverRangeEntityDao getNetworkDiscoverRangeEntityDao () {
		return networkDiscoverRangeEntityDao;
	}

	com.soffid.iam.pam.model.NetworkDiscoveryAccountEntityDao networkDiscoveryAccountEntityDao;

	/**
	 * Sets reference to <code>networkDiscoveryAccountEntityDao</code>.
	 */
	public void setNetworkDiscoveryAccountEntityDao (com.soffid.iam.pam.model.NetworkDiscoveryAccountEntityDao networkDiscoveryAccountEntityDao) {
		this.networkDiscoveryAccountEntityDao = networkDiscoveryAccountEntityDao;
	}

	/**
	 * Gets reference to <code>networkDiscoveryAccountEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.NetworkDiscoveryAccountEntityDao getNetworkDiscoveryAccountEntityDao () {
		return networkDiscoveryAccountEntityDao;
	}


	/**
	 * Operation findByAddress
	 * @param ip
	 * @return
	**/
	public com.soffid.iam.am.model.NetworkEntity findByAddress(
	    java.lang.String ip)
	
	{
		return findByAddress((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, ip);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.NetworkEntity findByAddress(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ip)
	
	{
		return findByAddress("from com.soffid.iam.am.model.NetworkEntity where tenant.id=:tenantId and ip=:ip",
			criteria, ip);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.NetworkEntity findByAddress(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ip)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("ip", ip, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.NetworkEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.NetworkEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.NetworkEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.am.model.NetworkEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.NetworkEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.am.model.NetworkEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.NetworkEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.NetworkEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.NetworkEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.NetworkEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation countByNetwork
	 * @param network
	 * @return
	**/
	public java.lang.Long countByNetwork(
	    java.lang.String network)
	
	{
		return countByNetwork((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, network);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long countByNetwork(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String network)
	
	{
		return countByNetwork("select count(*) from com.soffid.iam.am.model.HostEntity as h where h.network.name=:network and h.deleted = false and h.tenant.id=:tenantId",
			criteria, network);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long countByNetwork(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String network)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("network", network, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.lang.Long result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.Long' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.Long) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation getFirstFreeIP
	 * @param ipXarxa
	 * @param mascara
	 * @return
	**/
	public java.lang.String getFirstFreeIP(
	    java.lang.String ipXarxa, 
	    java.lang.String mascara)
	
	{
		return getFirstFreeIP((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, ipXarxa, mascara);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.String getFirstFreeIP(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ipXarxa, java.lang.String mascara)
	
	{
		return getFirstFreeIP("- CUSTOM -",
			criteria, ipXarxa, mascara);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.String getFirstFreeIP(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ipXarxa, java.lang.String mascara)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("ipXarxa", ipXarxa, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("mascara", mascara, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.lang.String result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.String' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.String) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.Network} object 
	 */
	public void toNetwork(com.soffid.iam.am.model.NetworkEntity source, com.soffid.iam.am.api.Network target) {
		// Attributes for Network
		target.setId(source.getId());
		target.setName(source.getName());
		target.setIp(source.getIp());
		target.setDescription(source.getDescription());
		target.setMask(source.getMask());
		target.setType(source.getType());
		target.setLanAccess(source.getLanAccess());
		target.setDhcp(source.getDhcp());
		// Missing attribute dhcpSupport on entity
		target.setLoginRestriction(source.getLoginRestriction());
		target.setDiscovery(source.getDiscovery());
		// Incompatible types source.discoveryServer and target.discoveryServer
		// Missing attribute discoveryRanges on entity
		target.setCountryCode(source.getCountryCode());
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Network} object 
	 */
	public com.soffid.iam.am.api.Network toNetwork(com.soffid.iam.am.model.NetworkEntity entity) {
		final com.soffid.iam.am.api.Network target = new com.soffid.iam.am.api.Network();
		this.toNetwork(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Network} list 
	 */
	public java.util.List<com.soffid.iam.am.api.Network> toNetworkList (java.util.Collection<com.soffid.iam.am.model.NetworkEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.Network> list =
				new java.util.LinkedList<com.soffid.iam.am.api.Network>();
			for (final com.soffid.iam.am.model.NetworkEntity instance: instances)
			{
				list.add( toNetwork(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.Network} object 
	 */
	public void networkToEntity (com.soffid.iam.am.api.Network source, com.soffid.iam.am.model.NetworkEntity target, boolean copyIfNull) {
		// Attributes for NetworkEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getIp() != null)
		{
			target.setIp(source.getIp());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getMask() != null)
		{
			target.setMask(source.getMask());
		}
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		if (copyIfNull || source.getLanAccess() != null)
		{
			target.setLanAccess(source.getLanAccess());
		}
		if (copyIfNull || source.getDhcp() != null)
		{
			target.setDhcp(source.getDhcp());
		}
		// Missing attribute tenant on entity
		// Missing attribute authorizations on entity
		// Missing attribute hosts on entity
		// Missing attribute dchpSupport on entity
		if (copyIfNull || source.getLoginRestriction() != null)
		{
			target.setLoginRestriction(source.getLoginRestriction());
		}
		if (copyIfNull || source.getDiscovery() != null)
		{
			target.setDiscovery(source.getDiscovery());
		}
		if (copyIfNull || source.getDiscoveryServer() != null)
		{
			// Incompatible types source.discoveryServer and target.discoveryServer
		}
		if (copyIfNull || source.getCountryCode() != null)
		{
			target.setCountryCode(source.getCountryCode());
		}
		// Missing attribute ranges on entity
		// Missing attribute accounts on entity
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
	 *  Transforms from {@link com.soffid.iam.am.api.Network} object 
	 */
	public com.soffid.iam.am.model.NetworkEntity networkToEntity (com.soffid.iam.am.api.Network instance) {
		com.soffid.iam.am.model.NetworkEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newNetworkEntity();
		networkToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Network} list 
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkEntity>  networkToEntityList (java.util.Collection<com.soffid.iam.am.api.Network> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.NetworkEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.NetworkEntity>();
		for (com.soffid.iam.am.api.Network instance: instances)
		{
			list.add (networkToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.NetworkEntity} .
	 */
	public com.soffid.iam.am.model.NetworkEntity newNetworkEntity()
	{
		return new com.soffid.iam.am.model.NetworkEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.NetworkEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.NetworkEntity result = (com.soffid.iam.am.model.NetworkEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.NetworkEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.NetworkEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.NetworkEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.NetworkEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.NetworkEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NetworkEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.am.model.NetworkEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.NetworkEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NetworkEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.NetworkEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NetworkEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.NetworkEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.NetworkEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NetworkEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.NetworkEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.NetworkEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.NetworkEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NetworkEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.NetworkEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.NetworkEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NetworkEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.NetworkEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"NetworkEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.NetworkEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.NetworkEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.NetworkEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
