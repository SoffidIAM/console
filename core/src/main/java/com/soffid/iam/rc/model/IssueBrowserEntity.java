//
// (c) 2014 Soffid
//
//

package com.soffid.iam.rc.model;

/**
 *  Entity IssueBrowserEntity
 */

public abstract class IssueBrowserEntity {

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
	 * Attribute browser
	 */
	private com.soffid.iam.am.model.BrowserEntity browser;
	/**
	 * Gets value for attribute browser
	 */
	public com.soffid.iam.am.model.BrowserEntity getBrowser() {
		return this.browser;
	}
	/**
	 * Sets value for attribute browser
	 */
	public void setBrowser(com.soffid.iam.am.model.BrowserEntity browser) {
		this.browser = browser;
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
	 * Returns <code>true</code> if the argument is an IssueBrowserEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof IssueBrowserEntity))
		{
			return false;
		}
		final IssueBrowserEntity that = (IssueBrowserEntity)object;
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
