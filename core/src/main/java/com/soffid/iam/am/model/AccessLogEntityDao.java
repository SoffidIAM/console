//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity AccessLogEntity
 * @see com.soffid.iam.am.model.AccessLogEntity
 */
public interface AccessLogEntityDao

{
	/**
	 * Operation findLastDateBySystem
	 * @param system
	 * @return
	**/
	public java.util.Date findLastDateBySystem(
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Date findLastDateBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	;
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
		com.soffid.iam.am.model.HostEntity server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByAgentAndSessionIDAndEndDate(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String sessioId, java.util.Date date, com.soffid.iam.am.model.HostEntity server)
	;
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
		java.lang.String hostName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByAgentAndSessionIDAndEndDate2(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String sessioId, java.util.Date date, java.lang.String hostName)
	;
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
		com.soffid.iam.am.model.HostEntity server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogBySessionIDAndStartDate(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String sessioId, java.util.Date date, com.soffid.iam.am.model.HostEntity server)
	;
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
		java.lang.String hostName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogBySessionIDAndStartDate2(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String sessioId, java.util.Date date, java.lang.String hostName)
	;
	/**
	 * Operation findByHostId
	 * @param id
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findByHostId(
		java.lang.Long id)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findByHostId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	;
	/**
	 * Operation findLastAccessLogByServerAndProtocol
	 * @param server
	 * @param protocol
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findLastAccessLogByServerAndProtocol(
		java.lang.String server, 
		java.lang.String protocol)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findLastAccessLogByServerAndProtocol(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String protocol)
	;
	/**
	 * Operation findLastAccessLogByUserName
	 * @param userName
	 * @param protocol
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findLastAccessLogByUserName(
		java.lang.String userName, 
		java.lang.String protocol)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findLastAccessLogByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String protocol)
	;
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
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByStartDateAndUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.lang.String userName)
	;
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
		java.lang.String protocol)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByStartDateAndUserNameAndProtocol(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.lang.String userName, java.lang.String protocol)
	;
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
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String clientHostName, java.lang.String server, java.lang.String userName)
	;
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
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date maxDate, java.util.Date minDate, java.lang.String clientHostName, java.lang.String server, java.lang.String userName)
	;
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
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria2Dates(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.util.Date endDate, java.lang.String clientHostName, java.lang.String server, java.lang.String userName)
	;
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
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByCriteria2(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.util.Date endDate, java.lang.String clientHostName, java.lang.String server, java.lang.String userName)
	;
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
		java.lang.String protocol)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByHost(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.lang.String server, java.lang.String protocol)
	;
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
		java.lang.String protocol)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> findAccessLogByHostAndStartDateAndProtocol(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date nullDate, java.util.Date startDate, java.lang.String server, java.lang.String protocol)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.AccessLog} object 
	 */
	public void toAccessLog(com.soffid.iam.am.model.AccessLogEntity source, com.soffid.iam.am.api.AccessLog target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessLog} object 
	 */
	public com.soffid.iam.am.api.AccessLog toAccessLog(com.soffid.iam.am.model.AccessLogEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.AccessLog} list 
	 */
	public java.util.List<com.soffid.iam.am.api.AccessLog> toAccessLogList (java.util.Collection<com.soffid.iam.am.model.AccessLogEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.AccessLog} object 
	 */
	public void accessLogToEntity (com.soffid.iam.am.api.AccessLog source, com.soffid.iam.am.model.AccessLogEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessLog} object 
	 */
	public com.soffid.iam.am.model.AccessLogEntity accessLogToEntity (com.soffid.iam.am.api.AccessLog instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.AccessLog} list 
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity>  accessLogToEntityList (java.util.Collection<com.soffid.iam.am.api.AccessLog> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.AccessLogEntity} .
	 */
	public com.soffid.iam.am.model.AccessLogEntity newAccessLogEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.AccessLogEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.AccessLogEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.AccessLogEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.AccessLogEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.AccessLogEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.AccessLogEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.AccessLogEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.AccessLogEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.AccessLogEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.AccessLogEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.AccessLogEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.AccessLogEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.AccessLogEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
