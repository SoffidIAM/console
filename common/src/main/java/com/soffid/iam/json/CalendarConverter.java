package com.soffid.iam.json;

import org.apache.johnzon.mapper.Converter;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarConverter implements Converter<Calendar>,
	Converter.TypeAccess
{
	static SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	@Override
    public String toString(Calendar instance) {
		if (instance == null)
			return "";
        return dt.format(instance.getTime());
    }
 
	@Override
    public Calendar fromString(final String text) {
		if (text == null || text.trim().isEmpty())
			return null;
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(dt.parse(text));
		} catch (ParseException e) {
			throw new RuntimeException("Unable to parse date "+text);
		}
		return c;
    }

	@Override
	public Type type()
	{
		return Calendar.class;
	}
}
