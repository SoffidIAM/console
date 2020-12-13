package com.soffid.iam.web.user;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.Component;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.MailDomain;
import com.soffid.iam.api.System;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;

import es.caib.zkib.component.Databox.Type;
import es.caib.zkib.datasource.XPathUtils;

public class EmailHandler extends InputFieldUIHandler {
	@Override
	public boolean validate(InputField3 field) throws Exception {
		String value = (String) field.getValue();
		if (value == null || value.trim().isEmpty())
			return true;
		int i = value.indexOf('@');
		if (i > 0) {
			String domain = value.substring(i+1);
			MailDomain d = EJBLocator.getMailListsService().findMailDomainByName(domain);
			if (d == null) {
				if (Security.isUserInRole("seu:dominiscorreu:show"))
					field.setWarning(null, String.format("The email domain %s is not valid. Register it at mail domains page", domain));
				else
					field.setWarning(null,  String.format("The email domain %s is not valid.", domain));
				return false;
			}
		}
		return true;
	}

}
