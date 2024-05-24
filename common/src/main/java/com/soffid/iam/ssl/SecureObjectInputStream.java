package com.soffid.iam.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class SecureObjectInputStream extends ObjectInputStream {
	String blackList[];
	public SecureObjectInputStream() throws IOException, SecurityException {
		super();
	}

	public SecureObjectInputStream(InputStream in) throws IOException {
		super(in);
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
		if (blackList == null)
		{
			String b = System.getProperty("soffid.serializer.blacklist");
			if (b == null) 
				b = "bsh. org.openjdk.nashorn.internal.";
			blackList = b.split(" ");
		}
		String className = desc.getName();
		for (String s: blackList)
			if (className.startsWith(s))
				throw new ClassNotFoundException (desc.getName());
		return super.resolveClass(desc);
	}

}
