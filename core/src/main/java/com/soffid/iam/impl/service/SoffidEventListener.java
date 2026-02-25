//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service SoffidEventListener
 */
public interface SoffidEventListener {
	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.SoffidEventListener";

	/**
	 * Operation onGrant

	 * @param grant 
	 */
	void onGrant(
		final com.soffid.iam.iga.model.RoleAccountEntity grant)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation onRevoke

	 * @param grant 
	 */
	void onRevoke(
		final com.soffid.iam.iga.model.RoleAccountEntity grant)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation onUserChange

	 * @param user 
	 */
	void onUserChange(
		final com.soffid.iam.base.model.UserEntity user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
