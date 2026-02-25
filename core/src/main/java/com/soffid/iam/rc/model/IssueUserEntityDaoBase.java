//
// (c) 2014 Soffid
//
//
package com.soffid.iam.rc.model;
/**
 * DAO Base for Entity IssueUserEntity
 */
public abstract class IssueUserEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.rc.model.IssueUserEntityDao
{
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
	 *  Copy data to {@link com.soffid.iam.rc.api.IssueUser} object 
	 */
	public void toIssueUser(com.soffid.iam.rc.model.IssueUserEntity source, com.soffid.iam.rc.api.IssueUser target) {
		// Attributes for IssueUser
		target.setAction(source.getAction());
		// Missing attribute userId on entity
		target.setUserName(source.getUserName());
		target.setExternalId(source.getExternalId());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssueUser} object 
	 */
	public com.soffid.iam.rc.api.IssueUser toIssueUser(com.soffid.iam.rc.model.IssueUserEntity entity) {
		final com.soffid.iam.rc.api.IssueUser target = new com.soffid.iam.rc.api.IssueUser();
		this.toIssueUser(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssueUser} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.IssueUser> toIssueUserList (java.util.Collection<com.soffid.iam.rc.model.IssueUserEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.rc.api.IssueUser> list =
				new java.util.LinkedList<com.soffid.iam.rc.api.IssueUser>();
			for (final com.soffid.iam.rc.model.IssueUserEntity instance: instances)
			{
				list.add( toIssueUser(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.IssueUser} object 
	 */
	public void issueUserToEntity (com.soffid.iam.rc.api.IssueUser source, com.soffid.iam.rc.model.IssueUserEntity target, boolean copyIfNull) {
		// Attributes for IssueUserEntity
		// Missing attribute issue on entity
		// Missing attribute user on entity
		if (copyIfNull || source.getAction() != null)
		{
			target.setAction(source.getAction());
		}
		if (copyIfNull || source.getUserName() != null)
		{
			target.setUserName(source.getUserName());
		}
		if (copyIfNull || source.getExternalId() != null)
		{
			target.setExternalId(source.getExternalId());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssueUser} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueUserEntity>  issueUserToEntityList (java.util.Collection<com.soffid.iam.rc.api.IssueUser> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.rc.model.IssueUserEntity> list =
			new java.util.LinkedList<com.soffid.iam.rc.model.IssueUserEntity>();
		for (com.soffid.iam.rc.api.IssueUser instance: instances)
		{
			list.add (issueUserToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.IssueUserEntity} .
	 */
	public com.soffid.iam.rc.model.IssueUserEntity newIssueUserEntity()
	{
		return new com.soffid.iam.rc.model.IssueUserEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.IssueUserEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.rc.model.IssueUserEntity result = (com.soffid.iam.rc.model.IssueUserEntity) this.getHibernateTemplate().get(com.soffid.iam.rc.model.IssueUserEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueUserEntity> loadAll() {
		java.util.List<com.soffid.iam.rc.model.IssueUserEntity> result = (java.util.List<com.soffid.iam.rc.model.IssueUserEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.rc.model.IssueUserEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.IssueUserEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.IssueUserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssueUserEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.IssueUserEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.IssueUserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssueUserEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.IssueUserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssueUserEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.IssueUserEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.IssueUserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssueUserEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssueUserEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.IssueUserEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.IssueUserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssueUserEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssueUserEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.IssueUserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssueUserEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssueUserEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"IssueUserEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.rc.model.IssueUserEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueUserEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.rc.model.IssueUserEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueUserEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.rc.model.IssueUserEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
