package com.soffid.iam.service.impl.events;

import org.apache.commons.beanutils.BeanUtils;

import com.soffid.iam.api.Issue;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.IssueHostEntity;
import com.soffid.iam.model.IssueUserEntity;
import com.soffid.iam.utils.ConfigurationCache;

public class IssueTextFormatter {
	public String format(String msg, Issue event, IssueEntity entity) {
		StringBuffer result = new StringBuffer();
		int processed = 0;
		do
		{
			int i = msg.indexOf("${", processed); //$NON-NLS-1$

			if ( i < 0) 
				break;
			int j = msg.indexOf("}", i); //$NON-NLS-1$
			if ( j < 0)
				break;
			String variable = msg.substring(i+2, j);
			result.append(msg.substring(processed, i));
			processed = j + 1;
			try {
				if (variable.equals("url")) {
					String externalURL = ConfigurationCache.getProperty("soffid.externalURL");
					if (externalURL == null)
						externalURL = ConfigurationCache.getProperty("AutoSSOURL");
					if (externalURL == null)
						externalURL = "${soffid.externalURL}";
					if (!externalURL.endsWith("/"))
						externalURL = externalURL + "/soffid/issues/issues.zul?filter=id eq "+event.getId();
				}
				else if (variable.equals("user")) {
					boolean first = true;
					for (IssueUserEntity u: entity.getUsers())
					{
						if (first) first = false;
						else result.append(" ");
						if (u.getUser() != null)
							result.append(u.getUser().getUserName());
						else
							result.append(u.getUserName());
					}
				}
				else if (variable.equals("host")) {
					boolean first = true;
					for (IssueHostEntity u: entity.getHosts())
					{
						if (first) first = false;
						else result.append(" ");
						result.append(u.getHostName());
					}
				}
				else if (variable.equals("account")) {
					if (event.getAccount() != null)
						result.append(event.getAccount().getName())
							.append("@")
							.append(event.getAccount().getSystem());
				}
				else if (variable.equals("rule")) {
					if (event.getRule() != null)
						result.append(event.getRule().getName());
				}
				else if (variable.equals("grantedRole")) {
					if (event.getRoleAccount() != null)
						result.append(event.getRoleAccount().getRoleName())
							.append("@")
							.append(event.getRoleAccount().getRoleName());
				}
				else {
					Object property = BeanUtils.getProperty(event, variable);
					if (property != null)
						result.append(property.toString());
					
				}
			} catch (Exception e) { 
				result.append ("${").append (variable).append("}"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		} while (true);
		result.append(msg.substring(processed));
		return result.toString();
	}

}
