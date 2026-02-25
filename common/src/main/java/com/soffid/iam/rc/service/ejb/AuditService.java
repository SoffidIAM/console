//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service.ejb;
/**
 * EJB AuditService
 */
public interface AuditService

 {

	com.soffid.iam.rc.api.Audit findAuditById(
		final java.lang.Long id)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Audit> findAudits(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
