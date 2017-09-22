package com.soffid.web.saml;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Configuration;
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
	}

	private void updateParam(String param, String value) throws InternalErrorException, NamingException, CreateException {
		ConfigurationService svc = EJBLocator.getConfigurationService();
		Configuration cfg = svc.findParameterByNameAndNetworkName(param, null);
		if (cfg == null)
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
