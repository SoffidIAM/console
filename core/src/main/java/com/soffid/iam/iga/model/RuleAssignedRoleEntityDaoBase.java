//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity RuleAssignedRoleEntity
 */
public abstract class RuleAssignedRoleEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.RuleAssignedRoleEntityDao
{
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

	com.soffid.iam.iga.model.RuleEntityDao ruleEntityDao;

	/**
	 * Sets reference to <code>ruleEntityDao</code>.
	 */
	public void setRuleEntityDao (com.soffid.iam.iga.model.RuleEntityDao ruleEntityDao) {
		this.ruleEntityDao = ruleEntityDao;
	}

	/**
	 * Gets reference to <code>ruleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RuleEntityDao getRuleEntityDao () {
		return ruleEntityDao;
	}


	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.RuleAssignedRole} object 
	 */
	public void toRuleAssignedRole(com.soffid.iam.iga.model.RuleAssignedRoleEntity source, com.soffid.iam.iga.api.RuleAssignedRole target) {
		// Attributes for RuleAssignedRole
		target.setId(source.getId());
		target.setBshDomainValueExpression(source.getBshDomainValueExpression());
		target.setDomainValue(source.getDomainValue());
		// Missing attribute roleId on entity
		// Missing attribute ruleId on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RuleAssignedRole} object 
	 */
	public com.soffid.iam.iga.api.RuleAssignedRole toRuleAssignedRole(com.soffid.iam.iga.model.RuleAssignedRoleEntity entity) {
		final com.soffid.iam.iga.api.RuleAssignedRole target = new com.soffid.iam.iga.api.RuleAssignedRole();
		this.toRuleAssignedRole(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RuleAssignedRole} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.RuleAssignedRole> toRuleAssignedRoleList (java.util.Collection<com.soffid.iam.iga.model.RuleAssignedRoleEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.RuleAssignedRole> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.RuleAssignedRole>();
			for (final com.soffid.iam.iga.model.RuleAssignedRoleEntity instance: instances)
			{
				list.add( toRuleAssignedRole(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.RuleAssignedRole} object 
	 */
	public void ruleAssignedRoleToEntity (com.soffid.iam.iga.api.RuleAssignedRole source, com.soffid.iam.iga.model.RuleAssignedRoleEntity target, boolean copyIfNull) {
		// Attributes for RuleAssignedRoleEntity
		if (copyIfNull || source.getBshDomainValueExpression() != null)
		{
			target.setBshDomainValueExpression(source.getBshDomainValueExpression());
		}
		// Missing attribute rule on entity
		// Missing attribute role on entity
		if (copyIfNull || source.getDomainValue() != null)
		{
			target.setDomainValue(source.getDomainValue());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RuleAssignedRole} object 
	 */
	public com.soffid.iam.iga.model.RuleAssignedRoleEntity ruleAssignedRoleToEntity (com.soffid.iam.iga.api.RuleAssignedRole instance) {
		com.soffid.iam.iga.model.RuleAssignedRoleEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newRuleAssignedRoleEntity();
		ruleAssignedRoleToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RuleAssignedRole} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleAssignedRoleEntity>  ruleAssignedRoleToEntityList (java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.RuleAssignedRoleEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.RuleAssignedRoleEntity>();
		for (com.soffid.iam.iga.api.RuleAssignedRole instance: instances)
		{
			list.add (ruleAssignedRoleToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} .
	 */
	public com.soffid.iam.iga.model.RuleAssignedRoleEntity newRuleAssignedRoleEntity()
	{
		return new com.soffid.iam.iga.model.RuleAssignedRoleEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RuleAssignedRoleEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.RuleAssignedRoleEntity result = (com.soffid.iam.iga.model.RuleAssignedRoleEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.RuleAssignedRoleEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleAssignedRoleEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.RuleAssignedRoleEntity> result = (java.util.List<com.soffid.iam.iga.model.RuleAssignedRoleEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.RuleAssignedRoleEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RuleAssignedRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RuleAssignedRoleEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RuleAssignedRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RuleAssignedRoleEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RuleAssignedRoleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RuleAssignedRoleEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RuleAssignedRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RuleAssignedRoleEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RuleAssignedRoleEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RuleAssignedRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RuleAssignedRoleEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RuleAssignedRoleEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RuleAssignedRoleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RuleAssignedRoleEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RuleAssignedRoleEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"RuleAssignedRoleEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.RuleAssignedRoleEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleAssignedRoleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.RuleAssignedRoleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleAssignedRoleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.RuleAssignedRoleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
