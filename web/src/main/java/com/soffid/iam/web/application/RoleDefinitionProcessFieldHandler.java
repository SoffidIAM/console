package com.soffid.iam.web.application;

import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

import es.caib.zkib.component.Databox.Type;


public class RoleDefinitionProcessFieldHandler extends InputFieldUIHandler {

	@Override
	public void afterCreate(InputField3 field) throws Exception {
		List<String> l = new LinkedList<String>();
		l.add(": - None -");
		for (ProcessDefinition def: EJBLocator.getBpmEngine().findProcessDefinitions (null, es.caib.bpm.vo.PredefinedProcessType.ROLE_DEFINITION_APPROVAL))
		{
			l.add(def.getName());
		}
		field.setValues(l);
		field.setType(Type.LIST);
		if (l.size() == 1)  {
			field.setDisabled(true);
			field.setReadonly(true);
		}
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
