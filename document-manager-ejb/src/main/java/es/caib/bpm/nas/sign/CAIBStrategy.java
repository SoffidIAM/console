package es.caib.bpm.nas.sign;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.apache.log4j.Logger;

import es.caib.bpm.nas.NASManager;
import es.caib.bpm.nas.SignerStrategy;
import es.caib.bpm.nas.entity.BpmDocument;
import es.caib.bpm.nas.exception.NASException;
import es.caib.signatura.api.Signature;
import es.caib.signatura.api.Signer;
import es.caib.signatura.api.SignerFactory;

public class CAIBStrategy implements SignerStrategy 
{
	Logger log = Logger.getLogger(CAIBStrategy.class);
	/**
	 * @see es.caib.bpm.nas.SignerStrategy#validateSign(es.caib.bpm.nas.entity.BpmDocument, byte[])
	 */
	public Signature validateSign(BpmDocument document, Signature sign) throws NASException 
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
