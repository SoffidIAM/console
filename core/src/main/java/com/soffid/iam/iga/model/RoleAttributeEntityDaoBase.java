//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity RoleAttributeEntity
 */
public abstract class RoleAttributeEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.RoleAttributeEntityDao
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

	com.soffid.iam.base.service.AuthorizationService authorizationService;

	/**
	 * Sets reference to <code>authorizationService</code>.
	 */
	public void setAuthorizationService (com.soffid.iam.base.service.AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	/**
	 * Gets reference to <code>authorizationService</code>.
	 */
	public com.soffid.iam.base.service.AuthorizationService getAuthorizationService () {
		return authorizationService;
	}

	com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao;

	/**
	 * Sets reference to <code>metaDataEntityDao</code>.
	 */
	public void setMetaDataEntityDao (com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao) {
		this.metaDataEntityDao = metaDataEntityDao;
	}

	/**
	 * Gets reference to <code>metaDataEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MetaDataEntityDao getMetaDataEntityDao () {
		return metaDataEntityDao;
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


	/**
	 * Operation findByNameAndValue
	 * @param name
	 * @param value
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity> findByNameAndValue(
	    java.lang.String name, 
	    java.lang.String value)
	
	{
		return findByNameAndValue((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, value);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity> findByNameAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String value)
	
	{
		return findByNameAndValue("select att from com.soffid.iam.iga.model.RoleAttributeEntity as att where att.metadata.name = :name and att.value = :value ",
			criteria, name, value);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity> findByNameAndValue(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String value)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("value", value, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RoleAttributeEntity} .
	 */
	public com.soffid.iam.iga.model.RoleAttributeEntity newRoleAttributeEntity()
	{
		return new com.soffid.iam.iga.model.RoleAttributeEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RoleAttributeEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.RoleAttributeEntity result = (com.soffid.iam.iga.model.RoleAttributeEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.RoleAttributeEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity> result = (java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.RoleAttributeEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RoleAttributeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RoleAttributeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleAttributeEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RoleAttributeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RoleAttributeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleAttributeEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RoleAttributeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"RoleAttributeEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RoleAttributeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAttributeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleAttributeEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleAttributeEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RoleAttributeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAttributeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleAttributeEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleAttributeEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAttributeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"RoleAttributeEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.RoleAttributeEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"RoleAttributeEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.RoleAttributeEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.RoleAttributeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
