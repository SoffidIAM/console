//
// (c) 2014 Soffid
//
//
package com.soffid.iam.am.model;
/**
 * DAO Base for Entity AccessLogEntity
 */
public abstract class AccessLogEntityDaoBase
	extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
	implements com.soffid.iam.am.model.AccessLogEntityDao
{
	com.soffid.iam.am.model.BrowserEntityDao browserEntityDao;

	/**
	 * Sets reference to <code>browserEntityDao</code>.
	 */
	public void setBrowserEntityDao (com.soffid.iam.am.model.BrowserEntityDao browserEntityDao) {
		this.browserEntityDao = browserEntityDao;
	}

	/**
	 * Gets reference to <code>browserEntityDao</code>.
	 */
	public com.soffid.iam.am.model.BrowserEntityDao getBrowserEntityDao () {
		return browserEntityDao;
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

	com.soffid.iam.am.model.ServiceEntityDao serviceEntityDao;

	/**
	 * Sets reference to <code>serviceEntityDao</code>.
	 */
	public void setServiceEntityDao (com.soffid.iam.am.model.ServiceEntityDao serviceEntityDao) {
		this.serviceEntityDao = serviceEntityDao;
	}

	/**
	 * Gets reference to <code>serviceEntityDao</code>.
	 */
	public com.soffid.iam.am.model.ServiceEntityDao getServiceEntityDao () {
		return serviceEntityDao;
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
	 * Operation findLastDateBySystem
	 * @param system
	 * @return
	**/
	public java.util.Date findLastDateBySystem(
	    java.lang.String system)
	
	{
		return findLastDateBySystem((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, system);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.Date findLastDateBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	
	{
		return findLastDateBySystem("select max(rac.startDate) as startDate\nfrom com.soffid.iam.am.model.AccessLogEntity rac where rac.system=:system and \nrac.tenant.id = :tenantId",
			criteria, system);
	}
	/**
	 * Internal implementation
	 */
	public java.util.Date findLastDateBySystem(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.Set results = new java.util.LinkedHashSet(queryObject.list());
			java.util.Date result = null;
			if (results.size() > 1) {
				throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
					"More than one instance of 'java.util.Date' was found when executing query --> '" + queryString + "'");
			}
			else if (results.size() == 1)
			{
				result = (java.util.Date) results.iterator().next();
			}
			return result;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAccessLogByAgentAndSessionIDAndEndDate
	 * @param system
	 * @param sessioId
	 * @param date
	 * @param server
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByAgentAndSessionIDAndEndDate(
	    java.lang.String system, 
	    java.lang.String sessioId, 
	    java.util.Date date, 
	    com.soffid.iam.am.model.HostEntity server)
	
	{
		return findAccessLogByAgentAndSessionIDAndEndDate((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, system, sessioId, date, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByAgentAndSessionIDAndEndDate(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String sessioId, java.util.Date date, com.soffid.iam.am.model.HostEntity server)
	
	{
		return findAccessLogByAgentAndSessionIDAndEndDate("select rac\nfrom com.soffid.iam.am.model.AccessLogEntity rac\nwhere rac.sessionId=:sessioId and rac.system = :system and (rac.endDate is null or rac.endDate=:date) and rac.server=:server and rac.tenant.id = :tenantId ",
			criteria, system, sessioId, date, server);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByAgentAndSessionIDAndEndDate(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String sessioId, java.util.Date date, com.soffid.iam.am.model.HostEntity server)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("sessioId", sessioId, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("date", date, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("server", server);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAccessLogByAgentAndSessionIDAndEndDate2
	 * @param system
	 * @param sessioId
	 * @param date
	 * @param hostName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByAgentAndSessionIDAndEndDate2(
	    java.lang.String system, 
	    java.lang.String sessioId, 
	    java.util.Date date, 
	    java.lang.String hostName)
	
	{
		return findAccessLogByAgentAndSessionIDAndEndDate2((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, system, sessioId, date, hostName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByAgentAndSessionIDAndEndDate2(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String sessioId, java.util.Date date, java.lang.String hostName)
	
	{
		return findAccessLogByAgentAndSessionIDAndEndDate2("select rac\nfrom com.soffid.iam.am.model.AccessLogEntity rac\nwhere rac.sessionId=:sessioId and rac.system = :system and (rac.endDate is null or rac.endDate=:date) and rac.hostName=:hostName and rac.tenant.id = :tenantId ",
			criteria, system, sessioId, date, hostName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByAgentAndSessionIDAndEndDate2(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String sessioId, java.util.Date date, java.lang.String hostName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("sessioId", sessioId, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("date", date, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("hostName", hostName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAccessLogBySessionIDAndStartDate
	 * @param system
	 * @param sessioId
	 * @param date
	 * @param server
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogBySessionIDAndStartDate(
	    java.lang.String system, 
	    java.lang.String sessioId, 
	    java.util.Date date, 
	    com.soffid.iam.am.model.HostEntity server)
	
	{
		return findAccessLogBySessionIDAndStartDate((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, system, sessioId, date, server);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogBySessionIDAndStartDate(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String sessioId, java.util.Date date, com.soffid.iam.am.model.HostEntity server)
	
	{
		return findAccessLogBySessionIDAndStartDate("select rac\nfrom com.soffid.iam.am.model.AccessLogEntity rac\nwhere rac.sessionId=:sessioId and rac.system = :system and (rac.startDate=:date) and rac.server=:server and rac.tenant.id = :tenantId ",
			criteria, system, sessioId, date, server);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogBySessionIDAndStartDate(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String sessioId, java.util.Date date, com.soffid.iam.am.model.HostEntity server)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("sessioId", sessioId, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("date", date, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("server", server);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAccessLogBySessionIDAndStartDate2
	 * @param system
	 * @param sessioId
	 * @param date
	 * @param hostName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogBySessionIDAndStartDate2(
	    java.lang.String system, 
	    java.lang.String sessioId, 
	    java.util.Date date, 
	    java.lang.String hostName)
	
	{
		return findAccessLogBySessionIDAndStartDate2((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, system, sessioId, date, hostName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogBySessionIDAndStartDate2(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String sessioId, java.util.Date date, java.lang.String hostName)
	
	{
		return findAccessLogBySessionIDAndStartDate2("select rac\nfrom com.soffid.iam.am.model.AccessLogEntity rac\nwhere rac.sessionId=:sessioId and rac.system = :system and (rac.startDate=:date) and rac.hostName=:hostName and rac.tenant.id = :tenantId ",
			criteria, system, sessioId, date, hostName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogBySessionIDAndStartDate2(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String sessioId, java.util.Date date, java.lang.String hostName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("system", system, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("sessioId", sessioId, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("date", date, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("hostName", hostName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findByHostId
	 * @param id
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findByHostId(
	    java.lang.Long id)
	
	{
		return findByHostId((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, id);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findByHostId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		return findByHostId("from com.soffid.iam.am.model.AccessLogEntity rac where rac.server.id=:id or rac.client.id=:id and rac.tenant.id = :tenantId order by rac.startDate",
			criteria, id);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findByHostId(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("id", id, org.hibernate.Hibernate.LONG);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findLastAccessLogByServerAndProtocol
	 * @param server
	 * @param protocol
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findLastAccessLogByServerAndProtocol(
	    java.lang.String server, 
	    java.lang.String protocol)
	
	{
		return findLastAccessLogByServerAndProtocol((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, server, protocol);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findLastAccessLogByServerAndProtocol(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String protocol)
	
	{
		return findLastAccessLogByServerAndProtocol("select registreAcces\nfrom com.soffid.iam.am.model.AccessLogEntity registreAcces\nwhere (:server is null or registreAcces.hostName like :server) and\nregistreAcces.tenant.id = :tenantId  and (:protocol is null or\nregistreAcces.protocol.name = :protocol)\norder by registreAcces.startDate desc",
			criteria, server, protocol);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findLastAccessLogByServerAndProtocol(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String protocol)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("protocol", protocol, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findLastAccessLogByUserName
	 * @param userName
	 * @param protocol
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findLastAccessLogByUserName(
	    java.lang.String userName, 
	    java.lang.String protocol)
	
	{
		return findLastAccessLogByUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, userName, protocol);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findLastAccessLogByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String protocol)
	
	{
		return findLastAccessLogByUserName("select registreAcces\nfrom com.soffid.iam.am.model.AccessLogEntity registreAcces\nleft join registreAcces.user usuari\nwhere (:userName is null or usuari.userName like :userName) and\n(:protocol is null or\nregistreAcces.protocol.name = :protocol) and\nregistreAcces.tenant.id = :tenantId order by registreAcces.startDate",
			criteria, userName, protocol);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findLastAccessLogByUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String protocol)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("protocol", protocol, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAccessLogByStartDateAndUserName
	 * @param nullDate
	 * @param startDate
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByStartDateAndUserName(
	    java.util.Date nullDate, 
	    java.util.Date startDate, 
	    java.lang.String userName)
	
	{
		return findAccessLogByStartDateAndUserName((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nullDate, startDate, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByStartDateAndUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.lang.String userName)
	
	{
		return findAccessLogByStartDateAndUserName("select registreAcces\nfrom com.soffid.iam.am.model.AccessLogEntity registreAcces\nleft join registreAcces.user usuari\nwhere (:userName is null or usuari.userName like :userName) and\n(:startDate = :nullDate or registreAcces.startDate >= :startDate ) and\nregistreAcces.tenant.id = :tenantId   order by registreAcces.startDate",
			criteria, nullDate, startDate, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByStartDateAndUserName(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.lang.String userName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nullDate", nullDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("startDate", startDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAccessLogByStartDateAndUserNameAndProtocol
	 * @param nullDate
	 * @param startDate
	 * @param userName
	 * @param protocol
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByStartDateAndUserNameAndProtocol(
	    java.util.Date nullDate, 
	    java.util.Date startDate, 
	    java.lang.String userName, 
	    java.lang.String protocol)
	
	{
		return findAccessLogByStartDateAndUserNameAndProtocol((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nullDate, startDate, userName, protocol);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByStartDateAndUserNameAndProtocol(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.lang.String userName, java.lang.String protocol)
	
	{
		return findAccessLogByStartDateAndUserNameAndProtocol("select registreAcces\nfrom com.soffid.iam.am.model.AccessLogEntity registreAcces\nleft join registreAcces.user usuari\nwhere (:userName is null or usuari.userName like :userName) and\n(:startDate = :nullDate or registreAcces.startDate >= :startDate ) and\nregistreAcces.protocol.name= :protocol and \nregistreAcces.tenant.id = :tenantId order by registreAcces.startDate desc",
			criteria, nullDate, startDate, userName, protocol);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByStartDateAndUserNameAndProtocol(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.lang.String userName, java.lang.String protocol)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nullDate", nullDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("startDate", startDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("protocol", protocol, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAccessLogByCriteria
	 * @param clientHostName
	 * @param server
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria(
	    java.lang.String clientHostName, 
	    java.lang.String server, 
	    java.lang.String userName)
	
	{
		return findAccessLogByCriteria((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, clientHostName, server, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String clientHostName, java.lang.String server, java.lang.String userName)
	
	{
		return findAccessLogByCriteria("select registreAcces\nfrom com.soffid.iam.am.model.AccessLogEntity registreAcces\nleft join registreAcces.user usuari\nwhere (:clientHostName is null or registreAcces.clientHostName like :clientHostName) and\n(:server is null or registreAcces.hostName  like :server) and\n(:userName is null or usuari.userName like :userName) and registreAcces.tenant.id = :tenantId order by registreAcces.startDate\n",
			criteria, clientHostName, server, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String clientHostName, java.lang.String server, java.lang.String userName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("clientHostName", clientHostName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAccessLogByCriteria
	 * @param nullDate
	 * @param maxDate
	 * @param minDate
	 * @param clientHostName
	 * @param server
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria(
	    java.util.Date nullDate, 
	    java.util.Date maxDate, 
	    java.util.Date minDate, 
	    java.lang.String clientHostName, 
	    java.lang.String server, 
	    java.lang.String userName)
	
	{
		return findAccessLogByCriteria((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nullDate, maxDate, minDate, clientHostName, server, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date maxDate, java.util.Date minDate, java.lang.String clientHostName, java.lang.String server, java.lang.String userName)
	
	{
		return findAccessLogByCriteria("select registreAcces\nfrom com.soffid.iam.am.model.AccessLogEntity registreAcces\nleft join registreAcces.user usuari\nwhere (:clientHostName is null or registreAcces.clientHostName like :clientHostName) and\n(:server is null or registreAcces.hostName like :server) and\n(:userName is null or usuari.userName like :userName) and\n(:maxDate = :nullDate or registreAcces.startDate < :maxDate ) and\n(:minDate = :nullDate or registreAcces.endDate > :minDate ) and registreAcces.tenant.id = :tenantId order by registreAcces.startDate",
			criteria, nullDate, maxDate, minDate, clientHostName, server, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date maxDate, java.util.Date minDate, java.lang.String clientHostName, java.lang.String server, java.lang.String userName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nullDate", nullDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("maxDate", maxDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("minDate", minDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("clientHostName", clientHostName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAccessLogByCriteria2Dates
	 * @param nullDate
	 * @param startDate
	 * @param endDate
	 * @param clientHostName
	 * @param server
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria2Dates(
	    java.util.Date nullDate, 
	    java.util.Date startDate, 
	    java.util.Date endDate, 
	    java.lang.String clientHostName, 
	    java.lang.String server, 
	    java.lang.String userName)
	
	{
		return findAccessLogByCriteria2Dates((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nullDate, startDate, endDate, clientHostName, server, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria2Dates(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.util.Date endDate, java.lang.String clientHostName, java.lang.String server, java.lang.String userName)
	
	{
		return findAccessLogByCriteria2Dates("select registreAcces\nfrom com.soffid.iam.am.model.AccessLogEntity registreAcces\nleft join registreAcces.user usuari\nwhere (:clientHostName is null or registreAcces.clientHostName like :clientHostName) and\n(:server is null or registreAcces.hostName like :server) and\n(:userName is null or usuari.userName like :userName) and\n(:startDate = :nullDate or registreAcces.startDate >= :startDate ) and\n(:endDate = :nullDate or registreAcces.endDate <= :endDate ) and registreAcces.tenant.id = :tenantId order by registreAcces.startDate",
			criteria, nullDate, startDate, endDate, clientHostName, server, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria2Dates(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.util.Date endDate, java.lang.String clientHostName, java.lang.String server, java.lang.String userName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nullDate", nullDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("startDate", startDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("endDate", endDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("clientHostName", clientHostName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAccessLogByCriteria2
	 * @param nullDate
	 * @param startDate
	 * @param endDate
	 * @param clientHostName
	 * @param server
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria2(
	    java.util.Date nullDate, 
	    java.util.Date startDate, 
	    java.util.Date endDate, 
	    java.lang.String clientHostName, 
	    java.lang.String server, 
	    java.lang.String userName)
	
	{
		return findAccessLogByCriteria2((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nullDate, startDate, endDate, clientHostName, server, userName);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria2(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.util.Date endDate, java.lang.String clientHostName, java.lang.String server, java.lang.String userName)
	
	{
		return findAccessLogByCriteria2("select registreAcces\nfrom com.soffid.iam.am.model.AccessLogEntity registreAcces\nleft join registreAcces.user usuari\nwhere (:clientHostName is null or registreAcces.clientHostName like :clientHostName) and\n(:server is null or registreAcces.hostName like :server) and\n(:userName is null or usuari.userName like :userName) and\n(:startDate = :nullDate or registreAcces.endDate >= :startDate ) and\n(:endDate = :nullDate or registreAcces.startDate <= :endDate ) and registreAcces.tenant.id = :tenantId order by registreAcces.startDate",
			criteria, nullDate, startDate, endDate, clientHostName, server, userName);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria2(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.util.Date endDate, java.lang.String clientHostName, java.lang.String server, java.lang.String userName)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nullDate", nullDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("startDate", startDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("endDate", endDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("clientHostName", clientHostName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("userName", userName, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAccessLogByHost
	 * @param nullDate
	 * @param startDate
	 * @param server
	 * @param protocol
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByHost(
	    java.util.Date nullDate, 
	    java.util.Date startDate, 
	    java.lang.String server, 
	    java.lang.String protocol)
	
	{
		return findAccessLogByHost((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nullDate, startDate, server, protocol);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByHost(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.lang.String server, java.lang.String protocol)
	
	{
		return findAccessLogByHost("select registreAcces\nfrom com.soffid.iam.am.model.AccessLogEntity registreAcces\nwhere (:server is null or registreAcces.hostName like :server) and\n(:startDate = :nullDate or registreAcces.startDate >= :startDate ) and\n(:protocol is null or\nregistreAcces.protocol.name = :protocol) and \n registreAcces.tenant.id = :tenantId order by registreAcces.startDate",
			criteria, nullDate, startDate, server, protocol);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByHost(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.lang.String server, java.lang.String protocol)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nullDate", nullDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("startDate", startDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("protocol", protocol, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 * Operation findAccessLogByHostAndStartDateAndProtocol
	 * @param nullDate
	 * @param startDate
	 * @param server
	 * @param protocol
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByHostAndStartDateAndProtocol(
	    java.util.Date nullDate, 
	    java.util.Date startDate, 
	    java.lang.String server, 
	    java.lang.String protocol)
	
	{
		return findAccessLogByHostAndStartDateAndProtocol((com.soffid.iam.model.criteria.CriteriaSearchConfiguration) null			, nullDate, startDate, server, protocol);
	}
	/**
	 * CriteriaSearchConfiguration implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByHostAndStartDateAndProtocol(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.lang.String server, java.lang.String protocol)
	
	{
		return findAccessLogByHostAndStartDateAndProtocol("select registreAcces\nfrom com.soffid.iam.am.model.AccessLogEntity registreAcces\nwhere (:server is null or registreAcces.hostName like :server) and\n(:startDate = :nullDate or registreAcces.startDate >= :startDate ) and\n(:protocol is null or\nregistreAcces.protocol.name = :protocol) and\nregistreAcces.tenant.id = :tenantId  order by registreAcces.startDate desc",
			criteria, nullDate, startDate, server, protocol);
	}
	/**
	 * Internal implementation
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByHostAndStartDateAndProtocol(final java.lang.String queryString, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.lang.String server, java.lang.String protocol)
	
	{
		try
		{
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nullDate", nullDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("startDate", startDate, org.hibernate.Hibernate.TIMESTAMP);
			queryObject.setParameter("server", server, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("protocol", protocol, org.hibernate.Hibernate.STRING);
			queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
			if (criteria != null && criteria.getMaximumResultSize () != null) {
				queryObject.setMaxResults (criteria.getMaximumResultSize ().intValue()); 
			}
			if (criteria != null && criteria.getFirstResult () != null) {
				queryObject.setFirstResult (criteria.getFirstResult().intValue()); 
			}
			java.util.List results = queryObject.list();
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		}
		catch (org.hibernate.HibernateException ex) 
		{
			throw super.convertHibernateAccessException(ex);
		}
	}
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessLog} object 
	 */
	public void toAccessLog(com.soffid.iam.am.model.AccessLogEntity source, com.soffid.iam.am.api.AccessLog target) {
		// Attributes for AccessLog
		target.setId(source.getId());
		target.setSessionId(source.getSessionId());
		if (source.getStartDate() == null) {
			target.setStartDate(null);
		} else {
			target.setStartDate(java.util.Calendar.getInstance());
			target.getStartDate().setTime(source.getStartDate());
		}
		if (source.getEndDate() == null) {
			target.setEndDate(null);
		} else {
			target.setEndDate(java.util.Calendar.getInstance());
			target.getEndDate().setTime(source.getEndDate());
		}
		// Missing attribute codeAge on entity
		target.setInformation(source.getInformation());
		// Missing attribute userName on entity
		// Missing attribute serverName on entity
		// Missing attribute clientName on entity
		target.setClientAddress(source.getClientAddress());
		target.setCountry(source.getCountry());
		target.setAccessType(source.getAccessType());
		// Missing attribute accessProtocol on entity
		// Missing attribute userFullName on entity
		target.setJumpServerGroup(source.getJumpServerGroup());
		target.setAccountName(source.getAccountName());
		target.setTargetApplication(source.getTargetApplication());
		target.setCreatedOn(source.getCreatedOn());
		target.setCreatedBy(source.getCreatedBy());
		target.setUpdatedOn(source.getUpdatedOn());
		target.setUpdatedBy(source.getUpdatedBy());
		target.setDeletedOn(source.getDeletedOn());
		target.setDeletedBy(source.getDeletedBy());
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessLog} object 
	 */
	public com.soffid.iam.am.api.AccessLog toAccessLog(com.soffid.iam.am.model.AccessLogEntity entity) {
		final com.soffid.iam.am.api.AccessLog target = new com.soffid.iam.am.api.AccessLog();
		this.toAccessLog(entity, target);
		return target;
	}

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessLog} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessLog> toAccessLogList (java.util.Collection<com.soffid.iam.am.model.AccessLogEntity> instances) {
		if (instances == null)
			return null;
		else {
			java.util.LinkedList<com.soffid.iam.am.api.AccessLog> list =
				new java.util.LinkedList<com.soffid.iam.am.api.AccessLog>();
			for (final com.soffid.iam.am.model.AccessLogEntity instance: instances)
			{
				list.add( toAccessLog(instance));
			}
			return list;
		}
	}
	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessLog} object 
	 */
	public void accessLogToEntity (com.soffid.iam.am.api.AccessLog source, com.soffid.iam.am.model.AccessLogEntity target, boolean copyIfNull) {
		// Attributes for AccessLogEntity
		if (copyIfNull || source.getSessionId() != null)
		{
			target.setSessionId(source.getSessionId());
		}
		if (copyIfNull || source.getStartDate() != null)
		{
			if (source.getStartDate() == null) {
				target.setStartDate(null);
			} else {
				target.setStartDate(source.getStartDate().getTime());
			}
		}
		if (copyIfNull || source.getEndDate() != null)
		{
			if (source.getEndDate() == null) {
				target.setEndDate(null);
			} else {
				target.setEndDate(source.getEndDate().getTime());
			}
		}
		// Missing attribute system on entity
		if (copyIfNull || source.getInformation() != null)
		{
			target.setInformation(source.getInformation());
		}
		// Missing attribute server on entity
		// Missing attribute client on entity
		// Missing attribute browser on entity
		// Missing attribute protocol on entity
		// Missing attribute user on entity
		if (copyIfNull || source.getAccessType() != null)
		{
			target.setAccessType(source.getAccessType());
		}
		if (copyIfNull || source.getClientAddress() != null)
		{
			target.setClientAddress(source.getClientAddress());
		}
		// Missing attribute clientHostName on entity
		// Missing attribute hostAddress on entity
		// Missing attribute hostName on entity
		if (copyIfNull || source.getCountry() != null)
		{
			target.setCountry(source.getCountry());
		}
		// Missing attribute tenant on entity
		if (copyIfNull || source.getJumpServerGroup() != null)
		{
			target.setJumpServerGroup(source.getJumpServerGroup());
		}
		if (copyIfNull || source.getAccountName() != null)
		{
			target.setAccountName(source.getAccountName());
		}
		if (copyIfNull || source.getTargetApplication() != null)
		{
			target.setTargetApplication(source.getTargetApplication());
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
	 *  Transforms from {@link com.soffid.iam.am.api.AccessLog} object 
	 */
	public com.soffid.iam.am.model.AccessLogEntity accessLogToEntity (com.soffid.iam.am.api.AccessLog instance) {
		com.soffid.iam.am.model.AccessLogEntity entity = null;
		if (instance.getId() != null) 
			entity = load(instance.getId());
		if (entity == null)
			entity = newAccessLogEntity();
		accessLogToEntity(instance, entity, true);
		return entity;
	}

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessLog} list 
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity>  accessLogToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessLog> instances) 
	{
		if (instances == null)
			return null;
		java.util.LinkedList<com.soffid.iam.am.model.AccessLogEntity> list =
			new java.util.LinkedList<com.soffid.iam.am.model.AccessLogEntity>();
		for (com.soffid.iam.am.api.AccessLog instance: instances)
		{
			list.add (accessLogToEntity(instance));
		}
		return list;
	}

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.AccessLogEntity} .
	 */
	public com.soffid.iam.am.model.AccessLogEntity newAccessLogEntity()
	{
		return new com.soffid.iam.am.model.AccessLogEntityImpl();
	}

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.AccessLogEntity load(java.lang.Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		com.soffid.iam.am.model.AccessLogEntity result = (com.soffid.iam.am.model.AccessLogEntity) this.getHibernateTemplate().get(com.soffid.iam.am.model.AccessLogEntityImpl.class, id);

		if ( result != null && ! com.soffid.iam.utils.Security.isAuthorizedTenant( result.getTenant())) 
			return null;

		return result;
	}
	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> loadAll() {
		org.hibernate.Query queryObject = super.getSession(false).createQuery
			("from com.soffid.iam.am.model.AccessLogEntity where tenant.id=:tenantId");
		queryObject.setParameter("tenantId", com.soffid.iam.utils.Security.getCurrentTenantId());
		return (java.util.List<com.soffid.iam.am.model.AccessLogEntity> ) queryObject.list();
	};

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.AccessLogEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.AccessLogEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccessLogEntityDao.create - 'entity' can not be null");
		}

		entity.setTenant  ( getTenantEntityDao().load (com.soffid.iam.utils.Security.getCurrentTenantId()) );
		entity.setCreatedOn(new java.util.Date());
		entity.setCreatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.AccessLogEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.AccessLogEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccessLogEntityDao.update - 'entity' can not be null");
		}
		entity.setUpdatedOn(new java.util.Date());
		entity.setUpdatedBy(com.soffid.iam.utils.Security.getCurrentAccount());
		this.getHibernateTemplate().update(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.AccessLogEntity entity)
	{
		if (entity == null)
		{
			throw new IllegalArgumentException(
				"AccessLogEntityDao.remove - 'entity' can not be null");
		}
		this.getHibernateTemplate().delete(entity);
		this.getHibernateTemplate().flush();
	}

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.AccessLogEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.AccessLogEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccessLogEntityDao.create - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.AccessLogEntity entity: entities) { 
			create(entity);
		}
	}

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.AccessLogEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.AccessLogEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccessLogEntityDao.update - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.AccessLogEntity entity: entities) { 
			update(entity);
		}
	}

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.AccessLogEntity> entities) {
		if (entities == null)
		{
			throw new IllegalArgumentException(
				"AccessLogEntityDao.remove - 'entities' cannot be null");
		}
		for (com.soffid.iam.am.model.AccessLogEntity entity: entities) { 
			remove(entity);
		}
	}

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id)
	{
		if (id == null) 
		{
			throw new IllegalArgumentException(
				"AccessLogEntityDao.remove - 'id' can not be null");
		}
		com.soffid.iam.am.model.AccessLogEntity entity = this.load(id);
		if (entity != null)
			this.remove(entity);
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters);
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

	/**
	 * Query of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> query (String queryString, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	{
		try {
			java.util.List results = new com.soffid.iam.model.QueryBuilder().query(this,
				queryString, parameters, criteria);
			return (java.util.List<com.soffid.iam.am.model.AccessLogEntity>) results;
		} catch (org.hibernate.HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}

}
