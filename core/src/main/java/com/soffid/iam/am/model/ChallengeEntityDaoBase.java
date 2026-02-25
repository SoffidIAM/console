//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity ChallengeEntity
 */
public abstract class ChallengeEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.ChallengeEntityDao
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


	/**
	 * Operation findByChallengeId
	 * @param challengeId
	 * @return
	**/
	public com.soffid.iam.am.model.ChallengeEntity findByChallengeId(
	    java.lang.String challengeId)
	
	{
		return findByChallengeId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, challengeId);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.am.model.ChallengeEntity findByChallengeId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String challengeId)
	
	{
		return findByChallengeId("from com.soffid.iam.am.model.ChallengeEntity where tenant.id=:tenantId and challengeId=:challengeId",
			criteria, challengeId);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.am.model.ChallengeEntity findByChallengeId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String challengeId)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("challengeId", challengeId, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.am.model.ChallengeEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.am.model.ChallengeEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.am.model.ChallengeEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findExpiredChallenges
	 * @param timeStamp
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.am.model.ChallengeEntity> findExpiredChallenges(
	    java.util.Date timeStamp)
	
	{
		return findExpiredChallenges((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, timeStamp);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Collection<com.soffid.iam.am.model.ChallengeEntity> findExpiredChallenges(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date timeStamp)
	
	{
		return findExpiredChallenges("select ch from com.soffid.iam.am.model.ChallengeEntity as ch where ch.tenant.id=:tenantId and ch.timeStamp < :timeStamp",
			criteria, timeStamp);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Collection<com.soffid.iam.am.model.ChallengeEntity> findExpiredChallenges(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date timeStamp)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("timeStamp", timeStamp, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.Collection<com.soffid.iam.am.model.ChallengeEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.Challenge} object 
	 */
	public void toChallenge(com.soffid.iam.am.model.ChallengeEntity source, com.soffid.iam.am.api.Challenge target) {
		// Attributes for Challenge
		// Missing attribute CARD_REQUIRED on entity
		// Missing attribute CARD_IFABLE on entity
		// Missing attribute CARD_IFNEEDED on entity
		// Missing attribute CARD_DISABLED on entity
		// Missing attribute TYPE_RMI on entity
		// Missing attribute TYPE_KERBEROS on entity
		// Missing attribute TYPE_CERT on entity
		// Missing attribute TYPE_PASSWORD on entity
		// Missing attribute password on entity
		target.setType(source.getType());
		// Incompatible types source.user and target.user
		// Incompatible types source.account and target.account
		target.setUserKey(source.getUserKey());
		// Incompatible types source.host and target.host
		// Incompatible types source.clientHost and target.clientHost
		// Incompatible types source.centinelPort and target.centinelPort
		target.setOtpHandler(source.getOtpHandler());
		target.setCardNumber(source.getCardNumber());
		target.setCell(source.getCell());
		target.setValue(source.getValue());
		// Incompatible types source.timeStamp and target.timeStamp
		// Incompatible types source.clientVersion and target.clientVersion
		target.setKerberosDomain(source.getKerberosDomain());
		target.setChallengeId(source.getChallengeId());
		// Missing attribute kerberosContext on entity
		target.setDomain(source.getDomain());
		target.setCloseOldSessions(source.isCloseOldSessions());
		target.setSilent(source.isSilent());
		// Missing attribute alternativeMethodAvailable on entity
		// Missing attribute resendAvailable on entity
		// Missing attribute additionalData on entity
		target.setIdentityProvider(source.getIdentityProvider());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Challenge} object 
	 */
	public com.soffid.iam.am.api.Challenge toChallenge(com.soffid.iam.am.model.ChallengeEntity entity) {
		final com.soffid.iam.am.api.Challenge target = new com.soffid.iam.am.api.Challenge();
		this.toChallenge(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Challenge} list 
	 */
	public java.util.List<com.soffid.iam.am.api.Challenge> toChallengeList (java.util.Collection<com.soffid.iam.am.model.ChallengeEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.Challenge> list =
				new java.util.LinkedList<com.soffid.iam.am.api.Challenge>();
			for (final com.soffid.iam.am.model.ChallengeEntity instance: instances)
			{
				list.add( toChallenge(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.Challenge} object 
	 */
	public void challengeToEntity (com.soffid.iam.am.api.Challenge source, com.soffid.iam.am.model.ChallengeEntity target, boolean copyIfNull) {
		// Attributes for ChallengeEntity
		target.setType(source.getType());
		if (copyIfNull || source.getUser() != null)
		{
			// Incompatible types source.user and target.user
		}
		if (copyIfNull || source.getAccount() != null)
		{
			// Incompatible types source.account and target.account
		}
		if (copyIfNull || source.getUserKey() != null)
		{
			target.setUserKey(source.getUserKey());
		}
		if (copyIfNull || source.getHost() != null)
		{
			// Incompatible types source.host and target.host
		}
		if (copyIfNull || source.getClientHost() != null)
		{
			// Incompatible types source.clientHost and target.clientHost
		}
		// Incompatible types source.centinelPort and target.centinelPort
		if (copyIfNull || source.getOtpHandler() != null)
		{
			target.setOtpHandler(source.getOtpHandler());
		}
		if (copyIfNull || source.getCardNumber() != null)
		{
			target.setCardNumber(source.getCardNumber());
		}
		if (copyIfNull || source.getCell() != null)
		{
			target.setCell(source.getCell());
		}
		if (copyIfNull || source.getValue() != null)
		{
			target.setValue(source.getValue());
		}
		if (copyIfNull || source.getTimeStamp() != null)
		{
			// Incompatible types source.timeStamp and target.timeStamp
		}
		// Incompatible types source.clientVersion and target.clientVersion
		if (copyIfNull || source.getKerberosDomain() != null)
		{
			target.setKerberosDomain(source.getKerberosDomain());
		}
		if (copyIfNull || source.getChallengeId() != null)
		{
			target.setChallengeId(source.getChallengeId());
		}
		if (copyIfNull || source.getDomain() != null)
		{
			target.setDomain(source.getDomain());
		}
		target.setCloseOldSessions(source.isCloseOldSessions());
		target.setSilent(source.isSilent());
		if (copyIfNull || source.getIdentityProvider() != null)
		{
			target.setIdentityProvider(source.getIdentityProvider());
		}
		// Missing attribute tenant on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Challenge} list 
	 */
	public java.util.List<com.soffid.iam.am.model.ChallengeEntity>  challengeToEntityList (java.util.Collection<com.soffid.iam.am.api.Challenge> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.ChallengeEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.ChallengeEntity>();
		for (com.soffid.iam.am.api.Challenge instance: instances)
		{
			list.add (challengeToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.ChallengeEntity} .
	 */
	public com.soffid.iam.am.model.ChallengeEntity newChallengeEntity()
	{
		return new com.soffid.iam.am.model.ChallengeEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.ChallengeEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.ChallengeEntity result = (com.soffid.iam.am.model.ChallengeEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.ChallengeEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.ChallengeEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.ChallengeEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.ChallengeEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.ChallengeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.ChallengeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ChallengeEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.ChallengeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.ChallengeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ChallengeEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.ChallengeEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"ChallengeEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.ChallengeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.ChallengeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ChallengeEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.ChallengeEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.ChallengeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.ChallengeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ChallengeEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.ChallengeEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.ChallengeEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"ChallengeEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.ChallengeEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"ChallengeEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.ChallengeEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.ChallengeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.ChallengeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.ChallengeEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.ChallengeEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
