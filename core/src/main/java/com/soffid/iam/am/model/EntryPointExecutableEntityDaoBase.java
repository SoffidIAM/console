//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity EntryPointExecutableEntity
 */
public abstract class EntryPointExecutableEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.EntryPointExecutableEntityDao
{
	com.soffid.iam.am.model.EntryPointEntityDao entryPointEntityDao;

	/**
	 * Sets reference to <code>entryPointEntityDao</code>.
	 */
	public void setEntryPointEntityDao (com.soffid.iam.am.model.EntryPointEntityDao entryPointEntityDao) {
		this.entryPointEntityDao = entryPointEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointEntityDao getEntryPointEntityDao () {
		return entryPointEntityDao;
	}

	com.soffid.iam.am.model.EntryPointExecutionTypeEntityDao entryPointExecutionTypeEntityDao;

	/**
	 * Sets reference to <code>entryPointExecutionTypeEntityDao</code>.
	 */
	public void setEntryPointExecutionTypeEntityDao (com.soffid.iam.am.model.EntryPointExecutionTypeEntityDao entryPointExecutionTypeEntityDao) {
		this.entryPointExecutionTypeEntityDao = entryPointExecutionTypeEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointExecutionTypeEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointExecutionTypeEntityDao getEntryPointExecutionTypeEntityDao () {
		return entryPointExecutionTypeEntityDao;
	}


	/**
	 * Operation findByEntryPoint
	 * @param entryPoint
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity> findByEntryPoint(
	    com.soffid.iam.am.model.EntryPointEntity entryPoint)
	
	{
		return findByEntryPoint((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, entryPoint);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity> findByEntryPoint(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.am.model.EntryPointEntity entryPoint)
	
	{
		return findByEntryPoint("from com.soffid.iam.am.model.EntryPointExecutableEntity where entryPoint=:entryPoint",
			criteria, entryPoint);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity> findByEntryPoint(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.am.model.EntryPointEntity entryPoint)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("entryPoint", entryPoint);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessTreeExecution} object 
	 */
	public void toAccessTreeExecution(com.soffid.iam.am.model.EntryPointExecutableEntity source, com.soffid.iam.am.api.AccessTreeExecution target) {
		// Attributes for AccessTreeExecution
		target.setId(source.getId());
		target.setScope(source.getScope());
		target.setContent(source.getContent());
		// Missing attribute executionTypeCode on entity
		// Missing attribute typeMimeExecution on entity
		// Missing attribute AccessTreeId on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeExecution} object 
	 */
	public com.soffid.iam.am.api.AccessTreeExecution toAccessTreeExecution(com.soffid.iam.am.model.EntryPointExecutableEntity entity) {
		final com.soffid.iam.am.api.AccessTreeExecution target = new com.soffid.iam.am.api.AccessTreeExecution();
		this.toAccessTreeExecution(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessTreeExecution} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessTreeExecution> toAccessTreeExecutionList (java.util.Collection<com.soffid.iam.am.model.EntryPointExecutableEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.AccessTreeExecution> list =
				new java.util.LinkedList<com.soffid.iam.am.api.AccessTreeExecution>();
			for (final com.soffid.iam.am.model.EntryPointExecutableEntity instance: instances)
			{
				list.add( toAccessTreeExecution(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessTreeExecution} object 
	 */
	public void accessTreeExecutionToEntity (com.soffid.iam.am.api.AccessTreeExecution source, com.soffid.iam.am.model.EntryPointExecutableEntity target, boolean copyIfNull) {
		// Attributes for EntryPointExecutableEntity
		if (copyIfNull || source.getScope() != null)
		{
			target.setScope(source.getScope());
		}
		if (copyIfNull || source.getContent() != null)
		{
			target.setContent(source.getContent());
		}
		// Missing attribute entryPoint on entity
		// Missing attribute executionCode on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeExecution} object 
	 */
	public com.soffid.iam.am.model.EntryPointExecutableEntity accessTreeExecutionToEntity (com.soffid.iam.am.api.AccessTreeExecution instance) {
		com.soffid.iam.am.model.EntryPointExecutableEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newEntryPointExecutableEntity();
		accessTreeExecutionToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessTreeExecution} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity>  accessTreeExecutionToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.EntryPointExecutableEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.EntryPointExecutableEntity>();
		for (com.soffid.iam.am.api.AccessTreeExecution instance: instances)
		{
			list.add (accessTreeExecutionToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointExecutableEntity newEntryPointExecutableEntity()
	{
		return new com.soffid.iam.am.model.EntryPointExecutableEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointExecutableEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.EntryPointExecutableEntity result = (com.soffid.iam.am.model.EntryPointExecutableEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.EntryPointExecutableEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity> loadAll() {
		java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity> result = (java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.am.model.EntryPointExecutableEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointExecutableEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointExecutableEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointExecutableEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointExecutableEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointExecutableEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointExecutableEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointExecutableEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointExecutableEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointExecutableEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointExecutableEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointExecutableEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointExecutableEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointExecutableEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointExecutableEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointExecutableEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"EntryPointExecutableEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.EntryPointExecutableEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointExecutableEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.EntryPointExecutableEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
