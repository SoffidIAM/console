package com.soffid.iam.ssl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.mortbay.jetty.HttpHeaders;

import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.remote.HeadersFactory;
import com.soffid.iam.remote.RemoteInvokerFactory;
import com.soffid.iam.util.Base64;

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
    	String[] success = new String[1];
        try {
        	PrivilegedExceptionAction<Object> action = new PrivilegedExceptionAction<Object>() {
				@Override
				public Object run() throws Exception {
		        	HttpURLConnection c;
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
		                oout.writeObject (unwrap(args[i]));
		            }

		            oout.close();
		            
		            SecureObjectInputStream oin = new SecureObjectInputStream (c.getInputStream());
		            Object result = oin.readObject();
		            oin.close ();
		            
		            success[0] = c.getHeaderField("Success");
		            
		            return result;
				}

			};
        	result = AccessController.doPrivileged(action);
        } catch (Exception e) {
            throw new RemoteException (Messages.getString("HttpInvokerHandler.5"), e); //$NON-NLS-1$
        }

        if ("true".equals(success[0])) //$NON-NLS-1$ //$NON-NLS-2$
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

	private Object unwrap(Object r) {
		if (r == null)
			return null;
		if (r instanceof PolyglotException) {
			PolyglotException som = (PolyglotException) r;
			throw som;
		}
		if (r instanceof Value value) {
			if (value.isBoolean())
				return value.asBoolean();
			else if (value.isDate())
				return value.asDate();
			else if (value.isDuration())
				return value.asDuration();
			else if (value.isException())
				value.throwException();
			else if (value.isHostObject())
				return value.asHostObject();
			else if (value.isInstant())
				return value.asInstant();
			else if (value.isMetaObject())
				return value.getMetaObject();
			else if (value.isNull())
				return null;
			else if (value.isNumber()) {
				if (value.fitsInInt())
					return value.asInt();
				else if (value.fitsInLong())
					return value.asLong();
				else
					return value.asDouble();
			}
			else if (value.isString())
				return value.asString();
			else if (value.isTime())
				return value.asTime();
			else if (value.isTimeZone())
				return value.asTimeZone();
			else if (value.hasArrayElements()) {
				LinkedList<Object> l = new LinkedList<Object>();
				for (int i = 0; i < value.getArraySize(); i++) {
					l.add(unwrap(value.getArrayElement(i)));
				}
				return l;
			}
			else {
				HashMap<String, Object> m = new HashMap<>();
				for (String o: value.getMemberKeys()) {
					m.put(o, unwrap(value.getMember(o)));
				}
				return m;
			}
		}

		return r;
	}

}
