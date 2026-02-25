//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service LogonService
 */
public interface LogonService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.sync.service.LogonService";

	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.LogonService";

	/**
	 * Operation mustChangePassword

	 * @param user 
	 * @param domain 
	 * @return 
	 */
	boolean mustChangePassword(
		final java.lang.String user, 
		final java.lang.String domain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.rmi.RemoteException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation validatePIN

	 * @param user 
	 * @param pin 
	 * @return 
	 */
	boolean validatePIN(
		final java.lang.String user, 
		final java.lang.String pin)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.rmi.RemoteException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getChallenge

	 * @param challengeId 
	 * @return 
	 */
	com.soffid.iam.am.api.Challenge getChallenge(
		final java.lang.String challengeId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation requestChallenge

	 * @param type 
	 * @param user 
	 * @param domain 
	 * @param host 
	 * @param clientHost 
	 * @param cardSupport 
	 * @return 
	 */
	com.soffid.iam.am.api.Challenge requestChallenge(
		final int type, 
		final java.lang.String user, 
		final java.lang.String domain, 
		final java.lang.String host, 
		final java.lang.String clientHost, 
		final int cardSupport)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.rmi.RemoteException, com.soffid.iam.exception.LogonDeniedException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation requestIdpChallenge

	 * @param type 
	 * @param user 
	 * @param domain 
	 * @param host 
	 * @param clientHost 
	 * @param cardSupport 
	 * @param identityProvider 
	 * @return 
	 */
	com.soffid.iam.am.api.Challenge requestIdpChallenge(
		final int type, 
		final java.lang.String user, 
		final java.lang.String domain, 
		final java.lang.String host, 
		final java.lang.String clientHost, 
		final int cardSupport, 
		final java.lang.String identityProvider)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.rmi.RemoteException, com.soffid.iam.exception.LogonDeniedException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation validatePassword

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordValidation validatePassword(
		final java.lang.String user, 
		final java.lang.String passwordDomain, 
		final java.lang.String password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.rmi.RemoteException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation responseChallenge

	 * @param result 
	 * @return 
	 */
	com.soffid.iam.am.api.Session responseChallenge(
		final com.soffid.iam.am.api.Challenge result)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.rmi.RemoteException, com.soffid.iam.exception.LogonDeniedException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPasswordPolicy

	 * @param user 
	 * @param domain 
	 * @return 
	 */
	java.lang.String getPasswordPolicy(
		final java.lang.String user, 
		final java.lang.String domain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation changePassword

	 * @param user 
	 * @param domain 
	 * @param oldPassword 
	 * @param newPassword 
	 */
	void changePassword(
		final java.lang.String user, 
		final java.lang.String domain, 
		final java.lang.String oldPassword, 
		final java.lang.String newPassword)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException, com.soffid.iam.exception.InvalidPasswordException, java.rmi.RemoteException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation propagatePassword

	 * @param user 
	 * @param domain 
	 * @param password 
	 */
	void propagatePassword(
		final java.lang.String user, 
		final java.lang.String domain, 
		final java.lang.String password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.rmi.RemoteException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation purgeChallenges

	 */
	void purgeChallenges()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerChallenge

	 * @param challenge 
	 */
	void registerChallenge(
		final com.soffid.iam.am.api.Challenge challenge)
			throws com.soffid.iam.exception.InternalErrorException;

}
