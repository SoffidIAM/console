package es.caib.bpm.beans.home;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

import es.caib.bpm.beans.remote.Document;
import es.caib.bpm.vo.DocumentReference;
 
public interface DocumentHome extends EJBHome 
{
	public final static String JNDI_NAME = "es.caib.bpm.DocumentEJB";
	
	/** Construye el EJB para un documento a partir del ID */
	public Document create(DocumentReference doc) throws RemoteException, CreateException;
	
	/** Construye un documento nuevo a partir del mime type */
	public Document createNew(String mimeType, String externalName, String application) throws RemoteException, CreateException;
}