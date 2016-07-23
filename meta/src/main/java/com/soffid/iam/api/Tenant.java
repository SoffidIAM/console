package com.soffid.iam.api;

import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class Tenant {
	@Nullable
	public java.lang.Long id;

	public java.lang.String name;

	public String description;
	
	@Attribute(defaultValue="true")
	public boolean enabled;
}
