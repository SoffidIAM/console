//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity UserProcessEntity
 */
public abstract class UserProcessEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.UserProcessEntityDao
{

	/**
	 * Operation findByUserName
	 * @param userName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByUserName(
	    java.lang.String userName)
	
	{
		return findByUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findByUserName("from com.soffid.iam.iga.model.UserProcessEntity where userName=:userName",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByProcessId
	 * @param processId
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByProcessId(
	    java.lang.Long processId)
	
	{
		return findByProcessId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, processId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByProcessId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long processId)
	
	{
		return findByProcessId("from com.soffid.iam.iga.model.UserProcessEntity where processId=:processId",
			criteria, processId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByProcessId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long processId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("processId", processId, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUserNationalId
	 * @param nationalId
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByUserNationalId(
	    java.lang.String nationalId)
	
	{
		return findByUserNationalId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nationalId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByUserNationalId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nationalId)
	
	{
		return findByUserNationalId("from com.soffid.iam.iga.model.UserProcessEntity where nationalId=:nationalId",
			criteria, nationalId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByUserNationalId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nationalId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nationalId", nationalId, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.BpmUserProcess} object 
	 */
	public void toBpmUserProcess(com.soffid.iam.iga.model.UserProcessEntity source, com.soffid.iam.iga.api.BpmUserProcess target) {
		// Attributes for BpmUserProcess
		target.setId(source.getId());
		target.setProcessId(source.getProcessId());
		target.setUserName(source.getUserName());
		target.setFinished(source.getFinished());
		// Missing attribute userNationalId on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.BpmUserProcess} object 
	 */
	public com.soffid.iam.iga.api.BpmUserProcess toBpmUserProcess(com.soffid.iam.iga.model.UserProcessEntity entity) {
		final com.soffid.iam.iga.api.BpmUserProcess target = new com.soffid.iam.iga.api.BpmUserProcess();
		this.toBpmUserProcess(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.BpmUserProcess} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.BpmUserProcess> toBpmUserProcessList (java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.BpmUserProcess> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.BpmUserProcess>();
			for (final com.soffid.iam.iga.model.UserProcessEntity instance: instances)
			{
				list.add( toBpmUserProcess(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.BpmUserProcess} object 
	 */
	public void bpmUserProcessToEntity (com.soffid.iam.iga.api.BpmUserProcess source, com.soffid.iam.iga.model.UserProcessEntity target, boolean copyIfNull) {
		// Attributes for UserProcessEntity
		if (copyIfNull || source.getUserName() != null)
		{
			target.setUserName(source.getUserName());
		}
		if (copyIfNull || source.getProcessId() != null)
		{
			target.setProcessId(source.getProcessId());
		}
		if (copyIfNull || source.getFinished() != null)
		{
			target.setFinished(source.getFinished());
		}
		// Missing attribute nationalId on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.BpmUserProcess} object 
	 */
	public com.soffid.iam.iga.model.UserProcessEntity bpmUserProcessToEntity (com.soffid.iam.iga.api.BpmUserProcess instance) {
		com.soffid.iam.iga.model.UserProcessEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newUserProcessEntity();
		bpmUserProcessToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.BpmUserProcess} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.UserProcessEntity>  bpmUserProcessToEntityList (java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.UserProcessEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.UserProcessEntity>();
		for (com.soffid.iam.iga.api.BpmUserProcess instance: instances)
		{
			list.add (bpmUserProcessToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.UserProcessEntity} .
	 */
	public com.soffid.iam.iga.model.UserProcessEntity newUserProcessEntity()
	{
		return new com.soffid.iam.iga.model.UserProcessEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.UserProcessEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.UserProcessEntity result = (com.soffid.iam.iga.model.UserProcessEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.UserProcessEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.UserProcessEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.UserProcessEntity> result = (java.util.List<com.soffid.iam.iga.model.UserProcessEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.UserProcessEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.UserProcessEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.UserProcessEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserProcessEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.UserProcessEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.UserProcessEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserProcessEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.UserProcessEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserProcessEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.UserProcessEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.UserProcessEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserProcessEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserProcessEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.UserProcessEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.UserProcessEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserProcessEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserProcessEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.UserProcessEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserProcessEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserProcessEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"UserProcessEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.UserProcessEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.UserProcessEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.UserProcessEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.UserProcessEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.UserProcessEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
