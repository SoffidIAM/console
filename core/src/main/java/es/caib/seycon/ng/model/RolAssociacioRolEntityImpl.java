// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import org.apache.webdav.lib.properties.GetContentLengthProperty;

import es.caib.seycon.ng.comu.TipusDomini;

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
		StringBuffer b = new StringBuffer();
		b.append(getRolContenidor().toString());
		String tipusDomini = getRolContenidor().getTipusDomini();
		if (TipusDomini.GRUPS.equals(tipusDomini) || TipusDomini.GRUPS_USUARI.equals(tipusDomini)) {
			GrupEntity grup = getGranteeGroupDomain();
			if (grup != null)
				b.append(" / ").append (grup.getCodi());
		} else if (TipusDomini.APLICACIONS.equals(tipusDomini)) {
			AplicacioEntity app = getGranteeApplicationDomain();
			if (app != null)
				b.append(" / ").append (app.getCodi());
		} else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDomini)) {
			ValorDominiAplicacioEntity vd = getGranteeDomainValue();
			if (vd != null)
				b.append (" / ").append(vd.getValor());
		}

		b.append (" => ");
		b.append(getRolContingut().toString());

		tipusDomini = getRolContenidor().getTipusDomini();
		if (TipusDomini.GRUPS.equals(tipusDomini) || TipusDomini.GRUPS_USUARI.equals(tipusDomini)) {
			GrupEntity grup = getGrantedGroupDomain();
			if (grup != null)
				b.append(" / ").append (grup.getCodi());
		} else if (TipusDomini.APLICACIONS.equals(tipusDomini)) {
			AplicacioEntity app = getGrantedApplicationDomain();
			if (app != null)
				b.append(" / ").append (app.getCodi());
		} else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDomini)) {
			ValorDominiAplicacioEntity vd = getGrantedDomainValue();
			if (vd != null)
				b.append (" / ").append(vd.getValor());
		}
				
		return b.toString();
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
			
			// Caso sense valor de domini (el grup, aplicacio, valor_domini_app será nulo )
			if (getGrantedGroupDomain() == null && getGrantedApplicationDomain() == null && getGrantedDomainValue() == null
					&& (rare.getGrantedGroupDomain() != null  || rare.getGrantedApplicationDomain() != null ||
						rare.getGrantedDomainValue() != null))
				return false;
			
			// Grupo
			if ( (getGrantedGroupDomain()!=null && rare.getGrantedGroupDomain()==null) || (getGrantedGroupDomain()==null && rare.getGrantedGroupDomain()!=null) )
				return false;
			if (getGrantedGroupDomain()!=null && rare.getGrantedGroupDomain()!=null && !getGrantedGroupDomain().getId().equals(rare.getGrantedGroupDomain().getId()))
					return false;
			
			// Domini aplicacio
			if ((getGranteeApplicationDomain() !=null && rare.getGranteeApplicationDomain()==null) || (getGranteeApplicationDomain()==null && rare.getGranteeApplicationDomain()!=null) )
				return false;
			if (getGranteeApplicationDomain() != null && rare.getGranteeApplicationDomain() != null
					&& !getGranteeApplicationDomain().getId().equals(rare.getGranteeApplicationDomain().getId()))
				return false;
			
			// Valor de domini
			if ( (getGrantedDomainValue()!=null && rare.getGrantedDomainValue()==null) || 
					(getGrantedDomainValue()==null && rare.getGrantedDomainValue()!=null))
				return false;
			if (getGrantedDomainValue() != null && rare.getGrantedDomainValue() != null
					&& !getGrantedDomainValue().getId().equals(rare.getGrantedDomainValue().getId()))
				return false;
			
			// The same for grantee domains
			// Caso sense valor de domini (el grup, aplicacio, valor_domini_app será nulo )
			if (getGranteeGroupDomain() == null && getGranteeApplicationDomain() == null && getGranteeDomainValue() == null
					&& (rare.getGranteeGroupDomain() != null  || rare.getGranteeApplicationDomain() != null ||
						rare.getGranteeDomainValue() != null))
				return false;
			
			// Grupo
			if ( (getGranteeGroupDomain()!=null && rare.getGranteeGroupDomain()==null) || (getGranteeGroupDomain()==null && rare.getGranteeGroupDomain()!=null) )
				return false;
			if (getGranteeGroupDomain()!=null && rare.getGranteeGroupDomain()!=null && !getGranteeGroupDomain().getId().equals(rare.getGranteeGroupDomain().getId()))
					return false;
			
			// Domini aplicacio
			if ((getGranteeApplicationDomain() !=null && rare.getGranteeApplicationDomain()==null) || (getGranteeApplicationDomain()==null && rare.getGranteeApplicationDomain()!=null) )
				return false;
			if (getGranteeApplicationDomain() != null && rare.getGranteeApplicationDomain() != null
					&& !getGranteeApplicationDomain().getId().equals(rare.getGranteeApplicationDomain().getId()))
				return false;
			
			// Valor de domini
			if ( (getGranteeDomainValue()!=null && rare.getGranteeDomainValue()==null) || (getGranteeDomainValue()==null && rare.getGranteeDomainValue()!=null))
				return false;
			if (getGranteeDomainValue() != null && rare.getGranteeDomainValue() != null
					&& !getGranteeDomainValue().getId().equals(rare.getGranteeDomainValue().getId()))
				return false;
			

			
			return true;
		} 
		return false;
	}
}