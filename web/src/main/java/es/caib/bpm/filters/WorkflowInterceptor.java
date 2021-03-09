package es.caib.bpm.filters;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.naming.NamingException;
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

import org.zkoss.util.TimeZones;
import org.zkoss.util.resource.Labels;
import org.zkoss.web.Attributes;
import org.zkoss.zk.scripting.Interpreters;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.service.ejb.SessionCacheService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.SecurityFunctionMapper;
import com.soffid.iam.web.interp.RefInterpreter;
import com.soffid.iam.web.zk.ConfigureUserSettings;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.datamodel.xml.FunctionMapperChain;

/**
 * Representa la validaci�n de la p�gina solicitada em base a la identificaci�n
 * y tipo del usuario actual
 * 
 * @author Diego S. Pongelli
 * @since 04/01/2007
 */
public class WorkflowInterceptor implements Filter {
	private static final String SOFFID_SESSION_CACHE_ATTR = "$$SoffidSessionCache$$";
	public static final String SOFFID_NESTED_PRINCIPAL = "$$SoffidNestedPrincipal$$";
	public static final String SOFFID_NESTED_TENANT = "$$SoffidNestedTenant$$";
	public static final String SOFFID_NESTED_PERMISSIONS = "$$SoffidNestedPermissions$$";
	public static final String SOFFID_NESTED_FULLNAME = "$$SoffidNestedFullname$$";
	/*
	 * DECLARACIONES
	 */
	protected FilterConfig config = null;
	private SessionCacheService sessionCacheService;

	/*
	 * METODOS
	 */
	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// limpiamos el filtro
		this.config = null;
	}

	private static boolean configured = false;

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filter) throws IOException, ServletException {
		if (!configured) {
			if (org.zkoss.util.resource.Labels.getLabel("login.lblUser") == null)
			{
				Labels.register(new com.soffid.iam.web.zk.V2LabelLocator());
				Labels.register(new com.soffid.iam.web.zk.BPMLabelLocator());
			}
//			Interpreters.add(
//					"java", "es.caib.seycon.ng.web.component.BSHInterpreter"); //$NON-NLS-1$ //$NON-NLS-2$
			FunctionMapperChain.addFunctionMapper(new SecurityFunctionMapper());
//			Interpreters.add("java", RefInterpreter.class.getCanonicalName());
			configured = true;
		}
		if (request instanceof HttpServletRequest) 
		{
			String requestURI = ((HttpServletRequest) request).getRequestURI();
			if ( ((HttpServletRequest) request).getSession(false) == null && 
					(requestURI.startsWith("/anonymous/") ||
						requestURI.startsWith("/selfservice/ananymous/"))) {
				filter.doFilter(request, response);
			} else {
				HttpSession sesion = ((HttpServletRequest) request).getSession();
				if (sesion.getAttribute("soffid-remoteIp") == null) {
					sesion.setAttribute("soffid-remoteIp", request.getRemoteAddr());
					sesion.setAttribute("soffid-remoteProxy", ((HttpServletRequest)request).getHeader("X-Forwarded-For"));
				}
				try {
					Principal principal = ((HttpServletRequest) request)
							.getUserPrincipal();
					if (principal != null) {
						String sessionId = (String) sesion
								.getAttribute(SOFFID_SESSION_CACHE_ATTR);
						if (sessionId == null) {
							sessionId = sessionCacheService.createSession();
							sesion.setAttribute(SOFFID_SESSION_CACHE_ATTR,
									sessionId);
						} else {
							sessionCacheService.setSession(sessionId);
						}
					}

					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse.addHeader("X-UA-Compatible", "IE=Edge");
					httpServletResponse.addHeader("X-Frame-Options", "SAMEORIGIN");
					
					
					SoffidPrincipal nestedPrincipal = (SoffidPrincipal) sesion
							.getAttribute(SOFFID_NESTED_PRINCIPAL);
					
					if (sesion.getAttribute(ConfigureUserSettings.SESSIO_IDIOMA) == null)
						ConfigureUserSettings.configuraUsuariSEU((HttpServletRequest) request);

					String forcedLocale = ConfigurationCache.getProperty("soffid.language");
					if (forcedLocale != null)
					{
						Locale locale = new Locale(forcedLocale);
						sesion.setAttribute(Attributes.PREFERRED_LOCALE, locale);
						org.zkoss.util.Locales.setThreadLocal(locale);
						MessageFactory.setThreadLocale(locale);
					} else {
						String lang = (String) sesion.getAttribute(ConfigureUserSettings.SESSIO_IDIOMA);			
						if (lang != null)
						{
							Locale locale = new Locale (lang);
							org.zkoss.util.Locales.setThreadLocal( locale );
							MessageFactory.setThreadLocale(locale);
						}
					}
					TimeZone timezone = (TimeZone) sesion.getAttribute(ConfigureUserSettings.SESSIO_TIMEZONE);
					if (timezone != null)
						TimeZones.setThreadLocal(timezone);
					String dateFormat = (String) sesion.getAttribute(ConfigureUserSettings.SESSIO_DATEFORMAT);
					String timeFormat = (String) sesion.getAttribute(ConfigureUserSettings.SESSIO_TIMEFORMAT);
					String sourceIP = (String) sesion.getAttribute(ConfigureUserSettings.SESSIO_IP);
					if (dateFormat != null) {
						DateFormats.setThreadLocal(new String[] {
								dateFormat,
								timeFormat == null ? "HH:mm:ss": timeFormat
						});
					}
					if (nestedPrincipal != null && principal.getName().startsWith("master\\")) {
						
						Security.nestedLogin(nestedPrincipal);
						try {
							filter.doFilter(request, response);
						} finally {
							Security.nestedLogoff();
						}
					} 
					else if (principal == null)
					{
						String tenant = new com.soffid.iam.filter.TenantExtractor().getTenant((HttpServletRequest) request);
						Security.nestedLogin(tenant, "anonymous", new String[0]);
						try {
							filter.doFilter(request, response);
						} finally {
							Security.nestedLogoff();
						}
					}
					else
					{
						String tenant = new com.soffid.iam.filter.TenantExtractor().getTenant((HttpServletRequest) request);
						Security.nestedLogin( (SoffidPrincipal) ((HttpServletRequest) request).getUserPrincipal());
						try {
							filter.doFilter(request, response);
						} finally {
							Security.nestedLogoff();
						}
					}

				} catch (Exception e) {
					throw new ServletException(
							Messages.getString("WorkflowInterceptor.ServerConfigError"), e); //$NON-NLS-1$ 
				} finally {
					Security.clearNestedLogins();
					try {
						MessageFactory.setThreadLocale(null);
						org.zkoss.util.Locales.setThreadLocal(null);
						TimeZones.setThreadLocal(null);
						DateFormats.setThreadLocal(null);
						sessionCacheService.clearSession();
					} catch (InternalErrorException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			filter.doFilter(request, response);
		}
	}

	private void generateScript(ServletRequest request,
			ServletResponse response, String uri) throws ServletException,
			IOException {
		int i = uri.indexOf('-');
		int j = uri.indexOf('/', i);

		response.setContentType("text/javascript");
		;
		request.getRequestDispatcher(uri.substring(j)).include(request,
				response);
		ServletOutputStream out = response.getOutputStream();
		out.print("zk.ald(");
		out.print(uri.substring(i + 1, j));
		out.println(");");
		out.close();
	}

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// inicializamos el filtro con la configuraci�n definida en web.xml
		this.config = arg0;
		try {
			sessionCacheService = EJBLocator.getSessionCacheService();
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (CreateException e) {
			throw new ServletException(e);
		}
	}

	// private static Logger log= Logger.getLogger(WorkflowInterceptor.class);
}
