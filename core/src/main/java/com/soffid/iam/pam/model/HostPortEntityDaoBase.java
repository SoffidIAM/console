//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity HostPortEntity
 */
public abstract class HostPortEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.HostPortEntityDao
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


	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public void toHostPort(com.soffid.iam.pam.model.HostPortEntity source, com.soffid.iam.pam.api.HostPort target) {
		// Attributes for HostPort
		target.setId(source.getId());
		// Missing attribute hostId on entity
		// Missing attribute hostName on entity
		target.setPort(source.getPort());
		target.setDescription(source.getDescription());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public com.soffid.iam.pam.api.HostPort toHostPort(com.soffid.iam.pam.model.HostPortEntity entity) {
		final com.soffid.iam.pam.api.HostPort target = new com.soffid.iam.pam.api.HostPort();
		this.toHostPort(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostPort} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.HostPort> toHostPortList (java.util.Collection<com.soffid.iam.pam.model.HostPortEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.pam.api.HostPort> list =
				new java.util.LinkedList<com.soffid.iam.pam.api.HostPort>();
			for (final com.soffid.iam.pam.model.HostPortEntity instance: instances)
			{
				list.add( toHostPort(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public void hostPortToEntity (com.soffid.iam.pam.api.HostPort source, com.soffid.iam.pam.model.HostPortEntity target, boolean copyIfNull) {
		// Attributes for HostPortEntity
		// Missing attribute host on entity
		if (copyIfNull || source.getPort() != null)
		{
			target.setPort(source.getPort());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public com.soffid.iam.pam.model.HostPortEntity hostPortToEntity (com.soffid.iam.pam.api.HostPort instance) {
		com.soffid.iam.pam.model.HostPortEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newHostPortEntity();
		hostPortToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostPort} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.HostPortEntity>  hostPortToEntityList (java.util.Collection<com.soffid.iam.pam.api.HostPort> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.pam.model.HostPortEntity> list =
			new java.util.LinkedList<com.soffid.iam.pam.model.HostPortEntity>();
		for (com.soffid.iam.pam.api.HostPort instance: instances)
		{
			list.add (hostPortToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.HostPortEntity} .
	 */
	public com.soffid.iam.pam.model.HostPortEntity newHostPortEntity()
	{
		return new com.soffid.iam.pam.model.HostPortEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.HostPortEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.HostPortEntity result = (com.soffid.iam.pam.model.HostPortEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.HostPortEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.HostPortEntity> loadAll() {
		java.util.List<com.soffid.iam.pam.model.HostPortEntity> result = (java.util.List<com.soffid.iam.pam.model.HostPortEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.pam.model.HostPortEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.HostPortEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.HostPortEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostPortEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.HostPortEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.HostPortEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostPortEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.HostPortEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostPortEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.HostPortEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.HostPortEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostPortEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.HostPortEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.HostPortEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.HostPortEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostPortEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.HostPortEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.HostPortEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostPortEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.HostPortEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"HostPortEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.HostPortEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.HostPortEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.HostPortEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.HostPortEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.HostPortEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
