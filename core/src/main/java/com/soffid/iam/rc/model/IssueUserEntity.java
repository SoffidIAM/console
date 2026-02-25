//
// (c) 2014 Soffid
//
//

package com.soffid.iam.rc.model;

/**
 *  Entity IssueUserEntity
 */

public abstract class IssueUserEntity {

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
	 * Attribute user
	 */
	private com.soffid.iam.base.model.UserEntity user;
	/**
	 * Gets value for attribute user
	 */
	public com.soffid.iam.base.model.UserEntity getUser() {
		return this.user;
	}
	/**
	 * Sets value for attribute user
	 */
	public void setUser(com.soffid.iam.base.model.UserEntity user) {
		this.user = user;
	}
	/**
	 * Attribute action
	 */
	private com.soffid.iam.iga.api.EventUserAction action;
	/**
	 * Gets value for attribute action
	 */
	public com.soffid.iam.iga.api.EventUserAction getAction() {
		return this.action;
	}
	/**
	 * Sets value for attribute action
	 */
	public void setAction(com.soffid.iam.iga.api.EventUserAction action) {
		this.action = action;
	}
	/**
	 * Attribute userName
	 */
	private java.lang.String userName;
	/**
	 * Gets value for attribute userName
	 */
	public java.lang.String getUserName() {
		return this.userName;
	}
	/**
	 * Sets value for attribute userName
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	/**
	 * Attribute externalId
	 */
	private java.lang.String externalId;
	/**
	 * Gets value for attribute externalId
	 */
	public java.lang.String getExternalId() {
		return this.externalId;
	}
	/**
	 * Sets value for attribute externalId
	 */
	public void setExternalId(java.lang.String externalId) {
		this.externalId = externalId;
	}
	/**
	 * Returns <code>true</code> if the argument is an IssueUserEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof IssueUserEntity))
		{
			return false;
		}
		final IssueUserEntity that = (IssueUserEntity)object;
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
