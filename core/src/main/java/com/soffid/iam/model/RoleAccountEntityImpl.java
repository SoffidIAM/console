package com.soffid.iam.model;

import java.util.Collection;

import com.soffid.iam.model.security.SecurityScopeEntity;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;

public class RoleAccountEntityImpl extends RoleAccountEntity implements SecurityScopeEntity
{

	@Override
	public String toString()
	{
		return String.format(Messages.getString("RolAccountEntityImpl.0"), //$NON-NLS-1$
						getAccount().getName(), getRole().toString());
	}

	public boolean isAllowed(String permission) {
		if (getAccount() != null && getAccount().getType().equals (AccountType.USER))
		{
			for (UserAccountEntity users: getAccount().getUsers())
			{
				if (users.getUser() != null && 
						users.getUser().getUserName().equals(Security.getCurrentUser()) &&
						getRule() == null)
					return false;
				if (users.getUser() != null && users.getUser().isAllowed(permission))
					return true;
			}
		}
		
		
		
		if (Security.isUserInRole(permission + Security.AUTO_ALL))
			return true;
				
		if (getInformationSystem() != null)
			return Security.isUserInRole(permission+"/"+getInformationSystem().getName());

		return false;
	}
	
	

}
