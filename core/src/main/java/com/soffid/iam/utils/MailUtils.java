package com.soffid.iam.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.HeaderTokenizer;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.zkoss.util.logging.Log;

public class MailUtils {

	public static void sendMail(String smtpServer, String to, String from,
			String subject, String body) throws MessagingException,
			NamingException {

		Session session = getSession();

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
			msg.setSubject(subject);

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

	private static Session getSession () throws NamingException
	{
		try {
			return (Session) PortableRemoteObject.narrow(
				new InitialContext().lookup("java:/es.caib.seycon.mail.smtp"), //$NON-NLS-1$
				Session.class);
		} catch (Exception e) {
			return Session.getDefaultInstance(System.getProperties());
		}
	}

	public static void sendHtmlMail(String smtpServer, String to, String from,
					String subject, String body) throws MessagingException,
					NamingException
	{
		Properties props = new Properties();

		// -- Attaching to default Session, or we could start a new one --
		String mailHost =  (smtpServer != null) ? smtpServer : "localhost";
		props.put("mail.smtp.host", mailHost); //$NON-NLS-1$ //$NON-NLS-2$
		Session session = getSession().getInstance(props, null);

		MimeMessage msg = new MimeMessage(session);

		// -- Create a new message --
		// -- Set the FROM and TO fields --
		try
		{
			org.apache.commons.logging.LogFactory.getLog(MailUtils.class)
				.info("Sending mail ["+subject+"] from ["+from+"] to ["+to+"] via ["+mailHost+"]");
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			// -- Set the subject and body text --
			msg.setSubject(subject);

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
			String subject, String body) throws NamingException {
		Properties props = new Properties();

		// -- Attaching to default Session, or we could start a new one --
		String mailHost =  (smtpServer != null) ? smtpServer : "localhost";
		props.put("mail.smtp.host", mailHost); //$NON-NLS-1$ //$NON-NLS-2$
		Session session = getSession().getInstance(props, null);

		MimeMessage msg = new MimeMessage(session);

		// -- Create a new message --
		// -- Set the FROM and TO fields --
		try
		{
			org.apache.commons.logging.LogFactory.getLog(MailUtils.class)
				.info("Sending mail ["+subject+"] from ["+from+"] to ["+to+"] via ["+mailHost+"]");
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, to.toArray(new InternetAddress[to.size()]));
			// -- Set the subject and body text --
			msg.setSubject(subject);

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

	public static void sendMail(String smtpServer, Set<InternetAddress>  to, String from,
			String subject, String body) throws MessagingException,
			NamingException {

		Session session = getSession();

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
			msg.setSubject(subject);

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
}
