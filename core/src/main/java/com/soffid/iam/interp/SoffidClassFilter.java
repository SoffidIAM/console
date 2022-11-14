package com.soffid.iam.interp;

import org.openjdk.nashorn.api.scripting.ClassFilter;

public class SoffidClassFilter implements ClassFilter {

	@Override
	public boolean exposeToScripts(String className) {
		if (className.startsWith("es.caib.seycon.ng.comu"))
			return true;
		else if (className.startsWith("com.soffid.iam.api"))
			return true;
		else if (className.startsWith("com.soffid.iam.addon") &&
					(className.contains(".common.") || className.contains(".api.")))
			return true;
		else if (className.equals("javax.naming.ldap.LdapName"))
			return true;
		else
			return false;
	}

}
