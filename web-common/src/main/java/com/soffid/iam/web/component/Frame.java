package com.soffid.iam.web.component;

import org.zkoss.zk.ui.ext.AfterCompose;

public class Frame extends es.caib.zkib.zkiblaf.Frame implements AfterCompose {
	String permissions[];

	@Override
	public void afterCompose() {
		if (new OtpPageHandler().needsOtp(this))
			setVisible(false);
	}

	public String[] getPermissions() {
		return permissions;
	}

	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions.split(" +");
	}
}
