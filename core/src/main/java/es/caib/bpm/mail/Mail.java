package es.caib.bpm.mail;

/**
 * Compatibility class
 *
 */
@Deprecated
public class Mail extends com.soffid.iam.bpm.mail.Mail {

	public Mail() {
	}

	public Mail(String template, String actors, String to, String subject, String text) {
		super(template, actors, to, subject, text);
	}

}
