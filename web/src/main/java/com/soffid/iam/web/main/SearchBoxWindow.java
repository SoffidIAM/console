package com.soffid.iam.web.main;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNodeCollection;


public class SearchBoxWindow extends Window implements AfterCompose {

	private DataTable table;
	private Timer timer;
	private DataModel model;

	static String[] types = {"menu", "user", "group", "application", "account"};
	
	@Override
	public void afterCompose() {
		table = (DataTable) getFellow("table");
		timer = (Timer) getFellow("timer");
		model = (DataModel) getFellow("model");
	}

	public void onChanging(Event event) throws Exception {
		InputEvent inputEvent = (InputEvent) event;
		String text = inputEvent.getValue();
		model.getVariables().declareVariable("text", text);
		if (text.length() > 2) {
			for ( String type: types)
			{
				DataNodeCollection coll = (DataNodeCollection) model.getJXPathContext().getValue("users");
				if (coll.isInProgress())
					coll.cancel();
				coll.refresh();
			}
			if ( !timer.isRunning())
				timer.start();
		} else {
			timer.stop();
		}
	}
}
