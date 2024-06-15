package com.soffid.iam.web.account;

import java.util.List;

import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

import es.caib.zkib.datasource.XPathUtils;

public class CredentialTypeHandler extends InputFieldUIHandler {
	@Override
	public void beforeCreate(InputField3 field) throws Exception {
		try {
			if ( XPathUtils.eval(field, "id") == null) {
				field.setReadonly(false);
			} else {
				field.setReadonly(true);
			}
		} catch (Exception e) { // Not in scope of account
		}
	}

	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		try {
			String system = (String) XPathUtils.eval(field, "system");
			String ssoSystem = ConfigurationCache.getProperty("AutoSSOSystem");
			if (ssoSystem == null) ssoSystem = "SSO";
			return system == null || system.equals(ssoSystem);
		} catch (Exception e) { // Not in scope of account
			return true;
		}
	}

}
