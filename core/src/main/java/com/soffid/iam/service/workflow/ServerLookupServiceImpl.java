// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.service.workflow;

import es.caib.seycon.ng.servei.workflow.*;

import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserData;
import com.soffid.iam.service.ConfigurationService;
import com.soffid.iam.service.NetworkService;
import com.soffid.iam.utils.MailUtils;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.exception.InternalErrorException;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.NamingException;

public class ServerLookupServiceImpl extends
		com.soffid.iam.service.workflow.ServerLookupServiceBase {

	/**
	 * @return retorna una collection amb tots els servidors de correu
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Host> handleGetMailServers() throws java.lang.Exception {
		NetworkService xarxaService = getNetworkService();
		return xarxaService.getMailServers();
	}

	/**
	 * @return retorna una collection amb tots els servidors de perfil
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Host> handleGetServidorsPerfil() throws java.lang.Exception {
		NetworkService xarxaService = getNetworkService();
		return xarxaService.getProfileServers();
	}

	/**
	 * @return retorna una collection amb tots els servidors home
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Host> handleGetHomeServers() throws java.lang.Exception {
		NetworkService xarxaService = getNetworkService();
		return xarxaService.getHomeServers();
	}

	/**
	 * @param nomUsuari
	 * @param llinatgeUsuari
	 * @param nomServidorCorreu
	 * @return
	 * @throws java.lang.Exception
	 */
	protected java.lang.String handleGetMailboxNameByMailServer(java.lang.String nomUsuari, java.lang.String llinatgeUsuari, java.lang.String nomServidorCorreu) throws java.lang.Exception {
		return nomUsuari + "." + llinatgeUsuari + "@" + nomServidorCorreu; //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void send(String to,
			String subject, String body) throws MessagingException, NamingException, InternalErrorException {
		String mailHost = "localhost"; //$NON-NLS-1$

		String mailFrom = "no-reply@soffid.com"; //$NON-NLS-1$

		ConfigurationService configService = getConfigurationService();
		Configuration param1 = configService.findParameterByNameAndNetworkName("mail.host", null); //$NON-NLS-1$
		if (param1 != null)
			mailHost = param1.getValue();
		Configuration param2 = configService.findParameterByNameAndNetworkName("mail.from", null); //$NON-NLS-1$
		if (param2 != null)
			mailFrom = param2.getValue();

		MailUtils.sendMail(mailHost, to, mailFrom, subject, body);
	}
	
	protected void handleSendMail(String codiUsuari, String header, String content) throws Exception {
	    com.soffid.iam.service.UserService usuariService = getUserService();		
	    
	    User usuari = usuariService.findUserByUserName(codiUsuari);
		String email = null;
		if (usuari != null)
		{
			if (usuari.getShortName() != null && !"".equals(usuari.getShortName())) { //$NON-NLS-1$
				if (usuari.getMailDomain() != null && !"".equals(usuari.getMailDomain())) //$NON-NLS-1$
					email = usuari.getShortName() + "@" + usuari.getMailDomain(); //$NON-NLS-1$
				else
					email = null;
			} else {
				UserData dadaUsuari = usuariService.findDataByUserAndCode(usuari.getUserName(), "EMAIL"); //$NON-NLS-1$
				if (dadaUsuari != null) email = dadaUsuari.getValue();
			}
		} 
		
		if (email != null)
			send (email, header, content);
	}
	
	protected void handleSendMailGeneric(String adrecaCorreuCompleta, String header, String content) throws Exception {
		send (adrecaCorreuCompleta, header, content);
	}

}