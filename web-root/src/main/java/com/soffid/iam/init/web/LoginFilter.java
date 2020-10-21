package com.soffid.iam.init.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.soffid.iam.init.Configuration;

import java.io.*;
import java.util.Locale;

@WebFilter(urlPatterns="/*")
public class LoginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if ( ! req.getRequestURI().startsWith("/logout") && 
				! req.getRequestURI().startsWith("/soffid")) {
			Configuration cfg = Configuration.getConfiguration();
			if (cfg.isConfigured())
				resp.sendRedirect("/soffid");
			else
				chain.doFilter(request, response);
		}
		else
			chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
