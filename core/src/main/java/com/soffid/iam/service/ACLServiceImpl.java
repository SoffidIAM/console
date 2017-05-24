package com.soffid.iam.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.LRUMap;

import com.soffid.iam.api.AccessControlList;

import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.UsuariGrupEntity;

public class ACLServiceImpl extends ACLServiceBase {
	
	private Map<Long,PermissionCache> permissionCache;

	public ACLServiceImpl ()
	{
    	int size = 50;
    	try {
	    	String cacheSize = System.getProperty("soffid.cache.identity.size");
	    	if (cacheSize != null )
	    		size = Integer.parseInt(cacheSize);
    	} catch (Throwable t) {
    		
    	}
        permissionCache = Collections.synchronizedMap(new LRUMap(size));
	}
	
	@Override
	protected AccessControlList handleExpandUser(long userId) throws Exception {
		UsuariEntity ue = getUsuariEntityDao().load(userId);
		if (ue == null)
			return new AccessControlList();
		
		PermissionCache pc = permissionCache.get (userId);
		if (pc != null)
		{
			if (System.currentTimeMillis() - pc.evaluationDate.getTime() < 30L*1000L) // 30 seconds live for ACL
			{
				if ( ue.getDataDarreraModificacio().before(pc.lastModification))
					return pc.acl;
			}
		}
		AccessControlList acl = new AccessControlList();
		acl.setGroups( new HashSet<Long>());
		acl.setUsers( new HashSet<Long>());
		acl.setRoles( new HashSet<Long>());
		
		acl.getUsers().add (userId);
		for (es.caib.seycon.ng.comu.RolGrant rg: getAplicacioService().findEffectiveRolGrantByUser(userId) )
		{
			acl.getRoles().add(rg.getIdRol());
		}
		
		if (ue != null)
		{
			recursivelyAddGroup (acl, ue.getGrupPrimari());
			for (es.caib.seycon.ng.model.UsuariGrupEntity uge: ue.getGrupsSecundaris())
			{
				recursivelyAddGroup (acl, uge.getGrup());
			}
		}
		pc = new PermissionCache();
		pc.acl = acl;
		pc.lastModification = ue.getDataDarreraModificacio();
		pc.evaluationDate = new Date();
		
		return acl;
	}

	private void recursivelyAddGroup(AccessControlList acl,
			es.caib.seycon.ng.model.GrupEntity grupPrimary) {
		es.caib.seycon.ng.model.GrupEntity g = grupPrimary;
		while ( g != null && ! acl.getGroups().contains(g.getId()))
		{
			acl.getGroups().add(g.getId());
			g = g.getPare();
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
		for (es.caib.seycon.ng.comu.RolGrant rg: getAplicacioService().findEffectiveRolGrantByAccount(accountId) )
		{
			if (acl.getRoles().contains(rg.getIdRol()))
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
			GrupEntity ge = getGrupEntityDao().load(groupId);
			addGroupMembers (ge, acl2.getUsers());
		}

		for (Long roleId: acl.getRoles())
		{
			for (RolGrant grant: getAplicacioService().findEffectiveRolGrantsByRolId(roleId))
			{
				if (grant.getUser() != null)
				{
					UsuariEntity ue = getUsuariEntityDao().findByCodi(grant.getUser());
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

		Dispatcher d = getDispatcherService().findSoffidDispatcher();
		
		for (Long user: acl.getUsers())
		{
			UsuariEntity ue = getUsuariEntityDao().load(user);
			addUserAccounts(ue, d, accounts);
						
		}

		for (Long groupId: acl.getGroups())
		{
			GrupEntity ge = getGrupEntityDao().load(groupId);
			addGroupMembers (ge, d, accounts);
		}

		for (Long roleId: acl.getRoles())
		{
			for (RolGrant grant: getAplicacioService().findEffectiveRolGrantsByRolId(roleId))
			{
				if (grant.getOwnerDispatcher().equals(d.getCodi()))
					accounts.add(grant.getOwnerAccountName());
				else if ( grant.getUser() != null)
				{
					UsuariEntity ue = getUsuariEntityDao().findByCodi(grant.getUser());
					if (ue != null)
						addUserAccounts(ue, d, accounts);
				}
			}
		}
		
		return accounts;
		
	}

	private void addUserAccounts(UsuariEntity ue, Dispatcher d,
			Collection<String> accounts) {
		if (ue != null)
		{
			for (AccountEntity acc: getAccountEntityDao().findByUsuariAndDispatcher(ue.getCodi(), d.getCodi())) {
				accounts.add(acc.getName());
			}
		}
	}

	private void addGroupMembers(GrupEntity ge, Set<Long> users) {
		for ( UsuariEntity ue: ge.getUsuarisGrupPrimari())
			users.add(ue.getId());

		for ( UsuariGrupEntity ue: ge.getUsuarisGrupSecundari())
			users.add(ue.getUsuari().getId());
		
		for (GrupEntity child: ge.getFills())
			addGroupMembers(child, users);
	}


	private void addGroupMembers(GrupEntity ge, Dispatcher d, Collection<String> accounts) {
		for ( UsuariEntity ue: ge.getUsuarisGrupPrimari())
			addUserAccounts(ue, d, accounts);

		for ( UsuariGrupEntity ue: ge.getUsuarisGrupSecundari())
			addUserAccounts(ue.getUsuari(), d, accounts);
		
		for (GrupEntity child: ge.getFills())
			addGroupMembers(child, d, accounts);
	}

}

class PermissionCache
{
	public Date lastModification;
	
	public Date evaluationDate;
	
	AccessControlList acl;
}
