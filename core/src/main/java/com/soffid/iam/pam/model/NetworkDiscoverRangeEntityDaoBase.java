//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity NetworkDiscoverRangeEntity
 */
public abstract class NetworkDiscoverRangeEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.NetworkDiscoverRangeEntityDao
{
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
	 * Creates an instance of {@link com.soffid.iam.pam.model.NetworkDiscoverRangeEntity} .
	 */
	public com.soffid.iam.pam.model.NetworkDiscoverRangeEntity newNetworkDiscoverRangeEntity()
	{
		return new com.soffid.iam.pam.model.NetworkDiscoverRangeEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.NetworkDiscoverRangeEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.NetworkDiscoverRangeEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.NetworkDiscoverRangeEntity result = (com.soffid.iam.pam.model.NetworkDiscoverRangeEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.NetworkDiscoverRangeEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.NetworkDiscoverRangeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.NetworkDiscoverRangeEntity> loadAll() {
		java.util.List<com.soffid.iam.pam.model.NetworkDiscoverRangeEntity> result = (java.util.List<com.soffid.iam.pam.model.NetworkDiscoverRangeEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.pam.model.NetworkDiscoverRangeEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.NetworkDiscoverRangeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.NetworkDiscoverRangeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NetworkDiscoverRangeEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.NetworkDiscoverRangeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.NetworkDiscoverRangeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NetworkDiscoverRangeEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.NetworkDiscoverRangeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.NetworkDiscoverRangeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NetworkDiscoverRangeEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.NetworkDiscoverRangeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.NetworkDiscoverRangeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NetworkDiscoverRangeEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.NetworkDiscoverRangeEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.NetworkDiscoverRangeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.NetworkDiscoverRangeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NetworkDiscoverRangeEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.NetworkDiscoverRangeEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.NetworkDiscoverRangeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.NetworkDiscoverRangeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NetworkDiscoverRangeEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.NetworkDiscoverRangeEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.NetworkDiscoverRangeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"NetworkDiscoverRangeEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.NetworkDiscoverRangeEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.NetworkDiscoverRangeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.NetworkDiscoverRangeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.NetworkDiscoverRangeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.NetworkDiscoverRangeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.NetworkDiscoverRangeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.NetworkDiscoverRangeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
