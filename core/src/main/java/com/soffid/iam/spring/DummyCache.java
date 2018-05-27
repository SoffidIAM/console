package com.soffid.iam.spring;

import java.util.Map;
import java.util.Set;

import org.apache.commons.jcs.access.behavior.ICacheAccess;
import org.apache.commons.jcs.access.exception.CacheException;
import org.apache.commons.jcs.engine.behavior.ICacheElement;
import org.apache.commons.jcs.engine.behavior.ICompositeCacheAttributes;
import org.apache.commons.jcs.engine.behavior.IElementAttributes;
import org.apache.commons.jcs.engine.stats.behavior.ICacheStats;

public class DummyCache<K,V> implements ICacheAccess<K, V> {

	@Override
	public void dispose() {
	}

	@Override
	public void clear() throws CacheException {
	}

	@Override
	public IElementAttributes getDefaultElementAttributes() throws CacheException {
		return null;
	}

	@Override
	public void setDefaultElementAttributes(IElementAttributes attr) throws CacheException {
	}

	@Override
	public ICompositeCacheAttributes getCacheAttributes() {
		return null;
	}

	@Override
	public void setCacheAttributes(ICompositeCacheAttributes cattr) {
	}

	@Override
	public int freeMemoryElements(int numberToFree) throws CacheException {
		return 0;
	}

	@Override
	public ICacheStats getStatistics() {
		return null;
	}

	@Override
	public String getStats() {
		return null;
	}

	@Override
	public V get(Object name) {
		return null;
	}

	@Override
	public Map<K, V> getMatching(String pattern) {
		return null;
	}

	@Override
	public void putSafe(Object name, Object obj) throws CacheException {
	}

	@Override
	public void put(Object name, Object obj) throws CacheException {
	}

	@Override
	public void put(Object name, Object obj, IElementAttributes attr) throws CacheException {
	}

	@Override
	public ICacheElement<K, V> getCacheElement(Object name) {
		return null;
	}

	@Override
	public Map<K, ICacheElement<K, V>> getCacheElements(Set<K> names) {
		return null;
	}

	@Override
	public Map<K, ICacheElement<K, V>> getMatchingCacheElements(String pattern) {
		return null;
	}

	@Override
	public void remove(Object name) throws CacheException {
	}

	@Override
	public void resetElementAttributes(Object name, IElementAttributes attributes) throws CacheException {
	}

	@Override
	public IElementAttributes getElementAttributes(Object name) throws CacheException {
		return null;
	}

}
