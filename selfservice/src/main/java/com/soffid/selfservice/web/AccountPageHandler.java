package com.soffid.selfservice.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.servei.ejb.SelfService;
import es.caib.seycon.ng.servei.ejb.SelfServiceHome;
import es.caib.seycon.ng.exception.InternalErrorException;

public class AccountPageHandler {
	private SelfService ejb;
	
	String getSystem (Account acc) 
	{
		String server = (String) acc.getAttributes().get("SSO:Server");
		if (server == null || server.trim().length() == 0)
			return acc.getDispatcher();
		else
			return server;
	}
	
	public Collection<SystemName> find (String filter, String account, String system) 
			throws CreateException, InternalErrorException, NamingException, UnsupportedEncodingException
	{
		ejb = (SelfService) new InitialContext().lookup(SelfServiceHome.JNDI_NAME);
		
		Map<String,SystemName> systems = new HashMap<String, SystemName>();
		
		List<Account> accounts = ejb.getSharedAccounts(filter);
		if (account != null && system != null)
		{
			for (Account acc:accounts)
			{
				if (acc.getName().equals(account) &&
						acc.getDispatcher().equals(system))
				{
					String s = getSystem (acc);
					SystemName sn = systems.get(s);
					if (sn == null)
					{
						sn = new SystemName();
						sn.setName(s);
						sn.setAccounts(new LinkedList<ExtendedAccount>());
						systems.put(s, sn);
					}
					ExtendedAccount ea = buildExtendedAccount (acc);
					sn.getAccounts().add(ea);
				}
			}
		} else {
			for (Account acc:accounts)
			{
				String s = getSystem (acc);
				SystemName sn = systems.get(s);
				if (sn == null)
				{
					sn = new SystemName();
					sn.setName(s);
					sn.setAccounts(new LinkedList<ExtendedAccount>());
					systems.put(s, sn);
				}
				ExtendedAccount ea = buildExtendedAccount (acc);
				sn.getAccounts().add(ea);
			}
		}
		return systems.values();
	}

	private ExtendedAccount buildExtendedAccount(Account acc) throws UnsupportedEncodingException {
		ExtendedAccount ea = new ExtendedAccount();
		ea.setActualAccount(acc);
		ea.setName(acc.getName());
		ea.setSystem( getSystem (acc));
		
		String sso = ConfigurationCache.getProperty("AutoSSOSystem");
		if (sso != null && sso.equals (acc.getDispatcher()))
		{
			String att = (String) acc.getAttributes().get("SSO:1");
			if (att != null)
			{
				String split[] = att.split("=");
				if (split.length == 2)
				{
					ea.setName( URLDecoder.decode(split[1], "UTF-8"));
				}
			}
		}
		return ea;
	}

}
