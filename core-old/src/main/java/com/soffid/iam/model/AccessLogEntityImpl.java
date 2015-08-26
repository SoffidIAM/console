//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.utils.Security;

/**
 * Entity RegistreAccesEntity implementation
 */
public class AccessLogEntityImpl extends com.soffid.iam.model.AccessLogEntity 
	implements SecurityScopeEntity
{

	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
				return true;
		
		if (getUser() != null)
			return getUser().isAllowed(permission);
		
		return false;
	}

}
