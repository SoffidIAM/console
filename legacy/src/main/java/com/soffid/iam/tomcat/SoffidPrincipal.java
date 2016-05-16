package com.soffid.iam.tomcat;

import java.security.Principal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.catalina.realm.GenericPrincipal;

public class SoffidPrincipal extends GenericPrincipal {
	String fullName;
	HashSet<String> roles = new HashSet<String>();
	String tenant;
	long accountId;
	
	public SoffidPrincipal(String name, String password, List<String> roles) {
		super(name, password, roles);
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

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public void setName(String name) {
		this.name = name;
	}

}
