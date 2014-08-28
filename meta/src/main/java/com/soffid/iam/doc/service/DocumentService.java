package com.soffid.iam.doc.service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import roles.Tothom;
import roles.anonymous;

import com.soffid.iam.doc.api.DocumentReference;
import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.model.DocSign;
import com.soffid.iam.doc.model.DocumentEntity;
import com.soffid.iam.doc.model.FileSystem;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Service;

import es.caib.signatura.api.Signature;

@Depends({DocumentEntity.class, FileSystem.class, DocSign.class})
@Service(grantees={Tothom.class}, stateful=true, internal=false)
public class DocumentService  
{
	public void openDocument(DocumentReference reference) {}
	
	public void createDocument(String mimeType, String externalName, String application) {}
	
	public void closeDocument () {};
	
	public String getMimeType() {return null;}

	public com.soffid.iam.doc.api.DocumentReference getReference() {return null;}
	
	public String getExternalName() {return null;}

	public String getFsPath() {return null;}
		
	public List<Signature> getSigns()  throws NASException {return null;};
	
	public void addSign(Signature firma)  throws NASException {};
	
	public void openUploadTransfer() throws DocumentBeanException {};
	
	public void openDownloadTransfer() throws DocumentBeanException {};
	
	public void nextUploadPackage(byte[] filePackage, int length)  throws DocumentBeanException {};
	
	public byte[] nextDownloadPackage(int length) throws DocumentBeanException {return null;};
	
	public void endUploadTransfer() throws DocumentBeanException {};
	
	public void endDownloadTransfer() throws DocumentBeanException {};
	
}
