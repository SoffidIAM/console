//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject Secret
 **/
public class Secret

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute value

	 */
	private com.soffid.iam.am.api.Password value;

	public Secret()
	{
	}

	public Secret(java.lang.String name, com.soffid.iam.am.api.Password value)
	{
		super();
		this.name = name;
		this.value = value;
	}

	public Secret(Secret otherBean)
	{
		this(otherBean.name, otherBean.value);
	}

	/**
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * Gets value for attribute value
	 */
	public com.soffid.iam.am.api.Password getValue() {
		return this.value;
	}

	/**
	 * Sets value for attribute value
	 */
	public void setValue(com.soffid.iam.am.api.Password value) {
		this.value = value;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[name: ");
		b.append (this.name);
		b.append (", value: ");
		b.append (this.value);
		b.append ("]");
		return b.toString();
	}

}
