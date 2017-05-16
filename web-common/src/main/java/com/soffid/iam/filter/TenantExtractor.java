package com.soffid.iam.filter;


import javax.servlet.http.HttpServletRequest;

public class TenantExtractor {
	public String getTenant (HttpServletRequest req)
	{
		String hostName = req.getServerName();
		String baseHost = System.getProperty("hostName");
		String tenantHost;
		
		if (hostName.toLowerCase().endsWith("."+baseHost.toLowerCase()))
		{
			String[] hostParts = hostName.split("\\.");
			tenantHost = hostParts[0];
			if (tenantHost.isEmpty())
				tenantHost = "master";
		} else
			tenantHost = "master";
		
		return tenantHost;
	}
}
