// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

/**
 * @see es.caib.seycon.ng.model.RolEntity
 */
/**
 * @author u88683
 *
 */
public class RoleEntityImpl
    extends com.soffid.iam.model.RoleEntity
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
        return "[ id=\'" + getId() + "\' nom=\'" + getName() + "\' descripcio=\'" + getDescription() + "\' defecte=\'" + getDefaultRole() + "\' contasenya=\'" + getPassword() + "\' tipusDomini=\'" + getDomainType() + "\' ]"; //$NON-NLS-1$
    }

	public String toRoleDescription() {//Imporante: CUIDADO AL CAMBIARLO, puede afectar a funcionalidad
		return getName() + "@" + getSystem().getName() + " (" + getInformationSystem().getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ 
	}

}