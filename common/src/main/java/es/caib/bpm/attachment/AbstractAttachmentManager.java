package es.caib.bpm.attachment;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

import com.soffid.iam.doc.api.DocumentReference;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.service.ejb.DocumentService;
import com.soffid.iam.doc.service.ejb.DocumentServiceHome;

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

        log.debug(Messages.getString("AbstractAttachmentManager.OpenArchiveTransfer")); //$NON-NLS-1$
        document.openUploadTransfer();

        log.debug(Messages.getString("AbstractAttachmentManager.SendPackages")); //$NON-NLS-1$
        while ((leidos = stream.read(buffer)) != -1) {
            document.nextUploadPackage(buffer, leidos);
        }

        log.debug(Messages.getString("AbstractAttachmentManager.CloseArchiveTransfer")); //$NON-NLS-1$
        document.endUploadTransfer();

        attach(tag, document);
        
        document.remove();
        
    }

    public void attach(String tag, DocumentService document) throws RemoteException, InternalErrorException {
        log.debug(Messages.getString("AbstractAttachmentManager.CloseSystemReference")); //$NON-NLS-1$
        putVariable(PREFIX + tag, document.getReference().toString());
    }

    public DocumentService createDocument(String contentType,
            String originalName) throws IOException,
            NamingException, CreateException, DocumentBeanException,
            InterruptedException, DocumentBeanException, BPMException, InternalErrorException {
        DocumentServiceHome documentHome = null;
        DocumentService document = null;
        Context context = null;

        log.debug(Messages.getString("AbstractAttachmentManager.Connect")); //$NON-NLS-1$
        context = new InitialContext();

        log.debug(Messages.getString("AbstractAttachmentManager.SendToHome")); //$NON-NLS-1$
        Object o = context.lookup(DocumentServiceHome.JNDI_NAME);
        documentHome = (DocumentServiceHome) PortableRemoteObject.narrow(o,
                DocumentServiceHome.class);

        log.debug(Messages.getString("AbstractAttachmentManager.MakeDocument")); //$NON-NLS-1$
        document = documentHome.create();
        document.createDocument(contentType, originalName, "BPM-WEB"); //$NON-NLS-1$

        return document;
    }
    
    public DocumentService createDocument(Context context,String contentType,
            String originalName) throws IOException,
            NamingException, CreateException, DocumentBeanException,
            InterruptedException, DocumentBeanException, BPMException, InternalErrorException {
        DocumentServiceHome documentHome = null;
        DocumentService document = null;

        log.debug(Messages.getString("AbstractAttachmentManager.Connect")); //$NON-NLS-1$

        log.debug(Messages.getString("AbstractAttachmentManager.SendToHome")); //$NON-NLS-1$
        Object o = context.lookup(DocumentServiceHome.JNDI_NAME);
        documentHome = (DocumentServiceHome) PortableRemoteObject.narrow(o,
                DocumentServiceHome.class);

        log.debug(Messages.getString("AbstractAttachmentManager.MakeDocument")); //$NON-NLS-1$
        document = documentHome.create();
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
        DocumentServiceHome documentHome = (DocumentServiceHome) PortableRemoteObject.narrow(
                new InitialContext().lookup(DocumentServiceHome.JNDI_NAME),
                DocumentServiceHome.class);
        DocumentReference ref = getReference(tag);

        if (ref == null)
            return null;
        else
        {
            DocumentService doc = documentHome.create();
            doc.openDocument(ref);
            return doc;
        }
    }

    private static Logger log = Logger.getLogger(AbstractAttachmentManager.class);
}
