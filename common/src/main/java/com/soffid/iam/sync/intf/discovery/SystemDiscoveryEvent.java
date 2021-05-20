package com.soffid.iam.sync.intf.discovery;

import com.soffid.iam.api.Account;

public class SystemDiscoveryEvent extends DiscoveryEvent {
	String hostName;
	String ip;
	String systemClass;
	Account account;
	
	public String toString() {
		return "SystemDiscoverEvent {ip: "+ip+", hostName:"+hostName+", systemClass: "+systemClass+"}";
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSystemClass() {
		return systemClass;
	}
	public void setSystemClass(String systemClass) {
		this.systemClass = systemClass;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
