package com.soffid.iam.web.account;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.xpath.XPathException;

import org.zkoss.zk.ui.Component;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.System;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldContainer;
import com.soffid.iam.web.component.InputFieldUIHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.zkib.component.Databox.Type;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathNotFoundException;

public class AccountTypeHandler extends InputFieldUIHandler {
	@Override
	public void beforeCreate(InputField3 field) throws Exception {
		try {
			if ( XPathUtils.getValue(field, "id") == null) {
				List<String> values = field.getValues();
				values.remove(0);
			}
		} catch (Exception e) { // Not in scope of account
			
		}
	}

	@Override
	public void afterCreate(InputField3 field) throws Exception {
		enableDescription(field);
	}

	public void enableDescription(InputField3 field) {
		InputFieldContainer d = field.getObjectContainer();
		if (d != null) {
			Object type = field.getValue();
			InputFieldContainer oad = (InputFieldContainer) d;
			InputField3 desc = oad.getInputFieldsMap().get("description");
			if (type == null)
				desc.setReadonly(false);
			else
				desc.setReadonly(type.toString().equals( AccountType.USER.toString()));
			desc.invalidate();
		}
	}

	@Override
	public void onChange(InputField3 field) throws Exception {
		enableDescription(field);
	}

}
