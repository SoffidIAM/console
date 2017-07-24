package es.caib.seycon.ng.web.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.servei.ejb.SeyconServerService;
import es.caib.seycon.ng.servei.ejb.SeyconServerServiceHome;

public class ServerLogServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServerLogServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			// Comprovem autoritzacions
			if (!Security.isUserInRole(Security.AUTO_BASE_LOG_QUERY)) {
				resp.sendRedirect(req.getContextPath()+"/getlogmsg.zul?why=not_allowed"); //$NON-NLS-1$
				return;
			}
			
			String urlServer = req.getParameter("urlserver"); //$NON-NLS-1$
			javax.naming.Context context = new javax.naming.InitialContext();		
			es.caib.seycon.ng.servei.ejb.SeyconServerService seyconServerService = 
					(SeyconServerService) 
					context.lookup(es.caib.seycon.ng.servei.ejb.SeyconServerServiceHome.JNDI_NAME);
		
			InputStream in = seyconServerService.getSeyconServerLog(urlServer);
			
			if (in==null) {
				resp.sendRedirect(req.getContextPath()+"/getlogmsg.zul?why=cannot_connect"); //$NON-NLS-1$
				return; //Pot ser que no podem establir comunicació amb el server
			}
			
			ServletOutputStream op = resp.getOutputStream();
			//ServletContext contexte = getServletConfig().getServletContext();
		    resp.setContentType("text/plain; charset=UTF-8"); //$NON-NLS-1$
		    resp.addHeader("content-disposition", "attachment; filename=seycon.log"); //$NON-NLS-1$ //$NON-NLS-2$

			byte[] bbuf = new byte[1024];
			int leido;
			while ((leido = in.read(bbuf)) > 0) {
			    op.write (bbuf, 0, leido);
			}
			in.close();
			op.close();
		
		} catch (Throwable th) {
			//th.printStackTrace();
		}
		

	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

}
