//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service SecretConfigurationService
 */
public interface SecretConfigurationService {
	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.SecretConfigurationService";

	/**
	 * Operation validateAuthToken

	 * @param token 
	 * @return 
	 */
	boolean validateAuthToken(
		final java.lang.String token)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getCurrentServer

	 * @return 
	 */
	com.soffid.iam.sync.api.Server getCurrentServer()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPrivateKey

	 * @return 
	 */
	java.security.PrivateKey getPrivateKey()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAllServers

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.Server> getAllServers()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation changeAuthToken

	 */
	void changeAuthToken()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
