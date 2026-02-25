//
// (c) 2014 Soffid
//
//
package com.soffid.iam.doc.model;
/**
 * DAO Base for Entity FileSystem
 */
public abstract class FileSystemDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.doc.model.FileSystemDao
{

	/**
	 * Operation nextNumberFor
	 * @param application
	 * @param year
	 * @return
	**/
	public java.lang.Long nextNumberFor(
	    java.lang.String application, 
	    int year)
	
	{
		return nextNumberFor((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, application, year);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long nextNumberFor(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String application, int year)
	
	{
		return nextNumberFor("from com.soffid.iam.doc.model.FileSystem where application=:application and year=:year",
			criteria, application, year);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long nextNumberFor(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String application, int year)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("application", application, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("year", year, org.hibernate.Hibernate.INTEGER);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.lang.Long result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.Long' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.Long) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Creates an instance of {@link com.soffid.iam.doc.model.FileSystem} .
	 */
	public com.soffid.iam.doc.model.FileSystem newFileSystem()
	{
		return new com.soffid.iam.doc.model.FileSystemImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 */
	public com.soffid.iam.doc.model.FileSystem load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.doc.model.FileSystem result = (com.soffid.iam.doc.model.FileSystem) this.getHibernateTemplate().get(com.soffid.iam.doc.model.FileSystemImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.doc.model.FileSystem> loadAll() {
		java.util.List<com.soffid.iam.doc.model.FileSystem> result = (java.util.List<com.soffid.iam.doc.model.FileSystem>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.doc.model.FileSystem.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.doc.model.FileSystem} to the persistent store.
	 */
	public void create (com.soffid.iam.doc.model.FileSystem entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"FileSystemDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.doc.model.FileSystem} at the persistent store.
	 */
	public void update (com.soffid.iam.doc.model.FileSystem entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"FileSystemDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 */
	public void remove (com.soffid.iam.doc.model.FileSystem entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"FileSystemDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.doc.model.FileSystem} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.doc.model.FileSystem> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"FileSystemDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.doc.model.FileSystem entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.doc.model.FileSystem} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.doc.model.FileSystem> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"FileSystemDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.doc.model.FileSystem entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.doc.model.FileSystem> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"FileSystemDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.doc.model.FileSystem entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"FileSystemDao.remove - 'id' can not be null");
		}
		com.soffid.iam.doc.model.FileSystem entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.doc.model.FileSystem> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.doc.model.FileSystem>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.doc.model.FileSystem> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.doc.model.FileSystem>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
