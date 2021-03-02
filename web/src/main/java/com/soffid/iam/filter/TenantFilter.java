package com.soffid.iam.filter;

import java.io.IOException;
import java.net.URL;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.juli.AsyncFileHandler;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class TenantFilter implements Filter {

	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	Log log = LogFactory.getLog(getClass());
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest httpReq = (HttpServletRequest) request;
			HttpServletResponse httpResp = (HttpServletResponse) response;
			
			String tenantHost;
			try {
				tenantHost = new TenantExtractor().getTenant(httpReq);
			} catch (InternalErrorException e) {
				throw new ServletException(e);
			}
	
			Security.setClientRequest(httpReq);

			HttpSession s = httpReq.getSession(false);
			Principal principal = httpReq.getUserPrincipal();
			if (s == null) {
				chain.doFilter(request, response);
			}
			else if (principal != null && ! principal.getName().startsWith(tenantHost+"\\"))
			{
				log.info("Received request on server "+httpReq.getServerName()+"(tenant "+tenantHost+") for user "+principal.getName());
				s.invalidate();
				httpResp.sendRedirect(httpReq.getContextPath());
			} else {
				String previousTenant = (String) s.getAttribute("tenant");
				if (previousTenant == null)
				{
					s.setAttribute("tenant", tenantHost);
					chain.doFilter(request, response);
				}
				else if (! previousTenant.equals(tenantHost))
				{
					log.info("Received request on server "+httpReq.getServerName()+"(tenant "+tenantHost+") with session for tenant "+previousTenant);
					s.invalidate();
					httpResp.sendRedirect(httpReq.getContextPath());
				}
				else
				{
	    			chain.doFilter(request, response);
				}
			}
		} finally {
			Security.setClientIp(null);
		}
	}

	@Override
	public void destroy() {
	}

}
