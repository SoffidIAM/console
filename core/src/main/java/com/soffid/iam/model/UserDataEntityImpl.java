//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.model.security.SecurityScopeEntity;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.TypeEnumeration;

/**
 * Entity DadaUsuariEntity implementation
 */
public class UserDataEntityImpl extends com.soffid.iam.model.UserDataEntity
		implements SecurityScopeEntity {
	public AttributeVisibilityEnum getAttributeVisibility() {
		return getInitialVisibility();
	}

	private AttributeVisibilityEnum getInitialVisibility() {
		
		if (Security.isSyncServer())
			return AttributeVisibilityEnum.EDITABLE;

		if (Security.isUserInRole(Security.AUTO_METADATA_UPDATE_ALL))
			return AttributeVisibilityEnum.EDITABLE;

		MetaDataEntity tda = getDataType();
		if (tda == null)
			return AttributeVisibilityEnum.HIDDEN;

		String user = Security.getCurrentUser();
		if (user != null) {
			if (getUser().getUserName().equals(user))
				return tda.getUserVisibility() == null ? AttributeVisibilityEnum.HIDDEN
						: tda.getUserVisibility();
		}

		if (Security.isUserInRole(Security.AUTO_METADATA_UPDATE_ALL))
			return AttributeVisibilityEnum.EDITABLE;
		else if (Security.isUserInRole(Security.AUTO_AUTHORIZATION_ALL))
			return tda.getAdminVisibility() == null ? AttributeVisibilityEnum.EDITABLE
					: tda.getAdminVisibility();
		else if (getUser().isAllowed(Security.AUTO_USER_METADATA_UPDATE))
			return tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.EDITABLE
					: tda.getOperatorVisibility();
		else if (getUser().isAllowed(Security.AUTO_USER_QUERY)) {
			AttributeVisibilityEnum v = tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.READONLY
					: tda.getOperatorVisibility();
			if (AttributeVisibilityEnum.EDITABLE.equals(v))
				v = AttributeVisibilityEnum.READONLY;
			return v;
		} else
			return AttributeVisibilityEnum.HIDDEN;
	}

	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(Security.AUTO_AUTHORIZATION_ALL))
			return true;
		else if (permission.equals(Security.AUTO_USER_METADATA_UPDATE)) {
			return true;
		} else if (permission.equals(Security.AUTO_USER_QUERY)) {
			return getInitialVisibility().equals(
					AttributeVisibilityEnum.EDITABLE)
					|| getInitialVisibility().equals(
							AttributeVisibilityEnum.READONLY);
		} else
			return Security.isUserInRole(permission + Security.AUTO_ALL);
	}

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss"); //$NON-NLS-1$
	private static final SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //$NON-NLS-1$
	@Override
	public void setObjectValue(Object value) {
		if (value == null || value.equals(""))
		{
			setValue(null);
			setBlobDataValue(null);
		} 
		else if (getDataType().getType().equals( TypeEnumeration.BINARY_TYPE) ||
				getDataType().getType().equals( TypeEnumeration.HTML) ||
				getDataType().getType().equals( TypeEnumeration.PHOTO_TYPE))
		{
			setBlobDataValue( (byte[]) value);
		}
		else if (getDataType().getType().equals( TypeEnumeration.DATE_TYPE))
		{
			if (value instanceof Calendar)
				setValue( DATE_FORMAT2.format(((Calendar) value).getTime()));
			else if (value instanceof Date)
				setValue( DATE_FORMAT2.format((Date) value));
			else if (getDataType() == null)
				throw new ClassCastException("Error trying to set value "+value+" for attribute of type date");
			else
				throw new ClassCastException("Error trying to set value "+value+" for attribute "+getDataType().getName());
		}
		else
			setValue(value.toString());
	}

	@Override
	public Object getObjectValue() {
		if (getDataType().getType() == null)
			return getValue();
		else if (getDataType().getType().equals( TypeEnumeration.BINARY_TYPE) ||
				getDataType().getType().equals( TypeEnumeration.HTML) ||
				getDataType().getType().equals( TypeEnumeration.PHOTO_TYPE))
		{
			return getBlobDataValue();
		}
		else if (getDataType().getType().equals( TypeEnumeration.DATE_TYPE))
		{
			if (getValue() == null || getValue().trim().isEmpty())
				return null;
			else
				try {
					return DATE_FORMAT2.parse(getValue());
				} catch (Exception e) {
					try {
						return DATE_FORMAT.parse(getValue());
					} catch (Exception e2) {
						try {
							return AccountAttributeEntityImpl.DATE_FORMAT3.parse(getValue());
						} catch (Exception e3) {
							org.apache.commons.logging.LogFactory.getLog(getClass()).info("Error parsing date "+getValue()+" for attribute value "+getId());
							return null;
						}
					}
				}
		}
		else
			return getValue();
	}


}
