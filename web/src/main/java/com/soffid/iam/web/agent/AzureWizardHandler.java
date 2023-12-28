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
import com.soffid.iam.api.UserDomain;
import com.soffid.iam.service.ejb.UserDomainService;
import com.soffid.iam.web.component.CustomField3;

import es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class AzureWizardHandler extends BaseWizardHandler {

	private CustomField3 domainName;
	private CustomField3 clientId;
	private CustomField3 clientSecret;
	private CustomField3 domainId;

	@Override
	public void afterCompose() {
		super.afterCompose();
		domainName = (CustomField3) getFellow("domainName");
		domainId = (CustomField3) getFellow("domainId");
		clientId = (CustomField3) getFellow("clientId");
		clientSecret = (CustomField3) getFellow("clientSecret");
	}
	
	protected void configureAgent(System s) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		super.configureAgent(s);
		s.setName("Azure "+domainName.getValue().toString());
		s.setDescription("Azure "+domainName.getValue().toString());
		s.setClassName("com.soffid.iam.sync.agent2.json.JSONAgent");
		s.setParam2("tokenOAuthCC");
		s.setParam3("https://login.microsoftonline.com/"+domainId.getValue().toString().trim()+"/oauth2/v2.0/token");
		s.setParam4("https://graph.microsoft.com");
		s.setParam7("access_token");
		s.setParam8("false"); // Debug
		s.setBlobParam(("{\"oauthParams\":["
				+ "{\"oauthParam\":\"grant_type\",\"oauthValue\":\"client_credentials\"},"
				+ "{\"oauthParam\":\"client_id\",\"oauthValue\":\""+encode(clientId.getValue().toString())+"\"},"
				+ "{\"oauthParam\":\"client_secret\",\"oauthValue\":\""+encode(clientSecret.getValue().toString())+"\"},"
				+ "{\"oauthParam\":\"scope\",\"oauthValue\":\"https://graph.microsoft.com/.default\"}]}")
				.getBytes(StandardCharsets.UTF_8));
		s.setUsersDomain("EMAIL");
		
		UserDomainService uds = new EJBLocator().getUserDomainService();
		if (uds.findUserDomainByName("EMAIL") == null) {
			UserDomain d = new UserDomain();
			d.setName("EMAIL");
			d.setType(TipusDominiUsuariEnumeration.SHELL);
			d.setBshExpr("/*js*/\nuser.mailDomain ? user.shortName+\"@\"+user.mailDomain: attributes[\"EMAIL\"]");
			d.setDescription("Email address");
			uds.create(d);
		}
	}
	
	private String encode(String string) {
		return string.replace("\\", "\\\\")
				.replace("\'", "\\\'")
				.replace("\"", "\\\"");
	}

	protected boolean validateConnectionAttributes() {
		if (domainId.attributeValidateAll() && domainName.attributeValidateAll() && 
				clientId.attributeValidateAll() && clientSecret.attributeValidateAll()) {
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
		InputStream in = getClass().getResourceAsStream("azure-mappings.xml");
		AMedia m = new AMedia("azure-mappings.xml", null, "text/xml", in);
		new DirectImporter(s).doImport(m, null);
	}
}
