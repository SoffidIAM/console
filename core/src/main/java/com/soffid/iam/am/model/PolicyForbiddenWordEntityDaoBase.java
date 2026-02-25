//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity PolicyForbiddenWordEntity
 */
public abstract class PolicyForbiddenWordEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.PolicyForbiddenWordEntityDao
{
	com.soffid.iam.am.model.ForbiddenWordEntityDao forbiddenWordEntityDao;

	/**
	 * Sets reference to <code>forbiddenWordEntityDao</code>.
	 */
	public void setForbiddenWordEntityDao (com.soffid.iam.am.model.ForbiddenWordEntityDao forbiddenWordEntityDao) {
		this.forbiddenWordEntityDao = forbiddenWordEntityDao;
	}

	/**
	 * Gets reference to <code>forbiddenWordEntityDao</code>.
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntityDao getForbiddenWordEntityDao () {
		return forbiddenWordEntityDao;
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


	/**
	 * Operation findByPasswordPolicy
	 * @param passwordPolicy
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity> findByPasswordPolicy(
	    com.soffid.iam.am.api.PasswordPolicy passwordPolicy)
	
	{
		return findByPasswordPolicy((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, passwordPolicy);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity> findByPasswordPolicy(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.am.api.PasswordPolicy passwordPolicy)
	
	{
		return findByPasswordPolicy("from com.soffid.iam.am.model.PolicyForbiddenWordEntity where passwordPolicy=:passwordPolicy",
			criteria, passwordPolicy);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity> findByPasswordPolicy(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.am.api.PasswordPolicy passwordPolicy)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("passwordPolicy", passwordPolicy);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.PasswordPolicyForbbidenWord} object 
	 */
	public void toPasswordPolicyForbbidenWord(com.soffid.iam.am.model.PolicyForbiddenWordEntity source, com.soffid.iam.am.api.PasswordPolicyForbbidenWord target) {
		// Attributes for PasswordPolicyForbbidenWord
		target.setId(source.getId());
		// Incompatible types source.forbiddenWord and target.forbiddenWord
		// Missing attribute passwordDomainPolicy on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordPolicyForbbidenWord} object 
	 */
	public com.soffid.iam.am.api.PasswordPolicyForbbidenWord toPasswordPolicyForbbidenWord(com.soffid.iam.am.model.PolicyForbiddenWordEntity entity) {
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord target = new com.soffid.iam.am.api.PasswordPolicyForbbidenWord();
		this.toPasswordPolicyForbbidenWord(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordPolicyForbbidenWord} list 
	 */
	public java.util.List<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> toPasswordPolicyForbbidenWordList (java.util.Collection<com.soffid.iam.am.model.PolicyForbiddenWordEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> list =
				new java.util.LinkedList<com.soffid.iam.am.api.PasswordPolicyForbbidenWord>();
			for (final com.soffid.iam.am.model.PolicyForbiddenWordEntity instance: instances)
			{
				list.add( toPasswordPolicyForbbidenWord(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.PasswordPolicyForbbidenWord} object 
	 */
	public void passwordPolicyForbbidenWordToEntity (com.soffid.iam.am.api.PasswordPolicyForbbidenWord source, com.soffid.iam.am.model.PolicyForbiddenWordEntity target, boolean copyIfNull) {
		// Attributes for PolicyForbiddenWordEntity
		if (copyIfNull || source.getForbiddenWord() != null)
		{
			// Incompatible types source.forbiddenWord and target.forbiddenWord
		}
		// Missing attribute passwordPolicy on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordPolicyForbbidenWord} object 
	 */
	public com.soffid.iam.am.model.PolicyForbiddenWordEntity passwordPolicyForbbidenWordToEntity (com.soffid.iam.am.api.PasswordPolicyForbbidenWord instance) {
		com.soffid.iam.am.model.PolicyForbiddenWordEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newPolicyForbiddenWordEntity();
		passwordPolicyForbbidenWordToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordPolicyForbbidenWord} list 
	 */
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity>  passwordPolicyForbbidenWordToEntityList (java.util.Collection<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.PolicyForbiddenWordEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.PolicyForbiddenWordEntity>();
		for (com.soffid.iam.am.api.PasswordPolicyForbbidenWord instance: instances)
		{
			list.add (passwordPolicyForbbidenWordToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} .
	 */
	public com.soffid.iam.am.model.PolicyForbiddenWordEntity newPolicyForbiddenWordEntity()
	{
		return new com.soffid.iam.am.model.PolicyForbiddenWordEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.PolicyForbiddenWordEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.PolicyForbiddenWordEntity result = (com.soffid.iam.am.model.PolicyForbiddenWordEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.PolicyForbiddenWordEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity> loadAll() {
		java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity> result = (java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.am.model.PolicyForbiddenWordEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.PolicyForbiddenWordEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PolicyForbiddenWordEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.PolicyForbiddenWordEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PolicyForbiddenWordEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.PolicyForbiddenWordEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PolicyForbiddenWordEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.PolicyForbiddenWordEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PolicyForbiddenWordEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.PolicyForbiddenWordEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.PolicyForbiddenWordEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PolicyForbiddenWordEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.PolicyForbiddenWordEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.PolicyForbiddenWordEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PolicyForbiddenWordEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.PolicyForbiddenWordEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"PolicyForbiddenWordEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.PolicyForbiddenWordEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
