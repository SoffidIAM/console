package com.soffid.iam.web.application;

import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.Component;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldContainer;
import com.soffid.iam.web.component.InputFieldUIHandler;

import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.Databox.Type;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.XPathEvent;


public class NameFieldHandler extends InputFieldUIHandler {

	@Override
	public void afterCreate(InputField3 field) throws Exception {
	}

	@Override
	public boolean validate(InputField3 field) {
		Component p = field.getParent();
		while (p != null && ! (p instanceof InputFieldContainer))
			p = p.getParent();
		if (p != null) {
			InputField3 name = ((InputFieldContainer)p).getInputFieldsMap().get("name");
			if (name != null) {
				BindContext ctx = XPathUtils.getComponentContext(name);
				name.onUpdate(new XPathEvent(ctx.getDataSource(), ctx.getXPath()));
			}
		}
		return true;
	}

	@Override
	public boolean isVisible(InputField3 field) {
		return true;
	}

}
