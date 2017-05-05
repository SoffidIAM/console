package com.soffid.iam.service;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
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

import org.jbpm.taskmgmt.exe.PooledActor;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.AutoritzacioRol;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariGrup;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.DispatcherEntityDao;
import es.caib.seycon.ng.servei.AplicacioService;
import es.caib.seycon.ng.servei.AutoritzacioService;
import es.caib.seycon.ng.servei.ConfiguracioService;
import es.caib.seycon.ng.servei.GrupService;
import es.caib.seycon.ng.utils.MailUtils;
import es.caib.seycon.ng.utils.Security;


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


	@Override
	protected void handleSendHtmlMailToActors(String[] actors, String subject,
			String body) throws Exception {
		Session session = MailUtils.getSession(null);

		MimeMessage msg = new MimeMessage(session);

		try
		{
			Address[] address = getAddress(actors);
			msg.setFrom(new InternetAddress(getFrom()));
			msg.setRecipients(Message.RecipientType.TO, address);
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
	protected void handleSendTextMailToActors(String[] actors, String subject,
			String body) throws Exception 
	{
		Session session = MailUtils.getSession(null);

		MimeMessage msg = new MimeMessage(session);

		try
		{
			Address[] address = getAddress(actors);
			msg.setFrom(new InternetAddress(getFrom()));
			msg.setRecipients(Message.RecipientType.TO, address);
			// -- Set the subject and body text --
			msg.setSubject(subject);

			// enviem en mime - utf-8, que és com ho tenim al repositori
			msg.setContent(body, "text/plain; charset=utf-8"); //$NON-NLS-1$
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

	private Address[] getAddress(String[] actors) throws UnsupportedEncodingException, InternalErrorException {
		Set<String> users  = new HashSet<String>();
		for (String actor: actors)
		{
			users.addAll( getNameUsers(actor));
		}
		
		Set<Address> addresses = new HashSet<Address>();
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
			AutoritzacioService autService = ServiceLocator.instance().getAutoritzacioService();
			for (AutoritzacioRol ar: autService.getRolsAutoritzacio(autorization))
			{
				String rol = ar.getRol().getNom();
				if (domain != null)
					rol = rol + "/" + domain; //$NON-NLS-1$
				rol = rol+"@"+ar.getRol().getBaseDeDades(); //$NON-NLS-1$
				result.addAll(getNameUsers(rol));
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
    		Usuari usuari = ServiceLocator.instance().getUsuariService().findUsuariByCodiUsuari(actorId);
    		if (usuari != null)
    		{
    			if (usuari.getActiu().booleanValue())
    			{
    				result.add(usuari.getCodi());
    			}
    		}
    		else
    		{
    			GrupService gs = ServiceLocator.instance().getGrupService();
    			Grup grup = gs.findGrupByCodiGrup(actorId);
    			if (grup != null)
    			{
    				StringBuffer sb = new StringBuffer();
    				for (UsuariGrup ug: gs.findUsuarisPertanyenAlGrupByCodiGrup(actorId))
    				{
    					result.add( ug.getCodiUsuari()) ;
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
    					DispatcherEntityDao dao = (DispatcherEntityDao) ServiceLocator.instance().getService("dispatcherEntityDao");
						DispatcherEntity defaultDispatcher = dao.findSoffidDispatcher();
    					dispatcher = defaultDispatcher.getCodi();
    				}
    				i = roleName.lastIndexOf('/');
    				if (i >= 0)
    				{
    					scope = roleName.substring(i+1);
    					roleName = roleName.substring(0, i);
    				}
    				AplicacioService aplicacioService = ServiceLocator.instance().getAplicacioService();
					for (Rol role: aplicacioService.findRolsByFiltre(roleName, "%", "%", dispatcher, "%", "%")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    				{
    					for (RolGrant grant: aplicacioService.findEffectiveRolGrantsByRolId(role.getId()))
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
		Usuari user = getUsuariService().findUsuariByCodiUsuari(userName);
		if (user.getNomCurt() != null && user.getDominiCorreu() != null)
		{
			return new InternetAddress( 
						user.getNomCurt()+"@"+user.getDominiCorreu(),
						user.getFullName());
		}
		else
		{
			DadaUsuari dada = ServiceLocator.instance().getUsuariService().findDadaByCodiTipusDada(user.getCodi(), "EMAIL"); //$NON-NLS-1$
			if (dada != null && dada.getValorDada() != null)
			{
				return new InternetAddress(dada.getValorDada(),
						user.getFullName());
			}
			else
				return null;
		}

	}
}
