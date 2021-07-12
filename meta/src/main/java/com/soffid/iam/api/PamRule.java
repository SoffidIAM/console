package com.soffid.iam.api;

import java.util.Date;
import java.util.List;

import com.soffid.iam.model.PamPolicyEntity;
import com.soffid.iam.model.PamRuleEntity;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.JsonAttribute;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
@JsonObject(hibernateClass = PamRuleEntity.class)
public class PamRule {
	@Nullable
	Long id;
	
	String name;
	
	@Nullable
	String description;

	PamRuleType type;
	
	String content;

	@Nullable
	@Attribute(readonly = true, type = "USER")
	String author;
	
	@Nullable
	@Attribute(readonly = true, type = "DATE_TIME")
	Date date;
	
}
