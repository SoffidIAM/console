package com.soffid.iam.filter;


import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class TenantExtractor {
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
		} else
			tenantHost = Security.getMasterTenantName();
		
		return tenantHost;
	}
}
