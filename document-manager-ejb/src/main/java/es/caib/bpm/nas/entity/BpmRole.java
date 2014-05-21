package es.caib.bpm.nas.entity;

import java.io.Serializable;
import java.util.Set;

public class BpmRole implements Serializable
{
/*
METODOS PUBLICOS
*/
	/**
	 * Constructor por defecto
	 */
	public BpmRole()
	{
		// constructor por defecto
	}
	
	// MODIFICADORES / DE ACCESO

	/**
	 * Recupera el identificador unico de la base de datos
	 * 
	 * @return el identificador unico de la base de datos
	 */
	public Long getId()
	{
		return id;
	}
	
	/**
	 * Establece el identificador unico de la base de datos
	 * 
	 * @param id el identificador unico de la base de datos
	 */
	public void setId(Long id)
	{
		this.id = id;
	}
	
	/**
	 * Recupera el nombre del rol
	 * 
	 * @return el nombre del rol
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Establece el nombre del rol
	 * 
	 * @param name el nombre del rol
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Recupera los access logs del rol
	 * 
	 * @return los access logs del rol
	 */
	public Set getAccessLogs()
	{
		return accessLogs;
	}

	/**
	 * Establece los accesss logs del rol
	 * 
	 * @param accessLogs los access logs del rol
	 */
	public void setAccessLogs(Set accessLogs)
	{
		this.accessLogs = accessLogs;
	}
	
	/**
	 * Recupera los documentos a los que tiene permiso el rol
	 * 
	 * @return los documentos a los que tiene permiso el rol
	 */
	public Set getDocuments()
	{
		return documents;
	}

	/**
	 * Establece los documentos a los que tiene permiso el rol
	 * 
	 * @param documents los documentos a los que tiene permiso el rol
	 */
	public void setDocuments(Set documents)
	{
		this.documents = documents;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) 
	{
		boolean resultado= false;
		BpmRole doc= null;
		
		if(obj instanceof BpmRole)
		{
			doc= (BpmRole)obj;
			
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
	/**El nombre del rol*/
	private String name= null;
	/**Los access log*/
	private Set accessLogs= null;
	/**Los documentos a los que tiene permiso el rol*/
	private Set documents= null;

}
