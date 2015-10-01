package com.soffid.selfservice.web;

import es.caib.seycon.ng.comu.Account;

public class ExtendedAccount {
	String name;
	String system;
	Account actualAccount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public Account getActualAccount() {
		return actualAccount;
	}
	public void setActualAccount(Account acc) {
		this.actualAccount = acc;
	}

}
