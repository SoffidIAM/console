//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.agent;
/**
 * ValueObject Plugin
 **/
public class Plugin

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
	 * Attribute version

	 */
	private java.lang.String version;

	/**
	 * Attribute content

	 */
	private byte[] content;

	public Plugin()
	{
	}

	public Plugin(java.lang.String name, java.lang.String version, byte[] content)
	{
		super();
		this.name = name;
		this.version = version;
		this.content = content;
	}

	public Plugin(Plugin otherBean)
	{
		this(otherBean.name, otherBean.version, otherBean.content);
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
	 * Gets value for attribute version
	 */
	public java.lang.String getVersion() {
		return this.version;
	}

	/**
	 * Sets value for attribute version
	 */
	public void setVersion(java.lang.String version) {
		this.version = version;
	}

	/**
	 * Gets value for attribute content
	 */
	public byte[] getContent() {
		return this.content;
	}

	/**
	 * Sets value for attribute content
	 */
	public void setContent(byte[] content) {
		this.content = content;
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
		b.append (", version: ");
		b.append (this.version);
		b.append (", content: ");
		b.append (this.content);
		b.append ("]");
		return b.toString();
	}

}
