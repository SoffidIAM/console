//
// (c) 2014 Soffid
//
//

package com.soffid.iam.rc.model;

/**
 *  Entity IssueHostEntity
 */

public abstract class IssueHostEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id
	 */
	private java.lang.Long id;
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
	 * Attribute issue
	 */
	private com.soffid.iam.rc.model.IssueEntity issue;
	/**
	 * Gets value for attribute issue
	 */
	public com.soffid.iam.rc.model.IssueEntity getIssue() {
		return this.issue;
	}
	/**
	 * Sets value for attribute issue
	 */
	public void setIssue(com.soffid.iam.rc.model.IssueEntity issue) {
		this.issue = issue;
	}
	/**
	 * Attribute host
	 */
	private com.soffid.iam.am.model.HostEntity host;
	/**
	 * Gets value for attribute host
	 */
	public com.soffid.iam.am.model.HostEntity getHost() {
		return this.host;
	}
	/**
	 * Sets value for attribute host
	 */
	public void setHost(com.soffid.iam.am.model.HostEntity host) {
		this.host = host;
	}
	/**
	 * Attribute action
	 */
	private com.soffid.iam.pam.api.HostEventAction action;
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
	 * Attribute hostName
	 */
	private java.lang.String hostName;
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
	 * Attribute hostIp
	 */
	private java.lang.String hostIp;
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
	 * Returns <code>true</code> if the argument is an IssueHostEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof IssueHostEntity))
		{
			return false;
		}
		final IssueHostEntity that = (IssueHostEntity)object;
		if (this.id == null || that.getId() == null || !this.id.equals(that.getId())) 
		{
			return false;
		}
		return true;
	}
	/**
	 * Returns a hash code based on this entity's identifiers.
	 */
	public int hashCode()
	{
		int hashCode = (id == null ? super.hashCode() : id.hashCode());
		return hashCode;
	}
}
