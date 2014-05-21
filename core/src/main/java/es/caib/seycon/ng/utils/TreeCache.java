package es.caib.seycon.ng.utils;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import org.jboss.cache.TreeCacheMBean;
import org.jboss.cache.CacheException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import org.jboss.mx.util.MBeanServerLocator;
import org.jboss.mx.util.MBeanProxyExt;

import es.caib.seycon.ng.exception.SeyconException;

public class TreeCache {

	private static TreeCacheMBean cache;

	static {
		try {
			MBeanServer server = MBeanServerLocator.locate();
			cache = (TreeCacheMBean) MBeanProxyExt.create(TreeCacheMBean.class,
					"jboss.cache:service=MyCache", server); //$NON-NLS-1$
		} catch (MalformedObjectNameException e) {
			throw new SeyconException(Messages.getString("TreeCache.TreeCacheLoadError")); //$NON-NLS-1$
		}
	}

	public static Object get(String fullyQualifiedName, String key) {
		Object object = null;
		try{
			object = cache.get(fullyQualifiedName, key);
		}catch(Exception e){
			throw new SeyconException(String.format(Messages.getString("TreeCache.GetInCacheError"), //$NON-NLS-1$
					fullyQualifiedName, key));
		}
		return object;
	}

	public static void put(String fullyQualifiedName, String key, Object value) {		
		Object object = null;
		try{
			cache.put(fullyQualifiedName, key, value);
		}catch(Exception e){
			throw new SeyconException(String.format(Messages.getString("TreeCache.PutInCacheError"), //$NON-NLS-1$
					fullyQualifiedName, key, value) + value == null ? "NULL" : value.getClass().getName()); //$NON-NLS-1$
		}
	}
	
	private TreeCache() {
		// es un sigleton
	}
}
