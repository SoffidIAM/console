package es.caib.bpm.nas.entity;

import java.io.Serializable;
import java.util.Date;

public class BpmAccessLog implements Serializable
{
/*
METODOS PUBLICOS
*/

	// CONSTRUCTORES
	/**
	 * Constructor por defecto
	 */
	public BpmAccessLog()
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
	 * @param id el identificador unico de la bae de datos
	 */
	public void setId(Long id)
	{
		this.id = id;
	}
	
	/**
	 * Recupera el tipo de log
	 * 
	 * @return el tipo de log
	 */
	public Character getType()
	{
		return type;
	}
	
	/**
	 * Establece el tipo de log
	 * 
	 * @param type el tipo de log
	 */
	public void setType(Character type)
	{
		this.type = type;
	}
	
	/**
	 * Recupera el documento
	 * 
	 * @return el documento
	 */
	public BpmDocument getBpmDocument()
	{
		return bpmDocument;
	}
	
	/**
	 * Establece el documento
	 * 
	 * @param bpmRole el documento
	 */
	public void setBpmDocument(BpmDocument bpmDocument)
	{
		this.bpmDocument = bpmDocument;
	}
	
	/**
	 * Recupera el usuario
	 * 
	 * @return el usuario
	 */
	public String getUser()
	{
		return user;
	}
	
	/**
	 * Establece el usuario
	 * 
	 * @param user el usuario
	 */
	public void setUser(String user)
	{
		this.user = user;
	}
	
	/**
	 * Recupera la fecha
	 * 
	 * @return la fecha
	 */
	public Date getDate()
	{
		return date;
	}
	
	/**
	 * Establece la fecha
	 * 
	 * @param date la fecha
	 */
	public void setDate(Date date)
	{
		this.date = date;
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
	/**El tipo de access log*/
	private Character type= null;
	/**El rol*/
	private BpmDocument bpmDocument= null;
	/**El usuario*/
	private String user= null;
	/**La fecha de registro*/
	private Date date= null;
}
