//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity InformationSystemEntity
 */
public abstract class InformationSystemEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.InformationSystemEntityDao
{
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

	com.soffid.iam.iga.model.InformationSystemAttributeEntityDao informationSystemAttributeEntityDao;

	/**
	 * Sets reference to <code>informationSystemAttributeEntityDao</code>.
	 */
	public void setInformationSystemAttributeEntityDao (com.soffid.iam.iga.model.InformationSystemAttributeEntityDao informationSystemAttributeEntityDao) {
		this.informationSystemAttributeEntityDao = informationSystemAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>informationSystemAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.InformationSystemAttributeEntityDao getInformationSystemAttributeEntityDao () {
		return informationSystemAttributeEntityDao;
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

	com.soffid.iam.rc.model.SoDRuleEntityDao soDRuleEntityDao;

	/**
	 * Sets reference to <code>soDRuleEntityDao</code>.
	 */
	public void setSoDRuleEntityDao (com.soffid.iam.rc.model.SoDRuleEntityDao soDRuleEntityDao) {
		this.soDRuleEntityDao = soDRuleEntityDao;
	}

	/**
	 * Gets reference to <code>soDRuleEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.SoDRuleEntityDao getSoDRuleEntityDao () {
		return soDRuleEntityDao;
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


	/**
	 * Operation findByCode
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.InformationSystemEntity findByCode(
	    java.lang.String name)
	
	{
		return findByCode((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity findByCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByCode("from com.soffid.iam.iga.model.InformationSystemEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity findByCode(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.iga.model.InformationSystemEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.InformationSystemEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.InformationSystemEntity) results.iterator().next();
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
	public java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> findByText(
	    java.lang.String text)
	
	{
		return findByText((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, text);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> findByText(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String text)
	
	{
		return findByText("from com.soffid.iam.base.model.AccountEntity  where :text is null",
			criteria, text);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> findByText(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String text)
	
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
			return (java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUser
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> findByUser(
	    java.lang.String userName)
	
	{
		return findByUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> findByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findByUser("select distinct aplicacio\nfrom com.soffid.iam.base.model.UserEntity as usuari\njoin usuari.accounts as accounts\njoin accounts.account as account\njoin account.roles as roles\njoin roles.role as rol\njoin rol.informationSystem as aplicacio \nwhere usuari.userName = :userName \nand aplicacio.tenant.id = :tenantId",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> findByUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
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
			return (java.util.List<com.soffid.iam.iga.model.InformationSystemEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findManageableByUser
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> findManageableByUser(
	    java.lang.String userName)
	
	{
		return findManageableByUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> findManageableByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findManageableByUser("select distinct aplicacio\nfrom com.soffid.iam.base.model.UserEntity as usuari\njoin usuari.accounts as accounts\njoin accounts.account as account\njoin account.roles as roles\njoin roles.role as rol\njoin rol.informationSystem as aplicacio with aplicacio.bpmEnabled='S'\nwhere usuari.userName = :userName \nand aplicacio.tenant.id = :tenantId",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> findManageableByUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
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
			return (java.util.List<com.soffid.iam.iga.model.InformationSystemEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public void toDomainValue(com.soffid.iam.iga.model.InformationSystemEntity source, com.soffid.iam.iga.api.DomainValue target) {
		// Attributes for DomainValue
		target.setId(source.getId());
		// Missing attribute value on entity
		target.setDescription(source.getDescription());
		// Missing attribute domainName on entity
		// Missing attribute informationSystemName on entity
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public com.soffid.iam.iga.api.DomainValue toDomainValue(com.soffid.iam.iga.model.InformationSystemEntity entity) {
		final com.soffid.iam.iga.api.DomainValue target = new com.soffid.iam.iga.api.DomainValue();
		this.toDomainValue(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.DomainValue} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.DomainValue> toDomainValueList (java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.DomainValue> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.DomainValue>();
			for (final com.soffid.iam.iga.model.InformationSystemEntity instance: instances)
			{
				list.add( toDomainValue(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public void domainValueToEntity (com.soffid.iam.iga.api.DomainValue source, com.soffid.iam.iga.model.InformationSystemEntity target, boolean copyIfNull) {
		// Attributes for InformationSystemEntity
		// Missing attribute name on entity
		// Missing attribute relativeName on entity
		// Missing attribute parent on entity
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		// Missing attribute sourceDir on entity
		// Missing attribute targetDir on entity
		// Missing attribute database on entity
		// Missing attribute roles on entity
		// Missing attribute contactPerson on entity
		// Missing attribute bpmEnabled on entity
		// Missing attribute notificationEmails on entity
		// Missing attribute approvalProcess on entity
		// Missing attribute tenant on entity
		// Missing attribute roleDefinitionProcess on entity
		// Missing attribute singleRole on entity
		// Missing attribute type on entity
		// Missing attribute sodRules on entity
		// Missing attribute attributes on entity
		// Missing attribute children on entity
		// Missing attribute roleScopeMailLists on entity
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
	 *  Transforms from {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity domainValueToEntity (com.soffid.iam.iga.api.DomainValue instance) {
		com.soffid.iam.iga.model.InformationSystemEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newInformationSystemEntity();
		domainValueToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.DomainValue} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity>  domainValueToEntityList (java.util.Collection<com.soffid.iam.iga.api.DomainValue> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.InformationSystemEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.InformationSystemEntity>();
		for (com.soffid.iam.iga.api.DomainValue instance: instances)
		{
			list.add (domainValueToEntity(instance));
		}
		return list;
	}

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.InformationSystem} object 
	 */
	public void toInformationSystem(com.soffid.iam.iga.model.InformationSystemEntity source, com.soffid.iam.iga.api.InformationSystem target) {
		// Attributes for InformationSystem
		target.setId(source.getId());
		target.setType(source.getType());
		// Incompatible types source.parent and target.parent
		target.setRelativeName(source.getRelativeName());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		// Missing attribute owner on entity
		target.setSourceDir(source.getSourceDir());
		target.setTargetDir(source.getTargetDir());
		target.setDatabase(source.getDatabase());
		target.setBpmEnabled(source.getBpmEnabled());
		target.setNotificationEmails(source.getNotificationEmails());
		target.setApprovalProcess(source.getApprovalProcess());
		target.setRoleDefinitionProcess(source.getRoleDefinitionProcess());
		target.setSingleRole(java.lang.Boolean.TRUE.equals(source.getSingleRole()));
		// Incompatible types source.attributes and target.attributes
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.InformationSystem} object 
	 */
	public com.soffid.iam.iga.api.InformationSystem toInformationSystem(com.soffid.iam.iga.model.InformationSystemEntity entity) {
		final com.soffid.iam.iga.api.InformationSystem target = new com.soffid.iam.iga.api.InformationSystem();
		this.toInformationSystem(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.InformationSystem} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.InformationSystem> toInformationSystemList (java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.InformationSystem> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.InformationSystem>();
			for (final com.soffid.iam.iga.model.InformationSystemEntity instance: instances)
			{
				list.add( toInformationSystem(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.InformationSystem} object 
	 */
	public void informationSystemToEntity (com.soffid.iam.iga.api.InformationSystem source, com.soffid.iam.iga.model.InformationSystemEntity target, boolean copyIfNull) {
		// Attributes for InformationSystemEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getRelativeName() != null)
		{
			target.setRelativeName(source.getRelativeName());
		}
		if (copyIfNull || source.getParent() != null)
		{
			// Incompatible types source.parent and target.parent
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getSourceDir() != null)
		{
			target.setSourceDir(source.getSourceDir());
		}
		if (copyIfNull || source.getTargetDir() != null)
		{
			target.setTargetDir(source.getTargetDir());
		}
		if (copyIfNull || source.getDatabase() != null)
		{
			target.setDatabase(source.getDatabase());
		}
		// Missing attribute roles on entity
		// Missing attribute contactPerson on entity
		if (copyIfNull || source.getBpmEnabled() != null)
		{
			target.setBpmEnabled(source.getBpmEnabled());
		}
		if (copyIfNull || source.getNotificationEmails() != null)
		{
			target.setNotificationEmails(source.getNotificationEmails());
		}
		if (copyIfNull || source.getApprovalProcess() != null)
		{
			target.setApprovalProcess(source.getApprovalProcess());
		}
		// Missing attribute tenant on entity
		if (copyIfNull || source.getRoleDefinitionProcess() != null)
		{
			target.setRoleDefinitionProcess(source.getRoleDefinitionProcess());
		}
		target.setSingleRole(new java.lang.Boolean(source.isSingleRole()));
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		// Missing attribute sodRules on entity
		if (copyIfNull || source.getAttributes() != null)
		{
			// Incompatible types source.attributes and target.attributes
		}
		// Missing attribute children on entity
		// Missing attribute roleScopeMailLists on entity
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
	 *  Transforms from {@link com.soffid.iam.iga.api.InformationSystem} object 
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity informationSystemToEntity (com.soffid.iam.iga.api.InformationSystem instance) {
		com.soffid.iam.iga.model.InformationSystemEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newInformationSystemEntity();
		informationSystemToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.InformationSystem} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity>  informationSystemToEntityList (java.util.Collection<com.soffid.iam.iga.api.InformationSystem> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.InformationSystemEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.InformationSystemEntity>();
		for (com.soffid.iam.iga.api.InformationSystem instance: instances)
		{
			list.add (informationSystemToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.InformationSystemEntity} .
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity newInformationSystemEntity()
	{
		return new com.soffid.iam.iga.model.InformationSystemEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.InformationSystemEntity result = (com.soffid.iam.iga.model.InformationSystemEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.InformationSystemEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.InformationSystemEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.InformationSystemEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.InformationSystemEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"InformationSystemEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.iga.model.InformationSystemEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.InformationSystemEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"InformationSystemEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.InformationSystemEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"InformationSystemEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.InformationSystemEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.InformationSystemEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"InformationSystemEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.InformationSystemEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.InformationSystemEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.InformationSystemEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"InformationSystemEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.InformationSystemEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.InformationSystemEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"InformationSystemEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.InformationSystemEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"InformationSystemEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.InformationSystemEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.InformationSystemEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.InformationSystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.InformationSystemEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
