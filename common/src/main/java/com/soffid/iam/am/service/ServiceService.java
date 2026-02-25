//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
/**
 * Service ServiceService
 */
public interface ServiceService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.am.service.ServiceService";

	public final static String SERVICE_NAME = "com.soffid.iam.am.service.ServiceService";

	/**
	 * Operation create

	 * @param servei 
	 * @return 
	 */
	com.soffid.iam.am.api.Service create(
		final com.soffid.iam.am.api.Service servei)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findServiceByName

	 * @param codi 
	 * @return 
	 */
	com.soffid.iam.am.api.Service findServiceByName(
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param servei 
	 * @return 
	 */
	com.soffid.iam.am.api.Service update(
		final com.soffid.iam.am.api.Service servei)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findServices

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Service> findServices(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getServices

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.Service> getServices()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param servei 
	 */
	void delete(
		final com.soffid.iam.am.api.Service servei)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
