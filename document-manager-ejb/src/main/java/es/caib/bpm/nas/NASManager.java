package es.caib.bpm.nas;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.caib.bpm.nas.comm.CIFSStrategy;
import es.caib.bpm.nas.comm.FTPStrategy;
import es.caib.bpm.nas.dao.NasModelDAO;
import es.caib.bpm.nas.entity.BpmDocument;
import es.caib.bpm.nas.exception.NASException;
import es.caib.signatura.api.Signature;

/**
 * Clase que aporta la funcionalidad para el manejo del NAS
 * 
 * @author Sebasti�n Carnota
 */
public class NASManager
{
	
	/*
	DECLARACIONES
	*/
	// CAMPOS DE INSTANCIA
		
	/**La instancia del NASManager*/
	private static NASManager instance = null;
	/**La estrategia de comunicacion con el NAS */
	private CommunicationStrategy comStrategy = null;
	/**La estrategia de firmas */
	private SignerStrategy signStrategy = null;
	private static Logger log = Logger.getLogger(NASManager.class);
	private File tempPath;
	
	/*
	 * METODOS PUBLICOS
	 */
	/**
	 * Devuelve la instancia del NASManager instanci�ndola en caso de ser necesario
	 * 
	 * @return la instancia del NASManagerion o configurar correctamente el manager.
	 */
	public static synchronized NASManager getInstance() throws NASException
	{
		try
		{
			if (NASManager.instance == null)
			{
				NASManager.instance = new NASManager();
				
				loadConfiguration();
				
				log.debug(String.format("NAS Manager started with strategy: %1$s", //$NON-NLS-1$
					new Object[]{System.getProperty("soffid.ui.docStrategy")})); //$NON-NLS-1$
			}
			
		}
		catch(Exception ex)
		{
			throw new NASException(ex);
		}
		
		//retornamos
		return NASManager.instance;
	}
	
	/**
	 * Method that implements the NAS configuration.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NASException 
	 */
	public static void loadConfiguration() throws InstantiationException,
		IllegalAccessException, ClassNotFoundException, NASException
	{
		// Instance of communication strategy
		instance.comStrategy = (CommunicationStrategy) Class.forName(
				System.getProperty("soffid.ui.docStrategy")).newInstance(); //$NON-NLS-1$
		instance.signStrategy= (SignerStrategy) Class.forName(
				"es.caib.bpm.nas.sign.CAIBStrategy").newInstance(); //$NON-NLS-1$
		
		instance.tempPath =
			new File(System.getProperty("soffid.ui.docTempPath"));
		instance.tempPath.mkdirs();
		
		instance.comStrategy.setProperties(System.getProperties());
	}

	public File getTempFile() throws NASException
	{
		File tempFile = null;
		
		do
		{
			try
			{
				tempFile = new File(this.tempPath,
						new Long(new Date().getTime()).toString());
			}
			
			catch(Throwable throwable)
			{
				throw new NASException(
					String.format("Unable to create temporary file in '%1$s'", //$NON-NLS-1$
						new Object[]{tempPath}));
			}
		} while (tempFile.exists());

		return tempFile;
	}
	
	/**
	 * Genera el path para el n�mero de fichero especificado
	 * 
	 * @param number el n�mero de fichero
	 * @return el path para el n�mero de fichero especificado
	 */
	public String getPathForNumber(long number)
	{
		long nDiv= number;
		long nMod= 0;
		StringBuffer path= new StringBuffer();
		
		while(nDiv > 0)
		{
			nMod= (nDiv -1) % 100;
			nDiv= (nDiv - 1) / 100; 
			
			path.insert(0, nMod);
			path.insert(0, '/');
		}
		
		return path.toString() + ".sto"; //$NON-NLS-1$
	}
	
	/**
	 * Sube un nuevo archivo al servicio de custodia de documentos.
	 * 
	 * @param application la aplicacion a la cual pertenece el documento
	 * @param year el a�o
	 * @param file el archivo a subir
	 * @return el path del archivo subido
	 * @throws NASException 
	 */
	public synchronized String uploadFile(String application, int year, File file) throws NASException
	{
		long number= 0L;
		String path= null;
		
		number= NasModelDAO.nextNumberFor(application, year);
		
		path= "/" + application + "/" + year + this.getPathForNumber(number); //$NON-NLS-1$ //$NON-NLS-2$

		this.comStrategy.uploadFile(file, path);
		
		return path;
	}
	
	/**
	 * Recupera un archivo del NAS a partir del path.
	 * 
	 * @param path
	 * @return
	 * @throws NASException 
	 */
	public File retreiveFile(String path) throws NASException
	{
		return this.comStrategy.retreiveFile(path);
	}
	
	/**
	 * Limpia los recursos temporarios.
	 *
	 */
	public void cleanTemporaryResources() throws NASException
	{
		this.comStrategy.cleanTemporaryResources();
	}
	
	/**
	 * Realiza la validacion del documento.
	 * 
	 * @param document el documento.
	 * @param sign la firma.
	 * @return true si el documento es valido.
	 * @throws NASException 
	 */
	public boolean validateSign(BpmDocument document, Signature sign) throws NASException
	{
		return this.signStrategy.validateSign(document, sign) != null;
	}
	
	/**
	 * Recupera la estrategia de firma.
	 * 
	 * @return la estrategia de firma.
	 */
	public SignerStrategy getSignStrategy() 
	{
		return signStrategy;
	}

	/*
METODOS PRIVADOS
*/
	/**
	 * Constructor
	 */
	private NASManager()
	{

	}
}
