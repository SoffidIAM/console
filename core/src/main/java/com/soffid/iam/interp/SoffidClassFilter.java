package com.soffid.iam.interp;

import java.security.AccessController;
import java.security.PrivilegedAction;

import org.openjdk.nashorn.api.scripting.ClassFilter;

public class SoffidClassFilter implements ClassFilter {
	static String sc = null;
	static String ss = null;
	static boolean init = false;
	
	public SoffidClassFilter() {
		if (!init) {
			AccessController.doPrivileged( new PrivilegedAction<Object> () {
				@Override
				public Object run() {
					sc = System.getenv("TRUSTED_CLASSES");
					ss = System.getenv("SECURE_SCRIPT");
					init = true;
					return null;
				}
			});
		}
	}
	
	@Override
	public boolean exposeToScripts(String className) {
		if (className.startsWith("es.caib.seycon.ng.comu"))
			return true;
		else if (className.startsWith("com.soffid.iam.api"))
			return true;
		else if (className.startsWith("com.soffid.iam.addon") &&
					(className.contains(".common.") || className.contains(".api.")))
			return true;
		else if ("true".equals(ss)) {
			for (Class c: TRUSTED_CLASSES)
				if (c.getName().equals(className))
					return true;
			if (sc != null) {
				for (String s: sc.split(" +"))
					if (className.equals(s))
						return true;
			}
			return false;
		}
		else if (className.startsWith("java.lang.") && 
				!className.equals("java.lang.System"))
			return true;
		else if (className.startsWith("java.util."))
			return true;
		else if (className.equals("javax.naming.ldap.LdapName"))
			return true;
		else
			return false;
	}
	
	static protected Class[] TRUSTED_CLASSES = {
			java.lang.Byte.class,
			java.lang.Boolean.class,
			java.lang.Character.class,
			java.lang.CharSequence.class,
			java.lang.Float.class,
			java.lang.Double.class,
			java.lang.Exception.class,
			java.lang.Integer.class,
			java.lang.Long.class,
			java.lang.Math.class,
			java.lang.Short.class,
			java.lang.String.class,
			java.lang.StringBuffer.class,
			java.lang.StringBuilder.class,
			java.util.ArrayList.class,
			java.util.Base64.class,
			java.util.Calendar.class,
			java.util.Collection.class,
			java.util.Collections.class,
			java.util.Date.class,
			java.util.HashMap.class,
			java.util.HashSet.class,
			java.util.Hashtable.class,
			java.util.LinkedList.class,
			java.util.List.class,
			java.util.Map.class,
			java.util.Random.class,
			java.util.Set.class,
			java.util.Stack.class,
			java.util.UUID.class,
			java.util.Vector.class
	};

}
