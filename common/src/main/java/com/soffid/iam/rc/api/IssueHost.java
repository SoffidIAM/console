//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject IssueHost
 **/
public class IssueHost

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute action

	 */
	private com.soffid.iam.pam.api.HostEventAction action;

	/**
	 * Attribute hostId

	 */
	private java.lang.Long hostId;

	/**
	 * Attribute hostName

	 */
	private java.lang.String hostName;

	/**
	 * Attribute hostIp

	 */
	private java.lang.String hostIp;

	public IssueHost()
	{
	}

	public IssueHost(com.soffid.iam.pam.api.HostEventAction action, java.lang.Long hostId, java.lang.String hostName, java.lang.String hostIp)
	{
		super();
		this.action = action;
		this.hostId = hostId;
		this.hostName = hostName;
		this.hostIp = hostIp;
	}

	public IssueHost(com.soffid.iam.pam.api.HostEventAction action, java.lang.Long hostId, java.lang.String hostName)
	{
		super();
		this.action = action;
		this.hostId = hostId;
		this.hostName = hostName;
	}

	public IssueHost(IssueHost otherBean)
	{
		this(otherBean.action, otherBean.hostId, otherBean.hostName, otherBean.hostIp);
	}

	/**
	 * Gets value for attribute action
	 */
	public com.soffid.iam.pam.api.HostEventAction getAction() {
		return this.action;
	}

	/**
	 * Sets value for attribute action
	 */
	public void setAction(com.soffid.iam.pam.api.HostEventAction action) {
		this.action = action;
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
	 * Gets value for attribute hostIp
	 */
	public java.lang.String getHostIp() {
		return this.hostIp;
	}

	/**
	 * Sets value for attribute hostIp
	 */
	public void setHostIp(java.lang.String hostIp) {
		this.hostIp = hostIp;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[action: ");
		b.append (this.action);
		b.append (", hostId: ");
		b.append (this.hostId);
		b.append (", hostName: ");
		b.append (this.hostName);
		b.append (", hostIp: ");
		b.append (this.hostIp);
		b.append ("]");
		return b.toString();
	}

}
