//
// (c) 2014 Soffid
//
//
package com.soffid.iam.rc.model;
/**
 * DAO Base for Entity IssueEntity
 */
public abstract class IssueEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.rc.model.IssueEntityDao
{
	com.soffid.iam.base.model.AccountEntityDao accountEntityDao;

	/**
	 * Sets reference to <code>accountEntityDao</code>.
	 */
	public void setAccountEntityDao (com.soffid.iam.base.model.AccountEntityDao accountEntityDao) {
		this.accountEntityDao = accountEntityDao;
	}

	/**
	 * Gets reference to <code>accountEntityDao</code>.
	 */
	public com.soffid.iam.base.model.AccountEntityDao getAccountEntityDao () {
		return accountEntityDao;
	}

	com.soffid.iam.rc.service.GeoInformationService geoInformationService;

	/**
	 * Sets reference to <code>geoInformationService</code>.
	 */
	public void setGeoInformationService (com.soffid.iam.rc.service.GeoInformationService geoInformationService) {
		this.geoInformationService = geoInformationService;
	}

	/**
	 * Gets reference to <code>geoInformationService</code>.
	 */
	public com.soffid.iam.rc.service.GeoInformationService getGeoInformationService () {
		return geoInformationService;
	}

	com.soffid.iam.rc.model.IssueBrowserEntityDao issueBrowserEntityDao;

	/**
	 * Sets reference to <code>issueBrowserEntityDao</code>.
	 */
	public void setIssueBrowserEntityDao (com.soffid.iam.rc.model.IssueBrowserEntityDao issueBrowserEntityDao) {
		this.issueBrowserEntityDao = issueBrowserEntityDao;
	}

	/**
	 * Gets reference to <code>issueBrowserEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueBrowserEntityDao getIssueBrowserEntityDao () {
		return issueBrowserEntityDao;
	}

	com.soffid.iam.pam.model.PamRuleEntityDao pamRuleEntityDao;

	/**
	 * Sets reference to <code>pamRuleEntityDao</code>.
	 */
	public void setPamRuleEntityDao (com.soffid.iam.pam.model.PamRuleEntityDao pamRuleEntityDao) {
		this.pamRuleEntityDao = pamRuleEntityDao;
	}

	/**
	 * Gets reference to <code>pamRuleEntityDao</code>.
	 */
	public com.soffid.iam.pam.model.PamRuleEntityDao getPamRuleEntityDao () {
		return pamRuleEntityDao;
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

	com.soffid.iam.iga.model.SystemEntityDao systemEntityDao;

	/**
	 * Sets reference to <code>systemEntityDao</code>.
	 */
	public void setSystemEntityDao (com.soffid.iam.iga.model.SystemEntityDao systemEntityDao) {
		this.systemEntityDao = systemEntityDao;
	}

	/**
	 * Gets reference to <code>systemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.SystemEntityDao getSystemEntityDao () {
		return systemEntityDao;
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

	com.soffid.iam.rc.model.IssueHostEntityDao issueHostEntityDao;

	/**
	 * Sets reference to <code>issueHostEntityDao</code>.
	 */
	public void setIssueHostEntityDao (com.soffid.iam.rc.model.IssueHostEntityDao issueHostEntityDao) {
		this.issueHostEntityDao = issueHostEntityDao;
	}

	/**
	 * Gets reference to <code>issueHostEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueHostEntityDao getIssueHostEntityDao () {
		return issueHostEntityDao;
	}

	com.soffid.iam.rc.model.IssueUserEntityDao issueUserEntityDao;

	/**
	 * Sets reference to <code>issueUserEntityDao</code>.
	 */
	public void setIssueUserEntityDao (com.soffid.iam.rc.model.IssueUserEntityDao issueUserEntityDao) {
		this.issueUserEntityDao = issueUserEntityDao;
	}

	/**
	 * Gets reference to <code>issueUserEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.IssueUserEntityDao getIssueUserEntityDao () {
		return issueUserEntityDao;
	}


	/**
	 * Operation countPending
	 * @param actor
	 * @return
	**/
	public java.lang.Long countPending(
	    java.lang.String actor)
	
	{
		return countPending((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, actor);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.lang.Long countPending(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String actor)
	
	{
		return countPending("select count(*) from com.soffid.iam.rc.model.IssueEntity as i where i.actor = :actor and (i.status = 'N') and i.tenant.id=:tenantId",
			criteria, actor);
	}
	/**
	 * Internal implementation
	 */
	public java.lang.Long countPending(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String actor)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("actor", actor, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.lang.Long result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.lang.Long' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.lang.Long) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findActiveRoles
	 * @return
	**/
	public java.util.Collection<java.lang.String> findActiveRoles(
)
	
	{
		return findActiveRoles((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<java.lang.String> findActiveRoles(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
	{
		return findActiveRoles("select distinct actor from com.soffid.iam.rc.model.IssueEntity\nwhere status in ('N', 'A')\n",
			criteria);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<java.lang.String> findActiveRoles(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	
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
	 * Operation findByIssueAndUser
	 * @param type
	 * @param user
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findByIssueAndUser(
	    java.lang.String type, 
	    java.lang.String user)
	
	{
		return findByIssueAndUser((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, type, user);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findByIssueAndUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type, java.lang.String user)
	
	{
		return findByIssueAndUser("select i from com.soffid.iam.rc.model.IssueEntity as i join i.users as users join users.user as user where user.userName = :user and user.tenant.id=:tenantId and (i.status = 'N' or i.status = 'A') and i.type = :type",
			criteria, type, user);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findByIssueAndUser(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type, java.lang.String user)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("type", type, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.rc.model.IssueEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findBySearchHash
	 * @param type
	 * @param searchHash
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findBySearchHash(
	    java.lang.String type, 
	    java.lang.String searchHash)
	
	{
		return findBySearchHash((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, type, searchHash);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findBySearchHash(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type, java.lang.String searchHash)
	
	{
		return findBySearchHash("select i from com.soffid.iam.rc.model.IssueEntity as i where (i.status = 'N' or i.status = 'A') and i.hash = :searchHash and i.type = :type and i.tenant.id=:tenantId",
			criteria, type, searchHash);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findBySearchHash(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type, java.lang.String searchHash)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("type", type, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("searchHash", searchHash, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.rc.model.IssueEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByUserName
	 * @param user
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findByUserName(
	    java.lang.String user)
	
	{
		return findByUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, user);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	
	{
		return findByUserName("select i from com.soffid.iam.rc.model.IssueEntity as i join i.users as users join users.user as user where user.userName = :user and user.tenant.id=:tenantId",
			criteria, user);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findByUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("user", user, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.rc.model.IssueEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.Issue} object 
	 */
	public void toIssue(com.soffid.iam.rc.model.IssueEntity source, com.soffid.iam.rc.api.Issue target) {
		// Attributes for Issue
		target.setId(source.getId());
		target.setNumber(source.getNumber());
		// Incompatible types source.requester and target.requester
		target.setType(source.getType());
		// Missing attribute description on entity
		target.setTimes(source.getTimes());
		target.setStatus(source.getStatus());
		target.setFailedLoginPct(source.getFailedLoginPct());
		target.setHumanConfidence(source.getHumanConfidence());
		// Incompatible types source.system and target.system
		target.setOtpDevice(source.getOtpDevice());
		target.setException(source.getException());
		target.setRisk(source.getRisk());
		// Incompatible types source.roleAccount and target.roleAccount
		// Incompatible types source.rule and target.rule
		target.setJobName(source.getJobName());
		target.setIp(source.getIp());
		target.setCountry(source.getCountry());
		// Incompatible types source.account and target.account
		target.setLoginName(source.getLoginName());
		target.setHash(source.getHash());
		// Incompatible types source.hosts and target.hosts
		// Missing attribute browsers on entity
		// Incompatible types source.users and target.users
		target.setBreachedEmail(source.getBreachedEmail());
		target.setDataBreach(source.getDataBreach());
		target.setHtmlDescription(source.getHtmlDescription());
		target.setCreated(source.getCreated());
		target.setAcknowledged(source.getAcknowledged());
		target.setSolved(source.getSolved());
		target.setActor(source.getActor());
		target.setPerformedActions(source.getPerformedActions());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
		target.setCreatedOn(source.getCreatedOn());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.Issue} object 
	 */
	public com.soffid.iam.rc.api.Issue toIssue(com.soffid.iam.rc.model.IssueEntity entity) {
		final com.soffid.iam.rc.api.Issue target = new com.soffid.iam.rc.api.Issue();
		this.toIssue(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.Issue} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.Issue> toIssueList (java.util.Collection<com.soffid.iam.rc.model.IssueEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.rc.api.Issue> list =
				new java.util.LinkedList<com.soffid.iam.rc.api.Issue>();
			for (final com.soffid.iam.rc.model.IssueEntity instance: instances)
			{
				list.add( toIssue(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.Issue} object 
	 */
	public void issueToEntity (com.soffid.iam.rc.api.Issue source, com.soffid.iam.rc.model.IssueEntity target, boolean copyIfNull) {
		// Attributes for IssueEntity
		if (copyIfNull || source.getNumber() != null)
		{
			target.setNumber(source.getNumber());
		}
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		if (copyIfNull || source.getStatus() != null)
		{
			target.setStatus(source.getStatus());
		}
		if (copyIfNull || source.getCreated() != null)
		{
			target.setCreated(source.getCreated());
		}
		if (copyIfNull || source.getAcknowledged() != null)
		{
			target.setAcknowledged(source.getAcknowledged());
		}
		if (copyIfNull || source.getSolved() != null)
		{
			target.setSolved(source.getSolved());
		}
		if (copyIfNull || source.getFailedLoginPct() != null)
		{
			target.setFailedLoginPct(source.getFailedLoginPct());
		}
		if (copyIfNull || source.getHumanConfidence() != null)
		{
			target.setHumanConfidence(source.getHumanConfidence());
		}
		if (copyIfNull || source.getSystem() != null)
		{
			// Incompatible types source.system and target.system
		}
		if (copyIfNull || source.getOtpDevice() != null)
		{
			target.setOtpDevice(source.getOtpDevice());
		}
		if (copyIfNull || source.getException() != null)
		{
			target.setException(source.getException());
		}
		if (copyIfNull || source.getIp() != null)
		{
			target.setIp(source.getIp());
		}
		if (copyIfNull || source.getCountry() != null)
		{
			target.setCountry(source.getCountry());
		}
		if (copyIfNull || source.getRoleAccount() != null)
		{
			// Incompatible types source.roleAccount and target.roleAccount
		}
		if (copyIfNull || source.getPerformedActions() != null)
		{
			target.setPerformedActions(source.getPerformedActions());
		}
		if (copyIfNull || source.getRule() != null)
		{
			// Incompatible types source.rule and target.rule
		}
		if (copyIfNull || source.getAccount() != null)
		{
			// Incompatible types source.account and target.account
		}
		if (copyIfNull || source.getActor() != null)
		{
			target.setActor(source.getActor());
		}
		if (copyIfNull || source.getLoginName() != null)
		{
			target.setLoginName(source.getLoginName());
		}
		if (copyIfNull || source.getJobName() != null)
		{
			target.setJobName(source.getJobName());
		}
		if (copyIfNull || source.getRequester() != null)
		{
			// Incompatible types source.requester and target.requester
		}
		if (copyIfNull || source.getRisk() != null)
		{
			target.setRisk(source.getRisk());
		}
		// Missing attribute tenant on entity
		if (copyIfNull || source.getHash() != null)
		{
			target.setHash(source.getHash());
		}
		if (copyIfNull || source.getTimes() != null)
		{
			target.setTimes(source.getTimes());
		}
		if (copyIfNull || source.getBreachedEmail() != null)
		{
			target.setBreachedEmail(source.getBreachedEmail());
		}
		if (copyIfNull || source.getDataBreach() != null)
		{
			target.setDataBreach(source.getDataBreach());
		}
		if (copyIfNull || source.getHtmlDescription() != null)
		{
			target.setHtmlDescription(source.getHtmlDescription());
		}
		// Missing attribute brosers on entity
		if (copyIfNull || source.getHosts() != null)
		{
			// Incompatible types source.hosts and target.hosts
		}
		if (copyIfNull || source.getUsers() != null)
		{
			// Incompatible types source.users and target.users
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
		if (copyIfNull || source.getCreatedOn() != null)
		{
			target.setCreatedOn(source.getCreatedOn());
		}
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.Issue} object 
	 */
	public com.soffid.iam.rc.model.IssueEntity issueToEntity (com.soffid.iam.rc.api.Issue instance) {
		com.soffid.iam.rc.model.IssueEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newIssueEntity();
		issueToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.Issue} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueEntity>  issueToEntityList (java.util.Collection<com.soffid.iam.rc.api.Issue> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.rc.model.IssueEntity> list =
			new java.util.LinkedList<com.soffid.iam.rc.model.IssueEntity>();
		for (com.soffid.iam.rc.api.Issue instance: instances)
		{
			list.add (issueToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.IssueEntity} .
	 */
	public com.soffid.iam.rc.model.IssueEntity newIssueEntity()
	{
		return new com.soffid.iam.rc.model.IssueEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.IssueEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.rc.model.IssueEntity result = (com.soffid.iam.rc.model.IssueEntity) this.getHibernateTemplate().get(com.soffid.iam.rc.model.IssueEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.rc.model.IssueEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.rc.model.IssueEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.IssueEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.IssueEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssueEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		entity.setCreated(new java.util.Date());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.IssueEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.IssueEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssueEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.IssueEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"IssueEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.IssueEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.IssueEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssueEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssueEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.IssueEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.IssueEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssueEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssueEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.IssueEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"IssueEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.IssueEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"IssueEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.rc.model.IssueEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.rc.model.IssueEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.rc.model.IssueEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
