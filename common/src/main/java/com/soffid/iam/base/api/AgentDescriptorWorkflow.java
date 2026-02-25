//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject AgentDescriptorWorkflow
 **/
public class AgentDescriptorWorkflow

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
	 * Attribute image

	 */
	private byte[] image;

	public AgentDescriptorWorkflow()
	{
	}

	public AgentDescriptorWorkflow(java.lang.String name, byte[] image)
	{
		super();
		this.name = name;
		this.image = image;
	}

	public AgentDescriptorWorkflow(AgentDescriptorWorkflow otherBean)
	{
		this(otherBean.name, otherBean.image);
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
	 * Gets value for attribute image
	 */
	public byte[] getImage() {
		return this.image;
	}

	/**
	 * Sets value for attribute image
	 */
	public void setImage(byte[] image) {
		this.image = image;
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
		b.append (", image: ");
		b.append (this.image);
		b.append ("]");
		return b.toString();
	}

}
