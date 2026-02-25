//
// (c) 2014 Soffid
//
//
package com.soffid.iam.rc.model;
/**
 * DAO Base for Entity AuditEntity
 */
public abstract class AuditEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.rc.model.AuditEntityDao
{
	com.soffid.iam.base.model.AccountEntityDao accountEntityDao;

	/**
	 * Sets reference to <code>accountEntityDao</code>.
	 */
	public void setAccountEntityDao (com.soffid.iam.base.model.AccountEntityDao accountEntityDao) {
		this.accountEntityDao = accountEntityDao;
	}

	/**
	 * Gets reference to <code>accountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountEntityDao getAccountEntityDao () {
		return accountEntityDao;
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

	com.soffid.iam.iga.model.MailDomainEntityDao mailDomainEntityDao;

	/**
	 * Sets reference to <code>mailDomainEntityDao</code>.
	 */
	public void setMailDomainEntityDao (com.soffid.iam.iga.model.MailDomainEntityDao mailDomainEntityDao) {
		this.mailDomainEntityDao = mailDomainEntityDao;
	}

	/**
	 * Gets reference to <code>mailDomainEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailDomainEntityDao getMailDomainEntityDao () {
		return mailDomainEntityDao;
	}

	com.soffid.iam.iga.model.MailListEntityDao mailListEntityDao;

	/**
	 * Sets reference to <code>mailListEntityDao</code>.
	 */
	public void setMailListEntityDao (com.soffid.iam.iga.model.MailListEntityDao mailListEntityDao) {
		this.mailListEntityDao = mailListEntityDao;
	}

	/**
	 * Gets reference to <code>mailListEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListEntityDao getMailListEntityDao () {
		return mailListEntityDao;
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

	com.soffid.iam.am.service.PasswordService passwordService;

	/**
	 * Sets reference to <code>passwordService</code>.
	 */
	public void setPasswordService (com.soffid.iam.am.service.PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	/**
	 * Gets reference to <code>passwordService</code>.
	 */
	public com.soffid.iam.am.service.PasswordService getPasswordService () {
		return passwordService;
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


	/**
	 * Operation findById
	 * @param id
	 * @return
	**/
	public com.soffid.iam.rc.model.AuditEntity findById(
	    java.lang.Long id)
	
	{
		return findById((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, id);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.rc.model.AuditEntity findById(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		return findById("from com.soffid.iam.rc.model.AuditEntity where tenant.id=:tenantId and id=:id",
			criteria, id);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.rc.model.AuditEntity findById(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("id", id, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.rc.model.AuditEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.rc.model.AuditEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.rc.model.AuditEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByIndex
	 * @param searchIndex
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.rc.model.AuditEntity> findByIndex(
	    java.lang.String searchIndex)
	
	{
		return findByIndex((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, searchIndex);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.rc.model.AuditEntity> findByIndex(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String searchIndex)
	
	{
		return findByIndex("select a from com.soffid.iam.rc.model.AuditEntity as a where searchIndex=:searchIndex and tenant.id=:tenantId order by date asc",
			criteria, searchIndex);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.rc.model.AuditEntity> findByIndex(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String searchIndex)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("searchIndex", searchIndex, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.rc.model.AuditEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.rc.model.AuditEntity#	 * @see com.soffid.iam.rc.model.AuditEntity#void unlinkAccounts(com.soffid.iam.base.model.AccountEntity account)
	 */
	public void unlinkAccounts(
		com.soffid.iam.base.model.AccountEntity account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.rc.model.AuditEntity.unlinkAccounts(com.soffid.iam.base.model.AccountEntity account) - account cannot be null");
		}
		try
		{
			handleUnlinkAccounts(account);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.model.AuditEntity.class).
				warn ("Error on AuditEntity.unlinkAccounts", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on AuditEntity.unlinkAccounts: "+th.toString(), th);
		}
	}

	protected abstract void handleUnlinkAccounts(com.soffid.iam.base.model.AccountEntity account) throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.Audit} object 
	 */
	public void toAudit(com.soffid.iam.rc.model.AuditEntity source, com.soffid.iam.rc.api.Audit target) {
		// Attributes for Audit
		target.setObject(source.getObject());
		target.setAction(source.getAction());
		// Missing attribute description on entity
		// Missing attribute author on entity
		target.setRole(source.getRole());
		target.setDatabase(source.getDatabase());
		target.setId(source.getId());
		target.setPrinter(source.getPrinter());
		// Missing attribute application on entity
		target.setMailList(source.getMailList());
		// Incompatible types source.group and target.group
		target.setUser(source.getUser());
		target.setMailDomain(source.getMailDomain());
		target.setHost(source.getHost());
		target.setNetwork(source.getNetwork());
		// Missing attribute file on entity
		target.setDomainValue(source.getDomainValue());
		target.setDomain(source.getDomain());
		target.setConfigurationParameter(source.getConfigurationParameter());
		// Missing attribute primaryGroupAuthor on entity
		// Missing attribute authorFullName on entity
		target.setAuthorization(source.getAuthorization());
		// Missing attribute fileName on entity
		target.setIdentityFederation(source.getIdentityFederation());
		// Missing attribute mailListBelong on entity
		// Missing attribute mailDomainBelogns on entity
		target.setAccount(source.getAccount());
		target.setComment(source.getComment());
		// Missing attribute message on entity
		target.setPasswordDomain(source.getPasswordDomain());
		target.setUserDomain(source.getUserDomain());
		target.setUserType(source.getUserType());
		target.setRule(source.getRule());
		target.setScheduledTask(source.getScheduledTask());
		// Missing attribute calendar on entity
		target.setCustomObjectName(source.getCustomObjectName());
		target.setCustomObjectType(source.getCustomObjectType());
		target.setSourceIp(source.getSourceIp());
		target.setJumpServerGroup(source.getJumpServerGroup());
		target.setPamSessionId(source.getPamSessionId());
		target.setOldValue(source.getOldValue());
		target.setNewValue(source.getNewValue());
		target.setSearchIndex(source.getSearchIndex());
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.Audit} object 
	 */
	public com.soffid.iam.rc.api.Audit toAudit(com.soffid.iam.rc.model.AuditEntity entity) {
		final com.soffid.iam.rc.api.Audit target = new com.soffid.iam.rc.api.Audit();
		this.toAudit(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.Audit} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.Audit> toAuditList (java.util.Collection<com.soffid.iam.rc.model.AuditEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.rc.api.Audit> list =
				new java.util.LinkedList<com.soffid.iam.rc.api.Audit>();
			for (final com.soffid.iam.rc.model.AuditEntity instance: instances)
			{
				list.add( toAudit(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.Audit} object 
	 */
	public void auditToEntity (com.soffid.iam.rc.api.Audit source, com.soffid.iam.rc.model.AuditEntity target, boolean copyIfNull) {
		// Attributes for AuditEntity
		// Missing attribute date on entity
		if (copyIfNull || source.getUser() != null)
		{
			target.setUser(source.getUser());
		}
		if (copyIfNull || source.getAction() != null)
		{
			target.setAction(source.getAction());
		}
		if (copyIfNull || source.getObject() != null)
		{
			target.setObject(source.getObject());
		}
		if (copyIfNull || source.getGroup() != null)
		{
			// Incompatible types source.group and target.group
		}
		if (copyIfNull || source.getDomain() != null)
		{
			target.setDomain(source.getDomain());
		}
		if (copyIfNull || source.getDomainValue() != null)
		{
			target.setDomainValue(source.getDomainValue());
		}
		if (copyIfNull || source.getConfigurationParameter() != null)
		{
			target.setConfigurationParameter(source.getConfigurationParameter());
		}
		if (copyIfNull || source.getDatabase() != null)
		{
			target.setDatabase(source.getDatabase());
		}
		if (copyIfNull || source.getPrinter() != null)
		{
			target.setPrinter(source.getPrinter());
		}
		// Missing attribute informationSystem on entity
		if (copyIfNull || source.getMailDomain() != null)
		{
			target.setMailDomain(source.getMailDomain());
		}
		if (copyIfNull || source.getMailList() != null)
		{
			target.setMailList(source.getMailList());
		}
		if (copyIfNull || source.getNetwork() != null)
		{
			target.setNetwork(source.getNetwork());
		}
		if (copyIfNull || source.getRole() != null)
		{
			target.setRole(source.getRole());
		}
		if (copyIfNull || source.getHost() != null)
		{
			target.setHost(source.getHost());
		}
		if (copyIfNull || source.getAuthorization() != null)
		{
			target.setAuthorization(source.getAuthorization());
		}
		// Missing attribute fileId on entity
		if (copyIfNull || source.getIdentityFederation() != null)
		{
			target.setIdentityFederation(source.getIdentityFederation());
		}
		// Missing attribute belongsMailList on entity
		// Missing attribute belongsmailDomain on entity
		if (copyIfNull || source.getAccount() != null)
		{
			target.setAccount(source.getAccount());
		}
		// Missing attribute accountAssoc on entity
		if (copyIfNull || source.getComment() != null)
		{
			target.setComment(source.getComment());
		}
		if (copyIfNull || source.getUserType() != null)
		{
			target.setUserType(source.getUserType());
		}
		if (copyIfNull || source.getUserDomain() != null)
		{
			target.setUserDomain(source.getUserDomain());
		}
		if (copyIfNull || source.getPasswordDomain() != null)
		{
			target.setPasswordDomain(source.getPasswordDomain());
		}
		if (copyIfNull || source.getRule() != null)
		{
			target.setRule(source.getRule());
		}
		if (copyIfNull || source.getScheduledTask() != null)
		{
			target.setScheduledTask(source.getScheduledTask());
		}
		if (copyIfNull || source.getCustomObjectName() != null)
		{
			target.setCustomObjectName(source.getCustomObjectName());
		}
		if (copyIfNull || source.getCustomObjectType() != null)
		{
			target.setCustomObjectType(source.getCustomObjectType());
		}
		// Missing attribute tenant on entity
		if (copyIfNull || source.getSourceIp() != null)
		{
			target.setSourceIp(source.getSourceIp());
		}
		if (copyIfNull || source.getJumpServerGroup() != null)
		{
			target.setJumpServerGroup(source.getJumpServerGroup());
		}
		if (copyIfNull || source.getPamSessionId() != null)
		{
			target.setPamSessionId(source.getPamSessionId());
		}
		if (copyIfNull || source.getOldValue() != null)
		{
			target.setOldValue(source.getOldValue());
		}
		if (copyIfNull || source.getNewValue() != null)
		{
			target.setNewValue(source.getNewValue());
		}
		if (copyIfNull || source.getSearchIndex() != null)
		{
			target.setSearchIndex(source.getSearchIndex());
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
	 *  Transforms from {@link com.soffid.iam.rc.api.Audit} object 
	 */
	public com.soffid.iam.rc.model.AuditEntity auditToEntity (com.soffid.iam.rc.api.Audit instance) {
		com.soffid.iam.rc.model.AuditEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newAuditEntity();
		auditToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.Audit} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.AuditEntity>  auditToEntityList (java.util.Collection<com.soffid.iam.rc.api.Audit> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.rc.model.AuditEntity> list =
			new java.util.LinkedList<com.soffid.iam.rc.model.AuditEntity>();
		for (com.soffid.iam.rc.api.Audit instance: instances)
		{
			list.add (auditToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.AuditEntity} .
	 */
	public com.soffid.iam.rc.model.AuditEntity newAuditEntity()
	{
		return new com.soffid.iam.rc.model.AuditEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.AuditEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.rc.model.AuditEntity result = (com.soffid.iam.rc.model.AuditEntity) this.getHibernateTemplate().get(com.soffid.iam.rc.model.AuditEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.AuditEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.rc.model.AuditEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.rc.model.AuditEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.AuditEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.AuditEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AuditEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.rc.model.AuditEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.AuditEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AuditEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.AuditEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AuditEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.AuditEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.AuditEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AuditEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.AuditEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.AuditEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.AuditEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AuditEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.AuditEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.AuditEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AuditEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.AuditEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AuditEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.rc.model.AuditEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.AuditEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.rc.model.AuditEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.AuditEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.AuditEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.rc.model.AuditEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
