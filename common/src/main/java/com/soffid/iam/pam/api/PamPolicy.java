//
// (C) 2020 Soffid
//
//

package com.soffid.iam.pam.api;
/**
 * ValueObject PamPolicy
 **/
public class PamPolicy

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
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute author

	 */
	private java.lang.String author;

	/**
	 * Attribute date

	 */
	private java.util.Date date;

	/**
	 * Attribute recordingDuration

	 */
	private java.lang.Integer recordingDuration;

	/**
	 * Attribute expression

	 */
	private java.lang.String expression;

	/**
	 * Attribute priority

	 */
	private java.lang.Integer priority;

	/**
	 * Attribute justInTimePermissions

	 */
	private java.util.List<java.lang.String> justInTimePermissions;

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

	public PamPolicy()
	{
	}

	public PamPolicy(java.lang.Long id, java.lang.String name, java.lang.String description, java.lang.String author, java.util.Date date, java.lang.Integer recordingDuration, java.lang.String expression, java.lang.Integer priority, java.util.List<java.lang.String> justInTimePermissions, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.author = author;
		this.date = date;
		this.recordingDuration = recordingDuration;
		this.expression = expression;
		this.priority = priority;
		this.justInTimePermissions = justInTimePermissions;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public PamPolicy(java.lang.String name)
	{
		super();
		this.name = name;
	}

	public PamPolicy(PamPolicy otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.description, otherBean.author, otherBean.date, otherBean.recordingDuration, otherBean.expression, otherBean.priority, otherBean.justInTimePermissions, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
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
	 * Gets value for attribute author
	 */
	public java.lang.String getAuthor() {
		return this.author;
	}

	/**
	 * Sets value for attribute author
	 */
	public void setAuthor(java.lang.String author) {
		this.author = author;
	}

	/**
	 * Gets value for attribute date
	 */
	public java.util.Date getDate() {
		return this.date;
	}

	/**
	 * Sets value for attribute date
	 */
	public void setDate(java.util.Date date) {
		this.date = date;
	}

	/**
	 * Gets value for attribute recordingDuration
	 */
	public java.lang.Integer getRecordingDuration() {
		return this.recordingDuration;
	}

	/**
	 * Sets value for attribute recordingDuration
	 */
	public void setRecordingDuration(java.lang.Integer recordingDuration) {
		this.recordingDuration = recordingDuration;
	}

	/**
	 * Gets value for attribute expression
	 */
	public java.lang.String getExpression() {
		return this.expression;
	}

	/**
	 * Sets value for attribute expression
	 */
	public void setExpression(java.lang.String expression) {
		this.expression = expression;
	}

	/**
	 * Gets value for attribute priority
	 */
	public java.lang.Integer getPriority() {
		return this.priority;
	}

	/**
	 * Sets value for attribute priority
	 */
	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}

	/**
	 * Gets value for attribute justInTimePermissions
	 */
	public java.util.List<java.lang.String> getJustInTimePermissions() {
		return this.justInTimePermissions;
	}

	/**
	 * Sets value for attribute justInTimePermissions
	 */
	public void setJustInTimePermissions(java.util.List<java.lang.String> justInTimePermissions) {
		this.justInTimePermissions = justInTimePermissions;
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
		b.append (", name: ");
		b.append (this.name);
		b.append (", description: ");
		b.append (this.description);
		b.append (", author: ");
		b.append (this.author);
		b.append (", date: ");
		b.append (this.date);
		b.append (", recordingDuration: ");
		b.append (this.recordingDuration);
		b.append (", expression: ");
		b.append (this.expression);
		b.append (", priority: ");
		b.append (this.priority);
		b.append (", justInTimePermissions: ");
		b.append (this.justInTimePermissions);
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
