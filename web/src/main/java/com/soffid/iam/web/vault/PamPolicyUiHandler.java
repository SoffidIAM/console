package com.soffid.iam.web.vault;

import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.PamPolicy;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

public class PamPolicyUiHandler extends InputFieldUIHandler {

	@Override
	public void afterCreate(InputField3 field) throws Exception {
		try {
			List <String> values = new LinkedList<>();
			for (PamPolicy policy: EJBLocator.getPamPolicyService().findPolicyByJsonQuery(null, null, null, null).getResources()) {
				values.add(policy.getName());
			}
			if (values.isEmpty())
				field.setVisible(false);
			else
			{
				field.setValues(values);
				field.createField();
			}
		} catch (Exception e) {
			field.setVisible(false);
		}
	}

}
