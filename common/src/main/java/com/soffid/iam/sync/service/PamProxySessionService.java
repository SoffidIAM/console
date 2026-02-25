//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service PamProxySessionService
 */
public interface PamProxySessionService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.sync.service.PamProxySessionService";

	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.PamProxySessionService";

	/**
	 * Operation validatePin

	 * @param challenge 
	 * @param value 
	 * @return 
	 */
	boolean validatePin(
		final com.soffid.iam.am.api.OtpChallengeProxy challenge, 
		final java.lang.String value)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation validateSshKey
	 * Returns the user name

	 * @param user 
	 * @param sshKey 
	 * @return 
	 */
	boolean validateSshKey(
		final java.lang.String user, 
		final java.lang.String sshKey)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateOtp

	 * @param user 
	 * @return 
	 */
	com.soffid.iam.am.api.OtpChallengeProxy generateOtp(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation validatePassword

	 * @param user 
	 * @param password 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordValidation validatePassword(
		final java.lang.String user, 
		final com.soffid.iam.am.api.Password password)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation startWorkflow

	 * @param workflow 
	 * @param userName 
	 * @param account 
	 * @param hours 
	 * @param comments 
	 * @return 
	 */
	com.soffid.iam.bpm.api.ProcessInstance startWorkflow(
		final java.lang.String workflow, 
		final java.lang.String userName, 
		final com.soffid.iam.base.api.Account account, 
		final int hours, 
		final java.lang.String comments)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation openSession

	 * @param userName 
	 * @param account 
	 * @param sourceIp 
	 * @param type 
	 * @param info 
	 * @param obligations 
	 * @return 
	 */
	com.soffid.iam.pam.api.NewPamSession openSession(
		final java.lang.String userName, 
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String sourceIp, 
		final com.soffid.iam.am.api.SessionType type, 
		final java.lang.String info, 
		final java.util.Map<java.lang.String,java.util.Map<java.lang.String,java.lang.String>> obligations)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccounts

	 * @param userName 
	 * @param url 
	 * @param accountName 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.Account> findAccounts(
		final java.lang.String userName, 
		final java.lang.String url, 
		final java.lang.String accountName)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendEmailNotification

	 * @param obligationDetails 
	 */
	void sendEmailNotification(
		final java.util.Map<java.lang.String,java.lang.String> obligationDetails)
			throws com.soffid.iam.exception.InternalErrorException;

}
