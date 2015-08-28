//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.model.security.SecurityScopeEntity;
import com.soffid.iam.utils.Security;

/**
 * Entity SessioEntity implementation
 */
public class SessionEntityImpl extends SessionEntity implements SecurityScopeEntity {

	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
		
		if (getUser() != null)
			return getUser().isAllowed(permission);
		
		return false;
	}

}
