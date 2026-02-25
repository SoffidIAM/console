//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity MailListContainerEntity
 */
public abstract class MailListContainerEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.MailListContainerEntityDao
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
	 * Operation findByContainerAndContained
	 * @param ownerName
	 * @param ownerDomain
	 * @param ownedName
	 * @param ownedDomain
	 * @return
	**/
	public com.soffid.iam.iga.model.MailListContainerEntity findByContainerAndContained(
	    java.lang.String ownerName, 
	    java.lang.String ownerDomain, 
	    java.lang.String ownedName, 
	    java.lang.String ownedDomain)
	
	{
		return findByContainerAndContained((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, ownerName, ownerDomain, ownedName, ownedDomain);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.MailListContainerEntity findByContainerAndContained(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ownerName, java.lang.String ownerDomain, java.lang.String ownedName, java.lang.String ownedDomain)
	
	{
		return findByContainerAndContained("select llistaCorreuR \nfrom com.soffid.iam.iga.model.MailListContainerEntity llistaCorreuR \nleft join llistaCorreuR.pertains.domain as correuDominiPertany \nleft join llistaCorreuR.contains.domain as correuDominiConte \nwhere \nllistaCorreuR.pertains.tenant.id = :tenantId and  llistaCorreuR.pertains.name = :ownerName and \nllistaCorreuR.contains.name = :ownedName and \n((:ownedDomain is null and correuDominiConte is null) or \n (:ownedDomain is not null and correuDominiConte.name = :ownedDomain)) and \n((:ownerDomain is null and correuDominiPertany is null) or \n (:ownerDomain is not null and correuDominiPertany.name = :ownerDomain))",
			criteria, ownerName, ownerDomain, ownedName, ownedDomain);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.MailListContainerEntity findByContainerAndContained(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ownerName, java.lang.String ownerDomain, java.lang.String ownedName, java.lang.String ownedDomain)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("ownerName", ownerName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("ownerDomain", ownerDomain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("ownedName", ownedName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("ownedDomain", ownedDomain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.MailListContainerEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.MailListContainerEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.MailListContainerEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByContained
	 * @param ownedName
	 * @param ownedDomain
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> findByContained(
	    java.lang.String ownedName, 
	    java.lang.String ownedDomain)
	
	{
		return findByContained((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, ownedName, ownedDomain);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> findByContained(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ownedName, java.lang.String ownedDomain)
	
	{
		return findByContained("select llistaCorreuR \nfrom com.soffid.iam.iga.model.MailListContainerEntity llistaCorreuR \nleft join llistaCorreuR.contains.domain as mailDomain \nwhere \n llistaCorreuR.contains.tenant.id = :tenantId and  llistaCorreuR.contains.name = :ownedName and \n((:ownedDomain is null and mailDomain is null) or \n (:ownedDomain is not null and mailDomain.name = :ownedDomain))",
			criteria, ownedName, ownedDomain);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> findByContained(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ownedName, java.lang.String ownedDomain)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("ownedName", ownedName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("ownedDomain", ownedDomain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.MailListContainerEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByContainer
	 * @param ownerName
	 * @param ownerDomain
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> findByContainer(
	    java.lang.String ownerName, 
	    java.lang.String ownerDomain)
	
	{
		return findByContainer((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, ownerName, ownerDomain);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> findByContainer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ownerName, java.lang.String ownerDomain)
	
	{
		return findByContainer("select llistaCorreuR \nfrom com.soffid.iam.iga.model.MailListContainerEntity llistaCorreuR \nleft join llistaCorreuR.pertains.domain as correuDominiPertany \nwhere \n llistaCorreuR.pertains.tenant.id=:tenantId and  llistaCorreuR.pertains.name = :ownerName and \n((:ownerDomain is null and correuDominiPertany is null) or \n (:ownerDomain is not null and correuDominiPertany.name = :ownerDomain))",
			criteria, ownerName, ownerDomain);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> findByContainer(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ownerName, java.lang.String ownerDomain)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("ownerName", ownerName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("ownerDomain", ownerDomain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.MailListContainerEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.MailListRelationship} object 
	 */
	public void toMailListRelationship(com.soffid.iam.iga.model.MailListContainerEntity source, com.soffid.iam.iga.api.MailListRelationship target) {
		// Attributes for MailListRelationship
		// Missing attribute mailListNameBelong on entity
		// Missing attribute mailListNameIncluded on entity
		// Missing attribute mailDomainBelongCode on entity
		// Missing attribute mailDomainAccountCode on entity
		target.setId(source.getId());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailListRelationship} object 
	 */
	public com.soffid.iam.iga.api.MailListRelationship toMailListRelationship(com.soffid.iam.iga.model.MailListContainerEntity entity) {
		final com.soffid.iam.iga.api.MailListRelationship target = new com.soffid.iam.iga.api.MailListRelationship();
		this.toMailListRelationship(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailListRelationship} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.MailListRelationship> toMailListRelationshipList (java.util.Collection<com.soffid.iam.iga.model.MailListContainerEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.MailListRelationship> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.MailListRelationship>();
			for (final com.soffid.iam.iga.model.MailListContainerEntity instance: instances)
			{
				list.add( toMailListRelationship(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.MailListRelationship} object 
	 */
	public void mailListRelationshipToEntity (com.soffid.iam.iga.api.MailListRelationship source, com.soffid.iam.iga.model.MailListContainerEntity target, boolean copyIfNull) {
		// Attributes for MailListContainerEntity
		// Missing attribute contains on entity
		// Missing attribute pertains on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.MailListRelationship} object 
	 */
	public com.soffid.iam.iga.model.MailListContainerEntity mailListRelationshipToEntity (com.soffid.iam.iga.api.MailListRelationship instance) {
		com.soffid.iam.iga.model.MailListContainerEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newMailListContainerEntity();
		mailListRelationshipToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.MailListRelationship} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity>  mailListRelationshipToEntityList (java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.MailListContainerEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.MailListContainerEntity>();
		for (com.soffid.iam.iga.api.MailListRelationship instance: instances)
		{
			list.add (mailListRelationshipToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.MailListContainerEntity} .
	 */
	public com.soffid.iam.iga.model.MailListContainerEntity newMailListContainerEntity()
	{
		return new com.soffid.iam.iga.model.MailListContainerEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.MailListContainerEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.MailListContainerEntity result = (com.soffid.iam.iga.model.MailListContainerEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.MailListContainerEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> result = (java.util.List<com.soffid.iam.iga.model.MailListContainerEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.MailListContainerEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.MailListContainerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.MailListContainerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MailListContainerEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.MailListContainerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.MailListContainerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MailListContainerEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.MailListContainerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MailListContainerEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.MailListContainerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.MailListContainerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MailListContainerEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MailListContainerEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.MailListContainerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.MailListContainerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MailListContainerEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MailListContainerEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.MailListContainerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MailListContainerEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MailListContainerEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"MailListContainerEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.MailListContainerEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.MailListContainerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListContainerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListContainerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.MailListContainerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
