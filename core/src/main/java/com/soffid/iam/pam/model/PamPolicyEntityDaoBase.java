//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity PamPolicyEntity
 */
public abstract class PamPolicyEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.PamPolicyEntityDao
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

	com.soffid.iam.am.model.VaultFolderEntityDao vaultFolderEntityDao;

	/**
	 * Sets reference to <code>vaultFolderEntityDao</code>.
	 */
	public void setVaultFolderEntityDao (com.soffid.iam.am.model.VaultFolderEntityDao vaultFolderEntityDao) {
		this.vaultFolderEntityDao = vaultFolderEntityDao;
	}

	/**
	 * Gets reference to <code>vaultFolderEntityDao</code>.
	 */
	public com.soffid.iam.am.model.VaultFolderEntityDao getVaultFolderEntityDao () {
		return vaultFolderEntityDao;
	}

	com.soffid.iam.pam.model.PamPolicyJITPermissionEntityDao pamPolicyJITPermissionEntityDao;

	/**
	 * Sets reference to <code>pamPolicyJITPermissionEntityDao</code>.
	 */
	public void setPamPolicyJITPermissionEntityDao (com.soffid.iam.pam.model.PamPolicyJITPermissionEntityDao pamPolicyJITPermissionEntityDao) {
		this.pamPolicyJITPermissionEntityDao = pamPolicyJITPermissionEntityDao;
	}

	/**
	 * Gets reference to <code>pamPolicyJITPermissionEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.PamPolicyJITPermissionEntityDao getPamPolicyJITPermissionEntityDao () {
		return pamPolicyJITPermissionEntityDao;
	}


	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.pam.model.PamPolicyEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.pam.model.PamPolicyEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.pam.model.PamPolicyEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.pam.model.PamPolicyEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.pam.model.PamPolicyEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.pam.model.PamPolicyEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.pam.model.PamPolicyEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.PamPolicy} object 
	 */
	public void toPamPolicy(com.soffid.iam.pam.model.PamPolicyEntity source, com.soffid.iam.pam.api.PamPolicy target) {
		// Attributes for PamPolicy
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setAuthor(source.getAuthor());
		target.setDate(source.getDate());
		target.setRecordingDuration(source.getRecordingDuration());
		target.setExpression(source.getExpression());
		target.setPriority(source.getPriority());
		// Incompatible types source.justInTimePermissions and target.justInTimePermissions
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.PamPolicy} object 
	 */
	public com.soffid.iam.pam.api.PamPolicy toPamPolicy(com.soffid.iam.pam.model.PamPolicyEntity entity) {
		final com.soffid.iam.pam.api.PamPolicy target = new com.soffid.iam.pam.api.PamPolicy();
		this.toPamPolicy(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.PamPolicy} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.PamPolicy> toPamPolicyList (java.util.Collection<com.soffid.iam.pam.model.PamPolicyEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.pam.api.PamPolicy> list =
				new java.util.LinkedList<com.soffid.iam.pam.api.PamPolicy>();
			for (final com.soffid.iam.pam.model.PamPolicyEntity instance: instances)
			{
				list.add( toPamPolicy(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.PamPolicy} object 
	 */
	public void pamPolicyToEntity (com.soffid.iam.pam.api.PamPolicy source, com.soffid.iam.pam.model.PamPolicyEntity target, boolean copyIfNull) {
		// Attributes for PamPolicyEntity
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
		if (copyIfNull || source.getRecordingDuration() != null)
		{
			target.setRecordingDuration(source.getRecordingDuration());
		}
		if (copyIfNull || source.getExpression() != null)
		{
			target.setExpression(source.getExpression());
		}
		if (copyIfNull || source.getPriority() != null)
		{
			target.setPriority(source.getPriority());
		}
		// Missing attribute tenant on entity
		// Missing attribute vaultFolders on entity
		// Missing attribute actions on entity
		if (copyIfNull || source.getJustInTimePermissions() != null)
		{
			// Incompatible types source.justInTimePermissions and target.justInTimePermissions
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
	 *  Transforms from {@link com.soffid.iam.pam.api.PamPolicy} object 
	 */
	public com.soffid.iam.pam.model.PamPolicyEntity pamPolicyToEntity (com.soffid.iam.pam.api.PamPolicy instance) {
		com.soffid.iam.pam.model.PamPolicyEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newPamPolicyEntity();
		pamPolicyToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.PamPolicy} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyEntity>  pamPolicyToEntityList (java.util.Collection<com.soffid.iam.pam.api.PamPolicy> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.pam.model.PamPolicyEntity> list =
			new java.util.LinkedList<com.soffid.iam.pam.model.PamPolicyEntity>();
		for (com.soffid.iam.pam.api.PamPolicy instance: instances)
		{
			list.add (pamPolicyToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.PamPolicyEntity} .
	 */
	public com.soffid.iam.pam.model.PamPolicyEntity newPamPolicyEntity()
	{
		return new com.soffid.iam.pam.model.PamPolicyEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.PamPolicyEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.PamPolicyEntity result = (com.soffid.iam.pam.model.PamPolicyEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.PamPolicyEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.pam.model.PamPolicyEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.pam.model.PamPolicyEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.PamPolicyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.PamPolicyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PamPolicyEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.pam.model.PamPolicyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.PamPolicyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PamPolicyEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.PamPolicyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PamPolicyEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.PamPolicyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.PamPolicyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PamPolicyEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.PamPolicyEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.PamPolicyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.PamPolicyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PamPolicyEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.PamPolicyEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.PamPolicyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PamPolicyEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.PamPolicyEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"PamPolicyEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.PamPolicyEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.PamPolicyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.PamPolicyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
