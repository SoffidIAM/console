package com.soffid.iam.web.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Server;
import com.soffid.iam.web.component.CustomField3;

import es.caib.seycon.ng.exception.InternalErrorException;


public class DiscoveryServerField extends CustomField3 {
	public DiscoveryServerField() throws InternalErrorException, NamingException, CreateException {
		List<String> l = new LinkedList<>();
		for (Server server: EJBLocator.getDispatcherService().findTenantServers()) {
			l.add(server.getName());
		}
		setListOfValues(l.toArray(new String[l.size()]));
	}

}
