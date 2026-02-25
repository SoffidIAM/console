//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service OTPValidationService
 */
public interface OTPValidationService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.impl.service.OTPValidationService";

	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.OTPValidationService";

	/**
	 * Operation hasToken

	 * @param user 
	 * @return 
	 */
	boolean hasToken(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation resetFailCount

	 * @param account 
	 * @return 
	 */
	boolean resetFailCount(
		final java.lang.String account)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation validatePin

	 * @param challenge 
	 * @param pin 
	 * @return 
	 */
	boolean validatePin(
		final com.soffid.iam.am.api.Challenge challenge, 
		final java.lang.String pin)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation resendToken

	 * @param challenge 
	 * @param alternativeMethod 
	 * @return 
	 */
	com.soffid.iam.am.api.Challenge resendToken(
		final com.soffid.iam.am.api.Challenge challenge, 
		final boolean alternativeMethod)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation selectToken

	 * @param challenge 
	 * @return 
	 */
	com.soffid.iam.am.api.Challenge selectToken(
		final com.soffid.iam.am.api.Challenge challenge)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateTypeForAudit

	 * @param challenge 
	 * @return 
	 */
	java.lang.String generateTypeForAudit(
		final com.soffid.iam.am.api.Challenge challenge)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerOTPHandler

	 * @param handler 
	 */
	void registerOTPHandler(
		final com.soffid.iam.service.impl.OTPHandler handler)
			throws com.soffid.iam.exception.InternalErrorException;

}
