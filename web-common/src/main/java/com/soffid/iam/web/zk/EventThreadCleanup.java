package com.soffid.iam.web.zk;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.catalina.realm.GenericPrincipal;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.service.ejb.SessionCacheService;
import com.soffid.iam.tomcat.SoffidPrincipal;
import com.soffid.iam.utils.Security;

import es.caib.zkib.zkiblaf.tomee.TomeeThreadCleanup;
import es.caib.zkib.zkiblaf.tomee.TomeeThreadInit;

public class EventThreadCleanup extends TomeeThreadCleanup {
	@Override
	public void cleanup(Component comp, Event evt, List errs) throws Exception {
		super.cleanup(comp, evt, errs);
		if (Security.getPrincipal() != null)
			Security.nestedLogoff();
	}

	public EventThreadCleanup() throws NamingException, CreateException {
		super();
	}

}
