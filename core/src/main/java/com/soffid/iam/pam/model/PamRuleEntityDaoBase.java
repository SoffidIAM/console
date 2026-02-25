//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity PamRuleEntity
 */
public abstract class PamRuleEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.PamRuleEntityDao
{
	com.soffid.iam.pam.model.PamActionEntityDao pamActionEntityDao;

	/**
	 * Sets reference to <code>pamActionEntityDao</code>.
	 */
	public void setPamActionEntityDao (com.soffid.iam.pam.model.PamActionEntityDao pamActionEntityDao) {
		this.pamActionEntityDao = pamActionEntityDao;
	}

	/**
	 * Gets reference to <code>pamActionEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.PamActionEntityDao getPamActionEntityDao () {
		return pamActionEntityDao;
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

	com.soffid.iam.rc.model.IssueEntityDao issueEntityDao;

	/**
	 * Sets reference to <code>issueEntityDao</code>.
	 */
	public void setIssueEntityDao (com.soffid.iam.rc.model.IssueEntityDao issueEntityDao) {
		this.issueEntityDao = issueEntityDao;
	}

	/**
	 * Gets reference to <code>issueEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueEntityDao getIssueEntityDao () {
		return issueEntityDao;
	}


	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.pam.model.PamRuleEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.pam.model.PamRuleEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.pam.model.PamRuleEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.pam.model.PamRuleEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.pam.model.PamRuleEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.pam.model.PamRuleEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.pam.model.PamRuleEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.PamRule} object 
	 */
	public void toPamRule(com.soffid.iam.pam.model.PamRuleEntity source, com.soffid.iam.pam.api.PamRule target) {
		// Attributes for PamRule
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setType(source.getType());
		target.setContent(source.getContent());
		target.setAuthor(source.getAuthor());
		target.setDate(source.getDate());
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.PamRule} object 
	 */
	public com.soffid.iam.pam.api.PamRule toPamRule(com.soffid.iam.pam.model.PamRuleEntity entity) {
		final com.soffid.iam.pam.api.PamRule target = new com.soffid.iam.pam.api.PamRule();
		this.toPamRule(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.PamRule} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.PamRule> toPamRuleList (java.util.Collection<com.soffid.iam.pam.model.PamRuleEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.pam.api.PamRule> list =
				new java.util.LinkedList<com.soffid.iam.pam.api.PamRule>();
			for (final com.soffid.iam.pam.model.PamRuleEntity instance: instances)
			{
				list.add( toPamRule(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.PamRule} object 
	 */
	public void pamRuleToEntity (com.soffid.iam.pam.api.PamRule source, com.soffid.iam.pam.model.PamRuleEntity target, boolean copyIfNull) {
		// Attributes for PamRuleEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getAuthor() != null)
		{
			target.setAuthor(source.getAuthor());
		}
		if (copyIfNull || source.getDate() != null)
		{
			target.setDate(source.getDate());
		}
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		if (copyIfNull || source.getContent() != null)
		{
			target.setContent(source.getContent());
		}
		// Missing attribute blob on entity
		// Missing attribute tenant on entity
		// Missing attribute actions on entity
		// Missing attribute events on entity
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
	 *  Transforms from {@link com.soffid.iam.pam.api.PamRule} object 
	 */
	public com.soffid.iam.pam.model.PamRuleEntity pamRuleToEntity (com.soffid.iam.pam.api.PamRule instance) {
		com.soffid.iam.pam.model.PamRuleEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newPamRuleEntity();
		pamRuleToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.PamRule} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.PamRuleEntity>  pamRuleToEntityList (java.util.Collection<com.soffid.iam.pam.api.PamRule> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.pam.model.PamRuleEntity> list =
			new java.util.LinkedList<com.soffid.iam.pam.model.PamRuleEntity>();
		for (com.soffid.iam.pam.api.PamRule instance: instances)
		{
			list.add (pamRuleToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.PamRuleEntity} .
	 */
	public com.soffid.iam.pam.model.PamRuleEntity newPamRuleEntity()
	{
		return new com.soffid.iam.pam.model.PamRuleEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.PamRuleEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.PamRuleEntity result = (com.soffid.iam.pam.model.PamRuleEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.PamRuleEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.PamRuleEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.pam.model.PamRuleEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.pam.model.PamRuleEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.PamRuleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.PamRuleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PamRuleEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.PamRuleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.PamRuleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PamRuleEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.PamRuleEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PamRuleEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.PamRuleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.PamRuleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PamRuleEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.PamRuleEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.PamRuleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.PamRuleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PamRuleEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.PamRuleEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.PamRuleEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PamRuleEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.PamRuleEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"PamRuleEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.PamRuleEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.PamRuleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.PamRuleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.PamRuleEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.PamRuleEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
