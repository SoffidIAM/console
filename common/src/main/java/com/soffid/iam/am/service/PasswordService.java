//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
/**
 * Service PasswordService
 */
public interface PasswordService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.am.service.PasswordService";

	public final static String SERVICE_NAME = "com.soffid.iam.am.service.PasswordService";

	/**
	 * Operation checkPassword

	 * @param account 
	 * @param dispatcher 
	 * @param password 
	 * @param checkTrusted 
	 * @param checkExpired 
	 * @return 
	 */
	boolean checkPassword(
		final java.lang.String account, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.am.api.Password password, 
		final boolean checkTrusted, 
		final boolean checkExpired)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkExpiredPassword

	 * @param accoount 
	 * @param dispatcher 
	 * @return 
	 */
	boolean checkExpiredPassword(
		final java.lang.String accoount, 
		final java.lang.String dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkPin

	 * @param user 
	 * @param pin 
	 * @return 
	 */
	boolean checkPin(
		final java.lang.String user, 
		final java.lang.String pin)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation checkPolicy

	 * @param account 
	 * @param dispatcher 
	 * @param password 
	 * @return 
	 */
	com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final java.lang.String account, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDefaultDispatcher

	 * @return 
	 */
	java.lang.String getDefaultDispatcher()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPolicyDescription

	 * @param account 
	 * @param dispatcher 
	 * @return 
	 */
	java.lang.String getPolicyDescription(
		final java.lang.String account, 
		final java.lang.String dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPasswordExpiredDate

	 * @param account 
	 * @param dispatcher 
	 * @return 
	 */
	java.util.Calendar getPasswordExpiredDate(
		final java.lang.String account, 
		final java.lang.String dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation changePassword

	 * @param account 
	 * @param dispatcher 
	 * @param oldPassword 
	 * @param newPassword 
	 */
	void changePassword(
		final java.lang.String account, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.am.api.Password oldPassword, 
		final com.soffid.iam.am.api.Password newPassword)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException, com.soffid.iam.exception.InvalidPasswordException;

}
