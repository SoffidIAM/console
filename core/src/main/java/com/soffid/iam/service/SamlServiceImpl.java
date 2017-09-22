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
	protected String[] handleAuthenticate(String hostName, String path, String protocol, Map<String, String> response)
			throws Exception {
		return getDelegate().authenticate(hostName, path, protocol, response);
	}

	@Override
	protected String handleGenerateMetadata(String hostName) throws Exception {
		return getDelegate().generateMetadata(hostName);
	}

	@Override
	protected String handleCheckAuthenticationToken(String[] token)
			throws Exception {
		return getDelegate().checkAuthenticationToken(token);
	}

	@Override
	protected SamlRequest handleGenerateSamlRequest(String hostName, String path) throws Exception {
		return getDelegate().generateSamlRequest(hostName, path);
	}

	@Override
	protected List<String> handleFindIdentityProviders() throws Exception {
		return getDelegate().findIdentityProviders();
	}

	@Override
	protected List<String> handleFindIdentityProviders(String url) throws Exception {
		return getDelegate().findIdentityProviders(url);
	}
}
