//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity RuleEntity
 */
public abstract class RuleEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.RuleEntityDao
{
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

	com.soffid.iam.iga.model.RuleAssignedRoleEntityDao ruleAssignedRoleEntityDao;

	/**
	 * Sets reference to <code>ruleAssignedRoleEntityDao</code>.
	 */
	public void setRuleAssignedRoleEntityDao (com.soffid.iam.iga.model.RuleAssignedRoleEntityDao ruleAssignedRoleEntityDao) {
		this.ruleAssignedRoleEntityDao = ruleAssignedRoleEntityDao;
	}

	/**
	 * Gets reference to <code>ruleAssignedRoleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RuleAssignedRoleEntityDao getRuleAssignedRoleEntityDao () {
		return ruleAssignedRoleEntityDao;
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
	 * Operation findByDescription
	 * @param description
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> findByDescription(
	    java.lang.String description)
	
	{
		return findByDescription((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, description);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> findByDescription(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String description)
	
	{
		return findByDescription("select rule \nfrom com.soffid.iam.iga.model.RuleEntity as rule\nwhere rule.tenant.id=:tenantId and (rule.description like :description or :description is null or rule.name like :description)",
			criteria, description);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> findByDescription(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String description)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("description", description, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RuleEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByRoleId
	 * @param roleId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> findByRoleId(
	    java.lang.Long roleId)
	
	{
		return findByRoleId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, roleId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> findByRoleId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long roleId)
	
	{
		return findByRoleId("select rule from com.soffid.iam.iga.model.RuleEntity as rule join rule.roles as roles join roles.role as role where rule.tenant.id=:tenantId and role.id=:roleId",
			criteria, roleId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> findByRoleId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long roleId)
	
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
			return (java.util.List<com.soffid.iam.iga.model.RuleEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.Rule} object 
	 */
	public void toRule(com.soffid.iam.iga.model.RuleEntity source, com.soffid.iam.iga.api.Rule target) {
		// Attributes for Rule
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setBshExpression(source.getBshExpression());
		target.setBshRoles(source.getBshRoles());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Rule} object 
	 */
	public com.soffid.iam.iga.api.Rule toRule(com.soffid.iam.iga.model.RuleEntity entity) {
		final com.soffid.iam.iga.api.Rule target = new com.soffid.iam.iga.api.Rule();
		this.toRule(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Rule} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.Rule> toRuleList (java.util.Collection<com.soffid.iam.iga.model.RuleEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.Rule> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.Rule>();
			for (final com.soffid.iam.iga.model.RuleEntity instance: instances)
			{
				list.add( toRule(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.Rule} object 
	 */
	public void ruleToEntity (com.soffid.iam.iga.api.Rule source, com.soffid.iam.iga.model.RuleEntity target, boolean copyIfNull) {
		// Attributes for RuleEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getBshExpression() != null)
		{
			target.setBshExpression(source.getBshExpression());
		}
		if (copyIfNull || source.getBshRoles() != null)
		{
			target.setBshRoles(source.getBshRoles());
		}
		// Missing attribute tenant on entity
		// Missing attribute roles on entity
		// Missing attribute generated on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Rule} object 
	 */
	public com.soffid.iam.iga.model.RuleEntity ruleToEntity (com.soffid.iam.iga.api.Rule instance) {
		com.soffid.iam.iga.model.RuleEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newRuleEntity();
		ruleToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Rule} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity>  ruleToEntityList (java.util.Collection<com.soffid.iam.iga.api.Rule> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.RuleEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.RuleEntity>();
		for (com.soffid.iam.iga.api.Rule instance: instances)
		{
			list.add (ruleToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RuleEntity} .
	 */
	public com.soffid.iam.iga.model.RuleEntity newRuleEntity()
	{
		return new com.soffid.iam.iga.model.RuleEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RuleEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.RuleEntity result = (com.soffid.iam.iga.model.RuleEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.RuleEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.RuleEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.RuleEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RuleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RuleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RuleEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RuleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RuleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RuleEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RuleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RuleEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RuleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RuleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RuleEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RuleEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RuleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RuleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RuleEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RuleEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RuleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RuleEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RuleEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"RuleEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.RuleEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.RuleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.RuleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
