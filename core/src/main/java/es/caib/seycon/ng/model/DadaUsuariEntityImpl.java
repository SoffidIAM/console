//
// (C) 2013 Soffid
//
//

package es.caib.seycon.ng.model;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.AutoritzacioService;
import es.caib.seycon.ng.utils.Security;

/**
 * Entity DadaUsuariEntity implementation
 */
public class DadaUsuariEntityImpl extends es.caib.seycon.ng.model.DadaUsuariEntity
	implements SecurityScopeEntity
{
	public AttributeVisibilityEnum getAttributeVisibility() {
		AutoritzacioService autService = ServiceLocator.instance().getAutoritzacioService();
		try {
			if (autService.hasPermission(
					Security.AUTO_USER_METADATA_UPDATE, this))
				return AttributeVisibilityEnum.EDITABLE;
			else if (autService.hasPermission(
					Security.AUTO_USER_QUERY, this))
				return AttributeVisibilityEnum.READONLY;
			else
				return AttributeVisibilityEnum.HIDDEN;
		} catch (InternalErrorException e) {
			throw new RuntimeException(e);
		}
	}


	private AttributeVisibilityEnum getInitialVisibility ()
	{
		if (Security.isDisableAllSecurityForEver())
			return AttributeVisibilityEnum.EDITABLE;
		
		if (Security.isUserInRole(Security.AUTO_METADATA_UPDATE_ALL))
			return AttributeVisibilityEnum.EDITABLE;

		TipusDadaEntity tda = getTipusDada();
		if (tda == null)
			return AttributeVisibilityEnum.HIDDEN;
		
		String user = Security.getCurrentUser();
		if (user != null)
		{
			if (getUsuari().getCodi().equals(user))
				return tda.getUserVisibility() == null ? AttributeVisibilityEnum.HIDDEN: tda.getUserVisibility();
		}
		
		if (Security.isUserInRole(Security.AUTO_AUTHORIZATION_ALL))
			return tda.getAdminVisibility() == null ? AttributeVisibilityEnum.EDITABLE
					: tda.getAdminVisibility();
		else if (getUsuari().isAllowed(Security.AUTO_USER_METADATA_UPDATE))
			return tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.EDITABLE
					: tda.getOperatorVisibility();
		else if (getUsuari().isAllowed(Security.AUTO_USER_QUERY)) {
			AttributeVisibilityEnum v = tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.READONLY
					: tda.getOperatorVisibility();
			if (AttributeVisibilityEnum.EDITABLE.equals(v))
				v = AttributeVisibilityEnum.READONLY;
			return v;
		} else
			return AttributeVisibilityEnum.HIDDEN;
	}

	public boolean isAllowed(String permission) {
		if (permission.equals (Security.AUTO_USER_QUERY))
		{
			return getInitialVisibility().equals (AttributeVisibilityEnum.EDITABLE) ||
					getInitialVisibility().equals (AttributeVisibilityEnum.READONLY) ;
		}
		else if (permission.equals (Security.AUTO_USER_METADATA_UPDATE))
		{
			return getInitialVisibility().equals (AttributeVisibilityEnum.EDITABLE)  ;
		}
		else
			return Security.isUserInRole(permission+Security.AUTO_ALL);
	}

}
