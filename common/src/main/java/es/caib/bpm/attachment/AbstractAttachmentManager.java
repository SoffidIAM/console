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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

import es.caib.bpm.beans.exception.DocumentBeanException;
import es.caib.bpm.beans.home.DocumentHome;
import es.caib.bpm.beans.remote.Document;
import es.caib.bpm.exception.BPMException;
import es.caib.bpm.vo.DocumentReference;
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
            InterruptedException, DocumentBeanException, BPMException, InternalErrorException {
        Document document = createDocument(contentType, originalName);
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
        
    }

    public void attach(String tag, Document document) throws RemoteException {
        log.debug(Messages.getString("AbstractAttachmentManager.CloseSystemReference")); //$NON-NLS-1$
        putVariable(PREFIX + tag, document.getReference());
    }

    public Document createDocument(String contentType,
            String originalName) throws IOException,
            NamingException, CreateException, DocumentBeanException,
            InterruptedException, DocumentBeanException, BPMException {
        DocumentHome documentHome = null;
        Document document = null;
        Context context = null;

        log.debug(Messages.getString("AbstractAttachmentManager.Connect")); //$NON-NLS-1$
        context = new InitialContext();

        log.debug(Messages.getString("AbstractAttachmentManager.SendToHome")); //$NON-NLS-1$
        Object o = context.lookup(DocumentHome.JNDI_NAME);
        documentHome = (DocumentHome) PortableRemoteObject.narrow(o,
                DocumentHome.class);

        log.debug(Messages.getString("AbstractAttachmentManager.MakeDocument")); //$NON-NLS-1$
        document = documentHome.createNew(contentType, originalName, "BPM-WEB"); //$NON-NLS-1$

        return document;
    }
    
    public Document createDocument(Context context,String contentType,
            String originalName) throws IOException,
            NamingException, CreateException, DocumentBeanException,
            InterruptedException, DocumentBeanException, BPMException {
        DocumentHome documentHome = null;
        Document document = null;

        log.debug(Messages.getString("AbstractAttachmentManager.Connect")); //$NON-NLS-1$

        log.debug(Messages.getString("AbstractAttachmentManager.SendToHome")); //$NON-NLS-1$
        Object o = context.lookup(DocumentHome.JNDI_NAME);
        documentHome = (DocumentHome) PortableRemoteObject.narrow(o,
                DocumentHome.class);

        log.debug(Messages.getString("AbstractAttachmentManager.MakeDocument")); //$NON-NLS-1$
        document = documentHome.createNew(contentType, originalName, "BPM-WEB"); //$NON-NLS-1$

        return document;
    }

    public void eliminarArchivo(String tag) throws Exception {

        removeVariable(PREFIX + tag);
    }

    public DocumentReference getReference(String tag) {
        return (DocumentReference) getVariable(PREFIX + tag);
    }

    public List getTags() {
        Vector v = new Vector();
        if(getVariables()!=null){
	        for (Iterator it = getVariables().iterator(); it
	                .hasNext();) {
	            String key = (String) it.next();
	            if (key.startsWith(PREFIX)) {
	                Object value = getVariable (key);
	                if (value != null && value instanceof DocumentReference) {
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

    public Document getDocument(String tag) throws NamingException,
            RemoteException, CreateException {
        DocumentHome documentHome = (DocumentHome) PortableRemoteObject.narrow(
                new InitialContext().lookup(DocumentHome.JNDI_NAME),
                DocumentHome.class);
        DocumentReference ref = getReference(tag);

        if (ref == null)
            return null;
        else
            return documentHome.create(ref);
    }

    private static Logger log = Logger.getLogger(AbstractAttachmentManager.class);
}
