package es.caib.seycon.ng.servei.impl;

import es.caib.seycon.ng.comu.ServerPluginModule;

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
