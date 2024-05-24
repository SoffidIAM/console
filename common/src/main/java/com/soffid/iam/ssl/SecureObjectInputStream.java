package com.soffid.iam.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class SecureObjectInputStream extends ObjectInputStream {
	public SecureObjectInputStream() throws IOException, SecurityException {
		super();
	}

	public SecureObjectInputStream(InputStream in) throws IOException {
		super(in);
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
		String className = desc.getName();
		if ((className.startsWith("com.soffid.") && (className.contains(".api.") || className.contains(".common"))) ||
			(className.contains("es.caib.")  && (className.contains(".api.") || className.contains(".common"))) ||
			className.startsWith("java."))
			return super.resolveClass(desc);
		throw new ClassNotFoundException (desc.getName());
	}

}
