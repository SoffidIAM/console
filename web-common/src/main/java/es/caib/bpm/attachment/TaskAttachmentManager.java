package es.caib.bpm.attachment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBContext;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Messagebox;

import com.soffid.iam.doc.exception.DocumentBeanException;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.ejb.BpmEngine;
import es.caib.bpm.toolkit.EJBContainer;
import es.caib.bpm.vo.ProcessInstance;
import es.caib.bpm.vo.TaskInstance;
import es.caib.seycon.ng.exception.InternalErrorException;

public class TaskAttachmentManager extends AbstractAttachmentManager {
    TaskInstance task;

    private static final String PREFIX = "attachment/"; //$NON-NLS-1$

    public TaskAttachmentManager(TaskInstance task) {
        super();
        this.task = task;
    }

    public void uploadFile(Media dataSubida) throws IOException,
            NamingException, CreateException, DocumentBeanException,
            InterruptedException, DocumentBeanException, BPMException, InternalErrorException, EJBException, RemoveException {
        uploadFile (dataSubida, dataSubida.getName());
    }

    public void uploadFile(Media dataSubida, String tag) throws IOException,
            NamingException, CreateException, DocumentBeanException,
            InterruptedException, DocumentBeanException, BPMException, InternalErrorException, EJBException, RemoveException {
        InputStream in ;
        if (dataSubida.inMemory())
        {
            //String data = dataSubida.getStringData();
            if (dataSubida.isBinary())
                in = new ByteArrayInputStream (dataSubida.getByteData());
            else
                in = new ByteArrayInputStream (dataSubida.getStringData().getBytes());
        }
        else
        {
            if (dataSubida.isBinary())
            // Se recomienda usar: in = new ByteArrayInputStream(dataSubida.getByteData());
                in = dataSubida.getStreamData();
            else
            {
                in = new ReaderInputStream (dataSubida.getReaderData());
            }
        }
        uploadFile(in, dataSubida.getContentType(),
                dataSubida.getName(), tag);
        in.close ();
    }

    public void uploadFile(InputStream stream, String contentType,
            String originalName, String tag) throws IOException,
            NamingException, CreateException, DocumentBeanException,
            InterruptedException, DocumentBeanException, BPMException, InternalErrorException, EJBException, RemoveException {
        super.uploadFile(stream, contentType, originalName, tag);

        HttpSession sesion = (HttpSession) Sessions.getCurrent()
                .getNativeSession();
        BpmEngine engine = EJBContainer.getEJBContainer(sesion)
                .getEngine();
        engine.update(task);
    }

    protected Set getVariables() {
        return task.getVariables().keySet();
    }

    public void eliminarArchivo(String tag) throws Exception {
        super.eliminarArchivo(tag);

        HttpSession sesion = (HttpSession) Sessions.getCurrent()
                .getNativeSession();
        BpmEngine engine = EJBContainer.getEJBContainer(sesion)
                .getEngine();
        engine.update(task);
    }

    private static Log log = LogFactory.getLog(TaskAttachmentManager.class);

    protected void putVariable(String key, Object value) {
        task.getVariables().put(key, value);
    }

    protected void removeVariable(String key) {
        task.getVariables().remove(key);
    }

    protected Object getVariable(String key) {
        return task.getVariables().get(key);
    }
    
    public String getDownloadURL (String tag)
    {
        HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
        String url= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +  //$NON-NLS-1$ //$NON-NLS-2$
            request.getContextPath() + "/download/"+task.getId()+"/"+tag; //$NON-NLS-1$ //$NON-NLS-2$
        return url;
    }

    public String getUploadURL (String tag)
    {
        HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
        String url= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +  //$NON-NLS-1$ //$NON-NLS-2$
        request.getContextPath() + "/upload/"+this.task.getId()+"/"+tag; //$NON-NLS-1$ //$NON-NLS-2$
        return url;
    }

}
