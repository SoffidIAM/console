package com.soffid.web.saml;

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
		updateParam("soffid.externalURL", c.hostName);


		if (c.enableLinotp)
		{
			if (c.linotpServer == null || c.linotpServer.trim().isEmpty())
				throw new InternalErrorException ("Missing LinOTP server");
		}
		updateParam("soffid.linotp.enable", c.enableLinotp? "true": "false");
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
