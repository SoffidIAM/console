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
import es.caib.zkib.zkiblaf.Missatgebox;

public class AtlassianWizardHandler extends BaseWizardHandler {

	private CustomField3 domainName;
	private CustomField3 domainId;
	private CustomField3 token;

	@Override
	public void afterCompose() {
		super.afterCompose();
		domainName = (CustomField3) getFellow("domainName");
		domainId = (CustomField3) getFellow("domainId");
		token = (CustomField3) getFellow("token");
	}
	
	protected void configureAgent(System s) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		super.configureAgent(s);
		s.setName("Atlassian "+domainName.getValue().toString());
		s.setDescription("Atlassian "+domainName.getValue().toString());
		s.setClassName("com.soffid.iam.sync.agent2.json.JSONAgent");
		s.setParam0(token.getValue().toString());
		s.setParam2("bearer");
		s.setParam4("https://api.atlassian.com/scim/directory/"+domainId.getValue().toString());
		s.setParam8("false"); // Debug
		s.setBlobParam(("{}")
				.getBytes(StandardCharsets.UTF_8));
		s.setUsersDomain("EMAIL");
	}
	
	private String encode(String string) {
		return string.replace("\\", "\\\\")
				.replace("\'", "\\\'")
				.replace("\"", "\\\"");
	}

	protected boolean validateConnectionAttributes() {
		if (domainId.attributeValidateAll() && domainName.attributeValidateAll() && 
				token.attributeValidateAll()) {
			String d = domainId.getValue().toString().trim();
			if (!validateDomain(d)) {
				domainId.setWarning(0, Labels.getLabel("wizard-azure.wrongDomain"));
				Missatgebox.avis(Labels.getLabel("wizard-azure.wrongDomain2"));
				return false;
			}
			if (!domainName.getValue().toString().contains(".") ) {
				domainName.setWarning(0, Labels.getLabel("wizard-servicenow.wrongHost"));
				return false;
			}
			return true;
		}
		else
			return false;
	}

	protected boolean validateDomain(String d) {
		if ( d.length() != 8 * 4 + 4 )
			return false;
		for (int i = 0; i < d.length(); i++) {
			char ch = d.charAt(i);
			switch (i) {
			case 8:
			case 13:
			case 18:
			case 23:
				if (ch != '-') return false;
				break;
			default:
				if (ch < '0' || ch > '9' && ch < 'a' || ch > 'f')
					return false;
				break;
			}
		}
		return true;
	}
	
	protected void loadMappings(System s) throws Exception {
		InputStream in = getClass().getResourceAsStream("atlassian-mappings.xml");
		AMedia m = new AMedia("atlassian-mappings.xml", null, "text/xml", in);
		new DirectImporter(s).doImport(m, null);
	}
}
