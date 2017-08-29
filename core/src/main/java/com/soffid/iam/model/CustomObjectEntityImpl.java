package com.soffid.iam.model;

import com.soffid.iam.utils.Security;

public class CustomObjectEntityImpl extends CustomObjectEntity {

	@Override
	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		return false;
	}

}
