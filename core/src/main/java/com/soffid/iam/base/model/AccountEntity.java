//
// (c) 2014 Soffid
//
//

package com.soffid.iam.base.model;

/**
 *  Entity AccountEntity
 */

public abstract class AccountEntity {

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
	 * Attribute roles
	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> roles =  new java.util.HashSet<com.soffid.iam.iga.model.RoleAccountEntity>();
	/**
	 * Gets value for attribute roles
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> getRoles() {
		return this.roles;
	}
	/**
	 * Sets value for attribute roles
	 */
	public void setRoles(java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> roles) {
		this.roles = roles;
	}
	/**
	 * Attribute users
	 */
	private java.util.Collection<com.soffid.iam.base.model.UserAccountEntity> users =  new java.util.HashSet<com.soffid.iam.base.model.UserAccountEntity>();
	/**
	 * Gets value for attribute users
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserAccountEntity> getUsers() {
		return this.users;
	}
	/**
	 * Sets value for attribute users
	 */
	public void setUsers(java.util.Collection<com.soffid.iam.base.model.UserAccountEntity> users) {
		this.users = users;
	}
	/**
	 * Attribute type
	 */
	private com.soffid.iam.base.api.AccountType type;
	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.base.api.AccountType getType() {
		return this.type;
	}
	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.base.api.AccountType type) {
		this.type = type;
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
	 * Account key (name + system name)
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
	 * Attribute oldName
	 */
	private java.lang.String oldName;
	/**
	 * Gets value for attribute oldName
	 */
	public java.lang.String getOldName() {
		return this.oldName;
	}
	/**
	 * Sets value for attribute oldName
	 */
	public void setOldName(java.lang.String oldName) {
		this.oldName = oldName;
	}
	/**
	 * Attribute acl
	 */
	private java.util.Collection<com.soffid.iam.pam.model.AccountAccessEntity> acl =  new java.util.HashSet<com.soffid.iam.pam.model.AccountAccessEntity>();
	/**
	 * Gets value for attribute acl
	 */
	public java.util.Collection<com.soffid.iam.pam.model.AccountAccessEntity> getAcl() {
		return this.acl;
	}
	/**
	 * Sets value for attribute acl
	 */
	public void setAcl(java.util.Collection<com.soffid.iam.pam.model.AccountAccessEntity> acl) {
		this.acl = acl;
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
	 * Attribute created
	 */
	private java.util.Date created = new java.util.Date();
	/**
	 * Gets value for attribute created
	 */
	public java.util.Date getCreated() {
		return this.created;
	}
	/**
	 * Sets value for attribute created
	 */
	public void setCreated(java.util.Date created) {
		this.created = created;
	}
	/**
	 * Attribute lastChange
	 */
	private java.util.Date lastChange;
	/**
	 * Gets value for attribute lastChange
	 */
	public java.util.Date getLastChange() {
		return this.lastChange;
	}
	/**
	 * Sets value for attribute lastChange
	 */
	public void setLastChange(java.util.Date lastChange) {
		this.lastChange = lastChange;
	}
	/**
	 * Attribute lastUpdated
	 */
	private java.util.Date lastUpdated;
	/**
	 * Gets value for attribute lastUpdated
	 */
	public java.util.Date getLastUpdated() {
		return this.lastUpdated;
	}
	/**
	 * Sets value for attribute lastUpdated
	 */
	public void setLastUpdated(java.util.Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	/**
	 * Attribute lastPasswordSet
	 */
	private java.util.Date lastPasswordSet;
	/**
	 * Gets value for attribute lastPasswordSet
	 */
	public java.util.Date getLastPasswordSet() {
		return this.lastPasswordSet;
	}
	/**
	 * Sets value for attribute lastPasswordSet
	 */
	public void setLastPasswordSet(java.util.Date lastPasswordSet) {
		this.lastPasswordSet = lastPasswordSet;
	}
	/**
	 * Attribute passwordExpiration
	 */
	private java.util.Date passwordExpiration;
	/**
	 * Gets value for attribute passwordExpiration
	 */
	public java.util.Date getPasswordExpiration() {
		return this.passwordExpiration;
	}
	/**
	 * Sets value for attribute passwordExpiration
	 */
	public void setPasswordExpiration(java.util.Date passwordExpiration) {
		this.passwordExpiration = passwordExpiration;
	}
	/**
	 * Attribute lastLogin
	 */
	private java.util.Date lastLogin;
	/**
	 * Gets value for attribute lastLogin
	 */
	public java.util.Date getLastLogin() {
		return this.lastLogin;
	}
	/**
	 * Sets value for attribute lastLogin
	 */
	public void setLastLogin(java.util.Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	/**
	 * Attribute secrets
	 */
	private java.lang.String secrets;
	/**
	 * Gets value for attribute secrets
	 */
	public java.lang.String getSecrets() {
		return this.secrets;
	}
	/**
	 * Sets value for attribute secrets
	 */
	public void setSecrets(java.lang.String secrets) {
		this.secrets = secrets;
	}
	/**
	 * Attribute sshPublicKey
	 */
	private java.lang.String sshPublicKey;
	/**
	 * Gets value for attribute sshPublicKey
	 */
	public java.lang.String getSshPublicKey() {
		return this.sshPublicKey;
	}
	/**
	 * Sets value for attribute sshPublicKey
	 */
	public void setSshPublicKey(java.lang.String sshPublicKey) {
		this.sshPublicKey = sshPublicKey;
	}
	/**
	 * Attribute disabled
	 * Do not use. Use status instead
	 */
	private boolean disabled = false;
	/**
	 * Gets value for attribute disabled
	 */
	public boolean isDisabled() {
		return this.disabled;
	}
	/**
	 * Sets value for attribute disabled
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	/**
	 * Attribute status
	 */
	private com.soffid.iam.base.api.AccountStatus status;
	/**
	 * Gets value for attribute status
	 */
	public com.soffid.iam.base.api.AccountStatus getStatus() {
		return this.status;
	}
	/**
	 * Sets value for attribute status
	 */
	public void setStatus(com.soffid.iam.base.api.AccountStatus status) {
		this.status = status;
	}
	/**
	 * Attribute folder
	 */
	private com.soffid.iam.am.model.VaultFolderEntity folder;
	/**
	 * Gets value for attribute folder
	 */
	public com.soffid.iam.am.model.VaultFolderEntity getFolder() {
		return this.folder;
	}
	/**
	 * Sets value for attribute folder
	 */
	public void setFolder(com.soffid.iam.am.model.VaultFolderEntity folder) {
		this.folder = folder;
	}
	/**
	 * Attribute inheritNewPermissions
	 */
	private java.lang.Boolean inheritNewPermissions = false;
	/**
	 * Gets value for attribute inheritNewPermissions
	 */
	public java.lang.Boolean getInheritNewPermissions() {
		return this.inheritNewPermissions;
	}
	/**
	 * Sets value for attribute inheritNewPermissions
	 */
	public void setInheritNewPermissions(java.lang.Boolean inheritNewPermissions) {
		this.inheritNewPermissions = inheritNewPermissions;
	}
	/**
	 * Attribute loginUrl
	 */
	private java.lang.String loginUrl;
	/**
	 * Gets value for attribute loginUrl
	 */
	public java.lang.String getLoginUrl() {
		return this.loginUrl;
	}
	/**
	 * Sets value for attribute loginUrl
	 */
	public void setLoginUrl(java.lang.String loginUrl) {
		this.loginUrl = loginUrl;
	}
	/**
	 * Attribute loginName
	 * Login name. Used for SSO accounts
	 */
	private java.lang.String loginName;
	/**
	 * Gets value for attribute loginName
	 */
	public java.lang.String getLoginName() {
		return this.loginName;
	}
	/**
	 * Sets value for attribute loginName
	 */
	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}
	/**
	 * Attribute launchType
	 */
	private com.soffid.iam.am.api.LaunchType launchType;
	/**
	 * Gets value for attribute launchType
	 */
	public com.soffid.iam.am.api.LaunchType getLaunchType() {
		return this.launchType;
	}
	/**
	 * Sets value for attribute launchType
	 */
	public void setLaunchType(com.soffid.iam.am.api.LaunchType launchType) {
		this.launchType = launchType;
	}
	/**
	 * Attribute serverType
	 * Server type for password synchronization
	 */
	private java.lang.String serverType;
	/**
	 * Gets value for attribute serverType
	 */
	public java.lang.String getServerType() {
		return this.serverType;
	}
	/**
	 * Sets value for attribute serverType
	 */
	public void setServerType(java.lang.String serverType) {
		this.serverType = serverType;
	}
	/**
	 * Attribute serverName
	 * Server name. Used for SSO accounts
	 */
	private java.lang.String serverName;
	/**
	 * Gets value for attribute serverName
	 */
	public java.lang.String getServerName() {
		return this.serverName;
	}
	/**
	 * Sets value for attribute serverName
	 */
	public void setServerName(java.lang.String serverName) {
		this.serverName = serverName;
	}
	/**
	 * Attribute jumpServerGroup
	 */
	private com.soffid.iam.pam.model.JumpServerGroupEntity jumpServerGroup;
	/**
	 * Gets value for attribute jumpServerGroup
	 */
	public com.soffid.iam.pam.model.JumpServerGroupEntity getJumpServerGroup() {
		return this.jumpServerGroup;
	}
	/**
	 * Sets value for attribute jumpServerGroup
	 */
	public void setJumpServerGroup(com.soffid.iam.pam.model.JumpServerGroupEntity jumpServerGroup) {
		this.jumpServerGroup = jumpServerGroup;
	}
	/**
	 * Attribute passwords
	 */
	private java.util.Collection<com.soffid.iam.am.model.AccountPasswordEntity> passwords =  new java.util.HashSet<com.soffid.iam.am.model.AccountPasswordEntity>();
	/**
	 * Gets value for attribute passwords
	 */
	public java.util.Collection<com.soffid.iam.am.model.AccountPasswordEntity> getPasswords() {
		return this.passwords;
	}
	/**
	 * Sets value for attribute passwords
	 */
	public void setPasswords(java.util.Collection<com.soffid.iam.am.model.AccountPasswordEntity> passwords) {
		this.passwords = passwords;
	}
	/**
	 * Attribute passwordPolicy
	 */
	private com.soffid.iam.base.model.UserTypeEntity passwordPolicy;
	/**
	 * Gets value for attribute passwordPolicy
	 */
	public com.soffid.iam.base.model.UserTypeEntity getPasswordPolicy() {
		return this.passwordPolicy;
	}
	/**
	 * Sets value for attribute passwordPolicy
	 */
	public void setPasswordPolicy(com.soffid.iam.base.model.UserTypeEntity passwordPolicy) {
		this.passwordPolicy = passwordPolicy;
	}
	/**
	 * Attribute passwordStatus
	 */
	private java.lang.String passwordStatus;
	/**
	 * Gets value for attribute passwordStatus
	 */
	public java.lang.String getPasswordStatus() {
		return this.passwordStatus;
	}
	/**
	 * Sets value for attribute passwordStatus
	 */
	public void setPasswordStatus(java.lang.String passwordStatus) {
		this.passwordStatus = passwordStatus;
	}
	/**
	 * Attribute snapshot
	 */
	private com.soffid.iam.iga.model.AccountSnapshotEntity snapshot;
	/**
	 * Gets value for attribute snapshot
	 */
	public com.soffid.iam.iga.model.AccountSnapshotEntity getSnapshot() {
		return this.snapshot;
	}
	/**
	 * Sets value for attribute snapshot
	 */
	public void setSnapshot(com.soffid.iam.iga.model.AccountSnapshotEntity snapshot) {
		this.snapshot = snapshot;
	}
	/**
	 * Attribute credentialType
	 */
	private com.soffid.iam.base.api.CredentialTypeEnum credentialType;
	/**
	 * Gets value for attribute credentialType
	 */
	public com.soffid.iam.base.api.CredentialTypeEnum getCredentialType() {
		return this.credentialType;
	}
	/**
	 * Sets value for attribute credentialType
	 */
	public void setCredentialType(com.soffid.iam.base.api.CredentialTypeEnum credentialType) {
		this.credentialType = credentialType;
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
	 * Attribute attributes

	 */
	private java.util.Collection<com.soffid.iam.base.model.AccountAttributeEntity> attributes =  new java.util.HashSet<com.soffid.iam.base.model.AccountAttributeEntity>();
	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Collection<com.soffid.iam.base.model.AccountAttributeEntity> getAttributes() {
		return this.attributes;
	}
	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Collection<com.soffid.iam.base.model.AccountAttributeEntity> attributes) {
		this.attributes = attributes;
	}
	/**
	 * Attribute services

	 */
	private java.util.Collection<com.soffid.iam.pam.model.HostServiceEntity> services =  new java.util.HashSet<com.soffid.iam.pam.model.HostServiceEntity>();
	/**
	 * Gets value for attribute services
	 */
	public java.util.Collection<com.soffid.iam.pam.model.HostServiceEntity> getServices() {
		return this.services;
	}
	/**
	 * Sets value for attribute services
	 */
	public void setServices(java.util.Collection<com.soffid.iam.pam.model.HostServiceEntity> services) {
		this.services = services;
	}
	/**
	 * Attribute networkDiscovery

	 */
	private java.util.Collection<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> networkDiscovery =  new java.util.HashSet<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity>();
	/**
	 * Gets value for attribute networkDiscovery
	 */
	public java.util.Collection<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> getNetworkDiscovery() {
		return this.networkDiscovery;
	}
	/**
	 * Sets value for attribute networkDiscovery
	 */
	public void setNetworkDiscovery(java.util.Collection<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> networkDiscovery) {
		this.networkDiscovery = networkDiscovery;
	}
	/**
	 * Attribute events

	 */
	private java.util.Collection<com.soffid.iam.rc.model.IssueEntity> events =  new java.util.HashSet<com.soffid.iam.rc.model.IssueEntity>();
	/**
	 * Gets value for attribute events
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> getEvents() {
		return this.events;
	}
	/**
	 * Sets value for attribute events
	 */
	public void setEvents(java.util.Collection<com.soffid.iam.rc.model.IssueEntity> events) {
		this.events = events;
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
	 * Returns <code>true</code> if the argument is an AccountEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof AccountEntity))
		{
			return false;
		}
		final AccountEntity that = (AccountEntity)object;
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
