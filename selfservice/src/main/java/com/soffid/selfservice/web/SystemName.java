package com.soffid.selfservice.web;

import java.util.List;

public class SystemName {
	String name;
	
	List<ExtendedAccount> accounts;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExtendedAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<ExtendedAccount> accounts) {
		this.accounts = accounts;
	}
}
