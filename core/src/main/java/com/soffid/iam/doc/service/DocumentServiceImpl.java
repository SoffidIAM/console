package com.soffid.iam.doc.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.api.DocumentReference;
import com.soffid.iam.doc.model.DocSign;
import com.soffid.iam.doc.model.DocumentEntity;
import com.soffid.iam.doc.nas.NASManager;
import com.soffid.iam.doc.nas.SignerStrategy;
import com.soffid.iam.doc.nas.crypto.CryptoUtil;
import com.soffid.iam.utils.Security;

import es.caib.signatura.api.Signature;
import es.caib.signatura.api.SignatureTimestampException;

public class DocumentServiceImpl extends DocumentServiceBase {
	/** El documento interno */
	private DocumentEntity innerDocument= null;
	/** Indica si es un nuevo documento */
	private Boolean newDocument= null;
	/** El arhivo temporario */
	private File tempFile= null;
	/** El stream de salida en el archivo */
	private transient FileOutputStream outputStream= null;
	/** El stream de lectura en el archivo */
	private transient FileInputStream inputStream= null;
	/** El nombre de la aplicacion al que pertenece */
	private String application= null;
	/** El a�o */
	private int year= 0;
	/** Logger */
	private static Log log= LogFactory.getLog(DocumentServiceImpl.class);
	public static String DOWNLOAD_LOG= "DOWNLOAD_LOG";
	public static String ACCESS_LOG= "ACCESS_LOG";
	public static String UPLOAD_LOG= "UPLOAD_LOG";

	
	@Override
	protected String handleGetMimeType() throws Exception {
		return this.innerDocument.getMimeType();
	}

	@Override
	protected void handleAddSign(Signature sign) throws Exception {
		DocSign signEntity= null;
		FileOutputStream streamEscritura= null;
		File tempFile= null;
		String path= null;
		
		SignerStrategy estrategiaFirma= null;

		estrategiaFirma = getNASManager().getSignStrategy();

		if (estrategiaFirma.validateSign(innerDocument, sign) == null)
		{
			throw new NASException("El arreglo de bytes no es una firma válida.");			
		}
		tempFile = File.createTempFile("docmgr", ".signature");

		streamEscritura= new FileOutputStream(tempFile);
		
		new ObjectOutputStream(streamEscritura).writeObject(sign);

		streamEscritura.close();
		
		DocumentEntity document = getDocumentEntityDao().load(innerDocument.getId());

		signEntity= getDocSignDao().newDocSign();
		
		path = getNASManager().uploadFile(document.getApplication(), document.getYear(), tempFile);
		
		tempFile.delete();
		
		signEntity.setBpmDocument(document);
		signEntity.setSignType("O"); // OBJECTE SIGNATURE
		signEntity.setFsPath(path);
		Date d = null;
		try {
			d = sign.getDate();
		} catch (SignatureTimestampException e) {
			log.error(e.getMessage(),e);
		}
		if (d == null) d = new Date ();
		signEntity.setTimestamp(d);

		getDocSignDao().create(signEntity);
	}

	@Override
	protected void handleNextUploadPackage(byte[] filePackage, int length)
			throws Exception {
		if(!this.newDocument.booleanValue())
		{
			throw new DocumentBeanException("This document has already been uploaded");
		}

		if(this.outputStream== null)
		{
			throw new DocumentBeanException("openUploadTransfer has not been called");
		}

		try
		{
			this.outputStream.write(filePackage, 0, length);
		}
		catch(Exception ex)
		{
			throw new DocumentBeanException(ex);
		}
	}

	@Override
	protected void handleOpenDocument(DocumentReference reference)
			throws Exception {
		DocumentEntity result= null;
		boolean roleFound= false;
		
		Long l = Long.decode(reference.getId());
		result = getDocumentEntityDao().load(l);
		
		if(result== null || !result.getHash().equals(reference.getHash()))
		{
			throw new DocumentBeanException("No se encontro un documento con referencia " + reference);
		}
		
		this.newDocument= new Boolean(false);
		
		
		this.innerDocument= result;
	}

	@Override
	protected String handleGetExternalName() throws Exception {
		return this.innerDocument.getExternalName();
	}

	@Override
	protected void handleOpenUploadTransfer() throws Exception {
		try
		{
			if(this.newDocument.booleanValue())
			{
				tempFile = File.createTempFile("docmgr", ".doc");
				
				this.outputStream= new FileOutputStream(this.tempFile);
			}
			else
			{
				throw new DocumentBeanException("Only new documents can be uploaded");
			}
		}
		catch(Exception ex)
		{
			throw new DocumentBeanException(ex);
		}
	}

	@Override
	protected byte[] handleNextDownloadPackage(int length) throws Exception {
		int leidos= 0;
		ByteArrayOutputStream outputStream= null;
		byte[] filePackage= null;
		
		if(this.newDocument.booleanValue())
		{
			throw new DocumentBeanException("Only existing documents can be downloaded");
		}

		if(this.inputStream== null)
		{
			throw new DocumentBeanException("Download must be open before downloading next package");
		}

		try
		{
			outputStream= new ByteArrayOutputStream();
			
			filePackage= new byte[length];
			
			leidos= this.inputStream.read(filePackage);
			
			if(leidos>= 0)
			{
				outputStream.write(filePackage, 0, leidos);
			}
			else
			{
				return null;
			}
		}
		catch(Exception ex)
		{
			throw new DocumentBeanException(ex);
		}
		
		return outputStream.toByteArray();
	}

	@Override
	protected void handleEndUploadTransfer() throws Exception {
		String path= null;
		String hash = null;
		if(!this.newDocument.booleanValue())
		{
			throw new DocumentBeanException("Only new documents can be uploaded");
		}

		if(this.outputStream== null)
		{
			throw new DocumentBeanException("Upload must be started before");
		}
		
		try
		{
			this.outputStream.close();
			
			hash= CryptoUtil.asHex(CryptoUtil.hashSHA1(this.tempFile));
			
			path= getNASManager().uploadFile(this.application, this.year, this.tempFile);
			
			this.innerDocument.setFsPath(path);
			this.innerDocument.setHash(hash);

			getDocumentEntityDao().create(innerDocument);
			
			this.newDocument= new Boolean(false);
			this.tempFile.delete();
		}
		catch(Exception ex)
		{
			log.error("Error almacenando documento:",ex);
			
			throw new DocumentBeanException(ex);
		}
	}

	@Override
	protected void handleEndDownloadTransfer() throws Exception {
		if(this.newDocument.booleanValue())
		{
			throw new DocumentBeanException("Only existing documents can be downloaded");
		}

		if(this.inputStream== null)
		{
			throw new DocumentBeanException("Download must be open before downloading next package");
		}
		
		try
		{
			this.inputStream.close();
			getNASManager().cleanTemporaryResources();
			
			this.inputStream= null;
			this.tempFile= null;
		}
		catch(Exception ex)
		{
			throw new DocumentBeanException(ex);
		}
	}

	@Override
	protected DocumentReference handleGetReference() throws Exception {
		return new DocumentReference (innerDocument.getId().toString(), innerDocument.getHash().toString()); 
	}

	@Override
	protected List<Signature> handleGetSigns() throws Exception {
		byte[][] signs= null;
		List signList= null;
		DocSign sign= null;
		File signFile= null;
		FileInputStream stream= null;
		ByteArrayOutputStream streamSalida= null;
		int leido= 0;
		byte[] buffer= new byte[10240];
		Vector signatures = new Vector ();
		
		try
		{
			DocumentEntity document = getDocumentEntityDao().load(innerDocument.getId());
			
			signList= new ArrayList(document.getSigns());
			
			Collections.sort(signList, new BeanComparator("timestamp"));
			Collections.reverse(signList);
			
			for(Iterator it= signList.iterator(); it.hasNext();)
			{
				sign= (DocSign)it.next();
				signFile= getNASManager().retreiveFile(sign.getFsPath());
				stream= new FileInputStream(signFile);

				Object signature = new ObjectInputStream (stream).readObject();

				signatures.add(signature);

				stream.close();
				getNASManager().cleanTemporaryResources();
			}
		} catch (ClassNotFoundException e) {
			throw new NASException (e);
		}
		finally
		{
			if(stream!= null)
			{
				stream.close();
			}
			
			if(streamSalida!= null)
			{
				streamSalida.close();
			}
		}
		
		return signatures;
	}

	@Override
	protected void handleOpenDownloadTransfer() throws Exception {
		try
		{
			if(!this.newDocument.booleanValue())
			{
				//Recuperamos el archivo
				this.tempFile= getNASManager().retreiveFile(this.innerDocument.getFsPath());
				
				this.inputStream= new FileInputStream(this.tempFile);
			}
			else
			{
				throw new DocumentBeanException("Only existing documents can be downloaded");
			}
		}
		catch(Exception ex)
		{
			throw new DocumentBeanException(ex);
		}
	}

	@Override
	protected String handleGetFsPath() throws Exception {
		return this.innerDocument.getFsPath();
	}

	/**
	 * Comprueba si el usuario corresponde a un servicio interno
	 * @return
	 */
	private boolean isInternalService ()
	{
		return Security.isUserInRole("BPM_INTERNAL");
	}
	
	private NASManager getNASManager () throws NASException
	{
		return NASManager.getInstance(getDocumentEntityDao(), getFileSystemDao(), getDocSignDao());
	}

	@Override
	protected void handleCloseDocument() throws Exception {
		innerDocument = null;
		newDocument = null;
		tempFile = null;
		outputStream = null;
		inputStream = null;
		
	}

	@Override
	protected void handleCreateDocument(String mimeType, String externalName,
			String application) throws Exception {
		this.newDocument= new Boolean(true);
		
		this.innerDocument= getDocumentEntityDao().newDocumentEntity();
		this.innerDocument.setMimeType(mimeType);
		this.innerDocument.setExternalName(externalName);
		
		this.application= application;
		this.year= Calendar.getInstance().get(Calendar.YEAR);
	}

	@Override
	protected void handleDeleteDocument(DocumentReference reference)
			throws Exception {
		DocumentEntity result= null;
		boolean roleFound= false;
		
		Long l = Long.decode(reference.getId());
		result = getDocumentEntityDao().load(l);
		
		if(result== null || !result.getHash().equals(reference.getHash()))
		{
			throw new DocumentBeanException("No se encontro un documento con referencia " + reference);
		}
		
		getNASManager().deleteFile(result.getFsPath());
		getDocumentEntityDao().remove(result);
	}
}
