package com.soffid.iam.remote;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.soffid.iam.config.Config;

import es.caib.seycon.ng.exception.InternalErrorException;

public class URLManager {
    String url;
    String rmiUrl;
    String httpUrl;
    
    public URLManager (String url)
    {
        this.url = url;
    }

    private java.net.URL toURL () throws MalformedURLException
    {
        String url = this.url;
        if (url.startsWith("rmi:")){ //$NON-NLS-1$
            url = url.substring(4);
        }if (url.startsWith("http:") || url.startsWith("https:")){ //$NON-NLS-1$ //$NON-NLS-2$
            return new URL (url);
        }else{
            return new URL("https:"+url); //$NON-NLS-1$
        }
    }

    public java.net.URL getHttpURL (String service) throws IOException, InternalErrorException
    {
        String port = Config.getConfig().getPort();
        if (!service.startsWith("/")) //$NON-NLS-1$
            service = "/"+service; //$NON-NLS-1$
        
        String url = this.url;
        if (url.startsWith("rmi:")) //$NON-NLS-1$
            url = url.substring(4);
        if (! url.contains("/")) //$NON-NLS-1$
            return new URL ("https://"+url+":"+port+service); //$NON-NLS-1$ //$NON-NLS-2$

        URL url2 = null;
        
        if (url.startsWith("https:") || url.startsWith("http:")) //$NON-NLS-1$ //$NON-NLS-2$
        {
            url2 = new URL(url);
        }
        else
        {
            url2 = new URL("http:"+url);             //$NON-NLS-1$
        }

        if (url.startsWith("https:") || url.startsWith("http:")) //$NON-NLS-1$
        {
  			return new URL (url2.getProtocol(),
    					url2.getHost(), 
    					url2.getPort() == -1 ?  Integer.parseInt(port): url2.getPort(),
    					service); //$NON-NLS-1$ //$NON-NLS-2$
        }
        else
        {
        	return new URL ("https",
        			url2.getHost(),
					url2.getPort() == -1 ?  Integer.parseInt(port): url2.getPort(),
					service);
        }
    }
    
    public URL getServerURL () throws IOException, InternalErrorException
    {
        return getHttpURL("/seycon/Server"); //$NON-NLS-1$
    }

    public URL getLogonURL () throws IOException, InternalErrorException
    {
        return getHttpURL("/seycon/Server/logon"); //$NON-NLS-1$
    }

    public URL getEnrollURL () throws IOException, InternalErrorException
    {
        return getHttpURL("/seycon/Server/enroll"); //$NON-NLS-1$
    }

    public URL getAdminURL () throws IOException, InternalErrorException
    {
        return getHttpURL("/seycon/Server/admin"); //$NON-NLS-1$
    }

    public URL getAgentURL () throws IOException, InternalErrorException
    {
        return getHttpURL("/seycon/AgentService"); //$NON-NLS-1$
    }
    
    public URL getServerStatusURL () throws IOException, InternalErrorException
    {// no pot estar a /seycon perquè està protegit per certificat
        return getHttpURL("/SEU/status"); //$NON-NLS-1$
    }
    
    /**
     * Obté la URL per obtindre el log del servidor
     * IMPORTANT: és un servlet, no és un servei RMI
     * @return
     * @throws IOException
     * @throws InternalErrorException
     */
    public URL getServerLogFileURL () throws IOException, InternalErrorException
    {
        return getHttpURL("/log"); //$NON-NLS-1$
    }       
    
    public boolean isRMI ()
    {
        return ! url.startsWith("http:"); //$NON-NLS-1$
    }
    
    public URL getRMIURL () throws MalformedURLException
    {
        if (! isRMI() )
            return null;
        else
            return toURL();
    }

    public String getRMIString () throws MalformedURLException
    {
        if (! isRMI() )
            return null;
        else if (url.startsWith("rmi:")) //$NON-NLS-1$
            return url.substring(4);
        else
            return url;
    }
}
