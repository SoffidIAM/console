//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.model.security.SecurityScopeEntity;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * Entity AccountAttributeEntity implementation
 */
public class AccountAttributeEntityImpl extends com.soffid.iam.model.AccountAttributeEntity 
	implements SecurityScopeEntity
{

	AttributeVisibilityEnum v = null;
	
	public AttributeVisibilityEnum getAttributeVisibility() {
		return AttributeVisibilityEnum.EDITABLE;
	}

	private AttributeVisibilityEnum getInitialVisibility ()
	{
		if (Security.isSyncServer())
			v = AttributeVisibilityEnum.EDITABLE;
		else if (Security.isUserInRole(Security.AUTO_METADATA_UPDATE_ALL))
			v = AttributeVisibilityEnum.EDITABLE;
		else 
		{
			com.soffid.iam.model.AccountMetadataEntity tda = getSystemMetadata();
			AttributeVisibilityEnum uservis = null, admin = null, operator = null;
			if (tda == null) {
				MetaDataEntity tda0 = getMetadata();
				if (tda0 == null)
					return AttributeVisibilityEnum.HIDDEN;
				else {
					uservis = tda0.getUserVisibility();
					operator = tda0.getOperatorVisibility();
					admin = tda0.getAdminVisibility();
				}
			}
			else
			{
				uservis = tda.getUserVisibility();
				operator = tda.getOperatorVisibility();
				admin = tda.getAdminVisibility();
			}
			String user = Security.getCurrentUser();
			if (user != null)
			{
				AccountEntity account = getAccount();
				if (account.getType().equals(AccountType.USER))
				{
					for (UserAccountEntity uac: account.getUsers())
					{
						if (uac.getUser() != null && uac.getUser().getUserName().equals(user))
						{
							return uservis == null ? AttributeVisibilityEnum.HIDDEN: uservis;
						}
					}
				}
			}
			AuthorizationService autService = ServiceLocator.instance().getAuthorizationService();
			if (Security.isUserInRole(Security.AUTO_AUTHORIZATION_ALL))
				v =  admin == null ? AttributeVisibilityEnum.EDITABLE: admin;
			else if (Security.isUserInRole(Security.AUTO_ACCOUNT_ATTRIBUTE_UPDATE))
				v = operator == null ? AttributeVisibilityEnum.EDITABLE: operator;
			else if (Security.isUserInRole(Security.AUTO_ACCOUNT_ATTRIBUTE_QUERY))
			{
				v = tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.READONLY: operator;
				if (AttributeVisibilityEnum.EDITABLE.equals (v))
					v = AttributeVisibilityEnum.READONLY;
			}
			else
				v = AttributeVisibilityEnum.HIDDEN;
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

	@Override
	public void setObjectValue(Object value) {
		AttributeParser ap = getSystemMetadata() == null ?
				new AttributeParser(getMetadata().getName(), getMetadata().getType(), value) :
				new AttributeParser(getSystemMetadata().getName(), getSystemMetadata().getType(), value);
		setValue(ap.getValue());
		setBlobDataValue(ap.getBlobValue());
	}

	@Override
	public Object getObjectValue() {
		return getSystemMetadata() == null?
				AttributeParser.getObjectValue( getMetadata().getType(), getValue(), getBlobDataValue()) :
				AttributeParser.getObjectValue( getSystemMetadata().getType(), getValue(), getBlobDataValue());
	}



}
