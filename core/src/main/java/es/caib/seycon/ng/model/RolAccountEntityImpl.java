package es.caib.seycon.ng.model;

import java.util.Collection;

import org.zkoss.zk.au.in.GetUploadInfoCommand;

import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.utils.Security;

public class RolAccountEntityImpl extends RolAccountEntity implements SecurityScopeEntity
{

	@Override
	public String toString()
	{
		return String.format(Messages.getString("RolAccountEntityImpl.0"), //$NON-NLS-1$
						getAccount().getName(), getRol().toString());
	}

	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission + Security.AUTO_ALL))
			return true;
				
		if (getAccount() != null && getAccount().getType().equals (AccountType.USER))
		{
			for (UserAccountEntity users: getAccount().getUsers())
			{
				if (users.getUser() != null && users.getUser().isAllowed(permission))
					return true;
			}
		}
		
		if (getAplicacioAdministrada() != null)
			return Security.isUserInRole(permission+"/"+getAplicacioAdministrada().getCodi());

		return false;
	}
	
	

}