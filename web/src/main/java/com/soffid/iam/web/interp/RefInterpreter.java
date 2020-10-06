package com.soffid.iam.web.interp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.scripting.HierachicalAware;
import org.zkoss.zk.scripting.util.GenericInterpreter;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

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
		Method m;
		try {
			Event event = (Event) getFromNamespace("event");
			Object o = getFromNamespace(s[0]) ;
			if ( o == null)
				throw new UiException("Cannot find component "+s[0]);
			String method = s[1];
			if (method.contains("("))
				method = method.substring(0, method.indexOf('('));
			try {
				m = o.getClass().getMethod(method, Event.class);
				m.invoke(o, event);
			} catch (NoSuchMethodException e1) {
				try {
					m = o.getClass().getMethod(method, event.getClass());
					m.invoke(o, event);
				} catch (NoSuchMethodException e2) {
					m = o.getClass().getMethod(method);
					m.invoke(o);
				}
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
			throw new UiException(e.getTargetException());
		}
	}

}
