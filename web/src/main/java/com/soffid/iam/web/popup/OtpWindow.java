package com.soffid.iam.web.popup;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import es.caib.seycon.ng.exception.InternalErrorException;


public class OtpWindow extends Window {
	com.soffid.iam.web.component.OtpPageHandler handler;

	public void close(Event event) {
		onCancelPassword(event); 
		event.stopPropagation();
	}

	public void onCancelPassword(Event event) {
		detach();
	}
	
	public void onSetPassword (Event event) throws WrongValueException, InternalErrorException, NamingException, CreateException {
		Textbox password = (Textbox) getFellow("password");
		if (com.soffid.iam.EJBLocator.getOTPValidationService().validatePin(handler.getChallenge(), password.getValue()))
		{
			handler.enable();
			detach();
		}
		else
		{
			es.caib.zkib.zkiblaf.Missatgebox.avis("Wrong PIN");
		}
	}

	
	public com.soffid.iam.web.component.OtpPageHandler getHandler() {
		return handler;
	}

	
	public void setHandler(com.soffid.iam.web.component.OtpPageHandler handler) {
		this.handler = handler;
	}
}
