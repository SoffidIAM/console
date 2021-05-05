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

import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.utils.Security;

@WebFilter(filterName = "securityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Security.nestedLogin( (SoffidPrincipal) ((HttpServletRequest) request).getUserPrincipal());
		try {
			chain.doFilter(request, response);
		} finally {
			Security.nestedLogoff();
		}
	}

	public void destroy() {
	}

}
