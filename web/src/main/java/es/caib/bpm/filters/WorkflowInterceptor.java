package es.caib.bpm.filters;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.scripting.Interpreters;

import com.soffid.web.SecurityFunctionMapper;

import es.caib.zkib.datamodel.xml.FunctionMapperChain;



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
			if (org.zkoss.util.resource.Labels.getLabel("login.lblUser") == null)		                  
				Labels.register(new es.caib.bpm.ui.BPMLabelLocator());
			Interpreters.add("java", "es.caib.seycon.ng.web.component.BSHInterpreter"); //$NON-NLS-1$ //$NON-NLS-2$
			FunctionMapperChain.addFunctionMapper(new SecurityFunctionMapper ());
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
				
				((HttpServletResponse) response).addHeader("X-UA-Compatible", "IE=8");

				String uri = ((HttpServletRequest) request).getRequestURI();
//				if (uri.endsWith("/js/zul/sel.js" ) || uri.endsWith("/js/zul/grid.js" ))
//				{
//					generateScript(request, response, uri);
//					return;
//				}
				
			} catch (Exception e) {
				throw new ServletException (Messages.getString("WorkflowInterceptor.ServerConfigError"), e); //$NON-NLS-1$
			}
			
	    }
		filter.doFilter(request, response);
	}

	private void generateScript(ServletRequest request,
			ServletResponse response, String uri) throws ServletException,
			IOException {
		int i = uri.indexOf('-');
		int j = uri.indexOf('/', i);
		
		response.setContentType("text/javascript");;
		request.getRequestDispatcher(uri.substring(j)).include(request, response);
		ServletOutputStream out = response.getOutputStream();
		out.print("zk.ald(");
		out.print(uri.substring(i+1, j));
		out.println(");");
		out.close();
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