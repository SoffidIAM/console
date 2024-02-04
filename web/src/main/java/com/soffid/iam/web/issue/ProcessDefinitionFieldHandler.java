package com.soffid.iam.web.issue;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.PamRule;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

public class ProcessDefinitionFieldHandler  extends InputFieldUIHandler {
	@Override
	public void afterCreate(InputField3 field) throws Exception {
	}

	@Override
	public void beforeCreate(InputField3 field) throws Exception {
		List<String> values = new LinkedList<>();
		for (ProcessDefinition def: EJBLocator.getBpmEngine().findAllProcessDefinitions(true)) {
			String encoded = URLEncoder.encode(def.getName(), StandardCharsets.UTF_8);
			values.add(encoded+":"+def.getName());
		}
		field.setValues(values);
	}

}
