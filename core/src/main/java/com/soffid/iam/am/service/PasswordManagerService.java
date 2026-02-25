//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
/**
 * Service PasswordManagerService
 */
public interface PasswordManagerService {
	public final static String SERVICE_NAME = "com.soffid.iam.am.service.PasswordManagerService";

	/**
	 * Operation findUserByToken

	 * @param token 
	 * @return 
	 */
	java.lang.String findUserByToken(
		final java.lang.String token)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateToken

	 * @param user 
	 * @return 
	 */
	java.lang.String generateToken(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation renewToken

	 * @param token 
	 * @return 
	 */
	java.lang.String renewToken(
		final java.lang.String token)
			throws com.soffid.iam.exception.InternalErrorException;

}
