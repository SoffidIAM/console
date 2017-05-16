package com.soffid.iam.api;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Criteria;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
@Criteria
public class TenantCriteria {
	@Nullable
	public java.lang.String name;

	@Nullable
	public String description;
}
