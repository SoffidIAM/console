package com.soffid.iam.script;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.ServiceLocator;
import bsh.BshClassManager;
import bsh.UtilEvalError;

public class MyBshClassManager extends BshClassManager {

	public MyBshClassManager() {
	}

	@Override
	public Class classForName(String name) {
		Class clas = null;
		try {
			clas = plainClassForName(name);
		} catch (ClassNotFoundException e) { /* ignore */
		}

		return clas;

	}

	public Class plainClassForName(String name) throws ClassNotFoundException {
		Class c = null;

		System.out.println("Testing class for name "+name);
		if (! isAllowed (name))
			return null;
		try {
			c = Class.forName(name);

			if (ClassLoader.class.isAssignableFrom(c))
				return null;
			
			cacheClassInfo(name, c);

			/*
			 * Original note: Jdk under Win is throwing these to warn about
			 * lower case / upper case possible mismatch. e.g. bsh.console
			 * bsh.Console
			 * 
			 * Update: Prior to 1.3 we were squeltching NoClassDefFoundErrors
			 * which was very annoying. I cannot reproduce the original problem
			 * and this was never a valid solution. If there are legacy VMs that
			 * have problems we can include a more specific test for them here.
			 */
		} catch (NoClassDefFoundError e) {
			throw noClassDefFound(name, e);
		}

		return c;
	}

	private boolean isAllowed(String name) {
		if (name.equals("java.lang.Class"))
			return false;
		
		if (name.startsWith("java.lang.reflect."))
			return false;

		if (name.startsWith("java.")
				|| name.startsWith("javax."))
			return true;
			
		String s = name.replace('.', '/') + ".class";
		URL u = Thread.currentThread().getContextClassLoader().getResource(s);
		
		if (u == null)
			return false;
		
		
		String file = u.getFile();
		int excl = file.lastIndexOf('!');
		if (u.getProtocol().equals("jar") && excl > 0)
		{
			try {
				URL u2;
				u2 = new URL( file.substring(0, excl) );
				file = u2.getFile();
			} catch (MalformedURLException e) {
			}
		}
		
		int slash = file.lastIndexOf(File.separatorChar);
		if (slash > 0)
		file = file.substring(slash + 1);
		if (file.matches("iam-common-.*\\.jar") ||
				file.matches ("plugin.*\\.jar"))
			return true;
		
		else
			throw new SecurityException("Access to class "+name+" on package "+file+" is forbidden");
		
	}

	@Override
	public void setClassLoader(ClassLoader externalCL) {
		throw new IllegalAccessError();
	}

	@Override
	public void addClassPath(URL path) throws IOException {
		throw new IllegalAccessError();
	}

	@Override
	public void setClassPath(URL[] cp) throws UtilEvalError {
		throw new IllegalAccessError();
	}

}
