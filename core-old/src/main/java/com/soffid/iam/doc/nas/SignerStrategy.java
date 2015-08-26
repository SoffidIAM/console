package com.soffid.iam.doc.nas;

import java.io.IOException;
import java.util.Date;

import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.model.DocumentEntity;

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
	public Signature validateSign(DocumentEntity document, Signature sign) throws NASException;
	
}
