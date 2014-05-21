package es.caib.bpm.nas.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.finj.FTPClient;
import org.finj.FTPException;

import jcifs.Config;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import es.caib.bpm.beans.DocumentBean;
import es.caib.bpm.nas.CommunicationStrategy;
import es.caib.bpm.nas.exception.NASException;

public class FTPStrategy implements CommunicationStrategy
{
	private String rootPath= null;
	private File tempPath= null;
	private List filesToClean= null;
	private FTPClient client = null;
	private String server= null;
	private String user= null;
	private String password= null;
	
	public FTPStrategy()
	{
		this.filesToClean= new Vector();
	}

	/**
	 * @see es.caib.bpm.nas.CommunicationStrategy#retreiveFile(java.lang.String)
	 */
	public File retreiveFile(String path) throws NASException 
	{
		FileOutputStream streamSalida= null;
		File archivoSalida= null;
		String nombreArchivo= null;
		
		try
		{
			this.client.open(this.server);
			this.client.login(this.user, this.password.toCharArray());
			
			nombreArchivo= path.replace('/', '_');
			
			archivoSalida= new File(this.tempPath,
				nombreArchivo + ".temp"); //$NON-NLS-1$
			streamSalida= new FileOutputStream(archivoSalida);

			this.client.getFile(streamSalida, this.rootPath + "/" +
					path.substring(path.indexOf("/") + 1));

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
				this.client.close();

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
	 * @see es.caib.bpm.nas.CommunicationStrategy#setProperties(java.util.Properties)
	 */
	public void setProperties(Properties properties) throws NASException 
	{
		try 
		{
			this.client = new FTPClient();
			
			this.server = properties.getProperty("soffid.ui.docServer").trim(); //$NON-NLS-1$
			this.user = properties.getProperty("soffid.ui.docUsername").trim(); //$NON-NLS-1$
			this.password = properties.getProperty("soffid.ui.docUserPassword").trim(); //$NON-NLS-1$
			
			// Check '/' end symbol
			if (properties.get("soffid.ui.docPath").toString().endsWith("/"))
			{
				this.rootPath = properties.get("soffid.ui.docPath")
					.toString().substring(0, properties.get("soffid.ui.docPath")
						.toString().length() - 1).trim(); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			else
			{
				this.rootPath = properties.get("soffid.ui.docPath").toString();
			}
			
			this.tempPath =
				new File(properties.get("soffid.ui.docTempPath").toString());
			
			Logger.getLogger(FTPStrategy.class).info("FTP folders established."); //$NON-NLS-1$
		} 
		catch (Exception ex) 
		{
			throw new NASException(ex);
		}
	}

	/**
	 * @see es.caib.bpm.nas.CommunicationStrategy#uploadFile(java.io.File, java.lang.String)
	 */
	public void uploadFile(File archivo, String path) throws NASException 
	{
		FileInputStream stream = null;
		String salida = null;
		String[] pathTokenized = null;
		String folder = null;
		String folderNuevo = null;
		
		try
		{
			this.client.open(this.server);
			this.client.login(this.user, this.password.toCharArray());
			
			stream = new FileInputStream(archivo);
			
			pathTokenized = path.split("/"); //$NON-NLS-1$
			
			if(pathTokenized.length > 1)
			{
				folder = this.rootPath + pathTokenized[0]; 
				
				try
				{
					Logger.getLogger(FTPStrategy.class)
						.debug("FOLDER: " + folder); //$NON-NLS-1$
					
					this.client.setWorkingDirectory(folder);
				}
				catch(FTPException ex)
				{
					if(ex.getCode() == 550)
					{
						this.client.makeDirectory(folder);
					}
					else
					{
						throw new NASException(ex);
					}
				}

				for(int index = 1; index < pathTokenized.length - 1; index++)
				{
					try
					{
						folderNuevo = folder + "/" + pathTokenized[index]; //$NON-NLS-1$
						
						Logger.getLogger(FTPStrategy.class)
							.debug("FOLDER 2: " + folderNuevo); //$NON-NLS-1$
						
						this.client.setWorkingDirectory(folderNuevo);
					}
					catch(FTPException ex)
					{
						if(ex.getCode() == 550)
						{
							this.client.makeDirectory(folderNuevo);
						}
						else
						{
							throw new NASException(ex);
						}
					}
					
					folder = folderNuevo;
				}
			}
			
			salida = folder + "/" + pathTokenized[pathTokenized.length - 1]; //$NON-NLS-1$
			
			this.client.putFile(stream, salida);
		}
		catch(Exception ex)
		{
			throw new NASException(ex);
		}
		finally
		{
			try
			{
				this.client.close();
				
				if (stream != null)
				{
					stream.close();
				}
			}
			catch(Exception ex)
			{
				throw new NASException(ex);
			}
		}
	}

	/**
	 * @see es.caib.bpm.nas.CommunicationStrategy#cleanTemporaryResources()
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
