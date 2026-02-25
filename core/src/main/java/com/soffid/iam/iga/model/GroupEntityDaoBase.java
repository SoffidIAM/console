//
// (c) 2014 Soffid
//
//
package com.soffid.iam.iga.model;
/**
 * DAO Base for Entity GroupEntity
 */
public abstract class GroupEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.iga.model.GroupEntityDao
{
	com.soffid.iam.pam.model.AccountAccessEntityDao accountAccessEntityDao;

	/**
	 * Sets reference to <code>accountAccessEntityDao</code>.
	 */
	public void setAccountAccessEntityDao (com.soffid.iam.pam.model.AccountAccessEntityDao accountAccessEntityDao) {
		this.accountAccessEntityDao = accountAccessEntityDao;
	}

	/**
	 * Gets reference to <code>accountAccessEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.AccountAccessEntityDao getAccountAccessEntityDao () {
		return accountAccessEntityDao;
	}

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

	com.soffid.iam.iga.model.GroupAttributeEntityDao groupAttributeEntityDao;

	/**
	 * Sets reference to <code>groupAttributeEntityDao</code>.
	 */
	public void setGroupAttributeEntityDao (com.soffid.iam.iga.model.GroupAttributeEntityDao groupAttributeEntityDao) {
		this.groupAttributeEntityDao = groupAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>groupAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupAttributeEntityDao getGroupAttributeEntityDao () {
		return groupAttributeEntityDao;
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

	com.soffid.iam.iga.model.GroupTypeEntityDao groupTypeEntityDao;

	/**
	 * Sets reference to <code>groupTypeEntityDao</code>.
	 */
	public void setGroupTypeEntityDao (com.soffid.iam.iga.model.GroupTypeEntityDao groupTypeEntityDao) {
		this.groupTypeEntityDao = groupTypeEntityDao;
	}

	/**
	 * Gets reference to <code>groupTypeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupTypeEntityDao getGroupTypeEntityDao () {
		return groupTypeEntityDao;
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

	com.soffid.iam.am.model.NetworkAuthorizationEntityDao networkAuthorizationEntityDao;

	/**
	 * Sets reference to <code>networkAuthorizationEntityDao</code>.
	 */
	public void setNetworkAuthorizationEntityDao (com.soffid.iam.am.model.NetworkAuthorizationEntityDao networkAuthorizationEntityDao) {
		this.networkAuthorizationEntityDao = networkAuthorizationEntityDao;
	}

	/**
	 * Gets reference to <code>networkAuthorizationEntityDao</code>.
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntityDao getNetworkAuthorizationEntityDao () {
		return networkAuthorizationEntityDao;
	}

	com.soffid.iam.iga.model.PrinterGroupEntityDao printerGroupEntityDao;

	/**
	 * Sets reference to <code>printerGroupEntityDao</code>.
	 */
	public void setPrinterGroupEntityDao (com.soffid.iam.iga.model.PrinterGroupEntityDao printerGroupEntityDao) {
		this.printerGroupEntityDao = printerGroupEntityDao;
	}

	/**
	 * Gets reference to <code>printerGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.PrinterGroupEntityDao getPrinterGroupEntityDao () {
		return printerGroupEntityDao;
	}

	com.soffid.iam.iga.model.RoleAccountEntityDao roleAccountEntityDao;

	/**
	 * Sets reference to <code>roleAccountEntityDao</code>.
	 */
	public void setRoleAccountEntityDao (com.soffid.iam.iga.model.RoleAccountEntityDao roleAccountEntityDao) {
		this.roleAccountEntityDao = roleAccountEntityDao;
	}

	/**
	 * Gets reference to <code>roleAccountEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleAccountEntityDao getRoleAccountEntityDao () {
		return roleAccountEntityDao;
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

	com.soffid.iam.iga.model.SystemGroupEntityDao systemGroupEntityDao;

	/**
	 * Sets reference to <code>systemGroupEntityDao</code>.
	 */
	public void setSystemGroupEntityDao (com.soffid.iam.iga.model.SystemGroupEntityDao systemGroupEntityDao) {
		this.systemGroupEntityDao = systemGroupEntityDao;
	}

	/**
	 * Gets reference to <code>systemGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.SystemGroupEntityDao getSystemGroupEntityDao () {
		return systemGroupEntityDao;
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

	com.soffid.iam.iga.model.UserGroupEntityDao userGroupEntityDao;

	/**
	 * Sets reference to <code>userGroupEntityDao</code>.
	 */
	public void setUserGroupEntityDao (com.soffid.iam.iga.model.UserGroupEntityDao userGroupEntityDao) {
		this.userGroupEntityDao = userGroupEntityDao;
	}

	/**
	 * Gets reference to <code>userGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserGroupEntityDao getUserGroupEntityDao () {
		return userGroupEntityDao;
	}

	com.soffid.iam.iga.model.MailListGroupMemberEntityDao mailListGroupMemberEntityDao;

	/**
	 * Sets reference to <code>mailListGroupMemberEntityDao</code>.
	 */
	public void setMailListGroupMemberEntityDao (com.soffid.iam.iga.model.MailListGroupMemberEntityDao mailListGroupMemberEntityDao) {
		this.mailListGroupMemberEntityDao = mailListGroupMemberEntityDao;
	}

	/**
	 * Gets reference to <code>mailListGroupMemberEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListGroupMemberEntityDao getMailListGroupMemberEntityDao () {
		return mailListGroupMemberEntityDao;
	}

	com.soffid.iam.iga.model.MailListRoleMemberEntityDao mailListRoleMemberEntityDao;

	/**
	 * Sets reference to <code>mailListRoleMemberEntityDao</code>.
	 */
	public void setMailListRoleMemberEntityDao (com.soffid.iam.iga.model.MailListRoleMemberEntityDao mailListRoleMemberEntityDao) {
		this.mailListRoleMemberEntityDao = mailListRoleMemberEntityDao;
	}

	/**
	 * Gets reference to <code>mailListRoleMemberEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListRoleMemberEntityDao getMailListRoleMemberEntityDao () {
		return mailListRoleMemberEntityDao;
	}


	protected org.apache.commons.collections.map.LRUMap mapGroup = new org.apache.commons.collections.map.LRUMap(300);
	protected int mapGroupTimeout = 5000;
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.GroupEntity findByName(
	    java.lang.String name)
	
	{
		return findByName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.GroupEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByName("from com.soffid.iam.iga.model.GroupEntity grup where grup.name = :name and grup.tenant.id = :tenantId and grup.obsolete is false order by grup.name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.GroupEntity findByName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.GroupEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.GroupEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.GroupEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByNameAndDate
	 * @param name
	 * @param d
	 * @return
	**/
	public com.soffid.iam.iga.model.GroupEntity findByNameAndDate(
	    java.lang.String name, 
	    java.util.Date d)
	
	{
		return findByNameAndDate((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name, d);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.GroupEntity findByNameAndDate(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.util.Date d)
	
	{
		return findByNameAndDate("from com.soffid.iam.iga.model.GroupEntity grup where grup.name = :name and grup.tenant.id = :tenantId and grup.obsolete is false and (grup.startDate <= :d or grup.startDate = null) and (grup.endDate = null or grup.endDate > :d) order by grup.name",
			criteria, name, d);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.GroupEntity findByNameAndDate(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.util.Date d)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("d", d, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.GroupEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.GroupEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.GroupEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByNameDeleted
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.GroupEntity findByNameDeleted(
	    java.lang.String name)
	
	{
		return findByNameDeleted((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, name);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.GroupEntity findByNameDeleted(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		return findByNameDeleted("from com.soffid.iam.iga.model.GroupEntity grup where grup.name = :name and grup.tenant.id = :tenantId order by grup.name",
			criteria, name);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.GroupEntity findByNameDeleted(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("name", name, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.GroupEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.GroupEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.GroupEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findPrimaryGroupByUser
	 * @param userName
	 * @return
	**/
	public com.soffid.iam.iga.model.GroupEntity findPrimaryGroupByUser(
	    java.lang.String userName)
	
	{
		return findPrimaryGroupByUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.GroupEntity findPrimaryGroupByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findPrimaryGroupByUser("select usuari.primaryGroup from com.soffid.iam.base.model.UserEntity usuari where usuari.userName = :userName and usuari.tenant.id = :tenantId",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.GroupEntity findPrimaryGroupByUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
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
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.iga.model.GroupEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.GroupEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.GroupEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByChild
	 * @param groupName
	 * @return
	**/
	public com.soffid.iam.iga.model.GroupEntity findByChild(
	    java.lang.String groupName)
	
	{
		return findByChild((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, groupName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.iga.model.GroupEntity findByChild(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	
	{
		return findByChild("select grup.parent from com.soffid.iam.iga.model.GroupEntity grup where grup.name = :groupName and grup.obsolete is false and grup.tenant.id = :tenantId",
			criteria, groupName);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.iga.model.GroupEntity findByChild(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	
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
			com.soffid.iam.iga.model.GroupEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.iga.model.GroupEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.iga.model.GroupEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByText
	 * @param text
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.GroupEntity> findByText(
	    java.lang.String text)
	
	{
		return findByText((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, text);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.GroupEntity> findByText(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String text)
	
	{
		return findByText("from com.soffid.iam.iga.model.GroupEntity   where :text is null",
			criteria, text);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.iga.model.GroupEntity> findByText(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String text)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("text", text, org.hibernate.Hibernate.STRING);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.iga.model.GroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findGroupNames
	 * @return
	**/
	public java.util.Collection<java.lang.String> findGroupNames(
)
	
	{
		return findGroupNames((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<java.lang.String> findGroupNames(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findGroupNames("select g.name from com.soffid.iam.iga.model.GroupEntity as g where g.obsolete is false",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<java.lang.String> findGroupNames(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
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
			return (java.util.Collection<java.lang.String>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByParent
	 * @param parent
	 * @param d
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByParent(
	    java.lang.String parent, 
	    java.util.Date d)
	
	{
		return findByParent((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, parent, d);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByParent(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String parent, java.util.Date d)
	
	{
		return findByParent("from com.soffid.iam.iga.model.GroupEntity grup where grup.parent.name = :parent and grup.tenant.id = :tenantId and (grup.startDate <= :d or grup.startDate = null) and (grup.endDate = null or grup.endDate > :d) order by grup.name",
			criteria, parent, d);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByParent(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String parent, java.util.Date d)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("parent", parent, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("d", d, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.GroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByType
	 * @param unitType
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByType(
	    java.lang.String unitType)
	
	{
		return findByType((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, unitType);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByType(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String unitType)
	
	{
		return findByType("from com.soffid.iam.iga.model.GroupEntity grup where grup.unitType.name = :unitType and grup.unitType.tenant.id = :tenantId and grup.obsolete is false order by grup.name",
			criteria, unitType);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByType(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String unitType)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("unitType", unitType, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.GroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByGrantedRolesToUser
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByGrantedRolesToUser(
	    java.lang.String userName)
	
	{
		return findByGrantedRolesToUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByGrantedRolesToUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findByGrantedRolesToUser("select grup from com.soffid.iam.base.model.UserEntity usu join usu.accounts as accounts join accounts.account as account with account.type='U' join account.roles as roles join roles.group as grup where usu.userName=:userName and usu.tenant.id = :tenantId order by grup.name\n",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByGrantedRolesToUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
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
			return (java.util.List<com.soffid.iam.iga.model.GroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findGroupsByUser
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findGroupsByUser(
	    java.lang.String userName)
	
	{
		return findGroupsByUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findGroupsByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
	{
		return findGroupsByUser("select  usuariGrup.group from com.soffid.iam.iga.model.UserGroupEntity usuariGrup where usuariGrup.user.userName = :userName and usuariGrup.user.tenant.id = :tenantId",
			criteria, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findGroupsByUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	
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
			return (java.util.List<com.soffid.iam.iga.model.GroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByParent
	 * @param parent
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByParent(
	    java.lang.String parent)
	
	{
		return findByParent((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, parent);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByParent(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String parent)
	
	{
		return findByParent("from com.soffid.iam.iga.model.GroupEntity grup where grup.parent.name = :parent and grup.tenant.id = :tenantId and grup.obsolete is false order by grup.name",
			criteria, parent);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> findByParent(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String parent)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("parent", parent, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.iga.model.GroupEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * @see com.soffid.iam.iga.model.GroupEntity#	 * @see com.soffid.iam.iga.model.GroupEntity#void setParentGroup(java.lang.String codiSubGrup, java.lang.String codiSuperGrup)
	 */
	public void setParentGroup(
		java.lang.String codiSubGrup, 
		java.lang.String codiSuperGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiSubGrup == null || codiSubGrup.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.GroupEntity.setParentGroup(java.lang.String codiSubGrup, java.lang.String codiSuperGrup) - codiSubGrup cannot be null");
		}
		if (codiSuperGrup == null || codiSuperGrup.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.model.GroupEntity.setParentGroup(java.lang.String codiSubGrup, java.lang.String codiSuperGrup) - codiSuperGrup cannot be null");
		}
		try
		{
			handleSetParentGroup(codiSubGrup, codiSuperGrup);
		}
		catch (com.soffid.iam.exception.InternalErrorException __internalException)
		{
			throw __internalException;
		}
		catch (Throwable th)
		{
			org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.model.GroupEntity.class).
				warn ("Error on GroupEntity.setParentGroup", th);
			throw new com.soffid.iam.exception.InternalErrorException(
				"Error on GroupEntity.setParentGroup: "+th.toString(), th);
		}
	}

	protected abstract void handleSetParentGroup(java.lang.String codiSubGrup, java.lang.String codiSuperGrup) throws Exception;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public void toDomainValue(com.soffid.iam.iga.model.GroupEntity source, com.soffid.iam.iga.api.DomainValue target) {
		// Attributes for DomainValue
		target.setId(source.getId());
		// Missing attribute value on entity
		target.setDescription(source.getDescription());
		// Missing attribute domainName on entity
		// Missing attribute informationSystemName on entity
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public com.soffid.iam.iga.api.DomainValue toDomainValue(com.soffid.iam.iga.model.GroupEntity entity) {
		final com.soffid.iam.iga.api.DomainValue target = new com.soffid.iam.iga.api.DomainValue();
		this.toDomainValue(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.DomainValue} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.DomainValue> toDomainValueList (java.util.Collection<com.soffid.iam.iga.model.GroupEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.DomainValue> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.DomainValue>();
			for (final com.soffid.iam.iga.model.GroupEntity instance: instances)
			{
				list.add( toDomainValue(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public void domainValueToEntity (com.soffid.iam.iga.api.DomainValue source, com.soffid.iam.iga.model.GroupEntity target, boolean copyIfNull) {
		// Attributes for GroupEntity
		// Missing attribute name on entity
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		// Missing attribute quota on entity
		// Missing attribute driveLetter on entity
		// Missing attribute parent on entity
		// Missing attribute printers on entity
		// Missing attribute secondaryGroupUsers on entity
		// Missing attribute driveServer on entity
		// Missing attribute networkAuthorization on entity
		// Missing attribute children on entity
		// Missing attribute usersRoles on entity
		// Missing attribute unitType on entity
		// Missing attribute obsolete on entity
		// Missing attribute startDate on entity
		// Missing attribute endDate on entity
		// Missing attribute tenant on entity
		// Missing attribute systemGroup on entity
		// Missing attribute grantedRoles on entity
		// Missing attribute primaryGroupUsers on entity
		// Missing attribute accountAccess on entity
		// Missing attribute holdedRoleAssignments on entity
		// Missing attribute attributes on entity
		// Missing attribute mailLists on entity
		// Missing attribute roleScopeMailLists on entity
		// Missing attribute audit on entity
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
	 *  Transforms from {@link com.soffid.iam.iga.api.DomainValue} object 
	 */
	public com.soffid.iam.iga.model.GroupEntity domainValueToEntity (com.soffid.iam.iga.api.DomainValue instance) {
		com.soffid.iam.iga.model.GroupEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newGroupEntity();
		domainValueToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.DomainValue} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity>  domainValueToEntityList (java.util.Collection<com.soffid.iam.iga.api.DomainValue> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.GroupEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.GroupEntity>();
		for (com.soffid.iam.iga.api.DomainValue instance: instances)
		{
			list.add (domainValueToEntity(instance));
		}
		return list;
	}

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.Group} object 
	 */
	public void toGroup(com.soffid.iam.iga.model.GroupEntity source, com.soffid.iam.iga.api.Group target) {
		// Attributes for Group
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		// Incompatible types source.quota and target.quota
		target.setDriveLetter(source.getDriveLetter());
		// Missing attribute parentGroup on entity
		// Missing attribute type on entity
		// Missing attribute driveServerName on entity
		target.setObsolete(source.getObsolete());
		target.setStartDate(source.getStartDate());
		target.setEndDate(source.getEndDate());
		// Incompatible types source.attributes and target.attributes
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Group} object 
	 */
	/**
	 *  Stores {@link com.soffid.iam.iga.api.Group} in cache 
	 */
	protected synchronized void storeGroupCacheEntry (java.lang.Long id, com.soffid.iam.iga.api.Group group)
	{
		GroupCacheEntry entry = new GroupCacheEntry ();
		entry.group = new com.soffid.iam.iga.api.Group(group);
		entry.timeStamp = System.currentTimeMillis();
		mapGroup.put(com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id, entry);
	}

	/**
	 *  Retrieves {@link com.soffid.iam.iga.api.Group} from cache 
	 */
	protected synchronized com.soffid.iam.iga.api.Group getGroupCacheEntry (java.lang.Long id)
	{
		GroupCacheEntry entry = (GroupCacheEntry) mapGroup.get (com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id);
		if (entry == null) return null;
		if (entry.timeStamp + mapGroupTimeout < System.currentTimeMillis())
		{
			mapGroup.remove (com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id);
			return null;
		}
		return new com.soffid.iam.iga.api.Group(entry.group);
	}

	/**
	 *  Removes {@link com.soffid.iam.iga.api.Group} from cache 
	 */
	protected synchronized void removeGroupCacheEntry (java.lang.Long id)
	{
		mapGroup.remove (com.soffid.iam.utils.Security.getCurrentTenantName()+":"+id);
	}

	public com.soffid.iam.iga.api.Group toGroup(com.soffid.iam.iga.model.GroupEntity entity) {
		com.soffid.iam.iga.api.Group target = es.caib.seycon.ng.utils.Security.isSyncServer() ? 
			null : 
			getGroupCacheEntry(entity.getId());
		if (target != null)
			return target;
		else
		{
			target = new com.soffid.iam.iga.api.Group();
			this.toGroup(entity, target);
			if (!es.caib.seycon.ng.utils.Security.isSyncServer() )
				storeGroupCacheEntry(entity.getId(), target);
			return target;
		}
	}

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Group} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.Group> toGroupList (java.util.Collection<com.soffid.iam.iga.model.GroupEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.iga.api.Group> list =
				new java.util.LinkedList<com.soffid.iam.iga.api.Group>();
			for (final com.soffid.iam.iga.model.GroupEntity instance: instances)
			{
				list.add( toGroup(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.Group} object 
	 */
	public void groupToEntity (com.soffid.iam.iga.api.Group source, com.soffid.iam.iga.model.GroupEntity target, boolean copyIfNull) {
		// Attributes for GroupEntity
		if (copyIfNull || source.getName() != null)
		{
			target.setName(source.getName());
		}
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getQuota() != null)
		{
			// Incompatible types source.quota and target.quota
		}
		if (copyIfNull || source.getDriveLetter() != null)
		{
			target.setDriveLetter(source.getDriveLetter());
		}
		// Missing attribute parent on entity
		// Missing attribute printers on entity
		// Missing attribute secondaryGroupUsers on entity
		// Missing attribute driveServer on entity
		// Missing attribute networkAuthorization on entity
		// Missing attribute children on entity
		// Missing attribute usersRoles on entity
		// Missing attribute unitType on entity
		if (copyIfNull || source.getObsolete() != null)
		{
			target.setObsolete(source.getObsolete());
		}
		if (copyIfNull || source.getStartDate() != null)
		{
			target.setStartDate(source.getStartDate());
		}
		if (copyIfNull || source.getEndDate() != null)
		{
			target.setEndDate(source.getEndDate());
		}
		// Missing attribute tenant on entity
		// Missing attribute systemGroup on entity
		// Missing attribute grantedRoles on entity
		// Missing attribute primaryGroupUsers on entity
		// Missing attribute accountAccess on entity
		// Missing attribute holdedRoleAssignments on entity
		if (copyIfNull || source.getAttributes() != null)
		{
			// Incompatible types source.attributes and target.attributes
		}
		// Missing attribute mailLists on entity
		// Missing attribute roleScopeMailLists on entity
		// Missing attribute audit on entity
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
	 *  Transforms from {@link com.soffid.iam.iga.api.Group} object 
	 */
	public com.soffid.iam.iga.model.GroupEntity groupToEntity (com.soffid.iam.iga.api.Group instance) {
		com.soffid.iam.iga.model.GroupEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newGroupEntity();
		groupToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Group} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity>  groupToEntityList (java.util.Collection<com.soffid.iam.iga.api.Group> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.GroupEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.GroupEntity>();
		for (com.soffid.iam.iga.api.Group instance: instances)
		{
			list.add (groupToEntity(instance));
		}
		return list;
	}

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.Identity} object 
	 */
	public void toIdentity(com.soffid.iam.iga.model.GroupEntity source, com.soffid.iam.base.api.Identity target) {
		// Attributes for Identity
		// Missing attribute userCode on entity
		// Missing attribute groupCode on entity
		// Missing attribute roleName on entity
		target.setDescription(source.getDescription());
		// Missing attribute identityCode on entity
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Identity} object 
	 */
	public com.soffid.iam.base.api.Identity toIdentity(com.soffid.iam.iga.model.GroupEntity entity) {
		final com.soffid.iam.base.api.Identity target = new com.soffid.iam.base.api.Identity();
		this.toIdentity(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Identity} list 
	 */
	public java.util.List<com.soffid.iam.base.api.Identity> toIdentityList (java.util.Collection<com.soffid.iam.iga.model.GroupEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.base.api.Identity> list =
				new java.util.LinkedList<com.soffid.iam.base.api.Identity>();
			for (final com.soffid.iam.iga.model.GroupEntity instance: instances)
			{
				list.add( toIdentity(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.base.api.Identity} object 
	 */
	public void identityToEntity (com.soffid.iam.base.api.Identity source, com.soffid.iam.iga.model.GroupEntity target, boolean copyIfNull) {
		// Attributes for GroupEntity
		// Missing attribute name on entity
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		// Missing attribute quota on entity
		// Missing attribute driveLetter on entity
		// Missing attribute parent on entity
		// Missing attribute printers on entity
		// Missing attribute secondaryGroupUsers on entity
		// Missing attribute driveServer on entity
		// Missing attribute networkAuthorization on entity
		// Missing attribute children on entity
		// Missing attribute usersRoles on entity
		// Missing attribute unitType on entity
		// Missing attribute obsolete on entity
		// Missing attribute startDate on entity
		// Missing attribute endDate on entity
		// Missing attribute tenant on entity
		// Missing attribute systemGroup on entity
		// Missing attribute grantedRoles on entity
		// Missing attribute primaryGroupUsers on entity
		// Missing attribute accountAccess on entity
		// Missing attribute holdedRoleAssignments on entity
		// Missing attribute attributes on entity
		// Missing attribute mailLists on entity
		// Missing attribute roleScopeMailLists on entity
		// Missing attribute audit on entity
		// Missing attribute createdOn on entity
		// Missing attribute createdBy on entity
		// Missing attribute updatedOn on entity
		// Missing attribute updatedBy on entity
		// Missing attribute deletedOn on entity
		// Missing attribute deletedBy on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Identity} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity>  identityToEntityList (java.util.Collection<com.soffid.iam.base.api.Identity> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.iga.model.GroupEntity> list =
			new java.util.LinkedList<com.soffid.iam.iga.model.GroupEntity>();
		for (com.soffid.iam.base.api.Identity instance: instances)
		{
			list.add (identityToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.GroupEntity} .
	 */
	public com.soffid.iam.iga.model.GroupEntity newGroupEntity()
	{
		return new com.soffid.iam.iga.model.GroupEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.GroupEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.iga.model.GroupEntity result = (com.soffid.iam.iga.model.GroupEntity) this.getHibernateTemplate().get(com.soffid.iam.iga.model.GroupEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.iga.model.GroupEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.iga.model.GroupEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.GroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.GroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"GroupEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
		removeGroupCacheEntry(entity.getId());
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.GroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.GroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"GroupEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
		removeGroupCacheEntry(entity.getId());
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.GroupEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"GroupEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
		removeGroupCacheEntry(entity.getId());
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.GroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.GroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"GroupEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.GroupEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.GroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.GroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"GroupEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.GroupEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.GroupEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"GroupEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.iga.model.GroupEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"GroupEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.iga.model.GroupEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.iga.model.GroupEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.iga.model.GroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.GroupEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.iga.model.GroupEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
class GroupCacheEntry {
	public com.soffid.iam.iga.api.Group group;
	public long timeStamp;
}
