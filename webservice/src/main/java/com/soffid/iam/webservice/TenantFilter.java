package com.soffid.iam.webservice;

import java.io.IOException;
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

import com.soffid.iam.utils.ConfigurationCache;
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
	
			Security.clearNestedLogins();
			Security.setClientRequest(httpReq);

   			nextStep(request, response, chain);
   			
		} finally {
			Security.setClientIp(null);
		}
	}

	protected void nextStep(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
