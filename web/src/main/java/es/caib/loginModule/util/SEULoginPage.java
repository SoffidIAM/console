package es.caib.loginModule.util;

import java.security.Principal;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;

import javax.management.remote.JMXPrincipal;
import javax.servlet.http.HttpServletRequest;

import com.soffid.iam.PrincipalStore;

import es.caib.loginModule.auth.ConstantesAutenticacion;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.exception.InternalErrorException;

public class SEULoginPage implements LoginPage {

	private HttpServletRequest request;
	private static ServiceLocator locator = null;
	private Principal principal = new JMXPrincipal("LoginPage"); //$NON-NLS-1$
	private static boolean cachedValorCertificateLogin = false;
	private static Calendar darreraActualitzacioValor = null;
	private final long tempsValidesaValor = 5 * 60 * 1000; // en milisegundos
	
	static {
		locator = ServiceLocator.instance();
	}

	public SEULoginPage() {
		super();
		
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	private boolean esCertificateLoginActivat() {

		// Mirem si tenim valor al cache de activació i és actualitzat 
		if ( darreraActualitzacioValor !=null && darreraActualitzacioValor.getTimeInMillis() > (Calendar.getInstance().getTimeInMillis() - tempsValidesaValor))
			return cachedValorCertificateLogin;
		PrincipalStore.set(principal);
		es.caib.seycon.ng.servei.ConfiguracioService service = locator.getConfiguracioService();
		Configuracio config = null;
		
		try {
			config = service.findParametreByCodiAndCodiXarxa(
				"CertificateLogin", null); //$NON-NLS-1$
		} catch (InternalErrorException e) {
			e.printStackTrace();
		}
		
		// Guardem el valor en cache i la data d'actualització
		if (config != null && "true".equals(config.getValor())) //$NON-NLS-1$
			cachedValorCertificateLogin = true;
		else
			cachedValorCertificateLogin = false;
		darreraActualitzacioValor = Calendar.getInstance();
		return cachedValorCertificateLogin;
	}

	public int getLoginMethod() {
		String page = request.getRequestURL().toString();
		//System.out.println("Page = " + page);
		
		boolean esPublic = (page.indexOf("/public/")!=-1); //$NON-NLS-1$
		//boolean esZKAU = (page.indexOf("/zkau")!=-1);
		//boolean esResource = ((page.indexOf("/images/")!=-1) || page.endsWith(".css"));
		// Mètode basic
		if (esPublic /*|| esZKAU || esResource*/){
			return LoginPage.BASIC;
		}
		
		boolean esZul = (page.indexOf(".zul")!=-1); //$NON-NLS-1$
		boolean esArrel = page.endsWith("seycon-web/"); //$NON-NLS-1$

		if (/*!esPublic &&*/ (esZul || esArrel) && esCertificateLoginActivat()) //Només els zuls no publics
			return LoginPage.CLIENT_CERT;

		return LoginPage.BASIC;
	}

	public Locale getLanguage() {
		Enumeration locales = request.getLocales();
		while (locales.hasMoreElements())
		{
			Locale lang = (Locale) locales.nextElement();
			if (lang.getLanguage().equals("ca")) //$NON-NLS-1$
				return lang;
			if (lang.getLanguage().equals("es")) //$NON-NLS-1$
				return lang;
		}
		return new Locale (ConstantesAutenticacion.DEFAULT_LANG);
	}

	public void setLanguage(Locale language) {
		// TODO Auto-generated method stub

	}

	public String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStyleSheet() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCertMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserPasswordMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAnonymousMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServerNameUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
