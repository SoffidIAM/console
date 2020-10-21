package com.soffid.iam.tomcat;

import java.security.Principal;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.catalina.Wrapper;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.RealmBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SoffidRealm extends RealmBase {
	Log log = LogFactory.getLog(getClass());
	
	public SoffidRealm() {
	}

	@Override
	public Principal authenticate(String username, String credentials) {
		LoginService svc;
		try {
			if (credentials == null || credentials.trim().isEmpty())
				return null;
			svc = (LoginService) new InitialContext().lookup("openejb:/local/soffid.ejb.com.soffid.iam.tomcat.service.LoginService");
			return svc.authenticate (username, credentials);
		} catch (NamingException e) {
			log.info("Error looking up LoginService", e);
			return null;
		}
	}

	@Override
	public boolean hasRole(Wrapper wrapper, Principal principal, String role) {
		if (principal != null && principal instanceof GenericPrincipal)
		{
	        if (wrapper != null) {
	            String realRole = wrapper.findSecurityReference(role);
	            if (realRole != null)
	                role = realRole;
	        }

	        return ((GenericPrincipal)principal).hasRole(role);
		}
		else
			return super.hasRole(wrapper, principal, role);
	}

	@Override
	protected String getPassword(String user) {
		return null;
	}

	@Override
	protected Principal getPrincipal(String user) {
		return null;
	}

}
