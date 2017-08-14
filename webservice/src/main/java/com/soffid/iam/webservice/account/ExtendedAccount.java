package com.soffid.iam.webservice.account;

import java.util.List;

import com.soffid.iam.api.Account;
import com.soffid.iam.webservice.ScimMeta;

public class ExtendedAccount extends Account
{
	private static final long serialVersionUID = 4544784110341469069L;

	public ExtendedAccount() {
		super ();
	}
	public ExtendedAccount(Account u) {
		super (u);
	}
	String password;
	List<RoleDomain> roles;
	ScimMeta meta;
	
	public ScimMeta getMeta() {
		return meta;
	}
	public void setMeta(ScimMeta meta) {
		this.meta = meta;
	}
	public List<RoleDomain> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDomain> roles) {
		this.roles = roles;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}