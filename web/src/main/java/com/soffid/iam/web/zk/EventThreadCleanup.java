package com.soffid.iam.web.zk;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.utils.Security;

import es.caib.zkib.zkiblaf.tomee.TomeeThreadCleanup;

public class EventThreadCleanup extends TomeeThreadCleanup {
	@Override
	public void cleanup(Component comp, Event evt, List errs) throws Exception {
		super.cleanup(comp, evt, errs);
		if (Security.getSoffidPrincipal() != null)
			Security.nestedLogoff();
		MessageFactory.setThreadLocale(null);
		Security.setClientIp(null);
	}

	public EventThreadCleanup() throws NamingException, CreateException {
		super();
	}

}
