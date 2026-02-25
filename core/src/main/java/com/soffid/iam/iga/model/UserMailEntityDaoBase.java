//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity UserMailEntity
 */
public abstract class UserMailEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.UserMailEntityDao
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


	/**
	 * Operation findByListAndUser
	 * @param mailList
	 * @param domain
	 * @param user
	 * @return
	**/
	public com.soffid.iam.iga.model.UserMailEntity findByListAndUser(
	    java.lang.String mailList, 
	    java.lang.String domain, 
	    java.lang.String user)
	
	{
		return findByListAndUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, mailList, domain, user);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.UserMailEntity findByListAndUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String mailList, java.lang.String domain, java.lang.String user)
	
	{
		return findByListAndUser("select liu from com.soffid.iam.iga.model.UserMailEntity liu left join liu.mailList.domain as dominiCorreu where liu.mailList.name = :mailList and   liu.mailList.tenant.id = :tenantId and  ((:domain is null and dominiCorreu is null) or  ( :domain is not null and dominiCorreu is not null and dominiCorreu.name = :domain)) and liu.user.userName = :user and liu.disabled = false",
			criteria, mailList, domain, user);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.UserMailEntity findByListAndUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String mailList, java.lang.String domain, java.lang.String user)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("mailList", mailList, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domain", domain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.UserMailEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.UserMailEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.UserMailEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUser
	 * @param user
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> findByUser(
	    java.lang.String user)
	
	{
		return findByUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, user);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> findByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	
	{
		return findByUser("select liu from  com.soffid.iam.iga.model.UserMailEntity liu where liu.user.userName = :user and liu.user.tenant.id = :tenantId and liu.disabled = false order by liu.mailList.name, liu.mailList.domain.name",
			criteria, user);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> findByUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	
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
			return (java.util.List<com.soffid.iam.iga.model.UserMailEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByMailList
	 * @param mailList
	 * @param domain
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> findByMailList(
	    java.lang.String mailList, 
	    java.lang.String domain)
	
	{
		return findByMailList((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, mailList, domain);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> findByMailList(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String mailList, java.lang.String domain)
	
	{
		return findByMailList("select liu from  com.soffid.iam.iga.model.UserMailEntity liu left join liu.mailList.domain as dominiCorreu where liu.mailList.name = :mailList and liu.mailList.tenant.id = :tenantId and liu.disabled = false and ((:domain is null and dominiCorreu is null) or  ( :domain is not null and dominiCorreu is not null and dominiCorreu.name = :domain))",
			criteria, mailList, domain);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> findByMailList(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String mailList, java.lang.String domain)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("mailList", mailList, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("domain", domain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.UserMailEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.UserMailList} object 
	 */
	public void toUserMailList(com.soffid.iam.iga.model.UserMailEntity source, com.soffid.iam.iga.api.UserMailList target) {
		// Attributes for UserMailList
		// Missing attribute mailListName on entity
		// Missing attribute mailListDescription on entity
		// Missing attribute userCode on entity
		// Missing attribute fullName on entity
		// Missing attribute domainCode on entity
		target.setId(source.getId());
		target.setStart(source.getStart());
		target.setEnd(source.getEnd());
		target.setDisabled(source.getDisabled());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserMailList} object 
	 */
	public com.soffid.iam.iga.api.UserMailList toUserMailList(com.soffid.iam.iga.model.UserMailEntity entity) {
		final com.soffid.iam.iga.api.UserMailList target = new com.soffid.iam.iga.api.UserMailList();
		this.toUserMailList(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserMailList} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.UserMailList> toUserMailListList (java.util.Collection<com.soffid.iam.iga.model.UserMailEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.UserMailList> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.UserMailList>();
			for (final com.soffid.iam.iga.model.UserMailEntity instance: instances)
			{
				list.add( toUserMailList(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.UserMailList} object 
	 */
	public void userMailListToEntity (com.soffid.iam.iga.api.UserMailList source, com.soffid.iam.iga.model.UserMailEntity target, boolean copyIfNull) {
		// Attributes for UserMailEntity
		// Missing attribute mailList on entity
		// Missing attribute user on entity
		if (copyIfNull || source.getStart() != null)
		{
			target.setStart(source.getStart());
		}
		if (copyIfNull || source.getEnd() != null)
		{
			target.setEnd(source.getEnd());
		}
		if (copyIfNull || source.getDisabled() != null)
		{
			target.setDisabled(source.getDisabled());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserMailList} object 
	 */
	public com.soffid.iam.iga.model.UserMailEntity userMailListToEntity (com.soffid.iam.iga.api.UserMailList instance) {
		com.soffid.iam.iga.model.UserMailEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newUserMailEntity();
		userMailListToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserMailList} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity>  userMailListToEntityList (java.util.Collection<com.soffid.iam.iga.api.UserMailList> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.UserMailEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.UserMailEntity>();
		for (com.soffid.iam.iga.api.UserMailList instance: instances)
		{
			list.add (userMailListToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.UserMailEntity} .
	 */
	public com.soffid.iam.iga.model.UserMailEntity newUserMailEntity()
	{
		return new com.soffid.iam.iga.model.UserMailEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.UserMailEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.UserMailEntity result = (com.soffid.iam.iga.model.UserMailEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.UserMailEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.UserMailEntity> result = (java.util.List<com.soffid.iam.iga.model.UserMailEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.UserMailEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.UserMailEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.UserMailEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserMailEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.UserMailEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.UserMailEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserMailEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.UserMailEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserMailEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.UserMailEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.UserMailEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserMailEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserMailEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.UserMailEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.UserMailEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserMailEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserMailEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.UserMailEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserMailEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserMailEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"UserMailEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.UserMailEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.UserMailEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.UserMailEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
