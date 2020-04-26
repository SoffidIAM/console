package com.soffid.iam.web.component;

import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.impl.LabelImageElement;

public class Menu2item extends Button {
	//-- super --//
	public String getOuterAttrs() {
		final StringBuffer sb =
			new StringBuffer(64).append(super.getOuterAttrs());

		appendAsapAttr(sb, Events.ON_CLICK);
		return sb.toString();
	}


}
