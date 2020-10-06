package com.soffid.iam.web.bpm.attachment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.bpm.attachment.AbstractAttachmentManager;
import com.soffid.iam.bpm.service.ejb.BpmEngine;
import com.soffid.iam.doc.exception.DocumentBeanException;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.toolkit.EJBContainer;
import es.caib.seycon.ng.exception.InternalErrorException;

public class ProcessAttachmentManager extends AbstractAttachmentManager {
    protected ProcessInstance process;

    private static final String PREFIX = "attachment/"; //$NON-NLS-1$

    
    public ProcessAttachmentManager(ProcessInstance process) {
        super();
        this.process = process;
    }

    public ProcessAttachmentManager(long processId) throws CreateException, NamingException, BPMException, InternalErrorException {
        super();
        BpmEngine engine = EJBLocator.getBpmEngine();
        
        this.process=engine.getProcess(processId);
            
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
        BpmEngine engine = EJBLocator.getBpmEngine();
        engine.update(process);
    }

    protected Set getVariables() {
    	if(process.getVariables()==null)
    		return new HashSet();
    	
        return process.getVariables().keySet();
    }

    public void eliminarArchivo(String tag) throws Exception {
        super.eliminarArchivo(tag);

        HttpSession sesion = (HttpSession) Sessions.getCurrent()
                .getNativeSession();
        BpmEngine engine = EJBLocator.getBpmEngine();
        engine.update(process);
    }

    private static Log log = LogFactory.getLog(ProcessAttachmentManager.class);

    protected void putVariable(String key, Object value) {
    	if(process.getVariables()==null)
    		process.setVariables(new Hashtable());

    	process.getVariables().put(key, value);
    		
    }

    protected void removeVariable(String key) {
    	if(process.getVariables()==null)
    		return;
    	
    	process.getVariables().remove(key);
    }

    protected Object getVariable(String key) {
    	if(process.getVariables()==null)
    		return null;
    	
        return process.getVariables().get(key);
    }
    
    public String getDownloadURL (String tag)
    {
        HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
        String url= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +  //$NON-NLS-1$ //$NON-NLS-2$
            request.getContextPath() + "/download/"+process.getId()+"/"+tag; //$NON-NLS-1$ //$NON-NLS-2$
        return url;
    }



}
