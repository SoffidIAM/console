package com.soffid.iam.lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
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
    	return getString(bundleName, message, l);
    }

	public static String getString(String bundleName, String message, Locale locale) {
		ResourceBundle resources;
		synchronized (bundles) {
    		Map<Locale, ResourceBundle> map = bundles.get(bundleName);
    		if (map == null)
    		{
    			map = new HashMap<Locale, ResourceBundle>();
    			bundles.put(bundleName, map);
    		}
    		resources = map.get(locale);
    		if (resources == null) {
    			try {
        			resources = ResourceBundle.getBundle(bundleName, locale,
            				ResourceBundle.Control.getNoFallbackControl(
            					ResourceBundle.Control.FORMAT_PROPERTIES));

    			} catch (MissingResourceException e)
    			{
        			resources = ResourceBundle.getBundle(bundleName, locale,
        					Thread.currentThread().getContextClassLoader(),
            				ResourceBundle.Control.getNoFallbackControl(
            					ResourceBundle.Control.FORMAT_PROPERTIES));

    			}
    			map.put(locale, resources);
    		}
    	}
    	return resources.getString(message);
	}

	static Map<String,Properties> labels = new HashMap<>();
	static Properties defaultLabels = null;
	public static String getLabel(String message, Locale locale) {
		Properties map;
		try {
			synchronized (labels) {
	    		map = labels.get(locale.getLanguage());
	    		if (map == null)
	    		{
	    			map = new Properties();
	    			map.putAll(readLabels("com/soffid/iam/web/iam-label_"+locale.getLanguage()+".properties"));
	    			map.putAll(readLabels("/iam-label_"+locale.getLanguage()+".properties"));
	    			labels.put(locale.getLanguage(), map);
	    		}
	    	}
			String s = map.getProperty(message);
			if (s == null)
				s = (String) getDefaultLabels().get(message);
	    	return s;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static Properties getDefaultLabels() throws IOException {
		if (defaultLabels == null) {
			Properties m = new Properties();
			m.putAll(readLabels("com/soffid/iam/web/iam-label.properties"));
			m.putAll(readLabels("/iam-label.properties"));
			defaultLabels = m;;
		}
		return defaultLabels;
	}

	private static Map<String, String> readLabels(String path) throws IOException {
		Map<String, String> m = new HashMap<>();
		for (Iterator<URL> it = MessageFactory.class.getClassLoader().getResources(path).asIterator();
				it.hasNext();) {
			m.putAll(readLabels(it.next().openStream()));
		}
		return m;
	}

	private static Map<? extends String, ? extends String> readLabels(InputStream in) throws IOException {
		Map<String, String> m = new HashMap<>();
		BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
		String line;
		while ((line = r.readLine()) != null) {
			int i = line.indexOf("=");
			if (i >= 0) m.put(line.substring(0,i), line.substring(i+1));
		}
		in.close();
		return m;
	}
}
