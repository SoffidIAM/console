package com.soffid.selfservice.web;

import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.http.SimpleSession;
import org.zkoss.zk.ui.impl.SimpleDesktopCache;
import org.zkoss.zk.ui.sys.DesktopCache;
import org.zkoss.zk.ui.sys.SessionCtrl;

@WebListener
public class SessionListener implements HttpSessionListener {
	Log log = LogFactory.getLog(getClass());
	static List<HttpSession> sessions = new LinkedList<HttpSession>();
	static SessionListenerThread thread = null;
	public SessionListener () {
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		synchronized (sessions) {
			log.info("Created self service session");
			sessions.add(se.getSession());
			if (thread == null || !thread.isAlive()) {
				thread = new SessionListenerThread();
				thread.setName("Memory-checker");
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
	Log log = LogFactory.getLog(getClass());
	@Override
	public void run () {
		while (true) {
			try {
				sleep(5000);
				dump();
			} catch (Throwable th) {
			}
		}
	}

	private void dump() {
		Runtime runtime = Runtime.getRuntime();
		long max = runtime.maxMemory();
		long used = runtime.totalMemory() - runtime.freeMemory();
		long free = max - used ;
		List<HttpSession> sessions = SessionListener.sessions;
		synchronized (sessions) {
			long pct = free * 100L / max;
			if (pct < 15) {
				log.info("Number of Self-Service active sessions: "+sessions.size()+" "+pct+"%free memory");
				for ( HttpSession session: sessions) {
					log.info(" * "+session.getId()+" "+session.getAttribute("soffid-principal")+" IP "+session.getAttribute("soffid-remoteIp")+" X-Forwarded-For "+session.getAttribute("soffid-remoteProxy"));
				}
				int num = sessions.size() / 2; 
				if (num > 10) num = 10;
				for (HttpSession session: sessions) {
					SimpleSession zkSession = (SimpleSession) session.getAttribute("javax.zkoss.zk.ui.Session");
					log.warn("Closing session from "+zkSession.getRemoteAddr());
					zkSession.invalidateNow();
				}
				runtime.gc();
			}
			else if (pct <25) {
				log.info("Number of Self-Service active sessions: "+sessions.size()+" "+pct+"% free memory");
				for ( HttpSession session: sessions) {
					log.info(" * "+session.getId()+" "+session.getAttribute("soffid-principal")+" IP "+session.getAttribute("soffid-remoteIp")+" X-Forwarded-For "+session.getAttribute("soffid-remoteProxy"));
				}
			}
		}
	}
}