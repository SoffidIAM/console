package es.caib.bpm.ui.tree;


import java.io.IOException;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import es.caib.signatura.api.Signature;
import es.caib.signatura.api.SignatureTimestampException;

/**
 * Representa una fila del arbol de firmas.
 * 
 * @author Pablo Hern�n Gim�nez.
 */
public class FirmaListitem extends Listitem 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FirmaListitem(Signature firma) throws IOException, ClassNotFoundException, SignatureTimestampException
	{
		
		this.firma= firma;

		
		this.appendChild(new Listcell(firma.getCertCaName()));
		this.appendChild(new Listcell(firma.getCertSubjectCommonName()));
		
		if(firma.getDate()!= null)
		{
			this.appendChild(new Listcell(firma.getDate().toString()));
		}
		else
		{
			this.appendChild(new Listcell(Messages.getString("FirmaListitem.NoUserSign"))); //$NON-NLS-1$
		}
	}
	
	public Signature getSign()
	{
		return this.firma;
	}
	
	private Signature firma= null;
}
