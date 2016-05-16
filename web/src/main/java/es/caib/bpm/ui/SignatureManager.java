package es.caib.bpm.ui;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.rmi.RemoteException;
import java.security.Principal;
import java.security.cert.CertificateEncodingException;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zul.Window;

import com.soffid.iam.doc.service.ejb.DocumentService;

import es.caib.bpm.attachment.TaskAttachmentManager;
import es.caib.bpm.toolkit.PrincipalSignatureAuthenticator;
import es.caib.bpm.toolkit.SignaturaHandler;
import es.caib.bpm.toolkit.WorkflowWindow;
import es.caib.bpm.toolkit.exception.SystemWorkflowException;
import es.caib.bpm.toolkit.exception.UserWorkflowException;
import es.caib.bpm.toolkit.exception.WorkflowException;
import es.caib.signatura.api.Signature;

/**
 * Classe que implementa les funcionalitats de firma per a les tasques del framework de workflows
 * 
 *
 */
public class SignatureManager implements SignaturaHandler {
	
	private WorkflowWindow window;
	
	public static final String STAMP_TYPE_ALL="ALL"; //$NON-NLS-1$
	public static final String STAMP_TYPE_ADOBE="ADOBE"; //$NON-NLS-1$
	public static final String STAMP_TYPE_PDF417="PDF417"; //$NON-NLS-1$
	public static final String STAMP_TYPE_NONE="NONE"; //$NON-NLS-1$

	public static final String STAMP_POSITION_ALL_TOP="TOP"; //$NON-NLS-1$
	public static final String STAMP_POSITION_ADOBE_TOP_LEFT="TOP_LEFT"; //$NON-NLS-1$
	public static final String STAMP_POSITION_ADOBE_TOP_RIGHT="TOP_RIGHT"; //$NON-NLS-1$
	public static final String STAMP_POSITION_PDF417_LEFT="LEFT"; //$NON-NLS-1$
	public static final String STAMP_POSITION_PDF417_RIGHT="RIGHT"; //$NON-NLS-1$
	public static final String STAMP_POSITION_ALL_BOTTOM="BOTTOM"; //$NON-NLS-1$
	public static final String STAMP_POSITION_ADOBE_BOTTOM_LEFT="BOTTOM_LEFT"; //$NON-NLS-1$
	public static final String STAMP_POSITION_ADOBE_BOTTOM_RIGHT="BOTTOM_RIGHT"; //$NON-NLS-1$
	public static final String STAMP_POSITION_ADOBE_LAST_PAGE = "LAST_PAGE"; //$NON-NLS-1$

	
	public SignatureManager(WorkflowWindow window) {
		super();
		this.window = window;
	}

	public void sign (String tag) throws WorkflowException
	{
		try {
			// Obtener el usuario actual (y su NIF)
			TaskAttachmentManager am = new TaskAttachmentManager(window.getTask());
			DocumentService doc = am.getDocument(tag);
			if (doc == null)
				throw new SystemWorkflowException(Labels.getLabel("error.msgFirma3")+" " +tag); //$NON-NLS-1$ //$NON-NLS-2$

			for (Iterator it = doc.getSigns().iterator(); it.hasNext();) {
				Signature sig = (Signature) it.next();
				if (verifyAuthor(this.window,sig))
					return ;
			}

			//si no està signat per l'usuari actual
			// Ahora mostrar el interfaz de usuario para firmar
			CMSSignatureManagerHelper.doSign (window,am, tag, doc);
			
			doc.closeDocument();
		} catch (UserWorkflowException e) {
			throw e;
		
		} catch (Exception e) {
			throw new SystemWorkflowException(e);
		}
	}

	public void signPDF (String inputTag,String outputTag,String enabledStampType, String[] enabledPositions,String forcedAdditionalText) throws WorkflowException
	{
		try {
			// Obtener el usuario actual (y su NIF)
			TaskAttachmentManager am = new TaskAttachmentManager(window.getTask());
			DocumentService doc = am.getDocument(inputTag);
			if (doc == null)
				throw new SystemWorkflowException(Labels.getLabel("error.msgFirma3")+" " +inputTag); //$NON-NLS-1$ //$NON-NLS-2$
			// Ahora mostrar el interfaz de usuario
			SignPDFSignatureManagerHelper.doSignPDF (am, inputTag,outputTag, enabledStampType, enabledPositions, forcedAdditionalText);
			
			doc.closeDocument();
		} catch (UserWorkflowException e) {
			throw e;
		
		} catch (Exception e) {
			throw new SystemWorkflowException(e);
		}
	}
	
	
	public void signPDF (String inputTag,String outputTag,String enabledStampType,String [] stampPositions, float top, float left, float height, float width, float rotation,String forcedAdditionalText) throws WorkflowException
	{
		try {
			// Obtener el usuario actual (y su NIF)
			TaskAttachmentManager am = new TaskAttachmentManager(window.getTask());
			DocumentService doc = am.getDocument(inputTag);
			if (doc == null)
				throw new SystemWorkflowException(Labels.getLabel("error.msgFirma3")+" " +inputTag); //$NON-NLS-1$ //$NON-NLS-2$
			// Ahora mostrar el interfaz de usuario
			SignPDFSignatureManagerHelper.doSignPDF (am, inputTag,outputTag, enabledStampType, stampPositions, top, left, height, width, rotation, forcedAdditionalText);
			
			doc.closeDocument();
		} catch (UserWorkflowException e) {
			throw e;
		
		} catch (Exception e) {
			throw new SystemWorkflowException(e);
		}
	}
	
	/**
	 * Comprueba que el usuario conectado es el que firma.
	 * La verificación de la firma se puede desactivar estableciendo un valor diferente a "true" para la propiedad de sistema es.caib.bpm.checkUserCert
	 * La verificación de la firma se delega a la clase establecida en la propiedad de sistema es.caib.bpm.principalSignatureAuthenticator y debe cumplir la interfaz es.caib.bpm.toolkit.PrincipalSignatureAuthenticator
	 * El comportamiento por defecto es validar las firmas, y delegar la validación a la clase es.caib.bpm.security.SeyconAuthenticationService
	 * @param sig
	 * @return
	 * @throws NamingException
	 * @throws CertificateEncodingException
	 * @throws IOException
	**/ 
	protected static boolean verifyAuthor(Window _window,Signature sig) throws Exception {
		if(System.getProperty("es.caib.bpm.checkUserCert")==null || (System.getProperty("es.caib.bpm.checkUserCert")!=null && "true".equals(System.getProperty("es.caib.bpm.checkUserCert").toLowerCase()))){ //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			Principal p = (Principal) _window.getDesktop().getExecution().getUserPrincipal();
			String authenticationServiceClassName=System.getProperty("es.caib.bpm.principalSignatureAuthenticator", "es.caib.bpm.security.SeyconAuthenticationService"); //$NON-NLS-1$ //$NON-NLS-2$
			Class authenticationServiceClass=SignatureManager.class.getClassLoader().loadClass(authenticationServiceClassName);
			Constructor constructor=authenticationServiceClass.getConstructor(null);
			PrincipalSignatureAuthenticator authenticationService=(PrincipalSignatureAuthenticator)constructor.newInstance(null);
			return authenticationService.verifySignerIsPrincipal(p, sig.getPkcs7());
		}else{
			return true;
		}
	}
	 


	
	
	public void compulsaPDF(
			String inputTag
			,String outputTag
			,String url
			,String location
			,float x
			,float y
			,float rotation
			) throws WorkflowException{
		
			try {

				TaskAttachmentManager am = new TaskAttachmentManager(window.getTask());
				DocumentService doc = am.getDocument(inputTag);
				if (doc == null)
					throw new SystemWorkflowException(Labels.getLabel("error.msgFirma3")+" " +inputTag); //$NON-NLS-1$ //$NON-NLS-2$
				
				// Ahora mostrar el interfaz de usuario
				CertifyDigitalCopyPDFSignatureManagerHelper.doCompulsaPDF (am, inputTag,outputTag,url,location,x,y,rotation);
				
				doc.closeDocument();
			} catch (UserWorkflowException e) {
				throw e;
			
			} catch (Exception e) {
				throw new SystemWorkflowException(e);
			}
		
		
	}



}
