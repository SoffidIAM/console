package com.soffid.iam.remote;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.ssl.ConnectionFactory;
import com.soffid.iam.ssl.HttpInvokerHandler;

import es.caib.seycon.util.Base64;

public class RemoteInvokerFactory {
    public Object getInvoker(URL url) throws IOException {
        return getInvoker (url, null, null);
    }
    
    public Object getInvoker(URL url, String authToken) throws IOException {
    	return getInvoker(url, null, authToken);
    }
    
    public Object getInvoker(URL url, String tenantName, String authToken) throws IOException {
        HttpURLConnection c;
        // Cambiar la factoria SSL
        try {

            c = ConnectionFactory.getConnection(url);

            c.setDoInput(true);
            c.setDoOutput(false);
            c.setAllowUserInteraction(false);
            if (authToken != null) {
                String seu = "-seu-"+tenantName+":"+authToken; //$NON-NLS-1$
                byte bytes[] = seu.getBytes("UTF-8"); //$NON-NLS-1$
                String tag = "Basic "+ Base64.encodeBytes(bytes, 0, bytes.length, Base64.DONT_BREAK_LINES); //$NON-NLS-1$
                c.addRequestProperty("Authorization", tag); //$NON-NLS-1$
                
            }
            Locale locale = MessageFactory.getLocale();
            if (locale != null)
            	c.addRequestProperty("Accept-Language", locale.toString());
            try {
            	c.connect();
            } catch (IOException e) {
            	try {
            		ConnectionFactory.reloadKeys();
            	} catch (Exception e2) {}
            	throw e;
            }
            
            // Consumir el stream
            InputStream in = c.getInputStream();
            while (in.read() >= 0);
            
            String classes = c.getHeaderField("Classes"); //$NON-NLS-1$
            if (classes == null)
                throw new IOException("Invalid response from " + url); //$NON-NLS-1$
            String[] split = classes.split("[, ]+"); //$NON-NLS-1$
            Class[] interfaces = new Class[split.length];
            for (int i = 0; i < split.length; i++) {
            	try {
	                interfaces[i] = Class.forName(split[i], false, 
	                		RemoteInvokerFactory.class.getClassLoader());
            	} catch (ClassNotFoundException e) {
	                interfaces[i] = Class.forName(split[i], false, 
	                		Thread.currentThread().getContextClassLoader());
            	}
            }
            HttpInvokerHandler invoker = new HttpInvokerHandler(url, tenantName, authToken);
            invoker.setHeadersFactory(headersFactory);
            
			return Proxy.newProxyInstance(RemoteInvokerFactory.class.getClassLoader(),
                    interfaces, invoker);
        } catch (IllegalArgumentException e) {
            throw new RemoteException ("Nested exception", e); //$NON-NLS-1$
        } catch (ClassNotFoundException e) {
            throw new RemoteException ("Nested exception", e); //$NON-NLS-1$
        }
    }

    public static List<HeadersFactory> headersFactory = new LinkedList<HeadersFactory>();
    public static void addHeadersFactory(HeadersFactory f)
    {
    	headersFactory.add(f);
    }
    public static final List<HeadersFactory> getHeadersFactory()
    {
    	return headersFactory;
    }
}
