package com.soffid.web.saml;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Challenge;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.User;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.SimpleDataNode;


public class SamlDataNode extends SimpleDataNode {

	public SamlDataNode(DataContext ctx) {
		super(ctx);
	}

	@Override
	protected void doUpdate() throws Exception {
		SAMLConfig c = (SAMLConfig) getDataContext().getData();
		updateParam ("soffid.auth.classic", c.classicMethod ? "true" : "false");
		updateParam ("soffid.auth.saml", c.samlMethod ? "true" : "false");
		updateParam ("soffid.auth.trustedLogin", c.trustedAuthentication ? "true" : "false");
		updateParam ("soffid.saml.metadata.alwaysTrust", c.alwaysTrust?"true":"false");
		updateParam ("soffid.saml.metadata.url", c.metadata);
		updateParam ("soffid.saml.idp", c.idp);
		updateParam ("soffid.saml.metadata.cache", Integer.toString(c.cache));
		updateParam("soffid.saml.debug", c.samlDebug ? "true": "false");
		updateParam("soffid.saml.principalAttribute", c.principalAttribute);
		updateParam("soffid.externalURL", c.hostName);
		updateParam("soffid.auth.maintenance", c.maintenanceMode ? "true": "false");
		updateParam("soffid.auth.timeout", c.sessionTimeout == null ? null: c.sessionTimeout.toString());
		if (c.motd == null || c.motd.trim().isEmpty())
			EJBLocator.getConfigurationService().deleteBlob("soffid.auth.motd");
		else
			EJBLocator.getConfigurationService().updateBlob("soffid.auth.motd", c.motd.getBytes(StandardCharsets.UTF_8));
		

		if (c.enableLinotp)
		{
			if (c.linotpServer == null || c.linotpServer.trim().isEmpty())
				throw new InternalErrorException ("Missing LinOTP server");
		}
		updateParam("soffid.linotp.enabled", c.enableLinotp? "true": "false");
		updateParam("soffid.linotp.user", c.linotpUser);
		updateParam("soffid.linotp.password", c.linotpPassword);
		updateParam("soffid.linotp.server", c.linotpServer);
		updateParam("soffid.linotp.userDomain", c.linotpUserDomain);
		updateParam("soffid.otp.optional", c.optionalToken);
		
		if (c.requireToken != null && ! c.requireToken.trim().isEmpty())
		{
			User myself = EJBLocator.getUserService().getCurrentUser();
			Challenge ch  = new Challenge();
			ch.setUser(myself);
			ch = EJBLocator.getOTPValidationService().selectToken(ch);
			if (ch.getCardNumber() == null)
			{
				throw new InternalErrorException("User has no active token. Unable to enforce two factor authentication requirement");
			}
		}
		updateParam("soffid.otp.required", c.requireToken);
		updateParam("soffid.otp.timeout", c.tokenTimeout == null?"" : c.tokenTimeout.toString());
		updateParam("soffid.webservice.auth.password", c.userPasswordWebservice? "true": "false");
		updateParam("soffid.webservice.auth.jwt", c.jwtWebservice? "true": "false");
		updateParam("soffid.webservice.auth.jwt-conf-url", c.jwtConfigurationUrl);
		updateParam("soffid.webservice.auth.jwt-iss", c.getJwtIssuer());
		saveAudiences(c.getJwtAudience());
	}

	private void saveAudiences(List<String> jwtAudience) throws InternalErrorException, NamingException, CreateException {
		final ConfigurationService configurationService = EJBLocator.getConfigurationService();
		for (Configuration cfg: configurationService.findConfigurationByFilter(
				"soffid.webservice.auth.jwt-aud%", 
				null, null, null)) {
			configurationService.delete(cfg);
		}
		int i = 0;
		for (String s: jwtAudience) {
			Configuration cfg = new Configuration();
			cfg.setCode("soffid.webservice.auth.jwt-aud-"+(i++));
			cfg.setValue(s);
			configurationService.create(cfg);
		}
	}

	private void updateParam(String param, String value) throws InternalErrorException, NamingException, CreateException {
		ConfigurationService svc = EJBLocator.getConfigurationService();
		Configuration cfg = svc.findParameterByNameAndNetworkName(param, null);
		if ( value == null || value.trim().isEmpty())
		{
			if (cfg != null)
				svc.delete(cfg);
		}
		else if (cfg == null )
		{
			cfg = new Configuration();
			cfg.setCode(param);
			cfg.setValue(value);
			cfg.setDescription("Auto configured");
			svc.create(cfg);
		}
		else
		{
			cfg.setValue(value);
			svc.update(cfg);
		}
		
	}

}
