package com.soffid.iam.web.component;

import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Grid;

public class Column extends org.zkoss.zul.Column implements AfterCompose {
	@Override
	public void afterCompose() {
		if (getParent() != null && 
				getParent().getParent() != null &&
				"table".equals( getParent().getParent().getMold()))
		{
			setMold ("table");
		}
	}

}
