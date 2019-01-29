package com.soffid.iam.web.zk;

import java.util.Locale;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.catalina.realm.GenericPrincipal;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.service.ejb.SessionCacheService;
import com.soffid.iam.utils.Security;

import es.caib.zkib.zkiblaf.tomee.TomeeThreadInit;

public class EventThreadInit extends TomeeThreadInit {
	String cacheSession;
	private SessionCacheService ejb;
	private SoffidPrincipal currentIdentity;
	Locale locale;
	private String clientIp;
	
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
		Security.clearNestedLogins();
		if (currentIdentity != null)
		{
			Security.setClientIp(clientIp);
			Security.nestedLogin(currentIdentity);
		}
		MessageFactory.setThreadLocale(locale);
		return true;
	}

	@Override
	public void prepare(Component comp, Event event) throws Exception {
		super.prepare(comp, event);
		cacheSession = ejb.getCurrentSessionId();
		currentIdentity = Security.getSoffidPrincipal();
		locale = MessageFactory.getLocale();
		clientIp = Security.getClientIp();
	}

}
