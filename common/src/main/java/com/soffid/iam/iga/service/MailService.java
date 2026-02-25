//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service MailService
 * Service to send emails
 */
public interface MailService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.iga.service.MailService";

	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.MailService";

	/**
	 * Operation sendHtmlMail
	 * Sends HTML email to a given email address

	 * @param to 
	 * @param subject 
	 * @param body 
	 */
	void sendHtmlMail(
		final java.lang.String to, 
		final java.lang.String subject, 
		final java.lang.String body)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendHtmlMail
	 * Sends HTML email to a given email address with cc

	 * @param to 
	 * @param cc 
	 * @param subject 
	 * @param body 
	 */
	void sendHtmlMail(
		final java.lang.String to, 
		final java.lang.String cc, 
		final java.lang.String subject, 
		final java.lang.String body)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendHtmlMail
	 * Sends HTML email with attachments to a given email address with cc

	 * @param to 
	 * @param cc 
	 * @param subject 
	 * @param body 
	 * @param mimeBodyParts 
	 */
	void sendHtmlMail(
		final java.lang.String to, 
		final java.lang.String cc, 
		final java.lang.String subject, 
		final java.lang.String body, 
		final java.util.Collection mimeBodyParts)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendHtmlMail
	 * Sends HTML email to a given email address with attachments

	 * @param to 
	 * @param subject 
	 * @param body 
	 * @param mimeBodyParts 
	 */
	void sendHtmlMail(
		final java.lang.String to, 
		final java.lang.String subject, 
		final java.lang.String body, 
		final java.util.Collection mimeBodyParts)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendHtmlMailToActors
	 * Sends HTML email to a set of users, groups or role owners

	 * @param actors 
	 * @param subject 
	 * @param body 
	 */
	void sendHtmlMailToActors(
		final java.lang.String[] actors, 
		final java.lang.String subject, 
		final java.lang.String body)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendHtmlMailToActors
	 * Sends HTML email to a set of users, groups or role owners, with cc

	 * @param actors 
	 * @param cc 
	 * @param subject 
	 * @param body 
	 */
	void sendHtmlMailToActors(
		final java.lang.String[] actors, 
		final java.lang.String cc, 
		final java.lang.String subject, 
		final java.lang.String body)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendTextMail
	 * Sends a plain text email to a given email address

	 * @param to 
	 * @param subject 
	 * @param body 
	 */
	void sendTextMail(
		final java.lang.String to, 
		final java.lang.String subject, 
		final java.lang.String body)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendTextMail
	 * Sends text email to a given email address with cc

	 * @param to 
	 * @param cc 
	 * @param subject 
	 * @param body 
	 */
	void sendTextMail(
		final java.lang.String to, 
		final java.lang.String cc, 
		final java.lang.String subject, 
		final java.lang.String body)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendTextMailToActors
	 * Sends a plain text email to a set of users, groups or role owners

	 * @param actors Set of users, groups or role owners

	 * @param subject 
	 * @param body 
	 */
	void sendTextMailToActors(
		final java.lang.String[] actors, 
		final java.lang.String subject, 
		final java.lang.String body)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendTextMailToActors
	 * Sends a plain text email to a set of users, groups or role owners, with cc

	 * @param actors 
	 * @param cc 
	 * @param subject 
	 * @param body 
	 */
	void sendTextMailToActors(
		final java.lang.String[] actors, 
		final java.lang.String cc, 
		final java.lang.String subject, 
		final java.lang.String body)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendTextMailToActors
	 * Sends a plain text email to a set of users, groups or role owners, with cc and attachments

	 * @param actors 
	 * @param cc 
	 * @param subject 
	 * @param body 
	 * @param mimeBodyParts 
	 */
	void sendTextMailToActors(
		final java.lang.String[] actors, 
		final java.lang.String cc, 
		final java.lang.String subject, 
		final java.lang.String body, 
		final java.util.Collection mimeBodyParts)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendTextMailToActors
	 * Sends plain text email with attachments to a set of users, groups or role owners

	 * @param actors Set of users, groups or role owners

	 * @param subject 
	 * @param body 
	 * @param mimeBodyParts 
	 */
	void sendTextMailToActors(
		final java.lang.String[] actors, 
		final java.lang.String subject, 
		final java.lang.String body, 
		final java.util.Collection mimeBodyParts)
			throws com.soffid.iam.exception.InternalErrorException;

}
