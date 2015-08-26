// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei.workflow;

import java.util.LinkedList;
import java.util.Properties;

import es.caib.seycon.net.SeyconServiceLocator;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.ConfiguracioService;
import es.caib.seycon.ng.servei.XarxaService;
import es.caib.seycon.ng.utils.MailUtils;

import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.NamingException;

import java.util.*;

public class ServidorsServiceImpl extends
		es.caib.seycon.ng.servei.workflow.ServidorsServiceBase {

	/**
	 * @return retorna una collection amb tots els servidors de correu
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Maquina> handleGetServidorsCorreu()
			throws java.lang.Exception {
		XarxaService xarxaService = getXarxaService();
		return xarxaService.getServidorsCorreu();
	}

	/**
	 * @return retorna una collection amb tots els servidors de perfil
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Maquina> handleGetServidorsPerfil()
			throws java.lang.Exception {
		XarxaService xarxaService = getXarxaService();
		return xarxaService.getServidorsPerfil();
	}

	/**
	 * @return retorna una collection amb tots els servidors home
	 * @throws java.lang.Exception
	 */
	protected java.util.Collection<Maquina> handleGetServidorsHome()
			throws java.lang.Exception {
		XarxaService xarxaService = getXarxaService();
		return xarxaService.getServidorsHome();
	}

	/**
	 * @param nomUsuari
	 * @param llinatgeUsuari
	 * @param nomServidorCorreu
	 * @return
	 * @throws java.lang.Exception
	 */
	protected java.lang.String handleGetNomBustiaDeCorreuByServidorCorreu(
			java.lang.String nomUsuari, java.lang.String llinatgeUsuari,
			java.lang.String nomServidorCorreu) throws java.lang.Exception {
		return nomUsuari + "." + llinatgeUsuari + "@" + nomServidorCorreu; //$NON-NLS-1$ //$NON-NLS-2$
	}

	private static void send(String to,
			String subject, String body) throws MessagingException, NamingException, InternalErrorException {
		String mailHost = "localhost"; //$NON-NLS-1$

		String mailFrom = "no-reply@soffid.com"; //$NON-NLS-1$

		ConfiguracioService configService = ServiceLocator.instance().getConfiguracioService();
		Configuracio param1 = configService.findParametreByCodiAndCodiXarxa("mail.host", null); //$NON-NLS-1$
		if (param1 != null)
			mailHost = param1.getValor();
		Configuracio param2 = configService.findParametreByCodiAndCodiXarxa("mail.from", null); //$NON-NLS-1$
		if (param2 != null)
			mailFrom = param2.getValor();

		MailUtils.sendMail(mailHost, to, mailFrom, subject, body);
	}
	
	protected void handleSendMail(String codiUsuari, String header, String content) throws Exception {
		ServiceLocator locator = SeyconServiceLocator.instance();		
	    es.caib.seycon.ng.servei.UsuariService usuariService = locator.getUsuariService();		
	    
	    Usuari usuari = usuariService.findUsuariByCodiUsuari(codiUsuari);
		String email = null;
		if (usuari != null)
		{
			if (usuari.getNomCurt() != null && !"".equals(usuari.getNomCurt())) { //$NON-NLS-1$
				if (usuari.getDominiCorreu() != null && !"".equals(usuari.getDominiCorreu())) //$NON-NLS-1$
					email = usuari.getNomCurt() + "@" + usuari.getDominiCorreu(); //$NON-NLS-1$
				else
					email = null;
			} else {
				DadaUsuari dadaUsuari = usuariService.findDadaByCodiTipusDada(usuari.getCodi(),"EMAIL"); //$NON-NLS-1$
				if (dadaUsuari != null) email = dadaUsuari.getValorDada();
			}
		} 
		
		if (email != null)
			send (email, header, content);
	}
	
	protected void handleSendMailGeneric(String adrecaCorreuCompleta, String header, String content) throws Exception {
		send (adrecaCorreuCompleta, header, content);
	}

}