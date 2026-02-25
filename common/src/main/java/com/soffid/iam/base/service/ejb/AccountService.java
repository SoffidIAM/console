//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB AccountService
 */
public interface AccountService

 {

	boolean hasAccountSshKey(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean isAccountPasswordAvailable(
		final long accountId)
	throws com.soffid.iam.exception.InternalErrorException;

	boolean isUpdatePending(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean needsAccount(
		final java.lang.String userName, 
		final java.lang.String dispatcherName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean setHPAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password, 
		final java.util.Date untilDate, 
		final boolean force)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Password generateAccountPassword(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Password generateAccountTemporaryPassword(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Password queryAccountPassword(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Password queryAccountSshKey(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Password setAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password, 
		final boolean temporary, 
		final boolean online)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException;

	com.soffid.iam.am.api.PasswordValidation checkPasswordSynchronizationStatus(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Account createAccount(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException;

	com.soffid.iam.base.api.Account createAccount2(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException;

	com.soffid.iam.base.api.Account findAccount(
		final java.lang.String accountAndDispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Account findAccount(
		final java.lang.String accountName, 
		final java.lang.String dispatcherName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Account findAccountByExternalId(
		final java.lang.String externalId, 
		final java.lang.String system)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Account findAccountById(
		final long id)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Account generateAccountSshPrivateKey(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Account removeAccountSnapshot(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Account setAccountSshPrivateKey(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String privateKey)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Account updateAccount(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException;

	com.soffid.iam.base.api.Account updateAccount2(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException;

	com.soffid.iam.base.api.AsyncProcessTracker disableAccounts(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.AsyncProcessTracker disableAccountsPreview(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, 
		final java.util.List<java.lang.Object[]> actions)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.User getHPAccountOwner(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.UserAccount createAccount(
		final com.soffid.iam.base.api.User usuari, 
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String name)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.NeedsAccountNameException, com.soffid.iam.exception.AccountAlreadyExistsException;

	com.soffid.iam.base.api.UserData createAccountAttribute(
		final com.soffid.iam.base.api.UserData attribute)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.UserData updateAccountAttribute(
		final com.soffid.iam.base.api.UserData attribute)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.Account> findAccounts(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	int isUpdatePendingExtended(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String guessAccountName(
		final java.lang.String userName, 
		final java.lang.String dispatcherName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.pam.api.HostService> findAccountServices(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.UserAccount> getUserAccounts(
		final com.soffid.iam.base.api.User usuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.Account> getUserGrantedAccounts(
		final com.soffid.iam.base.api.User usuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.Account> getUserGrantedAccounts(
		final com.soffid.iam.base.api.User usuari, 
		final com.soffid.iam.base.api.AccountAccessLevelEnum level)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.Account> findSharedAccountsByUser(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.iga.api.AccountHistory> findSharedAccountsHistoryByUser(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.UserAccount> findUsersAccounts(
		final java.lang.String userName, 
		final java.lang.String dispatcherName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.UserAccount> findUserAccountsByDomain(
		final java.lang.String user, 
		final java.lang.String passwordDomain)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.UserData> getAccountAttributes(
		final com.soffid.iam.base.api.Account acc)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.UserAccountHistory> listAccountGrants(
		final com.soffid.iam.base.api.Account acc)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.Account> listNonUserAccounts(
		final com.soffid.iam.iga.api.System dispatcher, 
		final java.lang.String nom)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.UserAccount> listUserAccounts(
		final com.soffid.iam.base.api.User usuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void checkinHPAccount(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void removeAccount(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void removeAccount(
		final com.soffid.iam.base.api.UserAccount account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void removeAccountAttribute(
		final com.soffid.iam.base.api.UserData attribute)
	throws com.soffid.iam.exception.InternalErrorException;

	void removeAccountGrant(
		final com.soffid.iam.base.api.UserAccountHistory acc)
	throws com.soffid.iam.exception.InternalErrorException;

	void renameAccount(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.AccountAlreadyExistsException;

	void sendAccountPassword(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException;

	void setAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException;

	void setAccountTemporaryPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException;

}
