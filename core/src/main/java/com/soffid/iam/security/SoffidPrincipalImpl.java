package com.soffid.iam.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.realm.GenericPrincipal;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.User;
import com.soffid.iam.common.security.Obligation;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.service.AccountService;
import com.soffid.iam.service.ApplicationService;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.service.DispatcherService;
import com.soffid.iam.service.GroupService;
import com.soffid.iam.service.UserService;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.UnknownUserException;

public class SoffidPrincipalImpl extends GenericPrincipal implements SoffidPrincipal {
	String fullName;
	String tenant;
	String holderGroup;
	private String[] groups;
	private String[] soffidRoles;
	private String[] groupsAndRoles;
	private String userName;
	List<Obligation> obligations = new LinkedList<>();
	private Map<String, SoffidPrincipal> holderGroupMap;
	private List<Long> roleIds;
	private List<Long> accountIds;
	private List<Long> groupIds;
	private Long userId;
	long timestamp;
	private String[] permissions;
	static long clearCacheTimestamp;
	private static Executor executor = Executors.newSingleThreadExecutor();
	private static UserService userService;
	private static AccountService accountService;
	private static DispatcherService dispatcherService;
	private static ApplicationService applicationService;
	private static AuthorizationService authorizationService;
	private static GroupService groupService;
	{
		if (! Security.isSyncProxy()) {
			userService = ServiceLocator.instance().getUserService();
			accountService = ServiceLocator.instance().getAccountService();
			dispatcherService = ServiceLocator.instance().getDispatcherService();
			applicationService = ServiceLocator.instance().getApplicationService();
			groupService = ServiceLocator.instance().getGroupService();
			authorizationService = ServiceLocator.instance().getAuthorizationService();
		} 
	}
	
	static public void clearCache() {
		clearCacheTimestamp = System.currentTimeMillis() + 500;
	}
	
	public SoffidPrincipalImpl(String name,
			String userName,
			String fullName,
			String holderGroup,
			List<String> permissions,
			List<String> groups,
			List<String> soffidRoles,
			List<Long> roleIds,
			List<Long> accountIds,
			List<Long> groupIds,
			Long userId) {
		super(name, "*", permissions);
		init (name, 
				groups == null ? new String[0]: groups.toArray(new String[groups.size()]), 
				soffidRoles == null ? new String[0]: soffidRoles.toArray(new String[soffidRoles.size()]) );
		this.holderGroup = holderGroup;
		this.fullName = fullName;
		this.userName = userName;
		this.roleIds = roleIds == null ? new LinkedList<>(): new LinkedList<>(roleIds);
		this.accountIds = accountIds == null ? new LinkedList<>(): new LinkedList<>(accountIds);
		this.groupIds = groupIds == null ? new LinkedList<>() : new LinkedList<>(groupIds);
		this.userId = userId;
		this.permissions = roles;
		timestamp = System.currentTimeMillis();
	}

	public SoffidPrincipalImpl(String name,
			String userName,
			String fullName,
			String holderGroup,
			List<String> permissions,
			List<String> groups,
			List<String> soffidRoles,
			Map<String, SoffidPrincipal> holderGroupMap,
			List<Long> roleIds,
			List<Long> accountIds,
			List<Long> groupIds,
			Long userId) {
		super(name, "*", permissions);
		init (name, 
				groups == null ? new String[0]: groups.toArray(new String[groups.size()]), 
				soffidRoles == null ? new String[0]: soffidRoles.toArray(new String[soffidRoles.size()]) );
		this.holderGroup = holderGroup;
		this.fullName = fullName;
		this.userName = userName;
		this.holderGroupMap = holderGroupMap;
		this.roleIds = roleIds == null ? new LinkedList<>(): new LinkedList<>(roleIds);
		this.accountIds = accountIds == null ? new LinkedList<>(): new LinkedList<>(accountIds);
		this.groupIds = groupIds == null ? new LinkedList<>() : new LinkedList<>(groupIds);
		this.userId = userId;
		this.permissions = roles;
		timestamp =  System.currentTimeMillis();
	}

	public SoffidPrincipalImpl(String name,  
			List<String> permissions,
			SoffidPrincipal parent) {
		super(name, "*", permissions);
		if (parent == null)
			init (name, new String[0], new String[0]);
		else
		{
			init(name, parent.getGroups(), parent.getSoffidRoles());
			userName = parent.getUserName() == null ? 
					null: 
				parent.getUserName().startsWith("*")? 
					parent.getUserName(): 
					"*"+parent.getUserName();
			fullName = parent.getFullName();
			holderGroup = parent.getHolderGroup();
		}
		this.permissions = roles;
	}

	private void init(String name, String[] groups, String[] soffidRoles) {
		int i = name.indexOf("\\");
		if (i > 0)
		{
			tenant = name.substring(0, i);
		}
		else
			tenant = "master";
		if (groups == null)
			this.groups = new String[0];
		else
			this.groups = groups;
		if (soffidRoles == null)
			this.soffidRoles = new String[0];
		else
			this.soffidRoles = soffidRoles;
		groupsAndRoles = new String [groups.length + soffidRoles.length];
		int idx = 0;
		for (int j = 0; j < soffidRoles.length; j++)
			groupsAndRoles[idx++] = soffidRoles[j];
		for (int j = 0; j < groups.length; j++)
			groupsAndRoles[idx++] = groups[j];
		Arrays.sort(soffidRoles);
		Arrays.sort(groups);
		Arrays.sort(groupsAndRoles);
	}


	public String getFullName() {
		return fullName;
	}

	public String getTenant() {
		return tenant;
	}

	public String getHolderGroup() {
		return holderGroup;
	}

	public void refresh() {
		if (Security.isSyncProxy() || Security.isSyncServer())
			return;
		if (holderGroup != null && holderGroupMap != null)
			return;
		if ( userId != null && 
				clearCacheTimestamp < System.currentTimeMillis() &&
				(timestamp < clearCacheTimestamp ||
						timestamp < System.currentTimeMillis() - 10 * 60 * 60 * 1000L)) { // 10 minutes cache
			executor.execute( () -> {
				if (clearCacheTimestamp > System.currentTimeMillis()) // Give time to finish transaction
					return ;
				if (timestamp >= clearCacheTimestamp && 
					timestamp > System.currentTimeMillis() - 10 * 60 * 60 * 1000L)
					return; // Already processed and not needed
				timestamp = System.currentTimeMillis();
				User user = null;
				Security.nestedLogin(tenant, "anonymous", Security.ALL_PERMISSIONS);
				try {
					user = userService.findUserByUserId(userId);
					
					int i = name.indexOf('\\');
					String account;
					if (i < 0) {
						account = name;
					} else {
						account = name.substring(i + 1);
					}

					Account acc = accountService.findAccount(account, dispatcherService.findSoffidDispatcher().getName());
					if (acc == null || acc.isDisabled() ||
							user == null || Boolean.FALSE.equals( user.getActive())) {
						permissions = new String[0];
						soffidRoles = new String[0];
						groups = new String[0];
						accountIds = new LinkedList<>();
						return;
					}
					
					// Update authorizations
					updatePermissions(acc);
					// Update account ids
					accountIds = new LinkedList<Long>(accountService.getUserGrantedAccountIds(user));
					// UdpateGroups
					updateGroups(acc);
					// update roles
					updateRoles(acc, user);
					// update group and roles
					updateGroupsAndRoles();
				} catch (Exception e) {
					LogFactory.getLog(getClass()).warn("Error refreshing permissions of "+name,e);
				} finally {
					Security.nestedLogoff();
				}
			});
		}
	}

	private void updateGroupsAndRoles() {
		List<String> l = new LinkedList<>();
		l.addAll(Arrays.asList(roles));
		l.addAll(Arrays.asList(groups));
		String[] a = l.toArray(new String[l.size()]);
		Arrays.sort(a);
		groupsAndRoles = a;
	}

	private void updateRoles(Account acc, User u) throws InternalErrorException {
    	List<String> result = new LinkedList<String>();
    	List<Long> roleIds = new LinkedList<>();
    	Collection<RoleGrant> groups;
		if (u != null)
    	{
    		result.add(u.getUserName());
			if (holderGroup == null)
    			groups = applicationService.findEffectiveRoleGrantByUser(u.getId());
    		else {
				Group group = groupService.findGroupByGroupName(holderGroup);
    			if (group == null)
        			groups = applicationService.findEffectiveRoleGrantByUser(u.getId());
    			else
    				groups = applicationService.findEffectiveRoleGrantByUserAndHolderGroup(u.getId(), group.getId());
    		}
    	} else {
			groups = applicationService.findEffectiveRoleGrantByAccount(acc.getId());
    	}
    	
    	com.soffid.iam.api.System soffidSystem = dispatcherService.findSoffidDispatcher();
    	for ( RoleGrant grant: groups)
    	{
    		if (soffidSystem.getName().equals( grant.getSystem() ) && acc.getName().equals(grant.getOwnerAccountName()))
    			result.add(grant.getRoleName());
    		result.add(grant.getRoleName()+"@"+grant.getSystem());
    		if (roleIds != null)
    			roleIds.add(grant.getRoleId());
    		if (grant.getDomainValue() != null)
    		{
    			if (grant.getSystem().equals(soffidSystem.getName()))
    				result.add(grant.getRoleName()+"/"+grant.getDomainValue());
    			result.add(grant.getRoleName()+"/"+grant.getDomainValue()+"@"+grant.getSystem());
    		}
    	}
    	String[] soffidRoles = result.toArray(new String[result.size()]); 
    	Arrays.sort(soffidRoles);
    	this.roleIds = roleIds;
    	this.soffidRoles = soffidRoles;
	}

	protected void updatePermissions(Account acc) throws InternalErrorException {
		String[] rolesArray;
		if (holderGroup == null)
		{
			rolesArray = authorizationService.getUserAuthorizationsString(acc.getName(),
					new HashMap<String, String>());
		} else {
			rolesArray = authorizationService.getUserGroupAuthorizationString(acc.getName(), holderGroup);
		}
		String[] roles2 = Arrays.copyOf(rolesArray, rolesArray.length + 1);
		if (Arrays.binarySearch(roles, "PASSWORD:EXPIRED") >= 0) {
			roles2[roles2.length-1] = "PASSWORD:EXPIRED";
		} else {
			roles2[roles2.length-1] = "PASSWORD:VALID";
		}
		Arrays.sort(roles2);
		this.permissions = roles2;
	}

	private void updateGroups(Account acc) throws InternalErrorException, UnknownUserException {
    	List<String> result = new LinkedList<String>();
    	if (acc.getType().equals(AccountType.USER))
    	{
    		LinkedList<String> groupNames = new LinkedList<String>();
    		LinkedList<Long> groupIds = new LinkedList<Long>();
    		User u = userService.findUserByUserName( acc.getOwnerUsers().iterator().next() );
    		Collection<Group> groups;
			if (holderGroup == null)
    			groups = userService.getUserGroupsHierarchy(u.getId());
    		else
    			groups = userService.getUserGroupsHierarchy(u.getId(), holderGroup );
			for ( Group g: groups) {
				groupIds.add(g.getId());
				groupNames.add(g.getName());
			}
			final String[] groupsArray = groupNames.toArray(new String[0]);
			Arrays.sort(groupsArray);
			this.groupIds = groupIds;
			this.groups = groupsArray;
    	}
	}

	public String[] getGroups() {
		if (holderGroup != null && holderGroupMap != null)
			return holderGroupMap.get(holderGroup).getGroups();
		else {
			refresh();
			return groups.clone();
		}
	}


	public String[] getSoffidRoles() {
		if (holderGroup != null && holderGroupMap != null)
			return holderGroupMap.get(holderGroup).getSoffidRoles();
		else {
			refresh();
			return soffidRoles.clone();
		}
	}

	public String[] getGroupsAndRoles() {
		if (holderGroup != null && holderGroupMap != null)
			return holderGroupMap.get(holderGroup).getGroupsAndRoles();
		else {
			refresh();
			return groupsAndRoles.clone();
		}
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public Map<String, Map<String, String>> getObligations() {
		Map<String, Map<String, String>> o = new HashMap<String, Map<String,String>>();
		synchronized (obligations) {
			for (Iterator<Obligation> it = obligations.iterator(); it.hasNext();) {
				Obligation obligation = it.next();
				if (obligation.getTimeout() < System.currentTimeMillis())
					it.remove();
				else {
					o.put(obligation.getObligation(), obligation.getAttributes());
				}
			}
		}
		return o ;
	}

	@Override
	public void setObligation(String obligation, Map<String, String> properties, long timeout) {
		Obligation o = new Obligation();
		o.setObligation(obligation);
		o.setAttributes(properties == null ? new HashMap<>(): properties);
		o.setTimeout(timeout);
		
		obligations.add(o);
	}

	@Override
	public Map<String, String> getObligation(String obligationName) {
		Obligation o = null;
		synchronized (obligations) {
			for (Iterator<Obligation> it = obligations.iterator(); it.hasNext();) {
				Obligation obligation = it.next();
				if (obligation.getTimeout() < System.currentTimeMillis())
					it.remove();
				else {
					if (obligationName.equals(obligation.getObligation()))
						o = obligation;
				}
			}
		}
		return o == null ? null : new HashMap<>(o.getAttributes());
	}
	public SoffidPrincipalImpl(SoffidPrincipal p)  {
		this(p.getName(),
				p.getUserName(),
				p.getFullName(),
				p.getHolderGroup(),
				Arrays.asList(p.getRoles()),
				Arrays.asList(p.getGroups()),
				Arrays.asList(p.getSoffidRoles()),
				p.getRoleIds(), 
				p.getAccountIds(),
				p.getGroupIds(),
				p.getUserId());
	}

	public SoffidPrincipalImpl(String name,
			String userName,
			String fullName,
			String holderGroup,
			List<String> permissions,
			List<String> groups,
			List<String> soffidRoles) {
		super(name, "*", permissions);
		init (name, 
				groups == null ? new String[0]: groups.toArray(new String[groups.size()]), 
				soffidRoles == null ? new String[0]: soffidRoles.toArray(new String[soffidRoles.size()]) );
		this.holderGroup = holderGroup;
		this.fullName = fullName;
		this.userName = userName;
		this.holderGroupMap = new HashMap<>();
		this.roleIds = new LinkedList<>();
		this.accountIds = new LinkedList<>();
		this.groupIds = new LinkedList<>();
		this.userId = null;
	}

	@Override
	public List<String> getHolderGroups() {
		if (holderGroupMap == null && holderGroupMap != null)
			return null;
		else
			return new LinkedList<String>( holderGroupMap.keySet() );
	}

	@Override
	public void setHolderGroup(String holderGroup) {
		if (holderGroupMap == null && holderGroupMap != null)
			throw new SecurityException("Not authorized to change holder group");
		else if (holderGroupMap.containsKey(holderGroup))
			this.holderGroup = holderGroup;
		else
			throw new SecurityException("Not authorized to set holder group "+holderGroup);
	}

	@Override
	public String[] getRoles() {
		if (holderGroup != null && holderGroupMap != null)
			return holderGroupMap.get(holderGroup).getRoles();
		else {
			refresh();
			return super.getRoles();
		}
	}

	@Override
	public boolean hasRole(String role) {
		if (holderGroup != null && holderGroupMap != null) 
			return holderGroupMap.get(holderGroup).hasRole(role);
		if ("*".equals(role)) { // Special 2.4 role meaning everyone
			return true;
		}
        if (role == null) {
            return false;
        }
		refresh();
        return Arrays.binarySearch(permissions, role) >= 0;
	}

	@Override
	public List<Long> getRoleIds() {
		if (roleIds == null)
			return Collections.emptyList();
		refresh();
		return Collections.unmodifiableList(roleIds);
	}

	@Override
	public List<Long> getAccountIds() {
		if (accountIds == null)
			return Collections.emptyList();
		refresh();
		return Collections.unmodifiableList(accountIds);
	}
	
	@Override
	public List<Long> getGroupIds() {
		if (groupIds == null)
			return Collections.emptyList();
		refresh();
		return Collections.unmodifiableList(groupIds);
	}

	public Long getUserId() {
		return userId;
	}
	
}
