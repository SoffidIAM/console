package com.soffid.iam.spring;

import org.apache.commons.jcs.engine.logging.behavior.ICacheEvent;
import org.apache.commons.jcs.engine.logging.behavior.ICacheEventLogger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CacheLogger implements ICacheEventLogger {
	Log log = LogFactory.getLog(getClass());
	
	@Override
	public <T> ICacheEvent<T> createICacheEvent(String source, String region, String eventName, String optionalDetails,
			T key) {
		return new CacheLoggerEvent<T> (source, region, eventName, optionalDetails, key);
	}

	@Override
	public <T> void logICacheEvent(ICacheEvent<T> event) {
		log.info("CACHE EVENT: " +event.toString());

	}

	@Override
	public void logApplicationEvent(String source, String eventName, String optionalDetails) {
		log.info("APP EVENT: Source: "+source+" / Event: "+eventName+" / Details: " + optionalDetails);

	}

	@Override
	public void logError(String source, String eventName, String errorMessage) {
		log.info("ERROR: Source: "+source+" / Event: "+eventName+" / Details: " + errorMessage);

	}

}
