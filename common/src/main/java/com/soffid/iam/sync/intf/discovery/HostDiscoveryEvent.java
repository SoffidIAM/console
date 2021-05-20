package com.soffid.iam.sync.intf.discovery;

import java.util.List;

import com.soffid.iam.api.HostPort;

public class HostDiscoveryEvent extends DiscoveryEvent {
	String ip;
	String name;
	String os;
	List<HostPort> ports;
	
	public String toString() {
		return "HostDiscoverEvent {ip: "+ip+", name:"+name+"}";
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public List<HostPort> getPorts() {
		return ports;
	}
	public void setPorts(List<HostPort> ports) {
		this.ports = ports;
	}
}
