//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
/**
 * Service SessionService
 */
public interface SessionService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.am.service.SessionService";

	public final static String SERVICE_NAME = "com.soffid.iam.am.service.SessionService";

	/**
	 * Operation getSessionByHost

	 * @param id 
	 * @param hostIp 
	 * @return 
	 */
	com.soffid.iam.am.api.Session getSessionByHost(
		final long id, 
		final java.lang.String hostIp)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSession

	 * @param id 
	 * @param key 
	 * @return 
	 */
	com.soffid.iam.am.api.Session getSession(
		final long id, 
		final java.lang.String key)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation joinEssoSession

	 * @param id 
	 * @param key 
	 * @param port 
	 * @return 
	 */
	com.soffid.iam.am.api.Session joinEssoSession(
		final long id, 
		final java.lang.String key, 
		final int port)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerBrowserSession

	 * @param userName 
	 * @param browserSerialNumber 
	 * @param ipAddress 
	 * @param sessionType 
	 * @param checkUrl 
	 * @param pamMonitorUrl 
	 * @param serviceProvider 
	 * @param authenticationMethod 
	 * @return 
	 */
	com.soffid.iam.am.api.Session registerBrowserSession(
		final java.lang.String userName, 
		final java.lang.String browserSerialNumber, 
		final java.lang.String ipAddress, 
		final com.soffid.iam.am.api.SessionType sessionType, 
		final java.lang.String checkUrl, 
		final java.lang.String pamMonitorUrl, 
		final java.lang.String serviceProvider, 
		final java.lang.String authenticationMethod)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation registerSession

	 * @param codiUsuari 
	 * @param hostKey 
	 * @param clientHost 
	 * @param port 
	 * @param key 
	 * @param authenticationMethod 
	 * @return 
	 */
	com.soffid.iam.am.api.Session registerSession(
		final java.lang.String codiUsuari, 
		final java.lang.String hostKey, 
		final java.lang.String clientHost, 
		final int port, 
		final java.lang.String key, 
		final java.lang.String authenticationMethod)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateTransientKey

	 * @param id 
	 * @param key 
	 * @return 
	 */
	java.lang.String updateTransientKey(
		final long id, 
		final java.lang.String key)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findActiveSessions

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.Session> findActiveSessions()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getActiveSessions

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.Session> getActiveSessions()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getActiveSessions

	 * @param idUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.Session> getActiveSessions(
		final long idUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation cleanTransientKey

	 * @param id 
	 * @param key 
	 */
	void cleanTransientKey(
		final long id, 
		final java.lang.String key)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation destroySession

	 * @param sessio 
	 */
	void destroySession(
		final com.soffid.iam.am.api.Session sessio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sessionKeepAlive

	 * @param session 
	 */
	void sessionKeepAlive(
		final com.soffid.iam.am.api.Session session)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
