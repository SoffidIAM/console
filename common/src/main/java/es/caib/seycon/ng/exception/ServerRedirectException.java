package es.caib.seycon.ng.exception;

import java.util.Collection;

public class ServerRedirectException extends Exception
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9136059242457783631L;
	
	
	@Override
	public String getMessage()
	{
		StringBuffer b = new StringBuffer("Needed information not available. Redirect to [");
		boolean first = true;
		for (String server: serverList)
		{
			if (!first) b.append(", ");
			b.append(server);
			first = false;
		}
		b.append ("]");
		return b.toString();
	}

	private Collection<String> serverList;

	public ServerRedirectException (Collection<String> serverList)
	{
		super ();
		this.serverList = serverList;
	}

	public Collection<String> getServerList()
	{
		return serverList;
	}

}
