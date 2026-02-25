//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity AccountPasswordEntity
 */
public abstract class AccountPasswordEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.AccountPasswordEntityDao
{
	com.soffid.iam.base.model.AccountEntityDao accountEntityDao;

	/**
	 * Sets reference to <code>accountEntityDao</code>.
	 */
	public void setAccountEntityDao (com.soffid.iam.base.model.AccountEntityDao accountEntityDao) {
		this.accountEntityDao = accountEntityDao;
	}

	/**
	 * Gets reference to <code>accountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountEntityDao getAccountEntityDao () {
		return accountEntityDao;
	}


	/**
	 * Operation findLastByAccount
	 * @param accountId
	 * @return
	**/
	public com.soffid.iam.am.model.AccountPasswordEntity findLastByAccount(
	    long accountId)
	
	{
		return findLastByAccount((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, accountId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.AccountPasswordEntity findLastByAccount(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long accountId)
	
	{
		return findLastByAccount("select pwd\nfrom com.soffid.iam.am.model.AccountPasswordEntity as pwd\nwhere pwd.account.id = :accountId\nand pwd.order=0",
			criteria, accountId);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.AccountPasswordEntity findLastByAccount(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long accountId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("accountId", accountId, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.AccountPasswordEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.AccountPasswordEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.AccountPasswordEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.PasswordStatus} object 
	 */
	public void toPasswordStatus(com.soffid.iam.am.model.AccountPasswordEntity source, com.soffid.iam.am.api.PasswordStatus target) {
		// Attributes for PasswordStatus
		// Missing attribute user on entity
		// Missing attribute PasswordDomain on entity
		// Missing attribute dispatcher on entity
		// Missing attribute accountName on entity
		if (source.getDate() == null) {
			target.setDate(null);
		} else {
			target.setDate(java.util.Calendar.getInstance());
			target.getDate().setTime(source.getDate());
		}
		if (source.getExpirationDate() == null) {
			target.setExpirationDate(null);
		} else {
			target.setExpirationDate(java.util.Calendar.getInstance());
			target.getExpirationDate().setTime(source.getExpirationDate());
		}
		// Missing attribute expired on entity
		// Missing attribute passwordPolicyType on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordStatus} object 
	 */
	public com.soffid.iam.am.api.PasswordStatus toPasswordStatus(com.soffid.iam.am.model.AccountPasswordEntity entity) {
		final com.soffid.iam.am.api.PasswordStatus target = new com.soffid.iam.am.api.PasswordStatus();
		this.toPasswordStatus(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordStatus} list 
	 */
	public java.util.List<com.soffid.iam.am.api.PasswordStatus> toPasswordStatusList (java.util.Collection<com.soffid.iam.am.model.AccountPasswordEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.PasswordStatus> list =
				new java.util.LinkedList<com.soffid.iam.am.api.PasswordStatus>();
			for (final com.soffid.iam.am.model.AccountPasswordEntity instance: instances)
			{
				list.add( toPasswordStatus(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.PasswordStatus} object 
	 */
	public void passwordStatusToEntity (com.soffid.iam.am.api.PasswordStatus source, com.soffid.iam.am.model.AccountPasswordEntity target, boolean copyIfNull) {
		// Attributes for AccountPasswordEntity
		// Missing attribute password on entity
		// Missing attribute password2 on entity
		// Missing attribute order on entity
		if (copyIfNull || source.getDate() != null)
		{
			if (source.getDate() == null) {
				target.setDate(null);
			} else {
				target.setDate(source.getDate().getTime());
			}
		}
		if (copyIfNull || source.getExpirationDate() != null)
		{
			if (source.getExpirationDate() == null) {
				target.setExpirationDate(null);
			} else {
				target.setExpirationDate(source.getExpirationDate().getTime());
			}
		}
		// Missing attribute active on entity
		// Missing attribute account on entity
		// Missing attribute fails on entity
		// Missing attribute unlockDate on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordStatus} list 
	 */
	public java.util.List<com.soffid.iam.am.model.AccountPasswordEntity>  passwordStatusToEntityList (java.util.Collection<com.soffid.iam.am.api.PasswordStatus> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.AccountPasswordEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.AccountPasswordEntity>();
		for (com.soffid.iam.am.api.PasswordStatus instance: instances)
		{
			list.add (passwordStatusToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.AccountPasswordEntity} .
	 */
	public com.soffid.iam.am.model.AccountPasswordEntity newAccountPasswordEntity()
	{
		return new com.soffid.iam.am.model.AccountPasswordEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.AccountPasswordEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.AccountPasswordEntity result = (com.soffid.iam.am.model.AccountPasswordEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.AccountPasswordEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.AccountPasswordEntity> loadAll() {
		java.util.List<com.soffid.iam.am.model.AccountPasswordEntity> result = (java.util.List<com.soffid.iam.am.model.AccountPasswordEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.am.model.AccountPasswordEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.AccountPasswordEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.AccountPasswordEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountPasswordEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.AccountPasswordEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.AccountPasswordEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountPasswordEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.AccountPasswordEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountPasswordEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.AccountPasswordEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.AccountPasswordEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountPasswordEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.AccountPasswordEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.AccountPasswordEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.AccountPasswordEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountPasswordEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.AccountPasswordEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.AccountPasswordEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountPasswordEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.AccountPasswordEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AccountPasswordEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.AccountPasswordEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.AccountPasswordEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.AccountPasswordEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.AccountPasswordEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.AccountPasswordEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
