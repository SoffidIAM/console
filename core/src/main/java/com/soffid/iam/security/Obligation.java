package com.soffid.iam.security;

import java.util.Map;

public class Obligation {
	String  obligation;
	Map<String,String> attributes;
	long timeout;
	
	public String getObligation() {
		return obligation;
	}
	public void setObligation(String obligation) {
		this.obligation = obligation;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
}