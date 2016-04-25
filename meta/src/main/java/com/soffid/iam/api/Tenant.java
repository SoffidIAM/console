package com.soffid.iam.api;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class Tenant {
	@Identifier
	public java.lang.Long id;

	public java.lang.String name;

	public String description;
}
