package com.soffid.iam.api;

import java.util.List;

import com.soffid.iam.model.IssuePolicyEntity;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
@JsonObject(hibernateClass = IssuePolicyEntity.class)
public class IssuePolicy {
	@Attribute(hidden = true)
	Long id;
	
	String type;

	@Nullable 
	String description;
	
	@Nullable
	String actor;
	
	@Nullable
	List<IssuePolicyAction> actions;
}
