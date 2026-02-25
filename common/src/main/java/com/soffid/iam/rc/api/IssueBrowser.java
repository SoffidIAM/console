//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject IssueBrowser
 **/
public class IssueBrowser

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
	 * Attribute browserId

	 */
	private java.lang.Long browserId;

	/**
	 * Attribute type

	 */
	private java.lang.String type;

	/**
	 * Attribute hostIp

	 */
	private java.lang.String hostIp;

	public IssueBrowser()
	{
	}

	public IssueBrowser(com.soffid.iam.pam.api.HostEventAction action, java.lang.Long browserId, java.lang.String type, java.lang.String hostIp)
	{
		super();
		this.action = action;
		this.browserId = browserId;
		this.type = type;
		this.hostIp = hostIp;
	}

	public IssueBrowser(com.soffid.iam.pam.api.HostEventAction action, java.lang.Long browserId, java.lang.String type)
	{
		super();
		this.action = action;
		this.browserId = browserId;
		this.type = type;
	}

	public IssueBrowser(IssueBrowser otherBean)
	{
		this(otherBean.action, otherBean.browserId, otherBean.type, otherBean.hostIp);
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
	 * Gets value for attribute browserId
	 */
	public java.lang.Long getBrowserId() {
		return this.browserId;
	}

	/**
	 * Sets value for attribute browserId
	 */
	public void setBrowserId(java.lang.Long browserId) {
		this.browserId = browserId;
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
		b.append (", browserId: ");
		b.append (this.browserId);
		b.append (", type: ");
		b.append (this.type);
		b.append (", hostIp: ");
		b.append (this.hostIp);
		b.append ("]");
		return b.toString();
	}

}
