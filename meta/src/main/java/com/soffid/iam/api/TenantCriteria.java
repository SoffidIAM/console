package com.soffid.iam.api;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Criteria;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
@Criteria
public class TenantCriteria {
	public java.lang.String name;

	public String description;
}
