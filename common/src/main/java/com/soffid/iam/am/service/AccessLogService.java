//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
/**
 * Service AccessLogService
 */
public interface AccessLogService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.am.service.AccessLogService";

	public final static String SERVICE_NAME = "com.soffid.iam.am.service.AccessLogService";

	/**
	 * Operation create

	 * @param registre 
	 * @return 
	 */
	com.soffid.iam.am.api.AccessLog create(
		final com.soffid.iam.am.api.AccessLog registre)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccessLogById

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.am.api.AccessLog findAccessLogById(
		final java.lang.Long id)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccessLogs

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessLog> findAccessLogs(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

}
