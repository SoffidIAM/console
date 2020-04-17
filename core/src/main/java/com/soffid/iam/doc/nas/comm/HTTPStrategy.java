package com.soffid.iam.doc.nas.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.util.Base64;


public class HTTPStrategy implements CommunicationStrategy 
{
	/** La URL del Server terminando con / */
	private String rootURL= null;
	/** El path de archivos temporarios */
	private String tempPath= null;
	/** El listado de archivos a limpiar */
	private List filesToClean= null;
	
	Log log = LogFactory.getLog(getClass());
	private String user;
	private String password;
	
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
			
			get(archivoSalida, urlPath);
			
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

	private void get(File archivoSalida, String urlPath) throws IOException {
		HttpURLConnection conn = generateConnection(urlPath);
		conn.connect();
		InputStream in = conn.getInputStream();
		FileOutputStream out = new FileOutputStream(archivoSalida);
		for (int read = in.read(); read >= 0; read = in.read())
			out.write(read);
		in.close();
		out.close();
	}

	public HttpURLConnection generateConnection(String urlPath)
			throws MalformedURLException, IOException, UnsupportedEncodingException {
		URL url = new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String auth = user+":"+password;
		conn.addRequestProperty("Authorization", "Basic "+Base64.encodeBytes(auth.getBytes("UTF-8")));
		conn.setDoInput(true);
		conn.setDoOutput(false);
		return conn;
	}


	private void put(File archivoSalida, String urlPath) throws IOException {
		URL url = new URL(urlPath);
		log.info("Putting "+urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String auth = user+":"+password;
		conn.addRequestProperty("Authorization", "Basic "+Base64.encodeBytes(auth.getBytes("UTF-8")));
		conn.setDoOutput(true);
		conn.setRequestMethod("PUT");
		OutputStream out = conn.getOutputStream();
		log.info("Source file "+archivoSalida+" size "+archivoSalida.length());
		FileInputStream in = new FileInputStream(archivoSalida);
		for (int read = in.read(); read >= 0; read = in.read())
			out.write(read);
		in.close();
		out.close();
		conn.getInputStream().close();
		log.info("End putting "+urlPath);
	}

	private void delete(String urlPath) throws IOException {
		URL url = new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String auth = user+":"+password;
		conn.addRequestProperty("Authorization", "Basic "+Base64.encodeBytes(auth.getBytes("UTF-8")));
		conn.setDoInput(false);
		conn.setDoOutput(false);
		conn.setRequestMethod("DELETE");
		conn.connect();
	}

	/**
	 * @see com.soffid.iam.doc.nas.CommunicationStrategy#setProperties(java.util.Properties)
	 */
	public void setProperties() throws NASException 
	{
		this.rootURL= ConfigurationCache.getMasterProperty("soffid.ui.docServer");
		if (!rootURL.endsWith("/"))
			rootURL = rootURL + "/";
		this.tempPath= ConfigurationCache.getMasterProperty("soffid.ui.docTempPath");
		
		Properties properties = new Properties();
		this.tempPath= properties.getProperty("soffid.ui.docTempPath");
		
		properties.put(JWOptions.QUIET_P, JWOptions.TRUE_P);
		this.user = ConfigurationCache.getMasterProperty("soffid.ui.docUsername"); //$NON-NLS-1$
		if (this.user != null)
			this.user= user.trim();
		this.password = ConfigurationCache.getMasterProperty("soffid.ui.docUserPassword"); //$NON-NLS-1$
		if (password != null)
			password = password.trim();
		if (user != null && ! user.isEmpty() && password != null && ! password.isEmpty())
		{
			properties.put (JWOptions.USER_P, user);
			try {
				properties.put(JWOptions.PASSWORD_P, Base64.encodeBytes(password.getBytes("UTF-8")));
			} catch (UnsupportedEncodingException e) {
				throw new NASException(e);
			}
		}
		
		
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
			put (nuevoArchivo, urlPath);
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

	public void deleteFile(String path) throws NASException {
		String urlPath= null;
		
		try
		{
			urlPath= this.rootURL + path.substring(1);
			
			delete(urlPath);
			
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
