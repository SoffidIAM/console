//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity UserPreferenceEntity
 */
public abstract class UserPreferenceEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.UserPreferenceEntityDao
{
	com.soffid.iam.base.model.UserEntityDao userEntityDao;

	/**
	 * Sets reference to <code>userEntityDao</code>.
	 */
	public void setUserEntityDao (com.soffid.iam.base.model.UserEntityDao userEntityDao) {
		this.userEntityDao = userEntityDao;
	}

	/**
	 * Gets reference to <code>userEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserEntityDao getUserEntityDao () {
		return userEntityDao;
	}


	/**
	 * Operation findByNameAndUserName
	 * @param name
	 * @param userName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> findByNameAndUserName(
	    java.lang.String name, 
	    java.lang.String userName)
	
	{
		return findByNameAndUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> findByNameAndUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String userName)
	
	{
		return findByNameAndUserName("select up from com.soffid.iam.base.model.UserPreferenceEntity as up where up.user.userName = :userName and up.name=:name and up.user.tenant.id=:tenantId",
			criteria, name, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> findByNameAndUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String userName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUserName
	 * @param userName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> findByUserName(
	    java.lang.String userName)
	
	{
		return findByUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findByUserName("select up from com.soffid.iam.base.model.UserPreferenceEntity as up where up.user.userName = :userName and up.user.tenant.id=:tenantId",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> findByUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.UserPreferenceEntity} .
	 */
	public com.soffid.iam.base.model.UserPreferenceEntity newUserPreferenceEntity()
	{
		return new com.soffid.iam.base.model.UserPreferenceEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.UserPreferenceEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.UserPreferenceEntity result = (com.soffid.iam.base.model.UserPreferenceEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.UserPreferenceEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.UserPreferenceEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.UserPreferenceEntity> result = (java.util.List<com.soffid.iam.base.model.UserPreferenceEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.UserPreferenceEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.UserPreferenceEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.UserPreferenceEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserPreferenceEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.UserPreferenceEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.UserPreferenceEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserPreferenceEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.UserPreferenceEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserPreferenceEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.UserPreferenceEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.UserPreferenceEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserPreferenceEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.UserPreferenceEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.UserPreferenceEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.UserPreferenceEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserPreferenceEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.UserPreferenceEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.UserPreferenceEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserPreferenceEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.UserPreferenceEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"UserPreferenceEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.UserPreferenceEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.UserPreferenceEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.UserPreferenceEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.UserPreferenceEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.UserPreferenceEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
