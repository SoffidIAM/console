package com.soffid.iam.model;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.util.Base64;

public class AttributeParser {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); //$NON-NLS-1$
	private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss"); //$NON-NLS-1$
	private static final SimpleDateFormat DATETIME_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //$NON-NLS-1$
	private static final SimpleDateFormat DATETIME_FORMAT3 = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss"); //$NON-NLS-1$
	private static final SimpleDateFormat DATETIME_FORMAT4 = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss"); //$NON-NLS-1$
	String value;
	byte[] blobValue;
	
	public AttributeParser (String attributeName, TypeEnumeration type, Object v) {
		if (v == null || v.equals(""))
		{
			value = null;
			blobValue = null;
		} 
		else if (type.equals( TypeEnumeration.BINARY_TYPE) ||
				type.equals( TypeEnumeration.PHOTO_TYPE))
		{
			value = null;
			if (v instanceof byte[])
				blobValue = (byte[]) v;
			else
				blobValue = Base64.decode(v.toString());
		}
		else if (type.equals( TypeEnumeration.HTML))
		{
			value = null;
			if (v instanceof byte[])
				blobValue = (byte[]) v;
			else
				try {
					blobValue = v.toString().getBytes("UTF-8");
				} catch (UnsupportedEncodingException e) {
					blobValue = v.toString().getBytes();
				}
		}
		else if (type.equals( TypeEnumeration.DATE_TYPE) ||
				type.equals( TypeEnumeration.DATE_TIME_TYPE))
		{
			if (v instanceof Calendar)
				value = DATETIME_FORMAT2.format(((Calendar) v).getTime());
			else if (v instanceof Date)
				value = DATETIME_FORMAT2.format((Date) v);
			else if (type.equals(TypeEnumeration.DATE_TYPE)) {
				try {
					value = ( DATETIME_FORMAT2.format(DATE_FORMAT.parse(v.toString())));
				} catch (ParseException e2) { 
					throw new RuntimeException("Bad date format for attribute "+attributeName+": "+v.toString(), e2);
				}
			} else {
				try {
					value = ( DATETIME_FORMAT2.format(DATETIME_FORMAT2.parse(v.toString())));
				} catch (ParseException e) {
					try {
						value = ( DATETIME_FORMAT2.format(DATETIME_FORMAT.parse(v.toString())));
					} catch (ParseException e2) { 
						try {
							value = ( DATETIME_FORMAT2.format(DATETIME_FORMAT3.parse(v.toString())));
							try {
								value = ( DATETIME_FORMAT2.format(DATETIME_FORMAT4.parse(v.toString())));
							} catch (ParseException e3) { 
								throw new RuntimeException("Bad date format for attribute "+attributeName+": "+v.toString(), e3);
							}
						} catch (ParseException e3) { 
							throw new RuntimeException("Bad date format for attribute "+attributeName+": "+v.toString(), e3);
						}
					}
				}
			}
		}
		else
			value = v.toString();

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
				type.equals( TypeEnumeration.PHOTO_TYPE))
		{
			return blobDataValue;
		}
		else if (type.equals( TypeEnumeration.HTML) )
		{
			if (blobDataValue == null)
				return value2;
			try {
				return new String(blobDataValue, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				return new String(blobDataValue);
			}
		}
		else if (type.equals( TypeEnumeration.DATE_TYPE) ||
				type.equals( TypeEnumeration.DATE_TIME_TYPE))
		{
			if (value2 == null || value2.trim().isEmpty())
				return null;
			else
				try {
					return DATETIME_FORMAT2.parse(value2);
				} catch (Exception e) {
					try {
						return DATETIME_FORMAT.parse(value2);
					} catch (Exception e2) {
						try {
							return DATETIME_FORMAT3.parse(value2);
						} catch (Exception e3) { 
							try {
								return DATETIME_FORMAT4.parse(value2);
							} catch (Exception e4) { 
								return null;
							}
						}
					}
				}
		}
		else
			return value2;
	}
}
