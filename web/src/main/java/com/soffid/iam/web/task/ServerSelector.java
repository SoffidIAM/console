package com.soffid.iam.web.task;

import java.util.LinkedList;
import java.util.List;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Server;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.web.component.InputField3;
import com.soffid.iam.web.component.InputFieldUIHandler;

import es.caib.zkib.component.Databox;

public class ServerSelector extends InputFieldUIHandler {

	@Override
	public void beforeCreate(InputField3 field) throws Exception {
		List<String> values = new LinkedList<>();
		values.add("*");
		DispatcherService ejb = EJBLocator.getDispatcherService();
		for (Server server: ejb.findAllServers())
		{
			if (server.getType().equals ( es.caib.seycon.ng.comu.ServerType.MASTERSERVER )) {
				values.add (server.getName());
			}
		}
		field.setValues(values);
		field.setType(Databox.Type.LIST);
	}

}
