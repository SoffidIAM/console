//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service.ejb;
/**
 * EJB AccessLogService
 */
public interface AccessLogService

 {

	com.soffid.iam.am.api.AccessLog findAccessLogById(
		final java.lang.Long id)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessLog> findAccessLogs(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

}
