// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.net.RoleInfo;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.RolsGrupEntity
 */
public class RolsGrupEntityImpl
    extends es.caib.seycon.ng.model.RolsGrupEntity
    implements SecurityScopeEntity
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = 1144352018496585230L;

    /**
     * @see es.caib.seycon.ng.model.RolsGrupEntity#toString()
     */
    public java.lang.String toString()
    {
        // @todo implement public java.lang.String toString()
        return null;
    }

	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		if (getRolOtorgat() != null)
			return getRolOtorgat().isAllowed(permission);
		
		return false;
	}

}