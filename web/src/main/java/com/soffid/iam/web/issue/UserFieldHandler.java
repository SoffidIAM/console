package com.soffid.iam.web.issue;

import com.soffid.iam.api.IssueUser;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;


public class UserFieldHandler extends InputFieldUIHandler {
	@Override
	public void afterCreate(InputField3 field) throws Exception {
		field.setDataHandler(new IssueUserDataHandler(field.getDataType()));
	}

	@Override
	public void beforeCreate(InputField3 field) throws Exception {
		field.setDataHandler(new IssueUserDataHandler(field.getDataType()));
	}

	@Override
	public Object translateToUserInterface(InputField3 field, Object o) throws Exception {
		if (o == null) return null;
		else return ((IssueUser)o).getUserName();
	}
}
