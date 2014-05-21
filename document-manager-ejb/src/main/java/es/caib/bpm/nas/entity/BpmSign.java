package es.caib.bpm.nas.entity;

import java.io.Serializable;
import java.util.Date;

public class BpmSign implements Serializable
{
	
/*
METODOS PUBLICOS
*/
	
	// CONSTRUCTORES
	/**
	 * Constructor por defecto
	 */
	public BpmSign()
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
	 * Recupera el path completo de la ubicacion de la firma en el file system
	 * 
	 * @return el path completo de la ubicacion de la firma en el file system
	 */
	public String getFsPath()
	{
		return fsPath;
	}
	
	/**
	 * Establece el path completo de la ubicacion de la firma en el file system
	 * 
	 * @param fsPath el path completo de la ubicacion de la firma en el file system
	 */
	public void setFsPath(String fsPath)
	{
		this.fsPath = fsPath;
	}

	
	/**
	 * Recupera el tipo de firma.
	 * 
	 * @return el tipo de firma.
	 */
	public char getSignType() 
	{
		return signType;
	}

	/**
	 * Establece el tipo de firma.
	 * 
	 * @param signType el tipo de firma.
	 */
	public void setSignType(char signType) 
	{
		this.signType = signType;
	}

	/**
	 * Recupera el documento al cual esta asociado la firma
	 * 
	 * @return el documento al cual esta asociado la firma
	 */
	public BpmDocument getBpmDocument()
	{
		return bpmDocument;
	}

	/**
	 * Establece el documento al cual esta asociado la firma
	 * 
	 * @param bpmDocuments el documento al cual esta asociado la firma
	 */
	public void setBpmDocument(BpmDocument bpmDocument)
	{
		this.bpmDocument = bpmDocument;
	}

	/**
	 * Recupera el timestamp de la firma.
	 * 
	 * @return el timestamp
	 */
	public Date getTimestamp() 
	{
		return timestamp;
	}

	/**
	 * Establece el timestamp de la firma.
	 * 
	 * @param timestamp
	 */
	public void setTimestamp(Date timestamp) 
	{
		this.timestamp = timestamp;
	}

	/**
	 * Recupera el arreglo de bytes asociado a la firma.
	 * 
	 * @return
	 */
	public byte[] getSign() 
	{
		return sign;
	}

	/**
	 * Establece el arreglo de bytes asociado a la firma.
	 * 
	 * @param sign
	 */
	public void setSign(byte[] sign) 
	{
		this.sign = sign;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) 
	{
		boolean resultado= false;
		BpmSign doc= null;
		
		if(obj instanceof BpmSign)
		{
			doc= (BpmSign)obj;
			
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
	/**El tipo de firma 'N' representa Normal y 'S' SMIME*/
	private char signType= 'N';
	/**El path completo de la ubicacion de la firma en el file system*/
	private String fsPath= null;
	/**El documento al cual esta asignado la firma*/
	private BpmDocument bpmDocument= null;
	/**El timestamp de la firma */
	private Date timestamp= null;
	/** La firma */
	private byte[] sign= null;
}
