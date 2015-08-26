package com.soffid.iam.script;

import java.io.StringReader;
import java.security.AccessController;
import java.security.PrivilegedAction;

import bsh.BshClassManager;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;
import junit.framework.TestCase;

public class ScriptTest extends  TestCase {
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();

		
		AccessController.doPrivileged(new PrivilegedAction<String>() {
			public String run() {
				System.setSecurityManager(old);
				return null;
			}
		});
	}

	BshClassManager bshcm;
	NameSpace ns;
	Interpreter interpreter ;
	private SecurityManager old;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		bshcm = new BshClassManager();
		ns = new bsh.NameSpace(bshcm, "namespace");
		interpreter = new Interpreter( new StringReader(""), System.out, System.err, false, ns);
		interpreter = new Interpreter();
		old = System.getSecurityManager();
		
		AccessController.doPrivileged(new PrivilegedAction<String>() {

			public String run() {
				System.setSecurityManager(new MySecurityManager());
				return null;
			}
		});
	}


	public ScriptTest() {
	}

	public void test1 () throws EvalError 
	{
		Object r = interpreter.eval("return \"hola\"");
		System.out.println ("Result = "+r);
	}

	public void test2 () throws EvalError 
	{
		try {
			es.caib.seycon.ng.utils.Security.nestedLogin ("hola", new String[] {} );
			Object r = interpreter.eval("es.caib.seycon.ng.utils.Security.nestedLogin (\"hola\", new String[] {} );");
//			assertFalse("Expected exception not thrown", true);
		} catch (EvalError e) {
			System.out.println ("Excpected error");
			e.printStackTrace();
		}
	}

	public void test4 () throws EvalError 
	{
		try {
			Object r = interpreter.eval("c = Class.forName(\"es.caib.seycon.ng.utils.Security\").getMethod(\"nestedLogooff\", new Class[0]).invoke(null, new Object[0]);");
//			assertFalse("Expected exception not thrown", true);
		} catch (EvalError e) {
			System.out.println ("Excpected error");
			e.printStackTrace();
		}
	}

	public void test3 () throws EvalError 
	{
		Object r = interpreter.eval("x = new es.caib.seycon.ng.comu.Usuari()");
		System.out.println ("Result = "+r);
	}

	public void test5 () 
	{
		try {
			Object r = interpreter.eval("cl = es.caib.seycon.ng.comu.Usuari.class; \n"
					+ "ccl = cl.getClassLoader(); \n"
					+ "c=ccl.loadClass (\"es.caib.seycon.ng.utils.Security\");\n"
//					+ "getMethod(\"nestedLogooff\", new Class[0]).\n"
//					+ "invoke(null, new Object[0]);"
					);
			System.out.println ("Result = " +r);
//			assertFalse("Expected exception not thrown", true);
		} catch (EvalError e) {
			System.out.println ("Excpected error");
			e.printStackTrace();
		}
	}

}
