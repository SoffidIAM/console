//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service InternalPasswordService
 */
public interface InternalPasswordService {
	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.InternalPasswordService";

	/**
	 * Operation checkPin

	 * @param user 
	 * @param pin 
	 * @return 
	 */
	boolean checkPin(
		final com.soffid.iam.base.model.UserEntity user, 
		final java.lang.String pin)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation existsAccountPassword

	 * @param account 
	 * @return 
	 */
	boolean existsAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation existsPassword

	 * @param user 
	 * @param passwordDomain 
	 * @return 
	 */
	boolean existsPassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isAccountPasswordExpired

	 * @param account 
	 * @return 
	 */
	boolean isAccountPasswordExpired(
		final com.soffid.iam.base.model.AccountEntity account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isLastPasswordIdForUser

	 * @param id 
	 * @return 
	 */
	boolean isLastPasswordIdForUser(
		final long id)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isOldAccountPassword

	 * @param account 
	 * @param password 
	 * @return 
	 */
	boolean isOldAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isOldPassword

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 * @return 
	 */
	boolean isOldPassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isPasswordExpired

	 * @param user 
	 * @param passwordDomain 
	 * @return 
	 */
	boolean isPasswordExpired(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateExpiredPasswords

	 * @param usuari 
	 * @param externalAuth 
	 * @return 
	 */
	boolean updateExpiredPasswords(
		final com.soffid.iam.base.model.UserEntity usuari, 
		final boolean externalAuth)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateFakeAccountPassword

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.Password generateFakeAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateFakePassword

	 * @param user 
	 * @param passDomain 
	 * @return 
	 */
	com.soffid.iam.am.api.Password generateFakePassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passDomain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateNewAccountPassword

	 * @param account 
	 * @param mustBeChanged 
	 * @return 
	 */
	com.soffid.iam.am.api.Password generateNewAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final boolean mustBeChanged)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateNewPassword

	 * @param user 
	 * @param passDomain 
	 * @param mustBeChanged 
	 * @return 
	 */
	com.soffid.iam.am.api.Password generateNewPassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passDomain, 
		final boolean mustBeChanged)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountPasswordsStatus

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordStatus getAccountPasswordsStatus(
		final com.soffid.iam.base.model.AccountEntity account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountPasswordsStatusById

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordStatus getAccountPasswordsStatusById(
		final long account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPasswordsStatus

	 * @param user 
	 * @param domini 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordStatus getPasswordsStatus(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity domini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPasswordsStatusById

	 * @param user 
	 * @param domini 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordStatus getPasswordsStatusById(
		final long user, 
		final long domini)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkAccountPassword

	 * @param account 
	 * @param password 
	 * @param checkTrusted 
	 * @param checkExpired 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordValidation checkAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean checkTrusted, 
		final boolean checkExpired)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkPassword

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 * @param checkTrusted 
	 * @param checkExpired 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordValidation checkPassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password, 
		final boolean checkTrusted, 
		final boolean checkExpired)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkAccountPolicy

	 * @param account 
	 * @param password 
	 * @return 
	 */
	com.soffid.iam.am.api.PolicyCheckResult checkAccountPolicy(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkPolicy

	 * @param policy 
	 * @param password 
	 * @return 
	 */
	com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final com.soffid.iam.am.model.PasswordPolicyEntity policy, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkPolicy

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 * @return 
	 */
	com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkPolicy

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 * @param ignoreMinimumPeriod 
	 * @return 
	 */
	com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password, 
		final boolean ignoreMinimumPeriod)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkPolicy

	 * @param user 
	 * @param politica 
	 * @param password 
	 * @return 
	 */
	com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordPolicyEntity politica, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkPolicy
	 * Checks password policy, ignoring the minimum password period option

	 * @param user 
	 * @param politica 
	 * @param password 
	 * @param ignoreMinimumPeriod 
	 * @return 
	 */
	com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordPolicyEntity politica, 
		final com.soffid.iam.am.api.Password password, 
		final boolean ignoreMinimumPeriod)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getLastPasswordIdForUser

	 * @param user 
	 * @return 
	 */
	java.lang.Long getLastPasswordIdForUser(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDefaultDispatcher

	 * @return 
	 */
	java.lang.String getDefaultDispatcher()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPolicyDescription

	 * @param politica 
	 * @return 
	 */
	java.lang.String getPolicyDescription(
		final com.soffid.iam.am.model.PasswordPolicyEntity politica)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPasswordExpiredDate

	 * @param user 
	 * @param passwordDomain 
	 * @return 
	 */
	java.util.Calendar getPasswordExpiredDate(
		final long user, 
		final long passwordDomain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation enumExpiredPasswords

	 * @param usuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.model.PasswordDomainEntity> enumExpiredPasswords(
		final com.soffid.iam.base.model.UserEntity usuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getExpiredPasswords

	 * @param desde 
	 * @param finsa 
	 * @param tipusUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.PasswordStatus> getExpiredPasswords(
		final java.util.Date desde, 
		final java.util.Date finsa, 
		final com.soffid.iam.base.model.UserTypeEntity tipusUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation confirmAccountPassword

	 * @param account 
	 * @param password 
	 */
	void confirmAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation confirmPassword

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 */
	void confirmPassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation disableExpiredPassword

	 */
	void disableExpiredPassword()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation disableUntrustedPasswords

	 */
	void disableUntrustedPasswords()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation storeAccountPassword

	 * @param account 
	 * @param password 
	 * @param mustChange 
	 * @param expirationDate 
	 */
	void storeAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange, 
		final java.util.Date expirationDate)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation storeAccountPassword

	 * @param account 
	 * @param dispatcher 
	 * @param password 
	 * @param mustChange 
	 * @param expirationDate 
	 */
	void storeAccountPassword(
		final java.lang.String account, 
		final java.lang.String dispatcher, 
		final java.lang.String password, 
		final boolean mustChange, 
		final java.util.Date expirationDate)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation storeAndForwardAccountPassword

	 * @param account 
	 * @param password 
	 * @param mustChange 
	 * @param expirationDate 
	 */
	void storeAndForwardAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange, 
		final java.util.Date expirationDate)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation storeAndForwardAccountPasswordById

	 * @param account 
	 * @param password 
	 * @param mustChange 
	 * @param expirationDate 
	 */
	void storeAndForwardAccountPasswordById(
		final long account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange, 
		final java.util.Date expirationDate)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation storeAndForwardPassword

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 * @param mustChange 
	 */
	void storeAndForwardPassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation storeAndForwardPasswordById

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 * @param mustChange 
	 */
	void storeAndForwardPasswordById(
		final long user, 
		final long passwordDomain, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation storeAndSynchronizeAccountPassword

	 * @param account 
	 * @param password 
	 * @param mustChange 
	 * @param expirationDate 
	 */
	void storeAndSynchronizeAccountPassword(
		final com.soffid.iam.base.model.AccountEntity account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange, 
		final java.util.Date expirationDate)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation storeAndSynchronizePassword

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 * @param mustChange 
	 */
	void storeAndSynchronizePassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation storePassword

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 * @param mustChange 
	 */
	void storePassword(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.am.model.PasswordDomainEntity passwordDomain, 
		final com.soffid.iam.am.api.Password password, 
		final boolean mustChange)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation storePassword

	 * @param user 
	 * @param passwordDomain 
	 * @param password 
	 * @param mustChange 
	 */
	void storePassword(
		final java.lang.String user, 
		final java.lang.String passwordDomain, 
		final java.lang.String password, 
		final boolean mustChange)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
