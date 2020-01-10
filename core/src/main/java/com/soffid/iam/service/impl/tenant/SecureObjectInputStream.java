package com.soffid.iam.service.impl.tenant;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class SecureObjectInputStream extends ObjectInputStream {
	Class<?> validClasses [] = {
		int.class,
		char.class,
		byte.class,
		String.class,
		Integer.class,
		Date.class,
		Long.class,
		Double.class,
		Float.class,
		Boolean.class,
		String[].class,
		Number.class,
		Object[].class,
		Timestamp.class,
		java.util.Date.class,
		Calendar.class,
		byte[].class
	};
	public SecureObjectInputStream() throws IOException, SecurityException {
		super();
	}

	public SecureObjectInputStream(InputStream in) throws IOException {
		super(in);
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
		String className = desc.getName();
		for (Class cl: validClasses)
		{
			if (cl.getName().equals(className))
				return super.resolveClass(desc);
		}
		throw new ClassNotFoundException (desc.getName());
	}

}
