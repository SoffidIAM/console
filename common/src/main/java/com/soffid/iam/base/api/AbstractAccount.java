//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject AbstractAccount
 * Contains the accounts existing on a target system.
 * The SCIM attribute roles contains the account's grants as an object of class com.soffid.iam.iga.api.RoleAccount.
 * The SCIM attribute roles.role contains the roles granted to a user as an object of class com.soffid.iam.iga.api.Role.
 * When the account is of type Single-user, the SCIM attribute users.user contains the owner of the account as on object of class com.soffid.iam.base.api.User.
 * When the account is of type shared, privileged or unmanaged, the SCIM attribute acl.user contains the users with access to this account.
 * When the account is of type shared, privileged or unmanaged, the SCIM attribute acl.role contains the roles with access to this account.
 * When the account is of type shared, privileged or unmanaged, the SCIM attribute acl.group contains the groups with access to this account.

 **/
public abstract class AbstractAccount

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
	 * Attribute system
	 * System where the acounts lives

	 */
	private java.lang.String system;

	/**
	 * Attribute name
	 * Account name

	 */
	private java.lang.String name;

	/**
	 * Attribute key
	 * Account key (name + system name)

	 */
	private java.lang.String key;

	/**
	 * Attribute oldName
	 * Old account name

	 */
	private java.lang.String oldName;

	/**
	 * Attribute loginName
	 * Login name. Used for SSO accounts

	 */
	private java.lang.String loginName;

	/**
	 * Attribute description
	 * Account description

	 */
	private java.lang.String description;

	/**
	 * Attribute type
	 * Account type

	 */
	private com.soffid.iam.base.api.AccountType type;

	/**
	 * Attribute disabled
	 * True if the account is disabled. Better use status attribute

	 */
	private boolean disabled = false;

	/**
	 * Attribute status
	 * Account status: active / disabled / removed

	 */
	private com.soffid.iam.base.api.AccountStatus status;

	/**
	 * Attribute credentialType
	 * Credential type

	 */
	private com.soffid.iam.base.api.CredentialTypeEnum credentialType = com.soffid.iam.base.api.CredentialTypeEnum.CT_PASSWORD;

	/**
	 * Attribute passwordPolicy
	 * Account's user type. It is directly bound to a password policy depending on the system's password domain

	 */
	private java.lang.String passwordPolicy;

	/**
	 * Attribute ownerGroups
	 * Groups that can manage the account using SSO &amp; Self Service &amp; Console

	 */
	private java.util.Collection<java.lang.String> ownerGroups;

	/**
	 * Attribute ownerUsers
	 * Users that can use the account using SSO &amp; Self Service &amp; Console

	 */
	private java.util.Collection<java.lang.String> ownerUsers;

	/**
	 * Attribute ownerRoles
	 * Roles that can use the account using SSO &amp; Self Service &amp; Console

	 */
	private java.util.Collection<java.lang.String> ownerRoles;

	/**
	 * Attribute managerGroups
	 * Groups that can use the account using SSO &amp; Self Service

	 */
	private java.util.Collection<java.lang.String> managerGroups;

	/**
	 * Attribute managerUsers
	 * Users that can use the account using SSO &amp; Self Service

	 */
	private java.util.Collection<java.lang.String> managerUsers;

	/**
	 * Attribute managerRoles
	 * Roles that can use the account using SSO &amp; Self Service

	 */
	private java.util.Collection<java.lang.String> managerRoles;

	/**
	 * Attribute grantedGroups
	 * Groups that can use the account using SSO

	 */
	private java.util.Collection<java.lang.String> grantedGroups;

	/**
	 * Attribute grantedUsers
	 * Users that can use the account using SSO

	 */
	private java.util.Collection<java.lang.String> grantedUsers;

	/**
	 * Attribute grantedRoles
	 * Roles that can use the account using SSO

	 */
	private java.util.Collection<java.lang.String> grantedRoles;

	/**
	 * Attribute accessLevel
	 * Read only attribute. Access level. See access level constants

	 */
	private com.soffid.iam.base.api.AccountAccessLevelEnum accessLevel;

	/**
	 * Attribute serverType
	 * Server type for password synchronization

	 */
	private java.lang.String serverType;

	/**
	 * Attribute serverName
	 * Server name. Used for SSO accounts

	 */
	private java.lang.String serverName;

	/**
	 * Attribute sshPublicKey
	 * Ssh public key

	 */
	private java.lang.String sshPublicKey;

	/**
	 * Attribute vaultFolderId

	 */
	private java.lang.Long vaultFolderId;

	/**
	 * Attribute vaultFolder

	 */
	private java.lang.String vaultFolder;

	/**
	 * Attribute inheritNewPermissions

	 */
	private boolean inheritNewPermissions = false;

	/**
	 * Attribute loginUrl
	 * Login URL

	 */
	private java.lang.String loginUrl;

	/**
	 * Attribute launchType

	 */
	private com.soffid.iam.am.api.LaunchType launchType;

	/**
	 * Attribute jumpServerGroup

	 */
	private java.lang.String jumpServerGroup;

	/**
	 * Attribute externalId

	 */
	private java.lang.String externalId;

	/**
	 * Attribute lastLogin
	 * Time stamp when the account was last used on target system

	 */
	private java.util.Calendar lastLogin;

	/**
	 * Attribute lastUpdated
	 * Time stamp when the account was last updated on target system

	 */
	private java.util.Calendar lastUpdated;

	/**
	 * Attribute lastPasswordSet
	 * Time stamp when the password was last set on target system

	 */
	private java.util.Calendar lastPasswordSet;

	/**
	 * Attribute passwordExpiration
	 * Time stamp when the password is expected to expire on target system

	 */
	private java.util.Calendar passwordExpiration;

	/**
	 * Attribute lockedBy
	 * Current user that is using this privileged account. Does not apply for other accounts

	 */
	private java.lang.String lockedBy;

	/**
	 * Attribute passwordStatus

	 */
	private com.soffid.iam.am.api.PasswordValidation passwordStatus;

	/**
	 * Attribute created
	 * Creation date

	 */
	private java.util.Date created;

	/**
	 * Attribute lastChange
	 * Last change in soffid database

	 */
	private java.util.Date lastChange;

	/**
	 * Attribute attributes
	 * Account attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes = new java.util.HashMap<String,Object>();

	/**
	 * Attribute hasSnapshot
	 * True if there is an snapshot for delta changes

	 */
	private boolean hasSnapshot = false;

	/**
	 * Attribute createdBy


	 */
	private java.lang.String createdBy;

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

	public AbstractAccount()
	{
	}

	public AbstractAccount(java.lang.Long id, java.lang.String system, java.lang.String name, java.lang.String key, java.lang.String oldName, java.lang.String loginName, java.lang.String description, com.soffid.iam.base.api.AccountType type, boolean disabled, com.soffid.iam.base.api.AccountStatus status, com.soffid.iam.base.api.CredentialTypeEnum credentialType, java.lang.String passwordPolicy, java.util.Collection<java.lang.String> ownerGroups, java.util.Collection<java.lang.String> ownerUsers, java.util.Collection<java.lang.String> ownerRoles, java.util.Collection<java.lang.String> managerGroups, java.util.Collection<java.lang.String> managerUsers, java.util.Collection<java.lang.String> managerRoles, java.util.Collection<java.lang.String> grantedGroups, java.util.Collection<java.lang.String> grantedUsers, java.util.Collection<java.lang.String> grantedRoles, com.soffid.iam.base.api.AccountAccessLevelEnum accessLevel, java.lang.String serverType, java.lang.String serverName, java.lang.String sshPublicKey, java.lang.Long vaultFolderId, java.lang.String vaultFolder, boolean inheritNewPermissions, java.lang.String loginUrl, com.soffid.iam.am.api.LaunchType launchType, java.lang.String jumpServerGroup, java.lang.String externalId, java.util.Calendar lastLogin, java.util.Calendar lastUpdated, java.util.Calendar lastPasswordSet, java.util.Calendar passwordExpiration, java.lang.String lockedBy, com.soffid.iam.am.api.PasswordValidation passwordStatus, java.util.Date created, java.util.Date lastChange, java.util.Map<java.lang.String,java.lang.Object> attributes, boolean hasSnapshot, java.lang.String createdBy, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy, java.lang.Boolean deleted)
	{
		super();
		this.id = id;
		this.system = system;
		this.name = name;
		this.key = key;
		this.oldName = oldName;
		this.loginName = loginName;
		this.description = description;
		this.type = type;
		this.disabled = disabled;
		this.status = status;
		this.credentialType = credentialType;
		this.passwordPolicy = passwordPolicy;
		this.ownerGroups = ownerGroups;
		this.ownerUsers = ownerUsers;
		this.ownerRoles = ownerRoles;
		this.managerGroups = managerGroups;
		this.managerUsers = managerUsers;
		this.managerRoles = managerRoles;
		this.grantedGroups = grantedGroups;
		this.grantedUsers = grantedUsers;
		this.grantedRoles = grantedRoles;
		this.accessLevel = accessLevel;
		this.serverType = serverType;
		this.serverName = serverName;
		this.sshPublicKey = sshPublicKey;
		this.vaultFolderId = vaultFolderId;
		this.vaultFolder = vaultFolder;
		this.inheritNewPermissions = inheritNewPermissions;
		this.loginUrl = loginUrl;
		this.launchType = launchType;
		this.jumpServerGroup = jumpServerGroup;
		this.externalId = externalId;
		this.lastLogin = lastLogin;
		this.lastUpdated = lastUpdated;
		this.lastPasswordSet = lastPasswordSet;
		this.passwordExpiration = passwordExpiration;
		this.lockedBy = lockedBy;
		this.passwordStatus = passwordStatus;
		this.created = created;
		this.lastChange = lastChange;
		this.attributes = attributes;
		this.hasSnapshot = hasSnapshot;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
		this.deleted = deleted;
	}

	public AbstractAccount(java.lang.String system, java.lang.String name, java.lang.String key, com.soffid.iam.base.api.AccountType type, boolean disabled, java.lang.String passwordPolicy, boolean inheritNewPermissions, boolean hasSnapshot)
	{
		super();
		this.system = system;
		this.name = name;
		this.key = key;
		this.type = type;
		this.disabled = disabled;
		this.passwordPolicy = passwordPolicy;
		this.inheritNewPermissions = inheritNewPermissions;
		this.hasSnapshot = hasSnapshot;
	}

	public AbstractAccount(AbstractAccount otherBean)
	{
		this(otherBean.id, otherBean.system, otherBean.name, otherBean.key, otherBean.oldName, otherBean.loginName, otherBean.description, otherBean.type, otherBean.disabled, otherBean.status, otherBean.credentialType, otherBean.passwordPolicy, otherBean.ownerGroups, otherBean.ownerUsers, otherBean.ownerRoles, otherBean.managerGroups, otherBean.managerUsers, otherBean.managerRoles, otherBean.grantedGroups, otherBean.grantedUsers, otherBean.grantedRoles, otherBean.accessLevel, otherBean.serverType, otherBean.serverName, otherBean.sshPublicKey, otherBean.vaultFolderId, otherBean.vaultFolder, otherBean.inheritNewPermissions, otherBean.loginUrl, otherBean.launchType, otherBean.jumpServerGroup, otherBean.externalId, otherBean.lastLogin, otherBean.lastUpdated, otherBean.lastPasswordSet, otherBean.passwordExpiration, otherBean.lockedBy, otherBean.passwordStatus, otherBean.created, otherBean.lastChange, otherBean.attributes, otherBean.hasSnapshot, otherBean.createdBy, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy, otherBean.deleted);
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
	 * Gets value for attribute passwordPolicy
	 */
	public java.lang.String getPasswordPolicy() {
		return this.passwordPolicy;
	}

	/**
	 * Sets value for attribute passwordPolicy
	 */
	public void setPasswordPolicy(java.lang.String passwordPolicy) {
		this.passwordPolicy = passwordPolicy;
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
	 * Gets value for attribute vaultFolderId
	 */
	public java.lang.Long getVaultFolderId() {
		return this.vaultFolderId;
	}

	/**
	 * Sets value for attribute vaultFolderId
	 */
	public void setVaultFolderId(java.lang.Long vaultFolderId) {
		this.vaultFolderId = vaultFolderId;
	}

	/**
	 * Gets value for attribute vaultFolder
	 */
	public java.lang.String getVaultFolder() {
		return this.vaultFolder;
	}

	/**
	 * Sets value for attribute vaultFolder
	 */
	public void setVaultFolder(java.lang.String vaultFolder) {
		this.vaultFolder = vaultFolder;
	}

	/**
	 * Gets value for attribute inheritNewPermissions
	 */
	public boolean isInheritNewPermissions() {
		return this.inheritNewPermissions;
	}

	/**
	 * Sets value for attribute inheritNewPermissions
	 */
	public void setInheritNewPermissions(boolean inheritNewPermissions) {
		this.inheritNewPermissions = inheritNewPermissions;
	}

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
	 * Gets value for attribute jumpServerGroup
	 */
	public java.lang.String getJumpServerGroup() {
		return this.jumpServerGroup;
	}

	/**
	 * Sets value for attribute jumpServerGroup
	 */
	public void setJumpServerGroup(java.lang.String jumpServerGroup) {
		this.jumpServerGroup = jumpServerGroup;
	}

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
	 * Gets value for attribute lastLogin
	 */
	public java.util.Calendar getLastLogin() {
		return this.lastLogin;
	}

	/**
	 * Sets value for attribute lastLogin
	 */
	public void setLastLogin(java.util.Calendar lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Gets value for attribute lastUpdated
	 */
	public java.util.Calendar getLastUpdated() {
		return this.lastUpdated;
	}

	/**
	 * Sets value for attribute lastUpdated
	 */
	public void setLastUpdated(java.util.Calendar lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * Gets value for attribute lastPasswordSet
	 */
	public java.util.Calendar getLastPasswordSet() {
		return this.lastPasswordSet;
	}

	/**
	 * Sets value for attribute lastPasswordSet
	 */
	public void setLastPasswordSet(java.util.Calendar lastPasswordSet) {
		this.lastPasswordSet = lastPasswordSet;
	}

	/**
	 * Gets value for attribute passwordExpiration
	 */
	public java.util.Calendar getPasswordExpiration() {
		return this.passwordExpiration;
	}

	/**
	 * Sets value for attribute passwordExpiration
	 */
	public void setPasswordExpiration(java.util.Calendar passwordExpiration) {
		this.passwordExpiration = passwordExpiration;
	}

	/**
	 * Gets value for attribute lockedBy
	 */
	public java.lang.String getLockedBy() {
		return this.lockedBy;
	}

	/**
	 * Sets value for attribute lockedBy
	 */
	public void setLockedBy(java.lang.String lockedBy) {
		this.lockedBy = lockedBy;
	}

	/**
	 * Gets value for attribute passwordStatus
	 */
	public com.soffid.iam.am.api.PasswordValidation getPasswordStatus() {
		return this.passwordStatus;
	}

	/**
	 * Sets value for attribute passwordStatus
	 */
	public void setPasswordStatus(com.soffid.iam.am.api.PasswordValidation passwordStatus) {
		this.passwordStatus = passwordStatus;
	}

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
	 * Gets value for attribute hasSnapshot
	 */
	public boolean isHasSnapshot() {
		return this.hasSnapshot;
	}

	/**
	 * Sets value for attribute hasSnapshot
	 */
	public void setHasSnapshot(boolean hasSnapshot) {
		this.hasSnapshot = hasSnapshot;
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
		b.append ("[id: ");
		b.append (this.id);
		b.append (", system: ");
		b.append (this.system);
		b.append (", name: ");
		b.append (this.name);
		b.append (", key: ");
		b.append (this.key);
		b.append (", oldName: ");
		b.append (this.oldName);
		b.append (", loginName: ");
		b.append (this.loginName);
		b.append (", description: ");
		b.append (this.description);
		b.append (", type: ");
		b.append (this.type);
		b.append (", disabled: ");
		b.append (this.disabled);
		b.append (", status: ");
		b.append (this.status);
		b.append (", credentialType: ");
		b.append (this.credentialType);
		b.append (", passwordPolicy: ");
		b.append (this.passwordPolicy);
		b.append (", ownerGroups: ");
		b.append (this.ownerGroups);
		b.append (", ownerUsers: ");
		b.append (this.ownerUsers);
		b.append (", ownerRoles: ");
		b.append (this.ownerRoles);
		b.append (", managerGroups: ");
		b.append (this.managerGroups);
		b.append (", managerUsers: ");
		b.append (this.managerUsers);
		b.append (", managerRoles: ");
		b.append (this.managerRoles);
		b.append (", grantedGroups: ");
		b.append (this.grantedGroups);
		b.append (", grantedUsers: ");
		b.append (this.grantedUsers);
		b.append (", grantedRoles: ");
		b.append (this.grantedRoles);
		b.append (", accessLevel: ");
		b.append (this.accessLevel);
		b.append (", serverType: ");
		b.append (this.serverType);
		b.append (", serverName: ");
		b.append (this.serverName);
		b.append (", sshPublicKey: ");
		b.append (this.sshPublicKey);
		b.append (", vaultFolderId: ");
		b.append (this.vaultFolderId);
		b.append (", vaultFolder: ");
		b.append (this.vaultFolder);
		b.append (", inheritNewPermissions: ");
		b.append (this.inheritNewPermissions);
		b.append (", loginUrl: ");
		b.append (this.loginUrl);
		b.append (", launchType: ");
		b.append (this.launchType);
		b.append (", jumpServerGroup: ");
		b.append (this.jumpServerGroup);
		b.append (", externalId: ");
		b.append (this.externalId);
		b.append (", lastLogin: ");
		b.append (this.lastLogin);
		b.append (", lastUpdated: ");
		b.append (this.lastUpdated);
		b.append (", lastPasswordSet: ");
		b.append (this.lastPasswordSet);
		b.append (", passwordExpiration: ");
		b.append (this.passwordExpiration);
		b.append (", lockedBy: ");
		b.append (this.lockedBy);
		b.append (", passwordStatus: ");
		b.append (this.passwordStatus);
		b.append (", created: ");
		b.append (this.created);
		b.append (", lastChange: ");
		b.append (this.lastChange);
		b.append (", attributes: ");
		b.append (this.attributes);
		b.append (", hasSnapshot: ");
		b.append (this.hasSnapshot);
		b.append (", createdBy: ");
		b.append (this.createdBy);
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
