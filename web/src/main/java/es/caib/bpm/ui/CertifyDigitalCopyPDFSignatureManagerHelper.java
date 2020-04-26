package es.caib.bpm.ui;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import es.caib.bpm.attachment.TaskAttachmentManager;
import es.caib.bpm.toolkit.exception.UserWorkflowException;

public class CertifyDigitalCopyPDFSignatureManagerHelper {
	protected static void doCompulsaPDF(
			TaskAttachmentManager am
			,String inputTag
			,String outputTag
			,String url
			,String location
			,float x
			,float y
			,float rotation) throws Exception {
    	// Generar archivo
		String downloadUrl = am.getDownloadURL(inputTag);
		String uploadUrl = am.getUploadURL(outputTag);
		
		Window windowFirma = (Window) Path.getComponent("//sign/window"); //$NON-NLS-1$
		Path.getComponent("//sign/appletFirma").setVisible(true); //$NON-NLS-1$
		windowFirma.setAttribute("type", "PDF-CERTIFY"); //$NON-NLS-1$ //$NON-NLS-2$
		Events.postEvent(new Event("onSetTarget", windowFirma, uploadUrl )); //upload via HTTP mèthod PUT //$NON-NLS-1$
		Events.postEvent(new Event("onSetSource", windowFirma, downloadUrl )); //$NON-NLS-1$
		
		
		((Textbox)windowFirma.getFellow("compulsaPDF_x")).setValue(""+x); //$NON-NLS-1$ //$NON-NLS-2$
		((Textbox)windowFirma.getFellow("compulsaPDF_y")).setValue(""+y); //$NON-NLS-1$ //$NON-NLS-2$
		((Textbox)windowFirma.getFellow("compulsaPDF_degrees")).setValue(""+rotation); //$NON-NLS-1$ //$NON-NLS-2$
		((Textbox)windowFirma.getFellow("compulsaPDF_url")).setValue(url); //$NON-NLS-1$
		((Textbox)windowFirma.getFellow("compulsaPDF_location")).setValue(location); //$NON-NLS-1$
		
		windowFirma.getFellow("panellOpcionsCompulsaPDF").setVisible(true); //$NON-NLS-1$
		
		//mostramos la ventana y esperamos intervención del usuario
		windowFirma.doModal();
		
		// Recoger firma, en firmaPDF debe llegar un string con valor "PDF-CERTIFY"
		String signatura = (String) windowFirma.getAttribute("signature"); //$NON-NLS-1$
		
		

		if (signatura == null)
			throw new UserWorkflowException(Labels.getLabel("error.msgFirma1")); //$NON-NLS-1$
		
	}
}
