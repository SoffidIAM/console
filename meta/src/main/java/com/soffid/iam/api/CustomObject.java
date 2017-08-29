package com.soffid.iam.api;

import java.util.Map;

import com.soffid.iam.model.CustomObjectEntity;
import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.JsonAttribute;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
@JsonObject(hibernateClass=CustomObjectEntity.class)
public class CustomObject {
	@Nullable
	public java.lang.Long id;

	public String name;
	
	public String description;

	public String type;

	public boolean builtin;

	@Description ("Role custom attributes")
	@JsonAttribute(hibernateJoin="attributes")
	@Nullable
	@Attribute(defaultValue="new java.util.HashMap<String,Object>()")
	public Map<String,Object> attributes; 
}
