// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import es.caib.seycon.ng.model.*;

import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import es.caib.seycon.ng.comu.TipusDomini;
import org.apache.webdav.lib.properties.GetContentLengthProperty;

/**
 * @see es.caib.seycon.ng.model.RolAssociacioRolEntity
 */
public class RoleDependencyEntityImpl
    extends com.soffid.iam.model.RoleDependencyEntity
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
		b.append(getRoleContainer().toString());
		String tipusDomini = getRoleContainer().getDomainType();
		if (TipusDomini.GRUPS.equals(tipusDomini) || TipusDomini.GRUPS_USUARI.equals(tipusDomini)) {
			GroupEntity grup = getGranteeGroupDomain();
			if (grup != null)
				b.append(" / ").append(grup.getCode());
		} else if (TipusDomini.APLICACIONS.equals(tipusDomini)) {
			InformationSystemEntity app = getGranteeApplicationDomain();
			if (app != null)
				b.append(" / ").append(app.getCode());
		} else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDomini)) {
			DomainValueEntity vd = getGranteeDomainValue();
			if (vd != null)
				b.append(" / ").append(vd.getValue());
		}

		b.append (" => ");
		b.append(getRoleContent().toString());

		tipusDomini = getRoleContainer().getDomainType();
		if (TipusDomini.GRUPS.equals(tipusDomini) || TipusDomini.GRUPS_USUARI.equals(tipusDomini)) {
			GroupEntity grup = getDomainGroup();
			if (grup != null)
				b.append(" / ").append(grup.getCode());
		} else if (TipusDomini.APLICACIONS.equals(tipusDomini)) {
			InformationSystemEntity app = getDomainApplication();
			if (app != null)
				b.append(" / ").append(app.getCode());
		} else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDomini)) {
			DomainValueEntity vd = getDomainApplicationValue();
			if (vd != null)
				b.append(" / ").append(vd.getValue());
		}
				
		return b.toString();
	}

	public boolean equals(Object object) {
		// Reemplazamos el equals, para que compare
		// los id de los elementos de la relación, no el id de la relación		
		if (getId()!=null) return super.equals(object);
		
		if (object instanceof RoleDependencyEntity)  {
			RoleDependencyEntity rare = (RoleDependencyEntity) object;
			
			// Comparamos roles
			if (getRoleContainer() != null && (rare.getRoleContainer() == null) || !getRoleContainer().getId().equals(rare.getRoleContainer().getId()))
				return false;
			if (getRoleContent() != null && ((rare.getRoleContent() == null) || !getRoleContent().getId().equals(rare.getRoleContent().getId())))
				return false;
			
			// Caso sense valor de domini (el grup, aplicacio, valor_domini_app será nulo )
			if (getDomainGroup() == null && getDomainApplication() == null && getDomainApplicationValue() == null && (rare.getDomainGroup() != null || rare.getDomainApplication() != null || rare.getDomainApplicationValue() != null))
				return false;
			
			// Grupo
			if ((getDomainGroup() != null && rare.getDomainGroup() == null) || (getDomainGroup() == null && rare.getDomainGroup() != null))
				return false;
			if (getDomainGroup() != null && rare.getDomainGroup() != null && !getDomainGroup().getId().equals(rare.getDomainGroup().getId()))
					return false;
			
			// Domini aplicacio
			if ((getGranteeApplicationDomain() !=null && rare.getGranteeApplicationDomain()==null) || (getGranteeApplicationDomain()==null && rare.getGranteeApplicationDomain()!=null) )
				return false;
			if (getGranteeApplicationDomain() != null && rare.getGranteeApplicationDomain() != null
					&& !getGranteeApplicationDomain().getId().equals(rare.getGranteeApplicationDomain().getId()))
				return false;
			
			// Valor de domini
			if ((getDomainApplicationValue() != null && rare.getDomainApplicationValue() == null) || (getDomainApplicationValue() == null && rare.getDomainApplicationValue() != null))
				return false;
			if (getDomainApplicationValue() != null && rare.getDomainApplicationValue() != null && !getDomainApplicationValue().getId().equals(rare.getDomainApplicationValue().getId()))
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