package com.soffid.iam.spring;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.behavior.ICacheAccess;
import org.hibernate.HibernateException;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CacheProvider;
import org.hibernate.cache.Timestamper;

import com.soffid.iam.config.Config;
import com.soffid.iam.utils.Security;

public class JCSCacheProvider implements CacheProvider 
{	
	public static final String PREFIX = "hibernate";
	
	private static Boolean enabled = null;
	public static boolean isEnabled () {
		if (enabled == null)
		{
			String enableCache = System.getProperty("soffid.cache.enable");
			if ( enableCache != null && ! "false".equals(enableCache))
				enabled = true;
			else
				enabled = false;
		}
		return enabled.booleanValue();
	}
	
	private CacheAccess<Object, Object> cache;

	public JCSCacheProvider() throws FileNotFoundException, IOException {
		init();
		
		cache = JCS.getInstance("default");
	}

	private static boolean initialised = false;
	
	private static void init() throws IOException, FileNotFoundException {
		if (!initialised)
		{
			Properties p = new Properties ();
			try {
				String propTxt = System.getProperty("soffid.cache.config");
				String propTxtFile = System.getProperty("soffid.cache.configFile");
				if (propTxtFile != null)
					p.load( new FileInputStream(propTxtFile));
				else if (propTxt == null || propTxt.isEmpty())
					p.load( JCSCacheProvider.class.getResourceAsStream("jcs.properties"));
				else
					p.load( new StringReader(propTxt));
			} catch (IOException e) {
				throw new HibernateException(e);
			}
			
			String cacheDir = null;
			if ( Security.isSyncServer())
				cacheDir =  Config.getConfig().getHomeDir()+"/tmp/cache";
			else
				cacheDir = System.getProperty("catalina.home")+"/work/cache";
			new File(cacheDir).mkdirs();
			
			p.put("cs.auxiliary.DC.attributes.DiskPath", cacheDir);		
			JCS.setConfigProperties(p);

			initialised = true;
		}
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
	
	public static <K,V> ICacheAccess<K, V> buildCache (String regionName) 
	{
		try {
			init();
		} catch (IOException e) {
			throw new RuntimeException("Error initializing java cache", e);
		}
		if (isEnabled() || ! Security.isSyncServer())
			return JCS.getInstance(regionName);
		else
			return new DummyCache<K, V>();
	}
}
