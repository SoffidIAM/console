package com.soffid.iam.doc.model;

public class DocumentEntityImpl extends DocumentEntity {

	@Override
	public String getApplication() {
		return getFsPath().split("/")[1];
	}

	@Override
	public int getYear() {
		String s = getFsPath().split("/")[2];
		return Integer.parseInt(s);
	}

}
