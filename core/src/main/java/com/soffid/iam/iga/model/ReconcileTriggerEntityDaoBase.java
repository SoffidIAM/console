//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity ReconcileTriggerEntity
 */
public abstract class ReconcileTriggerEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.ReconcileTriggerEntityDao
{
	com.soffid.iam.iga.model.SystemEntityDao systemEntityDao;

	/**
	 * Sets reference to <code>systemEntityDao</code>.
	 */
	public void setSystemEntityDao (com.soffid.iam.iga.model.SystemEntityDao systemEntityDao) {
		this.systemEntityDao = systemEntityDao;
	}

	/**
	 * Gets reference to <code>systemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.SystemEntityDao getSystemEntityDao () {
		return systemEntityDao;
	}


	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ReconcileTrigger} object 
	 */
	public void toReconcileTrigger(com.soffid.iam.iga.model.ReconcileTriggerEntity source, com.soffid.iam.iga.api.ReconcileTrigger target) {
		// Attributes for ReconcileTrigger
		target.setId(source.getId());
		target.setObjectType(source.getObjectType());
		target.setTrigger(source.getTrigger());
		target.setScript(source.getScript());
		// Incompatible types source.system and target.system
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileTrigger} object 
	 */
	public com.soffid.iam.iga.api.ReconcileTrigger toReconcileTrigger(com.soffid.iam.iga.model.ReconcileTriggerEntity entity) {
		final com.soffid.iam.iga.api.ReconcileTrigger target = new com.soffid.iam.iga.api.ReconcileTrigger();
		this.toReconcileTrigger(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileTrigger} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ReconcileTrigger> toReconcileTriggerList (java.util.Collection<com.soffid.iam.iga.model.ReconcileTriggerEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.ReconcileTrigger> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.ReconcileTrigger>();
			for (final com.soffid.iam.iga.model.ReconcileTriggerEntity instance: instances)
			{
				list.add( toReconcileTrigger(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ReconcileTrigger} object 
	 */
	public void reconcileTriggerToEntity (com.soffid.iam.iga.api.ReconcileTrigger source, com.soffid.iam.iga.model.ReconcileTriggerEntity target, boolean copyIfNull) {
		// Attributes for ReconcileTriggerEntity
		if (copyIfNull || source.getTrigger() != null)
		{
			target.setTrigger(source.getTrigger());
		}
		if (copyIfNull || source.getScript() != null)
		{
			target.setScript(source.getScript());
		}
		if (copyIfNull || source.getObjectType() != null)
		{
			target.setObjectType(source.getObjectType());
		}
		if (copyIfNull || source.getSystem() != null)
		{
			// Incompatible types source.system and target.system
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileTrigger} object 
	 */
	public com.soffid.iam.iga.model.ReconcileTriggerEntity reconcileTriggerToEntity (com.soffid.iam.iga.api.ReconcileTrigger instance) {
		com.soffid.iam.iga.model.ReconcileTriggerEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newReconcileTriggerEntity();
		reconcileTriggerToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileTrigger} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileTriggerEntity>  reconcileTriggerToEntityList (java.util.Collection<com.soffid.iam.iga.api.ReconcileTrigger> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.ReconcileTriggerEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.ReconcileTriggerEntity>();
		for (com.soffid.iam.iga.api.ReconcileTrigger instance: instances)
		{
			list.add (reconcileTriggerToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} .
	 */
	public com.soffid.iam.iga.model.ReconcileTriggerEntity newReconcileTriggerEntity()
	{
		return new com.soffid.iam.iga.model.ReconcileTriggerEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ReconcileTriggerEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.ReconcileTriggerEntity result = (com.soffid.iam.iga.model.ReconcileTriggerEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.ReconcileTriggerEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileTriggerEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.ReconcileTriggerEntity> result = (java.util.List<com.soffid.iam.iga.model.ReconcileTriggerEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.ReconcileTriggerEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ReconcileTriggerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileTriggerEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ReconcileTriggerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileTriggerEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ReconcileTriggerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ReconcileTriggerEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileTriggerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileTriggerEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileTriggerEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileTriggerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileTriggerEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileTriggerEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileTriggerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ReconcileTriggerEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ReconcileTriggerEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ReconcileTriggerEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.ReconcileTriggerEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileTriggerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.ReconcileTriggerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileTriggerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.ReconcileTriggerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
