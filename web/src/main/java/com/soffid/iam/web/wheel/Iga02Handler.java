package com.soffid.iam.web.wheel;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Application;


public class Iga02Handler extends Window {
	private Radiogroup radio;
	private Button step2Button;
	private String type;
	
	int substep;
	int currentServers;
	private Radiogroup radio2;
	private Button step3Button;
	private String type2;
	
	public void back(Event ev) {
		if (getFellow("step1").isVisible()) {
			setVisible(false);
		} else {
			getFellow("step2").setVisible(false);
			getFellow("step1").setVisible(true);
		}
	}
	
	public void onRadio(Event ev) {
		step2Button.setDisabled(false);
	}

	public void onRadio2(Event ev) {
		step3Button.setDisabled(false);
	}

	@Override
	public void doHighlighted() {
		super.doHighlighted();
		radio = (Radiogroup) getFellow("radio");
		step2Button = (Button) getFellow("step2Button");
		step2Button.setDisabled(true);
		radio2 = (Radiogroup) getFellow("radio2");
		step3Button = (Button) getFellow("step3Button");
		step3Button.setDisabled(true);
		radio.setSelectedItem(null);
		radio2.setSelectedItem(null);
		getFellow("step1").setVisible(true);
		getFellow("step2").setVisible(false);
	}
	
	public void step2(Event ev) {
		type = radio.getSelectedItem().getValue();
		if ("csv".equals(type)) {
			this.detach();
			Executions.getCurrent().sendRedirect("/resource/user/user.zul?wizard=csv", "_blank");
		}
		if ("form".equals(type)) {
			this.detach();
			Executions.getCurrent().sendRedirect("/resource/user/user.zul?wizard=form", "_blank");
		}
		if ("auth".equals(type)) {
			getFellow("step1").setVisible(false);
			getFellow("step2").setVisible(true);
		}
	}

	public void step3(Event ev) {
		type2 = radio2.getSelectedItem().getValue();
		if ("ad".equals(type2)) {
			this.detach();
			Executions.getCurrent().sendRedirect("/config/agents.zul?wizard=ad-source", "_blank");
		}
		if ("sql".equals(type2)) {
			this.detach();
			Executions.getCurrent().sendRedirect("/config/agents.zul?wizard=sql-source", "_blank");
		}
	}

}
