package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;
import es.caib.seycon.ng.utils.Security;

import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.security.SecurityScopeEntity;

public class UserEntityImpl extends UserEntity
	implements SecurityScopeEntity
{

	@Override
	public String getFullName()
	{
		String nameFormat = System.getProperty("soffid.nameformat");
		
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
			return String.format(nameFormat, getFirstName(), getMiddleName(), getLastName());
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
		if (getPrimaryGroup() != null
						&& Security.isUserInRole(permission + "/" //$NON-NLS-1$
										+ getPrimaryGroup().getName()))
			return true;
		if (!trobat)
		{ // mirem grups secundaris
			for (UserGroupEntity userGroup: getSecondaryGroups())
			{
				if (userGroup.getGroup() != null &&
						Security.isUserInRole(permission + "/" + userGroup.getGroup().getName() )) //$NON-NLS-1$
					return true;
			}
		}

		return false;
	}
}
