//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.model.security.SecurityScopeEntity;
import com.soffid.iam.utils.Security;

/**
 * Entity AplicacioEntity implementation
 */
public class InformationSystemEntityImpl extends com.soffid.iam.model.InformationSystemEntity
	implements SecurityScopeEntity
{
	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		return Security.isUserInRole(permission+"/"+getName());
	}
}
