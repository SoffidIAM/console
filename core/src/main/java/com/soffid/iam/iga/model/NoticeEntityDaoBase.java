//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity NoticeEntity
 */
public abstract class NoticeEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.NoticeEntityDao
{
	com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao;

	/**
	 * Sets reference to <code>informationSystemEntityDao</code>.
	 */
	public void setInformationSystemEntityDao (com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao) {
		this.informationSystemEntityDao = informationSystemEntityDao;
	}

	/**
	 * Gets reference to <code>informationSystemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.InformationSystemEntityDao getInformationSystemEntityDao () {
		return informationSystemEntityDao;
	}

	com.soffid.iam.iga.model.RoleEntityDao roleEntityDao;

	/**
	 * Sets reference to <code>roleEntityDao</code>.
	 */
	public void setRoleEntityDao (com.soffid.iam.iga.model.RoleEntityDao roleEntityDao) {
		this.roleEntityDao = roleEntityDao;
	}

	/**
	 * Gets reference to <code>roleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleEntityDao getRoleEntityDao () {
		return roleEntityDao;
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
	 * Operation findAll
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> findAll(
)
	
	{
		return findAll((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> findAll(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findAll("from com.soffid.iam.iga.model.NoticeEntity notifica order by notifica.modificationDate asc",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> findAll(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.NoticeEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByApplicationCode
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> findByApplicationCode(
	    java.lang.String informationSystem)
	
	{
		return findByApplicationCode((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, informationSystem);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> findByApplicationCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	
	{
		return findByApplicationCode("from com.soffid.iam.iga.model.NoticeEntity notifica where notifica.application.name = :informationSystem and notifica.application.tenant.id = :tenantId order by notifica.modificationDate asc",
			criteria, informationSystem);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> findByApplicationCode(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("informationSystem", informationSystem, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.NoticeEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.Notice} object 
	 */
	public void toNotice(com.soffid.iam.iga.model.NoticeEntity source, com.soffid.iam.iga.api.Notice target) {
		// Attributes for Notice
		// Missing attribute applicationName on entity
		// Missing attribute roleName on entity
		// Missing attribute userName on entity
		// Missing attribute userFullName on entity
		target.setInformation(source.getInformation());
		// Missing attribute assignmentDate on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Notice} object 
	 */
	public com.soffid.iam.iga.api.Notice toNotice(com.soffid.iam.iga.model.NoticeEntity entity) {
		final com.soffid.iam.iga.api.Notice target = new com.soffid.iam.iga.api.Notice();
		this.toNotice(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Notice} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.Notice> toNoticeList (java.util.Collection<com.soffid.iam.iga.model.NoticeEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.Notice> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.Notice>();
			for (final com.soffid.iam.iga.model.NoticeEntity instance: instances)
			{
				list.add( toNotice(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.Notice} object 
	 */
	public void noticeToEntity (com.soffid.iam.iga.api.Notice source, com.soffid.iam.iga.model.NoticeEntity target, boolean copyIfNull) {
		// Attributes for NoticeEntity
		// Missing attribute modificationDate on entity
		if (copyIfNull || source.getInformation() != null)
		{
			target.setInformation(source.getInformation());
		}
		// Missing attribute user on entity
		// Missing attribute role on entity
		// Missing attribute application on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Notice} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity>  noticeToEntityList (java.util.Collection<com.soffid.iam.iga.api.Notice> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.NoticeEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.NoticeEntity>();
		for (com.soffid.iam.iga.api.Notice instance: instances)
		{
			list.add (noticeToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.NoticeEntity} .
	 */
	public com.soffid.iam.iga.model.NoticeEntity newNoticeEntity()
	{
		return new com.soffid.iam.iga.model.NoticeEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.NoticeEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.NoticeEntity result = (com.soffid.iam.iga.model.NoticeEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.NoticeEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.NoticeEntity> result = (java.util.List<com.soffid.iam.iga.model.NoticeEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.NoticeEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.NoticeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.NoticeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NoticeEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.NoticeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.NoticeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NoticeEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.NoticeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"NoticeEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.NoticeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.NoticeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NoticeEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.NoticeEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.NoticeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.NoticeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NoticeEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.NoticeEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.NoticeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"NoticeEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.NoticeEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"NoticeEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.NoticeEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.NoticeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.NoticeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
