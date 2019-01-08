package com.soffid.iam.common.security;

import java.util.LinkedList;
import java.util.List;

import org.apache.catalina.realm.GenericPrincipal;

public class SoffidPrincipal extends GenericPrincipal {
	String fullName;
	String tenant;
	long accountId;
	String holderGroup;
	private String[] groups;
	private String[] soffidRoles;
	private String[] groupsAndRoles;
	
	public SoffidPrincipal(String name, 
			List<String> permissions,
			List<String> groups,
			List<String> soffidRoles) {
		super(name, "*", permissions);
		init (name, 
				groups == null ? new String[0]: groups.toArray(new String[groups.size()]), 
				soffidRoles == null ? new String[0]: soffidRoles.toArray(new String[soffidRoles.size()]) );
	}

	public SoffidPrincipal(String name,  
			List<String> permissions,
			SoffidPrincipal parent) {
		super(name, "*", permissions);
		if (parent == null)
			init (name, new String[0], new String[0]);
		else
		{
			init(name, parent.groups, parent.soffidRoles);
			fullName = parent.fullName;
			holderGroup = parent.holderGroup;
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
		
	}


	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTenant() {
		return tenant;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
	}


	public void setTenant(String tenant) {
	}


	public String getHolderGroup() {
		return holderGroup;
	}


	public String[] getGroups() {
		return groups;
	}


	public String[] getSoffidRoles() {
		return soffidRoles;
	}

	public void setHolderGroup(String holderGroup) {
		this.holderGroup = holderGroup;
	}

	public String[] getGroupsAndRoles() {
		return groupsAndRoles;
	}
}
