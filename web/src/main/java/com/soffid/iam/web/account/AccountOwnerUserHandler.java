package com.soffid.iam.web.account;

import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathNotFoundException;

public class AccountOwnerUserHandler extends InputFieldUIHandler {

	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		try {
			field.setReadonly(XPathUtils.getValue(field, "type") == AccountType.USER);
			field.createField();
		} catch (JXPathNotFoundException e) {
			field.setReadonly(true);
			field.createField();
			// Ignore
		}
		return true;
	}

}
