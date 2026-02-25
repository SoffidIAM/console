package com.soffid.iam.base.api;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.soffid.iam.am.api.LaunchType;
import com.soffid.iam.am.api.PasswordValidation;
import com.soffid.iam.iga.api.AbstractRole;

public class Account extends AbstractAccount {
	@Override
	public String getKey() {
		return getName() == null ? null: getName()+"@"+getSystem();
	}

	@Override
	public void setKey(String key) {
		super.setKey(key);
		if (key == null || key.isBlank())
			return;
		int i = key.lastIndexOf("@");
		if (i > 0) {
			setName (key.substring(0,i));
			setSystem(key.substring(i+1));
		} else {
			setName(key);
			setSystem(null);
		}
	}
	
	public Account() {
	}

	public Account(String system, String name, String key, AccountType type, boolean disabled, String passwordPolicy, boolean inheritNewPermissions, boolean hasSnapshot) {
		super(system, name, key, type, disabled, passwordPolicy, inheritNewPermissions, hasSnapshot);
	}

	public Account(AbstractAccount otherBean) {
		super(otherBean);
	}

	public Account(Long id, String system, String name, String key, String oldName, String loginName,
			String description, AccountType type, boolean disabled, AccountStatus status,
			CredentialTypeEnum credentialType, String passwordPolicy, Collection<String> ownerGroups,
			Collection<String> ownerUsers, Collection<String> ownerRoles, Collection<String> managerGroups,
			Collection<String> managerUsers, Collection<String> managerRoles, Collection<String> grantedGroups,
			Collection<String> grantedUsers, Collection<String> grantedRoles, AccountAccessLevelEnum accessLevel,
			String serverType, String serverName, String sshPublicKey, Long vaultFolderId, String vaultFolder,
			boolean inheritNewPermissions, String loginUrl, LaunchType launchType, String jumpServerGroup,
			String externalId, Calendar lastLogin, Calendar lastUpdated, Calendar lastPasswordSet,
			Calendar passwordExpiration, String lockedBy, PasswordValidation passwordStatus, Date created,
			Date lastChange, Map<String, Object> attributes, boolean hasSnapshot, String createdBy, String updatedBy,
			Date deletedOn, String deletedBy, Boolean deleted) {
		super(id, system, name, key, oldName, loginName, description, type, disabled, status, credentialType, passwordPolicy,
				ownerGroups, ownerUsers, ownerRoles, managerGroups, managerUsers, managerRoles, grantedGroups, grantedUsers,
				grantedRoles, accessLevel, serverType, serverName, sshPublicKey, vaultFolderId, vaultFolder,
				inheritNewPermissions, loginUrl, launchType, jumpServerGroup, externalId, lastLogin, lastUpdated,
				lastPasswordSet, passwordExpiration, lockedBy, passwordStatus, created, lastChange, attributes, hasSnapshot,
				createdBy, updatedBy, deletedOn, deletedBy, deleted);
		// TODO Auto-generated constructor stub
	}

}
