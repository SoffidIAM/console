//
// (c) 2014 Soffid
//
//
package com.soffid.iam.rc.model;
/**
 * DAO Base for Entity GeoInformationEntity
 */
public abstract class GeoInformationEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.rc.model.GeoInformationEntityDao
{
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


	/**
	 * Operation findByIp
	 * @param ip
	 * @return
	**/
	public com.soffid.iam.rc.model.GeoInformationEntity findByIp(
	    java.lang.String ip)
	
	{
		return findByIp((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, ip);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public com.soffid.iam.rc.model.GeoInformationEntity findByIp(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ip)
	
	{
		return findByIp("from com.soffid.iam.rc.model.GeoInformationEntity where tenant.id=:tenantId and ip=:ip",
			criteria, ip);
	}
	/**
	 * Internal implementation
	 */
	public com.soffid.iam.rc.model.GeoInformationEntity findByIp(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ip)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("ip", ip, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			com.soffid.iam.rc.model.GeoInformationEntity result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'com.soffid.iam.rc.model.GeoInformationEntity' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (com.soffid.iam.rc.model.GeoInformationEntity) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.GeoInformation} object 
	 */
	public void toGeoInformation(com.soffid.iam.rc.model.GeoInformationEntity source, com.soffid.iam.rc.api.GeoInformation target) {
		// Attributes for GeoInformation
		target.setIp(source.getIp());
		target.setDate(source.getDate());
		target.setCountry(source.getCountry());
		target.setCountryDivision1(source.getCountryDivision1());
		target.setCountryDivision2(source.getCountryDivision2());
		target.setCity(source.getCity());
		target.setLatitude(source.getLatitude());
		target.setLongitude(source.getLongitude());
		target.setAccuracy(source.getAccuracy());
		target.setDomain(source.getDomain());
		target.setIsp(source.getIsp());
		target.setUserType(source.getUserType());
		target.setAnonymous(source.getAnonymous());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.GeoInformation} object 
	 */
	public com.soffid.iam.rc.api.GeoInformation toGeoInformation(com.soffid.iam.rc.model.GeoInformationEntity entity) {
		final com.soffid.iam.rc.api.GeoInformation target = new com.soffid.iam.rc.api.GeoInformation();
		this.toGeoInformation(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.GeoInformation} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.GeoInformation> toGeoInformationList (java.util.Collection<com.soffid.iam.rc.model.GeoInformationEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.rc.api.GeoInformation> list =
				new java.util.LinkedList<com.soffid.iam.rc.api.GeoInformation>();
			for (final com.soffid.iam.rc.model.GeoInformationEntity instance: instances)
			{
				list.add( toGeoInformation(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.GeoInformation} object 
	 */
	public void geoInformationToEntity (com.soffid.iam.rc.api.GeoInformation source, com.soffid.iam.rc.model.GeoInformationEntity target, boolean copyIfNull) {
		// Attributes for GeoInformationEntity
		if (copyIfNull || source.getIp() != null)
		{
			target.setIp(source.getIp());
		}
		if (copyIfNull || source.getDate() != null)
		{
			target.setDate(source.getDate());
		}
		if (copyIfNull || source.getCountry() != null)
		{
			target.setCountry(source.getCountry());
		}
		if (copyIfNull || source.getCountryDivision1() != null)
		{
			target.setCountryDivision1(source.getCountryDivision1());
		}
		if (copyIfNull || source.getCountryDivision2() != null)
		{
			target.setCountryDivision2(source.getCountryDivision2());
		}
		if (copyIfNull || source.getCity() != null)
		{
			target.setCity(source.getCity());
		}
		if (copyIfNull || source.getLatitude() != null)
		{
			target.setLatitude(source.getLatitude());
		}
		if (copyIfNull || source.getLongitude() != null)
		{
			target.setLongitude(source.getLongitude());
		}
		if (copyIfNull || source.getAccuracy() != null)
		{
			target.setAccuracy(source.getAccuracy());
		}
		if (copyIfNull || source.getDomain() != null)
		{
			target.setDomain(source.getDomain());
		}
		if (copyIfNull || source.getIsp() != null)
		{
			target.setIsp(source.getIsp());
		}
		if (copyIfNull || source.getUserType() != null)
		{
			target.setUserType(source.getUserType());
		}
		if (copyIfNull || source.getAnonymous() != null)
		{
			target.setAnonymous(source.getAnonymous());
		}
		// Missing attribute tenant on entity
	}

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.GeoInformation} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.GeoInformationEntity>  geoInformationToEntityList (java.util.Collection<com.soffid.iam.rc.api.GeoInformation> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.rc.model.GeoInformationEntity> list =
			new java.util.LinkedList<com.soffid.iam.rc.model.GeoInformationEntity>();
		for (com.soffid.iam.rc.api.GeoInformation instance: instances)
		{
			list.add (geoInformationToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.GeoInformationEntity} .
	 */
	public com.soffid.iam.rc.model.GeoInformationEntity newGeoInformationEntity()
	{
		return new com.soffid.iam.rc.model.GeoInformationEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.GeoInformationEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.rc.model.GeoInformationEntity result = (com.soffid.iam.rc.model.GeoInformationEntity) this.getHibernateTemplate().get(com.soffid.iam.rc.model.GeoInformationEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.GeoInformationEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.rc.model.GeoInformationEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.rc.model.GeoInformationEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.GeoInformationEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.GeoInformationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"GeoInformationEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.GeoInformationEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.GeoInformationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"GeoInformationEntityDao.update - 'entity' can not be null");
		}
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.GeoInformationEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"GeoInformationEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.GeoInformationEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.GeoInformationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"GeoInformationEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.GeoInformationEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.GeoInformationEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.GeoInformationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"GeoInformationEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.GeoInformationEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.GeoInformationEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"GeoInformationEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.rc.model.GeoInformationEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"GeoInformationEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.rc.model.GeoInformationEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.GeoInformationEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.rc.model.GeoInformationEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.rc.model.GeoInformationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.GeoInformationEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.rc.model.GeoInformationEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
