//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity PrinterGroupEntity
 */
public abstract class PrinterGroupEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.PrinterGroupEntityDao
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

	com.soffid.iam.iga.model.GroupEntityDao groupEntityDao;

	/**
	 * Sets reference to <code>groupEntityDao</code>.
	 */
	public void setGroupEntityDao (com.soffid.iam.iga.model.GroupEntityDao groupEntityDao) {
		this.groupEntityDao = groupEntityDao;
	}

	/**
	 * Gets reference to <code>groupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupEntityDao getGroupEntityDao () {
		return groupEntityDao;
	}

	com.soffid.iam.am.service.NetworkService networkService;

	/**
	 * Sets reference to <code>networkService</code>.
	 */
	public void setNetworkService (com.soffid.iam.am.service.NetworkService networkService) {
		this.networkService = networkService;
	}

	/**
	 * Gets reference to <code>networkService</code>.
	 */
	public com.soffid.iam.am.service.NetworkService getNetworkService () {
		return networkService;
	}

	com.soffid.iam.iga.model.PrinterEntityDao printerEntityDao;

	/**
	 * Sets reference to <code>printerEntityDao</code>.
	 */
	public void setPrinterEntityDao (com.soffid.iam.iga.model.PrinterEntityDao printerEntityDao) {
		this.printerEntityDao = printerEntityDao;
	}

	/**
	 * Gets reference to <code>printerEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.PrinterEntityDao getPrinterEntityDao () {
		return printerEntityDao;
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
	 * Operation findByGroupAndPrinter
	 * @param group
	 * @param printer
	 * @return
	**/
	public com.soffid.iam.iga.model.PrinterGroupEntity findByGroupAndPrinter(
	    java.lang.String group, 
	    java.lang.String printer)
	
	{
		return findByGroupAndPrinter((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, group, printer);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.PrinterGroupEntity findByGroupAndPrinter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String group, java.lang.String printer)
	
	{
		return findByGroupAndPrinter("select grupImpressora from com.soffid.iam.iga.model.PrinterGroupEntity grupImpressora where grupImpressora.group.name = :group and grupImpressora.printer.name = :printer and grupImpressora.group.tenant.id = :tenantId order by grupImpressora.printer.name",
			criteria, group, printer);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.PrinterGroupEntity findByGroupAndPrinter(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String group, java.lang.String printer)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("group", group, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("printer", printer, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.PrinterGroupEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.PrinterGroupEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.PrinterGroupEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByGroup
	 * @param group
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> findByGroup(
	    java.lang.String group)
	
	{
		return findByGroup((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, group);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> findByGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String group)
	
	{
		return findByGroup("select grupImpressora from com.soffid.iam.iga.model.PrinterGroupEntity grupImpressora where grupImpressora.group.name = :group and grupImpressora.group.tenant.id = :tenantId order by grupImpressora.printer.name",
			criteria, group);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> findByGroup(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String group)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("group", group, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByPrinter
	 * @param printer
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> findByPrinter(
	    java.lang.String printer)
	
	{
		return findByPrinter((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, printer);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> findByPrinter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String printer)
	
	{
		return findByPrinter("select grupImpressora from com.soffid.iam.iga.model.PrinterGroupEntity grupImpressora where grupImpressora.printer.name = :printer and grupImpressora.printer.tenant.id=:tenantId order by grupImpressora.group.name",
			criteria, printer);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> findByPrinter(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String printer)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("printer", printer, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.PrinterGroup} object 
	 */
	public void toPrinterGroup(com.soffid.iam.iga.model.PrinterGroupEntity source, com.soffid.iam.iga.api.PrinterGroup target) {
		// Attributes for PrinterGroup
		// Missing attribute enabledByDefault on entity
		// Missing attribute groupCode on entity
		// Missing attribute printerCode on entity
		target.setId(source.getId());
		// Missing attribute printerServerName on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.PrinterGroup} object 
	 */
	public com.soffid.iam.iga.api.PrinterGroup toPrinterGroup(com.soffid.iam.iga.model.PrinterGroupEntity entity) {
		final com.soffid.iam.iga.api.PrinterGroup target = new com.soffid.iam.iga.api.PrinterGroup();
		this.toPrinterGroup(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.PrinterGroup} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.PrinterGroup> toPrinterGroupList (java.util.Collection<com.soffid.iam.iga.model.PrinterGroupEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.PrinterGroup> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.PrinterGroup>();
			for (final com.soffid.iam.iga.model.PrinterGroupEntity instance: instances)
			{
				list.add( toPrinterGroup(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.PrinterGroup} object 
	 */
	public void printerGroupToEntity (com.soffid.iam.iga.api.PrinterGroup source, com.soffid.iam.iga.model.PrinterGroupEntity target, boolean copyIfNull) {
		// Attributes for PrinterGroupEntity
		// Missing attribute order on entity
		// Missing attribute group on entity
		// Missing attribute printer on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.PrinterGroup} object 
	 */
	public com.soffid.iam.iga.model.PrinterGroupEntity printerGroupToEntity (com.soffid.iam.iga.api.PrinterGroup instance) {
		com.soffid.iam.iga.model.PrinterGroupEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newPrinterGroupEntity();
		printerGroupToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.PrinterGroup} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity>  printerGroupToEntityList (java.util.Collection<com.soffid.iam.iga.api.PrinterGroup> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.PrinterGroupEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.PrinterGroupEntity>();
		for (com.soffid.iam.iga.api.PrinterGroup instance: instances)
		{
			list.add (printerGroupToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.PrinterGroupEntity} .
	 */
	public com.soffid.iam.iga.model.PrinterGroupEntity newPrinterGroupEntity()
	{
		return new com.soffid.iam.iga.model.PrinterGroupEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.PrinterGroupEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.PrinterGroupEntity result = (com.soffid.iam.iga.model.PrinterGroupEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.PrinterGroupEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> result = (java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.PrinterGroupEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.PrinterGroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.PrinterGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PrinterGroupEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.PrinterGroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.PrinterGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PrinterGroupEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.PrinterGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PrinterGroupEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.PrinterGroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.PrinterGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PrinterGroupEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.PrinterGroupEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.PrinterGroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.PrinterGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PrinterGroupEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.PrinterGroupEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.PrinterGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PrinterGroupEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.PrinterGroupEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"PrinterGroupEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.PrinterGroupEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
