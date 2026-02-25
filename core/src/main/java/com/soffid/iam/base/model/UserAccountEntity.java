//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity UserAccountEntity
 */

public abstract class UserAccountEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
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
	 * Attribute account
	 */
	private com.soffid.iam.base.model.AccountEntity account;
	/**
	 * Gets value for attribute account
	 */
	public com.soffid.iam.base.model.AccountEntity getAccount() {
		return this.account;
	}
	/**
	 * Sets value for attribute account
	 */
	public void setAccount(com.soffid.iam.base.model.AccountEntity account) {
		this.account = account;
	}
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
	 * Attribute untilDate
	 */
	private java.util.Date untilDate;
	/**
	 * Gets value for attribute untilDate
	 */
	public java.util.Date getUntilDate() {
		return this.untilDate;
	}
	/**
	 * Sets value for attribute untilDate
	 */
	public void setUntilDate(java.util.Date untilDate) {
		this.untilDate = untilDate;
	}
	/**
	 * Attribute workflowId
	 */
	private java.lang.Long workflowId;
	/**
	 * Gets value for attribute workflowId
	 */
	public java.lang.Long getWorkflowId() {
		return this.workflowId;
	}
	/**
	 * Sets value for attribute workflowId
	 */
	public void setWorkflowId(java.lang.Long workflowId) {
		this.workflowId = workflowId;
	}
	/**
	 * Attribute entryPoint
	 */
	private java.lang.String entryPoint;
	/**
	 * Gets value for attribute entryPoint
	 */
	public java.lang.String getEntryPoint() {
		return this.entryPoint;
	}
	/**
	 * Sets value for attribute entryPoint
	 */
	public void setEntryPoint(java.lang.String entryPoint) {
		this.entryPoint = entryPoint;
	}
	/**
	 * Attribute approved
	 * Approved flag for BPM
	 */
	private java.lang.Boolean approved;
	/**
	 * Gets value for attribute approved
	 */
	public java.lang.Boolean getApproved() {
		return this.approved;
	}
	/**
	 * Sets value for attribute approved
	 */
	public void setApproved(java.lang.Boolean approved) {
		this.approved = approved;
	}
	/**
	 * Attribute createdOn

	 */
	private java.util.Date createdOn;
	/**
	 * Gets value for attribute createdOn
	 */
	public java.util.Date getCreatedOn() {
		return this.createdOn;
	}
	/**
	 * Sets value for attribute createdOn
	 */
	public void setCreatedOn(java.util.Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * Operation customCache
	**/
	 public abstract void customCache();

	/**
	 * Returns <code>true</code> if the argument is an UserAccountEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof UserAccountEntity))
		{
			return false;
		}
		final UserAccountEntity that = (UserAccountEntity)object;
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
