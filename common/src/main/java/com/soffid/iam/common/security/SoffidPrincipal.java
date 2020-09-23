package com.soffid.iam.common.security;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

public interface SoffidPrincipal extends Principal {
	public String getFullName();

	public String getTenant();

	public long getAccountId();

	public String getHolderGroup();

	public String[] getRoles() ;

	public String[] getGroups() ;

	public String[] getSoffidRoles() ;

	public String[] getGroupsAndRoles() ;

	public boolean hasRole(String role);

	public String getUserName();
	
	public List<String> getHolderGroups();
	
	public void setHolderGroup(String holderGroup) ;
}
