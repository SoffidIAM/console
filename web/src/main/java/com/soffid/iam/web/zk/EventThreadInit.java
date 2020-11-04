package com.soffid.iam.web.zk;

import java.util.Locale;
import java.util.TimeZone;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.Locales;
import org.zkoss.util.TimeZones;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.service.ejb.SessionCacheService;
import com.soffid.iam.utils.Security;

import es.caib.zkib.component.DateFormats;
import es.caib.zkib.zkiblaf.tomee.TomeeThreadInit;

public class EventThreadInit extends TomeeThreadInit {
	String cacheSession;
	private static SessionCacheService ejb = null;
	private SoffidPrincipal currentIdentity;
	Locale locale;
	TimeZone timeZone;
	String[] dateFormat;
	private String clientIp;
	
	public EventThreadInit() throws NamingException, CreateException {
		super();
		if (ejb == null)
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
		Locales.setThreadLocal(locale);
		TimeZones.setThreadLocal(timeZone);
		DateFormats.setThreadLocal(dateFormat);
		MessageFactory.setThreadLocale(locale);
		return true;
	}

	@Override
	public void prepare(Component comp, Event event) throws Exception {
		super.prepare(comp, event);
		cacheSession = ejb.getCurrentSessionId();
		currentIdentity = Security.getSoffidPrincipal();
		locale = Locales.getCurrent();
		timeZone = TimeZones.getCurrent();
		dateFormat = DateFormats.getThreadLocal();
		clientIp = Security.getClientIp();
	}

}
