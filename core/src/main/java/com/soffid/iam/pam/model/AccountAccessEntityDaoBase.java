//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity AccountAccessEntity
 * Contains the access control list for an account
 */
public abstract class AccountAccessEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.AccountAccessEntityDao
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
	 * Creates an instance of {@link com.soffid.iam.pam.model.AccountAccessEntity} .
	 */
	public com.soffid.iam.pam.model.AccountAccessEntity newAccountAccessEntity()
	{
		return new com.soffid.iam.pam.model.AccountAccessEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.AccountAccessEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.AccountAccessEntity result = (com.soffid.iam.pam.model.AccountAccessEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.AccountAccessEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.AccountAccessEntity> loadAll() {
		java.util.List<com.soffid.iam.pam.model.AccountAccessEntity> result = (java.util.List<com.soffid.iam.pam.model.AccountAccessEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.pam.model.AccountAccessEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.AccountAccessEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.AccountAccessEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountAccessEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.AccountAccessEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.AccountAccessEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountAccessEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.AccountAccessEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountAccessEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.AccountAccessEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.AccountAccessEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountAccessEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.AccountAccessEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.AccountAccessEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.AccountAccessEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountAccessEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.AccountAccessEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.AccountAccessEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountAccessEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.AccountAccessEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AccountAccessEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.AccountAccessEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.AccountAccessEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.AccountAccessEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.AccountAccessEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.AccountAccessEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.AccountAccessEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
