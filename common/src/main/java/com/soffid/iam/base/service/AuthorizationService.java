//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
/**
 * Service AuthorizationService
 * Manages find-grained, low-level authorization to Soffid objects
 */
public interface AuthorizationService {
	public final static String SERVICE_NAME = "com.soffid.iam.base.service.AuthorizationService";

	/**
	 * Operation hasPermission
	 * Returns true if the user has the selected low-level permission on the selected objects

	 * @param action 
	 * @param object 
	 * @return 
	 */
	boolean hasPermission(
		final java.lang.String action, 
		final java.lang.Object object)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create
	 * Grants a low-level authorizations to role

	 * @param autoritzacio 
	 * @return 
	 */
	com.soffid.iam.base.api.AuthorizationRole create(
		final com.soffid.iam.base.api.AuthorizationRole autoritzacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getCurrentPrincipal
	 * Returns current security principal

	 * @return 
	 */
	com.soffid.iam.common.security.SoffidPrincipal getCurrentPrincipal()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAuthorizationString
	 * Gets the low-level authorizations granted to a user

	 * @param codiAutoritzacio 
	 * @return 
	 */
	java.lang.String[] getUserAuthorizationString(
		final java.lang.String codiAutoritzacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAuthorizationString
	 * Gets the low-level authorizations granted to a user of an specific type

	 * @param athorizationName 
	 * @param userName 
	 * @return 
	 */
	java.lang.String[] getUserAuthorizationString(
		final java.lang.String athorizationName, 
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAuthorizationsString
	 * Get the low-level authorizations granted to current user

	 * @return 
	 */
	java.lang.String[] getUserAuthorizationsString()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAuthorizationsString
	 * Get the low-level authorizations granted to a user

	 * @param codiUsuari 
	 * @return 
	 */
	java.lang.String[] getUserAuthorizationsString(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAuthorizationsString
	 * Gets the autohrization given some login process properties

	 * @param userName 
	 * @param loginProperties 
	 * @return 
	 */
	java.lang.String[] getUserAuthorizationsString(
		final java.lang.String userName, 
		final java.util.Map<java.lang.String,java.lang.String> loginProperties)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserGroupAuthorizationString
	 * Gets the low-level authorizations granted to a user as a member of a business group

	 * @param userName 
	 * @param holderGroup 
	 * @return 
	 */
	java.lang.String[] getUserGroupAuthorizationString(
		final java.lang.String userName, 
		final java.lang.String holderGroup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAuthorizations
	 * Get the complete list of low-level authorizations.

	 * @param ambit 
	 * @param descripcio 
	 * @param codi 
	 * @return 
	 */
	java.util.Collection findAuthorizations(
		final java.lang.String ambit, 
		final java.lang.String descripcio, 
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDescriptionUserAuthorizations
	 * Gets the description of low-level authorizations to current user

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getDescriptionUserAuthorizations()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDescriptionUserAuthorizations
	 * Get the description of low-level authorizations granted to a user

	 * @param userName 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getDescriptionUserAuthorizations(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAuthorizationInfo
	 * Get the detail about a low-level authorization.

	 * @param autoritzacio 
	 * @return 
	 */
	java.util.Collection<java.lang.Object> getAuthorizationInfo(
		final java.lang.String autoritzacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAuthorizationRoles
	 * Get the roles granted with an specific low-level authorization

	 * @param authorization 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getAuthorizationRoles(
		final java.lang.String authorization)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAuthorization
	 * Get the roles granted with a low-level authorization.

	 * @param codiAutoritzacio 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorization(
		final java.lang.String codiAutoritzacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAuthorization
	 * Get the low-level authorizations of a specific type granted to a user

	 * @param codiAutoritzacio 
	 * @param codiUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorization(
		final java.lang.String codiAutoritzacio, 
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAuthorizations
	 * Get low level authorizations for current user

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> getUserAuthorizations()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAuthorizations
	 * Get the low-level authorizations granted to a user

	 * @param userName 
	 * @return 
	 */
	java.util.Collection getUserAuthorizations(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getScopeList
	 * Get the list of scopes that can be applied to a low-level authorization

	 * @return 
	 */
	java.util.List getScopeList()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete
	 * Revokes a low-level authorization to a role

	 * @param authorization 
	 */
	void delete(
		final com.soffid.iam.base.api.AuthorizationRole authorization)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
