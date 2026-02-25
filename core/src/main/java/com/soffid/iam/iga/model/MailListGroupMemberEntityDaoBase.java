//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity MailListGroupMemberEntity
 */
public abstract class MailListGroupMemberEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.MailListGroupMemberEntityDao
{
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
	 * Operation findByMailListAndGroup
	 * @param mailListId
	 * @param groupId
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> findByMailListAndGroup(
	    long mailListId, 
	    long groupId)
	
	{
		return findByMailListAndGroup((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, mailListId, groupId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> findByMailListAndGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long mailListId, long groupId)
	
	{
		return findByMailListAndGroup("from com.soffid.iam.iga.model.MailListGroupMemberEntity e where e.mailList.id=:mailListId and e.group.id=:groupId and e.group.tenant.id=:tenantId",
			criteria, mailListId, groupId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> findByMailListAndGroup(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long mailListId, long groupId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("mailListId", mailListId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("groupId", groupId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} .
	 */
	public com.soffid.iam.iga.model.MailListGroupMemberEntity newMailListGroupMemberEntity()
	{
		return new com.soffid.iam.iga.model.MailListGroupMemberEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.MailListGroupMemberEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.MailListGroupMemberEntity result = (com.soffid.iam.iga.model.MailListGroupMemberEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.MailListGroupMemberEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListGroupMemberEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.MailListGroupMemberEntity> result = (java.util.List<com.soffid.iam.iga.model.MailListGroupMemberEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.MailListGroupMemberEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.MailListGroupMemberEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MailListGroupMemberEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.MailListGroupMemberEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MailListGroupMemberEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.MailListGroupMemberEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MailListGroupMemberEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.MailListGroupMemberEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MailListGroupMemberEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MailListGroupMemberEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.MailListGroupMemberEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MailListGroupMemberEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MailListGroupMemberEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.MailListGroupMemberEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MailListGroupMemberEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MailListGroupMemberEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"MailListGroupMemberEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.MailListGroupMemberEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListGroupMemberEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.MailListGroupMemberEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListGroupMemberEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListGroupMemberEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.MailListGroupMemberEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
