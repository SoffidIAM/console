package com.soffid.iam.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.util.Base64;

public class AttributeParser {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd"); //$NON-NLS-1$
	private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss"); //$NON-NLS-1$
	private static final SimpleDateFormat DATETIME_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss"); //$NON-NLS-1$
	String value;
	byte[] blobValue;
	
	public AttributeParser (String attributeName, TypeEnumeration type, Object value) {
		if (value == null || value.equals(""))
		{
			value = null;
			blobValue = null;
		} 
		else if (type.equals( TypeEnumeration.BINARY_TYPE) ||
				type.equals( TypeEnumeration.HTML) ||
				type.equals( TypeEnumeration.PHOTO_TYPE))
		{
			value = null;
			if (value instanceof byte[])
				blobValue = (byte[]) value;
			else
				blobValue = Base64.decode(value.toString());
		}
		else if (type.equals( TypeEnumeration.DATE_TYPE) ||
				type.equals( TypeEnumeration.DATE_TIME_TYPE))
		{
			if (value instanceof Calendar)
				value = DATETIME_FORMAT2.format(((Calendar) value).getTime());
			else if (value instanceof Date)
				value = DATETIME_FORMAT2.format((Date) value);
			else if (type.equals(TypeEnumeration.DATE_TYPE)) {
				try {
					value = ( DATETIME_FORMAT2.format(DATE_FORMAT.parse(value.toString())));
				} catch (ParseException e2) { 
					throw new RuntimeException("Bad date format for attribute "+attributeName+": "+value.toString(), e2);
				}
			} else {
				try {
					value = ( DATETIME_FORMAT2.format(DATETIME_FORMAT2.parse(value.toString())));
				} catch (ParseException e) {
					try {
						value = ( DATETIME_FORMAT2.format(DATETIME_FORMAT.parse(value.toString())));
					} catch (ParseException e2) { 
						throw new RuntimeException("Bad date format for attribute "+attributeName+": "+value.toString(), e2);
					}
				}
			}
		}
		else
			value = value.toString();

	}

	public String getValue() {
		return value;
	}

	public byte[] getBlobValue() {
		return blobValue;
	}

	public static Object getObjectValue(TypeEnumeration type, String value2, byte[] blobDataValue) {
		if (type == null)
			return value2;
		else if (type.equals( TypeEnumeration.BINARY_TYPE) ||
				type.equals( TypeEnumeration.HTML) ||
				type.equals( TypeEnumeration.PHOTO_TYPE))
		{
			return blobDataValue;
		}
		else if (type.equals( TypeEnumeration.DATE_TYPE) ||
				type.equals( TypeEnumeration.DATE_TIME_TYPE))
		{
			if (value2 == null || value2.trim().isEmpty())
				return null;
			else
				try {
					return DATETIME_FORMAT2.parse(value2);
				} catch (ParseException e) {
					try {
						return DATETIME_FORMAT.parse(value2);
					} catch (ParseException e2) {
						return null;
					}
				}
		}
		else
			return value2;
	}
}
