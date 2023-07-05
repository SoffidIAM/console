package com.soffid.iam.common.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.openjdk.nashorn.api.scripting.AbstractJSObject;
import org.openjdk.nashorn.api.scripting.ClassFilter;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;

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
	
	String script2  = "var v = []; \n"
			+ "v.push('a'); \n"
			+ "v.push('b'); \n"
			+ "v.push('c'); \n"
			+ "v.push('d'); \n"
			+ "// return v; \n"
			+ "return {a: 'a', b:10};";
	public void testNashorn2() throws ScriptException {
		NashornScriptEngineFactory factory = new NashornScriptEngineFactory ();
		Account acc = new Account();
		ScriptEngine engine = factory.getScriptEngine(classFilter);
		Bindings bindings = new SimpleBindings();
		engine.setBindings(bindings , ScriptContext.ENGINE_SCOPE);
		Object r = engine.eval(script2);
		System.out.println(r);
		System.out.println(r.getClass());
		if (r instanceof org.openjdk.nashorn.api.scripting.AbstractJSObject) {
			org.openjdk.nashorn.api.scripting.AbstractJSObject js = (AbstractJSObject) r;
			if (js.isArray()) {
				System.out.println("Is an array");
				List<Object> l = new LinkedList<>();
				Number length = (Number) js.getMember("length");
				System.out.println(length);
				for (int i = 0; i < length.intValue(); i++)
					System.out.println(js.getSlot(i));
			}
			else 
			{
				HashMap m = new HashMap<>();
				for (Object value: js.keySet()) {
					m.put(value, js.getMember(value.toString()));
				}
				System.out.println(m);
			}
		}
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
