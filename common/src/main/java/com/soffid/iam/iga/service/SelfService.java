//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service SelfService
 */
public interface SelfService {
	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.SelfService";

	/**
	 * Operation setHPAccountPassword

	 * @param account 
	 * @param password 
	 * @param untilDate 
	 * @param force 
	 * @return 
	 */
	boolean setHPAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password, 
		final java.util.Date untilDate, 
		final boolean force)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoot

	 * @return 
	 */
	com.soffid.iam.am.api.AccessTree findRoot()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateAccountTemporaryPassword
	 * Generates a temporary password for the account

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.Password generateAccountTemporaryPassword(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation queryAccountPassword

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.Password queryAccountPassword(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation queryAccountPasswordBypassPolicy

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.Password queryAccountPasswordBypassPolicy(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation queryAccountSshKey

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.Password queryAccountSshKey(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation queryAccountSshKeyBypassPolicy

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.Password queryAccountSshKeyBypassPolicy(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPasswordPolicy

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordPolicy getPasswordPolicy(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation passwordsStatus

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordStatus passwordsStatus(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountById
	 * Get account

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.base.api.Account getAccountById(
		final long id)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateSharedAccount
	 * Updates an account, including ACLs if user is owner

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.base.api.Account updateSharedAccount(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDataTypeDescription

	 * @param systemName 
	 * @param attName 
	 * @return 
	 */
	com.soffid.iam.base.api.DataType getDataTypeDescription(
		final java.lang.String systemName, 
		final java.lang.String attName)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getCurrentUser

	 * @return 
	 */
	com.soffid.iam.base.api.User getCurrentUser()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createSharedAccountData
	 * Created an account attribute

	 * @param data 
	 * @return 
	 */
	com.soffid.iam.base.api.UserData createSharedAccountData(
		final com.soffid.iam.base.api.UserData data)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateSharedAccountData
	 * Updates an account attribute

	 * @param data 
	 * @return 
	 */
	com.soffid.iam.base.api.UserData updateSharedAccountData(
		final com.soffid.iam.base.api.UserData data)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateUserAttribute

	 * @param attribute 
	 * @return 
	 */
	com.soffid.iam.base.api.UserData updateUserAttribute(
		final com.soffid.iam.base.api.UserData attribute)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDispatcherInformation

	 * @param dispatcherCode 
	 * @return 
	 */
	com.soffid.iam.iga.api.System getDispatcherInformation(
		final java.lang.String dispatcherCode)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getClientHost

	 * @return 
	 */
	java.lang.String getClientHost()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation queryOtherAffectedAccounts

	 * @param account 
	 * @return 
	 */
	java.lang.String queryOtherAffectedAccounts(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findChildren

	 * @param puntEntrada 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.AccessTree> findChildren(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findEntryPoints
	 * Finds entry points by name

	 * @param name 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.AccessTree> findEntryPoints(
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRoleAccounts

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccounts()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserGroupsByUserName

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUserGroupsByUserName()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAccounts

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.Account> getUserAccounts()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAttributes

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.UserData> getUserAttributes()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountAttributes
	 * Get account attributes

	 * @param acc 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.UserData> getAccountAttributes(
		final com.soffid.iam.base.api.Account acc)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getSharedAccounts
	 * Gets the list of shared accounts granted to the user

	 * @param filter 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.Account> getSharedAccounts(
		final java.lang.String filter)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkCanSetAccountPassword

	 * @param account 
	 */
	void checkCanSetAccountPassword(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkinHPAccount
	 * Unlocks a high privileged account

	 * @param account 
	 */
	void checkinHPAccount(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setAccountPassword

	 * @param account 
	 * @param password 
	 */
	void setAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setAccountSshKey

	 * @param account 
	 * @param password 
	 */
	void setAccountSshKey(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
