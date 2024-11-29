package com.soffid.iam.web.account;

import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

import es.caib.zkib.datasource.XPathUtils;

public class SystemFieldHandler extends InputFieldUIHandler {
	boolean recursive = false;
	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		if (!recursive) {
			recursive = true;
			try {
				Long id = (Long) XPathUtils.getValue(field, "id");
				field.setReadonly (id != null ||
						!Security.isUserInRole("account:create"));
			} catch (Exception e) {
				field.setReadonly (true);
			} finally {
				recursive = false;
			}
			
		}
		return true;
	}

	@Override
	public void afterCreate(InputField3 field) throws Exception {
	}
}

