package com.soffid.iam.web.user;

import java.net.URLEncoder;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;

import es.caib.zkib.component.DataTable;


public class UserSharedAccountsHandler extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private String listboxPath;

	public UserSharedAccountsHandler() throws NamingException, CreateException {
	}
	
	public void afterCompose() {
		listboxPath = (String) Executions.getCurrent().getArg().get("listbox");
	}
	
	public void openAccount(Event e) {
		DataTable dt = (DataTable) getFellow("listbox");
		
		String name = (String) dt.getJXPathContext().getValue("name");
		String system = (String) dt.getJXPathContext().getValue("system");
		
		Executions.getCurrent().sendRedirect("/resource/account/account.zul?name="+URLEncoder.encode(name)+"&system="+URLEncoder.encode(system),
				"_blank");
	}
	
}
