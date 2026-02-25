//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service.ejb;
/**
 * EJB OTPValidationService
 */
public interface OTPValidationService

 {

	boolean hasToken(
		final java.lang.String user)
	throws com.soffid.iam.exception.InternalErrorException;

	boolean resetFailCount(
		final java.lang.String account)
	throws com.soffid.iam.exception.InternalErrorException;

	boolean validatePin(
		final com.soffid.iam.am.api.Challenge challenge, 
		final java.lang.String pin)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Challenge resendToken(
		final com.soffid.iam.am.api.Challenge challenge, 
		final boolean alternativeMethod)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Challenge selectToken(
		final com.soffid.iam.am.api.Challenge challenge)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String generateTypeForAudit(
		final com.soffid.iam.am.api.Challenge challenge)
	throws com.soffid.iam.exception.InternalErrorException;

}
