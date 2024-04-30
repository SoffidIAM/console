package com.soffid.iam.api;

import java.util.Date;
import java.util.List;

import com.soffid.iam.model.PamPolicyEntity;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
@JsonObject(hibernateClass = PamPolicyEntity.class)
public class PamPolicy {
	@Nullable
	Long id;
	
	String name;
	
	@Nullable
	String description;

	@Nullable
	@Attribute(readonly = true, type = "USER")
	String author;
	
	@Nullable
	@Attribute(readonly = true, type = "DATE_TIME")
	Date date;
	

	@Nullable
	Integer recordingDuration;

	@Nullable
	String expression;

	@Nullable
	Integer priority;
}
