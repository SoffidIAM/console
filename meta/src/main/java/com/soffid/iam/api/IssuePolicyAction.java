package com.soffid.iam.api;

import com.soffid.iam.model.IssuePolicyActionEntity;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
@JsonObject(hibernateClass = IssuePolicyActionEntity.class)
public class IssuePolicyAction {
	@Nullable @Attribute(hidden = true)
	Long id;
	
	String action;
	
	@Nullable
	String description;

	@Nullable
	@Attribute(defaultValue = "\"Soffid issue ${id}\"")
	String subject;
	
	@Nullable
	@Attribute(defaultValue = "\"${description}\"")
	String body;
	
	@Nullable
	String emailAddress;
	
	@Nullable
	String processDefinition;
	
	@Nullable
	String script;
	
}
