package es.caib.bpm.beans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

import es.caib.bpm.beans.exception.DocumentBeanException;
import es.caib.bpm.nas.DocumentManagerErrorCodes;
import es.caib.bpm.nas.NASManager;
import es.caib.bpm.nas.crypto.CryptoUtil;
import es.caib.bpm.nas.dao.NasModelDAO;
import es.caib.bpm.nas.entity.BpmAccessLog;
import es.caib.bpm.nas.entity.BpmDocument;
import es.caib.bpm.nas.entity.BpmRole;
import es.caib.bpm.nas.exception.NASException;
import es.caib.bpm.vo.DocumentReference;
import es.caib.signatura.api.Signature;

/**
 * @ejb.bean name="Document"
 *           display-name="Name for Document"
 *           description="Description for Document"
 *           jndi-name="ejb/Document"
 *           type="Stateful"
 *           view-type="remote"
 */
public class DocumentBean implements SessionBean {

	/**
	 * 
	 */
	public DocumentBean() 
	{

		Logger.getLogger(DocumentBean.class).debug("Constructor");
	}

	/**
	 * @see javax.ejb.SessionBean#ejbActivate()
	 */
	public void ejbActivate() throws EJBException, RemoteException 
	{

		Logger.getLogger(DocumentBean.class).debug("Activate");
	}

	/**
	 * @see javax.ejb.SessionBean#ejbPassivate()
	 */
	public void ejbPassivate() throws EJBException, RemoteException 
	{

		Logger.getLogger(DocumentBean.class).debug("Passivate");
	}

	/**
	 * @see javax.ejb.SessionBean#ejbRemove()
	 */
	public void ejbRemove() throws EJBException, RemoteException 
	{

		Logger.getLogger(DocumentBean.class).debug("Remove");
	}

	/**
	 * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
	 */
	public void setSessionContext(SessionContext cont) throws EJBException, RemoteException 
	{
		this.context= cont;
	}

	/** 
	 * Construye el EJB para un documento a partir del ID 
	 * 
	 * @param hash
	 */
	public void ejbCreate(DocumentReference reference) throws CreateException
	{
		BpmDocument result= null;
		boolean roleFound= false;
		BpmRole role= null;
		
		result= NasModelDAO.findDocument(reference.getId(), reference.getHash());
		
		if(result== null)
		{
			throw new CreateException("No se encontro un documento con referencia " + reference);
		}
		
		this.newDocument= new Boolean(false);
		
		
		if(!isInternalService()){
			if(result.getRoles().size()> 0 )
			{
				for(Iterator it= result.getRoles().iterator(); it.hasNext();)
				{
					role= (BpmRole)it.next();
					
					if(this.context.isCallerInRole(role.getName().trim()))
					{
						roleFound= true;
					}
				}
				
				if(!roleFound)
				{
					throw new CreateException("El usuario no tiene permisos para acceder al documento.");
				}
			}
		}
		this.innerDocument= result;
		
		this.generateAccessLog(DocumentBean.ACCESS_LOG);
	}
	
	/** 
	 * Construye un documento nuevo a partir del mime type
	 * 
	 *  @param mimeType
	 *  @param externalName
	 *  @param application
	 *  @param year
	 */
	public void ejbCreateNew(String mimeType, String externalName, String application) throws CreateException
	{
		this.newDocument= new Boolean(true);
		
		this.innerDocument= new BpmDocument();
		this.innerDocument.setMimeType(mimeType);
		this.innerDocument.setExternalName(externalName);
		
		this.application= application;
		this.year= Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * Recupera el ID unico del documento.
	 * 
	 * @return el ID.
	 */
	public DocumentReference getReference()
	{
		return new DocumentReference (innerDocument.getId().toString(), innerDocument.getHash().toString()); 
	}
	
	/**
	 * Recupera el MIME Type del documento
	 * 
	 * @return el MIME Type del documento
	 */
	public String getMimeType()
	{
		return this.innerDocument.getMimeType();
	}

	/**
	 * Recupera el nombre externo del documento
	 * 
	 * @return el nombre externo del documento
	 */
	public String getExternalName()
	{
		return this.innerDocument.getExternalName();
	}

	/**
	 * Recupera el hash del documento
	 * 
	 * @return el hash del documento
	 */
	public String getHash()
	{
		return this.innerDocument.getHash();
	}
	
	/**
	 * Recupera el path completo de la ubicaci�n f�sica del documento en el file system
	 * 
	 * @return el path completo de la ubicaci�n f�sica del documento en el file system
	 */
	public String getFsPath()
	{
		return this.innerDocument.getFsPath();
	}
	
	
	/**
	 * Recupera los roles que tienen permiso para accesder al documento
	 * 
	 * @return los roles que tienen permiso para acceder al documento
	 */
	public Set getRoles()
	{
		HashSet roles = new HashSet();
		for (Iterator it = innerDocument.getRoles().iterator(); it.hasNext();)
		{
			BpmRole role = (BpmRole) it.next();
			roles.add(role.getName());
		}
		return roles;
	}
	
	/**
	 * Permite agregar un rol de acceso al documento.
	 * 
	 * @param role
	 * @throws DocumentBeanException si el rol no existe
	 */
	public void addRole(String roleName) throws DocumentBeanException
	{
		BpmRole rol= NasModelDAO.findRoleByName(roleName);
		
		Logger.getLogger(DocumentBean.class).debug("Rol Recuperado");
		
		if(rol== null)
		{
			throw new DocumentBeanException("El rol no existe.", DocumentManagerErrorCodes.ROL_INEXISTENTE);
		}

		this.innerDocument= (BpmDocument)NasModelDAO.get(this.innerDocument.getClass(), this.innerDocument.getId());
		
		if(this.innerDocument.getRoles()== null)
		{
			this.innerDocument.setRoles(new HashSet());
		}
		
		if(!this.innerDocument.getRoles().contains(rol))
		{
			this.innerDocument.getRoles().add(rol);
			NasModelDAO.saveOrUpdate(this.innerDocument);
		}
	}
	
	/**
	 * Permite remover un rol de acceso del documento.
	 * 
	 * @param role
	 * @throws DocumentBeanException Si el rol no existe.
	 */
	public void removeRole(String roleName) throws DocumentBeanException
	{
		BpmRole rol= NasModelDAO.findRoleByName(roleName);
		
		log.debug("Estamos por remover el rol");
		
		if(rol== null)
		{
			throw new DocumentBeanException("El rol no existe.", DocumentManagerErrorCodes.ROL_INEXISTENTE);
		}
		
		if(this.innerDocument.getRoles().contains(rol))
		{
			log.debug("Borramos el Rol y Guardamos los cambios.");
			this.innerDocument.getRoles().remove(rol);
			NasModelDAO.saveOrUpdate(this.innerDocument);
		}
	}
	
	/**
	 * Comienza la transferencia del archivo asociado documento.
	 * Se utiliza en la creacion de documentos.
	 *  
	 * @throws DocumentBeanException si no se trata de un nuevo documento. 
	 */
	public void openUploadTransfer() throws DocumentBeanException
	{
		try
		{
			if(this.newDocument.booleanValue())
			{
				this.tempFile= DocumentBean.getTempFile();
				
				this.outputStream= new FileOutputStream(this.tempFile);
			}
			else
			{
				throw new DocumentBeanException("Este m�todo es solo para utilizar en documentos nuevos.", DocumentManagerErrorCodes.METODO_NO_DISPONIBLE);
			}
		}
		catch(Exception ex)
		{
			throw new DocumentBeanException(ex, DocumentManagerErrorCodes.EXCEPCION_NO_TIPIFICADA);
		}
	}

	/**
	 * Comienza la transferencia del archivo asociado documento.
	 * Se utiliza con documentos ya creados.
	 *  
	 * @throws DocumentBeanException si no se trata de un nuevo documento. 
	 */
	public void openDownloadTransfer() throws DocumentBeanException
	{
		try
		{
			if(!this.newDocument.booleanValue())
			{
				//Recuperamos el archivo
				this.tempFile= NASManager.getInstance().retreiveFile(this.innerDocument.getFsPath());
				
				this.inputStream= new FileInputStream(this.tempFile);
			}
			else
			{
				throw new DocumentBeanException("Este m�todo es solo para utilizar en documentos existentes.", DocumentManagerErrorCodes.METODO_NO_DISPONIBLE);
			}
		}
		catch(Exception ex)
		{
			throw new DocumentBeanException(ex, DocumentManagerErrorCodes.EXCEPCION_NO_TIPIFICADA);
		}
	}
	
	/**
	 * Envia el proximo paquete del archivo.
	 * Los paquetes se envian de forma secuencial.
	 * 
	 * @param filePackage el arreglo de bytes con la informacion.
	 * @param length la longitud del paquete.
	 * @throws DocumentBeanException 
	 */
	public void nextUploadPackage(byte[] filePackage, int length) throws DocumentBeanException
	{
		if(!this.newDocument.booleanValue())
		{
			throw new DocumentBeanException("Este m�todo es solo para utilizar en documentos nuevos.", DocumentManagerErrorCodes.METODO_NO_DISPONIBLE);
		}

		if(this.outputStream== null)
		{
			throw new DocumentBeanException("Debe llamar primero al metodo openUploadTransfer", DocumentManagerErrorCodes.METODO_NO_DISPONIBLE);
		}

		try
		{
			this.outputStream.write(filePackage, 0, length);
		}
		catch(Exception ex)
		{
			throw new DocumentBeanException(ex, DocumentManagerErrorCodes.EXCEPCION_NO_TIPIFICADA);
		}
	}

	/**
	 * Recupera el proximo paquete del archivo.
	 * Los paquetes se envian de forma secuencial.
	 * 
	 * @param filePackage el arreglo de bytes con la informacion.
	 * @param length la longitud del paquete.
	 * @throws DocumentBeanException 
	 */
	public byte[] nextDownloadPackage(int length) throws DocumentBeanException
	{
		int leidos= 0;
		ByteArrayOutputStream outputStream= null;
		byte[] filePackage= null;
		
		if(this.newDocument.booleanValue())
		{
			throw new DocumentBeanException("Este m�todo es solo para utilizar en documentos existentes.", DocumentManagerErrorCodes.METODO_NO_DISPONIBLE);
		}

		if(this.inputStream== null)
		{
			throw new DocumentBeanException("Debe llamar primero al metodo openDownloadTransfer", DocumentManagerErrorCodes.METODO_NO_DISPONIBLE);
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
			throw new DocumentBeanException(ex, DocumentManagerErrorCodes.EXCEPCION_NO_TIPIFICADA);
		}
		
		return outputStream.toByteArray();
	}

	/**
	 * Finaliza la transferencia del archivo y persiste el documento en el NAS y finalmente en la base de datos.
	 * 
	 * @throws DocumentBeanException
	 */
	public synchronized void endUploadTransfer() throws DocumentBeanException
	{
		String path= null;
		String hash = null;
		if(!this.newDocument.booleanValue())
		{
			throw new DocumentBeanException("Este m�todo es solo para utilizar en documentos nuevos.", DocumentManagerErrorCodes.METODO_NO_DISPONIBLE);
		}

		if(this.outputStream== null)
		{
			throw new DocumentBeanException("Debe llamar primero al metodo openUploadTransfer", DocumentManagerErrorCodes.METODO_NO_DISPONIBLE);
		}
		
		try
		{
			this.outputStream.close();
			
			hash= CryptoUtil.asHex(CryptoUtil.hashSHA1(this.tempFile));
			
			path= NASManager.getInstance().uploadFile(this.application, this.year, this.tempFile);
			
			this.innerDocument.setFsPath(path);
			this.innerDocument.setHash(hash);

			NasModelDAO.saveOrUpdate(this.innerDocument);
			
			this.generateAccessLog(DocumentBean.UPLOAD_LOG);
			
			this.newDocument= new Boolean(false);
			this.tempFile.delete();
		}
		catch(Exception ex)
		{
			log.error("Error almacenando documento:",ex);
			
			throw new DocumentBeanException(ex,DocumentManagerErrorCodes.EXCEPCION_NO_TIPIFICADA);
		}
	}

	/**
	 * Finaliza la transferencia del archivo y limpia los temporarios.
	 * 
	 * @throws DocumentBeanException
	 */
	public synchronized void endDownloadTransfer() throws DocumentBeanException
	{
		if(this.newDocument.booleanValue())
		{
			throw new DocumentBeanException("Este m�todo es solo para utilizar en documentos existentes.", DocumentManagerErrorCodes.METODO_NO_DISPONIBLE);
		}

		if(this.inputStream== null)
		{
			throw new DocumentBeanException("Debe llamar primero al metodo openDownloadTransfer", DocumentManagerErrorCodes.METODO_NO_DISPONIBLE);
		}
		
		try
		{
			this.inputStream.close();
			NASManager.getInstance().cleanTemporaryResources();
			
			this.generateAccessLog(DocumentBean.DOWNLOAD_LOG);
			
			this.inputStream= null;
			this.tempFile= null;
		}
		catch(Exception ex)
		{
			throw new DocumentBeanException(ex, DocumentManagerErrorCodes.EXCEPCION_NO_TIPIFICADA);
		}
	}

	/**
	 * Recupera las firmas del documento.
	 * 
	 * @return las firmas.
	 * @throws IOException 
	 * @throws NASException 
	 */
	public List getSigns() throws NASException, IOException
	{
		return NasModelDAO.getSignsForDocument(this.innerDocument);
	}
	
	/**
	 * Agrega la firma al documento.
	 *
	 * @param firma la firma.
	 */
	public void addSign(Signature firma) throws NASException, IOException
	{
		NasModelDAO.addSignForDocument(firma, innerDocument);
	}
	
	/**
	 * Realiza la verificacion de la firma.
	 * 
	 * @param firma
	 * @throws NASException
	 * @throws RemoteException
	 * 
	 * @return si la firma es valida para el documento.
	 */
	public boolean verifySign(Signature firma) throws DocumentBeanException, RemoteException
	{
		boolean valido= false;
		
		if(this.newDocument.booleanValue())
		{
			throw new DocumentBeanException("Este método es solo para utilizar en documentos existentes.", DocumentManagerErrorCodes.METODO_NO_DISPONIBLE);
		}

		try
		{
			valido= NASManager.getInstance().validateSign(this.innerDocument, firma);
	
		}
		catch(NASException ex)
		{
			throw new DocumentBeanException(DocumentManagerErrorCodes.ARCHIVO_FIRMA_INVALIDO);
		}
		
		return valido;
	}

	/**
	 * Recupera los logs de acceso al documento a partir del tipo, null trae todos.
	 * 
	 * @param logType el tipo puede ser DOWNLOAD_LOG, ACCESS_LOG, UPLOAD_LOG.
	 * @return el listado de logs de acceso.
	 */
	public List getAccessLog(String logType) 
	{
		return NasModelDAO.findAccessLog(this.innerDocument, logType);
	}
	
	
	/**
	 * Recupera un archivo temporario unico.
	 * 
	 * @return
	 */
	private static synchronized File getTempFile() throws NASException 
	{ 
		try{
			return NASManager.getInstance().getTempFile();
		}catch(NASException nasException){
			throw nasException;
		}
	}
	
	/**
	 * Genera un log de acceso en la base de datos para el documento.
	 * 
	 * @param logType el tipo de log (DOWNLOAD_LOG; UPLOAD_LOG; ACCESS_LOG)
	 */
	private void generateAccessLog(String logType)
	{
		BpmAccessLog accessLog= null;
		
		accessLog= new BpmAccessLog();
		
		Logger.getLogger(DocumentBean.class).debug("Docuemnt ID: " + this.innerDocument.getId());
		accessLog.setBpmDocument(this.innerDocument);
		
		if(logType.equals(DocumentBean.DOWNLOAD_LOG))
		{
			accessLog.setType(new Character('D'));
		}
		else if(logType.equals(DocumentBean.UPLOAD_LOG))
		{
			accessLog.setType(new Character('U'));
		}
		else if(logType.equals(DocumentBean.ACCESS_LOG))
		{
			accessLog.setType(new Character('A'));
		}
		
		accessLog.setDate(new Date());
		accessLog.setUser(this.context.getCallerPrincipal().getName());
		
		NasModelDAO.saveOrUpdate(accessLog);
	}

	/**
	 * Comprueba si el usuario corresponde a un servicio interno
	 * @return
	 */
	private boolean isInternalService ()
	{
		return context.isCallerInRole("BPM_INTERNAL");
	}
	
	
	/** El documento interno */
	private BpmDocument innerDocument= null;
	/** Indica si es un nuevo documento */
	private Boolean newDocument= null;
	/** El contexto de sesion */
	private SessionContext context= null;
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
	private static Logger log= Logger.getLogger(DocumentBean.class);
	public static String DOWNLOAD_LOG= "DOWNLOAD_LOG";
	public static String ACCESS_LOG= "ACCESS_LOG";
	public static String UPLOAD_LOG= "UPLOAD_LOG";
}
