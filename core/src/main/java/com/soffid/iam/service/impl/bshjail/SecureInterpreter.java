package com.soffid.iam.service.impl.bshjail;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bsh.BshClassManager;
import bsh.ConsoleInterface;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;

public class SecureInterpreter {
	Log log = LogFactory.getLog(getClass());
	private Interpreter interp;

	
	public void setConsole(ConsoleInterface console) {
		interp.setConsole(console);
	}


	public String toString() {
		return interp.toString();
	}


	public NameSpace getNameSpace() {
		return interp.getNameSpace();
	}


	public Reader getIn() {
		return interp.getIn();
	}


	public PrintStream getOut() {
		return interp.getOut();
	}


	public PrintStream getErr() {
		return interp.getErr();
	}


	public Object get(String name) throws EvalError {
		return interp.get(name);
	}


	public void set(String name, Object value) throws EvalError {
		interp.set(name, value);
	}


	public void set(String name, long value) throws EvalError {
		interp.set(name, value);
	}


	public void set(String name, int value) throws EvalError {
		interp.set(name, value);
	}


	public void set(String name, double value) throws EvalError {
		interp.set(name, value);
	}


	public void set(String name, float value) throws EvalError {
		interp.set(name, value);
	}


	public void set(String name, boolean value) throws EvalError {
		interp.set(name, value);
	}


	public void unset(String name) throws EvalError {
		interp.unset(name);
	}


	public Object getInterface(Class interf) throws EvalError {
		return interp.getInterface(interf);
	}


	public File pathToFile(String fileName) throws IOException {
		return interp.pathToFile(fileName);
	}


	public BshClassManager getClassManager() {
		return interp.getClassManager();
	}


	public void setStrictJava(boolean b) {
		interp.setStrictJava(b);
	}


	public boolean getStrictJava() {
		return interp.getStrictJava();
	}


	public String getSourceFileInfo() {
		return interp.getSourceFileInfo();
	}


	public Interpreter getParent() {
		return interp.getParent();
	}


	public boolean getShowResults() {
		return interp.getShowResults();
	}


	public void setOut(PrintStream out) {
		interp.setOut(out);
	}


	public void setErr(PrintStream err) {
		interp.setErr(err);
	}


	public void setExitOnEOF(boolean value) {
		interp.setExitOnEOF(value);
	}


	public void setShowResults(boolean arg0) {
		interp.setShowResults(arg0);
	}


	public SecureInterpreter() {
		BshJainClassManager bshcm = new BshJainClassManager();
		BshJailNamespace bshnm = new BshJailNamespace(null, bshcm, "Secure Namespace");
		interp  = new Interpreter(new StringReader(""), 
				System.out, System.err, false, bshnm);
	}


	public Object eval (final String expr) throws MalformedURLException, EvalError
	{
		return eval (expr, getNameSpace());
	}

	public Object eval (final String expr, final NameSpace ns) throws MalformedURLException, EvalError
	{
		Object [] result  = AccessController.doPrivileged(
				new PrivilegedAction<Object[]>() {
					public Object[] run() {
						Policy p = Policy.getPolicy();
						CodeSource codesource;
						try {
							codesource = new CodeSource(new URL("http://scripts.customers.soffid.com/"), new Certificate[0]);
						} catch (MalformedURLException e1) {
							return new Object[] {null, e1};
						}
						PermissionCollection permissions = p.getPermissions(codesource);

//						log.info("Running shell with permissions: "+permissions.toString());
						
						ProtectionDomain pd = new ProtectionDomain(codesource, permissions);
						
						
						AccessControlContext context = new AccessControlContext(new ProtectionDomain[]{pd});
						
						
						return AccessController.doPrivileged(new PrivilegedAction<Object[]>() {
							public Object[] run() {
								try {
									return new Object[] { interp.eval(expr, ns) };
								} catch (EvalError e) {
									return new Object[] { null, e};
								} catch (RuntimeException e) {
									return new Object[] { null, e};
								}
							}
						}, context);
					}
				});
		if (result.length == 0)
			return null;
		else if (result.length == 1)
			return result[0];
		else
		{
			if (result[1] instanceof EvalError)
				throw (EvalError) result[1];
			else 
				throw (RuntimeException) result[1];
		}
	}
}
