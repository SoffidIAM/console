//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity PamActionEntity
 */
public abstract class PamActionEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.PamActionEntityDao
{
	com.soffid.iam.pam.model.PamPolicyEntityDao pamPolicyEntityDao;

	/**
	 * Sets reference to <code>pamPolicyEntityDao</code>.
	 */
	public void setPamPolicyEntityDao (com.soffid.iam.pam.model.PamPolicyEntityDao pamPolicyEntityDao) {
		this.pamPolicyEntityDao = pamPolicyEntityDao;
	}

	/**
	 * Gets reference to <code>pamPolicyEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.PamPolicyEntityDao getPamPolicyEntityDao () {
		return pamPolicyEntityDao;
	}

	com.soffid.iam.pam.model.PamRuleEntityDao pamRuleEntityDao;

	/**
	 * Sets reference to <code>pamRuleEntityDao</code>.
	 */
	public void setPamRuleEntityDao (com.soffid.iam.pam.model.PamRuleEntityDao pamRuleEntityDao) {
		this.pamRuleEntityDao = pamRuleEntityDao;
	}

	/**
	 * Gets reference to <code>pamRuleEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.PamRuleEntityDao getPamRuleEntityDao () {
		return pamRuleEntityDao;
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
	 * @see com.soffid.iam.pam.model.PamActionEntity#	 * @see com.soffid.iam.pam.model.PamActionEntity#com.soffid.iam.pam.api.PamAction create(com.soffid.iam.pam.api.PamAction action)
	 */
	public com.soffid.iam.pam.api.PamAction create(
		com.soffid.iam.pam.api.PamAction action)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (action == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamAction com.soffid.iam.pam.model.PamActionEntity.create(com.soffid.iam.pam.api.PamAction action) - action cannot be null");
		}
		if (action.getPolicyName() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamAction com.soffid.iam.pam.model.PamActionEntity.create(com.soffid.iam.pam.api.PamAction action) - action.policyName cannot be null");
		}
		if (action.getRuleName() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamAction com.soffid.iam.pam.model.PamActionEntity.create(com.soffid.iam.pam.api.PamAction action) - action.ruleName cannot be null");
		}
		try
		{
			return handleCreate(action);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.model.PamActionEntity.class).
				warn ("Error on PamActionEntity.create", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on PamActionEntity.create: "+th.toString(), th);
		}
	}

	protected abstract com.soffid.iam.pam.api.PamAction handleCreate(com.soffid.iam.pam.api.PamAction action) throws Exception;

	/**
	 * @see com.soffid.iam.pam.model.PamActionEntity#	 * @see com.soffid.iam.pam.model.PamActionEntity#com.soffid.iam.pam.api.PamAction update(com.soffid.iam.pam.api.PamAction action)
	 */
	public com.soffid.iam.pam.api.PamAction update(
		com.soffid.iam.pam.api.PamAction action)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (action == null) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamAction com.soffid.iam.pam.model.PamActionEntity.update(com.soffid.iam.pam.api.PamAction action) - action cannot be null");
		}
		if (action.getPolicyName() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamAction com.soffid.iam.pam.model.PamActionEntity.update(com.soffid.iam.pam.api.PamAction action) - action.policyName cannot be null");
		}
		if (action.getRuleName() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.pam.api.PamAction com.soffid.iam.pam.model.PamActionEntity.update(com.soffid.iam.pam.api.PamAction action) - action.ruleName cannot be null");
		}
		try
		{
			return handleUpdate(action);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.model.PamActionEntity.class).
				warn ("Error on PamActionEntity.update", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on PamActionEntity.update: "+th.toString(), th);
		}
	}

	protected abstract com.soffid.iam.pam.api.PamAction handleUpdate(com.soffid.iam.pam.api.PamAction action) throws Exception;

	/**
	 * Operation findByPolicy
	 * @param policy
	 * @return
	**/
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> findByPolicy(
	    java.lang.String policy)
	
	{
		return findByPolicy((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, policy);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> findByPolicy(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String policy)
	
	{
		return findByPolicy("select a from com.soffid.iam.pam.model.PamActionEntity as a where a.policy.name=:policy",
			criteria, policy);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> findByPolicy(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String policy)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("policy", policy, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.pam.model.PamActionEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByPolicyAndRule
	 * @param policy
	 * @param rule
	 * @return
	**/
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> findByPolicyAndRule(
	    java.lang.String policy, 
	    java.lang.String rule)
	
	{
		return findByPolicyAndRule((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, policy, rule);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> findByPolicyAndRule(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String policy, java.lang.String rule)
	
	{
		return findByPolicyAndRule("select a from com.soffid.iam.pam.model.PamActionEntity as a where a.policy.name=:policy and a.rule.name=:rule",
			criteria, policy, rule);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> findByPolicyAndRule(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String policy, java.lang.String rule)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("policy", policy, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("rule", rule, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.pam.model.PamActionEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.pam.model.PamActionEntity#	 * @see com.soffid.iam.pam.model.PamActionEntity#java.util.List<com.soffid.iam.pam.api.PamAction> getActionsByPolicy(java.lang.String policy)
	 */
	public java.util.List<com.soffid.iam.pam.api.PamAction> getActionsByPolicy(
		java.lang.String policy)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (policy == null || policy.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List<com.soffid.iam.pam.api.PamAction> com.soffid.iam.pam.model.PamActionEntity.getActionsByPolicy(java.lang.String policy) - policy cannot be null");
		}
		try
		{
			return handleGetActionsByPolicy(policy);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.model.PamActionEntity.class).
				warn ("Error on PamActionEntity.getActionsByPolicy", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on PamActionEntity.getActionsByPolicy: "+th.toString(), th);
		}
	}

	protected abstract java.util.List<com.soffid.iam.pam.api.PamAction> handleGetActionsByPolicy(java.lang.String policy) throws Exception;

	/**
	 * @see com.soffid.iam.pam.model.PamActionEntity#	 * @see com.soffid.iam.pam.model.PamActionEntity#void remove(com.soffid.iam.pam.api.PamAction action)
	 */
	public void remove(
		com.soffid.iam.pam.api.PamAction action)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (action == null) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.model.PamActionEntity.remove(com.soffid.iam.pam.api.PamAction action) - action cannot be null");
		}
		if (action.getPolicyName() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.model.PamActionEntity.remove(com.soffid.iam.pam.api.PamAction action) - action.policyName cannot be null");
		}
		if (action.getRuleName() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.pam.model.PamActionEntity.remove(com.soffid.iam.pam.api.PamAction action) - action.ruleName cannot be null");
		}
		try
		{
			handleRemove(action);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.pam.model.PamActionEntity.class).
				warn ("Error on PamActionEntity.remove", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on PamActionEntity.remove: "+th.toString(), th);
		}
	}

	protected abstract void handleRemove(com.soffid.iam.pam.api.PamAction action) throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.PamAction} object 
	 */
	public void toPamAction(com.soffid.iam.pam.model.PamActionEntity source, com.soffid.iam.pam.api.PamAction target) {
		// Attributes for PamAction
		// Missing attribute policyName on entity
		// Missing attribute ruleName on entity
		// Missing attribute actions on entity
		target.setAuthor(source.getAuthor());
		target.setDate(source.getDate());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.PamAction} object 
	 */
	public com.soffid.iam.pam.api.PamAction toPamAction(com.soffid.iam.pam.model.PamActionEntity entity) {
		final com.soffid.iam.pam.api.PamAction target = new com.soffid.iam.pam.api.PamAction();
		this.toPamAction(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.PamAction} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.PamAction> toPamActionList (java.util.Collection<com.soffid.iam.pam.model.PamActionEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.pam.api.PamAction> list =
				new java.util.LinkedList<com.soffid.iam.pam.api.PamAction>();
			for (final com.soffid.iam.pam.model.PamActionEntity instance: instances)
			{
				list.add( toPamAction(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.PamAction} object 
	 */
	public void pamActionToEntity (com.soffid.iam.pam.api.PamAction source, com.soffid.iam.pam.model.PamActionEntity target, boolean copyIfNull) {
		// Attributes for PamActionEntity
		// Missing attribute policy on entity
		// Missing attribute rule on entity
		if (copyIfNull || source.getAuthor() != null)
		{
			target.setAuthor(source.getAuthor());
		}
		if (copyIfNull || source.getDate() != null)
		{
			target.setDate(source.getDate());
		}
		// Missing attribute type on entity
		// Missing attribute tenant on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.PamAction} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity>  pamActionToEntityList (java.util.Collection<com.soffid.iam.pam.api.PamAction> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.pam.model.PamActionEntity> list =
			new java.util.LinkedList<com.soffid.iam.pam.model.PamActionEntity>();
		for (com.soffid.iam.pam.api.PamAction instance: instances)
		{
			list.add (pamActionToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.PamActionEntity} .
	 */
	public com.soffid.iam.pam.model.PamActionEntity newPamActionEntity()
	{
		return new com.soffid.iam.pam.model.PamActionEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.PamActionEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.PamActionEntity result = (com.soffid.iam.pam.model.PamActionEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.PamActionEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.pam.model.PamActionEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.pam.model.PamActionEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.PamActionEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.PamActionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PamActionEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.PamActionEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.PamActionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PamActionEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.PamActionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PamActionEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.PamActionEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.PamActionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PamActionEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.PamActionEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.PamActionEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.PamActionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PamActionEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.PamActionEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.PamActionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PamActionEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.PamActionEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"PamActionEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.PamActionEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.PamActionEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.PamActionEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
