package es.caib.bpm.filters;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.scripting.Interpreters;



/**
 * Representa la validaci�n de la p�gina solicitada em base a la identificaci�n y tipo del usuario actual
 * @author Diego S. Pongelli
 * @since 04/01/2007
 */
public class WorkflowInterceptor implements Filter 
{
/*
 * DECLARACIONES
 */
	/**La configuraci�n del filtro*/
	protected FilterConfig config= null;
	
/*
 * METODOS
 */
	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() 
	{
		//limpiamos el filtro
		this.config= null;
	}

	private static boolean configured = false;
	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException 
	{
		if (!configured) {
			Labels.register(new es.caib.bpm.ui.BPMLabelLocator());
			Interpreters.add("java", "es.caib.seycon.ng.web.component.BSHInterpreter"); //$NON-NLS-1$ //$NON-NLS-2$
			configured = true;
		}
		if (request instanceof HttpServletRequest) 
	    {
			HttpSession sesion= ((HttpServletRequest)request).getSession();
			try {
				Principal principal = ((HttpServletRequest)request).getUserPrincipal();
				if (principal!=null) { // Pot ésser nul?? (cas de que no hagi iniciat sessió)
					sesion.setAttribute ("principal", principal); //$NON-NLS-1$
					sesion.setAttribute("user", principal.getName()); //$NON-NLS-1$
				} else {
					sesion.removeAttribute("principal"); //$NON-NLS-1$
					sesion.removeAttribute("user"); //$NON-NLS-1$
				}
			} catch (Exception e) {
				throw new ServletException (Messages.getString("WorkflowInterceptor.ServerConfigError"), e); //$NON-NLS-1$
			}
			
	    }
		filter.doFilter(request, response);
	}
	
	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		//inicializamos el filtro con la configuraci�n definida en web.xml
		this.config= arg0;
	}
	
//	private static Logger log= Logger.getLogger(WorkflowInterceptor.class);
}