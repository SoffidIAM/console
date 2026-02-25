//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject MailList
 **/
public class MailList

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute name
	 * Mail list name

	 */
	private java.lang.String name;

	/**
	 * Attribute domainName
	 * Mail domain

	 */
	private java.lang.String domainName;

	/**
	 * Attribute description
	 * Mail description

	 */
	private java.lang.String description;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute lists
	 * Embeded mail lists

	 */
	private java.util.List<java.lang.String> lists;

	/**
	 * Attribute externalList
	 * External (unmanaged) mail lists that are subscribed to this one

	 */
	private java.util.List<java.lang.String> externalList;

	/**
	 * Attribute roleMembers
	 * Role whose gramtee should be subscribed to this list

	 */
	private java.util.List<java.lang.String> roleMembers;

	/**
	 * Attribute groupMembers
	 * Business units whose membes should be subscribed to this list

	 */
	private java.util.List<java.lang.String> groupMembers;

	/**
	 * Attribute usersList
	 * Contains the users that are directly subscribed to this mail list

	 */
	private java.util.List<java.lang.String> usersList;

	/**
	 * Attribute attributes
	 * Mail list custom attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes = new java.util.HashMap<String,Object>();

	/**
	 * Attribute listsBelong
	 * Mail lists that this one is subscribed to

	 */
	private java.lang.String listsBelong;

	/**
	 * Attribute explodedUsersList
	 * Contains the exploded users list, resolving any group or role membership.

	 */
	private java.util.List<java.lang.String> explodedUsersList;

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

	/**
	 * Attribute deleted


	 */
	private java.lang.Boolean deleted;

	public MailList()
	{
	}

	public MailList(java.lang.String name, java.lang.String domainName, java.lang.String description, java.lang.Long id, java.util.List<java.lang.String> lists, java.util.List<java.lang.String> externalList, java.util.List<java.lang.String> roleMembers, java.util.List<java.lang.String> groupMembers, java.util.List<java.lang.String> usersList, java.util.Map<java.lang.String,java.lang.Object> attributes, java.lang.String listsBelong, java.util.List<java.lang.String> explodedUsersList, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy, java.lang.Boolean deleted)
	{
		super();
		this.name = name;
		this.domainName = domainName;
		this.description = description;
		this.id = id;
		this.lists = lists;
		this.externalList = externalList;
		this.roleMembers = roleMembers;
		this.groupMembers = groupMembers;
		this.usersList = usersList;
		this.attributes = attributes;
		this.listsBelong = listsBelong;
		this.explodedUsersList = explodedUsersList;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
		this.deleted = deleted;
	}

	public MailList(java.lang.String name, java.lang.String domainName)
	{
		super();
		this.name = name;
		this.domainName = domainName;
	}

	public MailList(MailList otherBean)
	{
		this(otherBean.name, otherBean.domainName, otherBean.description, otherBean.id, otherBean.lists, otherBean.externalList, otherBean.roleMembers, otherBean.groupMembers, otherBean.usersList, otherBean.attributes, otherBean.listsBelong, otherBean.explodedUsersList, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy, otherBean.deleted);
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
	 * Gets value for attribute domainName
	 */
	public java.lang.String getDomainName() {
		return this.domainName;
	}

	/**
	 * Sets value for attribute domainName
	 */
	public void setDomainName(java.lang.String domainName) {
		this.domainName = domainName;
	}

	/**
	 * Gets value for attribute domainName
	 */
	public java.lang.String getDomainCode() {
		return this.domainName;
	}

	/**
	 * Sets value for attribute domainName
	 */
	public void setDomainCode(java.lang.String domainName) {
		this.domainName = domainName;
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
	 * Gets value for attribute lists
	 */
	public java.util.List<java.lang.String> getLists() {
		return this.lists;
	}

	/**
	 * Sets value for attribute lists
	 */
	public void setLists(java.util.List<java.lang.String> lists) {
		this.lists = lists;
	}

	/**
	 * Gets value for attribute externalList
	 */
	public java.util.List<java.lang.String> getExternalList() {
		return this.externalList;
	}

	/**
	 * Sets value for attribute externalList
	 */
	public void setExternalList(java.util.List<java.lang.String> externalList) {
		this.externalList = externalList;
	}

	/**
	 * Gets value for attribute roleMembers
	 */
	public java.util.List<java.lang.String> getRoleMembers() {
		return this.roleMembers;
	}

	/**
	 * Sets value for attribute roleMembers
	 */
	public void setRoleMembers(java.util.List<java.lang.String> roleMembers) {
		this.roleMembers = roleMembers;
	}

	/**
	 * Gets value for attribute groupMembers
	 */
	public java.util.List<java.lang.String> getGroupMembers() {
		return this.groupMembers;
	}

	/**
	 * Sets value for attribute groupMembers
	 */
	public void setGroupMembers(java.util.List<java.lang.String> groupMembers) {
		this.groupMembers = groupMembers;
	}

	/**
	 * Gets value for attribute usersList
	 */
	public java.util.List<java.lang.String> getUsersList() {
		return this.usersList;
	}

	/**
	 * Sets value for attribute usersList
	 */
	public void setUsersList(java.util.List<java.lang.String> usersList) {
		this.usersList = usersList;
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
	 * Gets value for attribute listsBelong
	 */
	public java.lang.String getListsBelong() {
		return this.listsBelong;
	}

	/**
	 * Sets value for attribute listsBelong
	 */
	public void setListsBelong(java.lang.String listsBelong) {
		this.listsBelong = listsBelong;
	}

	/**
	 * Gets value for attribute explodedUsersList
	 */
	public java.util.List<java.lang.String> getExplodedUsersList() {
		return this.explodedUsersList;
	}

	/**
	 * Sets value for attribute explodedUsersList
	 */
	public void setExplodedUsersList(java.util.List<java.lang.String> explodedUsersList) {
		this.explodedUsersList = explodedUsersList;
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
	 * Gets value for attribute deleted
	 */
	public java.lang.Boolean getDeleted() {
		return this.deleted;
	}

	/**
	 * Sets value for attribute deleted
	 */
	public void setDeleted(java.lang.Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[name: ");
		b.append (this.name);
		b.append (", domainName: ");
		b.append (this.domainName);
		b.append (", description: ");
		b.append (this.description);
		b.append (", id: ");
		b.append (this.id);
		b.append (", lists: ");
		b.append (this.lists);
		b.append (", externalList: ");
		b.append (this.externalList);
		b.append (", roleMembers: ");
		b.append (this.roleMembers);
		b.append (", groupMembers: ");
		b.append (this.groupMembers);
		b.append (", usersList: ");
		b.append (this.usersList);
		b.append (", attributes: ");
		b.append (this.attributes);
		b.append (", listsBelong: ");
		b.append (this.listsBelong);
		b.append (", explodedUsersList: ");
		b.append (this.explodedUsersList);
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
		b.append (", deleted: ");
		b.append (this.deleted);
		b.append ("]");
		return b.toString();
	}

}
