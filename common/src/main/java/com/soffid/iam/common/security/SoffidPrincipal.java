package com.soffid.iam.common.security;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	public Map<String,Map<String,String>> getObligations();
	
	public void setObligation(String obligation, Map<String,String> properties, long timeout);

	public Map<String,String> getObligation(String obligation);
	
	public List<String> getHolderGroups();
	
	public void setHolderGroup(String holderGroup) ;

	List<Long> getAccountIds();

	List<Long> getRoleIds();

	Long getUserId();

	List<Long> getGroupIds();
}
