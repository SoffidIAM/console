//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity PasswordPolicyEntity
 */
public abstract class PasswordPolicyEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.PasswordPolicyEntityDao
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

	com.soffid.iam.am.model.ForbiddenWordEntityDao forbiddenWordEntityDao;

	/**
	 * Sets reference to <code>forbiddenWordEntityDao</code>.
	 */
	public void setForbiddenWordEntityDao (com.soffid.iam.am.model.ForbiddenWordEntityDao forbiddenWordEntityDao) {
		this.forbiddenWordEntityDao = forbiddenWordEntityDao;
	}

	/**
	 * Gets reference to <code>forbiddenWordEntityDao</code>.
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntityDao getForbiddenWordEntityDao () {
		return forbiddenWordEntityDao;
	}

	com.soffid.iam.am.model.PasswordDomainEntityDao passwordDomainEntityDao;

	/**
	 * Sets reference to <code>passwordDomainEntityDao</code>.
	 */
	public void setPasswordDomainEntityDao (com.soffid.iam.am.model.PasswordDomainEntityDao passwordDomainEntityDao) {
		this.passwordDomainEntityDao = passwordDomainEntityDao;
	}

	/**
	 * Gets reference to <code>passwordDomainEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PasswordDomainEntityDao getPasswordDomainEntityDao () {
		return passwordDomainEntityDao;
	}

	com.soffid.iam.am.model.PolicyForbiddenWordEntityDao policyForbiddenWordEntityDao;

	/**
	 * Sets reference to <code>policyForbiddenWordEntityDao</code>.
	 */
	public void setPolicyForbiddenWordEntityDao (com.soffid.iam.am.model.PolicyForbiddenWordEntityDao policyForbiddenWordEntityDao) {
		this.policyForbiddenWordEntityDao = policyForbiddenWordEntityDao;
	}

	/**
	 * Gets reference to <code>policyForbiddenWordEntityDao</code>.
	 */
	public com.soffid.iam.am.model.PolicyForbiddenWordEntityDao getPolicyForbiddenWordEntityDao () {
		return policyForbiddenWordEntityDao;
	}

	com.soffid.iam.iga.model.UserDomainEntityDao userDomainEntityDao;

	/**
	 * Sets reference to <code>userDomainEntityDao</code>.
	 */
	public void setUserDomainEntityDao (com.soffid.iam.iga.model.UserDomainEntityDao userDomainEntityDao) {
		this.userDomainEntityDao = userDomainEntityDao;
	}

	/**
	 * Gets reference to <code>userDomainEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserDomainEntityDao getUserDomainEntityDao () {
		return userDomainEntityDao;
	}

	com.soffid.iam.base.model.UserTypeEntityDao userTypeEntityDao;

	/**
	 * Sets reference to <code>userTypeEntityDao</code>.
	 */
	public void setUserTypeEntityDao (com.soffid.iam.base.model.UserTypeEntityDao userTypeEntityDao) {
		this.userTypeEntityDao = userTypeEntityDao;
	}

	/**
	 * Gets reference to <code>userTypeEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserTypeEntityDao getUserTypeEntityDao () {
		return userTypeEntityDao;
	}


	/**
	 * Operation findByAccount
	 * @param id
	 * @return
	**/
	public com.soffid.iam.am.model.PasswordPolicyEntity findByAccount(
	    java.lang.Long id)
	
	{
		return findByAccount((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, id);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntity findByAccount(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		return findByAccount("select pp from \ncom.soffid.iam.base.model.AccountEntity as acc, com.soffid.iam.am.model.PasswordPolicyEntity as pp, com.soffid.iam.iga.model.SystemEntity as sys where acc.id=:id  and  acc.system.id = sys.id  and  acc.passwordPolicy.id = pp.userType.id and  sys.passwordDomain.id = pp.passwordDomain.id ",
			criteria, id);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntity findByAccount(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("id", id, org.hibernate.Hibernate.LONG);
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.PasswordPolicyEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.PasswordPolicyEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.PasswordPolicyEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByPasswordDomainAndUserType
	 * @param passwordDomain
	 * @param userType
	 * @return
	**/
	public com.soffid.iam.am.model.PasswordPolicyEntity findByPasswordDomainAndUserType(
	    java.lang.String passwordDomain, 
	    java.lang.String userType)
	
	{
		return findByPasswordDomainAndUserType((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, passwordDomain, userType);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntity findByPasswordDomainAndUserType(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String passwordDomain, java.lang.String userType)
	
	{
		return findByPasswordDomainAndUserType("select pol from \ncom.soffid.iam.am.model.PasswordPolicyEntity pol\nleft join pol.passwordDomain domini\nleft join pol.userType tus\nwhere domini.name=:passwordDomain and \ndomini.tenant.id = :tenantId and           tus.name=:userType",
			criteria, passwordDomain, userType);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntity findByPasswordDomainAndUserType(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String passwordDomain, java.lang.String userType)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("passwordDomain", passwordDomain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("userType", userType, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.PasswordPolicyEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.PasswordPolicyEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.PasswordPolicyEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByPasswordDomain
	 * @param passwordDomain
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity> findByPasswordDomain(
	    java.lang.String passwordDomain)
	
	{
		return findByPasswordDomain((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, passwordDomain);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity> findByPasswordDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String passwordDomain)
	
	{
		return findByPasswordDomain("select pol from \ncom.soffid.iam.am.model.PasswordPolicyEntity pol\nleft join pol.passwordDomain con\nwhere (:passwordDomain is null or con.name=:passwordDomain) and \ncon.tenant.id = :tenantId order by con.name",
			criteria, passwordDomain);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity> findByPasswordDomain(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String passwordDomain)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("passwordDomain", passwordDomain, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.PasswordPolicy} object 
	 */
	public void toPasswordPolicy(com.soffid.iam.am.model.PasswordPolicyEntity source, com.soffid.iam.am.api.PasswordPolicy target) {
		// Attributes for PasswordPolicy
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		target.setType(source.getType());
		target.setRenewalTime(source.getRenewalTime());
		target.setMaximumPeriod(source.getMaximumPeriod());
		target.setMaximumPeriodExpired(source.getMaximumPeriodExpired());
		target.setMinimumPeriod(source.getMinimumPeriod());
		target.setMinimumLength(source.getMinimumLength());
		target.setMaximumLength(source.getMaximumLength());
		target.setRegularExpression(source.getRegularExpression());
		target.setMinimumUppercase(source.getMinimumUppercase());
		target.setMaximumUppercase(source.getMaximumUppercase());
		target.setMinimumLowercase(source.getMinimumLowercase());
		target.setMaximumLowercase(source.getMaximumLowercase());
		target.setMinimumNumbers(source.getMinimumNumbers());
		target.setMaximumNumbers(source.getMaximumNumbers());
		target.setMinimumSymbols(source.getMinimumSymbols());
		target.setMaximumSymbols(source.getMaximumSymbols());
		target.setMaximumHistorical(source.getMaximumHistorical());
		// Incompatible types source.userType and target.userType
		// Missing attribute userTypeDescription on entity
		// Missing attribute usersDomainCode on entity
		// Missing attribute passwordDomainCode on entity
		target.setMaxFailures(source.getMaxFailures());
		target.setUnlockAfterSeconds(source.getUnlockAfterSeconds());
		target.setAllowPasswordQuery(java.lang.Boolean.TRUE.equals(source.getAllowPasswordQuery()));
		target.setAllowPasswordChange(java.lang.Boolean.TRUE.equals(source.getAllowPasswordChange()));
		target.setStoreUserPasswords(source.getStoreUserPasswords());
		target.setComplexPasswords(java.lang.Boolean.TRUE.equals(source.getComplexPasswords()));
		target.setValidationScript(source.getValidationScript());
		target.setValidationScriptDescription(source.getValidationScriptDescription());
		target.setCheckPasswordBreached(java.lang.Boolean.TRUE.equals(source.getCheckPasswordBreached()));
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordPolicy} object 
	 */
	public com.soffid.iam.am.api.PasswordPolicy toPasswordPolicy(com.soffid.iam.am.model.PasswordPolicyEntity entity) {
		final com.soffid.iam.am.api.PasswordPolicy target = new com.soffid.iam.am.api.PasswordPolicy();
		this.toPasswordPolicy(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordPolicy} list 
	 */
	public java.util.List<com.soffid.iam.am.api.PasswordPolicy> toPasswordPolicyList (java.util.Collection<com.soffid.iam.am.model.PasswordPolicyEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.PasswordPolicy> list =
				new java.util.LinkedList<com.soffid.iam.am.api.PasswordPolicy>();
			for (final com.soffid.iam.am.model.PasswordPolicyEntity instance: instances)
			{
				list.add( toPasswordPolicy(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.PasswordPolicy} object 
	 */
	public void passwordPolicyToEntity (com.soffid.iam.am.api.PasswordPolicy source, com.soffid.iam.am.model.PasswordPolicyEntity target, boolean copyIfNull) {
		// Attributes for PasswordPolicyEntity
		if (copyIfNull || source.getDescription() != null)
		{
			target.setDescription(source.getDescription());
		}
		if (copyIfNull || source.getUserType() != null)
		{
			// Incompatible types source.userType and target.userType
		}
		if (copyIfNull || source.getType() != null)
		{
			target.setType(source.getType());
		}
		if (copyIfNull || source.getRenewalTime() != null)
		{
			target.setRenewalTime(source.getRenewalTime());
		}
		if (copyIfNull || source.getMaximumPeriod() != null)
		{
			target.setMaximumPeriod(source.getMaximumPeriod());
		}
		if (copyIfNull || source.getMaximumPeriodExpired() != null)
		{
			target.setMaximumPeriodExpired(source.getMaximumPeriodExpired());
		}
		if (copyIfNull || source.getMinimumPeriod() != null)
		{
			target.setMinimumPeriod(source.getMinimumPeriod());
		}
		if (copyIfNull || source.getMinimumLength() != null)
		{
			target.setMinimumLength(source.getMinimumLength());
		}
		if (copyIfNull || source.getMaximumLength() != null)
		{
			target.setMaximumLength(source.getMaximumLength());
		}
		if (copyIfNull || source.getRegularExpression() != null)
		{
			target.setRegularExpression(source.getRegularExpression());
		}
		if (copyIfNull || source.getMinimumUppercase() != null)
		{
			target.setMinimumUppercase(source.getMinimumUppercase());
		}
		if (copyIfNull || source.getMaximumUppercase() != null)
		{
			target.setMaximumUppercase(source.getMaximumUppercase());
		}
		if (copyIfNull || source.getMinimumLowercase() != null)
		{
			target.setMinimumLowercase(source.getMinimumLowercase());
		}
		if (copyIfNull || source.getMaximumLowercase() != null)
		{
			target.setMaximumLowercase(source.getMaximumLowercase());
		}
		if (copyIfNull || source.getMinimumNumbers() != null)
		{
			target.setMinimumNumbers(source.getMinimumNumbers());
		}
		if (copyIfNull || source.getMaximumNumbers() != null)
		{
			target.setMaximumNumbers(source.getMaximumNumbers());
		}
		if (copyIfNull || source.getMinimumSymbols() != null)
		{
			target.setMinimumSymbols(source.getMinimumSymbols());
		}
		if (copyIfNull || source.getMaximumSymbols() != null)
		{
			target.setMaximumSymbols(source.getMaximumSymbols());
		}
		if (copyIfNull || source.getMaximumHistorical() != null)
		{
			target.setMaximumHistorical(source.getMaximumHistorical());
		}
		// Missing attribute passwordDomain on entity
		// Missing attribute forbiddenWords on entity
		target.setAllowPasswordQuery(new java.lang.Boolean(source.isAllowPasswordQuery()));
		target.setAllowPasswordChange(new java.lang.Boolean(source.isAllowPasswordChange()));
		if (copyIfNull || source.getStoreUserPasswords() != null)
		{
			target.setStoreUserPasswords(source.getStoreUserPasswords());
		}
		target.setComplexPasswords(new java.lang.Boolean(source.isComplexPasswords()));
		if (copyIfNull || source.getMaxFailures() != null)
		{
			target.setMaxFailures(source.getMaxFailures());
		}
		if (copyIfNull || source.getUnlockAfterSeconds() != null)
		{
			target.setUnlockAfterSeconds(source.getUnlockAfterSeconds());
		}
		if (copyIfNull || source.getValidationScript() != null)
		{
			target.setValidationScript(source.getValidationScript());
		}
		if (copyIfNull || source.getValidationScriptDescription() != null)
		{
			target.setValidationScriptDescription(source.getValidationScriptDescription());
		}
		target.setCheckPasswordBreached(new java.lang.Boolean(source.isCheckPasswordBreached()));
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
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordPolicy} object 
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntity passwordPolicyToEntity (com.soffid.iam.am.api.PasswordPolicy instance) {
		com.soffid.iam.am.model.PasswordPolicyEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newPasswordPolicyEntity();
		passwordPolicyToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordPolicy} list 
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity>  passwordPolicyToEntityList (java.util.Collection<com.soffid.iam.am.api.PasswordPolicy> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.PasswordPolicyEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.PasswordPolicyEntity>();
		for (com.soffid.iam.am.api.PasswordPolicy instance: instances)
		{
			list.add (passwordPolicyToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.PasswordPolicyEntity} .
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntity newPasswordPolicyEntity()
	{
		return new com.soffid.iam.am.model.PasswordPolicyEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.PasswordPolicyEntity result = (com.soffid.iam.am.model.PasswordPolicyEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.PasswordPolicyEntityImpl.class, id);

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity> loadAll() {
		java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity> result = (java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity>)
			this.getHibernateTemplate().loadAll(com.soffid.iam.am.model.PasswordPolicyEntity.class);
		return result;
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.PasswordPolicyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.PasswordPolicyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PasswordPolicyEntityDao.create - 'entity' can not be null");
		}

		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.PasswordPolicyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.PasswordPolicyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PasswordPolicyEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.PasswordPolicyEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"PasswordPolicyEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.PasswordPolicyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.PasswordPolicyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PasswordPolicyEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.PasswordPolicyEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.PasswordPolicyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.PasswordPolicyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PasswordPolicyEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.PasswordPolicyEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.PasswordPolicyEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"PasswordPolicyEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.PasswordPolicyEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"PasswordPolicyEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.PasswordPolicyEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
