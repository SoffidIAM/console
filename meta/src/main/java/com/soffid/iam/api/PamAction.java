package com.soffid.iam.api;

import java.util.Date;
import java.util.List;

import com.soffid.iam.model.PamActionType;
import com.soffid.iam.model.PamPolicyEntity;
import com.soffid.iam.model.PamRuleEntity;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class PamAction {
	@Attribute(readonly = true)
	String policyName;
	
	@Attribute(readonly = true)
	String ruleName;

	@Nullable
	List<PamActionType> actions;

	@Nullable
	@Attribute(readonly = true, type = "USER")
	String author;
	
	@Nullable
	@Attribute(readonly = true, type = "DATE_TIME")
	Date date;
}
