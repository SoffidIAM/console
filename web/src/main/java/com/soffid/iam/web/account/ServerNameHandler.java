package com.soffid.iam.web.account;

import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathException;

public class ServerNameHandler extends InputFieldUIHandler {
	@Override
	public boolean isVisible (InputField3 field) throws Exception {
		try {
			String system = (String) XPathUtils.getValue(field, "system");			
			String ssoSystem = com.soffid.iam.utils.ConfigurationCache.getProperty("AutoSSOSystem"); //$NON-NLS-1$
			AccountType type = (AccountType) XPathUtils.getValue(field, "type");
			return (ssoSystem == null || ssoSystem.equals(system) && type != null);
		} catch (JXPathException e) {
			return false;
		}
	}

	@Override
	public void onChange(InputField3 field) throws Exception {
		String name = (String) XPathUtils.getValue(field, "serverName");			
		String type = (String) XPathUtils.getValue(field, "serverType");			
		if (type != null && ! type.trim().isEmpty() && name != null && ! name.trim().isEmpty()) {
			if (type.equals("Windows")) {
				XPathUtils.setValue(field, "loginUrl", "rdp://"+name);
			}
			if (type.equals("Linux")) {
				XPathUtils.setValue(field, "loginUrl", "ssh://"+name);
			}
			if (type.equals("Database")) {
				XPathUtils.setValue(field, "loginUrl", "jdbc:<DRIVER>://"+name+"/<DATABASE>");
			}
		}
	}

}
