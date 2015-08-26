package com.soffid.iam.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class SeyconHostnameVerifier implements HostnameVerifier {

    public boolean verify(String arg0, SSLSession arg1) {
        System.out.println (String.format(Messages.getString("SeyconHostnameVerifier.ArgExpectedInfo"), arg0));  //$NON-NLS-1$
        try {
            System.out.println(String.format(Messages.getString("SeyconHostnameVerifier.ArgFoundInfo"), arg1.getPeerCertificates()[0].toString())); //$NON-NLS-1$
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
