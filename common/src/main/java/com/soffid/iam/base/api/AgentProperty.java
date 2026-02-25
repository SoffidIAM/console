//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject AgentProperty
 **/
public class AgentProperty

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute template

	 */
	private java.lang.String template;

	/**
	 * Attribute type

	 */
	private java.lang.String type;

	public AgentProperty()
	{
	}

	public AgentProperty(java.lang.String template, java.lang.String type)
	{
		super();
		this.template = template;
		this.type = type;
	}

	public AgentProperty(AgentProperty otherBean)
	{
		this(otherBean.template, otherBean.type);
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
	 * Gets value for attribute type
	 */
	public java.lang.String getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[template: ");
		b.append (this.template);
		b.append (", type: ");
		b.append (this.type);
		b.append ("]");
		return b.toString();
	}

}
