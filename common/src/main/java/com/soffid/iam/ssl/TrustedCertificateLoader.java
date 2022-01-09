package com.soffid.iam.ssl;

import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.List;

public interface TrustedCertificateLoader {
	public List<X509Certificate> loadCerts(KeyStore ks) throws IOException;
}
