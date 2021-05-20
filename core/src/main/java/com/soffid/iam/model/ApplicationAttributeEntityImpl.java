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
 * Entity ApplicationAttributeEntity implementation
 */
public class ApplicationAttributeEntityImpl extends com.soffid.iam.model.ApplicationAttributeEntity {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss"); //$NON-NLS-1$
	private static final SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss"); //$NON-NLS-1$

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
				setValue( DATE_FORMAT2.format(((Calendar) value).getTime()));
			else if (value instanceof Date)
				setValue( DATE_FORMAT2.format((Date) value));
			else {
				try {
					setValue( DATE_FORMAT2.format(DATE_FORMAT2.parse(value.toString())));
				} catch (ParseException e) {
					try {
						setValue( DATE_FORMAT2.format(DATE_FORMAT.parse(value.toString())));
					} catch (ParseException e2) { 
						throw new RuntimeException("Bad date format for attribute "+getMetadata().getName()+": "+value.toString(), e2);
					}
				}
			}
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
					return DATE_FORMAT2.parse(getValue());
				} catch (ParseException e) {
					try {
						return DATE_FORMAT.parse(getValue());
					} catch (ParseException e2) {
						return null;
					}
				}
		}
		else
			return getValue();
	}


}
