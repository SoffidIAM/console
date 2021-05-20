package com.soffid.iam.init.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.soffid.iam.init.Configuration;

import java.io.*;
import java.net.URLEncoder;
import java.util.Locale;

@WebFilter(urlPatterns="/*")
public class LoginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if ( ! req.getRequestURI().equals("/favicon.ico") &&
				! req.getRequestURI().startsWith("/logout") && 
				! req.getRequestURI().startsWith("/soffid")) {
			Configuration cfg = Configuration.getConfiguration();
			if (cfg.isConfigured())  {
				if ( req.getRequestURI().startsWith("/selfservice/task.zul")) {
					resp.sendRedirect("/soffid/wf/task.zul?"+req.getQueryString());
				}
				else if ( req.getRequestURI().startsWith("/webservice")) {
					String target = "/soffid"+req.getRequestURI();
					if (req.getQueryString() != null)
						target = target + "?"+req.getQueryString();
					resp.setHeader("Location",  target);
					resp.setStatus(308); // Permanent redirect
				}
				else if ( req.getRequestURI().startsWith("/selfservice/index.zul") &&
						req.getParameter("target") != null &&
						req.getParameter("target").startsWith("sharedAccounts/sharedAccounts.zul")) {
					String qs = req.getQueryString();
					if (qs.contains("?")) qs = qs.substring(qs.indexOf("?")+1);
					resp.sendRedirect("/soffid/resource/account/vault.zul?"+qs);
				}
				else
					resp.sendRedirect("/soffid");
			} else
				chain.doFilter(request, response);
		}
		else
			chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
