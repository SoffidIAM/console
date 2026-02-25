//
// (c) 2014 Soffid
//
//
package com.soffid.iam.doc.model;
/**
 * DAO Base for Entity DocumentEntity
 */
public abstract class DocumentEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.doc.model.DocumentEntityDao
{
	com.soffid.iam.doc.model.DocSignDao docSignDao;

	/**
	 * Sets reference to <code>docSignDao</code>.
	 */
	public void setDocSignDao (com.soffid.iam.doc.model.DocSignDao docSignDao) {
		this.docSignDao = docSignDao;
	}

	/**
	 * Gets reference to <code>docSignDao</code>.
	 */
	public com.soffid.iam.doc.model.DocSignDao getDocSignDao () {
		return docSignDao;
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
	 * Creates an instance of {@link com.soffid.iam.doc.model.DocumentEntity} .
	 */
	public com.soffid.iam.doc.model.DocumentEntity newDocumentEntity()
	{
		return new com.soffid.iam.doc.model.DocumentEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 */
	public com.soffid.iam.doc.model.DocumentEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.doc.model.DocumentEntity result = (com.soffid.iam.doc.model.DocumentEntity) this.getHibernateTemplate().get(com.soffid.iam.doc.model.DocumentEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.doc.model.DocumentEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.doc.model.DocumentEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.doc.model.DocumentEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.doc.model.DocumentEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DocumentEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.doc.model.DocumentEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.doc.model.DocumentEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DocumentEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.doc.model.DocumentEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DocumentEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.doc.model.DocumentEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.doc.model.DocumentEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DocumentEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.doc.model.DocumentEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.doc.model.DocumentEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.doc.model.DocumentEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DocumentEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.doc.model.DocumentEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.doc.model.DocumentEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DocumentEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.doc.model.DocumentEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"DocumentEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.doc.model.DocumentEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.doc.model.DocumentEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.doc.model.DocumentEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.doc.model.DocumentEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.doc.model.DocumentEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
