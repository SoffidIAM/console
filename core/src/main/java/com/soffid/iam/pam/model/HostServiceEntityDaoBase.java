//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity HostServiceEntity
 */
public abstract class HostServiceEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.HostServiceEntityDao
{
	com.soffid.iam.base.model.AccountEntityDao accountEntityDao;

	/**
	 * Sets reference to <code>accountEntityDao</code>.
	 */
	public void setAccountEntityDao (com.soffid.iam.base.model.AccountEntityDao accountEntityDao) {
		this.accountEntityDao = accountEntityDao;
	}

	/**
	 * Gets reference to <code>accountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountEntityDao getAccountEntityDao () {
		return accountEntityDao;
	}

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
	 *  Copy data to {@link com.soffid.iam.pam.api.HostService} object 
	 */
	public void toHostService(com.soffid.iam.pam.model.HostServiceEntity source, com.soffid.iam.pam.api.HostService target) {
		// Attributes for HostService
		target.setId(source.getId());
		// Missing attribute hostId on entity
		// Missing attribute hostName on entity
		target.setService(source.getService());
		target.setCommand(source.getCommand());
		// Missing attribute accountId on entity
		// Missing attribute accountName on entity
		// Missing attribute accountSystem on entity
		target.setManual(source.isManual());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostService} object 
	 */
	public com.soffid.iam.pam.api.HostService toHostService(com.soffid.iam.pam.model.HostServiceEntity entity) {
		final com.soffid.iam.pam.api.HostService target = new com.soffid.iam.pam.api.HostService();
		this.toHostService(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostService} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.HostService> toHostServiceList (java.util.Collection<com.soffid.iam.pam.model.HostServiceEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.pam.api.HostService> list =
				new java.util.LinkedList<com.soffid.iam.pam.api.HostService>();
			for (final com.soffid.iam.pam.model.HostServiceEntity instance: instances)
			{
				list.add( toHostService(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.HostService} object 
	 */
	public void hostServiceToEntity (com.soffid.iam.pam.api.HostService source, com.soffid.iam.pam.model.HostServiceEntity target, boolean copyIfNull) {
		// Attributes for HostServiceEntity
		// Missing attribute host on entity
		if (copyIfNull || source.getService() != null)
		{
			target.setService(source.getService());
		}
		if (copyIfNull || source.getCommand() != null)
		{
			target.setCommand(source.getCommand());
		}
		target.setManual(source.isManual());
		// Missing attribute account on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostService} object 
	 */
	public com.soffid.iam.pam.model.HostServiceEntity hostServiceToEntity (com.soffid.iam.pam.api.HostService instance) {
		com.soffid.iam.pam.model.HostServiceEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newHostServiceEntity();
		hostServiceToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostService} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.HostServiceEntity>  hostServiceToEntityList (java.util.Collection<com.soffid.iam.pam.api.HostService> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.pam.model.HostServiceEntity> list =
			new java.util.LinkedList<com.soffid.iam.pam.model.HostServiceEntity>();
		for (com.soffid.iam.pam.api.HostService instance: instances)
		{
			list.add (hostServiceToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.HostServiceEntity} .
	 */
	public com.soffid.iam.pam.model.HostServiceEntity newHostServiceEntity()
	{
		return new com.soffid.iam.pam.model.HostServiceEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.HostServiceEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.HostServiceEntity result = (com.soffid.iam.pam.model.HostServiceEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.HostServiceEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.HostServiceEntity> loadAll() {
		java.util.List<com.soffid.iam.pam.model.HostServiceEntity> result = (java.util.List<com.soffid.iam.pam.model.HostServiceEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.pam.model.HostServiceEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.HostServiceEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.HostServiceEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostServiceEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.HostServiceEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.HostServiceEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostServiceEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.HostServiceEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostServiceEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.HostServiceEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.HostServiceEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostServiceEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.HostServiceEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.HostServiceEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.HostServiceEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostServiceEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.HostServiceEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.HostServiceEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostServiceEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.HostServiceEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"HostServiceEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.HostServiceEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.HostServiceEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.HostServiceEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.HostServiceEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.HostServiceEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
