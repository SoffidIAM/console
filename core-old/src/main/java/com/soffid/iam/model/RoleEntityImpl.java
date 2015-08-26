// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.RolEntity
 */
/**
 * @author u88683
 *
 */
public class RoleEntityImpl
    extends RoleEntity implements SecurityScopeEntity
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -8651576011680103025L;

    /**
     * @see es.caib.seycon.ng.model.RolEntity#toString()
     */
    public java.lang.String toString()
    {
        return 
        "[ id='"+getId()+ //$NON-NLS-1$
        "' name='"+getName()+ //$NON-NLS-1$
        "' description='"+getDescription()+ //$NON-NLS-1$
        "' default='"+getDefaultRole()+ //$NON-NLS-1$
        "' password='"+getPassword()+ //$NON-NLS-1$
        "' type='"+getDomainType()+ //$NON-NLS-1$
        "' ]"; //$NON-NLS-1$
    }

	public String toRoleDescription() {//Imporante: CUIDADO AL CAMBIARLO, puede afectar a funcionalidad
		return getName()+"@"+getSystem().getName()+" ("+getInformationSystem().getName()+")"; //$NON-NLS-1$ //$NON-NLS-2$ 
	}

	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		if (getInformationSystem() != null)
			return getInformationSystem().isAllowed(permission);
		
		return false;
	}

}