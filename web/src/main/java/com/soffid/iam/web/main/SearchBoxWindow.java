package com.soffid.iam.web.main;

import org.w3c.dom.html.HTMLInputElement;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;

import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNodeCollection;


public class SearchBoxWindow extends Popup implements AfterCompose { 
	private Timer timer;

	SearchHandler[] handlers = {
			new SearchMenuHandler(),
			new SearchUserHandler(),
			new SearchGroupHandler(),
			new SearchApplicationHandler()};

	private Component waiting;
	
	@Override
	public void afterCompose() {
		timer = (Timer) getFellow("timer");
		waiting = getFellowIfAny("waiting");
		for (SearchHandler handler: handlers) {
			Div div = new Div();
			div.setSclass("search-divisor");
			appendChild(div);
			handler.setParentDiv(div);
		}
	}

	public void onChange(Event inputEvent) throws Exception {
		InputElement e = (InputElement) inputEvent.getTarget();
		startSearch(inputEvent, (String) e.getRawValue());
	}
	
	private void startSearch(Event inputEvent, String value) throws Exception {
		for (SearchHandler handler: handlers) {
			handler.cancelSarch();
			handler.startSearch(value);
		}
		timer.start();
		if (waiting != null) waiting.setVisible(true);
		onTimer(inputEvent);
		if (!isVisible()) {
			open (inputEvent.getTarget());
		}
		for (SearchHandler handler: handlers) {
			handler.update();
		}
	}

	public void onChanging(InputEvent inputEvent) throws Exception {
		if (inputEvent.getValue().length() > 2) {
			startSearch(inputEvent, inputEvent.getValue());
		}
	}
	
	public void onTimer (Event event) throws Exception {
		boolean pending = false;
		for (SearchHandler handler: handlers) {
			handler.update();
			if (! handler.isFinished())
				pending = true;
		}
		if (!pending) {
			if (waiting != null) waiting.setVisible(false);
			timer.stop();
		}
	}
	
	public void onNavigate( MouseEvent event) {
		String url = (String) event.getTarget().getAttribute("url");
		if (url != null)
		{
			if (!url.startsWith("/")) url = "/"+url;
			if ( (event.getKeys() & MouseEvent.CTRL_KEY) != 0)
				Executions.getCurrent().sendRedirect(url, "_blank");
			else
				Executions.getCurrent().sendRedirect(url);
		}
	}
}


