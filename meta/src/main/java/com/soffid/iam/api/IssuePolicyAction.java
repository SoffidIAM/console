package com.soffid.iam.api;

import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class IssuePolicyAction {
	@Nullable @Attribute(hidden = true)
	Long id;
	
	String action;
	
	@Nullable
	String description;

	@Nullable
	String subject;
	
	@Nullable
	String body;
	
	@Nullable
	String emailAddress;
	
	@Nullable
	String processDefinition;
	
	@Nullable
	String script;
	
}
