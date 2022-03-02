package com.soffid.iam.script;

import java.io.StringReader;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.HashMap;

import javax.security.auth.Subject;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.interp.JavascriptEvaluator;
import com.soffid.iam.utils.RunAsPrincipal;

import bsh.BshClassManager;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;
import junit.framework.TestCase;

public class ScriptTest extends  TestCase {
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	BshClassManager bshcm;
	NameSpace ns;
	Interpreter interpreter ;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		bshcm = new BshClassManager();
		ns = new bsh.NameSpace(bshcm, "namespace");
		interpreter = new Interpreter( new StringReader(""), System.out, System.err, false, ns);
		interpreter = new Interpreter();
		
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
			com.soffid.iam.ServiceLocator.instance().init("testBeanRefFactory.xml", "beanRefFactory");
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
			Object r = interpreter.eval("c = Class.forName(\"es.caib.seycon.ng.utils.Security\").getMethod(\"nestedLogoff\", new Class[0]).invoke(null, new Object[0]);");
//			assertFalse("Expected exception not thrown", true);
		} catch (Exception e) {
			System.out.println ("Excpected error");
			e.printStackTrace();
		}
	}

	public void test3 () throws EvalError 
	{
		Object r = interpreter.eval("x = new es.caib.seycon.ng.comu.Usuari()");
		System.out.println ("Result = "+r);
		Object r2 = interpreter.eval("x = new com.soffid.iam.api.User()");
		System.out.println ("Result = "+r2);
		r2 = interpreter.eval("new bsh.Interpreter()");
		System.out.println ("Result = "+r2);
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
	
	
	public void testTranslate(String src, String target) {
		String t = new JavascriptEvaluator().translateFromBsh(src);
		
		System.out.println(src);
		System.out.println("> "+target);
		System.out.println("= "+t);
		
		assertEquals(target, t);
	}
	
	public void test6() {
		testTranslate(
				"x = attributes{\"test\"}; ",
				"x = attributes[\"test\"]; ");
		testTranslate(
				"return attributes{\"test\"}.equals(attributes { \"test2\" } ); ",
				"return attributes[\"test\"] == (attributes [\"test2\"] ); ");
	}
	
	public void test7 () throws Exception {
		String result = (String) new JavascriptEvaluator().evaluate("return '10'; '20';", new HashMap<>(), "Test script");
		assertEquals(result, "10");

		String result2 = (String) new JavascriptEvaluator().evaluate("'20'; '10'", new HashMap<>(), "Test script");
		assertEquals(result2, "10");

		String result3 = (String) new JavascriptEvaluator().evaluate("false ? '20': '10'", new HashMap<>(), "Test script");
		assertEquals("10", result3);

	}


}
