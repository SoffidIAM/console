//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * ValueObject AbstractParameter
 **/
public abstract class AbstractParameter

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
	private java.lang.Object value;

	public AbstractParameter()
	{
	}

	public AbstractParameter(java.lang.String name, java.lang.Object value)
	{
		super();
		this.name = name;
		this.value = value;
	}

	public AbstractParameter(AbstractParameter otherBean)
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
	public java.lang.Object getValue() {
		return this.value;
	}

	/**
	 * Sets value for attribute value
	 */
	public void setValue(java.lang.Object value) {
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
