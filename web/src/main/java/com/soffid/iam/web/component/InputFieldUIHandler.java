package com.soffid.iam.web.component;


public class InputFieldUIHandler {
	public void beforeCreate(InputField3 field) throws Exception {}
	public void afterCreate(InputField3 field) throws Exception {}
	public boolean validate (InputField3 field) throws Exception {return true;}
	public boolean isVisible (InputField3 field) throws Exception {return true;}
	public Object translateToUserInterface (InputField3 field, Object o) throws Exception {return o;}
	public Object translateFromUserInterface (InputField3 field, Object o) throws Exception {return o;}
	public void onChange(InputField3 inputField3) throws Exception {}
}
