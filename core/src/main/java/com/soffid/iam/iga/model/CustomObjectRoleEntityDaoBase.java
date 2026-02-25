//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity CustomObjectRoleEntity
 */
public abstract class CustomObjectRoleEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.CustomObjectRoleEntityDao
{
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

	com.soffid.iam.iga.model.RoleEntityDao roleEntityDao;

	/**
	 * Sets reference to <code>roleEntityDao</code>.
	 */
	public void setRoleEntityDao (com.soffid.iam.iga.model.RoleEntityDao roleEntityDao) {
		this.roleEntityDao = roleEntityDao;
	}

	/**
	 * Gets reference to <code>roleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleEntityDao getRoleEntityDao () {
		return roleEntityDao;
	}


	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public void toCustomObject(com.soffid.iam.iga.model.CustomObjectRoleEntity source, com.soffid.iam.iga.api.CustomObject target) {
		// Attributes for CustomObject
		target.setId(source.getId());
		// Missing attribute name on entity
		// Missing attribute description on entity
		// Missing attribute type on entity
		// Missing attribute attributes on entity
		// Missing attribute createdOn on entity
		// Missing attribute createdBy on entity
		// Missing attribute updatedOn on entity
		// Missing attribute updatedBy on entity
		// Missing attribute deletedOn on entity
		// Missing attribute deletedBy on entity
		// Missing attribute deleted on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public com.soffid.iam.iga.api.CustomObject toCustomObject(com.soffid.iam.iga.model.CustomObjectRoleEntity entity) {
		final com.soffid.iam.iga.api.CustomObject target = new com.soffid.iam.iga.api.CustomObject();
		this.toCustomObject(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.CustomObject} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.CustomObject> toCustomObjectList (java.util.Collection<com.soffid.iam.iga.model.CustomObjectRoleEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.CustomObject> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.CustomObject>();
			for (final com.soffid.iam.iga.model.CustomObjectRoleEntity instance: instances)
			{
				list.add( toCustomObject(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public void customObjectToEntity (com.soffid.iam.iga.api.CustomObject source, com.soffid.iam.iga.model.CustomObjectRoleEntity target, boolean copyIfNull) {
		// Attributes for CustomObjectRoleEntity
		// Missing attribute customObjectType on entity
		// Missing attribute role on entity
		// Missing attribute level on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public com.soffid.iam.iga.model.CustomObjectRoleEntity customObjectToEntity (com.soffid.iam.iga.api.CustomObject instance) {
		com.soffid.iam.iga.model.CustomObjectRoleEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newCustomObjectRoleEntity();
		customObjectToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.CustomObject} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectRoleEntity>  customObjectToEntityList (java.util.Collection<com.soffid.iam.iga.api.CustomObject> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.CustomObjectRoleEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.CustomObjectRoleEntity>();
		for (com.soffid.iam.iga.api.CustomObject instance: instances)
		{
			list.add (customObjectToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} .
	 */
	public com.soffid.iam.iga.model.CustomObjectRoleEntity newCustomObjectRoleEntity()
	{
		return new com.soffid.iam.iga.model.CustomObjectRoleEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.CustomObjectRoleEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.CustomObjectRoleEntity result = (com.soffid.iam.iga.model.CustomObjectRoleEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.CustomObjectRoleEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectRoleEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.CustomObjectRoleEntity> result = (java.util.List<com.soffid.iam.iga.model.CustomObjectRoleEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.CustomObjectRoleEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.CustomObjectRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectRoleEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.CustomObjectRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectRoleEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.CustomObjectRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectRoleEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectRoleEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.CustomObjectRoleEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectRoleEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.CustomObjectRoleEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"CustomObjectRoleEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.CustomObjectRoleEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"CustomObjectRoleEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.CustomObjectRoleEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectRoleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.CustomObjectRoleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectRoleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.CustomObjectRoleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
