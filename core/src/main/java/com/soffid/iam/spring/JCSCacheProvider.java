package com.soffid.iam.spring;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.hibernate.HibernateException;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CacheProvider;
import org.hibernate.cache.Timestamper;

public class JCSCacheProvider implements CacheProvider 
{	
	private CacheAccess<Object, Object> cache;

	public JCSCacheProvider() {
		Properties p = new Properties ();
		try {
			p.load( getClass().getResourceAsStream("jcs.properties"));
		} catch (IOException e) {
			throw new HibernateException(e);
		}
		JCS.setConfigProperties(p);
		
		cache = JCS.getInstance("default");
	}

	@Override
	public Cache buildCache(String regionName, Properties properties) throws CacheException {
		return new JCSCache ( regionName );
	}

	@Override
	public long nextTimestamp() {
		return Timestamper.next();
	}

	@Override
	public void start(Properties properties) throws CacheException {
	}

	@Override
	public void stop() {
	}

	@Override
	public boolean isMinimalPutsEnabledByDefault() {
		return true;
	}

}
