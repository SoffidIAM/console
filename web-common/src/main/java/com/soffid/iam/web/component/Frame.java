package com.soffid.iam.web.component;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequestWrapper;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Window;

import com.soffid.iam.utils.ConfigurationCache;

public class Frame extends es.caib.zkib.zkiblaf.Frame implements AfterCompose {

	@Override
	public void afterCompose() {
		if (new OtpPageHandler().needsOtp(this))
			setVisible(false);
	}

}
