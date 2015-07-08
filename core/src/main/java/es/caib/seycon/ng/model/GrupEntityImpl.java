// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.GrupEntity
 */
public class GrupEntityImpl extends es.caib.seycon.ng.model.GrupEntity 
	implements SecurityScopeEntity
{
	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = -6294585483589486558L;

	/**
	 * @see es.caib.seycon.ng.model.GrupEntity#toString()
	 */
	public java.lang.String toString() {
		// @todo implement public java.lang.String toString()
		return String.format(Messages.getString("GrupEntityImpl.toString"), //$NON-NLS-1$
		        getId(), getCodi(),getDescripcio(), getTipusUnitatOrganizativa());
	}

	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		if (Security.isUserInRole(permission+"/"+getCodi()))
			return true;
		
		return false;
	}
}