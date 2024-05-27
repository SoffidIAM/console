package com.soffid.iam.web.application;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.ApplicationType;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

import es.caib.zkib.datasource.XPathUtils;

public class SystemFieldHandler extends InputFieldUIHandler {
	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		try {
			Long id = (Long) XPathUtils.getValue(field, "id");
			String app = (String) XPathUtils.eval(field, "informationSystemName");
			if (app != null) {
				Application appInfo = EJBLocator.getApplicationService().findApplicationByApplicationName(app);
				if (appInfo != null && appInfo.getType() == ApplicationType.BUSINESS)  {
					field.setValue("business");
					return false;
				}
			}
			field.setReadonly (id != null);
		} catch (Exception e) {
			field.setReadonly (true);
		}
		return true;
	}

	@Override
	public void afterCreate(InputField3 field) throws Exception {
	}
}

