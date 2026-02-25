//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity HostAliasEntity
 */
public abstract class HostAliasEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.HostAliasEntityDao
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


	/**
	 * Operation findAliasByHostName
	 * @param nomMaquina
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findAliasByHostName(
	    java.lang.String nomMaquina)
	
	{
		return findAliasByHostName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nomMaquina);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findAliasByHostName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nomMaquina)
	
	{
		return findAliasByHostName("select alias\nfrom com.soffid.iam.iga.model.HostAliasEntity alias where alias.host.name = :nomMaquina\norder by alias.alias",
			criteria, nomMaquina);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findAliasByHostName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nomMaquina)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nomMaquina", nomMaquina, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.HostAliasEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAliasByHostNameAndAlias
	 * @param nomMaquina
	 * @param alias
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findAliasByHostNameAndAlias(
	    java.lang.String nomMaquina, 
	    java.lang.String alias)
	
	{
		return findAliasByHostNameAndAlias((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nomMaquina, alias);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findAliasByHostNameAndAlias(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nomMaquina, java.lang.String alias)
	
	{
		return findAliasByHostNameAndAlias("select alias\nfrom com.soffid.iam.iga.model.HostAliasEntity alias where alias.host.name = :nomMaquina and alias.alias = :alias\norder by alias.alias",
			criteria, nomMaquina, alias);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findAliasByHostNameAndAlias(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nomMaquina, java.lang.String alias)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nomMaquina", nomMaquina, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("alias", alias, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.HostAliasEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findHostByAlias
	 * @param alias
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findHostByAlias(
	    java.lang.String alias)
	
	{
		return findHostByAlias((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, alias);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findHostByAlias(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String alias)
	
	{
		return findHostByAlias("select aliasM\nfrom com.soffid.iam.iga.model.HostAliasEntity aliasM where aliasM.alias like :alias",
			criteria, alias);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findHostByAlias(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String alias)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("alias", alias, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.HostAliasEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.HostAlias} object 
	 */
	public void toHostAlias(com.soffid.iam.iga.model.HostAliasEntity source, com.soffid.iam.am.api.HostAlias target) {
		// Attributes for HostAlias
		target.setId(source.getId());
		target.setAlias(source.getAlias());
		// Missing attribute hostId on entity
		// Missing attribute hostName on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.HostAlias} object 
	 */
	public com.soffid.iam.am.api.HostAlias toHostAlias(com.soffid.iam.iga.model.HostAliasEntity entity) {
		final com.soffid.iam.am.api.HostAlias target = new com.soffid.iam.am.api.HostAlias();
		this.toHostAlias(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.HostAlias} list 
	 */
	public java.util.List<com.soffid.iam.am.api.HostAlias> toHostAliasList (java.util.Collection<com.soffid.iam.iga.model.HostAliasEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.HostAlias> list =
				new java.util.LinkedList<com.soffid.iam.am.api.HostAlias>();
			for (final com.soffid.iam.iga.model.HostAliasEntity instance: instances)
			{
				list.add( toHostAlias(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.HostAlias} object 
	 */
	public void hostAliasToEntity (com.soffid.iam.am.api.HostAlias source, com.soffid.iam.iga.model.HostAliasEntity target, boolean copyIfNull) {
		// Attributes for HostAliasEntity
		if (copyIfNull || source.getAlias() != null)
		{
			target.setAlias(source.getAlias());
		}
		// Missing attribute host on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.HostAlias} object 
	 */
	public com.soffid.iam.iga.model.HostAliasEntity hostAliasToEntity (com.soffid.iam.am.api.HostAlias instance) {
		com.soffid.iam.iga.model.HostAliasEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newHostAliasEntity();
		hostAliasToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.HostAlias} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity>  hostAliasToEntityList (java.util.Collection<com.soffid.iam.am.api.HostAlias> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.HostAliasEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.HostAliasEntity>();
		for (com.soffid.iam.am.api.HostAlias instance: instances)
		{
			list.add (hostAliasToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.HostAliasEntity} .
	 */
	public com.soffid.iam.iga.model.HostAliasEntity newHostAliasEntity()
	{
		return new com.soffid.iam.iga.model.HostAliasEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.HostAliasEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.HostAliasEntity result = (com.soffid.iam.iga.model.HostAliasEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.HostAliasEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.HostAliasEntity> result = (java.util.List<com.soffid.iam.iga.model.HostAliasEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.HostAliasEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.HostAliasEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.HostAliasEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostAliasEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.HostAliasEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.HostAliasEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostAliasEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.HostAliasEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"HostAliasEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.HostAliasEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.HostAliasEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostAliasEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.HostAliasEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.HostAliasEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.HostAliasEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostAliasEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.HostAliasEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.HostAliasEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"HostAliasEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.HostAliasEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"HostAliasEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.HostAliasEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.HostAliasEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.HostAliasEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
