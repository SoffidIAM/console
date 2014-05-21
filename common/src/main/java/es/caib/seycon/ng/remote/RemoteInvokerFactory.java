package es.caib.seycon.ng.remote;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import es.caib.seycon.ng.comu.lang.MessageFactory;
import es.caib.seycon.ssl.ConnectionFactory;
import es.caib.seycon.ssl.HttpInvokerHandler;
import es.caib.seycon.util.Base64;

public class RemoteInvokerFactory {
    public Object getInvoker(URL url) throws IOException {
        return getInvoker (url, null);
    }
    public Object getInvoker(URL url, String authToken) throws IOException {
        HttpsURLConnection c;
        // Cambiar la factoria SSL
        try {

            c = ConnectionFactory.getConnection(url);

            c.setDoInput(true);
            c.setDoOutput(false);
            c.setAllowUserInteraction(false);
            if (authToken != null) {
                String seu = "-seu-:"+authToken; //$NON-NLS-1$
                byte bytes[] = seu.getBytes("UTF-8"); //$NON-NLS-1$
                String tag = "Basic "+ Base64.encodeBytes(bytes, 0, bytes.length, Base64.DONT_BREAK_LINES); //$NON-NLS-1$
                c.addRequestProperty("Authorization", tag); //$NON-NLS-1$
                
            }
            Locale locale = MessageFactory.getLocale();
            if (locale != null)
            	c.addRequestProperty("Accept-Language", locale.toString());
            c.connect();
            
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
            return Proxy.newProxyInstance(RemoteInvokerFactory.class.getClassLoader(),
                    interfaces, new HttpInvokerHandler(url, authToken));
        } catch (IllegalArgumentException e) {
            throw new RemoteException ("Nested exception", e); //$NON-NLS-1$
        } catch (ClassNotFoundException e) {
            throw new RemoteException ("Nested exception", e); //$NON-NLS-1$
        }
    }

}
