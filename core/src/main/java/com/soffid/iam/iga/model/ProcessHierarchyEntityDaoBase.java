//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity ProcessHierarchyEntity
 */
public abstract class ProcessHierarchyEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.ProcessHierarchyEntityDao
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


	/**
	 * Operation findByChildren
	 * @param childProcess
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> findByChildren(
	    java.lang.Long childProcess)
	
	{
		return findByChildren((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, childProcess);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> findByChildren(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long childProcess)
	
	{
		return findByChildren("from com.soffid.iam.iga.model.ProcessHierarchyEntity where childProcess=:childProcess",
			criteria, childProcess);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> findByChildren(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long childProcess)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("childProcess", childProcess, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByParent
	 * @param parentProcess
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> findByParent(
	    java.lang.Long parentProcess)
	
	{
		return findByParent((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, parentProcess);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> findByParent(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long parentProcess)
	
	{
		return findByParent("from com.soffid.iam.iga.model.ProcessHierarchyEntity where parentProcess=:parentProcess",
			criteria, parentProcess);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> findByParent(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long parentProcess)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("parentProcess", parentProcess, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} .
	 */
	public com.soffid.iam.iga.model.ProcessHierarchyEntity newProcessHierarchyEntity()
	{
		return new com.soffid.iam.iga.model.ProcessHierarchyEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ProcessHierarchyEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.ProcessHierarchyEntity result = (com.soffid.iam.iga.model.ProcessHierarchyEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.ProcessHierarchyEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> result = (java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.ProcessHierarchyEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ProcessHierarchyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ProcessHierarchyEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ProcessHierarchyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ProcessHierarchyEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ProcessHierarchyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ProcessHierarchyEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ProcessHierarchyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ProcessHierarchyEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ProcessHierarchyEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ProcessHierarchyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ProcessHierarchyEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ProcessHierarchyEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ProcessHierarchyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ProcessHierarchyEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.ProcessHierarchyEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ProcessHierarchyEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.ProcessHierarchyEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.ProcessHierarchyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.ProcessHierarchyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
