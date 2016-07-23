package es.caib.loginModule;

import java.io.IOException;
import java.net.URLEncoder;
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

	/** servidor RMI seyconLogon */

	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("init"); //$NON-NLS-1$
		this.filterConfig = filterConfig;
		
		// Paràmetre de configuració: pàgina de canvi de contrasenya
		if (filterConfig.getInitParameter("changePassPage") != null) //$NON-NLS-1$
			changePassPage = filterConfig.getInitParameter("changePassPage"); //$NON-NLS-1$
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

		HttpServletRequest req = (HttpServletRequest) request;
		// Obtenim la sessio
		HttpSession session = req.getSession(true);
		
		boolean mustChangePassword = req.isUserInRole("PASSWORD:EXPIRED"); //$NON-NLS-1$
		Boolean passwordAlreadyChanged = (Boolean) session.getAttribute("$$SoffidPasswordChanged$$");
		
		if (mustChangePassword && passwordAlreadyChanged == null) {
			if (session.getAttribute("$$SoffidPassswordBack$$") == null)
			{
				String url = req.getRequestURI();
				if (req.getQueryString() != null && req.getQueryString().length() > 0)
					url = url + "?" + URLEncoder.encode(req.getQueryString(), "ISO-8859-1");
				session.setAttribute("$$SoffidPassswordBack$$", url);
			}
			request.getRequestDispatcher(
					changePassPage).forward(
					request, response);	
		} else {
			chain.doFilter(request, response);
		}
	}

}
