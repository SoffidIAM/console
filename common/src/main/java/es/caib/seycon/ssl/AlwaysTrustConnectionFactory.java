package es.caib.seycon.ssl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;


public class AlwaysTrustConnectionFactory {
    private static SSLSocketFactory sslFactory = null;
    private static void init() throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException {
        SSLContext ctx;
        ctx = SSLContext.getInstance("TLS"); //$NON-NLS-1$

        ctx.init(new KeyManager[0], new TrustManager[] { new AlwaysTrustManager() }, null);

        sslFactory = ctx.getSocketFactory();

    }

    public static SSLSocketFactory getSocketFactory() throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException {
        if (sslFactory == null) {
            init ();
        }
        return sslFactory;
            
    }

    public static URLConnection getConnection(URL url)
            throws RemoteException {
        try {
            if (sslFactory == null) {
                init();
            }

            URLConnection urlC = url.openConnection();
            if (urlC instanceof HttpsURLConnection)
            {
	            HttpsURLConnection sslC = (HttpsURLConnection) urlC;
	
	            sslC.setSSLSocketFactory(sslFactory);
            }

            return urlC;
        } catch (Exception e) {
            throw new RemoteException(Messages.getString("ConnectionFactory.NotSSLInitialized"), e); //$NON-NLS-1$
        }

    }
}
