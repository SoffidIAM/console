package com.soffid.iam.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class RemoveOnCloseStream extends FileInputStream {
	private File file;

	public RemoveOnCloseStream(File f) throws FileNotFoundException {
		super(f);
		this.file = f;
	}
	
	@Override
	public void close() throws IOException {
		file.delete();
		super.close();
	}
}
