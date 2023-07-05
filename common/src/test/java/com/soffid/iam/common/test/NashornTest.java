package com.soffid.iam.common.test;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.openjdk.nashorn.api.scripting.ClassFilter;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import com.soffid.iam.api.Account;

import junit.framework.TestCase;

public class NashornTest extends TestCase {
	String script = "acc.name = '10'; "
//			+ "ac =Java.type('com.soffid.iam.api.Account');"
//			+ "t = new ac(); "
			+ "t = new com.soffid.iam.api.Account();"
			+ "t.name='20'; "
			+ "acc.name = 14;"
			+ "acc.attributes.att2='xxx';"
			+ "t";
	
	public void testNashorn() throws ScriptException {
		NashornScriptEngineFactory factory = new NashornScriptEngineFactory ();
		Account acc = new Account();
		ScriptEngine engine = factory.getScriptEngine(classFilter);
		Bindings bindings = new SimpleBindings();
		bindings.put("acc", acc);
		acc.setName("test");
		acc.getAttributes().put("att", "xxx");
		engine.setBindings(bindings , ScriptContext.ENGINE_SCOPE);
		Object r = engine.eval(script);
		System.out.println(r);
		System.out.println(acc);
	}
	
	ClassFilter classFilter = new ClassFilter()
    {
        @Override
        public boolean exposeToScripts(String s)
        {
            return s.startsWith("com.soffid.iam.api.");
        }
    };
}
