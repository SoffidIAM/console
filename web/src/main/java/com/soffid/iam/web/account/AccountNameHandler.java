package com.soffid.iam.web.account;

import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;

import es.caib.zkib.datasource.XPathUtils;

public class AccountNameHandler extends InputFieldUIHandler {
	@Override
	public void onChange(InputField3 field) throws Exception {
		ObjectAttributesDiv d = field.getObjectContainer();
		if (d != null) {
			String system = (String) XPathUtils.getValue(field, "system");			
			String ssoSystem = com.soffid.iam.utils.ConfigurationCache.getProperty("AutoSSOSystem"); //$NON-NLS-1$
			if (ssoSystem == null || ! ssoSystem.equals(system)) {
				InputField3 desc = d.getInputFieldsMap().get("loginName");
				desc.setValue(field.getValue());
				desc.invalidate();
			}
		}
	}

	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		ObjectAttributesDiv d = field.getObjectContainer();
		if (d != null) {
			String system = (String) XPathUtils.getValue(field, "system");			
			String ssoSystem = com.soffid.iam.utils.ConfigurationCache.getProperty("AutoSSOSystem"); //$NON-NLS-1$
			boolean ro = ssoSystem != null && ssoSystem.equals(system);
			if (field.isReadonly() != ro) {
				field.setReadonly(ro);
				field.createField();
			}
			return ssoSystem == null || ! ssoSystem.equals(system) || 
					(field.getValue() != null && ! field.getValue().toString().trim().isEmpty());
		}
		else
			return true;
	}

}
