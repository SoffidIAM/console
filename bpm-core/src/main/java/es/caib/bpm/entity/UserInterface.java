package es.caib.bpm.entity;

import java.io.Serializable;
import java.sql.Blob;

public class UserInterface implements Serializable
{
/*
METODOS PUBLICOS
*/
	// CONSTRUCTORES
	/**
	 * Constructor por defecto
	 */
	public UserInterface()
	{
		// constructor por defecto
	}
	
	// MODIFICADORES / DE ACCESO
		
	/**
	 * Recupera el idenfiticador �nico de la base de datos
	 * 
	 * @return el idenfiticador �nico de la base de datos
	 */
	public Integer getId()
	{
		return id;
	}
	
	/**
	 * Establece el idenfiticador �nico de la base de datos
	 * 
	 * @param id el idenfiticador �nico de la base de datos
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) 
	{
		boolean resultado= false;
		UserInterface doc= null;
		
		if(obj instanceof UserInterface)
		{
			doc= (UserInterface)obj;
			
			if(doc.getId().equals(this.id))
			{
				resultado= true;
			}
		}
		
		return resultado;
	}
	
	/**
	 * Recupera la tarea.
	 * 
	 * @return
	 */
	public String getTarea() 
	{
		return tarea;
	}
	
	/**
	 * Establece la tarea.
	 * 
	 * @param tarea
	 */
	public void setTarea(String tarea) 
	{
		this.tarea = tarea;
	}
	
	/**
	 * Recupera la interfaz en zul.
	 * 
	 * @return
	 */
	public String getFileName() 
	{
		return fileName;
	}
	
	/**
	 * Establece la interfaz en zul.
	 * 
	 * @param contenidoZul
	 */
	public void setFileName(String contenidoZul) 
	{
		this.fileName = contenidoZul;
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
	/**El identificador �nico de la base de datos*/
	private Integer id= null;
	/**El MIME Type del documento*/
	private Long processDefinitionId= null;
	/**El nombre externo del documento*/
	private String tarea= null;
	/**La interfaz ZUL*/
	private String fileName= null;
	public Long getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(Long processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
}
