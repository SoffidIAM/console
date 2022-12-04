package com.soffid.iam.web.agent;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.ejb.CreateException;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import com.soffid.iam.api.System;
import com.soffid.iam.web.component.CustomField3;

import es.caib.seycon.ng.exception.InternalErrorException;

public class AdWizardHandler extends BaseWizardHandler {

	private CustomField3 name;
	private CustomField3 user;
	private CustomField3 pass;

	@Override
	public void afterCompose() {
		super.afterCompose();
		name = (CustomField3) getFellow("name");
		user = (CustomField3) getFellow("user");
		pass = (CustomField3) getFellow("pass");
		
	}
	
	protected void configureAgent(System s) throws InternalErrorException, NamingException, CreateException, UnsupportedEncodingException {
		super.configureAgent(s);
		s.setName(hostName);
		s.setDescription("Active directory "+ldapName.toString());
		s.setClassName("com.soffid.iam.sync.agent2.CustomizableActiveDirectoryAgent");
		s.setParam0(hostName);
		s.setParam1(ldapName.toString());
		s.setParam2(user.getValue().toString());
		s.setParam3(pass.getValue().toString());
		s.setParam7("false");
		s.setParam8("true"); // Insecure connection
		s.setParam9("false");
		s.setParam4("false");
		s.setParam5("false");
		s.setBlobParam("flatGroups=false&realTimeLogin=false&realTimeSource=true".getBytes(StandardCharsets.UTF_8));
	}
	
	private String hostName;
	
	LdapName ldapName;
	protected boolean validateConnectionAttributes() {
		if (name.attributeValidateAll() && user.attributeValidateAll() && pass.attributeValidateAll()) {
			try {
				ldapName = new LdapName((String) name.getValue());
			} catch (InvalidNameException e) {
				name.setWarning(0, "Invalid fully-qualyfied domain name");
				return false;
			}
			hostName = null;
			for (Rdn part: ldapName.getRdns()) {
				if (! part.getType().equalsIgnoreCase("dc")) {
					name.setWarning(0, part.getType()+"="+part.getValue().toString()+" should be dc="+part.getValue());
					return false;
				}
				if (hostName == null) hostName = part.getValue().toString();
				else hostName = part.getValue().toString()+"."+hostName;
			}
			return true;
		}
		else
			return false;
	}
}
