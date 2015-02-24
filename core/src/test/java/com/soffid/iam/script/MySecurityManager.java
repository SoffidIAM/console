package com.soffid.iam.script;

import java.security.AccessControlException;
import java.security.Permission;

public class MySecurityManager extends SecurityManager {

	public MySecurityManager() {
	}

	public boolean isScript ()
	{
		Class[] stack = getClassContext();
		
		return stack == null || stack[0].getPackage().getName().startsWith("bsh");

	}
	@Override
	public void checkPermission(Permission perm) {
		if (isScript()){
			System.out.println ("Checking "+perm.toString());
			super.checkPermission(perm);
		}
	}

	@Override
	public void checkPermission(Permission perm, Object context) {
		System.out.println ("Checking "+perm.toString()+" ctx="+context);
		super.checkPermission(perm, context);
	}

	@Override
	public void checkPackageAccess(String pkg) {
		System.out.println ("Checking package access "+pkg);
		if (pkg.startsWith("es.caib.seycon"))
		{
			System.out.println ("***");
		}
		if (isScript())
			throw new SecurityException ("Not allowed");
		super.checkPackageAccess(pkg);
	}

	@Override
	public void checkPackageDefinition(String pkg) {
		System.out.println ("Checking package def "+pkg);
		if (isScript())
			throw new SecurityException ("Not allowed");
		super.checkPackageDefinition(pkg);
	}

	@Override
	public void checkMemberAccess(Class<?> clazz, int which) {
		System.out.println ("Checking member access "+clazz.getName()+" "+which);
		super.checkMemberAccess(clazz, which);
	}

	
}
