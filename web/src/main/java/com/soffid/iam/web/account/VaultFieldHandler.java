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
import com.soffid.iam.web.component.inputField.VaultFolderDataHandler;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.zkib.component.Databox.Type;
import es.caib.zkib.datasource.XPathUtils;

public class VaultFieldHandler extends InputFieldUIHandler {
	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		AccountType type = (AccountType) XPathUtils.getValue(field, "type");
		return type != AccountType.USER;
	}

	@Override
	public void beforeCreate(InputField3 field) throws Exception {
		field.setDataHandler(new VaultFolderDataHandler(field.getDataType()));
		field.setType(Type.DESCRIPTION);
		field.setSelectIcon("/img/container.svg");
	}

	@Override
	public boolean validate(InputField3 field) throws Exception {
		return true;
	}

}
