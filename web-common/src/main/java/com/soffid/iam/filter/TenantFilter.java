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

public class TenantFilter implements Filter {

	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		String hostName = request.getServerName();
		String tenantHost;
		if (hostName.toLowerCase().endsWith("."+hostName.toLowerCase()))
		{
			String[] hostParts = hostName.split("\\.");
			tenantHost = hostParts[0];
			if (tenantHost.isEmpty())
				tenantHost = "master";
		} else
			tenantHost = "master";
		
		HttpSession s = httpReq.getSession(true);
		
		Principal principal = httpReq.getUserPrincipal();
		
		String previousTenant = (String) s.getAttribute("tenant");
		if (previousTenant == null)
		{
			s.setAttribute("tenant", tenantHost);
			chain.doFilter(request, response);
		}
		else if (! previousTenant.equals(tenantHost))
		{
			s.invalidate();
			String path = filterConfig.getServletContext().getRealPath("/");
			URL url = new URL(path);
			httpResp.sendRedirect(url.getPath());
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}
