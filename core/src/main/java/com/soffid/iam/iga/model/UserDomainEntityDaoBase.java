//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity UserDomainEntity
 */
public abstract class UserDomainEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.UserDomainEntityDao
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

	com.soffid.iam.am.model.PasswordDomainEntityDao passwordDomainEntityDao;

	/**
	 * Sets reference to <code>passwordDomainEntityDao</code>.
	 */
	public void setPasswordDomainEntityDao (com.soffid.iam.am.model.PasswordDomainEntityDao passwordDomainEntityDao) {
		this.passwordDomainEntityDao = passwordDomainEntityDao;
	}

	/**
	 * Gets reference to <code>passwordDomainEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PasswordDomainEntityDao getPasswordDomainEntityDao () {
		return passwordDomainEntityDao;
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
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.UserDomainEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.UserDomainEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.iga.model.UserDomainEntity ud where ud.name = :name and ud.tenant.id = :tenantId",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.UserDomainEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.iga.model.UserDomainEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.UserDomainEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.UserDomainEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findBySytem
	 * @param system
	 * @return
	**/
	public com.soffid.iam.iga.model.UserDomainEntity findBySytem(
	    java.lang.String system)
	
	{
		return findBySytem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.UserDomainEntity findBySytem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	
	{
		return findBySytem("select du from com.soffid.iam.iga.model.SystemEntity as dispatcher left join dispatcher.userDomain as du where dispatcher.name=:system and dispatcher.tenant.id = :tenantId",
			criteria, system);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.UserDomainEntity findBySytem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	
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
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.UserDomainEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.UserDomainEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.UserDomainEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.UserDomain} object 
	 */
	public void toUserDomain(com.soffid.iam.iga.model.UserDomainEntity source, com.soffid.iam.iga.api.UserDomain target) {
		// Attributes for UserDomain
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setType(source.getType());
		target.setBshExpr(source.getBshExpr());
		target.setBshExprCreate(source.getBshExprCreate());
		target.setBeanGenerator(source.getBeanGenerator());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserDomain} object 
	 */
	public com.soffid.iam.iga.api.UserDomain toUserDomain(com.soffid.iam.iga.model.UserDomainEntity entity) {
		final com.soffid.iam.iga.api.UserDomain target = new com.soffid.iam.iga.api.UserDomain();
		this.toUserDomain(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserDomain} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.UserDomain> toUserDomainList (java.util.Collection<com.soffid.iam.iga.model.UserDomainEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.UserDomain> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.UserDomain>();
			for (final com.soffid.iam.iga.model.UserDomainEntity instance: instances)
			{
				list.add( toUserDomain(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.UserDomain} object 
	 */
	public void userDomainToEntity (com.soffid.iam.iga.api.UserDomain source, com.soffid.iam.iga.model.UserDomainEntity target, boolean copyIfNull) {
		// Attributes for UserDomainEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		// Missing attribute systems on entity
		if (copyIfNull || source.getBshExpr() != null)
		{
			target.setBshExpr(source.getBshExpr());
		}
		if (copyIfNull || source.getBshExprCreate() != null)
		{
			target.setBshExprCreate(source.getBshExprCreate());
		}
		if (copyIfNull || source.getBeanGenerator() != null)
		{
			target.setBeanGenerator(source.getBeanGenerator());
		}
		// Missing attribute tenant on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserDomain} object 
	 */
	public com.soffid.iam.iga.model.UserDomainEntity userDomainToEntity (com.soffid.iam.iga.api.UserDomain instance) {
		com.soffid.iam.iga.model.UserDomainEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newUserDomainEntity();
		userDomainToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserDomain} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.UserDomainEntity>  userDomainToEntityList (java.util.Collection<com.soffid.iam.iga.api.UserDomain> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.UserDomainEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.UserDomainEntity>();
		for (com.soffid.iam.iga.api.UserDomain instance: instances)
		{
			list.add (userDomainToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.UserDomainEntity} .
	 */
	public com.soffid.iam.iga.model.UserDomainEntity newUserDomainEntity()
	{
		return new com.soffid.iam.iga.model.UserDomainEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.UserDomainEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.UserDomainEntity result = (com.soffid.iam.iga.model.UserDomainEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.UserDomainEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.UserDomainEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.UserDomainEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.UserDomainEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.UserDomainEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.UserDomainEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserDomainEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.UserDomainEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.UserDomainEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserDomainEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.UserDomainEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserDomainEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.UserDomainEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.UserDomainEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserDomainEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserDomainEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.UserDomainEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.UserDomainEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserDomainEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserDomainEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.UserDomainEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserDomainEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserDomainEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"UserDomainEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.UserDomainEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.UserDomainEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.UserDomainEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.UserDomainEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.UserDomainEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
