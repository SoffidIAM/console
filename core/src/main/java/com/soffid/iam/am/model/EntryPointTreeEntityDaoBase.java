//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity EntryPointTreeEntity
 */
public abstract class EntryPointTreeEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.EntryPointTreeEntityDao
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

	com.soffid.iam.am.model.EntryPointEntityDao entryPointEntityDao;

	/**
	 * Sets reference to <code>entryPointEntityDao</code>.
	 */
	public void setEntryPointEntityDao (com.soffid.iam.am.model.EntryPointEntityDao entryPointEntityDao) {
		this.entryPointEntityDao = entryPointEntityDao;
	}

	/**
	 * Gets reference to <code>entryPointEntityDao</code>.
	 */
	public com.soffid.iam.am.model.EntryPointEntityDao getEntryPointEntityDao () {
		return entryPointEntityDao;
	}


	/**
	 * Operation findByChildren
	 * @param childId
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> findByChildren(
	    java.lang.Long childId)
	
	{
		return findByChildren((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, childId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> findByChildren(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long childId)
	
	{
		return findByChildren("select arbre from com.soffid.iam.am.model.EntryPointTreeEntity arbre where arbre.child.id=:childId order by arbre.order",
			criteria, childId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> findByChildren(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long childId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("childId", childId, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByParent
	 * @param parentId
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> findByParent(
	    java.lang.Long parentId)
	
	{
		return findByParent((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, parentId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> findByParent(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long parentId)
	
	{
		return findByParent("select arbre from com.soffid.iam.am.model.EntryPointTreeEntity arbre where arbre.parent.id=:parentId order by arbre.order",
			criteria, parentId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> findByParent(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long parentId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("parentId", parentId, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.ApplicationAccessTree} object 
	 */
	public void toApplicationAccessTree(com.soffid.iam.am.model.EntryPointTreeEntity source, com.soffid.iam.am.api.ApplicationAccessTree target) {
		// Attributes for ApplicationAccessTree
		target.setId(source.getId());
		// Missing attribute ordre on entity
		// Missing attribute parentId on entity
		// Missing attribute parentName on entity
		// Missing attribute childId on entity
		// Missing attribute childName on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.ApplicationAccessTree} object 
	 */
	public com.soffid.iam.am.api.ApplicationAccessTree toApplicationAccessTree(com.soffid.iam.am.model.EntryPointTreeEntity entity) {
		final com.soffid.iam.am.api.ApplicationAccessTree target = new com.soffid.iam.am.api.ApplicationAccessTree();
		this.toApplicationAccessTree(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.ApplicationAccessTree} list 
	 */
	public java.util.List<com.soffid.iam.am.api.ApplicationAccessTree> toApplicationAccessTreeList (java.util.Collection<com.soffid.iam.am.model.EntryPointTreeEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.ApplicationAccessTree> list =
				new java.util.LinkedList<com.soffid.iam.am.api.ApplicationAccessTree>();
			for (final com.soffid.iam.am.model.EntryPointTreeEntity instance: instances)
			{
				list.add( toApplicationAccessTree(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.ApplicationAccessTree} object 
	 */
	public void applicationAccessTreeToEntity (com.soffid.iam.am.api.ApplicationAccessTree source, com.soffid.iam.am.model.EntryPointTreeEntity target, boolean copyIfNull) {
		// Attributes for EntryPointTreeEntity
		// Missing attribute order on entity
		// Missing attribute parent on entity
		// Missing attribute child on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.ApplicationAccessTree} object 
	 */
	public com.soffid.iam.am.model.EntryPointTreeEntity applicationAccessTreeToEntity (com.soffid.iam.am.api.ApplicationAccessTree instance) {
		com.soffid.iam.am.model.EntryPointTreeEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newEntryPointTreeEntity();
		applicationAccessTreeToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.ApplicationAccessTree} list 
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity>  applicationAccessTreeToEntityList (java.util.Collection<com.soffid.iam.am.api.ApplicationAccessTree> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.EntryPointTreeEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.EntryPointTreeEntity>();
		for (com.soffid.iam.am.api.ApplicationAccessTree instance: instances)
		{
			list.add (applicationAccessTreeToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.EntryPointTreeEntity} .
	 */
	public com.soffid.iam.am.model.EntryPointTreeEntity newEntryPointTreeEntity()
	{
		return new com.soffid.iam.am.model.EntryPointTreeEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.EntryPointTreeEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.EntryPointTreeEntity result = (com.soffid.iam.am.model.EntryPointTreeEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.EntryPointTreeEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> loadAll() {
		java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> result = (java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.am.model.EntryPointTreeEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.EntryPointTreeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.EntryPointTreeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointTreeEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.EntryPointTreeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.EntryPointTreeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointTreeEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.EntryPointTreeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"EntryPointTreeEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.EntryPointTreeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointTreeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointTreeEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointTreeEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.EntryPointTreeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointTreeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointTreeEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointTreeEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.EntryPointTreeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"EntryPointTreeEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.EntryPointTreeEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"EntryPointTreeEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.EntryPointTreeEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.EntryPointTreeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.EntryPointTreeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
