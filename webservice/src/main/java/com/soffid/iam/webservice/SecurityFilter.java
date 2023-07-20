package com.soffid.iam.webservice;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

@WebFilter(filterName = "securityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Security.nestedLogin( (SoffidPrincipal) ((HttpServletRequest) request).getUserPrincipal());
		try {
			String origin = ConfigurationCache.getProperty("soffid.scim.cors.origin");
			if (origin != null) {
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.addHeader(
	                "Access-Control-Allow-Origin", origin);
				resp.addHeader(
	                "Access-Control-Allow-Credentials", "true");
				resp.addHeader(
	               "Access-Control-Allow-Headers",
	               "origin, content-type, accept, authorization");
	    		String methods = ConfigurationCache.getProperty("soffid.scim.cors.methods");
	    		resp.addHeader(
	                "Access-Control-Allow-Methods", 
	        		methods == null ? "GET, OPTIONS, HEAD": methods);
			}
			chain.doFilter(request, response);
		} finally {
			Security.nestedLogoff();
		}
	}

	public void destroy() {
	}

}
