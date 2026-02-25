//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity MailListEntity
 */
public abstract class MailListEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.MailListEntityDao
{
	com.soffid.iam.iga.service.ApplicationService applicationService;

	/**
	 * Sets reference to <code>applicationService</code>.
	 */
	public void setApplicationService (com.soffid.iam.iga.service.ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	/**
	 * Gets reference to <code>applicationService</code>.
	 */
	public com.soffid.iam.iga.service.ApplicationService getApplicationService () {
		return applicationService;
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

	com.soffid.iam.iga.model.ExternalNameEntityDao externalNameEntityDao;

	/**
	 * Sets reference to <code>externalNameEntityDao</code>.
	 */
	public void setExternalNameEntityDao (com.soffid.iam.iga.model.ExternalNameEntityDao externalNameEntityDao) {
		this.externalNameEntityDao = externalNameEntityDao;
	}

	/**
	 * Gets reference to <code>externalNameEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ExternalNameEntityDao getExternalNameEntityDao () {
		return externalNameEntityDao;
	}

	com.soffid.iam.iga.service.GroupService groupService;

	/**
	 * Sets reference to <code>groupService</code>.
	 */
	public void setGroupService (com.soffid.iam.iga.service.GroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * Gets reference to <code>groupService</code>.
	 */
	public com.soffid.iam.iga.service.GroupService getGroupService () {
		return groupService;
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

	com.soffid.iam.iga.model.MailListAttributeEntityDao mailListAttributeEntityDao;

	/**
	 * Sets reference to <code>mailListAttributeEntityDao</code>.
	 */
	public void setMailListAttributeEntityDao (com.soffid.iam.iga.model.MailListAttributeEntityDao mailListAttributeEntityDao) {
		this.mailListAttributeEntityDao = mailListAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>mailListAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListAttributeEntityDao getMailListAttributeEntityDao () {
		return mailListAttributeEntityDao;
	}

	com.soffid.iam.iga.model.MailListContainerEntityDao mailListContainerEntityDao;

	/**
	 * Sets reference to <code>mailListContainerEntityDao</code>.
	 */
	public void setMailListContainerEntityDao (com.soffid.iam.iga.model.MailListContainerEntityDao mailListContainerEntityDao) {
		this.mailListContainerEntityDao = mailListContainerEntityDao;
	}

	/**
	 * Gets reference to <code>mailListContainerEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListContainerEntityDao getMailListContainerEntityDao () {
		return mailListContainerEntityDao;
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

	com.soffid.iam.iga.model.MailListGroupMemberEntityDao mailListGroupMemberEntityDao;

	/**
	 * Sets reference to <code>mailListGroupMemberEntityDao</code>.
	 */
	public void setMailListGroupMemberEntityDao (com.soffid.iam.iga.model.MailListGroupMemberEntityDao mailListGroupMemberEntityDao) {
		this.mailListGroupMemberEntityDao = mailListGroupMemberEntityDao;
	}

	/**
	 * Gets reference to <code>mailListGroupMemberEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListGroupMemberEntityDao getMailListGroupMemberEntityDao () {
		return mailListGroupMemberEntityDao;
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
	 * Operation findByNameAndDomain
	 * @param name
	 * @param domain
	 * @return
	**/
	public com.soffid.iam.iga.model.MailListEntity findByNameAndDomain(
	    java.lang.String name, 
	    java.lang.String domain)
	
	{
		return findByNameAndDomain((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, domain);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.MailListEntity findByNameAndDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String domain)
	
	{
		return findByNameAndDomain("select llistaCorreu from com.soffid.iam.iga.model.MailListEntity llistaCorreu left join llistaCorreu.domain as dominiCorreu where llistaCorreu.name = :name and (( :domain is null and dominiCorreu is null) or  ( :domain is not null and  dominiCorreu is not null and  dominiCorreu.name = :domain)) and llistaCorreu.tenant.id = :tenantId and (llistaCorreu.deleted is null or llistaCorreu.deleted is false) order by dominiCorreu.name, llistaCorreu.name",
			criteria, name, domain);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.MailListEntity findByNameAndDomain(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String domain)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domain", domain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.MailListEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.MailListEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.MailListEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByNameAndDomainDeleted
	 * @param name
	 * @param domain
	 * @return
	**/
	public com.soffid.iam.iga.model.MailListEntity findByNameAndDomainDeleted(
	    java.lang.String name, 
	    java.lang.String domain)
	
	{
		return findByNameAndDomainDeleted((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, domain);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.MailListEntity findByNameAndDomainDeleted(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String domain)
	
	{
		return findByNameAndDomainDeleted("select llistaCorreu from com.soffid.iam.iga.model.MailListEntity llistaCorreu left join llistaCorreu.domain as dominiCorreu where llistaCorreu.name = :name and (( :domain is null and dominiCorreu is null) or  ( :domain is not null and  dominiCorreu is not null and  dominiCorreu.name = :domain)) and llistaCorreu.tenant.id = :tenantId order by dominiCorreu.name, llistaCorreu.name",
			criteria, name, domain);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.MailListEntity findByNameAndDomainDeleted(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String domain)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domain", domain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.MailListEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.MailListEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.MailListEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByData
	 * @param name
	 * @param domain
	 * @param description
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> findByData(
	    java.lang.String name, 
	    java.lang.String domain, 
	    java.lang.String description)
	
	{
		return findByData((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, domain, description);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> findByData(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String domain, java.lang.String description)
	
	{
		return findByData("select llistaCorreu from com.soffid.iam.iga.model.MailListEntity llistaCorreu left join llistaCorreu.domain as domini where (:domain is null or domini.name like :domain) and (:description is null or llistaCorreu.description like :description) and (:name is null or llistaCorreu.name like :name) and llistaCorreu.tenant.id = :tenantId and (llistaCorreu.deleted is null or llistaCorreu.deleted is false) order by llistaCorreu.name, llistaCorreu.domain.name",
			criteria, name, domain, description);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> findByData(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String domain, java.lang.String description)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domain", domain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("description", description, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.MailListEntity>) results;
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
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.iga.model.MailListEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.MailListEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.iga.model.MailListEntity#	 * @see com.soffid.iam.iga.model.MailListEntity#void generateUpdateTasks(com.soffid.iam.iga.model.MailListEntity entity)
	 */
	public void generateUpdateTasks(
		com.soffid.iam.iga.model.MailListEntity entity)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (entity == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.MailListEntity.generateUpdateTasks(com.soffid.iam.iga.model.MailListEntity entity) - entity cannot be null");
		}
		try
		{
			handleGenerateUpdateTasks(entity);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.model.MailListEntity.class).
				warn ("Error on MailListEntity.generateUpdateTasks", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on MailListEntity.generateUpdateTasks: "+th.toString(), th);
		}
	}

	protected abstract void handleGenerateUpdateTasks(com.soffid.iam.iga.model.MailListEntity entity) throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.MailList} object 
	 */
	public void toMailList(com.soffid.iam.iga.model.MailListEntity source, com.soffid.iam.iga.api.MailList target) {
		// Attributes for MailList
		target.setName(source.getName());
		// Missing attribute domainName on entity
		target.setDescription(source.getDescription());
		target.setId(source.getId());
		// Missing attribute lists on entity
		// Missing attribute externalList on entity
		// Missing attribute roleMembers on entity
		// Missing attribute groupMembers on entity
		// Missing attribute usersList on entity
		// Incompatible types source.attributes and target.attributes
		// Missing attribute listsBelong on entity
		// Missing attribute explodedUsersList on entity
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
		target.setDeleted(source.getDeleted());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailList} object 
	 */
	public com.soffid.iam.iga.api.MailList toMailList(com.soffid.iam.iga.model.MailListEntity entity) {
		final com.soffid.iam.iga.api.MailList target = new com.soffid.iam.iga.api.MailList();
		this.toMailList(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailList} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.MailList> toMailListList (java.util.Collection<com.soffid.iam.iga.model.MailListEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.MailList> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.MailList>();
			for (final com.soffid.iam.iga.model.MailListEntity instance: instances)
			{
				list.add( toMailList(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.MailList} object 
	 */
	public void mailListToEntity (com.soffid.iam.iga.api.MailList source, com.soffid.iam.iga.model.MailListEntity target, boolean copyIfNull) {
		// Attributes for MailListEntity
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		// Missing attribute externals on entity
		// Missing attribute domain on entity
		// Missing attribute tenant on entity
		// Missing attribute userMailLists on entity
		// Missing attribute mailListPertain on entity
		// Missing attribute mailListContent on entity
		if (copyIfNull || source.getAttributes() != null)
		{
			// Incompatible types source.attributes and target.attributes
		}
		// Missing attribute groups on entity
		// Missing attribute roles on entity
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
	 *  Transforms from {@link com.soffid.iam.iga.api.MailList} object 
	 */
	public com.soffid.iam.iga.model.MailListEntity mailListToEntity (com.soffid.iam.iga.api.MailList instance) {
		com.soffid.iam.iga.model.MailListEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newMailListEntity();
		mailListToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.MailList} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity>  mailListToEntityList (java.util.Collection<com.soffid.iam.iga.api.MailList> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.MailListEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.MailListEntity>();
		for (com.soffid.iam.iga.api.MailList instance: instances)
		{
			list.add (mailListToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.MailListEntity} .
	 */
	public com.soffid.iam.iga.model.MailListEntity newMailListEntity()
	{
		return new com.soffid.iam.iga.model.MailListEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.MailListEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.MailListEntity result = (com.soffid.iam.iga.model.MailListEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.MailListEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.MailListEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.MailListEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.MailListEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.MailListEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MailListEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.iga.model.MailListEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.MailListEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MailListEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.MailListEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MailListEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.MailListEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.MailListEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MailListEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MailListEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.MailListEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.MailListEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MailListEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MailListEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.MailListEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MailListEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MailListEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"MailListEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.MailListEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.MailListEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.MailListEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
