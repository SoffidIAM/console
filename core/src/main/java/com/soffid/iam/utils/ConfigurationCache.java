package com.soffid.iam.utils;

import java.util.HashMap;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.Tenant;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class ConfigurationCache {
	static HashMap<String, HashMap<String, TimedValue<String>>> cache = new HashMap<String,HashMap<String,TimedValue<String>>>();
	static String masterTenantName =  null;
	
	private static String getMasterTenantName () throws InternalErrorException
	{
		if (masterTenantName == null)
			masterTenantName = Security.getMasterTenantName();
		return masterTenantName;
	}
	
	public static String getProperty (String property) 
	{
		String tenant;
		try {
			tenant = Security.getCurrentTenantName();
			return getProperty (tenant, property);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getTenantProperty (String tenant, String property) 
	{
		String t;
		try {
			t = Security.getCurrentTenantName();
			if (t.equals(tenant) || Security.isMasterTenant())
				return getProperty (tenant, property);
			else
				return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	static private Boolean ejb;
	private static String getProperty (String tenant, String property) throws InternalErrorException, NamingException, CreateException 	
	{
		
			if (tenant == null)
				return null;
			HashMap<String, TimedValue<String>> map = cache.get(tenant);
			if (map == null)
			{
				map = new HashMap<String, TimedValue<String>>();
				cache.put(tenant, map);
			}
			String value;
			if (!map.containsKey(property) || map.get(property).isExpired())
			{
				if (System.getProperty("java.naming.factory.initial") == null) {
					value = ServiceLocator.instance().getConfigurationService().findTenantParameter(tenant, property);
					map.put(property, new TimedValue<String>(value, -1)); // Forever
				}
				else {
					value = EJBLocator.getConfigurationService().findTenantParameter(tenant, property);
					map.put(property, new TimedValue<String>(value, System.currentTimeMillis() + 60_000L)); // 1 minute
				}
			}
			else
				value = map.get(property).getValue();

			if (value == null && !tenant.equals(getMasterTenantName()))
				return getProperty(getMasterTenantName(), property);
			else
				return value;
	}
	
	public static void setProperty (String property, String value)
	{
		try {
			String tenant = Security.getCurrentTenantName();
			if (tenant == null)
				return;
			HashMap<String, TimedValue<String>> map = cache.get(tenant);
			if (map == null)
			{
				map = new HashMap<String, TimedValue<String>>();
				cache.put(tenant, map);
			}
			map.put(property, new TimedValue<String>(value, Security.isSyncServer() ? 0:  System.currentTimeMillis() + 60_000L));
		} catch (InternalErrorException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getMasterProperty(String property) {
		try {
			return getProperty ("master", property);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void remove(String property) {
		try {
			String tenant = Security.getCurrentTenantName();
			if (tenant == null)
				return;
			HashMap<String, TimedValue<String>> map = cache.get(tenant);
			if (map == null)
			{
				map = new HashMap<String, TimedValue<String>>();
				cache.put(tenant, map);
			}
			map.remove(property);
		} catch (InternalErrorException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean isHistoryEnabled() {
		return "true".equals( ConfigurationCache.getProperty("soffid.history.enabled") );
	}
}
