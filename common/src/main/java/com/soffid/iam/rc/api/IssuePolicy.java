//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject IssuePolicy
 **/
public class IssuePolicy

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
	 * Attribute type

	 */
	private java.lang.String type;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute actor

	 */
	private java.lang.String actor;

	/**
	 * Attribute status

	 */
	private com.soffid.iam.rc.api.IssuePolicyStatus status;

	/**
	 * Attribute actions

	 */
	private java.util.List<com.soffid.iam.rc.api.IssuePolicyAction> actions;

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

	public IssuePolicy()
	{
	}

	public IssuePolicy(java.lang.Long id, java.lang.String type, java.lang.String description, java.lang.String actor, com.soffid.iam.rc.api.IssuePolicyStatus status, java.util.List<com.soffid.iam.rc.api.IssuePolicyAction> actions, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.type = type;
		this.description = description;
		this.actor = actor;
		this.status = status;
		this.actions = actions;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public IssuePolicy(java.lang.Long id, java.lang.String type)
	{
		super();
		this.id = id;
		this.type = type;
	}

	public IssuePolicy(IssuePolicy otherBean)
	{
		this(otherBean.id, otherBean.type, otherBean.description, otherBean.actor, otherBean.status, otherBean.actions, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute actor
	 */
	public java.lang.String getActor() {
		return this.actor;
	}

	/**
	 * Sets value for attribute actor
	 */
	public void setActor(java.lang.String actor) {
		this.actor = actor;
	}

	/**
	 * Gets value for attribute status
	 */
	public com.soffid.iam.rc.api.IssuePolicyStatus getStatus() {
		return this.status;
	}

	/**
	 * Sets value for attribute status
	 */
	public void setStatus(com.soffid.iam.rc.api.IssuePolicyStatus status) {
		this.status = status;
	}

	/**
	 * Gets value for attribute actions
	 */
	public java.util.List<com.soffid.iam.rc.api.IssuePolicyAction> getActions() {
		return this.actions;
	}

	/**
	 * Sets value for attribute actions
	 */
	public void setActions(java.util.List<com.soffid.iam.rc.api.IssuePolicyAction> actions) {
		this.actions = actions;
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
		b.append (", type: ");
		b.append (this.type);
		b.append (", description: ");
		b.append (this.description);
		b.append (", actor: ");
		b.append (this.actor);
		b.append (", status: ");
		b.append (this.status);
		b.append (", actions: ");
		b.append (this.actions);
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
