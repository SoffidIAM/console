package es.caib.bpm.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.bpm.attachment.AbstractAttachmentManager;
import es.caib.bpm.attachment.ProcessAttachmentManager;
import es.caib.bpm.attachment.TaskAttachmentManager;
import es.caib.bpm.beans.exception.DocumentBeanException;
import es.caib.bpm.beans.remote.Document;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.EJBContainer;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskInstance;
import es.caib.seycon.ng.exception.InternalErrorException;

public class DownloadServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final String DATE_ONE =
        (new SimpleDateFormat( "EEE, dd MMM yyyy HH:mm:ss zzz", //$NON-NLS-1$
                              Locale.US)).format(new Date(1));
	/**
	 * Download de documents
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//PJR 20100201-1 inici de las modificaciones para evitar el bug de descarga de documentos por SSL sobre explorer 6
		//http://community.jboss.org/wiki/DisableCacheControl
		
		resp.reset();
		resp.addHeader("Expires", DATE_ONE); //$NON-NLS-1$
		resp.setDateHeader("Last-Modified",new Date().getTime()); //$NON-NLS-1$
		
		//PFR 20100201-1 fin
		
		String path = req.getPathInfo();
		while (path.startsWith("/")) //$NON-NLS-1$
			path = path.substring(1);
		String split[] = path.split("/+"); //$NON-NLS-1$
		try {
			BpmEngine engine = EJBContainer.getEJBContainer(req.getSession()).getEngine();
			Long id = new Long(split[0]);
			AbstractAttachmentManager am = null;
			TaskInstance task = engine.getTask(id.longValue());
			if (task == null)
			{
				ProcessInstance process = engine.getProcess(id.longValue());
				if (process == null) {
					resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
					return;
				} else {
					am = new ProcessAttachmentManager(process);
				}
			} else {
				am = new TaskAttachmentManager(task);
			}
			Document d = am.getDocument(split[1]);
			if (d == null) {
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
				return;
			}
			resp.setContentType(d.getMimeType());
			resp.addHeader("content-disposition", "inline; filename=" + d.getExternalName()); //$NON-NLS-1$ //$NON-NLS-2$
			d.openDownloadTransfer();
			byte[] b;
			b = d.nextDownloadPackage(8192);

			ServletOutputStream out = resp.getOutputStream();
			while (b != null) {
				out.write(b);
				b = d.nextDownloadPackage(8192);
			}
			d.endDownloadTransfer();
		} catch (CreateException e) {
			throw new ServletException(Messages.getString("DownloadServlet.SessionExpired"), e); //$NON-NLS-1$
		} catch (NamingException e) {
			throw new ServletException(Messages.getString("DownloadServlet.NotLocateApplicationServer"), e); //$NON-NLS-1$
		} catch (BPMException e) {
			throw new ServletException(String.format(Messages.getString("DownloadServlet.NotLocateTask"), split[0]), e);  //$NON-NLS-1$
		} catch (DocumentBeanException e) {
			throw new ServletException(String.format(Messages.getString("DownloadServlet.NotDownloadDocument"), split[0], split[1]), e);   //$NON-NLS-1$
		} catch (InternalErrorException e) {
			throw new ServletException(Messages.getString("DownloadServlet.InternalError"), e); //$NON-NLS-1$
		}
	}



}
