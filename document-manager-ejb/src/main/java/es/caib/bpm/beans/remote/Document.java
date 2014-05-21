package es.caib.bpm.beans.remote;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import javax.ejb.EJBObject;

import es.caib.bpm.beans.exception.DocumentBeanException;
import es.caib.bpm.nas.exception.NASException;
import es.caib.bpm.vo.DocumentReference;
import es.caib.signatura.api.Signature;

public interface Document extends EJBObject 
{
	public String getMimeType() throws RemoteException;

	public DocumentReference getReference() throws RemoteException;
	
	public String getExternalName() throws RemoteException; 

	public String getFsPath() throws RemoteException;
		
	public Set getRoles() throws RemoteException;
	
	public List getSigns() throws NASException, IOException, RemoteException;
	
	public void addSign(Signature firma) throws NASException, IOException, RemoteException;
	
	public void addRole(String role) throws DocumentBeanException, RemoteException;
	
	public void removeRole(String role) throws DocumentBeanException, RemoteException;
	
	public void openUploadTransfer() throws DocumentBeanException, RemoteException;
	
	public void openDownloadTransfer() throws DocumentBeanException, RemoteException;
	
	public void nextUploadPackage(byte[] filePackage, int length) throws DocumentBeanException, RemoteException;
	
	public byte[] nextDownloadPackage(int length) throws DocumentBeanException, RemoteException;
	
	public void endUploadTransfer() throws DocumentBeanException, RemoteException;
	
	public void endDownloadTransfer() throws DocumentBeanException, RemoteException;
	
	public List getAccessLog(String logType) throws RemoteException;
}
