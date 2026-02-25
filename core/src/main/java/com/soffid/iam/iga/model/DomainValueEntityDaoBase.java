//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity DomainValueEntity
 */
public abstract class DomainValueEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.DomainValueEntityDao
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
	 * Operation findByApplicationDomainValue
	 * @param app
	 * @param domain
	 * @param value
	 * @return
	**/
	public com.soffid.iam.iga.model.DomainValueEntity findByApplicationDomainValue(
	    java.lang.String app, 
	    java.lang.String domain, 
	    java.lang.String value)
	
	{
		return findByApplicationDomainValue((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, app, domain, value);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.DomainValueEntity findByApplicationDomainValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String app, java.lang.String domain, java.lang.String value)
	
	{
		return findByApplicationDomainValue("select value from com.soffid.iam.iga.model.DomainValueEntity as value \nleft join value.domain as domain\nleft join domain.informationSystem as app\nwhere \napp.name = :app and app.tenant.id = :tenantId and domain.name = :domain and value.value = :value",
			criteria, app, domain, value);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.DomainValueEntity findByApplicationDomainValue(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String app, java.lang.String domain, java.lang.String value)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("app", app, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domain", domain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("value", value, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.DomainValueEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.DomainValueEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.DomainValueEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByRoleAndValue
	 * @param roleId
	 * @param value
	 * @return
	**/
	public com.soffid.iam.iga.model.DomainValueEntity findByRoleAndValue(
	    java.lang.Long roleId, 
	    java.lang.String value)
	
	{
		return findByRoleAndValue((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, roleId, value);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.DomainValueEntity findByRoleAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long roleId, java.lang.String value)
	
	{
		return findByRoleAndValue("select valorDominiAplicacio from com.soffid.iam.iga.model.DomainValueEntity valorDominiAplicacio \njoin valorDominiAplicacio.domain domini\njoin valorDominiAplicacio.domain.roles rol\nwhere \nrol.id = :roleId and \nvalorDominiAplicacio.value = :value",
			criteria, roleId, value);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.DomainValueEntity findByRoleAndValue(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long roleId, java.lang.String value)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("roleId", roleId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("value", value, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.DomainValueEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.DomainValueEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.DomainValueEntity) results.iterator().next();
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
	 * @param domain
	 * @param text
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.DomainValueEntity> findByText(
	    com.soffid.iam.iga.api.Domain domain, 
	    java.lang.String text)
	
	{
		return findByText((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, domain, text);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.DomainValueEntity> findByText(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.api.Domain domain, java.lang.String text)
	
	{
		return findByText("-",
			criteria, domain, text);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.DomainValueEntity> findByText(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.api.Domain domain, java.lang.String text)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("domain", domain);
			queryObject.setParameter("text", text, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.DomainValueEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByInformationSystem
	 * @param informationSystem
	 * @param domain
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity> findByInformationSystem(
	    java.lang.String informationSystem, 
	    java.lang.String domain)
	
	{
		return findByInformationSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, informationSystem, domain);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity> findByInformationSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem, java.lang.String domain)
	
	{
		return findByInformationSystem("select value from com.soffid.iam.iga.model.DomainValueEntity as value \nleft join value.domain as domain\nleft join domain.informationSystem as app\nwhere \napp.name = :informationSystem and app.tenant.id = :tenantId and\ndomain.name = :domain",
			criteria, informationSystem, domain);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity> findByInformationSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem, java.lang.String domain)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("informationSystem", informationSystem, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domain", domain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.DomainValueEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public void toDomainValue(com.soffid.iam.iga.model.DomainValueEntity source, com.soffid.iam.iga.api.DomainValue target) {
		// Attributes for DomainValue
		target.setId(source.getId());
		target.setValue(source.getValue());
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
	public com.soffid.iam.iga.api.DomainValue toDomainValue(com.soffid.iam.iga.model.DomainValueEntity entity) {
		final com.soffid.iam.iga.api.DomainValue target = new com.soffid.iam.iga.api.DomainValue();
		this.toDomainValue(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.DomainValue} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.DomainValue> toDomainValueList (java.util.Collection<com.soffid.iam.iga.model.DomainValueEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.DomainValue> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.DomainValue>();
			for (final com.soffid.iam.iga.model.DomainValueEntity instance: instances)
			{
				list.add( toDomainValue(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public void domainValueToEntity (com.soffid.iam.iga.api.DomainValue source, com.soffid.iam.iga.model.DomainValueEntity target, boolean copyIfNull) {
		// Attributes for DomainValueEntity
		if (copyIfNull || source.getValue() != null)
		{
			target.setValue(source.getValue());
		}
		// Missing attribute domain on entity
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		// Missing attribute tenant on entity
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
	public com.soffid.iam.iga.model.DomainValueEntity domainValueToEntity (com.soffid.iam.iga.api.DomainValue instance) {
		com.soffid.iam.iga.model.DomainValueEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newDomainValueEntity();
		domainValueToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.DomainValue} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity>  domainValueToEntityList (java.util.Collection<com.soffid.iam.iga.api.DomainValue> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.DomainValueEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.DomainValueEntity>();
		for (com.soffid.iam.iga.api.DomainValue instance: instances)
		{
			list.add (domainValueToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.DomainValueEntity} .
	 */
	public com.soffid.iam.iga.model.DomainValueEntity newDomainValueEntity()
	{
		return new com.soffid.iam.iga.model.DomainValueEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.DomainValueEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.DomainValueEntity result = (com.soffid.iam.iga.model.DomainValueEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.DomainValueEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.DomainValueEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.DomainValueEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.DomainValueEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.DomainValueEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DomainValueEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.iga.model.DomainValueEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.DomainValueEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DomainValueEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.DomainValueEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DomainValueEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.DomainValueEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.DomainValueEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DomainValueEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.DomainValueEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.DomainValueEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.DomainValueEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DomainValueEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.DomainValueEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.DomainValueEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DomainValueEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.DomainValueEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"DomainValueEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.DomainValueEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.DomainValueEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.DomainValueEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.DomainValueEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.DomainValueEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
