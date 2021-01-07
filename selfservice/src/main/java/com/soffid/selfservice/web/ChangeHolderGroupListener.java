package com.soffid.selfservice.web;

import org.zkoss.zk.au.AuScript;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.utils.Security;

public class ChangeHolderGroupListener implements EventListener {

	@Override
	public void onEvent(Event ev) throws Exception {
		String group = (String) ev.getTarget().getAttribute("holderGroup");
		Security.getSoffidPrincipal().setHolderGroup(group);
		ev.getTarget().response("reload",  new AuScript(ev.getTarget(), "window.location.reload();"));
	}

}
