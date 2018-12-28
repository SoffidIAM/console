package es.caib.seycon.ng.test;

import java.net.InetAddress;

import com.soffid.iam.api.SamlRequest;
import com.soffid.iam.service.SamlService;
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.ServiceLocator;
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
