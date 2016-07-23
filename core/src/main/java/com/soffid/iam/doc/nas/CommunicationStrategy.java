package com.soffid.iam.doc.nas;

import java.io.File;
import java.util.Properties;

import com.soffid.iam.doc.exception.NASException;

/**
 * Representa una estrategia de comunicacion con el NAS.
 * 
 */
public interface CommunicationStrategy 
{
		
	/**
	 * Establece las propiedades a la estrategia.
	 * 
	 * @throws NASException si no puede obtener alguna configuracion del archivo.
	 */
	public void setProperties() throws NASException;
	
	/**
	 * Sube un archivo al NAS en la ubicacion indicada.
	 * 
	 * @param archivo
	 * @param path
	 * @throws NASException
	 */
	public void uploadFile(File archivo, String path) throws NASException;
	
	/**
	 * Recupera el archivo del NAS a partir del path.
	 * 
	 * @param path
	 * @return
	 * @throws NASException
	 */
	public File retreiveFile(String path) throws NASException;
	
	/**
	 * Realiza la limpieza de los recursos temporarios.
	 * Se utiliza luego de subir o bajar un documento.
	 * 
	 * @throws NASException
	 */
	public void cleanTemporaryResources() throws NASException;

	public void deleteFile(String path) throws NASException;
}
