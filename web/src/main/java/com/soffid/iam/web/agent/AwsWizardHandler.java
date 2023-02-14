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

public class AwsWizardHandler extends BaseWizardHandler {

	private CustomField3 clientId;
	private CustomField3 clientSecret;

	@Override
	public void afterCompose() {
		super.afterCompose();
		clientId = (CustomField3) getFellow("clientId");
		clientSecret = (CustomField3) getFellow("clientSecret");
	}
	
	protected void configureAgent(System s) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		super.configureAgent(s);
		s.setName("AWS");
		s.setDescription("AWS connector");
		s.setClassName("com.soffid.iam.sync.agent.aws.AWSAgent");
		s.setParam0(clientId.getValue().toString());
		s.setParam1(clientSecret.getValue().toString());
		s.setParam2("https://iam.amazonaws.com");
		s.setParam3("false"); // Debug
	}
	
	protected boolean validateConnectionAttributes() {
		if (clientId.attributeValidateAll() && clientSecret.attributeValidateAll()) {
			return true;
		}
		else
			return false;
	}

	@Override
	protected void loadMappings(System s) throws InternalErrorException, NamingException, CreateException, Exception {
		// No mapping
	}
}
