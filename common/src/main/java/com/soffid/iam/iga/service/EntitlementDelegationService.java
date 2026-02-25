//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service EntitlementDelegationService
 */
public interface EntitlementDelegationService {
	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.EntitlementDelegationService";

	/**
	 * Operation acceptDelegation
	 * Accepts a delegation not accepted yet

	 * @param ra 
	 * @return 
	 */
	com.soffid.iam.iga.api.RoleAccount acceptDelegation(
		final com.soffid.iam.iga.api.RoleAccount ra)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation cancelDelegation
	 * Revokes an entitilement

	 * @param rolAccount 
	 * @return 
	 */
	com.soffid.iam.iga.api.RoleAccount cancelDelegation(
		final com.soffid.iam.iga.api.RoleAccount rolAccount)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delegate
	 * Delegates an entitilement

	 * @param rolAccount 
	 * @param user 
	 * @param account 
	 * @param since 
	 * @param until 
	 * @return 
	 */
	com.soffid.iam.iga.api.RoleAccount delegate(
		final com.soffid.iam.iga.api.RoleAccount rolAccount, 
		final java.lang.String user, 
		final java.lang.String account, 
		final java.util.Date since, 
		final java.util.Date until)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccountsToDelegate
	 * Gets the list of accounts that can be delegated for a user

	 * @param rolAccount 
	 * @param user 
	 * @return 
	 */
	java.util.List<java.lang.String> findAccountsToDelegate(
		final com.soffid.iam.iga.api.RoleAccount rolAccount, 
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findActiveDelegations
	 * Gets list of entitlements delegated by current user

	 * @return 
	 */
	java.util.List<com.soffid.iam.iga.api.RoleAccount> findActiveDelegations()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDelegationsToAccept
	 * Gets list of entitlements delegated to current user

	 * @return 
	 */
	java.util.List<com.soffid.iam.iga.api.RoleAccount> findDelegationsToAccept()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation revertExpiredDelegations
	 * Revernts expired delegation back to the caller

	 */
	void revertExpiredDelegations()
			throws com.soffid.iam.exception.InternalErrorException;

}
