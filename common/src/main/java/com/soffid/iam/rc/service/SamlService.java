//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
/**
 * Service SamlService
 */
public interface SamlService {
	public final static String SERVICE_NAME = "com.soffid.iam.rc.service.SamlService";

	/**
	 * Operation generateSamlRequest
	 * Generates a SAML request to formard to the IdP

	 * @param hostName 
	 * @param app 
	 * @return 
	 */
	com.soffid.iam.am.api.SamlRequest generateSamlRequest(
		final java.lang.String hostName, 
		final java.lang.String app)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkAuthenticationToken
	 * Validates the single use username and password generated on previous step. Returns the underlying account name

	 * @param token 
	 * @return 
	 */
	java.lang.String checkAuthenticationToken(
		final java.lang.String[] token)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateMetadata
	 * Generates SAML metadata to publish to SAML federation discovery database

	 * @param hostName 
	 * @return 
	 */
	java.lang.String generateMetadata(
		final java.lang.String hostName)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation validateOpenidToken

	 * @param token 
	 * @return 
	 */
	java.lang.String validateOpenidToken(
		final java.lang.String token)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation authenticate
	 * Validates the SAML response, and returns a single use username and password

	 * @param hostName 
	 * @param app 
	 * @param protocol 
	 * @param response 
	 * @return 
	 */
	java.lang.String[] authenticate(
		final java.lang.String hostName, 
		final java.lang.String app, 
		final java.lang.String protocol, 
		final java.util.Map<java.lang.String,java.lang.String> response)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findIdentityProviders
	 * Gets the list of Identity Providers from medatata URL

	 * @return 
	 */
	java.util.List<java.lang.String> findIdentityProviders()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findIdentityProviders
	 * Gets the list of Identity Providers from arbitrary URL

	 * @param url 
	 * @return 
	 */
	java.util.List<java.lang.String> findIdentityProviders(
		final java.lang.String url)
			throws com.soffid.iam.exception.InternalErrorException;

}
