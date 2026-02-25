//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
/**
 * Service AuditService
 */
public interface AuditService {
	public final static String SERVICE_NAME = "com.soffid.iam.rc.service.AuditService";

	/**
	 * Operation create

	 * @param auditoria 
	 * @return 
	 */
	com.soffid.iam.rc.api.Audit create(
		final com.soffid.iam.rc.api.Audit auditoria)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAuditById

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.rc.api.Audit findAuditById(
		final java.lang.Long id)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAudits

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Audit> findAudits(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
