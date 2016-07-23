package com.soffid.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.caib.bpm.filters.WorkflowInterceptor;

public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object nestedPrincipal = session.getAttribute(WorkflowInterceptor.SOFFID_NESTED_PRINCIPAL);
		if (nestedPrincipal != null )
		{
			session.removeAttribute(WorkflowInterceptor.SOFFID_NESTED_PRINCIPAL);
			session.removeAttribute(WorkflowInterceptor.SOFFID_NESTED_TENANT);
			session.removeAttribute(WorkflowInterceptor.SOFFID_NESTED_PERMISSIONS);
			resp.sendRedirect("/index.zul");
		}
		else
		{
			session.invalidate();
			resp.sendRedirect("/logout/");
		}
	}

}
