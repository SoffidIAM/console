//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service ACLService
 */
public interface ACLService {
	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.ACLService";

	/**
	 * Operation isAccountIncluded

	 * @param userId 
	 * @param acl 
	 * @return 
	 */
	boolean isAccountIncluded(
		final long userId, 
		final com.soffid.iam.iga.api.AccessControlList acl)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isCurrentUserIncluded

	 * @param acl 
	 * @return 
	 */
	boolean isCurrentUserIncluded(
		final com.soffid.iam.iga.api.AccessControlList acl)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isUserIncluded

	 * @param userId 
	 * @param acl 
	 * @return 
	 */
	boolean isUserIncluded(
		final long userId, 
		final com.soffid.iam.iga.api.AccessControlList acl)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation expandACL

	 * @param acl 
	 * @return 
	 */
	com.soffid.iam.iga.api.AccessControlList expandACL(
		final com.soffid.iam.iga.api.AccessControlList acl)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation expandUser

	 * @param userId 
	 * @return 
	 */
	com.soffid.iam.iga.api.AccessControlList expandUser(
		final long userId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation expandACLAccounts

	 * @param acl 
	 * @return 
	 */
	java.util.Collection<java.lang.String> expandACLAccounts(
		final com.soffid.iam.iga.api.AccessControlList acl)
			throws com.soffid.iam.exception.InternalErrorException;

}
