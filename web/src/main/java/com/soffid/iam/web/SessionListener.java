package com.soffid.iam.web;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.http.SimpleSession;

import es.caib.zkib.datamodel.DataModelCollection;

@WebListener
public class SessionListener implements HttpSessionListener {
	Log log = LogFactory.getLog(getClass());
	
	static List<HttpSession> sessions = new LinkedList<>();
	static SessionListenerThread thread = null;
	public SessionListener () {
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		synchronized (sessions) {
			sessions.add(se.getSession());
			if (thread == null || !thread.isAlive()) {
				thread = new SessionListenerThread();
				thread.setName("Console-Memory-checker");
				thread.setDaemon(true);
				thread.start();
			}
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		synchronized (sessions) {
			sessions.remove(se.getSession());
		}
		
	}
}

class SessionListenerThread extends Thread {
	long size;
	Map<String, Long> components ;
	Log log = LogFactory.getLog(getClass());
	@Override
	public void run () {
		while (true) {
			try {
				sleep(5000);
				dump();
			} catch (Throwable th) {
				log.warn("Error dumping sessions", th);
			}
		}
	}

	private void dump() {
		NumberFormat nf = NumberFormat.getInstance();

		Runtime runtime = Runtime.getRuntime();
		long max = runtime.maxMemory();
		long used = runtime.totalMemory() - runtime.freeMemory();
		long free = max - used ;
		List<HttpSession> sessions = SessionListener.sessions;
		int threshold2 = 20;
		try {
			threshold2 =  Integer.parseInt(System.getProperty("soffid.memory.limit2"));
		} catch (Exception e) {}
		int threshold1 = 15;
		try {
			threshold1 =  Integer.parseInt(System.getProperty("soffid.memory.limit1"));
		} catch (Exception e) {}
		synchronized (sessions) {
			long pct = free * 100L / max;
			if (pct < threshold1) {
				log.warn("SEVERE: Number of Console active sessions: "+sessions.size()+" "+pct+"% free memory");
				for ( HttpSession session: sessions) {
					logSession(nf, session);
				}
				int num = sessions.size() / 2; 
				if (num > 10) num = 10;
				for (HttpSession session: new LinkedList<> (sessions) ) {
					SimpleSession zkSession = (SimpleSession) session.getAttribute("javax.zkoss.zk.ui.Session");
					if (zkSession != null) {
						log.warn("Closing session from "+zkSession.getRemoteAddr());
						zkSession.invalidateNow();
					} else {
						session.invalidate();
					}
				}
				runtime.gc();
			}
			else if (pct < threshold2) {
				log.info("WARNING: Number of console active sessions: "+sessions.size()+" "+pct+"% free memory");
				for ( HttpSession session: sessions) {
					logSession(nf, session);
				}
			}
		}
	}

	protected void logSession(NumberFormat nf, HttpSession session) {
		Map<String, Long> sizes = new HashMap<>();
		dumpSession(session, sizes);
		log.info(" * ["+nf.format( size ) +"] "+session.getId()+" "+session.getAttribute("soffid-principal")+" IP "+session.getAttribute("soffid-remoteIp")+" X-Forwarded-For "+session.getAttribute("soffid-remoteProxy"));
		List<Entry<String, Long>> keySet = new LinkedList<>( sizes.entrySet() );
		Collections.sort(keySet, new Comparator<Entry<String, Long>>() {
			@Override
			public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		int num = 0;
		for (Entry<String, Long> s: keySet) {
			if (num++ > 10) break;
			log.info(" * > "+nf.format(s.getValue())+" "+s.getKey());
		}
		log.info("* ------------------------------");
		for (String key: components.keySet()) {
			log.info("* > "+key+" "+components.get(key));
		}
	}
	
	public void dumpSession(HttpSession session, Map<String, Long> sizes) {
		size = 0;
		components = new HashMap<>();
		HashSet<String> objects = new HashSet<>();
		try {
			for (Enumeration<String> it = session.getAttributeNames(); it.hasMoreElements(); ) {
				compute (session.getAttribute(it.nextElement()), objects, sizes);
			}
		} catch (Exception e) {
			size = -1;
		}
	}

	private void compute(Object object, HashSet<String> objects, Map<String, Long> sizes) {
		String tag = object.getClass().getName()+"@"+object.hashCode();
		
		// Alreade computed
		if (object == null || objects.contains(tag)) return;
		
		if (object instanceof AbstractComponent ) {
			AbstractComponent c = (AbstractComponent) object;
			Page p = c.getPage();
			String id = p == null ? "ORPHAN": p.getRequestPath()+ " # "+p.hashCode();
			Long prev = components.get(id);
			if (prev == null)
				components.put(id, new Long(1));
			else
				components.put(id, new Long(prev.longValue()+1));
		}
		
		objects.add(tag);
		String className = object.getClass().getName();
		if (className.startsWith("es.caib") || className.startsWith("com.soffid")) {
			Long s = sizes.get(className);
			if (s == null)
				sizes.put(object.getClass().getName(), new Long(1));
			else
				sizes.put(object.getClass().getName(), new Long(s.longValue()+1));
		}
		
		long size = 8; // min object size 
		if (object instanceof Map) {
			Map m = (Map) object;
			for (Object key: m.keySet()) {
				compute (key, objects, sizes);
				compute ( m.get(key), objects, sizes);
				size += 8; // internal size
			}
		} else if (object instanceof Collection && ! (object instanceof DataModelCollection)) {
			Collection l = (Collection) object;
			for (Object m: l) {
				compute (m, objects, sizes);
				size += 8; // internal size
			}
		} else if (object instanceof String) {
			size += ((String)object).length() * 4 + 4;
		} else if (object instanceof Integer) {
			size += 4;
		} else if (object instanceof Long) {
			size += 8;
		} else if (object instanceof Boolean) {
			size += 4;
		} else if (object instanceof Float) {
			size += 8;
		} else if (object instanceof Double) {
			size += 16;
		} else if (object.getClass().isArray()) {
			int l = Array.getLength(object);
			for (int i = 0; i < l; i++)
				computeClass(Array.get(objects, i), objects, sizes);
		} else {
			computeClass(object, objects, sizes);
		}
	}

	private void computeClass(Object object, HashSet<String> objects, Map<String, Long> sizes) {
		size += 8;
		Class cl = object.getClass();
		if (! cl.getName().startsWith("org.zkoss")  && ! cl.getName().startsWith("com.soffid") &&
				!cl.getName().startsWith("es.caib") && ! (object instanceof Serializable)) 
			return;
		if (cl.getName().startsWith("org.apache.catalina") || 
				cl.getName().startsWith("bsh.Token") || 
				object instanceof ClassLoader || object instanceof Class) return;
		do { 
			for (Field f: cl.getDeclaredFields()) {
				if ( (f.getModifiers() & Modifier.STATIC ) == 0) {
					try {
						f.setAccessible(true);
						Object v = f.get(object);
						compute (v, objects, sizes);
					} catch (Exception e) {
					}
				}
			}
			cl = cl.getSuperclass();
		} while (cl != null);
	}
}
