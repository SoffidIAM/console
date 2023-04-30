package com.soffid.iam.api;

import java.util.List;

import com.soffid.iam.model.CustomObjectAttributeEntity;
import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
@JsonObject(hibernateClass=CustomObjectTypeEntity.class)
public class CustomObjectType {
	@Nullable
	public java.lang.Long id;

	public String name;
	
	public String description;

	@Attribute(defaultValue="MetadataScope.CUSTOM")
	@Nullable
	public MetadataScope scope;

	public boolean builtin;
	
	public boolean textIndex;

	@Nullable
	@Attribute(defaultValue = "true")
	public Boolean publicAccess;

	@Nullable
	List<String> managerRoles;
	
	@Nullable
	List<String> userRoles;
}
