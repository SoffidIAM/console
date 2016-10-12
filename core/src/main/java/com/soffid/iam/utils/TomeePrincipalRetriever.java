package com.soffid.iam.utils;

import java.security.Principal;

import org.apache.catalina.realm.GenericPrincipal;
import org.apache.openejb.loader.SystemInstance;
import org.apache.openejb.spi.Assembler;
import org.apache.openejb.spi.SecurityService;
import org.apache.tomee.catalina.TomcatSecurityService.TomcatUser;

public class TomeePrincipalRetriever {

	public static GenericPrincipal getPrincipal() {
    	SystemInstance si = SystemInstance.get();
    	if (si == null)
    		return null;
    	Assembler a = si.getComponent(Assembler.class);
    	if (a == null)
    		return null;
        final SecurityService<?> ss = a.getSecurityService();
        if (ss == null)
        	return null;
        Principal p = ss.getCallerPrincipal();
        while (p != null)
        {
            if (p instanceof TomcatUser)
            	p = ((TomcatUser)p).getTomcatPrincipal();
            else if (p instanceof GenericPrincipal)
            	return (GenericPrincipal) p;
            else
            	return null;
        }
        return null;
	}

}
