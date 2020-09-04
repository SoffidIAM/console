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

import es.caib.zkib.component.Databox.Type;
import es.caib.zkib.datasource.XPathUtils;

public class SystemFieldHandler extends InputFieldUIHandler {
	@Override
	public boolean isVisible(InputField3 field) throws Exception {
		try {
			Long id = (Long) XPathUtils.getValue(field, "id");
			field.setReadonly (id != null);
		} catch (Exception e) {
			field.setReadonly (true);
		}
		return true;
	}

	@Override
	public void afterCreate(InputField3 field) throws Exception {
		field.setType(Type.LIST);
		LinkedList<System> active = new LinkedList<>( EJBLocator.getDispatcherService().findAllActiveDispatchers() );
		Collections.sort(active, new Comparator<System>() {
			@Override
			public int compare(System o1, System o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		LinkedList<System> all = new LinkedList<>( EJBLocator.getDispatcherService().findDispatchersByFilter(null, null, null, null, null, false) );
		Collections.sort(all, new Comparator<System>() {
			@Override
			public int compare(System o1, System o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		List<String> values = new LinkedList<>();
		for (System s: active)
			values.add(s.getName()+":"+s.getName()+" - "+s.getDescription());
		for (System s: all)
			if (s.getUrl() == null)
				values.add(s.getName()+":"+s.getName()+" - "+s.getDescription());
		field.setValues(values);
	}

}
