//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject UserAccount
 **/
public class UserAccount
 extends com.soffid.iam.base.api.Account

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
	 * Attribute createdOn


	 */
	private java.util.Date createdOn;

	public UserAccount()
	{
	}

	public UserAccount(java.lang.Long id, java.lang.String system, java.lang.String name, java.lang.String key, java.lang.String oldName, java.lang.String loginName, java.lang.String description, com.soffid.iam.base.api.AccountType type, boolean disabled, com.soffid.iam.base.api.AccountStatus status, com.soffid.iam.base.api.CredentialTypeEnum credentialType, java.lang.String passwordPolicy, java.util.Collection<java.lang.String> ownerGroups, java.util.Collection<java.lang.String> ownerUsers, java.util.Collection<java.lang.String> ownerRoles, java.util.Collection<java.lang.String> managerGroups, java.util.Collection<java.lang.String> managerUsers, java.util.Collection<java.lang.String> managerRoles, java.util.Collection<java.lang.String> grantedGroups, java.util.Collection<java.lang.String> grantedUsers, java.util.Collection<java.lang.String> grantedRoles, com.soffid.iam.base.api.AccountAccessLevelEnum accessLevel, java.lang.String serverType, java.lang.String serverName, java.lang.String sshPublicKey, java.lang.Long vaultFolderId, java.lang.String vaultFolder, boolean inheritNewPermissions, java.lang.String loginUrl, com.soffid.iam.am.api.LaunchType launchType, java.lang.String jumpServerGroup, java.lang.String externalId, java.util.Calendar lastLogin, java.util.Calendar lastUpdated, java.util.Calendar lastPasswordSet, java.util.Calendar passwordExpiration, java.lang.String lockedBy, com.soffid.iam.am.api.PasswordValidation passwordStatus, java.util.Date created, java.util.Date lastChange, java.util.Map<java.lang.String,java.lang.Object> attributes, boolean hasSnapshot, java.lang.String createdBy, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy, java.lang.Boolean deleted, java.lang.String user, java.util.Date createdOn)
	{
		super(id, system, name, key, oldName, loginName, description, type, disabled, status, credentialType, passwordPolicy, ownerGroups, ownerUsers, ownerRoles, managerGroups, managerUsers, managerRoles, grantedGroups, grantedUsers, grantedRoles, accessLevel, serverType, serverName, sshPublicKey, vaultFolderId, vaultFolder, inheritNewPermissions, loginUrl, launchType, jumpServerGroup, externalId, lastLogin, lastUpdated, lastPasswordSet, passwordExpiration, lockedBy, passwordStatus, created, lastChange, attributes, hasSnapshot, createdBy, updatedBy, deletedOn, deletedBy, deleted);
		this.user = user;
		this.createdOn = createdOn;
	}

	public UserAccount(java.lang.String system, java.lang.String name, java.lang.String key, com.soffid.iam.base.api.AccountType type, boolean disabled, java.lang.String passwordPolicy, boolean inheritNewPermissions, boolean hasSnapshot, java.lang.String user)
	{
		super(system, name, key, type, disabled, passwordPolicy, inheritNewPermissions, hasSnapshot);
		this.user = user;
	}

	public UserAccount(UserAccount otherBean)
	{
		this(otherBean.getId(), otherBean.getSystem(), otherBean.getName(), otherBean.getKey(), otherBean.getOldName(), otherBean.getLoginName(), otherBean.getDescription(), otherBean.getType(), otherBean.isDisabled(), otherBean.getStatus(), otherBean.getCredentialType(), otherBean.getPasswordPolicy(), otherBean.getOwnerGroups(), otherBean.getOwnerUsers(), otherBean.getOwnerRoles(), otherBean.getManagerGroups(), otherBean.getManagerUsers(), otherBean.getManagerRoles(), otherBean.getGrantedGroups(), otherBean.getGrantedUsers(), otherBean.getGrantedRoles(), otherBean.getAccessLevel(), otherBean.getServerType(), otherBean.getServerName(), otherBean.getSshPublicKey(), otherBean.getVaultFolderId(), otherBean.getVaultFolder(), otherBean.isInheritNewPermissions(), otherBean.getLoginUrl(), otherBean.getLaunchType(), otherBean.getJumpServerGroup(), otherBean.getExternalId(), otherBean.getLastLogin(), otherBean.getLastUpdated(), otherBean.getLastPasswordSet(), otherBean.getPasswordExpiration(), otherBean.getLockedBy(), otherBean.getPasswordStatus(), otherBean.getCreated(), otherBean.getLastChange(), otherBean.getAttributes(), otherBean.isHasSnapshot(), otherBean.getCreatedBy(), otherBean.getUpdatedBy(), otherBean.getDeletedOn(), otherBean.getDeletedBy(), otherBean.getDeleted(), otherBean.user, otherBean.createdOn);
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[user: ");
		b.append (this.user);
		b.append (", createdOn: ");
		b.append (this.createdOn);
		b.append (", id: ");
		b.append (this.getId());
		b.append (", system: ");
		b.append (this.getSystem());
		b.append (", name: ");
		b.append (this.getName());
		b.append (", key: ");
		b.append (this.getKey());
		b.append (", oldName: ");
		b.append (this.getOldName());
		b.append (", loginName: ");
		b.append (this.getLoginName());
		b.append (", description: ");
		b.append (this.getDescription());
		b.append (", type: ");
		b.append (this.getType());
		b.append (", disabled: ");
		b.append (this.isDisabled());
		b.append (", status: ");
		b.append (this.getStatus());
		b.append (", credentialType: ");
		b.append (this.getCredentialType());
		b.append (", passwordPolicy: ");
		b.append (this.getPasswordPolicy());
		b.append (", ownerGroups: ");
		b.append (this.getOwnerGroups());
		b.append (", ownerUsers: ");
		b.append (this.getOwnerUsers());
		b.append (", ownerRoles: ");
		b.append (this.getOwnerRoles());
		b.append (", managerGroups: ");
		b.append (this.getManagerGroups());
		b.append (", managerUsers: ");
		b.append (this.getManagerUsers());
		b.append (", managerRoles: ");
		b.append (this.getManagerRoles());
		b.append (", grantedGroups: ");
		b.append (this.getGrantedGroups());
		b.append (", grantedUsers: ");
		b.append (this.getGrantedUsers());
		b.append (", grantedRoles: ");
		b.append (this.getGrantedRoles());
		b.append (", accessLevel: ");
		b.append (this.getAccessLevel());
		b.append (", serverType: ");
		b.append (this.getServerType());
		b.append (", serverName: ");
		b.append (this.getServerName());
		b.append (", sshPublicKey: ");
		b.append (this.getSshPublicKey());
		b.append (", vaultFolderId: ");
		b.append (this.getVaultFolderId());
		b.append (", vaultFolder: ");
		b.append (this.getVaultFolder());
		b.append (", inheritNewPermissions: ");
		b.append (this.isInheritNewPermissions());
		b.append (", loginUrl: ");
		b.append (this.getLoginUrl());
		b.append (", launchType: ");
		b.append (this.getLaunchType());
		b.append (", jumpServerGroup: ");
		b.append (this.getJumpServerGroup());
		b.append (", externalId: ");
		b.append (this.getExternalId());
		b.append (", lastLogin: ");
		b.append (this.getLastLogin());
		b.append (", lastUpdated: ");
		b.append (this.getLastUpdated());
		b.append (", lastPasswordSet: ");
		b.append (this.getLastPasswordSet());
		b.append (", passwordExpiration: ");
		b.append (this.getPasswordExpiration());
		b.append (", lockedBy: ");
		b.append (this.getLockedBy());
		b.append (", passwordStatus: ");
		b.append (this.getPasswordStatus());
		b.append (", created: ");
		b.append (this.getCreated());
		b.append (", lastChange: ");
		b.append (this.getLastChange());
		b.append (", attributes: ");
		b.append (this.getAttributes());
		b.append (", hasSnapshot: ");
		b.append (this.isHasSnapshot());
		b.append (", createdBy: ");
		b.append (this.getCreatedBy());
		b.append (", updatedBy: ");
		b.append (this.getUpdatedBy());
		b.append (", deletedOn: ");
		b.append (this.getDeletedOn());
		b.append (", deletedBy: ");
		b.append (this.getDeletedBy());
		b.append (", deleted: ");
		b.append (this.getDeleted());
		b.append ("]");
		return b.toString();
	}

}
