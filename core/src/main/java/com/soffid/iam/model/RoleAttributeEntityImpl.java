//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.caib.seycon.ng.comu.TypeEnumeration;

/**
 * Entity RoleAttributeEntity implementation
 */
public class RoleAttributeEntityImpl extends com.soffid.iam.model.RoleAttributeEntity {
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
					Calendar c = Calendar.getInstance();
					c.setTime(DATE_FORMAT.parse(getValue()));
					return c;
				} catch (ParseException e) {
					return null;
				}
		}
		else
			return getValue();
	}


}
