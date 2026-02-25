//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity InformationSystemEntity
 */

public abstract class InformationSystemEntity {

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
	 * Attribute relativeName
	 */
	private java.lang.String relativeName;
	/**
	 * Gets value for attribute relativeName
	 */
	public java.lang.String getRelativeName() {
		return this.relativeName;
	}
	/**
	 * Sets value for attribute relativeName
	 */
	public void setRelativeName(java.lang.String relativeName) {
		this.relativeName = relativeName;
	}
	/**
	 * Attribute parent
	 */
	private com.soffid.iam.iga.model.InformationSystemEntity parent;
	/**
	 * Gets value for attribute parent
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity getParent() {
		return this.parent;
	}
	/**
	 * Sets value for attribute parent
	 */
	public void setParent(com.soffid.iam.iga.model.InformationSystemEntity parent) {
		this.parent = parent;
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
	 * Attribute sourceDir
	 */
	private java.lang.String sourceDir;
	/**
	 * Gets value for attribute sourceDir
	 */
	public java.lang.String getSourceDir() {
		return this.sourceDir;
	}
	/**
	 * Sets value for attribute sourceDir
	 */
	public void setSourceDir(java.lang.String sourceDir) {
		this.sourceDir = sourceDir;
	}
	/**
	 * Attribute targetDir
	 */
	private java.lang.String targetDir;
	/**
	 * Gets value for attribute targetDir
	 */
	public java.lang.String getTargetDir() {
		return this.targetDir;
	}
	/**
	 * Sets value for attribute targetDir
	 */
	public void setTargetDir(java.lang.String targetDir) {
		this.targetDir = targetDir;
	}
	/**
	 * Attribute database
	 */
	private java.lang.String database;
	/**
	 * Gets value for attribute database
	 */
	public java.lang.String getDatabase() {
		return this.database;
	}
	/**
	 * Sets value for attribute database
	 */
	public void setDatabase(java.lang.String database) {
		this.database = database;
	}
	/**
	 * Attribute roles
	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleEntity> roles =  new java.util.HashSet<com.soffid.iam.iga.model.RoleEntity>();
	/**
	 * Gets value for attribute roles
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleEntity> getRoles() {
		return this.roles;
	}
	/**
	 * Sets value for attribute roles
	 */
	public void setRoles(java.util.Collection<com.soffid.iam.iga.model.RoleEntity> roles) {
		this.roles = roles;
	}
	/**
	 * Attribute contactPerson
	 */
	private com.soffid.iam.base.model.UserEntity contactPerson;
	/**
	 * Gets value for attribute contactPerson
	 */
	public com.soffid.iam.base.model.UserEntity getContactPerson() {
		return this.contactPerson;
	}
	/**
	 * Sets value for attribute contactPerson
	 */
	public void setContactPerson(com.soffid.iam.base.model.UserEntity contactPerson) {
		this.contactPerson = contactPerson;
	}
	/**
	 * Attribute bpmEnabled
	 */
	private java.lang.Boolean bpmEnabled;
	/**
	 * Gets value for attribute bpmEnabled
	 */
	public java.lang.Boolean getBpmEnabled() {
		return this.bpmEnabled;
	}
	/**
	 * Sets value for attribute bpmEnabled
	 */
	public void setBpmEnabled(java.lang.Boolean bpmEnabled) {
		this.bpmEnabled = bpmEnabled;
	}
	/**
	 * Attribute notificationEmails
	 */
	private java.lang.String notificationEmails;
	/**
	 * Gets value for attribute notificationEmails
	 */
	public java.lang.String getNotificationEmails() {
		return this.notificationEmails;
	}
	/**
	 * Sets value for attribute notificationEmails
	 */
	public void setNotificationEmails(java.lang.String notificationEmails) {
		this.notificationEmails = notificationEmails;
	}
	/**
	 * Attribute approvalProcess
	 * Approval process needed for workflow managed roles belonging to this application. Null value means no approval process is needed
	 */
	private java.lang.String approvalProcess;
	/**
	 * Gets value for attribute approvalProcess
	 */
	public java.lang.String getApprovalProcess() {
		return this.approvalProcess;
	}
	/**
	 * Sets value for attribute approvalProcess
	 */
	public void setApprovalProcess(java.lang.String approvalProcess) {
		this.approvalProcess = approvalProcess;
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
	 * Attribute roleDefinitionProcess
	 * Approval process needed for any change applied to this application roles. Null value means no approval process is needed
	 */
	private java.lang.String roleDefinitionProcess;
	/**
	 * Gets value for attribute roleDefinitionProcess
	 */
	public java.lang.String getRoleDefinitionProcess() {
		return this.roleDefinitionProcess;
	}
	/**
	 * Sets value for attribute roleDefinitionProcess
	 */
	public void setRoleDefinitionProcess(java.lang.String roleDefinitionProcess) {
		this.roleDefinitionProcess = roleDefinitionProcess;
	}
	/**
	 * Attribute singleRole
	 */
	private java.lang.Boolean singleRole;
	/**
	 * Gets value for attribute singleRole
	 */
	public java.lang.Boolean getSingleRole() {
		return this.singleRole;
	}
	/**
	 * Sets value for attribute singleRole
	 */
	public void setSingleRole(java.lang.Boolean singleRole) {
		this.singleRole = singleRole;
	}
	/**
	 * Attribute type
	 * Type of application: CONTAINER / BUSINESS / APPLICATION
	 */
	private com.soffid.iam.iga.api.ApplicationType type;
	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.iga.api.ApplicationType getType() {
		return this.type;
	}
	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.iga.api.ApplicationType type) {
		this.type = type;
	}
	/**
	 * Attribute sodRules
	 */
	private java.util.Collection<com.soffid.iam.rc.model.SoDRuleEntity> sodRules =  new java.util.HashSet<com.soffid.iam.rc.model.SoDRuleEntity>();
	/**
	 * Gets value for attribute sodRules
	 */
	public java.util.Collection<com.soffid.iam.rc.model.SoDRuleEntity> getSodRules() {
		return this.sodRules;
	}
	/**
	 * Sets value for attribute sodRules
	 */
	public void setSodRules(java.util.Collection<com.soffid.iam.rc.model.SoDRuleEntity> sodRules) {
		this.sodRules = sodRules;
	}
	/**
	 * Attribute attributes

	 */
	private java.util.Collection<com.soffid.iam.iga.model.InformationSystemAttributeEntity> attributes =  new java.util.HashSet<com.soffid.iam.iga.model.InformationSystemAttributeEntity>();
	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Collection<com.soffid.iam.iga.model.InformationSystemAttributeEntity> getAttributes() {
		return this.attributes;
	}
	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Collection<com.soffid.iam.iga.model.InformationSystemAttributeEntity> attributes) {
		this.attributes = attributes;
	}
	/**
	 * Attribute children

	 */
	private java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> children =  new java.util.HashSet<com.soffid.iam.iga.model.InformationSystemEntity>();
	/**
	 * Gets value for attribute children
	 */
	public java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> getChildren() {
		return this.children;
	}
	/**
	 * Sets value for attribute children
	 */
	public void setChildren(java.util.Collection<com.soffid.iam.iga.model.InformationSystemEntity> children) {
		this.children = children;
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
	 * Returns <code>true</code> if the argument is an InformationSystemEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof InformationSystemEntity))
		{
			return false;
		}
		final InformationSystemEntity that = (InformationSystemEntity)object;
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
