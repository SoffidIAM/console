//
// (c) 2014 Soffid
//
//
package com.soffid.iam.rc.model;
/**
 * DAO Base for Entity IssuePolicyActionEntity
 */
public abstract class IssuePolicyActionEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.rc.model.IssuePolicyActionEntityDao
{
	com.soffid.iam.rc.model.IssuePolicyEntityDao issuePolicyEntityDao;

	/**
	 * Sets reference to <code>issuePolicyEntityDao</code>.
	 */
	public void setIssuePolicyEntityDao (com.soffid.iam.rc.model.IssuePolicyEntityDao issuePolicyEntityDao) {
		this.issuePolicyEntityDao = issuePolicyEntityDao;
	}

	/**
	 * Gets reference to <code>issuePolicyEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssuePolicyEntityDao getIssuePolicyEntityDao () {
		return issuePolicyEntityDao;
	}


	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.IssuePolicyAction} object 
	 */
	public void toIssuePolicyAction(com.soffid.iam.rc.model.IssuePolicyActionEntity source, com.soffid.iam.rc.api.IssuePolicyAction target) {
		// Attributes for IssuePolicyAction
		target.setId(source.getId());
		target.setStatus(source.getStatus());
		target.setAction(source.getAction());
		target.setDescription(source.getDescription());
		target.setSubject(source.getSubject());
		target.setBody(source.getBody());
		target.setEmailAddress(source.getEmailAddress());
		target.setProcessDefinition(source.getProcessDefinition());
		target.setScript(source.getScript());
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssuePolicyAction} object 
	 */
	public com.soffid.iam.rc.api.IssuePolicyAction toIssuePolicyAction(com.soffid.iam.rc.model.IssuePolicyActionEntity entity) {
		final com.soffid.iam.rc.api.IssuePolicyAction target = new com.soffid.iam.rc.api.IssuePolicyAction();
		this.toIssuePolicyAction(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssuePolicyAction} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.IssuePolicyAction> toIssuePolicyActionList (java.util.Collection<com.soffid.iam.rc.model.IssuePolicyActionEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.rc.api.IssuePolicyAction> list =
				new java.util.LinkedList<com.soffid.iam.rc.api.IssuePolicyAction>();
			for (final com.soffid.iam.rc.model.IssuePolicyActionEntity instance: instances)
			{
				list.add( toIssuePolicyAction(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.IssuePolicyAction} object 
	 */
	public void issuePolicyActionToEntity (com.soffid.iam.rc.api.IssuePolicyAction source, com.soffid.iam.rc.model.IssuePolicyActionEntity target, boolean copyIfNull) {
		// Attributes for IssuePolicyActionEntity
		// Missing attribute issuePolicy on entity
		if (copyIfNull || source.getStatus() != null)
		{
			target.setStatus(source.getStatus());
		}
		if (copyIfNull || source.getAction() != null)
		{
			target.setAction(source.getAction());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getSubject() != null)
		{
			target.setSubject(source.getSubject());
		}
		if (copyIfNull || source.getBody() != null)
		{
			target.setBody(source.getBody());
		}
		if (copyIfNull || source.getEmailAddress() != null)
		{
			target.setEmailAddress(source.getEmailAddress());
		}
		if (copyIfNull || source.getProcessDefinition() != null)
		{
			target.setProcessDefinition(source.getProcessDefinition());
		}
		if (copyIfNull || source.getScript() != null)
		{
			target.setScript(source.getScript());
		}
		if (copyIfNull || source.getCreatedOn() != null)
		{
			target.setCreatedOn(source.getCreatedOn());
		}
		if (copyIfNull || source.getCreatedBy() != null)
		{
			target.setCreatedBy(source.getCreatedBy());
		}
		if (copyIfNull || source.getUpdatedOn() != null)
		{
			target.setUpdatedOn(source.getUpdatedOn());
		}
		if (copyIfNull || source.getUpdatedBy() != null)
		{
			target.setUpdatedBy(source.getUpdatedBy());
		}
		if (copyIfNull || source.getDeletedOn() != null)
		{
			target.setDeletedOn(source.getDeletedOn());
		}
		if (copyIfNull || source.getDeletedBy() != null)
		{
			target.setDeletedBy(source.getDeletedBy());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssuePolicyAction} object 
	 */
	public com.soffid.iam.rc.model.IssuePolicyActionEntity issuePolicyActionToEntity (com.soffid.iam.rc.api.IssuePolicyAction instance) {
		com.soffid.iam.rc.model.IssuePolicyActionEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newIssuePolicyActionEntity();
		issuePolicyActionToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssuePolicyAction} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyActionEntity>  issuePolicyActionToEntityList (java.util.Collection<com.soffid.iam.rc.api.IssuePolicyAction> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.rc.model.IssuePolicyActionEntity> list =
			new java.util.LinkedList<com.soffid.iam.rc.model.IssuePolicyActionEntity>();
		for (com.soffid.iam.rc.api.IssuePolicyAction instance: instances)
		{
			list.add (issuePolicyActionToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} .
	 */
	public com.soffid.iam.rc.model.IssuePolicyActionEntity newIssuePolicyActionEntity()
	{
		return new com.soffid.iam.rc.model.IssuePolicyActionEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.IssuePolicyActionEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.rc.model.IssuePolicyActionEntity result = (com.soffid.iam.rc.model.IssuePolicyActionEntity) this.getHibernateTemplate().get(com.soffid.iam.rc.model.IssuePolicyActionEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyActionEntity> loadAll() {
		java.util.List<com.soffid.iam.rc.model.IssuePolicyActionEntity> result = (java.util.List<com.soffid.iam.rc.model.IssuePolicyActionEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.rc.model.IssuePolicyActionEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.IssuePolicyActionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssuePolicyActionEntityDao.create - 'entity' can not be null");
		}

		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.IssuePolicyActionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssuePolicyActionEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.IssuePolicyActionEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssuePolicyActionEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.IssuePolicyActionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssuePolicyActionEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssuePolicyActionEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.IssuePolicyActionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssuePolicyActionEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssuePolicyActionEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.IssuePolicyActionEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssuePolicyActionEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssuePolicyActionEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"IssuePolicyActionEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.rc.model.IssuePolicyActionEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyActionEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.rc.model.IssuePolicyActionEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyActionEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.rc.model.IssuePolicyActionEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
