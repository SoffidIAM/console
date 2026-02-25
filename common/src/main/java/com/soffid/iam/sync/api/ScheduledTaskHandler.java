//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject ScheduledTaskHandler
 **/
public class ScheduledTaskHandler

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
	 * Attribute className

	 */
	private java.lang.String className;

	public ScheduledTaskHandler()
	{
	}

	public ScheduledTaskHandler(java.lang.Long id, java.lang.String name, java.lang.String className)
	{
		super();
		this.id = id;
		this.name = name;
		this.className = className;
	}

	public ScheduledTaskHandler(java.lang.String name, java.lang.String className)
	{
		super();
		this.name = name;
		this.className = className;
	}

	public ScheduledTaskHandler(ScheduledTaskHandler otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.className);
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
		b.append ("[id: ");
		b.append (this.id);
		b.append (", name: ");
		b.append (this.name);
		b.append (", className: ");
		b.append (this.className);
		b.append ("]");
		return b.toString();
	}

}
