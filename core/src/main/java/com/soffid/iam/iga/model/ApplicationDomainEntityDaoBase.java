//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity ApplicationDomainEntity
 */
public abstract class ApplicationDomainEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.ApplicationDomainEntityDao
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

	com.soffid.iam.iga.model.DomainValueEntityDao domainValueEntityDao;

	/**
	 * Sets reference to <code>domainValueEntityDao</code>.
	 */
	public void setDomainValueEntityDao (com.soffid.iam.iga.model.DomainValueEntityDao domainValueEntityDao) {
		this.domainValueEntityDao = domainValueEntityDao;
	}

	/**
	 * Gets reference to <code>domainValueEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.DomainValueEntityDao getDomainValueEntityDao () {
		return domainValueEntityDao;
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

	com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao;

	/**
	 * Sets reference to <code>informationSystemEntityDao</code>.
	 */
	public void setInformationSystemEntityDao (com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao) {
		this.informationSystemEntityDao = informationSystemEntityDao;
	}

	/**
	 * Gets reference to <code>informationSystemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.InformationSystemEntityDao getInformationSystemEntityDao () {
		return informationSystemEntityDao;
	}

	com.soffid.iam.iga.model.RoleEntityDao roleEntityDao;

	/**
	 * Sets reference to <code>roleEntityDao</code>.
	 */
	public void setRoleEntityDao (com.soffid.iam.iga.model.RoleEntityDao roleEntityDao) {
		this.roleEntityDao = roleEntityDao;
	}

	/**
	 * Gets reference to <code>roleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleEntityDao getRoleEntityDao () {
		return roleEntityDao;
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
	 * Operation findByName
	 * @param name
	 * @param informationSystem
	 * @return
	**/
	public com.soffid.iam.iga.model.ApplicationDomainEntity findByName(
	    java.lang.String name, 
	    java.lang.String informationSystem)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, informationSystem);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String informationSystem)
	
	{
		return findByName("select dominiAplicacio from com.soffid.iam.iga.model.ApplicationDomainEntity dominiAplicacio left join dominiAplicacio.informationSystem aplicacio where aplicacio.name = :informationSystem and dominiAplicacio.name = :name and aplicacio.tenant.id = :tenantId order by dominiAplicacio.name",
			criteria, name, informationSystem);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String informationSystem)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("informationSystem", informationSystem, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.ApplicationDomainEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.ApplicationDomainEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.ApplicationDomainEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByDomainAndRole
	 * @param domainName
	 * @param roleName
	 * @return
	**/
	public com.soffid.iam.iga.model.ApplicationDomainEntity findByDomainAndRole(
	    java.lang.String domainName, 
	    java.lang.String roleName)
	
	{
		return findByDomainAndRole((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, domainName, roleName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity findByDomainAndRole(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String domainName, java.lang.String roleName)
	
	{
		return findByDomainAndRole("select dominiAplicacio from com.soffid.iam.iga.model.ApplicationDomainEntity as dominiAplicacio \nleft join dominiAplicacio.roles as role where ((:roleName is null and role is null) or        (:roleName is not null and role.name = :roleName)) and   dominiAplicacio.name = :domainName and   dominiAplicacio.informationSystem.tenant.id = :tenantId",
			criteria, domainName, roleName);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity findByDomainAndRole(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String domainName, java.lang.String roleName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("domainName", domainName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("roleName", roleName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.ApplicationDomainEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.ApplicationDomainEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.ApplicationDomainEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByInformationSystem
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> findByInformationSystem(
	    java.lang.String informationSystem)
	
	{
		return findByInformationSystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, informationSystem);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> findByInformationSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	
	{
		return findByInformationSystem("select dominiAplicacio from com.soffid.iam.iga.model.ApplicationDomainEntity dominiAplicacio left join dominiAplicacio.informationSystem aplicacio where aplicacio.name = :informationSystem and aplicacio.tenant.id = :tenantId order by dominiAplicacio.name",
			criteria, informationSystem);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> findByInformationSystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("informationSystem", informationSystem, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByInformationSystemPattern
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> findByInformationSystemPattern(
	    java.lang.String informationSystem)
	
	{
		return findByInformationSystemPattern((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, informationSystem);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> findByInformationSystemPattern(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	
	{
		return findByInformationSystemPattern("select dominiAplicacio from com.soffid.iam.iga.model.ApplicationDomainEntity dominiAplicacio left join dominiAplicacio.informationSystem aplicacio where aplicacio.name like :informationSystem and aplicacio.tenant.id = :tenantId order by dominiAplicacio.name",
			criteria, informationSystem);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> findByInformationSystemPattern(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("informationSystem", informationSystem, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.Domain} object 
	 */
	public void toDomain(com.soffid.iam.iga.model.ApplicationDomainEntity source, com.soffid.iam.iga.api.Domain target) {
		// Attributes for Domain
		target.setId(source.getId());
		target.setName(source.getName());
		// Incompatible types source.informationSystem and target.informationSystem
		target.setDescription(source.getDescription());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Domain} object 
	 */
	public com.soffid.iam.iga.api.Domain toDomain(com.soffid.iam.iga.model.ApplicationDomainEntity entity) {
		final com.soffid.iam.iga.api.Domain target = new com.soffid.iam.iga.api.Domain();
		this.toDomain(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Domain} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.Domain> toDomainList (java.util.Collection<com.soffid.iam.iga.model.ApplicationDomainEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.Domain> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.Domain>();
			for (final com.soffid.iam.iga.model.ApplicationDomainEntity instance: instances)
			{
				list.add( toDomain(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.Domain} object 
	 */
	public void domainToEntity (com.soffid.iam.iga.api.Domain source, com.soffid.iam.iga.model.ApplicationDomainEntity target, boolean copyIfNull) {
		// Attributes for ApplicationDomainEntity
		// Missing attribute values on entity
		// Missing attribute roles on entity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getInformationSystem() != null)
		{
			// Incompatible types source.informationSystem and target.informationSystem
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		// Missing attribute tenant on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Domain} object 
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity domainToEntity (com.soffid.iam.iga.api.Domain instance) {
		com.soffid.iam.iga.model.ApplicationDomainEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newApplicationDomainEntity();
		domainToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Domain} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity>  domainToEntityList (java.util.Collection<com.soffid.iam.iga.api.Domain> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.ApplicationDomainEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.ApplicationDomainEntity>();
		for (com.soffid.iam.iga.api.Domain instance: instances)
		{
			list.add (domainToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} .
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity newApplicationDomainEntity()
	{
		return new com.soffid.iam.iga.model.ApplicationDomainEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.ApplicationDomainEntity result = (com.soffid.iam.iga.model.ApplicationDomainEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.ApplicationDomainEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.ApplicationDomainEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ApplicationDomainEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ApplicationDomainEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ApplicationDomainEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ApplicationDomainEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ApplicationDomainEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ApplicationDomainEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ApplicationDomainEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ApplicationDomainEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ApplicationDomainEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ApplicationDomainEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ApplicationDomainEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ApplicationDomainEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ApplicationDomainEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ApplicationDomainEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ApplicationDomainEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ApplicationDomainEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.ApplicationDomainEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
