//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity AccountAttributeEntity
 */
public abstract class AccountAttributeEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.AccountAttributeEntityDao
{
	com.soffid.iam.base.model.AccountEntityDao accountEntityDao;

	/**
	 * Sets reference to <code>accountEntityDao</code>.
	 */
	public void setAccountEntityDao (com.soffid.iam.base.model.AccountEntityDao accountEntityDao) {
		this.accountEntityDao = accountEntityDao;
	}

	/**
	 * Gets reference to <code>accountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountEntityDao getAccountEntityDao () {
		return accountEntityDao;
	}

	com.soffid.iam.base.model.AccountMetadataEntityDao accountMetadataEntityDao;

	/**
	 * Sets reference to <code>accountMetadataEntityDao</code>.
	 */
	public void setAccountMetadataEntityDao (com.soffid.iam.base.model.AccountMetadataEntityDao accountMetadataEntityDao) {
		this.accountMetadataEntityDao = accountMetadataEntityDao;
	}

	/**
	 * Gets reference to <code>accountMetadataEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountMetadataEntityDao getAccountMetadataEntityDao () {
		return accountMetadataEntityDao;
	}

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

	com.soffid.iam.base.service.AuthorizationService authorizationService;

	/**
	 * Sets reference to <code>authorizationService</code>.
	 */
	public void setAuthorizationService (com.soffid.iam.base.service.AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	/**
	 * Gets reference to <code>authorizationService</code>.
	 */
	public com.soffid.iam.base.service.AuthorizationService getAuthorizationService () {
		return authorizationService;
	}

	com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao;

	/**
	 * Sets reference to <code>metaDataEntityDao</code>.
	 */
	public void setMetaDataEntityDao (com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao) {
		this.metaDataEntityDao = metaDataEntityDao;
	}

	/**
	 * Gets reference to <code>metaDataEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MetaDataEntityDao getMetaDataEntityDao () {
		return metaDataEntityDao;
	}


	/**
	 * Operation findByName
	 * @param system
	 * @param account
	 * @param name
	 * @return
	**/
	public com.soffid.iam.base.model.AccountAttributeEntity findByName(
	    java.lang.String system, 
	    java.lang.String account, 
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, system, account, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.base.model.AccountAttributeEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String account, java.lang.String name)
	
	{
		return findByName("select att from com.soffid.iam.base.model.AccountAttributeEntity as att where att.account.name = :account and att.account.system.name = :system and (att.metadata.name = :name or att.systemMetadata.name = :name) and att.account.system.tenant.id=:tenantId",
			criteria, system, account, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.base.model.AccountAttributeEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String account, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("account", account, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.base.model.AccountAttributeEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.base.model.AccountAttributeEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.base.model.AccountAttributeEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByNameAndValue
	 * @param system
	 * @param name
	 * @param value
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity> findByNameAndValue(
	    java.lang.String system, 
	    java.lang.String name, 
	    java.lang.String value)
	
	{
		return findByNameAndValue((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, system, name, value);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity> findByNameAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String name, java.lang.String value)
	
	{
		return findByNameAndValue("select att from com.soffid.iam.base.model.AccountAttributeEntity as att where att.account.system.name = :system and (att.metadata.name = :name or att.systemMetadata.name = :name) and att.value = :value and att.account.system.tenant.id=:tenantId",
			criteria, system, name, value);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity> findByNameAndValue(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String name, java.lang.String value)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("value", value, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.AccountAttributeEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.UserData} object 
	 */
	public void toUserData(com.soffid.iam.base.model.AccountAttributeEntity source, com.soffid.iam.base.api.UserData target) {
		// Attributes for UserData
		// Missing attribute attribute on entity
		target.setValue(source.getValue());
		// Missing attribute user on entity
		// Missing attribute accountName on entity
		// Missing attribute systemName on entity
		target.setId(source.getId());
		// Incompatible types source.blobDataValue and target.blobDataValue
		// Missing attribute dateValue on entity
		// Missing attribute dataLabel on entity
		// Missing attribute visibility on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserData} object 
	 */
	public com.soffid.iam.base.api.UserData toUserData(com.soffid.iam.base.model.AccountAttributeEntity entity) {
		final com.soffid.iam.base.api.UserData target = new com.soffid.iam.base.api.UserData();
		this.toUserData(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserData} list 
	 */
	public java.util.List<com.soffid.iam.base.api.UserData> toUserDataList (java.util.Collection<com.soffid.iam.base.model.AccountAttributeEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.UserData> list =
				new java.util.LinkedList<com.soffid.iam.base.api.UserData>();
			for (final com.soffid.iam.base.model.AccountAttributeEntity instance: instances)
			{
				list.add( toUserData(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.UserData} object 
	 */
	public void userDataToEntity (com.soffid.iam.base.api.UserData source, com.soffid.iam.base.model.AccountAttributeEntity target, boolean copyIfNull) {
		// Attributes for AccountAttributeEntity
		if (copyIfNull || source.getValue() != null)
		{
			target.setValue(source.getValue());
		}
		// Missing attribute account on entity
		// Missing attribute systemMetadata on entity
		// Missing attribute metadata on entity
		if (copyIfNull || source.getBlobDataValue() != null)
		{
			// Incompatible types source.blobDataValue and target.blobDataValue
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserData} object 
	 */
	public com.soffid.iam.base.model.AccountAttributeEntity userDataToEntity (com.soffid.iam.base.api.UserData instance) {
		com.soffid.iam.base.model.AccountAttributeEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newAccountAttributeEntity();
		userDataToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserData} list 
	 */
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity>  userDataToEntityList (java.util.Collection<com.soffid.iam.base.api.UserData> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.AccountAttributeEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.AccountAttributeEntity>();
		for (com.soffid.iam.base.api.UserData instance: instances)
		{
			list.add (userDataToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.AccountAttributeEntity} .
	 */
	public com.soffid.iam.base.model.AccountAttributeEntity newAccountAttributeEntity()
	{
		return new com.soffid.iam.base.model.AccountAttributeEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.AccountAttributeEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.AccountAttributeEntity result = (com.soffid.iam.base.model.AccountAttributeEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.AccountAttributeEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.AccountAttributeEntity> result = (java.util.List<com.soffid.iam.base.model.AccountAttributeEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.AccountAttributeEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.AccountAttributeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.AccountAttributeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountAttributeEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.AccountAttributeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.AccountAttributeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountAttributeEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.AccountAttributeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccountAttributeEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.AccountAttributeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.AccountAttributeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountAttributeEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AccountAttributeEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.AccountAttributeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.AccountAttributeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountAttributeEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AccountAttributeEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.AccountAttributeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccountAttributeEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.AccountAttributeEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AccountAttributeEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.AccountAttributeEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.AccountAttributeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.AccountAttributeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
