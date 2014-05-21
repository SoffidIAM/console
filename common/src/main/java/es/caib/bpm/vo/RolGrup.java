package es.caib.bpm.vo;

import java.io.Serializable;

public class RolGrup implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	String rol;
	String grup;

	public RolGrup(String rol, String grup) {
		this.rol=rol;
		this.grup=grup;
	}

	/**
	 * @return the rol
	 */
	public synchronized String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public synchronized void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return the grup
	 */
	public synchronized String getGrup() {
		return grup;
	}

	/**
	 * @param grup the grup to set
	 */
	public synchronized void setGrup(String grup) {
		this.grup = grup;
	}
	
	
	
}
