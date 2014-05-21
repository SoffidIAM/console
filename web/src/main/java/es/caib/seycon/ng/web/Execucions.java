package es.caib.seycon.ng.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;

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
			AplicacioService appService = ServiceLocator.instance().getAplicacioService();
			PuntEntradaService puntEntradaSvc = ServiceLocator.instance().getPuntEntradaService();
			PuntEntrada pe;
			try {
				pe = puntEntradaSvc.findPuntEntradaById(Long.decode(id).longValue());
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
			} catch (NumberFormatException e) {
				throw new ServletException(e);
			} catch (InternalErrorException e) {
				throw new ServletException(e);
			}
		}
	}
}