package com.soffid.iam.spring;

import java.util.Map;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.Timestamper;

public class JCSCache implements Cache {
	// Log log = LogFactory.getLog(getClass());
	
	private CacheAccess<Object, Object> jcs;

	private String regionName;

	public JCSCache(String regionName) {
		this.regionName = regionName;
		jcs = JCS.getInstance(regionName);
	}

	@Override
	public Object read(Object key) throws CacheException {
		return null;
	}

	@Override
	public Object get(Object key) throws CacheException {
		Object o = jcs.get(key.toString());
		return o;
	}

	@Override
	public void put(Object key, Object value) throws CacheException {
		jcs.put(key.toString(), value);
	}

	@Override
	public void update(Object key, Object value) throws CacheException {
		jcs.put(key.toString(), value);
	}

	@Override
	public void remove(Object key) throws CacheException {
		jcs.remove(key.toString());
	}

	@Override
	public void clear() throws CacheException {
		jcs.clear();
	}

	@Override
	public void destroy() throws CacheException {
		jcs.dispose();
	}

	@Override
	public void lock(Object key) throws CacheException {
	}

	@Override
	public void unlock(Object key) throws CacheException {
	}

	@Override
	public long nextTimestamp() {
		return Timestamper.next();
	}

	@Override
	public int getTimeout() {
		return Timestamper.ONE_MS * 60000; //ie. 60 seconds
	}

	@Override
	public String getRegionName() {
		return regionName;
	}

	@Override
	public long getSizeInMemory() {
		return -1;
	}

	@Override
	public long getElementCountInMemory() {
		return jcs.getCacheControl().getSize();
	}

	@Override
	public long getElementCountOnDisk() {
		return -1;
	}

	@Override
	public Map toMap() {
		return jcs.getMatching(".*");
	}

}
