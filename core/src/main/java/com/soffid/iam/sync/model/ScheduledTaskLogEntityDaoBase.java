//
// (c) 2014 Soffid
//
//
package com.soffid.iam.sync.model;
/**
 * DAO Base for Entity ScheduledTaskLogEntity
 */
public abstract class ScheduledTaskLogEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.sync.model.ScheduledTaskLogEntityDao
{
	com.soffid.iam.sync.model.ScheduledTaskEntityDao scheduledTaskEntityDao;

	/**
	 * Sets reference to <code>scheduledTaskEntityDao</code>.
	 */
	public void setScheduledTaskEntityDao (com.soffid.iam.sync.model.ScheduledTaskEntityDao scheduledTaskEntityDao) {
		this.scheduledTaskEntityDao = scheduledTaskEntityDao;
	}

	/**
	 * Gets reference to <code>scheduledTaskEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntityDao getScheduledTaskEntityDao () {
		return scheduledTaskEntityDao;
	}


	/**
	 *  Copy data to {@link com.soffid.iam.sync.api.ScheduledTaskLog} object 
	 */
	public void toScheduledTaskLog(com.soffid.iam.sync.model.ScheduledTaskLogEntity source, com.soffid.iam.sync.api.ScheduledTaskLog target) {
		// Attributes for ScheduledTaskLog
		target.setId(source.getId());
		target.setTime(source.getTime());
		target.setEnd(source.getEnd());
		target.setLogReferenceID(source.getLogReferenceID());
		target.setError(source.isError());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ScheduledTaskLog} object 
	 */
	public com.soffid.iam.sync.api.ScheduledTaskLog toScheduledTaskLog(com.soffid.iam.sync.model.ScheduledTaskLogEntity entity) {
		final com.soffid.iam.sync.api.ScheduledTaskLog target = new com.soffid.iam.sync.api.ScheduledTaskLog();
		this.toScheduledTaskLog(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ScheduledTaskLog} list 
	 */
	public java.util.List<com.soffid.iam.sync.api.ScheduledTaskLog> toScheduledTaskLogList (java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskLogEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.sync.api.ScheduledTaskLog> list =
				new java.util.LinkedList<com.soffid.iam.sync.api.ScheduledTaskLog>();
			for (final com.soffid.iam.sync.model.ScheduledTaskLogEntity instance: instances)
			{
				list.add( toScheduledTaskLog(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.sync.api.ScheduledTaskLog} object 
	 */
	public void scheduledTaskLogToEntity (com.soffid.iam.sync.api.ScheduledTaskLog source, com.soffid.iam.sync.model.ScheduledTaskLogEntity target, boolean copyIfNull) {
		// Attributes for ScheduledTaskLogEntity
		// Missing attribute task on entity
		if (copyIfNull || source.getTime() != null)
		{
			target.setTime(source.getTime());
		}
		if (copyIfNull || source.getEnd() != null)
		{
			target.setEnd(source.getEnd());
		}
		if (copyIfNull || source.getLogReferenceID() != null)
		{
			target.setLogReferenceID(source.getLogReferenceID());
		}
		target.setError(source.isError());
	}

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ScheduledTaskLog} object 
	 */
	public com.soffid.iam.sync.model.ScheduledTaskLogEntity scheduledTaskLogToEntity (com.soffid.iam.sync.api.ScheduledTaskLog instance) {
		com.soffid.iam.sync.model.ScheduledTaskLogEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newScheduledTaskLogEntity();
		scheduledTaskLogToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ScheduledTaskLog} list 
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskLogEntity>  scheduledTaskLogToEntityList (java.util.Collection<com.soffid.iam.sync.api.ScheduledTaskLog> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.sync.model.ScheduledTaskLogEntity> list =
			new java.util.LinkedList<com.soffid.iam.sync.model.ScheduledTaskLogEntity>();
		for (com.soffid.iam.sync.api.ScheduledTaskLog instance: instances)
		{
			list.add (scheduledTaskLogToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} .
	 */
	public com.soffid.iam.sync.model.ScheduledTaskLogEntity newScheduledTaskLogEntity()
	{
		return new com.soffid.iam.sync.model.ScheduledTaskLogEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskLogEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.sync.model.ScheduledTaskLogEntity result = (com.soffid.iam.sync.model.ScheduledTaskLogEntity) this.getHibernateTemplate().get(com.soffid.iam.sync.model.ScheduledTaskLogEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskLogEntity> loadAll() {
		java.util.List<com.soffid.iam.sync.model.ScheduledTaskLogEntity> result = (java.util.List<com.soffid.iam.sync.model.ScheduledTaskLogEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.sync.model.ScheduledTaskLogEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ScheduledTaskLogEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskLogEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ScheduledTaskLogEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskLogEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ScheduledTaskLogEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskLogEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskLogEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskLogEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ScheduledTaskLogEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskLogEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskLogEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ScheduledTaskLogEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskLogEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskLogEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ScheduledTaskLogEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ScheduledTaskLogEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.sync.model.ScheduledTaskLogEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskLogEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.sync.model.ScheduledTaskLogEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskLogEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.sync.model.ScheduledTaskLogEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
