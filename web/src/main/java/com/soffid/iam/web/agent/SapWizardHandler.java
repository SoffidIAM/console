package com.soffid.iam.web.agent;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.ejb.CreateException;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import com.soffid.iam.api.System;
import com.soffid.iam.web.component.CustomField3;

import es.caib.seycon.ng.exception.InternalErrorException;

public class SapWizardHandler extends BaseWizardHandler {

	private CustomField3 name;
	private CustomField3 user;
	private CustomField3 pass;
	private CustomField3 server;
	private CustomField3 system;
	private CustomField3 type;
	private CustomField3 client;
	private CustomField3 lang;

	@Override
	public void afterCompose() {
		super.afterCompose();
		name = (CustomField3) getFellow("name");
		user = (CustomField3) getFellow("user");
		pass = (CustomField3) getFellow("pass");
		server = (CustomField3) getFellow("server");
		system = (CustomField3) getFellow("system");
		type = (CustomField3) getFellow("type");
		client = (CustomField3) getFellow("client");
		lang = (CustomField3) getFellow("lang");
	}
	
	protected void configureAgent(System s) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		super.configureAgent(s);
		s.setName(name.getValue().toString());
		s.setDescription("SAP "+name.getValue().toString());
		s.setClassName("com.soffid.iam.sync.agent.SAPAgent2");
		s.setParam0(user.getValue().toString());
		s.setParam1(pass.getValue().toString());
		s.setParam2(server.getValue().toString());
		s.setParam3(client.getValue().toString());
		s.setParam4(system.getValue().toString());
		s.setParam5(lang.getValue().toString());
		s.setBlobParam(("sapcua="+URLEncoder.encode(type.getValue().toString(), "UTF-8")).getBytes(StandardCharsets.UTF_8));
	}
	
	protected boolean validateConnectionAttributes() {
		if (name.attributeValidateAll() && user.attributeValidateAll() && pass.attributeValidateAll() &&
				server.attributeValidateAll() && client.attributeValidateAll() && lang.attributeValidateAll() &&
				type.attributeValidateAll()) {
			return true;
		}
		else
			return false;
	}
}
