//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.commons.logging.LogFactory;

import com.soffid.iam.api.AccessControlList;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.User;
import com.soffid.iam.api.VaultFolder;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;

/**
 * DAO VaultFolderEntity implementation
 */
public class VaultFolderEntityDaoImpl extends VaultFolderEntityDaoBase
{

	@Override
	public void toVaultFolder(VaultFolderEntity source, VaultFolder target) {
		super.toVaultFolder(source, target);
		
		if (source.getParent() == null)
		{
			target.setParentId(null);
			target.setParentFolder(null);
		}
		else
		{
			target.setParentId(source.getParent().getId());
			target.setParentFolder(source.getParent().getName());
		}

		// Set ACLs
		Collection<Group> grups = new LinkedList<Group>();
		Collection<Role> roles = new LinkedList<Role>();
		Collection<User> usuaris = new LinkedList<User>();
		Collection<Group> managerGrups = new LinkedList<Group>();
		Collection<Role> managerRoles = new LinkedList<Role>();
		Collection<User> managerUsers = new LinkedList<User>();
		Collection<Group> ownerGrups = new LinkedList<Group>();
		Collection<Role> ownerRoles = new LinkedList<Role>();
		Collection<User> ownerUsers = new LinkedList<User>();

		for (VaultFolderAccessEntity acl: source.getAcl())
		{
			// Users
			if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_USER))
				grups.add(getGroupEntityDao().toGroup(acl.getGroup()));
			if (acl.getRol() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_USER))
				roles.add(getRoleEntityDao().toRole(acl.getRol()));
			if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_USER))
				usuaris.add(getUserEntityDao().toUser(acl.getUser()));
			// Managers
			if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_MANAGER))
				managerGrups.add(getGroupEntityDao().toGroup(acl.getGroup()));
			if (acl.getRol() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_MANAGER))
				managerRoles.add(getRoleEntityDao().toRole(acl.getRol()));
			if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_MANAGER))
				managerUsers.add(getUserEntityDao().toUser(acl.getUser()));
			// Users
			if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_OWNER))
				ownerGrups.add(getGroupEntityDao().toGroup(acl.getGroup()));
			if (acl.getRol() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_OWNER))
				ownerRoles.add(getRoleEntityDao().toRole(acl.getRol()));
			if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_OWNER))
				ownerUsers.add(getUserEntityDao().toUser(acl.getUser()));
		}

		target.setGrantedGroups(grups);
		target.setGrantedRoles(roles);
		target.setGrantedUsers(usuaris);

		target.setManagerGroups(managerGrups);
		target.setManagerRoles(managerRoles);
		target.setManagerUsers(managerUsers);

		target.setOwnerGroups(ownerGrups);
		target.setOwnerRoles(ownerRoles);
		target.setOwnerUsers(ownerUsers);

		try {
			target.setAccessLevel(getAccessLevel(source));
		} catch (InternalErrorException e) {
			LogFactory.getLog(getClass()).warn("Error gerating ACL for "+target, e);
			target.setAccessLevel(AccountAccessLevelEnum.ACCESS_NONE);
		}
	}
	
	@Override
	public void vaultFolderToEntity(VaultFolder source,
			VaultFolderEntity target, boolean copyIfNull) {
		super.vaultFolderToEntity(source, target, copyIfNull);
		if (source.getParentId() == null)
			target.setParent(null);
		else
			target.setParent( load (source.getParentId()));
	}

	private AccountAccessLevelEnum getAccessLevel (VaultFolderEntity source) throws InternalErrorException {
		String u = Security.getCurrentUser();
		if ( u != null)
		{
			UserEntity ue = getUserEntityDao().findByUserName(u);
			if (ue != null)
			{
				for (AccountAccessLevelEnum al: new AccountAccessLevelEnum [] {
						AccountAccessLevelEnum.ACCESS_OWNER,
						AccountAccessLevelEnum.ACCESS_MANAGER,
						AccountAccessLevelEnum.ACCESS_USER,
						AccountAccessLevelEnum.ACCESS_NAVIGATE
						})
				{
					AccessControlList acl = generateAcl (source, al);
					if ( getACLService().isUserIncluded(ue.getId(), acl))
						return al;
				}
			}			
		}
		return AccountAccessLevelEnum.ACCESS_NONE;
	}

	protected AccessControlList generateAcl(VaultFolderEntity source,
			AccountAccessLevelEnum al) {
		AccessControlList acl = new AccessControlList();
		for ( VaultFolderAccessEntity entry: source.getAcl())
		{
			if (entry.getLevel().equals (al))
			{
				if (entry.getUser() != null)
					acl.getUsers().add(entry.getUser().getId());
				if (entry.getGroup() != null)
					acl.getGroups().add(entry.getGroup().getId());
				if (entry.getRol() != null)
					acl.getRoles().add(entry.getRol().getId());
			}
		}
		return acl;
	}

}
