//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject Service
 **/
public class Service

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute code

	 */
	private java.lang.String code;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	public Service()
	{
	}

	public Service(java.lang.String code, java.lang.String description, java.lang.Long id)
	{
		super();
		this.code = code;
		this.description = description;
		this.id = id;
	}

	public Service(java.lang.String code)
	{
		super();
		this.code = code;
	}

	public Service(Service otherBean)
	{
		this(otherBean.code, otherBean.description, otherBean.id);
	}

	/**
	 * Gets value for attribute code
	 */
	public java.lang.String getCode() {
		return this.code;
	}

	/**
	 * Sets value for attribute code
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}

	/**
	 * Gets value for attribute description
	 */
	public java.lang.String getDescription() {
		return this.description;
	}

	/**
	 * Sets value for attribute description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * Gets value for attribute id
	 */
	public java.lang.Long getId() {
		return this.id;
	}

	/**
	 * Sets value for attribute id
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[code: ");
		b.append (this.code);
		b.append (", description: ");
		b.append (this.description);
		b.append (", id: ");
		b.append (this.id);
		b.append ("]");
		return b.toString();
	}

}
