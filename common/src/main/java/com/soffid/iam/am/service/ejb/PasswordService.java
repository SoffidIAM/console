//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service.ejb;
/**
 * EJB PasswordService
 */
public interface PasswordService

 {

	boolean checkPassword(
		final java.lang.String account, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.am.api.Password password, 
		final boolean checkTrusted, 
		final boolean checkExpired)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean checkExpiredPassword(
		final java.lang.String accoount, 
		final java.lang.String dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	boolean checkPin(
		final java.lang.String user, 
		final java.lang.String pin)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.PolicyCheckResult checkPolicy(
		final java.lang.String account, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.am.api.Password password)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String getDefaultDispatcher()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String getPolicyDescription(
		final java.lang.String account, 
		final java.lang.String dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Calendar getPasswordExpiredDate(
		final java.lang.String account, 
		final java.lang.String dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void changePassword(
		final java.lang.String account, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.am.api.Password oldPassword, 
		final com.soffid.iam.am.api.Password newPassword)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException, com.soffid.iam.exception.InvalidPasswordException;

}
