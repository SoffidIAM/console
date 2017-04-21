package com.soffid.iam.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.servei.ConfiguracioService;
import es.caib.seycon.ng.utils.MailUtils;


public class MailServiceImpl extends MailServiceBase {

	String getMailHost ()
	{
		Configuracio param1;
		try {
			ConfiguracioService configService = ServiceLocator.instance().getConfiguracioService();
			param1 = configService.findParametreByCodiAndCodiXarxa("mail.host", null); //$NON-NLS-1$
			if (param1 != null)
				return param1.getValor();
		} catch (Exception e) {
		}
		return "localhost";
	}

	String getConfigValue (String param)
	{
		Configuracio param1;
		try {
			ConfiguracioService configService = ServiceLocator.instance().getConfiguracioService();
			param1 = configService.findParametreByCodiAndCodiXarxa(param, null); //$NON-NLS-1$
			if (param1 != null)
				return param1.getValor();
		} catch (Exception e) {
		}
		return null;
	}

	String getFrom () throws UnknownHostException
	{
		Configuracio param1;
		try {
			ConfiguracioService configService = ServiceLocator.instance().getConfiguracioService();
			param1 = configService.findParametreByCodiAndCodiXarxa("mail.from", null); //$NON-NLS-1$
			if (param1 != null)
				return param1.getValor();
		} catch (Exception e) {
		}
		return "soffid@" + InetAddress.getLocalHost().getHostName() ;
	}
	

	@Override
	protected void handleSendHtmlMail(String to, String subject, String body)
			throws Exception {
		Session session = MailUtils.getSession(null);

		MimeMessage msg = new MimeMessage(session);

		// -- Create a new message --
		// -- Set the FROM and TO fields --
		try
		{
			msg.setFrom(new InternetAddress(getFrom()));
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
			e.printStackTrace();
			// throw e;
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
			// throw e;
		}

	}

	@Override
	protected void handleSendTextMail(String to, String subject, String body)
			throws Exception {
		Session session = MailUtils.getSession(null);

		MimeMessage msg = null;
		msg = new MimeMessage(session);

		// -- Create a new message --

		// -- Set the FROM and TO fields --
		try {
			msg.setFrom(new InternetAddress(getFrom()));
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

	}

}
