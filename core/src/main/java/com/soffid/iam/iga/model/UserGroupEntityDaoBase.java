//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity UserGroupEntity
 */
public abstract class UserGroupEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.UserGroupEntityDao
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

	com.soffid.iam.iga.model.RoleGroupEntityDao roleGroupEntityDao;

	/**
	 * Sets reference to <code>roleGroupEntityDao</code>.
	 */
	public void setRoleGroupEntityDao (com.soffid.iam.iga.model.RoleGroupEntityDao roleGroupEntityDao) {
		this.roleGroupEntityDao = roleGroupEntityDao;
	}

	/**
	 * Gets reference to <code>roleGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleGroupEntityDao getRoleGroupEntityDao () {
		return roleGroupEntityDao;
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

	com.soffid.iam.iga.model.UserGroupAttributeEntityDao userGroupAttributeEntityDao;

	/**
	 * Sets reference to <code>userGroupAttributeEntityDao</code>.
	 */
	public void setUserGroupAttributeEntityDao (com.soffid.iam.iga.model.UserGroupAttributeEntityDao userGroupAttributeEntityDao) {
		this.userGroupAttributeEntityDao = userGroupAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>userGroupAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserGroupAttributeEntityDao getUserGroupAttributeEntityDao () {
		return userGroupAttributeEntityDao;
	}


	/**
	 * Operation findByUserAndGroup
	 * @param userName
	 * @param groupName
	 * @return
	**/
	public com.soffid.iam.iga.model.UserGroupEntity findByUserAndGroup(
	    java.lang.String userName, 
	    java.lang.String groupName)
	
	{
		return findByUserAndGroup((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName, groupName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.UserGroupEntity findByUserAndGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String groupName)
	
	{
		return findByUserAndGroup("select usuariGrup from com.soffid.iam.iga.model.UserGroupEntity usuariGrup where usuariGrup.group.name = :groupName and usuariGrup.user.userName = :userName and usuariGrup.user.tenant.id = :tenantId and usuariGrup.disabled = false order by usuariGrup.user.userName, usuariGrup.group.name",
			criteria, userName, groupName);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.UserGroupEntity findByUserAndGroup(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String groupName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("groupName", groupName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.UserGroupEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.UserGroupEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.UserGroupEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation countByGroupName
	 * @param groupName
	 * @return
	**/
	public java.lang.Number countByGroupName(
	    java.lang.String groupName)
	
	{
		return countByGroupName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, groupName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Number countByGroupName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	
	{
		return countByGroupName("select count(usuariGrup.id) from com.soffid.iam.iga.model.UserGroupEntity usuariGrup where usuariGrup.group.name = :groupName and usuariGrup.disabled = false and usuariGrup.group.tenant.id = :tenantId ",
			criteria, groupName);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Number countByGroupName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("groupName", groupName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.lang.Number result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.Number' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.Number) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation countByGroupName
	 * @param groupName
	 * @param date
	 * @return
	**/
	public java.lang.Number countByGroupName(
	    java.lang.String groupName, 
	    java.util.Date date)
	
	{
		return countByGroupName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, groupName, date);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Number countByGroupName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName, java.util.Date date)
	
	{
		return countByGroupName("select count(usuariGrup.id) from com.soffid.iam.iga.model.UserGroupEntity usuariGrup where usuariGrup.group.name = :groupName and (usuariGrup.start <= :date or usuariGrup.start = null) and (usuariGrup.end > :date or usuariGrup.end = null) and usuariGrup.group.tenant.id = :tenantId ",
			criteria, groupName, date);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Number countByGroupName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName, java.util.Date date)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("groupName", groupName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("date", date, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.lang.Number result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.Number' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.Number) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByGroupName
	 * @param groupName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByGroupName(
	    java.lang.String groupName)
	
	{
		return findByGroupName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, groupName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByGroupName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	
	{
		return findByGroupName("select usuariGrup from com.soffid.iam.iga.model.UserGroupEntity usuariGrup where usuariGrup.group.name = :groupName and usuariGrup.disabled = false and usuariGrup.group.tenant.id = :tenantId order by usuariGrup.user.userName, usuariGrup.group.name",
			criteria, groupName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByGroupName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("groupName", groupName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.UserGroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByGroupName
	 * @param groupName
	 * @param date
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByGroupName(
	    java.lang.String groupName, 
	    java.util.Date date)
	
	{
		return findByGroupName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, groupName, date);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByGroupName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName, java.util.Date date)
	
	{
		return findByGroupName("select usuariGrup from com.soffid.iam.iga.model.UserGroupEntity usuariGrup where usuariGrup.group.name = :groupName and (usuariGrup.start <= :date or usuariGrup.start = null) and (usuariGrup.end > :date or usuariGrup.end = null) and usuariGrup.group.tenant.id = :tenantId order by usuariGrup.user.userName, usuariGrup.group.name",
			criteria, groupName, date);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByGroupName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName, java.util.Date date)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("groupName", groupName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("date", date, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.UserGroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByUserName(
	    java.lang.String userName)
	
	{
		return findByUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findByUserName("select usuariGrup from com.soffid.iam.iga.model.UserGroupEntity usuariGrup where usuariGrup.user.userName = :userName and usuariGrup.user.tenant.id = :tenantId and usuariGrup.disabled = false order by usuariGrup.user.userName, usuariGrup.group.name",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.UserGroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.UserGroup} object 
	 */
	public void toUserGroup(com.soffid.iam.iga.model.UserGroupEntity source, com.soffid.iam.iga.api.UserGroup target) {
		// Attributes for UserGroup
		// Incompatible types source.user and target.user
		// Incompatible types source.group and target.group
		// Missing attribute groupDescription on entity
		// Missing attribute groupId on entity
		target.setId(source.getId());
		// Missing attribute userId on entity
		// Missing attribute fullName on entity
		target.setStart(source.getStart());
		target.setEnd(source.getEnd());
		target.setDisabled(source.getDisabled());
		target.setPrimaryGroup(source.getPrimaryGroup());
		// Incompatible types source.attributes and target.attributes
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserGroup} object 
	 */
	public com.soffid.iam.iga.api.UserGroup toUserGroup(com.soffid.iam.iga.model.UserGroupEntity entity) {
		final com.soffid.iam.iga.api.UserGroup target = new com.soffid.iam.iga.api.UserGroup();
		this.toUserGroup(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserGroup} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.UserGroup> toUserGroupList (java.util.Collection<com.soffid.iam.iga.model.UserGroupEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.UserGroup> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.UserGroup>();
			for (final com.soffid.iam.iga.model.UserGroupEntity instance: instances)
			{
				list.add( toUserGroup(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.UserGroup} object 
	 */
	public void userGroupToEntity (com.soffid.iam.iga.api.UserGroup source, com.soffid.iam.iga.model.UserGroupEntity target, boolean copyIfNull) {
		// Attributes for UserGroupEntity
		if (copyIfNull || source.getUser() != null)
		{
			// Incompatible types source.user and target.user
		}
		if (copyIfNull || source.getGroup() != null)
		{
			// Incompatible types source.group and target.group
		}
		if (copyIfNull || source.getStart() != null)
		{
			target.setStart(source.getStart());
		}
		if (copyIfNull || source.getEnd() != null)
		{
			target.setEnd(source.getEnd());
		}
		if (copyIfNull || source.getDisabled() != null)
		{
			target.setDisabled(source.getDisabled());
		}
		if (copyIfNull || source.getPrimaryGroup() != null)
		{
			target.setPrimaryGroup(source.getPrimaryGroup());
		}
		if (copyIfNull || source.getAttributes() != null)
		{
			// Incompatible types source.attributes and target.attributes
		}
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
	 *  Transforms from {@link com.soffid.iam.iga.api.UserGroup} object 
	 */
	public com.soffid.iam.iga.model.UserGroupEntity userGroupToEntity (com.soffid.iam.iga.api.UserGroup instance) {
		com.soffid.iam.iga.model.UserGroupEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newUserGroupEntity();
		userGroupToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserGroup} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity>  userGroupToEntityList (java.util.Collection<com.soffid.iam.iga.api.UserGroup> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.UserGroupEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.UserGroupEntity>();
		for (com.soffid.iam.iga.api.UserGroup instance: instances)
		{
			list.add (userGroupToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.UserGroupEntity} .
	 */
	public com.soffid.iam.iga.model.UserGroupEntity newUserGroupEntity()
	{
		return new com.soffid.iam.iga.model.UserGroupEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.UserGroupEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.UserGroupEntity result = (com.soffid.iam.iga.model.UserGroupEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.UserGroupEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> loadAll() {
		java.util.List<com.soffid.iam.iga.model.UserGroupEntity> result = (java.util.List<com.soffid.iam.iga.model.UserGroupEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.iga.model.UserGroupEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.UserGroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.UserGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserGroupEntityDao.create - 'entity' can not be null");
		}

		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.UserGroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.UserGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserGroupEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.UserGroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"UserGroupEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.UserGroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.UserGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserGroupEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserGroupEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.UserGroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.UserGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserGroupEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserGroupEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.UserGroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"UserGroupEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.UserGroupEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"UserGroupEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.UserGroupEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.UserGroupEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.UserGroupEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
