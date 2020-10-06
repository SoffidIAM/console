package com.soffid.iam.bpm.attachment;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.doc.api.DocumentReference;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.service.ejb.DocumentService;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.exception.InternalErrorException;

public abstract class AbstractAttachmentManager {

    protected static final String PREFIX = "attachment/"; //$NON-NLS-1$



    protected abstract void putVariable(String key, Object value);
    protected abstract void removeVariable(String key);
    protected abstract Set getVariables() ;
    protected abstract Object getVariable(String key);

    public void uploadFile(InputStream stream, String contentType,
            String originalName, String tag) throws IOException,
            NamingException, CreateException, DocumentBeanException,
            InterruptedException, DocumentBeanException, BPMException, InternalErrorException, EJBException, RemoveException {
        DocumentService document = createDocument(contentType, originalName);
        byte[] buffer = new byte[4096];
 
        int leidos = 0;

        document.openUploadTransfer();

        while ((leidos = stream.read(buffer)) != -1) {
            document.nextUploadPackage(buffer, leidos);
        }

        document.endUploadTransfer();

        attach(tag, document);
        
        document.closeDocument();
        
    }

    public void attach(String tag, DocumentService document) throws RemoteException, InternalErrorException {
        putVariable(PREFIX + tag, document.getReference().toString());
    }

    public void attach(String tag, DocumentReference documentReference) throws RemoteException, InternalErrorException {
        putVariable(PREFIX + tag, documentReference.toString());
    }

    public DocumentService createDocument(String contentType,
            String originalName) throws IOException,
            NamingException, CreateException, DocumentBeanException,
            InterruptedException, DocumentBeanException, BPMException, InternalErrorException {
        
        DocumentService document = EJBLocator.getDocumentService();
        
        document.createDocument(contentType, originalName, "BPM-WEB"); //$NON-NLS-1$
        return document;
    }
    
    public void eliminarArchivo(String tag) throws Exception {

        removeVariable(PREFIX + tag);
    }

    public DocumentReference getReference(String tag) {
        return new DocumentReference ((String) getVariable(PREFIX + tag));
    }

    public List getTags() {
        Vector v = new Vector();
        if(getVariables()!=null){
	        for (Iterator it = getVariables().iterator(); it
	                .hasNext();) {
	            String key = (String) it.next();
	            if (key.startsWith(PREFIX)) {
	                Object value = getVariable (key);
	                if (value != null && value instanceof String) {
	                    String tag = key.substring(PREFIX.length());
	                    v.add(tag);
	                }
	            }
	        }
	        return v;
        }else{
        	return new LinkedList();
        }
    }

    public DocumentService getDocument(String tag) throws NamingException,
            RemoteException, CreateException, InternalErrorException {
        DocumentReference ref = getReference(tag);

        if (ref == null)
            return null;
        else
        {
        	DocumentService doc = EJBLocator.getDocumentService();
            doc.openDocument(ref);
            return doc;
        }
    }

    private static Log log = LogFactory.getLog(AbstractAttachmentManager.class);
}
