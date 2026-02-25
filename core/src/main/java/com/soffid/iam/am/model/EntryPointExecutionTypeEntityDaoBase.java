//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity EntryPointExecutionTypeEntity
 */
public abstract class EntryPointExecutionTypeEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.EntryPointExecutionTypeEntityDao
{

	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.am.model.EntryPointExecutionTypeEntity where name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.EntryPointExecutionTypeEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.EntryPointExecutionTypeEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.EntryPointExecutionTypeEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessTreeExecutionType} object 
	 */
	public void toAccessTreeExecutionType(com.soffid.iam.am.model.EntryPointExecutionTypeEntity source, com.soffid.iam.am.api.AccessTreeExecutionType target) {
		// Attributes for AccessTreeExecutionType
		target.setId(source.getId());
		target.setName(source.getName());
		target.setMimeType(source.getMimeType());
		target.setTemplate(source.getTemplate());
		target.setJavaClass(source.getJavaClass());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeExecutionType} object 
	 */
	public com.soffid.iam.am.api.AccessTreeExecutionType toAccessTreeExecutionType(com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity) {
		final com.soffid.iam.am.api.AccessTreeExecutionType target = new com.soffid.iam.am.api.AccessTreeExecutionType();
		this.toAccessTreeExecutionType(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeExecutionType} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessTreeExecutionType> toAccessTreeExecutionTypeList (java.util.Collection<com.soffid.iam.am.model.EntryPointExecutionTypeEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.AccessTreeExecutionType> list =
				new java.util.LinkedList<com.soffid.iam.am.api.AccessTreeExecutionType>();
			for (final com.soffid.iam.am.model.EntryPointExecutionTypeEntity instance: instances)
			{
				list.add( toAccessTreeExecutionType(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessTreeExecutionType} object 
	 */
	public void accessTreeExecutionTypeToEntity (com.soffid.iam.am.api.AccessTreeExecutionType source, com.soffid.iam.am.model.EntryPointExecutionTypeEntity target, boolean copyIfNull) {
		// Attributes for EntryPointExecutionTypeEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getMimeType() != null)
		{
			target.setMimeType(source.getMimeType());
		}
		if (copyIfNull || source.getTemplate() != null)
		{
			target.setTemplate(source.getTemplate());
		}
		if (copyIfNull || source.getJavaClass() != null)
		{
			target.setJavaClass(source.getJavaClass());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeExecutionType} object 
	 */
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntity accessTreeExecutionTypeToEntity (com.soffid.iam.am.api.AccessTreeExecutionType instance) {
		com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newEntryPointExecutionTypeEntity();
		accessTreeExecutionTypeToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeExecutionType} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutionTypeEntity>  accessTreeExecutionTypeToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessTreeExecutionType> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.EntryPointExecutionTypeEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.EntryPointExecutionTypeEntity>();
		for (com.soffid.iam.am.api.AccessTreeExecutionType instance: instances)
		{
			list.add (accessTreeExecutionTypeToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntity newEntryPointExecutionTypeEntity()
	{
		return new com.soffid.iam.am.model.EntryPointExecutionTypeEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.EntryPointExecutionTypeEntity result = (com.soffid.iam.am.model.EntryPointExecutionTypeEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.EntryPointExecutionTypeEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutionTypeEntity> loadAll() {
		java.util.List<com.soffid.iam.am.model.EntryPointExecutionTypeEntity> result = (java.util.List<com.soffid.iam.am.model.EntryPointExecutionTypeEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.am.model.EntryPointExecutionTypeEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointExecutionTypeEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointExecutionTypeEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointExecutionTypeEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointExecutionTypeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointExecutionTypeEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointExecutionTypeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointExecutionTypeEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointExecutionTypeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointExecutionTypeEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"EntryPointExecutionTypeEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.EntryPointExecutionTypeEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutionTypeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.EntryPointExecutionTypeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointExecutionTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutionTypeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.EntryPointExecutionTypeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
