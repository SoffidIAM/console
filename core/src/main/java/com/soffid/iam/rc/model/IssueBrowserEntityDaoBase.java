//
// (c) 2014 Soffid
//
//
package com.soffid.iam.rc.model;
/**
 * DAO Base for Entity IssueBrowserEntity
 */
public abstract class IssueBrowserEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.rc.model.IssueBrowserEntityDao
{
	com.soffid.iam.am.model.BrowserEntityDao browserEntityDao;

	/**
	 * Sets reference to <code>browserEntityDao</code>.
	 */
	public void setBrowserEntityDao (com.soffid.iam.am.model.BrowserEntityDao browserEntityDao) {
		this.browserEntityDao = browserEntityDao;
	}

	/**
	 * Gets reference to <code>browserEntityDao</code>.
	 */
	public com.soffid.iam.am.model.BrowserEntityDao getBrowserEntityDao () {
		return browserEntityDao;
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
	 *  Copy data to {@link com.soffid.iam.rc.api.IssueBrowser} object 
	 */
	public void toIssueBrowser(com.soffid.iam.rc.model.IssueBrowserEntity source, com.soffid.iam.rc.api.IssueBrowser target) {
		// Attributes for IssueBrowser
		target.setAction(source.getAction());
		// Missing attribute browserId on entity
		// Missing attribute type on entity
		target.setHostIp(source.getHostIp());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssueBrowser} object 
	 */
	public com.soffid.iam.rc.api.IssueBrowser toIssueBrowser(com.soffid.iam.rc.model.IssueBrowserEntity entity) {
		final com.soffid.iam.rc.api.IssueBrowser target = new com.soffid.iam.rc.api.IssueBrowser();
		this.toIssueBrowser(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssueBrowser} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.IssueBrowser> toIssueBrowserList (java.util.Collection<com.soffid.iam.rc.model.IssueBrowserEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.rc.api.IssueBrowser> list =
				new java.util.LinkedList<com.soffid.iam.rc.api.IssueBrowser>();
			for (final com.soffid.iam.rc.model.IssueBrowserEntity instance: instances)
			{
				list.add( toIssueBrowser(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.IssueBrowser} object 
	 */
	public void issueBrowserToEntity (com.soffid.iam.rc.api.IssueBrowser source, com.soffid.iam.rc.model.IssueBrowserEntity target, boolean copyIfNull) {
		// Attributes for IssueBrowserEntity
		// Missing attribute issue on entity
		// Missing attribute browser on entity
		if (copyIfNull || source.getAction() != null)
		{
			target.setAction(source.getAction());
		}
		if (copyIfNull || source.getHostIp() != null)
		{
			target.setHostIp(source.getHostIp());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssueBrowser} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueBrowserEntity>  issueBrowserToEntityList (java.util.Collection<com.soffid.iam.rc.api.IssueBrowser> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.rc.model.IssueBrowserEntity> list =
			new java.util.LinkedList<com.soffid.iam.rc.model.IssueBrowserEntity>();
		for (com.soffid.iam.rc.api.IssueBrowser instance: instances)
		{
			list.add (issueBrowserToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.IssueBrowserEntity} .
	 */
	public com.soffid.iam.rc.model.IssueBrowserEntity newIssueBrowserEntity()
	{
		return new com.soffid.iam.rc.model.IssueBrowserEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.IssueBrowserEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.rc.model.IssueBrowserEntity result = (com.soffid.iam.rc.model.IssueBrowserEntity) this.getHibernateTemplate().get(com.soffid.iam.rc.model.IssueBrowserEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueBrowserEntity> loadAll() {
		java.util.List<com.soffid.iam.rc.model.IssueBrowserEntity> result = (java.util.List<com.soffid.iam.rc.model.IssueBrowserEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.rc.model.IssueBrowserEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.IssueBrowserEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.IssueBrowserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssueBrowserEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.IssueBrowserEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.IssueBrowserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssueBrowserEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.IssueBrowserEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssueBrowserEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.IssueBrowserEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.IssueBrowserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssueBrowserEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssueBrowserEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.IssueBrowserEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.IssueBrowserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssueBrowserEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssueBrowserEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.IssueBrowserEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssueBrowserEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssueBrowserEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"IssueBrowserEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.rc.model.IssueBrowserEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueBrowserEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.rc.model.IssueBrowserEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueBrowserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueBrowserEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.rc.model.IssueBrowserEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
