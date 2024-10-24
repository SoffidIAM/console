package com.soffid.iam.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Random;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.soffid.iam.api.Password;
import com.soffid.iam.config.Config;

import es.caib.seycon.ng.exception.InternalErrorException;

public class SeyconKeyStore {
    
    public static KeyStore loadKeyStore (File file) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException
    {
        Security.addProvider(new BouncyCastleProvider());
        Password password = getKeyStorePassword();
        
        KeyStore ks = KeyStore.getInstance(getKeyStoreType());
        if (file == null)
        {
            ks.load((InputStream) null, null);
        }
        else if ( ! file.isFile())
        {
            ks.load((InputStream) null, null);
            ks.store( new FileOutputStream ( file ), password.getPassword().toCharArray());
        }
        else
        {
            ks.load(new FileInputStream (file), password.getPassword().toCharArray());
        }
        return ks;
    }

    public static Password getKeyStorePassword() throws FileNotFoundException, IOException {
        Config config = Config.getConfig();
        Password password = config.getSSLKey();
        if ( password == null )
            // Generar clave SSL
            {
                String sslKey = ""; //$NON-NLS-1$
                Random r = new Random ();
                for ( int i = 0; i < 32; i ++)
                {
                        int c = r.nextInt(94);
                        sslKey = sslKey + (char) ('!'+c);
                }
                password = new Password (config.getHostName() + sslKey);
                config.setSSLKey(password);
            }
        return password;
    }

    public static String getKeyStoreType() {
        return "JKS"; //$NON-NLS-1$
    }

    public static File getKeyStoreFile() throws IOException {
        Config config = Config.getConfig();
        if (config.getRole() != null && !"client".equals(config.getRole()))
            return new File (config.getHomeDir(), "conf/keystore.jks"); //$NON-NLS-1$
        else
            return null;
    }

    public static File getRootKeyStoreFile() throws IOException, InternalErrorException {
        Config config = Config.getConfig();
        File rootKeystore = new File (config.getHomeDir(), "conf/root.jks");
        if (rootKeystore.canRead())
			return rootKeystore;
		else
            return null;
    }

    public static void saveKeyStore (KeyStore ks, File file) throws FileNotFoundException, IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException
    {
        if (file != null)
        {
        	File old = new File (file.getPath()+".5");
        	if ( old.canRead() )
        		old.delete();
        	for (int i = 4; i >= 1; i--) {
        		File f = new File(file.getPath()+"."+i);
        		if (f.canRead()) 
        			f.renameTo(old);
        		old = f;
        	}
        	if (file.canRead())
        		file.renameTo(old);
            Password password = getKeyStorePassword();
            ks.store( new FileOutputStream ( file ), password.getPassword().toCharArray());
            ConnectionFactory.reloadKeys();
        }
    }

    public static final String ROOT_KEY = "rootKey"; //$NON-NLS-1$
    public static final String MY_KEY = "myKey"; //$NON-NLS-1$
    public static final String ROOT_CERT = "rootCert"; //$NON-NLS-1$
	public static final String NEXT_CERT = "nextCert";
}
