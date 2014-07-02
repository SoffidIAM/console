package com.soffid.iam.doc.nas.comm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.jwput.JWGet;
import org.w3c.jwput.JWOptions;
import org.w3c.jwput.JWPut;

import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.nas.CommunicationStrategy;


public class HTTPStrategy implements CommunicationStrategy 
{
	/** La URL del Server terminando con / */
	private String rootURL= null;
	/** El path de archivos temporarios */
	private String tempPath= null;
	/** El listado de archivos a limpiar */
	private List filesToClean= null;
	/** El getter de archivos */
	private JWGet fileGetter= null;
	/** El putter de archivos */
	private JWPut filePutter= null;
	
	Log log = LogFactory.getLog(getClass());
	
	/**
	 * @see com.soffid.iam.doc.nas.CommunicationStrategy#retreiveFile(java.lang.String)
	 */
	public File retreiveFile(String path) throws NASException 
	{
		FileOutputStream streamSalida= null;
		File archivoSalida= null;
		String nombreArchivo= null;
		String urlPath= null;
		
		try
		{
			nombreArchivo= path.replace('/', '_');

			urlPath= this.rootURL + path.substring(1);
			
			archivoSalida= new File(this.tempPath, nombreArchivo + ".temp");
			
			if(archivoSalida== null)
			{
				throw new NASException("No se encontro el archivo con el path: " + path);
			}
			
			this.fileGetter.get(archivoSalida, urlPath, true);
			
			this.filesToClean.add(archivoSalida);
		}
		catch(Exception ex)
		{
			throw new NASException(ex);
		}
		finally
		{
			try 
			{
				if(streamSalida!= null)
				{
					streamSalida.close();
				}
			} 
			catch (IOException ex) 
			{
				throw new NASException(ex);
			}
		}
		
		return archivoSalida;
	}

	/**
	 * @see com.soffid.iam.doc.nas.CommunicationStrategy#setProperties(java.util.Properties)
	 */
	public void setProperties(Properties properties) throws NASException 
	{
		this.rootURL= properties.getProperty("soffid.ui.docServer");
		this.tempPath= properties.getProperty("soffid.ui.docTempPath");
		
		properties.put(JWOptions.QUIET_P, JWOptions.TRUE_P);
		
		this.fileGetter= new JWGet(properties);
		this.filePutter= new JWPut("application/octet-stream", properties);
		
		this.filesToClean= new ArrayList();
	}

	/**
	 * @see com.soffid.iam.doc.nas.CommunicationStrategy#uploadFile(java.io.File, java.lang.String)
	 */
	public void uploadFile(File archivo, String path) throws NASException 
	{
		String urlPath= null;
		urlPath= this.rootURL + "/" + path.substring(1);
		File nuevoArchivo= null;
		
		nuevoArchivo= new File(archivo.getParent(), path.substring(path.lastIndexOf('/') + 1));
		
		log.debug("Nuevo Archivo: " + nuevoArchivo.getAbsolutePath());
		archivo.renameTo(nuevoArchivo);
		
		try
		{
			this.filePutter.putFile(nuevoArchivo, urlPath);
		}
		catch(Exception ex)
		{
			throw new NASException(ex);
		}
	}

	/**
	 * @see com.soffid.iam.doc.nas.CommunicationStrategy#cleanTemporaryResources()
	 */
	public void cleanTemporaryResources() throws NASException 
	{
		File archivo= null;
		
		for(Iterator it= this.filesToClean.iterator(); it.hasNext();)
		{
			archivo= (File)it.next();
			
			archivo.delete();
		}
	}
}
