package es.caib.bpm.ui;

import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;

import es.caib.signatura.api.Signature;

public class SignApplet extends es.caib.zkib.zkiblaf.SignApplet{
	public void onLoadCerts(Event event){
		String[] certs = (String[]) event.getData();
		Listbox certificats = (Listbox) new Path("//sign/window/certificats").getComponent(); //$NON-NLS-1$
		certificats.getItems().clear ();
		
		int i=0;
		for ( i = 0 ; i < certs.length; i++)
		{
			new Listitem (certs[i], certs[i]).setParent(certificats);
		}
		if (i > 0)
			certificats.setSelectedIndex(0);
		else
		{
			new Path("//sign/window").getComponent().setAttribute("signature", null); //$NON-NLS-1$ //$NON-NLS-2$
	    	new Path("//sign/window").getComponent().setVisible(false); //$NON-NLS-1$
	    	es.caib.zkib.zkiblaf.Missatgebox.avis (Messages.getString("SignApplet.NotCertificateForUser"), Messages.getString("SignApplet.WarningInfo")); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	public void onSign(Event event){
			Window signWindow=(Window) new Path("//sign/window").getComponent(); //$NON-NLS-1$
			//System.out.println(signWindow.getAttribute("type"));
	    	if (new String("PDF").equals(signWindow.getAttribute("type")))  //$NON-NLS-1$ //$NON-NLS-2$
				signWindow.setAttribute("signature", "PDF"); //$NON-NLS-1$ //$NON-NLS-2$
	    	else if(new String("CMS").equals(signWindow.getAttribute("type"))) //$NON-NLS-1$ //$NON-NLS-2$
				signWindow.setAttribute("signature", (Signature)event.getData()); //$NON-NLS-1$
	    	else if(new String("PDF-CERTIFY").equals(signWindow.getAttribute("type"))) //$NON-NLS-1$ //$NON-NLS-2$
				signWindow.setAttribute("signature", "PDF-CERTIFY");					 //$NON-NLS-1$ //$NON-NLS-2$
	    	else if(new String("PDF-EXTENDED").equals(signWindow.getAttribute("type"))) //$NON-NLS-1$ //$NON-NLS-2$
				signWindow.setAttribute("signature", "PDF-EXTENDED");					 //$NON-NLS-1$ //$NON-NLS-2$
	    	
			signWindow.setVisible(false);
	}
	
	
	
	public int calculaPDFStampPositionValue() throws Exception{
		Window windowFirma = (Window) Path.getComponent("//sign/window"); //$NON-NLS-1$
		Radiogroup tipusSegell=(Radiogroup) windowFirma.getFellow("tipusFirmaPDF"); //$NON-NLS-1$

		if("ADOBE".equals(tipusSegell.getSelectedItem().getValue())){ //$NON-NLS-1$
			return calculaPosicioSegellAdobe();	
		}else if("PDF417".equals(tipusSegell.getSelectedItem().getValue())){ //$NON-NLS-1$
			return calculaPosicioCodiPunts();
		}else if("NONE".equals(tipusSegell.getSelectedItem().getValue())){ //$NON-NLS-1$
			return 0;
		}else{
			es.caib.zkib.zkiblaf.Missatgebox.avis (Messages.getString("SignApplet.SelectTimeStampInfo"),Messages.getString("SignApplet.WarningInfo")); //$NON-NLS-1$ //$NON-NLS-2$
			return -1;
		}

	}

	public int calculaPosicioCodiPunts(){
		//calcula la posició final 
		Window windowFirma = (Window) Path.getComponent("//sign/window"); //$NON-NLS-1$
		Radiogroup posicioEstampaPDFCodiPunts=(Radiogroup) windowFirma.getFellow("posicioEstampaPDFCodiPunts"); //$NON-NLS-1$

		int posicio = Integer.parseInt(((Radiogroup)posicioEstampaPDFCodiPunts).getSelectedItem().getValue());
			
		return posicio;
	}

	public int calculaPosicioSegellAdobe(){
		//calcula la posició final 
		Window windowFirma = (Window) Path.getComponent("//sign/window"); //$NON-NLS-1$
		Radiogroup posicioEstampaPDFAdobe=(Radiogroup) windowFirma.getFellow("posicioEstampaPDFAdobe"); //$NON-NLS-1$

		Checkbox ADOBE_LAST_PAGE=(Checkbox) windowFirma.getFellow("ADOBE_LAST_PAGE"); //$NON-NLS-1$
		boolean afegirNomesUltimaPagina = ((Checkbox)ADOBE_LAST_PAGE).isChecked();
		
		int posicio = Integer.parseInt(((Radiogroup)posicioEstampaPDFAdobe).getSelectedItem().getValue());
			
		if(afegirNomesUltimaPagina){
			posicio=posicio | 16;
		}
		
		//Forcem estampa adobe
		posicio=posicio | 32;
		
		return posicio;
	}

}
