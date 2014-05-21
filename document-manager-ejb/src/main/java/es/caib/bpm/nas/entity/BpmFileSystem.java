package es.caib.bpm.nas.entity;

import java.io.Serializable;

public class BpmFileSystem implements Serializable
{
	// CONSTRUCTORES
	/**
	 * Constructor por defecto 
	 */
	public BpmFileSystem()
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
	 * Recupera el nombre de la aplicacion
	 * 
	 * @return el nombre de la aplicacion
	 */
	public String getApplication()
	{
		return application;
	}
	
	/**
	 * Establece el nombre de la aplicacion
	 * 
	 * @param application el nombre de la aplicacion
	 */
	public void setApplication(String application)
	{
		this.application = application;
	}
	
	/**
	 * Recupera year
	 * 
	 * @return year
	 */
	public Integer getYear()
	{
		return year;
	}

	/**
	 * Establece year
	 * 
	 * @param year year
	 */
	public void setYear(Integer year)
	{
		this.year = year;
	}
	
	/**
	 * Recupera el proximo numero de documento
	 *
	 * @return el proximo numero de documento
	 */
	public Long getNextDocNumber()
	{
		return nextDocNumber;
	}
	
	/**
	 * Establece el proximo numero de documento
	 * 
	 * @param nextDocNumber el proximo numero de documento
	 */
	public void setNextDocNumber(Long nextDocNumber)
	{
		this.nextDocNumber = nextDocNumber;
	}

/*
DECLARACIONES
*/
	
	// CAMPOS DE INSTANCIA
	/**El identificador unico de la base de datos*/
	private Long id= null;
	/**La aplicacion*/
	private String application= null;
	/**El year*/
	private Integer year= null;
	/**El numero del proximo documento*/
	private Long nextDocNumber= null;

}
