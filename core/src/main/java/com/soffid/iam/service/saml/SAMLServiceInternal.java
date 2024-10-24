package com.soffid.iam.service.saml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.Map.Entry;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import javax.crypto.SecretKey;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.opensaml.core.config.InitializationException;
import org.opensaml.core.config.InitializationService;
import org.opensaml.core.criterion.EntityIdCriterion;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.XMLObjectBuilder;
import org.opensaml.core.xml.XMLObjectBuilderFactory;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.io.Marshaller;
import org.opensaml.core.xml.io.MarshallerFactory;
import org.opensaml.core.xml.io.MarshallingException;
import org.opensaml.core.xml.io.Unmarshaller;
import org.opensaml.core.xml.io.UnmarshallerFactory;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.saml.common.SAMLObjectBuilder;
import org.opensaml.saml.common.assertion.AssertionValidationException;
import org.opensaml.saml.common.assertion.ValidationContext;
import org.opensaml.saml.common.assertion.ValidationResult;
import org.opensaml.saml.common.xml.SAMLConstants;
import org.opensaml.saml.metadata.criteria.entity.EvaluableEntityDescriptorCriterion;
import org.opensaml.saml.metadata.resolver.impl.AbstractMetadataResolver;
import org.opensaml.saml.metadata.resolver.impl.AbstractReloadingMetadataResolver;
import org.opensaml.saml.metadata.resolver.impl.FilesystemMetadataResolver;
import org.opensaml.saml.metadata.resolver.impl.HTTPMetadataResolver;
import org.opensaml.saml.saml2.assertion.ConditionValidator;
import org.opensaml.saml.saml2.assertion.SAML20AssertionValidator;
import org.opensaml.saml.saml2.assertion.SAML2AssertionValidationParameters;
import org.opensaml.saml.saml2.assertion.StatementValidator;
import org.opensaml.saml.saml2.assertion.SubjectConfirmationValidator;
import org.opensaml.saml.saml2.assertion.impl.AudienceRestrictionConditionValidator;
import org.opensaml.saml.saml2.core.Assertion;
import org.opensaml.saml.saml2.core.Attribute;
import org.opensaml.saml.saml2.core.AttributeStatement;
import org.opensaml.saml.saml2.core.AttributeValue;
import org.opensaml.saml.saml2.core.AuthnRequest;
import org.opensaml.saml.saml2.core.EncryptedAssertion;
import org.opensaml.saml.saml2.core.Issuer;
import org.opensaml.saml.saml2.core.NameID;
import org.opensaml.saml.saml2.core.Response;
import org.opensaml.saml.saml2.core.StatusCode;
import org.opensaml.saml.saml2.core.Subject;
import org.opensaml.saml.saml2.encryption.Decrypter;
import org.opensaml.saml.saml2.encryption.Encrypter;
import org.opensaml.saml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml.saml2.metadata.EntitiesDescriptor;
import org.opensaml.saml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml.saml2.metadata.KeyDescriptor;
import org.opensaml.saml.saml2.metadata.NameIDFormat;
import org.opensaml.saml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml.saml2.metadata.SingleLogoutService;
import org.opensaml.saml.saml2.metadata.SingleSignOnService;
import org.opensaml.saml.saml2.metadata.impl.AssertionConsumerServiceBuilder;
import org.opensaml.saml.saml2.metadata.impl.EntitiesDescriptorBuilder;
import org.opensaml.saml.saml2.metadata.impl.EntityDescriptorBuilder;
import org.opensaml.saml.saml2.metadata.impl.KeyDescriptorBuilder;
import org.opensaml.saml.saml2.metadata.impl.NameIDFormatBuilder;
import org.opensaml.saml.saml2.metadata.impl.SPSSODescriptorBuilder;
import org.opensaml.saml.saml2.metadata.impl.SingleLogoutServiceBuilder;
import org.opensaml.saml.security.impl.SAMLSignatureProfileValidator;
import org.opensaml.security.credential.BasicCredential;
import org.opensaml.security.credential.Credential;
import org.opensaml.security.credential.UsageType;
import org.opensaml.security.credential.impl.CollectionCredentialResolver;
import org.opensaml.security.x509.BasicX509Credential;
import org.opensaml.xmlsec.algorithm.AlgorithmSupport;
import org.opensaml.xmlsec.config.DefaultSecurityConfigurationBootstrap;
import org.opensaml.xmlsec.encryption.EncryptedData;
import org.opensaml.xmlsec.encryption.support.DataEncryptionParameters;
import org.opensaml.xmlsec.encryption.support.EncryptionConstants;
import org.opensaml.xmlsec.encryption.support.EncryptionException;
import org.opensaml.xmlsec.encryption.support.KeyEncryptionParameters;
import org.opensaml.xmlsec.keyinfo.KeyInfoCredentialResolver;
import org.opensaml.xmlsec.keyinfo.impl.StaticKeyInfoCredentialResolver;
import org.opensaml.xmlsec.signature.KeyInfo;
import org.opensaml.xmlsec.signature.KeyName;
import org.opensaml.xmlsec.signature.Signature;
import org.opensaml.xmlsec.signature.X509Data;
import org.opensaml.xmlsec.signature.X509SubjectName;
import org.opensaml.xmlsec.signature.impl.KeyInfoBuilder;
import org.opensaml.xmlsec.signature.impl.KeyNameBuilder;
import org.opensaml.xmlsec.signature.impl.X509CertificateBuilder;
import org.opensaml.xmlsec.signature.impl.X509DataBuilder;
import org.opensaml.xmlsec.signature.impl.X509SubjectNameBuilder;
import org.opensaml.xmlsec.signature.support.SignatureConstants;
import org.opensaml.xmlsec.signature.support.SignaturePrevalidator;
import org.opensaml.xmlsec.signature.support.SignatureTrustEngine;
import org.opensaml.xmlsec.signature.support.Signer;
import org.opensaml.xmlsec.signature.support.impl.ExplicitKeySignatureTrustEngine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.SamlRequest;
import com.soffid.iam.model.SamlAssertionEntityDao;
import com.soffid.iam.model.SamlRequestEntity;
import com.soffid.iam.model.SamlRequestEntityDao;
import com.soffid.iam.service.ConfigurationService;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.util.Base64;
import net.shibboleth.utilities.java.support.component.ComponentInitializationException;
import net.shibboleth.utilities.java.support.resolver.CriteriaSet;
import net.shibboleth.utilities.java.support.resolver.ResolverException;
import net.shibboleth.utilities.java.support.security.RandomIdentifierGenerationStrategy;
import net.shibboleth.utilities.java.support.xml.BasicParserPool;

public class SAMLServiceInternal {

	private static final String SAML_KEY = "saml-key";
	private static final String KEYSTORE_PASSWORD = "abc123";
	private XMLObjectBuilderFactory builderFactory = null;
	private ConfigurationService configurationService;
	SamlAssertionEntityDao assertionDao;
	SamlRequestEntityDao requestDao;

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public SamlAssertionEntityDao getAssertionDao() {
		return assertionDao;
	}

	public void setAssertionDao(SamlAssertionEntityDao assertionDao) {
		this.assertionDao = assertionDao;
	}

	public SamlRequestEntityDao getRequestDao() {
		return requestDao;
	}

	public void setRequestDao(SamlRequestEntityDao requestDao) {
		this.requestDao = requestDao;
	}

	public SAMLServiceInternal () throws InitializationException 
	{
		InitializationService.initialize();
		builderFactory = XMLObjectProviderRegistrySupport.getBuilderFactory();
	}
	
	boolean debug () {
		return "true".equals(ConfigurationCache.getProperty("soffid.saml.debug"));
	}
	
	public String[] authenticate(String hostName, String path, String protocol, Map<String, String> response) throws Exception {
		String samlResponse = response.get("SAMLResponse");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(
					new ByteArrayInputStream(Base64.decode(samlResponse))
				);

		// Get the marshaller factory
		UnmarshallerFactory marshallerFactory = XMLObjectProviderRegistrySupport.getUnmarshallerFactory();
		 
		// Get the Subject marshaller
		Unmarshaller marshaller = marshallerFactory.getUnmarshaller(doc.getDocumentElement());
		 
		// Marshall the Subject
		Response saml2Response = (Response) marshaller.unmarshall(doc.getDocumentElement());

		log.info("Processing authentication response from "+saml2Response.getIssuer().getValue());

		boolean responseSigned = saml2Response.isSigned();
		if (debug()) {
			log.info("Response:\n"+ marshall(saml2Response));
			if (responseSigned)
				log.info("Response is signed");
			else
				log.info("Response is not signed");
		}
		if (responseSigned && ! validateResponse(hostName, saml2Response))
			return null;

		String originalrequest = saml2Response.getInResponseTo();
		SamlRequestEntity requestEntity = requestDao.findByExternalId(originalrequest);
		if (requestEntity == null)
		{
			log.info("Received authentication response for unknown request "+originalrequest);
			return null;
		}
		if (requestEntity.isFinished() == true)
		{
			log.info("Received authentication response for already served request "+originalrequest);
			return null;
		}

		for ( EncryptedAssertion encryptedAssertion: saml2Response.getEncryptedAssertions())
		{
			Assertion assertion = decrypt (encryptedAssertion);
			if (debug()) {
				log.info("Encrypted assertion:\n" + marshall(assertion));
			}
			if (validateAssertion(hostName, assertion, responseSigned))
			{
				log.info("Encrypted assertion is valid");
				return createAuthenticationRecord(hostName, requestEntity, assertion);
			}
		}
		for ( Assertion assertion: saml2Response.getAssertions())
		{
			if (validateAssertion(hostName, assertion, responseSigned))
			{
				log.info("Encrypted assertion is valid");
				return createAuthenticationRecord(hostName, requestEntity, assertion);
			}
		}
		
		return null;

	}

	
	private String marshall(XMLObject entity) {
		try {
			Transformer t = TransformerFactory.newDefaultInstance().newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			java.io.ByteArrayOutputStream out = new ByteArrayOutputStream();
			t.transform(new DOMSource(entity.getDOM()), new StreamResult(out ));
			return out.toString();
		} catch (Exception e) {
			return e.toString();
		}

	}

	private Assertion decrypt(EncryptedAssertion encryptedAssertion) throws Exception {		
		KeyStore ks = getKeyStore();
		X509Certificate cert = (X509Certificate) ks.getCertificate(SAML_KEY);
		PrivateKey privateKey = null;
		privateKey = (PrivateKey) ks.getKey(SAML_KEY, KEYSTORE_PASSWORD.toCharArray());

        KeyInfoCredentialResolver keyResolver = new StaticKeyInfoCredentialResolver(
        		new BasicCredential(cert.getPublicKey(), privateKey));

	    org.opensaml.xmlsec.encryption.EncryptedKey key = encryptedAssertion.getEncryptedData().
	                getKeyInfo().getEncryptedKeys().get(0);
	    
        Decrypter decrypter = new Decrypter(null, keyResolver, null);
	    SecretKey dkey = (SecretKey) decrypter.decryptKey(key, encryptedAssertion.getEncryptedData().
	                getEncryptionMethod().getAlgorithm());
	    
        Credential shared = new BasicCredential(dkey);
        
	    decrypter = new Decrypter(new StaticKeyInfoCredentialResolver(shared), null, null);
	    decrypter.setRootInNewDocument(true);
	    return decrypter.decrypt(encryptedAssertion);
	}

	private String[] createAuthenticationRecord(String hostName, SamlRequestEntity requestEntity, Assertion assertion) {
		Subject subject = assertion.getSubject();
		if (subject == null)
		{
			log.info("Assertion does not contain subject information");
			return null;
		}

		String user = null;
		String att = ConfigurationCache.getProperty("soffid.saml.principalAttribute");
		if (att != null && ! att.trim().isEmpty()) {
			user = getAttribute(assertion, att);
		} else {
			NameID nameID = subject.getNameID();
			if (nameID == null)
			{
				log.info("Assertion does not contain nameID information");
				return null;
			}
			
			if (nameID.getFormat() == null ||
					nameID.getFormat().equals(NameID.PERSISTENT) ||
					nameID.getFormat().equals(NameID.TRANSIENT) ||
					nameID.getFormat().equals(NameID.UNSPECIFIED) ||
					nameID.getFormat().equals(NameID.EMAIL))
			{
				user = nameID.getValue();
			}
			else {
				log.info("Cannot get user name. Format "+nameID.getFormat()+" not supported");
			}
		}
		if (user != null) {
			StringBuffer sb = new StringBuffer();
			SecureRandom sr = new SecureRandom();
			for (int i = 0; i < 180; i++)
			{
				int random = sr.nextInt(64);
				if (random < 26)
					sb.append((char) ('A'+random));
				else if (random < 52)
					sb.append((char) ('a'+random-26));
				else if (random < 62)
					sb.append((char) ('0'+random-52));
				else if (random < 63)
					sb.append('+');
				else
					sb.append('/');
			}
			requestEntity.setUser(user);
			requestEntity.setFinished(true);
			requestEntity.setKey(sb.toString());
			requestDao.update(requestEntity);
			log.info("Authenticated user "+user);
			return new String[]{ requestEntity.getHostName() + "\\"+ requestEntity.getExternalId(), sb.toString()};
		} 
		else
			return null;
	}

	private String getAttribute(Assertion assertion,String att) {
		for (AttributeStatement attStatement: assertion.getAttributeStatements()) {
			for (Attribute attribute: attStatement.getAttributes()) {
				if (attribute.getName().equals(att) || attribute.getFriendlyName().equals(att))
				{
					for (XMLObject value: attribute.getAttributeValues()) {
						return value.getDOM().getTextContent();
					}
				}
			}
		}
		return null;
	}

	public String generateMetadata(String hostName) throws MarshallingException, InvalidKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IllegalStateException, NoSuchProviderException, SignatureException, IOException, InternalErrorException, TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		EntityDescriptor entity = new EntityDescriptorBuilder().buildObject();
		// Generate entity descriptor
		entity.setEntityID(getEntityId(hostName, true));		

		SPSSODescriptor spsso = new SPSSODescriptorBuilder().buildObject();
		entity.getRoleDescriptors().add(spsso);
		spsso.setWantAssertionsSigned(true);
		spsso.setAuthnRequestsSigned(true);
		spsso.addSupportedProtocol(SAMLConstants.SAML20P_NS);
		
		// Generate security keys
		KeyDescriptor kd = new KeyDescriptorBuilder ().buildObject();
		spsso.getKeyDescriptors().add(kd);
		kd.setUse(UsageType.SIGNING);
		KeyInfo keyInfo = generateKeyInfo(true);
		kd.setKeyInfo(keyInfo);

		KeyDescriptor kdCrypt = new KeyDescriptorBuilder ().buildObject();
		spsso.getKeyDescriptors().add(kdCrypt);
		kdCrypt.setUse(UsageType.ENCRYPTION);
		KeyInfo keyInfoCrypt = generateKeyInfo(true);
		kdCrypt.setKeyInfo(keyInfoCrypt);

		// Generate Login services
		String prefix = "soffid/";
		AssertionConsumerService acs = new AssertionConsumerServiceBuilder().buildObject();
		spsso.getAssertionConsumerServices().add(acs);
		acs.setBinding(SAMLConstants.SAML2_POST_BINDING_URI);
		acs.setLocation(getBaseURL(hostName)+prefix+"saml/log/post");
		acs.setIndex(0);
		
		acs = new AssertionConsumerServiceBuilder().buildObject();
		spsso.getAssertionConsumerServices().add(acs);
		acs.setBinding(SAMLConstants.SAML2_POST_SIMPLE_SIGN_BINDING_URI);
		acs.setLocation(getBaseURL(hostName)+prefix+"saml/log/simple-post");
		acs.setIndex(2);
		
		// Generate logout service
		SingleLogoutService sls = new SingleLogoutServiceBuilder().buildObject();
		spsso.getSingleLogoutServices().add(sls);
		sls.setBinding(SAMLConstants.SAML2_SOAP11_BINDING_URI);
		sls.setLocation(getBaseURL(hostName)+prefix+"saml/slo/soap");

		sls = new SingleLogoutServiceBuilder().buildObject();
		spsso.getSingleLogoutServices().add(sls);
		sls.setBinding(SAMLConstants.SAML2_POST_BINDING_URI);
		sls.setLocation(getBaseURL(hostName)+prefix+"saml/slo/post");

		sls = new SingleLogoutServiceBuilder().buildObject();
		spsso.getSingleLogoutServices().add(sls);
		sls.setBinding(SAMLConstants.SAML2_REDIRECT_BINDING_URI);
		sls.setLocation(getBaseURL(hostName)+prefix+"saml/slo/redirect");

		// Generates name-id format
		NameIDFormat nid = new NameIDFormatBuilder().buildObject();
		spsso.getNameIDFormats().add(nid);
		nid.setFormat("urn:oasis:names:tc:SAML:2.0:nameid-format:persistent");
		
		MarshallerFactory marshallerFactory = XMLObjectProviderRegistrySupport.getMarshallerFactory();
		 
		// Get the Subject marshaller
		Marshaller marshaller = marshallerFactory.getMarshaller(entity);
		 
		// Marshall the Subject
		Element xml = marshaller.marshall(entity);

		return generateString(xml, true);
	}

	private KeyInfo generateKeyInfo(boolean complete) throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, InternalErrorException, InvalidKeyException, NoSuchProviderException, SignatureException,
			CertificateEncodingException {
		KeyInfo keyInfo = new KeyInfoBuilder().buildObject();

		if (complete) {
			KeyName keyName = new KeyNameBuilder().buildObject();
			keyName.setValue(SAML_KEY);
			keyInfo.getKeyNames().add(keyName);
		}
		
		X509Data data = new X509DataBuilder ().buildObject();
		keyInfo.getX509Datas().add(data);
		
		KeyStore ks = getKeyStore();
		X509Certificate cert = (X509Certificate) ks.getCertificate(SAML_KEY);
		
		X509SubjectName sn = new X509SubjectNameBuilder ().buildObject();
		if (complete)
			data.getX509SubjectNames().add(sn);
		sn.setValue(cert.getSubjectDN().getName());
		
		
		org.opensaml.xmlsec.signature.X509Certificate c = new X509CertificateBuilder().buildObject();
		data.getX509Certificates().add(c);
		c.setValue(Base64.encodeBytes(cert.getEncoded()));
		return keyInfo;
	}

	public String checkAuthenticationToken(String[] token) {
		String user = token[0];
		String tenant = null;
		int i = user.indexOf("\\");
		if ( i > 0)
		{
			tenant = user.substring(0, i);
			user = user.substring(i+1);
		} else {
			tenant = "master";
		}
		com.soffid.iam.utils.Security.nestedLogin(tenant, "anonymous", com.soffid.iam.utils.Security.ALL_PERMISSIONS);
		try {
			SamlRequestEntity requestEntity = requestDao.findByExternalId(user);
			if (requestEntity == null)
				return null;
			if (!requestEntity.isFinished())
				return null;
			if (!requestEntity.getHostName().equals(tenant))
				return null;
			if (!requestEntity.getKey().equals(token[1]))
				return null;
			return tenant+"\\"+requestEntity.getUser();
		} finally {
			com.soffid.iam.utils.Security.nestedLogoff();
		}
	}

	public SamlRequest generateSamlRequest(String hostName, String path) throws InternalErrorException {
		try {
			RandomIdentifierGenerationStrategy idGenerator = new RandomIdentifierGenerationStrategy();
			// Get the assertion builder based on the assertion element name
			SAMLObjectBuilder<AuthnRequest> builder = (SAMLObjectBuilder<AuthnRequest>) builderFactory.getBuilder(AuthnRequest.DEFAULT_ELEMENT_NAME);
			 
			EntityDescriptor idp = getIdpMetadata(hostName);
			if (idp == null)
				throw new InternalErrorException(String.format("Unable to find Identity Provider metadata"));
			IDPSSODescriptor idpssoDescriptor = idp.getIDPSSODescriptor(SAMLConstants.SAML20P_NS);

			// Create the assertion
			
			AuthnRequest req = builder.buildObject( );
			
			String newID = idGenerator.generateIdentifier();
			
			SamlRequest r = new SamlRequest();
			r.setParameters(new HashMap<String, String>());
			boolean compress = false;
			for (SingleSignOnService sss : idpssoDescriptor.getSingleSignOnServices()) {
				if (sss.getBinding().equals(SAMLConstants.SAML2_REDIRECT_BINDING_URI)) {
					r.setMethod(SAMLConstants.SAML2_REDIRECT_BINDING_URI);
					r.setUrl(sss.getLocation());
					req.setDestination(sss.getLocation());
					compress = true;
					break;
				}
				if (sss.getBinding().equals(SAMLConstants.SAML2_POST_BINDING_URI)) {
					r.setMethod(SAMLConstants.SAML2_POST_BINDING_URI);
					r.setUrl(sss.getLocation());
					req.setDestination(sss.getLocation());
					break;
				}
			}
			if (r.getUrl() == null)
				throw new InternalErrorException(String.format("Unable to find a suitable endpoint for IdP %s"), idp.getEntityID());

			if (path.startsWith("/")) path = path.substring(1);
			if (!path.isEmpty() && ! path.endsWith("/")) path = path + "/";
			req.setAssertionConsumerServiceURL(getBaseURL(hostName)+path+"saml/log/post");
			req.setProtocolBinding(SAMLConstants.SAML2_POST_BINDING_URI);
			req.setForceAuthn(false);
			req.setID(newID);
			req.setIssueInstant(new DateTime ());
			Issuer issuer = ( (SAMLObjectBuilder<Issuer>) builderFactory.getBuilder(Issuer.DEFAULT_ELEMENT_NAME)).buildObject();
			issuer.setValue(getEntityId(hostName, !path.contains("selfservice")));
			
			req.setIssuer( issuer );
			
			Element xml = sign (builderFactory, req);
			
			String xmlString = generateString(xml, false);
			
			r.getParameters().put("RelayState", newID);
			if (compress) {
				ByteArrayOutputStream ba = new ByteArrayOutputStream();
				DeflaterOutputStream out = new DeflaterOutputStream(ba, new Deflater(Deflater.DEFAULT_COMPRESSION, true));
				out.write(xmlString.getBytes("UTF-8"));
				out.flush();
				out.close();
				r.getParameters().put("SAMLRequest", Base64.encodeBytes(ba.toByteArray()));
			}
			else 
			{
				r.getParameters().put("SAMLRequest", Base64.encodeBytes(xmlString.getBytes("UTF-8")));
			}
//			encryptAssertion(req, r, idp, idpssoDescriptor);
			
			SamlRequestEntity reqEntity = requestDao.newSamlRequestEntity();
			reqEntity.setHostName(hostName);
			reqEntity.setDate(new Date());
			reqEntity.setExternalId(newID);
			reqEntity.setFinished(false);
			requestDao.create(reqEntity);

			
			return r;
		} catch (Exception e) {
			if (e instanceof InternalErrorException)
				throw (InternalErrorException) e;
			else
				throw new InternalErrorException(e.getMessage(), e);
		}

	}
	private void encryptAssertion(AuthnRequest req, SamlRequest r,
			EntityDescriptor entityDescriptor,
			IDPSSODescriptor idpssoDescriptor) throws CertificateException,
			EncryptionException, NoSuchAlgorithmException, KeyException {
		
		for (KeyDescriptor key: idpssoDescriptor.getKeyDescriptors())
		{
			X509Certificate cert = getCert(key);
			if (cert != null && (
					key.getUse() == UsageType.ENCRYPTION || key.getUse() == UsageType.UNSPECIFIED))
			{
				BasicX509Credential cred = new BasicX509Credential(cert);
				cred.setEntityId(entityDescriptor.getEntityID());
				cred.setUsageType(key.getUse());

				KeyEncryptionParameters kekParams = new KeyEncryptionParameters();
				kekParams.setEncryptionCredential(cred);
				kekParams.setAlgorithm(EncryptionConstants.ALGO_ID_KEYTRANSPORT_RSA15);
//				kekParams.setKeyInfoGenerator(new StaticKeyInfoGenerator(key.getKeyInfo()));

				Credential symmetricCredential = AlgorithmSupport.generateSymmetricKeyAndCredential(EncryptionConstants.ALGO_ID_BLOCKCIPHER_AES128); 
				
				DataEncryptionParameters encParams = new DataEncryptionParameters();
				encParams.setAlgorithm(EncryptionConstants.ALGO_ID_BLOCKCIPHER_AES256);
//				encParams.setKeyInfoGenerator(new StaticKeyInfoGenerator(key.getKeyInfo()));
				encParams.setEncryptionCredential( symmetricCredential );

				Encrypter encrypter = new Encrypter(encParams, kekParams);
				EncryptedData encObject = encrypter.encryptElement(req, encParams, kekParams);
				 
				r.getParameters().put("Encrypted", encObject.toString());
			}
		}
	}
	
	
	/**
	 * Builds the requested XMLObject.
	 * 
	 * @param name
	 *            name of the XMLObject
	 * 
	 * @return the build XMLObject
	 */
	protected <T extends XMLObject> T buildXMLObject(QName name) {
		final XMLObjectBuilder<T> builder = builderFactory.getBuilderOrThrow(name);
		final T wsObj = builder.buildObject(name);
		return wsObj;
	}     
 
 
 
	private X509Certificate getCert(KeyDescriptor key) throws CertificateException {
		if (key.getKeyInfo().getX509Datas().size() < 1)
			return null;
		if (key.getKeyInfo().getX509Datas().get(0).getX509Certificates().size() < 1)
			return null;
		
		String certb64 = key.getKeyInfo().getX509Datas().get(0).getX509Certificates().get(0).getValue();

		byte encodedCert[] = Base64.decode(certb64);
		ByteArrayInputStream inputStream  =  new ByteArrayInputStream(encodedCert);

		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		return (X509Certificate)certFactory.generateCertificate(inputStream);
	}

	private Element sign(XMLObjectBuilderFactory builderFactory, AuthnRequest req) throws InvalidKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IllegalStateException, NoSuchProviderException, SignatureException, IOException, InternalErrorException, UnrecoverableKeyException, MarshallingException, org.opensaml.xmlsec.signature.support.SignatureException, UnmarshallingException {

		
		KeyStore ks = getKeyStore();
		Credential cred = new BasicX509Credential(
				(X509Certificate) ks.getCertificate(SAML_KEY), 
				(PrivateKey) ks.getKey(SAML_KEY, KEYSTORE_PASSWORD.toCharArray()));
		XMLObjectBuilder<Signature> signatureBuilder = builderFactory.getBuilderOrThrow(Signature.DEFAULT_ELEMENT_NAME);
		Signature signature = signatureBuilder.buildObject(Signature.DEFAULT_ELEMENT_NAME);
		signature.setSigningCredential(cred);
		signature.setCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
		signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA256);
		signature.setKeyInfo(generateKeyInfo(false));
		req.setSignature(signature);
		
		// Get the marshaller factory
		MarshallerFactory marshallerFactory = XMLObjectProviderRegistrySupport.getMarshallerFactory();
		UnmarshallerFactory unmarshallerFactory = XMLObjectProviderRegistrySupport.getUnmarshallerFactory();
		Marshaller marshaller = marshallerFactory.getMarshaller(req);
		Element element = marshaller.marshall(req);
		Signer.signObject(signature);
		
		req = (AuthnRequest) unmarshallerFactory.getUnmarshaller(req.getDOM()).unmarshall(req.getDOM());
		return marshallerFactory.getMarshaller(req).marshall(req);

	}

	private String generateString(Element xml, boolean pretty)
			throws TransformerConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		if (pretty)
		{
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		}
		
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(xml);
		transformer.transform(source, result);

		String xmlString = result.getWriter().toString();
		return xmlString;
	}

	private String getBaseURL(String hostName) throws InternalErrorException 
	{
		String url = System.getProperty("soffid.externalURL");
		if (url == null)
			url = ConfigurationCache.getTenantProperty(com.soffid.iam.utils.Security.getCurrentTenantName(), "soffid.externalURL");
		if (url == null)
			url = ConfigurationCache.getProperty("AutoSSOURL");
		if (url == null)
		{
			if (hostName.startsWith("http"))
				url = hostName;
			else
				url = "http://"+hostName;
		}
		if (! url.endsWith("/"))
			url = url + "/";

		return url;
	}

	private String getEntityId(String hostName, Boolean console) throws InternalErrorException 
	{
		if (console)
			return getBaseURL(hostName)+"soffid-iam-console";
		else
			return getBaseURL(hostName)+"soffid-iam-selfservice";
	}

	private KeyStore getKeyStore () throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, InternalErrorException, InvalidKeyException, IllegalStateException, NoSuchProviderException, SignatureException
	{
		byte[] key = configurationService.getBlob(SAML_KEY);
		if (key == null)
		{
			KeyStore ks = generateKeys();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ks.store(out, KEYSTORE_PASSWORD.toCharArray());
			configurationService.updateBlob(SAML_KEY, out.toByteArray());
			return ks;
		}
		else
		{
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load( new ByteArrayInputStream(key), KEYSTORE_PASSWORD.toCharArray());
			return ks;
		}
	}

	private KeyStore generateKeys() throws KeyStoreException, InvalidKeyException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, CertificateException, IOException {
        java.security.Security.addProvider( new BouncyCastleProvider() );

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
        SecureRandom random = new SecureRandom();

        keyGen.initialize(2048, random);
        KeyPair pair = keyGen.generateKeyPair();
        // Generar clave raiz
        X509Certificate cert = createCertificate(pair.getPublic(), pair.getPrivate());
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load((InputStream) null, null);
        ks.setKeyEntry(SAML_KEY, pair.getPrivate(), KEYSTORE_PASSWORD
                .toCharArray(), new X509Certificate[] { cert });
        return ks;
	}

    private X509V3CertificateGenerator getX509Generator() {

        long now = System.currentTimeMillis() - 1000 * 60 * 10; // 10 minutos
        long l = now + 1000L * 60L * 60L * 24L * 366L * 20L; // 20 years
        X509V3CertificateGenerator generator = new X509V3CertificateGenerator();
        generator.setIssuerDN(new X509Name("CN=SOFFID-SAML-SP,O=Soffid"));
        generator.setNotAfter(new Date(l));
        generator.setNotBefore(new Date(now));
        generator.setSerialNumber(BigInteger.valueOf(now));
        generator.setSignatureAlgorithm("sha256WithRSAEncryption");
        return generator;
    }

    public X509Certificate createCertificate(PublicKey certificateKey,
            PrivateKey signerKey) throws CertificateEncodingException, InvalidKeyException,
            IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException,
            SignatureException {
        X509V3CertificateGenerator generator = getX509Generator();
        String name = "CN=SOFFID-SAML-SP,O=Soffid";
        generator.setSubjectDN(new X509Name(name));
        generator.setPublicKey(certificateKey);
        String algorithm = "SHA256WithRSA";
        generator.setSignatureAlgorithm(algorithm);
        
        X509Certificate cert = generator.generate(signerKey, "BC");

        return cert;
    }
    
    
    Map<String,AbstractReloadingMetadataResolver> resolver = new HashMap<String, AbstractReloadingMetadataResolver>();
    
    private EntityDescriptor getIdpMetadata (String tenant) throws ResolverException, InternalErrorException, ComponentInitializationException, MalformedURLException
    {
    	String metadataUrl = ConfigurationCache.getTenantProperty(tenant, "soffid.saml.metadata.url");
    	String metadataCache = ConfigurationCache.getTenantProperty(tenant, "soffid.saml.metadata.cache");
    	if (metadataCache == null) metadataCache="600";
    	String metadataIdp = ConfigurationCache.getTenantProperty(tenant, "soffid.saml.idp");
    	
    	if (metadataUrl == null)
    		throw new InternalErrorException("Metadata URL is not configured");
    	if (metadataIdp == null)
    		throw new InternalErrorException("Identity provider ID is not configured");
    	
    	String entryName = tenant + " " + metadataUrl + " " + metadataIdp;
    	AbstractReloadingMetadataResolver r = resolver.get(entryName);
    	if ( r == null)
    	{
    		r = configureMetadataResolver(metadataUrl, metadataCache);
    		resolver.put(entryName, r);
    	}
    	
    	CriteriaSet criteria = new CriteriaSet();
    	criteria.add( new EntityIdCriterion (metadataIdp));
		EntityDescriptor entity = r.resolveSingle(criteria );
    	return entity;
    }

	private AbstractReloadingMetadataResolver configureMetadataResolver(String metadataUrl, String metadataCache)
			throws ResolverException, ComponentInitializationException, MalformedURLException {

		if (metadataUrl.startsWith("java:")) {
			AbstractReloadingMetadataResolver r;
			try {
				r = (AbstractReloadingMetadataResolver) Class.forName(metadataUrl.substring(5)).newInstance();
			} catch (Exception e) {
				throw new MalformedURLException(metadataUrl);
			}
			r.setMinRefreshDelay(Long.parseLong(metadataCache) * 1000L);
			r.setId(metadataUrl);
			r.setResolveViaPredicatesOnly(true);
			r.setUseDefaultPredicateRegistry(true);
			BasicParserPool pool = new BasicParserPool();
			pool.initialize();
			r.setParserPool(pool);
			r.initialize();
			return r;
		}
		else if (metadataUrl.startsWith("http"))
		{
			HttpClient client = HttpClients.createDefault();
			HTTPMetadataResolver r = new HTTPMetadataResolver(client, metadataUrl);
			r.setMinRefreshDelay(Long.parseLong(metadataCache) * 1000L);
			r.setId(metadataUrl);
			r.setResolveViaPredicatesOnly(true);
			r.setUseDefaultPredicateRegistry(true);
			BasicParserPool pool = new BasicParserPool();
			pool.initialize();
			r.setParserPool(pool);
			r.initialize();
			return r;
		}
		else
		{
			String file = metadataUrl;
			if (file.startsWith("file:"))
				file = new URL(file).getFile();
			FilesystemMetadataResolver r = new FilesystemMetadataResolver(new File (file));
			r.setMinRefreshDelay(Long.parseLong(metadataCache) * 1000L);
			r.setId(metadataUrl);
			r.setResolveViaPredicatesOnly(true);
			r.setUseDefaultPredicateRegistry(true);
			BasicParserPool pool = new BasicParserPool();
			pool.initialize();
			r.setParserPool(pool);
			r.initialize();
			return r;
		}
		
	}
    
    Log log = LogFactory.getLog(getClass());
    
    private boolean validateAssertion (String hostName, Assertion assertion, boolean responseSigned) throws ResolverException, InternalErrorException, ComponentInitializationException, AssertionValidationException, CertificateException, MalformedURLException
    {
    	SAML20AssertionValidator validator = getValidator(hostName);
    	
    	HashMap<String, Object> params = new HashMap<String, Object>();
    	HashSet<String> names = new HashSet<String>();
    	names.add(getEntityId(hostName, true));
    	names.add(getEntityId(hostName, false));
    	params.put(
                SAML2AssertionValidationParameters.COND_VALID_AUDIENCES, names);
    	Set<String> set = new HashSet<String>();
    	set.add(getBaseURL(hostName)+"saml/log/post");
    	set.add(getBaseURL(hostName)+"saml/log/simple-post");
    	set.add(getBaseURL(hostName)+"selfservice/saml/log/post");
    	set.add(getBaseURL(hostName)+"selfservice/saml/log/simple-post");
    	set.add(getBaseURL(hostName)+"saml/slo/soap");
    	set.add(getBaseURL(hostName)+"saml/slo/post");
    	set.add(getBaseURL(hostName)+"saml/slo/redirect");
    	set.add(getBaseURL(hostName)+"selfservice/saml/slo/soap");
    	set.add(getBaseURL(hostName)+"selfservice/saml/slo/post");
    	set.add(getBaseURL(hostName)+"selfservice/saml/slo/redirect");
    	params.put (SAML2AssertionValidationParameters.SC_VALID_RECIPIENTS, set);

    	if (responseSigned)
    		params.put(SAML2AssertionValidationParameters.SIGNATURE_REQUIRED, Boolean.FALSE);

    	org.opensaml.saml.common.assertion.ValidationContext ctx = new ValidationContext(params);

    	ValidationResult result = validator.validate(assertion, ctx);
		if (result != ValidationResult.VALID)
			log.info("Error validating Assertion: "+ctx.getValidationFailureMessage());
		
		if ( ! validDate (assertion.getIssueInstant()))
			return false;

		return result == ValidationResult.VALID ;
    	
    }

	private boolean validDate(DateTime issueInstant) {
		if (issueInstant == null)
		{
			log.info("Error validatig assertion: issueInstant is null");
			return false;
		}
		Calendar c = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		c.set(Calendar.YEAR, issueInstant.getYear());
		c.set(Calendar.MONTH, issueInstant.getMonthOfYear()-1);
		c.set(Calendar.DAY_OF_MONTH, issueInstant.getDayOfMonth());
		c.set(Calendar.HOUR_OF_DAY, issueInstant.getHourOfDay());
		c.set(Calendar.MINUTE, issueInstant.getMinuteOfHour());
		c.set(Calendar.SECOND, issueInstant.getSecondOfMinute());
		c.set(Calendar.MILLISECOND, issueInstant.getSecondOfMinute());
		
		// Test if issue instant is five minutes after now (allow 5 minutes time skew)
		Calendar now = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		now.add(Calendar.MINUTE, 5);
		if (c.after( now ))
		{
			log.info("Error validatig assertion: issueInstant is after current instant");
			return false;
		}
		
		// Test if issue instant is ten minutes before now (allow 5 minutes for assertion to travel plus 5 minutes time skew)
		now = new GregorianCalendar();
		now.add(Calendar.MINUTE, -10);
		if (c.before( now ))
		{
			log.info("Error validatig assertion: issueInstant is more than ten minutes old");
			return false;
		}

		return true;
	}

	private SAML20AssertionValidator getValidator(String tenant) throws ResolverException, InternalErrorException, ComponentInitializationException, CertificateException, MalformedURLException {
		EntityDescriptor md = getIdpMetadata(tenant);

		List<ConditionValidator> conditionValidators = new LinkedList<ConditionValidator>();
		conditionValidators.add( new AudienceRestrictionConditionValidator() );
		
    	List<SubjectConfirmationValidator> subjectConfirmationValidators = new LinkedList<SubjectConfirmationValidator>();
    	List<StatementValidator> statementValidators = new LinkedList<StatementValidator>();
    	Set<Credential> trustedCredentials = new HashSet<Credential>();
    	
    	IDPSSODescriptor idp = md.getIDPSSODescriptor(SAMLConstants.SAML20P_NS);
    	if (idp != null)
    	{
    		for (KeyDescriptor kd: idp.getKeyDescriptors())
    		{
    			if (kd.getUse() == UsageType.SIGNING || 
    					kd.getUse() == UsageType.UNSPECIFIED)
    			{
	    			for (X509Data x509data: kd.getKeyInfo().getX509Datas())
	    			{
						for (org.opensaml.xmlsec.signature.X509Certificate certElement: x509data.getX509Certificates())
	    				{
							CertificateFactory factory = CertificateFactory.getInstance("X.509");
							X509Certificate cert = (X509Certificate) 
								factory.generateCertificate( new ByteArrayInputStream(
									Base64.decode(certElement.getValue())
								));
	    					BasicX509Credential cred = new BasicX509Credential(cert);
	    					cred.setEntityId(md.getEntityID());
	    					cred.setUsageType(kd.getUse());
							trustedCredentials.add( cred);
	    				}
	    			}
    			}
    		}
    	}
    	CollectionCredentialResolver credentialResolver  = new CollectionCredentialResolver(trustedCredentials);
    	SignatureTrustEngine signatureTrustEngine;
    	SignaturePrevalidator signaturePrevalidator;
    	  
		subjectConfirmationValidators.add( new CustomSubjectConfirmationValidator());
    	signatureTrustEngine = new ExplicitKeySignatureTrustEngine(credentialResolver, DefaultSecurityConfigurationBootstrap.buildBasicInlineKeyInfoCredentialResolver());
    	signaturePrevalidator = new SAMLSignatureProfileValidator();
    	
    	return new SAML20AssertionValidator(conditionValidators, subjectConfirmationValidators, statementValidators, signatureTrustEngine, signaturePrevalidator);
	}

    private boolean validateResponse (String tenant, Response assertion) throws ResolverException, InternalErrorException, ComponentInitializationException, AssertionValidationException, CertificateException, MalformedURLException
    {
    	SAML20ResponseValidator validator = getResponseValidator(tenant);
    	
    	org.opensaml.saml.common.assertion.ValidationContext ctx = new ValidationContext();
    	
		ValidationResult result = validator.validate(assertion, ctx);
		if (result != ValidationResult.VALID)
			log.info("Error validating SAML message: "+ctx.getValidationFailureMessage());
		
		if ( ! validDate (assertion.getIssueInstant()))
			return false;
		
		if ( assertion.getStatus() == null || assertion.getStatus().getStatusCode() == null)
		{
			log.info("Response does not contain status");
			return false;
		}
		
		if ( ! assertion.getStatus().getStatusCode().getValue().equals(StatusCode.SUCCESS))
		{
			log.info("Authentication failed: "+assertion.getStatus().getStatusCode().getValue());
			return false;
		}

    	return result == ValidationResult.VALID ;
    	
    }

    private SAML20ResponseValidator getResponseValidator(String tenant) throws ResolverException, InternalErrorException, ComponentInitializationException, CertificateException, MalformedURLException {
		EntityDescriptor md = getIdpMetadata(tenant);

		List<ConditionValidator> conditionValidators = new LinkedList<ConditionValidator>();
    	List<SubjectConfirmationValidator> subjectConfirmationValidators = new LinkedList<SubjectConfirmationValidator>();
    	List<StatementValidator> statementValidators = new LinkedList<StatementValidator>();
    	Set<Credential> trustedCredentials = new HashSet<Credential>();
    	
    	IDPSSODescriptor idp = md.getIDPSSODescriptor(SAMLConstants.SAML20P_NS);
    	if (idp != null)
    	{
    		for (KeyDescriptor kd: idp.getKeyDescriptors())
    		{
    			if (kd.getUse() == UsageType.SIGNING || 
    					kd.getUse() == UsageType.UNSPECIFIED)
    			{
	    			for (X509Data x509data: kd.getKeyInfo().getX509Datas())
	    			{
						for (org.opensaml.xmlsec.signature.X509Certificate certElement: x509data.getX509Certificates())
	    				{
							CertificateFactory factory = CertificateFactory.getInstance("X.509");
							X509Certificate cert = (X509Certificate) 
								factory.generateCertificate( new ByteArrayInputStream(
									Base64.decode(certElement.getValue())
								));
	    					BasicX509Credential cred = new BasicX509Credential(cert);
	    					cred.setEntityId(md.getEntityID());
	    					cred.setUsageType(kd.getUse());
							trustedCredentials.add( cred);
	    				}
	    			}
    			}
    		}
    	}
    	CollectionCredentialResolver credentialResolver  = new CollectionCredentialResolver(trustedCredentials);
    	SignatureTrustEngine signatureTrustEngine;
    	SignaturePrevalidator signaturePrevalidator;
    	  
    	subjectConfirmationValidators.add(new CustomSubjectConfirmationValidator());
    	signatureTrustEngine = new ExplicitKeySignatureTrustEngine(credentialResolver, DefaultSecurityConfigurationBootstrap.buildBasicInlineKeyInfoCredentialResolver());
    	signaturePrevalidator = new SAMLSignatureProfileValidator();

    	return new SAML20ResponseValidator(conditionValidators, subjectConfirmationValidators, statementValidators, signatureTrustEngine, signaturePrevalidator);
	}

	public List<String> findIdentityProviders() throws InternalErrorException, ResolverException, ComponentInitializationException, MalformedURLException {
    	String metadataUrl = ConfigurationCache.getProperty("soffid.saml.metadata.url");
    	String metadataCache = ConfigurationCache.getProperty("soffid.saml.metadata.cache");
    	if (metadataCache == null) metadataCache="600";
    	
    	if (metadataUrl == null)
    		throw new InternalErrorException("Metadata URL is not configured");
    	
    	String tenant = com.soffid.iam.utils.Security.getCurrentTenantName();

    	AbstractReloadingMetadataResolver r = configureMetadataResolver(metadataUrl, metadataCache);

    	CriteriaSet criteria = new CriteriaSet();
    	String metadataIdp = ConfigurationCache.getProperty("soffid.saml.idp");
    	criteria.add( new EvaluableEntityDescriptorCriterion() {
			@Override
			public boolean apply(EntityDescriptor input) {
				return input.getIDPSSODescriptor(SAMLConstants.SAML20P_NS) != null;
			}
		});
		List<String> ids = new LinkedList<String>();
		for (EntityDescriptor ed: r.resolve(criteria ))
		{
			ids.add(ed.getEntityID());
		}
		r.destroy();
    	return ids;
	}
	
	public List<String> findIdentityProviders(String url) throws InternalErrorException, ResolverException, ComponentInitializationException, MalformedURLException {
		AbstractReloadingMetadataResolver r = configureMetadataResolver(url, "5");
    	
    	CriteriaSet criteria = new CriteriaSet();
    	criteria.add( new EvaluableEntityDescriptorCriterion() {
			@Override
			public boolean apply(EntityDescriptor input) {
				return input.getIDPSSODescriptor(SAMLConstants.SAML20P_NS) != null;
			}
		});
		List<String> ids = new LinkedList<String>();
		for (EntityDescriptor ed: r.resolve(criteria ))
		{
			ids.add(ed.getEntityID());
		}
		r.destroy();
		
    	return ids;
	}

	public String validateOpenidToken(String token) throws JSONException, MalformedURLException, NoSuchAlgorithmException, InvalidKeySpecException, CertificateException, IOException, InternalErrorException {
		HashMap<String, Algorithm> conf = readConfiguration();
		if (conf == null)
			return null;
		DecodedJWT jwt = JWT.decode(token);
		if (jwt.getExpiresAt() != null && jwt.getExpiresAt().before(new Date())) {
			log.info("Received expired token "+token);
		}
		String issuer = ConfigurationCache.getProperty("soffid.webservice.auth.jwt-iss");
		if (issuer != null && !issuer.isEmpty() && !issuer.equals(jwt.getIssuer()))
		{
			log.info("Received token from wrong issuer "+jwt.getIssuer());
		}
		List<String> audiences = getAudiences();
		if (! audiences.isEmpty() && intersectionEmpty(audiences, jwt.getAudience())) {
			log.info("Received token from wrong audience "+jwt.getAudience());
		}
		for (Entry<String, Algorithm> entry: conf.entrySet()) {
			String name = entry.getKey();
			String keyName = jwt.getKeyId();
			if (keyName == null || keyName.equals(name)) {
				try {
					entry.getValue().verify(jwt);
					return Security.getCurrentTenantName()+"\\"+jwt.getSubject();
				} catch (SignatureVerificationException e) 
				{
					// Invalid signature
				}
			}
		}
		return null;
	}

	private boolean intersectionEmpty(List<String> audiences1, List<String> audiences2) {
		for (String aud: audiences1)
			if (audiences2.contains(aud))
				return false;
		return true;
	}

	private List<String> getAudiences() throws InternalErrorException {
		List<String> l = new LinkedList<>();
		for (Configuration cfg: ServiceLocator.instance().getConfigurationService().findConfigurationByFilter(
				"soffid.webservice.auth.jwt-aud%", 
				null, null, null)) {
			if (cfg.getValue() != null && !cfg.getValue().trim().isBlank())
				l.add(cfg.getValue());
		}
		return l;
	}

	class TenantConfig {
		HashMap<String, Algorithm> algorithms =null;
		String lastUrl = null;
	}
	
	Map<String,TenantConfig> tenantConfig = new Hashtable<>();
	
	private HashMap<String, Algorithm> readConfiguration() throws JSONException, MalformedURLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, CertificateException {
		String url = ConfigurationCache.getProperty("soffid.webservice.auth.jwt-conf-url");
		if (url == null)
			return null;
		
		TenantConfig cfg = tenantConfig.get(Security.getCurrentTenantName());
		
		if (cfg != null && url.equals(cfg.lastUrl))
			return cfg.algorithms;
		
		cfg = new TenantConfig();
		tenantConfig.put(Security.getCurrentTenantName(), cfg);
		
		JSONObject o = new JSONObject(new JSONTokener(new URL(url).openStream()));
		cfg.algorithms = new HashMap<>();
		
		JSONArray a = o.optJSONArray("keys");
		if (a != null) {
			for (int i = 0; i < a.length(); i++) {
				JSONObject key = a.getJSONObject(i);
				Algorithm alg = loadAlgorithm(key);
				String name = key.optString("kid", "");
				if (alg != null)
					cfg.algorithms.put(name, alg);
			}
		}
		
		cfg.lastUrl = url;
		return cfg.algorithms;
	}

	private Algorithm loadAlgorithm(JSONObject key) throws NoSuchAlgorithmException, InvalidKeySpecException, CertificateException, JSONException {
		String alg = key.optString("alg");
		if ("RS256".equals(alg)) {
			PublicKey pub = parseRsaKey(key);
			if (pub == null)
				return null;
			return Algorithm.RSA256((RSAPublicKey) pub, null);
		}
		if ("RS384".equals(alg)) {
			PublicKey pub = parseRsaKey(key);
			if (pub == null)
				return null;
			return Algorithm.RSA384((RSAPublicKey) pub, null);
		}
		if ("RS512".equals(alg)) {
			PublicKey pub = parseRsaKey(key);
			if (pub == null)
				return null;
			return Algorithm.RSA512((RSAPublicKey) pub, null);
		}
		if ("ES256".equals(alg)) {
			PublicKey pub = parseEcKey(key);
			if (pub == null)
				return null;
			return Algorithm.ECDSA256((ECPublicKey) pub, null);
		}
		if ("ES384".equals(alg)) {
			PublicKey pub = parseEcKey(key);
			if (pub == null)
				return null;
			return Algorithm.ECDSA384((ECPublicKey) pub, null);
		}
		if ("ES512".equals(alg)) {
			PublicKey pub = parseEcKey(key);
			if (pub == null)
				return null;
			return Algorithm.ECDSA512((ECPublicKey) pub, null);
		}
		return null;
	}

	protected PublicKey parseRsaKey(JSONObject key) throws NoSuchAlgorithmException, InvalidKeySpecException, CertificateException, JSONException {
		if (key.has("n") && key.has("e")) {
			BigInteger exp = parseBigInteger(key.getString("e"));
			BigInteger modulus = parseBigInteger(key.getString("n"));
			RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exp);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			PublicKey pub = factory.generatePublic(spec);
			return pub;
		} else if (key.has("x5c")) {
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate)certFactory
					.generateCertificate(
							new ByteArrayInputStream(
									java.util.Base64.getUrlDecoder().decode(
											key.getString("x5c"))));
			return cert.getPublicKey();
		} else {
			return null;
		}
	}

	protected PublicKey parseEcKey(JSONObject key) throws NoSuchAlgorithmException, InvalidKeySpecException, CertificateException, JSONException {
		if (key.has("x5c")) {
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate)certFactory
					.generateCertificate(
							new ByteArrayInputStream(
									java.util.Base64.getUrlDecoder().decode(
											key.getString("x5c"))));
			return cert.getPublicKey();
		} else {
			return null;
		}
	}

	private BigInteger parseBigInteger(String string) {
		byte data[] = java.util.Base64.getUrlDecoder().decode(string);
		BigInteger b = new BigInteger(+1, data);
		return b;
	}
}
