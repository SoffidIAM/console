package com.soffid.iam.service;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.soffid.iam.api.AuthorizationRole;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserData;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.MailUtils;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class MailServiceImpl extends MailServiceBase {

	String getMailHost ()
	{
		String param1 = ConfigurationCache.getProperty("mail.host");
		if (param1 == null || param1.trim().isEmpty())
			return "localhost";
		else
			return param1;
	}

	String getConfigValue (String param)
	{
		return ConfigurationCache.getProperty(param);
	}

	String getFrom () throws UnknownHostException
	{
		String param1 = ConfigurationCache.getProperty("mail.from");
		if (param1 == null || param1.trim().isEmpty())
			return "soffid@" + InetAddress.getLocalHost().getHostName() ;
		else
			return param1;
	}
	

	@Override
	protected void handleSendHtmlMail(String to, String subject, String body) throws Exception {
		handleSendHtmlMail(to, null, subject, body);
	}
	
	@Override
	protected void handleSendHtmlMail(String to, String cc, String subject, String body)
			throws Exception {
		Session session = MailUtils.getSession(null);

		MimeMessage msg = new MimeMessage(session);

		// -- Create a new message --
		// -- Set the FROM and TO fields --
		try
		{
			msg.setFrom(new InternetAddress(getFrom()));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			if (cc != null && ! cc.trim().isEmpty())
				msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(cc, false));
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
		handleSendTextMail(to, null, subject, body);
	}
	
	@Override
	protected void handleSendTextMail(String to, String cc, String subject, String body)
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
			if (cc != null && ! cc.trim().isEmpty())
				msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(cc, false));
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

	}


	@Override
	protected void handleSendHtmlMailToActors(String[] actors,  String subject,
			String body) throws Exception {
		handleSendHtmlMailToActors(actors, null, subject, body);
	}
	
	@Override
	protected void handleSendHtmlMailToActors(String[] actors, String cc, String subject,
			String body) throws Exception {
		Session session = MailUtils.getSession(null);

		MimeMessage msg = new MimeMessage(session);

		try
		{
			Address[] address = getAddress(actors);
			if (address.length > 0)
			{
				StringBuffer sb = new StringBuffer();
				for (Address a: address) {
					if (sb.length() > 0) sb.append(", ");
					sb.append(a.toString());
				}
				msg.setFrom(new InternetAddress(getFrom()));
				msg.setRecipients(Message.RecipientType.TO, sb.toString());
				msg.setHeader("To", sb.toString());
				if (cc != null && ! cc.trim().isEmpty())
					msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(cc, false));
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
	protected void handleSendTextMailToActors(String[] actors, String subject,
			String body) throws Exception 
	{
		handleSendTextMailToActors(actors, null, subject, body);
	}
	
	@Override
	protected void handleSendTextMailToActors(String[] actors, String cc, String subject,
			String body) throws Exception 
	{
		Session session = MailUtils.getSession(null);

		MimeMessage msg = new MimeMessage(session);

		try
		{
			Address[] address = getAddress(actors);
			if (address.length > 0)
			{
				msg.setFrom(new InternetAddress(getFrom()));
				StringBuffer sb = new StringBuffer();
				for (Address a: address) {
					if (sb.length() > 0) sb.append(", ");
					sb.append(a.toString());
				}
				msg.setRecipients(Message.RecipientType.TO, address);
				msg.setHeader("To", sb.toString());
				if (cc != null && ! cc.trim().isEmpty())
					msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(cc, false));
				// -- Set the subject and body text --
				msg.setSubject(subject, "UTF-8");
	
				// enviem en mime - utf-8, que és com ho tenim al repositori
				msg.setContent(body, "text/plain; charset=utf-8"); //$NON-NLS-1$
				// -- Set some other header information --
				msg.setHeader("X-Mailer", "SoffidMailer"); //$NON-NLS-1$ //$NON-NLS-2$
				msg.setSentDate(new Date());
	
				// -- Send the message --
				Transport.send(msg);
			}
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

	private Address[] getAddress(String[] actors) throws UnsupportedEncodingException, InternalErrorException {
		Set<String> users  = new HashSet<String>();
		Set<Address> addresses = new HashSet<Address>();
		for (String actor: actors)
		{
			Set<String> namedUsers = getNameUsers(actor);
			if (namedUsers != null)
				users.addAll( namedUsers );
			else if (actor.contains("@"))
				addresses.add( new InternetAddress(actor, actor));
		}
		
		for (String user: users)
		{
			InternetAddress addr = getUserAddress(user);
			if (addr != null)
				addresses.add(addr);
		}
		
		return addresses.toArray(new Address[addresses.size()]);
	}
	
	/**
	 * @param actorId
	 * @return
	 * @throws InternalErrorException 
	 * @throws UnsupportedEncodingException 
	 */
	private Set<String> getNameUsers (String actorId) throws InternalErrorException, UnsupportedEncodingException
	{
		HashSet<String> result = new HashSet<String>();
		if (actorId == null)
			return result;
		if (actorId.startsWith("auth:")) //$NON-NLS-1$
		{
			String autorization = actorId.substring(5);
			String domain = null;
			int i = autorization.indexOf('/');
			if (i > 0)
			{
				domain = autorization.substring(i + 1);
				autorization = autorization.substring(0,i);
			}
			AuthorizationService autService = getAuthorizationService();
			for (AuthorizationRole ar: autService.getAuthorizationRoles(autorization))
			{
				String rol = ar.getRole().getName();
				if (domain != null)
					rol = rol + "/" + domain; //$NON-NLS-1$
				rol = rol+"@"+ar.getRole().getSystem(); //$NON-NLS-1$
				Set<String> nameUsers = getNameUsers(rol);
				if (nameUsers != null)
					result.addAll(nameUsers);
			}
			return result;
			
		}
		Security.nestedLogin("mail-server", new String[] { //$NON-NLS-1$
						Security.AUTO_USER_QUERY + Security.AUTO_ALL,
						Security.AUTO_ROLE_QUERY + Security.AUTO_ALL,
						Security.AUTO_GROUP_QUERY + Security.AUTO_ALL,
						Security.AUTO_USER_ROLE_QUERY + Security.AUTO_ALL,
						Security.AUTO_ACCOUNT_QUERY + Security.AUTO_ALL,
						Security.AUTO_APPLICATION_QUERY + Security.AUTO_ALL});
		try {
    		User usuari = getUserService().findUserByUserName(actorId);
    		if (usuari != null)
    		{
    			if (usuari.getActive().booleanValue())
    			{
    				result.add(usuari.getUserName());
    			}
    		}
    		else
    		{
    			Group grup = getGroupService().findGroupByGroupName(actorId);
    			if (grup != null)
    			{
    				for (GroupUser ug: getGroupService().findUsersBelongtoGroupByGroupName(actorId))
    				{
    					result.add( ug.getUser()) ;
    				}
    				return result;
    			}
    			else
    			{
    				int i = actorId.indexOf('@');
    				String roleName;
    				String dispatcher;
    				String scope = null;
    				if (i >= 0)
    				{
    					roleName = actorId.substring(0, i);
    					dispatcher = actorId.substring(i+1);
    				}
    				else
    				{
    					roleName = actorId;
						SystemEntity defaultDispatcher = getSystemEntityDao().findSoffidSystem();
    					dispatcher = defaultDispatcher.getName();
    				}
    				i = roleName.lastIndexOf('/');
    				if (i >= 0)
    				{
    					scope = roleName.substring(i+1);
    					roleName = roleName.substring(0, i);
    				}
    				ApplicationService aplicacioService = getApplicationService();
    				Role role = aplicacioService.findRoleByNameAndSystem(roleName, dispatcher);
    				if (role == null) {
    					return null;
    				} else {
    					for (RoleGrant grant: aplicacioService.findEffectiveRoleGrantsByRoleId(role.getId()))
    					{
    						if (grant.getUser() != null && (scope == null || scope.equals (grant.getDomainValue())))
    						{
    							result.add(grant.getUser());
    						}
    					}
    				}
    				return result;
    			}
    		}
    		return result;
		} finally {
			Security.nestedLogoff();
		}
		
	}

	
	private InternetAddress getUserAddress (String userName) throws UnsupportedEncodingException, InternalErrorException
	{
		User user = getUserService().findUserByUserName(userName);
		if (! user.getActive().booleanValue())
			return null;
		if (user.getShortName() != null && user.getMailDomain() != null)
		{
			return new InternetAddress( 
						user.getShortName()+"@"+user.getMailDomain(),
						user.getFullName());
		}
		else
		{
			UserData dada = getUserService().findDataByUserAndCode(user.getUserName(), "EMAIL"); //$NON-NLS-1$
			if (dada != null && dada.getValue() != null)
			{
				return new InternetAddress(dada.getValue(),
						user.getFullName());
			}
			else
				return null;
		}

	}

	@Override
	protected void handleSendHtmlMail(String to, String subject, String body, Collection mimeBodyParts)
			throws Exception {
		handleSendHtmlMail(to, null, subject, body, mimeBodyParts);
	}
	
	@Override
	protected void handleSendHtmlMail(String to,  String cc, String subject, String body, Collection mimeBodyParts)
			throws Exception {
		Session session = MailUtils.getSession(null);

		MimeMessage msg = new MimeMessage(session);

		// -- Create a new message --
		// -- Set the FROM and TO fields --
		try
		{
			msg.setFrom(new InternetAddress(getFrom()));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			if (cc != null && ! cc.trim().isEmpty())
				msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(cc, false));
			// -- Set the subject and body text --
			msg.setSubject(subject, "UTF-8");

			// -- Set some other header information --
			msg.setHeader("X-Mailer", "SoffidMailer"); //$NON-NLS-1$ //$NON-NLS-2$
			msg.setSentDate(new Date());
			
			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(body, "text/html; charset=utf-8");
			multipart.addBodyPart(messageBodyPart);

			for (Object mimeBodyPart: mimeBodyParts) {
				multipart.addBodyPart((BodyPart)mimeBodyPart);
			}
			
			msg.setContent(multipart);
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
	protected void handleSendTextMailToActors(String[] actors, String subject, String body, Collection mimeBodyParts)
			throws Exception {
		handleSendTextMailToActors(actors, null, subject, body, mimeBodyParts);
	}
	
	@Override
	protected void handleSendTextMailToActors(String[] actors, String cc, String subject, String body, Collection mimeBodyParts)
			throws Exception {
		Session session = MailUtils.getSession(null);

		MimeMessage msg = new MimeMessage(session);

		try
		{
			Address[] address = getAddress(actors);
			if (address.length > 0)
			{
				msg.setFrom(new InternetAddress(getFrom()));
				msg.setRecipients(Message.RecipientType.TO, address);
				if (cc != null && ! cc.trim().isEmpty())
					msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(cc, false));
				// -- Set the subject and body text --
				msg.setSubject(subject, "UTF-8");
	
				// -- Set some other header information --
				msg.setHeader("X-Mailer", "SoffidMailer"); //$NON-NLS-1$ //$NON-NLS-2$
				msg.setSentDate(new Date());
				
				MimeMultipart multipart = new MimeMultipart("related");
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(body, "text/html; charset=utf-8");
				multipart.addBodyPart(messageBodyPart);

				for (Object mimeBodyPart: mimeBodyParts) {
					multipart.addBodyPart((BodyPart)mimeBodyPart);
				}
				
				msg.setContent(multipart);
				// -- Send the message --
				Transport.send(msg);
			}
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
}
