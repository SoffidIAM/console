//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import org.apache.commons.logging.LogFactory;

import com.soffid.iam.api.AccessControlList;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.User;
import com.soffid.iam.api.VaultFolder;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;

/**
 * DAO VaultFolderEntity implementation
 */
public class VaultFolderEntityDaoImpl extends VaultFolderEntityDaoBase
{

	@Override
	protected synchronized VaultFolder getVaultFolderCacheEntry(Long id) {
		VaultFolder vf = super.getVaultFolderCacheEntry(id);
		
		if (vf != null)
		{
			try {
				vf.setAccessLevel(getAccessLevel(load (id)));
			} catch (InternalErrorException e) {
				return null;
			}
		}
		return vf;
	}

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
		Collection<Long> navGrups = new LinkedList<Long>();
		Collection<Long> navRoles = new LinkedList<Long>();
		Collection<Long> navUsers = new LinkedList<Long>();

		for (VaultFolderAccessEntity acl: source.getAcl())
		{
			// Users
			if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_USER))
				grups.add(getGroupEntityDao().toGroup(acl.getGroup()));
			if (acl.getRole() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_USER))
				roles.add(getRoleEntityDao().toRole(acl.getRole()));
			if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_USER))
				usuaris.add(getUserEntityDao().toUser(acl.getUser()));
			// Managers
			if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_MANAGER))
				managerGrups.add(getGroupEntityDao().toGroup(acl.getGroup()));
			if (acl.getRole() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_MANAGER))
				managerRoles.add(getRoleEntityDao().toRole(acl.getRole()));
			if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_MANAGER))
				managerUsers.add(getUserEntityDao().toUser(acl.getUser()));
			// Users
			if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_OWNER))
				ownerGrups.add(getGroupEntityDao().toGroup(acl.getGroup()));
			if (acl.getRole() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_OWNER))
				ownerRoles.add(getRoleEntityDao().toRole(acl.getRole()));
			if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_OWNER))
				ownerUsers.add(getUserEntityDao().toUser(acl.getUser()));
			// Navigate
			if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_NAVIGATE))
				navGrups.add(acl.getGroup().getId());
			if (acl.getRole() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_NAVIGATE))
				navRoles.add(acl.getRole().getId());
			if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_NAVIGATE))
				navUsers.add(acl.getUser().getId());
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

		target.setNavigateGroups(navGrups);
		target.setNavigateRoles(navRoles);
		target.setNavigateUsers(navUsers);

		try {
			target.setAccessLevel(getAccessLevel(target));
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

	private AccountAccessLevelEnum getAccessLevel (VaultFolder source) throws InternalErrorException {
		User u = getUserService().getCurrentUser();
		if ( u != null)
		{
			for (AccountAccessLevelEnum al: new AccountAccessLevelEnum [] {
					AccountAccessLevelEnum.ACCESS_OWNER,
					AccountAccessLevelEnum.ACCESS_MANAGER,
					AccountAccessLevelEnum.ACCESS_USER,
					AccountAccessLevelEnum.ACCESS_NAVIGATE
					})
			{
				AccessControlList acl = generateAcl (source, al);
				if ( getACLService().isUserIncluded(u.getId(), acl))
					return al;
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
				if (entry.getRole() != null)
					acl.getRoles().add(entry.getRole().getId());
			}
		}
		return acl;
	}

	protected AccessControlList generateAcl(VaultFolder source,
			AccountAccessLevelEnum al) {
		AccessControlList acl = new AccessControlList();
		if (al == AccountAccessLevelEnum.ACCESS_OWNER)
		{
			for ( User u: source.getOwnerUsers())
				acl.getUsers().add(u.getId());
			for ( Group g: source.getOwnerGroups())
				acl.getGroups().add(g.getId());
			for ( Role r: source.getOwnerRoles())
				acl.getRoles().add(r.getId());
		}
		if (al == AccountAccessLevelEnum.ACCESS_MANAGER)
		{
			for ( User u: source.getManagerUsers())
				acl.getUsers().add(u.getId());
			for ( Group g: source.getManagerGroups())
				acl.getGroups().add(g.getId());
			for ( Role r: source.getManagerRoles())
				acl.getRoles().add(r.getId());
		}
		if (al == AccountAccessLevelEnum.ACCESS_USER)
		{
			for ( User u: source.getGrantedUsers())
				acl.getUsers().add(u.getId());
			for ( Group g: source.getGrantedGroups())
				acl.getGroups().add(g.getId());
			for ( Role r: source.getGrantedRoles())
				acl.getRoles().add(r.getId());
		}
		if (al == AccountAccessLevelEnum.ACCESS_NAVIGATE)
		{
			for ( User u: source.getGrantedUsers())
				acl.getUsers().add(u.getId());
			for ( Group g: source.getGrantedGroups())
				acl.getGroups().add(g.getId());
			for ( Role r: source.getGrantedRoles())
				acl.getRoles().add(r.getId());
		}
		return acl;
	}
}

