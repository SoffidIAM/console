// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

/**
 * @see es.caib.seycon.ng.model.RolEntity
 */
/**
 * @author u88683
 *
 */
public class RolEntityImpl
    extends es.caib.seycon.ng.model.RolEntity
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
        "' nom='"+getNom()+ //$NON-NLS-1$
        "' descripcio='"+getDescripcio()+ //$NON-NLS-1$
        "' defecte='"+getDefecte()+ //$NON-NLS-1$
        "' contasenya='"+getContrasenya()+ //$NON-NLS-1$
        "' tipusDomini='"+getTipusDomini()+ //$NON-NLS-1$
        "' ]"; //$NON-NLS-1$
    }

	public String toDescripcioRol() {//Imporante: CUIDADO AL CAMBIARLO, puede afectar a funcionalidad
		return getNom()+"@"+getBaseDeDades().getCodi()+">"+getAplicacio().getCodi(); //$NON-NLS-1$ //$NON-NLS-2$ 
	}

}