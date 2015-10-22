package es.caib.seycon.ng.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;

import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.ExecucioPuntEntrada;
import es.caib.seycon.ng.comu.PuntEntrada;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.AplicacioService;
import es.caib.seycon.ng.servei.PuntEntradaService;
import es.caib.seycon.ng.servei.ejb.PuntEntradaServiceHome;

public class Execucions extends HttpServlet {

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
				es.caib.seycon.ng.servei.ejb.PuntEntradaService puntEntradaService = 
						((PuntEntradaServiceHome) new InitialContext()
							.lookup(PuntEntradaServiceHome.JNDI_NAME))
								.create();
				PuntEntrada pe;
				pe = puntEntradaService.findPuntEntradaById(Long.decode(id).longValue());
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
							response.setContentType(URLDecoder.decode(exe.getTipusMimeExecucio(),"UTF-8")); //$NON-NLS-1$
							response.getWriter().print(URLDecoder.decode(id,"UTF-8")); //$NON-NLS-1$
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