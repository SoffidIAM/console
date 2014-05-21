package es.caib.seycon.ng.model;

import org.zkoss.zk.au.in.GetUploadInfoCommand;

public class RolAccountEntityImpl extends RolAccountEntity
{

	@Override
	public String toString()
	{
		return String.format(Messages.getString("RolAccountEntityImpl.0"), //$NON-NLS-1$
						getAccount().getName(), getRol().toString());
	}

}
