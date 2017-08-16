package com.soffid.iam.service;

import java.util.List;
import java.util.Map;

import roles.Tothom;

import com.soffid.iam.api.SamlRequest;
import com.soffid.iam.model.SamlAssertionEntity;
import com.soffid.iam.model.SamlRequestEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.servei.ConfiguracioService;

@Service(grantees={Tothom.class})
@Depends({ ConfiguracioService.class,
	SamlRequestEntity.class,
	SamlAssertionEntity.class})
public class SamlService {
	@Description("Generates a SAML request to formard to the IdP")
	SamlRequest generateSamlRequest () {return null;}
	
	@Description("Generates SAML metadata to publish to SAML federation discovery database")
	String generateMetadata () {return null;}
			
	@Description("Validates the SAML response, and returns a single use username and password")
	String[] authenticate(String protocol, Map<String,String> response) {return null;}

	@Description("Validates the single use username and password generated on previous step. Returns the underlying account name")
	String checkAuthenticationToken (String token[]) {return null;}
	
	@Description("Gets the list of Identity Providers from medatata URL")
	List<String> findIdentityProviders () {return null;}
	
}
