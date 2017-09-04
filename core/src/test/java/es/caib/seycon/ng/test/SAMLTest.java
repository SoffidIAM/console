package es.caib.seycon.ng.test;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
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
import java.security.cert.X509Certificate;
import java.util.Date;

import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import com.soffid.iam.api.SamlRequest;
import com.soffid.iam.service.SamlService;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.DominiContrasenya;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;
import es.caib.seycon.ssl.SeyconKeyStore;
import es.caib.seycon.util.Base64;

public class SAMLTest extends AbstractTest
{
	public void testSAMLRequest() throws Exception
	{
		Security.nestedLogin("Test", new String[] {Security.AUTO_AUTHORIZATION_ALL});
		try {
			System.setProperty("soffid.saml.metadata.url", "https://iam.soffid.com:760/SAML/metadata.xml");
			System.setProperty("soffid.saml.idp", "https://www.soffid.com/soffid-idp");
			SamlService svc = ServiceLocator.instance().getSamlService();
			
			String metadata = svc.generateMetadata();
			System.out.println(metadata);
			SamlRequest r = svc.generateSamlRequest();
			System.out.println (r);
			System.out.println(new String(Base64.decode(r.getParameters().get("SAMLRequest"))));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			Security.nestedLogoff();
		} 
	}

}
