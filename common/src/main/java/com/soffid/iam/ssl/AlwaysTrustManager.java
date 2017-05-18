/*
 * Created on 24-ago-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.soffid.iam.ssl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.seycon.util.Base64;

public class AlwaysTrustManager implements X509TrustManager {

    private boolean debug;

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
    	if (debug)
    	{
    		Log log = LogFactory.getLog(getClass());
    		log.info("Received certificate");
    		for (X509Certificate cert: arg0)
    		{
    			StringBuffer b = new StringBuffer();
    			b.append ("-----BEGIN CERTIFICATE----- \r\n")
    				.append(Base64.encodeBytes(cert.getEncoded()))
    				.append("-----END CERTIFICATE-----");
    			log.info(cert.getSubjectDN().getName()+"\r\n"+
    				b.toString());
    		}
   
    	}
    	return ;
    }

    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
        throw new CertificateException("No allowed to use client certificates");
    }

    public AlwaysTrustManager() throws KeyStoreException,
            NoSuchAlgorithmException, CertificateException,
            FileNotFoundException, IOException {
    	debug = "true".equals(System.getProperty("com.soffid.ssl.dump"));
    }


}
