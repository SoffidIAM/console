//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity OsTypeEntity
 */
public abstract class OsTypeEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.OsTypeEntityDao
{
	com.soffid.iam.am.model.HostEntityDao hostEntityDao;

	/**
	 * Sets reference to <code>hostEntityDao</code>.
	 */
	public void setHostEntityDao (com.soffid.iam.am.model.HostEntityDao hostEntityDao) {
		this.hostEntityDao = hostEntityDao;
	}

	/**
	 * Gets reference to <code>hostEntityDao</code>.
	 */
	public com.soffid.iam.am.model.HostEntityDao getHostEntityDao () {
		return hostEntityDao;
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
	 * Operation findOSTypeByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.am.model.OsTypeEntity findOSTypeByName(
	    java.lang.String name)
	
	{
		return findOSTypeByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.OsTypeEntity findOSTypeByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findOSTypeByName("from com.soffid.iam.am.model.OsTypeEntity where tenant.id=:tenantId and name=:name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.OsTypeEntity findOSTypeByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
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
			com.soffid.iam.am.model.OsTypeEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.OsTypeEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.OsTypeEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.OsType} object 
	 */
	public void toOsType(com.soffid.iam.am.model.OsTypeEntity source, com.soffid.iam.am.api.OsType target) {
		// Attributes for OsType
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.OsType} object 
	 */
	public com.soffid.iam.am.api.OsType toOsType(com.soffid.iam.am.model.OsTypeEntity entity) {
		final com.soffid.iam.am.api.OsType target = new com.soffid.iam.am.api.OsType();
		this.toOsType(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.OsType} list 
	 */
	public java.util.List<com.soffid.iam.am.api.OsType> toOsTypeList (java.util.Collection<com.soffid.iam.am.model.OsTypeEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.OsType> list =
				new java.util.LinkedList<com.soffid.iam.am.api.OsType>();
			for (final com.soffid.iam.am.model.OsTypeEntity instance: instances)
			{
				list.add( toOsType(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.OsType} object 
	 */
	public void osTypeToEntity (com.soffid.iam.am.api.OsType source, com.soffid.iam.am.model.OsTypeEntity target, boolean copyIfNull) {
		// Attributes for OsTypeEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		// Missing attribute tenant on entity
		// Missing attribute operatingSystemHost on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.OsType} object 
	 */
	public com.soffid.iam.am.model.OsTypeEntity osTypeToEntity (com.soffid.iam.am.api.OsType instance) {
		com.soffid.iam.am.model.OsTypeEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newOsTypeEntity();
		osTypeToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.OsType} list 
	 */
	public java.util.List<com.soffid.iam.am.model.OsTypeEntity>  osTypeToEntityList (java.util.Collection<com.soffid.iam.am.api.OsType> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.OsTypeEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.OsTypeEntity>();
		for (com.soffid.iam.am.api.OsType instance: instances)
		{
			list.add (osTypeToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.OsTypeEntity} .
	 */
	public com.soffid.iam.am.model.OsTypeEntity newOsTypeEntity()
	{
		return new com.soffid.iam.am.model.OsTypeEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.OsTypeEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.OsTypeEntity result = (com.soffid.iam.am.model.OsTypeEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.OsTypeEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.OsTypeEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.OsTypeEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.OsTypeEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.OsTypeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.OsTypeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"OsTypeEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.OsTypeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.OsTypeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"OsTypeEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.OsTypeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"OsTypeEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.OsTypeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.OsTypeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"OsTypeEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.OsTypeEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.OsTypeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.OsTypeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"OsTypeEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.OsTypeEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.OsTypeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"OsTypeEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.OsTypeEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"OsTypeEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.OsTypeEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.OsTypeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.OsTypeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.OsTypeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.OsTypeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
