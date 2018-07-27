package es.caib.seycon.ng.test;

import java.math.BigInteger;
import java.net.InetAddress;
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
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.DominiContrasenya;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;
import es.caib.seycon.util.Base64;

public class SAMLTest extends AbstractTest
{
	public void testSAMLRequest() throws Exception
	{
		if (true) return; // temporary disabled
		Security.nestedLogin("Test", new String[] {Security.AUTO_AUTHORIZATION_ALL});
		try {
			String hostName = InetAddress.getLocalHost().getHostName();
			ConfigurationCache.setProperty("soffid.saml.metadata.url", "https://iam.soffid.com:760/SAML/metadata.xml");
			ConfigurationCache.setProperty("soffid.saml.idp", "https://www.soffid.com/soffid-idp");
			SamlService svc = ServiceLocator.instance().getSamlService();
			
			System.out.println("Identity providers:");
			for (String s: svc.findIdentityProviders())
			{
				System.out.println(s);
			}
			String metadata = svc.generateMetadata(hostName);
			System.out.println(metadata);
			SamlRequest r = svc.generateSamlRequest(hostName, "/");
			System.out.println (r);
			System.out.println(new String(Base64.decode(r.getParameters().get("SAMLRequest"))));
			System.out.println(new String(Base64.decode(r.getParameters().get("SAMLRequest"))));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			Security.nestedLogoff();
		} 
	}

}
