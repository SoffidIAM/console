//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject ProcessDefinition
 **/
public class ProcessDefinition

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute version

	 */
	private int version;

	/**
	 * Attribute tag

	 */
	private java.lang.String tag;

	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute id

	 */
	private long id;

	/**
	 * Attribute enabled

	 */
	private boolean enabled;

	/**
	 * Attribute appliesTo

	 */
	private java.lang.String appliesTo;

	/**
	 * Attribute type

	 */
	private com.soffid.iam.bpm.api.PredefinedProcessType type;

	/**
	 * Attribute deployed

	 */
	private java.util.Date deployed;

	/**
	 * Attribute author

	 */
	private java.lang.String author;

	public ProcessDefinition()
	{
	}

	public ProcessDefinition(int version, java.lang.String tag, java.lang.String name, long id, boolean enabled, java.lang.String appliesTo, com.soffid.iam.bpm.api.PredefinedProcessType type, java.util.Date deployed, java.lang.String author)
	{
		super();
		this.version = version;
		this.tag = tag;
		this.name = name;
		this.id = id;
		this.enabled = enabled;
		this.appliesTo = appliesTo;
		this.type = type;
		this.deployed = deployed;
		this.author = author;
	}

	public ProcessDefinition(int version, boolean enabled)
	{
		super();
		this.version = version;
		this.enabled = enabled;
	}

	public ProcessDefinition(ProcessDefinition otherBean)
	{
		this(otherBean.version, otherBean.tag, otherBean.name, otherBean.id, otherBean.enabled, otherBean.appliesTo, otherBean.type, otherBean.deployed, otherBean.author);
	}

	/**
	 * Gets value for attribute version
	 */
	public int getVersion() {
		return this.version;
	}

	/**
	 * Sets value for attribute version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Gets value for attribute tag
	 */
	public java.lang.String getTag() {
		return this.tag;
	}

	/**
	 * Sets value for attribute tag
	 */
	public void setTag(java.lang.String tag) {
		this.tag = tag;
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
	 * Gets value for attribute id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets value for attribute id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets value for attribute enabled
	 */
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * Sets value for attribute enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets value for attribute appliesTo
	 */
	public java.lang.String getAppliesTo() {
		return this.appliesTo;
	}

	/**
	 * Sets value for attribute appliesTo
	 */
	public void setAppliesTo(java.lang.String appliesTo) {
		this.appliesTo = appliesTo;
	}

	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.bpm.api.PredefinedProcessType getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.bpm.api.PredefinedProcessType type) {
		this.type = type;
	}

	/**
	 * Gets value for attribute deployed
	 */
	public java.util.Date getDeployed() {
		return this.deployed;
	}

	/**
	 * Sets value for attribute deployed
	 */
	public void setDeployed(java.util.Date deployed) {
		this.deployed = deployed;
	}

	/**
	 * Gets value for attribute author
	 */
	public java.lang.String getAuthor() {
		return this.author;
	}

	/**
	 * Sets value for attribute author
	 */
	public void setAuthor(java.lang.String author) {
		this.author = author;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[version: ");
		b.append (this.version);
		b.append (", tag: ");
		b.append (this.tag);
		b.append (", name: ");
		b.append (this.name);
		b.append (", id: ");
		b.append (this.id);
		b.append (", enabled: ");
		b.append (this.enabled);
		b.append (", appliesTo: ");
		b.append (this.appliesTo);
		b.append (", type: ");
		b.append (this.type);
		b.append (", deployed: ");
		b.append (this.deployed);
		b.append (", author: ");
		b.append (this.author);
		b.append ("]");
		return b.toString();
	}

}
