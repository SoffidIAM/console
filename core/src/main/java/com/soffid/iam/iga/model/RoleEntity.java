//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity RoleEntity
 */

public abstract class RoleEntity {

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
	 * Attribute key
	 * Key (name + system name)
	 */
	private java.lang.String key;
	/**
	 * Gets value for attribute key
	 */
	public java.lang.String getKey() {
		return this.key;
	}
	/**
	 * Sets value for attribute key
	 */
	public void setKey(java.lang.String key) {
		this.key = key;
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
	 * Attribute category
	 */
	private java.lang.String category;
	/**
	 * Gets value for attribute category
	 */
	public java.lang.String getCategory() {
		return this.category;
	}
	/**
	 * Sets value for attribute category
	 */
	public void setCategory(java.lang.String category) {
		this.category = category;
	}
	/**
	 * Attribute enableByDefault
	 */
	private java.lang.Boolean enableByDefault = true;
	/**
	 * Gets value for attribute enableByDefault
	 */
	public java.lang.Boolean getEnableByDefault() {
		return this.enableByDefault;
	}
	/**
	 * Sets value for attribute enableByDefault
	 */
	public void setEnableByDefault(java.lang.Boolean enableByDefault) {
		this.enableByDefault = enableByDefault;
	}
	/**
	 * Attribute password
	 */
	private java.lang.String password;
	/**
	 * Gets value for attribute password
	 */
	public java.lang.String getPassword() {
		return this.password;
	}
	/**
	 * Sets value for attribute password
	 */
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	/**
	 * Attribute informationSystem
	 */
	private com.soffid.iam.iga.model.InformationSystemEntity informationSystem;
	/**
	 * Gets value for attribute informationSystem
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity getInformationSystem() {
		return this.informationSystem;
	}
	/**
	 * Sets value for attribute informationSystem
	 */
	public void setInformationSystem(com.soffid.iam.iga.model.InformationSystemEntity informationSystem) {
		this.informationSystem = informationSystem;
	}
	/**
	 * Attribute accounts
	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> accounts =  new java.util.HashSet<com.soffid.iam.iga.model.RoleAccountEntity>();
	/**
	 * Gets value for attribute accounts
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> getAccounts() {
		return this.accounts;
	}
	/**
	 * Sets value for attribute accounts
	 */
	public void setAccounts(java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> accounts) {
		this.accounts = accounts;
	}
	/**
	 * Attribute system
	 */
	private com.soffid.iam.iga.model.SystemEntity system;
	/**
	 * Gets value for attribute system
	 */
	public com.soffid.iam.iga.model.SystemEntity getSystem() {
		return this.system;
	}
	/**
	 * Sets value for attribute system
	 */
	public void setSystem(com.soffid.iam.iga.model.SystemEntity system) {
		this.system = system;
	}
	/**
	 * Attribute applicationDomain
	 */
	private com.soffid.iam.iga.model.ApplicationDomainEntity applicationDomain;
	/**
	 * Gets value for attribute applicationDomain
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity getApplicationDomain() {
		return this.applicationDomain;
	}
	/**
	 * Sets value for attribute applicationDomain
	 */
	public void setApplicationDomain(com.soffid.iam.iga.model.ApplicationDomainEntity applicationDomain) {
		this.applicationDomain = applicationDomain;
	}
	/**
	 * Attribute domainType
	 */
	private java.lang.String domainType;
	/**
	 * Gets value for attribute domainType
	 */
	public java.lang.String getDomainType() {
		return this.domainType;
	}
	/**
	 * Sets value for attribute domainType
	 */
	public void setDomainType(java.lang.String domainType) {
		this.domainType = domainType;
	}
	/**
	 * Attribute ownerRoles
	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleDependencyEntity> ownerRoles =  new java.util.HashSet<com.soffid.iam.iga.model.RoleDependencyEntity>();
	/**
	 * Gets value for attribute ownerRoles
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleDependencyEntity> getOwnerRoles() {
		return this.ownerRoles;
	}
	/**
	 * Sets value for attribute ownerRoles
	 */
	public void setOwnerRoles(java.util.Collection<com.soffid.iam.iga.model.RoleDependencyEntity> ownerRoles) {
		this.ownerRoles = ownerRoles;
	}
	/**
	 * Attribute ownedRoles
	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleDependencyEntity> ownedRoles =  new java.util.HashSet<com.soffid.iam.iga.model.RoleDependencyEntity>();
	/**
	 * Gets value for attribute ownedRoles
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleDependencyEntity> getOwnedRoles() {
		return this.ownedRoles;
	}
	/**
	 * Sets value for attribute ownedRoles
	 */
	public void setOwnedRoles(java.util.Collection<com.soffid.iam.iga.model.RoleDependencyEntity> ownedRoles) {
		this.ownedRoles = ownedRoles;
	}
	/**
	 * Attribute containerGroups
	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleGroupEntity> containerGroups =  new java.util.HashSet<com.soffid.iam.iga.model.RoleGroupEntity>();
	/**
	 * Gets value for attribute containerGroups
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleGroupEntity> getContainerGroups() {
		return this.containerGroups;
	}
	/**
	 * Sets value for attribute containerGroups
	 */
	public void setContainerGroups(java.util.Collection<com.soffid.iam.iga.model.RoleGroupEntity> containerGroups) {
		this.containerGroups = containerGroups;
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
	 * Attribute accessControl
	 */
	private java.util.Collection<com.soffid.iam.iga.model.AccessControlEntity> accessControl =  new java.util.HashSet<com.soffid.iam.iga.model.AccessControlEntity>();
	/**
	 * Gets value for attribute accessControl
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AccessControlEntity> getAccessControl() {
		return this.accessControl;
	}
	/**
	 * Sets value for attribute accessControl
	 */
	public void setAccessControl(java.util.Collection<com.soffid.iam.iga.model.AccessControlEntity> accessControl) {
		this.accessControl = accessControl;
	}
	/**
	 * Attribute manageableWF
	 */
	private java.lang.String manageableWF;
	/**
	 * Gets value for attribute manageableWF
	 */
	public java.lang.String getManageableWF() {
		return this.manageableWF;
	}
	/**
	 * Sets value for attribute manageableWF
	 */
	public void setManageableWF(java.lang.String manageableWF) {
		this.manageableWF = manageableWF;
	}
	/**
	 * Attribute notificationEntities
	 */
	private java.util.Collection<com.soffid.iam.iga.model.NoticeEntity> notificationEntities =  new java.util.HashSet<com.soffid.iam.iga.model.NoticeEntity>();
	/**
	 * Gets value for attribute notificationEntities
	 */
	public java.util.Collection<com.soffid.iam.iga.model.NoticeEntity> getNotificationEntities() {
		return this.notificationEntities;
	}
	/**
	 * Sets value for attribute notificationEntities
	 */
	public void setNotificationEntities(java.util.Collection<com.soffid.iam.iga.model.NoticeEntity> notificationEntities) {
		this.notificationEntities = notificationEntities;
	}
	/**
	 * Attribute authorizations
	 */
	private java.util.Collection<com.soffid.iam.base.model.AuthorizationEntity> authorizations =  new java.util.HashSet<com.soffid.iam.base.model.AuthorizationEntity>();
	/**
	 * Gets value for attribute authorizations
	 */
	public java.util.Collection<com.soffid.iam.base.model.AuthorizationEntity> getAuthorizations() {
		return this.authorizations;
	}
	/**
	 * Sets value for attribute authorizations
	 */
	public void setAuthorizations(java.util.Collection<com.soffid.iam.base.model.AuthorizationEntity> authorizations) {
		this.authorizations = authorizations;
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
	 * Attribute rules
	 */
	private java.util.Collection<com.soffid.iam.iga.model.RuleAssignedRoleEntity> rules =  new java.util.HashSet<com.soffid.iam.iga.model.RuleAssignedRoleEntity>();
	/**
	 * Gets value for attribute rules
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RuleAssignedRoleEntity> getRules() {
		return this.rules;
	}
	/**
	 * Sets value for attribute rules
	 */
	public void setRules(java.util.Collection<com.soffid.iam.iga.model.RuleAssignedRoleEntity> rules) {
		this.rules = rules;
	}
	/**
	 * Attribute sodRules
	 */
	private java.util.Collection<com.soffid.iam.rc.model.SoDRoleEntity> sodRules =  new java.util.HashSet<com.soffid.iam.rc.model.SoDRoleEntity>();
	/**
	 * Gets value for attribute sodRules
	 */
	public java.util.Collection<com.soffid.iam.rc.model.SoDRoleEntity> getSodRules() {
		return this.sodRules;
	}
	/**
	 * Sets value for attribute sodRules
	 */
	public void setSodRules(java.util.Collection<com.soffid.iam.rc.model.SoDRoleEntity> sodRules) {
		this.sodRules = sodRules;
	}
	/**
	 * Attribute approvalProcess
	 * When an aproval process is needed to enable this rol grants
	 */
	private java.lang.Long approvalProcess;
	/**
	 * Gets value for attribute approvalProcess
	 */
	public java.lang.Long getApprovalProcess() {
		return this.approvalProcess;
	}
	/**
	 * Sets value for attribute approvalProcess
	 */
	public void setApprovalProcess(java.lang.Long approvalProcess) {
		this.approvalProcess = approvalProcess;
	}
	/**
	 * Attribute approvalStart
	 */
	private java.util.Date approvalStart;
	/**
	 * Gets value for attribute approvalStart
	 */
	public java.util.Date getApprovalStart() {
		return this.approvalStart;
	}
	/**
	 * Sets value for attribute approvalStart
	 */
	public void setApprovalStart(java.util.Date approvalStart) {
		this.approvalStart = approvalStart;
	}
	/**
	 * Attribute approvalEnd
	 */
	private java.util.Date approvalEnd;
	/**
	 * Gets value for attribute approvalEnd
	 */
	public java.util.Date getApprovalEnd() {
		return this.approvalEnd;
	}
	/**
	 * Sets value for attribute approvalEnd
	 */
	public void setApprovalEnd(java.util.Date approvalEnd) {
		this.approvalEnd = approvalEnd;
	}
	/**
	 * Attribute externalId
	 */
	private java.lang.String externalId;
	/**
	 * Gets value for attribute externalId
	 */
	public java.lang.String getExternalId() {
		return this.externalId;
	}
	/**
	 * Sets value for attribute externalId
	 */
	public void setExternalId(java.lang.String externalId) {
		this.externalId = externalId;
	}
	/**
	 * Attribute customObjects

	 */
	private java.util.Collection<com.soffid.iam.iga.model.CustomObjectRoleEntity> customObjects =  new java.util.HashSet<com.soffid.iam.iga.model.CustomObjectRoleEntity>();
	/**
	 * Gets value for attribute customObjects
	 */
	public java.util.Collection<com.soffid.iam.iga.model.CustomObjectRoleEntity> getCustomObjects() {
		return this.customObjects;
	}
	/**
	 * Sets value for attribute customObjects
	 */
	public void setCustomObjects(java.util.Collection<com.soffid.iam.iga.model.CustomObjectRoleEntity> customObjects) {
		this.customObjects = customObjects;
	}
	/**
	 * Attribute mailLists

	 */
	private java.util.Collection<com.soffid.iam.iga.model.MailListRoleMemberEntity> mailLists =  new java.util.HashSet<com.soffid.iam.iga.model.MailListRoleMemberEntity>();
	/**
	 * Gets value for attribute mailLists
	 */
	public java.util.Collection<com.soffid.iam.iga.model.MailListRoleMemberEntity> getMailLists() {
		return this.mailLists;
	}
	/**
	 * Sets value for attribute mailLists
	 */
	public void setMailLists(java.util.Collection<com.soffid.iam.iga.model.MailListRoleMemberEntity> mailLists) {
		this.mailLists = mailLists;
	}
	/**
	 * Attribute attributes

	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleAttributeEntity> attributes =  new java.util.HashSet<com.soffid.iam.iga.model.RoleAttributeEntity>();
	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleAttributeEntity> getAttributes() {
		return this.attributes;
	}
	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Collection<com.soffid.iam.iga.model.RoleAttributeEntity> attributes) {
		this.attributes = attributes;
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
	 * Attribute deleted

	 */
	private java.lang.Boolean deleted;
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
	 * Operation isAllowed
	 * Returns true if the permission on this object is granted
	 * @param permission
	 * @return
	**/
	 public abstract boolean isAllowed(
		java.lang.String permission);

	/**
	 * Operation toRoleDescription
	 * @return
	**/
	 public abstract java.lang.String toRoleDescription();

	/**
	 * Operation toString
	 * @return
	**/
	 public abstract java.lang.String toString();

	/**
	 * Returns <code>true</code> if the argument is an RoleEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof RoleEntity))
		{
			return false;
		}
		final RoleEntity that = (RoleEntity)object;
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
