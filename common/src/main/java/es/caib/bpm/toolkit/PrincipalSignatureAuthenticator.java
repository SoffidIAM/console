package es.caib.bpm.toolkit;

import java.security.Principal;


public interface PrincipalSignatureAuthenticator {
	public boolean verifySignerIsPrincipal(Principal principal, byte[] pkcs7) throws Exception;
}
