//
// (c) 2014 Soffid
//
//
package com.soffid.iam.pam.model;
/**
 * DAO Base for Entity JumpServerEntity
 */
public abstract class JumpServerEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.pam.model.JumpServerEntityDao
{
	com.soffid.iam.pam.model.JumpServerGroupEntityDao jumpServerGroupEntityDao;

	/**
	 * Sets reference to <code>jumpServerGroupEntityDao</code>.
	 */
	public void setJumpServerGroupEntityDao (com.soffid.iam.pam.model.JumpServerGroupEntityDao jumpServerGroupEntityDao) {
		this.jumpServerGroupEntityDao = jumpServerGroupEntityDao;
	}

	/**
	 * Gets reference to <code>jumpServerGroupEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntityDao getJumpServerGroupEntityDao () {
		return jumpServerGroupEntityDao;
	}


	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.JumpServerEntity} .
	 */
	public com.soffid.iam.pam.model.JumpServerEntity newJumpServerEntity()
	{
		return new com.soffid.iam.pam.model.JumpServerEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.JumpServerEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.pam.model.JumpServerEntity result = (com.soffid.iam.pam.model.JumpServerEntity) this.getHibernateTemplate().get(com.soffid.iam.pam.model.JumpServerEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerEntity> loadAll() {
		java.util.List<com.soffid.iam.pam.model.JumpServerEntity> result = (java.util.List<com.soffid.iam.pam.model.JumpServerEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.pam.model.JumpServerEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.JumpServerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.JumpServerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"JumpServerEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.JumpServerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.JumpServerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"JumpServerEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.JumpServerEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"JumpServerEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.JumpServerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.JumpServerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"JumpServerEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.JumpServerEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.JumpServerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.JumpServerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"JumpServerEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.JumpServerEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.JumpServerEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"JumpServerEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.pam.model.JumpServerEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"JumpServerEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.pam.model.JumpServerEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.pam.model.JumpServerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.pam.model.JumpServerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.JumpServerEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.pam.model.JumpServerEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
