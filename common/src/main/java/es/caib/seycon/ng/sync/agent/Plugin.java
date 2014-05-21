package es.caib.seycon.ng.sync.agent;

import java.io.Serializable;

public class Plugin implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	String version;
	byte []content;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getVersion()
	{
		return version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}
	public byte[] getContent()
	{
		return content;
	}
	public void setContent(byte[] content)
	{
		this.content = content;
	}
}
