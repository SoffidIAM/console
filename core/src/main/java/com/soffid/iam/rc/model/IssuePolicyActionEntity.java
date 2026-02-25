//
// (c) 2014 Soffid
//
//

package com.soffid.iam.rc.model;

/**
 *  Entity IssuePolicyActionEntity
 */

public abstract class IssuePolicyActionEntity {

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
	 * Attribute issuePolicy
	 */
	private com.soffid.iam.rc.model.IssuePolicyEntity issuePolicy;
	/**
	 * Gets value for attribute issuePolicy
	 */
	public com.soffid.iam.rc.model.IssuePolicyEntity getIssuePolicy() {
		return this.issuePolicy;
	}
	/**
	 * Sets value for attribute issuePolicy
	 */
	public void setIssuePolicy(com.soffid.iam.rc.model.IssuePolicyEntity issuePolicy) {
		this.issuePolicy = issuePolicy;
	}
	/**
	 * Attribute status
	 */
	private com.soffid.iam.rc.api.IssueStatus status;
	/**
	 * Gets value for attribute status
	 */
	public com.soffid.iam.rc.api.IssueStatus getStatus() {
		return this.status;
	}
	/**
	 * Sets value for attribute status
	 */
	public void setStatus(com.soffid.iam.rc.api.IssueStatus status) {
		this.status = status;
	}
	/**
	 * Attribute action
	 */
	private java.lang.String action;
	/**
	 * Gets value for attribute action
	 */
	public java.lang.String getAction() {
		return this.action;
	}
	/**
	 * Sets value for attribute action
	 */
	public void setAction(java.lang.String action) {
		this.action = action;
	}
	/**
	 * Attribute description
	 */
	private java.lang.String description;
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
	 * Attribute subject
	 */
	private java.lang.String subject;
	/**
	 * Gets value for attribute subject
	 */
	public java.lang.String getSubject() {
		return this.subject;
	}
	/**
	 * Sets value for attribute subject
	 */
	public void setSubject(java.lang.String subject) {
		this.subject = subject;
	}
	/**
	 * Attribute body
	 */
	private java.lang.String body;
	/**
	 * Gets value for attribute body
	 */
	public java.lang.String getBody() {
		return this.body;
	}
	/**
	 * Sets value for attribute body
	 */
	public void setBody(java.lang.String body) {
		this.body = body;
	}
	/**
	 * Attribute emailAddress
	 */
	private java.lang.String emailAddress;
	/**
	 * Gets value for attribute emailAddress
	 */
	public java.lang.String getEmailAddress() {
		return this.emailAddress;
	}
	/**
	 * Sets value for attribute emailAddress
	 */
	public void setEmailAddress(java.lang.String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * Attribute processDefinition
	 */
	private java.lang.String processDefinition;
	/**
	 * Gets value for attribute processDefinition
	 */
	public java.lang.String getProcessDefinition() {
		return this.processDefinition;
	}
	/**
	 * Sets value for attribute processDefinition
	 */
	public void setProcessDefinition(java.lang.String processDefinition) {
		this.processDefinition = processDefinition;
	}
	/**
	 * Attribute script
	 */
	private java.lang.String script;
	/**
	 * Gets value for attribute script
	 */
	public java.lang.String getScript() {
		return this.script;
	}
	/**
	 * Sets value for attribute script
	 */
	public void setScript(java.lang.String script) {
		this.script = script;
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
	 * Attribute createdBy

	 */
	private java.lang.String createdBy;
	/**
	 * Gets value for attribute createdBy
	 */
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}
	/**
	 * Sets value for attribute createdBy
	 */
	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * Attribute updatedOn

	 */
	private java.util.Date updatedOn;
	/**
	 * Gets value for attribute updatedOn
	 */
	public java.util.Date getUpdatedOn() {
		return this.updatedOn;
	}
	/**
	 * Sets value for attribute updatedOn
	 */
	public void setUpdatedOn(java.util.Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * Attribute updatedBy

	 */
	private java.lang.String updatedBy;
	/**
	 * Gets value for attribute updatedBy
	 */
	public java.lang.String getUpdatedBy() {
		return this.updatedBy;
	}
	/**
	 * Sets value for attribute updatedBy
	 */
	public void setUpdatedBy(java.lang.String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * Attribute deletedOn

	 */
	private java.util.Date deletedOn;
	/**
	 * Gets value for attribute deletedOn
	 */
	public java.util.Date getDeletedOn() {
		return this.deletedOn;
	}
	/**
	 * Sets value for attribute deletedOn
	 */
	public void setDeletedOn(java.util.Date deletedOn) {
		this.deletedOn = deletedOn;
	}
	/**
	 * Attribute deletedBy

	 */
	private java.lang.String deletedBy;
	/**
	 * Gets value for attribute deletedBy
	 */
	public java.lang.String getDeletedBy() {
		return this.deletedBy;
	}
	/**
	 * Sets value for attribute deletedBy
	 */
	public void setDeletedBy(java.lang.String deletedBy) {
		this.deletedBy = deletedBy;
	}
	/**
	 * Returns <code>true</code> if the argument is an IssuePolicyActionEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof IssuePolicyActionEntity))
		{
			return false;
		}
		final IssuePolicyActionEntity that = (IssuePolicyActionEntity)object;
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
