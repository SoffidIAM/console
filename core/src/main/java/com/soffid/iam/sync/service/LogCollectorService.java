//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service LogCollectorService
 */
public interface LogCollectorService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.sync.service.LogCollectorService";

	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.LogCollectorService";

	/**
	 * Operation getLastLogEntryDate

	 * @param dispatcher 
	 * @return 
	 */
	java.util.Date getLastLogEntryDate(
		final java.lang.String dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerFailedLogon

	 * @param dispatcher 
	 * @param sessionId 
	 * @param date 
	 * @param user 
	 * @param server 
	 * @param client 
	 * @param protocol 
	 * @param info 
	 */
	void registerFailedLogon(
		final java.lang.String dispatcher, 
		final java.lang.String sessionId, 
		final java.util.Date date, 
		final java.lang.String user, 
		final java.lang.String server, 
		final java.lang.String client, 
		final java.lang.String protocol, 
		final java.lang.String info)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException, com.soffid.iam.exception.UnknownHostException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerLogoff

	 * @param dispatcher 
	 * @param sessionId 
	 * @param date 
	 * @param user 
	 * @param server 
	 * @param client 
	 * @param protocol 
	 * @param info 
	 */
	void registerLogoff(
		final java.lang.String dispatcher, 
		final java.lang.String sessionId, 
		final java.util.Date date, 
		final java.lang.String user, 
		final java.lang.String server, 
		final java.lang.String client, 
		final java.lang.String protocol, 
		final java.lang.String info)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownHostException, com.soffid.iam.exception.UnknownUserException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerLogon

	 * @param dispatcher 
	 * @param sessionId 
	 * @param date 
	 * @param user 
	 * @param server 
	 * @param client 
	 * @param protocol 
	 * @param info 
	 */
	void registerLogon(
		final java.lang.String dispatcher, 
		final java.lang.String sessionId, 
		final java.util.Date date, 
		final java.lang.String user, 
		final java.lang.String server, 
		final java.lang.String client, 
		final java.lang.String protocol, 
		final java.lang.String info)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException, com.soffid.iam.exception.UnknownHostException, com.soffid.iam.exception.InternalErrorException;

}
