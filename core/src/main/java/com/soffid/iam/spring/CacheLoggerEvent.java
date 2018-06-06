package com.soffid.iam.spring;

import org.apache.commons.jcs.engine.logging.behavior.ICacheEvent;

public class CacheLoggerEvent<T> implements ICacheEvent<T> {

	private String source;
	private String region;
	private String eventName;
	private String optionalDetails;
	T key;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getOptionalDetails() {
		return optionalDetails;
	}
	public void setOptionalDetails(String optionalDetails) {
		this.optionalDetails = optionalDetails;
	}
	public T getKey() {
		return key;
	}
	public void setKey(T key) {
		this.key = key;
	}


	public CacheLoggerEvent(String source, String region, String eventName, String optionalDetails, T key) {
		super();
		this.source = source;
		this.region = region;
		this.eventName = eventName;
		this.optionalDetails = optionalDetails;
		this.key = key;
	}
	
	public String toString ()
	{
		return "Source: "+source+" / Region: "+region+" / Event: "+eventName+" / Details: " + optionalDetails + " / Key: "+key;
	}
}
