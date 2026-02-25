//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity ExternalNameEntity
 */
public abstract class ExternalNameEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.ExternalNameEntityDao
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


	/**
	 * Operation findByAddress
	 * @param address
	 * @return
	**/
	public com.soffid.iam.iga.model.ExternalNameEntity findByAddress(
	    java.lang.String address)
	
	{
		return findByAddress((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, address);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.ExternalNameEntity findByAddress(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String address)
	
	{
		return findByAddress("select extern \nfrom com.soffid.iam.iga.model.MailListEntity llicor left join llicor.externals as extern where extern.address = :address and  llicor.tenant.id = :tenantId",
			criteria, address);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.ExternalNameEntity findByAddress(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String address)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("address", address, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.ExternalNameEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.ExternalNameEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.ExternalNameEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByList
	 * @param listName
	 * @param listDomain
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity> findByList(
	    java.lang.String listName, 
	    java.lang.String listDomain)
	
	{
		return findByList((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, listName, listDomain);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity> findByList(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String listName, java.lang.String listDomain)
	
	{
		return findByList("select llicor.externals as extern \nfrom com.soffid.iam.iga.model.MailListEntity llicor left join llicor.domain as dominiCorreu where (:listName is null or llicor.name = :listName) and  llicor.tenant.id = :tenantId and ( (:listDomain is null and dominiCorreu is null) or (:listDomain is not null and dominiCorreu is not null and dominiCorreu.name = :listDomain))",
			criteria, listName, listDomain);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity> findByList(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String listName, java.lang.String listDomain)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("listName", listName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("listDomain", listDomain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.ExternalNameEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ExternalName} object 
	 */
	public void toExternalName(com.soffid.iam.iga.model.ExternalNameEntity source, com.soffid.iam.iga.api.ExternalName target) {
		// Attributes for ExternalName
		// Missing attribute email on entity
		// Missing attribute mailListName on entity
		// Missing attribute domainCode on entity
		target.setId(source.getId());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ExternalName} object 
	 */
	public com.soffid.iam.iga.api.ExternalName toExternalName(com.soffid.iam.iga.model.ExternalNameEntity entity) {
		final com.soffid.iam.iga.api.ExternalName target = new com.soffid.iam.iga.api.ExternalName();
		this.toExternalName(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ExternalName} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ExternalName> toExternalNameList (java.util.Collection<com.soffid.iam.iga.model.ExternalNameEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.ExternalName> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.ExternalName>();
			for (final com.soffid.iam.iga.model.ExternalNameEntity instance: instances)
			{
				list.add( toExternalName(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ExternalName} object 
	 */
	public void externalNameToEntity (com.soffid.iam.iga.api.ExternalName source, com.soffid.iam.iga.model.ExternalNameEntity target, boolean copyIfNull) {
		// Attributes for ExternalNameEntity
		// Missing attribute address on entity
		// Missing attribute mailList on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ExternalName} object 
	 */
	public com.soffid.iam.iga.model.ExternalNameEntity externalNameToEntity (com.soffid.iam.iga.api.ExternalName instance) {
		com.soffid.iam.iga.model.ExternalNameEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newExternalNameEntity();
		externalNameToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ExternalName} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity>  externalNameToEntityList (java.util.Collection<com.soffid.iam.iga.api.ExternalName> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.ExternalNameEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.ExternalNameEntity>();
		for (com.soffid.iam.iga.api.ExternalName instance: instances)
		{
			list.add (externalNameToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ExternalNameEntity} .
	 */
	public com.soffid.iam.iga.model.ExternalNameEntity newExternalNameEntity()
	{
		return new com.soffid.iam.iga.model.ExternalNameEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ExternalNameEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.ExternalNameEntity result = (com.soffid.iam.iga.model.ExternalNameEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.ExternalNameEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.ExternalNameEntity> result = (java.util.List<com.soffid.iam.iga.model.ExternalNameEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.ExternalNameEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ExternalNameEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ExternalNameEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ExternalNameEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ExternalNameEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ExternalNameEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ExternalNameEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ExternalNameEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ExternalNameEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ExternalNameEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ExternalNameEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ExternalNameEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ExternalNameEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ExternalNameEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ExternalNameEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ExternalNameEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ExternalNameEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ExternalNameEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ExternalNameEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ExternalNameEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ExternalNameEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.ExternalNameEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.ExternalNameEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ExternalNameEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ExternalNameEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.ExternalNameEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
