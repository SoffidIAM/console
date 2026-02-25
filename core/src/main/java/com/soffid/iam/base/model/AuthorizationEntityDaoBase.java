//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity AuthorizationEntity
 */
public abstract class AuthorizationEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.AuthorizationEntityDao
{
	com.soffid.iam.iga.model.ApplicationDomainEntityDao applicationDomainEntityDao;

	/**
	 * Sets reference to <code>applicationDomainEntityDao</code>.
	 */
	public void setApplicationDomainEntityDao (com.soffid.iam.iga.model.ApplicationDomainEntityDao applicationDomainEntityDao) {
		this.applicationDomainEntityDao = applicationDomainEntityDao;
	}

	/**
	 * Gets reference to <code>applicationDomainEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntityDao getApplicationDomainEntityDao () {
		return applicationDomainEntityDao;
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

	com.soffid.iam.iga.model.RoleAccountEntityDao roleAccountEntityDao;

	/**
	 * Sets reference to <code>roleAccountEntityDao</code>.
	 */
	public void setRoleAccountEntityDao (com.soffid.iam.iga.model.RoleAccountEntityDao roleAccountEntityDao) {
		this.roleAccountEntityDao = roleAccountEntityDao;
	}

	/**
	 * Gets reference to <code>roleAccountEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleAccountEntityDao getRoleAccountEntityDao () {
		return roleAccountEntityDao;
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


	/**
	 * Operation findByAuthorization
	 * @param authorization
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> findByAuthorization(
	    java.lang.String authorization)
	
	{
		return findByAuthorization((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, authorization);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> findByAuthorization(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String authorization)
	
	{
		return findByAuthorization("from com.soffid.iam.base.model.AuthorizationEntity as autoritzacioRolEntity \nwhere autoritzacioRolEntity.authorization = :authorization and\n autoritzacioRolEntity.tenant.id = :tenantId \norder by autoritzacioRolEntity.role.name",
			criteria, authorization);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> findByAuthorization(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String authorization)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("authorization", authorization, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.AuthorizationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByRoleID
	 * @param roleId
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> findByRoleID(
	    java.lang.Long roleId)
	
	{
		return findByRoleID((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, roleId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> findByRoleID(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long roleId)
	
	{
		return findByRoleID("from com.soffid.iam.base.model.AuthorizationEntity as autoritzacioRolEntity \nwhere autoritzacioRolEntity.role.id = :roleId and\nautoritzacioRolEntity.tenant.id = :tenantId \norder by autoritzacioRolEntity.authorization",
			criteria, roleId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> findByRoleID(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long roleId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("roleId", roleId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.AuthorizationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.AuthorizationRole} object 
	 */
	public void toAuthorizationRole(com.soffid.iam.base.model.AuthorizationEntity source, com.soffid.iam.base.api.AuthorizationRole target) {
		// Attributes for AuthorizationRole
		target.setId(source.getId());
		target.setAuthorization(source.getAuthorization());
		// Incompatible types source.role and target.role
		// Missing attribute userRoleValueDomain on entity
		// Missing attribute description on entity
		// Missing attribute domainType on entity
		// Missing attribute businessGroupScope on entity
		// Missing attribute scope on entity
		// Missing attribute inherit on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.AuthorizationRole} object 
	 */
	public com.soffid.iam.base.api.AuthorizationRole toAuthorizationRole(com.soffid.iam.base.model.AuthorizationEntity entity) {
		final com.soffid.iam.base.api.AuthorizationRole target = new com.soffid.iam.base.api.AuthorizationRole();
		this.toAuthorizationRole(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.AuthorizationRole} list 
	 */
	public java.util.List<com.soffid.iam.base.api.AuthorizationRole> toAuthorizationRoleList (java.util.Collection<com.soffid.iam.base.model.AuthorizationEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.AuthorizationRole> list =
				new java.util.LinkedList<com.soffid.iam.base.api.AuthorizationRole>();
			for (final com.soffid.iam.base.model.AuthorizationEntity instance: instances)
			{
				list.add( toAuthorizationRole(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.AuthorizationRole} object 
	 */
	public void authorizationRoleToEntity (com.soffid.iam.base.api.AuthorizationRole source, com.soffid.iam.base.model.AuthorizationEntity target, boolean copyIfNull) {
		// Attributes for AuthorizationEntity
		if (copyIfNull || source.getAuthorization() != null)
		{
			target.setAuthorization(source.getAuthorization());
		}
		if (copyIfNull || source.getRole() != null)
		{
			// Incompatible types source.role and target.role
		}
		// Missing attribute tenant on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.AuthorizationRole} object 
	 */
	public com.soffid.iam.base.model.AuthorizationEntity authorizationRoleToEntity (com.soffid.iam.base.api.AuthorizationRole instance) {
		com.soffid.iam.base.model.AuthorizationEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newAuthorizationEntity();
		authorizationRoleToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.AuthorizationRole} list 
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity>  authorizationRoleToEntityList (java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.AuthorizationEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.AuthorizationEntity>();
		for (com.soffid.iam.base.api.AuthorizationRole instance: instances)
		{
			list.add (authorizationRoleToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.AuthorizationEntity} .
	 */
	public com.soffid.iam.base.model.AuthorizationEntity newAuthorizationEntity()
	{
		return new com.soffid.iam.base.model.AuthorizationEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.AuthorizationEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.AuthorizationEntity result = (com.soffid.iam.base.model.AuthorizationEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.AuthorizationEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.base.model.AuthorizationEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.base.model.AuthorizationEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.AuthorizationEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.AuthorizationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AuthorizationEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.AuthorizationEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.AuthorizationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AuthorizationEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.AuthorizationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AuthorizationEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.AuthorizationEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.AuthorizationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AuthorizationEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AuthorizationEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.AuthorizationEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.AuthorizationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AuthorizationEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AuthorizationEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.AuthorizationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AuthorizationEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AuthorizationEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AuthorizationEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.AuthorizationEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.AuthorizationEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.AuthorizationEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
