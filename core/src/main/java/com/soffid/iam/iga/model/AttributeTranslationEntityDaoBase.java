//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity AttributeTranslationEntity
 */
public abstract class AttributeTranslationEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.AttributeTranslationEntityDao
{
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
	 * Operation findByColumn1
	 * @param domain
	 * @param column1
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByColumn1(
	    java.lang.String domain, 
	    java.lang.String column1)
	
	{
		return findByColumn1((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, domain, column1);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByColumn1(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String domain, java.lang.String column1)
	
	{
		return findByColumn1("from com.soffid.iam.iga.model.AttributeTranslationEntity where tenant.id=:tenantId and domain=:domain and column1=:column1",
			criteria, domain, column1);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByColumn1(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String domain, java.lang.String column1)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("domain", domain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("column1", column1, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByColumn2
	 * @param domain
	 * @param column2
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByColumn2(
	    java.lang.String domain, 
	    java.lang.String column2)
	
	{
		return findByColumn2((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, domain, column2);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByColumn2(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String domain, java.lang.String column2)
	
	{
		return findByColumn2("from com.soffid.iam.iga.model.AttributeTranslationEntity where tenant.id=:tenantId and domain=:domain and column2=:column2",
			criteria, domain, column2);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByColumn2(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String domain, java.lang.String column2)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("domain", domain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("column2", column2, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByExample
	 * @param domain
	 * @param column1
	 * @param column2
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByExample(
	    java.lang.String domain, 
	    java.lang.String column1, 
	    java.lang.String column2)
	
	{
		return findByExample((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, domain, column1, column2);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByExample(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String domain, java.lang.String column1, java.lang.String column2)
	
	{
		return findByExample("select ate from com.soffid.iam.iga.model.AttributeTranslationEntity as ate where (ate.domain like :domain or :domain is null) and (ate.column1 like :column1 or :column1 is null) and(ate.column2 like :column2 or :column2 is null) and\nate.tenant.id = :tenantId order by ate.domain, ate.column1",
			criteria, domain, column1, column2);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByExample(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String domain, java.lang.String column1, java.lang.String column2)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("domain", domain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("column1", column1, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("column2", column2, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.AttributeTranslation} object 
	 */
	public void toAttributeTranslation(com.soffid.iam.iga.model.AttributeTranslationEntity source, com.soffid.iam.iga.api.AttributeTranslation target) {
		// Attributes for AttributeTranslation
		target.setId(source.getId());
		target.setDomain(source.getDomain());
		target.setColumn1(source.getColumn1());
		target.setColumn2(source.getColumn2());
		target.setColumn3(source.getColumn3());
		target.setColumn4(source.getColumn4());
		target.setColumn5(source.getColumn5());
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.AttributeTranslation} object 
	 */
	public com.soffid.iam.iga.api.AttributeTranslation toAttributeTranslation(com.soffid.iam.iga.model.AttributeTranslationEntity entity) {
		final com.soffid.iam.iga.api.AttributeTranslation target = new com.soffid.iam.iga.api.AttributeTranslation();
		this.toAttributeTranslation(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.AttributeTranslation} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.AttributeTranslation> toAttributeTranslationList (java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.AttributeTranslation> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.AttributeTranslation>();
			for (final com.soffid.iam.iga.model.AttributeTranslationEntity instance: instances)
			{
				list.add( toAttributeTranslation(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.AttributeTranslation} object 
	 */
	public void attributeTranslationToEntity (com.soffid.iam.iga.api.AttributeTranslation source, com.soffid.iam.iga.model.AttributeTranslationEntity target, boolean copyIfNull) {
		// Attributes for AttributeTranslationEntity
		if (copyIfNull || source.getDomain() != null)
		{
			target.setDomain(source.getDomain());
		}
		if (copyIfNull || source.getColumn1() != null)
		{
			target.setColumn1(source.getColumn1());
		}
		if (copyIfNull || source.getColumn2() != null)
		{
			target.setColumn2(source.getColumn2());
		}
		if (copyIfNull || source.getColumn3() != null)
		{
			target.setColumn3(source.getColumn3());
		}
		if (copyIfNull || source.getColumn4() != null)
		{
			target.setColumn4(source.getColumn4());
		}
		if (copyIfNull || source.getColumn5() != null)
		{
			target.setColumn5(source.getColumn5());
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
	 *  Transforms from {@link com.soffid.iam.iga.api.AttributeTranslation} object 
	 */
	public com.soffid.iam.iga.model.AttributeTranslationEntity attributeTranslationToEntity (com.soffid.iam.iga.api.AttributeTranslation instance) {
		com.soffid.iam.iga.model.AttributeTranslationEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newAttributeTranslationEntity();
		attributeTranslationToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.AttributeTranslation} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeTranslationEntity>  attributeTranslationToEntityList (java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.AttributeTranslationEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.AttributeTranslationEntity>();
		for (com.soffid.iam.iga.api.AttributeTranslation instance: instances)
		{
			list.add (attributeTranslationToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} .
	 */
	public com.soffid.iam.iga.model.AttributeTranslationEntity newAttributeTranslationEntity()
	{
		return new com.soffid.iam.iga.model.AttributeTranslationEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.AttributeTranslationEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.AttributeTranslationEntity result = (com.soffid.iam.iga.model.AttributeTranslationEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.AttributeTranslationEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeTranslationEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.AttributeTranslationEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.AttributeTranslationEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.AttributeTranslationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AttributeTranslationEntityDao.create - 'entity' can not be null");
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
	 * Updates an instance of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.AttributeTranslationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AttributeTranslationEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.AttributeTranslationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AttributeTranslationEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.AttributeTranslationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AttributeTranslationEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AttributeTranslationEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.AttributeTranslationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AttributeTranslationEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AttributeTranslationEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.AttributeTranslationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AttributeTranslationEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.AttributeTranslationEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AttributeTranslationEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.AttributeTranslationEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeTranslationEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.AttributeTranslationEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeTranslationEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.AttributeTranslationEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
