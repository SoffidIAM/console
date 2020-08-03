package com.soffid.iam.web.application;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

import es.caib.seycon.ng.exception.InternalErrorException;


public class RoleDefinitionProcessFieldHandler implements InputFieldUIHandler {

	@Override
	public void afterCreate(InputField3 field) throws Exception {
		List<String> l = new LinkedList<String>();
		for (ProcessDefinition def: EJBLocator.getBpmEngine().findProcessDefinitions (null, es.caib.bpm.vo.PredefinedProcessType.ROLE_DEFINITION_APPROVAL))
		{
			l.add(def.getName());
		}
		field.setValues(l);
		if (l.isEmpty())
			field.setReadonly(true);
	}

	@Override
	public boolean validate(InputField3 field) {
		return true;
	}

	@Override
	public boolean isVisible(InputField3 field) {
		return true;
	}

}
