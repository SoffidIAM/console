package com.soffid.iam.web.application;

import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

import es.caib.zkib.datasource.XPathUtils;

public class ApprovalFieldHandler extends InputFieldUIHandler {
	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		try {
			Long id = (Long) XPathUtils.eval(field, "id");
			return (id != null);
		} catch (Exception e) {
			return true;
		}
	}

	@Override
	public void afterCreate(InputField3 field) throws Exception {
	}

}
