//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity MailListRoleMemberEntity
 */
public abstract class MailListRoleMemberEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.MailListRoleMemberEntityDao
{
	com.soffid.iam.iga.model.DomainValueEntityDao domainValueEntityDao;

	/**
	 * Sets reference to <code>domainValueEntityDao</code>.
	 */
	public void setDomainValueEntityDao (com.soffid.iam.iga.model.DomainValueEntityDao domainValueEntityDao) {
		this.domainValueEntityDao = domainValueEntityDao;
	}

	/**
	 * Gets reference to <code>domainValueEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.DomainValueEntityDao getDomainValueEntityDao () {
		return domainValueEntityDao;
	}

	com.soffid.iam.iga.model.GroupEntityDao groupEntityDao;

	/**
	 * Sets reference to <code>groupEntityDao</code>.
	 */
	public void setGroupEntityDao (com.soffid.iam.iga.model.GroupEntityDao groupEntityDao) {
		this.groupEntityDao = groupEntityDao;
	}

	/**
	 * Gets reference to <code>groupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupEntityDao getGroupEntityDao () {
		return groupEntityDao;
	}

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

	com.soffid.iam.iga.model.MailListEntityDao mailListEntityDao;

	/**
	 * Sets reference to <code>mailListEntityDao</code>.
	 */
	public void setMailListEntityDao (com.soffid.iam.iga.model.MailListEntityDao mailListEntityDao) {
		this.mailListEntityDao = mailListEntityDao;
	}

	/**
	 * Gets reference to <code>mailListEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListEntityDao getMailListEntityDao () {
		return mailListEntityDao;
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

	com.soffid.iam.sync.model.TaskEntityDao taskEntityDao;

	/**
	 * Sets reference to <code>taskEntityDao</code>.
	 */
	public void setTaskEntityDao (com.soffid.iam.sync.model.TaskEntityDao taskEntityDao) {
		this.taskEntityDao = taskEntityDao;
	}

	/**
	 * Gets reference to <code>taskEntityDao</code>.
	 */
	public com.soffid.iam.sync.model.TaskEntityDao getTaskEntityDao () {
		return taskEntityDao;
	}


	/**
	 * Operation findByMailListAndGroup
	 * @param mailListId
	 * @param roleId
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> findByMailListAndGroup(
	    long mailListId, 
	    long roleId)
	
	{
		return findByMailListAndGroup((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, mailListId, roleId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> findByMailListAndGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long mailListId, long roleId)
	
	{
		return findByMailListAndGroup("from com.soffid.iam.iga.model.MailListRoleMemberEntity e where e.mailList.id=:mailListId and e.role.id=:roleId and e.role.system.tenant.id=:tenantId",
			criteria, mailListId, roleId);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> findByMailListAndGroup(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long mailListId, long roleId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("mailListId", mailListId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("roleId", roleId, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.MailListRoleMember} object 
	 */
	public void toMailListRoleMember(com.soffid.iam.iga.model.MailListRoleMemberEntity source, com.soffid.iam.iga.api.MailListRoleMember target) {
		// Attributes for MailListRoleMember
		// Missing attribute roleName on entity
		// Missing attribute dispatcherName on entity
		// Missing attribute scope on entity
		// Missing attribute roleDescription on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailListRoleMember} object 
	 */
	public com.soffid.iam.iga.api.MailListRoleMember toMailListRoleMember(com.soffid.iam.iga.model.MailListRoleMemberEntity entity) {
		final com.soffid.iam.iga.api.MailListRoleMember target = new com.soffid.iam.iga.api.MailListRoleMember();
		this.toMailListRoleMember(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailListRoleMember} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.MailListRoleMember> toMailListRoleMemberList (java.util.Collection<com.soffid.iam.iga.model.MailListRoleMemberEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.MailListRoleMember> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.MailListRoleMember>();
			for (final com.soffid.iam.iga.model.MailListRoleMemberEntity instance: instances)
			{
				list.add( toMailListRoleMember(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.MailListRoleMember} object 
	 */
	public void mailListRoleMemberToEntity (com.soffid.iam.iga.api.MailListRoleMember source, com.soffid.iam.iga.model.MailListRoleMemberEntity target, boolean copyIfNull) {
		// Attributes for MailListRoleMemberEntity
		// Missing attribute mailList on entity
		// Missing attribute role on entity
		// Missing attribute informationSystemScope on entity
		// Missing attribute groupScope on entity
		// Missing attribute domainValueScope on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.MailListRoleMember} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListRoleMemberEntity>  mailListRoleMemberToEntityList (java.util.Collection<com.soffid.iam.iga.api.MailListRoleMember> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.MailListRoleMemberEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.MailListRoleMemberEntity>();
		for (com.soffid.iam.iga.api.MailListRoleMember instance: instances)
		{
			list.add (mailListRoleMemberToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} .
	 */
	public com.soffid.iam.iga.model.MailListRoleMemberEntity newMailListRoleMemberEntity()
	{
		return new com.soffid.iam.iga.model.MailListRoleMemberEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.MailListRoleMemberEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.MailListRoleMemberEntity result = (com.soffid.iam.iga.model.MailListRoleMemberEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.MailListRoleMemberEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListRoleMemberEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.MailListRoleMemberEntity> result = (java.util.List<com.soffid.iam.iga.model.MailListRoleMemberEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.MailListRoleMemberEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.MailListRoleMemberEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MailListRoleMemberEntityDao.create - 'entity' can not be null");
		}

		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.MailListRoleMemberEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MailListRoleMemberEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.MailListRoleMemberEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"MailListRoleMemberEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.MailListRoleMemberEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MailListRoleMemberEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MailListRoleMemberEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.MailListRoleMemberEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MailListRoleMemberEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MailListRoleMemberEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.MailListRoleMemberEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"MailListRoleMemberEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.MailListRoleMemberEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"MailListRoleMemberEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.MailListRoleMemberEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListRoleMemberEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.MailListRoleMemberEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailListRoleMemberEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.MailListRoleMemberEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.MailListRoleMemberEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
