package com.soffid.iam.web.component;


public interface InputFieldUIHandler {
	public void afterCreate(InputField3 field) throws Exception;
	public boolean validate (InputField3 field) throws Exception;
	public boolean isVisible (InputField3 field) throws Exception;
}
