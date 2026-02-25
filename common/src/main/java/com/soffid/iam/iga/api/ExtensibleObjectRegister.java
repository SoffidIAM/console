//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject ExtensibleObjectRegister
 **/
public class ExtensibleObjectRegister

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
	 * Attribute descsription

	 */
	private java.lang.String descsription;

	/**
	 * Attribute className

	 */
	private java.lang.String className;

	public ExtensibleObjectRegister()
	{
	}

	public ExtensibleObjectRegister(java.lang.String name, java.lang.String descsription, java.lang.String className)
	{
		super();
		this.name = name;
		this.descsription = descsription;
		this.className = className;
	}

	public ExtensibleObjectRegister(ExtensibleObjectRegister otherBean)
	{
		this(otherBean.name, otherBean.descsription, otherBean.className);
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
	 * Gets value for attribute descsription
	 */
	public java.lang.String getDescsription() {
		return this.descsription;
	}

	/**
	 * Sets value for attribute descsription
	 */
	public void setDescsription(java.lang.String descsription) {
		this.descsription = descsription;
	}

	/**
	 * Gets value for attribute className
	 */
	public java.lang.String getClassName() {
		return this.className;
	}

	/**
	 * Sets value for attribute className
	 */
	public void setClassName(java.lang.String className) {
		this.className = className;
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
		b.append (", descsription: ");
		b.append (this.descsription);
		b.append (", className: ");
		b.append (this.className);
		b.append ("]");
		return b.toString();
	}

}
