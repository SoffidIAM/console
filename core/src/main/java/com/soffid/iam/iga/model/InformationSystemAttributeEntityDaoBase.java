//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity InformationSystemAttributeEntity
 */
public abstract class InformationSystemAttributeEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.InformationSystemAttributeEntityDao
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

	com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao;

	/**
	 * Sets reference to <code>informationSystemEntityDao</code>.
	 */
	public void setInformationSystemEntityDao (com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao) {
		this.informationSystemEntityDao = informationSystemEntityDao;
	}

	/**
	 * Gets reference to <code>informationSystemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.InformationSystemEntityDao getInformationSystemEntityDao () {
		return informationSystemEntityDao;
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


	/**
	 * Operation findByNameAndValue
	 * @param name
	 * @param value
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.InformationSystemAttributeEntity> findByNameAndValue(
	    java.lang.String name, 
	    java.lang.String value)
	
	{
		return findByNameAndValue((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, value);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemAttributeEntity> findByNameAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String value)
	
	{
		return findByNameAndValue("select att from com.soffid.iam.iga.model.InformationSystemAttributeEntity as att where att.metadata.name = :name and att.value = :value ",
			criteria, name, value);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemAttributeEntity> findByNameAndValue(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String value)
	
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
			return (java.util.List<com.soffid.iam.iga.model.InformationSystemAttributeEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.InformationSystemAttributeEntity} .
	 */
	public com.soffid.iam.iga.model.InformationSystemAttributeEntity newInformationSystemAttributeEntity()
	{
		return new com.soffid.iam.iga.model.InformationSystemAttributeEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.InformationSystemAttributeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.InformationSystemAttributeEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.InformationSystemAttributeEntity result = (com.soffid.iam.iga.model.InformationSystemAttributeEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.InformationSystemAttributeEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.InformationSystemAttributeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemAttributeEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.InformationSystemAttributeEntity where informationSystem.tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.InformationSystemAttributeEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.InformationSystemAttributeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.InformationSystemAttributeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"InformationSystemAttributeEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.InformationSystemAttributeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.InformationSystemAttributeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"InformationSystemAttributeEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.InformationSystemAttributeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.InformationSystemAttributeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"InformationSystemAttributeEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.InformationSystemAttributeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.InformationSystemAttributeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"InformationSystemAttributeEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.InformationSystemAttributeEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.InformationSystemAttributeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.InformationSystemAttributeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"InformationSystemAttributeEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.InformationSystemAttributeEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.InformationSystemAttributeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.InformationSystemAttributeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"InformationSystemAttributeEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.InformationSystemAttributeEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.InformationSystemAttributeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"InformationSystemAttributeEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.InformationSystemAttributeEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.InformationSystemAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemAttributeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.InformationSystemAttributeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.InformationSystemAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.InformationSystemAttributeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.InformationSystemAttributeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
