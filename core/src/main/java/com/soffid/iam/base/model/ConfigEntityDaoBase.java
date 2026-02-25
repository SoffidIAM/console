//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity ConfigEntity
 */
public abstract class ConfigEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.ConfigEntityDao
{
	com.soffid.iam.rc.model.AuditEntityDao auditEntityDao;

	/**
	 * Sets reference to <code>auditEntityDao</code>.
	 */
	public void setAuditEntityDao (com.soffid.iam.rc.model.AuditEntityDao auditEntityDao) {
		this.auditEntityDao = auditEntityDao;
	}

	/**
	 * Gets reference to <code>auditEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.AuditEntityDao getAuditEntityDao () {
		return auditEntityDao;
	}

	com.soffid.iam.am.model.NetworkEntityDao networkEntityDao;

	/**
	 * Sets reference to <code>networkEntityDao</code>.
	 */
	public void setNetworkEntityDao (com.soffid.iam.am.model.NetworkEntityDao networkEntityDao) {
		this.networkEntityDao = networkEntityDao;
	}

	/**
	 * Gets reference to <code>networkEntityDao</code>.
	 */
	public com.soffid.iam.am.model.NetworkEntityDao getNetworkEntityDao () {
		return networkEntityDao;
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
	 * Operation findByCodeAndNetworkCode
	 * @param name
	 * @param networkName
	 * @return
	**/
	public com.soffid.iam.base.model.ConfigEntity findByCodeAndNetworkCode(
	    java.lang.String name, 
	    java.lang.String networkName)
	
	{
		return findByCodeAndNetworkCode((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, networkName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.ConfigEntity findByCodeAndNetworkCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String networkName)
	
	{
		return findByCodeAndNetworkCode("select configuracio  \nfrom com.soffid.iam.base.model.ConfigEntity as configuracio \nleft join configuracio.network as network \nwhere \n  configuracio.name = :name and \n  configuracio.tenant.id = :tenantId and\n ((:networkName is null and network is null) or (network.name = :networkName))",
			criteria, name, networkName);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.ConfigEntity findByCodeAndNetworkCode(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String networkName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("networkName", networkName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.ConfigEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.ConfigEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.ConfigEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByTenantNameAndNetwork
	 * @param tenant
	 * @param name
	 * @param networkName
	 * @return
	**/
	public com.soffid.iam.base.model.ConfigEntity findByTenantNameAndNetwork(
	    java.lang.String tenant, 
	    java.lang.String name, 
	    java.lang.String networkName)
	
	{
		return findByTenantNameAndNetwork((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, tenant, name, networkName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.ConfigEntity findByTenantNameAndNetwork(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String tenant, java.lang.String name, java.lang.String networkName)
	
	{
		return findByTenantNameAndNetwork("select configuracio  \nfrom com.soffid.iam.base.model.ConfigEntity as configuracio \nleft join configuracio.network as network \nwhere \n  configuracio.name = :name and \n  configuracio.tenant.name = :tenant and \n ((:networkName is null and network is null) or (network.name = :networkName))",
			criteria, tenant, name, networkName);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.ConfigEntity findByTenantNameAndNetwork(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String tenant, java.lang.String name, java.lang.String networkName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("tenant", tenant, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("networkName", networkName, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.ConfigEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.ConfigEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.ConfigEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByFilter
	 * @param name
	 * @param network
	 * @param value
	 * @param description
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.ConfigEntity> findByFilter(
	    java.lang.String name, 
	    java.lang.String network, 
	    java.lang.String value, 
	    java.lang.String description)
	
	{
		return findByFilter((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, network, value, description);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.ConfigEntity> findByFilter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String network, java.lang.String value, java.lang.String description)
	
	{
		return findByFilter("select config from com.soffid.iam.base.model.ConfigEntity config left join config.network network where (:name is null or config.name like :name) and (:network is null or network.name like :network) and (:value is null or config.value like :value) and (:description is null or config.description like :description) and \nconfig.tenant.id = :tenantId",
			criteria, name, network, value, description);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.ConfigEntity> findByFilter(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String network, java.lang.String value, java.lang.String description)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("network", network, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("value", value, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("description", description, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.ConfigEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.base.model.ConfigEntity#	 * @see com.soffid.iam.base.model.ConfigEntity#void createMasterConfig(com.soffid.iam.base.model.ConfigEntity entity)
	 */
	public void createMasterConfig(
		com.soffid.iam.base.model.ConfigEntity entity)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (entity == null) {
			throw new IllegalArgumentException("void com.soffid.iam.base.model.ConfigEntity.createMasterConfig(com.soffid.iam.base.model.ConfigEntity entity) - entity cannot be null");
		}
		try
		{
			handleCreateMasterConfig(entity);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.base.model.ConfigEntity.class).
				warn ("Error on ConfigEntity.createMasterConfig", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on ConfigEntity.createMasterConfig: "+th.toString(), th);
		}
	}

	protected abstract void handleCreateMasterConfig(com.soffid.iam.base.model.ConfigEntity entity) throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.Configuration} object 
	 */
	public void toConfiguration(com.soffid.iam.base.model.ConfigEntity source, com.soffid.iam.base.api.Configuration target) {
		// Attributes for Configuration
		target.setName(source.getName());
		target.setValue(source.getValue());
		// Missing attribute networkName on entity
		target.setDescription(source.getDescription());
		target.setHidden(source.getHidden());
		target.setId(source.getId());
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Configuration} object 
	 */
	public com.soffid.iam.base.api.Configuration toConfiguration(com.soffid.iam.base.model.ConfigEntity entity) {
		final com.soffid.iam.base.api.Configuration target = new com.soffid.iam.base.api.Configuration();
		this.toConfiguration(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Configuration} list 
	 */
	public java.util.List<com.soffid.iam.base.api.Configuration> toConfigurationList (java.util.Collection<com.soffid.iam.base.model.ConfigEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.Configuration> list =
				new java.util.LinkedList<com.soffid.iam.base.api.Configuration>();
			for (final com.soffid.iam.base.model.ConfigEntity instance: instances)
			{
				list.add( toConfiguration(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.Configuration} object 
	 */
	public void configurationToEntity (com.soffid.iam.base.api.Configuration source, com.soffid.iam.base.model.ConfigEntity target, boolean copyIfNull) {
		// Attributes for ConfigEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getValue() != null)
		{
			target.setValue(source.getValue());
		}
		// Missing attribute network on entity
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getHidden() != null)
		{
			target.setHidden(source.getHidden());
		}
		// Missing attribute tenant on entity
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
	 *  Transforms from {@link com.soffid.iam.base.api.Configuration} object 
	 */
	public com.soffid.iam.base.model.ConfigEntity configurationToEntity (com.soffid.iam.base.api.Configuration instance) {
		com.soffid.iam.base.model.ConfigEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newConfigEntity();
		configurationToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Configuration} list 
	 */
	public java.util.List<com.soffid.iam.base.model.ConfigEntity>  configurationToEntityList (java.util.Collection<com.soffid.iam.base.api.Configuration> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.ConfigEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.ConfigEntity>();
		for (com.soffid.iam.base.api.Configuration instance: instances)
		{
			list.add (configurationToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.ConfigEntity} .
	 */
	public com.soffid.iam.base.model.ConfigEntity newConfigEntity()
	{
		return new com.soffid.iam.base.model.ConfigEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.ConfigEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.ConfigEntity result = (com.soffid.iam.base.model.ConfigEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.ConfigEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.ConfigEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.base.model.ConfigEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.base.model.ConfigEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.ConfigEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.ConfigEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ConfigEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.ConfigEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.ConfigEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ConfigEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.ConfigEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ConfigEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.ConfigEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.ConfigEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ConfigEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.ConfigEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.ConfigEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.ConfigEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ConfigEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.ConfigEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.ConfigEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ConfigEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.ConfigEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ConfigEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.ConfigEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.ConfigEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.ConfigEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.ConfigEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.ConfigEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
