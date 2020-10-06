package com.soffid.iam.init.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

public class Messages {
	private static final String BUNDLE_NAME = "es.caib.seycon.ng.weblogout.messages"; //$NON-NLS-1$

	static Map<Locale, ResourceBundle> map =  new HashMap<Locale, ResourceBundle>();

	private Messages() {
	}


	static Map<String, Map<Locale, ResourceBundle>> bundles = new HashMap<String, Map<Locale,ResourceBundle>>();

    public static String getString(Locale l, String bundleName, String message) {
    	ResourceBundle resources;
    	synchronized (bundles) {
    		resources = map.get(l);
    		if (resources == null) {
    			try {
        			resources = ResourceBundle.getBundle(BUNDLE_NAME, l,
            				ResourceBundle.Control.getNoFallbackControl(
            					ResourceBundle.Control.FORMAT_PROPERTIES));

    			} catch (MissingResourceException e)
    			{
        			resources = ResourceBundle.getBundle(BUNDLE_NAME, l,
        					Thread.currentThread().getContextClassLoader(),
            				ResourceBundle.Control.getNoFallbackControl(
            					ResourceBundle.Control.FORMAT_PROPERTIES));

    			}
    			map.put(l, resources);
    		}
    	}
    	return resources.getString(message);
    }

    public static String getString(HttpServletRequest request, String key) {
		try {
			String lang = request.getHeader("Accept-Language");
			if (lang == null)
				return getString(Locale.getDefault(), BUNDLE_NAME, key);
			else
			{
				lang = lang.trim().split("[,_-]+")[0];
				return getString(new Locale(lang), BUNDLE_NAME, key);
			}
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
