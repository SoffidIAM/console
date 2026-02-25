//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity BrowserEntity
 */
public abstract class BrowserEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.BrowserEntityDao
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

	com.soffid.iam.pam.model.HostAdminEntityDao hostAdminEntityDao;

	/**
	 * Sets reference to <code>hostAdminEntityDao</code>.
	 */
	public void setHostAdminEntityDao (com.soffid.iam.pam.model.HostAdminEntityDao hostAdminEntityDao) {
		this.hostAdminEntityDao = hostAdminEntityDao;
	}

	/**
	 * Gets reference to <code>hostAdminEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.HostAdminEntityDao getHostAdminEntityDao () {
		return hostAdminEntityDao;
	}

	com.soffid.iam.iga.model.HostAliasEntityDao hostAliasEntityDao;

	/**
	 * Sets reference to <code>hostAliasEntityDao</code>.
	 */
	public void setHostAliasEntityDao (com.soffid.iam.iga.model.HostAliasEntityDao hostAliasEntityDao) {
		this.hostAliasEntityDao = hostAliasEntityDao;
	}

	/**
	 * Gets reference to <code>hostAliasEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.HostAliasEntityDao getHostAliasEntityDao () {
		return hostAliasEntityDao;
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

	com.soffid.iam.am.model.OsTypeEntityDao osTypeEntityDao;

	/**
	 * Sets reference to <code>osTypeEntityDao</code>.
	 */
	public void setOsTypeEntityDao (com.soffid.iam.am.model.OsTypeEntityDao osTypeEntityDao) {
		this.osTypeEntityDao = osTypeEntityDao;
	}

	/**
	 * Gets reference to <code>osTypeEntityDao</code>.
	 */
	public com.soffid.iam.am.model.OsTypeEntityDao getOsTypeEntityDao () {
		return osTypeEntityDao;
	}

	com.soffid.iam.iga.model.PrinterEntityDao printerEntityDao;

	/**
	 * Sets reference to <code>printerEntityDao</code>.
	 */
	public void setPrinterEntityDao (com.soffid.iam.iga.model.PrinterEntityDao printerEntityDao) {
		this.printerEntityDao = printerEntityDao;
	}

	/**
	 * Gets reference to <code>printerEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.PrinterEntityDao getPrinterEntityDao () {
		return printerEntityDao;
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

	com.soffid.iam.am.model.SessionEntityDao sessionEntityDao;

	/**
	 * Sets reference to <code>sessionEntityDao</code>.
	 */
	public void setSessionEntityDao (com.soffid.iam.am.model.SessionEntityDao sessionEntityDao) {
		this.sessionEntityDao = sessionEntityDao;
	}

	/**
	 * Gets reference to <code>sessionEntityDao</code>.
	 */
	public com.soffid.iam.am.model.SessionEntityDao getSessionEntityDao () {
		return sessionEntityDao;
	}

	com.soffid.iam.rc.model.IssueBrowserEntityDao issueBrowserEntityDao;

	/**
	 * Sets reference to <code>issueBrowserEntityDao</code>.
	 */
	public void setIssueBrowserEntityDao (com.soffid.iam.rc.model.IssueBrowserEntityDao issueBrowserEntityDao) {
		this.issueBrowserEntityDao = issueBrowserEntityDao;
	}

	/**
	 * Gets reference to <code>issueBrowserEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueBrowserEntityDao getIssueBrowserEntityDao () {
		return issueBrowserEntityDao;
	}


	/**
	 * Operation findByHost
	 * @param hostId
	 * @return
	**/
	public com.soffid.iam.am.model.BrowserEntity findByHost(
	    java.lang.Long hostId)
	
	{
		return findByHost((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, hostId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.BrowserEntity findByHost(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long hostId)
	
	{
		return findByHost("select b from com.soffid.iam.am.model.BrowserEntity as b where b.deleted is false and b.host.id=:hostId",
			criteria, hostId);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.BrowserEntity findByHost(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long hostId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("hostId", hostId, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.BrowserEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.BrowserEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.BrowserEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findBySerialNumber
	 * @param serialNumber
	 * @return
	**/
	public com.soffid.iam.am.model.BrowserEntity findBySerialNumber(
	    java.lang.String serialNumber)
	
	{
		return findBySerialNumber((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, serialNumber);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.BrowserEntity findBySerialNumber(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serialNumber)
	
	{
		return findBySerialNumber("from com.soffid.iam.am.model.BrowserEntity where tenant.id=:tenantId and serialNumber=:serialNumber",
			criteria, serialNumber);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.BrowserEntity findBySerialNumber(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serialNumber)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("serialNumber", serialNumber, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.BrowserEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.BrowserEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.BrowserEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.Browser} object 
	 */
	public void toBrowser(com.soffid.iam.am.model.BrowserEntity source, com.soffid.iam.am.api.Browser target) {
		// Attributes for Browser
		target.setId(source.getId());
		target.setSerialNumber(source.getSerialNumber());
		target.setDeviceType(source.getDeviceType());
		target.setOperatingSystem(source.getOperatingSystem());
		target.setBrowser(source.getBrowser());
		target.setCpu(source.getCpu());
		target.setIp(source.getIp());
		// Incompatible types source.lastUser and target.lastUser
		// Missing attribute hostName on entity
		target.setLastSeen(source.getLastSeen());
		target.setCreated(source.getCreated());
		target.setDeleted(source.getDeleted());
		target.setLocked(source.getLocked());
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Browser} object 
	 */
	public com.soffid.iam.am.api.Browser toBrowser(com.soffid.iam.am.model.BrowserEntity entity) {
		final com.soffid.iam.am.api.Browser target = new com.soffid.iam.am.api.Browser();
		this.toBrowser(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Browser} list 
	 */
	public java.util.List<com.soffid.iam.am.api.Browser> toBrowserList (java.util.Collection<com.soffid.iam.am.model.BrowserEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.Browser> list =
				new java.util.LinkedList<com.soffid.iam.am.api.Browser>();
			for (final com.soffid.iam.am.model.BrowserEntity instance: instances)
			{
				list.add( toBrowser(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.Browser} object 
	 */
	public void browserToEntity (com.soffid.iam.am.api.Browser source, com.soffid.iam.am.model.BrowserEntity target, boolean copyIfNull) {
		// Attributes for BrowserEntity
		if (copyIfNull || source.getIp() != null)
		{
			target.setIp(source.getIp());
		}
		// Missing attribute host on entity
		if (copyIfNull || source.getSerialNumber() != null)
		{
			target.setSerialNumber(source.getSerialNumber());
		}
		if (copyIfNull || source.getLastSeen() != null)
		{
			target.setLastSeen(source.getLastSeen());
		}
		if (copyIfNull || source.getCreated() != null)
		{
			target.setCreated(source.getCreated());
		}
		if (copyIfNull || source.getDeleted() != null)
		{
			target.setDeleted(source.getDeleted());
		}
		if (copyIfNull || source.getLocked() != null)
		{
			target.setLocked(source.getLocked());
		}
		if (copyIfNull || source.getDeviceType() != null)
		{
			target.setDeviceType(source.getDeviceType());
		}
		if (copyIfNull || source.getOperatingSystem() != null)
		{
			target.setOperatingSystem(source.getOperatingSystem());
		}
		if (copyIfNull || source.getBrowser() != null)
		{
			target.setBrowser(source.getBrowser());
		}
		if (copyIfNull || source.getCpu() != null)
		{
			target.setCpu(source.getCpu());
		}
		if (copyIfNull || source.getLastUser() != null)
		{
			// Incompatible types source.lastUser and target.lastUser
		}
		// Missing attribute tenant on entity
		// Missing attribute sessions on entity
		// Missing attribute events on entity
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
	 *  Transforms from {@link com.soffid.iam.am.api.Browser} object 
	 */
	public com.soffid.iam.am.model.BrowserEntity browserToEntity (com.soffid.iam.am.api.Browser instance) {
		com.soffid.iam.am.model.BrowserEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newBrowserEntity();
		browserToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Browser} list 
	 */
	public java.util.List<com.soffid.iam.am.model.BrowserEntity>  browserToEntityList (java.util.Collection<com.soffid.iam.am.api.Browser> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.BrowserEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.BrowserEntity>();
		for (com.soffid.iam.am.api.Browser instance: instances)
		{
			list.add (browserToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.BrowserEntity} .
	 */
	public com.soffid.iam.am.model.BrowserEntity newBrowserEntity()
	{
		return new com.soffid.iam.am.model.BrowserEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.BrowserEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.BrowserEntity result = (com.soffid.iam.am.model.BrowserEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.BrowserEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.BrowserEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.BrowserEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.BrowserEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.BrowserEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.BrowserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"BrowserEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.am.model.BrowserEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.BrowserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"BrowserEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.BrowserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"BrowserEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.BrowserEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.BrowserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"BrowserEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.BrowserEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.BrowserEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.BrowserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"BrowserEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.BrowserEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.BrowserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"BrowserEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.BrowserEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"BrowserEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.BrowserEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.BrowserEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.BrowserEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.BrowserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.BrowserEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.BrowserEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
