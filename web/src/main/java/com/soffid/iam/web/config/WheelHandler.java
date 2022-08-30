package com.soffid.iam.web.config;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Listbox;

import com.soffid.iam.web.component.FrameHandler;

import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.Form;

public class WheelHandler extends FrameHandler {
	public WheelHandler () throws Exception {
		System.out.println("");
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
	}
	
	public void next1(Event ev) {
		getFellow("box1").setVisible(false);
	}
}
