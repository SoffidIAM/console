package es.caib.seycon.ng.web.component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.lang.Classes;
import org.zkoss.lang.reflect.Fields;
import org.zkoss.xel.Function;
import org.zkoss.zk.scripting.HierachicalAware;
import org.zkoss.zk.scripting.Namespace;
import org.zkoss.zk.scripting.NamespaceChangeListener;
import org.zkoss.zk.scripting.SerializableAware;
import org.zkoss.zk.scripting.util.GenericInterpreter;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

import bsh.BshClassManager;
import bsh.BshMethod;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;
import bsh.Primitive;
import bsh.TargetError;
import bsh.UtilEvalError;
import bsh.Variable;

public class RefInterpreter extends GenericInterpreter implements
		HierachicalAware {

	Log log = LogFactory.getLog(getClass());

	@Override
	public Object getNativeInterpreter() {
		return null;
	}

	@Override
	protected void exec(String script) {
		String[] s = script.split("\\.");
		if ( s.length != 2 )
			throw new UiException("Wrong reference script "+script);
		Object o = getFromNamespace(s[0]);
		if ( o == null)
			throw new UiException("Cannot find component "+s[0]);
		Method m;
		try {
			try {
				m = o.getClass().getMethod(s[1], Event.class);
				m.invoke(o, getFromNamespace("event"));
			} catch (NoSuchMethodException e1) {
				m = o.getClass().getMethod(s[1]);
				m.invoke(o);
			}
		} catch (NoSuchMethodException e2) {
			throw new UiException("Cannot find method "+script);
		} catch (SecurityException e1) {
			throw new UiException("Cannot use method "+script);
		} catch (IllegalAccessException e) {
			throw new UiException(e);
		} catch (IllegalArgumentException e) {
			throw new UiException(e);
		} catch (InvocationTargetException e) {
			throw new UiException(e);
		}
	}

}
