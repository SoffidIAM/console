package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import java.util.Collection;

import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.security.SecurityScopeEntity;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

public class UserEntityImpl extends UserEntity
	implements SecurityScopeEntity
{

	@Override
	public String getFullName()
	{
		String nameFormat = ConfigurationCache.getProperty("soffid.nameformat");
		
		if (nameFormat == null)
		{
			StringBuffer b = new StringBuffer ();
			b.append(getFirstName());
			b.append (" "); //$NON-NLS-1$
			b.append(getLastName());
			if (getMiddleName() != null && !getMiddleName().isEmpty())
			{
				b.append(" "); //$NON-NLS-1$
				b.append(getMiddleName());
			}
			return b.toString();
		} else {
			return String.format(nameFormat, getFirstName(), getMiddleName() == null? "": getMiddleName(), getLastName());
		}
	}

	public boolean isAllowed(String permission) {
		if (! permission.endsWith(":query"))
		{
			String user = Security.getCurrentUser();
			if ( user != null && user.equals (getUserName()))
				return false;
		}
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
		boolean trobat = false;
		if (getPrimaryGroup() != null && getPrimaryGroup().isAllowed(permission))
			return true;
		if (!trobat)
		{ // mirem grups secundaris
			for (UserGroupEntity userGroup: getSecondaryGroups())
			{
				if (userGroup.getGroup() != null && 
						!Boolean.TRUE.equals( userGroup.getDisabled() ) && 
						userGroup.getGroup().isAllowed(permission)) //$NON-NLS-1$
					return true;
			}
		}

		return false;
	}
	
	public Collection<UserDataEntity> getUserData() {
		return getAttributes();
	}
}
