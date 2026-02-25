//
// (c) 2014 Soffid
//
//
package com.soffid.iam.doc.model;
/**
 * DAO Base for Entity DocSign
 */
public abstract class DocSignDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.doc.model.DocSignDao
{
	com.soffid.iam.doc.model.DocumentEntityDao documentEntityDao;

	/**
	 * Sets reference to <code>documentEntityDao</code>.
	 */
	public void setDocumentEntityDao (com.soffid.iam.doc.model.DocumentEntityDao documentEntityDao) {
		this.documentEntityDao = documentEntityDao;
	}

	/**
	 * Gets reference to <code>documentEntityDao</code>.
	 */
	public com.soffid.iam.doc.model.DocumentEntityDao getDocumentEntityDao () {
		return documentEntityDao;
	}


	/**
	 * Creates an instance of {@link com.soffid.iam.doc.model.DocSign} .
	 */
	public com.soffid.iam.doc.model.DocSign newDocSign()
	{
		return new com.soffid.iam.doc.model.DocSignImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 */
	public com.soffid.iam.doc.model.DocSign load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.doc.model.DocSign result = (com.soffid.iam.doc.model.DocSign) this.getHibernateTemplate().get(com.soffid.iam.doc.model.DocSignImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.doc.model.DocSign> loadAll() {
		java.util.List<com.soffid.iam.doc.model.DocSign> result = (java.util.List<com.soffid.iam.doc.model.DocSign>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.doc.model.DocSign.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.doc.model.DocSign} to the persistent store.
	 */
	public void create (com.soffid.iam.doc.model.DocSign entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DocSignDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.doc.model.DocSign} at the persistent store.
	 */
	public void update (com.soffid.iam.doc.model.DocSign entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DocSignDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 */
	public void remove (com.soffid.iam.doc.model.DocSign entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"DocSignDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.doc.model.DocSign} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.doc.model.DocSign> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DocSignDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.doc.model.DocSign entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.doc.model.DocSign} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.doc.model.DocSign> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DocSignDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.doc.model.DocSign entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.doc.model.DocSign> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"DocSignDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.doc.model.DocSign entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"DocSignDao.remove - 'id' can not be null");
		}
		com.soffid.iam.doc.model.DocSign entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.doc.model.DocSign> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.doc.model.DocSign>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.doc.model.DocSign} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.doc.model.DocSign> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.doc.model.DocSign>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
