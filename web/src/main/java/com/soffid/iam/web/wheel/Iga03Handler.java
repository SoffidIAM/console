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

	public void addSap(Event ev) {
		Executions.getCurrent().sendRedirect("/config/agents.zul?wizard=sap", "_blank");
	}

	public void addServiceNow(Event ev) {
		Executions.getCurrent().sendRedirect("/config/agents.zul?wizard=servicenow", "_blank");
	}

	public void addAws(Event ev) {
		Executions.getCurrent().sendRedirect("/config/agents.zul?wizard=aws", "_blank");
	}

	public void addGoogle(Event ev) {
		Executions.getCurrent().sendRedirect("/config/agents.zul?wizard=google", "_blank");
	}

	public void addAzure(Event ev) {
		Executions.getCurrent().sendRedirect("/config/agents.zul?wizard=azure", "_blank");
	}

	public void addAtlassian(Event ev) {
		Executions.getCurrent().sendRedirect("/config/agents.zul?wizard=atlassian", "_blank");
	}

	public void addMariadb(Event ev) {
		Executions.getCurrent().sendRedirect("/config/agents.zul?wizard=mariadb", "_blank");
	}

	public void addSqlserver(Event ev) {
		Executions.getCurrent().sendRedirect("/config/agents.zul?wizard=sqlserver", "_blank");
	}

	public void addOracle(Event ev) {
		Executions.getCurrent().sendRedirect("/config/agents.zul?wizard=oracle", "_blank");
	}

	public void discovery(Event ev) {
		Executions.getCurrent().sendRedirect("/resource/network/discovery.zul?wizard=new", "_blank");
	}
}	
