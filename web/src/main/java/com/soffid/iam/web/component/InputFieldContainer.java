package com.soffid.iam.web.component;

import java.util.Map;

public interface InputFieldContainer {
	public Map<String,InputField3> getInputFieldsMap() ;

	public Map getAttributesMap();

	public void adjustVisibility();
}
