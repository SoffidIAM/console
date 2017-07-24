package com.soffid.iam.json;

import org.apache.johnzon.mapper.Converter;

import java.lang.reflect.Type;
import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

public class CalendarConverter implements Converter<Calendar>,
	Converter.TypeAccess
{
	@Override
    public String toString(Calendar instance) {
        return DatatypeConverter.printDateTime(instance);
    }
 
	@Override
    public Calendar fromString(final String text) {
        return DatatypeConverter.parseDateTime(text);
    }

	@Override
	public Type type()
	{
		return Calendar.class;
	}
}
