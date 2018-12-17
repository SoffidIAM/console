package com.soffid.iam.doc.nas.comm;

import java.io.File;

public class DoNotDeleteFile extends File {

	public DoNotDeleteFile(File archivo) {
		super (archivo.getPath());
	}

	@Override
	public boolean delete() {
		// Do nothing
		return true;
	}

	@Override
	public void deleteOnExit() {
		// Do nothing
	}
	
}
