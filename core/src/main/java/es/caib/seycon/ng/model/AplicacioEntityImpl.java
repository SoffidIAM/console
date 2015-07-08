//
// (C) 2013 Soffid
//
//

package es.caib.seycon.ng.model;

import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.utils.Security;

/**
 * Entity AplicacioEntity implementation
 */
public class AplicacioEntityImpl extends es.caib.seycon.ng.model.AplicacioEntity 
	implements SecurityScopeEntity
{
	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		return Security.isUserInRole(permission+"/"+getCodi());
	}
}
