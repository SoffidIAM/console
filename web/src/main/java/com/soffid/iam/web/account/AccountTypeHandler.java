package com.soffid.iam.web.account;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.Component;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.System;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.zkib.component.Databox.Type;
import es.caib.zkib.datasource.XPathUtils;

public class AccountTypeHandler extends InputFieldUIHandler {
	@Override
	public void beforeCreate(InputField3 field) throws Exception {
		if ( XPathUtils.getValue(field, "id") == null) {
			List<String> values = field.getValues();
			values.remove(0);
		}
	}

	@Override
	public void afterCreate(InputField3 field) throws Exception {
		enableDescription(field);
	}

	public void enableDescription(InputField3 field) {
		ObjectAttributesDiv d = field.getObjectContainer();
		if (d != null) {
			AccountType type = (AccountType) field.getValue();
			ObjectAttributesDiv oad = (ObjectAttributesDiv) d;
			InputField3 desc = oad.getInputFieldsMap().get("description");
			desc.setReadonly(type == AccountType.USER);
			desc.invalidate();
		}
	}

	@Override
	public void onChange(InputField3 field) throws Exception {
		enableDescription(field);
	}

}
