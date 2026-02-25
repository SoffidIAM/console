//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject VaultFolder
 **/
public class VaultFolder

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
	 * Attribute personal

	 */
	private boolean personal;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute parentId

	 */
	private java.lang.Long parentId;

	/**
	 * Attribute parentFolder

	 */
	private java.lang.String parentFolder;

	/**
	 * Attribute grantedGroups
	 * Groups that can use the account using SSO

	 */
	private java.util.Collection<java.lang.String> grantedGroups = new java.util.LinkedList<String>();

	/**
	 * Attribute grantedUsers
	 * Users that can use the account using SSO

	 */
	private java.util.Collection<java.lang.String> grantedUsers = new java.util.LinkedList<String>();

	/**
	 * Attribute grantedRoles
	 * Roles that can use the account using SSO

	 */
	private java.util.Collection<java.lang.String> grantedRoles = new java.util.LinkedList<String>();

	/**
	 * Attribute managerGroups
	 * Groups that can use the account using SSO &amp; Self Service

	 */
	private java.util.Collection<java.lang.String> managerGroups = new java.util.LinkedList<String>();

	/**
	 * Attribute managerUsers
	 * Users that can use the account using SSO &amp; Self Service

	 */
	private java.util.Collection<java.lang.String> managerUsers = new java.util.LinkedList<String>();

	/**
	 * Attribute managerRoles
	 * Roles that can use the account using SSO &amp; Self Service

	 */
	private java.util.Collection<java.lang.String> managerRoles = new java.util.LinkedList<String>();

	/**
	 * Attribute ownerGroups
	 * Groups that can manage the account using SSO &amp; Self Service &amp; Console

	 */
	private java.util.Collection<java.lang.String> ownerGroups = new java.util.LinkedList<String>();

	/**
	 * Attribute ownerUsers
	 * Users that can use the account using SSO &amp; Self Service &amp; Console

	 */
	private java.util.Collection<java.lang.String> ownerUsers = new java.util.LinkedList<String>();

	/**
	 * Attribute ownerRoles
	 * Roles that can use the account using SSO &amp; Self Serva	ice &amp; Console

	 */
	private java.util.Collection<java.lang.String> ownerRoles = new java.util.LinkedList<String>();

	/**
	 * Attribute navigateGroups
	 * Groups that can navigate

	 */
	private java.util.Collection<java.lang.String> navigateGroups = new java.util.LinkedList<String>();

	/**
	 * Attribute navigateUsers
	 * Users that can navigate

	 */
	private java.util.Collection<java.lang.String> navigateUsers = new java.util.LinkedList<String>();

	/**
	 * Attribute navigateRoles
	 * Roles that can use the account using SSO &amp; Self Service &amp; Console

	 */
	private java.util.Collection<java.lang.String> navigateRoles = new java.util.LinkedList<String>();

	/**
	 * Attribute accessLevel
	 * Effective access level

	 */
	private com.soffid.iam.base.api.AccountAccessLevelEnum accessLevel;

	/**
	 * Attribute pamPolicy
	 * PAM Policy

	 */
	private java.lang.String pamPolicy;

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

	public VaultFolder()
	{
	}

	public VaultFolder(java.lang.Long id, java.lang.String name, boolean personal, java.lang.String description, java.lang.Long parentId, java.lang.String parentFolder, java.util.Collection<java.lang.String> grantedGroups, java.util.Collection<java.lang.String> grantedUsers, java.util.Collection<java.lang.String> grantedRoles, java.util.Collection<java.lang.String> managerGroups, java.util.Collection<java.lang.String> managerUsers, java.util.Collection<java.lang.String> managerRoles, java.util.Collection<java.lang.String> ownerGroups, java.util.Collection<java.lang.String> ownerUsers, java.util.Collection<java.lang.String> ownerRoles, java.util.Collection<java.lang.String> navigateGroups, java.util.Collection<java.lang.String> navigateUsers, java.util.Collection<java.lang.String> navigateRoles, com.soffid.iam.base.api.AccountAccessLevelEnum accessLevel, java.lang.String pamPolicy, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.name = name;
		this.personal = personal;
		this.description = description;
		this.parentId = parentId;
		this.parentFolder = parentFolder;
		this.grantedGroups = grantedGroups;
		this.grantedUsers = grantedUsers;
		this.grantedRoles = grantedRoles;
		this.managerGroups = managerGroups;
		this.managerUsers = managerUsers;
		this.managerRoles = managerRoles;
		this.ownerGroups = ownerGroups;
		this.ownerUsers = ownerUsers;
		this.ownerRoles = ownerRoles;
		this.navigateGroups = navigateGroups;
		this.navigateUsers = navigateUsers;
		this.navigateRoles = navigateRoles;
		this.accessLevel = accessLevel;
		this.pamPolicy = pamPolicy;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public VaultFolder(java.lang.String name, boolean personal, java.lang.String description)
	{
		super();
		this.name = name;
		this.personal = personal;
		this.description = description;
	}

	public VaultFolder(VaultFolder otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.personal, otherBean.description, otherBean.parentId, otherBean.parentFolder, otherBean.grantedGroups, otherBean.grantedUsers, otherBean.grantedRoles, otherBean.managerGroups, otherBean.managerUsers, otherBean.managerRoles, otherBean.ownerGroups, otherBean.ownerUsers, otherBean.ownerRoles, otherBean.navigateGroups, otherBean.navigateUsers, otherBean.navigateRoles, otherBean.accessLevel, otherBean.pamPolicy, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute personal
	 */
	public boolean isPersonal() {
		return this.personal;
	}

	/**
	 * Sets value for attribute personal
	 */
	public void setPersonal(boolean personal) {
		this.personal = personal;
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
	 * Gets value for attribute parentId
	 */
	public java.lang.Long getParentId() {
		return this.parentId;
	}

	/**
	 * Sets value for attribute parentId
	 */
	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets value for attribute parentFolder
	 */
	public java.lang.String getParentFolder() {
		return this.parentFolder;
	}

	/**
	 * Sets value for attribute parentFolder
	 */
	public void setParentFolder(java.lang.String parentFolder) {
		this.parentFolder = parentFolder;
	}

	/**
	 * Gets value for attribute grantedGroups
	 */
	public java.util.Collection<java.lang.String> getGrantedGroups() {
		return this.grantedGroups;
	}

	/**
	 * Sets value for attribute grantedGroups
	 */
	public void setGrantedGroups(java.util.Collection<java.lang.String> grantedGroups) {
		this.grantedGroups = grantedGroups;
	}

	/**
	 * Gets value for attribute grantedUsers
	 */
	public java.util.Collection<java.lang.String> getGrantedUsers() {
		return this.grantedUsers;
	}

	/**
	 * Sets value for attribute grantedUsers
	 */
	public void setGrantedUsers(java.util.Collection<java.lang.String> grantedUsers) {
		this.grantedUsers = grantedUsers;
	}

	/**
	 * Gets value for attribute grantedRoles
	 */
	public java.util.Collection<java.lang.String> getGrantedRoles() {
		return this.grantedRoles;
	}

	/**
	 * Sets value for attribute grantedRoles
	 */
	public void setGrantedRoles(java.util.Collection<java.lang.String> grantedRoles) {
		this.grantedRoles = grantedRoles;
	}

	/**
	 * Gets value for attribute managerGroups
	 */
	public java.util.Collection<java.lang.String> getManagerGroups() {
		return this.managerGroups;
	}

	/**
	 * Sets value for attribute managerGroups
	 */
	public void setManagerGroups(java.util.Collection<java.lang.String> managerGroups) {
		this.managerGroups = managerGroups;
	}

	/**
	 * Gets value for attribute managerUsers
	 */
	public java.util.Collection<java.lang.String> getManagerUsers() {
		return this.managerUsers;
	}

	/**
	 * Sets value for attribute managerUsers
	 */
	public void setManagerUsers(java.util.Collection<java.lang.String> managerUsers) {
		this.managerUsers = managerUsers;
	}

	/**
	 * Gets value for attribute managerRoles
	 */
	public java.util.Collection<java.lang.String> getManagerRoles() {
		return this.managerRoles;
	}

	/**
	 * Sets value for attribute managerRoles
	 */
	public void setManagerRoles(java.util.Collection<java.lang.String> managerRoles) {
		this.managerRoles = managerRoles;
	}

	/**
	 * Gets value for attribute ownerGroups
	 */
	public java.util.Collection<java.lang.String> getOwnerGroups() {
		return this.ownerGroups;
	}

	/**
	 * Sets value for attribute ownerGroups
	 */
	public void setOwnerGroups(java.util.Collection<java.lang.String> ownerGroups) {
		this.ownerGroups = ownerGroups;
	}

	/**
	 * Gets value for attribute ownerUsers
	 */
	public java.util.Collection<java.lang.String> getOwnerUsers() {
		return this.ownerUsers;
	}

	/**
	 * Sets value for attribute ownerUsers
	 */
	public void setOwnerUsers(java.util.Collection<java.lang.String> ownerUsers) {
		this.ownerUsers = ownerUsers;
	}

	/**
	 * Gets value for attribute ownerRoles
	 */
	public java.util.Collection<java.lang.String> getOwnerRoles() {
		return this.ownerRoles;
	}

	/**
	 * Sets value for attribute ownerRoles
	 */
	public void setOwnerRoles(java.util.Collection<java.lang.String> ownerRoles) {
		this.ownerRoles = ownerRoles;
	}

	/**
	 * Gets value for attribute navigateGroups
	 */
	public java.util.Collection<java.lang.String> getNavigateGroups() {
		return this.navigateGroups;
	}

	/**
	 * Sets value for attribute navigateGroups
	 */
	public void setNavigateGroups(java.util.Collection<java.lang.String> navigateGroups) {
		this.navigateGroups = navigateGroups;
	}

	/**
	 * Gets value for attribute navigateUsers
	 */
	public java.util.Collection<java.lang.String> getNavigateUsers() {
		return this.navigateUsers;
	}

	/**
	 * Sets value for attribute navigateUsers
	 */
	public void setNavigateUsers(java.util.Collection<java.lang.String> navigateUsers) {
		this.navigateUsers = navigateUsers;
	}

	/**
	 * Gets value for attribute navigateRoles
	 */
	public java.util.Collection<java.lang.String> getNavigateRoles() {
		return this.navigateRoles;
	}

	/**
	 * Sets value for attribute navigateRoles
	 */
	public void setNavigateRoles(java.util.Collection<java.lang.String> navigateRoles) {
		this.navigateRoles = navigateRoles;
	}

	/**
	 * Gets value for attribute accessLevel
	 */
	public com.soffid.iam.base.api.AccountAccessLevelEnum getAccessLevel() {
		return this.accessLevel;
	}

	/**
	 * Sets value for attribute accessLevel
	 */
	public void setAccessLevel(com.soffid.iam.base.api.AccountAccessLevelEnum accessLevel) {
		this.accessLevel = accessLevel;
	}

	/**
	 * Gets value for attribute pamPolicy
	 */
	public java.lang.String getPamPolicy() {
		return this.pamPolicy;
	}

	/**
	 * Sets value for attribute pamPolicy
	 */
	public void setPamPolicy(java.lang.String pamPolicy) {
		this.pamPolicy = pamPolicy;
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
		b.append (", personal: ");
		b.append (this.personal);
		b.append (", description: ");
		b.append (this.description);
		b.append (", parentId: ");
		b.append (this.parentId);
		b.append (", parentFolder: ");
		b.append (this.parentFolder);
		b.append (", grantedGroups: ");
		b.append (this.grantedGroups);
		b.append (", grantedUsers: ");
		b.append (this.grantedUsers);
		b.append (", grantedRoles: ");
		b.append (this.grantedRoles);
		b.append (", managerGroups: ");
		b.append (this.managerGroups);
		b.append (", managerUsers: ");
		b.append (this.managerUsers);
		b.append (", managerRoles: ");
		b.append (this.managerRoles);
		b.append (", ownerGroups: ");
		b.append (this.ownerGroups);
		b.append (", ownerUsers: ");
		b.append (this.ownerUsers);
		b.append (", ownerRoles: ");
		b.append (this.ownerRoles);
		b.append (", navigateGroups: ");
		b.append (this.navigateGroups);
		b.append (", navigateUsers: ");
		b.append (this.navigateUsers);
		b.append (", navigateRoles: ");
		b.append (this.navigateRoles);
		b.append (", accessLevel: ");
		b.append (this.accessLevel);
		b.append (", pamPolicy: ");
		b.append (this.pamPolicy);
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
