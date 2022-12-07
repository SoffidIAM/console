package com.soffid.iam.web.agent;

import java.io.UnsupportedEncodingException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;

import com.soffid.iam.api.System;
import com.soffid.iam.web.component.CustomField3;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class OracleWizardHandler extends BaseWizardHandler {

	private CustomField3 host;
	private CustomField3 user;
	private CustomField3 pass;
	private CustomField3 port;
	private CustomField3 serviceName;
	private CustomField3 sid;

	@Override
	public void afterCompose() {
		super.afterCompose();
		host = (CustomField3) getFellow("host");
		user = (CustomField3) getFellow("user");
		pass = (CustomField3) getFellow("pass");
		port = (CustomField3) getFellow("port");
		sid = (CustomField3) getFellow("sid");
		serviceName = (CustomField3) getFellow("serviceName");
	}
	
	protected void configureAgent(System s) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		super.configureAgent(s);
		String name = sid.getValue() == null ? serviceName.getValue().toString() : sid.getValue().toString();
		s.setName("Oracle "+name);
		s.setClassName("com.soffid.iam.agent.oracle.OracleAgent");
		s.setParam0(user.getValue().toString());
		s.setParam1(pass.getValue().toString());
		String url;
		if (sid.getValue() == null || sid.getValue().toString().trim().isEmpty()) 
			url = "jdbc:oracle:thin:@//"+host.getValue().toString()+":"+port.getValue().toString()+"/"+serviceName.getValue().toString();
		else
			url = "jdbc:oracle:thin:@"+host.getValue().toString()+":"+port.getValue().toString()+":"+sid.getValue().toString();
		s.setParam2(url);
		s.setParam4("false"); // Debug
		s.setDescription("Oracle "+url);
	}
	
	protected boolean validateConnectionAttributes() {
		if (host.attributeValidateAll() && user.attributeValidateAll() && pass.attributeValidateAll() &&
				port.attributeValidateAll()) {
			boolean n1 = sid.getValue() == null || sid.getValue().toString().trim().isEmpty();
			boolean n2 = serviceName.getValue() == null || serviceName.getValue().toString().trim().isEmpty();
			if (n1 && n2) {
				Missatgebox.avis(Labels.getLabel("wizard-oracle.needsOne"));
				return false;
			}
			if (!n1 && !n2) {
				Missatgebox.avis(Labels.getLabel("wizard-oracle.onlyOne"));
				return false;
			}
			return true;
		}
		else
			return false;
	}
	
	protected void loadMappings(System s) throws Exception {
	}
}
