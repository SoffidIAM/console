package com.soffid.iam.ui;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.ExecucioPuntEntrada;
import es.caib.seycon.ng.comu.PuntEntrada;

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

		if (id == null)
		{
			if (!"".equals(mime) && !"".equals(contingut)) { //$NON-NLS-1$ //$NON-NLS-2$
	
				if ("text/html".equals(mime)) { //$NON-NLS-1$
					response.sendRedirect(contingut);
				} else {
					response.reset();
					response.resetBuffer();
					response.setContentType(URLDecoder.decode(mime,"UTF-8")); //$NON-NLS-1$
					response.getWriter().print(URLDecoder.decode(contingut,"UTF-8")); //$NON-NLS-1$
				}
			}
		} else {
			try {
				PuntEntrada pe = EJBLocator.getPuntEntradaService().findPuntEntradaById(Long.decode(id).longValue());
				if (pe != null)
				{
					for (Iterator<ExecucioPuntEntrada> it = pe.getExecucions().iterator(); it.hasNext();)
					{
						ExecucioPuntEntrada exe = it.next();
						if ("text/html".equals(exe.getTipusMimeExecucio())) { //$NON-NLS-1$
							response.sendRedirect(exe.getContingut());
						} else {
							response.reset();
							response.resetBuffer();
							String fileName = pe.getNom()+"."+exe.getCodiTipusExecucio().toLowerCase();
							response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName+"\"");
							response.setContentType(URLDecoder.decode(exe.getTipusMimeExecucio(),"UTF-8")); //$NON-NLS-1$
							if ( exe.getCodiTipusExecucio().equals("MZN"))
								response.getWriter().print(URLDecoder.decode(id,"UTF-8")); //$NON-NLS-1$
							else
								response.getWriter().print(exe.getContingut()); //$NON-NLS-1$
						}
						return;
					}
				}
			} catch (Throwable e) {
				throw new ServletException(e);
			}
		}
	}
}