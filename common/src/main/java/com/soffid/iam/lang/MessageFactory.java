package com.soffid.iam.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageFactory
{
	private static Class localesClass = null;
	private static Method getCurrentMethod = null;
	
	private static ThreadLocal<Locale> currentLocale = new ThreadLocal<Locale>();
	
	public static void setThreadLocale(Locale l) {
		currentLocale.set(l);
	}

	public static Locale getThreadLocale() {
		return currentLocale.get();
	}

	public static Locale getLocale () {
		Locale l = currentLocale.get();
	
		if ( l == null)
			return Locale.getDefault();
		else
			return l;
	}
	
	static Map<String, Map<Locale, ResourceBundle>> bundles = new HashMap<String, Map<Locale,ResourceBundle>>();

    public static String getString(String bundleName, String message) {
    	ResourceBundle resources;
    	Locale l = getLocale ();
    	synchronized (bundles) {
    		Map<Locale, ResourceBundle> map = bundles.get(bundleName);
    		if (map == null)
    		{
    			map = new HashMap<Locale, ResourceBundle>();
    			bundles.put(bundleName, map);
    		}
    		resources = map.get(l);
    		if (resources == null) {
    			try {
        			resources = ResourceBundle.getBundle(bundleName, l,
            				ResourceBundle.Control.getNoFallbackControl(
            					ResourceBundle.Control.FORMAT_PROPERTIES));

    			} catch (MissingResourceException e)
    			{
        			resources = ResourceBundle.getBundle(bundleName, l,
        					Thread.currentThread().getContextClassLoader(),
            				ResourceBundle.Control.getNoFallbackControl(
            					ResourceBundle.Control.FORMAT_PROPERTIES));

    			}
    			map.put(l, resources);
    		}
    	}
    	return resources.getString(message);
    }
}
