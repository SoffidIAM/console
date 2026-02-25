//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity GroupEntity
 */

public abstract class GroupEntity {

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
	 * Attribute name
	 */
	private java.lang.String name;
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
	 * Attribute quota
	 */
	private java.lang.Long quota;
	/**
	 * Gets value for attribute quota
	 */
	public java.lang.Long getQuota() {
		return this.quota;
	}
	/**
	 * Sets value for attribute quota
	 */
	public void setQuota(java.lang.Long quota) {
		this.quota = quota;
	}
	/**
	 * Attribute driveLetter
	 */
	private java.lang.String driveLetter;
	/**
	 * Gets value for attribute driveLetter
	 */
	public java.lang.String getDriveLetter() {
		return this.driveLetter;
	}
	/**
	 * Sets value for attribute driveLetter
	 */
	public void setDriveLetter(java.lang.String driveLetter) {
		this.driveLetter = driveLetter;
	}
	/**
	 * Attribute parent
	 */
	private com.soffid.iam.iga.model.GroupEntity parent;
	/**
	 * Gets value for attribute parent
	 */
	public com.soffid.iam.iga.model.GroupEntity getParent() {
		return this.parent;
	}
	/**
	 * Sets value for attribute parent
	 */
	public void setParent(com.soffid.iam.iga.model.GroupEntity parent) {
		this.parent = parent;
	}
	/**
	 * Attribute printers
	 */
	private java.util.Collection<com.soffid.iam.iga.model.PrinterGroupEntity> printers =  new java.util.HashSet<com.soffid.iam.iga.model.PrinterGroupEntity>();
	/**
	 * Gets value for attribute printers
	 */
	public java.util.Collection<com.soffid.iam.iga.model.PrinterGroupEntity> getPrinters() {
		return this.printers;
	}
	/**
	 * Sets value for attribute printers
	 */
	public void setPrinters(java.util.Collection<com.soffid.iam.iga.model.PrinterGroupEntity> printers) {
		this.printers = printers;
	}
	/**
	 * Attribute secondaryGroupUsers
	 */
	private java.util.Collection<com.soffid.iam.iga.model.UserGroupEntity> secondaryGroupUsers =  new java.util.HashSet<com.soffid.iam.iga.model.UserGroupEntity>();
	/**
	 * Gets value for attribute secondaryGroupUsers
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserGroupEntity> getSecondaryGroupUsers() {
		return this.secondaryGroupUsers;
	}
	/**
	 * Sets value for attribute secondaryGroupUsers
	 */
	public void setSecondaryGroupUsers(java.util.Collection<com.soffid.iam.iga.model.UserGroupEntity> secondaryGroupUsers) {
		this.secondaryGroupUsers = secondaryGroupUsers;
	}
	/**
	 * Attribute driveServer
	 */
	private com.soffid.iam.am.model.HostEntity driveServer;
	/**
	 * Gets value for attribute driveServer
	 */
	public com.soffid.iam.am.model.HostEntity getDriveServer() {
		return this.driveServer;
	}
	/**
	 * Sets value for attribute driveServer
	 */
	public void setDriveServer(com.soffid.iam.am.model.HostEntity driveServer) {
		this.driveServer = driveServer;
	}
	/**
	 * Attribute networkAuthorization
	 */
	private java.util.Collection<com.soffid.iam.am.model.NetworkAuthorizationEntity> networkAuthorization =  new java.util.HashSet<com.soffid.iam.am.model.NetworkAuthorizationEntity>();
	/**
	 * Gets value for attribute networkAuthorization
	 */
	public java.util.Collection<com.soffid.iam.am.model.NetworkAuthorizationEntity> getNetworkAuthorization() {
		return this.networkAuthorization;
	}
	/**
	 * Sets value for attribute networkAuthorization
	 */
	public void setNetworkAuthorization(java.util.Collection<com.soffid.iam.am.model.NetworkAuthorizationEntity> networkAuthorization) {
		this.networkAuthorization = networkAuthorization;
	}
	/**
	 * Attribute children
	 */
	private java.util.Collection<com.soffid.iam.iga.model.GroupEntity> children =  new java.util.HashSet<com.soffid.iam.iga.model.GroupEntity>();
	/**
	 * Gets value for attribute children
	 */
	public java.util.Collection<com.soffid.iam.iga.model.GroupEntity> getChildren() {
		return this.children;
	}
	/**
	 * Sets value for attribute children
	 */
	public void setChildren(java.util.Collection<com.soffid.iam.iga.model.GroupEntity> children) {
		this.children = children;
	}
	/**
	 * Attribute usersRoles
	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> usersRoles =  new java.util.HashSet<com.soffid.iam.iga.model.RoleAccountEntity>();
	/**
	 * Gets value for attribute usersRoles
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> getUsersRoles() {
		return this.usersRoles;
	}
	/**
	 * Sets value for attribute usersRoles
	 */
	public void setUsersRoles(java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> usersRoles) {
		this.usersRoles = usersRoles;
	}
	/**
	 * Attribute unitType
	 */
	private com.soffid.iam.iga.model.GroupTypeEntity unitType;
	/**
	 * Gets value for attribute unitType
	 */
	public com.soffid.iam.iga.model.GroupTypeEntity getUnitType() {
		return this.unitType;
	}
	/**
	 * Sets value for attribute unitType
	 */
	public void setUnitType(com.soffid.iam.iga.model.GroupTypeEntity unitType) {
		this.unitType = unitType;
	}
	/**
	 * Attribute obsolete
	 */
	private java.lang.Boolean obsolete;
	/**
	 * Gets value for attribute obsolete
	 */
	public java.lang.Boolean getObsolete() {
		return this.obsolete;
	}
	/**
	 * Sets value for attribute obsolete
	 */
	public void setObsolete(java.lang.Boolean obsolete) {
		this.obsolete = obsolete;
	}
	/**
	 * Attribute startDate
	 */
	private java.util.Date startDate;
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
	 * Attribute endDate
	 */
	private java.util.Date endDate;
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
	 * Attribute tenant
	 */
	private com.soffid.iam.base.model.TenantEntity tenant;
	/**
	 * Gets value for attribute tenant
	 */
	public com.soffid.iam.base.model.TenantEntity getTenant() {
		return this.tenant;
	}
	/**
	 * Sets value for attribute tenant
	 */
	public void setTenant(com.soffid.iam.base.model.TenantEntity tenant) {
		this.tenant = tenant;
	}
	/**
	 * Attribute systemGroup
	 */
	private java.util.Collection<com.soffid.iam.iga.model.SystemGroupEntity> systemGroup =  new java.util.HashSet<com.soffid.iam.iga.model.SystemGroupEntity>();
	/**
	 * Gets value for attribute systemGroup
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemGroupEntity> getSystemGroup() {
		return this.systemGroup;
	}
	/**
	 * Sets value for attribute systemGroup
	 */
	public void setSystemGroup(java.util.Collection<com.soffid.iam.iga.model.SystemGroupEntity> systemGroup) {
		this.systemGroup = systemGroup;
	}
	/**
	 * Attribute grantedRoles
	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleGroupEntity> grantedRoles =  new java.util.HashSet<com.soffid.iam.iga.model.RoleGroupEntity>();
	/**
	 * Gets value for attribute grantedRoles
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleGroupEntity> getGrantedRoles() {
		return this.grantedRoles;
	}
	/**
	 * Sets value for attribute grantedRoles
	 */
	public void setGrantedRoles(java.util.Collection<com.soffid.iam.iga.model.RoleGroupEntity> grantedRoles) {
		this.grantedRoles = grantedRoles;
	}
	/**
	 * Attribute primaryGroupUsers
	 */
	private java.util.Collection<com.soffid.iam.base.model.UserEntity> primaryGroupUsers =  new java.util.HashSet<com.soffid.iam.base.model.UserEntity>();
	/**
	 * Gets value for attribute primaryGroupUsers
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserEntity> getPrimaryGroupUsers() {
		return this.primaryGroupUsers;
	}
	/**
	 * Sets value for attribute primaryGroupUsers
	 */
	public void setPrimaryGroupUsers(java.util.Collection<com.soffid.iam.base.model.UserEntity> primaryGroupUsers) {
		this.primaryGroupUsers = primaryGroupUsers;
	}
	/**
	 * Attribute accountAccess
	 */
	private java.util.Collection<com.soffid.iam.pam.model.AccountAccessEntity> accountAccess =  new java.util.HashSet<com.soffid.iam.pam.model.AccountAccessEntity>();
	/**
	 * Gets value for attribute accountAccess
	 */
	public java.util.Collection<com.soffid.iam.pam.model.AccountAccessEntity> getAccountAccess() {
		return this.accountAccess;
	}
	/**
	 * Sets value for attribute accountAccess
	 */
	public void setAccountAccess(java.util.Collection<com.soffid.iam.pam.model.AccountAccessEntity> accountAccess) {
		this.accountAccess = accountAccess;
	}
	/**
	 * Attribute holdedRoleAssignments
	 * This foreign key binds a group with all the role assignments that are granted to members of this group because they are members of this group
	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> holdedRoleAssignments =  new java.util.HashSet<com.soffid.iam.iga.model.RoleAccountEntity>();
	/**
	 * Gets value for attribute holdedRoleAssignments
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> getHoldedRoleAssignments() {
		return this.holdedRoleAssignments;
	}
	/**
	 * Sets value for attribute holdedRoleAssignments
	 */
	public void setHoldedRoleAssignments(java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> holdedRoleAssignments) {
		this.holdedRoleAssignments = holdedRoleAssignments;
	}
	/**
	 * Attribute attributes

	 */
	private java.util.Collection<com.soffid.iam.iga.model.GroupAttributeEntity> attributes =  new java.util.HashSet<com.soffid.iam.iga.model.GroupAttributeEntity>();
	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Collection<com.soffid.iam.iga.model.GroupAttributeEntity> getAttributes() {
		return this.attributes;
	}
	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Collection<com.soffid.iam.iga.model.GroupAttributeEntity> attributes) {
		this.attributes = attributes;
	}
	/**
	 * Attribute mailLists

	 */
	private java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> mailLists =  new java.util.HashSet<com.soffid.iam.iga.model.MailListGroupMemberEntity>();
	/**
	 * Gets value for attribute mailLists
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> getMailLists() {
		return this.mailLists;
	}
	/**
	 * Sets value for attribute mailLists
	 */
	public void setMailLists(java.util.Collection<com.soffid.iam.iga.model.MailListGroupMemberEntity> mailLists) {
		this.mailLists = mailLists;
	}
	/**
	 * Attribute roleScopeMailLists

	 */
	private java.util.Collection<com.soffid.iam.iga.model.MailListRoleMemberEntity> roleScopeMailLists =  new java.util.HashSet<com.soffid.iam.iga.model.MailListRoleMemberEntity>();
	/**
	 * Gets value for attribute roleScopeMailLists
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListRoleMemberEntity> getRoleScopeMailLists() {
		return this.roleScopeMailLists;
	}
	/**
	 * Sets value for attribute roleScopeMailLists
	 */
	public void setRoleScopeMailLists(java.util.Collection<com.soffid.iam.iga.model.MailListRoleMemberEntity> roleScopeMailLists) {
		this.roleScopeMailLists = roleScopeMailLists;
	}
	/**
	 * Attribute audit

	 */
	private java.util.Collection<com.soffid.iam.rc.model.AuditEntity> audit =  new java.util.HashSet<com.soffid.iam.rc.model.AuditEntity>();
	/**
	 * Gets value for attribute audit
	 */
	public java.util.Collection<com.soffid.iam.rc.model.AuditEntity> getAudit() {
		return this.audit;
	}
	/**
	 * Sets value for attribute audit
	 */
	public void setAudit(java.util.Collection<com.soffid.iam.rc.model.AuditEntity> audit) {
		this.audit = audit;
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
	 * Operation isAllowed
	 * Returns true if the permission on this object is granted
	 * @param permission
	 * @return
	**/
	 public abstract boolean isAllowed(
		java.lang.String permission);

	/**
	 * Operation toString
	 * @return
	**/
	 public abstract java.lang.String toString();

	/**
	 * Operation customCache
	**/
	 public abstract void customCache();

	/**
	 * Returns <code>true</code> if the argument is an GroupEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof GroupEntity))
		{
			return false;
		}
		final GroupEntity that = (GroupEntity)object;
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
