package es.caib.bpm.nas;

import java.io.IOException;
import java.util.Date;

import es.caib.bpm.nas.entity.BpmDocument;
import es.caib.bpm.nas.exception.NASException;
import es.caib.signatura.api.Signature;

public interface SignerStrategy 
{
	/**
	 * Realza la validacion del documento y la firma.
	 * 
	 * @param document
	 * @param sign
	 * @return true si el documento es valido
	 * @throws NASException 
	 */
	public Signature validateSign(BpmDocument document, Signature sign) throws NASException;
	
}
