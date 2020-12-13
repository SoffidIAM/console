package com.soffid.iam.web.zk;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import com.soffid.iam.api.User;
import com.soffid.iam.config.Config;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.Languages;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.servei.ejb.UsuariService;

/**
 * Classe per establir i guardar les preferències de configuració del SEU
 * 
 * Alejandro Usero Ruiz - 25 d'agost de 2011
 * 
 * @author u88683
 * 
 */
public class ConfigureUserSettings {

	private static final long serialVersionUID = 1L;

	// Preferències/dades de configuració
	public static final String SEU_LANG = "lang"; //$NON-NLS-1$
	
	// Nom de les variables de sessio
	public static final String SESSIO_IDIOMA = "es.caib.seycon.ng.web.idioma"; //$NON-NLS-1$
	public static final String SESSIO_TIMEZONE = Attributes.PREFERRED_TIME_ZONE; //$NON-NLS-1$

	public static final String SESSIO_DATEFORMAT = "es.caib.seycon.ng.web.dateformat";

	public static final String SESSIO_TIMEFORMAT = "es.caib.seycon.ng.web.timeformat";

	public static final String SESSIO_IP = "es.caib.seycon.ng.web.ip";
	
	static Log log = LogFactory.getLog(ConfigureUserSettings.class);
	
	
	public static Locale configuraLocale (String language) {
		HttpServletRequest req = (HttpServletRequest) org.zkoss.zk.ui.Executions
				.getCurrent().getNativeRequest();
		return configuraLocale(language, req);
	}
	/**
	 * Si l'usuari té un idioma definit a la seva configuració
	 * fem un override de la configuració del navegador i de 
	 * la pàgina de login
	 * @param language
	 */
	public static Locale configuraLocale (String language, HttpServletRequest req) {
		
		java.util.Locale idioma = null; 
		
		// Intentem crear un locale del idioma
		try {
			if (language != null)
				idioma = new Locale (language);
		} catch (Throwable th) {
			idioma = null;
		}
		

		// Si l'usuari no té com a preferència l'idioma, agafem el de
		// el navegador o de la pàgina de login
		if (idioma == null)  {
			if (ConfigurationCache.getProperty("soffid.language") != null)
				idioma = new Locale(ConfigurationCache.getProperty("soffid.language"));
			else
			{
	            if (idioma == null)
	            {
	    			// Medium priority, web browser preferences ( if any )
	                Enumeration e = req.getLocales();
	                while (e.hasMoreElements())
	                {
                        Locale l = (Locale) e.nextElement();
					    for (int i = 0; i < com.soffid.iam.web.Languages.langs.length; i++)
					    {
					    	if (l.getLanguage().equals( Languages.langs[i] ))
					    		idioma = l;
                        }
					    if ( idioma != null)
					    	break;
	                }
	                if (idioma == null)
	                {
	                    // Low priority, default language
	        			if (ConfigurationCache.getProperty("soffid.language.default") == null)
	        				idioma = new Locale("en","US"); //$NON-NLS-1$ //$NON-NLS-2$
	        			else
	        				idioma = new Locale (ConfigurationCache.getProperty("soffid.language.default"));
	        		
	                }
	            }
            	
            }

		}
		
		// Con esto, el zk establece el idioma:
		if (req != null)
			req.getSession().setAttribute(Attributes.PREFERRED_LOCALE, idioma); //$NON-NLS-1$
		else
		{
			Session sessio = Sessions.getCurrent();
			if (sessio != null)
				sessio.setAttribute(Attributes.PREFERRED_LOCALE, idioma); //$NON-NLS-1$
		}
		org.zkoss.util.Locales.setThreadLocal(idioma);
		return idioma;
	}
	
	/**
	 * Obté les preferències de l'usuari a nivel de SEU
	 * @throws Exception
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws CreateException
	 */
	public static void configuraUsuariSEU(HttpServletRequest req) throws Exception,
			RemoteException, NamingException, CreateException  {
		
		// Comprovem que no s'hagin obtinunnamed1()gut les dades en aquesta sessió
		HttpSession sessio = req.getSession();
		
		User user = com.soffid.iam.EJBLocator.getUserService().getCurrentUser();
		if (user == null)
		{
			Locale l = configuraLocale(null, req);
			return;
		}
		
		String preferredLanguage = (String) com.soffid.iam.EJBLocator.getPreferencesService().findMyPreference(SEU_LANG);
		Locale l  = configuraLocale(preferredLanguage, req);
		sessio.setAttribute(SESSIO_IDIOMA, l.getLanguage());
		
		String timezone = (String) com.soffid.iam.EJBLocator.getPreferencesService().findMyPreference("timezone");
		if (timezone == null)
			timezone = ConfigurationCache.getProperty("soffid.timezone");
		if (timezone != null)
			sessio.setAttribute(SESSIO_TIMEZONE, TimeZone.getTimeZone(timezone));
		String dateformat = (String) com.soffid.iam.EJBLocator.getPreferencesService().findMyPreference("dateformat");
		if (dateformat == null)
			dateformat = ConfigurationCache.getProperty("soffid.dateformat");
		if (dateformat != null)
			sessio.setAttribute(SESSIO_DATEFORMAT, dateformat.trim());
		String timeformat = (String) com.soffid.iam.EJBLocator.getPreferencesService().findMyPreference("timeformat");
		if (timeformat == null)
			timeformat = ConfigurationCache.getProperty("soffid.timeformat");
		if (timeformat != null)
			sessio.setAttribute(SESSIO_TIMEFORMAT, timeformat.trim());
		String sourceIP = Security.getClientIp();
		sessio.setAttribute(SESSIO_IP, sourceIP);
		com.soffid.iam.EJBLocator.getPreferencesService().updateMyPreference("last_ip", sourceIP);
	}

}
