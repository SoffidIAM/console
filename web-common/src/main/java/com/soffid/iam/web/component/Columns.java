package com.soffid.iam.web.component;

import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Grid;

public class Columns extends org.zkoss.zul.Columns implements AfterCompose {
	@Override
	public void afterCompose() {
		if (getParent() != null && 
				"table".equals( getParent().getMold()))
		{
			setMold ("table");
		}
	}

}
