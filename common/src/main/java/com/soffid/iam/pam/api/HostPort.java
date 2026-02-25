//
// (C) 2020 Soffid
//
//

package com.soffid.iam.pam.api;
/**
 * ValueObject HostPort
 **/
public class HostPort

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
	 * Attribute hostId

	 */
	private java.lang.Long hostId;

	/**
	 * Attribute hostName

	 */
	private java.lang.String hostName;

	/**
	 * Attribute port

	 */
	private java.lang.String port;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	public HostPort()
	{
	}

	public HostPort(java.lang.Long id, java.lang.Long hostId, java.lang.String hostName, java.lang.String port, java.lang.String description)
	{
		super();
		this.id = id;
		this.hostId = hostId;
		this.hostName = hostName;
		this.port = port;
		this.description = description;
	}

	public HostPort(java.lang.Long hostId, java.lang.String hostName, java.lang.String port)
	{
		super();
		this.hostId = hostId;
		this.hostName = hostName;
		this.port = port;
	}

	public HostPort(HostPort otherBean)
	{
		this(otherBean.id, otherBean.hostId, otherBean.hostName, otherBean.port, otherBean.description);
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
	 * Gets value for attribute port
	 */
	public java.lang.String getPort() {
		return this.port;
	}

	/**
	 * Sets value for attribute port
	 */
	public void setPort(java.lang.String port) {
		this.port = port;
	}

	/**
	 * Gets value for attribute description
	 */
	public java.lang.String getDescription() {
		return this.description;
	}

	/**
	 * Sets value for attribute description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
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
		b.append (", hostId: ");
		b.append (this.hostId);
		b.append (", hostName: ");
		b.append (this.hostName);
		b.append (", port: ");
		b.append (this.port);
		b.append (", description: ");
		b.append (this.description);
		b.append ("]");
		return b.toString();
	}

}
