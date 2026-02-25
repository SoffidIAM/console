//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject RoleGrant
 **/
public class RoleGrant

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
	 * Attribute roleId

	 */
	private java.lang.Long roleId;

	/**
	 * Attribute roleName

	 */
	private java.lang.String roleName;

	/**
	 * Attribute roleDescription

	 */
	private java.lang.String roleDescription;

	/**
	 * Attribute system

	 */
	private java.lang.String system;

	/**
	 * Attribute informationSystem

	 */
	private java.lang.String informationSystem;

	/**
	 * Attribute hasDomain
	 * true if the grant has domain or scope

	 */
	private boolean hasDomain;

	/**
	 * Attribute domainValue
	 * The grant domain or scope

	 */
	private java.lang.String domainValue;

	/**
	 * Attribute domainDescription
	 * The grant domain or scope description

	 */
	private java.lang.String domainDescription;

	/**
	 * Attribute ownerAccountName

	 */
	private java.lang.String ownerAccountName;

	/**
	 * Attribute ownerInformationSystem

	 */
	private java.lang.String ownerInformationSystem;

	/**
	 * Attribute ownerSystem

	 */
	private java.lang.String ownerSystem;

	/**
	 * Attribute ownerGroup
	 * Owner group, if any. Applies to roles granted to groups

	 */
	private java.lang.String ownerGroup;

	/**
	 * Attribute ownerRole
	 * Owner role id, if any. Applies to roles granted to roles

	 */
	private java.lang.Long ownerRole;

	/**
	 * Attribute ownerRolDomainValue
	 * Scope to be applied to owner role grant

	 */
	private java.lang.String ownerRolDomainValue;

	/**
	 * Attribute ownerRoleName

	 */
	private java.lang.String ownerRoleName;

	/**
	 * Attribute ownerRoleDescription

	 */
	private java.lang.String ownerRoleDescription;

	/**
	 * Attribute user

	 */
	private java.lang.String user;

	/**
	 * Attribute startDate
	 * Rol assignment start date. Null means since now

	 */
	private java.util.Date startDate;

	/**
	 * Attribute endDate
	 * Rol assignment end date. Null means forever

	 */
	private java.util.Date endDate;

	/**
	 * Attribute enabled

	 */
	private boolean enabled = true;

	/**
	 * Attribute holderGroup
	 * This attribute holds the group name that is bound to this the role assignment. Not applicable for shared accounts

	 */
	private java.lang.String holderGroup;

	/**
	 * Attribute status

	 */
	private com.soffid.iam.iga.api.RoleDependencyStatus status = com.soffid.iam.iga.api.RoleDependencyStatus.STATUS_TOAPPROVE;

	/**
	 * Attribute mandatory
	 * True if the role is always granted. False if role grant is optional, and thus can be removed from user entitlements form

	 */
	private java.lang.Boolean mandatory = true;

	/**
	 * Attribute attributes
	 * Grant custom attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes = new java.util.HashMap<String,Object>();

	public RoleGrant()
	{
	}

	public RoleGrant(java.lang.Long id, java.lang.Long roleId, java.lang.String roleName, java.lang.String roleDescription, java.lang.String system, java.lang.String informationSystem, boolean hasDomain, java.lang.String domainValue, java.lang.String domainDescription, java.lang.String ownerAccountName, java.lang.String ownerInformationSystem, java.lang.String ownerSystem, java.lang.String ownerGroup, java.lang.Long ownerRole, java.lang.String ownerRolDomainValue, java.lang.String ownerRoleName, java.lang.String ownerRoleDescription, java.lang.String user, java.util.Date startDate, java.util.Date endDate, boolean enabled, java.lang.String holderGroup, com.soffid.iam.iga.api.RoleDependencyStatus status, java.lang.Boolean mandatory, java.util.Map<java.lang.String,java.lang.Object> attributes)
	{
		super();
		this.id = id;
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.system = system;
		this.informationSystem = informationSystem;
		this.hasDomain = hasDomain;
		this.domainValue = domainValue;
		this.domainDescription = domainDescription;
		this.ownerAccountName = ownerAccountName;
		this.ownerInformationSystem = ownerInformationSystem;
		this.ownerSystem = ownerSystem;
		this.ownerGroup = ownerGroup;
		this.ownerRole = ownerRole;
		this.ownerRolDomainValue = ownerRolDomainValue;
		this.ownerRoleName = ownerRoleName;
		this.ownerRoleDescription = ownerRoleDescription;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.enabled = enabled;
		this.holderGroup = holderGroup;
		this.status = status;
		this.mandatory = mandatory;
		this.attributes = attributes;
	}

	public RoleGrant(java.lang.Long roleId, java.lang.String roleName, java.lang.String system, boolean hasDomain, boolean enabled)
	{
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.system = system;
		this.hasDomain = hasDomain;
		this.enabled = enabled;
	}

	public RoleGrant(RoleGrant otherBean)
	{
		this(otherBean.id, otherBean.roleId, otherBean.roleName, otherBean.roleDescription, otherBean.system, otherBean.informationSystem, otherBean.hasDomain, otherBean.domainValue, otherBean.domainDescription, otherBean.ownerAccountName, otherBean.ownerInformationSystem, otherBean.ownerSystem, otherBean.ownerGroup, otherBean.ownerRole, otherBean.ownerRolDomainValue, otherBean.ownerRoleName, otherBean.ownerRoleDescription, otherBean.user, otherBean.startDate, otherBean.endDate, otherBean.enabled, otherBean.holderGroup, otherBean.status, otherBean.mandatory, otherBean.attributes);
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
	 * Gets value for attribute roleId
	 */
	public java.lang.Long getRoleId() {
		return this.roleId;
	}

	/**
	 * Sets value for attribute roleId
	 */
	public void setRoleId(java.lang.Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets value for attribute roleName
	 */
	public java.lang.String getRoleName() {
		return this.roleName;
	}

	/**
	 * Sets value for attribute roleName
	 */
	public void setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
	}

	/**
	 * Gets value for attribute roleDescription
	 */
	public java.lang.String getRoleDescription() {
		return this.roleDescription;
	}

	/**
	 * Sets value for attribute roleDescription
	 */
	public void setRoleDescription(java.lang.String roleDescription) {
		this.roleDescription = roleDescription;
	}

	/**
	 * Gets value for attribute system
	 */
	public java.lang.String getSystem() {
		return this.system;
	}

	/**
	 * Sets value for attribute system
	 */
	public void setSystem(java.lang.String system) {
		this.system = system;
	}

	/**
	 * Gets value for attribute informationSystem
	 */
	public java.lang.String getInformationSystem() {
		return this.informationSystem;
	}

	/**
	 * Sets value for attribute informationSystem
	 */
	public void setInformationSystem(java.lang.String informationSystem) {
		this.informationSystem = informationSystem;
	}

	/**
	 * Gets value for attribute hasDomain
	 */
	public boolean isHasDomain() {
		return this.hasDomain;
	}

	/**
	 * Sets value for attribute hasDomain
	 */
	public void setHasDomain(boolean hasDomain) {
		this.hasDomain = hasDomain;
	}

	/**
	 * Gets value for attribute domainValue
	 */
	public java.lang.String getDomainValue() {
		return this.domainValue;
	}

	/**
	 * Sets value for attribute domainValue
	 */
	public void setDomainValue(java.lang.String domainValue) {
		this.domainValue = domainValue;
	}

	/**
	 * Gets value for attribute domainDescription
	 */
	public java.lang.String getDomainDescription() {
		return this.domainDescription;
	}

	/**
	 * Sets value for attribute domainDescription
	 */
	public void setDomainDescription(java.lang.String domainDescription) {
		this.domainDescription = domainDescription;
	}

	/**
	 * Gets value for attribute ownerAccountName
	 */
	public java.lang.String getOwnerAccountName() {
		return this.ownerAccountName;
	}

	/**
	 * Sets value for attribute ownerAccountName
	 */
	public void setOwnerAccountName(java.lang.String ownerAccountName) {
		this.ownerAccountName = ownerAccountName;
	}

	/**
	 * Gets value for attribute ownerInformationSystem
	 */
	public java.lang.String getOwnerInformationSystem() {
		return this.ownerInformationSystem;
	}

	/**
	 * Sets value for attribute ownerInformationSystem
	 */
	public void setOwnerInformationSystem(java.lang.String ownerInformationSystem) {
		this.ownerInformationSystem = ownerInformationSystem;
	}

	/**
	 * Gets value for attribute ownerSystem
	 */
	public java.lang.String getOwnerSystem() {
		return this.ownerSystem;
	}

	/**
	 * Sets value for attribute ownerSystem
	 */
	public void setOwnerSystem(java.lang.String ownerSystem) {
		this.ownerSystem = ownerSystem;
	}

	/**
	 * Gets value for attribute ownerGroup
	 */
	public java.lang.String getOwnerGroup() {
		return this.ownerGroup;
	}

	/**
	 * Sets value for attribute ownerGroup
	 */
	public void setOwnerGroup(java.lang.String ownerGroup) {
		this.ownerGroup = ownerGroup;
	}

	/**
	 * Gets value for attribute ownerRole
	 */
	public java.lang.Long getOwnerRole() {
		return this.ownerRole;
	}

	/**
	 * Sets value for attribute ownerRole
	 */
	public void setOwnerRole(java.lang.Long ownerRole) {
		this.ownerRole = ownerRole;
	}

	/**
	 * Gets value for attribute ownerRolDomainValue
	 */
	public java.lang.String getOwnerRolDomainValue() {
		return this.ownerRolDomainValue;
	}

	/**
	 * Sets value for attribute ownerRolDomainValue
	 */
	public void setOwnerRolDomainValue(java.lang.String ownerRolDomainValue) {
		this.ownerRolDomainValue = ownerRolDomainValue;
	}

	/**
	 * Gets value for attribute ownerRoleName
	 */
	public java.lang.String getOwnerRoleName() {
		return this.ownerRoleName;
	}

	/**
	 * Sets value for attribute ownerRoleName
	 */
	public void setOwnerRoleName(java.lang.String ownerRoleName) {
		this.ownerRoleName = ownerRoleName;
	}

	/**
	 * Gets value for attribute ownerRoleDescription
	 */
	public java.lang.String getOwnerRoleDescription() {
		return this.ownerRoleDescription;
	}

	/**
	 * Sets value for attribute ownerRoleDescription
	 */
	public void setOwnerRoleDescription(java.lang.String ownerRoleDescription) {
		this.ownerRoleDescription = ownerRoleDescription;
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
	 * Gets value for attribute startDate
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets value for attribute startDate
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets value for attribute endDate
	 */
	public java.util.Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets value for attribute endDate
	 */
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets value for attribute enabled
	 */
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * Sets value for attribute enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets value for attribute holderGroup
	 */
	public java.lang.String getHolderGroup() {
		return this.holderGroup;
	}

	/**
	 * Sets value for attribute holderGroup
	 */
	public void setHolderGroup(java.lang.String holderGroup) {
		this.holderGroup = holderGroup;
	}

	/**
	 * Gets value for attribute status
	 */
	public com.soffid.iam.iga.api.RoleDependencyStatus getStatus() {
		return this.status;
	}

	/**
	 * Sets value for attribute status
	 */
	public void setStatus(com.soffid.iam.iga.api.RoleDependencyStatus status) {
		this.status = status;
	}

	/**
	 * Gets value for attribute mandatory
	 */
	public java.lang.Boolean getMandatory() {
		return this.mandatory;
	}

	/**
	 * Sets value for attribute mandatory
	 */
	public void setMandatory(java.lang.Boolean mandatory) {
		this.mandatory = mandatory;
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", roleId: ");
		b.append (this.roleId);
		b.append (", roleName: ");
		b.append (this.roleName);
		b.append (", roleDescription: ");
		b.append (this.roleDescription);
		b.append (", system: ");
		b.append (this.system);
		b.append (", informationSystem: ");
		b.append (this.informationSystem);
		b.append (", hasDomain: ");
		b.append (this.hasDomain);
		b.append (", domainValue: ");
		b.append (this.domainValue);
		b.append (", domainDescription: ");
		b.append (this.domainDescription);
		b.append (", ownerAccountName: ");
		b.append (this.ownerAccountName);
		b.append (", ownerInformationSystem: ");
		b.append (this.ownerInformationSystem);
		b.append (", ownerSystem: ");
		b.append (this.ownerSystem);
		b.append (", ownerGroup: ");
		b.append (this.ownerGroup);
		b.append (", ownerRole: ");
		b.append (this.ownerRole);
		b.append (", ownerRolDomainValue: ");
		b.append (this.ownerRolDomainValue);
		b.append (", ownerRoleName: ");
		b.append (this.ownerRoleName);
		b.append (", ownerRoleDescription: ");
		b.append (this.ownerRoleDescription);
		b.append (", user: ");
		b.append (this.user);
		b.append (", startDate: ");
		b.append (this.startDate);
		b.append (", endDate: ");
		b.append (this.endDate);
		b.append (", enabled: ");
		b.append (this.enabled);
		b.append (", holderGroup: ");
		b.append (this.holderGroup);
		b.append (", status: ");
		b.append (this.status);
		b.append (", mandatory: ");
		b.append (this.mandatory);
		b.append (", attributes: ");
		b.append (this.attributes);
		b.append ("]");
		return b.toString();
	}

}
