package es.caib.bpm.ui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.cert.CertificateEncodingException;

import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;

import com.soffid.iam.doc.service.ejb.DocumentService;

import es.caib.bpm.attachment.TaskAttachmentManager;
import es.caib.bpm.toolkit.exception.UserWorkflowException;
import es.caib.seycon.ng.utils.Security;
import es.caib.signatura.api.Signature;

public class CMSSignatureManagerHelper {
	protected static void doSign(Window window,TaskAttachmentManager am, String tag, DocumentService doc) throws Exception {
    	// Generar archivo
		String url = am.getDownloadURL(tag);
		
		Window windowFirma = (Window) Path.getComponent("//sign/window"); //$NON-NLS-1$
		Path.getComponent("//sign/appletFirma").setVisible(true); //$NON-NLS-1$
		windowFirma.setAttribute("type", "CMS"); //$NON-NLS-1$ //$NON-NLS-2$
		Events.postEvent(new Event("onSetSource", windowFirma, url)); //$NON-NLS-1$
        
		windowFirma.doModal();
		
		// Recoger firma

		Signature signatura = (Signature) windowFirma.getAttribute("signature"); //$NON-NLS-1$
		if (signatura == null)
			throw new UserWorkflowException(Labels.getLabel("error.msgFirma1")); //$NON-NLS-1$
		

		if (! SignatureManager.verifyAuthor(window,signatura))
			throw new UserWorkflowException(Labels.getLabel("error.msgFirma2")+" "+Security.getCurrentUser()); //$NON-NLS-1$ //$NON-NLS-2$
		
		doc.addSign( signatura );
	}

}
