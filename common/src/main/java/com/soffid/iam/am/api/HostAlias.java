//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject HostAlias
 **/
public class HostAlias

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
	 * Attribute alias

	 */
	private java.lang.String alias;

	/**
	 * Attribute hostId

	 */
	private java.lang.Long hostId;

	/**
	 * Attribute hostName

	 */
	private java.lang.String hostName;

	public HostAlias()
	{
	}

	public HostAlias(java.lang.Long id, java.lang.String alias, java.lang.Long hostId, java.lang.String hostName)
	{
		super();
		this.id = id;
		this.alias = alias;
		this.hostId = hostId;
		this.hostName = hostName;
	}

	public HostAlias(java.lang.String alias, java.lang.String hostName)
	{
		super();
		this.alias = alias;
		this.hostName = hostName;
	}

	public HostAlias(HostAlias otherBean)
	{
		this(otherBean.id, otherBean.alias, otherBean.hostId, otherBean.hostName);
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
	 * Gets value for attribute alias
	 */
	public java.lang.String getAlias() {
		return this.alias;
	}

	/**
	 * Sets value for attribute alias
	 */
	public void setAlias(java.lang.String alias) {
		this.alias = alias;
	}

	/**
	 * Gets value for attribute hostId
	 */
	public java.lang.Long getHostId() {
		return this.hostId;
	}

	/**
	 * Sets value for attribute hostId
	 */
	public void setHostId(java.lang.Long hostId) {
		this.hostId = hostId;
	}

	/**
	 * Gets value for attribute hostName
	 */
	public java.lang.String getHostName() {
		return this.hostName;
	}

	/**
	 * Sets value for attribute hostName
	 */
	public void setHostName(java.lang.String hostName) {
		this.hostName = hostName;
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
		b.append (", alias: ");
		b.append (this.alias);
		b.append (", hostId: ");
		b.append (this.hostId);
		b.append (", hostName: ");
		b.append (this.hostName);
		b.append ("]");
		return b.toString();
	}

}
