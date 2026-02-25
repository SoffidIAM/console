//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity ObjectMappingTriggerEntity
 */
public abstract class ObjectMappingTriggerEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.ObjectMappingTriggerEntityDao
{
	com.soffid.iam.iga.model.ObjectMappingEntityDao objectMappingEntityDao;

	/**
	 * Sets reference to <code>objectMappingEntityDao</code>.
	 */
	public void setObjectMappingEntityDao (com.soffid.iam.iga.model.ObjectMappingEntityDao objectMappingEntityDao) {
		this.objectMappingEntityDao = objectMappingEntityDao;
	}

	/**
	 * Gets reference to <code>objectMappingEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ObjectMappingEntityDao getObjectMappingEntityDao () {
		return objectMappingEntityDao;
	}


	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ObjectMappingTrigger} object 
	 */
	public void toObjectMappingTrigger(com.soffid.iam.iga.model.ObjectMappingTriggerEntity source, com.soffid.iam.iga.api.ObjectMappingTrigger target) {
		// Attributes for ObjectMappingTrigger
		target.setId(source.getId());
		target.setTrigger(source.getTrigger());
		target.setScript(source.getScript());
		// Missing attribute objectId on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ObjectMappingTrigger} object 
	 */
	public com.soffid.iam.iga.api.ObjectMappingTrigger toObjectMappingTrigger(com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity) {
		final com.soffid.iam.iga.api.ObjectMappingTrigger target = new com.soffid.iam.iga.api.ObjectMappingTrigger();
		this.toObjectMappingTrigger(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ObjectMappingTrigger} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ObjectMappingTrigger> toObjectMappingTriggerList (java.util.Collection<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.ObjectMappingTrigger> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.ObjectMappingTrigger>();
			for (final com.soffid.iam.iga.model.ObjectMappingTriggerEntity instance: instances)
			{
				list.add( toObjectMappingTrigger(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ObjectMappingTrigger} object 
	 */
	public void objectMappingTriggerToEntity (com.soffid.iam.iga.api.ObjectMappingTrigger source, com.soffid.iam.iga.model.ObjectMappingTriggerEntity target, boolean copyIfNull) {
		// Attributes for ObjectMappingTriggerEntity
		if (copyIfNull || source.getTrigger() != null)
		{
			target.setTrigger(source.getTrigger());
		}
		if (copyIfNull || source.getScript() != null)
		{
			target.setScript(source.getScript());
		}
		// Missing attribute object on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ObjectMappingTrigger} object 
	 */
	public com.soffid.iam.iga.model.ObjectMappingTriggerEntity objectMappingTriggerToEntity (com.soffid.iam.iga.api.ObjectMappingTrigger instance) {
		com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newObjectMappingTriggerEntity();
		objectMappingTriggerToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ObjectMappingTrigger} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingTriggerEntity>  objectMappingTriggerToEntityList (java.util.Collection<com.soffid.iam.iga.api.ObjectMappingTrigger> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.ObjectMappingTriggerEntity>();
		for (com.soffid.iam.iga.api.ObjectMappingTrigger instance: instances)
		{
			list.add (objectMappingTriggerToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} .
	 */
	public com.soffid.iam.iga.model.ObjectMappingTriggerEntity newObjectMappingTriggerEntity()
	{
		return new com.soffid.iam.iga.model.ObjectMappingTriggerEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ObjectMappingTriggerEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.ObjectMappingTriggerEntity result = (com.soffid.iam.iga.model.ObjectMappingTriggerEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.ObjectMappingTriggerEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> result = (java.util.List<com.soffid.iam.iga.model.ObjectMappingTriggerEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.ObjectMappingTriggerEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingTriggerEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingTriggerEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingTriggerEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingTriggerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingTriggerEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingTriggerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingTriggerEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingTriggerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingTriggerEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ObjectMappingTriggerEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.ObjectMappingTriggerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.ObjectMappingTriggerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
