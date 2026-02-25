//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity NetworkDiscoveryAccountEntity
 */
public abstract class NetworkDiscoveryAccountEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.NetworkDiscoveryAccountEntityDao
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

	com.soffid.iam.am.model.NetworkEntityDao networkEntityDao;

	/**
	 * Sets reference to <code>networkEntityDao</code>.
	 */
	public void setNetworkEntityDao (com.soffid.iam.am.model.NetworkEntityDao networkEntityDao) {
		this.networkEntityDao = networkEntityDao;
	}

	/**
	 * Gets reference to <code>networkEntityDao</code>.
	 */
	public com.soffid.iam.am.model.NetworkEntityDao getNetworkEntityDao () {
		return networkEntityDao;
	}


	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public void toHostPort(com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity source, com.soffid.iam.pam.api.HostPort target) {
		// Attributes for HostPort
		target.setId(source.getId());
		// Missing attribute hostId on entity
		// Missing attribute hostName on entity
		// Missing attribute port on entity
		// Missing attribute description on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public com.soffid.iam.pam.api.HostPort toHostPort(com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity) {
		final com.soffid.iam.pam.api.HostPort target = new com.soffid.iam.pam.api.HostPort();
		this.toHostPort(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostPort} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.HostPort> toHostPortList (java.util.Collection<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.pam.api.HostPort> list =
				new java.util.LinkedList<com.soffid.iam.pam.api.HostPort>();
			for (final com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity instance: instances)
			{
				list.add( toHostPort(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public void hostPortToEntity (com.soffid.iam.pam.api.HostPort source, com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity target, boolean copyIfNull) {
		// Attributes for NetworkDiscoveryAccountEntity
		// Missing attribute network on entity
		// Missing attribute account on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity hostPortToEntity (com.soffid.iam.pam.api.HostPort instance) {
		com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newNetworkDiscoveryAccountEntity();
		hostPortToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostPort} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity>  hostPortToEntityList (java.util.Collection<com.soffid.iam.pam.api.HostPort> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> list =
			new java.util.LinkedList<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity>();
		for (com.soffid.iam.pam.api.HostPort instance: instances)
		{
			list.add (hostPortToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} .
	 */
	public com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity newNetworkDiscoveryAccountEntity()
	{
		return new com.soffid.iam.pam.model.NetworkDiscoveryAccountEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity result = (com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.NetworkDiscoveryAccountEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> loadAll() {
		java.util.List<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> result = (java.util.List<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NetworkDiscoveryAccountEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NetworkDiscoveryAccountEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NetworkDiscoveryAccountEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NetworkDiscoveryAccountEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NetworkDiscoveryAccountEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NetworkDiscoveryAccountEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"NetworkDiscoveryAccountEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
