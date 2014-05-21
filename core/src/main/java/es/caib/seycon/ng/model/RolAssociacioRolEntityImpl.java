// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import es.caib.seycon.ng.utils.TipusDomini;

/**
 * @see es.caib.seycon.ng.model.RolAssociacioRolEntity
 */
public class RolAssociacioRolEntityImpl
    extends es.caib.seycon.ng.model.RolAssociacioRolEntity
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -7115789345753650710L;

    /**
     * @see es.caib.seycon.ng.model.RolAssociacioRolEntity#toString()
     */
	public java.lang.String toString() {
		String sdomini = String.format(Messages.getString("RolAssociacioRolEntityImpl.domain"),getTipusDomini()); //$NON-NLS-1$
		if (!TipusDomini.SENSE_DOMINI.equals(getTipusDomini())) {
			String tipusDomini = getTipusDomini();
			if (TipusDomini.GRUPS.equals(tipusDomini) || TipusDomini.GRUPS_USUARI.equals(tipusDomini)) {
				GrupEntity grup = getGrupDomini();
				sdomini += String.format(Messages.getString("RolAssociacioRolEntityImpl.group"), grup != null ? grup.getCodi() //$NON-NLS-1$
						: TipusDomini.QUALQUE_VALOR_DOMINI );
			} else if (TipusDomini.APLICACIONS.equals(tipusDomini)) {
				AplicacioEntity app = getAplicacioDomini();
				sdomini += String.format(Messages.getString("RolAssociacioRolEntityImpl.application"), app != null ? getAplicacioDomini() //$NON-NLS-1$
						.getCodi() : TipusDomini.QUALQUE_VALOR_DOMINI);
			} else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDomini)) {
				ValorDominiAplicacioEntity vd = getValorDominiAplicacio();
				sdomini += String.format(Messages.getString("RolAssociacioRolEntityImpl.domainValue"), //$NON-NLS-1$
				        vd != null ? vd.getDomini().getNom()
						: TipusDomini.QUALQUE_VALOR_DOMINI,
					vd != null ? getValorDominiAplicacio().getValor()
						: TipusDomini.Descripcio.QUALQUE_VALOR_DOMINI);
			} else if (TipusDomini.QUALQUE_VALOR_DOMINI.equals(tipusDomini) || tipusDomini==null) {
				sdomini += String.format(Messages.getString("RolAssociacioRolEntityImpl.domainType"), //$NON-NLS-1$
						getRolContenidor().getTipusDomini(),
						TipusDomini.QUALQUE_VALOR_DOMINI);
			}
		}
		return String.format(Messages.getString("RolAssociacioRolEntityImpl.assignedTo"),getRolContingut().toDescripcioRol(), getRolContenidor().toDescripcioRol(),sdomini); //$NON-NLS-1$
				
	}

	public boolean equals(Object object) {
		// Reemplazamos el equals, para que compare
		// los id de los elementos de la relación, no el id de la relación		
		if (getId()!=null) return super.equals(object);
		
		if (object instanceof RolAssociacioRolEntity)  {
			RolAssociacioRolEntity rare = (RolAssociacioRolEntity) object;
			
			// Comparamos roles
			if (getRolContenidor() != null
					&& (rare.getRolContenidor() == null) || 
						!getRolContenidor().getId().equals(rare.getRolContenidor().getId() )
				)
				return false;
			if (getRolContingut() != null
					&& ((rare.getRolContingut() == null) || 
							!getRolContingut().getId().equals(rare.getRolContingut().getId()))
				)
				return false;
			
			// Comparamos valor de dominio
			if (!getTipusDomini().equals(rare.getTipusDomini())) return false;
			
			// Caso sense valor de domini (el grup, aplicacio, valor_domini_app será nulo )
			if (getGrupDomini() == null && getAplicacioDomini() == null && getValorDominiAplicacio() == null
					&& (rare.getGrupDomini() != null  || rare.getAplicacioDomini() != null || rare
							.getValorDominiAplicacio() != null))
				return false;
			
			// Grupo
			if ( (getGrupDomini()!=null && rare.getGrupDomini()==null) || (getGrupDomini()==null && rare.getGrupDomini()!=null) )
				return false;
			if (getGrupDomini()!=null && rare.getGrupDomini()!=null && !getGrupDomini().getId().equals(rare.getGrupDomini().getId()))
					return false;
			
			// Domini aplicacio
			if ((getAplicacioDomini() !=null && rare.getAplicacioDomini()==null) || (getAplicacioDomini()==null && rare.getAplicacioDomini()!=null) )
				return false;
			if (getAplicacioDomini() != null && rare.getAplicacioDomini() != null
					&& !getAplicacioDomini().getId().equals(rare.getAplicacioDomini().getId()))
				return false;
			
			// Valor de domini
			if ( (getValorDominiAplicacio()!=null && rare.getValorDominiAplicacio()==null) || (getValorDominiAplicacio()==null && rare.getValorDominiAplicacio()!=null))
				return false;
			if (getValorDominiAplicacio() != null && rare.getValorDominiAplicacio() != null
					&& !getValorDominiAplicacio().getId().equals(rare.getValorDominiAplicacio().getId()))
				return false;
			
			return true;
		} 
		return false;
	}
}