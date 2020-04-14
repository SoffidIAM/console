package com.soffid.iam.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.ApplicationComponent;

public class FrameFilter implements Filter {

	private FilterConfig config;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ApplicationComponent app = null;
		HttpServletRequest req = (HttpServletRequest) request;
		String target = req.getServletPath();
		if (req.getPathInfo() != null)
			target = target + req.getPathInfo();
		if (req.getQueryString() != null)
			target = target + "?"+req.getQueryString();
		try {
				app = Application.getApplication();
		} catch (Exception e) {}
		if (app == null && target != null && 
				!target.isEmpty() &&
				!target.equals("/index.zul"))
		{
			HttpServletRequestWrapper req2 = new IndexRequestWrapper(req, "/index.zul", target);
			req.setAttribute("$soffid$target", target);
			request.getRequestDispatcher("/index.zul").forward(req2, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}

class IndexRequestWrapper extends HttpServletRequestWrapper {

	private String path;

	public IndexRequestWrapper(HttpServletRequest request, String path, String target) {
		super(request);
		this.path = path;
	}

	@Override
	public String getServletPath() {
		return path;
	}

}