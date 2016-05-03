package com.soffid.iam.script;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.AccessControlException;
import java.security.Permission;

public class MySecurityManager extends SecurityManager {

	private SecurityManager chain;

	public MySecurityManager(SecurityManager previousSecurityManager) {
		this.chain = previousSecurityManager;
	}

	public boolean isScript ()
	{
		Class[] stack = getClassContext();
		System.out.println("Checking is script: "+
				(stack == null ? "NULL STACK":
					stack[1].getPackage().getName()));
		for (int i = 0; i < 3; i++)
		{
			System.out.println (stack[i].getName());
		}
		return stack == null || stack[1].getPackage().getName().startsWith("bsh");

	}
	@Override
	public void checkPermission(Permission perm) {
		System.out.println ("Checking "+perm.toString());
		try {
			if (chain != null)
				chain.checkPermission(perm);
		} catch (RuntimeException e) {
			System.out.println ("************** DENIED "+e.toString());
			throw e;
		}
	}

	@Override
	public void checkPermission(Permission perm, Object context) {
		System.out.println ("Checking "+perm.toString()+" ctx="+context);
		try {
			if (chain != null)
				chain.checkPermission(perm, context);
		} catch (RuntimeException e) {
			System.out.println ("************** DENIED "+e.toString());
			throw e;
		}
	}

	@Override
	public void checkPackageAccess(String pkg) {
		System.out.println ("Checking package access "+pkg);
		if (isScript())
		{
			System.out.println ("*************** DENIED");
			throw new SecurityException ("Not allowed");
		}
		if (chain != null)
			chain.checkPackageAccess(pkg);
	}

	@Override
	public void checkPackageDefinition(String pkg) {
		if (isScript())
		{
			System.out.println ("*************** DENIED");
			throw new SecurityException ("Not allowed");
		}
		if (chain != null)
			chain.checkPackageDefinition(pkg);
	}

}
