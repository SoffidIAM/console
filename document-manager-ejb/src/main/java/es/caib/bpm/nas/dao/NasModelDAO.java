package es.caib.bpm.nas.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;

import es.caib.bpm.beans.DocumentBean;
import es.caib.bpm.nas.NASManager;
import es.caib.bpm.nas.SignerStrategy;
import es.caib.bpm.nas.comm.FTPStrategy;
import es.caib.bpm.nas.entity.BpmAccessLog;
import es.caib.bpm.nas.entity.BpmDocument;
import es.caib.bpm.nas.entity.BpmFileSystem;
import es.caib.bpm.nas.entity.BpmRole;
import es.caib.bpm.nas.entity.BpmSign;
import es.caib.bpm.nas.exception.NASException;
import es.caib.bpm.nas.hibernate.HibernateUtil;
import es.caib.signatura.api.SignatureTimestampException;

public class NasModelDAO {
	/**
	 * Recupera los logs de acceso al documento a partir del tipo, null trae todos.
	 * 
	 * @param document el documento.
	 * @param logType el tipo puede ser DOWNLOAD_LOG, ACCESS_LOG, UPLOAD_LOG.
	 * @return el listado de logs de acceso.
	 */
	public static List findAccessLog(BpmDocument document, String logType)
	{
		List resultadoBusqueda= null;
		Criteria criteria= null;
		
		try
		{
			//Creamos el criterio de busqueda
			criteria= HibernateUtil.currentSession().createCriteria(BpmAccessLog.class);
	
			document= (BpmDocument)NasModelDAO.get(BpmDocument.class, document.getId());
			
			criteria.add(Restrictions.eq("bpmDocument", document));
			
			if(logType!= null)
			{
				if(logType.equals(DocumentBean.DOWNLOAD_LOG))
				{
					criteria.add(Restrictions.eq("type", new Character('D')));
				}
				else if(logType.equals(DocumentBean.UPLOAD_LOG))
				{
					criteria.add(Restrictions.eq("type", new Character('U')));
				}
				else if(logType.equals(DocumentBean.ACCESS_LOG))
				{
					criteria.add(Restrictions.eq("type", new Character('A')));
				}
			}
	
			resultadoBusqueda= criteria.list();
		}
		finally
		{
			HibernateUtil.closeSession();
		}
		
		return resultadoBusqueda;
	}
	
	/**
	 * Recupera un objeto en la sesion.
	 * 
	 * @param object
	 * @return
	 */
	public static Object get(Class clase, Serializable id)
	{
		return HibernateUtil.currentSession().get(clase, id);
	}
	
	/**
	 * Obtiene el proximo n�mero de documento para una aplicacion y a�o.
	 * Si el par aplicacion/a�o no existe, lo crea.
	 * 
	 * @param application
	 * @param year
	 * @return
	 */
	public static synchronized long nextNumberFor (String application, int year)
	{
		Criteria criteria= null;
		BpmFileSystem fileSystem= null;
		
		try
		{
			//Creamos el criterio de busqueda
			criteria= HibernateUtil.currentSession().createCriteria(BpmFileSystem.class);

			criteria.add(Restrictions.eq("application", application));
			criteria.add(Restrictions.eq("year", new Integer(year)));
			criteria.setLockMode(LockMode.UPGRADE);
			fileSystem= (BpmFileSystem)criteria.uniqueResult();
			
			if(fileSystem!= null)
			{
//				System.out.println("Estoy Salvando el file System");

				long newValue = fileSystem.getNextDocNumber().longValue() + 1; 
				fileSystem.setNextDocNumber(new Long(newValue));
				
				HibernateUtil.currentSession().saveOrUpdate(fileSystem);
				HibernateUtil.currentSession().flush();
				return newValue;
			}
			else
			{
				fileSystem= new BpmFileSystem();
				
				fileSystem.setApplication(application);
				fileSystem.setYear(new Integer(year));
				fileSystem.setNextDocNumber(new Long(1));
				
				HibernateUtil.currentSession().save(fileSystem);
				HibernateUtil.currentSession().flush();
				return 1;
			}
		}
		finally
		{
			HibernateUtil.closeSession();
		}
	}

	/**
	 * Realiza la busqueda del documento a partir del hash.
	 * 
	 * @param hash
	 * @return el BpmDocument
	 */
	public static BpmDocument findDocument(String id, String hash) 
	{
		Criteria criteria= null;
		BpmDocument documento= null;
		
		try
		{
			criteria= HibernateUtil.currentSession().createCriteria(BpmDocument.class);
			
			criteria.add(Restrictions.eq("id", new Long(id)));
			criteria.add(Restrictions.eq("hash", hash));
			
			documento= (BpmDocument)criteria.uniqueResult();
			
			if(documento!= null && !Hibernate.isInitialized(documento.getSigns()))
			{
				Hibernate.initialize(documento.getSigns());
			}

			if(documento!= null && !Hibernate.isInitialized(documento.getRoles()))
			{
				Hibernate.initialize(documento.getRoles());
			}
		}
		finally
		{
			HibernateUtil.closeSession();
		}
		
		return documento;
	}
	
	/**
	 * Realiza la busqueda de la firma a partir del hash.
	 * 
	 * @param hash
	 * @return el BpmSign
	 */
	public static BpmSign findSign(String hash) 
	{
		Criteria criteria= null;
		BpmSign firma= null;
		
		try
		{
			criteria= HibernateUtil.currentSession().createCriteria(BpmSign.class);
			
			criteria.add(Restrictions.eq("hash", hash));
			
			firma= (BpmSign)criteria.uniqueResult();
		}
		finally
		{
			HibernateUtil.closeSession();
		}
		
		return firma;
	}

	/**
	 * Busca un rol por su nombre.
	 * 
	 * @param nombre el nombre del rol
	 * @return
	 */
	public static BpmRole findRoleByName(String nombre)
	{
		BpmRole rol= null;

		try
		{
			Criteria busqueda= HibernateUtil.currentSession().createCriteria(BpmRole.class);
			
			busqueda.add(Restrictions.eq("name", nombre));
			
			rol= (BpmRole)busqueda.uniqueResult();
		}
		finally
		{
			HibernateUtil.closeSession();
		}
		
		return rol;
	}
	/**
	 * Crea o actualiza la entidad.
	 * 
	 * @param entity
	 */
	public static void saveOrUpdate(Serializable entity)
	{
		try
		{
			HibernateUtil.currentSession().saveOrUpdate(entity);
			HibernateUtil.currentSession().flush();
		}
		finally
		{
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Recupera el listado de roles existentes cargados en el sistema de custodia de documentos.
	 *  
	 * @return los roles existentes
	 */
	public static List listExistingRoles()
	{
		List resultado= null;
		
		try
		{
			resultado= HibernateUtil.currentSession().createCriteria(BpmRole.class).list();
		}
		finally
		{
			HibernateUtil.closeSession();
		}
		
		return resultado;
	}

	/**
	 * Recupera las firmas del documento.
	 * 
	 * @return las firmas.
	 * @throws NASException 
	 * @throws IOException 
	 */
	public static List getSignsForDocument(BpmDocument document) throws NASException, IOException
	{
		byte[][] signs= null;
		List signList= null;
		BpmSign sign= null;
		File signFile= null;
		FileInputStream stream= null;
		ByteArrayOutputStream streamSalida= null;
		int leido= 0;
		byte[] buffer= new byte[10240];
		Vector signatures = new Vector ();
		
		try
		{
			document= (BpmDocument)HibernateUtil.currentSession().get(BpmDocument.class, document.getId());
			
			signList= new ArrayList(document.getSigns());
			
			Collections.sort(signList, new BeanComparator("timestamp"));
			Collections.reverse(signList);
			
			for(Iterator it= signList.iterator(); it.hasNext();)
			{
				sign= (BpmSign)it.next();
				signFile= NASManager.getInstance().retreiveFile(sign.getFsPath());
				stream= new FileInputStream(signFile);

				Object signature = new ObjectInputStream (stream).readObject();

				signatures.add(signature);

				stream.close();
				NASManager.getInstance().cleanTemporaryResources();
			}
		} catch (ClassNotFoundException e) {
			throw new NASException (e);
		}
		finally
		{
			HibernateUtil.closeSession();

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
	
	/**
	 * Agrega una firma al documento.
	 *
	 * @param signType el tipo de firma.
	 * @param sign el arreglo de bytes de la firma.
	 * @param document el documento al cual se asocia la firma.
	 * @throws NASException Si ocurre al problema de comunicacion con el NAS.
	 * @throws IOException si ocurre algun problema al generar archivos temporarios.
	 */
	public static void addSignForDocument(es.caib.signatura.api.Signature sign, BpmDocument document) throws IOException, NASException
	{
		BpmSign signEntity= null;
		FileOutputStream streamEscritura= null;
		File tempFile= null;
		String path= null;
		SignerStrategy estrategiaFirma= null;

		estrategiaFirma= NASManager.getInstance().getSignStrategy();

		if (estrategiaFirma.validateSign(document, sign) == null)
		{
			throw new NASException("El arreglo de bytes no es una firma válida.");			
		}
		tempFile= NasModelDAO.getTempFile();
		streamEscritura= new FileOutputStream(tempFile);
		
		new ObjectOutputStream(streamEscritura).writeObject(sign);

		streamEscritura.close();
		
		document= (BpmDocument)HibernateUtil.currentSession().get(BpmDocument.class, document.getId());

		signEntity= new BpmSign();
		
		path= NASManager.getInstance().uploadFile(NasModelDAO.getApplication(document.getFsPath()), new Integer(NasModelDAO.getYear(document.getFsPath())).intValue(), tempFile);
		
		tempFile.delete();
		
		signEntity.setBpmDocument(document);
		signEntity.setSignType('O'); // OBJECTE SIGNATURE
		signEntity.setFsPath(path);
		Date d = null;
		try {
			d = sign.getDate();
		} catch (SignatureTimestampException e) {
			Logger.getLogger(NasModelDAO.class).error(e.getMessage(),e);
		}
		if (d == null) d = new Date ();
		signEntity.setTimestamp(d);
		
		NasModelDAO.saveOrUpdate(signEntity);
	}
	
	/**
	 * Recupera la aplicacion a partir del fs path.
	 * 
	 * @param fsPath
	 * @return
	 */
	private static String getApplication(String fsPath)
	{
		return fsPath.split("/")[1];
	}
	
	/**
	 * Recupera el a�o a partir del fs path.
	 * 
	 * @param fsPath
	 * @return
	 */
	private static String getYear(String fsPath)
	{	
		return fsPath.split("/")[2];
	}
	
	/**
	 * Recupera un archivo temporario unico.
	 * 
	 * @return
	 */
	private static synchronized File getTempFile() throws IOException
	{
		File tempFile= null;
		
		tempFile = File.createTempFile("docmgr", ".signature");
		return tempFile;
	}
}
