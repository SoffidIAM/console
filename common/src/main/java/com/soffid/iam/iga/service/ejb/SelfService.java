//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB SelfService
 */
public interface SelfService

 {

	boolean setHPAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password, 
		final java.util.Date untilDate, 
		final boolean force)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.AccessTree findRoot()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Password generateAccountTemporaryPassword(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Password queryAccountPassword(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Password queryAccountPasswordBypassPolicy(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Password queryAccountSshKey(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Password queryAccountSshKeyBypassPolicy(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.PasswordPolicy getPasswordPolicy(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.PasswordStatus passwordsStatus(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Account getAccountById(
		final long id)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Account updateSharedAccount(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.DataType getDataTypeDescription(
		final java.lang.String systemName, 
		final java.lang.String attName)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.User getCurrentUser()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.UserData createSharedAccountData(
		final com.soffid.iam.base.api.UserData data)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.UserData updateSharedAccountData(
		final com.soffid.iam.base.api.UserData data)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.UserData updateUserAttribute(
		final com.soffid.iam.base.api.UserData attribute)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.System getDispatcherInformation(
		final java.lang.String dispatcherCode)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String getClientHost()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String queryOtherAffectedAccounts(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.AccessTree> findChildren(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.AccessTree> findEntryPoints(
		final java.lang.String name)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccounts()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.UserGroup> findUserGroupsByUserName()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.Account> getUserAccounts()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.UserData> getUserAttributes()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.UserData> getAccountAttributes(
		final com.soffid.iam.base.api.Account acc)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.Account> getSharedAccounts(
		final java.lang.String filter)
	throws com.soffid.iam.exception.InternalErrorException;

	void checkCanSetAccountPassword(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void checkinHPAccount(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void setAccountPassword(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void setAccountSshKey(
		final com.soffid.iam.base.api.Account account, 
		final com.soffid.iam.am.api.Password password)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
