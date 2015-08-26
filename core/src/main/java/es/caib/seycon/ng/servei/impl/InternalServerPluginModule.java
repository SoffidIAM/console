package es.caib.seycon.ng.servei.impl;

import com.soffid.iam.api.ServerPluginModule;

/**
 * @author bubu
 *
 */
public class InternalServerPluginModule extends ServerPluginModule
{
	byte data [];

	public byte[] getData()
	{
		return data;
	}

	public void setData(byte[] data)
	{
		this.data = data;
	}
	
}
