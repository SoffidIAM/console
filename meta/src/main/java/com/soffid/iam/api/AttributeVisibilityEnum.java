package com.soffid.iam.api;

import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Enumeration;

@Enumeration
@Description("Defines the custom attribute visibility scope")
public class AttributeVisibilityEnum {
	public String HIDDEN = "H";
	public String READONLY = "R";
	public String EDITABLE = "E";
}
