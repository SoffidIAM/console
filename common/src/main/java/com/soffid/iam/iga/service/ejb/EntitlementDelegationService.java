//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB EntitlementDelegationService
 */
public interface EntitlementDelegationService

 {

	com.soffid.iam.iga.api.RoleAccount acceptDelegation(
		final com.soffid.iam.iga.api.RoleAccount ra)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.RoleAccount cancelDelegation(
		final com.soffid.iam.iga.api.RoleAccount rolAccount)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.RoleAccount delegate(
		final com.soffid.iam.iga.api.RoleAccount rolAccount, 
		final java.lang.String user, 
		final java.lang.String account, 
		final java.util.Date since, 
		final java.util.Date until)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<java.lang.String> findAccountsToDelegate(
		final com.soffid.iam.iga.api.RoleAccount rolAccount, 
		final java.lang.String user)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.iga.api.RoleAccount> findActiveDelegations()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.iga.api.RoleAccount> findDelegationsToAccept()
	throws com.soffid.iam.exception.InternalErrorException;

	void revertExpiredDelegations()
	throws com.soffid.iam.exception.InternalErrorException;

}
