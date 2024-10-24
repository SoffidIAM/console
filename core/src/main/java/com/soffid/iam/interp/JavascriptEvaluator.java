package com.soffid.iam.interp;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.apache.commons.logging.LogFactory;
import org.openjdk.nashorn.api.scripting.AbstractJSObject;
import org.openjdk.nashorn.api.scripting.ClassFilter;
import org.openjdk.nashorn.api.scripting.JSObject;
import org.openjdk.nashorn.api.scripting.NashornException;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;

import com.soffid.iam.api.Account;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class JavascriptEvaluator extends Evaluator {
	static ClassFilter classFilter = new SoffidClassFilter();
	@Override
	public Object evaluate(String script, Map<String, Object> vars, String label) throws Exception {
		if (!vars.containsKey("log"))
			vars.put("log", LogFactory.getLog(Evaluator.class));
		if (!vars.containsKey("principal"))
			vars.put("principal", Security.getSoffidPrincipal());
		NashornScriptEngineFactory factory = new NashornScriptEngineFactory ();
		ScriptEngine engine = factory.getScriptEngine(classFilter);
		Bindings bindings = new DynamicBindings(vars);
		engine.setBindings(bindings , ScriptContext.ENGINE_SCOPE);
		try {
			return unwrap(secureRun(engine, script), engine);
        } catch (ScriptException se) {
            // get the original cause
            Throwable cause = se.getCause();
            // in this case, the cause is a nashorn exception
            if (cause instanceof NashornException) {
                NashornException ne = (NashornException)cause;
                Object obj = ne.getEcmaError();
                if (obj instanceof JSObject) {
                    JSObject jsObj = (JSObject)obj;
                    throw new InternalErrorException("Error evaluating "+label+": "+
                    		jsObj.getMember("message")+" : "+jsObj.getMember("name")+"\nStack trace: "+ jsObj.getMember("stack"));
                }
                else {
                    throw new InternalErrorException("Error evaluating script: "+ ne.getMessage(), ne);
                }
            }
            throw se;
        }
	}
	private Object unwrap(Object r, ScriptEngine engine) {
		if (r == null)
			return null;
		if (r instanceof ScriptObjectMirror) {
			ScriptObjectMirror som = (ScriptObjectMirror) r;
			if (som.hasMember("getTime") && som.hasMember("getTimezoneOffset")) {
				long timestampLocalTime = (long) (double) som.callMember("getTime"); 
				int timezoneOffsetMinutes = (int) (double) som.callMember("getTimezoneOffset");
				return new Date(timestampLocalTime + timezoneOffsetMinutes * 60 * 1000);
			}
		}
		if (r instanceof org.openjdk.nashorn.api.scripting.AbstractJSObject) {
			org.openjdk.nashorn.api.scripting.AbstractJSObject js = (AbstractJSObject) r;
			if (js.isArray()) {
				List<Object> l = new LinkedList<>();
				Number length = (Number) js.getMember("length");
				for (int i = 0; i < length.intValue(); i++)
					l.add(unwrap(js.getSlot(i), engine));
				return l;
			}
			else 
			{
				HashMap m = new HashMap<>();
				for (Object value: js.keySet()) {
					m.put(value, unwrap(js.getMember(value.toString()), engine));
				}
				return m;
			}
		}

		return r;
	}
	
	@Override
	public boolean isSecure() {
		return true;
	}

	public Object secureRun (final ScriptEngine engine, final String expr) throws MalformedURLException, ScriptException
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
									return new Object[] { engine.eval(expr) };
								} catch (RuntimeException e) {
									return new Object[] { null, e};
								} catch (ScriptException e) {
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
			if (result[1] instanceof ScriptException)
				throw (ScriptException) result[1];
			else 
				throw (RuntimeException) result[1];
		}
	}
	@Override
	public String translateFromBsh(String bshScript) {
		return bshScript.replaceAll("\\{\\s*(\"[^\"]*\")\\s*\\}", "[$1]")
				.replaceAll("\\.\\s*size\\s*\\(\\s*\\)", ".length")
				.replaceAll("\\.\\s*equals", " == ")
				.replaceAll("(\\W)void(\\W)", "$1undefined$2");
	}
}
