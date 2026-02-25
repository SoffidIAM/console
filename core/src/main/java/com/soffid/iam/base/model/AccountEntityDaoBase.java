//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity AccountEntity
 */
public abstract class AccountEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.AccountEntityDao
{
	com.soffid.iam.impl.service.ACLService aCLService;

	/**
	 * Sets reference to <code>aCLService</code>.
	 */
	public void setACLService (com.soffid.iam.impl.service.ACLService aCLService) {
		this.aCLService = aCLService;
	}

	/**
	 * Gets reference to <code>aCLService</code>.
	 */
	public com.soffid.iam.impl.service.ACLService getACLService () {
		return aCLService;
	}

	com.soffid.iam.pam.model.AccountAccessEntityDao accountAccessEntityDao;

	/**
	 * Sets reference to <code>accountAccessEntityDao</code>.
	 */
	public void setAccountAccessEntityDao (com.soffid.iam.pam.model.AccountAccessEntityDao accountAccessEntityDao) {
		this.accountAccessEntityDao = accountAccessEntityDao;
	}

	/**
	 * Gets reference to <code>accountAccessEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.AccountAccessEntityDao getAccountAccessEntityDao () {
		return accountAccessEntityDao;
	}

	com.soffid.iam.base.model.AccountAttributeEntityDao accountAttributeEntityDao;

	/**
	 * Sets reference to <code>accountAttributeEntityDao</code>.
	 */
	public void setAccountAttributeEntityDao (com.soffid.iam.base.model.AccountAttributeEntityDao accountAttributeEntityDao) {
		this.accountAttributeEntityDao = accountAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>accountAttributeEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountAttributeEntityDao getAccountAttributeEntityDao () {
		return accountAttributeEntityDao;
	}

	com.soffid.iam.am.model.AccountPasswordEntityDao accountPasswordEntityDao;

	/**
	 * Sets reference to <code>accountPasswordEntityDao</code>.
	 */
	public void setAccountPasswordEntityDao (com.soffid.iam.am.model.AccountPasswordEntityDao accountPasswordEntityDao) {
		this.accountPasswordEntityDao = accountPasswordEntityDao;
	}

	/**
	 * Gets reference to <code>accountPasswordEntityDao</code>.
	 */
	public com.soffid.iam.am.model.AccountPasswordEntityDao getAccountPasswordEntityDao () {
		return accountPasswordEntityDao;
	}

	com.soffid.iam.iga.model.AccountSnapshotEntityDao accountSnapshotEntityDao;

	/**
	 * Sets reference to <code>accountSnapshotEntityDao</code>.
	 */
	public void setAccountSnapshotEntityDao (com.soffid.iam.iga.model.AccountSnapshotEntityDao accountSnapshotEntityDao) {
		this.accountSnapshotEntityDao = accountSnapshotEntityDao;
	}

	/**
	 * Gets reference to <code>accountSnapshotEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.AccountSnapshotEntityDao getAccountSnapshotEntityDao () {
		return accountSnapshotEntityDao;
	}

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

	com.soffid.iam.pam.model.JumpServerGroupEntityDao jumpServerGroupEntityDao;

	/**
	 * Sets reference to <code>jumpServerGroupEntityDao</code>.
	 */
	public void setJumpServerGroupEntityDao (com.soffid.iam.pam.model.JumpServerGroupEntityDao jumpServerGroupEntityDao) {
		this.jumpServerGroupEntityDao = jumpServerGroupEntityDao;
	}

	/**
	 * Gets reference to <code>jumpServerGroupEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntityDao getJumpServerGroupEntityDao () {
		return jumpServerGroupEntityDao;
	}

	com.soffid.iam.iga.model.RoleAccountEntityDao roleAccountEntityDao;

	/**
	 * Sets reference to <code>roleAccountEntityDao</code>.
	 */
	public void setRoleAccountEntityDao (com.soffid.iam.iga.model.RoleAccountEntityDao roleAccountEntityDao) {
		this.roleAccountEntityDao = roleAccountEntityDao;
	}

	/**
	 * Gets reference to <code>roleAccountEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleAccountEntityDao getRoleAccountEntityDao () {
		return roleAccountEntityDao;
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

	com.soffid.iam.base.model.UserAccountEntityDao userAccountEntityDao;

	/**
	 * Sets reference to <code>userAccountEntityDao</code>.
	 */
	public void setUserAccountEntityDao (com.soffid.iam.base.model.UserAccountEntityDao userAccountEntityDao) {
		this.userAccountEntityDao = userAccountEntityDao;
	}

	/**
	 * Gets reference to <code>userAccountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserAccountEntityDao getUserAccountEntityDao () {
		return userAccountEntityDao;
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

	com.soffid.iam.base.model.UserTypeEntityDao userTypeEntityDao;

	/**
	 * Sets reference to <code>userTypeEntityDao</code>.
	 */
	public void setUserTypeEntityDao (com.soffid.iam.base.model.UserTypeEntityDao userTypeEntityDao) {
		this.userTypeEntityDao = userTypeEntityDao;
	}

	/**
	 * Gets reference to <code>userTypeEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserTypeEntityDao getUserTypeEntityDao () {
		return userTypeEntityDao;
	}

	com.soffid.iam.am.model.VaultFolderEntityDao vaultFolderEntityDao;

	/**
	 * Sets reference to <code>vaultFolderEntityDao</code>.
	 */
	public void setVaultFolderEntityDao (com.soffid.iam.am.model.VaultFolderEntityDao vaultFolderEntityDao) {
		this.vaultFolderEntityDao = vaultFolderEntityDao;
	}

	/**
	 * Gets reference to <code>vaultFolderEntityDao</code>.
	 */
	public com.soffid.iam.am.model.VaultFolderEntityDao getVaultFolderEntityDao () {
		return vaultFolderEntityDao;
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

	com.soffid.iam.rc.model.IssueEntityDao issueEntityDao;

	/**
	 * Sets reference to <code>issueEntityDao</code>.
	 */
	public void setIssueEntityDao (com.soffid.iam.rc.model.IssueEntityDao issueEntityDao) {
		this.issueEntityDao = issueEntityDao;
	}

	/**
	 * Gets reference to <code>issueEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueEntityDao getIssueEntityDao () {
		return issueEntityDao;
	}


	/**
	 * @see com.soffid.iam.base.model.AccountEntity#	 * @see com.soffid.iam.base.model.AccountEntity#com.soffid.iam.base.api.AccountAccessLevelEnum getAccessLevel(com.soffid.iam.base.model.AccountEntity account, java.lang.String user)
	 */
	public com.soffid.iam.base.api.AccountAccessLevelEnum getAccessLevel(
		com.soffid.iam.base.model.AccountEntity account, 
		java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AccountAccessLevelEnum com.soffid.iam.base.model.AccountEntity.getAccessLevel(com.soffid.iam.base.model.AccountEntity account, java.lang.String user) - account cannot be null");
		}
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.AccountAccessLevelEnum com.soffid.iam.base.model.AccountEntity.getAccessLevel(com.soffid.iam.base.model.AccountEntity account, java.lang.String user) - user cannot be null");
		}
		try
		{
			return handleGetAccessLevel(account, user);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.AccountEntity.class).
				warn ("Error on AccountEntity.getAccessLevel", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on AccountEntity.getAccessLevel: "+th.toString(), th);
		}
	}

	protected abstract com.soffid.iam.base.api.AccountAccessLevelEnum handleGetAccessLevel(com.soffid.iam.base.model.AccountEntity account, java.lang.String user) throws Exception;

	/**
	 * Operation findByExternalIdAndDispatcher
	 * @param externalId
	 * @param dispatcher
	 * @return
	**/
	public com.soffid.iam.base.model.AccountEntity findByExternalIdAndDispatcher(
	    java.lang.String externalId, 
	    java.lang.String dispatcher)
	
	{
		return findByExternalIdAndDispatcher((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, externalId, dispatcher);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.AccountEntity findByExternalIdAndDispatcher(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId, java.lang.String dispatcher)
	
	{
		return findByExternalIdAndDispatcher("from com.soffid.iam.base.model.AccountEntity acc\nwhere acc.externalId = :externalId and acc.system.name=:dispatcher and acc.system.tenant.id=:tenantId and (acc.deleted is null or acc.deleted is false)",
			criteria, externalId, dispatcher);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.AccountEntity findByExternalIdAndDispatcher(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId, java.lang.String dispatcher)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("externalId", externalId, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("dispatcher", dispatcher, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.AccountEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.AccountEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.AccountEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByLoginNameAndSystem
	 * @param loginName
	 * @param dispatcher
	 * @return
	**/
	public com.soffid.iam.base.model.AccountEntity findByLoginNameAndSystem(
	    java.lang.String loginName, 
	    java.lang.String dispatcher)
	
	{
		return findByLoginNameAndSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, loginName, dispatcher);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.AccountEntity findByLoginNameAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String loginName, java.lang.String dispatcher)
	
	{
		return findByLoginNameAndSystem("from com.soffid.iam.base.model.AccountEntity acc\nwhere acc.loginName = :loginName and acc.system.name=:dispatcher and acc.system.tenant.id=:tenantId and (acc.deleted is null or acc.deleted is false)",
			criteria, loginName, dispatcher);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.AccountEntity findByLoginNameAndSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String loginName, java.lang.String dispatcher)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("loginName", loginName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("dispatcher", dispatcher, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.AccountEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.AccountEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.AccountEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByNameAndSystem
	 * @param name
	 * @param dispatcher
	 * @return
	**/
	public com.soffid.iam.base.model.AccountEntity findByNameAndSystem(
	    java.lang.String name, 
	    java.lang.String dispatcher)
	
	{
		return findByNameAndSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, dispatcher);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.AccountEntity findByNameAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String dispatcher)
	
	{
		return findByNameAndSystem("from com.soffid.iam.base.model.AccountEntity acc\nwhere acc.name = :name and acc.system.name=:dispatcher and acc.system.tenant.id=:tenantId and (acc.deleted is false or acc.deleted is null)",
			criteria, name, dispatcher);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.AccountEntity findByNameAndSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String dispatcher)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("dispatcher", dispatcher, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.AccountEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.AccountEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.AccountEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByNameAndSystemDeleted
	 * @param name
	 * @param dispatcher
	 * @return
	**/
	public com.soffid.iam.base.model.AccountEntity findByNameAndSystemDeleted(
	    java.lang.String name, 
	    java.lang.String dispatcher)
	
	{
		return findByNameAndSystemDeleted((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, dispatcher);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.AccountEntity findByNameAndSystemDeleted(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String dispatcher)
	
	{
		return findByNameAndSystemDeleted("from com.soffid.iam.base.model.AccountEntity acc\nwhere acc.name = :name and acc.system.name=:dispatcher and acc.system.tenant.id=:tenantId ",
			criteria, name, dispatcher);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.AccountEntity findByNameAndSystemDeleted(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String dispatcher)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("dispatcher", dispatcher, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.AccountEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.AccountEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.AccountEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation getHPAccounts
	 * @return
	**/
	public java.lang.Long getHPAccounts(
)
	
	{
		return getHPAccounts((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long getHPAccounts(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return getHPAccounts("select count(a.id) from com.soffid.iam.base.model.AccountEntity as a where a.type='P' and a.system.url is not null and a.system.tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long getHPAccounts(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
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
	 * Operation getPamAccounts
	 * @return
	**/
	public java.lang.Long getPamAccounts(
)
	
	{
		return getPamAccounts((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long getPamAccounts(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return getPamAccounts("select count(a.id) from com.soffid.iam.base.model.AccountEntity as a where a.launchType = 'P' and a.system.tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long getPamAccounts(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
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
	 * Operation getPamAccountsExpiredPassword
	 * @return
	**/
	public java.lang.Long getPamAccountsExpiredPassword(
)
	
	{
		return getPamAccountsExpiredPassword((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long getPamAccountsExpiredPassword(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return getPamAccountsExpiredPassword("select count(a.id) from com.soffid.iam.base.model.AccountEntity as a where a.launchType = 'P' and a.passwordStatus = 'PASSWORD_GOOD_EXPIRED' and a.system.tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long getPamAccountsExpiredPassword(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
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
	 * Operation getPamAccountsWrongPassword
	 * @return
	**/
	public java.lang.Long getPamAccountsWrongPassword(
)
	
	{
		return getPamAccountsWrongPassword((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long getPamAccountsWrongPassword(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return getPamAccountsWrongPassword("select count(a.id) from com.soffid.iam.base.model.AccountEntity as a where a.launchType = 'P' and a.passwordStatus = 'PASSWORD_WRONG' and a.system.tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long getPamAccountsWrongPassword(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
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
	 * Operation getReservedHPAccounts
	 * @return
	**/
	public java.lang.Long getReservedHPAccounts(
)
	
	{
		return getReservedHPAccounts((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long getReservedHPAccounts(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return getReservedHPAccounts("select count(ua.id) from com.soffid.iam.base.model.UserAccountEntity as ua where ua.account.type='P' and ua.account.system.url is not null and ua.account.system.tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long getReservedHPAccounts(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
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
	 * Operation findByText
	 * @param text
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.AccountEntity> findByText(
	    java.lang.String text)
	
	{
		return findByText((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, text);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.AccountEntity> findByText(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String text)
	
	{
		return findByText("from com.soffid.iam.base.model.AccountEntity where :text is null",
			criteria, text);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.AccountEntity> findByText(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String text)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("text", text, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.base.model.AccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAcountNames
	 * @param systemName
	 * @return
	**/
	public java.util.List<java.lang.String> findAcountNames(
	    java.lang.String systemName)
	
	{
		return findAcountNames((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, systemName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<java.lang.String> findAcountNames(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	
	{
		return findAcountNames("select account.name from com.soffid.iam.base.model.AccountEntity as account where account.system.name=:systemName and account.system.tenant.id = :tenantId and (account.deleted is null or account.deleted is false) ",
			criteria, systemName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<java.lang.String> findAcountNames(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	
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
			return (java.util.List<java.lang.String>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUser
	 * @param userId
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUser(
	    java.lang.Long userId)
	
	{
		return findByUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long userId)
	
	{
		return findByUser("select acc\nfrom   com.soffid.iam.base.model.AccountEntity acc\nleft join     acc.users as users\nleft join     users.user as user\nwhere acc.type='U' and user.id = :userId  and (acc.deleted is null or acc.deleted is false) order by acc.name",
			criteria, userId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long userId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userId", userId, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.AccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUserAndDomain
	 * @param user
	 * @param domain
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUserAndDomain(
	    java.lang.String user, 
	    java.lang.String domain)
	
	{
		return findByUserAndDomain((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, user, domain);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUserAndDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.lang.String domain)
	
	{
		return findByUserAndDomain("select acc\nfrom com.soffid.iam.base.model.AccountEntity acc\njoin acc.users as users join users.user as user where user.userName=:user and user.tenant.id=:tenantId and  acc.system.passwordDomain.name=:domain and acc.type='U' and  (acc.deleted is null or acc.deleted is false) ",
			criteria, user, domain);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUserAndDomain(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.lang.String domain)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domain", domain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.AccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUserAndSystem
	 * @param user
	 * @param dispatcher
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUserAndSystem(
	    java.lang.String user, 
	    java.lang.String dispatcher)
	
	{
		return findByUserAndSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, user, dispatcher);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUserAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.lang.String dispatcher)
	
	{
		return findByUserAndSystem("select acc\nfrom   com.soffid.iam.base.model.AccountEntity acc\nleft join     acc.users as users\nleft join     users.user as user\nleft join     acc.system as dispatcher\nwhere acc.type='U' and user.userName = :user and dispatcher.name = :dispatcher and dispatcher.tenant.id = :tenantId and (acc.deleted is null or acc.deleted is false) order by user.userName, acc.name",
			criteria, user, dispatcher);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUserAndSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.lang.String dispatcher)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("dispatcher", dispatcher, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.AccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findSharedAccounts
	 * @param name
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findSharedAccounts(
	    java.lang.String name, 
	    java.lang.String system)
	
	{
		return findSharedAccounts((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findSharedAccounts(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String system)
	
	{
		return findSharedAccounts("select acc from com.soffid.iam.base.model.AccountEntity acc where acc.name = :name and acc.system.name = :system and acc.system.tenant.id = :tenantId and (acc.deleted is null or acc.deleted is false) order by acc.name",
			criteria, name, system);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findSharedAccounts(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.AccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.base.model.AccountEntity#	 * @see com.soffid.iam.base.model.AccountEntity#void propagateChanges(com.soffid.iam.base.model.AccountEntity account)
	 */
	public void propagateChanges(
		com.soffid.iam.base.model.AccountEntity account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.AccountEntity.propagateChanges(com.soffid.iam.base.model.AccountEntity account) - account cannot be null");
		}
		try
		{
			handlePropagateChanges(account);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.AccountEntity.class).
				warn ("Error on AccountEntity.propagateChanges", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on AccountEntity.propagateChanges: "+th.toString(), th);
		}
	}

	protected abstract void handlePropagateChanges(com.soffid.iam.base.model.AccountEntity account) throws Exception;

	/**
	 * @see com.soffid.iam.base.model.AccountEntity#	 * @see com.soffid.iam.base.model.AccountEntity#void refresh(com.soffid.iam.base.model.AccountEntity entity)
	 */
	public void refresh(
		com.soffid.iam.base.model.AccountEntity entity)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (entity == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.AccountEntity.refresh(com.soffid.iam.base.model.AccountEntity entity) - entity cannot be null");
		}
		try
		{
			handleRefresh(entity);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.AccountEntity.class).
				warn ("Error on AccountEntity.refresh", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on AccountEntity.refresh: "+th.toString(), th);
		}
	}

	protected abstract void handleRefresh(com.soffid.iam.base.model.AccountEntity entity) throws Exception;

	/**
	 * @see com.soffid.iam.base.model.AccountEntity#	 * @see com.soffid.iam.base.model.AccountEntity#void removeFromCache(com.soffid.iam.base.model.AccountEntity entity)
	 */
	public void removeFromCache(
		com.soffid.iam.base.model.AccountEntity entity)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (entity == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.AccountEntity.removeFromCache(com.soffid.iam.base.model.AccountEntity entity) - entity cannot be null");
		}
		try
		{
			handleRemoveFromCache(entity);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.AccountEntity.class).
				warn ("Error on AccountEntity.removeFromCache", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on AccountEntity.removeFromCache: "+th.toString(), th);
		}
	}

	protected abstract void handleRemoveFromCache(com.soffid.iam.base.model.AccountEntity entity) throws Exception;

	/**
	 * @see com.soffid.iam.base.model.AccountEntity#	 * @see com.soffid.iam.base.model.AccountEntity#void update(com.soffid.iam.base.model.AccountEntity entity, java.lang.String auditType)
	 */
	public void update(
		com.soffid.iam.base.model.AccountEntity entity, 
		java.lang.String auditType)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (entity == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.AccountEntity.update(com.soffid.iam.base.model.AccountEntity entity, java.lang.String auditType) - entity cannot be null");
		}
		try
		{
			handleUpdate(entity, auditType);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.AccountEntity.class).
				warn ("Error on AccountEntity.update", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on AccountEntity.update: "+th.toString(), th);
		}
	}

	protected abstract void handleUpdate(com.soffid.iam.base.model.AccountEntity entity, java.lang.String auditType) throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.Account} object 
	 */
	public void toAccount(com.soffid.iam.base.model.AccountEntity source, com.soffid.iam.base.api.Account target) {
		// Attributes for Account
		target.setId(source.getId());
		// Incompatible types source.system and target.system
		target.setName(source.getName());
		target.setKey(source.getKey());
		target.setOldName(source.getOldName());
		target.setLoginName(source.getLoginName());
		target.setDescription(source.getDescription());
		target.setType(source.getType());
		target.setDisabled(source.isDisabled());
		target.setStatus(source.getStatus());
		target.setCredentialType(source.getCredentialType());
		// Incompatible types source.passwordPolicy and target.passwordPolicy
		// Missing attribute ownerGroups on entity
		// Missing attribute ownerUsers on entity
		// Missing attribute ownerRoles on entity
		// Missing attribute managerGroups on entity
		// Missing attribute managerUsers on entity
		// Missing attribute managerRoles on entity
		// Missing attribute grantedGroups on entity
		// Missing attribute grantedUsers on entity
		// Missing attribute grantedRoles on entity
		// Missing attribute accessLevel on entity
		target.setServerType(source.getServerType());
		target.setServerName(source.getServerName());
		target.setSshPublicKey(source.getSshPublicKey());
		// Missing attribute vaultFolderId on entity
		// Missing attribute vaultFolder on entity
		target.setInheritNewPermissions(java.lang.Boolean.TRUE.equals(source.getInheritNewPermissions()));
		target.setLoginUrl(source.getLoginUrl());
		target.setLaunchType(source.getLaunchType());
		// Incompatible types source.jumpServerGroup and target.jumpServerGroup
		target.setExternalId(source.getExternalId());
		if (source.getLastLogin() == null) {
			target.setLastLogin(null);
		} else {
			target.setLastLogin(java.util.Calendar.getInstance());
			target.getLastLogin().setTime(source.getLastLogin());
		}
		if (source.getLastUpdated() == null) {
			target.setLastUpdated(null);
		} else {
			target.setLastUpdated(java.util.Calendar.getInstance());
			target.getLastUpdated().setTime(source.getLastUpdated());
		}
		if (source.getLastPasswordSet() == null) {
			target.setLastPasswordSet(null);
		} else {
			target.setLastPasswordSet(java.util.Calendar.getInstance());
			target.getLastPasswordSet().setTime(source.getLastPasswordSet());
		}
		if (source.getPasswordExpiration() == null) {
			target.setPasswordExpiration(null);
		} else {
			target.setPasswordExpiration(java.util.Calendar.getInstance());
			target.getPasswordExpiration().setTime(source.getPasswordExpiration());
		}
		// Missing attribute lockedBy on entity
		// Incompatible types source.passwordStatus and target.passwordStatus
		target.setCreated(source.getCreated());
		target.setLastChange(source.getLastChange());
		// Incompatible types source.attributes and target.attributes
		// Missing attribute hasSnapshot on entity
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
		target.setDeleted(source.getDeleted());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Account} object 
	 */
	public com.soffid.iam.base.api.Account toAccount(com.soffid.iam.base.model.AccountEntity entity) {
		final com.soffid.iam.base.api.Account target = new com.soffid.iam.base.api.Account();
		this.toAccount(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Account} list 
	 */
	public java.util.List<com.soffid.iam.base.api.Account> toAccountList (java.util.Collection<com.soffid.iam.base.model.AccountEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.Account> list =
				new java.util.LinkedList<com.soffid.iam.base.api.Account>();
			for (final com.soffid.iam.base.model.AccountEntity instance: instances)
			{
				list.add( toAccount(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.Account} object 
	 */
	public void accountToEntity (com.soffid.iam.base.api.Account source, com.soffid.iam.base.model.AccountEntity target, boolean copyIfNull) {
		// Attributes for AccountEntity
		// Missing attribute roles on entity
		// Missing attribute users on entity
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getKey() != null)
		{
			target.setKey(source.getKey());
		}
		if (copyIfNull || source.getSystem() != null)
		{
			// Incompatible types source.system and target.system
		}
		if (copyIfNull || source.getOldName() != null)
		{
			target.setOldName(source.getOldName());
		}
		// Missing attribute acl on entity
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getCreated() != null)
		{
			target.setCreated(source.getCreated());
		}
		if (copyIfNull || source.getLastChange() != null)
		{
			target.setLastChange(source.getLastChange());
		}
		if (copyIfNull || source.getLastUpdated() != null)
		{
			if (source.getLastUpdated() == null) {
				target.setLastUpdated(null);
			} else {
				target.setLastUpdated(source.getLastUpdated().getTime());
			}
		}
		if (copyIfNull || source.getLastPasswordSet() != null)
		{
			if (source.getLastPasswordSet() == null) {
				target.setLastPasswordSet(null);
			} else {
				target.setLastPasswordSet(source.getLastPasswordSet().getTime());
			}
		}
		if (copyIfNull || source.getPasswordExpiration() != null)
		{
			if (source.getPasswordExpiration() == null) {
				target.setPasswordExpiration(null);
			} else {
				target.setPasswordExpiration(source.getPasswordExpiration().getTime());
			}
		}
		if (copyIfNull || source.getLastLogin() != null)
		{
			if (source.getLastLogin() == null) {
				target.setLastLogin(null);
			} else {
				target.setLastLogin(source.getLastLogin().getTime());
			}
		}
		// Missing attribute secrets on entity
		if (copyIfNull || source.getSshPublicKey() != null)
		{
			target.setSshPublicKey(source.getSshPublicKey());
		}
		target.setDisabled(source.isDisabled());
		if (copyIfNull || source.getStatus() != null)
		{
			target.setStatus(source.getStatus());
		}
		// Missing attribute folder on entity
		target.setInheritNewPermissions(new java.lang.Boolean(source.isInheritNewPermissions()));
		if (copyIfNull || source.getLoginUrl() != null)
		{
			target.setLoginUrl(source.getLoginUrl());
		}
		if (copyIfNull || source.getLoginName() != null)
		{
			target.setLoginName(source.getLoginName());
		}
		if (copyIfNull || source.getLaunchType() != null)
		{
			target.setLaunchType(source.getLaunchType());
		}
		if (copyIfNull || source.getServerType() != null)
		{
			target.setServerType(source.getServerType());
		}
		if (copyIfNull || source.getServerName() != null)
		{
			target.setServerName(source.getServerName());
		}
		if (copyIfNull || source.getJumpServerGroup() != null)
		{
			// Incompatible types source.jumpServerGroup and target.jumpServerGroup
		}
		// Missing attribute passwords on entity
		if (copyIfNull || source.getPasswordPolicy() != null)
		{
			// Incompatible types source.passwordPolicy and target.passwordPolicy
		}
		if (copyIfNull || source.getPasswordStatus() != null)
		{
			// Incompatible types source.passwordStatus and target.passwordStatus
		}
		// Missing attribute snapshot on entity
		if (copyIfNull || source.getCredentialType() != null)
		{
			target.setCredentialType(source.getCredentialType());
		}
		if (copyIfNull || source.getExternalId() != null)
		{
			target.setExternalId(source.getExternalId());
		}
		if (copyIfNull || source.getAttributes() != null)
		{
			// Incompatible types source.attributes and target.attributes
		}
		// Missing attribute services on entity
		// Missing attribute networkDiscovery on entity
		// Missing attribute events on entity
		if (copyIfNull || source.getCreatedBy() != null)
		{
			target.setCreatedBy(source.getCreatedBy());
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
		if (copyIfNull || source.getDeleted() != null)
		{
			target.setDeleted(source.getDeleted());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Account} object 
	 */
	public com.soffid.iam.base.model.AccountEntity accountToEntity (com.soffid.iam.base.api.Account instance) {
		com.soffid.iam.base.model.AccountEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newAccountEntity();
		accountToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Account} list 
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity>  accountToEntityList (java.util.Collection<com.soffid.iam.base.api.Account> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.AccountEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.AccountEntity>();
		for (com.soffid.iam.base.api.Account instance: instances)
		{
			list.add (accountToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.AccountEntity} .
	 */
	public com.soffid.iam.base.model.AccountEntity newAccountEntity()
	{
		return new com.soffid.iam.base.model.AccountEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.AccountEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.AccountEntity result = (com.soffid.iam.base.model.AccountEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.AccountEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.base.model.AccountEntity where system.tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.base.model.AccountEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.AccountEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.AccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountEntityDao.create - 'entity' can not be null");
		}

		entity.setCreated(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setLastChange(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.AccountEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.AccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountEntityDao.update - 'entity' can not be null");
		}
		entity.setLastChange(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.AccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.AccountEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.AccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AccountEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.AccountEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.AccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AccountEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.AccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AccountEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AccountEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.AccountEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.AccountEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.AccountEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
