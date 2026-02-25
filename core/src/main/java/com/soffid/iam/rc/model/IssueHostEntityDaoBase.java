//
// (c) 2014 Soffid
//
//
package com.soffid.iam.rc.model;
/**
 * DAO Base for Entity IssueHostEntity
 */
public abstract class IssueHostEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.rc.model.IssueHostEntityDao
{
	com.soffid.iam.am.model.HostEntityDao hostEntityDao;

	/**
	 * Sets reference to <code>hostEntityDao</code>.
	 */
	public void setHostEntityDao (com.soffid.iam.am.model.HostEntityDao hostEntityDao) {
		this.hostEntityDao = hostEntityDao;
	}

	/**
	 * Gets reference to <code>hostEntityDao</code>.
	 */
	public com.soffid.iam.am.model.HostEntityDao getHostEntityDao () {
		return hostEntityDao;
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
	 *  Copy data to {@link com.soffid.iam.rc.api.IssueHost} object 
	 */
	public void toIssueHost(com.soffid.iam.rc.model.IssueHostEntity source, com.soffid.iam.rc.api.IssueHost target) {
		// Attributes for IssueHost
		target.setAction(source.getAction());
		// Missing attribute hostId on entity
		target.setHostName(source.getHostName());
		target.setHostIp(source.getHostIp());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssueHost} object 
	 */
	public com.soffid.iam.rc.api.IssueHost toIssueHost(com.soffid.iam.rc.model.IssueHostEntity entity) {
		final com.soffid.iam.rc.api.IssueHost target = new com.soffid.iam.rc.api.IssueHost();
		this.toIssueHost(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssueHost} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.IssueHost> toIssueHostList (java.util.Collection<com.soffid.iam.rc.model.IssueHostEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.rc.api.IssueHost> list =
				new java.util.LinkedList<com.soffid.iam.rc.api.IssueHost>();
			for (final com.soffid.iam.rc.model.IssueHostEntity instance: instances)
			{
				list.add( toIssueHost(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.IssueHost} object 
	 */
	public void issueHostToEntity (com.soffid.iam.rc.api.IssueHost source, com.soffid.iam.rc.model.IssueHostEntity target, boolean copyIfNull) {
		// Attributes for IssueHostEntity
		// Missing attribute issue on entity
		// Missing attribute host on entity
		if (copyIfNull || source.getAction() != null)
		{
			target.setAction(source.getAction());
		}
		if (copyIfNull || source.getHostName() != null)
		{
			target.setHostName(source.getHostName());
		}
		if (copyIfNull || source.getHostIp() != null)
		{
			target.setHostIp(source.getHostIp());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssueHost} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueHostEntity>  issueHostToEntityList (java.util.Collection<com.soffid.iam.rc.api.IssueHost> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.rc.model.IssueHostEntity> list =
			new java.util.LinkedList<com.soffid.iam.rc.model.IssueHostEntity>();
		for (com.soffid.iam.rc.api.IssueHost instance: instances)
		{
			list.add (issueHostToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.IssueHostEntity} .
	 */
	public com.soffid.iam.rc.model.IssueHostEntity newIssueHostEntity()
	{
		return new com.soffid.iam.rc.model.IssueHostEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.IssueHostEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.rc.model.IssueHostEntity result = (com.soffid.iam.rc.model.IssueHostEntity) this.getHibernateTemplate().get(com.soffid.iam.rc.model.IssueHostEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueHostEntity> loadAll() {
		java.util.List<com.soffid.iam.rc.model.IssueHostEntity> result = (java.util.List<com.soffid.iam.rc.model.IssueHostEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.rc.model.IssueHostEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.IssueHostEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.IssueHostEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssueHostEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.IssueHostEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.IssueHostEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssueHostEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.IssueHostEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssueHostEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.IssueHostEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.IssueHostEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssueHostEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssueHostEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.IssueHostEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.IssueHostEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssueHostEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssueHostEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.IssueHostEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssueHostEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssueHostEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"IssueHostEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.rc.model.IssueHostEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueHostEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.rc.model.IssueHostEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueHostEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.rc.model.IssueHostEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
