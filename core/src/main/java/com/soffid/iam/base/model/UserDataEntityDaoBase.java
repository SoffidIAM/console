//
// (c) 2014 Soffid
//
//
package com.soffid.iam.base.model;
/**
 * DAO Base for Entity UserDataEntity
 */
public abstract class UserDataEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.base.model.UserDataEntityDao
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

	com.soffid.iam.base.model.UserEntityDao userEntityDao;

	/**
	 * Sets reference to <code>userEntityDao</code>.
	 */
	public void setUserEntityDao (com.soffid.iam.base.model.UserEntityDao userEntityDao) {
		this.userEntityDao = userEntityDao;
	}

	/**
	 * Gets reference to <code>userEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserEntityDao getUserEntityDao () {
		return userEntityDao;
	}


	/**
	 * Operation findByUserAndAttribute
	 * @param userId
	 * @param attributes
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> findByUserAndAttribute(
	    java.lang.Long userId, 
	    java.lang.String[] attributes)
	
	{
		return findByUserAndAttribute((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userId, attributes);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> findByUserAndAttribute(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long userId, java.lang.String[] attributes)
	
	{
		return findByUserAndAttribute("select d from com.soffid.iam.base.model.UserDataEntity as d where d.user.id = :userId and d.dataType.name in (:attributes)",
			criteria, userId, attributes);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> findByUserAndAttribute(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long userId, java.lang.String[] attributes)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userId", userId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("attributes", attributes);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.UserDataEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByDataType
	 * @param userName
	 * @param dataType
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.UserDataEntity> findByDataType(
	    java.lang.String userName, 
	    java.lang.String dataType)
	
	{
		return findByDataType((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName, dataType);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserDataEntity> findByDataType(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String dataType)
	
	{
		return findByDataType("select dadaUsuari from com.soffid.iam.base.model.UserDataEntity as dadaUsuari left join dadaUsuari.user as user left join dadaUsuari.dataType as type where user.userName = :userName and type.name=:dataType and user.tenant.id = :tenantId",
			criteria, userName, dataType);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserDataEntity> findByDataType(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String dataType)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("dataType", dataType, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.base.model.UserDataEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByTypeAndValue
	 * @param dataType
	 * @param value
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> findByTypeAndValue(
	    java.lang.String dataType, 
	    java.lang.String value)
	
	{
		return findByTypeAndValue((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, dataType, value);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> findByTypeAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String dataType, java.lang.String value)
	
	{
		return findByTypeAndValue("select dadaUsuari from com.soffid.iam.base.model.UserDataEntity as dadaUsuari left join dadaUsuari.dataType as type where dadaUsuari.value = :value and type.name=:dataType and dadaUsuari.user.tenant.id = :tenantId",
			criteria, dataType, value);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> findByTypeAndValue(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String dataType, java.lang.String value)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("dataType", dataType, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("value", value, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.base.model.UserDataEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.UserData} object 
	 */
	public void toUserData(com.soffid.iam.base.model.UserDataEntity source, com.soffid.iam.base.api.UserData target) {
		// Attributes for UserData
		// Missing attribute attribute on entity
		target.setValue(source.getValue());
		// Incompatible types source.user and target.user
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
	public com.soffid.iam.base.api.UserData toUserData(com.soffid.iam.base.model.UserDataEntity entity) {
		final com.soffid.iam.base.api.UserData target = new com.soffid.iam.base.api.UserData();
		this.toUserData(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserData} list 
	 */
	public java.util.List<com.soffid.iam.base.api.UserData> toUserDataList (java.util.Collection<com.soffid.iam.base.model.UserDataEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.UserData> list =
				new java.util.LinkedList<com.soffid.iam.base.api.UserData>();
			for (final com.soffid.iam.base.model.UserDataEntity instance: instances)
			{
				list.add( toUserData(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.UserData} object 
	 */
	public void userDataToEntity (com.soffid.iam.base.api.UserData source, com.soffid.iam.base.model.UserDataEntity target, boolean copyIfNull) {
		// Attributes for UserDataEntity
		if (copyIfNull || source.getValue() != null)
		{
			target.setValue(source.getValue());
		}
		if (copyIfNull || source.getUser() != null)
		{
			// Incompatible types source.user and target.user
		}
		// Missing attribute dataType on entity
		if (copyIfNull || source.getBlobDataValue() != null)
		{
			// Incompatible types source.blobDataValue and target.blobDataValue
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserData} object 
	 */
	public com.soffid.iam.base.model.UserDataEntity userDataToEntity (com.soffid.iam.base.api.UserData instance) {
		com.soffid.iam.base.model.UserDataEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newUserDataEntity();
		userDataToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserData} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity>  userDataToEntityList (java.util.Collection<com.soffid.iam.base.api.UserData> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.base.model.UserDataEntity> list =
			new java.util.LinkedList<com.soffid.iam.base.model.UserDataEntity>();
		for (com.soffid.iam.base.api.UserData instance: instances)
		{
			list.add (userDataToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.UserDataEntity} .
	 */
	public com.soffid.iam.base.model.UserDataEntity newUserDataEntity()
	{
		return new com.soffid.iam.base.model.UserDataEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.UserDataEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.base.model.UserDataEntity result = (com.soffid.iam.base.model.UserDataEntity) this.getHibernateTemplate().get(com.soffid.iam.base.model.UserDataEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> loadAll() {
		java.util.List<com.soffid.iam.base.model.UserDataEntity> result = (java.util.List<com.soffid.iam.base.model.UserDataEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.base.model.UserDataEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.UserDataEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.UserDataEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserDataEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.UserDataEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.UserDataEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserDataEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.UserDataEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserDataEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.UserDataEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.UserDataEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserDataEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.UserDataEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.UserDataEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.UserDataEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserDataEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.UserDataEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.UserDataEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserDataEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.base.model.UserDataEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"UserDataEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.base.model.UserDataEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.base.model.UserDataEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.base.model.UserDataEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
