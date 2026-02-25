//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject Printer
 **/
public class Printer

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute model

	 */
	private java.lang.String model;

	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute hostName

	 */
	private java.lang.String hostName;

	/**
	 * Attribute local

	 */
	private java.lang.Boolean local;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute users

	 */
	private java.util.List<java.lang.String> users;

	/**
	 * Attribute groups

	 */
	private java.util.List<java.lang.String> groups;

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

	public Printer()
	{
	}

	public Printer(java.lang.String model, java.lang.String name, java.lang.String description, java.lang.String hostName, java.lang.Boolean local, java.lang.Long id, java.util.List<java.lang.String> users, java.util.List<java.lang.String> groups, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.model = model;
		this.name = name;
		this.description = description;
		this.hostName = hostName;
		this.local = local;
		this.id = id;
		this.users = users;
		this.groups = groups;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public Printer(java.lang.String name, java.lang.String description)
	{
		super();
		this.name = name;
		this.description = description;
	}

	public Printer(Printer otherBean)
	{
		this(otherBean.model, otherBean.name, otherBean.description, otherBean.hostName, otherBean.local, otherBean.id, otherBean.users, otherBean.groups, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
	}

	/**
	 * Gets value for attribute model
	 */
	public java.lang.String getModel() {
		return this.model;
	}

	/**
	 * Sets value for attribute model
	 */
	public void setModel(java.lang.String model) {
		this.model = model;
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
	 * Gets value for attribute name
	 */
	public java.lang.String getCode() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setCode(java.lang.String name) {
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
	 * Gets value for attribute local
	 */
	public java.lang.Boolean getLocal() {
		return this.local;
	}

	/**
	 * Sets value for attribute local
	 */
	public void setLocal(java.lang.Boolean local) {
		this.local = local;
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
	 * Gets value for attribute users
	 */
	public java.util.List<java.lang.String> getUsers() {
		return this.users;
	}

	/**
	 * Sets value for attribute users
	 */
	public void setUsers(java.util.List<java.lang.String> users) {
		this.users = users;
	}

	/**
	 * Gets value for attribute groups
	 */
	public java.util.List<java.lang.String> getGroups() {
		return this.groups;
	}

	/**
	 * Sets value for attribute groups
	 */
	public void setGroups(java.util.List<java.lang.String> groups) {
		this.groups = groups;
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
		b.append ("[model: ");
		b.append (this.model);
		b.append (", name: ");
		b.append (this.name);
		b.append (", description: ");
		b.append (this.description);
		b.append (", hostName: ");
		b.append (this.hostName);
		b.append (", local: ");
		b.append (this.local);
		b.append (", id: ");
		b.append (this.id);
		b.append (", users: ");
		b.append (this.users);
		b.append (", groups: ");
		b.append (this.groups);
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
