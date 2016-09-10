package com.soffid.iam.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.api.VaultFolderAccountPermissions;
import com.soffid.iam.api.VaultFolderPermissions;
import com.soffid.iam.model.VaultFolderAccessEntity;
import com.soffid.iam.model.VaultFolderEntity;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountCriteria;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.AccountAccessEntity;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.utils.Security;

public class VaultServiceImpl extends VaultServiceBase {

	@Override
	protected Account handleAddToFolder(Account account)
			throws Exception {
		AccountEntity accountEntity =  getAccountEntityDao().load(account.getId());
		if (account.getVaultFolderId() == null)
		{
			if (accountEntity.getFolder() != null)
			{
				accountEntity.setFolder(null);
				getAccountEntityDao().update(accountEntity);
				account = getAccountEntityDao().toAccount(accountEntity);
			}
		} else {
			VaultFolderEntity newFolderEntity = getVaultFolderEntityDao().load(account.getVaultFolderId());
			VaultFolderEntity oldFolderEntity = accountEntity.getFolder();
			if (newFolderEntity != oldFolderEntity)
			{
				accountEntity.setFolder(newFolderEntity);
				if (accountEntity.getInheritNewPermissions() == null ||
						accountEntity.getInheritNewPermissions().booleanValue())
					setAccountPermissions (accountEntity);
				getAccountEntityDao().update(accountEntity);
				account = getAccountEntityDao().toAccount(accountEntity);
			}
		}
		return account;
	}

	private void setAccountPermissions(AccountEntity accountEntity) {
		// Remove previous permissions
		for ( AccountAccessEntity aae: accountEntity.getAcl())
		{
			getAccountAccessEntityDao().remove (aae);
		}
		// Add new permissions
		for ( VaultFolderAccessEntity fae: accountEntity.getFolder().getAcl())
		{
			if (fae.getLevel().equals(AccountAccessLevelEnum.ACCESS_MANAGER) ||
					fae.getLevel().equals(AccountAccessLevelEnum.ACCESS_OWNER) ||
					fae.getLevel().equals(AccountAccessLevelEnum.ACCESS_USER) )
			{
				AccountAccessEntity aae = generateAccountAccessEntity(fae,
						accountEntity);
				getAccountAccessEntityDao().create(aae);
			}
		}
	}

	private AccountAccessEntity generateAccountAccessEntity(
			VaultFolderAccessEntity folderAccess, AccountEntity accountEntity) {
		AccountAccessEntity aae = getAccountAccessEntityDao().newAccountAccessEntity();
		aae.setAccount(accountEntity);
		aae.setGroup(folderAccess.getGroup());
		aae.setRol(folderAccess.getRol());
		aae.setUser(folderAccess.getUser());
		aae.setLevel(folderAccess.getLevel());
		return aae;
	}

	@Override
	protected List<VaultFolder> handleGetChildren(VaultFolder parent)
			throws Exception {
		VaultFolderEntity entity = getVaultFolderEntityDao().load(parent.getId());
		
		List<VaultFolder> folders = getVaultFolderEntityDao().toVaultFolderList(entity.getChildren());
		
		filterFolders(folders);

		return folders;
	}

	@Override
	protected void handleRemove(VaultFolder folder) throws Exception {
		VaultFolderEntity entity = getVaultFolderEntityDao().load (folder.getId());
		folder = getVaultFolderEntityDao().toVaultFolder(entity);
		if (!folder.getAccessLevel().equals( AccountAccessLevelEnum.ACCESS_OWNER ))
			throw new SeyconException("Insufficient permissions");
		
		if (!entity.getChildren().isEmpty())
			throw new SeyconException("Cannot remove folder with accounts inside");
		
		for ( VaultFolderAccessEntity ace: entity.getAcl())
		{
			checkRemoveParentAce (entity, ace);
		}
		getVaultFolderEntityDao().remove(entity);
	}

	/**
	 * Removes from parent any equivalent "Navigate" ACE, if possible.
	 * 
	 * It would be possible if no other sibling folder has a similar ACE.
	 * 
	 * @param entity
	 * @param ace
	 * @return
	 */
	private boolean checkRemoveParentAce(VaultFolderEntity entity,
			VaultFolderAccessEntity ace) {
		VaultFolderEntity parent = entity.getParent();
		boolean remove = true;
		if (parent != null)
		{
			for (VaultFolderEntity sibling: parent.getChildren())
			{
				if (! sibling.getId().equals (entity.getId()))
				{
					for (VaultFolderAccessEntity ace2: sibling.getAcl())
					{
						if (ace2.getGroup() == ace.getGroup() &&
							ace2.getRol() == ace.getRol() &&
							ace2.getUser() == ace.getUser() )
						{
							remove = false;
							break;
						}
					}
				}
				if (! remove) break;
			}
			if (remove)
			{
				for (Iterator<VaultFolderAccessEntity> it = parent.getAcl().iterator(); it.hasNext();)
				{
					VaultFolderAccessEntity ace2 = it.next();
					if (ace2.getLevel().equals( AccountAccessLevelEnum.ACCESS_NAVIGATE ) &&
						ace2.getGroup() == ace.getGroup() &&
						ace2.getRol() == ace.getRol() &&
						ace2.getUser() == ace.getUser() )
					{
						checkRemoveParentAce (parent, ace2);
						getVaultFolderAccessEntityDao().remove(ace2);
						it.remove();
					}
				}
			}
		}
		return remove;
	}

	/**
	 * Creates a "Navigate" ACE into parent, if needed
	 * 
	 * It would be possible if no other sibling folder has a similar ACE.
	 * 
	 * @param entity
	 * @param ace Current access control entry
	 * @return
	 */
	private boolean checkAddParentAce(VaultFolderEntity entity,
			VaultFolderAccessEntity ace) {
		VaultFolderEntity parent = entity.getParent();
		boolean add = true;
		if (parent != null)
		{
			for (VaultFolderAccessEntity ace2: parent.getAcl())
			{
				if (ace2.getLevel().equals( AccountAccessLevelEnum.ACCESS_NAVIGATE ) &&
					ace2.getGroup() == ace.getGroup() &&
					ace2.getRol() == ace.getRol() &&
					ace2.getUser() == ace.getUser() )
				{
					add = false;
					break;
				}
			}
			if (add)
			{
				VaultFolderAccessEntity ace2 = getVaultFolderAccessEntityDao().newVaultFolderAccessEntity();
				ace2.setVault(parent);
				ace2.setGroup(ace.getGroup());
				ace2.setRol(ace.getRol());
				ace2.setUser(ace.getUser());
				ace2.setLevel(AccountAccessLevelEnum.ACCESS_NAVIGATE);
				getVaultFolderAccessEntityDao().create(ace2);
				parent.getAcl().add(ace2);
				checkAddParentAce(parent, ace2);
			}
			return add;
		} else
			return false;
	}

	@Override
	protected List<Account> handleList(VaultFolder folder) throws Exception {
		VaultFolderEntity entity = getVaultFolderEntityDao().load (folder.getId());
		folder = getVaultFolderEntityDao().toVaultFolder(entity);
		if (!folder.getAccessLevel().equals( AccountAccessLevelEnum.ACCESS_OWNER ))
			throw new SeyconException("Insufficient permissions");
		
		List<Account> accounts = getAccountEntityDao().toAccountList(entity.getAccounts());
		for (Iterator<Account> it = accounts.iterator(); it.hasNext();)
		{
			Account account = it.next();
			if (account.getAccessLevel().equals (AccountAccessLevelEnum.ACCESS_NONE))
				it.remove();
		}
		Collections.sort(accounts, new Comparator<Account>() {
			public int compare(Account o1, Account o2) {
				return o1.getDescription().compareTo(o2.getDescription());
			}
			
		});
		return accounts;
	}

	private void checkAnyOnwer (VaultFolder folder) throws InternalErrorException
	{
		if (folder.getOwnerGroups().isEmpty() &&
				folder.getOwnerRoles().isEmpty() &&
				folder.getOwnerUsers().isEmpty())
		{
			throw new InternalErrorException("Missing owner(s) for folder "+folder.getName());
		}
	}
	
	@Override
	protected VaultFolder handleCreate(VaultFolder folder) throws Exception {
		
		if (folder.getParentId() == null)
		{
			if (! Security.isUserInRole("sso:createSharedFolders"))
				throw new SecurityException("Not authorized. Missing permission sso:craeteSharedFolders");
		}
		else
		{
			VaultFolderEntity parent = getVaultFolderEntityDao().load(folder.getParentId());
			AccountAccessLevelEnum al = getVaultFolderEntityDao().toVaultFolder(parent).getAccessLevel();
			if (! al.equals( AccountAccessLevelEnum.ACCESS_OWNER))
				throw new SecurityException("Not authorized. Missing owner permission on parent folder");
		}
		
		checkAnyOnwer(folder);
		
		VaultFolderEntity entity = getVaultFolderEntityDao().newVaultFolderEntity();
		getVaultFolderEntityDao().vaultFolderToEntity(folder, entity, true);

		if (entity.getParent() != null)
			entity.setPersonal(entity.getParent().getPersonal());
		
		getVaultFolderEntityDao().create(entity);
		
		updateAcl(entity, folder);
		
		return getVaultFolderEntityDao().toVaultFolder(entity);
		
	}

	@Override
	protected VaultFolder handleUpdate(VaultFolder folder) throws Exception {
		VaultFolderEntity entity = getVaultFolderEntityDao().load(folder.getId());
		// Checks current permission
		AccountAccessLevelEnum al = getVaultFolderEntityDao().toVaultFolder(entity).getAccessLevel();
		if (! al.equals( AccountAccessLevelEnum.ACCESS_OWNER))
			throw new SecurityException("Not authorized. Missing owner permission on folder");

		// Checks permission on new parent folder
		if (folder.getParentId() == null && entity.getParent() != null)
		{
			if (! Security.isUserInRole("sso:createSharedFolders"))
				throw new SecurityException("Not authorized. Missing permission sso:craeteSharedFolders");
		}
		if (folder.getParentId() != null && 
				(entity.getParent() == null || !entity.getParent().getId().equals(folder.getParentId())))
		{
			VaultFolderEntity parent = getVaultFolderEntityDao().load(folder.getParentId());
			al = getVaultFolderEntityDao().toVaultFolder(parent).getAccessLevel();
			if (! al.equals( AccountAccessLevelEnum.ACCESS_OWNER))
				throw new SecurityException("Not authorized. Missing owner permission on parent folder");
		}

		
		checkAnyOnwer(folder);

		getVaultFolderEntityDao().vaultFolderToEntity(folder);
		
		getVaultFolderEntityDao().update(entity);
		updateAcl(entity, folder);
		
		return getVaultFolderEntityDao().toVaultFolder(entity);
	}

	
	@Override
	protected List<VaultFolder> handleGetRootFolders() throws Exception {
		List<VaultFolderEntity> list = getVaultFolderEntityDao().findRoots();
		List<VaultFolder> folders = getVaultFolderEntityDao().toVaultFolderList(list);
		filterFolders(folders);
		
		// Ensure there is a personal folder
		boolean personal = false;
		for (VaultFolder folder: folders)
		{
			if (folder.isPersonal())
			{
				personal = true;
				break;
			}
		}
		if (! personal)
		{
			String userName = Security.getCurrentUser();
			if (userName  != null)
			{
				UsuariEntity userEntity = getUsuariEntityDao().findByCodi(userName);
				if (userEntity != null)
				{
					VaultFolderEntity folder = getVaultFolderEntityDao().newVaultFolderEntity();
					folder.setName("Personal accounts");
					folder.setDescription("Accounts that won't be shared");
					folder.setPersonal(true);
					getVaultFolderEntityDao().create(folder);
					VaultFolderAccessEntity ace = getVaultFolderAccessEntityDao().newVaultFolderAccessEntity();
					ace.setUser( userEntity );
					ace.setLevel(AccountAccessLevelEnum.ACCESS_OWNER);
					ace.setVault(folder);
					getVaultFolderAccessEntityDao().create(ace);
					folder.getAcl().add(ace);
					folders.add( getVaultFolderEntityDao().toVaultFolder(folder));
				}
			}
		}
		
		Collections.sort(folders, new Comparator<VaultFolder>() {
			public int compare(VaultFolder o1, VaultFolder o2) {
				if (o1.isPersonal() && ! o2.isPersonal())
					return -1;
				else if (! o1.isPersonal() && o2.isPersonal())
					return +1;
				else
					return o1.getName().compareTo(o2.getName());
			}
		});
		
		return folders;
	}

	private void filterFolders(List<VaultFolder> folders) {
		for (Iterator<VaultFolder> it = folders.iterator(); it.hasNext();)
		{
			VaultFolder folder = it.next();
			if (folder.getAccessLevel().equals (AccountAccessLevelEnum.ACCESS_NONE))
				it.remove();
		}
		Collections.sort(folders, new Comparator<VaultFolder>() {
			public int compare(VaultFolder o1, VaultFolder o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}
	
	private void updateAcl(VaultFolderEntity entity, VaultFolder folder) throws InternalErrorException
	{
		boolean personal = (entity.getPersonal() != null && entity.getPersonal());
		AccountAccessLevelEnum levels[] = new AccountAccessLevelEnum[] {
				AccountAccessLevelEnum.ACCESS_USER,
				AccountAccessLevelEnum.ACCESS_MANAGER, 
				AccountAccessLevelEnum.ACCESS_OWNER
		};
		if (folder.getGrantedGroups() == null || personal)
			folder.setGrantedGroups(Collections.EMPTY_LIST);
		if (folder.getManagerGroups() == null  || personal)
			folder.setManagerGroups(Collections.EMPTY_LIST);
		if (folder.getOwnerGroups() == null  || personal)
			folder.setOwnerGroups(Collections.EMPTY_LIST);
		if (folder.getGrantedUsers() == null  || personal)
			folder.setGrantedUsers(Collections.EMPTY_LIST);
		if (folder.getManagerUsers() == null  || personal)
			folder.setManagerUsers(Collections.EMPTY_LIST);
		if (personal)
		{
			folder.setOwnerUsers(Collections.singleton(getUsuariService().getCurrentUsuari()));
		}
		else if (folder.getOwnerUsers() == null)
			folder.setOwnerUsers(Collections.EMPTY_LIST);
		if (folder.getGrantedRoles() == null  || personal)
			folder.setGrantedRoles(Collections.EMPTY_LIST);
		if (folder.getManagerRoles() == null  || personal)
			folder.setManagerRoles(Collections.EMPTY_LIST);
		if (folder.getOwnerRoles() == null  || personal)
			folder.setOwnerRoles(Collections.EMPTY_LIST);
		@SuppressWarnings("unchecked")
		List<Grup> newgrups []= new List[] {
			new LinkedList<Grup>(folder.getGrantedGroups()),
			new LinkedList<Grup>(folder.getManagerGroups()),
			new LinkedList<Grup>(folder.getOwnerGroups())
		};
		@SuppressWarnings("unchecked")
		List<Rol> newroles []= new List[] {
			new LinkedList<Rol>(folder.getGrantedRoles()),
			new LinkedList<Rol>(folder.getManagerRoles()),
			new LinkedList<Rol>(folder.getOwnerRoles())
		};
		@SuppressWarnings("unchecked")
		List<Usuari> newusers []= new List[] {
			new LinkedList<Usuari>(folder.getGrantedUsers()),
			new LinkedList<Usuari>(folder.getManagerUsers()),
			new LinkedList<Usuari>(folder.getOwnerUsers())
		};
		// Remove grants
		for (Iterator<VaultFolderAccessEntity> aclIterator = entity.getAcl().iterator(); aclIterator.hasNext();)
		{
			VaultFolderAccessEntity access = aclIterator.next();
			for (int index = 0; index < levels.length; index++)
			{
				if (levels[index] == access.getLevel())
				{
					boolean found = false;
					if (access.getGroup() != null)
					{
						for (Iterator<Grup> it = newgrups[index].iterator(); !found && it.hasNext();)
						{
							Grup g = it.next();
							if (g.getId().equals (access.getGroup().getId()))
							{
								it.remove();
								found = true;
							}
						}
					}
					else if (access.getRol() != null)
					{
						for (Iterator<Rol> it = newroles[index].iterator(); !found && it.hasNext();)
						{
							Rol r = it.next();
							if (r.getId().equals (access.getRol().getId()))
							{
								it.remove();
								found = true;
							}
						}
					}
					else if (access.getUser() != null)
					{
						for (Iterator<Usuari> it = newusers[index].iterator(); !found && it.hasNext();)
						{
							Usuari u = it.next();
							if (u.getId().equals (access.getUser().getId()))
							{
								it.remove();
								found = true;
							}
						}
					}
					if (!found)
					{
						aclIterator.remove();
						checkRemoveParentAce(entity, access);
						removeAccountPermissions (entity, access);
						getVaultFolderAccessEntityDao().remove(access);
					}
				}
			}
		}
		// Add new groups
		for (int index = 0 ; index < levels.length; index++)
		{
			for (Grup g: newgrups[index]) {
				GrupEntity ge = getGrupEntityDao().load(g.getId());
				if (ge != null)
				{
					VaultFolderAccessEntity access = getVaultFolderAccessEntityDao().newVaultFolderAccessEntity();
					access.setGroup(ge);
					access.setVault(entity);
					access.setLevel(levels[index]);
					getVaultFolderAccessEntityDao().create(access);
					entity.getAcl().add(access);
					checkAddParentAce(entity, access);
					addAccountPermissions (entity, access);
				}
			}
			// Add new roles
			for (Rol r: newroles[index]) {
				RolEntity re = getRolEntityDao().load(r.getId());
				if (re != null)
				{
					VaultFolderAccessEntity access = getVaultFolderAccessEntityDao().newVaultFolderAccessEntity();
					access.setRol(re);
					access.setVault(entity);
					access.setLevel(levels[index]);
					getVaultFolderAccessEntityDao().create(access);
					entity.getAcl().add(access);
					checkAddParentAce(entity, access);
					addAccountPermissions (entity, access);
				}
			}
			// Add new users
			for (Usuari u: newusers[index]) {
				UsuariEntity ue = getUsuariEntityDao().load(u.getId());
				if (ue != null)
				{
					VaultFolderAccessEntity access = getVaultFolderAccessEntityDao().newVaultFolderAccessEntity();
					access.setVault(entity);
					access.setUser(ue);
					access.setLevel(levels[index]);
					getVaultFolderAccessEntityDao().create(access);
					entity.getAcl().add(access);
					checkAddParentAce(entity, access);
					addAccountPermissions (entity, access);
				}
			}
		}
	}

	private void addAccountPermissions(VaultFolderEntity entity,
			VaultFolderAccessEntity access) {
		if (access.getLevel() == AccountAccessLevelEnum.ACCESS_NONE)
		{
			removeAccountPermissions(entity, access);
		}
		else if (access.getLevel() == AccountAccessLevelEnum.ACCESS_NAVIGATE)
		{
			// Nothing to do
		}
		else
		{
			for ( AccountEntity ae: entity.getAccounts())
			{
				if (ae.getInheritNewPermissions() == null ||
						ae.getInheritNewPermissions().booleanValue())
				{
					boolean found = false;
					for (AccountAccessEntity aae: ae.getAcl())
					{
						if (matchACE (access, aae))
						{
							found = true;
							aae.setLevel(access.getLevel());
							getAccountAccessEntityDao().update(aae);
						}
					}
					if (! found)
					{
						AccountAccessEntity aae = generateAccountAccessEntity(access, ae);
						getAccountAccessEntityDao().create(aae);
					}
					createAccountTask(ae);
				}
			}
		}
	}

	private boolean matchACE (VaultFolderAccessEntity vfae, AccountAccessEntity aae)
	{
		if (vfae.getGroup() != aae.getGroup())
			return false;
		
		if (vfae.getRol() != aae.getRol())
			return false;
		
		if (vfae.getUser() != aae.getUser())
			return false;
		
		return true;
	}
	
	private void removeAccountPermissions(VaultFolderEntity entity,
			VaultFolderAccessEntity access) {
		for ( AccountEntity ae: entity.getAccounts())
		{
			for (AccountAccessEntity aae: ae.getAcl())
			{
				if (matchACE (access, aae))
				{
					getAccountAccessEntityDao().remove(aae);
					createAccountTask(ae);
					break;
				}
			}
		}
		
	}

	private void createAccountTask(AccountEntity ae)
	{
		if (! ae.getType().equals(AccountType.IGNORED))
		{
			TasqueEntity tasque = getTasqueEntityDao().newTasqueEntity();
			tasque.setTransa(TaskHandler.UPDATE_ACCOUNT);
			tasque.setUsuari(ae.getName());
			tasque.setCoddis(ae.getDispatcher().getCodi());
			getTasqueEntityDao().create(tasque);
		}
	}

	@Override
	protected VaultFolderPermissions handleGetFolderPermissions(
			VaultFolder folder) throws Exception {
		VaultFolderEntity entity = getVaultFolderEntityDao().load(folder.getId());
		// Checks current permission
		AccountAccessLevelEnum al = getVaultFolderEntityDao().toVaultFolder(entity).getAccessLevel();
		if (! al.equals( AccountAccessLevelEnum.ACCESS_OWNER))
			throw new SecurityException("Not authorized. Missing owner permission on folder");

		VaultFolderPermissions p = new VaultFolderPermissions();
		Vector<Object> grantee = new Vector<Object>();

		p.setVaultId(folder.getId());
		p.setGrantee( grantee );
		p.setAccounts( new Vector<VaultFolderAccountPermissions>());
		
		for (VaultFolderAccessEntity perm: entity.getAcl())
		{
			if (!perm.getLevel().equals(AccountAccessLevelEnum.ACCESS_NAVIGATE) &&
					!perm.getLevel().equals(AccountAccessLevelEnum.ACCESS_NONE))
				addGrantee (grantee, perm.getUser(), perm.getGroup(), perm.getRol());
		}
		
		// Sort grantees
		Collections.sort(grantee, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				if (o1 instanceof Usuari)
				{
					if (o2 instanceof Usuari)
						return ((Usuari) o1).getNom().compareTo(((Usuari) o2).getNom());
					else
						return -1;
				}
				if (o1 instanceof Grup)
				{
					if (o2 instanceof Grup)
						return ((Grup) o1).getCodi().compareTo(((Grup) o2).getCodi());
					else
						return -1;
				}
				if (o1 instanceof Rol)
				{
					if (o2 instanceof Rol)
						return ((Rol) o1).getNom().compareTo(((Rol) o2).getNom());
					else
						return -1;
				}
				return 0;
			}
		});

		// Sort accounts
		List<AccountEntity> accounts = new LinkedList<AccountEntity> (entity.getAccounts());
		Collections.sort(accounts, new Comparator<AccountEntity>() {

			public int compare(AccountEntity o1, AccountEntity o2) {
				return o1.getDescription().compareTo(o2.getDescription());
			}
		});
		
		for ( AccountEntity accountEntity: accounts)
		{
			VaultFolderAccountPermissions ap = new VaultFolderAccountPermissions();
			ap.setAccount(getAccountEntityDao().toAccount(accountEntity));
			ap.setPermissions(new Vector<AccountAccessLevelEnum>());
			for (AccountAccessEntity perm : accountEntity.getAcl())
			{
				addPermissions (grantee, ap, perm.getUser(), perm.getGroup(), perm.getRol(), perm.getLevel());
			}
			p.getAccounts().add(ap);
		}
		// Now, normalize vector sizes
		for ( VaultFolderAccountPermissions ap: p.getAccounts())
		{
			while (ap.getPermissions().size() < grantee.size())
			{
				ap.getPermissions().add(AccountAccessLevelEnum.ACCESS_NONE);
			}
		}
		
		return p;
	}

	private void addPermissions(Vector<Object> grantee, VaultFolderAccountPermissions ap, UsuariEntity user,
			GrupEntity group, RolEntity rol, AccountAccessLevelEnum level) {
		if (level.equals (AccountAccessLevelEnum.ACCESS_NONE))
			return;
		
		int index;
		for (index = 0; index < grantee.size(); index++)
		{
			Object g = grantee.get(index);
			if (match (g, user, group, rol))
				break;
		}
		if (index == grantee.size())
			addGrantee (grantee, user, group, rol);
		while (ap.getPermissions().size() <= index)
			ap.getPermissions().add(AccountAccessLevelEnum.ACCESS_NONE);
		ap.getPermissions().set(index, level);
	}

	private void addGrantee(Vector<Object> grantee, UsuariEntity user,
			GrupEntity group, RolEntity rol) {
		for (Object g: grantee)
		{
			if (match (g, user, group, rol))
				return;
		}
		if (user != null)
			grantee.add(getUsuariEntityDao().toUsuari(user));
		else if (group != null)
			grantee.add(getGrupEntityDao().toGrup(group));
		else if (rol != null)
			grantee.add(getRolEntityDao().toRol(rol));
	}

	@Override
	protected void handleApplyFolderPermissions(
			VaultFolderPermissions permissions) throws Exception {
		VaultFolderEntity entity = getVaultFolderEntityDao().load(permissions.getVaultId());
		// Checks current permission
		AccountAccessLevelEnum al = getVaultFolderEntityDao().toVaultFolder(entity).getAccessLevel();
		if (! al.equals( AccountAccessLevelEnum.ACCESS_OWNER))
			throw new SecurityException("Not authorized. Missing owner permission on folder");
		
		
		for ( VaultFolderAccountPermissions ap: permissions.getAccounts())
		{
			AccountEntity account = getAccountEntityDao().load(ap.getAccount().getId());
			if (account != null && account.getFolder() == entity)
			{
				boolean anyChange = false;
				for (int i = 0; i < ap.getPermissions().size(); i++)
				{
					if ( i < permissions.getGrantee().size())
					{
						if (grant (permissions.getGrantee().get(i), account, ap.getPermissions().get(i)))
							anyChange = true;
					}
				}
				if (anyChange)
				{
					createAccountTask(account);
				}
			}
		}
	}

	
	private boolean match (Object g, UsuariEntity user, GrupEntity group, RolEntity rol)
	{
		if (user != null && (g instanceof Usuari) && ((Usuari) g).getId().equals(user.getId()))
		{
			return true;
		}
		if (rol != null && (g instanceof Rol) && ((Rol) g).getId().equals(rol.getId()))
		{
			return true;
		}
		if (group != null && (g instanceof Grup) && ((Grup) g).getId().equals(group.getId()))
		{
			return true;
		}
		return false;
	}
	
	private boolean grant(Object object, AccountEntity account,
			AccountAccessLevelEnum level) {
		for ( AccountAccessEntity ace: account.getAcl())
		{
			if (match (object, ace.getUser(), ace.getGroup(), ace.getRol()))
			{
				if (! ace.getLevel().equals (level))
				{
					if (level.equals (AccountAccessLevelEnum.ACCESS_NONE))
						getAccountAccessEntityDao().remove(ace);
					else
					{
						ace.setLevel(level);
						getAccountAccessEntityDao().update(ace);
					}
					return true;
				}
				else
					return false;
				
			}
		}
		if (level.equals (AccountAccessLevelEnum.ACCESS_NONE))
			return false;
		// Create new ace
		AccountAccessEntity ace = getAccountAccessEntityDao().newAccountAccessEntity();
		ace.setAccount(account);
		if (object instanceof Usuari)
			ace.setUser( getUsuariEntityDao().load (((Usuari) object).getId()));
		if (object instanceof Grup)
			ace.setGroup( getGrupEntityDao().load (((Grup) object).getId()));
		if (object instanceof Rol)
			ace.setRol( getRolEntityDao().load (((Rol) object).getId()));
		ace.setLevel(level);
		getAccountAccessEntityDao().create(ace);
		account.getAcl().add(ace);
		
		return true;
	}

	@Override
	protected VaultFolder handleGetPersonalFolder() throws Exception {
		for (VaultFolder folder: handleGetRootFolders())
		{
			if (folder.isPersonal())
				return folder;
		}
		return null;
	}

	@Override
	protected List<VaultFolder> handleFindFolders(String filter)
			throws Exception {
		if (filter == null || filter.trim().length() == 0)
			return getRootFolders();
		else
		{
			LinkedList<VaultFolder> list = new LinkedList<VaultFolder>();
			for (VaultFolderEntity entity: getVaultFolderEntityDao().findByName("%"+filter.replace('*', '%')+"%"))
			{
				VaultFolder folder = getVaultFolderEntityDao().toVaultFolder(entity);
				if (! folder.getAccessLevel().equals(AccountAccessLevelEnum.ACCESS_NONE))
				{
						list.add(folder);
				}
			}
			return list;
		}
	}

	@Override
	protected List<Account> handleFindAccounts(String filter)
			throws Exception {
		if (filter == null || filter.trim().length() == 0)
			return Collections.emptyList();
		else
		{
			LinkedList<Account> list = new LinkedList<Account>();
			AccountCriteria criteria = new AccountCriteria();
			criteria.setDescription("%"+filter.replace('*', '%')+"%");
			for (AccountEntity entity: getAccountEntityDao().findByCriteria(criteria))
			{
				if (entity.getFolder() != null)
				{
					Account account = getAccountEntityDao().toAccount(entity);
					if (! account.getAccessLevel().equals(AccountAccessLevelEnum.ACCESS_NONE))
					{
						list.add(account);
					}
				}
			}
			return list;
		}
	}

}
