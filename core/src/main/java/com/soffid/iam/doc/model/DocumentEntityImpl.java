package com.soffid.iam.doc.model;

public class DocumentEntityImpl extends DocumentEntity {

	@Override
	public String getApplication() {
		return getFsPath().split("/")[2];
	}

	@Override
	public int getYear() {
		String s = getFsPath().split("/")[3];
		return Integer.parseInt(s);
	}

}
