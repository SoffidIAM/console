package com.soffid.iam.web.account;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.Component;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.JumpServerGroup;
import com.soffid.iam.api.LaunchType;
import com.soffid.iam.api.System;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;

import es.caib.zkib.component.Databox.Type;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathException;

public class JumpServerGroupFieldHandler extends InputFieldUIHandler {
	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		try {
			LaunchType type = (LaunchType) XPathUtils.getValue(field, "launchType");
			return type == LaunchType.LAUNCH_TYPE_PAM;
		} catch (JXPathException e) {
			return false;
		}
	}

	@Override
	public void afterCreate(InputField3 field) throws Exception {
		field.setType(Type.LIST);
		LinkedList<JumpServerGroup> active = new LinkedList<>( EJBLocator.getPamSessionService().findJumpServerGroups() );
		Collections.sort(active, new Comparator<JumpServerGroup>() {
			@Override
			public int compare(JumpServerGroup o1, JumpServerGroup o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		List<String> values = new LinkedList<>();
		for (JumpServerGroup s: active)
			values.add(s.getName()+":"+s.getName()+" - "+s.getDescription());
		field.setValues(values);
	}

}
