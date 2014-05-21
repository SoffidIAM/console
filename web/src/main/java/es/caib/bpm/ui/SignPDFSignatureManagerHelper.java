package es.caib.bpm.ui;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import es.caib.bpm.attachment.TaskAttachmentManager;
import es.caib.bpm.toolkit.exception.SystemWorkflowException;
import es.caib.bpm.toolkit.exception.UserWorkflowException;
import es.caib.bpm.toolkit.exception.WorkflowException;

public class SignPDFSignatureManagerHelper {
	protected static void doSignPDF(TaskAttachmentManager am, String inputTag,String outputTag,String enabledStampType,String[] enabledPositions,String forcedAdditionalText) throws Exception {
    	// Generar archivo
		String downloadUrl = am.getDownloadURL(inputTag);
		String uploadUrl = am.getUploadURL(outputTag);
		
		Window windowFirma = (Window) Path.getComponent("//sign/window"); //$NON-NLS-1$
		Path.getComponent("//sign/appletFirma").setVisible(true); //$NON-NLS-1$
		windowFirma.setAttribute("type", "PDF"); //$NON-NLS-1$ //$NON-NLS-2$
		Events.postEvent(new Event("onSetTarget", windowFirma, uploadUrl )); //upload via HTTP mèthod PUT //$NON-NLS-1$
		Events.postEvent(new Event("onSetSource", windowFirma, downloadUrl )); //$NON-NLS-1$
        
		//control de tipos de sello permitidos
		if(SignatureManager.STAMP_TYPE_ALL.equals(enabledStampType)){
			windowFirma.getFellow("windowTipusSegell").setVisible(true); //$NON-NLS-1$

			//default: sello_adobe
			Radio radio=(Radio)windowFirma.getFellow("sello_ADOBE"); //$NON-NLS-1$
			Radiogroup radioGroup = ((Radiogroup)windowFirma.getFellow("tipusFirmaPDF")); //$NON-NLS-1$
			selectRadioGroupItem(radio,radioGroup);


			windowFirma.getFellow("panellOpcionsPDFAdobe").setVisible(true); //$NON-NLS-1$
			
		}else if(SignatureManager.STAMP_TYPE_ADOBE.equals(enabledStampType)){
			Radio radio=(Radio)windowFirma.getFellow("sello_ADOBE"); //$NON-NLS-1$
			Radiogroup radioGroup = ((Radiogroup)windowFirma.getFellow("tipusFirmaPDF")); //$NON-NLS-1$
			selectRadioGroupItem(radio,radioGroup);

			
			windowFirma.getFellow("panellOpcionsPDFAdobe").setVisible(true); //$NON-NLS-1$
			
		}else if(SignatureManager.STAMP_TYPE_PDF417.equals(enabledStampType)){
			Radio radio=(Radio)windowFirma.getFellow("sello_PDF417"); //$NON-NLS-1$
			Radiogroup radioGroup = ((Radiogroup)windowFirma.getFellow("tipusFirmaPDF")); //$NON-NLS-1$
			selectRadioGroupItem(radio,radioGroup);

			
			windowFirma.getFellow("panellOpcionsPDF417").setVisible(true); //$NON-NLS-1$
			
		}else if(SignatureManager.STAMP_TYPE_NONE.equals(enabledStampType)){
			Radio radio=(Radio)windowFirma.getFellow("sello_NONE"); //$NON-NLS-1$
			Radiogroup radioGroup = ((Radiogroup)windowFirma.getFellow("tipusFirmaPDF")); //$NON-NLS-1$
			selectRadioGroupItem(radio,radioGroup);

		}

		//control de posiciones admitidas
		//si es nulo, se admiten todas
		//si no es nulo, la primera que venga en el array es la seleccionada por defecto
		if(enabledPositions!=null){
			if(enabledPositions.length==0){
				throw new SystemWorkflowException(Messages.getString("SignPDFSignatureManagerHelper.NotPositionPDFAllowed")); //$NON-NLS-1$
			}else if(enabledPositions.length==1){
				if(enabledPositions.equals(SignatureManager.STAMP_POSITION_ADOBE_LAST_PAGE)){
					Checkbox check=(Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_ADOBE+"_"+SignatureManager.STAMP_POSITION_ADOBE_LAST_PAGE); //$NON-NLS-1$
					check.setChecked(true);
				}else{
					//marquem l'opció sel.leccionada
					Radio radio=(Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_ADOBE+"_"+enabledPositions[0]); //$NON-NLS-1$
					if(radio!=null){
						radio.setDisabled(false);
						Radiogroup radioGroup = ((Radiogroup)windowFirma.getFellow("posicioEstampaPDFAdobe")); //$NON-NLS-1$
						selectRadioGroupItem(radio,radioGroup);

					}else{
						throw new SystemWorkflowException(Messages.getString("SignPDFSignatureManagerHelper.NotPositionPDFAllowed")); //$NON-NLS-1$
					}
				}
				//no permitim escollir posició
				windowFirma.getFellow("panellOpcionsPDFAdobe_Posicio").setVisible(false); //$NON-NLS-1$
				windowFirma.getFellow("panellOpcionsPDFCodiPunts_Posicio").setVisible(false); //$NON-NLS-1$
				
			}else{
				//Last page siempre lo permitimos en adobe
				//disable ADOBE
				((Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_ADOBE+"_"+SignatureManager.STAMP_POSITION_ADOBE_TOP_LEFT)).setDisabled(true); //$NON-NLS-1$
				((Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_ADOBE+"_"+SignatureManager.STAMP_POSITION_ALL_TOP)).setDisabled(true); //$NON-NLS-1$
				((Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_ADOBE+"_"+SignatureManager.STAMP_POSITION_ADOBE_TOP_RIGHT)).setDisabled(true); //$NON-NLS-1$
				((Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_ADOBE+"_"+SignatureManager.STAMP_POSITION_ADOBE_BOTTOM_LEFT)).setDisabled(true); //$NON-NLS-1$
				((Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_ADOBE+"_"+SignatureManager.STAMP_POSITION_ALL_BOTTOM)).setDisabled(true); //$NON-NLS-1$
				((Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_ADOBE+"_"+SignatureManager.STAMP_POSITION_ADOBE_BOTTOM_RIGHT)).setDisabled(true); //$NON-NLS-1$

				//disable PDF417
				((Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_PDF417+"_"+SignatureManager.STAMP_POSITION_ALL_TOP)).setDisabled(true); //$NON-NLS-1$
				((Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_PDF417+"_"+SignatureManager.STAMP_POSITION_PDF417_LEFT)).setDisabled(true); //$NON-NLS-1$
				((Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_PDF417+"_"+SignatureManager.STAMP_POSITION_PDF417_RIGHT)).setDisabled(true); //$NON-NLS-1$
				((Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_PDF417+"_"+SignatureManager.STAMP_POSITION_ALL_BOTTOM)).setDisabled(true); //$NON-NLS-1$

				
				
				//para adobe
				for(int i=0;i<enabledPositions.length;i++){
					try{
						Radio radio=(Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_ADOBE+"_"+enabledPositions[i]); //$NON-NLS-1$
						if(radio!=null){
							radio.setDisabled(false);
							if(i==0){
								Radiogroup radioGroup=((Radiogroup)windowFirma.getFellow("posicioEstampaPDFAdobe")); //$NON-NLS-1$
								selectRadioGroupItem(radio,radioGroup);
								
							}
						}
					}catch(ComponentNotFoundException e){//NO ES POSIOCIÓ ADOBE
					}
				}
				//para PDF417
				for(int i=0;i<enabledPositions.length;i++){
					try{
						Radio radio=(Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_PDF417+"_"+enabledPositions[i]); //$NON-NLS-1$
						if(radio!=null){
							radio.setDisabled(false);
							if(i==0){
								Radiogroup radioGroup = ((Radiogroup)windowFirma.getFellow("posicioEstampaPDFCodiPunts")); //$NON-NLS-1$
								selectRadioGroupItem(radio,radioGroup);

							}
						}
					}catch(ComponentNotFoundException e){//NO ES POSIOCIÓ PDF417}
					}
				}
			}
		}else{
			//totes les opcions disponibles
			
			//habilitem posicions per defecte
			
			Radio radio=((Radio)windowFirma.getFellow(SignatureManager.STAMP_TYPE_PDF417+"_"+SignatureManager.STAMP_POSITION_ALL_TOP)); //$NON-NLS-1$
			Radiogroup radioGroup = ((Radiogroup)windowFirma.getFellow("posicioEstampaPDFCodiPunts")); //$NON-NLS-1$
			selectRadioGroupItem(radio,radioGroup);

		}
		

		
		
		//control de texto adicional
		if(forcedAdditionalText!=null){
			//pdf417
			Textbox textAddicional=((Textbox)windowFirma.getFellow("textAddicional")); //$NON-NLS-1$
			textAddicional.setValue(forcedAdditionalText);
			textAddicional.setVisible(false);
			
			windowFirma.getFellow("panellOpcionsPDFCodiPunts_TextAddicional").setVisible(false); //$NON-NLS-1$

			//adobe
			Textbox motiuDeFirma=((Textbox)windowFirma.getFellow("motiuDeFirma")); //$NON-NLS-1$
			motiuDeFirma.setValue(forcedAdditionalText);
			motiuDeFirma.setVisible(false);

			windowFirma.getFellow("panellOpcionsPDFAdobe_MotiuDeFirma").setVisible(false); //$NON-NLS-1$
		}else{
			//pdf417
			Textbox textAddicional=((Textbox)windowFirma.getFellow("textAddicional")); //$NON-NLS-1$
			textAddicional.setValue(Messages.getString("SignPDFSignatureManagerHelper.AcceptDocumentInfo")); //$NON-NLS-1$
			textAddicional.setVisible(true);
			
			windowFirma.getFellow("panellOpcionsPDFCodiPunts_TextAddicional").setVisible(true); //$NON-NLS-1$

			//adobe
			Textbox motiuDeFirma=((Textbox)windowFirma.getFellow("motiuDeFirma")); //$NON-NLS-1$
			motiuDeFirma.setValue(Messages.getString("SignPDFSignatureManagerHelper.AcceptDocumentInfo")); //$NON-NLS-1$
			motiuDeFirma.setVisible(true);

			windowFirma.getFellow("panellOpcionsPDFAdobe_MotiuDeFirma").setVisible(true); //$NON-NLS-1$
			
		}

		
		
		
		windowFirma.doModal();
		
		// Recoger firma, en firmaPDF debe llegar un string con valor "PDF"
		String signatura = (String) windowFirma.getAttribute("signature"); //$NON-NLS-1$
		
		if (signatura == null)
			throw new UserWorkflowException(Labels.getLabel("error.msgFirma1")); //$NON-NLS-1$
	}

	
	private static void selectRadioGroupItem(Radio radio, Radiogroup radioGroup) {
		boolean found=false;
		for(int i=0;i<radioGroup.getItemCount();i++){
			if(radio.getId()!= null && radio.getId().equals(radioGroup.getItemAtIndex(i).getId())){
				radioGroup.setSelectedIndex(i);
				found=true;
				break;
			}
		}
		if ( ! found ) throw new UiException(String.format(Messages.getString("SignPDFSignatureManagerHelper.NotChildInfo"), radio.getId()));  //$NON-NLS-1$
		
	}


	protected static void doSignPDF(TaskAttachmentManager am, String inputTag,String outputTag,String enabledStampType,String[] enabledPositions,float top, float left, float height, float width, float rotation,String forcedAdditionalText) throws Exception {
    	// Generar archivo
		String downloadUrl = am.getDownloadURL(inputTag);
		String uploadUrl = am.getUploadURL(outputTag);
		
		Window windowFirma = (Window) Path.getComponent("//sign/window"); //$NON-NLS-1$
		Path.getComponent("//sign/appletFirma").setVisible(true); //$NON-NLS-1$
		windowFirma.setAttribute("type", "PDF-EXTENDED"); //$NON-NLS-1$ //$NON-NLS-2$
		Events.postEvent(new Event("onSetTarget", windowFirma, uploadUrl )); //upload via HTTP mèthod PUT //$NON-NLS-1$
		Events.postEvent(new Event("onSetSource", windowFirma, downloadUrl )); //$NON-NLS-1$
        
		//control de tipos de sello permitidos
		if(SignatureManager.STAMP_TYPE_ADOBE.equals(enabledStampType)){
			Radio radio=(Radio)windowFirma.getFellow("sello_ADOBE"); //$NON-NLS-1$
			Radiogroup radioGroup = ((Radiogroup)windowFirma.getFellow("tipusFirmaPDF")); //$NON-NLS-1$
			selectRadioGroupItem(radio,radioGroup);

			
			windowFirma.getFellow("panellOpcionsPDFAdobe").setVisible(true); //$NON-NLS-1$
			
		}else if(SignatureManager.STAMP_TYPE_PDF417.equals(enabledStampType)){
			Radio radio=(Radio)windowFirma.getFellow("sello_PDF417"); //$NON-NLS-1$
			Radiogroup radioGroup = ((Radiogroup)windowFirma.getFellow("tipusFirmaPDF")); //$NON-NLS-1$
			selectRadioGroupItem(radio,radioGroup);

			windowFirma.getFellow("panellOpcionsPDF417").setVisible(true); //$NON-NLS-1$
			
			
		}else if(SignatureManager.STAMP_TYPE_NONE.equals(enabledStampType)){
			Radio radio=(Radio)windowFirma.getFellow("sello_NONE"); //$NON-NLS-1$
			Radiogroup radioGroup = ((Radiogroup)windowFirma.getFellow("tipusFirmaPDF")); //$NON-NLS-1$
			selectRadioGroupItem(radio,radioGroup);

		}
		
		//control de posiciones admitidas
		//no permitim escollir posició pero forcem si estampar adobe a ultima pàgina
		windowFirma.getFellow("panellOpcionsPDFCodiPunts_Posicio").setVisible(false); //$NON-NLS-1$
		windowFirma.getFellow("panellOpcionsPDFAdobe_OpcionsPosicio").setVisible(false); //$NON-NLS-1$
		
		
		
		//control de texto adicional
		if(forcedAdditionalText!=null){
			//pdf417
			Textbox textAddicional=((Textbox)windowFirma.getFellow("textAddicional")); //$NON-NLS-1$
			textAddicional.setValue(forcedAdditionalText);
			textAddicional.setVisible(false);
			
			windowFirma.getFellow("panellOpcionsPDFCodiPunts_TextAddicional").setVisible(false); //$NON-NLS-1$

			//adobe
			Textbox motiuDeFirma=((Textbox)windowFirma.getFellow("motiuDeFirma")); //$NON-NLS-1$
			motiuDeFirma.setValue(forcedAdditionalText);
			motiuDeFirma.setVisible(false);

			windowFirma.getFellow("panellOpcionsPDFAdobe_MotiuDeFirma").setVisible(false); //$NON-NLS-1$
		}else{
			//pdf417
			Textbox textAddicional=((Textbox)windowFirma.getFellow("textAddicional")); //$NON-NLS-1$
			textAddicional.setValue(Messages.getString("SignPDFSignatureManagerHelper.AcceptDocumentInfo")); //$NON-NLS-1$
			textAddicional.setVisible(true);
			
			windowFirma.getFellow("panellOpcionsPDFCodiPunts_TextAddicional").setVisible(true); //$NON-NLS-1$

			//adobe
			Textbox motiuDeFirma=((Textbox)windowFirma.getFellow("motiuDeFirma")); //$NON-NLS-1$
			motiuDeFirma.setValue(Messages.getString("SignPDFSignatureManagerHelper.AcceptDocumentInfo")); //$NON-NLS-1$
			motiuDeFirma.setVisible(true);

			windowFirma.getFellow("panellOpcionsPDFAdobe_MotiuDeFirma").setVisible(true); //$NON-NLS-1$
			
		}

		((Textbox)windowFirma.getFellow("signPDFExt_x")).setValue(""+left); //$NON-NLS-1$ //$NON-NLS-2$
		((Textbox)windowFirma.getFellow("signPDFExt_y")).setValue(""+top); //$NON-NLS-1$ //$NON-NLS-2$
		((Textbox)windowFirma.getFellow("signPDFExt_height")).setValue(""+height); //$NON-NLS-1$ //$NON-NLS-2$
		((Textbox)windowFirma.getFellow("signPDFExt_width")).setValue(""+width); //$NON-NLS-1$ //$NON-NLS-2$
		((Textbox)windowFirma.getFellow("signPDFExt_degrees")).setValue(""+rotation); //$NON-NLS-1$ //$NON-NLS-2$
		
		//establim si estampar primera o última pàgina
		if(enabledPositions!=null && enabledPositions.length>0 && enabledPositions[0].equals(SignatureManager.STAMP_POSITION_ADOBE_LAST_PAGE))
			((Checkbox)windowFirma.getFellow("ADOBE_LAST_PAGE")).setChecked(true); //$NON-NLS-1$
		else
			((Checkbox)windowFirma.getFellow("ADOBE_LAST_PAGE")).setChecked(false); //$NON-NLS-1$
		
		
		windowFirma.doModal();
		
		// Recoger firma, en firmaPDF debe llegar un string con valor "PDF"
		String signatura = (String) windowFirma.getAttribute("signature"); //$NON-NLS-1$
		
		if (signatura == null)
			throw new UserWorkflowException(Labels.getLabel("error.msgFirma1")); //$NON-NLS-1$
	}
}
