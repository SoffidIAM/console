package es.caib.bpm.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Executions;

import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.service.ejb.DocumentService;

import es.caib.bpm.attachment.TaskAttachmentManager;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.EJBContainer;
import es.caib.bpm.ui.task.TaskUI;
import es.caib.bpm.vo.TaskInstance;
import es.caib.seycon.ng.exception.InternalErrorException;

public class UploadServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getPathInfo();
        while (path.startsWith("/")) //$NON-NLS-1$
            path=path.substring(1);
        String split[] = path.split("/+"); //$NON-NLS-1$
        try {
			String taskUUID=split[0];
			//obtenim el task instance
			TaskUI taskUI = (TaskUI) Executions.getCurrent().getDesktop().getComponentByUuid(taskUUID);
			TaskInstance task = taskUI.getCurrentTask();
			//obtenim l'engine
			BpmEngine engine = EJBContainer.getEJBContainer(req.getSession()).getEngine();
        	
            if (task == null)
            {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                return;
            }
            TaskAttachmentManager am = new TaskAttachmentManager(task);
			String tag=split[1];
            String type = req.getParameter("type"); //$NON-NLS-1$
            String name = req.getParameter("name"); //$NON-NLS-1$
            DocumentService d = am.createDocument(type == null ? "octet/binary-stream": type, //$NON-NLS-1$
            							   name == null ? "unknown": name); //$NON-NLS-1$
            d.openUploadTransfer();
            BufferedInputStream in = new BufferedInputStream (req.getInputStream());
            byte [] b = new byte [8192];
            int read = in.read(b);
   
            while (read > 0)
            {
            	d.nextUploadPackage(b, read);
                read = in.read(b);
            }
            d.endUploadTransfer();
            in.close();
            am.attach(tag, d);
            
            //si no fem update, el attach no es guarda
            engine.update(task);
            
            d.remove();
        } catch (CreateException e) {
            throw new ServletException(Messages.getString("UploadServlet.SessionExpired"), e); //$NON-NLS-1$
        } catch (NamingException e) {
            throw new ServletException(Messages.getString("UploadServlet.NotLocateApplicationServer"), e); //$NON-NLS-1$
        } catch (BPMException e) {
            throw new ServletException(String.format(Messages.getString("UploadServlet.NotLocateTask"), split[0]), e);  //$NON-NLS-1$
        } catch (DocumentBeanException e) {
            throw new ServletException(String.format(Messages.getString("UploadServlet.NotUploadDoc"), split[0], split[1]), e);  //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$
        } catch (InterruptedException e) {
            throw new ServletException(String.format(Messages.getString("UploadServlet.NotCreateDoc"), split[0], split[1]), e);  //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$
		} catch (InternalErrorException e) {
            throw new ServletException(Messages.getString("UploadServlet.InternalError"), e); //$NON-NLS-1$
		} catch (EJBException e) {
            throw new ServletException(Messages.getString("UploadServlet.InternalError"), e); //$NON-NLS-1$
		} catch (RemoveException e) {
            throw new ServletException(Messages.getString("UploadServlet.InternalError"), e); //$NON-NLS-1$
		}
    }
    
	/**
	 * Upload de pdfs signats desde zkiblaf/applet/SignApplet
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			String path = req.getPathInfo();
			while (path.startsWith("/")) //$NON-NLS-1$
				path = path.substring(1);
			String split[] = path.split("/+"); //$NON-NLS-1$
			
			String taskId=split[0];
			String mime=req.getContentType();
			String tag=split[1];
			InputStream in=req.getInputStream();

			//FIXME comentar això perquè és un trenca closques
			//obtenim el task instance
			//TaskUI taskUI = (TaskUI) Executions.getCurrent().getDesktop().getComponentByUuid(taskUUID);
			//TaskInstance task = taskUI.getCurrentTask();
			TaskInstance task = (TaskInstance)req.getSession().getAttribute("ZKActualTask-"+taskId); //$NON-NLS-1$
			
			
			EJBContainer.getEJBContainer(req.getSession()).getEngine();
			
			
			if (task == null) {
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
				return;
			}
			TaskAttachmentManager am = new TaskAttachmentManager(task);
	
			//inici procés d'upload al document manager
				DocumentService doc=am.createDocument(mime,tag);
				doc.openUploadTransfer();
	         
				byte [] b = new byte [10240];
				int read = in.read(b);
	         
				while (read > 0)
				{
	         	
					doc.nextUploadPackage(b, read);
					read = in.read(b);
	             
				}
				doc.endUploadTransfer();
				in.close();
	         
				am.attach(tag, doc);
				
				//si no fem update, el attach no es guarda
				//engine.update(task);
	        	doc.remove();
	        //fi de procés d'upload al document manager
		}catch(Exception e){
			throw new ServletException(e);
		}
	}    

}
