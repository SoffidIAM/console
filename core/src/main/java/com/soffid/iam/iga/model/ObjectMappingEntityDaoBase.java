//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity ObjectMappingEntity
 */
public abstract class ObjectMappingEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.ObjectMappingEntityDao
{
	com.soffid.iam.iga.model.AttributeMappingEntityDao attributeMappingEntityDao;

	/**
	 * Sets reference to <code>attributeMappingEntityDao</code>.
	 */
	public void setAttributeMappingEntityDao (com.soffid.iam.iga.model.AttributeMappingEntityDao attributeMappingEntityDao) {
		this.attributeMappingEntityDao = attributeMappingEntityDao;
	}

	/**
	 * Gets reference to <code>attributeMappingEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.AttributeMappingEntityDao getAttributeMappingEntityDao () {
		return attributeMappingEntityDao;
	}

	com.soffid.iam.iga.model.CustomObjectTypeEntityDao customObjectTypeEntityDao;

	/**
	 * Sets reference to <code>customObjectTypeEntityDao</code>.
	 */
	public void setCustomObjectTypeEntityDao (com.soffid.iam.iga.model.CustomObjectTypeEntityDao customObjectTypeEntityDao) {
		this.customObjectTypeEntityDao = customObjectTypeEntityDao;
	}

	/**
	 * Gets reference to <code>customObjectTypeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntityDao getCustomObjectTypeEntityDao () {
		return customObjectTypeEntityDao;
	}

	com.soffid.iam.iga.model.ObjectMappingPropertyEntityDao objectMappingPropertyEntityDao;

	/**
	 * Sets reference to <code>objectMappingPropertyEntityDao</code>.
	 */
	public void setObjectMappingPropertyEntityDao (com.soffid.iam.iga.model.ObjectMappingPropertyEntityDao objectMappingPropertyEntityDao) {
		this.objectMappingPropertyEntityDao = objectMappingPropertyEntityDao;
	}

	/**
	 * Gets reference to <code>objectMappingPropertyEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ObjectMappingPropertyEntityDao getObjectMappingPropertyEntityDao () {
		return objectMappingPropertyEntityDao;
	}

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

	com.soffid.iam.iga.model.ObjectMappingTriggerEntityDao objectMappingTriggerEntityDao;

	/**
	 * Sets reference to <code>objectMappingTriggerEntityDao</code>.
	 */
	public void setObjectMappingTriggerEntityDao (com.soffid.iam.iga.model.ObjectMappingTriggerEntityDao objectMappingTriggerEntityDao) {
		this.objectMappingTriggerEntityDao = objectMappingTriggerEntityDao;
	}

	/**
	 * Gets reference to <code>objectMappingTriggerEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ObjectMappingTriggerEntityDao getObjectMappingTriggerEntityDao () {
		return objectMappingTriggerEntityDao;
	}


	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ObjectMapping} object 
	 */
	public void toObjectMapping(com.soffid.iam.iga.model.ObjectMappingEntity source, com.soffid.iam.iga.api.ObjectMapping target) {
		// Attributes for ObjectMapping
		target.setId(source.getId());
		target.setSystemObject(source.getSystemObject());
		target.setSoffidObject(source.getSoffidObject());
		// Incompatible types source.soffidCustomObject and target.soffidCustomObject
		target.setCondition(source.getCondition());
		// Missing attribute dispatcherId on entity
		// Missing attribute authoritative on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ObjectMapping} object 
	 */
	public com.soffid.iam.iga.api.ObjectMapping toObjectMapping(com.soffid.iam.iga.model.ObjectMappingEntity entity) {
		final com.soffid.iam.iga.api.ObjectMapping target = new com.soffid.iam.iga.api.ObjectMapping();
		this.toObjectMapping(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ObjectMapping} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ObjectMapping> toObjectMappingList (java.util.Collection<com.soffid.iam.iga.model.ObjectMappingEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.ObjectMapping> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.ObjectMapping>();
			for (final com.soffid.iam.iga.model.ObjectMappingEntity instance: instances)
			{
				list.add( toObjectMapping(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ObjectMapping} object 
	 */
	public void objectMappingToEntity (com.soffid.iam.iga.api.ObjectMapping source, com.soffid.iam.iga.model.ObjectMappingEntity target, boolean copyIfNull) {
		// Attributes for ObjectMappingEntity
		if (copyIfNull || source.getSystemObject() != null)
		{
			target.setSystemObject(source.getSystemObject());
		}
		if (copyIfNull || source.getSoffidObject() != null)
		{
			target.setSoffidObject(source.getSoffidObject());
		}
		if (copyIfNull || source.getSoffidCustomObject() != null)
		{
			// Incompatible types source.soffidCustomObject and target.soffidCustomObject
		}
		// Missing attribute soffidExtensibleObject on entity
		if (copyIfNull || source.getCondition() != null)
		{
			target.setCondition(source.getCondition());
		}
		// Missing attribute properties on entity
		// Missing attribute system on entity
		// Missing attribute attributeMappings on entity
		// Missing attribute triggers on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ObjectMapping} object 
	 */
	public com.soffid.iam.iga.model.ObjectMappingEntity objectMappingToEntity (com.soffid.iam.iga.api.ObjectMapping instance) {
		com.soffid.iam.iga.model.ObjectMappingEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newObjectMappingEntity();
		objectMappingToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ObjectMapping} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingEntity>  objectMappingToEntityList (java.util.Collection<com.soffid.iam.iga.api.ObjectMapping> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.ObjectMappingEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.ObjectMappingEntity>();
		for (com.soffid.iam.iga.api.ObjectMapping instance: instances)
		{
			list.add (objectMappingToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ObjectMappingEntity} .
	 */
	public com.soffid.iam.iga.model.ObjectMappingEntity newObjectMappingEntity()
	{
		return new com.soffid.iam.iga.model.ObjectMappingEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ObjectMappingEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.ObjectMappingEntity result = (com.soffid.iam.iga.model.ObjectMappingEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.ObjectMappingEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.ObjectMappingEntity> result = (java.util.List<com.soffid.iam.iga.model.ObjectMappingEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.ObjectMappingEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ObjectMappingEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ObjectMappingEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ObjectMappingEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ObjectMappingEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ObjectMappingEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ObjectMappingEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ObjectMappingEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ObjectMappingEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ObjectMappingEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ObjectMappingEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ObjectMappingEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.ObjectMappingEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.ObjectMappingEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.ObjectMappingEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
