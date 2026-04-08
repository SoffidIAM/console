//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity RoleEntity
 */
public abstract class RoleEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.RoleEntityDao
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

	com.soffid.iam.base.service.AdditionalDataService additionalDataService;

	/**
	 * Sets reference to <code>additionalDataService</code>.
	 */
	public void setAdditionalDataService (com.soffid.iam.base.service.AdditionalDataService additionalDataService) {
		this.additionalDataService = additionalDataService;
	}

	/**
	 * Gets reference to <code>additionalDataService</code>.
	 */
	public com.soffid.iam.base.service.AdditionalDataService getAdditionalDataService () {
		return additionalDataService;
	}

	com.soffid.iam.iga.model.ApplicationDomainEntityDao applicationDomainEntityDao;

	/**
	 * Sets reference to <code>applicationDomainEntityDao</code>.
	 */
	public void setApplicationDomainEntityDao (com.soffid.iam.iga.model.ApplicationDomainEntityDao applicationDomainEntityDao) {
		this.applicationDomainEntityDao = applicationDomainEntityDao;
	}

	/**
	 * Gets reference to <code>applicationDomainEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntityDao getApplicationDomainEntityDao () {
		return applicationDomainEntityDao;
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

	com.soffid.iam.base.model.AuthorizationEntityDao authorizationEntityDao;

	/**
	 * Sets reference to <code>authorizationEntityDao</code>.
	 */
	public void setAuthorizationEntityDao (com.soffid.iam.base.model.AuthorizationEntityDao authorizationEntityDao) {
		this.authorizationEntityDao = authorizationEntityDao;
	}

	/**
	 * Gets reference to <code>authorizationEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AuthorizationEntityDao getAuthorizationEntityDao () {
		return authorizationEntityDao;
	}

	com.soffid.iam.bpm.service.BpmEngine bpmEngine;

	/**
	 * Sets reference to <code>bpmEngine</code>.
	 */
	public void setBpmEngine (com.soffid.iam.bpm.service.BpmEngine bpmEngine) {
		this.bpmEngine = bpmEngine;
	}

	/**
	 * Gets reference to <code>bpmEngine</code>.
	 */
	public com.soffid.iam.bpm.service.BpmEngine getBpmEngine () {
		return bpmEngine;
	}

	com.soffid.iam.iga.model.CustomObjectRoleEntityDao customObjectRoleEntityDao;

	/**
	 * Sets reference to <code>customObjectRoleEntityDao</code>.
	 */
	public void setCustomObjectRoleEntityDao (com.soffid.iam.iga.model.CustomObjectRoleEntityDao customObjectRoleEntityDao) {
		this.customObjectRoleEntityDao = customObjectRoleEntityDao;
	}

	/**
	 * Gets reference to <code>customObjectRoleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.CustomObjectRoleEntityDao getCustomObjectRoleEntityDao () {
		return customObjectRoleEntityDao;
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

	com.soffid.iam.iga.model.MailListRoleMemberEntityDao mailListRoleMemberEntityDao;

	/**
	 * Sets reference to <code>mailListRoleMemberEntityDao</code>.
	 */
	public void setMailListRoleMemberEntityDao (com.soffid.iam.iga.model.MailListRoleMemberEntityDao mailListRoleMemberEntityDao) {
		this.mailListRoleMemberEntityDao = mailListRoleMemberEntityDao;
	}

	/**
	 * Gets reference to <code>mailListRoleMemberEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListRoleMemberEntityDao getMailListRoleMemberEntityDao () {
		return mailListRoleMemberEntityDao;
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

	com.soffid.iam.iga.model.RoleAttributeEntityDao roleAttributeEntityDao;

	/**
	 * Sets reference to <code>roleAttributeEntityDao</code>.
	 */
	public void setRoleAttributeEntityDao (com.soffid.iam.iga.model.RoleAttributeEntityDao roleAttributeEntityDao) {
		this.roleAttributeEntityDao = roleAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>roleAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleAttributeEntityDao getRoleAttributeEntityDao () {
		return roleAttributeEntityDao;
	}

	com.soffid.iam.iga.model.RoleDependencyEntityDao roleDependencyEntityDao;

	/**
	 * Sets reference to <code>roleDependencyEntityDao</code>.
	 */
	public void setRoleDependencyEntityDao (com.soffid.iam.iga.model.RoleDependencyEntityDao roleDependencyEntityDao) {
		this.roleDependencyEntityDao = roleDependencyEntityDao;
	}

	/**
	 * Gets reference to <code>roleDependencyEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleDependencyEntityDao getRoleDependencyEntityDao () {
		return roleDependencyEntityDao;
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

	com.soffid.iam.iga.model.RuleAssignedRoleEntityDao ruleAssignedRoleEntityDao;

	/**
	 * Sets reference to <code>ruleAssignedRoleEntityDao</code>.
	 */
	public void setRuleAssignedRoleEntityDao (com.soffid.iam.iga.model.RuleAssignedRoleEntityDao ruleAssignedRoleEntityDao) {
		this.ruleAssignedRoleEntityDao = ruleAssignedRoleEntityDao;
	}

	/**
	 * Gets reference to <code>ruleAssignedRoleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RuleAssignedRoleEntityDao getRuleAssignedRoleEntityDao () {
		return ruleAssignedRoleEntityDao;
	}

	com.soffid.iam.rc.model.SoDRoleEntityDao soDRoleEntityDao;

	/**
	 * Sets reference to <code>soDRoleEntityDao</code>.
	 */
	public void setSoDRoleEntityDao (com.soffid.iam.rc.model.SoDRoleEntityDao soDRoleEntityDao) {
		this.soDRoleEntityDao = soDRoleEntityDao;
	}

	/**
	 * Gets reference to <code>soDRoleEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.SoDRoleEntityDao getSoDRoleEntityDao () {
		return soDRoleEntityDao;
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


	protected org.apache.commons.collections.map.LRUMap mapRole = new org.apache.commons.collections.map.LRUMap(300);
	protected int mapRoleTimeout = 5000;
	/**
	 * @see com.soffid.iam.iga.model.RoleEntity#	 * @see com.soffid.iam.iga.model.RoleEntity#com.soffid.iam.iga.model.RoleEntity create(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles)
	 */
	public com.soffid.iam.iga.model.RoleEntity create(
		com.soffid.iam.iga.api.Role role, 
		boolean updateOwnedRoles)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (role == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.model.RoleEntity com.soffid.iam.iga.model.RoleEntity.create(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) - role cannot be null");
		}
		if (role.getName() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.model.RoleEntity com.soffid.iam.iga.model.RoleEntity.create(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) - role.name cannot be null");
		}
		if (role.getKey() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.model.RoleEntity com.soffid.iam.iga.model.RoleEntity.create(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) - role.key cannot be null");
		}
		if (role.getDescription() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.model.RoleEntity com.soffid.iam.iga.model.RoleEntity.create(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) - role.description cannot be null");
		}
		if (role.getSystem() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.model.RoleEntity com.soffid.iam.iga.model.RoleEntity.create(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) - role.system cannot be null");
		}
		if (role.getInformationSystemName() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.model.RoleEntity com.soffid.iam.iga.model.RoleEntity.create(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) - role.informationSystemName cannot be null");
		}
		try
		{
			return handleCreate(role, updateOwnedRoles);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.model.RoleEntity.class).
				warn ("Error on RoleEntity.create", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on RoleEntity.create: "+th.toString(), th);
		}
	}

	protected abstract com.soffid.iam.iga.model.RoleEntity handleCreate(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) throws Exception;

	/**
	 * Operation findByExternalIdAndDispatcher
	 * @param externalId
	 * @param system
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity findByExternalIdAndDispatcher(
	    java.lang.String externalId, 
	    java.lang.String system)
	
	{
		return findByExternalIdAndDispatcher((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, externalId, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.RoleEntity findByExternalIdAndDispatcher(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId, java.lang.String system)
	
	{
		return findByExternalIdAndDispatcher("select rolEntity \nfrom com.soffid.iam.iga.model.RoleEntity rolEntity \nwhere rolEntity.externalId = :externalId and\nrolEntity.system.name = :system and rolEntity.system.tenant.id = :tenantId and (rolEntity.deleted is null or rolEntity.deleted is false)",
			criteria, externalId, system);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.RoleEntity findByExternalIdAndDispatcher(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("externalId", externalId, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.RoleEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.RoleEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.RoleEntity) results.iterator().next();
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
	public com.soffid.iam.iga.model.RoleEntity findById(
	    java.lang.Long id)
	
	{
		return findById((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, id);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.RoleEntity findById(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		return findById("from com.soffid.iam.iga.model.RoleEntity where system.tenant.id=:tenantId and id=:id",
			criteria, id);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.RoleEntity findById(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
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
			com.soffid.iam.iga.model.RoleEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.RoleEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.RoleEntity) results.iterator().next();
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
	 * @param roleName
	 * @param system
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity findByNameAndSystem(
	    java.lang.String roleName, 
	    java.lang.String system)
	
	{
		return findByNameAndSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, roleName, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.RoleEntity findByNameAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String system)
	
	{
		return findByNameAndSystem("select rolEntity \nfrom com.soffid.iam.iga.model.RoleEntity rolEntity \nwhere rolEntity.name = :roleName and\nrolEntity.system.name = :system and (rolEntity.deleted is null or rolEntity.deleted is false) and rolEntity.system.tenant.id = :tenantId and rolEntity.deletedOn is null",
			criteria, roleName, system);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.RoleEntity findByNameAndSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("roleName", roleName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.RoleEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.RoleEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.RoleEntity) results.iterator().next();
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
	 * @param roleName
	 * @param system
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity findByNameAndSystemDeleted(
	    java.lang.String roleName, 
	    java.lang.String system)
	
	{
		return findByNameAndSystemDeleted((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, roleName, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.RoleEntity findByNameAndSystemDeleted(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String system)
	
	{
		return findByNameAndSystemDeleted("select rolEntity \nfrom com.soffid.iam.iga.model.RoleEntity rolEntity \nwhere rolEntity.name = :roleName and\nrolEntity.system.name = :system",
			criteria, roleName, system);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.RoleEntity findByNameAndSystemDeleted(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("roleName", roleName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.RoleEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.RoleEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.RoleEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findRoleByNameInformationSystemAndStystem
	 * @param roleName
	 * @param informationSystem
	 * @param system
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity findRoleByNameInformationSystemAndStystem(
	    java.lang.String roleName, 
	    java.lang.String informationSystem, 
	    java.lang.String system)
	
	{
		return findRoleByNameInformationSystemAndStystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, roleName, informationSystem, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.RoleEntity findRoleByNameInformationSystemAndStystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String informationSystem, java.lang.String system)
	
	{
		return findRoleByNameInformationSystemAndStystem("select rolEntity \nfrom com.soffid.iam.iga.model.RoleEntity rolEntity \nwhere \nrolEntity.informationSystem.name = :informationSystem and\nrolEntity.name = :roleName and rolEntity.system.tenant.id = :tenantId and \n(rolEntity.deleted is null or rolEntity.deleted is false) and rolEntity.system.name = :system",
			criteria, roleName, informationSystem, system);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.RoleEntity findRoleByNameInformationSystemAndStystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String informationSystem, java.lang.String system)
	
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
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.RoleEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.RoleEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.RoleEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByShortName
	 * @param shortName
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity findByShortName(
	    java.lang.String shortName)
	
	{
		return findByShortName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, shortName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.RoleEntity findByShortName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String shortName)
	
	{
		return findByShortName("select r from com.soffid.iam.iga.model.RoleEntity as r where key = :shortName and  (r.deleted is null or r.deleted is false)",
			criteria, shortName);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.RoleEntity findByShortName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String shortName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("shortName", shortName, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.RoleEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.RoleEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.RoleEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.iga.model.RoleEntity#	 * @see com.soffid.iam.iga.model.RoleEntity#com.soffid.iam.iga.model.RoleEntity update(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles)
	 */
	public com.soffid.iam.iga.model.RoleEntity update(
		com.soffid.iam.iga.api.Role role, 
		boolean updateOwnedRoles)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (role == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.model.RoleEntity com.soffid.iam.iga.model.RoleEntity.update(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) - role cannot be null");
		}
		if (role.getName() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.model.RoleEntity com.soffid.iam.iga.model.RoleEntity.update(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) - role.name cannot be null");
		}
		if (role.getKey() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.model.RoleEntity com.soffid.iam.iga.model.RoleEntity.update(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) - role.key cannot be null");
		}
		if (role.getDescription() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.model.RoleEntity com.soffid.iam.iga.model.RoleEntity.update(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) - role.description cannot be null");
		}
		if (role.getSystem() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.model.RoleEntity com.soffid.iam.iga.model.RoleEntity.update(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) - role.system cannot be null");
		}
		if (role.getInformationSystemName() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.model.RoleEntity com.soffid.iam.iga.model.RoleEntity.update(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) - role.informationSystemName cannot be null");
		}
		try
		{
			return handleUpdate(role, updateOwnedRoles);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.model.RoleEntity.class).
				warn ("Error on RoleEntity.update", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on RoleEntity.update: "+th.toString(), th);
		}
	}

	protected abstract com.soffid.iam.iga.model.RoleEntity handleUpdate(com.soffid.iam.iga.api.Role role, boolean updateOwnedRoles) throws Exception;

	/**
	 * Operation findApplicationManagementRoles
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.RoleEntity> findApplicationManagementRoles(
)
	throws com.soffid.iam.exception.InternalErrorException
	{
		return findApplicationManagementRoles((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleEntity> findApplicationManagementRoles(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	throws com.soffid.iam.exception.InternalErrorException
	{
		return findApplicationManagementRoles("select u from com.soffid.iam.iga.model.RoleEntity as u where (u.domainType = 'APLICACIONS' or u.domainType = 'APPLICATIONS') and  u.id in (select e.role.id from com.soffid.iam.base.model.AuthorizationEntity e) and  (u.deleted is null or u.deleted is false) and  u.system.tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleEntity> findApplicationManagementRoles(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	throws com.soffid.iam.exception.InternalErrorException
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
			return (java.util.Collection<com.soffid.iam.iga.model.RoleEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findGroupManagementRoles
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.RoleEntity> findGroupManagementRoles(
)
	throws com.soffid.iam.exception.InternalErrorException
	{
		return findGroupManagementRoles((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleEntity> findGroupManagementRoles(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	throws com.soffid.iam.exception.InternalErrorException
	{
		return findGroupManagementRoles("select distinct u from com.soffid.iam.iga.model.RoleEntity as u where (u.domainType = 'GRUPS' or u.domainType='GRUPS_USUARI' or u.domainType='GROUPS' or u.domainType='MEMBERSHIPS') and  u.id in (select e.role.id from com.soffid.iam.base.model.AuthorizationEntity e) and  (u.deleted is null or u.deleted is false) and  u.system.tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleEntity> findGroupManagementRoles(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	throws com.soffid.iam.exception.InternalErrorException
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
			return (java.util.Collection<com.soffid.iam.iga.model.RoleEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByInformationSystem
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByInformationSystem(
	    java.lang.String informationSystem)
	
	{
		return findByInformationSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, informationSystem);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByInformationSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	
	{
		return findByInformationSystem("select role \nfrom com.soffid.iam.iga.model.RoleEntity role \nwhere role.informationSystem.name = :informationSystem and       role.informationSystem.tenant.id = :tenantId       and (role.deleted is null or role.deleted is false) \norder by role.name, role.system.name",
			criteria, informationSystem);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByInformationSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	
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
			return (java.util.List<com.soffid.iam.iga.model.RoleEntity>) results;
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
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByExternalId(
	    java.lang.String externalId)
	
	{
		return findByExternalId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, externalId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByExternalId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId)
	
	{
		return findByExternalId("from com.soffid.iam.iga.model.RoleEntity where system.tenant.id=:tenantId and externalId=:externalId",
			criteria, externalId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByExternalId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId)
	
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
			return (java.util.List<com.soffid.iam.iga.model.RoleEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByInformationSystemAndDomain
	 * @param informationSystem
	 * @param domainName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByInformationSystemAndDomain(
	    java.lang.String informationSystem, 
	    java.lang.String domainName)
	
	{
		return findByInformationSystemAndDomain((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, informationSystem, domainName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByInformationSystemAndDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem, java.lang.String domainName)
	
	{
		return findByInformationSystemAndDomain("select rol from com.soffid.iam.iga.model.RoleEntity rol join rol.applicationDomain domini left join domini.informationSystem aplicacio where domini.name = :domainName and (rol.deleted is null or rol.deleted is false) and ((:informationSystem is null and aplicacio is null) or (aplicacio.name = :informationSystem)) and aplicacio.tenant.id = :tenantId",
			criteria, informationSystem, domainName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByInformationSystemAndDomain(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem, java.lang.String domainName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("informationSystem", informationSystem, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domainName", domainName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findRoleNames
	 * @param system
	 * @return
	**/
	public java.util.List<java.lang.String> findRoleNames(
	    java.lang.String system)
	
	{
		return findRoleNames((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<java.lang.String> findRoleNames(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	
	{
		return findRoleNames("select u.name from com.soffid.iam.iga.model.RoleEntity as u join u.system as s where s.name = :system and (u.deleted is null or u.deleted is false)",
			criteria, system);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<java.lang.String> findRoleNames(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
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
	 * Operation findRolesByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findRolesByUserName(
	    java.lang.String userName)
	
	{
		return findRolesByUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findRolesByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findRolesByUserName("select rol \nfrom com.soffid.iam.base.model.UserEntity usu\n join usu.accounts as accounts\n join accounts.account as account with account.type='U'\n join account.roles as roles\n join roles.role as rol\nwhere usu.userName = :userName and usu.tenant.id = :tenantId       and roles.enabled is true       and (rol.deleted is null or rol.deleted is false)",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findRolesByUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
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
			return (java.util.List<com.soffid.iam.iga.model.RoleEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findApplicationRolesByUserAndInformationSystem
	 * @param userName
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findApplicationRolesByUserAndInformationSystem(
	    java.lang.String userName, 
	    java.lang.String informationSystem)
	
	{
		return findApplicationRolesByUserAndInformationSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName, informationSystem);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findApplicationRolesByUserAndInformationSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String informationSystem)
	
	{
		return findApplicationRolesByUserAndInformationSystem("select role \nfrom com.soffid.iam.base.model.UserEntity user\n join user.accounts as accounts\n join accounts.account as account with account.type='U'\n join account.roles as roles\n join roles.role as role\n join role.informationSystem as informationSystem\nwhere user.userName = :userName and informationSystem.name = :informationSystem       and (role.deleted is null or role.deleted is false) \nand user.tenant.id = :tenantId",
			criteria, userName, informationSystem);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findApplicationRolesByUserAndInformationSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String informationSystem)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("informationSystem", informationSystem, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.iga.model.RoleEntity#	 * @see com.soffid.iam.iga.model.RoleEntity#void commitDefinition(com.soffid.iam.iga.model.RoleEntity role)
	 */
	public void commitDefinition(
		com.soffid.iam.iga.model.RoleEntity role)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (role == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleEntity.commitDefinition(com.soffid.iam.iga.model.RoleEntity role) - role cannot be null");
		}
		try
		{
			handleCommitDefinition(role);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.model.RoleEntity.class).
				warn ("Error on RoleEntity.commitDefinition", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on RoleEntity.commitDefinition: "+th.toString(), th);
		}
	}

	protected abstract void handleCommitDefinition(com.soffid.iam.iga.model.RoleEntity role) throws Exception;

	/**
	 * @see com.soffid.iam.iga.model.RoleEntity#	 * @see com.soffid.iam.iga.model.RoleEntity#void remove(com.soffid.iam.iga.api.Role role)
	 */
	public void remove(
		com.soffid.iam.iga.api.Role role)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (role == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleEntity.remove(com.soffid.iam.iga.api.Role role) - role cannot be null");
		}
		if (role.getName() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleEntity.remove(com.soffid.iam.iga.api.Role role) - role.name cannot be null");
		}
		if (role.getKey() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleEntity.remove(com.soffid.iam.iga.api.Role role) - role.key cannot be null");
		}
		if (role.getDescription() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleEntity.remove(com.soffid.iam.iga.api.Role role) - role.description cannot be null");
		}
		if (role.getSystem() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleEntity.remove(com.soffid.iam.iga.api.Role role) - role.system cannot be null");
		}
		if (role.getInformationSystemName() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleEntity.remove(com.soffid.iam.iga.api.Role role) - role.informationSystemName cannot be null");
		}
		try
		{
			handleRemove(role);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.model.RoleEntity.class).
				warn ("Error on RoleEntity.remove", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on RoleEntity.remove: "+th.toString(), th);
		}
	}

	protected abstract void handleRemove(com.soffid.iam.iga.api.Role role) throws Exception;

	/**
	 * @see com.soffid.iam.iga.model.RoleEntity#	 * @see com.soffid.iam.iga.model.RoleEntity#void rollbackDefinition(com.soffid.iam.iga.model.RoleEntity role)
	 */
	public void rollbackDefinition(
		com.soffid.iam.iga.model.RoleEntity role)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (role == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleEntity.rollbackDefinition(com.soffid.iam.iga.model.RoleEntity role) - role cannot be null");
		}
		try
		{
			handleRollbackDefinition(role);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.model.RoleEntity.class).
				warn ("Error on RoleEntity.rollbackDefinition", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on RoleEntity.rollbackDefinition: "+th.toString(), th);
		}
	}

	protected abstract void handleRollbackDefinition(com.soffid.iam.iga.model.RoleEntity role) throws Exception;

	/**
	 * @see com.soffid.iam.iga.model.RoleEntity#	 * @see com.soffid.iam.iga.model.RoleEntity#void updateMailLists(com.soffid.iam.iga.model.RoleEntity role)
	 */
	public void updateMailLists(
		com.soffid.iam.iga.model.RoleEntity role)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (role == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.RoleEntity.updateMailLists(com.soffid.iam.iga.model.RoleEntity role) - role cannot be null");
		}
		try
		{
			handleUpdateMailLists(role);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.model.RoleEntity.class).
				warn ("Error on RoleEntity.updateMailLists", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on RoleEntity.updateMailLists: "+th.toString(), th);
		}
	}

	protected abstract void handleUpdateMailLists(com.soffid.iam.iga.model.RoleEntity role) throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.Identity} object 
	 */
	public void toIdentity(com.soffid.iam.iga.model.RoleEntity source, com.soffid.iam.base.api.Identity target) {
		// Attributes for Identity
		// Missing attribute userCode on entity
		// Missing attribute groupCode on entity
		// Missing attribute roleName on entity
		target.setDescription(source.getDescription());
		// Missing attribute identityCode on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Identity} object 
	 */
	public com.soffid.iam.base.api.Identity toIdentity(com.soffid.iam.iga.model.RoleEntity entity) {
		final com.soffid.iam.base.api.Identity target = new com.soffid.iam.base.api.Identity();
		this.toIdentity(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Identity} list 
	 */
	public java.util.List<com.soffid.iam.base.api.Identity> toIdentityList (java.util.Collection<com.soffid.iam.iga.model.RoleEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.Identity> list =
				new java.util.LinkedList<com.soffid.iam.base.api.Identity>();
			for (final com.soffid.iam.iga.model.RoleEntity instance: instances)
			{
				list.add( toIdentity(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.Identity} object 
	 */
	public void identityToEntity (com.soffid.iam.base.api.Identity source, com.soffid.iam.iga.model.RoleEntity target, boolean copyIfNull) {
		// Attributes for RoleEntity
		// Missing attribute name on entity
		// Missing attribute key on entity
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		// Missing attribute category on entity
		// Missing attribute enableByDefault on entity
		// Missing attribute password on entity
		// Missing attribute informationSystem on entity
		// Missing attribute accounts on entity
		// Missing attribute system on entity
		// Missing attribute applicationDomain on entity
		// Missing attribute domainType on entity
		// Missing attribute ownerRoles on entity
		// Missing attribute ownedRoles on entity
		// Missing attribute containerGroups on entity
		// Missing attribute networkAuthorization on entity
		// Missing attribute accessControl on entity
		// Missing attribute manageableWF on entity
		// Missing attribute notificationEntities on entity
		// Missing attribute authorizations on entity
		// Missing attribute accountAccess on entity
		// Missing attribute rules on entity
		// Missing attribute sodRules on entity
		// Missing attribute approvalProcess on entity
		// Missing attribute approvalStart on entity
		// Missing attribute approvalEnd on entity
		// Missing attribute externalId on entity
		// Missing attribute customObjects on entity
		// Missing attribute mailLists on entity
		// Missing attribute attributes on entity
		// Missing attribute createdOn on entity
		// Missing attribute createdBy on entity
		// Missing attribute updatedOn on entity
		// Missing attribute updatedBy on entity
		// Missing attribute deletedOn on entity
		// Missing attribute deletedBy on entity
		// Missing attribute deleted on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Identity} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity>  identityToEntityList (java.util.Collection<com.soffid.iam.base.api.Identity> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.RoleEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.RoleEntity>();
		for (com.soffid.iam.base.api.Identity instance: instances)
		{
			list.add (identityToEntity(instance));
		}
		return list;
	}

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.Role} object 
	 */
	public void toRole(com.soffid.iam.iga.model.RoleEntity source, com.soffid.iam.iga.api.Role target) {
		// Attributes for Role
		target.setId(source.getId());
		target.setName(source.getName());
		target.setKey(source.getKey());
		target.setDescription(source.getDescription());
		// Incompatible types source.system and target.system
		target.setCategory(source.getCategory());
		target.setEnableByDefault(source.getEnableByDefault());
		// Incompatible types source.password and target.password
		// Missing attribute informationSystemName on entity
		target.setDomainType(source.getDomainType());
		// Incompatible types source.ownerRoles and target.ownerRoles
		// Missing attribute ownerGroups on entity
		// Missing attribute granteeGroups on entity
		// Incompatible types source.ownedRoles and target.ownedRoles
		// Missing attribute bpmEnabled on entity
		target.setExternalId(source.getExternalId());
		target.setApprovalStart(source.getApprovalStart());
		target.setApprovalEnd(source.getApprovalEnd());
		// Incompatible types source.attributes and target.attributes
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
		target.setDeleted(source.getDeleted());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Role} object 
	 */
	/**
	 *  Stores {@link com.soffid.iam.iga.api.Role} in cache 
	 */
	protected synchronized void storeRoleCacheEntry (java.lang.Long id, com.soffid.iam.iga.api.Role role)
	{
		RoleCacheEntry entry = new RoleCacheEntry ();
		entry.role = new com.soffid.iam.iga.api.Role(role);
		entry.timeStamp = System.currentTimeMillis();
		mapRole.put(com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id, entry);
	}

	/**
	 *  Retrieves {@link com.soffid.iam.iga.api.Role} from cache 
	 */
	protected synchronized com.soffid.iam.iga.api.Role getRoleCacheEntry (java.lang.Long id)
	{
		RoleCacheEntry entry = (RoleCacheEntry) mapRole.get (com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id);
		if (entry == null) return null;
		if (entry.timeStamp + mapRoleTimeout < System.currentTimeMillis())
		{
			mapRole.remove (com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id);
			return null;
		}
		return new com.soffid.iam.iga.api.Role(entry.role);
	}

	/**
	 *  Removes {@link com.soffid.iam.iga.api.Role} from cache 
	 */
	protected synchronized void removeRoleCacheEntry (java.lang.Long id)
	{
		mapRole.remove (com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id);
	}

	public com.soffid.iam.iga.api.Role toRole(com.soffid.iam.iga.model.RoleEntity entity) {
		com.soffid.iam.iga.api.Role target = es.caib.seycon.ng.utils.Security.isSyncServer() ? 
			null : 
			getRoleCacheEntry(entity.getId());
		if (target != null)
			return target;
		else
		{
			target = new com.soffid.iam.iga.api.Role();
			this.toRole(entity, target);
			if (!es.caib.seycon.ng.utils.Security.isSyncServer() )
				storeRoleCacheEntry(entity.getId(), target);
			return target;
		}
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Role} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.Role> toRoleList (java.util.Collection<com.soffid.iam.iga.model.RoleEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.Role> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.Role>();
			for (final com.soffid.iam.iga.model.RoleEntity instance: instances)
			{
				list.add( toRole(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.Role} object 
	 */
	public void roleToEntity (com.soffid.iam.iga.api.Role source, com.soffid.iam.iga.model.RoleEntity target, boolean copyIfNull) {
		// Attributes for RoleEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getKey() != null)
		{
			target.setKey(source.getKey());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getCategory() != null)
		{
			target.setCategory(source.getCategory());
		}
		if (copyIfNull || source.getEnableByDefault() != null)
		{
			target.setEnableByDefault(source.getEnableByDefault());
		}
		if (copyIfNull || source.getPassword() != null)
		{
			// Incompatible types source.password and target.password
		}
		// Missing attribute informationSystem on entity
		// Missing attribute accounts on entity
		if (copyIfNull || source.getSystem() != null)
		{
			// Incompatible types source.system and target.system
		}
		// Missing attribute applicationDomain on entity
		if (copyIfNull || source.getDomainType() != null)
		{
			target.setDomainType(source.getDomainType());
		}
		if (copyIfNull || source.getOwnerRoles() != null)
		{
			// Incompatible types source.ownerRoles and target.ownerRoles
		}
		if (copyIfNull || source.getOwnedRoles() != null)
		{
			// Incompatible types source.ownedRoles and target.ownedRoles
		}
		// Missing attribute containerGroups on entity
		// Missing attribute networkAuthorization on entity
		// Missing attribute accessControl on entity
		// Missing attribute manageableWF on entity
		// Missing attribute notificationEntities on entity
		// Missing attribute authorizations on entity
		// Missing attribute accountAccess on entity
		// Missing attribute rules on entity
		// Missing attribute sodRules on entity
		// Missing attribute approvalProcess on entity
		if (copyIfNull || source.getApprovalStart() != null)
		{
			target.setApprovalStart(source.getApprovalStart());
		}
		if (copyIfNull || source.getApprovalEnd() != null)
		{
			target.setApprovalEnd(source.getApprovalEnd());
		}
		if (copyIfNull || source.getExternalId() != null)
		{
			target.setExternalId(source.getExternalId());
		}
		// Missing attribute customObjects on entity
		// Missing attribute mailLists on entity
		if (copyIfNull || source.getAttributes() != null)
		{
			// Incompatible types source.attributes and target.attributes
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
		if (copyIfNull || source.getDeleted() != null)
		{
			target.setDeleted(source.getDeleted());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Role} object 
	 */
	public com.soffid.iam.iga.model.RoleEntity roleToEntity (com.soffid.iam.iga.api.Role instance) {
		com.soffid.iam.iga.model.RoleEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newRoleEntity();
		roleToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Role} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity>  roleToEntityList (java.util.Collection<com.soffid.iam.iga.api.Role> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.RoleEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.RoleEntity>();
		for (com.soffid.iam.iga.api.Role instance: instances)
		{
			list.add (roleToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RoleEntity} .
	 */
	public com.soffid.iam.iga.model.RoleEntity newRoleEntity()
	{
		return new com.soffid.iam.iga.model.RoleEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RoleEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.RoleEntity result = (com.soffid.iam.iga.model.RoleEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.RoleEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.RoleEntity where system.tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.RoleEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RoleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleEntityDao.create - 'entity' can not be null");
		}

		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
		removeRoleCacheEntry(entity.getId());
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RoleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
		removeRoleCacheEntry(entity.getId());
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
		removeRoleCacheEntry(entity.getId());
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RoleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RoleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"RoleEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.RoleEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.RoleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.RoleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
class RoleCacheEntry {
	public com.soffid.iam.iga.api.Role role;
	public long timeStamp;
}
