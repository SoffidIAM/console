//
// (C) 2013 Soffid
//
//

package es.caib.seycon.ng.model;

import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.utils.Security;

/**
 * Entity RegistreAccesEntity implementation
 */
public class RegistreAccesEntityImpl extends es.caib.seycon.ng.model.RegistreAccesEntity 
	implements SecurityScopeEntity
{

	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
				return true;
		
		if (getUsuari() != null)
			return getUsuari().isAllowed(permission);
		
		return false;
	}

}
