//
// (c) 2014 Soffid
//
//
package com.soffid.iam.sync.model;
/**
 * DAO Base for Entity ScheduledTaskEntity
 */
public abstract class ScheduledTaskEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.sync.model.ScheduledTaskEntityDao
{
	com.soffid.iam.sync.model.ScheduledTaskHandlerEntityDao scheduledTaskHandlerEntityDao;

	/**
	 * Sets reference to <code>scheduledTaskHandlerEntityDao</code>.
	 */
	public void setScheduledTaskHandlerEntityDao (com.soffid.iam.sync.model.ScheduledTaskHandlerEntityDao scheduledTaskHandlerEntityDao) {
		this.scheduledTaskHandlerEntityDao = scheduledTaskHandlerEntityDao;
	}

	/**
	 * Gets reference to <code>scheduledTaskHandlerEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntityDao getScheduledTaskHandlerEntityDao () {
		return scheduledTaskHandlerEntityDao;
	}

	com.soffid.iam.sync.model.ServerEntityDao serverEntityDao;

	/**
	 * Sets reference to <code>serverEntityDao</code>.
	 */
	public void setServerEntityDao (com.soffid.iam.sync.model.ServerEntityDao serverEntityDao) {
		this.serverEntityDao = serverEntityDao;
	}

	/**
	 * Gets reference to <code>serverEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ServerEntityDao getServerEntityDao () {
		return serverEntityDao;
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

	com.soffid.iam.sync.model.ScheduledTaskLogEntityDao scheduledTaskLogEntityDao;

	/**
	 * Sets reference to <code>scheduledTaskLogEntityDao</code>.
	 */
	public void setScheduledTaskLogEntityDao (com.soffid.iam.sync.model.ScheduledTaskLogEntityDao scheduledTaskLogEntityDao) {
		this.scheduledTaskLogEntityDao = scheduledTaskLogEntityDao;
	}

	/**
	 * Gets reference to <code>scheduledTaskLogEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskLogEntityDao getScheduledTaskLogEntityDao () {
		return scheduledTaskLogEntityDao;
	}


	/**
	 * Operation findByHandlerParams
	 * @param handlerName
	 * @param params
	 * @return
	**/
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByHandlerParams(
	    java.lang.String handlerName, 
	    java.lang.String params)
	
	{
		return findByHandlerParams((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, handlerName, params);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByHandlerParams(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String handlerName, java.lang.String params)
	
	{
		return findByHandlerParams("select ste\nfrom  com.soffid.iam.sync.model.ScheduledTaskEntity as ste\njoin ste.handler as handler\nwhere handler.name=:handlerName and ste.params=:params\nand ste.tenant.id=:tenantId",
			criteria, handlerName, params);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByHandlerParams(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String handlerName, java.lang.String params)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("handlerName", handlerName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("params", params, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.sync.model.ScheduledTaskEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.sync.model.ScheduledTaskEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.sync.model.ScheduledTaskEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.sync.model.ScheduledTaskEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.sync.model.ScheduledTaskEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.sync.model.ScheduledTaskEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.sync.model.ScheduledTaskEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByStopPendding
	 * @param id
	 * @return
	**/
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByStopPendding(
	    java.lang.Long id)
	
	{
		return findByStopPendding((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, id);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByStopPendding(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		return findByStopPendding("select ste\nfrom  com.soffid.iam.sync.model.ScheduledTaskEntity as ste\nwhere ste.id=:id and ste.stop is true\nand ste.tenant.id=:tenantId",
			criteria, id);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByStopPendding(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
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
			com.soffid.iam.sync.model.ScheduledTaskEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.sync.model.ScheduledTaskEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.sync.model.ScheduledTaskEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAllByServer
	 * @param server
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> findAllByServer(
	    java.lang.String server)
	
	{
		return findAllByServer((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> findAllByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
	{
		return findAllByServer("select ste from  com.soffid.iam.sync.model.ScheduledTaskEntity as ste join ste.tenant as tenant join tenant.servers as servers join servers.tenantServer as server where server.name=:server",
			criteria, server);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> findAllByServer(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	
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
			return (java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findEnabled
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> findEnabled(
)
	
	{
		return findEnabled((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> findEnabled(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findEnabled("select ste from  com.soffid.iam.sync.model.ScheduledTaskEntity as ste where ste.enabled is true",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> findEnabled(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.sync.api.ScheduledTask} object 
	 */
	public void toScheduledTask(com.soffid.iam.sync.model.ScheduledTaskEntity source, com.soffid.iam.sync.api.ScheduledTask target) {
		// Attributes for ScheduledTask
		target.setId(source.getId());
		// Incompatible types source.tenant and target.tenant
		target.setName(source.getName());
		target.setParams(source.getParams());
		// Missing attribute handlerName on entity
		// Missing attribute nextExecution on entity
		if (source.getLastExecution() == null) {
			target.setLastExecution(null);
		} else {
			target.setLastExecution(java.util.Calendar.getInstance());
			target.getLastExecution().setTime(source.getLastExecution());
		}
		if (source.getLastEnd() == null) {
			target.setLastEnd(null);
		} else {
			target.setLastEnd(java.util.Calendar.getInstance());
			target.getLastEnd().setTime(source.getLastEnd());
		}
		target.setLogReferenceID(source.getLogReferenceID());
		// Missing attribute dayPattern on entity
		// Missing attribute hoursPattern on entity
		// Missing attribute monthsPattern on entity
		// Missing attribute dayOfWeekPattern on entity
		// Missing attribute minutesPattern on entity
		target.setError(source.isError());
		target.setActive(source.isActive());
		target.setEnabled(source.isEnabled());
		target.setStatus(source.getStatus());
		target.setPercentageDone(source.getPercentageDone());
		target.setStop(source.getStop());
		// Missing attribute serverName on entity
		// Incompatible types source.logs and target.logs
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ScheduledTask} object 
	 */
	public com.soffid.iam.sync.api.ScheduledTask toScheduledTask(com.soffid.iam.sync.model.ScheduledTaskEntity entity) {
		final com.soffid.iam.sync.api.ScheduledTask target = new com.soffid.iam.sync.api.ScheduledTask();
		this.toScheduledTask(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ScheduledTask} list 
	 */
	public java.util.List<com.soffid.iam.sync.api.ScheduledTask> toScheduledTaskList (java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.sync.api.ScheduledTask> list =
				new java.util.LinkedList<com.soffid.iam.sync.api.ScheduledTask>();
			for (final com.soffid.iam.sync.model.ScheduledTaskEntity instance: instances)
			{
				list.add( toScheduledTask(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.sync.api.ScheduledTask} object 
	 */
	public void scheduledTaskToEntity (com.soffid.iam.sync.api.ScheduledTask source, com.soffid.iam.sync.model.ScheduledTaskEntity target, boolean copyIfNull) {
		// Attributes for ScheduledTaskEntity
		// Missing attribute handler on entity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getParams() != null)
		{
			target.setParams(source.getParams());
		}
		// Missing attribute schedulePattern on entity
		if (copyIfNull || source.getLastExecution() != null)
		{
			if (source.getLastExecution() == null) {
				target.setLastExecution(null);
			} else {
				target.setLastExecution(source.getLastExecution().getTime());
			}
		}
		if (copyIfNull || source.getLastEnd() != null)
		{
			if (source.getLastEnd() == null) {
				target.setLastEnd(null);
			} else {
				target.setLastEnd(source.getLastEnd().getTime());
			}
		}
		if (copyIfNull || source.getLogReferenceID() != null)
		{
			target.setLogReferenceID(source.getLogReferenceID());
		}
		target.setError(source.isError());
		// Missing attribute server on entity
		target.setActive(source.isActive());
		target.setEnabled(source.isEnabled());
		if (copyIfNull || source.getTenant() != null)
		{
			// Incompatible types source.tenant and target.tenant
		}
		if (copyIfNull || source.getStatus() != null)
		{
			target.setStatus(source.getStatus());
		}
		if (copyIfNull || source.getPercentageDone() != null)
		{
			target.setPercentageDone(source.getPercentageDone());
		}
		if (copyIfNull || source.getStop() != null)
		{
			target.setStop(source.getStop());
		}
		if (copyIfNull || source.getLogs() != null)
		{
			// Incompatible types source.logs and target.logs
		}
		if (copyIfNull || source.getCreatedOn() != null)
		{
			target.setCreatedOn(source.getCreatedOn());
		}
		if (copyIfNull || source.getCreatedBy() != null)
		{
			target.setCreatedBy(source.getCreatedBy());
		}
		if (copyIfNull || source.getUpdatedOn() != null)
		{
			target.setUpdatedOn(source.getUpdatedOn());
		}
		if (copyIfNull || source.getUpdatedBy() != null)
		{
			target.setUpdatedBy(source.getUpdatedBy());
		}
		if (copyIfNull || source.getDeletedOn() != null)
		{
			target.setDeletedOn(source.getDeletedOn());
		}
		if (copyIfNull || source.getDeletedBy() != null)
		{
			target.setDeletedBy(source.getDeletedBy());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ScheduledTask} object 
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity scheduledTaskToEntity (com.soffid.iam.sync.api.ScheduledTask instance) {
		com.soffid.iam.sync.model.ScheduledTaskEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newScheduledTaskEntity();
		scheduledTaskToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ScheduledTask} list 
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskEntity>  scheduledTaskToEntityList (java.util.Collection<com.soffid.iam.sync.api.ScheduledTask> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.sync.model.ScheduledTaskEntity> list =
			new java.util.LinkedList<com.soffid.iam.sync.model.ScheduledTaskEntity>();
		for (com.soffid.iam.sync.api.ScheduledTask instance: instances)
		{
			list.add (scheduledTaskToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} .
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity newScheduledTaskEntity()
	{
		return new com.soffid.iam.sync.model.ScheduledTaskEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.sync.model.ScheduledTaskEntity result = (com.soffid.iam.sync.model.ScheduledTaskEntity) this.getHibernateTemplate().get(com.soffid.iam.sync.model.ScheduledTaskEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.sync.model.ScheduledTaskEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.sync.model.ScheduledTaskEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ScheduledTaskEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ScheduledTaskEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ScheduledTaskEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ScheduledTaskEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ScheduledTaskEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ScheduledTaskEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.sync.model.ScheduledTaskEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ScheduledTaskEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.sync.model.ScheduledTaskEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.sync.model.ScheduledTaskEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.sync.model.ScheduledTaskEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
