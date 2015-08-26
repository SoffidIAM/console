//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.utils.Security;

/**
 * Entity UsuariGrupEntity implementation
 */
public class UserGroupEntityImpl extends UserGroupEntity
	implements SecurityScopeEntity
{

	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		if (getGroup() != null)
			return getGroup().isAllowed(permission);
		
		return false;
	}

}
