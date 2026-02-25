//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity PrinterEntity
 */
public abstract class PrinterEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.PrinterEntityDao
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

	com.soffid.iam.iga.model.PrinterGroupEntityDao printerGroupEntityDao;

	/**
	 * Sets reference to <code>printerGroupEntityDao</code>.
	 */
	public void setPrinterGroupEntityDao (com.soffid.iam.iga.model.PrinterGroupEntityDao printerGroupEntityDao) {
		this.printerGroupEntityDao = printerGroupEntityDao;
	}

	/**
	 * Gets reference to <code>printerGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.PrinterGroupEntityDao getPrinterGroupEntityDao () {
		return printerGroupEntityDao;
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

	com.soffid.iam.iga.model.UserPrinterEntityDao userPrinterEntityDao;

	/**
	 * Sets reference to <code>userPrinterEntityDao</code>.
	 */
	public void setUserPrinterEntityDao (com.soffid.iam.iga.model.UserPrinterEntityDao userPrinterEntityDao) {
		this.userPrinterEntityDao = userPrinterEntityDao;
	}

	/**
	 * Gets reference to <code>userPrinterEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserPrinterEntityDao getUserPrinterEntityDao () {
		return userPrinterEntityDao;
	}


	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.PrinterEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.PrinterEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.iga.model.PrinterEntity p where p.name=:name and p.tenant.id = :tenantId",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.PrinterEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.iga.model.PrinterEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.PrinterEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.PrinterEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findPrintersByCriteria
	 * @param model
	 * @param name
	 * @param local
	 * @param host
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity> findPrintersByCriteria(
	    java.lang.String model, 
	    java.lang.String name, 
	    java.lang.String local, 
	    java.lang.String host)
	
	{
		return findPrintersByCriteria((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, model, name, local, host);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity> findPrintersByCriteria(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String model, java.lang.String name, java.lang.String local, java.lang.String host)
	
	{
		return findPrintersByCriteria("select impressora from com.soffid.iam.iga.model.PrinterEntity impressora where (:name is null or impressora.name like :name) and (:model is null or impressora.model like :model) and (:local is null or impressora.local = :local) and (:host is null or  impressora.server.name like :host) and impressora.tenant.id = :tenantId order by impressora.name",
			criteria, model, name, local, host);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity> findPrintersByCriteria(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String model, java.lang.String name, java.lang.String local, java.lang.String host)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("model", model, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("local", local, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("host", host, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.PrinterEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.Printer} object 
	 */
	public void toPrinter(com.soffid.iam.iga.model.PrinterEntity source, com.soffid.iam.iga.api.Printer target) {
		// Attributes for Printer
		target.setModel(source.getModel());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		// Missing attribute hostName on entity
		target.setLocal(source.getLocal());
		target.setId(source.getId());
		// Incompatible types source.users and target.users
		// Incompatible types source.groups and target.groups
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Printer} object 
	 */
	public com.soffid.iam.iga.api.Printer toPrinter(com.soffid.iam.iga.model.PrinterEntity entity) {
		final com.soffid.iam.iga.api.Printer target = new com.soffid.iam.iga.api.Printer();
		this.toPrinter(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Printer} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.Printer> toPrinterList (java.util.Collection<com.soffid.iam.iga.model.PrinterEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.Printer> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.Printer>();
			for (final com.soffid.iam.iga.model.PrinterEntity instance: instances)
			{
				list.add( toPrinter(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.Printer} object 
	 */
	public void printerToEntity (com.soffid.iam.iga.api.Printer source, com.soffid.iam.iga.model.PrinterEntity target, boolean copyIfNull) {
		// Attributes for PrinterEntity
		if (copyIfNull || source.getModel() != null)
		{
			target.setModel(source.getModel());
		}
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getLocal() != null)
		{
			target.setLocal(source.getLocal());
		}
		// Missing attribute server on entity
		// Missing attribute tenant on entity
		if (copyIfNull || source.getGroups() != null)
		{
			// Incompatible types source.groups and target.groups
		}
		if (copyIfNull || source.getUsers() != null)
		{
			// Incompatible types source.users and target.users
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
	 *  Transforms from {@link com.soffid.iam.iga.api.Printer} object 
	 */
	public com.soffid.iam.iga.model.PrinterEntity printerToEntity (com.soffid.iam.iga.api.Printer instance) {
		com.soffid.iam.iga.model.PrinterEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newPrinterEntity();
		printerToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Printer} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity>  printerToEntityList (java.util.Collection<com.soffid.iam.iga.api.Printer> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.PrinterEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.PrinterEntity>();
		for (com.soffid.iam.iga.api.Printer instance: instances)
		{
			list.add (printerToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.PrinterEntity} .
	 */
	public com.soffid.iam.iga.model.PrinterEntity newPrinterEntity()
	{
		return new com.soffid.iam.iga.model.PrinterEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.PrinterEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.PrinterEntity result = (com.soffid.iam.iga.model.PrinterEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.PrinterEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.PrinterEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.PrinterEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.PrinterEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.PrinterEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PrinterEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.iga.model.PrinterEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.PrinterEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PrinterEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.PrinterEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PrinterEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.PrinterEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.PrinterEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PrinterEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.PrinterEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.PrinterEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.PrinterEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PrinterEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.PrinterEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.PrinterEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PrinterEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.PrinterEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"PrinterEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.PrinterEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.PrinterEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.PrinterEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
