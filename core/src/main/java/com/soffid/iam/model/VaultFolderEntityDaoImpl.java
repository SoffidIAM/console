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
import com.soffid.iam.api.VaultFolder;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.AccountAccessEntity;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.UserAccountEntity;
import es.caib.seycon.ng.model.UsuariEntity;
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
		Collection<Grup> grups = new LinkedList<Grup>();
		Collection<Rol> roles = new LinkedList<Rol>();
		Collection<Usuari> usuaris = new LinkedList<Usuari>();
		Collection<Grup> managerGrups = new LinkedList<Grup>();
		Collection<Rol> managerRoles = new LinkedList<Rol>();
		Collection<Usuari> managerUsers = new LinkedList<Usuari>();
		Collection<Grup> ownerGrups = new LinkedList<Grup>();
		Collection<Rol> ownerRoles = new LinkedList<Rol>();
		Collection<Usuari> ownerUsers = new LinkedList<Usuari>();
		Collection<Long> navGrups = new LinkedList<Long>();
		Collection<Long> navRoles = new LinkedList<Long>();
		Collection<Long> navUsers = new LinkedList<Long>();

		for (VaultFolderAccessEntity acl: source.getAcl())
		{
			// Users
			if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_USER))
				grups.add(getGrupEntityDao().toGrup(acl.getGroup()));
			if (acl.getRol() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_USER))
				roles.add(getRolEntityDao().toRol(acl.getRol()));
			if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_USER))
				usuaris.add(getUsuariEntityDao().toUsuari(acl.getUser()));
			// Managers
			if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_MANAGER))
				managerGrups.add(getGrupEntityDao().toGrup(acl.getGroup()));
			if (acl.getRol() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_MANAGER))
				managerRoles.add(getRolEntityDao().toRol(acl.getRol()));
			if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_MANAGER))
				managerUsers.add(getUsuariEntityDao().toUsuari(acl.getUser()));
			// Users
			if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_OWNER))
				ownerGrups.add(getGrupEntityDao().toGrup(acl.getGroup()));
			if (acl.getRol() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_OWNER))
				ownerRoles.add(getRolEntityDao().toRol(acl.getRol()));
			if (acl.getUser() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_OWNER))
				ownerUsers.add(getUsuariEntityDao().toUsuari(acl.getUser()));
			// Navigate
			if (acl.getGroup() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_NAVIGATE))
				navGrups.add(acl.getGroup().getId());
			if (acl.getRol() != null & acl.getLevel().equals ( AccountAccessLevelEnum.ACCESS_NAVIGATE))
				navRoles.add(acl.getRol().getId());
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
			UsuariEntity ue = getUsuariEntityDao().findByCodi(u);
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
		Usuari u = getUsuariService().getCurrentUsuari();
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
				if (entry.getRol() != null)
					acl.getRoles().add(entry.getRol().getId());
			}
		}
		return acl;
	}

	protected AccessControlList generateAcl(VaultFolder source,
			AccountAccessLevelEnum al) {
		AccessControlList acl = new AccessControlList();
		if (al == AccountAccessLevelEnum.ACCESS_OWNER)
		{
			for ( Usuari u: source.getOwnerUsers())
				acl.getUsers().add(u.getId());
			for ( Grup g: source.getOwnerGroups())
				acl.getGroups().add(g.getId());
			for ( Rol r: source.getOwnerRoles())
				acl.getRoles().add(r.getId());
		}
		if (al == AccountAccessLevelEnum.ACCESS_MANAGER)
		{
			for ( Usuari u: source.getManagerUsers())
				acl.getUsers().add(u.getId());
			for ( Grup g: source.getManagerGroups())
				acl.getGroups().add(g.getId());
			for ( Rol r: source.getManagerRoles())
				acl.getRoles().add(r.getId());
		}
		if (al == AccountAccessLevelEnum.ACCESS_USER)
		{
			for ( Usuari u: source.getGrantedUsers())
				acl.getUsers().add(u.getId());
			for ( Grup g: source.getGrantedGroups())
				acl.getGroups().add(g.getId());
			for ( Rol r: source.getGrantedRoles())
				acl.getRoles().add(r.getId());
		}
		if (al == AccountAccessLevelEnum.ACCESS_NAVIGATE)
		{
			for ( Usuari u: source.getGrantedUsers())
				acl.getUsers().add(u.getId());
			for ( Grup g: source.getGrantedGroups())
				acl.getGroups().add(g.getId());
			for ( Rol r: source.getGrantedRoles())
				acl.getRoles().add(r.getId());
		}
		return acl;
	}
}

