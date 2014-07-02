package com.soffid.iam.doc.nas.sign;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.model.DocumentEntity;
import com.soffid.iam.doc.nas.NASManager;
import com.soffid.iam.doc.nas.SignerStrategy;

import es.caib.signatura.api.Signature;

public class CAIBStrategy implements SignerStrategy 
{
	Log log = LogFactory.getLog(CAIBStrategy.class);
	/**
	 * @see com.soffid.iam.doc.nas.SignerStrategy#validateSign(es.caib.bpm.nas.entity.DocumentEntity, byte[])
	 */
	public Signature validateSign(DocumentEntity document, Signature sign) throws NASException 
	{
		
		File documentFile= null;
		ByteArrayInputStream streamBytes= null;
		ObjectInputStream streamObjeto= null;
		boolean valido= false;
		
		try
		{
			documentFile= NASManager.getInstance().retreiveFile(document.getFsPath());		
			
			if (! sign.verify() )
			{
				log.warn("Certificate not valid for signature: "+sign.getCertSubjectCommonName());
				return null;
			}
			if ( ! sign.verifyAPosterioriTimestamp(new FileInputStream(documentFile)))
			{
				log.warn("Signature tampering for: "+sign.getCertSubjectCommonName());
				return null;
			}
		}
		catch(Exception ex)
		{
			throw new NASException(ex);
		}
		finally
		{
			NASManager.getInstance().cleanTemporaryResources();

			try
			{
				if(streamBytes!= null)
				{
					streamBytes.close();
				}

				if(streamObjeto!= null)
				{
					streamObjeto.close();
				}
			}
			catch(Exception ex)
			{
				throw new NASException(ex);
			}
		}
		
		return sign;
	}

}
