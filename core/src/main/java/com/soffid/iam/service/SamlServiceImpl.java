package com.soffid.iam.service;

import java.util.List;
import java.util.Map;

import com.soffid.iam.api.SamlRequest;
import com.soffid.iam.service.saml.SAMLServiceInternal;

public class SamlServiceImpl extends SamlServiceBase {

	SAMLServiceInternal delegate;
	
	public SamlServiceImpl () 
	{
	}

	SAMLServiceInternal getDelegate () throws Exception
	{
		if (delegate == null)
		{
			delegate = new SAMLServiceInternal();
			delegate.setConfigurationService(getConfigurationService());
			delegate.setAssertionDao(getSamlAssertionEntityDao());
			delegate.setRequestDao(getSamlRequestEntityDao());
		}
		return delegate;
	}

	
	@Override
	protected String[] handleAuthenticate(String protocol, Map<String, String> response)
			throws Exception {
		return getDelegate().authenticate(protocol, response);
	}

	@Override
	protected String handleGenerateMetadata() throws Exception {
		return getDelegate().generateMetadata();
	}

	@Override
	protected String handleCheckAuthenticationToken(String[] token)
			throws Exception {
		return getDelegate().checkAuthenticationToken(token);
	}

	@Override
	protected SamlRequest handleGenerateSamlRequest() throws Exception {
		return getDelegate().generateSamlRequest();
	}

	@Override
	protected List<String> handleFindIdentityProviders() throws Exception {
		return delegate.findIdentityProviders();
	}
}
