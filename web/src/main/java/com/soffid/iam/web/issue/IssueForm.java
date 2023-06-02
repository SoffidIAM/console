package com.soffid.iam.web.issue;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.beanutils.PropertyUtils;
import org.zkoss.zk.ui.ext.AfterCompose;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Issue;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.ObjectAttributesDiv;

import es.caib.seycon.ng.comu.TypeEnumeration;


public class IssueForm extends ObjectAttributesDiv implements AfterCompose {

	@Override
	public void afterCompose() {
		setReadonly(true);
		setObjectType(Issue.class.getName());
	}

	@Override
	public void adjustVisibility() {
		for (InputField3 input : fields)
		{
			Object value = input.getValue();
			if (value != null && value instanceof Collection)
				input.setVisible(!((Collection) value).isEmpty());
			else
				input.setVisible(value != null &&  !value.equals(""));
		}
	}

	@Override
	public void refresh() {
		super.refresh();
		adjustVisibility();
	}
	
}
