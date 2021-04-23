package com.soffid.iam.web.host;

import org.apache.commons.beanutils.PropertyUtils;

import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;


public class IPHandler extends InputFieldUIHandler {

	@Override
	public void afterCreate(InputField3 field) throws Exception {
	}

	@Override
	public boolean validate(InputField3 field) throws Exception {
		return true;
	}

	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		Object o = field.getOwnerObject();
		if (o != null && Boolean.TRUE.equals( PropertyUtils.getProperty(o, "dhcp"))) {
			field.setReadonly(true);
			field.invalidate();
		}
		return true;
	}

}
