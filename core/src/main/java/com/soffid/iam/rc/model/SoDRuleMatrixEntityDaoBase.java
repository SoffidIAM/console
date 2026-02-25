//
// (c) 2014 Soffid
//
//
package com.soffid.iam.rc.model;
/**
 * DAO Base for Entity SoDRuleMatrixEntity
 */
public abstract class SoDRuleMatrixEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.rc.model.SoDRuleMatrixEntityDao
{
	com.soffid.iam.rc.model.SoDRoleEntityDao soDRoleEntityDao;

	/**
	 * Sets reference to <code>soDRoleEntityDao</code>.
	 */
	public void setSoDRoleEntityDao (com.soffid.iam.rc.model.SoDRoleEntityDao soDRoleEntityDao) {
		this.soDRoleEntityDao = soDRoleEntityDao;
	}

	/**
	 * Gets reference to <code>soDRoleEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.SoDRoleEntityDao getSoDRoleEntityDao () {
		return soDRoleEntityDao;
	}

	com.soffid.iam.rc.model.SoDRuleEntityDao soDRuleEntityDao;

	/**
	 * Sets reference to <code>soDRuleEntityDao</code>.
	 */
	public void setSoDRuleEntityDao (com.soffid.iam.rc.model.SoDRuleEntityDao soDRuleEntityDao) {
		this.soDRuleEntityDao = soDRuleEntityDao;
	}

	/**
	 * Gets reference to <code>soDRuleEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.SoDRuleEntityDao getSoDRuleEntityDao () {
		return soDRuleEntityDao;
	}


	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.SoDRuleMatrix} object 
	 */
	public void toSoDRuleMatrix(com.soffid.iam.rc.model.SoDRuleMatrixEntity source, com.soffid.iam.rc.api.SoDRuleMatrix target) {
		// Attributes for SoDRuleMatrix
		target.setId(source.getId());
		// Missing attribute ruleId on entity
		target.setRisk(source.getRisk());
		// Incompatible types source.row and target.row
		// Incompatible types source.column and target.column
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.SoDRuleMatrix} object 
	 */
	public com.soffid.iam.rc.api.SoDRuleMatrix toSoDRuleMatrix(com.soffid.iam.rc.model.SoDRuleMatrixEntity entity) {
		final com.soffid.iam.rc.api.SoDRuleMatrix target = new com.soffid.iam.rc.api.SoDRuleMatrix();
		this.toSoDRuleMatrix(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.SoDRuleMatrix} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.SoDRuleMatrix> toSoDRuleMatrixList (java.util.Collection<com.soffid.iam.rc.model.SoDRuleMatrixEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.rc.api.SoDRuleMatrix> list =
				new java.util.LinkedList<com.soffid.iam.rc.api.SoDRuleMatrix>();
			for (final com.soffid.iam.rc.model.SoDRuleMatrixEntity instance: instances)
			{
				list.add( toSoDRuleMatrix(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.SoDRuleMatrix} object 
	 */
	public void soDRuleMatrixToEntity (com.soffid.iam.rc.api.SoDRuleMatrix source, com.soffid.iam.rc.model.SoDRuleMatrixEntity target, boolean copyIfNull) {
		// Attributes for SoDRuleMatrixEntity
		// Missing attribute rule on entity
		if (copyIfNull || source.getRisk() != null)
		{
			target.setRisk(source.getRisk());
		}
		if (copyIfNull || source.getRow() != null)
		{
			// Incompatible types source.row and target.row
		}
		if (copyIfNull || source.getColumn() != null)
		{
			// Incompatible types source.column and target.column
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.SoDRuleMatrix} object 
	 */
	public com.soffid.iam.rc.model.SoDRuleMatrixEntity soDRuleMatrixToEntity (com.soffid.iam.rc.api.SoDRuleMatrix instance) {
		com.soffid.iam.rc.model.SoDRuleMatrixEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newSoDRuleMatrixEntity();
		soDRuleMatrixToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.SoDRuleMatrix} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleMatrixEntity>  soDRuleMatrixToEntityList (java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.rc.model.SoDRuleMatrixEntity> list =
			new java.util.LinkedList<com.soffid.iam.rc.model.SoDRuleMatrixEntity>();
		for (com.soffid.iam.rc.api.SoDRuleMatrix instance: instances)
		{
			list.add (soDRuleMatrixToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} .
	 */
	public com.soffid.iam.rc.model.SoDRuleMatrixEntity newSoDRuleMatrixEntity()
	{
		return new com.soffid.iam.rc.model.SoDRuleMatrixEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.SoDRuleMatrixEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.rc.model.SoDRuleMatrixEntity result = (com.soffid.iam.rc.model.SoDRuleMatrixEntity) this.getHibernateTemplate().get(com.soffid.iam.rc.model.SoDRuleMatrixEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleMatrixEntity> loadAll() {
		java.util.List<com.soffid.iam.rc.model.SoDRuleMatrixEntity> result = (java.util.List<com.soffid.iam.rc.model.SoDRuleMatrixEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.rc.model.SoDRuleMatrixEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.SoDRuleMatrixEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SoDRuleMatrixEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.SoDRuleMatrixEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SoDRuleMatrixEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.SoDRuleMatrixEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"SoDRuleMatrixEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRuleMatrixEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SoDRuleMatrixEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.SoDRuleMatrixEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRuleMatrixEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SoDRuleMatrixEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.SoDRuleMatrixEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRuleMatrixEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"SoDRuleMatrixEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.SoDRuleMatrixEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"SoDRuleMatrixEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.rc.model.SoDRuleMatrixEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleMatrixEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.rc.model.SoDRuleMatrixEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleMatrixEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.rc.model.SoDRuleMatrixEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
