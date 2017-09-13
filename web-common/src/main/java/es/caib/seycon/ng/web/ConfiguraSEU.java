package es.caib.seycon.ng.web;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import com.soffid.iam.config.Config;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariSEU;
import es.caib.seycon.ng.servei.ejb.UsuariService;

/**
 * Classe per establir i guardar les preferències de configuració del SEU
 * 
 * Alejandro Usero Ruiz - 25 d'agost de 2011
 * 
 * @author u88683
 * 
 */
public class ConfiguraSEU extends Vbox {

	private static final long serialVersionUID = 1L;

	private static String versio = getVersioSEU();
	
	private boolean mostraSplash = false;
	
	// Preferències/dades de configuració
	public static final String SEU_LANG = "lang"; //$NON-NLS-1$
	public static final String SEU_LAST_IP = "lastIP"; //$NON-NLS-1$
	
	
	// Nom de les variables de sessio
	private static final String SESSIO_VERSIO_SEU = "es.caib.seycon.ng.web.versioSEU";  //$NON-NLS-1$
	private static final String SESSIO_LAST_LOGIN_SEU = "es.caib.seycon.ng.web.lastLoginSEU"; //$NON-NLS-1$
	private static final String SESSIO_LAST_IP_SEU = "es.caib.seycon.ng.web.lastIP"; //$NON-NLS-1$
	// Atenció: el valor de la variable de sessió SESSIO_CURRENT_IP_SEU s'empra des dels workflows:
	private static final String SESSIO_CURRENT_IP_SEU = "es.caib.seycon.ng.web.currentIP"; //$NON-NLS-1$
	private static final String SESSIO_IDIOMA = "es.caib.seycon.ng.web.idioma"; //$NON-NLS-1$
	
	Log log = LogFactory.getLog(ConfiguraSEU.class);
	
	
	public ConfiguraSEU() throws RemoteException, NamingException, CreateException, Exception {
		super();
		
		addEventListener(org.zkoss.zk.ui.event.Events.ON_CLIENT_INFO,
					new Eventos());
		
		// Establim el nom de l'aplicacio (wohoo) per als missatgebox sense titol
		try {Executions.getCurrent().getDesktop().getWebApp().setAppName("Soffid IAM");} catch (Throwable th) {} //$NON-NLS-1$
		
		// Obtenim informació de l'usuariSEU
		configuraUsuariSEU();
	}
	
	/**
	 * Mètode per obtindre la IP des de la que es
	 * connecta l'usuari
	 * @return
	 */
	private String getClientIPValue() {
		try {
			Class c = Class.forName("es.caib.loginModule.auth.ClientIPValve"); //$NON-NLS-1$
			Method m = c.getMethod("getClientIP",new Class[]{}); //$NON-NLS-1$
			Object res = m.invoke(null, new Object[] {});
			if (res instanceof String)
				return (String) res;
		} catch (Throwable th) {
		}

		return ""; // No l'hem trobat //$NON-NLS-1$
	}
	
	/**
	 * Event per a establir les preferències gràfiques
	 * del SEU (resolució, etc..) 
	 * @author u88683
	 */
	class Eventos implements EventListener {
		public void onEvent(org.zkoss.zk.ui.event.Event event)
				throws Exception {
			if (event instanceof ClientInfoEvent) {
				onClientInfo((ClientInfoEvent) event);
			}
		}
		
		void onClientInfo(ClientInfoEvent evt) {
			
			org.zkoss.zk.ui.Session sessio = org.zkoss.zk.ui.Sessions.getCurrent();
			
			if (sessio == null) return;
			
			int ample = evt.getDesktopWidth(); 
			//int widthPantalla = evt.getScreenWidth();
			int heigthPantalla = evt.getDesktopHeight();
			int hPantalla2 = (int) ((heigthPantalla)*0.90);
			int amplediv2 = (int) ((ample-50)/2);
			int amplediv2_98 = (int) (amplediv2 * 0.98);
			int amplefinestra = (int) (ample * 0.90);
			if (ample < 800) {
				ample = 850; //resolució mínima 800
			 	amplediv2 = 400;
			 	amplediv2_98 = 392;
			 	amplefinestra = 750;
			}
			String hPantalla = (hPantalla2)+""; //$NON-NLS-1$
			//String wPantalla = (widthPantalla * 0.94)+"px";
			
			sessio.setAttribute("ample", new Integer(ample)); //$NON-NLS-1$
			sessio.setAttribute("amplediv2", new Integer(amplediv2)); //$NON-NLS-1$
			sessio.setAttribute("amplediv2_98",  new Integer(amplediv2_98)); //$NON-NLS-1$
			sessio.setAttribute("amplaria", "100%"); //$NON-NLS-1$
			sessio.setAttribute("amplaria2", "100%"); //$NON-NLS-1$
			sessio.setAttribute("amplefinestra", amplefinestra+"px"); //$NON-NLS-1$ //$NON-NLS-2$
			//sessio.setAttribute("widthPantalla", wPantalla);
			sessio.setAttribute("heigthPantalla", hPantalla); //$NON-NLS-1$
	    }		
	}
	
	/**
	 * Mostrem la finestra de Splash només si fa falta
	 * @throws Exception
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws CreateException
	 */
	public void onCreate() throws Exception, RemoteException, NamingException,
			CreateException {
		if (mostraSplash && false) {
			Splash splash = new Splash();
			appendChild(splash);
			splash.mostraFinestra();
		}
	}
	
	
	
	/**
	 * Mètode per obtindre la versió actual del SEU
	 * 
	 * @return
	 */
	private static String getVersioSEU() {
		try {
			InputStream in = Config.class
					.getResourceAsStream("/META-INF/maven/es.caib.seycon.ng/SEU/pom.properties"); //$NON-NLS-1$
			if (in == null) {
				return "TEST"; //$NON-NLS-1$
			} else {
				Properties p = new Properties();
				p.load(in);
				return p.getProperty("version"); //$NON-NLS-1$
			}
		} catch (IOException e) {
			return "TEST"; //$NON-NLS-1$
		}

	}
	
	/**
	 * Si l'usuari té un idioma definit a la seva configuració
	 * fem un override de la configuració del navegador i de 
	 * la pàgina de login
	 * @param language
	 */
	public static Locale configuraLocale (String language) {
		
		java.util.Locale idioma = null; 
		org.zkoss.zk.ui.Session sessio = org.zkoss.zk.ui.Sessions.getCurrent();
		
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
			if (System.getProperty("soffid.language") != null)
				idioma = new Locale(System.getProperty("soffid.language"));
			else
			{
				HttpServletRequest req = (HttpServletRequest) org.zkoss.zk.ui.Executions
						.getCurrent().getNativeRequest();
	            if (idioma == null)
	            {
	    			// Medium priority, web browser preferences ( if any )
	                Enumeration e = req.getLocales();
	                while (e.hasMoreElements())
	                {
	                        Locale l = (Locale) e.nextElement();
	                        if (l.getLanguage().equalsIgnoreCase("ca"))
	                        {
	                        	idioma = l;
	                        	break;
	                        }
	                        else if (l.getLanguage().equalsIgnoreCase("es"))
	                        {
	                        	idioma = l;
	                        	break;
	                        }
	                        else if (l.getLanguage().equalsIgnoreCase("en"))
	                        {
	                        	idioma = l;
	                        	break;
	                        }
	                        else if (l.getLanguage().equalsIgnoreCase("nl"))
	                        {
	                        	idioma = l;
	                        	break;
	                        }
	                }
	                if (idioma == null)
	                {
	                    // Low priority, default language
	        			if (System.getProperty("soffid.language.default") == null)
	        				idioma = new Locale("en","US"); //$NON-NLS-1$ //$NON-NLS-2$
	        			else
	        				idioma = new Locale (System.getProperty("soffid.language.default"));
	        		
	                }
	            }
            	
            }

		}
		
		// Con esto, el zk establece el idioma:
		sessio.setAttribute("px_preferred_locale", idioma); //$NON-NLS-1$
		org.zkoss.util.Locales.setThreadLocal(idioma);
		return idioma;
	}
	
	private UsuariService usuari_service = null;
	
	private UsuariService getUsuariService() throws NamingException, CreateException, RemoteException {
		if (usuari_service == null) usuari_service = EJBLocator.getUsuariService();
		return usuari_service;
	}
	
	private UsuariSEU obteUsuariSEU(String codiUsuari) {
		try {
			return getUsuariService().findUsuariSEUByCodiUsuari(codiUsuari);
		} catch (Throwable th) {
			log.warn(th);
			return null;
		}
	}
	
	private Usuari findCurrentUsuari() {
		try {
			return getUsuariService().getCurrentUsuari();
		} catch (Throwable th) {
			log.warn(th);
			return null;
		}
	}
	
	
	

	/**
	 * Obté les preferències de l'usuari a nivel de SEU
	 * @throws Exception
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws CreateException
	 */
	private void configuraUsuariSEU() throws Exception,
			RemoteException, NamingException, CreateException  {
		
		// Comprovem que no s'hagin obtinunnamed1()gut les dades en aquesta sessió
		org.zkoss.zk.ui.Session sessio = org.zkoss.zk.ui.Sessions.getCurrent();
		String versioSEU = null;
		try {
			versioSEU = (String) sessio
					.getAttribute(SESSIO_VERSIO_SEU);
		} catch (Throwable th) {
		}
		
		Usuari user = findCurrentUsuari();
		if (user == null)
		{
			Locale l = configuraLocale(null);
			sessio.setAttribute(SESSIO_IDIOMA, l.getLanguage());
			String currentIPUsuari = getClientIPValue();
			if (currentIPUsuari!=null) {
				sessio.setAttribute(SESSIO_CURRENT_IP_SEU, currentIPUsuari);
			}
			
			return;
		}
		
		String codiUsuari = user.getCodi();

		if (versioSEU == null) {
			// Només actualitcem l'usuari si canvia de versió o
			// si la sessio ha caducat
			UsuariSEU usuariSEU = obteUsuariSEU(codiUsuari);
			
			// L'usuariSEU ja existeix: obtenim informació de preferències
			if (usuariSEU != null) {
				String usuDarreraVersio = usuariSEU.getVersio();
				
				// L'usuari encara no té versió del SEU (encara q ja existeix)
				if (usuDarreraVersio == null
						|| (usuDarreraVersio != null && !usuDarreraVersio
								.equals(versio))) {
					mostraSplash = true;					
										
					// Actualitzem la versió del SEU a l'usuari
					usuariSEU.setVersio(versio);
				}
				
				// Obtenim la darrera sessió de l'usuari (no aquesta):
				Calendar lastLogin = usuariSEU.getDataDarrerLogin();
				// Guardem en la sessió el darrer login
				sessio.setAttribute(SESSIO_LAST_LOGIN_SEU, lastLogin);
				
				//
				// CONFIGURACIÓ DE L'USUARI:
				//
				// OBTENIM informació de la configuració de l'usuari
				
				// Obtenim la darrera IP de l'usuari
				String lastIP = (String) usuariSEU.getPreferenciesSEU().get(SEU_LAST_IP);
				sessio.setAttribute(SESSIO_LAST_IP_SEU, lastIP);
				
				// Establim les seves preferències d'idioma
				// per defecte en català.. a no se que l'usuari
				// ho especifique
				Locale l  = configuraLocale((String) usuariSEU.getPreferenciesSEU().get(SEU_LANG));
				sessio.setAttribute(SESSIO_IDIOMA, l.getLanguage());
				
				// Actualitzem el seu darrer login
				usuariSEU.setDataDarrerLogin(Calendar.getInstance());
				
				// I guardem la seua ip a les preferències de l'usuari
				String currentIPUsuari = getClientIPValue();
				if (currentIPUsuari!=null) {
					usuariSEU.getPreferenciesSEU().put(SEU_LAST_IP, currentIPUsuari);
					// Per a que es puga emprar des dels workflows
					sessio.setAttribute(SESSIO_CURRENT_IP_SEU, currentIPUsuari);
				}
				
				// Guardem la versió e la sessioó
				sessio.setAttribute(SESSIO_VERSIO_SEU, versio);

				// Actualitzem les dades de l'usuari
				// data i versió de l'accés al SEU
				getUsuariService().update(usuariSEU);
			} else { // Hem de crear una nova fila de usuariSEU
				
				// Verifiquem que l'usuari existeix (bug login de Signatura)
				
				usuariSEU = new es.caib.seycon.ng.comu.UsuariSEU();
				usuariSEU.setVersio(versio);
				// Guardem la data del darrer inici de sessió de l'usuari
				usuariSEU.setDataDarrerLogin(Calendar.getInstance());
				// Guardem la ip de conexio:
				// I guardem la seua ip
				String currentIPUsuari = getClientIPValue();
				HashMap prefs = new HashMap();
				
				if (currentIPUsuari!=null) {
					// Només tenim una preferència: la IP
					prefs.put(SEU_LAST_IP, currentIPUsuari);
					// Per a que es puga emprar des dels workflows
					sessio.setAttribute(SESSIO_CURRENT_IP_SEU, currentIPUsuari);
				}
				
				// Guardem els favorits encara que siga buit
				usuariSEU.setPreferenciesSEU(prefs);
				
				// Establim les seves preferències d'idioma
				// per defecte per defecte, l'idioma del navegador
				// a no se que l'usuari ho especifique al seu perfil
				HttpServletRequest req = (HttpServletRequest) org.zkoss.zk.ui
								.Executions.getCurrent().getNativeRequest();
				
				Locale lang = configuraLocale(null);
				sessio.setAttribute(SESSIO_IDIOMA, lang.getLanguage());
				usuariSEU.getPreferenciesSEU().put(SEU_LANG, lang.getLanguage());
				
				// Guardem les variable de sessio:
				sessio.setAttribute(SESSIO_LAST_LOGIN_SEU, null);
				sessio.setAttribute(SESSIO_VERSIO_SEU, versio);
				sessio.setAttribute(SESSIO_LAST_IP_SEU, null);
				
				usuariSEU.setCodiUsuari(codiUsuari);

				getUsuariService().create(usuariSEU); // el creem
				//Mostrem la fiestra de novetats
				mostraSplash = true;
			}
		}
	}

	/**
	 * Finestra on se mostra les novetats d'aquesta versió del SEU Només es
	 * mostra 1 vegada per versió (es guarda a UsuariSEU)
	 * 
	 * @author u88683
	 * 
	 */
	public class Splash extends Window {
		private static final long serialVersionUID = 1L;
		
		private Eventos onclick = new Eventos();

		public Splash() {
			super("SEU: Nova versió " + versio, "none", true);   //$NON-NLS-1$//$NON-NLS-2$
	
		}

		public void mostraFinestra() throws Exception {
			setWidth("750px"); //$NON-NLS-1$
			// setHeight("400px");
			setBorder("normal"); //$NON-NLS-1$
			setClosable(true);
			setMode("highlighted"); //$NON-NLS-1$
			setPosition("center"); //$NON-NLS-1$

			Include splashInclude = new Include("/aboutseu/splash.zul"); //$NON-NLS-1$
			// Afegim el include
			appendChild(splashInclude);

			Div hbox = new Div();
			hbox.setAlign("right"); //$NON-NLS-1$
			hbox.setWidth("100%"); //$NON-NLS-1$

			Button tanca = new Button(Messages.getString("ConfiguraSEU.AcceptMessage")); //$NON-NLS-1$
			tanca.addEventListener(org.zkoss.zk.ui.event.Events.ON_CLICK,
					onclick);

			hbox.appendChild(tanca);

			// I el botó per sortir
			appendChild(hbox);

			setVisible(true);
			addEventListener(Events.ON_CLICK, onclick);
			// doModal();
		}

		/**
		 * Fem que si quan es detecte click s'amague la finestra
		 * 
		 */
		class Eventos implements EventListener {
			public void onEvent(org.zkoss.zk.ui.event.Event event)
					throws Exception {
				setVisible(false);
			}
		}

	}

	public static String getVersio() {
		return versio;
	}

}
