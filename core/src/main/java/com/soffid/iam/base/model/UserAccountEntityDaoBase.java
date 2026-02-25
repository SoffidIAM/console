//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity UserAccountEntity
 */
public abstract class UserAccountEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.UserAccountEntityDao
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

	com.soffid.iam.sync.model.TaskEntityDao taskEntityDao;

	/**
	 * Sets reference to <code>taskEntityDao</code>.
	 */
	public void setTaskEntityDao (com.soffid.iam.sync.model.TaskEntityDao taskEntityDao) {
		this.taskEntityDao = taskEntityDao;
	}

	/**
	 * Gets reference to <code>taskEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.TaskEntityDao getTaskEntityDao () {
		return taskEntityDao;
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
	 * Operation findByAccountSystemAndName
	 * @param account
	 * @param systemName
	 * @param user
	 * @return
	**/
	public com.soffid.iam.base.model.UserAccountEntity findByAccountSystemAndName(
	    java.lang.String account, 
	    java.lang.String systemName, 
	    java.lang.String user)
	
	{
		return findByAccountSystemAndName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, account, systemName, user);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.UserAccountEntity findByAccountSystemAndName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String account, java.lang.String systemName, java.lang.String user)
	
	{
		return findByAccountSystemAndName("select uae\nfrom  com.soffid.iam.base.model.UserAccountEntity as uae\nwhere uae.user.userName=:user and uae.account.name=:account and uae.account.system.name=:systemName and uae.account.system.tenant.id = :tenantId and uae.account.deleted is null",
			criteria, account, systemName, user);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.UserAccountEntity findByAccountSystemAndName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String account, java.lang.String systemName, java.lang.String user)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("account", account, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("systemName", systemName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.UserAccountEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.UserAccountEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.UserAccountEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUserAndDispatcher
	 * @param user
	 * @param dispatcher
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity> findByUserAndDispatcher(
	    java.lang.String user, 
	    java.lang.String dispatcher)
	
	{
		return findByUserAndDispatcher((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, user, dispatcher);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity> findByUserAndDispatcher(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.lang.String dispatcher)
	
	{
		return findByUserAndDispatcher("select uae\nfrom  com.soffid.iam.base.model.UserAccountEntity uae\nwhere uae.user.userName=:user and uae.account.system.name=:dispatcher and uae.account.type = 'U' and uae.account.system.tenant.id = :tenantId and uae.account.deletedOn is null",
			criteria, user, dispatcher);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity> findByUserAndDispatcher(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.lang.String dispatcher)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("dispatcher", dispatcher, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.UserAccountEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.base.model.UserAccountEntity#	 * @see com.soffid.iam.base.model.UserAccountEntity#void propagateChanges(com.soffid.iam.base.model.UserAccountEntity account)
	 */
	public void propagateChanges(
		com.soffid.iam.base.model.UserAccountEntity account)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (account == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.UserAccountEntity.propagateChanges(com.soffid.iam.base.model.UserAccountEntity account) - account cannot be null");
		}
		try
		{
			handlePropagateChanges(account);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.UserAccountEntity.class).
				warn ("Error on UserAccountEntity.propagateChanges", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on UserAccountEntity.propagateChanges: "+th.toString(), th);
		}
	}

	protected abstract void handlePropagateChanges(com.soffid.iam.base.model.UserAccountEntity account) throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.UserAccount} object 
	 */
	public void toUserAccount(com.soffid.iam.base.model.UserAccountEntity source, com.soffid.iam.base.api.UserAccount target) {
		// Attributes for UserAccount
		// Incompatible types source.user and target.user
		target.setCreatedOn(source.getCreatedOn());
		// Attributes for Account
		target.setId(source.getId());
		// Missing attribute system on entity
		// Missing attribute name on entity
		// Missing attribute key on entity
		// Missing attribute oldName on entity
		// Missing attribute loginName on entity
		// Missing attribute description on entity
		// Missing attribute type on entity
		// Missing attribute disabled on entity
		// Missing attribute status on entity
		// Missing attribute credentialType on entity
		// Missing attribute passwordPolicy on entity
		// Missing attribute ownerGroups on entity
		// Missing attribute ownerUsers on entity
		// Missing attribute ownerRoles on entity
		// Missing attribute managerGroups on entity
		// Missing attribute managerUsers on entity
		// Missing attribute managerRoles on entity
		// Missing attribute grantedGroups on entity
		// Missing attribute grantedUsers on entity
		// Missing attribute grantedRoles on entity
		// Missing attribute accessLevel on entity
		// Missing attribute serverType on entity
		// Missing attribute serverName on entity
		// Missing attribute sshPublicKey on entity
		// Missing attribute vaultFolderId on entity
		// Missing attribute vaultFolder on entity
		// Missing attribute inheritNewPermissions on entity
		// Missing attribute loginUrl on entity
		// Missing attribute launchType on entity
		// Missing attribute jumpServerGroup on entity
		// Missing attribute externalId on entity
		// Missing attribute lastLogin on entity
		// Missing attribute lastUpdated on entity
		// Missing attribute lastPasswordSet on entity
		// Missing attribute passwordExpiration on entity
		// Missing attribute lockedBy on entity
		// Missing attribute passwordStatus on entity
		// Missing attribute created on entity
		// Missing attribute lastChange on entity
		// Missing attribute attributes on entity
		// Missing attribute hasSnapshot on entity
		// Missing attribute createdBy on entity
		// Missing attribute updatedBy on entity
		// Missing attribute deletedOn on entity
		// Missing attribute deletedBy on entity
		// Missing attribute deleted on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserAccount} object 
	 */
	public com.soffid.iam.base.api.UserAccount toUserAccount(com.soffid.iam.base.model.UserAccountEntity entity) {
		final com.soffid.iam.base.api.UserAccount target = new com.soffid.iam.base.api.UserAccount();
		this.toUserAccount(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserAccount} list 
	 */
	public java.util.List<com.soffid.iam.base.api.UserAccount> toUserAccountList (java.util.Collection<com.soffid.iam.base.model.UserAccountEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.UserAccount> list =
				new java.util.LinkedList<com.soffid.iam.base.api.UserAccount>();
			for (final com.soffid.iam.base.model.UserAccountEntity instance: instances)
			{
				list.add( toUserAccount(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.UserAccount} object 
	 */
	public void userAccountToEntity (com.soffid.iam.base.api.UserAccount source, com.soffid.iam.base.model.UserAccountEntity target, boolean copyIfNull) {
		// Attributes for UserAccountEntity
		if (copyIfNull || source.getUser() != null)
		{
			// Incompatible types source.user and target.user
		}
		// Missing attribute account on entity
		// Missing attribute untilDate on entity
		// Missing attribute workflowId on entity
		// Missing attribute entryPoint on entity
		// Missing attribute approved on entity
		if (copyIfNull || source.getCreatedOn() != null)
		{
			target.setCreatedOn(source.getCreatedOn());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserAccount} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity>  userAccountToEntityList (java.util.Collection<com.soffid.iam.base.api.UserAccount> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.UserAccountEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.UserAccountEntity>();
		for (com.soffid.iam.base.api.UserAccount instance: instances)
		{
			list.add (userAccountToEntity(instance));
		}
		return list;
	}

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.UserAccountHistory} object 
	 */
	public void toUserAccountHistory(com.soffid.iam.base.model.UserAccountEntity source, com.soffid.iam.base.api.UserAccountHistory target) {
		// Attributes for UserAccountHistory
		target.setId(source.getId());
		// Incompatible types source.user and target.user
		// Incompatible types source.account and target.account
		// Missing attribute system on entity
		target.setUntilDate(source.getUntilDate());
		target.setWorkflowId(source.getWorkflowId());
		target.setApproved(source.getApproved());
		target.setEntryPoint(source.getEntryPoint());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserAccountHistory} object 
	 */
	public com.soffid.iam.base.api.UserAccountHistory toUserAccountHistory(com.soffid.iam.base.model.UserAccountEntity entity) {
		final com.soffid.iam.base.api.UserAccountHistory target = new com.soffid.iam.base.api.UserAccountHistory();
		this.toUserAccountHistory(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserAccountHistory} list 
	 */
	public java.util.List<com.soffid.iam.base.api.UserAccountHistory> toUserAccountHistoryList (java.util.Collection<com.soffid.iam.base.model.UserAccountEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.UserAccountHistory> list =
				new java.util.LinkedList<com.soffid.iam.base.api.UserAccountHistory>();
			for (final com.soffid.iam.base.model.UserAccountEntity instance: instances)
			{
				list.add( toUserAccountHistory(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.UserAccountHistory} object 
	 */
	public void userAccountHistoryToEntity (com.soffid.iam.base.api.UserAccountHistory source, com.soffid.iam.base.model.UserAccountEntity target, boolean copyIfNull) {
		// Attributes for UserAccountEntity
		if (copyIfNull || source.getUser() != null)
		{
			// Incompatible types source.user and target.user
		}
		if (copyIfNull || source.getAccount() != null)
		{
			// Incompatible types source.account and target.account
		}
		if (copyIfNull || source.getUntilDate() != null)
		{
			target.setUntilDate(source.getUntilDate());
		}
		if (copyIfNull || source.getWorkflowId() != null)
		{
			target.setWorkflowId(source.getWorkflowId());
		}
		if (copyIfNull || source.getEntryPoint() != null)
		{
			target.setEntryPoint(source.getEntryPoint());
		}
		if (copyIfNull || source.getApproved() != null)
		{
			target.setApproved(source.getApproved());
		}
		// Missing attribute createdOn on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserAccountHistory} object 
	 */
	public com.soffid.iam.base.model.UserAccountEntity userAccountHistoryToEntity (com.soffid.iam.base.api.UserAccountHistory instance) {
		com.soffid.iam.base.model.UserAccountEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newUserAccountEntity();
		userAccountHistoryToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserAccountHistory} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity>  userAccountHistoryToEntityList (java.util.Collection<com.soffid.iam.base.api.UserAccountHistory> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.UserAccountEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.UserAccountEntity>();
		for (com.soffid.iam.base.api.UserAccountHistory instance: instances)
		{
			list.add (userAccountHistoryToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.UserAccountEntity} .
	 */
	public com.soffid.iam.base.model.UserAccountEntity newUserAccountEntity()
	{
		return new com.soffid.iam.base.model.UserAccountEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.UserAccountEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.UserAccountEntity result = (com.soffid.iam.base.model.UserAccountEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.UserAccountEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.UserAccountEntity> result = (java.util.List<com.soffid.iam.base.model.UserAccountEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.UserAccountEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.UserAccountEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.UserAccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserAccountEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.UserAccountEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.UserAccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserAccountEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.UserAccountEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserAccountEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.UserAccountEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.UserAccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserAccountEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.UserAccountEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.UserAccountEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.UserAccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserAccountEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.UserAccountEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.UserAccountEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserAccountEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.UserAccountEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"UserAccountEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.UserAccountEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.UserAccountEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.UserAccountEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
