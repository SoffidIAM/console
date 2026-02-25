//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject AccessTreeExecutionType
 **/
public class AccessTreeExecutionType

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute mimeType

	 */
	private java.lang.String mimeType;

	/**
	 * Attribute template

	 */
	private java.lang.String template;

	/**
	 * Attribute javaClass

	 */
	private java.lang.String javaClass;

	public AccessTreeExecutionType()
	{
	}

	public AccessTreeExecutionType(java.lang.Long id, java.lang.String name, java.lang.String mimeType, java.lang.String template, java.lang.String javaClass)
	{
		super();
		this.id = id;
		this.name = name;
		this.mimeType = mimeType;
		this.template = template;
		this.javaClass = javaClass;
	}

	public AccessTreeExecutionType(java.lang.Long id, java.lang.String name, java.lang.String mimeType, java.lang.String template)
	{
		super();
		this.id = id;
		this.name = name;
		this.mimeType = mimeType;
		this.template = template;
	}

	public AccessTreeExecutionType(AccessTreeExecutionType otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.mimeType, otherBean.template, otherBean.javaClass);
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
	 * Gets value for attribute name
	 */
	public java.lang.String getCode() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setCode(java.lang.String name) {
		this.name = name;
	}

	/**
	 * Gets value for attribute mimeType
	 */
	public java.lang.String getMimeType() {
		return this.mimeType;
	}

	/**
	 * Sets value for attribute mimeType
	 */
	public void setMimeType(java.lang.String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * Gets value for attribute template
	 */
	public java.lang.String getTemplate() {
		return this.template;
	}

	/**
	 * Sets value for attribute template
	 */
	public void setTemplate(java.lang.String template) {
		this.template = template;
	}

	/**
	 * Gets value for attribute javaClass
	 */
	public java.lang.String getJavaClass() {
		return this.javaClass;
	}

	/**
	 * Sets value for attribute javaClass
	 */
	public void setJavaClass(java.lang.String javaClass) {
		this.javaClass = javaClass;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", name: ");
		b.append (this.name);
		b.append (", mimeType: ");
		b.append (this.mimeType);
		b.append (", template: ");
		b.append (this.template);
		b.append (", javaClass: ");
		b.append (this.javaClass);
		b.append ("]");
		return b.toString();
	}

}
