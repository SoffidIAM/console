package com.soffid.iam.api;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class IssueUser {
	EventUserAction action;
	
	Long userId;
	
	String userName;
	
	@Nullable String externalId;
}
