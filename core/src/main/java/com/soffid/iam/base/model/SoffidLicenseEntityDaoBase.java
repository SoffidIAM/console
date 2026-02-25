//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity SoffidLicenseEntity
 */
public abstract class SoffidLicenseEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.SoffidLicenseEntityDao
{

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.SoffidLicenseEntity} .
	 */
	public com.soffid.iam.base.model.SoffidLicenseEntity newSoffidLicenseEntity()
	{
		return new com.soffid.iam.base.model.SoffidLicenseEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.SoffidLicenseEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.SoffidLicenseEntity result = (com.soffid.iam.base.model.SoffidLicenseEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.SoffidLicenseEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.SoffidLicenseEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.SoffidLicenseEntity> result = (java.util.List<com.soffid.iam.base.model.SoffidLicenseEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.SoffidLicenseEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.SoffidLicenseEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.SoffidLicenseEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SoffidLicenseEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.SoffidLicenseEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.SoffidLicenseEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SoffidLicenseEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.SoffidLicenseEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SoffidLicenseEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.SoffidLicenseEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.SoffidLicenseEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SoffidLicenseEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.SoffidLicenseEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.SoffidLicenseEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.SoffidLicenseEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SoffidLicenseEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.SoffidLicenseEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.SoffidLicenseEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SoffidLicenseEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.SoffidLicenseEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"SoffidLicenseEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.SoffidLicenseEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.SoffidLicenseEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.SoffidLicenseEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.SoffidLicenseEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.SoffidLicenseEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.SoffidLicenseEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
