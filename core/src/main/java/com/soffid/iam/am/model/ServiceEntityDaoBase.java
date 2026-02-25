//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity ServiceEntity
 */
public abstract class ServiceEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.ServiceEntityDao
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
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.am.model.ServiceEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.ServiceEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.am.model.ServiceEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.ServiceEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.am.model.ServiceEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.ServiceEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.ServiceEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAllByName
	 * @param name
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.ServiceEntity> findAllByName(
	    java.lang.String name)
	
	{
		return findAllByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.ServiceEntity> findAllByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findAllByName("select servei from com.soffid.iam.am.model.ServiceEntity servei where servei.name like :name and servei.tenant.id = :tenantId order by servei.name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.ServiceEntity> findAllByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.ServiceEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.Service} object 
	 */
	public void toService(com.soffid.iam.am.model.ServiceEntity source, com.soffid.iam.am.api.Service target) {
		// Attributes for Service
		// Missing attribute code on entity
		target.setDescription(source.getDescription());
		target.setId(source.getId());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Service} object 
	 */
	public com.soffid.iam.am.api.Service toService(com.soffid.iam.am.model.ServiceEntity entity) {
		final com.soffid.iam.am.api.Service target = new com.soffid.iam.am.api.Service();
		this.toService(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Service} list 
	 */
	public java.util.List<com.soffid.iam.am.api.Service> toServiceList (java.util.Collection<com.soffid.iam.am.model.ServiceEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.Service> list =
				new java.util.LinkedList<com.soffid.iam.am.api.Service>();
			for (final com.soffid.iam.am.model.ServiceEntity instance: instances)
			{
				list.add( toService(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.Service} object 
	 */
	public void serviceToEntity (com.soffid.iam.am.api.Service source, com.soffid.iam.am.model.ServiceEntity target, boolean copyIfNull) {
		// Attributes for ServiceEntity
		// Missing attribute name on entity
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		// Missing attribute tenant on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Service} object 
	 */
	public com.soffid.iam.am.model.ServiceEntity serviceToEntity (com.soffid.iam.am.api.Service instance) {
		com.soffid.iam.am.model.ServiceEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newServiceEntity();
		serviceToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Service} list 
	 */
	public java.util.List<com.soffid.iam.am.model.ServiceEntity>  serviceToEntityList (java.util.Collection<com.soffid.iam.am.api.Service> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.ServiceEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.ServiceEntity>();
		for (com.soffid.iam.am.api.Service instance: instances)
		{
			list.add (serviceToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.ServiceEntity} .
	 */
	public com.soffid.iam.am.model.ServiceEntity newServiceEntity()
	{
		return new com.soffid.iam.am.model.ServiceEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.ServiceEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.ServiceEntity result = (com.soffid.iam.am.model.ServiceEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.ServiceEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.ServiceEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.ServiceEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.ServiceEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.ServiceEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.ServiceEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServiceEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.ServiceEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.ServiceEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServiceEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.ServiceEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ServiceEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.ServiceEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.ServiceEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServiceEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.ServiceEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.ServiceEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.ServiceEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServiceEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.ServiceEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.ServiceEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ServiceEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.ServiceEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ServiceEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.ServiceEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.ServiceEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.ServiceEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.ServiceEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.ServiceEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
