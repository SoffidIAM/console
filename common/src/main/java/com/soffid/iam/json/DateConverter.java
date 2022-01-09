package com.soffid.iam.json;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.johnzon.mapper.Converter;

public class DateConverter implements Converter<Date>, Converter.TypeAccess
{
	static SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	@Override
	public String toString(Date date) {
		if (date == null)
			return "";
		return dt.format(date);
	}

	@Override
	public Date fromString(String text) {
		if (text == null || text.trim().isEmpty())
			return null;
		try {
			return dt.parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Unable to parse date "+text);
		}
	}

	@Override
	public Type type() {
		return Date.class;
	}
}
