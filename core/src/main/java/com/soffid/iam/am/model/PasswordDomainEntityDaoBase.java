//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity PasswordDomainEntity
 */
public abstract class PasswordDomainEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.PasswordDomainEntityDao
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

	com.soffid.iam.am.model.PasswordPolicyEntityDao passwordPolicyEntityDao;

	/**
	 * Sets reference to <code>passwordPolicyEntityDao</code>.
	 */
	public void setPasswordPolicyEntityDao (com.soffid.iam.am.model.PasswordPolicyEntityDao passwordPolicyEntityDao) {
		this.passwordPolicyEntityDao = passwordPolicyEntityDao;
	}

	/**
	 * Gets reference to <code>passwordPolicyEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntityDao getPasswordPolicyEntityDao () {
		return passwordPolicyEntityDao;
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

	com.soffid.iam.iga.model.UserDomainEntityDao userDomainEntityDao;

	/**
	 * Sets reference to <code>userDomainEntityDao</code>.
	 */
	public void setUserDomainEntityDao (com.soffid.iam.iga.model.UserDomainEntityDao userDomainEntityDao) {
		this.userDomainEntityDao = userDomainEntityDao;
	}

	/**
	 * Gets reference to <code>userDomainEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserDomainEntityDao getUserDomainEntityDao () {
		return userDomainEntityDao;
	}


	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.am.model.PasswordDomainEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.am.model.PasswordDomainEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.am.model.PasswordDomainEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.PasswordDomainEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.PasswordDomainEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findBySystem
	 * @param systemName
	 * @return
	**/
	public com.soffid.iam.am.model.PasswordDomainEntity findBySystem(
	    java.lang.String systemName)
	
	{
		return findBySystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, systemName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity findBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	
	{
		return findBySystem("select de.passwordDomain from com.soffid.iam.iga.model.SystemEntity as de where de.name=:systemName and de.tenant.id = :tenantId",
			criteria, systemName);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity findBySystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("systemName", systemName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.PasswordDomainEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.PasswordDomainEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.PasswordDomainEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findDefaultDomain
	 * @param userId
	 * @return
	**/
	public com.soffid.iam.am.model.PasswordDomainEntity findDefaultDomain(
	    long userId)
	
	{
		return findDefaultDomain((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity findDefaultDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long userId)
	
	{
		return findDefaultDomain("select pd from com.soffid.iam.base.model.UserEntity as usuari inner join usuari.userType as tipus inner join tipus.policies as politica  with politica.type='M'  inner join politica.passwordDomain pd where usuari.id= :userId and pd.tenant.id = :tenantId",
			criteria, userId);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity findDefaultDomain(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long userId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userId", userId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.PasswordDomainEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.PasswordDomainEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.PasswordDomainEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUser
	 * @param id
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity> findByUser(
	    java.lang.Long id)
	
	{
		return findByUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, id);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity> findByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		return findByUser("select distinct pd from com.soffid.iam.base.model.UserEntity as user join user.accounts as ua join ua.account.system.passwordDomain as pd where pd.tenant.id =:tenantId and user.id=:id",
			criteria, id);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity> findByUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
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
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.PasswordDomainEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.PasswordDomain} object 
	 */
	public void toPasswordDomain(com.soffid.iam.am.model.PasswordDomainEntity source, com.soffid.iam.am.api.PasswordDomain target) {
		// Attributes for PasswordDomain
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordDomain} object 
	 */
	public com.soffid.iam.am.api.PasswordDomain toPasswordDomain(com.soffid.iam.am.model.PasswordDomainEntity entity) {
		final com.soffid.iam.am.api.PasswordDomain target = new com.soffid.iam.am.api.PasswordDomain();
		this.toPasswordDomain(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordDomain} list 
	 */
	public java.util.List<com.soffid.iam.am.api.PasswordDomain> toPasswordDomainList (java.util.Collection<com.soffid.iam.am.model.PasswordDomainEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.PasswordDomain> list =
				new java.util.LinkedList<com.soffid.iam.am.api.PasswordDomain>();
			for (final com.soffid.iam.am.model.PasswordDomainEntity instance: instances)
			{
				list.add( toPasswordDomain(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.PasswordDomain} object 
	 */
	public void passwordDomainToEntity (com.soffid.iam.am.api.PasswordDomain source, com.soffid.iam.am.model.PasswordDomainEntity target, boolean copyIfNull) {
		// Attributes for PasswordDomainEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		// Missing attribute tenant on entity
		// Missing attribute passwordPolicies on entity
		// Missing attribute systems on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordDomain} object 
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity passwordDomainToEntity (com.soffid.iam.am.api.PasswordDomain instance) {
		com.soffid.iam.am.model.PasswordDomainEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newPasswordDomainEntity();
		passwordDomainToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordDomain} list 
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity>  passwordDomainToEntityList (java.util.Collection<com.soffid.iam.am.api.PasswordDomain> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.PasswordDomainEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.PasswordDomainEntity>();
		for (com.soffid.iam.am.api.PasswordDomain instance: instances)
		{
			list.add (passwordDomainToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.PasswordDomainEntity} .
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity newPasswordDomainEntity()
	{
		return new com.soffid.iam.am.model.PasswordDomainEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.PasswordDomainEntity result = (com.soffid.iam.am.model.PasswordDomainEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.PasswordDomainEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.PasswordDomainEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.PasswordDomainEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.PasswordDomainEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.PasswordDomainEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PasswordDomainEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.PasswordDomainEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.PasswordDomainEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PasswordDomainEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.PasswordDomainEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PasswordDomainEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.PasswordDomainEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.PasswordDomainEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PasswordDomainEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.PasswordDomainEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.PasswordDomainEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.PasswordDomainEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PasswordDomainEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.PasswordDomainEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.PasswordDomainEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PasswordDomainEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.PasswordDomainEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"PasswordDomainEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.PasswordDomainEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.PasswordDomainEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.PasswordDomainEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
