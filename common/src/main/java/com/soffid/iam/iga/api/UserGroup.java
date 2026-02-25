//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject UserGroup
 **/
public class UserGroup

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute user

	 */
	private java.lang.String user;

	/**
	 * Attribute group

	 */
	private java.lang.String group;

	/**
	 * Attribute groupDescription

	 */
	private java.lang.String groupDescription;

	/**
	 * Attribute groupId

	 */
	private java.lang.Long groupId;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute userId

	 */
	private java.lang.Long userId;

	/**
	 * Attribute fullName

	 */
	private java.lang.String fullName;

	/**
	 * Attribute start

	 */
	private java.util.Date start;

	/**
	 * Attribute end

	 */
	private java.util.Date end;

	/**
	 * Attribute disabled

	 */
	private java.lang.Boolean disabled = Boolean.FALSE;

	/**
	 * Attribute primaryGroup
	 * This column indicates that this membership is an historic snapshot of a primary group membership

	 */
	private java.lang.Boolean primaryGroup = Boolean.FALSE;

	/**
	 * Attribute attributes
	 * User group custom attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes = new java.util.HashMap<String,Object>();

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

	public UserGroup()
	{
	}

	public UserGroup(java.lang.String user, java.lang.String group, java.lang.String groupDescription, java.lang.Long groupId, java.lang.Long id, java.lang.Long userId, java.lang.String fullName, java.util.Date start, java.util.Date end, java.lang.Boolean disabled, java.lang.Boolean primaryGroup, java.util.Map<java.lang.String,java.lang.Object> attributes, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.user = user;
		this.group = group;
		this.groupDescription = groupDescription;
		this.groupId = groupId;
		this.id = id;
		this.userId = userId;
		this.fullName = fullName;
		this.start = start;
		this.end = end;
		this.disabled = disabled;
		this.primaryGroup = primaryGroup;
		this.attributes = attributes;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public UserGroup(java.lang.String user, java.lang.String group)
	{
		super();
		this.user = user;
		this.group = group;
	}

	public UserGroup(UserGroup otherBean)
	{
		this(otherBean.user, otherBean.group, otherBean.groupDescription, otherBean.groupId, otherBean.id, otherBean.userId, otherBean.fullName, otherBean.start, otherBean.end, otherBean.disabled, otherBean.primaryGroup, otherBean.attributes, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
	}

	/**
	 * Gets value for attribute user
	 */
	public java.lang.String getUser() {
		return this.user;
	}

	/**
	 * Sets value for attribute user
	 */
	public void setUser(java.lang.String user) {
		this.user = user;
	}

	/**
	 * Gets value for attribute group
	 */
	public java.lang.String getGroup() {
		return this.group;
	}

	/**
	 * Sets value for attribute group
	 */
	public void setGroup(java.lang.String group) {
		this.group = group;
	}

	/**
	 * Gets value for attribute groupDescription
	 */
	public java.lang.String getGroupDescription() {
		return this.groupDescription;
	}

	/**
	 * Sets value for attribute groupDescription
	 */
	public void setGroupDescription(java.lang.String groupDescription) {
		this.groupDescription = groupDescription;
	}

	/**
	 * Gets value for attribute groupId
	 */
	public java.lang.Long getGroupId() {
		return this.groupId;
	}

	/**
	 * Sets value for attribute groupId
	 */
	public void setGroupId(java.lang.Long groupId) {
		this.groupId = groupId;
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
	 * Gets value for attribute userId
	 */
	public java.lang.Long getUserId() {
		return this.userId;
	}

	/**
	 * Sets value for attribute userId
	 */
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets value for attribute fullName
	 */
	public java.lang.String getFullName() {
		return this.fullName;
	}

	/**
	 * Sets value for attribute fullName
	 */
	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets value for attribute start
	 */
	public java.util.Date getStart() {
		return this.start;
	}

	/**
	 * Sets value for attribute start
	 */
	public void setStart(java.util.Date start) {
		this.start = start;
	}

	/**
	 * Gets value for attribute end
	 */
	public java.util.Date getEnd() {
		return this.end;
	}

	/**
	 * Sets value for attribute end
	 */
	public void setEnd(java.util.Date end) {
		this.end = end;
	}

	/**
	 * Gets value for attribute disabled
	 */
	public java.lang.Boolean getDisabled() {
		return this.disabled;
	}

	/**
	 * Sets value for attribute disabled
	 */
	public void setDisabled(java.lang.Boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * Gets value for attribute primaryGroup
	 */
	public java.lang.Boolean getPrimaryGroup() {
		return this.primaryGroup;
	}

	/**
	 * Sets value for attribute primaryGroup
	 */
	public void setPrimaryGroup(java.lang.Boolean primaryGroup) {
		this.primaryGroup = primaryGroup;
	}

	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Map<java.lang.String,java.lang.Object> getAttributes() {
		return this.attributes;
	}

	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Map<java.lang.String,java.lang.Object> attributes) {
		this.attributes = attributes;
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
		b.append ("[user: ");
		b.append (this.user);
		b.append (", group: ");
		b.append (this.group);
		b.append (", groupDescription: ");
		b.append (this.groupDescription);
		b.append (", groupId: ");
		b.append (this.groupId);
		b.append (", id: ");
		b.append (this.id);
		b.append (", userId: ");
		b.append (this.userId);
		b.append (", fullName: ");
		b.append (this.fullName);
		b.append (", start: ");
		b.append (this.start);
		b.append (", end: ");
		b.append (this.end);
		b.append (", disabled: ");
		b.append (this.disabled);
		b.append (", primaryGroup: ");
		b.append (this.primaryGroup);
		b.append (", attributes: ");
		b.append (this.attributes);
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
