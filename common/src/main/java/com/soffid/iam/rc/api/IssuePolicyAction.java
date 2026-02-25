//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject IssuePolicyAction
 **/
public class IssuePolicyAction

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
	 * Attribute status

	 */
	private com.soffid.iam.rc.api.IssueStatus status;

	/**
	 * Attribute action

	 */
	private java.lang.String action;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute subject

	 */
	private java.lang.String subject = "Soffid issue ${number}";

	/**
	 * Attribute body

	 */
	private java.lang.String body = "${description}";

	/**
	 * Attribute emailAddress

	 */
	private java.lang.String emailAddress;

	/**
	 * Attribute processDefinition

	 */
	private java.lang.String processDefinition;

	/**
	 * Attribute script

	 */
	private java.lang.String script;

	/**
	 * Attribute createdOn


	 */
	private java.util.Date createdOn;

	/**
	 * Attribute createdBy


	 */
	private java.lang.String createdBy;

	/**
	 * Attribute updatedOn


	 */
	private java.util.Date updatedOn;

	/**
	 * Attribute updatedBy


	 */
	private java.lang.String updatedBy;

	/**
	 * Attribute deletedOn


	 */
	private java.util.Date deletedOn;

	/**
	 * Attribute deletedBy


	 */
	private java.lang.String deletedBy;

	public IssuePolicyAction()
	{
	}

	public IssuePolicyAction(java.lang.Long id, com.soffid.iam.rc.api.IssueStatus status, java.lang.String action, java.lang.String description, java.lang.String subject, java.lang.String body, java.lang.String emailAddress, java.lang.String processDefinition, java.lang.String script, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.status = status;
		this.action = action;
		this.description = description;
		this.subject = subject;
		this.body = body;
		this.emailAddress = emailAddress;
		this.processDefinition = processDefinition;
		this.script = script;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public IssuePolicyAction(com.soffid.iam.rc.api.IssueStatus status, java.lang.String action)
	{
		super();
		this.status = status;
		this.action = action;
	}

	public IssuePolicyAction(IssuePolicyAction otherBean)
	{
		this(otherBean.id, otherBean.status, otherBean.action, otherBean.description, otherBean.subject, otherBean.body, otherBean.emailAddress, otherBean.processDefinition, otherBean.script, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", status: ");
		b.append (this.status);
		b.append (", action: ");
		b.append (this.action);
		b.append (", description: ");
		b.append (this.description);
		b.append (", subject: ");
		b.append (this.subject);
		b.append (", body: ");
		b.append (this.body);
		b.append (", emailAddress: ");
		b.append (this.emailAddress);
		b.append (", processDefinition: ");
		b.append (this.processDefinition);
		b.append (", script: ");
		b.append (this.script);
		b.append (", createdOn: ");
		b.append (this.createdOn);
		b.append (", createdBy: ");
		b.append (this.createdBy);
		b.append (", updatedOn: ");
		b.append (this.updatedOn);
		b.append (", updatedBy: ");
		b.append (this.updatedBy);
		b.append (", deletedOn: ");
		b.append (this.deletedOn);
		b.append (", deletedBy: ");
		b.append (this.deletedBy);
		b.append ("]");
		return b.toString();
	}

}
