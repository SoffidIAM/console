package com.soffid.iam.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.jcs.access.behavior.ICacheAccess;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.api.AccessControlList;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.spring.JCSCacheProvider;
import com.soffid.iam.utils.Security;

public class ACLServiceImpl extends ACLServiceBase  {
	
	public ACLServiceImpl ()
	{
	}
	
	Log log = LogFactory.getLog(getClass());
	
	@Override
	protected AccessControlList handleExpandUser(long userId) throws Exception {
		UserEntity ue = getUserEntityDao().load(userId);
		if (ue == null)
			return new AccessControlList();
		
		PermissionCache pc = getCache().get(userId);
		if (pc != null)
		{
			return pc.acl;
		}
		AccessControlList acl = new AccessControlList();
		acl.setGroups( new HashSet<Long>());
		acl.setUsers( new HashSet<Long>());
		acl.setRoles( new HashSet<Long>());
		
		acl.getUsers().add (userId);
		for (RoleGrant rg: getApplicationService().findEffectiveRoleGrantByUser(userId) )
		{
			acl.getRoles().add(rg.getRoleId());
		}
		
		if (ue != null)
		{
			recursivelyAddGroup (acl, ue.getPrimaryGroup());
			for (UserGroupEntity uge: ue.getSecondaryGroups())
			{
				if (! Boolean.TRUE.equals(uge.getDisabled()))
					recursivelyAddGroup (acl, uge.getGroup());
			}
		}
		pc = new PermissionCache();
		pc.acl = acl;
		pc.lastModification = ue.getLastModificationDate();
		pc.evaluationDate = new Date();
		getCache().put(userId, pc);
//		log.info("Entry stored");
		
		return acl;
	}

	private void recursivelyAddGroup(AccessControlList acl,
			GroupEntity grupPrimary) {
		GroupEntity g = grupPrimary;
		while ( g != null && ! acl.getGroups().contains(g.getId()))
		{
			acl.getGroups().add(g.getId());
			g = g.getParent();
		}
	}

	@Override
	protected boolean handleIsUserIncluded(long userId, AccessControlList acl)
			throws Exception {
		for (Long userId2: acl.getUsers())
		{
			if (userId2.equals(userId)) return true;
		}
		
		AccessControlList userAcl = expandUser(userId);
		for ( Long groupId: acl.getGroups())
		{
			if (userAcl.getGroups().contains(groupId))
				return true;
		}
		for ( Long roleId: acl.getRoles())
		{
			if (userAcl.getRoles().contains(roleId))
				return true;
		}
		return false;
	}

	@Override
	protected boolean handleIsAccountIncluded(long accountId, AccessControlList acl)
			throws Exception {
		for (RoleGrant rg: getApplicationService().findEffectiveRoleGrantByAccount(accountId) )
		{
			if (acl.getRoles().contains(rg.getRoleId()))
				return true;
		}
		return false;
	}

	@Override
	protected AccessControlList handleExpandACL(AccessControlList acl)
			throws Exception {
		AccessControlList acl2 = new AccessControlList();
		acl2.setGroups( new HashSet<Long>());
		acl2.setUsers( new HashSet<Long>());
		acl2.setRoles( new HashSet<Long>());
		
		acl2.getUsers().addAll(acl.getUsers());
		for (Long groupId: acl.getGroups())
		{
			GroupEntity ge = getGroupEntityDao().load(groupId);
			addGroupMembers (ge, acl2.getUsers());
		}

		for (Long roleId: acl.getRoles())
		{
			for (RoleGrant grant: getApplicationService().findEffectiveRoleGrantsByRoleId(roleId))
			{
				if (grant.getUser() != null)
				{
					UserEntity ue = getUserEntityDao().findByUserName(grant.getUser());
					if (ue != null)
						acl2.getUsers().add(ue.getId());
				}
			}
		}
		
		return acl2;
		
	}

	@Override
	protected Collection<String> handleExpandACLAccounts(AccessControlList acl)
			throws Exception {
		Collection<String> accounts = new HashSet<String>();

		com.soffid.iam.api.System d = getDispatcherService().findSoffidDispatcher();
		
		for (Long user: acl.getUsers())
		{
			UserEntity ue = getUserEntityDao().load(user);
			addUserAccounts(ue, d, accounts);
						
		}

		for (Long groupId: acl.getGroups())
		{
			GroupEntity ge = getGroupEntityDao().load(groupId);
			addGroupMembers (ge, d, accounts);
		}

		for (Long roleId: acl.getRoles())
		{
			for (RoleGrant grant: getApplicationService().findEffectiveRoleGrantsByRoleId(roleId))
			{
				if (grant.getOwnerAccountName() != null && grant.getOwnerSystem().equals(d.getName()))
					accounts.add(grant.getOwnerAccountName());
				else if ( grant.getUser() != null)
				{
					UserEntity ue = getUserEntityDao().findByUserName(grant.getUser());
					if (ue != null)
						addUserAccounts(ue, d, accounts);
				}
			}
		}
		
		return accounts;
		
	}

	private void addUserAccounts(UserEntity ue, com.soffid.iam.api.System d,
			Collection<String> accounts) {
		if (ue != null)
		{
			for (com.soffid.iam.model.AccountEntity acc: getAccountEntityDao().findByUserAndSystem(ue.getUserName(), d.getName())) {
				accounts.add(acc.getName());
			}
		}
	}

	private void addGroupMembers(GroupEntity ge, Set<Long> users) {
		for ( UserEntity ue: ge.getPrimaryGroupUsers())
			users.add(ue.getId());

		for ( UserGroupEntity ue: ge.getSecondaryGroupUsers())
			if (! Boolean.TRUE.equals(ue.getDisabled()))
				users.add(ue.getUser().getId());
		
		for (GroupEntity child: ge.getChildren())
			addGroupMembers(child, users);
	}


	private void addGroupMembers(GroupEntity ge, com.soffid.iam.api.System d, Collection<String> accounts) {
		for ( UserEntity ue: ge.getPrimaryGroupUsers())
			addUserAccounts(ue, d, accounts);

		for ( UserGroupEntity ue: ge.getSecondaryGroupUsers())
			if (! Boolean.TRUE.equals(ue.getDisabled()))
				addUserAccounts(ue.getUser(), d, accounts);
		
		for (GroupEntity child: ge.getChildren())
			addGroupMembers(child, d, accounts);
	}

	
	private ICacheAccess<Long, PermissionCache> cache;
	private ICacheAccess<Long, PermissionCache> getCache()
	{ 
		if (cache == null)
			cache = JCSCacheProvider.buildCache(PermissionCache.class.getName());
		return cache;
	}

	@Override
	protected boolean handleIsCurrentUserIncluded(AccessControlList acl) throws Exception {
		SoffidPrincipal p = Security.getSoffidPrincipal();
		if (p == null)
			return false;
		if (p.getUserId() != null) {
			if (acl.getUsers().contains(p.getUserId()))
				return true;
		}
		if (p.getSoffidRoles() != null) {
			for ( Long roleId: p.getRoleIds())
			{
				if (acl.getRoles().contains(roleId))
					return true;
			}
		}
		if (p.getGroupIds() != null) {
			for ( Long id: p.getGroupIds())
			{
				if (acl.getGroups().contains(id))
					return true;
			}
		}
		return false;
	}

}

class PermissionCache
{
	public Date lastModification;
	
	public Date evaluationDate;
	
	AccessControlList acl;
}
