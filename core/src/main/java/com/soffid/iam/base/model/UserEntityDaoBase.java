//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity UserEntity
 */
public abstract class UserEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.UserEntityDao
{
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

	com.soffid.iam.iga.model.AuthoritativeChangeEntityDao authoritativeChangeEntityDao;

	/**
	 * Sets reference to <code>authoritativeChangeEntityDao</code>.
	 */
	public void setAuthoritativeChangeEntityDao (com.soffid.iam.iga.model.AuthoritativeChangeEntityDao authoritativeChangeEntityDao) {
		this.authoritativeChangeEntityDao = authoritativeChangeEntityDao;
	}

	/**
	 * Gets reference to <code>authoritativeChangeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.AuthoritativeChangeEntityDao getAuthoritativeChangeEntityDao () {
		return authoritativeChangeEntityDao;
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

	com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao;

	/**
	 * Sets reference to <code>metaDataEntityDao</code>.
	 */
	public void setMetaDataEntityDao (com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao) {
		this.metaDataEntityDao = metaDataEntityDao;
	}

	/**
	 * Gets reference to <code>metaDataEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MetaDataEntityDao getMetaDataEntityDao () {
		return metaDataEntityDao;
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

	com.soffid.iam.iga.model.NoticeEntityDao noticeEntityDao;

	/**
	 * Sets reference to <code>noticeEntityDao</code>.
	 */
	public void setNoticeEntityDao (com.soffid.iam.iga.model.NoticeEntityDao noticeEntityDao) {
		this.noticeEntityDao = noticeEntityDao;
	}

	/**
	 * Gets reference to <code>noticeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.NoticeEntityDao getNoticeEntityDao () {
		return noticeEntityDao;
	}

	com.soffid.iam.am.model.PasswordEntityDao passwordEntityDao;

	/**
	 * Sets reference to <code>passwordEntityDao</code>.
	 */
	public void setPasswordEntityDao (com.soffid.iam.am.model.PasswordEntityDao passwordEntityDao) {
		this.passwordEntityDao = passwordEntityDao;
	}

	/**
	 * Gets reference to <code>passwordEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PasswordEntityDao getPasswordEntityDao () {
		return passwordEntityDao;
	}

	com.soffid.iam.am.model.PasswordManagerTokenEntityDao passwordManagerTokenEntityDao;

	/**
	 * Sets reference to <code>passwordManagerTokenEntityDao</code>.
	 */
	public void setPasswordManagerTokenEntityDao (com.soffid.iam.am.model.PasswordManagerTokenEntityDao passwordManagerTokenEntityDao) {
		this.passwordManagerTokenEntityDao = passwordManagerTokenEntityDao;
	}

	/**
	 * Gets reference to <code>passwordManagerTokenEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PasswordManagerTokenEntityDao getPasswordManagerTokenEntityDao () {
		return passwordManagerTokenEntityDao;
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

	com.soffid.iam.iga.model.RoleGroupEntityDao roleGroupEntityDao;

	/**
	 * Sets reference to <code>roleGroupEntityDao</code>.
	 */
	public void setRoleGroupEntityDao (com.soffid.iam.iga.model.RoleGroupEntityDao roleGroupEntityDao) {
		this.roleGroupEntityDao = roleGroupEntityDao;
	}

	/**
	 * Gets reference to <code>roleGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleGroupEntityDao getRoleGroupEntityDao () {
		return roleGroupEntityDao;
	}

	com.soffid.iam.am.model.SecretEntityDao secretEntityDao;

	/**
	 * Sets reference to <code>secretEntityDao</code>.
	 */
	public void setSecretEntityDao (com.soffid.iam.am.model.SecretEntityDao secretEntityDao) {
		this.secretEntityDao = secretEntityDao;
	}

	/**
	 * Gets reference to <code>secretEntityDao</code>.
	 */
	public com.soffid.iam.am.model.SecretEntityDao getSecretEntityDao () {
		return secretEntityDao;
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

	com.soffid.iam.base.model.UserDataEntityDao userDataEntityDao;

	/**
	 * Sets reference to <code>userDataEntityDao</code>.
	 */
	public void setUserDataEntityDao (com.soffid.iam.base.model.UserDataEntityDao userDataEntityDao) {
		this.userDataEntityDao = userDataEntityDao;
	}

	/**
	 * Gets reference to <code>userDataEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserDataEntityDao getUserDataEntityDao () {
		return userDataEntityDao;
	}

	com.soffid.iam.iga.model.UserDomainEntityDao userDomainEntityDao;

	/**
	 * Sets reference to <code>userDomainEntityDao</code>.
	 */
	public void setUserDomainEntityDao (com.soffid.iam.iga.model.UserDomainEntityDao userDomainEntityDao) {
		this.userDomainEntityDao = userDomainEntityDao;
	}

	/**
	 * Gets reference to <code>userDomainEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserDomainEntityDao getUserDomainEntityDao () {
		return userDomainEntityDao;
	}

	com.soffid.iam.iga.model.UserGroupEntityDao userGroupEntityDao;

	/**
	 * Sets reference to <code>userGroupEntityDao</code>.
	 */
	public void setUserGroupEntityDao (com.soffid.iam.iga.model.UserGroupEntityDao userGroupEntityDao) {
		this.userGroupEntityDao = userGroupEntityDao;
	}

	/**
	 * Gets reference to <code>userGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserGroupEntityDao getUserGroupEntityDao () {
		return userGroupEntityDao;
	}

	com.soffid.iam.iga.model.UserMailEntityDao userMailEntityDao;

	/**
	 * Sets reference to <code>userMailEntityDao</code>.
	 */
	public void setUserMailEntityDao (com.soffid.iam.iga.model.UserMailEntityDao userMailEntityDao) {
		this.userMailEntityDao = userMailEntityDao;
	}

	/**
	 * Gets reference to <code>userMailEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserMailEntityDao getUserMailEntityDao () {
		return userMailEntityDao;
	}

	com.soffid.iam.iga.model.UserPrinterEntityDao userPrinterEntityDao;

	/**
	 * Sets reference to <code>userPrinterEntityDao</code>.
	 */
	public void setUserPrinterEntityDao (com.soffid.iam.iga.model.UserPrinterEntityDao userPrinterEntityDao) {
		this.userPrinterEntityDao = userPrinterEntityDao;
	}

	/**
	 * Gets reference to <code>userPrinterEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserPrinterEntityDao getUserPrinterEntityDao () {
		return userPrinterEntityDao;
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

	com.soffid.iam.am.model.VaultFolderAccessEntityDao vaultFolderAccessEntityDao;

	/**
	 * Sets reference to <code>vaultFolderAccessEntityDao</code>.
	 */
	public void setVaultFolderAccessEntityDao (com.soffid.iam.am.model.VaultFolderAccessEntityDao vaultFolderAccessEntityDao) {
		this.vaultFolderAccessEntityDao = vaultFolderAccessEntityDao;
	}

	/**
	 * Gets reference to <code>vaultFolderAccessEntityDao</code>.
	 */
	public com.soffid.iam.am.model.VaultFolderAccessEntityDao getVaultFolderAccessEntityDao () {
		return vaultFolderAccessEntityDao;
	}

	com.soffid.iam.base.model.UserPreferenceEntityDao userPreferenceEntityDao;

	/**
	 * Sets reference to <code>userPreferenceEntityDao</code>.
	 */
	public void setUserPreferenceEntityDao (com.soffid.iam.base.model.UserPreferenceEntityDao userPreferenceEntityDao) {
		this.userPreferenceEntityDao = userPreferenceEntityDao;
	}

	/**
	 * Gets reference to <code>userPreferenceEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserPreferenceEntityDao getUserPreferenceEntityDao () {
		return userPreferenceEntityDao;
	}

	com.soffid.iam.rc.model.IssueUserEntityDao issueUserEntityDao;

	/**
	 * Sets reference to <code>issueUserEntityDao</code>.
	 */
	public void setIssueUserEntityDao (com.soffid.iam.rc.model.IssueUserEntityDao issueUserEntityDao) {
		this.issueUserEntityDao = issueUserEntityDao;
	}

	/**
	 * Gets reference to <code>issueUserEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueUserEntityDao getIssueUserEntityDao () {
		return issueUserEntityDao;
	}


	protected org.apache.commons.collections.map.LRUMap mapUser = new org.apache.commons.collections.map.LRUMap(300);
	protected int mapUserTimeout = 5000;
	/**
	 * Operation getPasswordsStatus
	 * @param usuariEntity
	 * @param dominiContrasenyes
	 * @return
	**/
	public com.soffid.iam.am.api.PasswordStatus getPasswordsStatus(
	    com.soffid.iam.base.model.UserEntity usuariEntity, 
	    com.soffid.iam.am.model.PasswordDomainEntity dominiContrasenyes)
	
	{
		return getPasswordsStatus((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, usuariEntity, dominiContrasenyes);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.api.PasswordStatus getPasswordsStatus(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.model.UserEntity usuariEntity, com.soffid.iam.am.model.PasswordDomainEntity dominiContrasenyes)
	
	{
		return getPasswordsStatus("- CUSTOM -",
			criteria, usuariEntity, dominiContrasenyes);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.api.PasswordStatus getPasswordsStatus(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.model.UserEntity usuariEntity, com.soffid.iam.am.model.PasswordDomainEntity dominiContrasenyes)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("usuariEntity", usuariEntity);
			queryObject.setParameter("dominiContrasenyes", dominiContrasenyes);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.api.PasswordStatus result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.api.PasswordStatus' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.api.PasswordStatus) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.base.model.UserEntity#	 * @see com.soffid.iam.base.model.UserEntity#com.soffid.iam.base.api.User toUser(com.soffid.iam.base.model.UserEntity entity, java.lang.String[] attributes)
	 */
	public com.soffid.iam.base.api.User toUser(
		com.soffid.iam.base.model.UserEntity entity, 
		java.lang.String[] attributes)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (entity == null) {
			throw new IllegalArgumentException("com.soffid.iam.base.api.User com.soffid.iam.base.model.UserEntity.toUser(com.soffid.iam.base.model.UserEntity entity, java.lang.String[] attributes) - entity cannot be null");
		}
		try
		{
			return handleToUser(entity, attributes);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.UserEntity.class).
				warn ("Error on UserEntity.toUser", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on UserEntity.toUser: "+th.toString(), th);
		}
	}

	protected abstract com.soffid.iam.base.api.User handleToUser(com.soffid.iam.base.model.UserEntity entity, java.lang.String[] attributes) throws Exception;

	/**
	 * Operation findByAccount
	 * @param account
	 * @param system
	 * @return
	**/
	public com.soffid.iam.base.model.UserEntity findByAccount(
	    java.lang.String account, 
	    java.lang.String system)
	
	{
		return findByAccount((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, account, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.UserEntity findByAccount(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String account, java.lang.String system)
	
	{
		return findByAccount("select ue\nfrom com.soffid.iam.base.model.UserEntity ue\njoin ue.accounts as accounts\njoin accounts.account as acc\njoin acc.system as dispatcher\nwhere acc.type='U' and dispatcher.name=:system and acc.name=:account and dispatcher.tenant.id = :tenantId and (ue.deleted is null or ue.deleted is false)",
			criteria, account, system);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.UserEntity findByAccount(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String account, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("account", account, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.UserEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.UserEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.UserEntity) results.iterator().next();
			}
			return result;
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
	public com.soffid.iam.base.model.UserEntity findByUserName(
	    java.lang.String userName)
	
	{
		return findByUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.UserEntity findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findByUserName("from com.soffid.iam.base.model.UserEntity  where userName = :userName and tenant.id = :tenantId and (deleted is null or deleted is false)",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.UserEntity findByUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
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
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.UserEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.UserEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.UserEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findById
	 * @param id
	 * @return
	**/
	public com.soffid.iam.base.model.UserEntity findById(
	    java.lang.Long id)
	
	{
		return findById((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, id);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.UserEntity findById(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		return findById("from com.soffid.iam.base.model.UserEntity where id = :id",
			criteria, id);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.UserEntity findById(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("id", id, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.UserEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.UserEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.UserEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByNationalID
	 * @param nif
	 * @return
	**/
	public com.soffid.iam.base.model.UserEntity findByNationalID(
	    java.lang.String nif)
	
	{
		return findByNationalID((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nif);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.UserEntity findByNationalID(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nif)
	
	{
		return findByNationalID("select usuari from com.soffid.iam.base.model.UserEntity usuari join usuari.attributes as dadaUsuari where dadaUsuari.dataType.name = 'NIF' and dadaUsuari.value = :nif and usuari.tenant.id = :tenantId and (usuari.deleted is null or usuari.deleted is false)",
			criteria, nif);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.UserEntity findByNationalID(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nif)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nif", nif, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.UserEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.UserEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.UserEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUserNameDeleted
	 * @param userName
	 * @return
	**/
	public com.soffid.iam.base.model.UserEntity findByUserNameDeleted(
	    java.lang.String userName)
	
	{
		return findByUserNameDeleted((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.UserEntity findByUserNameDeleted(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findByUserNameDeleted("from com.soffid.iam.base.model.UserEntity  where userName = :userName and tenant.id = :tenantId",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.UserEntity findByUserNameDeleted(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
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
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.UserEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.UserEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.UserEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findUserByDataValue
	 * @param dataType
	 * @param value
	 * @return
	**/
	public com.soffid.iam.base.model.UserEntity findUserByDataValue(
	    java.lang.String dataType, 
	    java.lang.String value)
	
	{
		return findUserByDataValue((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, dataType, value);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.UserEntity findUserByDataValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String dataType, java.lang.String value)
	
	{
		return findUserByDataValue("SELECT distinct usu \nFROM com.soffid.iam.base.model.UserEntity usu join usu.attributes as dada \nWHERE \n   dada.dataType.name = :dataType  and \n   dada.value = :value and    usu.tenant.id = :tenantId and    (usu.deleted is not false or usu.deleted is null)",
			criteria, dataType, value);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.UserEntity findUserByDataValue(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String dataType, java.lang.String value)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("dataType", dataType, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("value", value, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.UserEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.UserEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.UserEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation countByGrupPrimari
	 * @param primaryGroupName
	 * @return
	**/
	public java.lang.Number countByGrupPrimari(
	    java.lang.String primaryGroupName)
	
	{
		return countByGrupPrimari((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, primaryGroupName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Number countByGrupPrimari(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String primaryGroupName)
	
	{
		return countByGrupPrimari("select count(usuari.id) from com.soffid.iam.base.model.UserEntity as usuari join usuari.primaryGroup as grup where grup.name=:primaryGroupName and usuari.tenant.id = :tenantId and (usuari.deleted is null or usuari.deleted is false)",
			criteria, primaryGroupName);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Number countByGrupPrimari(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String primaryGroupName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("primaryGroupName", primaryGroupName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.lang.Number result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.Number' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.Number) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation generateUserName
	 * @return
	**/
	public java.lang.String generateUserName(
)
	
	{
		return generateUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.String generateUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return generateUserName("from com.soffid.iam.base.model.UserEntity where tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.String generateUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
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
	 * Operation getNextUserName
	 * @return
	**/
	public java.lang.String getNextUserName(
)
	
	{
		return getNextUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.String getNextUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return getNextUserName("from com.soffid.iam.base.model.UserEntity where tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.String getNextUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
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
	 * Operation getNextAnonimUser
	 * @return
	**/
	public java.lang.String getNextAnonimUser(
)
	
	{
		return getNextAnonimUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.String getNextAnonimUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return getNextAnonimUser("from com.soffid.iam.base.model.UserEntity where tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.String getNextAnonimUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
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
	 * Operation getNextUserIDRequest
	 * @return
	**/
	public java.lang.String getNextUserIDRequest(
)
	
	{
		return getNextUserIDRequest((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.String getNextUserIDRequest(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return getNextUserIDRequest("- CUSTOM -",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.String getNextUserIDRequest(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
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
	 * @see com.soffid.iam.base.model.UserEntity#	 * @see com.soffid.iam.base.model.UserEntity#java.lang.String refreshCanvis(java.lang.String codiUsuari)
	 */
	public java.lang.String refreshCanvis(
		java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.base.model.UserEntity.refreshCanvis(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		try
		{
			return handleRefreshCanvis(codiUsuari);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.UserEntity.class).
				warn ("Error on UserEntity.refreshCanvis", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on UserEntity.refreshCanvis: "+th.toString(), th);
		}
	}

	protected abstract java.lang.String handleRefreshCanvis(java.lang.String codiUsuari) throws Exception;

	/**
	 * Operation getTasks
	 * @param codiUsuari
	 * @return
	**/
	public java.lang.String[] getTasks(
	    java.lang.String codiUsuari)
	
	{
		return getTasks((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, codiUsuari);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.String[] getTasks(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String codiUsuari)
	
	{
		return getTasks("- CUSTOM -",
			criteria, codiUsuari);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.String[] getTasks(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String codiUsuari)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("codiUsuari", codiUsuari, org.hibernate.Hibernate.STRING);
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
	 * Operation findByShortNameAndDomain
	 * @param shortName
	 * @param mailDomainId
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.UserEntity> findByShortNameAndDomain(
	    java.lang.String shortName, 
	    java.lang.Long mailDomainId)
	
	{
		return findByShortNameAndDomain((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, shortName, mailDomainId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserEntity> findByShortNameAndDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String shortName, java.lang.Long mailDomainId)
	
	{
		return findByShortNameAndDomain("select ue from com.soffid.iam.base.model.UserEntity ue where ue.tenant.id = :tenantId and ue.shortName = :shortName and ue.mailDomain.id = :mailDomainId",
			criteria, shortName, mailDomainId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserEntity> findByShortNameAndDomain(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String shortName, java.lang.Long mailDomainId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("shortName", shortName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("mailDomainId", mailDomainId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.base.model.UserEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByPrimaryGroup
	 * @param primaryGroupName
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.UserEntity> findByPrimaryGroup(
	    java.lang.String primaryGroupName)
	
	{
		return findByPrimaryGroup((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, primaryGroupName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> findByPrimaryGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String primaryGroupName)
	
	{
		return findByPrimaryGroup("select usuari from com.soffid.iam.base.model.UserEntity as usuari join usuari.primaryGroup as grup where grup.name=:primaryGroupName and usuari.tenant.id = :tenantId and (usuari.deleted is null or usuari.deleted is false)",
			criteria, primaryGroupName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> findByPrimaryGroup(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String primaryGroupName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("primaryGroupName", primaryGroupName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.UserEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findUserNames
	 * @return
	**/
	public java.util.List<java.lang.String> findUserNames(
)
	
	{
		return findUserNames((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<java.lang.String> findUserNames(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findUserNames("select u.userName from com.soffid.iam.base.model.UserEntity as u where u.active='S' and (u.deleted is false or u.deleted is null)",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<java.lang.String> findUserNames(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
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
	 * Operation findUsersByNationalID
	 * @param nif
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.UserEntity> findUsersByNationalID(
	    java.lang.String nif)
	
	{
		return findUsersByNationalID((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nif);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> findUsersByNationalID(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nif)
	
	{
		return findUsersByNationalID("select usuari from com.soffid.iam.base.model.UserEntity usuari join usuari.attributes as dadaUsuari where dadaUsuari.dataType.name = 'NIF' and dadaUsuari.value = :nif and usuari.tenant.id = :tenantId and (usuari.deleted is null or usuari.deleted is false)",
			criteria, nif);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> findUsersByNationalID(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nif)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nif", nif, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.UserEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findUsersGroupAndSubgroupsByGroupCode
	 * @param codiGrup
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.UserEntity> findUsersGroupAndSubgroupsByGroupCode(
	    java.lang.String codiGrup)
	
	{
		return findUsersGroupAndSubgroupsByGroupCode((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, codiGrup);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> findUsersGroupAndSubgroupsByGroupCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String codiGrup)
	
	{
		return findUsersGroupAndSubgroupsByGroupCode("- CUSTOM -",
			criteria, codiGrup);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> findUsersGroupAndSubgroupsByGroupCode(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String codiGrup)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("codiGrup", codiGrup, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.UserEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.base.model.UserEntity#	 * @see com.soffid.iam.base.model.UserEntity#void merge(java.lang.Long src, java.lang.Long target)
	 */
	public void merge(
		java.lang.Long src, 
		java.lang.Long target)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (src == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.UserEntity.merge(java.lang.Long src, java.lang.Long target) - src cannot be null");
		}
		if (target == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.UserEntity.merge(java.lang.Long src, java.lang.Long target) - target cannot be null");
		}
		try
		{
			handleMerge(src, target);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.UserEntity.class).
				warn ("Error on UserEntity.merge", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on UserEntity.merge: "+th.toString(), th);
		}
	}

	protected abstract void handleMerge(java.lang.Long src, java.lang.Long target) throws Exception;

	/**
	 * @see com.soffid.iam.base.model.UserEntity#	 * @see com.soffid.iam.base.model.UserEntity#void createUpdateTasks(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.base.api.User oldValue)
	 */
	public void createUpdateTasks(
		com.soffid.iam.base.model.UserEntity user, 
		com.soffid.iam.base.api.User oldValue)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.UserEntity.createUpdateTasks(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.base.api.User oldValue) - user cannot be null");
		}
		if (oldValue == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.UserEntity.createUpdateTasks(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.base.api.User oldValue) - oldValue cannot be null");
		}
		if (oldValue.getUserName() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.UserEntity.createUpdateTasks(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.base.api.User oldValue) - oldValue.userName cannot be null");
		}
		if (oldValue.getFirstName() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.UserEntity.createUpdateTasks(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.base.api.User oldValue) - oldValue.firstName cannot be null");
		}
		if (oldValue.getLastName() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.UserEntity.createUpdateTasks(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.base.api.User oldValue) - oldValue.lastName cannot be null");
		}
		if (oldValue.getUserType() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.UserEntity.createUpdateTasks(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.base.api.User oldValue) - oldValue.userType cannot be null");
		}
		if (oldValue.getPrimaryGroup() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.UserEntity.createUpdateTasks(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.base.api.User oldValue) - oldValue.primaryGroup cannot be null");
		}
		try
		{
			handleCreateUpdateTasks(user, oldValue);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.UserEntity.class).
				warn ("Error on UserEntity.createUpdateTasks", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on UserEntity.createUpdateTasks: "+th.toString(), th);
		}
	}

	protected abstract void handleCreateUpdateTasks(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.base.api.User oldValue) throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.bpm.api.BPMUser} object 
	 */
	public void toBPMUser(com.soffid.iam.base.model.UserEntity source, com.soffid.iam.bpm.api.BPMUser target) {
		// Attributes for BPMUser
		target.setUserName(source.getUserName());
		// Missing attribute givenName on entity
		// Missing attribute surName on entity
		// Missing attribute group on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.bpm.api.BPMUser} object 
	 */
	public com.soffid.iam.bpm.api.BPMUser toBPMUser(com.soffid.iam.base.model.UserEntity entity) {
		final com.soffid.iam.bpm.api.BPMUser target = new com.soffid.iam.bpm.api.BPMUser();
		this.toBPMUser(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.bpm.api.BPMUser} list 
	 */
	public java.util.List<com.soffid.iam.bpm.api.BPMUser> toBPMUserList (java.util.Collection<com.soffid.iam.base.model.UserEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.bpm.api.BPMUser> list =
				new java.util.LinkedList<com.soffid.iam.bpm.api.BPMUser>();
			for (final com.soffid.iam.base.model.UserEntity instance: instances)
			{
				list.add( toBPMUser(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.bpm.api.BPMUser} object 
	 */
	public void bPMUserToEntity (com.soffid.iam.bpm.api.BPMUser source, com.soffid.iam.base.model.UserEntity target, boolean copyIfNull) {
		// Attributes for UserEntity
		if (copyIfNull || source.getUserName() != null)
		{
			target.setUserName(source.getUserName());
		}
		// Missing attribute firstName on entity
		// Missing attribute lastName on entity
		// Missing attribute middleName on entity
		// Missing attribute fullName on entity
		// Missing attribute shortName on entity
		// Missing attribute emailAddress on entity
		// Missing attribute modifiedBy on entity
		// Missing attribute modifiedOn on entity
		// Missing attribute createdOn on entity
		// Missing attribute createdBy on entity
		// Missing attribute active on entity
		// Missing attribute comments on entity
		// Missing attribute ACNetwork on entity
		// Missing attribute mailServer on entity
		// Missing attribute homeServer on entity
		// Missing attribute mailDomain on entity
		// Missing attribute profileServer on entity
		// Missing attribute primaryGroup on entity
		// Missing attribute attributes on entity
		// Missing attribute secondaryGroups on entity
		// Missing attribute printers on entity
		// Missing attribute sessions on entity
		// Missing attribute userMailList on entity
		// Missing attribute multiSession on entity
		// Missing attribute tenant on entity
		// Missing attribute ApplicationResponsible on entity
		// Missing attribute accessHostAsAdministratorAuthorization on entity
		// Missing attribute userType on entity
		// Missing attribute secrets on entity
		// Missing attribute accounts on entity
		// Missing attribute accountAccess on entity
		// Missing attribute passwords on entity
		// Missing attribute pendingAuthoritativeChanges on entity
		// Missing attribute browsers on entity
		// Missing attribute passwordManagerToken on entity
		// Missing attribute vaultFolders on entity
		// Missing attribute preferences on entity
		// Missing attribute events on entity
		// Missing attribute deletedOn on entity
		// Missing attribute deletedBy on entity
		// Missing attribute deleted on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.bpm.api.BPMUser} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity>  bPMUserToEntityList (java.util.Collection<com.soffid.iam.bpm.api.BPMUser> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.UserEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.UserEntity>();
		for (com.soffid.iam.bpm.api.BPMUser instance: instances)
		{
			list.add (bPMUserToEntity(instance));
		}
		return list;
	}

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.Identity} object 
	 */
	public void toIdentity(com.soffid.iam.base.model.UserEntity source, com.soffid.iam.base.api.Identity target) {
		// Attributes for Identity
		// Missing attribute userCode on entity
		// Missing attribute groupCode on entity
		// Missing attribute roleName on entity
		// Missing attribute description on entity
		// Missing attribute identityCode on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Identity} object 
	 */
	public com.soffid.iam.base.api.Identity toIdentity(com.soffid.iam.base.model.UserEntity entity) {
		final com.soffid.iam.base.api.Identity target = new com.soffid.iam.base.api.Identity();
		this.toIdentity(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Identity} list 
	 */
	public java.util.List<com.soffid.iam.base.api.Identity> toIdentityList (java.util.Collection<com.soffid.iam.base.model.UserEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.Identity> list =
				new java.util.LinkedList<com.soffid.iam.base.api.Identity>();
			for (final com.soffid.iam.base.model.UserEntity instance: instances)
			{
				list.add( toIdentity(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.Identity} object 
	 */
	public void identityToEntity (com.soffid.iam.base.api.Identity source, com.soffid.iam.base.model.UserEntity target, boolean copyIfNull) {
		// Attributes for UserEntity
		// Missing attribute userName on entity
		// Missing attribute firstName on entity
		// Missing attribute lastName on entity
		// Missing attribute middleName on entity
		// Missing attribute fullName on entity
		// Missing attribute shortName on entity
		// Missing attribute emailAddress on entity
		// Missing attribute modifiedBy on entity
		// Missing attribute modifiedOn on entity
		// Missing attribute createdOn on entity
		// Missing attribute createdBy on entity
		// Missing attribute active on entity
		// Missing attribute comments on entity
		// Missing attribute ACNetwork on entity
		// Missing attribute mailServer on entity
		// Missing attribute homeServer on entity
		// Missing attribute mailDomain on entity
		// Missing attribute profileServer on entity
		// Missing attribute primaryGroup on entity
		// Missing attribute attributes on entity
		// Missing attribute secondaryGroups on entity
		// Missing attribute printers on entity
		// Missing attribute sessions on entity
		// Missing attribute userMailList on entity
		// Missing attribute multiSession on entity
		// Missing attribute tenant on entity
		// Missing attribute ApplicationResponsible on entity
		// Missing attribute accessHostAsAdministratorAuthorization on entity
		// Missing attribute userType on entity
		// Missing attribute secrets on entity
		// Missing attribute accounts on entity
		// Missing attribute accountAccess on entity
		// Missing attribute passwords on entity
		// Missing attribute pendingAuthoritativeChanges on entity
		// Missing attribute browsers on entity
		// Missing attribute passwordManagerToken on entity
		// Missing attribute vaultFolders on entity
		// Missing attribute preferences on entity
		// Missing attribute events on entity
		// Missing attribute deletedOn on entity
		// Missing attribute deletedBy on entity
		// Missing attribute deleted on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Identity} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity>  identityToEntityList (java.util.Collection<com.soffid.iam.base.api.Identity> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.UserEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.UserEntity>();
		for (com.soffid.iam.base.api.Identity instance: instances)
		{
			list.add (identityToEntity(instance));
		}
		return list;
	}

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.User} object 
	 */
	public void toUser(com.soffid.iam.base.model.UserEntity source, com.soffid.iam.base.api.User target) {
		// Attributes for User
		target.setId(source.getId());
		target.setUserName(source.getUserName());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setMiddleName(source.getMiddleName());
		target.setFullName(source.getFullName());
		// Incompatible types source.userType and target.userType
		// Incompatible types source.primaryGroup and target.primaryGroup
		// Missing attribute primaryGroupDescription on entity
		// Incompatible types source.homeServer and target.homeServer
		// Incompatible types source.profileServer and target.profileServer
		target.setEmailAddress(source.getEmailAddress());
		// Missing attribute mailAlias on entity
		// Incompatible types source.mailServer and target.mailServer
		target.setShortName(source.getShortName());
		// Incompatible types source.mailDomain and target.mailDomain
		target.setActive(new java.lang.Boolean(source.isActive()));
		target.setMultiSession(source.getMultiSession());
		target.setComments(source.getComments());
		target.setCreatedBy(source.getCreatedBy());
		if (source.getCreatedOn() == null) {
			target.setCreatedOn(null);
		} else {
			target.setCreatedOn(java.util.Calendar.getInstance());
			target.getCreatedOn().setTime(source.getCreatedOn());
		}
		target.setModifiedBy(source.getModifiedBy());
		if (source.getModifiedOn() == null) {
			target.setModifiedOn(null);
		} else {
			target.setModifiedOn(java.util.Calendar.getInstance());
			target.getModifiedOn().setTime(source.getModifiedOn());
		}
		// Incompatible types source.attributes and target.attributes
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
		target.setDeleted(source.getDeleted());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.User} object 
	 */
	/**
	 *  Stores {@link com.soffid.iam.base.api.User} in cache 
	 */
	protected synchronized void storeUserCacheEntry (java.lang.Long id, com.soffid.iam.base.api.User user)
	{
		UserCacheEntry entry = new UserCacheEntry ();
		entry.user = new com.soffid.iam.base.api.User(user);
		entry.timeStamp = System.currentTimeMillis();
		mapUser.put(com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id, entry);
	}

	/**
	 *  Retrieves {@link com.soffid.iam.base.api.User} from cache 
	 */
	protected synchronized com.soffid.iam.base.api.User getUserCacheEntry (java.lang.Long id)
	{
		UserCacheEntry entry = (UserCacheEntry) mapUser.get (com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id);
		if (entry == null) return null;
		if (entry.timeStamp + mapUserTimeout < System.currentTimeMillis())
		{
			mapUser.remove (com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id);
			return null;
		}
		return new com.soffid.iam.base.api.User(entry.user);
	}

	/**
	 *  Removes {@link com.soffid.iam.base.api.User} from cache 
	 */
	protected synchronized void removeUserCacheEntry (java.lang.Long id)
	{
		mapUser.remove (com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id);
	}

	public com.soffid.iam.base.api.User toUser(com.soffid.iam.base.model.UserEntity entity) {
		com.soffid.iam.base.api.User target = es.caib.seycon.ng.utils.Security.isSyncServer() ? 
			null : 
			getUserCacheEntry(entity.getId());
		if (target != null)
			return target;
		else
		{
			target = new com.soffid.iam.base.api.User();
			this.toUser(entity, target);
			if (!es.caib.seycon.ng.utils.Security.isSyncServer() )
				storeUserCacheEntry(entity.getId(), target);
			return target;
		}
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.User} list 
	 */
	public java.util.List<com.soffid.iam.base.api.User> toUserList (java.util.Collection<com.soffid.iam.base.model.UserEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.User> list =
				new java.util.LinkedList<com.soffid.iam.base.api.User>();
			for (final com.soffid.iam.base.model.UserEntity instance: instances)
			{
				list.add( toUser(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.User} object 
	 */
	public void userToEntity (com.soffid.iam.base.api.User source, com.soffid.iam.base.model.UserEntity target, boolean copyIfNull) {
		// Attributes for UserEntity
		if (copyIfNull || source.getUserName() != null)
		{
			target.setUserName(source.getUserName());
		}
		if (copyIfNull || source.getFirstName() != null)
		{
			target.setFirstName(source.getFirstName());
		}
		if (copyIfNull || source.getLastName() != null)
		{
			target.setLastName(source.getLastName());
		}
		if (copyIfNull || source.getMiddleName() != null)
		{
			target.setMiddleName(source.getMiddleName());
		}
		if (copyIfNull || source.getFullName() != null)
		{
			target.setFullName(source.getFullName());
		}
		if (copyIfNull || source.getShortName() != null)
		{
			target.setShortName(source.getShortName());
		}
		if (copyIfNull || source.getEmailAddress() != null)
		{
			target.setEmailAddress(source.getEmailAddress());
		}
		if (copyIfNull || source.getModifiedBy() != null)
		{
			target.setModifiedBy(source.getModifiedBy());
		}
		if (copyIfNull || source.getModifiedOn() != null)
		{
			if (source.getModifiedOn() == null) {
				target.setModifiedOn(null);
			} else {
				target.setModifiedOn(source.getModifiedOn().getTime());
			}
		}
		if (copyIfNull || source.getCreatedOn() != null)
		{
			if (source.getCreatedOn() == null) {
				target.setCreatedOn(null);
			} else {
				target.setCreatedOn(source.getCreatedOn().getTime());
			}
		}
		if (copyIfNull || source.getCreatedBy() != null)
		{
			target.setCreatedBy(source.getCreatedBy());
		}
		if (copyIfNull || source.getActive() != null)
		{
			target.setActive(java.lang.Boolean.TRUE.equals(source.getActive()));
		}
		if (copyIfNull || source.getComments() != null)
		{
			target.setComments(source.getComments());
		}
		// Missing attribute ACNetwork on entity
		if (copyIfNull || source.getMailServer() != null)
		{
			// Incompatible types source.mailServer and target.mailServer
		}
		if (copyIfNull || source.getHomeServer() != null)
		{
			// Incompatible types source.homeServer and target.homeServer
		}
		if (copyIfNull || source.getMailDomain() != null)
		{
			// Incompatible types source.mailDomain and target.mailDomain
		}
		if (copyIfNull || source.getProfileServer() != null)
		{
			// Incompatible types source.profileServer and target.profileServer
		}
		if (copyIfNull || source.getPrimaryGroup() != null)
		{
			// Incompatible types source.primaryGroup and target.primaryGroup
		}
		if (copyIfNull || source.getAttributes() != null)
		{
			// Incompatible types source.attributes and target.attributes
		}
		// Missing attribute secondaryGroups on entity
		// Missing attribute printers on entity
		// Missing attribute sessions on entity
		// Missing attribute userMailList on entity
		if (copyIfNull || source.getMultiSession() != null)
		{
			target.setMultiSession(source.getMultiSession());
		}
		// Missing attribute tenant on entity
		// Missing attribute ApplicationResponsible on entity
		// Missing attribute accessHostAsAdministratorAuthorization on entity
		if (copyIfNull || source.getUserType() != null)
		{
			// Incompatible types source.userType and target.userType
		}
		// Missing attribute secrets on entity
		// Missing attribute accounts on entity
		// Missing attribute accountAccess on entity
		// Missing attribute passwords on entity
		// Missing attribute pendingAuthoritativeChanges on entity
		// Missing attribute browsers on entity
		// Missing attribute passwordManagerToken on entity
		// Missing attribute vaultFolders on entity
		// Missing attribute preferences on entity
		// Missing attribute events on entity
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
	 *  Transforms from {@link com.soffid.iam.base.api.User} object 
	 */
	public com.soffid.iam.base.model.UserEntity userToEntity (com.soffid.iam.base.api.User instance) {
		com.soffid.iam.base.model.UserEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newUserEntity();
		userToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.User} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity>  userToEntityList (java.util.Collection<com.soffid.iam.base.api.User> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.UserEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.UserEntity>();
		for (com.soffid.iam.base.api.User instance: instances)
		{
			list.add (userToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.UserEntity} .
	 */
	public com.soffid.iam.base.model.UserEntity newUserEntity()
	{
		return new com.soffid.iam.base.model.UserEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.UserEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.UserEntity result = (com.soffid.iam.base.model.UserEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.UserEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.base.model.UserEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.base.model.UserEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.UserEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.UserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setModifiedOn(new java.util.Date());
		entity.setModifiedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
		removeUserCacheEntry(entity.getId());
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.UserEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.UserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserEntityDao.update - 'entity' can not be null");
		}
		entity.setModifiedOn(new java.util.Date());
		entity.setModifiedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
		removeUserCacheEntry(entity.getId());
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.UserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
		removeUserCacheEntry(entity.getId());
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.UserEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.UserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.UserEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.UserEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.UserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.UserEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.UserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.UserEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"UserEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.UserEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.UserEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.UserEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
class UserCacheEntry {
	public com.soffid.iam.base.api.User user;
	public long timeStamp;
}
