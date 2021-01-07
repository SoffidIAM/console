package com.soffid.iam.web.interp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.enterprise.event.ObserverException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.scripting.HierachicalAware;
import org.zkoss.zk.scripting.util.GenericInterpreter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.security.ObserveObligationException;
import com.soffid.iam.web.obligation.ObligationManager;

public class RefInterpreter extends GenericInterpreter implements
		HierachicalAware {

	Log log = LogFactory.getLog(getClass());

	@Override
	public Object getNativeInterpreter() {
		return null;
	}

	@Override
	protected void exec(String script) {
		if (script == null || script.trim().isEmpty())
			return;
		if (script.startsWith("import java.util.*;"))
			return; // Ignore bsh expression
		String[] s = script.split("\\.");
		if ( s.length != 2 ) {
			log.warn("Error evaluating expression for component " + getVariable("self") );
			throw new UiException("Wrong reference script "+script);
		}
		Method m;
		Event event = (Event) getFromNamespace("event");
		try {
			Object o = getFromNamespace(s[0]) ;
			if ( o == null || o == UNDEFINED) {
				o = null;
				Component c = event.getTarget();
				do {
					c = c.getNamespace().getOwner();
					if (c == null) break;
					c = c.getParent();
					if (c == null) break;
					o = c.getFellowIfAny(s[0]);
				} while (o == null);
			}
			if (o == null)
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
			Throwable te = e.getTargetException();
			Throwable current = te;
			while (current != null) {
				if (current instanceof ObserveObligationException) {
					ObserveObligationException oe = (ObserveObligationException) current;
					ObligationManager om = new ObligationManager();
					om.setCurrentObligations(event, oe.getObligations());
					throw new UiException(oe);
				}
				if (current.getCause() == current) 
					current = null;
				else
					current = current.getCause();
			}
			throw new UiException(te);
		}
	}

}
