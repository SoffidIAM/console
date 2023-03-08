package com.soffid.iam.api;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class DisableObjectRule {
	@Nullable String criteria;
	
	@Nullable Integer parameter;

	@Nullable String action;

	@Nullable String emailCopy;
	
	@Nullable String emailSubject;
	
	@Nullable String emailBody;
}
