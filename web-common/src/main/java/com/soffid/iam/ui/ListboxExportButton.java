package com.soffid.iam.ui;

import org.zkoss.zk.ui.ext.AfterCompose;

import com.soffid.iam.utils.Security;

public class ListboxExportButton extends es.caib.zkib.zkiblaf.ListboxExportButton
	implements AfterCompose {

	@Override
	public void afterCompose() {
		setVisible ( Security.isUserInRole("seu:data:export"));
	}

}
