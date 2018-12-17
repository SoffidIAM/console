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
							if (uac.getUser() != null && uac.getUser().getUserName().equals(user))
							{
								return tda.getUserVisibility() == null ? AttributeVisibilityEnum.HIDDEN: tda.getUserVisibility();
							}
						}
					}
				}
				AuthorizationService autService = ServiceLocator.instance().getAuthorizationService();
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

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss"); //$NON-NLS-1$

	@Override
	public void setObjectValue(Object value) {
		if (value == null || value.equals(""))
		{
			setValue(null);
			setBlobDataValue(null);
		} 
		else if (getMetadata().getType().equals( TypeEnumeration.BINARY_TYPE) ||
				getMetadata().getType().equals( TypeEnumeration.HTML) ||
				getMetadata().getType().equals( TypeEnumeration.PHOTO_TYPE))
		{
			setBlobDataValue( (byte[]) value);
		}
		else if (getMetadata().getType().equals( TypeEnumeration.DATE_TYPE))
		{
			if (value instanceof Calendar)
				setValue( DATE_FORMAT.format(((Calendar) value).getTime()));
			else 
				setValue( DATE_FORMAT.format((Date) value));
		}
		else
			setValue(value.toString());
	}

	@Override
	public Object getObjectValue() {
		if (getMetadata().getType().equals( TypeEnumeration.BINARY_TYPE) ||
				getMetadata().getType().equals( TypeEnumeration.HTML) ||
				getMetadata().getType().equals( TypeEnumeration.PHOTO_TYPE))
		{
			return getBlobDataValue();
		}
		else if (getMetadata().getType().equals( TypeEnumeration.DATE_TYPE))
		{
			if (getValue() == null || getValue().trim().isEmpty())
				return null;
			else
				try {
					return DATE_FORMAT.parse(getValue());
				} catch (ParseException e) {
					return null;
				}
		}
		else
			return getValue();
	}



}
