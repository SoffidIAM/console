//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity UserPrinterEntity
 */
public abstract class UserPrinterEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.UserPrinterEntityDao
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
	 * Operation findUserByUserAndPrinter
	 * @param userName
	 * @param printer
	 * @return
	**/
	public com.soffid.iam.iga.model.UserPrinterEntity findUserByUserAndPrinter(
	    java.lang.String userName, 
	    java.lang.String printer)
	
	{
		return findUserByUserAndPrinter((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName, printer);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.UserPrinterEntity findUserByUserAndPrinter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String printer)
	
	{
		return findUserByUserAndPrinter("from com.soffid.iam.iga.model.UserPrinterEntity as ui where ui.user.userName=:userName and ui.printer.name=:printer and ui.user.tenant.id = :tenantId order by ui.user.userName, ui.printer.name",
			criteria, userName, printer);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.UserPrinterEntity findUserByUserAndPrinter(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String printer)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("printer", printer, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.UserPrinterEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.UserPrinterEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.UserPrinterEntity) results.iterator().next();
			}
			return result;
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
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> findByPrinter(
	    java.lang.String printer)
	
	{
		return findByPrinter((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, printer);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> findByPrinter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String printer)
	
	{
		return findByPrinter("from com.soffid.iam.iga.model.UserPrinterEntity as ui where ui.printer.name=:printer and ui.printer.tenant.id = :tenantId order by ui.user.userName, ui.printer.name",
			criteria, printer);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> findByPrinter(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String printer)
	
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
			return (java.util.List<com.soffid.iam.iga.model.UserPrinterEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUser
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> findByUser(
	    java.lang.String userName)
	
	{
		return findByUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> findByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findByUser("from com.soffid.iam.iga.model.UserPrinterEntity as ui where ui.user.userName=:userName and ui.user.tenant.id = :tenantId order by ui.user.userName, ui.printer.name",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> findByUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
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
			return (java.util.List<com.soffid.iam.iga.model.UserPrinterEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.PrinterUser} object 
	 */
	public void toPrinterUser(com.soffid.iam.iga.model.UserPrinterEntity source, com.soffid.iam.iga.api.PrinterUser target) {
		// Attributes for PrinterUser
		// Incompatible types source.printer and target.printer
		// Incompatible types source.user and target.user
		// Missing attribute enabledByDefault on entity
		target.setId(source.getId());
		// Missing attribute fullName on entity
		// Missing attribute printerServerName on entity
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.PrinterUser} object 
	 */
	public com.soffid.iam.iga.api.PrinterUser toPrinterUser(com.soffid.iam.iga.model.UserPrinterEntity entity) {
		final com.soffid.iam.iga.api.PrinterUser target = new com.soffid.iam.iga.api.PrinterUser();
		this.toPrinterUser(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.PrinterUser} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.PrinterUser> toPrinterUserList (java.util.Collection<com.soffid.iam.iga.model.UserPrinterEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.PrinterUser> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.PrinterUser>();
			for (final com.soffid.iam.iga.model.UserPrinterEntity instance: instances)
			{
				list.add( toPrinterUser(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.PrinterUser} object 
	 */
	public void printerUserToEntity (com.soffid.iam.iga.api.PrinterUser source, com.soffid.iam.iga.model.UserPrinterEntity target, boolean copyIfNull) {
		// Attributes for UserPrinterEntity
		// Missing attribute order on entity
		if (copyIfNull || source.getPrinter() != null)
		{
			// Incompatible types source.printer and target.printer
		}
		if (copyIfNull || source.getUser() != null)
		{
			// Incompatible types source.user and target.user
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
	 *  Transforms from {@link com.soffid.iam.iga.api.PrinterUser} object 
	 */
	public com.soffid.iam.iga.model.UserPrinterEntity printerUserToEntity (com.soffid.iam.iga.api.PrinterUser instance) {
		com.soffid.iam.iga.model.UserPrinterEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newUserPrinterEntity();
		printerUserToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.PrinterUser} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity>  printerUserToEntityList (java.util.Collection<com.soffid.iam.iga.api.PrinterUser> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.UserPrinterEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.UserPrinterEntity>();
		for (com.soffid.iam.iga.api.PrinterUser instance: instances)
		{
			list.add (printerUserToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.UserPrinterEntity} .
	 */
	public com.soffid.iam.iga.model.UserPrinterEntity newUserPrinterEntity()
	{
		return new com.soffid.iam.iga.model.UserPrinterEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.UserPrinterEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.UserPrinterEntity result = (com.soffid.iam.iga.model.UserPrinterEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.UserPrinterEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> result = (java.util.List<com.soffid.iam.iga.model.UserPrinterEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.UserPrinterEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.UserPrinterEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.UserPrinterEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserPrinterEntityDao.create - 'entity' can not be null");
		}

		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.UserPrinterEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.UserPrinterEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserPrinterEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.UserPrinterEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserPrinterEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.UserPrinterEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.UserPrinterEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserPrinterEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserPrinterEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.UserPrinterEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.UserPrinterEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserPrinterEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserPrinterEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.UserPrinterEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserPrinterEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserPrinterEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"UserPrinterEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.UserPrinterEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.UserPrinterEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.UserPrinterEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
