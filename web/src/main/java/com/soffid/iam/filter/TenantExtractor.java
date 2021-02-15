package com.soffid.iam.filter;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import com.soffid.iam.api.Tenant;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.exception.InternalErrorException;

public class TenantExtractor {
	static Hashtable<String,Long> cache = new Hashtable<>();
	public String getTenant (HttpServletRequest req) throws InternalErrorException
	{
		String hostName = req.getServerName();
		String baseHost = System.getProperty("hostName");
		if (baseHost == null)
			baseHost = ConfigurationCache.getMasterProperty("hostName");
		if (baseHost == null)
			try {
				baseHost = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String tenantHost;
		
		if (baseHost != null && hostName.toLowerCase().endsWith("."+baseHost.toLowerCase()))
		{
			String[] hostParts = hostName.split("\\.");
			tenantHost = hostParts[0];
			if (tenantHost.isEmpty())
				tenantHost = Security.getMasterTenantName();
			else {
				Long l = cache.get(tenantHost);
				if ( l == null || l.longValue() < System.currentTimeMillis() - 20000) {
		    		Tenant tenant = ServiceLocator.instance().getTenantService().getTenant(tenantHost);
		    		if (tenant == null || !tenant.isEnabled())
		    			tenantHost = Security.getMasterTenantName();
		    		else
		    			cache.put(tenantHost, new Long(System.currentTimeMillis()));
				}
			}
		} else
			tenantHost = Security.getMasterTenantName();
		
		return tenantHost;
	}
}
