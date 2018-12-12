package com.soffid.iam.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.User;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.api.VaultFolderAccountPermissions;
import com.soffid.iam.api.VaultFolderPermissions;
import com.soffid.iam.model.AccountAccessEntity;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.VaultFolderAccessEntity;
import com.soffid.iam.model.VaultFolderEntity;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountCriteria;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
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
				if (newFolderEntity.getPersonal() ||
						(oldFolderEntity != null && 
						oldFolderEntity.getPersonal()) )
					accountEntity.setInheritNewPermissions(true);
					
				if (accountEntity.getInheritNewPermissions() != null &&
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
		for ( AccountAccessEntity aae: new LinkedList<AccountAccessEntity> (accountEntity.getAcl()))
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
				accountEntity.getAcl().add(aae);
			}
		}
	}

	private AccountAccessEntity generateAccountAccessEntity(
			VaultFolderAccessEntity folderAccess, AccountEntity accountEntity) {
		AccountAccessEntity aae = getAccountAccessEntityDao().newAccountAccessEntity();
		aae.setAccount(accountEntity);
		aae.setGroup(folderAccess.getGroup());
		aae.setRole(folderAccess.getRole());
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
		
		getVaultFolderAccessEntityDao().remove(entity.getAcl());
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
							ace2.getRole() == ace.getRole() &&
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
						ace2.getRole() == ace.getRole() &&
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
					ace2.getRole() == ace.getRole() &&
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
				ace2.setRole(ace.getRole());
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

	/**
	 * Creates a "Navigate" ACE into parent, if needed
	 * 
	 * It would be possible if no other sibling folder has a similar ACE.
	 * 
	 * @param entity
	 * @param ace Current access control entry
	 * @return
	 */
	private boolean checkAddAce(VaultFolderEntity parent,
			AccountAccessEntity ace) {
		boolean add = true;
		if (parent != null)
		{
			for (VaultFolderAccessEntity ace2: parent.getAcl())
			{
				if (! ace2.getLevel().equals( AccountAccessLevelEnum.ACCESS_NONE ) &&
					ace2.getGroup() == ace.getGroup() &&
					ace2.getRole() == ace.getRole() &&
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
				ace2.setRole(ace.getRole());
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
		List<Account> accounts = getAccountEntityDao().toAccountList(entity.getAccounts());
		if (folder.getAccessLevel().equals( AccountAccessLevelEnum.ACCESS_NONE ))
		{
			return accounts;
		}
		
		for (Iterator<Account> it = accounts.iterator(); it.hasNext();)
		{
			Account account = it.next();
			if (account.getAccessLevel().equals (AccountAccessLevelEnum.ACCESS_NONE))
				it.remove();
		}
		Collections.sort(accounts, new Comparator<Account>() {
			public int compare(Account o1, Account o2) {
				if (o1.getDescription() == null && o2.getDescription() == null) return 0;
				else if (o2.getDescription() == null) return -1;
				else if (o1.getDescription() == null) return +1;
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
		List<VaultFolderEntity> list = getVaultFolderEntityDao().findPublicRoots();
		if (Security.getCurrentUser() != null)
			list.addAll( getVaultFolderEntityDao().findPersonalFolders(Security.getCurrentUser()) );
		
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
				UserEntity userEntity = getUserEntityDao().findByUserName(userName);
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
			folder.setOwnerUsers(Collections.singleton(getUserService().getCurrentUser()));
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
		List<Group> newgrups []= new List[] {
			new LinkedList<Group>(folder.getGrantedGroups()),
			new LinkedList<Group>(folder.getManagerGroups()),
			new LinkedList<Group>(folder.getOwnerGroups())
		};
		@SuppressWarnings("unchecked")
		List<Role> newroles []= new List[] {
			new LinkedList<Role>(folder.getGrantedRoles()),
			new LinkedList<Role>(folder.getManagerRoles()),
			new LinkedList<Role>(folder.getOwnerRoles())
		};
		@SuppressWarnings("unchecked")
		List<User> newusers []= new List[] {
			new LinkedList<User>(folder.getGrantedUsers()),
			new LinkedList<User>(folder.getManagerUsers()),
			new LinkedList<User>(folder.getOwnerUsers())
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
						for (Iterator<Group> it = newgrups[index].iterator(); !found && it.hasNext();)
						{
							Group g = it.next();
							if (g.getId().equals (access.getGroup().getId()))
							{
								it.remove();
								found = true;
							}
						}
					}
					else if (access.getRole() != null)
					{
						for (Iterator<Role> it = newroles[index].iterator(); !found && it.hasNext();)
						{
							Role r = it.next();
							if (r.getId().equals (access.getRole().getId()))
							{
								it.remove();
								found = true;
							}
						}
					}
					else if (access.getUser() != null)
					{
						for (Iterator<User> it = newusers[index].iterator(); !found && it.hasNext();)
						{
							User u = it.next();
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
			for (Group g: newgrups[index]) {
				GroupEntity ge = getGroupEntityDao().load(g.getId());
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
			for (Role r: newroles[index]) {
				RoleEntity re = getRoleEntityDao().load(r.getId());
				if (re != null)
				{
					VaultFolderAccessEntity access = getVaultFolderAccessEntityDao().newVaultFolderAccessEntity();
					access.setRole(re);
					access.setVault(entity);
					access.setLevel(levels[index]);
					getVaultFolderAccessEntityDao().create(access);
					entity.getAcl().add(access);
					checkAddParentAce(entity, access);
					addAccountPermissions (entity, access);
				}
			}
			// Add new users
			for (User u: newusers[index]) {
				UserEntity ue = getUserEntityDao().load(u.getId());
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
		
		if (vfae.getRole() != aae.getRole())
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
			TaskEntity tasque = getTaskEntityDao().newTaskEntity();
			tasque.setTransaction(com.soffid.iam.sync.engine.TaskHandler.UPDATE_ACCOUNT);
			tasque.setUser(ae.getName());
			tasque.setSystemName(ae.getSystem().getName());
			getTaskEntityDao().create(tasque);
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
				addGrantee (grantee, perm.getUser(), perm.getGroup(), perm.getRole());
		}
		
		// Sort grantees
		Collections.sort(grantee, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				if (o1 instanceof User)
				{
					if (o2 instanceof User)
						return ((User) o1).getUserName().compareTo(((User) o2).getUserName());
					else
						return -1;
				}
				if (o1 instanceof Group)
				{
					if (o2 instanceof Group)
						return ((Group) o1).getName().compareTo(((Group) o2).getName());
					else
						return -1;
				}
				if (o1 instanceof Role)
				{
					if (o2 instanceof Role)
						return ((Role) o1).getName().compareTo(((Role) o2).getName());
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
				return o1.getDescription() == null && o2.getDescription() == null ? 0:
					o1.getDescription() == null ? -1 :
						o2.getDescription() == null ? 1 :
							o1.getDescription().compareTo(o2.getDescription());
			}
		});
		
		for ( AccountEntity accountEntity: accounts)
		{
			VaultFolderAccountPermissions ap = new VaultFolderAccountPermissions();
			ap.setAccount(getAccountEntityDao().toAccount(accountEntity));
			ap.setPermissions(new Vector<AccountAccessLevelEnum>());
			for (AccountAccessEntity perm : accountEntity.getAcl())
			{
				addPermissions (grantee, ap, perm.getUser(), perm.getGroup(), perm.getRole(), perm.getLevel());
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

	private void addPermissions(Vector<Object> grantee, VaultFolderAccountPermissions ap, UserEntity user,
			GroupEntity group, RoleEntity rol, AccountAccessLevelEnum level) {
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

	private void addGrantee(Vector<Object> grantee, UserEntity user,
			GroupEntity group, RoleEntity rol) {
		for (Object g: grantee)
		{
			if (match (g, user, group, rol))
				return;
		}
		if (user != null)
			grantee.add(getUserEntityDao().toUser(user));
		else if (group != null)
			grantee.add(getGroupEntityDao().toGroup(group));
		else if (rol != null)
			grantee.add(getRoleEntityDao().toRole(rol));
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

	
	private boolean match (Object g, UserEntity user, GroupEntity group, RoleEntity rol)
	{
		if (user != null && (g instanceof User) && ((User) g).getId().equals(user.getId()))
		{
			return true;
		}
		if (rol != null && (g instanceof Role) && ((Role) g).getId().equals(rol.getId()))
		{
			return true;
		}
		if (group != null && (g instanceof Group) && ((Group) g).getId().equals(group.getId()))
		{
			return true;
		}
		return false;
	}
	
	private boolean grant(Object object, AccountEntity account,
			AccountAccessLevelEnum level) {
		for ( AccountAccessEntity ace: account.getAcl())
		{
			if (match (object, ace.getUser(), ace.getGroup(), ace.getRole()))
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
		if (object instanceof User)
			ace.setUser( getUserEntityDao().load (((User) object).getId()));
		if (object instanceof Group)
			ace.setGroup( getGroupEntityDao().load (((Group) object).getId()));
		if (object instanceof Role)
			ace.setRole( getRoleEntityDao().load (((Role) object).getId()));
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

	@Override
	protected VaultFolder handleFindFolder(long id) throws Exception {
		VaultFolderEntity entity = getVaultFolderEntityDao().load(id);
		VaultFolder folder = getVaultFolderEntityDao().toVaultFolder(entity);
		
		if (folder.getAccessLevel().equals (AccountAccessLevelEnum.ACCESS_NONE))
			return null;
		else
			return folder;
	}

	@Override
	protected List<VaultFolder> handleGetPublicRootFolders() throws Exception {
		List<VaultFolderEntity> list = getVaultFolderEntityDao().findPublicRoots();
		List<VaultFolder> folders = getVaultFolderEntityDao().toVaultFolderList(list);
		filterFolders(folders);
		
		
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
}
