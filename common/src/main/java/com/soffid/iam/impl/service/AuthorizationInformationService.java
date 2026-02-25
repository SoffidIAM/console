//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service AuthorizationInformationService
 */
public interface AuthorizationInformationService {
	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.AuthorizationInformationService";

	/**
	 * Operation interventionNeeded

	 * @param codiAplicacio 
	 * @param codisRols 
	 * @return 
	 */
	boolean interventionNeeded(
		final java.lang.String codiAplicacio, 
		final java.lang.String[] codisRols)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationByApplicationCode

	 * @param codiAplicacio 
	 * @return 
	 */
	com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationCode(
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSystemsRoles

	 * @param codiAplicacio 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role getSystemsRoles(
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isApplicationManager

	 * @param codiUsuari 
	 * @param codiApliacio 
	 * @return 
	 */
	java.lang.Boolean isApplicationManager(
		final java.lang.String codiUsuari, 
		final java.lang.String codiApliacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findApplicationManagersByApplicationCode

	 * @param codiAplicacio 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.User> findApplicationManagersByApplicationCode(
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findManagedApplicationsByUserCode

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.InformationSystem> findManagedApplicationsByUserCode(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRolesByApplicationCode

	 * @param codiAplicacio 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationCode(
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRolesByApplicationCodeUnrestricted

	 * @param codiAplicacio 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationCodeUnrestricted(
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRolesByUserCode

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByUserCode(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getApplicationsByUserCode

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.InformationSystem> getApplicationsByUserCode(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getApplicationRolesByUserCodeAndApplicationCode

	 * @param codiUsuari 
	 * @param codiAplicacio 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> getApplicationRolesByUserCodeAndApplicationCode(
		final java.lang.String codiUsuari, 
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getRolesByUserCode

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Role> getRolesByUserCode(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
