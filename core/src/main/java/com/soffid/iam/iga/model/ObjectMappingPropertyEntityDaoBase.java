//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity ObjectMappingPropertyEntity
 */
public abstract class ObjectMappingPropertyEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.ObjectMappingPropertyEntityDao
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
	 *  Copy data to {@link com.soffid.iam.iga.api.ObjectMappingProperty} object 
	 */
	public void toObjectMappingProperty(com.soffid.iam.iga.model.ObjectMappingPropertyEntity source, com.soffid.iam.iga.api.ObjectMappingProperty target) {
		// Attributes for ObjectMappingProperty
		target.setId(source.getId());
		target.setProperty(source.getProperty());
		target.setValue(source.getValue());
		// Missing attribute objectId on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ObjectMappingProperty} object 
	 */
	public com.soffid.iam.iga.api.ObjectMappingProperty toObjectMappingProperty(com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity) {
		final com.soffid.iam.iga.api.ObjectMappingProperty target = new com.soffid.iam.iga.api.ObjectMappingProperty();
		this.toObjectMappingProperty(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ObjectMappingProperty} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ObjectMappingProperty> toObjectMappingPropertyList (java.util.Collection<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.ObjectMappingProperty> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.ObjectMappingProperty>();
			for (final com.soffid.iam.iga.model.ObjectMappingPropertyEntity instance: instances)
			{
				list.add( toObjectMappingProperty(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ObjectMappingProperty} object 
	 */
	public void objectMappingPropertyToEntity (com.soffid.iam.iga.api.ObjectMappingProperty source, com.soffid.iam.iga.model.ObjectMappingPropertyEntity target, boolean copyIfNull) {
		// Attributes for ObjectMappingPropertyEntity
		if (copyIfNull || source.getProperty() != null)
		{
			target.setProperty(source.getProperty());
		}
		if (copyIfNull || source.getValue() != null)
		{
			target.setValue(source.getValue());
		}
		// Missing attribute object on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ObjectMappingProperty} object 
	 */
	public com.soffid.iam.iga.model.ObjectMappingPropertyEntity objectMappingPropertyToEntity (com.soffid.iam.iga.api.ObjectMappingProperty instance) {
		com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newObjectMappingPropertyEntity();
		objectMappingPropertyToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ObjectMappingProperty} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingPropertyEntity>  objectMappingPropertyToEntityList (java.util.Collection<com.soffid.iam.iga.api.ObjectMappingProperty> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.ObjectMappingPropertyEntity>();
		for (com.soffid.iam.iga.api.ObjectMappingProperty instance: instances)
		{
			list.add (objectMappingPropertyToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} .
	 */
	public com.soffid.iam.iga.model.ObjectMappingPropertyEntity newObjectMappingPropertyEntity()
	{
		return new com.soffid.iam.iga.model.ObjectMappingPropertyEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ObjectMappingPropertyEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.ObjectMappingPropertyEntity result = (com.soffid.iam.iga.model.ObjectMappingPropertyEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.ObjectMappingPropertyEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> result = (java.util.List<com.soffid.iam.iga.model.ObjectMappingPropertyEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.ObjectMappingPropertyEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingPropertyEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingPropertyEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingPropertyEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingPropertyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingPropertyEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingPropertyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingPropertyEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingPropertyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ObjectMappingPropertyEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ObjectMappingPropertyEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.ObjectMappingPropertyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.ObjectMappingPropertyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
