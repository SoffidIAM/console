package es.caib.loginModule;

import java.io.IOException;
import java.security.Principal;

import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.utils.Security;

import es.caib.loginModule.client.SeyconPrincipal;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.exception.BadPasswordException;
import es.caib.seycon.ng.exception.InvalidPasswordException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.UnknownUserException;
import es.caib.seycon.ng.servei.PasswordService;

public class CheckPasswordLoginFilter implements Filter {

	/**
	 * Configuracion del filtro
	 */
	private FilterConfig filterConfig = null;

	private static Log log = LogFactory.getLog(CheckPasswordLoginFilter.class);
	
	// Pàgina de canvi de contrasenya (parametrizable)
	private String changePassPage = "/changepass.zul"; //$NON-NLS-1$
	private String changePassPublicPage = "/public/changepass.zul"; //$NON-NLS-1$

	/** servidor RMI seyconLogon */

	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("init"); //$NON-NLS-1$
		this.filterConfig = filterConfig;
		
		// Paràmetre de configuració: pàgina de canvi de contrasenya
		if (filterConfig.getInitParameter("changePassPage") != null) //$NON-NLS-1$
			changePassPage = filterConfig.getInitParameter("changePassPage"); //$NON-NLS-1$
		if (filterConfig.getInitParameter("changePassPublicPage") != null) //$NON-NLS-1$
			changePassPublicPage = filterConfig.getInitParameter("changePassPublicPage");		 //$NON-NLS-1$
	}

	/**
	 * Destruccion del filtro
	 */
	public void destroy() {
		log.info("destroy"); //$NON-NLS-1$
		this.filterConfig = null;
	}

	/**
	 * Filtrar la ejecución. Si es necesario se enviará al usuario a la pantalla
	 * de cambio de contraseña
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// SI L'USUARI HA ENTRAT AMB CERTIFICAT, NO COMPROVEM
		try {
			if (SeyconPrincipal.getCurrent()
					.getCredentialType() == SeyconPrincipal.SIGNATURE_CREDENTIAL)  {
				//log.info ("L'usuari ha entrat amb certificat");
				chain.doFilter(request, response);	
				return; //sortim
			}
		} catch (NamingException e) {
			log.warn (e);			
		}

		HttpServletRequest req = (HttpServletRequest) request;
		// Obtenim la sessio
		HttpSession session = req.getSession(true);
		
		// I l'atribut mustChangePassword per veure si ja s'ha establert
		Boolean mustChangePassword = (Boolean) session
				.getAttribute("SEYCON_MUSTCHANGEPASSWORD"); //$NON-NLS-1$
		
		if (mustChangePassword == null) {// Si és null: fem la comprovació
			//log.info("Atribut SEYCON_MUSTCHANGEPASSWORD = " + mustChangePassword);
			// Ho comprovem, i ho fiquem a la sessió
			try {
				Principal principal = req.getUserPrincipal();
				if (principal !=null && principal.getName()!=null) {
					log.info(String.format(Messages.getString("CheckPasswordLoginFilter.MustChangePassCallInfo"), principal.getName()));  //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$
					// Ho verifiquem en amb SC_CONTRA
					mustChangePassword = checkMustChangePassword(Security.getCurrentAccount());
					// i contra els rols (jboss)
					boolean mustChangeJboss = req.isUserInRole("SEYCON_CHANGE_PASSWORD"); //$NON-NLS-1$
					// si qualsevols dels dos és true: mustChangePassword
					mustChangePassword = new Boolean (mustChangePassword.booleanValue() || mustChangeJboss);
				} else {
					log.info (Messages.getString("CheckPasswordLoginFilter.PrincipalNotObtained")); //$NON-NLS-1$
					mustChangePassword = new Boolean(req.isUserInRole("SEYCON_CHANGE_PASSWORD")); //$NON-NLS-1$
				}
			} catch (Exception e) {
				// ha ocorregut un error
				log.warn(Messages.getString("CheckPasswordLoginFilter.CallMustChangePassError"),e); //$NON-NLS-1$

				// Si falla ho mirem a nivell de ROL
				mustChangePassword = new Boolean(req.isUserInRole("SEYCON_CHANGE_PASSWORD")); //$NON-NLS-1$
			}
			// Guardem el valor a la sessió
			//log.info ("Guardant SEYCON_MUSTCHANGEPASSWORD com a "+mustChangePassword);
			session.setAttribute("SEYCON_MUSTCHANGEPASSWORD", mustChangePassword); //$NON-NLS-1$
		}

		if (mustChangePassword.booleanValue()) {
			//log.info ("request URI "+req.getRequestURI());
			String changePage = changePassPage;
			if (req.getRequestURI().endsWith("/public/")) { //$NON-NLS-1$
				changePage = changePassPublicPage;
			} 
			
			//String urlInterna = req.getRequestURI().substring(req.getContextPath().length());
			//log.info("request2: "+urlInterna);
			log.info(String.format(Messages.getString("CheckPasswordLoginFilter.MustChangePassRedirectInfo"), changePage));  //$NON-NLS-1$
			// Indicamos que estamos en public
			request.getRequestDispatcher(
					changePage).forward(
					request, response);	

			return; // no procesamos el resto de los filtros (!!)
		} else {
			//log.info("filter: no filtering");
		}

		// Procesamos el resto de los filtros
		chain.doFilter(request, response);
	}

	protected java.lang.Boolean checkMustChangePassword(String user)
			throws UnknownUserException, BadPasswordException,
			InvalidPasswordException, SeyconException {
		try {
			// cridem al seycon server comprovar si l'usuari
			// ha de canviar la contrasenya
			PasswordService passwordService = ServiceLocator.instance().getPasswordService();
			String passwordDomain = passwordService.getDefaultDispatcher();
			if (passwordDomain == null)
				passwordDomain = "soffid"; // Per defecte //$NON-NLS-1$
			return passwordService.checkPasswordExpired(user, passwordDomain);
		} catch (Throwable sex) {
			sex.printStackTrace();
			SeyconException ex = new SeyconException(
					Messages.getString("CheckPasswordLoginFilter.ServerInternalError")); //$NON-NLS-1$
			ex.setStackTrace(sex.getStackTrace());
			throw ex;
		}
	}

	
}
