package com.soffid.iam.doc.nas.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.logging.LogFactory;

import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.nas.CommunicationStrategy;
import com.soffid.iam.utils.ConfigurationCache;

import jcifs.Config;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class CIFSStrategy implements CommunicationStrategy
{
	private SmbFile rootPath = null;
	private File tempPath = null;
	private List filesToClean = null;
	
	public CIFSStrategy()
	{
		this.filesToClean = new Vector();
	}

	/**
	 * @see com.soffid.iam.doc.nas.CommunicationStrategy#retreiveFile(java.lang.String)
	 */
	public File retreiveFile(String path) throws NASException 
	{
		SmbFile archivo= null;
		SmbFileInputStream stream= null;
		FileOutputStream streamSalida= null;
		File archivoSalida= null;
		int leidos= 0;
		byte[] buffer= new byte[10240];
		String nombreArchivo= null;
		
		try
		{
			archivo= new SmbFile(this.rootPath + path.substring(path.indexOf("/") + 1));
			
			stream= new SmbFileInputStream(archivo);
			
			nombreArchivo= path.replace('/', '_');
			
			archivoSalida= new File(this.tempPath, nombreArchivo + ".temp");
			streamSalida= new FileOutputStream(archivoSalida);
			
			while((leidos= stream.read(buffer))!= -1)
			{
				streamSalida.write(buffer, 0, leidos);
			}
			
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
				if(stream!= null)
				{
					stream.close();
				}
				
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
	public void setProperties() throws NASException 
	{
		try 
		{
			Config.setProperty("jcifs.smb.client.username",
					ConfigurationCache.getMasterProperty("soffid.ui.docUsername").trim()); //$NON-NLS-1$
			Config.setProperty("jcifs.smb.client.password",
					ConfigurationCache.getMasterProperty("soffid.ui.docUserPassword").trim()); //$NON-NLS-1$
			Config.setProperty("jcifs.smb.client.domain",
					ConfigurationCache.getMasterProperty("soffid.ui.docDomain").trim());
			
			this.rootPath= new SmbFile(ConfigurationCache.getMasterProperty("soffid.ui.docPath").toString());
			
			this.tempPath= new File(ConfigurationCache.getMasterProperty("soffid.ui.docTempPath").toString());
			
			LogFactory.getLog(CIFSStrategy.class).info("Carpetas establecidas.");
		} 
		catch (Exception ex) 
		{
			throw new NASException(ex);
		}
	}

	/**
	 * @see com.soffid.iam.doc.nas.CommunicationStrategy#uploadFile(java.io.File, java.lang.String)
	 */
	public void uploadFile(File archivo, String path) throws NASException 
	{
		FileInputStream stream= null;
		SmbFile salida= null;
		SmbFileOutputStream streamSalida= null;
		byte[] buffer= new byte[10240];
		int leidos= 0;
		String[] pathTokenized= null;
		SmbFile folder= null;
		SmbFile folderNuevo= null;
		
		try
		{
			stream= new FileInputStream(archivo);
			
			pathTokenized= path.split("/");
			
			if(pathTokenized.length > 1)
			{
				folder= new SmbFile(this.rootPath, pathTokenized[0]);
				
				if(!folder.exists())
				{
					folder.mkdir();
				}
				
				for(int index= 1; index< pathTokenized.length - 1; index++)
				{
					folderNuevo= new SmbFile(folder, pathTokenized[index]);
					
					if(!folderNuevo.exists())
					{
						folderNuevo.mkdir();
					}

					folder= new SmbFile(folderNuevo.getPath() + "/");
				}
			}

			salida= new SmbFile(folder, pathTokenized[pathTokenized.length - 1]);

			if(!salida.exists())
			{
				streamSalida= new SmbFileOutputStream(salida);
				
				while((leidos= stream.read(buffer))!= -1)
				{
					streamSalida.write(buffer, 0, leidos);
				}
			}
		}
		catch(Exception ex)
		{
			throw new NASException(ex);
		}
		finally
		{
			try
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
			catch(Exception ex)
			{
				throw new NASException(ex);
			}
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

	public void deleteFile(String path) throws NASException {
		SmbFile archivo= null;
		
		try
		{
			archivo= new SmbFile(this.rootPath + path.substring(path.indexOf("/") + 1));
			
			archivo.delete();
		}
		catch(Exception ex)
		{
			throw new NASException(ex);
		}
		finally
		{
		}
	}
}
