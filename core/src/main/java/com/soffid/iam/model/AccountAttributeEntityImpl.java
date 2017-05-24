//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.UserAccountEntity;
import es.caib.seycon.ng.servei.AutoritzacioService;
import es.caib.seycon.ng.utils.Security;

/**
 * Entity AccountAttributeEntity implementation
 */
public class AccountAttributeEntityImpl extends com.soffid.iam.model.AccountAttributeEntity 
	implements SecurityScopeEntity
{

	AttributeVisibilityEnum v = null;
	
	public AttributeVisibilityEnum getAttributeVisibility() {
		return AttributeVisibilityEnum.EDITABLE;

/*		AutoritzacioService autService = ServiceLocator.instance().getAutoritzacioService();
		try {
			if (autService.hasPermission(
					Security.AUTO_ACCOUNT_ATTRIBUTE_UPDATE, this))
				return AttributeVisibilityEnum.EDITABLE;
			else if (autService.hasPermission(
					Security.AUTO_ACCOUNT_ATTRIBUTE_QUERY, this))
				return AttributeVisibilityEnum.READONLY;
			else
				return AttributeVisibilityEnum.HIDDEN;
		} catch (InternalErrorException e) {
			throw new RuntimeException(e);
		}
		*/
	}

	private AttributeVisibilityEnum getInitialVisibility ()
	{
		if (Security.isDisableAllSecurityForEver())
			v = AttributeVisibilityEnum.EDITABLE;
		else if (Security.isUserInRole(Security.AUTO_METADATA_UPDATE_ALL))
			v = AttributeVisibilityEnum.EDITABLE;
		else 
		{
			com.soffid.iam.model.AccountMetadataEntity tda = getMetadata();
			if (tda == null)
				v = AttributeVisibilityEnum.HIDDEN;
			else
			{
				String user = Security.getCurrentUser();
				if (user != null)
				{
					AccountEntity account = getAccount();
					if (account.getType().equals(AccountType.USER))
					{
						for (UserAccountEntity uac: account.getUsers())
						{
							if (uac.getUser() != null && uac.getUser().getCodi().equals(user))
							{
								return tda.getUserVisibility() == null ? AttributeVisibilityEnum.HIDDEN: tda.getUserVisibility();
							}
						}
					}
				}
				AutoritzacioService autService = ServiceLocator.instance().getAutoritzacioService();
				if (Security.isUserInRole(Security.AUTO_AUTHORIZATION_ALL))
					v =  tda.getAdminVisibility() == null ? AttributeVisibilityEnum.EDITABLE: tda.getAdminVisibility();
				else if (Security.isUserInRole(Security.AUTO_ACCOUNT_ATTRIBUTE_UPDATE))
					v = tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.EDITABLE: tda.getOperatorVisibility();
				else if (Security.isUserInRole(Security.AUTO_ACCOUNT_ATTRIBUTE_QUERY))
				{
					v = tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.READONLY: tda.getOperatorVisibility();
					if (AttributeVisibilityEnum.EDITABLE.equals (v))
						v = AttributeVisibilityEnum.READONLY;
				}
				else
					v = AttributeVisibilityEnum.HIDDEN;
			}
		}
		return v;
		
	}
	
	public boolean isAllowed(String permission) {
		if (permission.equals (Security.AUTO_ACCOUNT_ATTRIBUTE_QUERY))
		{
			return getInitialVisibility().equals (AttributeVisibilityEnum.EDITABLE) ||
					getInitialVisibility().equals (AttributeVisibilityEnum.READONLY) ;
		}
		else if (permission.equals (Security.AUTO_ACCOUNT_ATTRIBUTE_UPDATE))
		{
			return getInitialVisibility().equals (AttributeVisibilityEnum.EDITABLE)  ;
		}
		else
			return Security.isUserInRole(permission+Security.AUTO_ALL);
	}

}
