//
// (c) 2014 Soffid
//
//
package com.soffid.iam.sync.model;
/**
 * DAO Base for Entity TaskLogEntity
 */
public abstract class TaskLogEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.sync.model.TaskLogEntityDao
{
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

	com.soffid.iam.sync.model.TaskEntityDao taskEntityDao;

	/**
	 * Sets reference to <code>taskEntityDao</code>.
	 */
	public void setTaskEntityDao (com.soffid.iam.sync.model.TaskEntityDao taskEntityDao) {
		this.taskEntityDao = taskEntityDao;
	}

	/**
	 * Gets reference to <code>taskEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.TaskEntityDao getTaskEntityDao () {
		return taskEntityDao;
	}


	/**
	 * Operation countTasksByServerAndSystem
	 * @param server
	 * @return
	**/
	public java.util.Collection<java.lang.Object[]> countTasksByServerAndSystem(
	    java.lang.String server)
	
	{
		return countTasksByServerAndSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<java.lang.Object[]> countTasksByServerAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		return countTasksByServerAndSystem("select s.name, count(distinct tlo.task.id) from \ncom.soffid.iam.sync.model.TaskLogEntity tlo left outer join tlo.system as s \nwhere tlo.task.tenant.id = :tenantId and tlo.completed='S' and tlo.task.server=:server and \n(tlo.task.systemName is null or tlo.task.systemName = s.name) group by s.name \norder by s.name",
			criteria, server);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<java.lang.Object[]> countTasksByServerAndSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
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
	 * Operation countTasksByServerAndSystem
	 * @param server
	 * @param serverInstance
	 * @return
	**/
	public java.util.Collection<java.lang.Object[]> countTasksByServerAndSystem(
	    java.lang.String server, 
	    java.lang.String serverInstance)
	
	{
		return countTasksByServerAndSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server, serverInstance);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<java.lang.Object[]> countTasksByServerAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	
	{
		return countTasksByServerAndSystem("select s.name, count(distinct tlo.task.id) from \ncom.soffid.iam.sync.model.TaskLogEntity tlo left outer join tlo.system as s \nwhere tlo.task.tenant.id = :tenantId and tlo.completed='S' and tlo.task.server=:server and tlo.task.serverInstance=:serverInstance and \n(tlo.task.systemName is null or tlo.task.systemName = s.name) group by s.name \norder by s.name",
			criteria, server, serverInstance);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<java.lang.Object[]> countTasksByServerAndSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	
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
		return countTasksBySystem("select s.name, count(distinct tlo.task.id) from \ncom.soffid.iam.sync.model.TaskLogEntity tlo left outer join tlo.system as s \nwhere tlo.task.tenant.id = :tenantId and tlo.completed='S'  \ngroup by s.name \norder by s.name",
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
	 * Operation findAllHavingTasqueByAgentAndServer
	 * @param server
	 * @param system
	 * @param status
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByAgentAndServer(
	    java.lang.String server, 
	    java.lang.String system, 
	    java.lang.String status)
	
	{
		return findAllHavingTasqueByAgentAndServer((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server, system, status);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByAgentAndServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String system, java.lang.String status)
	
	{
		return findAllHavingTasqueByAgentAndServer("select tlog from com.soffid.iam.sync.model.TaskLogEntity tlog\nleft join tlog.system system\nleft join tlog.task task\nwhere system.name=:system and \n(:status is null or tlog.completed=:status) and ( \n   (:server is not null and task.server=:server) or \n   (:server is null and task.server is null) \n) order by task.id, system.name",
			criteria, server, system, status);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByAgentAndServer(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String system, java.lang.String status)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("status", status, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAllHavingTasqueByServer
	 * @param server
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByServer(
	    java.lang.String server)
	
	{
		return findAllHavingTasqueByServer((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		return findAllHavingTasqueByServer("select tlog from com.soffid.iam.sync.model.TaskLogEntity tlog \nleft join tlog.task tasca\nwhere  \n  (:server is not null and tasca.server=:server) \n  or (:server is null and tasca.server is null)\norder by tlog.task.id, tlog.system.name",
			criteria, server);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByServer(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAllHavingTasqueByServerAndServerInstance
	 * @param server
	 * @param serverInstance
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByServerAndServerInstance(
	    java.lang.String server, 
	    java.lang.String serverInstance)
	
	{
		return findAllHavingTasqueByServerAndServerInstance((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server, serverInstance);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByServerAndServerInstance(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	
	{
		return findAllHavingTasqueByServerAndServerInstance("select tlog from com.soffid.iam.sync.model.TaskLogEntity tlog \nleft join tlog.task tasca\nwhere  \n  (:server is not null and tasca.server=:server) or (:server is null and tasca.server is null) and \n  (:serverInstance is not null and tasca.serverInstance=:serverInstance) or (:serverInstance is null and tasca.serverInstance is null)\norder by tlog.task.id, tlog.system.name",
			criteria, server, serverInstance);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByServerAndServerInstance(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("serverInstance", serverInstance, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByTaskID
	 * @param taskId
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByTaskID(
	    java.lang.Long taskId)
	
	{
		return findByTaskID((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, taskId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByTaskID(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long taskId)
	
	{
		return findByTaskID("from com.soffid.iam.sync.model.TaskLogEntity tlog\nwhere tlog.task.id=:taskId",
			criteria, taskId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByTaskID(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long taskId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("taskId", taskId, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskLogEntity>) results;
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
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByServerAndSystem(
	    java.lang.String server, 
	    java.lang.String system)
	
	{
		return findByServerAndSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByServerAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String system)
	
	{
		return findByServerAndSystem("select tlog from com.soffid.iam.sync.model.TaskLogEntity tlog where tlog.system.name=:system  and (tlog.task.server=:server and :server is not null or tlog.task.server is null and :server is null) order by tlog.task.id, tlog.system.name",
			criteria, server, system);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByServerAndSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskLogEntity>) results;
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
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByServerAndSystem(
	    java.lang.String server, 
	    java.lang.String serverInstance, 
	    java.lang.String system)
	
	{
		return findByServerAndSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server, serverInstance, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByServerAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance, java.lang.String system)
	
	{
		return findByServerAndSystem("select tlog from com.soffid.iam.sync.model.TaskLogEntity tlog where tlog.system.name=:system  and (tlog.task.server=:server and :server is not null or tlog.task.server is null and :server is null) and     (tlog.task.serverInstance=:serverInstance and :serverInstance is not null or tlog.task.serverInstance is null and :serverInstance is null)order by tlog.task.id, tlog.system.name",
			criteria, server, serverInstance, system);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByServerAndSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("serverInstance", serverInstance, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskLogEntity>) results;
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
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findBySystem(
	    java.lang.String system)
	
	{
		return findBySystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	
	{
		return findBySystem("select tlog from com.soffid.iam.sync.model.TaskLogEntity tlog where tlog.system.name=:system  order by tlog.task.id, tlog.system.name",
			criteria, system);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findBySystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.sync.model.TaskLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.TaskLogEntity} .
	 */
	public com.soffid.iam.sync.model.TaskLogEntity newTaskLogEntity()
	{
		return new com.soffid.iam.sync.model.TaskLogEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.TaskLogEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.sync.model.TaskLogEntity result = (com.soffid.iam.sync.model.TaskLogEntity) this.getHibernateTemplate().get(com.soffid.iam.sync.model.TaskLogEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> loadAll() {
		java.util.List<com.soffid.iam.sync.model.TaskLogEntity> result = (java.util.List<com.soffid.iam.sync.model.TaskLogEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.sync.model.TaskLogEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.TaskLogEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.TaskLogEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TaskLogEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.TaskLogEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.TaskLogEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TaskLogEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.TaskLogEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TaskLogEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.TaskLogEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.TaskLogEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TaskLogEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.TaskLogEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.TaskLogEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.TaskLogEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TaskLogEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.TaskLogEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.TaskLogEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TaskLogEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.TaskLogEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"TaskLogEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.sync.model.TaskLogEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.sync.model.TaskLogEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.sync.model.TaskLogEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
