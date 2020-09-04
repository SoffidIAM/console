package com.soffid.iam.web.popup;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Window;

import com.soffid.iam.api.Account;

import es.caib.zkib.component.DataTable;


public class AccountSelectorWindow extends Window {
	EventListener listener;

	public EventListener getListener() {
		return listener;
	}

	public void setListener(EventListener listener) {
		this.listener = listener;
	}
	
	public void accept(Event event) throws Exception {
		listener.onEvent(event);
		setVisible(false);
	}
	
	public void cancel(Event event) throws Exception {
		listener = null;
		setVisible(false);
	}
	
	public DataTable getDataTable() {
		return (DataTable) getFellow("accountsListbox");
	}

	public int getSelectedAccount() {
		DataTable dt = getDataTable();
		return dt.getSelectedIndex();
	}
}
