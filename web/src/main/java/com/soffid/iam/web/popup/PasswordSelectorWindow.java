package com.soffid.iam.web.popup;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Window;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.Password;
import com.soffid.iam.web.component.InputField3;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Databox;


public class PasswordSelectorWindow extends Window {
	EventListener listener;
	private Password password;

	public EventListener getListener() {
		return listener;
	}

	public void setListener(EventListener listener) {
		this.listener = listener;
	}
	
	public void accept(Event event) throws Exception {
		InputField3 np1 = (InputField3) getFellow("passnueva1");
		InputField3 np2 = (InputField3) getFellow("passnueva2");
		if (np1.attributeValidateAll() && np2.attributeValidateAll()) {
			Password p1 = (Password) np1.getValue();
			Password p2 = (Password) np2.getValue();
			if (p1.getPassword().isEmpty()) {
				np1.setWarning(0, Labels.getLabel("selfService.EnterAPassword"));
			} else if (p2.getPassword().isEmpty()) {
				np2.setWarning(0, Labels.getLabel("selfService.EnterAPassword"));
			} else if (!p1.getPassword().equals(p2.getPassword())) {
				np2.setWarning(0, Labels.getLabel("changepass.Coincidir"));
			} else {
				this.password = p1;
				listener.onEvent(event);
				setVisible(false);				
			}
		}
	}
	
	public void cancel(Event event) throws Exception {
		listener = null;
		setVisible(false);
	}
	
	public Password getPassword() {
		return password;
	}

	
	public void setPassword(Password password) {
		this.password = password;
	}
}
