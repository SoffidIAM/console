package com.soffid.iam.ui;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AccessTreeExecution;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.vault.LaunchHelper;

public class Executions extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = (String) request.getParameter("id"); //$NON-NLS-1$
		String mime = (String) request.getParameter("mime"); //$NON-NLS-1$
		String contingut = (String) request.getParameter("contingut"); //$NON-NLS-1$

		if (id != null) {
			try {
				AccessTree pe = EJBLocator.getEntryPointService().findApplicationAccessById(Long.decode(id).longValue());
				if (pe != null)
				{
					String scope = request.getParameter("scope");
					if (scope == null)
						scope = com.soffid.iam.ServiceLocator.instance()
							.getEntryPointService()
							.getScopeForAddress(Security.getClientIp());
					AccessTreeExecution selected = null;
					if ("L".equals(scope))
						selected = findExecution (pe.getExecutions(), "L");
					if ("W".equals(scope) || "L".equals(scope) && selected == null)
						selected = findExecution (pe.getExecutions(), "W");
					if (selected == null)
						selected = findExecution (pe.getExecutions(), "I");

					if (selected != null) {
						if ("text/html".equals(selected.getTypeMimeExecution())) { //$NON-NLS-1$
							response.sendRedirect(selected.getContent());
						} else {
							response.reset();
							response.resetBuffer();
							String fileName = pe.getName()+"."+selected.getExecutionTypeCode().toLowerCase();
							response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName+"\"");
							response.setContentType(URLDecoder.decode(selected.getTypeMimeExecution(),"UTF-8")); //$NON-NLS-1$
							if ( selected.getExecutionTypeCode().equals("MZN"))
								response.getWriter().print(URLDecoder.decode(id,"UTF-8")); //$NON-NLS-1$
							else
								response.getWriter().print(selected.getContent()); //$NON-NLS-1$
						}
						
					}
				}
			} catch (Throwable e) {
				throw new ServletException(e);
			}
		}
	}

	private AccessTreeExecution findExecution(Collection<AccessTreeExecution> executions, String scope) {
		for (AccessTreeExecution exe: executions)
			if (scope.equals(exe.getScope()))
				return exe;
		return null;
	}

}