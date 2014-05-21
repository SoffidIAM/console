package es.caib.bpm.nas.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

import es.caib.bpm.nas.CommunicationStrategy;
import es.caib.bpm.nas.exception.NASException;

/**
 * Representa una estrategia para guardar archivos en un filesystem local.
 * 
 * @author Pablo Hern�n Gim�nez.
 */
public class LocalFileSystemStrategy implements CommunicationStrategy
{
	private File rootPath = null;

	/**
	 * @see es.caib.bpm.nas.CommunicationStrategy#setProperties(java.util.Properties)
	 */
	public void setProperties(Properties properties) throws NASException {
		this.rootPath = new File(properties.get("soffid.ui.docPath").toString());
	}

	/**
	 * @see es.caib.bpm.nas.CommunicationStrategy#retreiveFile(java.lang.String)
	 */
	public File retreiveFile(String path) throws NASException {
		File archivo = null;

		archivo = new File(this.rootPath, path);

		return archivo;
	}

	/**
	 * @see es.caib.bpm.nas.CommunicationStrategy#uploadFile(java.io.File,
	 *      java.lang.String)
	 */
	public void uploadFile(File archivo, String path) throws NASException
	{
		FileInputStream stream = null;
		File salida = null;
		FileOutputStream streamSalida = null;
		byte[] buffer = new byte[10240];
		int leidos = 0;
		String[] pathTokenized = null;
		File folder = null;
		File folderNuevo = null;

		try
		{
			stream = new FileInputStream(archivo);

			pathTokenized = path.split("/");

			if (pathTokenized.length > 1)
			{
				folder = new File(this.rootPath, pathTokenized[0]);

				if (!folder.exists())
				{
					folder.mkdir();
				}

				for (int index = 1; index < pathTokenized.length - 1; index++)
				{
					folderNuevo = new File(folder, pathTokenized[index]);

					if (!folderNuevo.exists())
					{
						folderNuevo.mkdir();
					}

					folder = folderNuevo;
				}
			}

			salida = new File(folder, pathTokenized[pathTokenized.length - 1]);

			if (!salida.exists())
			{
				streamSalida = new FileOutputStream(salida);

				while ((leidos = stream.read(buffer)) != -1)
				{
					streamSalida.write(buffer, 0, leidos);
				}
			}
			
			else
			{
				throw new NASException(String.format("The file '%1$s'already exists",
						new Object[]{salida.getAbsolutePath()})); 
			}
		}
		
		catch (Exception ex)
		{
			throw new NASException(ex);
		}
		
		finally
		{
			try
			{
				if (stream != null)
				{
					stream.close();
				}
				if (streamSalida != null)
				{
					streamSalida.close();
				}
			}
			
			catch (Exception ex)
			{
				throw new NASException(ex);
			}
		}
	}

	/**
	 * @see es.caib.bpm.nas.CommunicationStrategy#cleanTemporaryResources()
	 */
	public void cleanTemporaryResources() throws NASException {
		// NO HACEMOS NADA

	}
}
