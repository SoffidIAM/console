package com.soffid.iam.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.LogFactory;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.soffid.iam.api.SamlRequest;
import com.soffid.iam.model.ServerCertificateEntity;
import com.soffid.iam.service.saml.SAMLServiceInternal;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class SamlServiceImpl extends SamlServiceBase {
	org.apache.commons.logging.Log log = LogFactory.getLog(getClass());
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

	@Override
	protected String handleValidateOpenidToken(String token) throws Exception {
		return getDelegate().validateOpenidToken(token);
	}

}
