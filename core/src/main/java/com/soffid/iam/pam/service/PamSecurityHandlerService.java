//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service;
/**
 * Service PamSecurityHandlerService
 */
public interface PamSecurityHandlerService {
	public final static String SERVICE_NAME = "com.soffid.iam.pam.service.PamSecurityHandlerService";

	/**
	 * Operation checkPermissionImpl

	 * @param account 
	 * @param entryPoint 
	 * @param jumpServerGroup 
	 * @param url 
	 * @param action 
	 * @return 
	 */
	com.soffid.iam.pam.api.PamSecurityCheck checkPermissionImpl(
		final com.soffid.iam.base.model.AccountEntity account, 
		final java.lang.String entryPoint, 
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String action)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getObligations

	 * @param account 
	 * @param entryPoint 
	 * @param jumpServerGroup 
	 * @param url 
	 * @param action 
	 * @return 
	 */
	com.soffid.iam.pam.api.PamSecurityCheck getObligations(
		final com.soffid.iam.base.model.AccountEntity account, 
		final java.lang.String entryPoint, 
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String action)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkPermission

	 * @param account 
	 * @param entryPoint 
	 * @param jumpServerGroup 
	 * @param url 
	 * @param action 
	 */
	void checkPermission(
		final com.soffid.iam.base.model.AccountEntity account, 
		final java.lang.String entryPoint, 
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String action)
			throws com.soffid.iam.exception.InternalErrorException;

}
