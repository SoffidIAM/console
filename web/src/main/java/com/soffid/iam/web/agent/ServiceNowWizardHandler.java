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

public class ServiceNowWizardHandler extends BaseWizardHandler {

	private CustomField3 host;
	private CustomField3 user;
	private CustomField3 pass;
	private CustomField3 clientId;
	private CustomField3 clientSecret;

	@Override
	public void afterCompose() {
		super.afterCompose();
		host = (CustomField3) getFellow("host");
		user = (CustomField3) getFellow("user");
		pass = (CustomField3) getFellow("pass");
		clientId = (CustomField3) getFellow("clientId");
		clientSecret = (CustomField3) getFellow("clientSecret");
	}
	
	protected void configureAgent(System s) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		super.configureAgent(s);
		s.setName(host.getValue().toString());
		s.setDescription("Service now "+host.getValue().toString());
		s.setClassName("com.soffid.iam.sync.agent2.json.JSONAgent");
		s.setParam0(user.getValue().toString());
		s.setParam1(pass.getValue().toString());
		s.setParam2("tokenOAuthCC");
		s.setParam3("https://"+host.getValue().toString()+"/oauth_token.do");
		s.setParam4("https://"+host.getValue().toString()+"/api/now/table/");
		s.setParam7("access_token");
		s.setParam8("false"); // Debug
		s.setBlobParam(("{\"oauthParams\":["
				+ "{\"oauthParam\":\"grant_type\",\"oauthValue\":\"client_credentials\"},"
				+ "{\"oauthParam\":\"client_id\",\"oauthValue\":\""+encode(clientId.getValue().toString())+"\"},"
				+ "{\"oauthParam\":\"client_secret\",\"oauthValue\":\""+encode(clientSecret.getValue().toString())+"\"}]}")
				.getBytes(StandardCharsets.UTF_8));
	}
	
	private String encode(String string) {
		return string.replace("\\", "\\\\")
				.replace("\'", "\\\'")
				.replace("\"", "\\\"");
	}

	protected boolean validateConnectionAttributes() {
		if (host.attributeValidateAll() && user.attributeValidateAll() && pass.attributeValidateAll() &&
				clientId.attributeValidateAll() && clientSecret.attributeValidateAll()) {
			if (!host.getValue().toString().toLowerCase().endsWith(".service-now.com")) {
				host.setWarning(0, Labels.getLabel("wizard-servicenow.wrongHost2"));
				return false;
			}
			try {
				InetAddress.getByName(host.getValue().toString());
				return true;
			} catch (Exception e) {
				host.setWarning(0, Labels.getLabel("wizard-servicenow.wrongHost"));
				return false;
			}
		}
		else
			return false;
	}
	
	protected void loadMappings(System s) throws Exception {
		InputStream in = getClass().getResourceAsStream("servicenow-mappings.xml");
		AMedia m = new AMedia("servicenow-mappings.xml", null, "text/xml", in);
		new DirectImporter(s).doImport(m, null);
	}
}
