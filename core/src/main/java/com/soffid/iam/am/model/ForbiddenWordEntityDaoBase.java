//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity ForbiddenWordEntity
 */
public abstract class ForbiddenWordEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.ForbiddenWordEntityDao
{
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

	com.soffid.iam.am.model.PolicyForbiddenWordEntityDao policyForbiddenWordEntityDao;

	/**
	 * Sets reference to <code>policyForbiddenWordEntityDao</code>.
	 */
	public void setPolicyForbiddenWordEntityDao (com.soffid.iam.am.model.PolicyForbiddenWordEntityDao policyForbiddenWordEntityDao) {
		this.policyForbiddenWordEntityDao = policyForbiddenWordEntityDao;
	}

	/**
	 * Gets reference to <code>policyForbiddenWordEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PolicyForbiddenWordEntityDao getPolicyForbiddenWordEntityDao () {
		return policyForbiddenWordEntityDao;
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
	 * Operation findByName
	 * @param forbiddenWord
	 * @return
	**/
	public com.soffid.iam.am.model.ForbiddenWordEntity findByName(
	    java.lang.String forbiddenWord)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, forbiddenWord);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String forbiddenWord)
	
	{
		return findByName("from com.soffid.iam.am.model.ForbiddenWordEntity where tenant.id=:tenantId and forbiddenWord=:forbiddenWord",
			criteria, forbiddenWord);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String forbiddenWord)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("forbiddenWord", forbiddenWord, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.ForbiddenWordEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.ForbiddenWordEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.ForbiddenWordEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.ForbiddenWord} object 
	 */
	public void toForbiddenWord(com.soffid.iam.am.model.ForbiddenWordEntity source, com.soffid.iam.am.api.ForbiddenWord target) {
		// Attributes for ForbiddenWord
		target.setId(source.getId());
		target.setForbiddenWord(source.getForbiddenWord());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.ForbiddenWord} object 
	 */
	public com.soffid.iam.am.api.ForbiddenWord toForbiddenWord(com.soffid.iam.am.model.ForbiddenWordEntity entity) {
		final com.soffid.iam.am.api.ForbiddenWord target = new com.soffid.iam.am.api.ForbiddenWord();
		this.toForbiddenWord(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.ForbiddenWord} list 
	 */
	public java.util.List<com.soffid.iam.am.api.ForbiddenWord> toForbiddenWordList (java.util.Collection<com.soffid.iam.am.model.ForbiddenWordEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.ForbiddenWord> list =
				new java.util.LinkedList<com.soffid.iam.am.api.ForbiddenWord>();
			for (final com.soffid.iam.am.model.ForbiddenWordEntity instance: instances)
			{
				list.add( toForbiddenWord(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.ForbiddenWord} object 
	 */
	public void forbiddenWordToEntity (com.soffid.iam.am.api.ForbiddenWord source, com.soffid.iam.am.model.ForbiddenWordEntity target, boolean copyIfNull) {
		// Attributes for ForbiddenWordEntity
		if (copyIfNull || source.getForbiddenWord() != null)
		{
			target.setForbiddenWord(source.getForbiddenWord());
		}
		// Missing attribute policies on entity
		// Missing attribute tenant on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.ForbiddenWord} object 
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntity forbiddenWordToEntity (com.soffid.iam.am.api.ForbiddenWord instance) {
		com.soffid.iam.am.model.ForbiddenWordEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newForbiddenWordEntity();
		forbiddenWordToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.ForbiddenWord} list 
	 */
	public java.util.List<com.soffid.iam.am.model.ForbiddenWordEntity>  forbiddenWordToEntityList (java.util.Collection<com.soffid.iam.am.api.ForbiddenWord> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.ForbiddenWordEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.ForbiddenWordEntity>();
		for (com.soffid.iam.am.api.ForbiddenWord instance: instances)
		{
			list.add (forbiddenWordToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.ForbiddenWordEntity} .
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntity newForbiddenWordEntity()
	{
		return new com.soffid.iam.am.model.ForbiddenWordEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.ForbiddenWordEntity result = (com.soffid.iam.am.model.ForbiddenWordEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.ForbiddenWordEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.ForbiddenWordEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.ForbiddenWordEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.ForbiddenWordEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.ForbiddenWordEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.ForbiddenWordEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ForbiddenWordEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.ForbiddenWordEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.ForbiddenWordEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ForbiddenWordEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.ForbiddenWordEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ForbiddenWordEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.ForbiddenWordEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.ForbiddenWordEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ForbiddenWordEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.ForbiddenWordEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.ForbiddenWordEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.ForbiddenWordEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ForbiddenWordEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.ForbiddenWordEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.ForbiddenWordEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ForbiddenWordEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.ForbiddenWordEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ForbiddenWordEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.ForbiddenWordEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.ForbiddenWordEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.ForbiddenWordEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.ForbiddenWordEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.ForbiddenWordEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
