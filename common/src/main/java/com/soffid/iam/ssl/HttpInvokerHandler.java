package com.soffid.iam.ssl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.mortbay.jetty.HttpHeaders;

import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.remote.HeadersFactory;
import com.soffid.iam.remote.RemoteInvokerFactory;

import es.caib.seycon.util.Base64;

public class HttpInvokerHandler implements InvocationHandler {
    private URL url;
    private String authToken;
	private String tenantName;
	private List<HeadersFactory> headersFactory = null;
    
    public HttpInvokerHandler (URL url, String tenantName, String authToken)
    {
        this.url = url;
        this.tenantName = tenantName;
        this.authToken = authToken;
    }
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        Object result = null;
        HttpsURLConnection c;
        try {
            
            // Cambiar la factoria SSL
            c = ConnectionFactory.getConnection(url);
            
            c.setDoInput(true);
            c.setDoOutput(true);
            c.setRequestMethod("POST"); //$NON-NLS-1$
            if (authToken != null) {
                String seu = "-seu-";  //$NON-NLS-1$
                if (tenantName != null)
                	seu = seu + URLEncoder.encode(tenantName, "UTF-8");
                seu = seu + ":" + authToken;
                byte bytes[] = seu.getBytes("UTF-8"); //$NON-NLS-1$
                String tag = "Basic "+ Base64.encodeBytes(bytes, 0, bytes.length, Base64.DONT_BREAK_LINES);  //$NON-NLS-1$
                c.addRequestProperty("Authorization", tag); //$NON-NLS-1$
                
            }
            if (headersFactory != null)
            {
            	for ( HeadersFactory h: headersFactory)
            		h.addHeaders(c);
            }
            
            Locale locale = MessageFactory.getLocale();
            c.addRequestProperty (HttpHeaders.ACCEPT_LANGUAGE, locale.toString() );
            
            c.connect();
            ObjectOutputStream oout = new ObjectOutputStream (c.getOutputStream());

            oout.writeUTF(method.getName());
            
            int len  = args == null? 0 : args.length;
            oout.writeInt(len);
            for ( int i = 0; i < len; i++)
            {
                Class clazz = method.getParameterTypes()[i];
                oout.writeUTF(clazz.getName());
            }
            
            for ( int i = 0; i < len; i++)
            {
                oout.writeObject (args[i]);
            }

            oout.close();
            
            ObjectInputStream oin = new ObjectInputStream (c.getInputStream());
            result = oin.readObject();
            oin.close ();
            
        } catch (Exception e) {
            throw new RemoteException (Messages.getString("HttpInvokerHandler.5"), e); //$NON-NLS-1$
        }

        if ("true".equals(c.getHeaderField("Success"))) //$NON-NLS-1$ //$NON-NLS-2$
        {
            return result;
        }
        else
        {
            for ( int i = 0; i < method.getExceptionTypes().length; i++)
            {
                if( method.getExceptionTypes()[i].isInstance(result));
                    throw (Throwable) result;
            }
            if (result instanceof Throwable)
                throw new UndeclaredThrowableException((Throwable) result);
            else
                throw new RemoteException (result.toString());
        }
            
    }
	public void setHeadersFactory(List<HeadersFactory> headersFactory) {
		this.headersFactory  = headersFactory;
		
	}

}
