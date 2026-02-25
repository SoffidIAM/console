//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity SessionEntity
 */
public abstract class SessionEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.SessionEntityDao
{
	com.soffid.iam.am.model.AccessLogEntityDao accessLogEntityDao;

	/**
	 * Sets reference to <code>accessLogEntityDao</code>.
	 */
	public void setAccessLogEntityDao (com.soffid.iam.am.model.AccessLogEntityDao accessLogEntityDao) {
		this.accessLogEntityDao = accessLogEntityDao;
	}

	/**
	 * Gets reference to <code>accessLogEntityDao</code>.
	 */
	public com.soffid.iam.am.model.AccessLogEntityDao getAccessLogEntityDao () {
		return accessLogEntityDao;
	}

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

	com.soffid.iam.am.model.BrowserEntityDao browserEntityDao;

	/**
	 * Sets reference to <code>browserEntityDao</code>.
	 */
	public void setBrowserEntityDao (com.soffid.iam.am.model.BrowserEntityDao browserEntityDao) {
		this.browserEntityDao = browserEntityDao;
	}

	/**
	 * Gets reference to <code>browserEntityDao</code>.
	 */
	public com.soffid.iam.am.model.BrowserEntityDao getBrowserEntityDao () {
		return browserEntityDao;
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

	com.soffid.iam.base.model.TenantEntityDao tenantEntityDao;

	/**
	 * Sets reference to <code>tenantEntityDao</code>.
	 */
	public void setTenantEntityDao (com.soffid.iam.base.model.TenantEntityDao tenantEntityDao) {
		this.tenantEntityDao = tenantEntityDao;
	}

	/**
	 * Gets reference to <code>tenantEntityDao</code>.
	 */
	public com.soffid.iam.base.model.TenantEntityDao getTenantEntityDao () {
		return tenantEntityDao;
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
	 * Operation findById
	 * @param id
	 * @return
	**/
	public com.soffid.iam.am.model.SessionEntity findById(
	    java.lang.Long id)
	
	{
		return findById((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, id);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.SessionEntity findById(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		return findById("from com.soffid.iam.am.model.SessionEntity where tenant.id=:tenantId and id=:id",
			criteria, id);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.SessionEntity findById(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("id", id, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.SessionEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.SessionEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.SessionEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findSessionByCriteria
	 * @param port
	 * @param userName
	 * @param serverHostName
	 * @param clientHostName
	 * @return
	**/
	public com.soffid.iam.am.model.SessionEntity findSessionByCriteria(
	    java.lang.Long port, 
	    java.lang.String userName, 
	    java.lang.String serverHostName, 
	    java.lang.String clientHostName)
	
	{
		return findSessionByCriteria((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, port, userName, serverHostName, clientHostName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.SessionEntity findSessionByCriteria(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long port, java.lang.String userName, java.lang.String serverHostName, java.lang.String clientHostName)
	
	{
		return findSessionByCriteria("select session \nfrom com.soffid.iam.am.model.SessionEntity session \nwhere session.port = :port and  session.clientHostName = :clientHostName and  session.hostName = :serverHostName and  session.user.userName = :userName",
			criteria, port, userName, serverHostName, clientHostName);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.SessionEntity findSessionByCriteria(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long port, java.lang.String userName, java.lang.String serverHostName, java.lang.String clientHostName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("port", port, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("serverHostName", serverHostName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("clientHostName", clientHostName, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.SessionEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.SessionEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.SessionEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByKey
	 * @param key
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.am.model.SessionEntity> findByKey(
	    java.lang.String key)
	
	{
		return findByKey((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, key);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.am.model.SessionEntity> findByKey(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String key)
	
	{
		return findByKey("from com.soffid.iam.am.model.SessionEntity where tenant.id=:tenantId and key=:key",
			criteria, key);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.am.model.SessionEntity> findByKey(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String key)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("key", key, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.am.model.SessionEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByBrowserId
	 * @param browserId
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findByBrowserId(
	    java.lang.Long browserId)
	
	{
		return findByBrowserId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, browserId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findByBrowserId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long browserId)
	
	{
		return findByBrowserId("select session \nfrom com.soffid.iam.am.model.SessionEntity session \nwhere session.browser.id = :browserId",
			criteria, browserId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findByBrowserId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long browserId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("browserId", browserId, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.SessionEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findSessionByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findSessionByUserName(
	    java.lang.String userName)
	
	{
		return findSessionByUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findSessionByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findSessionByUserName("select session\nfrom com.soffid.iam.am.model.SessionEntity session where session.user.userName=:userName",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findSessionByUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.SessionEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findSessionsByCriteria
	 * @param port
	 * @param userName
	 * @param serverHostName
	 * @param clientHostName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findSessionsByCriteria(
	    java.lang.Long port, 
	    java.lang.String userName, 
	    java.lang.String serverHostName, 
	    java.lang.String clientHostName)
	
	{
		return findSessionsByCriteria((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, port, userName, serverHostName, clientHostName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findSessionsByCriteria(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long port, java.lang.String userName, java.lang.String serverHostName, java.lang.String clientHostName)
	
	{
		return findSessionsByCriteria("select session \nfrom com.soffid.iam.am.model.SessionEntity session \nwhere (:port is null or session.port like :port) \nand (:clientHostName is null \n         or (session.clientHostName like :clientHostName)) \nand (:serverHostName is null \n  or (session.hostName like :serverHostName)) \nand (:userName is null or session.user.userName like :userName)\n",
			criteria, port, userName, serverHostName, clientHostName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findSessionsByCriteria(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long port, java.lang.String userName, java.lang.String serverHostName, java.lang.String clientHostName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("port", port, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("serverHostName", serverHostName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("clientHostName", clientHostName, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.SessionEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.Session} object 
	 */
	public void toSession(com.soffid.iam.am.model.SessionEntity source, com.soffid.iam.am.api.Session target) {
		// Attributes for Session
		// Missing attribute userName on entity
		// Missing attribute serverHostName on entity
		target.setClientHostName(source.getClientHostName());
		// Incompatible types source.browser and target.browser
		// Missing attribute country on entity
		target.setPort(source.getPort());
		target.setId(source.getId());
		// Missing attribute userFullName on entity
		if (source.getStartDate() == null) {
			target.setStartDate(null);
		} else {
			target.setStartDate(java.util.Calendar.getInstance());
			target.getStartDate().setTime(source.getStartDate());
		}
		target.setKey(source.getKey());
		if (source.getKeepAliveDate() == null) {
			target.setKeepAliveDate(null);
		} else {
			target.setKeepAliveDate(java.util.Calendar.getInstance());
			target.getKeepAliveDate().setTime(source.getKeepAliveDate());
		}
		// Missing attribute temporaryKey on entity
		// Missing attribute accessLogId on entity
		// Missing attribute url on entity
		target.setAuthenticationMethod(source.getAuthenticationMethod());
		target.setType(source.getType());
		target.setMonitorUrl(source.getMonitorUrl());
		// Missing attribute sessionUrl on entity
		// Missing attribute accountName on entity
		target.setServiceProvider(source.getServiceProvider());
		// Missing attribute tenantName on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Session} object 
	 */
	public com.soffid.iam.am.api.Session toSession(com.soffid.iam.am.model.SessionEntity entity) {
		final com.soffid.iam.am.api.Session target = new com.soffid.iam.am.api.Session();
		this.toSession(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Session} list 
	 */
	public java.util.List<com.soffid.iam.am.api.Session> toSessionList (java.util.Collection<com.soffid.iam.am.model.SessionEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.Session> list =
				new java.util.LinkedList<com.soffid.iam.am.api.Session>();
			for (final com.soffid.iam.am.model.SessionEntity instance: instances)
			{
				list.add( toSession(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.Session} object 
	 */
	public void sessionToEntity (com.soffid.iam.am.api.Session source, com.soffid.iam.am.model.SessionEntity target, boolean copyIfNull) {
		// Attributes for SessionEntity
		if (copyIfNull || source.getPort() != null)
		{
			target.setPort(source.getPort());
		}
		// Missing attribute user on entity
		// Missing attribute account on entity
		// Missing attribute clientHost on entity
		// Missing attribute host on entity
		if (copyIfNull || source.getBrowser() != null)
		{
			// Incompatible types source.browser and target.browser
		}
		if (copyIfNull || source.getStartDate() != null)
		{
			if (source.getStartDate() == null) {
				target.setStartDate(null);
			} else {
				target.setStartDate(source.getStartDate().getTime());
			}
		}
		if (copyIfNull || source.getKeepAliveDate() != null)
		{
			if (source.getKeepAliveDate() == null) {
				target.setKeepAliveDate(null);
			} else {
				target.setKeepAliveDate(source.getKeepAliveDate().getTime());
			}
		}
		// Missing attribute externalClientIp on entity
		// Missing attribute newKey on entity
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		// Missing attribute webHandler on entity
		if (copyIfNull || source.getKey() != null)
		{
			target.setKey(source.getKey());
		}
		// Missing attribute loginLogInfo on entity
		// Missing attribute clientAddress on entity
		if (copyIfNull || source.getClientHostName() != null)
		{
			target.setClientHostName(source.getClientHostName());
		}
		// Missing attribute hostName on entity
		// Missing attribute hostAddress on entity
		if (copyIfNull || source.getAuthenticationMethod() != null)
		{
			target.setAuthenticationMethod(source.getAuthenticationMethod());
		}
		if (copyIfNull || source.getMonitorUrl() != null)
		{
			target.setMonitorUrl(source.getMonitorUrl());
		}
		// Missing attribute justInTimePermissionToRemove on entity
		// Missing attribute tenant on entity
		if (copyIfNull || source.getServiceProvider() != null)
		{
			target.setServiceProvider(source.getServiceProvider());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Session} object 
	 */
	public com.soffid.iam.am.model.SessionEntity sessionToEntity (com.soffid.iam.am.api.Session instance) {
		com.soffid.iam.am.model.SessionEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newSessionEntity();
		sessionToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Session} list 
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity>  sessionToEntityList (java.util.Collection<com.soffid.iam.am.api.Session> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.SessionEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.SessionEntity>();
		for (com.soffid.iam.am.api.Session instance: instances)
		{
			list.add (sessionToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.SessionEntity} .
	 */
	public com.soffid.iam.am.model.SessionEntity newSessionEntity()
	{
		return new com.soffid.iam.am.model.SessionEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.SessionEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.SessionEntity result = (com.soffid.iam.am.model.SessionEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.SessionEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.SessionEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.SessionEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.SessionEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.SessionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SessionEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.SessionEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.SessionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SessionEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.SessionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SessionEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.SessionEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.SessionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SessionEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.SessionEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.SessionEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.SessionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SessionEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.SessionEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.SessionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SessionEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.SessionEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"SessionEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.SessionEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.SessionEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.SessionEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
