//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity SystemEntity
 */
public abstract class SystemEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.SystemEntityDao
{
	com.soffid.iam.iga.model.AccessControlEntityDao accessControlEntityDao;

	/**
	 * Sets reference to <code>accessControlEntityDao</code>.
	 */
	public void setAccessControlEntityDao (com.soffid.iam.iga.model.AccessControlEntityDao accessControlEntityDao) {
		this.accessControlEntityDao = accessControlEntityDao;
	}

	/**
	 * Gets reference to <code>accessControlEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.AccessControlEntityDao getAccessControlEntityDao () {
		return accessControlEntityDao;
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

	com.soffid.iam.base.model.AccountMetadataEntityDao accountMetadataEntityDao;

	/**
	 * Sets reference to <code>accountMetadataEntityDao</code>.
	 */
	public void setAccountMetadataEntityDao (com.soffid.iam.base.model.AccountMetadataEntityDao accountMetadataEntityDao) {
		this.accountMetadataEntityDao = accountMetadataEntityDao;
	}

	/**
	 * Gets reference to <code>accountMetadataEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountMetadataEntityDao getAccountMetadataEntityDao () {
		return accountMetadataEntityDao;
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

	com.soffid.iam.am.model.EntryPointEntityDao entryPointEntityDao;

	/**
	 * Sets reference to <code>entryPointEntityDao</code>.
	 */
	public void setEntryPointEntityDao (com.soffid.iam.am.model.EntryPointEntityDao entryPointEntityDao) {
		this.entryPointEntityDao = entryPointEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointEntityDao getEntryPointEntityDao () {
		return entryPointEntityDao;
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

	com.soffid.iam.iga.model.ObjectMappingEntityDao objectMappingEntityDao;

	/**
	 * Sets reference to <code>objectMappingEntityDao</code>.
	 */
	public void setObjectMappingEntityDao (com.soffid.iam.iga.model.ObjectMappingEntityDao objectMappingEntityDao) {
		this.objectMappingEntityDao = objectMappingEntityDao;
	}

	/**
	 * Gets reference to <code>objectMappingEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ObjectMappingEntityDao getObjectMappingEntityDao () {
		return objectMappingEntityDao;
	}

	com.soffid.iam.am.model.PasswordDomainEntityDao passwordDomainEntityDao;

	/**
	 * Sets reference to <code>passwordDomainEntityDao</code>.
	 */
	public void setPasswordDomainEntityDao (com.soffid.iam.am.model.PasswordDomainEntityDao passwordDomainEntityDao) {
		this.passwordDomainEntityDao = passwordDomainEntityDao;
	}

	/**
	 * Gets reference to <code>passwordDomainEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PasswordDomainEntityDao getPasswordDomainEntityDao () {
		return passwordDomainEntityDao;
	}

	com.soffid.iam.am.model.PasswordPolicyEntityDao passwordPolicyEntityDao;

	/**
	 * Sets reference to <code>passwordPolicyEntityDao</code>.
	 */
	public void setPasswordPolicyEntityDao (com.soffid.iam.am.model.PasswordPolicyEntityDao passwordPolicyEntityDao) {
		this.passwordPolicyEntityDao = passwordPolicyEntityDao;
	}

	/**
	 * Gets reference to <code>passwordPolicyEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntityDao getPasswordPolicyEntityDao () {
		return passwordPolicyEntityDao;
	}

	com.soffid.iam.iga.model.ReconcileTriggerEntityDao reconcileTriggerEntityDao;

	/**
	 * Sets reference to <code>reconcileTriggerEntityDao</code>.
	 */
	public void setReconcileTriggerEntityDao (com.soffid.iam.iga.model.ReconcileTriggerEntityDao reconcileTriggerEntityDao) {
		this.reconcileTriggerEntityDao = reconcileTriggerEntityDao;
	}

	/**
	 * Gets reference to <code>reconcileTriggerEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ReconcileTriggerEntityDao getReconcileTriggerEntityDao () {
		return reconcileTriggerEntityDao;
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

	com.soffid.iam.iga.model.SystemGroupEntityDao systemGroupEntityDao;

	/**
	 * Sets reference to <code>systemGroupEntityDao</code>.
	 */
	public void setSystemGroupEntityDao (com.soffid.iam.iga.model.SystemGroupEntityDao systemGroupEntityDao) {
		this.systemGroupEntityDao = systemGroupEntityDao;
	}

	/**
	 * Gets reference to <code>systemGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.SystemGroupEntityDao getSystemGroupEntityDao () {
		return systemGroupEntityDao;
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

	com.soffid.iam.iga.model.UserTypeSystemEntityDao userTypeSystemEntityDao;

	/**
	 * Sets reference to <code>userTypeSystemEntityDao</code>.
	 */
	public void setUserTypeSystemEntityDao (com.soffid.iam.iga.model.UserTypeSystemEntityDao userTypeSystemEntityDao) {
		this.userTypeSystemEntityDao = userTypeSystemEntityDao;
	}

	/**
	 * Gets reference to <code>userTypeSystemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserTypeSystemEntityDao getUserTypeSystemEntityDao () {
		return userTypeSystemEntityDao;
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
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.SystemEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.SystemEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.iga.model.SystemEntity s where s.name = :name and s.tenant.id = :tenantId",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.SystemEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.iga.model.SystemEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.SystemEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.SystemEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findSoffidSystem
	 * @return
	**/
	public com.soffid.iam.iga.model.SystemEntity findSoffidSystem(
)
	
	{
		return findSoffidSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.SystemEntity findSoffidSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findSoffidSystem("select dis from com.soffid.iam.iga.model.SystemEntity as dis where dis.mainSystem = true and dis.tenant.id = :tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.SystemEntity findSoffidSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
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
			com.soffid.iam.iga.model.SystemEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.SystemEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.SystemEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUsage
	 * @param usage
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findByUsage(
	    java.lang.String usage)
	
	{
		return findByUsage((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, usage);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findByUsage(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String usage)
	
	{
		return findByUsage("from com.soffid.iam.iga.model.SystemEntity where tenant.id=:tenantId and usage=:usage",
			criteria, usage);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findByUsage(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String usage)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("usage", usage, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.SystemEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findServerTenants
	 * @param server
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findServerTenants(
	    java.lang.String server)
	
	{
		return findServerTenants((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findServerTenants(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		return findServerTenants("select dis from com.soffid.iam.iga.model.SystemEntity as dis where dis.id in (select distinct dis2.id from com.soffid.iam.iga.model.SystemEntity as dis2 join dis2.tenant as tenant join tenant.servers as server where server.tenantServer.name = :server)",
			criteria, server);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findServerTenants(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.SystemEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findServices
	 * @param url
	 * @param t
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findServices(
	    java.lang.String url, 
	    boolean t)
	
	{
		return findServices((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, url, t);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findServices(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String url, boolean t)
	
	{
		return findServices("select dis from com.soffid.iam.iga.model.SystemEntityImpl as dis, com.soffid.iam.base.model.AgentDescriptorEntity d where (dis.url = :url or dis.url2 = :url) and dis.className = d.className and d.service = :t",
			criteria, url, t);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findServices(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String url, boolean t)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("url", url, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("t", t, org.hibernate.Hibernate.BOOLEAN);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.SystemEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findActives
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> findActives(
)
	
	{
		return findActives((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> findActives(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findActives("from com.soffid.iam.iga.model.SystemEntity agent where agent.url is not null and agent.tenant.id = :tenantId order by agent.name",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> findActives(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
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
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.SystemEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByFilter
	 * @param name
	 * @param className
	 * @param url
	 * @param roleBased
	 * @param trusted
	 * @param active
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> findByFilter(
	    java.lang.String name, 
	    java.lang.String className, 
	    java.lang.String url, 
	    java.lang.String roleBased, 
	    java.lang.String trusted, 
	    java.lang.String active)
	
	{
		return findByFilter((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, className, url, roleBased, trusted, active);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> findByFilter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String className, java.lang.String url, java.lang.String roleBased, java.lang.String trusted, java.lang.String active)
	
	{
		return findByFilter("from com.soffid.iam.iga.model.SystemEntity se where (:name is null or se.name like :name) and (:className is null or se.className like :className) and (:url is null or upper(url) like upper(:url) or upper(url2) like upper(:url)) and\n(:roleBased is null or se.roleBased = :roleBased) and (:trusted is null or se.trusted = :trusted) and (:active is null or se.url is not null) and se.tenant.id = :tenantId",
			criteria, name, className, url, roleBased, trusted, active);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> findByFilter(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String className, java.lang.String url, java.lang.String roleBased, java.lang.String trusted, java.lang.String active)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("className", className, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("url", url, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("roleBased", roleBased, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("trusted", trusted, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("active", active, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.SystemEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.System} object 
	 */
	public void toSystem(com.soffid.iam.iga.model.SystemEntity source, com.soffid.iam.iga.api.System target) {
		// Attributes for System
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setClassName(source.getClassName());
		target.setUrl(source.getUrl());
		target.setUrl2(source.getUrl2());
		// Missing attribute rolebased on entity
		// Incompatible types source.trusted and target.trusted
		// Missing attribute userTypes on entity
		target.setManualAccountCreation(source.getManualAccountCreation());
		target.setFullReconciliation(java.lang.Boolean.TRUE.equals(source.getFullReconciliation()));
		target.setGenerateTasksOnLoad(java.lang.Boolean.TRUE.equals(source.getGenerateTasksOnLoad()));
		// Missing attribute groupsList on entity
		// Missing attribute accessControl on entity
		// Missing attribute passwordsDomainId on entity
		// Missing attribute passwordsDomain on entity
		// Missing attribute usersDomain on entity
		target.setReadOnly(source.isReadOnly());
		target.setPause(java.lang.Boolean.TRUE.equals(source.getPause()));
		target.setAuthoritative(java.lang.Boolean.TRUE.equals(source.getAuthoritative()));
		if (source.getTimeStamp() == null) {
			target.setTimeStamp(null);
		} else {
			target.setTimeStamp(java.util.Calendar.getInstance());
			target.getTimeStamp().setTime(source.getTimeStamp());
		}
		if (source.getCreated() == null) {
			target.setCreated(null);
		} else {
			target.setCreated(java.util.Calendar.getInstance());
			target.getCreated().setTime(source.getCreated());
		}
		target.setAuthoritativeProcess(source.getAuthoritativeProcess());
		target.setSharedDispatcher(source.getSharedDispatcher());
		target.setThreads(source.getThreads());
		target.setTimeout(source.getTimeout());
		target.setLongTimeout(source.getLongTimeout());
		target.setUsage(source.getUsage());
		target.setDeltaChanges(source.getDeltaChanges());
		target.setRemoveRolesFromDisabledAccounts(source.getRemoveRolesFromDisabledAccounts());
		target.setCreateDisabledAccounts(source.getCreateDisabledAccounts());
		// Incompatible types source.tenant and target.tenant
		// Missing attribute attributes on entity
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
		target.setCreatedOn(source.getCreatedOn());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.System} object 
	 */
	public com.soffid.iam.iga.api.System toSystem(com.soffid.iam.iga.model.SystemEntity entity) {
		final com.soffid.iam.iga.api.System target = new com.soffid.iam.iga.api.System();
		this.toSystem(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.System} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.System> toSystemList (java.util.Collection<com.soffid.iam.iga.model.SystemEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.System> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.System>();
			for (final com.soffid.iam.iga.model.SystemEntity instance: instances)
			{
				list.add( toSystem(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.System} object 
	 */
	public void systemToEntity (com.soffid.iam.iga.api.System source, com.soffid.iam.iga.model.SystemEntity target, boolean copyIfNull) {
		// Attributes for SystemEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getClassName() != null)
		{
			target.setClassName(source.getClassName());
		}
		if (copyIfNull || source.getUrl() != null)
		{
			target.setUrl(source.getUrl());
		}
		if (copyIfNull || source.getUrl2() != null)
		{
			target.setUrl2(source.getUrl2());
		}
		// Missing attribute param0 on entity
		// Missing attribute param1 on entity
		// Missing attribute param2 on entity
		// Missing attribute param3 on entity
		// Missing attribute param4 on entity
		// Missing attribute param5 on entity
		// Missing attribute param6 on entity
		// Missing attribute param7 on entity
		// Missing attribute param8 on entity
		// Missing attribute param9 on entity
		// Missing attribute roleBased on entity
		if (copyIfNull || source.getTrusted() != null)
		{
			// Incompatible types source.trusted and target.trusted
		}
		if (copyIfNull || source.getManualAccountCreation() != null)
		{
			target.setManualAccountCreation(source.getManualAccountCreation());
		}
		target.setFullReconciliation(new java.lang.Boolean(source.isFullReconciliation()));
		target.setGenerateTasksOnLoad(new java.lang.Boolean(source.isGenerateTasksOnLoad()));
		if (copyIfNull || source.getTenant() != null)
		{
			// Incompatible types source.tenant and target.tenant
		}
		// Missing attribute role on entity
		// Missing attribute userType on entity
		// Missing attribute systemGroup on entity
		// Missing attribute enableAccessControl on entity
		// Missing attribute accessControls on entity
		// Missing attribute passwordDomain on entity
		// Missing attribute userDomain on entity
		// Missing attribute accounts on entity
		// Missing attribute mainSystem on entity
		target.setReadOnly(source.isReadOnly());
		target.setPause(new java.lang.Boolean(source.isPause()));
		target.setAuthoritative(new java.lang.Boolean(source.isAuthoritative()));
		// Missing attribute blobParam on entity
		if (copyIfNull || source.getTimeStamp() != null)
		{
			if (source.getTimeStamp() == null) {
				target.setTimeStamp(null);
			} else {
				target.setTimeStamp(source.getTimeStamp().getTime());
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
		// Missing attribute objectMappings on entity
		if (copyIfNull || source.getAuthoritativeProcess() != null)
		{
			target.setAuthoritativeProcess(source.getAuthoritativeProcess());
		}
		if (copyIfNull || source.getSharedDispatcher() != null)
		{
			target.setSharedDispatcher(source.getSharedDispatcher());
		}
		if (copyIfNull || source.getThreads() != null)
		{
			target.setThreads(source.getThreads());
		}
		// Missing attribute pendingChanges on entity
		if (copyIfNull || source.getTimeout() != null)
		{
			target.setTimeout(source.getTimeout());
		}
		if (copyIfNull || source.getLongTimeout() != null)
		{
			target.setLongTimeout(source.getLongTimeout());
		}
		if (copyIfNull || source.getUsage() != null)
		{
			target.setUsage(source.getUsage());
		}
		if (copyIfNull || source.getDeltaChanges() != null)
		{
			target.setDeltaChanges(source.getDeltaChanges());
		}
		if (copyIfNull || source.getRemoveRolesFromDisabledAccounts() != null)
		{
			target.setRemoveRolesFromDisabledAccounts(source.getRemoveRolesFromDisabledAccounts());
		}
		if (copyIfNull || source.getCreateDisabledAccounts() != null)
		{
			target.setCreateDisabledAccounts(source.getCreateDisabledAccounts());
		}
		// Missing attribute entryPoints on entity
		// Missing attribute metaData on entity
		// Missing attribute reconcileTriggers on entity
		// Missing attribute hosts on entity
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
	 *  Transforms from {@link com.soffid.iam.iga.api.System} object 
	 */
	public com.soffid.iam.iga.model.SystemEntity systemToEntity (com.soffid.iam.iga.api.System instance) {
		com.soffid.iam.iga.model.SystemEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newSystemEntity();
		systemToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.System} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity>  systemToEntityList (java.util.Collection<com.soffid.iam.iga.api.System> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.SystemEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.SystemEntity>();
		for (com.soffid.iam.iga.api.System instance: instances)
		{
			list.add (systemToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.SystemEntity} .
	 */
	public com.soffid.iam.iga.model.SystemEntity newSystemEntity()
	{
		return new com.soffid.iam.iga.model.SystemEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.SystemEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.SystemEntity result = (com.soffid.iam.iga.model.SystemEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.SystemEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.SystemEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.SystemEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.SystemEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.SystemEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SystemEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.iga.model.SystemEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.SystemEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SystemEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.SystemEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SystemEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.SystemEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.SystemEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SystemEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.SystemEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.SystemEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.SystemEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SystemEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.SystemEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.SystemEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SystemEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.SystemEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"SystemEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.SystemEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.SystemEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.SystemEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
