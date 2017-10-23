package com.soffid.iam.web.zk;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.catalina.realm.GenericPrincipal;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.service.ejb.SessionCacheService;
import com.soffid.iam.tomcat.SoffidPrincipal;
import com.soffid.iam.utils.Security;

import es.caib.zkib.zkiblaf.tomee.TomeeThreadInit;

public class EventThreadInit extends TomeeThreadInit {
	String cacheSession;
	private SessionCacheService ejb;
	private GenericPrincipal currentIdentity;
	
	
	public EventThreadInit() throws NamingException, CreateException {
		super();
		ejb = EJBLocator.getSessionCacheService();
	}

	@Override
	public boolean init(Component comp, Event event) throws Exception {
		super.init(comp, event);
		if (cacheSession == null)
			ejb.clearSession();
		else
			ejb.setSession(cacheSession);
		if (currentIdentity != null)
		{
			Security.nestedLogin(currentIdentity);
		}
		return true;
	}

	@Override
	public void prepare(Component comp, Event event) throws Exception {
		super.prepare(comp, event);
		cacheSession = ejb.getCurrentSessionId();
		currentIdentity = Security.getPrincipal();
	}

}
