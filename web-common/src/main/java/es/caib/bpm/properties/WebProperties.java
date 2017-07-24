package es.caib.bpm.properties;

import java.io.File;
import java.rmi.RemoteException;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.exception.InvalidConfigurationException;

public class WebProperties 
{
	private WebProperties() throws BPMException
	{
	}
	
	public String getTempFolder() throws RemoteException, InvalidConfigurationException
	{
		String jbossTemp = System.getProperty("jboss.server.temp.dir"); //$NON-NLS-1$
		if (jbossTemp == null)
			jbossTemp = System.getProperty ("java.io.tmpdir"); //$NON-NLS-1$
		
		File tmp = new File (new File(jbossTemp), "bpm-web"); //$NON-NLS-1$
		tmp.mkdirs();
		return tmp.toString();
	}
	
	public static synchronized WebProperties getInstance() throws BPMException
	{
		if(INSTANCE== null)
		{
			INSTANCE= new WebProperties();
		}
		
		return INSTANCE;
	}

	private static WebProperties INSTANCE= null;
}
