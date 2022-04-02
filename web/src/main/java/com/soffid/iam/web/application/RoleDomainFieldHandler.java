package com.soffid.iam.web.application;

import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Domain;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

import es.caib.zkib.component.Databox.Type;
import es.caib.zkib.datasource.XPathUtils;


public class RoleDomainFieldHandler extends InputFieldUIHandler {
	String lastInformationSystem = null;

	public void afterCreate(InputField3 field) throws Exception {
		field.setValues( getValues(field));
		field.setType(Type.LIST);
	}
	

	@Override
	public boolean validate(InputField3 field) {
		return true;
	}

	@Override
	public boolean isVisible(InputField3 field) {
		try {
			String app = (String) XPathUtils.eval(field, "informationSystemName");
			if (app == null ? lastInformationSystem != null: !app.equals(lastInformationSystem)) {
				field.setValues( getValues(field));
			}
		} catch (Exception e) {
			
		}
		return true;
	}

	public List<String> getValues(InputField3 field) {
		List<String> l = new LinkedList<String>();
		l.add(":"+Labels.getLabel("domainType.SENSE_DOMINI"));
		l.add("GROUPS: "+Labels.getLabel("domainType.GRUPS"));
		l.add("APPLICATIONS: "+Labels.getLabel("domainType.APLICACIONS"));
		try {
			String app = (String) XPathUtils.eval(field, "informationSystemName");
			if (app != null) {
				for (Domain d: EJBLocator.getDomainService().findApplicationDomainsByApplicationName(app)) {
					l.add( URLEncoder.encode( d.getName(), "UTF-8")+": "+d.getName()+" - "+d.getDescription());
				}
			}
			lastInformationSystem = app;
			field.invalidate();
		} catch (Exception e) {
			
		}
		return l;
	}
}
