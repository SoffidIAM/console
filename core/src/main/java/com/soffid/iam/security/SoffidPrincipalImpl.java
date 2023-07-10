package com.soffid.iam.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.catalina.realm.GenericPrincipal;

import com.soffid.iam.common.security.Obligation;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.utils.Security;

public class SoffidPrincipalImpl extends GenericPrincipal implements SoffidPrincipal {
	String fullName;
	String tenant;
	long accountId;
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

	public long getAccountId() {
		return accountId;
	}

	public String getHolderGroup() {
		return holderGroup;
	}


	public String[] getGroups() {
		if (holderGroup != null && holderGroupMap != null)
			return holderGroupMap.get(holderGroup).getGroups();
		else
			return groups.clone();
	}


	public String[] getSoffidRoles() {
		if (holderGroup != null && holderGroupMap != null)
			return holderGroupMap.get(holderGroup).getSoffidRoles();
		else
			return soffidRoles.clone();
	}

	public String[] getGroupsAndRoles() {
		if (holderGroup != null && holderGroupMap != null)
			return holderGroupMap.get(holderGroup).getGroupsAndRoles();
		else
			return groupsAndRoles.clone();
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
		else
			return super.getRoles();
	}

	@Override
	public boolean hasRole(String role) {
		if (holderGroup != null && holderGroupMap != null) 
			return holderGroupMap.get(holderGroup).hasRole(role);
		else
			return super.hasRole(role);
	}

	@Override
	public List<Long> getRoleIds() {
		if (roleIds == null)
			return Collections.emptyList();
		return Collections.unmodifiableList(roleIds);
	}

	@Override
	public List<Long> getAccountIds() {
		if (accountIds == null)
			return Collections.emptyList();
		return Collections.unmodifiableList(accountIds);
	}
	
	@Override
	public List<Long> getGroupIds() {
		if (groupIds == null)
			return Collections.emptyList();
		return Collections.unmodifiableList(groupIds);
	}

	public Long getUserId() {
		return userId;
	}
	

}
