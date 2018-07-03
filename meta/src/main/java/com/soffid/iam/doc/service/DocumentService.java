package com.soffid.iam.doc.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import roles.Tothom;
import roles.anonymous;
import roles.parameter_update;

import com.soffid.iam.doc.api.DocumentReference;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.model.DocSign;
import com.soffid.iam.doc.model.DocumentEntity;
import com.soffid.iam.doc.model.FileSystem;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.signatura.api.Signature;

@Depends({DocumentEntity.class, FileSystem.class, DocSign.class})
@Service(grantees={Tothom.class}, stateful=true, internal=false)
public class DocumentService  
{
	public void openDocument(DocumentReference reference) {}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void createDocument(String mimeType, String externalName, String application) {}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void closeDocument () {};
	
	public String getMimeType() {return null;}

	public com.soffid.iam.doc.api.DocumentReference getReference() {return null;}
	
	public String getExternalName() {return null;}

	public String getFsPath() {return null;}
		
	public List<Signature> getSigns()  throws NASException {return null;};
	
	public void addSign(Signature firma)  throws NASException {};
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void openUploadTransfer() throws DocumentBeanException {};
	
	@Transactional(readOnly=true)
	public void openDownloadTransfer() throws DocumentBeanException {};
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void nextUploadPackage(byte[] filePackage, int length)  throws DocumentBeanException {};
	
	@Transactional(readOnly=true)
	public byte[] nextDownloadPackage(int length) throws DocumentBeanException {return null;};
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void endUploadTransfer() throws DocumentBeanException {};
	
	@Transactional(readOnly=true)
	public void endDownloadTransfer() throws DocumentBeanException {};
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, noRollbackFor={InternalErrorException.class, DocumentBeanException.class})
	public void deleteDocument(DocumentReference reference) {}
	
	@Transactional(propagation=Propagation.NEVER, readOnly=true)
	@Operation(grantees={ parameter_update.class})
	public void exportDocuments (OutputStream out) {}
	
	@Transactional(propagation=Propagation.NEVER)
	@Operation(grantees={ parameter_update.class})
	public void importDocuments(InputStream out) {}
	
}
