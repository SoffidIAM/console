package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.UserEntity;

public class UserEntityImpl extends UserEntity
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

}
