//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject RolGrup
 **/
public class RolGrup

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute rol

	 */
	private java.lang.String rol;

	/**
	 * Attribute grup

	 */
	private java.lang.String grup;

	public RolGrup()
	{
	}

	public RolGrup(java.lang.String rol, java.lang.String grup)
	{
		super();
		this.rol = rol;
		this.grup = grup;
	}

	public RolGrup(RolGrup otherBean)
	{
		this(otherBean.rol, otherBean.grup);
	}

	/**
	 * Gets value for attribute rol
	 */
	public java.lang.String getRol() {
		return this.rol;
	}

	/**
	 * Sets value for attribute rol
	 */
	public void setRol(java.lang.String rol) {
		this.rol = rol;
	}

	/**
	 * Gets value for attribute grup
	 */
	public java.lang.String getGrup() {
		return this.grup;
	}

	/**
	 * Sets value for attribute grup
	 */
	public void setGrup(java.lang.String grup) {
		this.grup = grup;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[rol: ");
		b.append (this.rol);
		b.append (", grup: ");
		b.append (this.grup);
		b.append ("]");
		return b.toString();
	}

}
