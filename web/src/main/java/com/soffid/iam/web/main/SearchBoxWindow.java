package com.soffid.iam.web.main;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Div;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

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
	
	@Override
	public void afterCompose() {
		timer = (Timer) getFellow("timer");
		for (SearchHandler handler: handlers) {
			Div div = new Div();
			div.setSclass("search-divisor");
			appendChild(div);
			handler.setParentDiv(div);
		}
	}

	public void onChanging(InputEvent inputEvent) throws Exception {
		if (inputEvent.getValue().length() > 2) {
			for (SearchHandler handler: handlers) {
				handler.cancelSarch();
				handler.startSearch(inputEvent.getValue());
			}
			timer.start();
			onTimer(inputEvent);
			open (inputEvent.getTarget());
		}
	}
	
	public void onTimer (Event event) throws Exception {
		boolean pending = false;
		for (SearchHandler handler: handlers) {
			handler.update();
			if (! handler.isFinished())
				pending = true;
		}
		if (!pending)
			timer.stop();
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


