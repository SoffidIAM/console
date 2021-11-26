package com.soffid.iam.common.security;

import java.io.Serializable;
import java.util.Map;

public class Obligation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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