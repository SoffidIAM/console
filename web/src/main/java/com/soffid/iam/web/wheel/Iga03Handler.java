package com.soffid.iam.web.wheel;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;


public class Iga03Handler extends Window {
	private Div step1;

	public void onClose(Event ev) {
		detach();
	}
	
	public void onRadio(Event ev) {
	}

	public void onRadio2(Event ev) {
	}

	@Override
	public void doHighlighted() {
		step1 = (Div) getFellow("step1");
		
		step1.setVisible(true);
		super.doHighlighted();
	}
	
	public void addActiveDirectory(Event ev) {
		Executions.getCurrent().sendRedirect("/config/agents.zul?wizard=ad", "_blank");
	}
}	
