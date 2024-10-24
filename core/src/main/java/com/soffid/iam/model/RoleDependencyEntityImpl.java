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

import org.hibernate.proxy.HibernateProxy;

import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.security.SecurityScopeEntity;
import com.soffid.iam.spring.JCSCacheProvider;

import es.caib.seycon.ng.comu.TipusDomini;

/**
 * @see es.caib.seycon.ng.model.RolAssociacioRolEntity
 */
public class RoleDependencyEntityImpl
    extends com.soffid.iam.model.RoleDependencyEntity
    implements SecurityScopeEntity
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
		b.append(getContainer().toString());
		String tipusDomini = getContainer().getDomainType();
		if (TipusDomini.GRUPS.equals(tipusDomini) || TipusDomini.GRUPS_USUARI.equals(tipusDomini)
				|| TipusDomini.GROUPS.equals(tipusDomini) || TipusDomini.MEMBERSHIPS.equals(tipusDomini)) {
			GroupEntity grup = getGranteeGroupDomain();
			if (grup != null)
				b.append(" / ").append(grup.getName());
		} else if (TipusDomini.APLICACIONS.equals(tipusDomini)
				|| TipusDomini.APPLICATIONS.equals(tipusDomini)) {
			InformationSystemEntity app = getGranteeApplicationDomain();
			if (app != null)
				b.append(" / ").append(app.getName());
		} else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDomini) 
				|| TipusDomini.CUSTOM.equals(tipusDomini)) {
			DomainValueEntity vd = getGranteeDomainValue();
			if (vd != null)
				b.append(" / ").append(vd.getValue());
		}

		b.append (" => ");
		b.append(getContained().toString());

		tipusDomini = getContainer().getDomainType();
		if (TipusDomini.GRUPS.equals(tipusDomini) || TipusDomini.GRUPS_USUARI.equals(tipusDomini)
				|| TipusDomini.GROUPS.equals(tipusDomini) || TipusDomini.CUSTOM.equals(tipusDomini)) {
			GroupEntity grup = getDomainGroup();
			if (grup != null)
				b.append(" / ").append(grup.getName());
		} else if (TipusDomini.APLICACIONS.equals(tipusDomini) 
				|| TipusDomini.APPLICATIONS.equals(tipusDomini)) {
			InformationSystemEntity app = getDomainApplication();
			if (app != null)
				b.append(" / ").append(app.getName());
		} else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDomini)
				|| TipusDomini.CUSTOM.equals(tipusDomini)) {
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
			if (getContainer() != null && (rare.getContainer() == null) || !getContainer().getId().equals(rare.getContainer().getId()))
				return false;
			if (getContained() != null && ((rare.getContained() == null) || !getContained().getId().equals(rare.getContained().getId())))
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
			if ((getDomainApplication() !=null && rare.getDomainApplication()==null) || 
					(getDomainApplication()==null && rare.getDomainApplication()!=null) )
				return false;
			if (getDomainApplication() != null && rare.getDomainApplication() != null
					&& !getDomainApplication().getId().equals(rare.getDomainApplication().getId()))
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

	public boolean isAllowed(String permission) {
		if (getContained() == null)
			return false;
		else
			return getContained().isAllowed(permission);
	}

	@Override
	public void customCache() {
	}

}
