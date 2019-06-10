package com.soffid.iam.utils;

import java.util.Date;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.seycon.ng.exception.InternalErrorException;

public class MailUtils {
	public static void sendMail(String smtpServer, String to, String from,
			String subject, String body) throws MessagingException,
			NamingException {

		Session session = getSession(smtpServer);

		// Properties props = new Properties();

		// -- Attaching to default Session, or we could start a new one --
		// props.put("mail.smtp.host", smtpServer);
		// Session session = Session.getDefaultInstance(props, null);

		MimeMessage msg = null;
		msg = new MimeMessage(session);

		// -- Create a new message --

		// -- Set the FROM and TO fields --
		try {
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to, false));
			// -- Set the subject and body text --
			msg.setSubject(subject, "UTF-8");

			// enviem en mime - utf-8, que és com ho tenim al repositori
			msg.setText(body, "UTF-8"); //$NON-NLS-1$

			// -- Set some other header information --
			msg.setHeader("X-Mailer", "SoffidMailer"); //$NON-NLS-1$ //$NON-NLS-2$
			msg.setSentDate(new Date());

			// -- Send the message --
			Transport.send(msg);

		} catch (AddressException e) {
			e.printStackTrace();
			// throw e;
		} catch (MessagingException e) {
			e.printStackTrace();
			// throw e;
		}

		//System.out.println("Message sent OK.");

	}

	public static Session getSession (String mailHost) throws NamingException
	{
		Properties props = new Properties();
		javax.mail.Authenticator authenticator = null;

		String protocol = getConfigValue("mail.transport.protocol", "smtp");
		props.put("mail.transport.protocol", protocol);
		if ("smtps".equals(protocol))
		{
		    props.put("mail.smtp.socketFactory.class",
		            "com.soffid.iam.utils.CustomSSLFactory");
		    props.put("mail.smtp.socketFactory.fallback", "false");
		    props.put("mail.smtp.starttls.enable", "true");
		}
		String auth = getConfigValue("mail.auth", "false");
		if ("true".equals(auth))
		{
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtps.auth", "true");
			final String user = getConfigValue("mail.user", null);
			if (user != null)
			{
				props.put("mail.smtp.user", user);
				props.put("mail.smtps.user", user);
			}
			final String password = getConfigValue("mail.password", null);
			if (password != null)
			{
				props.put("password", password);
				props.put("mail.smtp.password", password);
				props.put("mail.smtps.password", password);
			}
			authenticator = new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	            	return new PasswordAuthentication(user, password);
	            }
			};
		}
		
		String port = getConfigValue("mail.port", null); 
		if (port != null)
		{
			props.put("mail.smtp.port", port);
			props.put("mail.smtps.port", port);
			props.put("mail.smtp.socketFactory.port", port);
		}
		// -- Attaching to default Session, or we could start a new one --
		props.put("mail.smtp.host", getConfigValue("mail.host", "localhost")); //$NON-NLS-1$ //$NON-NLS-2$
		props.put("mail.smtps.host", getConfigValue("mail.host", "localhost")); //$NON-NLS-1$ //$NON-NLS-2$
		Session session = Session.getDefaultInstance(props, authenticator);
		session.setDebug(true);
		return session;
	}
	
	

	private static String getConfigValue(String string, String defaultValue) {
		String v = ConfigurationCache.getProperty(string);
		if (v == null)
			v = defaultValue;
		return v;
	}

	public static void sendHtmlMail(String smtpServer, String to, String from,
					String subject, String body) throws MessagingException,
					NamingException
	{
		// -- Attaching to default Session, or we could start a new one --
		Session session = getSession(smtpServer);

		MimeMessage msg = new MimeMessage(session);

		// -- Create a new message --
		// -- Set the FROM and TO fields --
		try
		{
			org.apache.commons.logging.LogFactory.getLog(MailUtils.class)
				.info("Sending mail ["+subject+"] from ["+from+"] to ["+to+"] via ["+smtpServer+"]");
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			// -- Set the subject and body text --
			msg.setSubject(subject, "UTF-8");

			// enviem en mime - utf-8, que és com ho tenim al repositori
			msg.setContent(body, "text/html; charset=utf-8"); //$NON-NLS-1$
			// -- Set some other header information --
			msg.setHeader("X-Mailer", "SoffidMailer"); //$NON-NLS-1$ //$NON-NLS-2$
			msg.setSentDate(new Date());

			// -- Send the message --
			Transport.send(msg);
		}
		catch (AddressException e)
		{
			org.apache.commons.logging.LogFactory.getLog(MailUtils.class)
				.warn("Error sending message to ["+to+"] :", e);
		}
		catch (MessagingException e)
		{
			org.apache.commons.logging.LogFactory.getLog(MailUtils.class)
				.warn("Error sending message to ["+to+"] :", e);
		}
		
		// System.out.println("Message sent OK.");
	}

	public static void sendHtmlMail(String smtpServer,
			Set<InternetAddress> to, String from,
			String subject, String body) throws NamingException, InternalErrorException {
		Properties props = new Properties();

		// -- Attaching to default Session, or we could start a new one --
		Session session = getSession(smtpServer);

		MimeMessage msg = new MimeMessage(session);

		// -- Create a new message --
		// -- Set the FROM and TO fields --
		try
		{
			org.apache.commons.logging.LogFactory.getLog(MailUtils.class)
				.info("Sending mail ["+subject+"] from ["+from+"] to ["+to+"] via ["+smtpServer+"]");
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, to.toArray(new InternetAddress[to.size()]));
			// -- Set the subject and body text --
			msg.setSubject(subject, "UTF-8");

			// enviem en mime - utf-8, que és com ho tenim al repositori
			msg.setContent(body, "text/html; charset=utf-8"); //$NON-NLS-1$
			// -- Set some other header information --
			msg.setHeader("X-Mailer", "SoffidMailer"); //$NON-NLS-1$ //$NON-NLS-2$
			msg.setSentDate(new Date());

			// -- Send the message --
			Transport.send(msg);
		}
		catch (AddressException e)
		{
			throw new InternalErrorException("Unable to send mail message to "+to, e);
		}
		catch (MessagingException e)
		{
			throw new InternalErrorException("Unable to send mail message to "+to, e);
		}
		
		// System.out.println("Message sent OK.");
	}

	public static void sendMail(String smtpServer, Set<InternetAddress>  to, String from,
			String subject, String body) throws MessagingException,
			NamingException, InternalErrorException {

		Session session = getSession(smtpServer);

		// Properties props = new Properties();

		// -- Attaching to default Session, or we could start a new one --
		// props.put("mail.smtp.host", smtpServer);
		// Session session = Session.getDefaultInstance(props, null);

		MimeMessage msg = null;
		msg = new MimeMessage(session);

		// -- Create a new message --

		// -- Set the FROM and TO fields --
		try {
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, to.toArray(new InternetAddress[to.size()]));
			// -- Set the subject and body text --
			msg.setSubject(subject, "UTF-8");

			// enviem en mime - utf-8, que és com ho tenim al repositori
			msg.setText(body, "UTF-8"); //$NON-NLS-1$

			// -- Set some other header information --
			msg.setHeader("X-Mailer", "SoffidMailer"); //$NON-NLS-1$ //$NON-NLS-2$
			msg.setSentDate(new Date());

			// -- Send the message --
			Transport.send(msg);

		} catch (AddressException e) {
			throw new InternalErrorException("Unable to send mail message to "+to, e);
		} catch (MessagingException e) {
			throw new InternalErrorException("Unable to send mail message to "+to, e);
		}

		//System.out.println("Message sent OK.");

	}
}
