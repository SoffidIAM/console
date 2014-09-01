package com.soffid.iam.api;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class AttributeTranslation {
	@Nullable
	@Identifier
	public java.lang.Long id;

	
	public java.lang.String domain;
	
	public java.lang.String column1;

	@Nullable
	public java.lang.String column2;

	@Nullable
	public java.lang.String column3;

	@Nullable
	public java.lang.String column4;

	@Nullable
	public java.lang.String column5;
}


