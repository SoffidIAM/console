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

public class GoogleWizardHandler extends BaseWizardHandler {

	private CustomField3 user;
	private CustomField3 account;
	private CustomField3 host;
	private CustomField3 key;

	@Override
	public void afterCompose() {
		super.afterCompose();
		user = (CustomField3) getFellow("user");
		account = (CustomField3) getFellow("account");
		key = (CustomField3) getFellow("key");
		host = (CustomField3) getFellow("host");
	}
	
	protected void configureAgent(System s) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		super.configureAgent(s);
		s.setName("Goougle "+host.getValue().toString());
		s.setDescription("Google apps connector for "+host.getValue().toString());
		s.setClassName("com.soffid.iam.sync.agent.googleapps.GoogleAppsAgent");
		s.setParam0(user.getValue().toString());
		s.setParam1(account.getValue().toString());
		s.setParam3(host.getValue().toString()); // Debug
		s.setBlobParam(key.getValue().toString().getBytes(StandardCharsets.UTF_8));
	}
	
	protected boolean validateConnectionAttributes() {
		if (user.attributeValidateAll() && account.attributeValidateAll() &&
				host.attributeValidateAll() && key.attributeValidateAll()) {
			String h = host.getValue().toString();
			if (!h.contains("."))
			{
				host.setWarning(0, "Wrong domain name");
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
}
