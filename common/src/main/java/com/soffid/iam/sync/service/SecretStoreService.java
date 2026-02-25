//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service SecretStoreService
 */
public interface SecretStoreService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.sync.service.SecretStoreService";

	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.SecretStoreService";

	/**
	 * Operation getPassword

	 * @param accountId 
	 * @return 
	 */
	com.soffid.iam.am.api.Password getPassword(
		final long accountId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSecret

	 * @param user 
	 * @param secret 
	 * @return 
	 */
	com.soffid.iam.am.api.Password getSecret(
		final com.soffid.iam.base.api.User user, 
		final java.lang.String secret)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSshPrivateKey

	 * @param accountId 
	 * @return 
	 */
	com.soffid.iam.am.api.Password getSshPrivateKey(
		final long accountId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountsWithPassword

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.Account> getAccountsWithPassword()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUsersWithSecrets

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.User> getUsersWithSecrets()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAllSecrets

	 * @param user 
	 * @return 
	 */
	java.util.List<com.soffid.iam.sync.api.Secret> getAllSecrets(
		final com.soffid.iam.base.api.User user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSecrets

	 * @param user 
	 * @return 
	 */
	java.util.List<com.soffid.iam.sync.api.Secret> getSecrets(
		final com.soffid.iam.base.api.User user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation putSecret

	 * @param user 
	 * @param secret 
	 * @param value 
	 */
	void putSecret(
		final com.soffid.iam.base.api.User user, 
		final java.lang.String secret, 
		final com.soffid.iam.am.api.Password value)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reencode

	 * @param user 
	 */
	void reencode(
		final com.soffid.iam.base.api.User user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeSecret

	 * @param user 
	 * @param secret 
	 */
	void removeSecret(
		final com.soffid.iam.base.api.User user, 
		final java.lang.String secret)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setPassword

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 */
	void setPassword(
		final com.soffid.iam.base.api.User user, 
		final java.lang.String passwordDomain, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setPassword

	 * @param accountId 
	 * @param value 
	 */
	void setPassword(
		final long accountId, 
		final com.soffid.iam.am.api.Password value)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setPasswordAndUpdateAccount

	 * @param accountId 
	 * @param value 
	 * @param mustChange 
	 * @param expirationDate 
	 */
	void setPasswordAndUpdateAccount(
		final long accountId, 
		final com.soffid.iam.am.api.Password value, 
		final boolean mustChange, 
		final java.util.Date expirationDate)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setSshPrivateKey

	 * @param accountId 
	 * @param privateKey 
	 */
	void setSshPrivateKey(
		final long accountId, 
		final com.soffid.iam.am.api.Password privateKey)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
