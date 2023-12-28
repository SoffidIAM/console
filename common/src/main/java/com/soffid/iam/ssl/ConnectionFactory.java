package com.soffid.iam.ssl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.soffid.iam.ssl.SeyconTrustManager;

import com.soffid.iam.api.Password;


public class ConnectionFactory {
    private static SSLSocketFactory sslFactory = null;
    private static TrustedCertificateLoader loader = null;
    
    public static void setTrustedCertificateLoader(TrustedCertificateLoader loader) {
    	ConnectionFactory.loader = loader;
    }
    
    private static KeyManager[] getKeyManagers(KeyStore ks)
            throws KeyStoreException,
            NoSuchAlgorithmException, UnrecoverableKeyException, FileNotFoundException, IOException {
        Password password = SeyconKeyStore.getKeyStorePassword();

        X509Certificate myKey = (X509Certificate) ks
                .getCertificate(SeyconKeyStore.MY_KEY);
        if (myKey == null) {
            return new KeyManager[0];
        } else {

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509"); //$NON-NLS-1$
            kmf.init(ks, password.getPassword().toCharArray());
            return kmf.getKeyManagers();
        }

    }

    private static void init() throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException {
        File file = SeyconKeyStore.getKeyStoreFile();
        KeyStore ks = SeyconKeyStore.loadKeyStore(file);

        if (loader != null) {
        	synchronized (loader) {
                ks = SeyconKeyStore.loadKeyStore(file);
	        	List<X509Certificate> certs = loader.loadCerts(ks);
	        	if (certs != null && anyChange (certs, ks)) {
	    			for (Enumeration<String> e =  ks.aliases(); e.hasMoreElements();) {
	    				String alias = e.nextElement();
	    				if (alias.startsWith("trusted-")) {
	    					ks.deleteEntry(alias);
	    				}
	    			}
	    			int i = 0;
	    			for (X509Certificate trustedCert: certs) {
	    				ks.setCertificateEntry("trusted-"+i, trustedCert);
	    				i ++;
	    			}
	    			if (file != null) {
	    				Password password = SeyconKeyStore.getKeyStorePassword();
	    				SeyconKeyStore.saveKeyStore(ks, file);
	    			}
	        	}
        	}
        }
        for (Enumeration<String> e = ks.aliases(); e.hasMoreElements(); ) {
        	String key = e.nextElement();
        	if ( !key.equalsIgnoreCase(SeyconKeyStore.MY_KEY) && ks.isKeyEntry(key) )
        		ks.deleteEntry(key);
        }
        SSLContext ctx;
        ctx = SSLContext.getInstance("TLS"); //$NON-NLS-1$

        ctx.init(getKeyManagers(ks), getTrustManagers(ks), null);

        sslFactory = ctx.getSocketFactory();
    }

    private static boolean anyChange(List<X509Certificate> certs, KeyStore ks) throws KeyStoreException {
    	certs = new LinkedList<>(certs);
		for (Enumeration<String> e =  ks.aliases(); e.hasMoreElements();) {
			String alias = e.nextElement();
			if (alias.startsWith("trusted-")) {
				Certificate cert = ks.getCertificate(alias);
				if (! certs.contains(cert))
					return true;
				else
					certs.remove(cert);
			}
		}
		return ! certs.isEmpty();
	}

	public static void reloadKeys () throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException
    {
    	init ();
    }
    
    private static TrustManager[] getTrustManagers(KeyStore ks)
            throws KeyStoreException, NoSuchAlgorithmException,
            CertificateException, FileNotFoundException, IOException {
        return new TrustManager[] { new SeyconTrustManager(ks) };
    }
    
    public static SSLSocketFactory getSocketFactory() throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException {
        if (sslFactory == null) {
            init ();
        }
        return sslFactory;
            
    }

    public static HttpURLConnection getConnection(URL url)
            throws RemoteException {
        try {
            if (sslFactory == null) {
                init();
            }

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn instanceof HttpsURLConnection) {
	            HttpsURLConnection sslC = (HttpsURLConnection) conn;
	
	            sslC.setSSLSocketFactory(sslFactory);
            }
	        return conn;
        } catch (Exception e) {
            throw new RemoteException(Messages.getString("ConnectionFactory.NotSSLInitialized"), e); //$NON-NLS-1$
        }

    }
}
