//
// (c) 2014 Soffid
//
//
package com.soffid.iam.sync.model;
/**
 * DAO Base for Entity TaskEntity
 */
public abstract class TaskEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.sync.model.TaskEntityDao
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

	com.soffid.iam.sync.model.TaskLogEntityDao taskLogEntityDao;

	/**
	 * Sets reference to <code>taskLogEntityDao</code>.
	 */
	public void setTaskLogEntityDao (com.soffid.iam.sync.model.TaskLogEntityDao taskLogEntityDao) {
		this.taskLogEntityDao = taskLogEntityDao;
	}

	/**
	 * Gets reference to <code>taskLogEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.TaskLogEntityDao getTaskLogEntityDao () {
		return taskLogEntityDao;
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


	/**
	 * Operation countTasks
	 * @return
	**/
	public java.lang.Long countTasks(
)
	
	{
		return countTasks((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long countTasks(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return countTasks("select count(*) \nfrom com.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server is not null and tasques.tenant.id = :tenantId ",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long countTasks(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.lang.Long result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.Long' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.Long) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation countTasksByServer
	 * @param server
	 * @return
	**/
	public java.lang.Long countTasksByServer(
	    java.lang.String server)
	
	{
		return countTasksByServer((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long countTasksByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		return countTasksByServer("select count(*) \nfrom com.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server = :server and tasques.tenant.id = :tenantId ",
			criteria, server);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long countTasksByServer(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.lang.Long result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.Long' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.Long) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation countTasksByServerInstance
	 * @param server
	 * @param serverInstance
	 * @return
	**/
	public java.lang.Long countTasksByServerInstance(
	    java.lang.String server, 
	    java.lang.String serverInstance)
	
	{
		return countTasksByServerInstance((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server, serverInstance);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long countTasksByServerInstance(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	
	{
		return countTasksByServerInstance("select count(*) \nfrom com.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server = :server and tasques.serverInstance=:serverInstance and tasques.tenant.id = :tenantId ",
			criteria, server, serverInstance);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long countTasksByServerInstance(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("serverInstance", serverInstance, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.lang.Long result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.Long' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.Long) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation countUnscheduledTasks
	 * @return
	**/
	public java.lang.Long countUnscheduledTasks(
)
	
	{
		return countUnscheduledTasks((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long countUnscheduledTasks(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return countUnscheduledTasks("select count(*) \nfrom com.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server is null and tasques.tenant.id = :tenantId ",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long countUnscheduledTasks(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.lang.Long result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.Long' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.Long) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.sync.model.TaskEntity#	 * @see com.soffid.iam.sync.model.TaskEntity#java.lang.String startVirtualSourceTransaction()
	 */
	public java.lang.String startVirtualSourceTransaction()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		try
		{
			return handleStartVirtualSourceTransaction();
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.model.TaskEntity.class).
				warn ("Error on TaskEntity.startVirtualSourceTransaction", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on TaskEntity.startVirtualSourceTransaction: "+th.toString(), th);
		}
	}

	protected abstract java.lang.String handleStartVirtualSourceTransaction() throws Exception;

	/**
	 * @see com.soffid.iam.sync.model.TaskEntity#	 * @see com.soffid.iam.sync.model.TaskEntity#java.lang.String startVirtualSourceTransaction(boolean readonly)
	 */
	public java.lang.String startVirtualSourceTransaction(
		boolean readonly)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		try
		{
			return handleStartVirtualSourceTransaction(readonly);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.model.TaskEntity.class).
				warn ("Error on TaskEntity.startVirtualSourceTransaction", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on TaskEntity.startVirtualSourceTransaction: "+th.toString(), th);
		}
	}

	protected abstract java.lang.String handleStartVirtualSourceTransaction(boolean readonly) throws Exception;

	/**
	 * @see com.soffid.iam.sync.model.TaskEntity#	 * @see com.soffid.iam.sync.model.TaskEntity#java.lang.String startVirtualSourceTransaction(boolean readonly, java.lang.String server)
	 */
	public java.lang.String startVirtualSourceTransaction(
		boolean readonly, 
		java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (server == null || server.trim().length() == 0) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.sync.model.TaskEntity.startVirtualSourceTransaction(boolean readonly, java.lang.String server) - server cannot be null");
		}
		try
		{
			return handleStartVirtualSourceTransaction(readonly, server);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.model.TaskEntity.class).
				warn ("Error on TaskEntity.startVirtualSourceTransaction", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on TaskEntity.startVirtualSourceTransaction: "+th.toString(), th);
		}
	}

	protected abstract java.lang.String handleStartVirtualSourceTransaction(boolean readonly, java.lang.String server) throws Exception;

	/**
	 * Operation countTasksBySystem
	 * @return
	**/
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(
)
	
	{
		return countTasksBySystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return countTasksBySystem("select systemName, count(*) \nfrom com.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server is not null and tasques.tenant.id = :tenantId \ngroup by systemName",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<java.lang.Object[]>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation countTasksBySystem
	 * @param server
	 * @return
	**/
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(
	    java.lang.String server)
	
	{
		return countTasksBySystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		return countTasksBySystem("select systemName, count(*) \nfrom com.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server = :server and tasques.tenant.id = :tenantId \ngroup by systemName",
			criteria, server);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<java.lang.Object[]>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation countTasksBySystem
	 * @param server
	 * @param serverInstance
	 * @return
	**/
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(
	    java.lang.String server, 
	    java.lang.String serverInstance)
	
	{
		return countTasksBySystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server, serverInstance);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	
	{
		return countTasksBySystem("select systemName, count(*) \nfrom com.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server = :server and tasques.serverInstance=:serverInstance and tasques.tenant.id = :tenantId \ngroup by systemName",
			criteria, server, serverInstance);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("serverInstance", serverInstance, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<java.lang.Object[]>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByAccount
	 * @param user
	 * @param systemName
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByAccount(
	    java.lang.String user, 
	    java.lang.String systemName)
	
	{
		return findByAccount((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, user, systemName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByAccount(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.lang.String systemName)
	
	{
		return findByAccount("from com.soffid.iam.sync.model.TaskEntity where tenant.id=:tenantId and user=:user and systemName=:systemName",
			criteria, user, systemName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByAccount(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.lang.String systemName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("systemName", systemName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByHash
	 * @param hash
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByHash(
	    java.lang.String hash)
	
	{
		return findByHash((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, hash);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByHash(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String hash)
	
	{
		return findByHash("from com.soffid.iam.sync.model.TaskEntity where tenant.id=:tenantId and hash=:hash",
			criteria, hash);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByHash(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String hash)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("hash", hash, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByHost
	 * @param host
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByHost(
	    java.lang.String host)
	
	{
		return findByHost((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, host);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByHost(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String host)
	
	{
		return findByHost("from com.soffid.iam.sync.model.TaskEntity where tenant.id=:tenantId and host=:host",
			criteria, host);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByHost(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String host)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("host", host, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByServer
	 * @param server
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServer(
	    java.lang.String server)
	
	{
		return findByServer((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		return findByServer("select tasques from \ncom.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server=:server  and tasques.tenant.id = :tenantId order by tasques.date, tasques.id",
			criteria, server);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServer(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByServerAndServerInstance
	 * @param server
	 * @param serverInstance
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndServerInstance(
	    java.lang.String server, 
	    java.lang.String serverInstance)
	
	{
		return findByServerAndServerInstance((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server, serverInstance);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndServerInstance(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	
	{
		return findByServerAndServerInstance("select tasques from \ncom.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server=:server and tasques.serverInstance=:serverInstance and tasques.tenant.id = :tenantId order by tasques.date, tasques.id",
			criteria, server, serverInstance);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndServerInstance(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("serverInstance", serverInstance, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByServerAndSystem
	 * @param server
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndSystem(
	    java.lang.String server, 
	    java.lang.String system)
	
	{
		return findByServerAndSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String system)
	
	{
		return findByServerAndSystem("select tasques from \ncom.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server=:server and (tasques.systemName is null or tasques.systemName = :system) and tasques.tenant.id = :tenantId order by tasques.date, tasques.id",
			criteria, server, system);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByServerAndSystem
	 * @param server
	 * @param serverInstance
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndSystem(
	    java.lang.String server, 
	    java.lang.String serverInstance, 
	    java.lang.String system)
	
	{
		return findByServerAndSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server, serverInstance, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance, java.lang.String system)
	
	{
		return findByServerAndSystem("select tasques from \ncom.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server=:server and tasques.serverInstance=:serverInstance and (tasques.systemName is null or tasques.systemName = :system) and tasques.tenant.id = :tenantId order by tasques.date, tasques.id",
			criteria, server, serverInstance, system);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("serverInstance", serverInstance, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findBySystem
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findBySystem(
	    java.lang.String system)
	
	{
		return findBySystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	
	{
		return findBySystem("select tasques from \ncom.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server is not null and (tasques.systemName is null or tasques.systemName = :system) and tasques.tenant.id = :tenantId order by tasques.date, tasques.id",
			criteria, system);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findBySystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByTaskAndServer
	 * @param transaction
	 * @param server
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByTaskAndServer(
	    java.lang.String transaction, 
	    java.lang.String server)
	
	{
		return findByTaskAndServer((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, transaction, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByTaskAndServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String transaction, java.lang.String server)
	
	{
		return findByTaskAndServer("select tasques from \ncom.soffid.iam.sync.model.TaskEntity tasques\nwhere tasques.server=:server and tasques.transaction=:transaction  and tasques.tenant.id = :tenantId order by tasques.date, tasques.id",
			criteria, transaction, server);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByTaskAndServer(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String transaction, java.lang.String server)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("transaction", transaction, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUser
	 * @param user
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByUser(
	    java.lang.String user)
	
	{
		return findByUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, user);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	
	{
		return findByUser("from com.soffid.iam.sync.model.TaskEntity where tenant.id=:tenantId and user=:user",
			criteria, user);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findDataPendingTasks
	 * @param server
	 * @return
	**/
	public java.util.List<java.lang.Long> findDataPendingTasks(
	    java.lang.String server)
	
	{
		return findDataPendingTasks((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<java.lang.Long> findDataPendingTasks(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		return findDataPendingTasks("select count(*) from com.soffid.iam.sync.model.TaskEntity as tasqueEntity where ((:server is null and tasqueEntity.server is null) or        (:server is not null and  tasqueEntity.server=:server) ) and tasqueEntity.tenant.id = :tenantId ",
			criteria, server);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<java.lang.Long> findDataPendingTasks(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<java.lang.Long>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findDataUnplannedTasks
	 * @return
	**/
	public java.util.List<java.lang.Object[]> findDataUnplannedTasks(
)
	
	{
		return findDataUnplannedTasks((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<java.lang.Object[]> findDataUnplannedTasks(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findDataUnplannedTasks("select count(*),max(tasqueEntity.date) from com.soffid.iam.sync.model.TaskEntity as tasqueEntity where tasqueEntity.server is null and tasqueEntity.tenant.id = :tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<java.lang.Object[]> findDataUnplannedTasks(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<java.lang.Object[]>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findUnscheduled
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findUnscheduled(
)
	
	{
		return findUnscheduled((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findUnscheduled(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findUnscheduled("select t from com.soffid.iam.sync.model.TaskEntity as t where t.server is null and t.tenant.id=:tenantId",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findUnscheduled(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.sync.model.TaskEntity#	 * @see com.soffid.iam.sync.model.TaskEntity#void cancelUnscheduled()
	 */
	public void cancelUnscheduled()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		try
		{
			handleCancelUnscheduled();
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.model.TaskEntity.class).
				warn ("Error on TaskEntity.cancelUnscheduled", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on TaskEntity.cancelUnscheduled: "+th.toString(), th);
		}
	}

	protected abstract void handleCancelUnscheduled() throws Exception;

	/**
	 * @see com.soffid.iam.sync.model.TaskEntity#	 * @see com.soffid.iam.sync.model.TaskEntity#void cancelUnscheduledCopies(com.soffid.iam.sync.model.TaskEntity entity)
	 */
	public void cancelUnscheduledCopies(
		com.soffid.iam.sync.model.TaskEntity entity)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (entity == null) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.model.TaskEntity.cancelUnscheduledCopies(com.soffid.iam.sync.model.TaskEntity entity) - entity cannot be null");
		}
		try
		{
			handleCancelUnscheduledCopies(entity);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.model.TaskEntity.class).
				warn ("Error on TaskEntity.cancelUnscheduledCopies", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on TaskEntity.cancelUnscheduledCopies: "+th.toString(), th);
		}
	}

	protected abstract void handleCancelUnscheduledCopies(com.soffid.iam.sync.model.TaskEntity entity) throws Exception;

	/**
	 * @see com.soffid.iam.sync.model.TaskEntity#	 * @see com.soffid.iam.sync.model.TaskEntity#void createForce(com.soffid.iam.sync.model.TaskEntity tasque)
	 */
	public void createForce(
		com.soffid.iam.sync.model.TaskEntity tasque)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tasque == null) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.model.TaskEntity.createForce(com.soffid.iam.sync.model.TaskEntity tasque) - tasque cannot be null");
		}
		try
		{
			handleCreateForce(tasque);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.model.TaskEntity.class).
				warn ("Error on TaskEntity.createForce", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on TaskEntity.createForce: "+th.toString(), th);
		}
	}

	protected abstract void handleCreateForce(com.soffid.iam.sync.model.TaskEntity tasque) throws Exception;

	/**
	 * @see com.soffid.iam.sync.model.TaskEntity#	 * @see com.soffid.iam.sync.model.TaskEntity#void createNoFlush(com.soffid.iam.sync.model.TaskEntity tasque)
	 */
	public void createNoFlush(
		com.soffid.iam.sync.model.TaskEntity tasque)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (tasque == null) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.model.TaskEntity.createNoFlush(com.soffid.iam.sync.model.TaskEntity tasque) - tasque cannot be null");
		}
		try
		{
			handleCreateNoFlush(tasque);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.model.TaskEntity.class).
				warn ("Error on TaskEntity.createNoFlush", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on TaskEntity.createNoFlush: "+th.toString(), th);
		}
	}

	protected abstract void handleCreateNoFlush(com.soffid.iam.sync.model.TaskEntity tasque) throws Exception;

	/**
	 * @see com.soffid.iam.sync.model.TaskEntity#	 * @see com.soffid.iam.sync.model.TaskEntity#void finishVirtualSourceTransaction(java.lang.String virtualTransactionId)
	 */
	public void finishVirtualSourceTransaction(
		java.lang.String virtualTransactionId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (virtualTransactionId == null || virtualTransactionId.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.sync.model.TaskEntity.finishVirtualSourceTransaction(java.lang.String virtualTransactionId) - virtualTransactionId cannot be null");
		}
		try
		{
			handleFinishVirtualSourceTransaction(virtualTransactionId);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.model.TaskEntity.class).
				warn ("Error on TaskEntity.finishVirtualSourceTransaction", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on TaskEntity.finishVirtualSourceTransaction: "+th.toString(), th);
		}
	}

	protected abstract void handleFinishVirtualSourceTransaction(java.lang.String virtualTransactionId) throws Exception;

	/**
	 * @see com.soffid.iam.sync.model.TaskEntity#	 * @see com.soffid.iam.sync.model.TaskEntity#void releaseAll()
	 */
	public void releaseAll()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		try
		{
			handleReleaseAll();
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.sync.model.TaskEntity.class).
				warn ("Error on TaskEntity.releaseAll", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on TaskEntity.releaseAll: "+th.toString(), th);
		}
	}

	protected abstract void handleReleaseAll() throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.sync.api.Task} object 
	 */
	public void toTask(com.soffid.iam.sync.model.TaskEntity source, com.soffid.iam.sync.api.Task target) {
		// Attributes for Task
		target.setId(source.getId());
		target.setUser(source.getUser());
		target.setPassword(source.getPassword());
		// Missing attribute passwordChange on entity
		target.setFolder(source.getFolder());
		target.setFolderType(source.getFolderType());
		target.setPrinter(source.getPrinter());
		target.setHost(source.getHost());
		target.setSubnet(source.getSubnet());
		target.setMessage(source.getMessage());
		target.setStatus(source.getStatus());
		// Missing attribute taskDate on entity
		target.setTransaction(source.getTransaction());
		target.setGroup(source.getGroup());
		target.setAlias(source.getAlias());
		target.setMailDomain(source.getMailDomain());
		target.setRole(source.getRole());
		// Missing attribute database on entity
		target.setSystemName(source.getSystemName());
		target.setServer(source.getServer());
		target.setServerInstance(source.getServerInstance());
		// Missing attribute userDomain on entity
		// Missing attribute passwordDomain on entity
		target.setHash(source.getHash());
		if (source.getExpirationDate() == null) {
			target.setExpirationDate(null);
		} else {
			target.setExpirationDate(java.util.Calendar.getInstance());
			target.getExpirationDate().setTime(source.getExpirationDate());
		}
		target.setEntity(source.getEntity());
		target.setPrimaryKeyValue(source.getPrimaryKeyValue());
		target.setCustomObjectType(source.getCustomObjectType());
		target.setCustomObjectName(source.getCustomObjectName());
		target.setExternalId(source.getExternalId());
		target.setCustomTaskName(source.getCustomTaskName());
		target.setParameters(source.getParameters());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.Task} object 
	 */
	public com.soffid.iam.sync.api.Task toTask(com.soffid.iam.sync.model.TaskEntity entity) {
		final com.soffid.iam.sync.api.Task target = new com.soffid.iam.sync.api.Task();
		this.toTask(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.Task} list 
	 */
	public java.util.List<com.soffid.iam.sync.api.Task> toTaskList (java.util.Collection<com.soffid.iam.sync.model.TaskEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.sync.api.Task> list =
				new java.util.LinkedList<com.soffid.iam.sync.api.Task>();
			for (final com.soffid.iam.sync.model.TaskEntity instance: instances)
			{
				list.add( toTask(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.sync.api.Task} object 
	 */
	public void taskToEntity (com.soffid.iam.sync.api.Task source, com.soffid.iam.sync.model.TaskEntity target, boolean copyIfNull) {
		// Attributes for TaskEntity
		if (copyIfNull || source.getUser() != null)
		{
			target.setUser(source.getUser());
		}
		if (copyIfNull || source.getPassword() != null)
		{
			target.setPassword(source.getPassword());
		}
		// Missing attribute changePassword on entity
		if (copyIfNull || source.getFolder() != null)
		{
			target.setFolder(source.getFolder());
		}
		if (copyIfNull || source.getFolderType() != null)
		{
			target.setFolderType(source.getFolderType());
		}
		if (copyIfNull || source.getPrinter() != null)
		{
			target.setPrinter(source.getPrinter());
		}
		if (copyIfNull || source.getHost() != null)
		{
			target.setHost(source.getHost());
		}
		if (copyIfNull || source.getSubnet() != null)
		{
			target.setSubnet(source.getSubnet());
		}
		if (copyIfNull || source.getMessage() != null)
		{
			target.setMessage(source.getMessage());
		}
		if (copyIfNull || source.getStatus() != null)
		{
			target.setStatus(source.getStatus());
		}
		// Missing attribute date on entity
		if (copyIfNull || source.getTransaction() != null)
		{
			target.setTransaction(source.getTransaction());
		}
		if (copyIfNull || source.getGroup() != null)
		{
			target.setGroup(source.getGroup());
		}
		if (copyIfNull || source.getAlias() != null)
		{
			target.setAlias(source.getAlias());
		}
		if (copyIfNull || source.getMailDomain() != null)
		{
			target.setMailDomain(source.getMailDomain());
		}
		if (copyIfNull || source.getRole() != null)
		{
			target.setRole(source.getRole());
		}
		// Missing attribute db on entity
		if (copyIfNull || source.getSystemName() != null)
		{
			target.setSystemName(source.getSystemName());
		}
		if (copyIfNull || source.getServer() != null)
		{
			target.setServer(source.getServer());
		}
		if (copyIfNull || source.getServerInstance() != null)
		{
			target.setServerInstance(source.getServerInstance());
		}
		// Missing attribute priority on entity
		// Missing attribute usersDomain on entity
		// Missing attribute passwordsDomain on entity
		if (copyIfNull || source.getHash() != null)
		{
			target.setHash(source.getHash());
		}
		// Missing attribute logs on entity
		if (copyIfNull || source.getExpirationDate() != null)
		{
			if (source.getExpirationDate() == null) {
				target.setExpirationDate(null);
			} else {
				target.setExpirationDate(source.getExpirationDate().getTime());
			}
		}
		if (copyIfNull || source.getEntity() != null)
		{
			target.setEntity(source.getEntity());
		}
		if (copyIfNull || source.getPrimaryKeyValue() != null)
		{
			target.setPrimaryKeyValue(source.getPrimaryKeyValue());
		}
		if (copyIfNull || source.getCustomObjectType() != null)
		{
			target.setCustomObjectType(source.getCustomObjectType());
		}
		if (copyIfNull || source.getCustomObjectName() != null)
		{
			target.setCustomObjectName(source.getCustomObjectName());
		}
		if (copyIfNull || source.getCustomTaskName() != null)
		{
			target.setCustomTaskName(source.getCustomTaskName());
		}
		if (copyIfNull || source.getParameters() != null)
		{
			target.setParameters(source.getParameters());
		}
		if (copyIfNull || source.getExternalId() != null)
		{
			target.setExternalId(source.getExternalId());
		}
		// Missing attribute sourceTransaction on entity
		// Missing attribute tenant on entity
		// Missing attribute taskLogs on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.Task} object 
	 */
	public com.soffid.iam.sync.model.TaskEntity taskToEntity (com.soffid.iam.sync.api.Task instance) {
		com.soffid.iam.sync.model.TaskEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newTaskEntity();
		taskToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.Task} list 
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity>  taskToEntityList (java.util.Collection<com.soffid.iam.sync.api.Task> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.sync.model.TaskEntity> list =
			new java.util.LinkedList<com.soffid.iam.sync.model.TaskEntity>();
		for (com.soffid.iam.sync.api.Task instance: instances)
		{
			list.add (taskToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.TaskEntity} .
	 */
	public com.soffid.iam.sync.model.TaskEntity newTaskEntity()
	{
		return new com.soffid.iam.sync.model.TaskEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.TaskEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.sync.model.TaskEntity result = (com.soffid.iam.sync.model.TaskEntity) this.getHibernateTemplate().get(com.soffid.iam.sync.model.TaskEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.sync.model.TaskEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.sync.model.TaskEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.TaskEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.TaskEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TaskEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.TaskEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.TaskEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TaskEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.TaskEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TaskEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.TaskEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.TaskEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TaskEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.TaskEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.TaskEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.TaskEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TaskEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.TaskEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.TaskEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TaskEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.TaskEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"TaskEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.sync.model.TaskEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.sync.model.TaskEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
