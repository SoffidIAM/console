//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity HostEntity
 */
public abstract class HostEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.HostEntityDao
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

	com.soffid.iam.am.model.BrowserEntityDao browserEntityDao;

	/**
	 * Sets reference to <code>browserEntityDao</code>.
	 */
	public void setBrowserEntityDao (com.soffid.iam.am.model.BrowserEntityDao browserEntityDao) {
		this.browserEntityDao = browserEntityDao;
	}

	/**
	 * Gets reference to <code>browserEntityDao</code>.
	 */
	public com.soffid.iam.am.model.BrowserEntityDao getBrowserEntityDao () {
		return browserEntityDao;
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

	com.soffid.iam.am.model.HostAttributeEntityDao hostAttributeEntityDao;

	/**
	 * Sets reference to <code>hostAttributeEntityDao</code>.
	 */
	public void setHostAttributeEntityDao (com.soffid.iam.am.model.HostAttributeEntityDao hostAttributeEntityDao) {
		this.hostAttributeEntityDao = hostAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>hostAttributeEntityDao</code>.
	 */
	public com.soffid.iam.am.model.HostAttributeEntityDao getHostAttributeEntityDao () {
		return hostAttributeEntityDao;
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

	com.soffid.iam.pam.model.HostPortEntityDao hostPortEntityDao;

	/**
	 * Sets reference to <code>hostPortEntityDao</code>.
	 */
	public void setHostPortEntityDao (com.soffid.iam.pam.model.HostPortEntityDao hostPortEntityDao) {
		this.hostPortEntityDao = hostPortEntityDao;
	}

	/**
	 * Gets reference to <code>hostPortEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.HostPortEntityDao getHostPortEntityDao () {
		return hostPortEntityDao;
	}

	com.soffid.iam.pam.model.HostServiceEntityDao hostServiceEntityDao;

	/**
	 * Sets reference to <code>hostServiceEntityDao</code>.
	 */
	public void setHostServiceEntityDao (com.soffid.iam.pam.model.HostServiceEntityDao hostServiceEntityDao) {
		this.hostServiceEntityDao = hostServiceEntityDao;
	}

	/**
	 * Gets reference to <code>hostServiceEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.HostServiceEntityDao getHostServiceEntityDao () {
		return hostServiceEntityDao;
	}

	com.soffid.iam.pam.model.HostSystemEntityDao hostSystemEntityDao;

	/**
	 * Sets reference to <code>hostSystemEntityDao</code>.
	 */
	public void setHostSystemEntityDao (com.soffid.iam.pam.model.HostSystemEntityDao hostSystemEntityDao) {
		this.hostSystemEntityDao = hostSystemEntityDao;
	}

	/**
	 * Gets reference to <code>hostSystemEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.HostSystemEntityDao getHostSystemEntityDao () {
		return hostSystemEntityDao;
	}

	com.soffid.iam.rc.model.IssueHostEntityDao issueHostEntityDao;

	/**
	 * Sets reference to <code>issueHostEntityDao</code>.
	 */
	public void setIssueHostEntityDao (com.soffid.iam.rc.model.IssueHostEntityDao issueHostEntityDao) {
		this.issueHostEntityDao = issueHostEntityDao;
	}

	/**
	 * Gets reference to <code>issueHostEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueHostEntityDao getIssueHostEntityDao () {
		return issueHostEntityDao;
	}


	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.am.model.HostEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.HostEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("select maq from com.soffid.iam.am.model.HostEntity maq where maq.name=:name and maq.deleted = false and maq.tenant.id=:tenantId",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.HostEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.am.model.HostEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.HostEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.HostEntity) results.iterator().next();
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
	public com.soffid.iam.am.model.HostEntity findBySerialNumber(
	    java.lang.String serialNumber)
	
	{
		return findBySerialNumber((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, serialNumber);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.HostEntity findBySerialNumber(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serialNumber)
	
	{
		return findBySerialNumber("from com.soffid.iam.am.model.HostEntity where tenant.id=:tenantId and serialNumber=:serialNumber",
			criteria, serialNumber);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.HostEntity findBySerialNumber(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serialNumber)
	
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
			com.soffid.iam.am.model.HostEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.HostEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.HostEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation getTasks
	 * @param hostName
	 * @return
	**/
	public java.lang.String[] getTasks(
	    java.lang.String hostName)
	
	{
		return getTasks((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, hostName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.String[] getTasks(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String hostName)
	
	{
		return getTasks("- CUSTOM -",
			criteria, hostName);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.String[] getTasks(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String hostName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("hostName", hostName, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.lang.String[] result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.String[]' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.String[]) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByIP
	 * @param ip
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.am.model.HostEntity> findByIP(
	    java.lang.String ip)
	
	{
		return findByIP((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, ip);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.am.model.HostEntity> findByIP(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ip)
	
	{
		return findByIP("select host from com.soffid.iam.am.model.HostEntity as host where host.ip=:ip and host.tenant.id=:tenantId and host.deleted=false order by host.lastSeen desc",
			criteria, ip);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.am.model.HostEntity> findByIP(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ip)
	
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
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.am.model.HostEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByCurrentUserAndIpAddress
	 * @param userId
	 * @param ip
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.HostEntity> findByCurrentUserAndIpAddress(
	    java.lang.Long userId, 
	    java.lang.String ip)
	
	{
		return findByCurrentUserAndIpAddress((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userId, ip);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntity> findByCurrentUserAndIpAddress(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long userId, java.lang.String ip)
	
	{
		return findByCurrentUserAndIpAddress("select host from com.soffid.iam.am.model.SessionEntity as session      left join session.host host where host.ip = :ip and session.user.id=:userId and       not (host.deleted is true)",
			criteria, userId, ip);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntity> findByCurrentUserAndIpAddress(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long userId, java.lang.String ip)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userId", userId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("ip", ip, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.HostEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.Host} object 
	 */
	public void toHost(com.soffid.iam.am.model.HostEntity source, com.soffid.iam.am.api.Host target) {
		// Attributes for Host
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		// Incompatible types source.network and target.network
		target.setIp(source.getIp());
		target.setDynamicIp(source.getDynamicIp());
		target.setMail(source.getMail());
		target.setFolders(source.getFolders());
		target.setMac(source.getMac());
		target.setPrintersServer(source.getPrintersServer());
		target.setSerialNumber(source.getSerialNumber());
		// Missing attribute os on entity
		// Incompatible types source.hostAlias and target.hostAlias
		target.setDhcp(source.getDhcp());
		if (source.getLastSeen() == null) {
			target.setLastSeen(null);
		} else {
			target.setLastSeen(java.util.Calendar.getInstance());
			target.getLastSeen().setTime(source.getLastSeen());
		}
		if (source.getCreated() == null) {
			target.setCreated(null);
		} else {
			target.setCreated(java.util.Calendar.getInstance());
			target.getCreated().setTime(source.getCreated());
		}
		target.setLocked(source.getLocked());
		// Incompatible types source.attributes and target.attributes
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
		target.setCreatedOn(source.getCreatedOn());
		target.setDeleted(source.getDeleted());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Host} object 
	 */
	public com.soffid.iam.am.api.Host toHost(com.soffid.iam.am.model.HostEntity entity) {
		final com.soffid.iam.am.api.Host target = new com.soffid.iam.am.api.Host();
		this.toHost(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Host} list 
	 */
	public java.util.List<com.soffid.iam.am.api.Host> toHostList (java.util.Collection<com.soffid.iam.am.model.HostEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.Host> list =
				new java.util.LinkedList<com.soffid.iam.am.api.Host>();
			for (final com.soffid.iam.am.model.HostEntity instance: instances)
			{
				list.add( toHost(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.Host} object 
	 */
	public void hostToEntity (com.soffid.iam.am.api.Host source, com.soffid.iam.am.model.HostEntity target, boolean copyIfNull) {
		// Attributes for HostEntity
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
		if (copyIfNull || source.getDhcp() != null)
		{
			target.setDhcp(source.getDhcp());
		}
		if (copyIfNull || source.getMail() != null)
		{
			target.setMail(source.getMail());
		}
		if (copyIfNull || source.getFolders() != null)
		{
			target.setFolders(source.getFolders());
		}
		// Missing attribute userProfiles on entity
		// Missing attribute printers on entity
		if (copyIfNull || source.getNetwork() != null)
		{
			// Incompatible types source.network and target.network
		}
		if (copyIfNull || source.getPrintersServer() != null)
		{
			target.setPrintersServer(source.getPrintersServer());
		}
		if (copyIfNull || source.getMac() != null)
		{
			target.setMac(source.getMac());
		}
		if (copyIfNull || source.getHostAlias() != null)
		{
			// Incompatible types source.hostAlias and target.hostAlias
		}
		// Missing attribute administratorAuthorizationAccess on entity
		// Missing attribute administratorUser on entity
		// Missing attribute administratorPassword on entity
		// Missing attribute administratorPasswordDate on entity
		if (copyIfNull || source.getSerialNumber() != null)
		{
			target.setSerialNumber(source.getSerialNumber());
		}
		if (copyIfNull || source.getDynamicIp() != null)
		{
			target.setDynamicIp(source.getDynamicIp());
		}
		if (copyIfNull || source.getLastSeen() != null)
		{
			if (source.getLastSeen() == null) {
				target.setLastSeen(null);
			} else {
				target.setLastSeen(source.getLastSeen().getTime());
			}
		}
		if (copyIfNull || source.getCreated() != null)
		{
			if (source.getCreated() == null) {
				target.setCreated(null);
			} else {
				target.setCreated(source.getCreated().getTime());
			}
		}
		if (copyIfNull || source.getDeleted() != null)
		{
			target.setDeleted(source.getDeleted());
		}
		if (copyIfNull || source.getLocked() != null)
		{
			target.setLocked(source.getLocked());
		}
		// Missing attribute operatingSystem on entity
		// Missing attribute tenant on entity
		// Missing attribute browsers on entity
		if (copyIfNull || source.getAttributes() != null)
		{
			// Incompatible types source.attributes and target.attributes
		}
		// Missing attribute entryPoints on entity
		// Missing attribute ports on entity
		// Missing attribute services on entity
		// Missing attribute systems on entity
		// Missing attribute events on entity
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
		if (copyIfNull || source.getCreatedOn() != null)
		{
			target.setCreatedOn(source.getCreatedOn());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Host} object 
	 */
	public com.soffid.iam.am.model.HostEntity hostToEntity (com.soffid.iam.am.api.Host instance) {
		com.soffid.iam.am.model.HostEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newHostEntity();
		hostToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Host} list 
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntity>  hostToEntityList (java.util.Collection<com.soffid.iam.am.api.Host> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.HostEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.HostEntity>();
		for (com.soffid.iam.am.api.Host instance: instances)
		{
			list.add (hostToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.HostEntity} .
	 */
	public com.soffid.iam.am.model.HostEntity newHostEntity()
	{
		return new com.soffid.iam.am.model.HostEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.HostEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.HostEntity result = (com.soffid.iam.am.model.HostEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.HostEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.HostEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.HostEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.HostEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.HostEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		entity.setCreated(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.HostEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.HostEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.HostEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.HostEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.HostEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.HostEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.HostEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.HostEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.HostEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.HostEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.HostEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"HostEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.HostEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.HostEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.HostEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
