package com.soffid.iam.web.popup;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Window;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.Password;
import com.soffid.iam.web.component.CustomField3;

import es.caib.zkib.component.DataTable;


public class EnterAccountWindow extends Window {
	EventListener listener;

	public EventListener getListener() {
		return listener;
	}

	public void setListener(EventListener listener) {
		this.listener = listener;
	}
	
	public void accept(Event event) throws Exception {
		if (getUserField().attributeValidateAll() &&
				getPasswordField().attributeValidateAll()) {
			listener.onEvent(event);
			setVisible(false);
		}
	}
	
	public void cancel(Event event) throws Exception {
		listener = null;
		setVisible(false);
	}
	

	public String getUserName() {
		return (String) getUserField().getValue();
	}

	protected CustomField3 getUserField() {
		return (CustomField3)getFellow("user");
	}

	public Password getPassword() {
		return (Password) getPasswordField().getValue();
	}

	protected CustomField3 getPasswordField() {
		return (CustomField3)getFellow("password");
	}
}
