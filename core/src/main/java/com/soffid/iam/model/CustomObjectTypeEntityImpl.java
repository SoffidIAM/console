package com.soffid.iam.model;

import com.soffid.iam.utils.Security;

public class CustomObjectTypeEntityImpl extends CustomObjectTypeEntity {

	@Override
	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		return false;
	}

}
