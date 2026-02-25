//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity HostAdminEntity
 */
public abstract class HostAdminEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.HostAdminEntityDao
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

	com.soffid.iam.base.model.UserDataEntityDao userDataEntityDao;

	/**
	 * Sets reference to <code>userDataEntityDao</code>.
	 */
	public void setUserDataEntityDao (com.soffid.iam.base.model.UserDataEntityDao userDataEntityDao) {
		this.userDataEntityDao = userDataEntityDao;
	}

	/**
	 * Gets reference to <code>userDataEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserDataEntityDao getUserDataEntityDao () {
		return userDataEntityDao;
	}

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
	 * Operation findByHostNameAndRequestDate
	 * @param nomHost
	 * @param requestDate
	 * @param expirationDate
	 * @param nullDate
	 * @return
	**/
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity> findByHostNameAndRequestDate(
	    java.lang.String nomHost, 
	    java.util.Date requestDate, 
	    java.util.Date expirationDate, 
	    java.util.Date nullDate)
	
	{
		return findByHostNameAndRequestDate((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nomHost, requestDate, expirationDate, nullDate);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity> findByHostNameAndRequestDate(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nomHost, java.util.Date requestDate, java.util.Date expirationDate, java.util.Date nullDate)
	
	{
		return findByHostNameAndRequestDate("select autoriza from com.soffid.iam.pam.model.HostAdminEntity autoriza where   autoriza.host.name = :nomHost and   (:requestDate = :nullDate or autoriza.requestDate >= :requestDate) and   (:expirationDate = :nullDate or autoriza.expirationDate > :expirationDate) order by autoriza.requestDate",
			criteria, nomHost, requestDate, expirationDate, nullDate);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity> findByHostNameAndRequestDate(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nomHost, java.util.Date requestDate, java.util.Date expirationDate, java.util.Date nullDate)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nomHost", nomHost, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("requestDate", requestDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("expirationDate", expirationDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("nullDate", nullDate, org.hibernate.Hibernate.TIMESTAMP);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.pam.model.HostAdminEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.HostAdmin} object 
	 */
	public void toHostAdmin(com.soffid.iam.pam.model.HostAdminEntity source, com.soffid.iam.pam.api.HostAdmin target) {
		// Attributes for HostAdmin
		target.setId(source.getId());
		// Missing attribute userCode on entity
		// Missing attribute hostName on entity
		// Missing attribute authorizationAccessExpirationDate on entity
		// Missing attribute bpmProcessId on entity
		// Missing attribute userName on entity
		// Missing attribute userEmail on entity
		// Missing attribute hostIp on entity
		// Missing attribute hostNetwork on entity
		// Missing attribute hostDescription on entity
		if (source.getRequestDate() == null) {
			target.setRequestDate(null);
		} else {
			target.setRequestDate(java.util.Calendar.getInstance());
			target.getRequestDate().setTime(source.getRequestDate());
		}
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostAdmin} object 
	 */
	public com.soffid.iam.pam.api.HostAdmin toHostAdmin(com.soffid.iam.pam.model.HostAdminEntity entity) {
		final com.soffid.iam.pam.api.HostAdmin target = new com.soffid.iam.pam.api.HostAdmin();
		this.toHostAdmin(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostAdmin} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.HostAdmin> toHostAdminList (java.util.Collection<com.soffid.iam.pam.model.HostAdminEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.pam.api.HostAdmin> list =
				new java.util.LinkedList<com.soffid.iam.pam.api.HostAdmin>();
			for (final com.soffid.iam.pam.model.HostAdminEntity instance: instances)
			{
				list.add( toHostAdmin(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.HostAdmin} object 
	 */
	public void hostAdminToEntity (com.soffid.iam.pam.api.HostAdmin source, com.soffid.iam.pam.model.HostAdminEntity target, boolean copyIfNull) {
		// Attributes for HostAdminEntity
		// Missing attribute expirationDate on entity
		// Missing attribute user on entity
		// Missing attribute host on entity
		// Missing attribute processWFID on entity
		if (copyIfNull || source.getRequestDate() != null)
		{
			if (source.getRequestDate() == null) {
				target.setRequestDate(null);
			} else {
				target.setRequestDate(source.getRequestDate().getTime());
			}
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostAdmin} object 
	 */
	public com.soffid.iam.pam.model.HostAdminEntity hostAdminToEntity (com.soffid.iam.pam.api.HostAdmin instance) {
		com.soffid.iam.pam.model.HostAdminEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newHostAdminEntity();
		hostAdminToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostAdmin} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity>  hostAdminToEntityList (java.util.Collection<com.soffid.iam.pam.api.HostAdmin> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.pam.model.HostAdminEntity> list =
			new java.util.LinkedList<com.soffid.iam.pam.model.HostAdminEntity>();
		for (com.soffid.iam.pam.api.HostAdmin instance: instances)
		{
			list.add (hostAdminToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.HostAdminEntity} .
	 */
	public com.soffid.iam.pam.model.HostAdminEntity newHostAdminEntity()
	{
		return new com.soffid.iam.pam.model.HostAdminEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.HostAdminEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.HostAdminEntity result = (com.soffid.iam.pam.model.HostAdminEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.HostAdminEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity> loadAll() {
		java.util.List<com.soffid.iam.pam.model.HostAdminEntity> result = (java.util.List<com.soffid.iam.pam.model.HostAdminEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.pam.model.HostAdminEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.HostAdminEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.HostAdminEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostAdminEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.HostAdminEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.HostAdminEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostAdminEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.HostAdminEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostAdminEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.HostAdminEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.HostAdminEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostAdminEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.HostAdminEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.HostAdminEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.HostAdminEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostAdminEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.HostAdminEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.HostAdminEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostAdminEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.HostAdminEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"HostAdminEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.HostAdminEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.HostAdminEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostAdminEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.HostAdminEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.HostAdminEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
