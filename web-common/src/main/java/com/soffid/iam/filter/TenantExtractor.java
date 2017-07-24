package com.soffid.iam.filter;


import javax.servlet.http.HttpServletRequest;

import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class TenantExtractor {
	public String getTenant (HttpServletRequest req) throws InternalErrorException
	{
		String hostName = req.getServerName();
		String baseHost = System.getProperty("hostName");
		String tenantHost;
		
		if (hostName.toLowerCase().endsWith("."+baseHost.toLowerCase()))
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
