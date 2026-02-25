//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity RoleAccountEntity
 */
public abstract class RoleAccountEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.RoleAccountEntityDao
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

	com.soffid.iam.iga.model.RoleAccountAttributeEntityDao roleAccountAttributeEntityDao;

	/**
	 * Sets reference to <code>roleAccountAttributeEntityDao</code>.
	 */
	public void setRoleAccountAttributeEntityDao (com.soffid.iam.iga.model.RoleAccountAttributeEntityDao roleAccountAttributeEntityDao) {
		this.roleAccountAttributeEntityDao = roleAccountAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>roleAccountAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleAccountAttributeEntityDao getRoleAccountAttributeEntityDao () {
		return roleAccountAttributeEntityDao;
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

	com.soffid.iam.iga.model.RuleEntityDao ruleEntityDao;

	/**
	 * Sets reference to <code>ruleEntityDao</code>.
	 */
	public void setRuleEntityDao (com.soffid.iam.iga.model.RuleEntityDao ruleEntityDao) {
		this.ruleEntityDao = ruleEntityDao;
	}

	/**
	 * Gets reference to <code>ruleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RuleEntityDao getRuleEntityDao () {
		return ruleEntityDao;
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
	 * Operation findAllByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllByUserName(
	    java.lang.String userName)
	
	{
		return findAllByUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findAllByUserName("select ra from com.soffid.iam.iga.model.RoleAccountEntity ra\njoin ra.account as account\njoin account.users as users\njoin users.user as user\nleft join ra.role as role\nleft join role.system as system\nwhere  user.userName = :userName and user.tenant.id = :tenantId and ra.enabled = true \norder by system.name, role.name",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllByUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
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
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAllRolAccountToEndDelegation
	 * Search delegations to end
	 * @param now
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllRolAccountToEndDelegation(
	    java.util.Date now)
	
	{
		return findAllRolAccountToEndDelegation((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, now);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllRolAccountToEndDelegation(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date now)
	
	{
		return findAllRolAccountToEndDelegation("select ra from com.soffid.iam.iga.model.RoleAccountEntity as ra\nwhere ra.delegateUntil < :now and ra.delegationStatus is not null and      ra.role.system.tenant.id=:tenantId and ra.enabled = true ",
			criteria, now);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllRolAccountToEndDelegation(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date now)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("now", now, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAllRolAccountToStartDelegation
	 * Search delegations to start
	 * @param now
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllRolAccountToStartDelegation(
	    java.util.Date now)
	
	{
		return findAllRolAccountToStartDelegation((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, now);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllRolAccountToStartDelegation(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date now)
	
	{
		return findAllRolAccountToStartDelegation("select ra from com.soffid.iam.iga.model.RoleAccountEntity as ra\nwhere ra.delegateSince < :now and (ra.delegateUntil is null or ra.delegateUntil >= :now)       and ra.delegationStatus = 'P'       and ra.role.system.tenant.id=:tenantId and ra.enabled = true ",
			criteria, now);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllRolAccountToStartDelegation(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date now)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("now", now, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
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
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByGroupName(
	    java.lang.String groupName)
	
	{
		return findByGroupName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, groupName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByGroupName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	
	{
		return findByGroupName("select ra\nfrom com.soffid.iam.iga.model.RoleAccountEntity ra\nwhere ra.group.name=:groupName and ra.group.tenant.id = :tenantId and ra.enabled = true ",
			criteria, groupName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByGroupName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	
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
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
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
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByUserName(
	    java.lang.String userName)
	
	{
		return findByUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findByUserName("select ra\nfrom com.soffid.iam.base.model.UserEntity user\ninner join    user.accounts as accounts\ninner join    accounts.account as account\ninner join    account.roles as ra\ninner join    ra.role as role\ninner join    role.system as dispatcher\nwhere ra.enabled is true and account.type='U' and user.userName = :userName and user.tenant.id=:tenantId\norder by dispatcher.name, role.name\n",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
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
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByExternalId
	 * @param externalId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByExternalId(
	    java.lang.String externalId)
	
	{
		return findByExternalId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, externalId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByExternalId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId)
	
	{
		return findByExternalId("from com.soffid.iam.iga.model.RoleAccountEntity where role.system.tenant.id=:tenantId and externalId=:externalId",
			criteria, externalId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByExternalId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("externalId", externalId, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByQualifierGroup
	 * @param groupName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByQualifierGroup(
	    java.lang.String groupName)
	
	{
		return findByQualifierGroup((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, groupName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByQualifierGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	
	{
		return findByQualifierGroup("select ra from com.soffid.iam.iga.model.RoleAccountEntity ra\nwhere ra.group.name=:groupName and ra.group.tenant.id=:tenantId and ra.enabled = true \n",
			criteria, groupName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByQualifierGroup(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	
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
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByInformationSystem
	 * Gets all granted roles for an information system
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByInformationSystem(
	    java.lang.String informationSystem)
	
	{
		return findByInformationSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, informationSystem);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByInformationSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	
	{
		return findByInformationSystem("select rolusu from com.soffid.iam.iga.model.RoleAccountEntity rolusu join rolusu.account.users as users join users.user as user where rolusu.role.informationSystem.name = :informationSystem and rolusu.role.system.tenant.id = :tenantId and rolusu.enabled = true order by user.userName, rolusu.role.name, rolusu.role.system.name",
			criteria, informationSystem);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByInformationSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("informationSystem", informationSystem, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByQualifierIS
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByQualifierIS(
	    java.lang.String informationSystem)
	
	{
		return findByQualifierIS((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, informationSystem);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByQualifierIS(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	
	{
		return findByQualifierIS("select ra from com.soffid.iam.iga.model.RoleAccountEntity ra\nwhere ra.informationSystem.name=:informationSystem and ra.role.system.tenant.id = :tenantId and ra.enabled = true \n",
			criteria, informationSystem);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByQualifierIS(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("informationSystem", informationSystem, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByRoleAndDomainType
	 * @param roleName
	 * @param systemName
	 * @param informationSystemName
	 * @param domainType
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByRoleAndDomainType(
	    java.lang.String roleName, 
	    java.lang.String systemName, 
	    java.lang.String informationSystemName, 
	    java.lang.String domainType)
	
	{
		return findByRoleAndDomainType((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, roleName, systemName, informationSystemName, domainType);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByRoleAndDomainType(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String systemName, java.lang.String informationSystemName, java.lang.String domainType)
	
	{
		return findByRoleAndDomainType("select ra from com.soffid.iam.iga.model.RoleAccountEntity ra\nleft join ra.role role \nwhere role.name = :roleName and role.system.name = :systemName and \nrole.informationSystem.name = :informationSystemName and ra.enabled = true and ra.domainType=:domainType and role.system.tenant.id = :tenantId",
			criteria, roleName, systemName, informationSystemName, domainType);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByRoleAndDomainType(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String systemName, java.lang.String informationSystemName, java.lang.String domainType)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("roleName", roleName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("systemName", systemName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("informationSystemName", informationSystemName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domainType", domainType, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByRoleAndDomainValue
	 * @param roleName
	 * @param systemName
	 * @param domainType
	 * @param groupScope
	 * @param informationSystemScope
	 * @param domainValueId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByRoleAndDomainValue(
	    java.lang.String roleName, 
	    java.lang.String systemName, 
	    java.lang.String domainType, 
	    java.lang.String groupScope, 
	    java.lang.String informationSystemScope, 
	    java.lang.Long domainValueId)
	
	{
		return findByRoleAndDomainValue((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, roleName, systemName, domainType, groupScope, informationSystemScope, domainValueId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByRoleAndDomainValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String systemName, java.lang.String domainType, java.lang.String groupScope, java.lang.String informationSystemScope, java.lang.Long domainValueId)
	
	{
		return findByRoleAndDomainValue("select ra\nfrom com.soffid.iam.iga.model.RoleAccountEntity ra\nleft join ra.role role \nleft join ra.group as gr left join ra.informationSystem informationSystem \nleft join ra.domainValue domainValue \nwhere (role.name = :roleName and role.system.name = :systemName) and \nra.domainType=:domainType and ra.enabled = true and \n( gr is null or :groupScope = gr.name) and \n( informationSystem is null or :informationSystemScope = informationSystem.name) and \n( domainValue is null or :domainValueId = domainValue.id) and role.system.tenant.id = :tenantId ",
			criteria, roleName, systemName, domainType, groupScope, informationSystemScope, domainValueId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByRoleAndDomainValue(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String systemName, java.lang.String domainType, java.lang.String groupScope, java.lang.String informationSystemScope, java.lang.Long domainValueId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("roleName", roleName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("systemName", systemName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domainType", domainType, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("groupScope", groupScope, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("informationSystemScope", informationSystemScope, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domainValueId", domainValueId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUserAndRule
	 * @param userId
	 * @param ruleId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByUserAndRule(
	    java.lang.Long userId, 
	    java.lang.Long ruleId)
	
	{
		return findByUserAndRule((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userId, ruleId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByUserAndRule(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long userId, java.lang.Long ruleId)
	
	{
		return findByUserAndRule("select ra from com.soffid.iam.iga.model.RoleAccountEntity ra\njoin ra.account.users as useraccount\nwhere ra.rule.id = :ruleId and useraccount.user.id = :userId and ra.account.type='U' and ra.account.system.tenant.id = :tenantId and ra.enabled = true order by ra.account.name",
			criteria, userId, ruleId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByUserAndRule(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long userId, java.lang.Long ruleId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userId", userId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("ruleId", ruleId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findDelegatedRolAccounts
	 * Search delegations done by a user
	 * @param user
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findDelegatedRolAccounts(
	    java.lang.String user)
	
	{
		return findDelegatedRolAccounts((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, user);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findDelegatedRolAccounts(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	
	{
		return findDelegatedRolAccounts("select ra from com.soffid.iam.iga.model.RoleAccountEntity as ra\njoin ra.ownerAccount.users as userAccount join userAccount.user as user where ra.delegationStatus='A' and user.userName = :user and user.tenant.id=:tenantId and ra.enabled = true ",
			criteria, user);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findDelegatedRolAccounts(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findHistoryByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findHistoryByUserName(
	    java.lang.String userName)
	
	{
		return findHistoryByUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findHistoryByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findHistoryByUserName("select ra\nfrom com.soffid.iam.base.model.UserEntity user\ninner join    user.accounts as accounts\ninner join    accounts.account as account\ninner join    account.roles as ra\ninner join    ra.role as role\ninner join    role.system as dispatcher\nwhere account.type='U' and user.userName = :userName and user.tenant.id=:tenantId\norder by dispatcher.name, role.name\n",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findHistoryByUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
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
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findMatching
	 * @param accountId
	 * @param roleId
	 * @param domainType
	 * @param groupName
	 * @param informationSystem
	 * @param domainValue
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findMatching(
	    java.lang.Long accountId, 
	    java.lang.Long roleId, 
	    java.lang.String domainType, 
	    java.lang.String groupName, 
	    java.lang.String informationSystem, 
	    java.lang.String domainValue)
	
	{
		return findMatching((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, accountId, roleId, domainType, groupName, informationSystem, domainValue);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findMatching(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long accountId, java.lang.Long roleId, java.lang.String domainType, java.lang.String groupName, java.lang.String informationSystem, java.lang.String domainValue)
	
	{
		return findMatching("select rolsUsuaris from com.soffid.iam.iga.model.RoleAccountEntity rolsUsuaris left join rolsUsuaris.group grup left join rolsUsuaris.informationSystem aplicacio left join rolsUsuaris.domainValue valorDominiAplicacio where rolsUsuaris.account.id = :accountId and rolsUsuaris.role.id = :roleId and (rolsUsuaris.domainType = :domainType) and ((:groupName is null and grup is null) or (grup.name = :groupName)) and ((:informationSystem is null and aplicacio is null) or (aplicacio.name = :informationSystem)) and ((:domainValue is null and valorDominiAplicacio is null) or (valorDominiAplicacio.value = :domainValue)) and rolsUsuaris.account.system.tenant.id = :tenantId and rolsUsuaris.enabled = true",
			criteria, accountId, roleId, domainType, groupName, informationSystem, domainValue);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findMatching(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long accountId, java.lang.Long roleId, java.lang.String domainType, java.lang.String groupName, java.lang.String informationSystem, java.lang.String domainValue)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("accountId", accountId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("roleId", roleId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("domainType", domainType, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("groupName", groupName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("informationSystem", informationSystem, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domainValue", domainValue, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findRolAccountToDisable
	 * @param now
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRolAccountToDisable(
	    java.util.Date now)
	
	{
		return findRolAccountToDisable((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, now);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRolAccountToDisable(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date now)
	
	{
		return findRolAccountToDisable("select ra from com.soffid.iam.iga.model.RoleAccountEntity ra\nwhere ra.endDate < :now and ra.enabled = true and ra.account.system.tenant.id = :tenantId",
			criteria, now);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRolAccountToDisable(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date now)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("now", now, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findRolAccountToEnable
	 * @param now
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRolAccountToEnable(
	    java.util.Date now)
	
	{
		return findRolAccountToEnable((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, now);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRolAccountToEnable(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date now)
	
	{
		return findRolAccountToEnable("select ra from com.soffid.iam.iga.model.RoleAccountEntity ra\nwhere ra.startDate < :now and (ra.endDate is null or ra.endDate >= :now) and ra.enabled = false and ra.account.system.tenant.id = :tenantId ",
			criteria, now);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRolAccountToEnable(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date now)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("now", now, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findRoleAccountToEndDelegation
	 * Search delegations to end
	 * @param user
	 * @param now
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRoleAccountToEndDelegation(
	    java.lang.String user, 
	    java.util.Date now)
	
	{
		return findRoleAccountToEndDelegation((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, user, now);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRoleAccountToEndDelegation(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.util.Date now)
	
	{
		return findRoleAccountToEndDelegation("select ra from com.soffid.iam.iga.model.RoleAccountEntity as ra\njoin ra.delegateAccount.users as userAccount join userAccount.user as user where ra.delegateUntil < :now and ra.delegationStatus is not null and user.userName = :user and user.tenant.id=:tenantId and ra.enabled = true ",
			criteria, user, now);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRoleAccountToEndDelegation(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.util.Date now)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("now", now, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findRoleAccountToStartDelegation
	 * Search delegations to start
	 * @param user
	 * @param now
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRoleAccountToStartDelegation(
	    java.lang.String user, 
	    java.util.Date now)
	
	{
		return findRoleAccountToStartDelegation((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, user, now);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRoleAccountToStartDelegation(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.util.Date now)
	
	{
		return findRoleAccountToStartDelegation("select ra from com.soffid.iam.iga.model.RoleAccountEntity as ra\njoin ra.delegateAccount.users as userAccount join userAccount.user as user where ra.delegateSince < :now and (ra.delegateUntil is null or ra.delegateUntil >= :now) and ra.delegationStatus = 'P' and user.userName = :user and user.tenant.id=:tenantId and ra.enabled = true ",
			criteria, user, now);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRoleAccountToStartDelegation(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.util.Date now)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("now", now, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.iga.model.RoleAccountEntity#	 * @see com.soffid.iam.iga.model.RoleAccountEntity#void update(com.soffid.iam.iga.model.RoleAccountEntity entity, java.lang.String auditOperation)
	 */
	public void update(
		com.soffid.iam.iga.model.RoleAccountEntity entity, 
		java.lang.String auditOperation)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (entity == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleAccountEntity.update(com.soffid.iam.iga.model.RoleAccountEntity entity, java.lang.String auditOperation) - entity cannot be null");
		}
		if (auditOperation == null || auditOperation.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleAccountEntity.update(com.soffid.iam.iga.model.RoleAccountEntity entity, java.lang.String auditOperation) - auditOperation cannot be null");
		}
		try
		{
			handleUpdate(entity, auditOperation);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.model.RoleAccountEntity.class).
				warn ("Error on RoleAccountEntity.update", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on RoleAccountEntity.update: "+th.toString(), th);
		}
	}

	protected abstract void handleUpdate(com.soffid.iam.iga.model.RoleAccountEntity entity, java.lang.String auditOperation) throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.RoleAccount} object 
	 */
	public void toRoleAccount(com.soffid.iam.iga.model.RoleAccountEntity source, com.soffid.iam.iga.api.RoleAccount target) {
		// Attributes for RoleAccount
		target.setId(source.getId());
		// Missing attribute accountId on entity
		// Missing attribute accountName on entity
		// Missing attribute accountSystem on entity
		// Missing attribute roleName on entity
		// Missing attribute roleId on entity
		// Missing attribute roleCategory on entity
		// Missing attribute informationSystemName on entity
		// Missing attribute roleDescription on entity
		// Missing attribute userFullName on entity
		// Missing attribute groupDescription on entity
		// Incompatible types source.domainValue and target.domainValue
		// Missing attribute system on entity
		// Missing attribute userGroupCode on entity
		// Missing attribute bpmEnabled on entity
		// Missing attribute userName on entity
		// Missing attribute ruleId on entity
		// Missing attribute ruleDescription on entity
		// Missing attribute sodRisk on entity
		// Missing attribute sodRules on entity
		target.setStartDate(source.getStartDate());
		target.setEndDate(source.getEndDate());
		target.setEnabled(source.isEnabled());
		target.setApprovalPending(source.isApprovalPending());
		target.setRemovalPending(source.getRemovalPending());
		// Incompatible types source.holderGroup and target.holderGroup
		target.setApprovalProcess(source.getApprovalProcess());
		target.setCertificationDate(source.getCertificationDate());
		// Missing attribute parentGrant on entity
		target.setDelegationStatus(source.getDelegationStatus());
		// Incompatible types source.ownerAccount and target.ownerAccount
		// Incompatible types source.delegateAccount and target.delegateAccount
		target.setDelegateSince(source.getDelegateSince());
		target.setDelegateUntil(source.getDelegateUntil());
		target.setExternalId(source.getExternalId());
		// Incompatible types source.attributes and target.attributes
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleAccount} object 
	 */
	public com.soffid.iam.iga.api.RoleAccount toRoleAccount(com.soffid.iam.iga.model.RoleAccountEntity entity) {
		final com.soffid.iam.iga.api.RoleAccount target = new com.soffid.iam.iga.api.RoleAccount();
		this.toRoleAccount(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleAccount} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.RoleAccount> toRoleAccountList (java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.RoleAccount> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.RoleAccount>();
			for (final com.soffid.iam.iga.model.RoleAccountEntity instance: instances)
			{
				list.add( toRoleAccount(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.RoleAccount} object 
	 */
	public void roleAccountToEntity (com.soffid.iam.iga.api.RoleAccount source, com.soffid.iam.iga.model.RoleAccountEntity target, boolean copyIfNull) {
		// Attributes for RoleAccountEntity
		// Missing attribute group on entity
		// Missing attribute role on entity
		if (copyIfNull || source.getDomainValue() != null)
		{
			// Incompatible types source.domainValue and target.domainValue
		}
		// Missing attribute domainType on entity
		// Missing attribute informationSystem on entity
		// Missing attribute account on entity
		// Missing attribute rule on entity
		if (copyIfNull || source.getStartDate() != null)
		{
			target.setStartDate(source.getStartDate());
		}
		if (copyIfNull || source.getEndDate() != null)
		{
			target.setEndDate(source.getEndDate());
		}
		target.setEnabled(source.isEnabled());
		target.setApprovalPending(source.isApprovalPending());
		if (copyIfNull || source.getRemovalPending() != null)
		{
			target.setRemovalPending(source.getRemovalPending());
		}
		if (copyIfNull || source.getHolderGroup() != null)
		{
			// Incompatible types source.holderGroup and target.holderGroup
		}
		if (copyIfNull || source.getApprovalProcess() != null)
		{
			target.setApprovalProcess(source.getApprovalProcess());
		}
		if (copyIfNull || source.getCertificationDate() != null)
		{
			target.setCertificationDate(source.getCertificationDate());
		}
		// Missing attribute parent on entity
		if (copyIfNull || source.getDelegationStatus() != null)
		{
			target.setDelegationStatus(source.getDelegationStatus());
		}
		if (copyIfNull || source.getOwnerAccount() != null)
		{
			// Incompatible types source.ownerAccount and target.ownerAccount
		}
		if (copyIfNull || source.getDelegateAccount() != null)
		{
			// Incompatible types source.delegateAccount and target.delegateAccount
		}
		if (copyIfNull || source.getDelegateSince() != null)
		{
			target.setDelegateSince(source.getDelegateSince());
		}
		if (copyIfNull || source.getDelegateUntil() != null)
		{
			target.setDelegateUntil(source.getDelegateUntil());
		}
		if (copyIfNull || source.getExternalId() != null)
		{
			target.setExternalId(source.getExternalId());
		}
		if (copyIfNull || source.getAttributes() != null)
		{
			// Incompatible types source.attributes and target.attributes
		}
		// Missing attribute children on entity
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
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleAccount} object 
	 */
	public com.soffid.iam.iga.model.RoleAccountEntity roleAccountToEntity (com.soffid.iam.iga.api.RoleAccount instance) {
		com.soffid.iam.iga.model.RoleAccountEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newRoleAccountEntity();
		roleAccountToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleAccount} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>  roleAccountToEntityList (java.util.Collection<com.soffid.iam.iga.api.RoleAccount> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.RoleAccountEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.RoleAccountEntity>();
		for (com.soffid.iam.iga.api.RoleAccount instance: instances)
		{
			list.add (roleAccountToEntity(instance));
		}
		return list;
	}

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public void toRoleGrant(com.soffid.iam.iga.model.RoleAccountEntity source, com.soffid.iam.iga.api.RoleGrant target) {
		// Attributes for RoleGrant
		target.setId(source.getId());
		// Missing attribute roleId on entity
		// Missing attribute roleName on entity
		// Missing attribute roleDescription on entity
		// Missing attribute system on entity
		// Incompatible types source.informationSystem and target.informationSystem
		// Missing attribute hasDomain on entity
		// Incompatible types source.domainValue and target.domainValue
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
		target.setStartDate(source.getStartDate());
		target.setEndDate(source.getEndDate());
		target.setEnabled(source.isEnabled());
		// Incompatible types source.holderGroup and target.holderGroup
		// Missing attribute status on entity
		// Missing attribute mandatory on entity
		// Incompatible types source.attributes and target.attributes
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public com.soffid.iam.iga.api.RoleGrant toRoleGrant(com.soffid.iam.iga.model.RoleAccountEntity entity) {
		final com.soffid.iam.iga.api.RoleGrant target = new com.soffid.iam.iga.api.RoleGrant();
		this.toRoleGrant(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleGrant} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.RoleGrant> toRoleGrantList (java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.RoleGrant> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.RoleGrant>();
			for (final com.soffid.iam.iga.model.RoleAccountEntity instance: instances)
			{
				list.add( toRoleGrant(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public void roleGrantToEntity (com.soffid.iam.iga.api.RoleGrant source, com.soffid.iam.iga.model.RoleAccountEntity target, boolean copyIfNull) {
		// Attributes for RoleAccountEntity
		// Missing attribute group on entity
		// Missing attribute role on entity
		if (copyIfNull || source.getDomainValue() != null)
		{
			// Incompatible types source.domainValue and target.domainValue
		}
		// Missing attribute domainType on entity
		if (copyIfNull || source.getInformationSystem() != null)
		{
			// Incompatible types source.informationSystem and target.informationSystem
		}
		// Missing attribute account on entity
		// Missing attribute rule on entity
		if (copyIfNull || source.getStartDate() != null)
		{
			target.setStartDate(source.getStartDate());
		}
		if (copyIfNull || source.getEndDate() != null)
		{
			target.setEndDate(source.getEndDate());
		}
		target.setEnabled(source.isEnabled());
		// Missing attribute approvalPending on entity
		// Missing attribute removalPending on entity
		if (copyIfNull || source.getHolderGroup() != null)
		{
			// Incompatible types source.holderGroup and target.holderGroup
		}
		// Missing attribute approvalProcess on entity
		// Missing attribute certificationDate on entity
		// Missing attribute parent on entity
		// Missing attribute delegationStatus on entity
		// Missing attribute ownerAccount on entity
		// Missing attribute delegateAccount on entity
		// Missing attribute delegateSince on entity
		// Missing attribute delegateUntil on entity
		// Missing attribute externalId on entity
		if (copyIfNull || source.getAttributes() != null)
		{
			// Incompatible types source.attributes and target.attributes
		}
		// Missing attribute children on entity
		// Missing attribute events on entity
		// Missing attribute createdOn on entity
		// Missing attribute createdBy on entity
		// Missing attribute updatedOn on entity
		// Missing attribute updatedBy on entity
		// Missing attribute deletedOn on entity
		// Missing attribute deletedBy on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public com.soffid.iam.iga.model.RoleAccountEntity roleGrantToEntity (com.soffid.iam.iga.api.RoleGrant instance) {
		com.soffid.iam.iga.model.RoleAccountEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newRoleAccountEntity();
		roleGrantToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleGrant} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>  roleGrantToEntityList (java.util.Collection<com.soffid.iam.iga.api.RoleGrant> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.RoleAccountEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.RoleAccountEntity>();
		for (com.soffid.iam.iga.api.RoleGrant instance: instances)
		{
			list.add (roleGrantToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RoleAccountEntity} .
	 */
	public com.soffid.iam.iga.model.RoleAccountEntity newRoleAccountEntity()
	{
		return new com.soffid.iam.iga.model.RoleAccountEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RoleAccountEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.RoleAccountEntity result = (com.soffid.iam.iga.model.RoleAccountEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.RoleAccountEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.RoleAccountEntity where role.system.tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RoleAccountEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RoleAccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleAccountEntityDao.create - 'entity' can not be null");
		}

		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RoleAccountEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RoleAccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleAccountEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RoleAccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleAccountEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RoleAccountEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleAccountEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleAccountEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RoleAccountEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleAccountEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleAccountEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleAccountEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleAccountEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"RoleAccountEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.RoleAccountEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
