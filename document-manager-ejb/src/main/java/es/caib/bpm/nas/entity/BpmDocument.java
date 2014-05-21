package es.caib.bpm.nas.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class BpmDocument implements Serializable
{
/*
METODOS PUBLICOS
*/
	// CONSTRUCTORES
	/**
	 * Constructor por defecto
	 */
	public BpmDocument()
	{
		// constructor por defecto
	}
	
	// MODIFICADORES / DE ACCESO
		
	/**
	 * Recupera el idenfiticador unico de la base de datos
	 * 
	 * @return el idenfiticador unico de la base de datos
	 */
	public Long getId()
	{
		return id;
	}
	
	/**
	 * Establece el idenfiticador unico de la base de datos
	 * 
	 * @param id el idenfiticador unico de la base de datos
	 */
	public void setId(Long id)
	{
		this.id = id;
	}
	
	/**
	 * Recupera el MIME Type del documento
	 * 
	 * @return el MIME Type del documento
	 */
	public String getMimeType()
	{
		return mimeType;
	}
	
	/**
	 * Establece el MIME Type del documento
	 * 
	 * @param mimeType el MIME Type del documento
	 */
	public void setMimeType(String mimeType)
	{
		this.mimeType = mimeType;
	}
	
	/**
	 * Recupera el nombre externo del documento
	 * 
	 * @return el nombre externo del documento
	 */
	public String getExternalName()
	{
		return externalName;
	}
	
	/**
	 * Establece el nombre externo del documento
	 * 
	 * @param externalName el nombre externo del documento
	 */
	public void setExternalName(String externalName)
	{
		this.externalName = externalName;
	}
	
	/**
	 * Recupera el hash del documento
	 * 
	 * @return el hash del documento
	 */
	public String getHash()
	{
		return hash;
	}
	
	/**
	 * Establece el hash del documento
	 * 
	 * @param hash el hash del documento
	 */
	public void setHash(String hash)
	{
		this.hash = hash;
	}	

	/**
	 * Recupera el path completo de la ubicacion fisica del documento en el file system
	 * 
	 * @return el path completo de la ubicacion fisica del documento en el file system
	 */
	public String getFsPath()
	{
		return fsPath;
	}
	
	/**
	 * Establece el path completo de la ubicacion fisica del documento en el file system
	 * 
	 * @param fsPath el path completo de la ubicacion fisica del documento en el file system
	 */
	public void setFsPath(String fsPath)
	{
		this.fsPath = fsPath;
	}
	
	/**
	 * Recupera las firmas asociadas al documento
	 * 
	 * @return las firmas asociadas al documento
	 */
	public Set getSigns()
	{
		return signs;
	}

	/**
	 * Establece las firmas asociadas al documento
	 * 
	 * @param bpmSigns las firmas asociadas al documento
	 */
	public void setSigns(Set signs)
	{
		this.signs = signs;
	}
	
	/**
	 * Recupera los roles que tienen permiso para accesder al documento
	 * 
	 * @return los roles que tienen permiso para acceder al documento
	 */
	public Set getRoles()
	{
		return roles;
	}

	/**
	 * Establece los roles que tienen permiso para acceder al documento
	 * 
	 * @param roles los roles que tienen permiso para acceder al documento
	 */
	public void setRoles(Set roles)
	{
		this.roles = roles;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) 
	{
		boolean resultado= false;
		BpmDocument doc= null;
		
		if(obj instanceof BpmDocument)
		{
			doc= (BpmDocument)obj;
			
			if(doc.getId().equals(this.id))
			{
				resultado= true;
			}
		}
		
		return resultado;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return this.id.hashCode();
	}

/*
DECLARACIONES
*/

	// CAMPOS DE INSTANCIA
	/**El identificador unico de la base de datos*/
	private Long id= null;
	/**El MIME Type del documento*/
	private String mimeType= null;
	/**El nombre externo del documento*/
	private String externalName= null;
	/**El hash del documento*/
	private String hash= null;
	/**El path completo de la ubicacion fisica del documento*/
	private String fsPath= null;
	/**Las firmas asociadas al documento*/
	private Set signs= null;
	/**Los roles que tiene permiso al documento*/
	private Set roles= null;

}
