package com.soffid.iam.json;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.apache.johnzon.mapper.Converter;

public class DateConverter implements Converter<Date>, Converter.TypeAccess
{
	@Override
	public String toString(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return DatatypeConverter.printDateTime(calendar);
	}

	@Override
	public Date fromString(String text) {
		return DatatypeConverter.parseDateTime(text).getTime();
	}

	@Override
	public Type type() {
		return Date.class;
	}
}
