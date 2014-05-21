/*
 * Created on 24-ago-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package es.caib.seycon.ssl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

public class AlwaysTrustManager implements X509TrustManager {

    /*
     * (non-Javadoc)
     * 
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    public X509Certificate[] getAcceptedIssuers() {
           return new X509Certificate[0];

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[],
     *      java.lang.String)
     */
    public void checkServerTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
    	return ;
    }

    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
        throw new CertificateException("No allowed to use client certificates");
    }

    public AlwaysTrustManager() throws KeyStoreException,
            NoSuchAlgorithmException, CertificateException,
            FileNotFoundException, IOException {
    }


}
