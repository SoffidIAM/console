//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity UserTypeSystemEntity
 */
public abstract class UserTypeSystemEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.UserTypeSystemEntityDao
{
	com.soffid.iam.rc.model.AuditEntityDao auditEntityDao;

	/**
	 * Sets reference to <code>auditEntityDao</code>.
	 */
	public void setAuditEntityDao (com.soffid.iam.rc.model.AuditEntityDao auditEntityDao) {
		this.auditEntityDao = auditEntityDao;
	}

	/**
	 * Gets reference to <code>auditEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.AuditEntityDao getAuditEntityDao () {
		return auditEntityDao;
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

	com.soffid.iam.base.model.UserTypeEntityDao userTypeEntityDao;

	/**
	 * Sets reference to <code>userTypeEntityDao</code>.
	 */
	public void setUserTypeEntityDao (com.soffid.iam.base.model.UserTypeEntityDao userTypeEntityDao) {
		this.userTypeEntityDao = userTypeEntityDao;
	}

	/**
	 * Gets reference to <code>userTypeEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserTypeEntityDao getUserTypeEntityDao () {
		return userTypeEntityDao;
	}


	/**
	 * Operation findBySystem
	 * @param systemName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.UserTypeSystemEntity> findBySystem(
	    java.lang.String systemName)
	
	{
		return findBySystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, systemName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserTypeSystemEntity> findBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	
	{
		return findBySystem("select tu from com.soffid.iam.iga.model.UserTypeSystemEntity tu where tu.system.name=:systemName",
			criteria, systemName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserTypeSystemEntity> findBySystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("systemName", systemName, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.UserTypeSystemEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.UserTypeDispatcher} object 
	 */
	public void toUserTypeDispatcher(com.soffid.iam.iga.model.UserTypeSystemEntity source, com.soffid.iam.iga.api.UserTypeDispatcher target) {
		// Attributes for UserTypeDispatcher
		target.setId(source.getId());
		// Missing attribute type on entity
		// Missing attribute dispatcherCode on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserTypeDispatcher} object 
	 */
	public com.soffid.iam.iga.api.UserTypeDispatcher toUserTypeDispatcher(com.soffid.iam.iga.model.UserTypeSystemEntity entity) {
		final com.soffid.iam.iga.api.UserTypeDispatcher target = new com.soffid.iam.iga.api.UserTypeDispatcher();
		this.toUserTypeDispatcher(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserTypeDispatcher} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.UserTypeDispatcher> toUserTypeDispatcherList (java.util.Collection<com.soffid.iam.iga.model.UserTypeSystemEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.UserTypeDispatcher> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.UserTypeDispatcher>();
			for (final com.soffid.iam.iga.model.UserTypeSystemEntity instance: instances)
			{
				list.add( toUserTypeDispatcher(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.UserTypeDispatcher} object 
	 */
	public void userTypeDispatcherToEntity (com.soffid.iam.iga.api.UserTypeDispatcher source, com.soffid.iam.iga.model.UserTypeSystemEntity target, boolean copyIfNull) {
		// Attributes for UserTypeSystemEntity
		// Missing attribute system on entity
		// Missing attribute userType on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserTypeDispatcher} object 
	 */
	public com.soffid.iam.iga.model.UserTypeSystemEntity userTypeDispatcherToEntity (com.soffid.iam.iga.api.UserTypeDispatcher instance) {
		com.soffid.iam.iga.model.UserTypeSystemEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newUserTypeSystemEntity();
		userTypeDispatcherToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserTypeDispatcher} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.UserTypeSystemEntity>  userTypeDispatcherToEntityList (java.util.Collection<com.soffid.iam.iga.api.UserTypeDispatcher> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.UserTypeSystemEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.UserTypeSystemEntity>();
		for (com.soffid.iam.iga.api.UserTypeDispatcher instance: instances)
		{
			list.add (userTypeDispatcherToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} .
	 */
	public com.soffid.iam.iga.model.UserTypeSystemEntity newUserTypeSystemEntity()
	{
		return new com.soffid.iam.iga.model.UserTypeSystemEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.UserTypeSystemEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.UserTypeSystemEntity result = (com.soffid.iam.iga.model.UserTypeSystemEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.UserTypeSystemEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.UserTypeSystemEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.UserTypeSystemEntity> result = (java.util.List<com.soffid.iam.iga.model.UserTypeSystemEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.UserTypeSystemEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.UserTypeSystemEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserTypeSystemEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.UserTypeSystemEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserTypeSystemEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.UserTypeSystemEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserTypeSystemEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.UserTypeSystemEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserTypeSystemEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserTypeSystemEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.UserTypeSystemEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserTypeSystemEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserTypeSystemEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.UserTypeSystemEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserTypeSystemEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserTypeSystemEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"UserTypeSystemEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.UserTypeSystemEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.UserTypeSystemEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.UserTypeSystemEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.UserTypeSystemEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.UserTypeSystemEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
