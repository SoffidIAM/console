package com.soffid.iam.web.account;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.Component;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.System;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;
import com.soffid.iam.web.component.InputFieldContainer;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.zkib.component.Databox.Type;
import es.caib.zkib.datasource.XPathUtils;

public class AccountStatusHandler extends InputFieldUIHandler {
	@Override
	public void onChange(InputField3 field) throws Exception {
		InputFieldContainer d = field.getObjectContainer();
		if (d != null)
		{
			Object o1 = field.getValue();
			boolean disabled = o1 == AccountStatus.REMOVED ||
					o1 == AccountStatus.DISABLED ||
					o1 == AccountStatus.FORCED_DISABLED ||
					o1 == AccountStatus.LOCKED;
			
			if (disabled)
				field.setSclass("databox dashed");
			else
				field.setSclass("databox");
			XPathUtils.setValue(field, "disabled", disabled);
		}
	}

}
