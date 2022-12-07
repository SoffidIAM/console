package com.soffid.iam.web.agent;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.ejb.CreateException;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.System;
import com.soffid.iam.web.component.CustomField3;

import es.caib.seycon.ng.exception.InternalErrorException;

public class MariadbWizardHandler extends BaseWizardHandler {

	private CustomField3 host;
	private CustomField3 user;
	private CustomField3 pass;
	private CustomField3 port;

	@Override
	public void afterCompose() {
		super.afterCompose();
		host = (CustomField3) getFellow("host");
		user = (CustomField3) getFellow("user");
		pass = (CustomField3) getFellow("pass");
		port = (CustomField3) getFellow("port");
	}
	
	protected void configureAgent(System s) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		super.configureAgent(s);
		s.setName("Mariadb "+host.getValue().toString());
		s.setClassName("com.soffid.iam.agent.mariadb.MariadbAgent");
		s.setParam0(user.getValue().toString());
		s.setParam1(pass.getValue().toString());
		String url = "jdbc:mariadb://"+host.getValue().toString();
		if (port.getValue() != null && !port.getValue().toString().isEmpty())
			url = url + ":"+port.getValue().toString();
		s.setParam2(url);
		s.setParam4("false"); // Debug
		s.setDescription("Mariadb "+url);
	}
	
	protected boolean validateConnectionAttributes() {
		if (host.attributeValidateAll() && user.attributeValidateAll() && pass.attributeValidateAll()) {
			return true;
		}
		else
			return false;
	}
	
	protected void loadMappings(System s) throws Exception {
	}
}
