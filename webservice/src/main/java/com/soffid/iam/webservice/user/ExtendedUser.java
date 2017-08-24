package com.soffid.iam.webservice.user;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.johnzon.mapper.JohnzonIgnore;

import com.soffid.iam.api.User;
import com.soffid.iam.webservice.ScimMeta;
import com.soffid.iam.webservice.group.JsonSecundaryGroup;

public class ExtendedUser extends User
{
	private static final long serialVersionUID = 1L;
	ScimMeta meta = new ScimMeta();
	
	public ScimMeta getMeta() {
		return meta;
	}
	
	public void setMeta(ScimMeta meta) {
		this.meta = meta;
	}
	public ExtendedUser() {
	}
	public ExtendedUser(User u) {
		super (u);
	}
	Map<String, Object> attributes = new HashMap<String, Object>();
	String password;
	List<JsonAccount> accounts = new LinkedList<JsonAccount>();
	
	public List<JsonAccount> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<JsonAccount> accounts) {
		this.accounts = accounts;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	List<JsonSecundaryGroup> secundaryGroups = new LinkedList<JsonSecundaryGroup>();

	public List<JsonSecundaryGroup> getSecundaryGroups() {
		return secundaryGroups;
	}

	public void setSecundaryGroups(List<JsonSecundaryGroup> secundaryGroups) {
		this.secundaryGroups = secundaryGroups;
	}

	/**
	 * This field is deprecated and it doesn't be managed in the SCIM REST request/responses
	 */
	@JohnzonIgnore
	private Long passwordMaxAge;
}