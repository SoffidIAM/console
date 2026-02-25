//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity TranslatedLabelEntity
 */
public abstract class TranslatedLabelEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.TranslatedLabelEntityDao
{
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

	com.soffid.iam.iga.model.CustomObjectTypeEntityDao customObjectTypeEntityDao;

	/**
	 * Sets reference to <code>customObjectTypeEntityDao</code>.
	 */
	public void setCustomObjectTypeEntityDao (com.soffid.iam.iga.model.CustomObjectTypeEntityDao customObjectTypeEntityDao) {
		this.customObjectTypeEntityDao = customObjectTypeEntityDao;
	}

	/**
	 * Gets reference to <code>customObjectTypeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntityDao getCustomObjectTypeEntityDao () {
		return customObjectTypeEntityDao;
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
	 *  Copy data to {@link com.soffid.iam.base.api.TranslatedLabel} object 
	 */
	public void toTranslatedLabel(com.soffid.iam.iga.model.TranslatedLabelEntity source, com.soffid.iam.base.api.TranslatedLabel target) {
		// Attributes for TranslatedLabel
		target.setId(source.getId());
		target.setLanguage(source.getLanguage());
		target.setLabel(source.getLabel());
		// Incompatible types source.customObjectType and target.customObjectType
		// Incompatible types source.metadata and target.metadata
		// Incompatible types source.accountMetadata and target.accountMetadata
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.TranslatedLabel} object 
	 */
	public com.soffid.iam.base.api.TranslatedLabel toTranslatedLabel(com.soffid.iam.iga.model.TranslatedLabelEntity entity) {
		final com.soffid.iam.base.api.TranslatedLabel target = new com.soffid.iam.base.api.TranslatedLabel();
		this.toTranslatedLabel(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.TranslatedLabel} list 
	 */
	public java.util.List<com.soffid.iam.base.api.TranslatedLabel> toTranslatedLabelList (java.util.Collection<com.soffid.iam.iga.model.TranslatedLabelEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.TranslatedLabel> list =
				new java.util.LinkedList<com.soffid.iam.base.api.TranslatedLabel>();
			for (final com.soffid.iam.iga.model.TranslatedLabelEntity instance: instances)
			{
				list.add( toTranslatedLabel(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.TranslatedLabel} object 
	 */
	public void translatedLabelToEntity (com.soffid.iam.base.api.TranslatedLabel source, com.soffid.iam.iga.model.TranslatedLabelEntity target, boolean copyIfNull) {
		// Attributes for TranslatedLabelEntity
		if (copyIfNull || source.getLanguage() != null)
		{
			target.setLanguage(source.getLanguage());
		}
		if (copyIfNull || source.getLabel() != null)
		{
			target.setLabel(source.getLabel());
		}
		if (copyIfNull || source.getCustomObjectType() != null)
		{
			// Incompatible types source.customObjectType and target.customObjectType
		}
		if (copyIfNull || source.getMetadata() != null)
		{
			// Incompatible types source.metadata and target.metadata
		}
		if (copyIfNull || source.getAccountMetadata() != null)
		{
			// Incompatible types source.accountMetadata and target.accountMetadata
		}
		// Missing attribute tenant on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.TranslatedLabel} object 
	 */
	public com.soffid.iam.iga.model.TranslatedLabelEntity translatedLabelToEntity (com.soffid.iam.base.api.TranslatedLabel instance) {
		com.soffid.iam.iga.model.TranslatedLabelEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newTranslatedLabelEntity();
		translatedLabelToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.TranslatedLabel} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.TranslatedLabelEntity>  translatedLabelToEntityList (java.util.Collection<com.soffid.iam.base.api.TranslatedLabel> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.TranslatedLabelEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.TranslatedLabelEntity>();
		for (com.soffid.iam.base.api.TranslatedLabel instance: instances)
		{
			list.add (translatedLabelToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} .
	 */
	public com.soffid.iam.iga.model.TranslatedLabelEntity newTranslatedLabelEntity()
	{
		return new com.soffid.iam.iga.model.TranslatedLabelEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.TranslatedLabelEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.TranslatedLabelEntity result = (com.soffid.iam.iga.model.TranslatedLabelEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.TranslatedLabelEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.TranslatedLabelEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.TranslatedLabelEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.TranslatedLabelEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.TranslatedLabelEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TranslatedLabelEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.TranslatedLabelEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TranslatedLabelEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.TranslatedLabelEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"TranslatedLabelEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.TranslatedLabelEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TranslatedLabelEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.TranslatedLabelEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.TranslatedLabelEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TranslatedLabelEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.TranslatedLabelEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.TranslatedLabelEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"TranslatedLabelEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.TranslatedLabelEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"TranslatedLabelEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.TranslatedLabelEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.TranslatedLabelEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.TranslatedLabelEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.TranslatedLabelEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.TranslatedLabelEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
