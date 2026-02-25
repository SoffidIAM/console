//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity AttributeMappingEntity
 */
public abstract class AttributeMappingEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.AttributeMappingEntityDao
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
	 *  Copy data to {@link com.soffid.iam.iga.api.AttributeMapping} object 
	 */
	public void toAttributeMapping(com.soffid.iam.iga.model.AttributeMappingEntity source, com.soffid.iam.iga.api.AttributeMapping target) {
		// Attributes for AttributeMapping
		target.setId(source.getId());
		target.setSoffidAttribute(source.getSoffidAttribute());
		target.setSystemAttribute(source.getSystemAttribute());
		target.setDirection(source.getDirection());
		// Missing attribute objectId on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.AttributeMapping} object 
	 */
	public com.soffid.iam.iga.api.AttributeMapping toAttributeMapping(com.soffid.iam.iga.model.AttributeMappingEntity entity) {
		final com.soffid.iam.iga.api.AttributeMapping target = new com.soffid.iam.iga.api.AttributeMapping();
		this.toAttributeMapping(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.AttributeMapping} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.AttributeMapping> toAttributeMappingList (java.util.Collection<com.soffid.iam.iga.model.AttributeMappingEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.AttributeMapping> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.AttributeMapping>();
			for (final com.soffid.iam.iga.model.AttributeMappingEntity instance: instances)
			{
				list.add( toAttributeMapping(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.AttributeMapping} object 
	 */
	public void attributeMappingToEntity (com.soffid.iam.iga.api.AttributeMapping source, com.soffid.iam.iga.model.AttributeMappingEntity target, boolean copyIfNull) {
		// Attributes for AttributeMappingEntity
		if (copyIfNull || source.getSoffidAttribute() != null)
		{
			target.setSoffidAttribute(source.getSoffidAttribute());
		}
		if (copyIfNull || source.getSystemAttribute() != null)
		{
			target.setSystemAttribute(source.getSystemAttribute());
		}
		if (copyIfNull || source.getDirection() != null)
		{
			target.setDirection(source.getDirection());
		}
		// Missing attribute object on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.AttributeMapping} object 
	 */
	public com.soffid.iam.iga.model.AttributeMappingEntity attributeMappingToEntity (com.soffid.iam.iga.api.AttributeMapping instance) {
		com.soffid.iam.iga.model.AttributeMappingEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newAttributeMappingEntity();
		attributeMappingToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.AttributeMapping} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeMappingEntity>  attributeMappingToEntityList (java.util.Collection<com.soffid.iam.iga.api.AttributeMapping> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.AttributeMappingEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.AttributeMappingEntity>();
		for (com.soffid.iam.iga.api.AttributeMapping instance: instances)
		{
			list.add (attributeMappingToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.AttributeMappingEntity} .
	 */
	public com.soffid.iam.iga.model.AttributeMappingEntity newAttributeMappingEntity()
	{
		return new com.soffid.iam.iga.model.AttributeMappingEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.AttributeMappingEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.AttributeMappingEntity result = (com.soffid.iam.iga.model.AttributeMappingEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.AttributeMappingEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeMappingEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.AttributeMappingEntity> result = (java.util.List<com.soffid.iam.iga.model.AttributeMappingEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.AttributeMappingEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.AttributeMappingEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.AttributeMappingEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AttributeMappingEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.AttributeMappingEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.AttributeMappingEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AttributeMappingEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.AttributeMappingEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AttributeMappingEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.AttributeMappingEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.AttributeMappingEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AttributeMappingEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AttributeMappingEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.AttributeMappingEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.AttributeMappingEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AttributeMappingEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AttributeMappingEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.AttributeMappingEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AttributeMappingEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AttributeMappingEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AttributeMappingEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.AttributeMappingEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeMappingEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.AttributeMappingEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.AttributeMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeMappingEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.AttributeMappingEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
