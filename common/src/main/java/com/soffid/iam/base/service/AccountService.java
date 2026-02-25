//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
/**
 * Service AccountService
 */
public interface AccountService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.base.service.AccountService";

	public final static String SERVICE_NAME = "com.soffid.iam.base.service.AccountService";

	/**
	 * Operation hasAccountSshKey
	 * Gets the account password

	 * @param account 
	 * @return 
	 */
	boolean hasAccountSshKey(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isAccountPasswordAvailable
	 * Checks if there is a password available

	 * @param accountId 
	 * @return 
	 */
	boolean isAccountPasswordAvailable(
		final long accountId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isUpdatePending
	 * Identifies if there is any pending change to apply

	 * @param account 
	 * @return 
	 */
	boolean isUpdatePending(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation needsAccount
	 * Checks if an account should be created for a user and system

	 * @param userName 
	 * @param dispatcherName 
	 * @return 
	 */
	boolean needsAccount(
		final java.lang.String userName, 
		final java.lang.String dispatcherName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setHPAccountPassword
	 * Sets the high privileged account password. Returns false if the action is waiting for approval

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
	 * Operation generateAccountPassword
	 * Generates a password for the account

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.Password generateAccountPassword(
		final com.soffid.iam.base.api.Account account)
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
	 * Gets the account password

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.Password queryAccountPassword(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation queryAccountPasswordBypassPolicy
	 * Gets the account password bypassing passowrd policy. Used for SSO

	 * @param accountId 
	 * @param level 
	 * @return 
	 */
	com.soffid.iam.am.api.Password queryAccountPasswordBypassPolicy(
		final long accountId, 
		final com.soffid.iam.base.api.AccountAccessLevelEnum level)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation queryAccountSshKey
	 * Gets the account password

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.Password queryAccountSshKey(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation queryAccountSshKeyBypassPolicy
	 * Gets the account password bypassing passowrd policy. Used for SSO

	 * @param accountId 
	 * @param level 
	 * @return 
	 */
	com.soffid.iam.am.api.Password queryAccountSshKeyBypassPolicy(
		final long accountId, 
		final com.soffid.iam.base.api.AccountAccessLevelEnum level)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setAccountPassword
	 * Sets the account password. Advanced method to set it online or offline

	 * @param account 
	 * @param password 
	 * @param temporary 
	 * @param online 
	 * @return 
	 */
	com.soffid.iam.am.api.Password setAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean temporary, 
		final boolean online)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException;

	/**
	 * Operation checkPasswordSynchronizationStatus

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordValidation checkPasswordSynchronizationStatus(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createAccount
	 * Creates a shared account

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.base.api.Account createAccount(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException;

	/**
	 * Operation createAccount2
	 * Creates a shared account, including its attributes

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.base.api.Account createAccount2(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException;

	/**
	 * Operation findAccount
	 * Finds an account by name and system

	 * @param accountAndDispatcher 
	 * @return 
	 */
	com.soffid.iam.base.api.Account findAccount(
		final java.lang.String accountAndDispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccount
	 * Finds an account by name and system

	 * @param accountName 
	 * @param dispatcherName 
	 * @return 
	 */
	com.soffid.iam.base.api.Account findAccount(
		final java.lang.String accountName, 
		final java.lang.String dispatcherName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccountByExternalId
	 * Finds an account by external id

	 * @param externalId 
	 * @param system 
	 * @return 
	 */
	com.soffid.iam.base.api.Account findAccountByExternalId(
		final java.lang.String externalId, 
		final java.lang.String system)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccountById
	 * Finds an account by id

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.base.api.Account findAccountById(
		final long id)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateAccountSshPrivateKey
	 * Stores the account SSH private key

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.base.api.Account generateAccountSshPrivateKey(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation load
	 * Gets an account by id

	 * @param identifier 
	 * @return 
	 */
	com.soffid.iam.base.api.Account load(
		final java.lang.Long identifier)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeAccountSnapshot

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.base.api.Account removeAccountSnapshot(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setAccountSshPrivateKey
	 * Stores the account SSH private key

	 * @param account 
	 * @param privateKey 
	 * @return 
	 */
	com.soffid.iam.base.api.Account setAccountSshPrivateKey(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String privateKey)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateAccount
	 * Updates a shared account

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.base.api.Account updateAccount(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException;

	/**
	 * Operation updateAccount2
	 * Updates a shared account, including its attributes

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.base.api.Account updateAccount2(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException;

	/**
	 * Operation disableAccounts

	 * @param scimQuery 
	 * @param rules 
	 * @return 
	 */
	com.soffid.iam.base.api.AsyncProcessTracker disableAccounts(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation disableAccountsPreview

	 * @param scimQuery 
	 * @param rules 
	 * @param actions 
	 * @return 
	 */
	com.soffid.iam.base.api.AsyncProcessTracker disableAccountsPreview(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, 
		final java.util.List<java.lang.Object[]> actions)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getHPAccountOwner
	 * Gets the current privileged account owner

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.base.api.User getHPAccountOwner(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createAccount

	 * @param usuari 
	 * @param dispatcher 
	 * @param name 
	 * @return 
	 */
	com.soffid.iam.base.api.UserAccount createAccount(
		final com.soffid.iam.base.api.User usuari, 
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.NeedsAccountNameException, com.soffid.iam.exception.AccountAlreadyExistsException;

	/**
	 * Operation createAccountAttribute
	 * Creates an account attributes

	 * @param attribute 
	 * @return 
	 */
	com.soffid.iam.base.api.UserData createAccountAttribute(
		final com.soffid.iam.base.api.UserData attribute)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateAccountAttribute
	 * Updates an account attributes

	 * @param attribute 
	 * @return 
	 */
	com.soffid.iam.base.api.UserData updateAccountAttribute(
		final com.soffid.iam.base.api.UserData attribute)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccounts

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Account> findAccounts(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isUpdatePendingExtended
	 * Identifies if there is any pending change. 0 means no change pending, 1 task is on hald, 2 means synchronization in progress, 3 means error

	 * @param account 
	 * @return 
	 */
	int isUpdatePendingExtended(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation guessAccountName
	 * Generates the account name for a user and system

	 * @param userName 
	 * @param dispatcherName 
	 * @return 
	 */
	java.lang.String guessAccountName(
		final java.lang.String userName, 
		final java.lang.String dispatcherName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation guessAccountNameForDomain
	 * Generates the account name for a user domain

	 * @param userName 
	 * @param domainName 
	 * @return 
	 */
	java.lang.String guessAccountNameForDomain(
		final java.lang.String userName, 
		final java.lang.String domainName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation predictAccountName

	 * @param userId 
	 * @param dispatcher 
	 * @param domainId 
	 * @return 
	 */
	java.lang.String predictAccountName(
		final java.lang.Long userId, 
		final java.lang.String dispatcher, 
		final java.lang.Long domainId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.NeedsAccountNameException, com.soffid.iam.exception.AccountAlreadyExistsException;

	/**
	 * Operation findAccountNames

	 * @param system 
	 * @return 
	 */
	java.util.Collection<java.lang.String> findAccountNames(
		final java.lang.String system)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccountServices

	 * @param account 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.pam.api.HostService> findAccountServices(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountUsers
	 * Gets the users than currently owns the account. 
	 * For single user accounts, it's the single user.
	 * For privileged and shared accounts they are de acl members

	 * @param account 
	 * @return 
	 */
	java.util.Collection<java.lang.String> getAccountUsers(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountUsers
	 * Gets the users than currently matches the desired access level for the account. 
	 * For single user accounts, it's the single user.
	 * For privileged and shared accounts they are de acl members

	 * @param account 
	 * @param level 
	 * @return 
	 */
	java.util.Collection<java.lang.String> getAccountUsers(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.base.api.AccountAccessLevelEnum level)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getActiveTasks

	 * @param account 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getActiveTasks(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAccounts
	 * Gets the account a user has

	 * @param usuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.UserAccount> getUserAccounts(
		final com.soffid.iam.base.api.User usuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserGrantedAccountIds
	 * Gets the users than currently can use the account from console

	 * @param usuari 
	 * @return 
	 */
	java.util.Collection<java.lang.Long> getUserGrantedAccountIds(
		final com.soffid.iam.base.api.User usuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserGrantedAccounts
	 * Gets the users than currently can use the account from console

	 * @param usuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.Account> getUserGrantedAccounts(
		final com.soffid.iam.base.api.User usuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserGrantedAccounts
	 * Gets the users than currently can use the account with the desired access level

	 * @param usuari 
	 * @param level 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.Account> getUserGrantedAccounts(
		final com.soffid.iam.base.api.User usuari, 
		final com.soffid.iam.base.api.AccountAccessLevelEnum level)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccountsNearToExpire
	 * Gets accounts near to expire

	 * @param currentDate 
	 * @param limitDate 
	 * @param accTypes 
	 * @param userTypes 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.Account> findAccountsNearToExpire(
		final java.util.Date currentDate, 
		final java.util.Date limitDate, 
		final java.util.Collection<com.soffid.iam.base.api.AccountType> accTypes, 
		final java.util.Collection<com.soffid.iam.iga.api.UserType> userTypes)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSharedAccountsByUser
	 * Gets the non single-user accounts for a user

	 * @param userName 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.Account> findSharedAccountsByUser(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSharedAccountsHistoryByUser
	 * Gets the non single-user accounts history for a user

	 * @param userName 
	 * @return 
	 */
	java.util.List<com.soffid.iam.iga.api.AccountHistory> findSharedAccountsHistoryByUser(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUsersAccounts
	 * Gets the accounts for a user on a single system name

	 * @param userName 
	 * @param dispatcherName 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.UserAccount> findUsersAccounts(
		final java.lang.String userName, 
		final java.lang.String dispatcherName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserAccountsByDomain
	 * Find the user accounts for a user and password domain

	 * @param user 
	 * @param passwordDomain 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.UserAccount> findUserAccountsByDomain(
		final java.lang.String user, 
		final java.lang.String passwordDomain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountAttributes
	 * Gets account attributes

	 * @param acc 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.UserData> getAccountAttributes(
		final com.soffid.iam.base.api.Account acc)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation listAccountGrants
	 * List high privileged account reservations

	 * @param acc 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.UserAccountHistory> listAccountGrants(
		final com.soffid.iam.base.api.Account acc)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation listNonUserAccounts
	 * Search shareds account on a system

	 * @param dispatcher 
	 * @param nom 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.Account> listNonUserAccounts(
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String nom)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation listUserAccounts
	 * Gets the accounts bound to this user

	 * @param usuari 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.UserAccount> listUserAccounts(
		final com.soffid.iam.base.api.User usuari)
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
	 * Operation checkinHPAccounts
	 * Unlocks expired high privileged accounts

	 */
	void checkinHPAccounts()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateUserAccounts
	 * Creates user accounts depending on user domain rules

	 * @param user 
	 */
	void generateUserAccounts(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeAccount
	 * Removes a shared account

	 * @param account 
	 */
	void removeAccount(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeAccount

	 * @param account 
	 */
	void removeAccount(
		final com.soffid.iam.base.api.UserAccount account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeAccountAttribute
	 * Deletes an account attributes

	 * @param attribute 
	 */
	void removeAccountAttribute(
		final com.soffid.iam.base.api.UserData attribute)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeAccountGrant
	 * List high privileged account reservations

	 * @param acc 
	 */
	void removeAccountGrant(
		final com.soffid.iam.base.api.UserAccountHistory acc)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation renameAccount
	 * Renames a user account

	 * @param account 
	 */
	void renameAccount(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException;

	/**
	 * Operation sendAccountPassword
	 * Sends the current account password back to the target system

	 * @param account 
	 */
	void sendAccountPassword(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException;

	/**
	 * Operation setAccountPassword
	 * Sets the account password

	 * @param account 
	 * @param password 
	 */
	void setAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException;

	/**
	 * Operation setAccountTemporaryPassword
	 * Sets the account temporary password

	 * @param account 
	 * @param password 
	 */
	void setAccountTemporaryPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException;

	/**
	 * Operation synchronizeAccount

	 * @param accountName 
	 * @param system 
	 */
	void synchronizeAccount(
		final java.lang.String accountName, 
		final java.lang.String system)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateAccountLastUpdate
	 * Updates the accounts last update property

	 * @param account 
	 */
	void updateAccountLastUpdate(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateAccountPasswordDate
	 * Updates the account password property

	 * @param account 
	 * @param passwordTerm 
	 */
	void updateAccountPasswordDate(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.Long passwordTerm)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateAccountPasswordDate2
	 * Updates account password set and expiration

	 * @param account 
	 * @param expirationDate 
	 */
	void updateAccountPasswordDate2(
		final com.soffid.iam.base.api.Account account, 
		final java.util.Date expirationDate)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation grantAcccountToUser

	 * @param account 
	 * @param user 
	 * @param processId 
	 * @param until 
	 */
	void grantAcccountToUser(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String user, 
		final java.lang.Long processId, 
		final java.util.Date until)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation grantAcccountToUser

	 * @param account 
	 * @param user 
	 * @param processId 
	 * @param until 
	 * @param entryPoint 
	 */
	void grantAcccountToUser(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String user, 
		final java.lang.Long processId, 
		final java.util.Date until, 
		final java.lang.String entryPoint)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerAccountReservationProcess

	 * @param account 
	 * @param user 
	 * @param processId 
	 */
	void registerAccountReservationProcess(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String user, 
		final java.lang.Long processId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerAccountReservationProcess

	 * @param account 
	 * @param user 
	 * @param processId 
	 * @param entryPoint 
	 */
	void registerAccountReservationProcess(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String user, 
		final java.lang.Long processId, 
		final java.lang.String entryPoint)
			throws com.soffid.iam.exception.InternalErrorException;

}
