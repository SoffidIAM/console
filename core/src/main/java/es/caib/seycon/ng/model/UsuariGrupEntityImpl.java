//
// (C) 2013 Soffid
//
//

package es.caib.seycon.ng.model;

import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.utils.Security;

/**
 * Entity UsuariGrupEntity implementation
 */
public class UsuariGrupEntityImpl extends es.caib.seycon.ng.model.UsuariGrupEntity 
	implements SecurityScopeEntity
{

	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		if (getGrup() != null)
			return getGrup().isAllowed(permission);
		
		return false;
	}

}
